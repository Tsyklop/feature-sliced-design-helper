package design.featuresliced.helper.actions.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.dialog.shared.BaseSharedDialog;
import design.featuresliced.helper.gui.dialog.shared.NewCustomSharedDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewCustomInSharedAction extends BaseNewInSharedAction {

    public NewCustomInSharedAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSharedDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewCustomSharedDialog(project, jsLibrary);
    }

}
