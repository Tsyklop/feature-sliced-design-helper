package design.featuresliced.helper.actions.creation.slice.old;

import com.intellij.openapi.project.Project;

import design.featuresliced.helper.gui.dialog.creation.slice.old.BaseSliceCreationDialog;
import design.featuresliced.helper.gui.dialog.creation.slice.old.NewWidgetCreationDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewWidgetCreationAction extends BaseSliceCreationAction {

    public NewWidgetCreationAction(@Nullable String text,
                                   @Nullable String description,
                                   @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSliceCreationDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewWidgetCreationDialog(project, jsLibrary);
    }

}
