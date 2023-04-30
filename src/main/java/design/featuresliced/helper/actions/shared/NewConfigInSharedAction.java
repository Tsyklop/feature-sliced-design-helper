package design.featuresliced.helper.actions.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.ui.dialog.shared.BaseSharedDialog;
import design.featuresliced.helper.ui.dialog.shared.NewConfigSharedDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewConfigInSharedAction extends BaseNewInSharedAction {

    public NewConfigInSharedAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    protected @NotNull BaseSharedDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary) {
        return new NewConfigSharedDialog(project, jsLibrary);
    }

}
