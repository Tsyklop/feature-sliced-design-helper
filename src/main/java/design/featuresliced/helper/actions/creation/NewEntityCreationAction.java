package design.featuresliced.helper.actions.creation;

import design.featuresliced.helper.model.type.fsd.LayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;

public class NewEntityCreationAction extends BaseSliceCreationAction {

    public NewEntityCreationAction() {
        super("Create Entity", "Create new entity", FSDIcons.MAIN_ICON);
    }

    @Override
    protected @NotNull LayerType getLayer() {
        return LayerType.ENTITIES;
    }

    @Override
    protected @NotNull String getDialogTittle() {
        return "Create Entity";
    }

}
