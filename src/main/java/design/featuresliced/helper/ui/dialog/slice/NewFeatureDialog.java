package design.featuresliced.helper.ui.dialog.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.ui.form.slice.SliceForm;
import org.jetbrains.annotations.NotNull;

public class NewFeatureDialog extends BaseSliceDialog<SliceForm> {

    public NewFeatureDialog(@NotNull Project project) {
        super("Create Feature", new SliceForm(), project, FsdLayerType.FEATURES);
    }

}
