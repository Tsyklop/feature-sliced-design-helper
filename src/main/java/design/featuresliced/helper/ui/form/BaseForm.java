package design.featuresliced.helper.ui.form;

import design.featuresliced.helper.model.SegmentAsType;
import design.featuresliced.helper.ui.model.FormError;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public interface BaseForm {

    JPanel getRoot();

    String getName();

    @Nullable FormError validate(@NotNull VirtualFile baseDir);

    boolean isCreateLibSegment();

    boolean isCreateModelSegment();

    boolean isCreateUiSegment();

    boolean isCreateApiSegment();

    SegmentAsType getUiSegmentAsType();

    SegmentAsType getLibSegmentAsType();

    SegmentAsType getApiSegmentAsType();

    SegmentAsType getModelSegmentAsType();

}
