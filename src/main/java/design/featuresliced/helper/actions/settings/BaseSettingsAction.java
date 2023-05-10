package design.featuresliced.helper.actions.settings;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.util.NlsActions;
import org.jetbrains.annotations.Nullable;

public abstract class BaseSettingsAction extends AnAction {

    public BaseSettingsAction(@Nullable @NlsActions.ActionText String text) {
        super(text);
    }

}
