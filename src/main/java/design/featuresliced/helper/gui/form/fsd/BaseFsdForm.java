package design.featuresliced.helper.gui.form.fsd;

import design.featuresliced.helper.gui.form.BaseForm;
import design.featuresliced.helper.gui.model.FormError;
import org.jetbrains.annotations.Nullable;

public interface BaseFsdForm extends BaseForm {

    String getName();

    @Nullable FormError validate(/*@NotNull VirtualFile baseDir*/);

}
