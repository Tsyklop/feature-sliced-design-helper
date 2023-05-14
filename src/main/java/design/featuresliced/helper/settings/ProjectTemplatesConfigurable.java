package design.featuresliced.helper.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import design.featuresliced.helper.gui.form.settings.templates.TemplatesSettingsForm;
import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.service.ProjectTemplatesService;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

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
        return "Feature-Sliced Design Templates";
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

        List<Template> newTemplates = this.form.getNewTemplates();

        for (Template newTemplate : newTemplates) {
            this.projectTemplatesService.getState().addTemplate(newTemplate);
        }

        List<Template> removedTemplates = this.form.getRemovedTemplates();

        for (Template removedTemplate : removedTemplates) {
            this.projectTemplatesService.getState().removeTemplate(removedTemplate);
        }

        this.projectTemplatesService.getState().markAllTemplatesAsSaved();

        this.form.clearRemovedTemplates();

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
