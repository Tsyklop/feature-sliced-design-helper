package design.featuresliced.helper.actions.shared;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import design.featuresliced.helper.actions.BaseAction;
import design.featuresliced.helper.ui.dialog.shared.NewApiSharedDialog;
import design.featuresliced.helper.util.NotifyUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewApiInSharedAction extends BaseAction {

    public NewApiInSharedAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e.getProject() == null) {
            NotifyUtil.show(null,  "Project is null", NotificationType.ERROR);
        } else {
            new NewApiSharedDialog(e.getProject()).show();
        }
    }

}
