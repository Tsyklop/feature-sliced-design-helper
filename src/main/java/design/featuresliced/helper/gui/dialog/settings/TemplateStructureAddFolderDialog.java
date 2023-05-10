package design.featuresliced.helper.gui.dialog.settings;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import design.featuresliced.helper.gui.form.settings.structure.TemplateStructureAddFileForm;
import design.featuresliced.helper.gui.form.settings.structure.TemplateStructureAddFolderForm;
import design.featuresliced.helper.model.type.FileExtensionType;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TemplateStructureAddFolderDialog extends DialogWrapper {

    private TemplateStructureAddFolderForm form;

    public TemplateStructureAddFolderDialog(@Nullable Project project) {
        super(project, false);
        init();
        initValidation();
        setTitle("Add folder to structure");
    }

    public String getName() {
        return this.form.getNameTextField().getText();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        this.form = new TemplateStructureAddFolderForm();
        return this.form.$$$getRootComponent$$$();
    }

}
