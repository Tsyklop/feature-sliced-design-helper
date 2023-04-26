package design.featuresliced.helper.ui.form.slice;

import design.featuresliced.helper.model.SegmentAsType;
import design.featuresliced.helper.ui.form.BaseForm;

public interface BaseSliceForm extends BaseForm {

    boolean isCreateLibSegment();

    boolean isCreateModelSegment();

    boolean isCreateUiSegment();

    boolean isCreateApiSegment();

    SegmentAsType getUiSegmentAsType();

    SegmentAsType getLibSegmentAsType();

    SegmentAsType getApiSegmentAsType();

    SegmentAsType getModelSegmentAsType();

}