package design.featuresliced.helper.ui.form.shared;

import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import design.featuresliced.helper.model.ComponentStyleType;
import design.featuresliced.helper.model.FormErrorType;
import design.featuresliced.helper.model.SegmentType;
import design.featuresliced.helper.ui.model.FormError;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.nio.file.Path;
import java.util.Arrays;

public class UiSharedForm implements BaseSharedForm {

    private JPanel root;
    private JTextField componentNameTextField;
    private JCheckBox createStyleCheckBox;
    private JCheckBox createStorybookCheckBox;
    private JComboBox<ComponentStyleType> styleTypeComboBox;

    public UiSharedForm() {
        removeInsetsAndBorder(getRoot());
        Arrays.stream(ComponentStyleType.values()).forEach(this.styleTypeComboBox::addItem);
        this.createStyleCheckBox.addChangeListener(e -> {
            this.styleTypeComboBox.setEnabled(((JCheckBox) e.getSource()).isSelected());
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
        root.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), 5, 5));
        root.setAlignmentX(0.5f);
        root.setAlignmentY(0.5f);
        root.setMinimumSize(new Dimension(-1, 160));
        root.setName("Test");
        root.setPreferredSize(new Dimension(-1, -1));
        root.setToolTipText("");
        root.setBorder(IdeBorderFactory.PlainSmallWithIndent.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Component name:");
        root.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        componentNameTextField = new JTextField();
        root.add(componentNameTextField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        createStorybookCheckBox = new JCheckBox();
        createStorybookCheckBox.setText("Create storybook");
        root.add(createStorybookCheckBox, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createStyleCheckBox = new JCheckBox();
        createStyleCheckBox.setText("Create style");
        root.add(createStyleCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Type:");
        root.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        styleTypeComboBox = new JComboBox();
        styleTypeComboBox.setEnabled(false);
        root.add(styleTypeComboBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    @Override
    public JPanel getRoot() {
        return this.root;
    }

    @Override
    public String getName() {
        return this.componentNameTextField.getText();
    }

    public boolean isCreateStyle() {
        return this.createStyleCheckBox.isSelected();
    }

    public ComponentStyleType getStyleType() {
        return (ComponentStyleType) this.styleTypeComboBox.getSelectedItem();
    }

    public boolean isCreateStoryBook() {
        return this.createStorybookCheckBox.isSelected();
    }

    @Override
    public @Nullable FormError validate(@NotNull VirtualFile baseDir) {
        if (StringUtils.isEmpty(getName())) {
            return new FormError(FormErrorType.NAME_INCORRECT, this.componentNameTextField);
        }
        if (LocalFileSystem.getInstance().findFileByNioFile(Path.of(baseDir.getPath(), SegmentType.UI.getFolderName(), getName())) != null) {
            return new FormError(FormErrorType.ALREADY_EXISTS, this.componentNameTextField);
        }
        return null;
    }

}