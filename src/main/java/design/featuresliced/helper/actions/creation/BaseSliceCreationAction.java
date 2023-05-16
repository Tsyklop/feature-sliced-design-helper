package design.featuresliced.helper.actions.creation;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.project.Project;
import design.featuresliced.helper.actions.creation.BaseCreationAction;
import design.featuresliced.helper.gui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.gui.dialog.creation.slice.NewCreationDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseSliceCreationAction extends BaseCreationAction {

    public BaseSliceCreationAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    @Override
    protected @NotNull BaseDialogWrapper<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewCreationDialog(getDialogTittle(), project, getLayer(), jsLibrary);
    }

    protected abstract @NotNull LayerType getLayer();

    protected abstract @NotNull String getDialogTittle();

}
