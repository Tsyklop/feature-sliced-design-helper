package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.gui.dialog.settings.TemplateStructureAddFolderDialog;
import design.featuresliced.helper.model.settings.templates.structure.TemplateStructureNode;
import design.featuresliced.helper.model.type.TemplateStructureNodeType;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class CreateFolderTemplateStructureSettingsAction extends BaseTemplateStructureSettingsAction {

    public CreateFolderTemplateStructureSettingsAction(Tree tree, Project project) {
        super("folder", tree, project);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        TemplateStructureAddFolderDialog dialog = new TemplateStructureAddFolderDialog(project);

        if (!dialog.showAndGet()) {
            return;
        }

        DefaultMutableTreeNode parentNode = getParentNode();

        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(TemplateStructureNode.folderNode(dialog.getName()));

        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        tree.scrollPathToVisible(new TreePath(childNode.getPath()));

    }

}
