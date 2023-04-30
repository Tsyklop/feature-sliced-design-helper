package design.featuresliced.helper.actions.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.actions.BaseAction;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.ui.dialog.shared.BaseSharedDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseNewInSharedAction extends BaseAction {

    public BaseNewInSharedAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    protected abstract @NotNull BaseSharedDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary);

}
