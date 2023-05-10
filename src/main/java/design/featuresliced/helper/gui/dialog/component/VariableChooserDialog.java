package design.featuresliced.helper.gui.dialog.component;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import design.featuresliced.helper.gui.form.component.VariableChooserForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class VariableChooserDialog extends DialogWrapper {

    private VariableChooserForm form;

    public VariableChooserDialog(@Nullable Project project, @Nullable Component parentComponent) {
        super(project, parentComponent, false, IdeModalityType.PROJECT);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        this.form = new VariableChooserForm();
        return this.form.$$$getRootComponent$$$();
    }

}
