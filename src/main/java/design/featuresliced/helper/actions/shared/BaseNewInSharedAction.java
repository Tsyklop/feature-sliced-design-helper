package design.featuresliced.helper.actions.shared;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.project.Project;
import design.featuresliced.helper.actions.BaseAction;
import design.featuresliced.helper.gui.dialog.shared.BaseSharedDialog;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseNewInSharedAction extends BaseAction {

    public BaseNewInSharedAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    protected abstract @NotNull BaseSharedDialog<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary);

}
