package design.featuresliced.helper.gui.component;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.externalSystem.service.ui.completion.TextCompletionField;
import com.intellij.openapi.externalSystem.service.ui.completion.TextCompletionInfo;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.fields.ExtendableTextComponent;
import design.featuresliced.helper.gui.dialog.component.VariableChooserDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VariableChooserTextField extends TextCompletionField<TextCompletionInfo> {

    public VariableChooserTextField(@Nullable Project project) {
        super(project);
        ExtendableTextComponent.Extension browseExtension = ExtendableTextComponent.Extension.create(
                AllIcons.General.InlineVariables,
                AllIcons.General.InlineVariablesHover,
                "Choose variables",
                () -> {
                    VariableChooserDialog dialog = new VariableChooserDialog(project, this);
                    dialog.showAndGet();

                }
        );
        addExtension(browseExtension);
    }

    @NotNull
    @Override
    protected List<TextCompletionInfo> getCompletionVariants() {
        return null;
    }

}
