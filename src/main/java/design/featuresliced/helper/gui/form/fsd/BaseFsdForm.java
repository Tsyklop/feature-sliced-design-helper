package design.featuresliced.helper.gui.form.fsd;

import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.gui.form.BaseForm;
import design.featuresliced.helper.gui.model.FormError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public interface BaseFsdForm extends BaseForm {

    String getName();

    @Nullable FormError validate(@NotNull VirtualFile baseDir);

}
