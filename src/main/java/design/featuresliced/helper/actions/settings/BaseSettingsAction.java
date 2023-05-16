package design.featuresliced.helper.actions.settings;

import com.intellij.openapi.util.NlsActions;
import design.featuresliced.helper.actions.BaseAction;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseSettingsAction extends BaseAction {

    public BaseSettingsAction(@Nullable @NlsActions.ActionText String text) {
        super(text);
    }

    public BaseSettingsAction(@Nullable @NlsActions.ActionText String text, @Nullable Icon icon) {
        super(text, "", icon);
    }

}
