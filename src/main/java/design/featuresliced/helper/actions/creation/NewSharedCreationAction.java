package design.featuresliced.helper.actions.creation;

import design.featuresliced.helper.model.type.fsd.LayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;

public class NewSharedCreationAction extends BaseSliceCreationAction {

    public NewSharedCreationAction() {
        super("Create In Shared", "Create new thing in shared", FSDIcons.MAIN_ICON);
    }

    @Override
    protected @NotNull LayerType getLayer() {
        return LayerType.SHARED;
    }

    @Override
    protected @NotNull String getDialogTittle() {
        return "Create In Shared";
    }

}
