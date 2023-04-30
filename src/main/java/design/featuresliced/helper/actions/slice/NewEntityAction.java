package design.featuresliced.helper.actions.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.ui.dialog.slice.BaseSliceDialog;
import design.featuresliced.helper.ui.dialog.slice.NewEntityDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewEntityAction extends BaseSliceAction {

    public NewEntityAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSliceDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewEntityDialog(project, jsLibrary);
    }

}
