package design.featuresliced.helper.ui.dialog.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.ui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.ui.form.shared.BaseSharedForm;
import org.jetbrains.annotations.NotNull;

public abstract class BaseSharedDialog<F extends BaseSharedForm> extends BaseDialogWrapper<F> {

    public BaseSharedDialog(@NotNull String title, @NotNull F form, @NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super(title, form, project, jsLibrary, FsdLayerType.SHARED);
    }

}
