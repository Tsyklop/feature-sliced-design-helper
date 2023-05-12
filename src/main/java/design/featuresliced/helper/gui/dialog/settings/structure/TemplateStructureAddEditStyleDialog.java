package design.featuresliced.helper.gui.dialog.settings.structure;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import design.featuresliced.helper.gui.form.settings.templates.structure.TemplateStructureAddEditStyleForm;
import design.featuresliced.helper.model.type.FileExtensionType;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TemplateStructureAddEditStyleDialog extends DialogWrapper {

    private final TemplateStructureAddEditStyleForm form;

    public TemplateStructureAddEditStyleDialog(@NotNull Project project) {
        this(null, null, project);
    }

    public TemplateStructureAddEditStyleDialog(@Nullable String name,
                                               @Nullable FileExtensionType extensionType,
                                               @NotNull Project project) {
        super(project, false);
        this.form = new TemplateStructureAddEditStyleForm(name, extensionType, FileExtensionType.FOR_STYLES);
        init();
        initValidation();
        setTitle("Add style to structure");
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {
        if (StringUtils.isEmpty(this.form.getNameTextField().getText())) {
            return new ValidationInfo("Enter name", this.form.getNameTextField());
        }
        return null;
    }

    public String getName() {
        return this.form.getNameTextField().getText();
    }

    public FileExtensionType getExtensionType() {
        return (FileExtensionType) this.form.getExtensionComboBox().getSelectedItem();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.$$$getRootComponent$$$();
    }

}
