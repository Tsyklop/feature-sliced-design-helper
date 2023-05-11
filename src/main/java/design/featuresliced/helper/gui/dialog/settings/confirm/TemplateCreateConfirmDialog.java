package design.featuresliced.helper.gui.dialog.settings.confirm;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import design.featuresliced.helper.gui.form.settings.TemplateCreateForm;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TemplateCreateConfirmDialog extends DialogWrapper {

    private final TemplateCreateForm form;

    public TemplateCreateConfirmDialog(@Nullable Project project, @Nullable Component parentComponent) {
        super(project, parentComponent, false, IdeModalityType.PROJECT);
        this.form = new TemplateCreateForm();
        init();
        initValidation();
        setTitle("Template Creation");
    }

    public String getName() {
        return this.form.getNameTextField().getText();
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {
        if (StringUtils.isEmpty(this.form.getNameTextField().getText())) {
            return new ValidationInfo("Enter template name", this.form.getNameTextField());
        }
        return null;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.$$$getRootComponent$$$();
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return this.form.getNameTextField();
    }

}
