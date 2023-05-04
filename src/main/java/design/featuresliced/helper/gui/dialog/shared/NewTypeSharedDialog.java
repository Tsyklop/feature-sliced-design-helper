package design.featuresliced.helper.gui.dialog.shared;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.model.type.JsLibraryExtensionsType;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import design.featuresliced.helper.gui.form.fsd.shared.DefaultSharedForm;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.JsLibraryUtil;
import design.featuresliced.helper.util.NotifyUtil;
import design.featuresliced.helper.util.PsiUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NewTypeSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewTypeSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New TypeScript Type", new DefaultSharedForm(), project, jsLibrary);
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

                    final VirtualFile sharedUiDir = VfsUtil.createDirectoryIfMissing(projectRoot, buildPathToSegment(SegmentType.TYPE));

                    VirtualFile componentDir = VfsUtil.createDirectoryIfMissing(sharedUiDir, componentName);

                    return FileUtil.createFile(jsLibraryExtensions.withUsualExt(componentName), componentDir);

                });

                openInEditorIfSelected(PsiUtil.findPsiFileAndNavigate(project, createdComponentFile));

                NotifyUtil.show(this.project, "Shared TypeScript Type created!", NotificationType.INFORMATION);

                super.doOKAction();

            } catch (IOException e) {
                NotifyUtil.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
            }

        }, "Create New TypeScript Type in Shared Layer", null);

    }

}