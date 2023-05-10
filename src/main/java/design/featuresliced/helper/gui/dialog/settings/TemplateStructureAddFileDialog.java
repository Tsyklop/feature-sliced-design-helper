package design.featuresliced.helper.gui.dialog.settings;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import design.featuresliced.helper.gui.form.settings.structure.TemplateStructureAddFileForm;
import design.featuresliced.helper.model.type.FileExtensionType;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TemplateStructureAddFileDialog extends DialogWrapper {

    private TemplateStructureAddFileForm form;

    public TemplateStructureAddFileDialog(@Nullable Project project) {
        super(project, false);
        init();
        initValidation();
        setTitle("Add file to structure");
    }

    public String getName() {
        return this.form.getNameTextField().getText();
    }

    public FileExtensionType getExtensionType() {
        return (FileExtensionType) this.form.getExtensionComboBox().getSelectedItem();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        this.form = new TemplateStructureAddFileForm();
        return this.form.$$$getRootComponent$$$();
    }

}
