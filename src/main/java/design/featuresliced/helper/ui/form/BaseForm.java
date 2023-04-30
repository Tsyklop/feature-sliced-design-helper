package design.featuresliced.helper.ui.form;

import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.ui.model.FormError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public interface BaseForm {

    JPanel getRoot();

    String getName();

    @Nullable FormError validate(@NotNull VirtualFile baseDir);

    default void removeInsetsAndBorder(JComponent component) {
        component.getInsets().set(0, 0, 0, 0);
        component.setBorder(BorderFactory.createEmptyBorder());
    }

}
