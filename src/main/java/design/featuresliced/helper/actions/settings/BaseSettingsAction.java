package design.featuresliced.helper.actions.settings;


import design.featuresliced.helper.actions.BaseAction;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseSettingsAction extends BaseAction {

    public BaseSettingsAction(@Nullable String text) {
        super(text);
    }

    public BaseSettingsAction(@Nullable String text, @Nullable Icon icon) {
        super(text, "", icon);
    }

}
