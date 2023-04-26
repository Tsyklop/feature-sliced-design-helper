package design.featuresliced.helper.ui.dialog.shared;

import com.intellij.ide.util.EditorHelper;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiBinaryFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.refactoring.copy.CopyHandler;
import design.featuresliced.helper.model.FileLibraryType;
import design.featuresliced.helper.model.SegmentType;
import design.featuresliced.helper.ui.form.shared.DefaultSharedForm;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.NotifyUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NewTypeSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewTypeSharedDialog(@NotNull Project project) {
        super("New TypeScript Type", new DefaultSharedForm(), project);
        init();
        initValidation();
    }

    @Override
    protected void doOKAction() {

        FileLibraryType fileLibrary = FileUtil.determineProjectFileLibrary(project);

        CommandProcessor.getInstance().executeCommand(project, () -> {

            final String componentName = this.form.getName();

            final VirtualFile projectRoot = ProjectUtil.guessProjectDir(this.project);

            try {

                VirtualFile createdComponentFile = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                    final VirtualFile sharedUiDir = VfsUtil.createDirectoryIfMissing(projectRoot, buildPathToSegment(SegmentType.TYPE));

                    VirtualFile componentDir = VfsUtil.createDirectoryIfMissing(sharedUiDir, componentName);

                    return FileUtil.createFile(fileLibrary.withUsualExt(componentName), componentDir);

                });

                PsiFile psiComponentFile = PsiManager.getInstance(project).findFile(createdComponentFile);
                psiComponentFile.navigate(true);
                openInEditorIfSelected(psiComponentFile);

                NotifyUtil.show(this.project, "Shared TypeScript Type created!", NotificationType.INFORMATION);

                super.doOKAction();

            } catch (IOException e) {
                NotifyUtil.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
            }

        }, "Create New TypeScript Type in Shared Layer", null);

    }

}