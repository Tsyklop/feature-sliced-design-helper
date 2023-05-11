package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.gui.dialog.settings.TemplateStructureAddFileDialog;
import design.featuresliced.helper.gui.dialog.settings.TemplateStructureAddStyleDialog;
import design.featuresliced.helper.model.settings.templates.structure.TemplateStructureNode;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class CreateStyleTemplateStructureSettingsAction extends BaseTemplateStructureSettingsAction {

    public CreateStyleTemplateStructureSettingsAction(Tree tree, Project project) {
        super("style", tree, project);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        TemplateStructureAddStyleDialog dialog = new TemplateStructureAddStyleDialog(project);

        if (!dialog.showAndGet()) {
            return;
        }

        insertNodeToTree(TemplateStructureNode.styleNode(dialog.getName(), dialog.getType()));

    }

}
