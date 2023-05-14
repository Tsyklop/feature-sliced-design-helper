package design.featuresliced.helper.gui.dialog.creation.slice;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.gui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.gui.form.fsd.slice.SliceAddForm;
import design.featuresliced.helper.gui.form.fsd.slice.old.SliceForm;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.service.ProjectTemplatesService;
import org.jetbrains.annotations.NotNull;

public class NewCreationDialog extends BaseDialogWrapper<SliceAddForm> {

    public NewCreationDialog(@NotNull String title,
                             @NotNull Project project,
                             @NotNull LayerType layerType,
                             @NotNull JsLibraryType jsLibrary) {
        super(
                title,
                new SliceAddForm(layerType, ProjectTemplatesService.getInstance(project).getState().getTemplatesBy(layerType)),
                project,
                jsLibrary,
                layerType
        );
        init();
        initValidation();
    }

    @Override
    protected void doOKAction() {

        super.doOKAction();

    }

}
