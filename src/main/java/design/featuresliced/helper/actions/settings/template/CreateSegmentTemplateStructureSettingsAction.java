package design.featuresliced.helper.actions.settings.template;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;

public class CreateSegmentTemplateStructureSettingsAction extends BaseTemplateStructureSettingsAction {

    public CreateSegmentTemplateStructureSettingsAction(Tree tree, Project project) {
        super("segment", tree, project);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {



    }

}
