package design.featuresliced.helper.gui.dialog.settings;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import design.featuresliced.helper.gui.form.settings.structure.TemplateStructureAddFolderForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TemplateStructureAddFolderDialog extends DialogWrapper {

    private final TemplateStructureAddFolderForm form;

    public TemplateStructureAddFolderDialog(@Nullable Project project) {
        super(project, false);
        this.form = new TemplateStructureAddFolderForm();
        init();
        initValidation();
        setTitle("Add folder to structure");
    }

    public String getName() {
        return this.form.getNameTextField().getText();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.$$$getRootComponent$$$();
    }

}
