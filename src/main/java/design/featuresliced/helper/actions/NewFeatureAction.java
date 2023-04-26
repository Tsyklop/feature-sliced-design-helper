package design.featuresliced.helper.actions;

import design.featuresliced.helper.ui.dialog.slice.NewFeatureDialog;
import design.featuresliced.helper.util.NotifyUtil;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewFeatureAction extends BaseAction {

    public NewFeatureAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        if (event.getProject() == null) {
            NotifyUtil.show(null,  "Project is null", NotificationType.ERROR);
        } else {
            new NewFeatureDialog(event.getProject()).show();
        }
    }

}
