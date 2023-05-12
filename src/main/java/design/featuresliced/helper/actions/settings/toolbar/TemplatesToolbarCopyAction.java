package design.featuresliced.helper.actions.settings.toolbar;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import design.featuresliced.helper.actions.settings.toolbar.BaseToolbarSettingsAction;
import org.jetbrains.annotations.NotNull;

public class TemplatesToolbarCopyAction extends BaseToolbarSettingsAction {

    public TemplatesToolbarCopyAction() {
        super("Copy", AllIcons.Actions.Copy);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

    }

}
