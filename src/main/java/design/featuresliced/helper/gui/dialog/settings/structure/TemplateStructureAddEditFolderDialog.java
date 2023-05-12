package design.featuresliced.helper.gui.dialog.settings.structure;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import design.featuresliced.helper.gui.form.settings.templates.structure.TemplateStructureAddEditFolderForm;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TemplateStructureAddEditFolderDialog extends DialogWrapper {

    private final TemplateStructureAddEditFolderForm form;

    public TemplateStructureAddEditFolderDialog(@Nullable String name, @Nullable Project project) {
        super(project, false);
        this.form = new TemplateStructureAddEditFolderForm(name);
        init();
        initValidation();
        setTitle(name == null ? "Add folder to structure" : "Edit folder");
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

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.$$$getRootComponent$$$();
    }

}
