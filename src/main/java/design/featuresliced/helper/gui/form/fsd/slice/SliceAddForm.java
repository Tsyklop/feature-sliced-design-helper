package design.featuresliced.helper.gui.form.fsd.slice;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DetailsComponent;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.JBColor;
import com.intellij.ui.SimpleListCellRenderer;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.JBUI;
import design.featuresliced.helper.gui.model.FormError;
import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.model.type.FormErrorType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SliceAddForm implements BaseSliceForm {

    private final LayerType layerType;

    private final DetailsComponent detailsComponent;

    private final Map<TemplateStructureVariableType, JTextField> variablesTextFieldsByType = new HashMap<>();

    private JPanel root;

    private JPanel contentPanel;

    private JTextField layerReadOnlyTextField;

    private ComboBox<TemplateComboBoxValue> templatesComboBox;

    private static class TemplateComboBoxValue {

        private String label;
        private Template template;

        public TemplateComboBoxValue(String label, Template template) {
            this.label = label;
            this.template = template;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Template getTemplate() {
            return template;
        }

        public void setTemplate(Template template) {
            this.template = template;
        }

    }

    public SliceAddForm(@NotNull LayerType layerType, @NotNull Set<Template> templates) {
        this.layerType = layerType;
        this.detailsComponent = new DetailsComponent(false, false);
        this.detailsComponent.setContent(null);
        this.detailsComponent.setEmptyContentText("Select template");

        this.layerReadOnlyTextField.setText(layerType.getLabel());

        this.contentPanel.setBorder(BorderFactory.createTitledBorder(JBUI.Borders.customLine(JBColor.GRAY), "Variables"));

        this.templatesComboBox.setEditable(false);
        this.templatesComboBox.setRenderer(SimpleListCellRenderer.create((label, value, index) -> {
            label.setText(value != null ? value.getLabel() : null);
        }));

        DefaultComboBoxModel<TemplateComboBoxValue> templatesComboBoxModel = new DefaultComboBoxModel<>();
        templatesComboBoxModel.addAll(
                templates.stream().map(template -> new TemplateComboBoxValue(template.getName(), template)).toList()
        );

        this.templatesComboBox.addItemListener(e -> {

            if (e.getID() != ItemEvent.ITEM_STATE_CHANGED) {
                return;
            }

            switch (e.getStateChange()) {
                case ItemEvent.SELECTED -> {

                    TemplateComboBoxValue item = (TemplateComboBoxValue) e.getItem();

                    if (item == null) {
                        return;
                    }

                    fillContentPaneWithVariablesFields(item);

                }
                case ItemEvent.DESELECTED -> {
                    this.detailsComponent.setContent(null);
                }
            }

        });

        this.templatesComboBox.setModel(templatesComboBoxModel);

        this.contentPanel.removeAll();
        this.contentPanel.add(this.detailsComponent.getComponent(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

    }

    @Override
    public JPanel getRoot() {
        return root;
    }

    @Override
    public LayerType getLayer() {
        return this.layerType;
    }

    public Optional<Template> getSelectedTemplate() {
        return Optional.ofNullable(templatesComboBox.getSelectedItem())
                .map(o -> (TemplateComboBoxValue) o)
                .map(TemplateComboBoxValue::getTemplate);
    }

    public Map<TemplateStructureVariableType, String> getVariableValueByType() {
        return variablesTextFieldsByType.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getText()));
    }

    @Override
    public @Nullable FormError validate() {

        TemplateComboBoxValue value = (TemplateComboBoxValue) this.templatesComboBox.getSelectedItem();

        if (value == null || value.getTemplate() == null) {
            return new FormError(FormErrorType.TEMPLATE_NOT_SELECTED, this.templatesComboBox);
        }

        for (JTextField variableTextField : variablesTextFieldsByType.values()) {
            if (StringUtils.isEmpty(variableTextField.getText())) {
                return new FormError(FormErrorType.TEXT_FIELD_EMPTY, variableTextField);
            }
        }

        return null;
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
        root.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        root.setMinimumSize(new Dimension(400, 250));
        root.setPreferredSize(new Dimension(400, 250));
        final JLabel label1 = new JLabel();
        label1.setText("Template:");
        root.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        templatesComboBox = new ComboBox();
        root.add(templatesComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.setName("contentPane");
        root.add(contentPanel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        contentPanel.setBorder(IdeBorderFactory.PlainSmallWithIndent.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Variables", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Group name:");
        panel1.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JTextField textField1 = new JTextField();
        panel1.add(textField1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Layer:");
        root.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        layerReadOnlyTextField = new JTextField();
        layerReadOnlyTextField.setEditable(false);
        layerReadOnlyTextField.setEnabled(false);
        root.add(layerReadOnlyTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    private void fillContentPaneWithVariablesFields(TemplateComboBoxValue item) {

        this.variablesTextFieldsByType.clear();

        Set<TemplateStructureVariableType> usedVariables = item.getTemplate().getUsedVariables();

        JPanel panel = new JPanel(new GridLayoutManager(usedVariables.size() + 1, 2, JBUI.emptyInsets(), -1, -1));

        panel.setBorder(JBUI.Borders.empty());
        panel.getInsets().set(0, 10, 0, 10);

        int rowIndex = 0;
        for (TemplateStructureVariableType variableType : usedVariables.stream().sorted(Comparator.comparingInt(TemplateStructureVariableType::getOrder)).toList()) {

            if (variableType.isByDefault()) {
                continue;
            }

            final JLabel variableLabel = new JLabel();
            variableLabel.setName(variableType.getValue() + "VariableLabel");
            variableLabel.setText(variableType.getLabel() + ":");
            panel.add(variableLabel, new GridConstraints(rowIndex, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

            JTextField variableValueTextField = new JTextField();
            variableValueTextField.setName(variableType.getValue() + "VariableValueTextField");
            panel.add(variableValueTextField, new GridConstraints(rowIndex, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

            this.variablesTextFieldsByType.put(variableType, variableValueTextField);

            rowIndex++;

        }

        panel.add(new Spacer(), new GridConstraints(usedVariables.size(), 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));

        JBScrollPane scrollPane = new JBScrollPane(panel);
        scrollPane.setBorder(JBUI.Borders.empty());

        this.detailsComponent.setContent(scrollPane);

    }

}
