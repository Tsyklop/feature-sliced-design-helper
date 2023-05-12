package design.featuresliced.helper.actions.settings.toolbar;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import design.featuresliced.helper.actions.settings.BaseSettingsAction;
import org.jetbrains.annotations.NotNull;

public class TemplatesToolbarPasteAction extends BaseToolbarSettingsAction {

    public TemplatesToolbarPasteAction() {
        super("Paste", AllIcons.Actions.MenuPaste);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

    }

}
