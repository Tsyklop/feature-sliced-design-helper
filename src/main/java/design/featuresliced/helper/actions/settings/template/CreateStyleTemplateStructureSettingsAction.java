package design.featuresliced.helper.actions.settings.template;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.gui.dialog.settings.structure.TemplateStructureAddEditStyleDialog;
import design.featuresliced.helper.model.settings.templates.TemplateStructureNode;
import org.jetbrains.annotations.NotNull;

public class CreateStyleTemplateStructureSettingsAction extends BaseTemplateStructureSettingsAction {

    public CreateStyleTemplateStructureSettingsAction(Tree tree, Project project) {
        super("style", AllIcons.FileTypes.Css, tree, project);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        TemplateStructureAddEditStyleDialog dialog = new TemplateStructureAddEditStyleDialog(project);

        if (!dialog.showAndGet()) {
            return;
        }

        insertNodeToTree(TemplateStructureNode.styleNode(dialog.getName(), dialog.getExtensionType(), dialog.getUsedVariables()));

    }

}
