package design.featuresliced.helper.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import design.featuresliced.helper.settings.ProjectGeneralConfigurable;
import org.jetbrains.annotations.NotNull;

public class OpenPluginSettingsAction extends AnAction {

    public OpenPluginSettingsAction() {
        super("Open settings");
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ShowSettingsUtil.getInstance().showSettingsDialog(e.getProject(), ProjectGeneralConfigurable.class);
    }

}
