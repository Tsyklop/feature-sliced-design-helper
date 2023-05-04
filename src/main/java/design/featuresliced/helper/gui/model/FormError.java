package design.featuresliced.helper.gui.model;

import design.featuresliced.helper.model.type.FormErrorType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;

public class FormError {

    private final FormErrorType type;

    private final JComponent component;

    public FormError(@NotNull FormErrorType type, @NotNull JComponent component) {
        this.type = Objects.requireNonNull(type);
        this.component = Objects.requireNonNull(component);
    }

    public FormErrorType getType() {
        return type;
    }

    public JComponent getComponent() {
        return component;
    }

}
