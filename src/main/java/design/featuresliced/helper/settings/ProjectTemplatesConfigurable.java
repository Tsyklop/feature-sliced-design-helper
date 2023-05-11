package design.featuresliced.helper.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import design.featuresliced.helper.gui.form.settings.TemplatesSettingsForm;
import design.featuresliced.helper.service.ProjectTemplatesService;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ProjectTemplatesConfigurable implements SearchableConfigurable {

    private TemplatesSettingsForm form;

    private final ProjectTemplatesService projectTemplatesService;

    public ProjectTemplatesConfigurable(Project project) {
        this.projectTemplatesService = ProjectTemplatesService.getInstance(project);
    }

    @Override
    public @NotNull @NonNls String getId() {
        return "design.featuresliced.helper.settings.ProjectTemplatesConfigurable";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Feature-Sliced Design";
    }

    @Override
    public @Nullable JComponent createComponent() {
        this.form = new TemplatesSettingsForm(this.projectTemplatesService.getProject());
        //this.form.fillFrom(this.projectService.getState());
        return this.form.$$$getRootComponent$$$();
    }

    @Override
    public boolean isModified() {
        return this.form.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {

        /*ProjectSettings projectSettings = this.projectService.getState();

        if (projectSettings == null) {
            projectSettings = new ProjectSettings();
        }

        projectSettings.setSourcesFolder(this.form.getSourceRoot());

        for(LayerType type: LayerType.values()) {
            String value = this.form.getLayerCustomFolderNameBy(type);
            if (value != null) {
                projectSettings.setLayerCustomFolderNameBy(type, value);
            }
        }*/

        //this.projectService.loadState(projectSettings);

    }

    @Override
    public void reset() {
        if (this.form != null) {
            //this.form.fillFrom(this.projectService.getState());
        }
    }

    @Override
    public void disposeUIResources() {
        if (this.form != null) {
            //this.form.dispose();
            this.form = null;
        }
    }

}
