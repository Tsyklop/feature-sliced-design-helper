package design.featuresliced.helper.gui.form.fsd.slice.old;

import design.featuresliced.helper.gui.form.fsd.BaseFsdForm;
import design.featuresliced.helper.model.type.StyleType;
import design.featuresliced.helper.model.type.fsd.SegmentAsType;

@Deprecated
public interface BaseSliceForm extends BaseFsdForm {

    String getName();

    boolean isCreateStyle();

    boolean isCreateLibSegment();

    boolean isCreateModelSegment();

    boolean isCreateUiSegment();

    boolean isCreateApiSegment();

    SegmentAsType getUiSegmentAsType();

    SegmentAsType getLibSegmentAsType();

    SegmentAsType getApiSegmentAsType();

    SegmentAsType getModelSegmentAsType();

    StyleType getStyleType();

}
