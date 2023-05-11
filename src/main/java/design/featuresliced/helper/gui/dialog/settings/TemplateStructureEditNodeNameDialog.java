package design.featuresliced.helper.gui.dialog.settings;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import design.featuresliced.helper.gui.form.settings.structure.TemplateStructureEditNodeNameForm;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TemplateStructureEditNodeNameDialog extends DialogWrapper {

    private final TemplateStructureEditNodeNameForm form;

    public TemplateStructureEditNodeNameDialog(@NotNull String name, @NotNull Project project) {
        super(project, false);
        this.form = new TemplateStructureEditNodeNameForm(name);
        init();
        initValidation();
        setTitle("Edit node");
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
