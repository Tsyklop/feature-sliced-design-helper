package design.featuresliced.helper.gui.dialog.settings.structure;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import design.featuresliced.helper.gui.form.settings.templates.modify.structure.TemplateStructureAddEditFolderForm;
import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Set;

public class TemplateStructureAddEditFolderDialog extends DialogWrapper {

    private final TemplateStructureAddEditFolderForm form;

    public TemplateStructureAddEditFolderDialog(@Nullable String name, @NotNull Project project) {
        super(project, false);
        this.form = new TemplateStructureAddEditFolderForm(name, project);
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

    public Set<TemplateStructureVariableType> getUsedVariables() {
        return this.form.getNameTextField().getUsedVariables();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.$$$getRootComponent$$$();
    }

}
