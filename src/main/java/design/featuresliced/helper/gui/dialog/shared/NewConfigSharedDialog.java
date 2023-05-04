package design.featuresliced.helper.gui.dialog.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.form.fsd.shared.DefaultSharedForm;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import org.jetbrains.annotations.NotNull;

public class NewConfigSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewConfigSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New Config", new DefaultSharedForm(), project, SegmentType.CONFIG, jsLibrary);
        init();
        initValidation();
    }
}
