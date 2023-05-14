package design.featuresliced.helper.gui.dialog.creation.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.form.fsd.shared.DefaultSharedForm;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import org.jetbrains.annotations.NotNull;

public class NewTypeSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewTypeSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New TypeScript Type", new DefaultSharedForm(), project, SegmentType.TYPE, jsLibrary);
        init();
        initValidation();
    }

}
