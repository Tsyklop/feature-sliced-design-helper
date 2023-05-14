package design.featuresliced.helper.actions.creation.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.dialog.creation.shared.BaseSharedDialog;
import design.featuresliced.helper.gui.dialog.creation.shared.NewLibSharedDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewLibInSharedAction extends BaseNewInSharedAction {

    public NewLibInSharedAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSharedDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewLibSharedDialog(project, jsLibrary);
    }

}
