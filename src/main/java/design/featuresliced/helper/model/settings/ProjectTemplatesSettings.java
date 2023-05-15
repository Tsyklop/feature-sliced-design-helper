package design.featuresliced.helper.model.settings;

import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.model.type.fsd.LayerType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ProjectTemplatesSettings {

    private Map<LayerType, Set<Template>> templates;

    public ProjectTemplatesSettings() {
        this.templates = new HashMap<>();
    }

    public ProjectTemplatesSettings(@NotNull Map<LayerType, Set<Template>> templates) {
        this.templates = new HashMap<>(templates);
    }

    public Map<LayerType, Set<Template>> getTemplates() {
        return templates;
    }

    public void addTemplate(LayerType layerType, Template template) {
        this.templates.compute(layerType, (layerType1, templates) -> {
            if (templates == null) {
                templates = new HashSet<>();
            }
            templates.add(template);
            return templates;
        });
    }

    public void removeTemplate(LayerType layerType, Template toRemove) {
        this.templates.computeIfAbsent(layerType, layerType1 -> new HashSet<>()).removeIf(template -> template.getUuid().equals(toRemove.getUuid()));
    }

    public @NotNull Map<LayerType, Set<Template>> getAllTemplates() {
        return new HashMap<>(this.templates);
    }

    public @NotNull Set<Template> getTemplatesBy(@NotNull LayerType layer) {
        return this.templates.computeIfAbsent(layer, layerType -> new HashSet<>());
    }

    public void markAllTemplatesAsSaved() {
        this.templates.values().stream().flatMap(Collection::stream).forEach(Template::changeStatusToSavedIfPossible);
    }

    public void setTemplates(Map<LayerType, Set<Template>> templates) {
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
