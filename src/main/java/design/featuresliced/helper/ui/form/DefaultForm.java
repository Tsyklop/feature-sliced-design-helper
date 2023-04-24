package design.featuresliced.helper.ui.form;

import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import design.featuresliced.helper.model.FormErrorType;
import design.featuresliced.helper.model.SegmentAsType;
import design.featuresliced.helper.ui.model.FormError;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.nio.file.Path;

public class DefaultForm implements BaseForm {

    private JPanel root;

    private JTextField nameTextField;
    private JCheckBox createUiSegmentCheckBox;
    private JCheckBox createLibSegmentCheckBox;
    private JCheckBox createModelSegmentCheckBox;
    private JCheckBox createApiSegmentCheckBox;
    private JComboBox<SegmentAsType> uiSegmentTypeComboBox;
    private JComboBox<SegmentAsType> libSegmentTypeComboBox;
    private JComboBox<SegmentAsType> modelSegmentTypeComboBox;
    private JComboBox<SegmentAsType> apiSegmentTypeComboBox;

    public DefaultForm() {
        for (SegmentAsType segmentAsType : SegmentAsType.values()) {
            this.uiSegmentTypeComboBox.addItem(segmentAsType);
            this.libSegmentTypeComboBox.addItem(segmentAsType);
            this.modelSegmentTypeComboBox.addItem(segmentAsType);
            this.apiSegmentTypeComboBox.addItem(segmentAsType);
        }
        this.uiSegmentTypeComboBox.setSelectedItem(SegmentAsType.FILE);
        this.libSegmentTypeComboBox.setSelectedItem(SegmentAsType.FILE);
        this.modelSegmentTypeComboBox.setSelectedItem(SegmentAsType.FILE);
        this.apiSegmentTypeComboBox.setSelectedItem(SegmentAsType.FILE);
        this.root.getInsets().set(0, 0, 0, 0);
        this.root.setBorder(BorderFactory.createEmptyBorder());

        this.createUiSegmentCheckBox.addChangeListener(e -> {
            this.uiSegmentTypeComboBox.setEnabled(((JCheckBox) e.getSource()).isSelected());
        });

        this.createLibSegmentCheckBox.addChangeListener(e -> {
            this.libSegmentTypeComboBox.setEnabled(((JCheckBox) e.getSource()).isSelected());
        });

        this.createApiSegmentCheckBox.addChangeListener(e -> {
            this.apiSegmentTypeComboBox.setEnabled(((JCheckBox) e.getSource()).isSelected());
        });

        this.createModelSegmentCheckBox.addChangeListener(e -> {
            this.modelSegmentTypeComboBox.setEnabled(((JCheckBox) e.getSource()).isSelected());
        });

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), 5, 0));
        root.setAlignmentX(0.5f);
        root.setAlignmentY(0.5f);
        root.setMinimumSize(new Dimension(300, 172));
        root.setName("Test");
        root.setPreferredSize(new Dimension(300, 172));
        root.setToolTipText("");
        root.setBorder(IdeBorderFactory.PlainSmallWithIndent.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Slice Name:");
        root.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(81, 17), null, 0, false));
        nameTextField = new JTextField();
        nameTextField.setMargin(new Insets(2, 6, 2, 6));
        root.add(nameTextField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        createUiSegmentCheckBox = new JCheckBox();
        createUiSegmentCheckBox.setSelected(true);
        createUiSegmentCheckBox.setText("Create ui segment");
        root.add(createUiSegmentCheckBox, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        createLibSegmentCheckBox = new JCheckBox();
        createLibSegmentCheckBox.setSelected(false);
        createLibSegmentCheckBox.setText("Create lib segment");
        root.add(createLibSegmentCheckBox, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        createApiSegmentCheckBox = new JCheckBox();
        createApiSegmentCheckBox.setSelected(false);
        createApiSegmentCheckBox.setText("Create api segment");
        root.add(createApiSegmentCheckBox, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        uiSegmentTypeComboBox = new JComboBox();
        root.add(uiSegmentTypeComboBox, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        libSegmentTypeComboBox = new JComboBox();
        libSegmentTypeComboBox.setEnabled(false);
        root.add(libSegmentTypeComboBox, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apiSegmentTypeComboBox = new JComboBox();
        apiSegmentTypeComboBox.setEnabled(false);
        root.add(apiSegmentTypeComboBox, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("as");
        root.add(label2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("as");
        root.add(label3, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("as");
        root.add(label4, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createModelSegmentCheckBox = new JCheckBox();
        createModelSegmentCheckBox.setSelected(true);
        createModelSegmentCheckBox.setText("Create model segment");
        root.add(createModelSegmentCheckBox, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("as");
        root.add(label5, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modelSegmentTypeComboBox = new JComboBox();
        root.add(modelSegmentTypeComboBox, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label1.setLabelFor(nameTextField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    @Override
    public JPanel getRoot() {
        return root;
    }

    @Override
    public String getName() {
        return nameTextField.getText();
    }

    @Override
    public boolean isCreateLibSegment() {
        return createLibSegmentCheckBox.isSelected();
    }

    @Override
    public boolean isCreateModelSegment() {
        return createModelSegmentCheckBox.isSelected();
    }

    @Override
    public boolean isCreateUiSegment() {
        return createUiSegmentCheckBox.isSelected();
    }

    @Override
    public boolean isCreateApiSegment() {
        return createApiSegmentCheckBox.isSelected();
    }

    @Override
    public SegmentAsType getUiSegmentAsType() {
        return (SegmentAsType) uiSegmentTypeComboBox.getSelectedItem();
    }

    @Override
    public SegmentAsType getLibSegmentAsType() {
        return (SegmentAsType) libSegmentTypeComboBox.getSelectedItem();
    }

    @Override
    public SegmentAsType getApiSegmentAsType() {
        return (SegmentAsType) apiSegmentTypeComboBox.getSelectedItem();
    }

    @Override
    public SegmentAsType getModelSegmentAsType() {
        return (SegmentAsType) modelSegmentTypeComboBox.getSelectedItem();
    }

    @Override
    public @Nullable FormError validate(@NotNull VirtualFile baseDir) {
        if (StringUtils.isEmpty(getName())) {
            return new FormError(FormErrorType.NAME_INCORRECT, this.nameTextField);
        }
        if (LocalFileSystem.getInstance().findFileByNioFile(Path.of(baseDir.getPath(), getName())) != null) {
            return new FormError(FormErrorType.ALREADY_EXISTS, this.nameTextField);
        }
        if (!this.isCreateApiSegment()
                && !this.isCreateUiSegment()
                && !this.isCreateLibSegment()
                && !this.isCreateModelSegment()) {
            return new FormError(FormErrorType.SEGMENT_NOT_SELECTED, this.root);
        }
        return null;
    }

}
