package design.featuresliced.helper.actions;

import design.featuresliced.helper.ui.dialog.NewEntityDialog;
import design.featuresliced.helper.util.Notify;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewEntityAction extends BaseAction {

    public NewEntityAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        if (event.getProject() == null) {
            Notify.show(null,  "Project is null", NotificationType.ERROR);
        } else {
            new NewEntityDialog(event.getProject()).show();
        }
    }

}
