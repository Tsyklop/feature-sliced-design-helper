package design.featuresliced.helper.gui.dialog.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.gui.form.fsd.slice.SliceForm;
import org.jetbrains.annotations.NotNull;

public class NewPageDialog extends BaseSliceDialog<SliceForm> {

    public NewPageDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("Create Page", new SliceForm(), project, jsLibrary, LayerType.PAGES);
    }

}
