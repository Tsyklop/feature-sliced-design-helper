package design.featuresliced.helper.gui.form.fsd;

import design.featuresliced.helper.gui.form.BaseForm;
import design.featuresliced.helper.gui.model.FormError;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface BaseFsdForm extends BaseForm {

    @Nullable FormError validate();

}
