package design.featuresliced.helper.gui.dialog.creation.slice.old;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.form.fsd.slice.old.SliceForm;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import org.jetbrains.annotations.NotNull;

public class NewEntityCreationDialog extends BaseSliceCreationDialog<SliceForm> {

    public NewEntityCreationDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("Create Entity", new SliceForm(), project, jsLibrary, LayerType.ENTITIES);
    }

}
