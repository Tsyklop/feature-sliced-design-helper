package design.featuresliced.helper.util;

import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.model.ComponentStyleType;
import design.featuresliced.helper.model.JsLibraryExtensionsType;
import design.featuresliced.helper.model.SegmentAsType;
import design.featuresliced.helper.model.SegmentType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

public final class SegmentUtil {

    public static void createUiDependsOnAsType(@NotNull String sliceName,
                                                               @NotNull SegmentAsType asType,
                                                               @NotNull JsLibraryExtensionsType jsLibraryExtensions,
                                                               @NotNull VirtualFile parent) throws IOException {
        switch (asType) {
            case FILE:
                // ui.(jsx|tsx|vue) as file
                VirtualFile uiFile = FileUtil.createFile(jsLibraryExtensions.withComponentExt(SegmentType.UI.getFileName()), parent);
                // write file template to ui file
                FileUtil.writeContentToFile(
                        uiFile,
                        FileTemplateUtil.fillTemplate(FileTemplateUtil.uiComponentTemplateBy(jsLibraryExtensions), Map.of("sliceName", sliceName))
                );
                break;
            case FOLDER:
                // ui as folder
                VirtualFile newEntityUiDir = FileUtil.createDirectory(SegmentType.UI.getFolderName(), parent);
                // create ui component file and write template
                FileUtil.writeContentToFile(
                        FileUtil.createFile(jsLibraryExtensions.withComponentExt(sliceName), newEntityUiDir),
                        FileTemplateUtil.fillTemplate(FileTemplateUtil.uiComponentTemplateBy(jsLibraryExtensions), Map.of("sliceName", sliceName))
                );
                break;
        }
    }

    public static void createUiDependsOnAsTypeWithStyles(@NotNull String sliceName,
                                                         @NotNull SegmentAsType asType,
                                                         @NotNull ComponentStyleType styleType,
                                                         @NotNull JsLibraryExtensionsType jsLibraryExtensions,
                                                         @NotNull VirtualFile parent) throws IOException {
        VirtualFile uiFile = null;
        String styleImport = null;
        switch (asType) {
            case FILE:
                // ui.(jsx|tsx|vue) as file
                uiFile = FileUtil.createFile(jsLibraryExtensions.withComponentExt(SegmentType.UI.getFileName()), parent);
                // Write file template to ui file
                FileUtil.writeContentToFile(
                        uiFile,
                        FileTemplateUtil.fillTemplate(FileTemplateUtil.uiComponentTemplateBy(jsLibraryExtensions), Map.of("sliceName", sliceName))
                );
                // Create styles
                styleImport = StyleUtil.createStyleDependsOnTypeAndReturnImportStatementPart(sliceName, parent, styleType);
                break;
            case FOLDER:
                // UI as folder
                VirtualFile newEntityUiDir = FileUtil.createDirectory(SegmentType.UI.getFolderName(), parent);
                // Create ui component file
                uiFile = FileUtil.createFile(jsLibraryExtensions.withComponentExt(sliceName), newEntityUiDir);
                // Write template to ui file
                FileUtil.writeContentToFile(
                        uiFile,
                        FileTemplateUtil.fillTemplate(FileTemplateUtil.uiComponentTemplateBy(jsLibraryExtensions), Map.of("sliceName", sliceName))
                );
                // Create styles
                styleImport = StyleUtil.createStyleDependsOnTypeAndReturnImportStatementPart(sliceName, newEntityUiDir, styleType);
                break;
        }
        if (!styleImport.isEmpty()) {
            FileUtil.prependContentToFile(uiFile, styleImport);
        }
    }

    public static void createApiDependsOnAsType(@NotNull SegmentAsType asType,
                                                JsLibraryExtensionsType fileLibrary,
                                                @NotNull VirtualFile parent) throws IOException {
        switch (asType) {
            case FILE:
                FileUtil.createFile(fileLibrary.withUsualExt(SegmentType.API.getFileName()), parent);
                break;
            case FOLDER:
                FileUtil.createDirectory(SegmentType.API.getFolderName(), parent);
                break;
        }
    }

    public static void createLibDependsOnAsType(@NotNull SegmentAsType asType,
                                                JsLibraryExtensionsType fileLibrary,
                                                @NotNull VirtualFile parent) throws IOException {
        switch (asType) {
            case FILE:
                FileUtil.createFile(fileLibrary.withUsualExt(SegmentType.LIB.getFileName()), parent);
                break;
            case FOLDER:
                FileUtil.createDirectory(SegmentType.LIB.getFolderName(), parent);
                break;
        }
    }

    public static void createModelDependsOnAsType(@NotNull SegmentAsType asType,
                                                  @NotNull JsLibraryExtensionsType jsLibraryExtensions,
                                                  @NotNull VirtualFile parent) throws IOException {
        createModelDependsOnAsType(asType, jsLibraryExtensions, parent, true);
    }

    public static void createModelDependsOnAsType(@NotNull SegmentAsType asType,
                                                  @NotNull JsLibraryExtensionsType jsLibraryExtensions,
                                                  @NotNull VirtualFile parent,
                                                  boolean createIndexIfFolder) throws IOException {
        switch (asType) {
            case FILE:
                FileUtil.createFile(jsLibraryExtensions.withUsualExt(SegmentType.MODEL.getFileName()), parent);
                break;
            case FOLDER:

                VirtualFile modelFolder = FileUtil.createDirectory(SegmentType.MODEL.getFolderName(), parent);

                if (createIndexIfFolder) {
                    FileUtil.createFile(jsLibraryExtensions.withUsualExt("index"), modelFolder);
                }

                break;
        }
    }

}
