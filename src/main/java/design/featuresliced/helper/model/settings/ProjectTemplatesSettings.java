package design.featuresliced.helper.model.settings;

import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.model.type.fsd.LayerType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectTemplatesSettings {

    private Set<Template> templates;

    public ProjectTemplatesSettings() {
        this.templates = new HashSet<>();
    }

    public ProjectTemplatesSettings(@NotNull Set<Template> templates) {
        this.templates = new HashSet<>(templates);
    }

    public Set<Template> getTemplates() {
        return templates;
    }

    public void addTemplate(Template template) {
        this.templates.add(template);
    }

    public void removeTemplate(Template toRemove) {
        this.templates.removeIf(template -> template.getUuid().equals(toRemove.getUuid()));
    }

    public @NotNull Set<Template> getAllTemplates() {
        return new HashSet<>(this.templates);
    }

    public @NotNull Set<Template> getTemplatesBy(@NotNull LayerType layer) {
        return this.templates.stream()
                .filter(template -> template.getLayer() == layer)
                .collect(Collectors.toSet());
    }

    public void markAllTemplatesAsSaved() {
        for (Template template : this.templates) {
            template.changeStatusToSavedIfPossible();
        }
    }

    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTemplatesSettings that = (ProjectTemplatesSettings) o;
        return Objects.equals(templates, that.templates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templates);
    }

}
