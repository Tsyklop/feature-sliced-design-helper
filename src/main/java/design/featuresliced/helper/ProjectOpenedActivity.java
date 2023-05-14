package design.featuresliced.helper;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import design.featuresliced.helper.actions.OpenPluginSettingsAction;
import design.featuresliced.helper.service.ProjectGeneralService;
import design.featuresliced.helper.util.NotifyUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProjectOpenedActivity implements ProjectActivity {

    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {

        ProjectGeneralService projectGeneralService = ProjectGeneralService.getInstance(project);

        try {
            if (projectGeneralService.getSourcesRoot() == null) {
                NotifyUtil.showWithActions(
                        project,
                        "Default sources root not found. Change it in plugin settings for correct work.",
                        NotificationType.WARNING,
                        List.of(new OpenPluginSettingsAction())
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
