package design.featuresliced.helper.util;

import design.featuresliced.helper.model.JsLibraryExtensionsType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class FileTemplateUtil {

    public static final String UI_DEFAULT_CONTENT = "export const [[sliceName]] = () => {\n\treturn null;\n};";

    public static final String UI_VUE_DEFAULT_CONTENT = "<template>\n\n</template>\n\n<script setup>\n\n</script>";
    public static final String UI_VUE_TS_DEFAULT_CONTENT = "<template>\n\n</template>\n\n<script setup lang=\"ts\">\n\n</script>";

    /*@Deprecated
    public static final String UI_DEFAULT_IMPORT_FROM_FILE_CONTENT = "export { %s } from './ui';";*/

    public static final String UI_VUE_IMPORT_FROM_FILE_CONTENT = "export { default as [[sliceName]] } from './ui';";
    public static final String UI_REACT_IMPORT_FROM_FILE_CONTENT = "export { [[sliceName]] } from './ui';";

    /*@Deprecated
    public static final String UI_DEFAULT_IMPORT_FROM_FOLDER_CONTENT = "export { %s } from './ui/%s';";*/

    public static final String UI_VUE_IMPORT_FROM_FOLDER_CONTENT = "export { default as [[sliceName]] } from './ui/[[sliceName]]';";
    public static final String UI_REACT_IMPORT_FROM_FOLDER_CONTENT = "export { [[sliceName]] } from './ui/[[sliceName]]';";

    public static String uiComponentTemplateBy(@NotNull JsLibraryExtensionsType jsLibraryExtensions) {
        switch (jsLibraryExtensions.getLibrary()) {
            case VUE:
                if (jsLibraryExtensions.isTypeScript()) {
                    return UI_VUE_TS_DEFAULT_CONTENT;
                } else {
                    return UI_VUE_DEFAULT_CONTENT;
                }
            case REACT:
                return UI_DEFAULT_CONTENT;
        }
        throw new IllegalArgumentException("Cannot determine js library type: " + jsLibraryExtensions);
    }

    public static String uiComponentExportFromFileTemplateBy(@NotNull JsLibraryExtensionsType jsLibraryExtensions) {
        switch (jsLibraryExtensions.getLibrary()) {
            case VUE:
                return UI_VUE_IMPORT_FROM_FILE_CONTENT;
            case REACT:
                return UI_REACT_IMPORT_FROM_FILE_CONTENT;
        }
        throw new IllegalArgumentException("Cannot determine js library type: " + jsLibraryExtensions);
    }

    public static String uiComponentExportFromFolderTemplateBy(@NotNull JsLibraryExtensionsType jsLibraryExtensions) {
        switch (jsLibraryExtensions.getLibrary()) {
            case VUE:
                return UI_VUE_IMPORT_FROM_FOLDER_CONTENT;
            case REACT:
                return UI_REACT_IMPORT_FROM_FOLDER_CONTENT;
        }
        throw new IllegalArgumentException("Cannot determine js library type: " + jsLibraryExtensions);
    }

    public static String fillTemplate(@NotNull String template, @NotNull Map<String, String> params) {
        for (Map.Entry<String, String> entry: params.entrySet()) {
            template = template.replace("[[" + entry.getKey() + "]]", entry.getValue());
        }
        return template;
    }

}
