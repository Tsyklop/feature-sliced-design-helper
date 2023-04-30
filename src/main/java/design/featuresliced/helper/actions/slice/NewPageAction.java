package design.featuresliced.helper.actions.slice;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsActions;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.ui.dialog.slice.BaseSliceDialog;
import design.featuresliced.helper.ui.dialog.slice.NewPageDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewPageAction extends BaseSliceAction {

    public NewPageAction(@Nullable @NlsActions.ActionText String text,
                         @Nullable @NlsActions.ActionDescription String description,
                         @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSliceDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewPageDialog(project, jsLibrary);
    }

}
