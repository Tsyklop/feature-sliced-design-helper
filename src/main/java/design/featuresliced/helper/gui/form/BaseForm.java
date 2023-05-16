package design.featuresliced.helper.gui.form;

import javax.swing.*;

public interface BaseForm {

    JPanel getRoot();

    default void removeInsetsAndBorder(JComponent component) {
        component.getInsets().set(0, 0, 0, 0);
        component.setBorder(BorderFactory.createEmptyBorder());
    }

}
