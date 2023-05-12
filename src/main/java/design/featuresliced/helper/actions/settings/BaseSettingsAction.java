package design.featuresliced.helper.actions.settings;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.util.NlsActions;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseSettingsAction extends AnAction {

    public BaseSettingsAction(@Nullable @NlsActions.ActionText String text) {
        super(text);
    }

    public BaseSettingsAction(@Nullable @NlsActions.ActionText String text, @Nullable Icon icon) {
        super(text, "", icon);
    }

}
