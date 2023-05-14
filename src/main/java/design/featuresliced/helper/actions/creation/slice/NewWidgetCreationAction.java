package design.featuresliced.helper.actions.creation.slice;

import design.featuresliced.helper.model.type.fsd.LayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;

public class NewWidgetCreationAction extends BaseSliceCreationAction {

    public NewWidgetCreationAction() {
        super("Create Widget", "Create new widget", FSDIcons.MAIN_ICON);
    }

    @Override
    protected @NotNull LayerType getLayer() {
        return LayerType.WIDGETS;
    }

    @Override
    protected @NotNull String getDialogTittle() {
        return "Create Widget";
    }

}
