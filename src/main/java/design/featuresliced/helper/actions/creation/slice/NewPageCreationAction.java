package design.featuresliced.helper.actions.creation.slice;

import design.featuresliced.helper.model.type.fsd.LayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;

public class NewPageCreationAction extends BaseSliceCreationAction {

    public NewPageCreationAction() {
        super("Create Page", "Create new page", FSDIcons.MAIN_ICON);
    }

    @Override
    protected @NotNull LayerType getLayer() {
        return LayerType.PAGES;
    }

    @Override
    protected @NotNull String getDialogTittle() {
        return "Create Page";
    }

}
