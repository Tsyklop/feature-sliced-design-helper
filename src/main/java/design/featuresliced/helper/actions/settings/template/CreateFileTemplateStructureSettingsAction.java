package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.gui.dialog.settings.structure.TemplateStructureAddEditFileDialog;
import design.featuresliced.helper.model.settings.templates.structure.TemplateStructureNode;
import org.jetbrains.annotations.NotNull;

public class CreateFileTemplateStructureSettingsAction extends BaseTemplateStructureSettingsAction {

    public CreateFileTemplateStructureSettingsAction(Tree tree, Project project) {
        super("file", tree, project);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        TemplateStructureAddEditFileDialog dialog = new TemplateStructureAddEditFileDialog(project);

        if (!dialog.showAndGet()) {
            return;
        }

        insertNodeToTree(TemplateStructureNode.fileNode(dialog.getName(), dialog.getExtensionType()));

    }

}
