package design.featuresliced.helper.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ComponentStyleType {

    CSS("CSS", ".css", "import './[[name]][[ext]]';"),
    SCSS("Saas", ".scss", "import './[[name]][[ext]]';"),
    INLINE("Inline", ".styles.js", "import {styles} from './[[name]].styles';"),
    CSS_MODULES_CSS("CSS Modules (.css)", ".module.css", "import cls from './[[name]][[ext]]'"),
    CSS_MODULES_SCSS("CSS Modules (.scss)", ".module.scss", "import cls from './[[name]][[ext]]'"),
    //TAILWIND_CSS("Tailwind CSS", ".css"),
    STYLED_COMPONENTS("Styled Components", null, "import styled from 'styled-components';");

    private final String label;
    private final String extension;
    private final String importTemplate;

    ComponentStyleType(@NotNull String label, @Nullable String extension, @NotNull String importTemplate) {
        this.label = label;
        this.extension = extension;
        this.importTemplate = importTemplate;
    }

    public String getLabel() {
        return label;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isCssModules() {
        return this == CSS_MODULES_CSS || this == CSS_MODULES_SCSS;
    }

    public String getImportTemplate() {
        return importTemplate;
    }

    @Override
    public String toString() {
        return label;
    }

}
