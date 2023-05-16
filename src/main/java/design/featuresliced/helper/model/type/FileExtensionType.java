package design.featuresliced.helper.model.type;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum FileExtensionType {

    JS(1, "JS", ".js"),
    TS(2, "TS", ".ts"),
    JSX(3, "JSX", ".jsx"),
    TSX(4, "TSX", ".tsx"),
    VUE(5, "Vue", ".vue"),

    CSS(6, "CSS", ".css", true),
    SCSS(7, "Saas", ".scss", true),
    INLINE_JS(8, "Inline", ".styles.js", true),
    INLINE_TS(9, "Inline", ".styles.ts", true),
    CSS_MODULES_CSS(10, "CSS Modules", ".module.css", true),
    CSS_MODULES_SCSS(11, "CSS Modules", ".module.scss", true),
    EMOTION_JSX(12, "@emotion", ".jsx", true),
    EMOTION_TSX(13, "@emotion", ".tsx", true),
    STYLED_COMPONENTS_JSX(14, "Styled Components", ".jsx", true),
    STYLED_COMPONENTS_TSX(15, "Styled Components", ".tsx", true);

    //AUTO("Auto", ".<auto>", true);

    public static final Set<FileExtensionType> FOR_STYLES = Arrays.stream(values())
            .filter(FileExtensionType::isCanUseForStyles)
            .collect(Collectors.toSet());
    public static final Set<FileExtensionType> NOT_FOR_STYLES = Arrays.stream(values())
            .filter(extensionType -> !extensionType.isCanUseForStyles())
            .collect(Collectors.toSet());

    private final int order;

    private final String label;

    private final String value;

    private final boolean canUseForStyles;

    FileExtensionType(int order, String label, String value) {
        this(order, label, value, false);
    }

    FileExtensionType(int order, String label, String value, boolean canUseForStyles) {
        this.order = order;
        this.label = label;
        this.value = value;
        this.canUseForStyles = canUseForStyles;
    }

    public int getOrder() {
        return order;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public boolean isCanUseForStyles() {
        return canUseForStyles;
    }

    public String getLabelWithValue() {
        return getLabel() + " " + getValue();
    }

}
