package design.featuresliced.helper.gui.form.fsd.slice;

import design.featuresliced.helper.gui.form.fsd.BaseFsdForm;
import design.featuresliced.helper.model.type.StyleType;
import design.featuresliced.helper.model.type.fsd.SegmentAsType;

public interface BaseSliceForm extends BaseFsdForm {

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
