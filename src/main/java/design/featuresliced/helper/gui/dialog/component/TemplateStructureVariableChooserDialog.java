package design.featuresliced.helper.gui.dialog.component;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import design.featuresliced.helper.gui.form.component.TemplateStructureVariableChooserForm;
import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class TemplateStructureVariableChooserDialog extends DialogWrapper {

    private final TemplateStructureVariableChooserForm form;

    public TemplateStructureVariableChooserDialog(@NotNull Project project,
                                                  @Nullable Component parentComponent,
                                                  @NotNull Set<TemplateStructureVariableType> usedVariables) {
        super(project, parentComponent, false, IdeModalityType.PROJECT);
        this.form = new TemplateStructureVariableChooserForm(usedVariables);
        init();
        initValidation();
        setTitle("Choose variable");
        setSize(800, 210);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.$$$getRootComponent$$$();
    }

    public TemplateStructureVariableType getSelectedVariableType() {
        return this.form.getSelectedVariableType();
    }

}
