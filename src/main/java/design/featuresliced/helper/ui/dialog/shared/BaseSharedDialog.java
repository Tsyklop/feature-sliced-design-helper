package design.featuresliced.helper.ui.dialog.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.model.SegmentType;
import design.featuresliced.helper.ui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.ui.form.shared.BaseSharedForm;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public abstract class BaseSharedDialog<F extends BaseSharedForm> extends BaseDialogWrapper<F> {

    public BaseSharedDialog(@NotNull String title, @NotNull F form, @NotNull Project project) {
        super(title, form, project, FsdLayerType.SHARED);
    }

    protected String buildPathToSegment(SegmentType segmentType) {
        return StringUtils.joinWith(
                "/",
                "src",
                getFsdLayerType().getName(),
                segmentType.getFolderName()
        );
    }

}
