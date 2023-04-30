package design.featuresliced.helper.ui.dialog.shared;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import design.featuresliced.helper.model.JsLibraryExtensionsType;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.model.SegmentType;
import design.featuresliced.helper.ui.form.shared.DefaultSharedForm;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.JsLibraryUtil;
import design.featuresliced.helper.util.NotifyUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NewAssetSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewAssetSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New Asset", new DefaultSharedForm(), project, jsLibrary);
        init();
        initValidation();
    }

    @Override
    protected void doOKAction() {

        final String componentName = this.form.getName();

        final VirtualFile projectRoot = ProjectUtil.guessProjectDir(this.project);

        final JsLibraryExtensionsType jsLibraryExtensions = JsLibraryUtil.resolveLibraryExtension(jsLibrary, projectRoot);

        CommandProcessor.getInstance().executeCommand(project, () -> {

            try {

                VirtualFile createdComponentFile = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                    final VirtualFile sharedUiDir = VfsUtil.createDirectoryIfMissing(projectRoot, buildPathToSegment(SegmentType.ASSETS));

                    final VirtualFile componentDir = VfsUtil.createDirectoryIfMissing(sharedUiDir, componentName);

                    return FileUtil.createFile(jsLibraryExtensions.withUsualExt(componentName), componentDir);

                });

                openInEditorIfSelected(findPsiFileAndNavigate(createdComponentFile));

                NotifyUtil.show(this.project, "Shared Asset created!", NotificationType.INFORMATION);

                super.doOKAction();

            } catch (IOException e) {
                NotifyUtil.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
            }

        }, "Create New Asset in Shared Layer", null);

    }

}
