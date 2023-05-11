package design.featuresliced.helper.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.model.settings.ProjectTemplatesSettings;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@State(name = "FeatureSlicedDesignTemplates", storages = {@Storage("feature-sliced-design/templates.xml")})
public final class ProjectTemplatesService implements PersistentStateComponent<ProjectTemplatesSettings> {

    private final Project project;

    private ProjectTemplatesSettings templatesSettings;

    public ProjectTemplatesService(Project project) {
        this.project = project;
    }

    public static ProjectTemplatesService getInstance(Project project) {
        return project.getService(ProjectTemplatesService.class);
    }

    @Override
    public @NotNull ProjectTemplatesSettings getState() {
        if (templatesSettings == null) {
            templatesSettings = new ProjectTemplatesSettings();
        }
        return templatesSettings;
    }

    @Override
    public void loadState(@NotNull ProjectTemplatesSettings settings) {
        this.templatesSettings = settings;
    }

    public Project getProject() {
        return project;
    }

}
