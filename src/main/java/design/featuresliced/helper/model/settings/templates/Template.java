package design.featuresliced.helper.model.settings.templates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.model.type.template.TemplateStatusType;
import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Template implements Comparable<Template> {

    private String uuid;

    private String name;

    private LayerType layer;

    private String description;

    private TemplateStatusType status;

    private TemplateStructureNode rootNode;

    public Template() {
    }

    public Template(@NotNull String name) {
        this(name, null);
    }

    public Template(@NotNull String name, @Nullable LayerType layer) {
        this(name, null, layer, TemplateStatusType.NEW, null);
    }

    public Template(@NotNull String name, @Nullable LayerType layer, @NotNull TemplateStatusType status) {
        this(name, null, layer, status, null);
    }

    public Template(@NotNull String name, @Nullable String description, @Nullable LayerType layer, @NotNull TemplateStatusType status) {
        this(name, description, layer, status, null);
    }

    public Template(@NotNull String name, @Nullable LayerType layer, @Nullable TemplateStructureNode rootNode) {
        this(name, null, layer, TemplateStatusType.NEW, rootNode);
    }

    public Template(@NotNull String name, @Nullable String description, @Nullable LayerType layer, @Nullable TemplateStructureNode rootNode) {
        this(name, description, layer, TemplateStatusType.NEW, rootNode);
    }

    public Template(@NotNull String name, @Nullable String description, @Nullable LayerType layer, @NotNull TemplateStatusType status, @Nullable TemplateStructureNode rootNode) {
        this.name = name;
        this.layer = layer;
        this.status = status;
        this.rootNode = rootNode;
        this.description = description;
    }

    public static Template createTemplate(@NotNull String name, @Nullable String description, @Nullable LayerType layer) {
        return new Template(name, description, layer, TemplateStatusType.NEW).generateAndSetUuid();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public void setLayer(LayerType layer) {
        this.layer = layer;
    }

    public TemplateStatusType getStatus() {
        return status;
    }

    public void setStatus(TemplateStatusType status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public boolean isNew() {
        return this.status == TemplateStatusType.NEW;
    }

    @JsonIgnore
    public boolean isChanged() {
        return this.status == TemplateStatusType.CHANGED;
    }

    @JsonIgnore
    public boolean isRemoved() {
        return this.status == TemplateStatusType.REMOVED;
    }

    public void changeStatusToNewAndGenerateUuid() {
        this.status = TemplateStatusType.NEW;
        setUuid(null);
        generateAndSetUuid();
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

    public void changeStatusToRemovedIfPossible() {
        this.status = TemplateStatusType.REMOVED;
    }

    @JsonIgnore
    public boolean isNewOrChanged() {
        return isNew() || isChanged();
    }

    public TemplateStructureNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TemplateStructureNode rootNode) {
        this.rootNode = rootNode;
    }

    @JsonIgnore
    public Set<TemplateStructureVariableType> getUsedVariables() {
        return new HashSet<>(this.rootNode.getAllVariables());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return Objects.equals(uuid, template.uuid)
                && Objects.equals(name, template.name)
                && Objects.equals(description, template.description)
                && layer == template.layer
                && status == template.status
                && Objects.equals(rootNode, template.rootNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, description, layer, status, rootNode);
    }

    @Override
    public int compareTo(@NotNull Template o) {
        return this.name.compareTo(o.getName());
    }

    private Template generateAndSetUuid() {
        if (this.uuid != null) {
            throw new IllegalStateException();
        }
        this.uuid = UUID.randomUUID().toString();
        return this;
    }

}
