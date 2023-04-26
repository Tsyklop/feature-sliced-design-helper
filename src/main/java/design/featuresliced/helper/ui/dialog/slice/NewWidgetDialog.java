package design.featuresliced.helper.ui.dialog.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.ui.form.slice.SliceForm;
import org.jetbrains.annotations.NotNull;

public class NewWidgetDialog extends BaseSliceDialog<SliceForm> {

    public NewWidgetDialog(@NotNull Project project) {
        super("Create Widget", new SliceForm(), project, FsdLayerType.WIDGETS);
    }

}