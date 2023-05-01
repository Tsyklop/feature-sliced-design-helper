package design.featuresliced.helper.ui.dialog.slice;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.model.JsLibraryExtensionsType;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.ui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.ui.form.slice.BaseSliceForm;
import design.featuresliced.helper.util.FileTemplateUtil;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.JsLibraryUtil;
import design.featuresliced.helper.util.NotifyUtil;
import design.featuresliced.helper.util.PsiUtil;
import design.featuresliced.helper.util.SegmentUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

public abstract class BaseSliceDialog<F extends BaseSliceForm> extends BaseDialogWrapper<F> {

    public BaseSliceDialog(@NotNull String title,
                           @NotNull F form,
                           @NotNull Project project,
                           @NotNull JsLibraryType jsLibrary,
                           @NotNull FsdLayerType fsdLayerType) {
        super(title, form, project, jsLibrary, fsdLayerType);
        init();
        initValidation();
    }

    @Override
    protected void doOKAction() {

        final String sliceName = this.form.getName();

        final VirtualFile projectRoot = ProjectUtil.guessProjectDir(this.project);

        final JsLibraryExtensionsType jsLibraryExtensions = JsLibraryUtil.resolveLibraryExtension(jsLibrary, projectRoot);

        CommandProcessor.getInstance().executeCommand(project, () -> {

            try {

                VirtualFile createdSliceDir = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                    VirtualFile fsdLayerDir = VfsUtil.createDirectoryIfMissing(projectRoot, buildPathToLayer());

                    VirtualFile newSliceDir = FileUtil.createDirectory(sliceName, fsdLayerDir);

                    if (this.form.isCreateUiSegment()) {

                        if (this.form.isCreateStyle()) {
                            SegmentUtil.createUiDependsOnAsTypeWithStyles(
                                    sliceName,
                                    this.form.getUiSegmentAsType(),
                                    this.form.getStyleType(),
                                    jsLibraryExtensions,
                                    newSliceDir
                            );
                        } else {
                            SegmentUtil.createUiDependsOnAsType(sliceName, this.form.getUiSegmentAsType(), jsLibraryExtensions, newSliceDir);
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

                    String newSliceIndexName = getFsdLayerType() == FsdLayerType.PAGES
                            ? jsLibraryExtensions.withComponentExt("index")
                            : jsLibraryExtensions.withUsualExt("index");

                    // index.(js|ts|tsx|jsx)
                    VirtualFile newSliceIndexFile = FileUtil.createFile(newSliceIndexName, newSliceDir);

                    if (form.isCreateUiSegment()) {

                        String template = null;

                        switch (form.getUiSegmentAsType()) {
                            case FILE:
                                template = FileTemplateUtil.uiComponentExportFromFileTemplateBy(jsLibraryExtensions);
                                break;
                            case FOLDER:
                                template = FileTemplateUtil.uiComponentExportFromFolderTemplateBy(jsLibraryExtensions);
                                break;
                        }

                        if (template != null) {
                            FileUtil.writeContentToFile(newSliceIndexFile, FileTemplateUtil.fillTemplate(template, Map.of("sliceName", sliceName)));
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
