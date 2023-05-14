package design.featuresliced.helper.actions.creation.slice.old;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.actions.creation.BaseCreationAction;
import design.featuresliced.helper.gui.dialog.creation.slice.old.BaseSliceCreationDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseSliceCreationAction extends BaseCreationAction {

    public BaseSliceCreationAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    protected abstract @NotNull BaseSliceCreationDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary);

}
