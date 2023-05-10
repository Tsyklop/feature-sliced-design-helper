package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;

public class CreateStyleTemplateStructureSettingsAction extends BaseTemplateStructureSettingsAction {

    public CreateStyleTemplateStructureSettingsAction(Tree tree, Project project) {
        super("style", tree, project);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {



    }

}
