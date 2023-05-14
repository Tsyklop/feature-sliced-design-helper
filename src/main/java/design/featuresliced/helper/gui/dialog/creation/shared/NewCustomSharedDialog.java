package design.featuresliced.helper.gui.dialog.creation.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.form.fsd.shared.DefaultSharedForm;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import org.jetbrains.annotations.NotNull;

public class NewCustomSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewCustomSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("Custom in shared", new DefaultSharedForm(), project, SegmentType.TYPE, jsLibrary);
        init();
        initValidation();
    }

}
