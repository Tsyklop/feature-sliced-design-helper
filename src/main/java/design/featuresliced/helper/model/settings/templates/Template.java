package design.featuresliced.helper.model.settings.templates;

import design.featuresliced.helper.model.settings.templates.structure.TemplateStructureNode;
import design.featuresliced.helper.model.type.fsd.LayerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Template {

    private String name;

    private LayerType layer;

    private List<TemplateStructureNode> nodes;

    public Template(@NotNull String name) {
        this(name, null, null);
    }

    public Template(@NotNull String name, @Nullable LayerType layer) {
        this(name, layer, null);
    }

    public Template(@NotNull String name, @Nullable LayerType layer, @Nullable List<TemplateStructureNode> nodes) {
        this.name = name;
        this.layer = layer;
        this.nodes = nodes;
    }

    public String getName() {
        return name;
    }

    public LayerType getLayer() {
        return layer;
    }

    public List<TemplateStructureNode> getNodes() {
        return nodes;
    }

}
