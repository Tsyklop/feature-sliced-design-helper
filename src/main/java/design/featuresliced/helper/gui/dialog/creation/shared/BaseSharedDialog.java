package design.featuresliced.helper.gui.dialog.creation.shared;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.gui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.gui.form.fsd.shared.BaseSharedForm;
import design.featuresliced.helper.model.type.JsLibraryExtensionsType;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import design.featuresliced.helper.service.ProjectGeneralService;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.JsLibraryUtil;
import design.featuresliced.helper.util.NotifyUtil;
import design.featuresliced.helper.util.PsiUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public abstract class BaseSharedDialog<F extends BaseSharedForm> extends BaseDialogWrapper<F> {

    protected final SegmentType segment;

    public BaseSharedDialog(@NotNull String title, @NotNull F form, @NotNull Project project, @NotNull SegmentType segment, @NotNull JsLibraryType jsLibrary) {
        super(title, form, project, jsLibrary, LayerType.SHARED);
        this.segment = segment;
    }

    @Override
    protected void doOKAction() {

        ProjectGeneralService projectGeneralService = ProjectGeneralService.getInstance(project);

        final String componentName = this.form.getName();

        final VirtualFile sourcesRoot = projectGeneralService.getSourcesRoot();

        final JsLibraryExtensionsType jsLibraryExtensions = JsLibraryUtil.resolveLibraryExtension(jsLibrary, sourcesRoot);

        CommandProcessor.getInstance().executeCommand(project, () -> {

            try {

                VirtualFile createdComponentFile = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                    final VirtualFile segmentDirInSharedLayerDir = VfsUtil.createDirectoryIfMissing(
                            sourcesRoot,
                            StringUtils.joinWith(
                                    "/",
                                    getFsdLayerCustomOrDefaultName(),
                                    this.segment.getFolderName()
                            )
                    );

                    VirtualFile componentDir = VfsUtil.createDirectoryIfMissing(segmentDirInSharedLayerDir, componentName);

                    return FileUtil.createFile(jsLibraryExtensions.withUsualExt(componentName), componentDir);

                });

                openInEditorIfSelected(PsiUtil.findPsiFileAndNavigate(project, createdComponentFile));

                NotifyUtil.show(this.project, "Shared " + segment.getLabel() + " created!", NotificationType.INFORMATION);

                super.doOKAction();

            } catch (IOException e) {
                NotifyUtil.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
            }

        }, "Create New " + segment.getLabel() + " in Shared Layer", null);

    }

}
