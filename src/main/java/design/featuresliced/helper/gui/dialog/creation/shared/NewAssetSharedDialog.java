package design.featuresliced.helper.gui.dialog.creation.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.form.fsd.shared.DefaultSharedForm;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import org.jetbrains.annotations.NotNull;

public class NewAssetSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewAssetSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New Asset", new DefaultSharedForm(), project, SegmentType.ASSETS, jsLibrary);
        init();
        initValidation();
    }
}
