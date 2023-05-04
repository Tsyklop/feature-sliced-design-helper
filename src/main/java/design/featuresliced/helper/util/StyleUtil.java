package design.featuresliced.helper.util;

import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.model.type.ComponentStyleType;

import java.io.IOException;
import java.util.Map;

public class StyleUtil {

    public static String createStyleDependsOnTypeAndReturnImportStatementPart(String componentName, VirtualFile componentDir, ComponentStyleType styleType) throws IOException {

        String styleFileName = componentName + styleType.getExtension();

        switch (styleType) {
            case CSS:
            case SCSS:
            case INLINE:
            case CSS_MODULES_CSS:
            case CSS_MODULES_SCSS:
                FileUtil.createFile(styleFileName, componentDir);
                return FileTemplateUtil.fillTemplate(styleType.getImportTemplate(), Map.of("name", componentName, "ext", styleType.getExtension())) + "\n\n";
            case STYLED_COMPONENTS:
                return  styleType.getImportTemplate() + "\n\n";
        }

        return "";

    }

}
