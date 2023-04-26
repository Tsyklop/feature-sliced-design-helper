package design.featuresliced.helper.actions;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.NlsActions;
import design.featuresliced.helper.ui.dialog.slice.NewPageDialog;
import design.featuresliced.helper.util.NotifyUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewPageAction extends BaseAction {

    public NewPageAction(@Nullable @NlsActions.ActionText String text,
                         @Nullable @NlsActions.ActionDescription String description,
                         @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e.getProject() == null) {
            NotifyUtil.show(null, "Project is null", NotificationType.ERROR);
        } else {
            new NewPageDialog(e.getProject()).show();
        }
    }

}