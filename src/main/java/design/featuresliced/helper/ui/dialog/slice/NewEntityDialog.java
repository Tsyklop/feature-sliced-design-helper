package design.featuresliced.helper.ui.dialog.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.ui.form.slice.SliceForm;
import org.jetbrains.annotations.NotNull;

public class NewEntityDialog extends BaseSliceDialog<SliceForm> {

    public NewEntityDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("Create Entity", new SliceForm(), project, jsLibrary, FsdLayerType.ENTITIES);
    }

}
