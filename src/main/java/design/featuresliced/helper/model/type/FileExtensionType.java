package design.featuresliced.helper.model.type;

import com.intellij.util.ObjectUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum FileExtensionType {

    JS("JS", ".js"),
    TS("TS", ".ts"),
    JSX("JSX", ".jsx"),
    TSX("TSX", ".tsx"),
    VUE("Vue", ".vue"),

    CSS("CSS", ".css", true),
    SCSS("Saas", ".scss", true),
    INLINE("Inline", Set.of(".styles.js", ".styles.ts"), true),
    CSS_MODULES_CSS("CSS Modules", ".module.css", true),
    CSS_MODULES_SCSS("CSS Modules", ".module.scss", true),
    EMOTION("@emotion", Set.of(".jsx", ".tsx"), true),
    STYLED_COMPONENTS("Styled Components", Set.of(".jsx", ".tsx"), true),

    AUTO("Auto", ".<auto>");

    public static final Set<FileExtensionType> FOR_STYLES = Arrays.stream(values())
            .filter(FileExtensionType::isCanUseForStyles)
            .collect(Collectors.toSet());
    public static final Set<FileExtensionType> NOT_FOR_STYLES = Arrays.stream(values())
            .filter(extensionType -> !extensionType.isCanUseForStyles())
            .collect(Collectors.toSet());

    private final String label;

    private final Set<String> values;

    private final boolean canUseForStyles;

    FileExtensionType(String label, String value) {
        this(label, Set.of(value), false);
    }

    FileExtensionType(String label, Set<String> values) {
        this(label, values, false);
    }

    FileExtensionType(String label, String value, boolean canUseForStyles) {
        this(label, Set.of(value), canUseForStyles);
    }

    FileExtensionType(String label, Set<String> values, boolean canUseForStyles) {
        this.label = label;
        this.values = values;
        this.canUseForStyles = canUseForStyles;
    }

    public String getLabel() {
        return label;
    }

    public Set<String> getValues() {
        return values;
    }

    public boolean isAuto() {
        return this == AUTO;
    }

    public boolean isCanUseForStyles() {
        return canUseForStyles;
    }

    @Override
    public String toString() {
        return getLabel() + " (" + String.join(",", getValues()) + ")";
    }

}
