package design.featuresliced.helper.util;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

    public static void prependContentToFile(@NotNull VirtualFile file, @NotNull String content) throws IOException {
        file.setBinaryContent((content + new String(file.contentsToByteArray())).getBytes(StandardCharsets.UTF_8));
    }

}
