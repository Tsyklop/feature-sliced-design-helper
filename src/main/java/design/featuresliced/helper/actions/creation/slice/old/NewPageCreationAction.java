package design.featuresliced.helper.actions.creation.slice.old;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsActions;
import design.featuresliced.helper.gui.dialog.creation.slice.old.BaseSliceCreationDialog;
import design.featuresliced.helper.gui.dialog.creation.slice.old.NewPageCreationDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewPageCreationAction extends BaseSliceCreationAction {

    public NewPageCreationAction(@Nullable @NlsActions.ActionText String text,
                                 @Nullable @NlsActions.ActionDescription String description,
                                 @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSliceCreationDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewPageCreationDialog(project, jsLibrary);
    }

}
