package design.featuresliced.helper.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import design.featuresliced.helper.gui.form.settings.ProjectSettingsForm;
import design.featuresliced.helper.model.ProjectSettings;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.service.ProjectService;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ProjectConfigurable implements SearchableConfigurable {

    private ProjectSettingsForm form;

    private final ProjectService projectService;

    public ProjectConfigurable(Project project) {
        this.projectService = ProjectService.getInstance(project);
    }

    @Override
    public @NotNull @NonNls String getId() {
        return "design.featuresliced.helper.settings.ProjectConfigurable";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Feature-Sliced Design";
    }

    @Override
    public @Nullable JComponent createComponent() {
        this.form = new ProjectSettingsForm(this.projectService.getProject());
        this.form.fillFrom(this.projectService.getState());
        return this.form.getRoot();
    }

    @Override
    public boolean isModified() {
        return this.form.isModified(this.projectService.getState());
    }

    @Override
    public void apply() throws ConfigurationException {

        ProjectSettings projectSettings = this.projectService.getState();

        if (projectSettings == null) {
            projectSettings = new ProjectSettings();
        }

        projectSettings.setSourcesFolder(this.form.getSourceRoot());

        for(LayerType type: LayerType.values()) {
            String value = this.form.getLayerCustomFolderNameBy(type);
            if (value != null) {
                projectSettings.setLayerCustomFolderNameBy(type, value);
            }
        }

        //this.projectService.loadState(projectSettings);

    }

    @Override
    public void reset() {
        if (this.form != null) {
            this.form.fillFrom(this.projectService.getState());
        }
    }

    @Override
    public void disposeUIResources() {
        this.form = null;
    }

}
