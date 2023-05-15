package design.featuresliced.helper.util;

import design.featuresliced.helper.model.settings.templates.TemplateStructureNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

public final class TemplateStructureUtil {

    private TemplateStructureUtil() {}

    public static void populateNodesTree(DefaultMutableTreeNode rootNode, List<TemplateStructureNode> nodes) {

        for (TemplateStructureNode node : nodes) {
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node);
            populateNodesTree(treeNode, node.getNodes());
            rootNode.add(treeNode);
        }

    }

    public static void expandAllTreeNodes(JTree tree, int startingIndex, int rowCount) {

        for (int i = startingIndex; i < rowCount; ++i) {
            tree.expandRow(i);
        }

        if (tree.getRowCount() != rowCount) {
            expandAllTreeNodes(tree, rowCount, tree.getRowCount());
        }

    }

}
