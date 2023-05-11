package design.featuresliced.helper.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Transient;
import design.featuresliced.helper.model.settings.ProjectTemplatesSettings;
import org.jetbrains.annotations.NotNull;

@State(name = "FeatureSlicedDesignTemplates", storages = {@Storage("feature-sliced-design/templates.xml")})
public final class ProjectTemplatesService implements PersistentStateComponent<ProjectTemplatesSettings> {

    @Transient
    private final Project project;

    private ProjectTemplatesSettings settings;

    public ProjectTemplatesService(Project project) {
        this.project = project;
    }

    public static ProjectTemplatesService getInstance(Project project) {
        return project.getService(ProjectTemplatesService.class);
    }

    public Project getProject() {
        return project;
    }

    @Override
    public @NotNull ProjectTemplatesSettings getState() {
        if (this.settings == null) {
            this.settings = new ProjectTemplatesSettings();
        }
        return this.settings;
    }

    @Override
    public void loadState(@NotNull ProjectTemplatesSettings settings) {
        this.settings = new ProjectTemplatesSettings();
        XmlSerializerUtil.copyBean(settings, this.settings);
    }

}
