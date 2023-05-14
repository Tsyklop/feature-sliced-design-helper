package design.featuresliced.helper.gui.dialog.settings.confirm;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;
import design.featuresliced.helper.model.settings.templates.Template;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TemplateDeleteConfirmDialog extends DialogWrapper {

    private final Template template;

    public TemplateDeleteConfirmDialog(@NotNull Template template, @Nullable Project project, @Nullable Component parentComponent) {
        super(project, parentComponent, false, IdeModalityType.PROJECT);
        this.template = template;
        init();
        setTitle("Template Deleting");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Deleting \"" + this.template.getName() + "\" template. Are you sure?");
        label.setFont(JBUI.Fonts.label(16));
        dialogPanel.add(label, BorderLayout.CENTER);
        return dialogPanel;
    }

}
