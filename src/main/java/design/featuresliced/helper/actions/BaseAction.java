package design.featuresliced.helper.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.NlsActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseAction extends AnAction {

    public BaseAction(@Nullable @NlsActions.ActionText String text) {
        super(text);
    }

    public BaseAction(@Nullable @NlsActions.ActionText String text,
                      @Nullable Icon icon) {
        super(text, null, icon);
    }

    public BaseAction(@Nullable @NlsActions.ActionText String text,
                      @Nullable @NlsActions.ActionDescription String description,
                      @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getProject() != null);
    }

}
