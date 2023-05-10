package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.actions.settings.BaseSettingsAction;
import design.featuresliced.helper.model.settings.templates.structure.TemplateStructureNode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public abstract class BaseTemplateStructureSettingsAction extends BaseSettingsAction {

    protected final Tree tree;

    protected final Project project;

    protected final DefaultTreeModel treeModel;

    protected BaseTemplateStructureSettingsAction(String name, Tree tree, Project project) {
        super(name);
        this.tree = tree;
        this.project = project;
        this.treeModel = (DefaultTreeModel) tree.getModel();
    }

    protected DefaultMutableTreeNode getParentNode() {
        if (tree.getSelectionPath() == null) {
            //There is no selection. Default to the root node.
            return  (DefaultMutableTreeNode) treeModel.getRoot();
        } else {
            return  (DefaultMutableTreeNode) (tree.getSelectionPath().getLastPathComponent());
        }
    }

}
