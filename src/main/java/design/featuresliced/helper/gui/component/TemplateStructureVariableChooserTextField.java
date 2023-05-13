package design.featuresliced.helper.gui.component;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.fields.ExtendableTextComponent;
import com.intellij.ui.components.fields.ExtendableTextField;
import design.featuresliced.helper.gui.dialog.component.TemplateStructureVariableChooserDialog;
import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.HashSet;
import java.util.Set;

public class TemplateStructureVariableChooserTextField extends ExtendableTextField {

    private final Project project;

    private final Set<TemplateStructureVariableType> usedVariables = new HashSet<>();

    public TemplateStructureVariableChooserTextField(@NotNull Project project) {
        super("");
        this.project = project;

        addExtension(
                ExtendableTextComponent.Extension.create(
                        AllIcons.General.InlineVariables,
                        AllIcons.General.InlineVariablesHover,
                        "Choose variable",
                        () -> {

                            TemplateStructureVariableChooserDialog dialog = new TemplateStructureVariableChooserDialog(
                                    this.project,
                                    this,
                                    usedVariables
                            );

                            if (!dialog.showAndGet()) {
                                return;
                            }

                            StringBuilder oldText = new StringBuilder(this.getText());

                            oldText.insert(this.getCaretPosition(), dialog.getSelectedVariableType().valueToVariable());

                            this.setText(oldText.toString());

                            this.usedVariables.add(dialog.getSelectedVariableType());

                        }
                )
        );

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

    }

    public Set<TemplateStructureVariableType> getUsedVariables() {
        return usedVariables;
    }

}
