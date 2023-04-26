package design.featuresliced.helper.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ComponentStyleType {

    CSS("CSS", ".css"),
    SCSS("Saas", ".scss"),
    INLINE("Inline", ".styles.js"),
    CSS_MODULES("CSS Modules", ".module.scss"),
    //TAILWIND_CSS("Tailwind CSS", ".css"),
    STYLED_COMPONENTS("Styled Components", null);

    private final String label;
    private final String extension;

    ComponentStyleType(@NotNull String label, @Nullable String extension) {
        this.label = label;
        this.extension = extension;
    }

    public String getLabel() {
        return label;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return label;
    }

}
