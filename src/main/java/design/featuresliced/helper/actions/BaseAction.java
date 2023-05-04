package design.featuresliced.helper.actions;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsActions;
import design.featuresliced.helper.gui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.service.ProjectService;
import design.featuresliced.helper.util.JsLibraryUtil;
import design.featuresliced.helper.util.NotifyUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseAction extends AnAction {

    public BaseAction(@Nullable @NlsActions.ActionText String text,
                           @Nullable @NlsActions.ActionDescription String description,
                           @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getProject() != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e.getProject() == null) {
            NotifyUtil.show(null,  "Project is null", NotificationType.ERROR);
        } else {
            try {
                ProjectService projectService = ProjectService.getInstance(e.getProject());
                createDialog(e.getProject(), JsLibraryUtil.resolveType(projectService.getSourcesRoot())).show();
            } catch (Exception ex) {
                Messages.showErrorDialog(e.getProject(), "Error: " + ex.getMessage(), "Feature Sliced Design Error");
            }
        }
    }

    protected abstract @NotNull BaseDialogWrapper<?> createDialog(@NotNull Project project, JsLibraryType jsLibrary);

}
