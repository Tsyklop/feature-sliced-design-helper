package design.featuresliced.helper.model.settings;

import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.model.type.fsd.LayerType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectTemplatesSettings {

    private Set<Template> templates = new HashSet<>();

    public ProjectTemplatesSettings() {

    }

    public ProjectTemplatesSettings(@NotNull Set<Template> templates) {
        this.templates = new HashSet<>(templates);
    }

    public void addTemplate(Template newTemplate) {
        this.templates.add(newTemplate);
    }

    public @NotNull Set<Template> getAllTemplates() {
        return new HashSet<>(this.templates);
    }

    public @NotNull Set<Template> getTemplatesBy(@NotNull LayerType layer) {
        return this.templates.stream()
                .filter(template -> template.getLayer() == layer)
                .collect(Collectors.toSet());
    }

}
