package design.featuresliced.helper.gui.dialog.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.gui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.gui.form.fsd.shared.BaseSharedForm;
import org.jetbrains.annotations.NotNull;

public abstract class BaseSharedDialog<F extends BaseSharedForm> extends BaseDialogWrapper<F> {

    public BaseSharedDialog(@NotNull String title, @NotNull F form, @NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super(title, form, project, jsLibrary, LayerType.SHARED);
    }

}
