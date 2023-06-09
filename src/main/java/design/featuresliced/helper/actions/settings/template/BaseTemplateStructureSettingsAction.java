package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.actions.settings.BaseSettingsAction;
import design.featuresliced.helper.model.settings.templates.TemplateStructureNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public abstract class BaseTemplateStructureSettingsAction extends BaseSettingsAction {

    protected final Tree tree;

    protected final Project project;

    protected final DefaultTreeModel treeModel;

    public BaseTemplateStructureSettingsAction(@NotNull String name, @NotNull Tree tree, @NotNull Project project) {
        this(name, null, tree, project);
    }

    public BaseTemplateStructureSettingsAction(@NotNull String name, @Nullable Icon icon, Tree tree, Project project) {
        super(name, icon);
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

    protected void insertNodeToTree(@NotNull TemplateStructureNode node) {

        DefaultMutableTreeNode parentNode = getParentNode();

        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(node);

        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
        tree.scrollPathToVisible(new TreePath(childNode.getPath()));

    }

}
