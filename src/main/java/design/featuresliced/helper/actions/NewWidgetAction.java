package design.featuresliced.helper.actions;

import design.featuresliced.helper.ui.dialog.NewWidgetDialog;
import design.featuresliced.helper.util.Notify;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.NlsActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewWidgetAction extends BaseAction {

    public NewWidgetAction(@Nullable @NlsActions.ActionText String text,
                           @Nullable @NlsActions.ActionDescription String description,
                           @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e.getProject() == null) {
            Notify.show(null, "FSD Error", "Project is null", NotificationType.ERROR);
        } else {
            new NewWidgetDialog(e.getProject()).show();
        }
    }

}
