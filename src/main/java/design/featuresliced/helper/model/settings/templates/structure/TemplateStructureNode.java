package design.featuresliced.helper.model.settings.templates.structure;

import design.featuresliced.helper.model.type.FileExtensionType;
import design.featuresliced.helper.model.type.template.TemplateStructureNodeType;
import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class TemplateStructureNode implements Comparable<TemplateStructureNode> {

    private String name;

    private String template;

    private FileExtensionType extensionType;

    private List<TemplateStructureNode> nodes;

    private TemplateStructureNodeType nodeType;

    private Set<TemplateStructureVariableType> variables;

    public TemplateStructureNode() {
        this("", TemplateStructureNodeType.ROOT);
    }

    public TemplateStructureNode(@NotNull String name,
                                 @NotNull TemplateStructureNodeType nodeType) {
        this(name, null, nodeType, new ArrayList<>());
    }

    public TemplateStructureNode(@NotNull String name,
                                 @Nullable FileExtensionType extensionType,
                                 @NotNull TemplateStructureNodeType nodeType) {
        this(name, extensionType, nodeType, new ArrayList<>());
    }

    public TemplateStructureNode(@NotNull String name,
                                 @Nullable FileExtensionType extensionType,
                                 @NotNull TemplateStructureNodeType nodeType,
                                 @NotNull List<TemplateStructureNode> nodes) {
        this.name = Objects.requireNonNull(name);
        this.nodes = Objects.requireNonNull(nodes);
        this.nodeType = Objects.requireNonNull(nodeType);
        this.extensionType = extensionType;
    }

    public static TemplateStructureNode fileNode(@NotNull String name, @NotNull FileExtensionType extensionType) {
        return createNode(
                name,
                extensionType,
                TemplateStructureNodeType.FILE
        );
    }

    public static TemplateStructureNode styleNode(@NotNull String name, @NotNull FileExtensionType extensionType) {
        return createNode(
                name,
                extensionType,
                TemplateStructureNodeType.STYLE
        );
    }

    public static TemplateStructureNode folderNode(@NotNull String name, @NotNull Set<TemplateStructureVariableType> variables) {
        TemplateStructureNode node = createNode(name, null, TemplateStructureNodeType.FOLDER);
        node.setVariables(variables);
        return node;
    }

    private static TemplateStructureNode createNode(@NotNull String name,
                                                    @Nullable FileExtensionType extensionType,
                                                    @NotNull TemplateStructureNodeType type) {
        return new TemplateStructureNode(
                name,
                extensionType,
                type
        );
    }

    public String getName() {
        return name;
    }

    public @NotNull TemplateStructureNodeType getNodeType() {
        return nodeType;
    }

    public List<TemplateStructureNode> getNodes() {
        return nodes;
    }

    public FileExtensionType getExtensionType() {
        return extensionType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void addNode(TemplateStructureNode node) {
        this.nodes.add(node);
    }

    public void removeNode(TemplateStructureNode node) {
        this.nodes.remove(node);
    }

    public void setExtensionType(FileExtensionType extensionType) {
        this.extensionType = extensionType;
    }

    public void setNodes(List<TemplateStructureNode> nodes) {
        this.nodes = nodes;
    }

    public void setNodeType(TemplateStructureNodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Set<TemplateStructureVariableType> getVariables() {
        return variables;
    }

    public void setVariables(Set<TemplateStructureVariableType> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return name + getExtensionIfNodeFile().orElse("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateStructureNode that = (TemplateStructureNode) o;
        return Objects.equals(name, that.name) && Objects.equals(template, that.template) && extensionType == that.extensionType && Objects.equals(nodes, that.nodes) && nodeType == that.nodeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, template, extensionType, nodes, nodeType);
    }

    @Override
    public int compareTo(@NotNull TemplateStructureNode o) {
        return this.name.compareTo(o.getName());
    }

    private Optional<String> getExtensionIfNodeFile() {
        if (!nodeType.isFile()) {
            return Optional.empty();
        }
        return Optional.of(extensionType != null ? String.join(",", extensionType.getValues()) : "");
    }

}
