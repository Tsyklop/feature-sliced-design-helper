package design.featuresliced.helper.gui.form.settings.templates.structure;

import com.intellij.openapi.project.Project;
import com.intellij.ui.SimpleListCellRenderer;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import design.featuresliced.helper.gui.component.TemplateStructureVariableChooserTextField;
import design.featuresliced.helper.model.type.FileExtensionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class TemplateStructureAddEditFileForm {

    private final Project project;
    private JPanel root;
    private TemplateStructureVariableChooserTextField nameTextField;
    private JComboBox<FileExtensionType> extensionComboBox;

    public TemplateStructureAddEditFileForm(@NotNull Project project, @NotNull Set<FileExtensionType> extensionTypes) {
        this(project, null, null, extensionTypes);
    }

    public TemplateStructureAddEditFileForm(@NotNull Project project,
                                            @Nullable String name,
                                            @Nullable FileExtensionType extensionType,
                                            @NotNull Set<FileExtensionType> extensionTypes) {
        this.project = project;
        $$$setupUI$$$();
        this.extensionComboBox.setRenderer(SimpleListCellRenderer.create((label, value, index) -> {
            label.setText(value != null ? value.getLabelWithValue() : null);
        }));
        extensionTypes.forEach(this.extensionComboBox::addItem);
        this.nameTextField.setText(name);
        if (extensionType != null) {
            this.extensionComboBox.setSelectedItem(extensionType);
        }
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        root = new JPanel();
        root.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Name:");
        root.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        root.add(nameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        extensionComboBox = new JComboBox();
        root.add(extensionComboBox, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    public TemplateStructureVariableChooserTextField getNameTextField() {
        return nameTextField;
    }

    public JComboBox<FileExtensionType> getExtensionComboBox() {
        return extensionComboBox;
    }

    private void createUIComponents() {
        nameTextField = new TemplateStructureVariableChooserTextField(this.project);
    }

}
