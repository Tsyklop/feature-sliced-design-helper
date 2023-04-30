package design.featuresliced.helper.actions.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.actions.BaseAction;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.ui.dialog.slice.BaseSliceDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseSliceAction extends BaseAction {

    public BaseSliceAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    protected abstract @NotNull BaseSliceDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary);

}
