package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.gui.dialog.settings.structure.TemplateStructureAddEditFolderDialog;
import design.featuresliced.helper.model.settings.templates.structure.TemplateStructureNode;
import org.jetbrains.annotations.NotNull;

public class CreateFolderTemplateStructureSettingsAction extends BaseTemplateStructureSettingsAction {

    public CreateFolderTemplateStructureSettingsAction(Tree tree, Project project) {
        super("folder", tree, project);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        TemplateStructureAddEditFolderDialog dialog = new TemplateStructureAddEditFolderDialog(null, project);

        if (!dialog.showAndGet()) {
            return;
        }

        insertNodeToTree(TemplateStructureNode.folderNode(dialog.getName()));

    }

}
