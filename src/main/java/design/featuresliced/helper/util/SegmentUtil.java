package design.featuresliced.helper.util;

import design.featuresliced.helper.model.SegmentAsType;
import design.featuresliced.helper.model.SegmentType;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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
                FileUtil.writeContentToFile(
                        FileUtil.createFile(SegmentType.UI.getFileName(), parent),
                        String.format(UI_DEFAULT_CONTENT, sliceName)
                );
                break;
            case FOLDER:
                VirtualFile newEntityUiDir = FileUtil.createDirectory(SegmentType.UI.getFolderName(), parent);
                FileUtil.writeContentToFile(
                        FileUtil.createFile(sliceName + ".tsx", newEntityUiDir),
                        String.format(UI_DEFAULT_CONTENT, sliceName)
                );
                break;
        }
    }

    public static void createLibDependsOnAsType(@NotNull SegmentAsType asType, @NotNull VirtualFile parent) throws IOException {
        switch (asType) {
            case FILE:
                FileUtil.createFile(SegmentType.LIB.getFileName(), parent);
                break;
            case FOLDER:
                FileUtil.createDirectory(SegmentType.LIB.getFolderName(), parent);
                break;
        }
    }

    public static void createModelDependsOnAsType(@NotNull SegmentAsType asType, @NotNull VirtualFile parent) throws IOException {
        createModelDependsOnAsType(asType, parent, true);
    }

    public static void createModelDependsOnAsType(@NotNull SegmentAsType asType, @NotNull VirtualFile parent, boolean createIndexIfFolder) throws IOException {
        switch (asType) {
            case FILE:
                FileUtil.createFile(SegmentType.MODEL.getFileName(), parent);
                break;
            case FOLDER:

                VirtualFile modelFolder = FileUtil.createDirectory(SegmentType.MODEL.getFolderName(), parent);

                if (createIndexIfFolder) {
                    FileUtil.createFile("index.tsx", modelFolder);
                }

                break;
        }
    }

}
