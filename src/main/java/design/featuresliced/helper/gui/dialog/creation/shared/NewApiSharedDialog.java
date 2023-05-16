package design.featuresliced.helper.gui.dialog.creation.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.form.fsd.shared.DefaultSharedForm;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import org.jetbrains.annotations.NotNull;

public class NewApiSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewApiSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New Api", new DefaultSharedForm(), project, SegmentType.API, jsLibrary);
        init();
        initValidation();
    }

}
