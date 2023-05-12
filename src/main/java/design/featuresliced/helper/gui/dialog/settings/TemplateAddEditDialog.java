package design.featuresliced.helper.gui.dialog.settings;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import design.featuresliced.helper.gui.form.settings.templates.TemplateAddEditForm;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TemplateAddEditDialog extends DialogWrapper {

    private final TemplateAddEditForm form;

    public TemplateAddEditDialog(@NotNull Project project, @Nullable Component parentComponent) {
        this(null, project, parentComponent);
    }

    public TemplateAddEditDialog(@Nullable String currentName, @NotNull Project project, @Nullable Component parentComponent) {
        super(project, parentComponent, false, IdeModalityType.PROJECT);
        this.form = new TemplateAddEditForm(currentName);
        init();
        initValidation();
        setTitle((currentName == null ? "Add" : "Edit") + " template");
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {
        if (StringUtils.isEmpty(this.form.getNameTextField().getText())) {
            return new ValidationInfo("Enter template name", this.form.getNameTextField());
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

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return this.form.getNameTextField();
    }

}
