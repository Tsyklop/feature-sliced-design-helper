package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.actions.settings.BaseSettingsAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultTreeModel;

public class CreateIndexTemplateStructureSettingsAction extends BaseTemplateStructureSettingsAction {

    public CreateIndexTemplateStructureSettingsAction(Tree tree, Project project) {
        super("index", tree, project);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {



    }

}
