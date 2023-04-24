package design.featuresliced.helper.util;

import design.featuresliced.helper.model.SegmentAsType;
import design.featuresliced.helper.model.SegmentType;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class SegmentUtil {

    public static final String UI_DEFAULT_CONTENT = "export const %s = () => {\n\treturn null;\n};";

    public static final String UI_DEFAULT_IMPORT_CONTENT = "export { %s } from './ui/%s';";

    public static void createDependsOnType(@NotNull SegmentType type, @NotNull SegmentAsType asType, @NotNull VirtualFile parent, boolean createIndexIfFolder) throws IOException {
        switch (type) {
            case MODEL:
                createModelDependsOnAsType(asType, parent, createIndexIfFolder);
        }
    }

    public static void createUiDependsOnAsType(@NotNull String sliceName, @NotNull SegmentAsType asType, @NotNull VirtualFile parent) throws IOException {
        switch (asType) {
            case FILE:
                writeContentToFile(
                        createFile(SegmentType.UI.getFileName(), parent),
                        String.format(UI_DEFAULT_CONTENT, sliceName)
                );
                break;
            case FOLDER:
                VirtualFile newEntityUiDir = createDirectory(SegmentType.UI.getFolderName(), parent);
                writeContentToFile(
                        createFile(sliceName + ".tsx", newEntityUiDir),
                        String.format(UI_DEFAULT_CONTENT, sliceName)
                );
                break;
        }
    }

    public static void createLibDependsOnAsType(@NotNull SegmentAsType asType, @NotNull VirtualFile parent) throws IOException {
        switch (asType) {
            case FILE:
                SegmentUtil.createFile(SegmentType.LIB.getFileName(), parent);
                break;
            case FOLDER:
                SegmentUtil.createDirectory(SegmentType.LIB.getFolderName(), parent);
                break;
        }
    }

    public static void createModelDependsOnAsType(@NotNull SegmentAsType asType, @NotNull VirtualFile parent) throws IOException {
        createModelDependsOnAsType(asType, parent, true);
    }

    public static void createModelDependsOnAsType(@NotNull SegmentAsType asType, @NotNull VirtualFile parent, boolean createIndexIfFolder) throws IOException {
        switch (asType) {
            case FILE:
                SegmentUtil.createFile(SegmentType.MODEL.getFileName(), parent);
                break;
            case FOLDER:

                VirtualFile modelFolder = SegmentUtil.createDirectory(SegmentType.MODEL.getFolderName(), parent);

                if (createIndexIfFolder) {
                    SegmentUtil.createFile("index.tsx", modelFolder);
                }

                break;
        }
    }

    public static VirtualFile createFile(@NotNull String name, @NotNull VirtualFile parent) throws IOException {
        return parent.createChildData(SegmentUtil.class, name);
    }

    public static VirtualFile createDirectory(@NotNull String name, @NotNull VirtualFile parent) throws IOException {
        return parent.createChildDirectory(SegmentUtil.class, name);
    }

    public static void writeContentToFile(@NotNull VirtualFile file, @NotNull String content) throws IOException {
        file.setBinaryContent(content.getBytes(StandardCharsets.UTF_8));
    }

}
