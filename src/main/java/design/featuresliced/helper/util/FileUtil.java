package design.featuresliced.helper.util;

import com.intellij.lang.javascript.TypeScriptFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import design.featuresliced.helper.model.FileLibraryType;
import design.featuresliced.helper.model.FileType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collection;

public class FileUtil {

    public static VirtualFile createFile(@NotNull String name, @NotNull VirtualFile parent) throws IOException {
        return parent.createChildData(SegmentUtil.class, name);
    }

    public static VirtualFile createDirectory(@NotNull String name, @NotNull VirtualFile parent) throws IOException {
        return parent.createChildDirectory(SegmentUtil.class, name);
    }

    public static void writeContentToFile(@NotNull VirtualFile file, @NotNull String content) throws IOException {
        file.setBinaryContent(content.getBytes(StandardCharsets.UTF_8));
    }

    public static FileLibraryType determineProjectFileLibrary(@NotNull Project project) {

        VirtualFile projectDir = ProjectUtil.guessProjectDir(project);

        VirtualFile projectSrcDir = VfsUtil.findFile(Path.of(projectDir.getPath(), "src"), true);

        VirtualFile tsconfigFile = VirtualFileManager.getInstance().findFileByNioPath(Path.of(projectDir.getPath(), "tsconfig.json"));

        if (!FilenameIndex.getAllFilesByExt(project, "vue", GlobalSearchScope.fileScope(project, projectSrcDir)).isEmpty()) {
            if (tsconfigFile != null) {
                return FileLibraryType.VUE_TS;
            }
            return FileLibraryType.VUE;
        } else if (tsconfigFile != null) {
            return FileLibraryType.TYPE_SCRIPT;
        } else {
            return FileLibraryType.JS;
        }

    }

    /*public static boolean determineFileTypeSupport(FileLibraryType type) {
        return
    }*/

}
