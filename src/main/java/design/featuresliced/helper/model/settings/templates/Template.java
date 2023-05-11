package design.featuresliced.helper.model.settings.templates;

import design.featuresliced.helper.model.settings.templates.structure.TemplateStructureNode;
import design.featuresliced.helper.model.type.TemplateStatusType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Template {

    private String name;

    private LayerType layer;

    private TemplateStatusType status;

    private List<TemplateStructureNode> nodes;

    public Template(@NotNull String name) {
        this(name, null, new ArrayList<>());
    }

    public Template(@NotNull String name, @Nullable LayerType layer) {
        this(name, layer, TemplateStatusType.NEW, new ArrayList<>());
    }

    public Template(@NotNull String name, @Nullable LayerType layer, @NotNull TemplateStatusType status) {
        this(name, layer, status, new ArrayList<>());
    }

    public Template(@NotNull String name, @Nullable LayerType layer, @Nullable List<TemplateStructureNode> nodes) {
        this(name, layer, TemplateStatusType.NEW, nodes);
    }

    public Template(@NotNull String name, @Nullable LayerType layer, @NotNull TemplateStatusType status, @Nullable List<TemplateStructureNode> nodes) {
        this.name = name;
        this.layer = layer;
        this.nodes = nodes;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        changeStatusToChangedIfPossible();
    }

    public LayerType getLayer() {
        return layer;
    }
    public TemplateStatusType getStatus() {
        return status;
    }

    public void changeStatusToSavedIfPossible() {
        /*if (this.status != TemplateStatusType.NEW && this.status != TemplateStatusType.CHANGED) {
            throw new IllegalStateException("Cannot change status to SAVED from " + this.status);
        }*/
        this.status = TemplateStatusType.SAVED;
    }

    public void changeStatusToChangedIfPossible() {
        if (this.status != TemplateStatusType.SAVED) {
            return;
        }
        this.status = TemplateStatusType.CHANGED;
    }

    public List<TemplateStructureNode> getNodes() {
        return nodes;
    }

}
