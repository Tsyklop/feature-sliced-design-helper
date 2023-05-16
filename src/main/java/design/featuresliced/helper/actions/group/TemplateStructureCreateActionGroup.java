package design.featuresliced.helper.actions.group;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import design.featuresliced.helper.actions.settings.template.CreateFileTemplateStructureSettingsAction;
import design.featuresliced.helper.actions.settings.template.CreateFolderTemplateStructureSettingsAction;
import design.featuresliced.helper.actions.settings.template.CreateStyleTemplateStructureSettingsAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TemplateStructureCreateActionGroup extends ActionGroup {

    private final Tree tree;

    private final Project project;

    public TemplateStructureCreateActionGroup(Tree tree, Project project) {
        super("", true);
        this.tree = tree;
        this.project = project;
    }

    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        return new AnAction[]{
                new CreateFileTemplateStructureSettingsAction(this.tree, this.project),
                new CreateFolderTemplateStructureSettingsAction(this.tree, this.project),
                new CreateStyleTemplateStructureSettingsAction(this.tree, this.project),
        };
    }

}
