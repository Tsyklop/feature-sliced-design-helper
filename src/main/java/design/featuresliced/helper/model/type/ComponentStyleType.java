package design.featuresliced.helper.model.type;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ComponentStyleType {

    CSS("CSS", ".css", "import './[[name]][[ext]]';", "[[name]][[ext]]"),
    SCSS("Saas", ".scss", "import './[[name]][[ext]]';", "[[name]][[ext]]"),
    INLINE("Inline", ".styles.js", "import {styles} from './[[name]].styles';", "[[name]][[ext]]"),
    CSS_MODULES_CSS("CSS Modules (.css)", ".module.css", "import cls from './[[name]][[ext]]'", "[[name]][[ext]]"),
    CSS_MODULES_SCSS("CSS Modules (.scss)", ".module.scss", "import cls from './[[name]][[ext]]'", "[[name]][[ext]]"),
    //TAILWIND_CSS("Tailwind CSS", ".css"),
    EMOTION("@emotion", "styles", "import styled from '@emotion/styled';", "[[ext]]"),
    STYLED_COMPONENTS("Styled Components", "styles", "import styled from 'styled-components';", "[[ext]]");

    private final String label;
    private final String extension;
    private final String importTemplate;
    private final String fileNameTemplate;

    ComponentStyleType(@NotNull String label, @Nullable String extension, @NotNull String importTemplate, @NotNull String fileNameTemplate) {
        this.label = label;
        this.extension = extension;
        this.importTemplate = importTemplate;
        this.fileNameTemplate = fileNameTemplate;
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
