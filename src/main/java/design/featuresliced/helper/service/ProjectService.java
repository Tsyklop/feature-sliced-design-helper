package design.featuresliced.helper.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import design.featuresliced.helper.model.ProjectSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "FeatureSlicedDesign", storages = {@Storage("feature-sliced-design.xml")})
public final class ProjectService implements PersistentStateComponent<ProjectSettings> {

    private ProjectSettings settings;

    private final Project project;

    public ProjectService(Project project) {
        this.project = project;
    }

    public static ProjectService getInstance(Project project) {
        return project.getService(ProjectService.class);
    }

    @Override
    public @Nullable ProjectSettings getState() {
        if (settings == null) {
            settings = new ProjectSettings(ProjectUtil.guessProjectDir(this.project).getCanonicalPath());
        }
        return settings;
    }

    @Override
    public void loadState(@NotNull ProjectSettings settings) {
        this.settings = settings;
    }

    public Project getProject() {
        return project;
    }

}
