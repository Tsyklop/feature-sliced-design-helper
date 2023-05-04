package design.featuresliced.helper.gui.dialog.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.form.fsd.shared.DefaultSharedForm;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import org.jetbrains.annotations.NotNull;

public class NewLibSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewLibSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New Shared Lib", new DefaultSharedForm(), project, SegmentType.LIB, jsLibrary);
        init();
        initValidation();
    }
}
