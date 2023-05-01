package design.featuresliced.helper.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

public class PsiUtil {

    public static PsiFile findPsiFile(@NotNull Project project, @NotNull VirtualFile file) {
        return PsiManager.getInstance(project).findFile(file);
    }

    public static PsiDirectory findPsiDirectory(@NotNull Project project, @NotNull VirtualFile file) {
        return PsiManager.getInstance(project).findDirectory(file);
    }

    public static PsiFile findPsiFileAndNavigate(@NotNull Project project, @NotNull VirtualFile file) {
        PsiFile psiFile = findPsiFile(project, file);
        if (psiFile != null) {
            psiFile.navigate(false);
        }
        return psiFile;
    }

    public static PsiDirectory findPsiDirectoryAndNavigate(@NotNull Project project, @NotNull VirtualFile file) {
        PsiDirectory psiDirectory = findPsiDirectory(project, file);
        if (psiDirectory != null) {
            psiDirectory.navigate(false);
        }
        return psiDirectory;
    }

}
