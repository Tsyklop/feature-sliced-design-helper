package design.featuresliced.helper.actions.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.gui.dialog.slice.BaseSliceDialog;
import design.featuresliced.helper.gui.dialog.slice.NewFeatureDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewFeatureAction extends BaseSliceAction {

    public NewFeatureAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSliceDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewFeatureDialog(project, jsLibrary);
    }

}
