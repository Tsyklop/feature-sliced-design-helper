package design.featuresliced.helper.actions.settings.toolbar;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.ui.AnActionButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class BaseToolbarSettingsAction extends AnActionButton {

    public BaseToolbarSettingsAction(@NlsContexts.Button String text, Icon icon) {
        super(text, icon);
    }

}
