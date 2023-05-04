package design.featuresliced.helper.gui.dialog.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.gui.form.fsd.slice.SliceForm;
import org.jetbrains.annotations.NotNull;

public class NewWidgetDialog extends BaseSliceDialog<SliceForm> {

    public NewWidgetDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("Create Widget", new SliceForm(), project, jsLibrary, LayerType.WIDGETS);
    }

}
