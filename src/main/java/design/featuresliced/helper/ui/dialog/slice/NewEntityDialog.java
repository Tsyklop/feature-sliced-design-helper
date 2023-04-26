package design.featuresliced.helper.ui.dialog.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.ui.form.slice.SliceForm;
import org.jetbrains.annotations.NotNull;

public class NewEntityDialog extends BaseSliceDialog<SliceForm> {

    public NewEntityDialog(@NotNull Project project) {
        super("Create Entity", new SliceForm(), project, FsdLayerType.ENTITIES);
    }

}
