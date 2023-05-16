package design.featuresliced.helper.actions.creation;

import design.featuresliced.helper.model.type.fsd.LayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;

public class NewFeatureCreationAction extends BaseSliceCreationAction {

    public NewFeatureCreationAction() {
        super("Create Feature", "Create new feature", FSDIcons.MAIN_ICON);
    }

    @Override
    protected @NotNull LayerType getLayer() {
        return LayerType.FEATURES;
    }

    @Override
    protected @NotNull String getDialogTittle() {
        return "Create Feature";
    }

}
