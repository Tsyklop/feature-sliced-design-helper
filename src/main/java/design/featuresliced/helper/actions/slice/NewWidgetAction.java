package design.featuresliced.helper.actions.slice;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsActions;
import design.featuresliced.helper.gui.dialog.slice.BaseSliceDialog;
import design.featuresliced.helper.gui.dialog.slice.NewWidgetDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewWidgetAction extends BaseSliceAction {

    public NewWidgetAction(@Nullable @NlsActions.ActionText String text,
                           @Nullable @NlsActions.ActionDescription String description,
                           @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSliceDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewWidgetDialog(project, jsLibrary);
    }

}
