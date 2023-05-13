package design.featuresliced.helper.gui.dialog.settings.confirm;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;
import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.model.settings.templates.structure.TemplateStructureNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TemplateStructureNodeDeleteConfirmDialog extends DialogWrapper {

    private final TemplateStructureNode node;

    public TemplateStructureNodeDeleteConfirmDialog(@NotNull TemplateStructureNode node, @Nullable Project project, @Nullable Component parentComponent) {
        super(project, parentComponent, false, IdeModalityType.PROJECT);
        this.node = node;
        init();
        setTitle("Template Structure Node Deleting");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Deleting \"" + this.node.getName() + "\" structure node. Are you sure?");
        label.setFont(JBUI.Fonts.label(16));
        dialogPanel.add(label, BorderLayout.CENTER);
        return dialogPanel;
    }

}
