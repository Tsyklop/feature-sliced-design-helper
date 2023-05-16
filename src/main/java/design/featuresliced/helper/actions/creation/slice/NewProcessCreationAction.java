package design.featuresliced.helper.actions.creation.slice;

import design.featuresliced.helper.model.type.fsd.LayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;

public class NewProcessCreationAction extends BaseSliceCreationAction {

    public NewProcessCreationAction() {
        super("Create In Process", "Create new thing in process", FSDIcons.MAIN_ICON);
    }

    @Override
    protected @NotNull LayerType getLayer() {
        return LayerType.PROCESSES;
    }

    @Override
    protected @NotNull String getDialogTittle() {
        return "Create In Process";
    }

}
