package design.featuresliced.helper.actions.creation.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.actions.creation.BaseCreationAction;
import design.featuresliced.helper.gui.dialog.creation.shared.BaseSharedDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseNewInSharedAction extends BaseCreationAction {

    public BaseNewInSharedAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    protected abstract @NotNull BaseSharedDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary);

}
