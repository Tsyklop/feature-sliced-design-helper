package design.featuresliced.helper.gui.form.settings.templates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.icons.AllIcons;
import com.intellij.lang.javascript.JavaScriptFileType;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.impl.ContextMenuPopupHandler;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DetailsComponent;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.OnePixelSplitter;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.TextTransferable;
import design.featuresliced.helper.actions.group.TemplateStructureCreateActionGroup;
import design.featuresliced.helper.gui.dialog.component.TemplateStructureVariableChooserDialog;
import design.featuresliced.helper.gui.dialog.settings.confirm.TemplateStructureNodeDeleteConfirmDialog;
import design.featuresliced.helper.gui.dialog.settings.structure.TemplateStructureAddEditFileDialog;
import design.featuresliced.helper.gui.dialog.settings.structure.TemplateStructureAddEditFolderDialog;
import design.featuresliced.helper.gui.dialog.settings.structure.TemplateStructureAddEditStyleDialog;
import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.model.settings.templates.TemplateStructureNode;
import design.featuresliced.helper.model.settings.templates.TemplateStructureNodeTemplate;
import design.featuresliced.helper.model.type.FileExtensionType;
import design.featuresliced.helper.util.TemplateStructureUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TemplateDetailsForm implements Disposable {

    private static final Logger log = Logger.getInstance(TemplateDetailsForm.class);

    private final Project project;

    private final Template template;

    private final EditorTextField editorTextField;

    private final DetailsComponent detailsComponent;

    private final DefaultTreeModel structureTreeModel;

    private final EditorContextMenuPopupHandler editorContextMenuPopupHandler;

    private Tree structureTree;

    private JPanel root;

    private JBSplitter splitter;

    private JPanel toolbarPanel;

    private AnActionButton toolbarAddActionButton;

    private AnActionButton toolbarEditActionButton;

    private AnActionButton toolbarRemoveActionButton;

    private DocumentListener templateDocumentListener;

    public TemplateDetailsForm(@NotNull Project project, @NotNull Template template) {
        this.project = project;
        this.template = template;
        this.editorTextField = new EditorTextField(project, JavaScriptFileType.INSTANCE) {
            @Override
            protected boolean shouldHaveBorder() {
                return false;
            }
        };
        this.editorTextField.setOneLineMode(false);
        this.editorTextField.setFont(JBUI.Fonts.label(13));

        this.detailsComponent = new DetailsComponent(false, true);
        this.detailsComponent.getContentGutter().getInsets().set(0, 5, 0, 5);
        this.structureTreeModel = new DefaultTreeModel(null);
        this.editorContextMenuPopupHandler = new EditorContextMenuPopupHandler(editorTextField);

        $$$setupUI$$$();

        this.detailsComponent.setContent(null);
        this.detailsComponent.setEmptyContentText("Select file from structure");

        fillFormFromTemplateIfExists(template);

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        root = new JPanel();
        root.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        root.setMinimumSize(new Dimension(-1, -1));
        root.setOpaque(true);
        root.setPreferredSize(new Dimension(-1, -1));
        splitter.setName("templateSplitter");
        root.add(splitter, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    public JPanel getRoot() {
        return root;
    }

    @Override
    public void dispose() {
        Disposer.dispose(this);
    }

    private void createUIComponents() {

        this.splitter = new OnePixelSplitter(0.3F);

        this.structureTreeModel.addTreeModelListener(new TreeModelListener() {

            @Override
            public void treeNodesChanged(TreeModelEvent e) {
            }

            @Override
            public void treeNodesInserted(TreeModelEvent e) {

                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) e.getTreePath().getLastPathComponent();

                if (parentNode == null) {
                    log.warn("node is null");
                    return;
                }

                TemplateStructureNode templateStructureParentNode = (TemplateStructureNode) parentNode.getUserObject();

                if (templateStructureParentNode == null) {
                    log.warn("templateStructureNode is null");
                    return;
                }

                templateStructureParentNode.addNode((TemplateStructureNode) ((DefaultMutableTreeNode) e.getChildren()[0]).getUserObject());

                template.changeStatusToChangedIfPossible();

            }

            @Override
            public void treeNodesRemoved(TreeModelEvent e) {
                log.info("treeNodesRemoved: " + e);

                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) e.getTreePath().getLastPathComponent();

                if (parentNode == null) {
                    log.warn("node is null");
                    return;
                }

                TemplateStructureNode templateStructureParentNode = (TemplateStructureNode) parentNode.getUserObject();

                if (templateStructureParentNode == null) {
                    log.warn("templateStructureNode is null");
                    return;
                }

                templateStructureParentNode.removeNode((TemplateStructureNode) ((DefaultMutableTreeNode) e.getChildren()[0]).getUserObject());

                template.changeStatusToChangedIfPossible();

            }

            @Override
            public void treeStructureChanged(TreeModelEvent e) {
            }

        });

        this.structureTree = initStructureTree(this.structureTreeModel);

        this.toolbarPanel = initToolbarPanel(this.structureTree);

        this.toolbarAddActionButton = ToolbarDecorator.findAddButton(this.toolbarPanel);
        this.toolbarEditActionButton = ToolbarDecorator.findEditButton(this.toolbarPanel);
        this.toolbarRemoveActionButton = ToolbarDecorator.findRemoveButton(this.toolbarPanel);

        toggleToolbarButtons(true, false, false);

        JPanel leftPanel = new JPanel(new BorderLayout());

        leftPanel.add(new Label("Structure"), BorderLayout.PAGE_START);
        leftPanel.add(this.toolbarPanel, BorderLayout.CENTER);

        this.splitter.setFirstComponent(leftPanel);

        JPanel rightPanel = new JPanel(new BorderLayout());

        rightPanel.add(new Label("File Template (You can insert variable with Right Mouse Button -> Insert variable)"), BorderLayout.PAGE_START);
        rightPanel.add(this.detailsComponent.getComponent(), BorderLayout.CENTER);

        this.splitter.setSecondComponent(rightPanel);

    }

    private JPanel initToolbarPanel(Tree tree) {

        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(tree)
                .initPosition()
                .setAddActionName("Create structure node")
                .setAddAction(anActionButton -> {

                    ListPopup listPopup = JBPopupFactory.getInstance()
                            .createActionGroupPopup(
                                    "Create",
                                    new TemplateStructureCreateActionGroup(structureTree, project),
                                    anActionButton.getDataContext(),
                                    null,
                                    true,
                                    null,
                                    -1,
                                    null,
                                    ActionPlaces.getActionGroupPopupPlace(null
                                    )
                            );

                    listPopup.show(anActionButton.getPreferredPopupPoint());

                })
                .setEditActionName("Edit structure node")
                .setEditAction(anActionButton -> {

                    TreePath parentPath = structureTree.getSelectionPath();

                    if (parentPath == null) {
                        return;
                    }

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) parentPath.getLastPathComponent();

                    TemplateStructureNode templateStructureNode = (TemplateStructureNode) node.getUserObject();

                    switch (templateStructureNode.getNodeType()) {
                        case FILE -> {
                            TemplateStructureAddEditFileDialog fileDialog = new TemplateStructureAddEditFileDialog(
                                    templateStructureNode.getName(),
                                    templateStructureNode.getExtensionType(),
                                    project
                            );
                            if (!fileDialog.showAndGet()) {
                                return;
                            }
                            updateTemplateStructureNode(templateStructureNode, fileDialog.getName(), fileDialog.getExtensionType());
                            this.structureTreeModel.nodeChanged(node);
                        }
                        case STYLE -> {
                            TemplateStructureAddEditStyleDialog styleDialog = new TemplateStructureAddEditStyleDialog(
                                    templateStructureNode.getName(),
                                    templateStructureNode.getExtensionType(),
                                    project
                            );
                            if (!styleDialog.showAndGet()) {
                                return;
                            }
                            updateTemplateStructureNode(templateStructureNode, styleDialog.getName(), styleDialog.getExtensionType());
                            this.structureTreeModel.nodeChanged(node);
                        }
                        case FOLDER -> {
                            TemplateStructureAddEditFolderDialog folderDialog = new TemplateStructureAddEditFolderDialog(
                                    templateStructureNode.getName(),
                                    project
                            );
                            if (!folderDialog.showAndGet()) {
                                return;
                            }
                            updateTemplateStructureNode(templateStructureNode, folderDialog.getName(), null);
                            this.structureTreeModel.nodeChanged(node);
                        }
                    }

                })
                .setRemoveActionName("Remove structure node")
                .setRemoveAction(anActionButton -> {

                    TreePath parentPath = structureTree.getSelectionPath();

                    if (parentPath == null) {
                        return;
                    }

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) parentPath.getLastPathComponent();

                    TemplateStructureNode templateStructureNode = (TemplateStructureNode) node.getUserObject();

                    if (new TemplateStructureNodeDeleteConfirmDialog(templateStructureNode, project, splitter).showAndGet()) {
                        this.structureTreeModel.removeNodeFromParent((MutableTreeNode) parentPath.getLastPathComponent());
                    }

                })
                .setToolbarPosition(ActionToolbarPosition.TOP);

        return toolbarDecorator.createPanel();

    }

    private Tree initStructureTree(TreeModel treeModel) {

        Tree structureTree = new Tree(treeModel);

        structureTree.setDropMode(DropMode.USE_SELECTION);
        structureTree.setEditable(false);
        structureTree.setDragEnabled(true);
        structureTree.setRootVisible(true);
        structureTree.setShowsRootHandles(false);
        structureTree.setExpandableItemsEnabled(true);

        structureTree.setCellRenderer(new StructureTreeCellRenderer());

        structureTree.setTransferHandler(new StructureTransferHandler(structureTree));

        structureTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        structureTree.getSelectionModel().addTreeSelectionListener(e -> {

            if (this.templateDocumentListener != null) {
                this.editorTextField.getDocument().removeDocumentListener(this.templateDocumentListener);
                this.templateDocumentListener = null;
            }

            DefaultMutableTreeNode selectedNode = ((DefaultMutableTreeNode) this.structureTree.getLastSelectedPathComponent());

            if (selectedNode == null || selectedNode.isRoot()) {
                this.detailsComponent.setContent(null);
                toggleToolbarButtons(true, false, false);
                return;
            }

            TemplateStructureNode templateStructureNode = (TemplateStructureNode) selectedNode.getUserObject();

            if (templateStructureNode == null) {
                resetFileTemplatePanelAndDisableToolbarButtons();
                return;
            }

            if (!templateStructureNode.getNodeType().isFile()) {
                this.detailsComponent.setContent(null);
                toggleToolbarButtons(true);
                return;
            }

            toggleToolbarButtons(false, true, true);

            JPanel jPanel = new JPanel(new GridLayoutManager(1, 1));

            this.editorTextField.setNewDocumentAndFileType(
                    JavaScriptFileType.INSTANCE,
                    EditorFactory.getInstance().createDocument(
                            Optional.ofNullable(templateStructureNode.getTemplate())
                                    .map(TemplateStructureNodeTemplate::getValue)
                                    .orElse("")
                    )
            );

            this.templateDocumentListener = new DocumentListener() {
                @Override
                public void documentChanged(@NotNull DocumentEvent event) {
                    log.info("Document changed");

                    TemplateStructureNodeTemplate nodeTemplate = templateStructureNode.getTemplate();

                    if (nodeTemplate == null) {
                        nodeTemplate = new TemplateStructureNodeTemplate();
                        templateStructureNode.setTemplate(nodeTemplate);
                    }

                    nodeTemplate.setValueAndParseVariables(event.getDocument().getText());

                    template.changeStatusToChangedIfPossible();
                }
            };

            this.editorTextField.getDocument().addDocumentListener(this.templateDocumentListener);

            jPanel.add(this.editorTextField.getComponent(), new GridConstraints(
                    0,
                    0,
                    1,
                    1,
                    GridConstraints.ANCHOR_CENTER,
                    GridConstraints.FILL_BOTH,
                    GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null, 0, false
            ));

            this.detailsComponent.setContent(jPanel);

            if (this.editorTextField.getEditor() != null) {

                this.editorTextField.getEditor().setBorder(JBUI.Borders.empty());
                this.editorTextField.getEditor().getInsets().set(0, 0, 0, 0);

                if (this.editorTextField.getEditor() instanceof EditorImpl editor) {
                    editor.uninstallPopupHandler(this.editorContextMenuPopupHandler);
                    editor.installPopupHandler(this.editorContextMenuPopupHandler);
                }

            }

        });

        return structureTree;
    }

    private void toggleToolbarButtons(boolean enabled) {
        toggleToolbarButtons(enabled, enabled, enabled);
    }

    private void toggleToolbarButtons(boolean addEnabled, boolean editEnabled, boolean removeEnabled) {
        toolbarAddActionButton.setEnabled(addEnabled);
        toolbarEditActionButton.setEnabled(editEnabled);
        toolbarRemoveActionButton.setEnabled(removeEnabled);
    }

    private void fillFormFromTemplateIfExists(Template template) {

        if (template != null) {

            if (template.isNew()) {
                if (template.getRootNode() == null) {
                    template.setRootNode(TemplateStructureNode.layerNode());
                }
            }

            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(template.getRootNode());

            TemplateStructureUtil.populateNodesTree(rootNode, template.getRootNode().getNodes());

            this.structureTreeModel.setRoot(rootNode);

            TemplateStructureUtil.expandAllTreeNodes(this.structureTree, 0, this.structureTree.getRowCount());

        } else {
            this.detailsComponent.setText("New template");
        }

    }

    private void resetFileTemplatePanelAndDisableToolbarButtons() {
        this.detailsComponent.setContent(null);
        toggleToolbarButtons(false);
    }

    private void updateTemplateStructureNode(TemplateStructureNode templateStructureNode,
                                             String updatedName,
                                             FileExtensionType updatedExtensionType) {
        templateStructureNode.setName(updatedName);
        if (updatedExtensionType != null) {
            templateStructureNode.setExtensionType(updatedExtensionType);
        }
        template.changeStatusToChangedIfPossible();
    }


    private static class StructureTransferHandler extends TransferHandler {

        private final Tree structureTree;

        private final ObjectMapper objectMapper;

        private final DefaultTreeModel structureTreeModel;

        private DefaultMutableTreeNode movingNode;

        private StructureTransferHandler(Tree structureTree) {
            this.structureTree = structureTree;
            this.objectMapper = new ObjectMapper();
            this.structureTreeModel = (DefaultTreeModel) structureTree.getModel();
        }

        @Override
        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY_OR_MOVE;
        }

        @Nullable
        @Override
        protected Transferable createTransferable(JComponent c) {
            try {
                movingNode = (DefaultMutableTreeNode) ((Tree) c).getLastSelectedPathComponent();
                return new TextTransferable(objectMapper.writeValueAsString(movingNode.getUserObject()));
            } catch (Exception e) {
                movingNode = null;
                log.error(e);
            }
            return null;
        }

        public boolean canImport(TransferSupport info) {
            // for the demo, we'll only support drops (not clipboard paste)
            if (!info.isDrop()) {
                return false;
            }

            info.setShowDropLocation(true);

            // we only import Strings
            if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return false;
            }

            // fetch the drop location
            JTree.DropLocation dl = (JTree.DropLocation) info.getDropLocation();

            TreePath path = dl.getPath();

            if (path == null) {
                return false;
            }

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

            if (node.isRoot() || node.getUserObject() == null) {
                return false;
            }

            TemplateStructureNode structureNode = ((TemplateStructureNode) node.getUserObject());

            return structureNode.getNodeType().isFolderOrRoot();
        }

        public boolean importData(TransferSupport info) {
            // if we can't handle the import, say so
            if (!canImport(info)) {
                return false;
            }

            // fetch the drop location
            JTree.DropLocation dl = (JTree.DropLocation) info.getDropLocation();

            // fetch the path and child index from the drop location
            TreePath path = dl.getPath();
            int childIndex = dl.getChildIndex();

            // fetch the data and bail if this fails
            TemplateStructureNode data;
            try {
                data = objectMapper.readValue(info.getTransferable().getTransferData(DataFlavor.stringFlavor).toString(), TemplateStructureNode.class);
            } catch (UnsupportedFlavorException | IOException e) {
                return false;
            }

            // if child index is -1, the drop was on top of the path, so we'll
            // treat it as inserting at the end of that path's list of children
            if (childIndex == -1) {
                childIndex = structureTreeModel.getChildCount(path.getLastPathComponent());
            }

            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) path.getLastPathComponent();

            // create a new node to represent the data and insert it into the model

            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(data);

            TemplateStructureUtil.populateNodesTree(newNode, data.getNodes());

            structureTreeModel.insertNodeInto(newNode, parentNode, childIndex);

            // make the new node visible and scroll so that it's visible
            structureTree.makeVisible(path.pathByAddingChild(newNode));
            structureTree.scrollRectToVisible(structureTree.getPathBounds(path.pathByAddingChild(newNode)));

            if (movingNode != null) {
                structureTreeModel.removeNodeFromParent(movingNode);
                movingNode = null;
            }

            TemplateStructureUtil.expandAllTreeNodes(this.structureTree, 0, this.structureTree.getRowCount());

            return true;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            movingNode = null;
        }

    }

    private static class StructureTreeCellRenderer extends DefaultTreeCellRenderer {

        public StructureTreeCellRenderer() {
            this.setLeafIcon(null);
            this.setOpenIcon(null);
            this.setClosedIcon(null);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            if (value instanceof DefaultMutableTreeNode node) {
                if (node.getUserObject() instanceof TemplateStructureNode structureNode) {
                    switch (structureNode.getNodeType()) {
                        case FILE -> setIcon(AllIcons.FileTypes.Text);
                        case STYLE -> setIcon(AllIcons.FileTypes.Css);
                        case ROOT, FOLDER -> setIcon(PlatformIcons.FOLDER_ICON);
                    }
                }
            }
            return this;
        }

    }

    private static class EditorContextMenuPopupHandler extends ContextMenuPopupHandler.Simple {

        private final EditorTextField editorTextField;

        public EditorContextMenuPopupHandler(EditorTextField editorTextField) {
            super(IdeActions.GROUP_BASIC_EDITOR_POPUP);
            this.editorTextField = editorTextField;
        }

        @Override
        public @Nullable ActionGroup getActionGroup(@NotNull EditorMouseEvent event) {
            return new TemplateActionGroup(Arrays.stream(super.getActionGroup(event).getChildren(null)).collect(Collectors.toList()), editorTextField);
        }
    }

    private static class TemplateActionGroup extends DefaultActionGroup {

        private final EditorTextField editorTextField;

        public TemplateActionGroup(@NotNull List<? extends AnAction> actions, EditorTextField editorTextField) {
            super(actions);
            this.editorTextField = editorTextField;
        }

        @Override
        public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {

            AnAction[] defaultActions = super.getChildren(e);

            AnAction[] actions = new AnAction[defaultActions.length + 1];

            System.arraycopy(defaultActions, 0, actions, 0, defaultActions.length);

            actions[actions.length - 1] = new InsertVariableAction(editorTextField);

            return actions;

        }
    }

    private static class InsertVariableAction extends AnAction {

        private final EditorTextField editorTextField;

        public InsertVariableAction(EditorTextField editorTextField) {
            super("Insert variable", "", PlatformIcons.VARIABLE_ICON);
            this.editorTextField = editorTextField;
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {

            TemplateStructureVariableChooserDialog dialog = new TemplateStructureVariableChooserDialog(e.getProject(), null, java.util.Set.of());

            if (!dialog.showAndGet()) {
                return;
            }

            ApplicationManager.getApplication().runWriteAction(() -> {

                Document document = this.editorTextField.getDocument();

                StringBuilder oldText = new StringBuilder(document.getText());

                oldText.insert(
                        this.editorTextField.getCaretModel().getCurrentCaret().getSelectionEnd(),
                        dialog.getSelectedVariableType().valueToVariableName()
                );

                document.setText(oldText.toString());

            });

        }
    }

}
