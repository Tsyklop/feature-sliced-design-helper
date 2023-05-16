package design.featuresliced.helper.actions.creation.slice;

import design.featuresliced.helper.model.type.fsd.LayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;

public class NewAppCreationAction extends BaseSliceCreationAction {

    public NewAppCreationAction() {
        super("Create In App", "Create new thing in app", FSDIcons.MAIN_ICON);
    }

    @Override
    protected @NotNull LayerType getLayer() {
        return LayerType.APP;
    }

    @Override
    protected @NotNull String getDialogTittle() {
        return "Create In App";
    }

}
