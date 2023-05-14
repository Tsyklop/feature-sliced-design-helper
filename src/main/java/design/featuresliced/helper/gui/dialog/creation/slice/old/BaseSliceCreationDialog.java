package design.featuresliced.helper.gui.dialog.creation.slice.old;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.gui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.gui.form.fsd.slice.old.BaseSliceForm;
import design.featuresliced.helper.model.settings.ProjectGeneralSettings;
import design.featuresliced.helper.model.type.JsLibraryExtensionsType;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.service.ProjectGeneralService;
import design.featuresliced.helper.util.FileTemplateUtil;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.JsLibraryUtil;
import design.featuresliced.helper.util.NameUtil;
import design.featuresliced.helper.util.NotifyUtil;
import design.featuresliced.helper.util.PsiUtil;
import design.featuresliced.helper.util.SegmentUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public abstract class BaseSliceCreationDialog<F extends BaseSliceForm> extends BaseDialogWrapper<F> {

    public BaseSliceCreationDialog(@NotNull String title,
                                   @NotNull F form,
                                   @NotNull Project project,
                                   @NotNull JsLibraryType jsLibrary,
                                   @NotNull LayerType layerType) {
        super(title, form, project, jsLibrary, layerType);
        init();
        initValidation();
    }

    @Override
    protected void doOKAction() {

        ProjectGeneralService projectGeneralService = ProjectGeneralService.getInstance(project);

        ProjectGeneralSettings projectGeneralSettings = projectGeneralService.getState();

        // [user] or [auth, login-form] e.t.c
        final String[] slicePaths = this.form.getName().replace("\\", "/").split("/");

        // user or login-form
        final String sliceName = slicePaths[slicePaths.length - 1];

        final String sliceNameForUi = NameUtil.normalizeNameForUiIfNeed(sliceName);

        final VirtualFile sourcesRoot = projectGeneralService.getSourcesRoot();

        final JsLibraryExtensionsType jsLibraryExtensions = JsLibraryUtil.resolveLibraryExtension(jsLibrary, sourcesRoot);

        CommandProcessor.getInstance().executeCommand(project, () -> {

            try {

                VirtualFile createdSliceDir = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                    // $SOURCES_ROOT$/entities or $SOURCES_ROOT$/<custom name>
                    VirtualFile fsdLayerDir = VfsUtil.createDirectoryIfMissing(
                            sourcesRoot,
                            projectGeneralSettings.getLayerCustomFolderNameByOrDefault(getFsdLayerType())
                    );

                    VirtualFile newSliceDir = null;

                    if (slicePaths.length > 1) {
                        for (String path: slicePaths) {

                            VirtualFile pathFile = fsdLayerDir.findChild(path);

                            if (pathFile != null) {
                                newSliceDir = pathFile;
                            } else {
                                newSliceDir = FileUtil.createDirectory(path, Objects.requireNonNullElse(newSliceDir, fsdLayerDir));
                            }
                        }
                    } else {
                        // $SOURCES_ROOT$/entities/user or $SOURCES_ROOT$/<custom name>/user
                        newSliceDir = FileUtil.createDirectory(sliceName, fsdLayerDir);
                    }

                    if (this.form.isCreateUiSegment()) {

                        if (this.form.isCreateStyle()) {
                            SegmentUtil.createUiDependsOnAsTypeWithStyles(
                                    sliceNameForUi,
                                    this.form.getUiSegmentAsType(),
                                    this.form.getStyleType(),
                                    jsLibraryExtensions,
                                    newSliceDir
                            );
                        } else {
                            SegmentUtil.createUiDependsOnAsType(sliceNameForUi, this.form.getUiSegmentAsType(), jsLibraryExtensions, newSliceDir);
                        }

                    }

                    if (this.form.isCreateLibSegment()) {
                        SegmentUtil.createLibDependsOnAsType(this.form.getLibSegmentAsType(), jsLibraryExtensions, newSliceDir);
                    }

                    if (this.form.isCreateApiSegment()) {
                        SegmentUtil.createApiDependsOnAsType(this.form.getApiSegmentAsType(), jsLibraryExtensions, newSliceDir);
                    }

                    if (this.form.isCreateModelSegment()) {
                        SegmentUtil.createModelDependsOnAsType(this.form.getModelSegmentAsType(), jsLibraryExtensions, newSliceDir);
                    }

                    String newSliceIndexName = getFsdLayerType() == LayerType.PAGES
                            ? jsLibraryExtensions.withComponentExt("index")
                            : jsLibraryExtensions.withUsualExt("index");

                    // index.(js|ts|tsx|jsx)
                    VirtualFile newSliceIndexFile = FileUtil.createFile(newSliceIndexName, newSliceDir);

                    if (form.isCreateUiSegment()) {

                        String template = switch (form.getUiSegmentAsType()) {
                            case FILE -> FileTemplateUtil.uiComponentExportFromFileTemplateBy(jsLibraryExtensions);
                            case FOLDER -> FileTemplateUtil.uiComponentExportFromFolderTemplateBy(jsLibraryExtensions);
                        };

                        if (template != null) {
                            FileUtil.writeContentToFile(newSliceIndexFile, FileTemplateUtil.fillTemplate(template, Map.of("sliceName", sliceNameForUi)));
                        }

                    }

                    return newSliceDir;

                });

                PsiUtil.findPsiDirectoryAndNavigate(project, createdSliceDir);

                NotifyUtil.show(this.project, "Slice '" + sliceName + "' created!", NotificationType.INFORMATION);

                super.doOKAction();

            } catch (IOException e) {
                Messages.showErrorDialog(project, "Error occurred: " + e.getMessage(), "Feature Sliced Design Error");
            }

        }, "Create slice " + sliceName, null);

    }

}
