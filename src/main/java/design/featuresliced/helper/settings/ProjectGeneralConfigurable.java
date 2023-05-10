package design.featuresliced.helper.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import design.featuresliced.helper.gui.form.settings.GeneralSettingsForm;
import design.featuresliced.helper.model.settings.ProjectGeneralSettings;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.service.ProjectService;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ProjectGeneralConfigurable implements SearchableConfigurable {

    private GeneralSettingsForm form;

    private final ProjectService projectService;

    public ProjectGeneralConfigurable(Project project) {
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
        this.form = new GeneralSettingsForm(this.projectService.getProject());
        this.form.fillFrom(this.projectService.getState());
        return this.form.getRoot();
    }

    @Override
    public boolean isModified() {
        return this.form.isModified(this.projectService.getState());
    }

    @Override
    public void apply() throws ConfigurationException {

        ProjectGeneralSettings projectGeneralSettings = this.projectService.getState();

        if (projectGeneralSettings == null) {
            projectGeneralSettings = new ProjectGeneralSettings();
        }

        projectGeneralSettings.setSourcesFolder(this.form.getSourceRoot());

        for(LayerType type: LayerType.values()) {
            String value = this.form.getLayerCustomFolderNameBy(type);
            if (value != null) {
                projectGeneralSettings.setLayerCustomFolderNameBy(type, value);
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
        if (this.form != null) {
            this.form.dispose();
            this.form = null;
        }
    }

}
