package design.featuresliced.helper.model.settings.templates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import design.featuresliced.helper.model.type.FileExtensionType;
import design.featuresliced.helper.model.type.template.TemplateStructureNodeType;
import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class TemplateStructureNode implements Comparable<TemplateStructureNode> {

    private String name;

    //private String template;

    private FileExtensionType extensionType;

    private List<TemplateStructureNode> nodes;

    private TemplateStructureNodeType nodeType;

    private TemplateStructureNodeTemplate template;

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
                                 @NotNull Set<TemplateStructureVariableType> variables) {
        this(name, extensionType, nodeType, new ArrayList<>(), variables);
    }

    public TemplateStructureNode(@NotNull String name,
                                 @Nullable FileExtensionType extensionType,
                                 @NotNull TemplateStructureNodeType nodeType,
                                 @NotNull List<TemplateStructureNode> nodes) {
        this(name, extensionType, nodeType, nodes, Set.of());
    }

    public TemplateStructureNode(@NotNull String name,
                                 @Nullable FileExtensionType extensionType,
                                 @NotNull TemplateStructureNodeType nodeType,
                                 @NotNull List<TemplateStructureNode> nodes,
                                 @NotNull Set<TemplateStructureVariableType> variables) {
        this.name = Objects.requireNonNull(name);
        this.nodes = Objects.requireNonNull(nodes);
        this.nodeType = Objects.requireNonNull(nodeType);
        this.variables = variables;
        this.extensionType = extensionType;
    }

    public static TemplateStructureNode layerNode() {
        return createNode(
                TemplateStructureVariableType.LAYER_NAME.valueToVariableName(),
                null,
                TemplateStructureNodeType.ROOT,
                Set.of(TemplateStructureVariableType.LAYER_NAME)
        );
    }

    public static TemplateStructureNode fileNode(@NotNull String name,
                                                 @NotNull FileExtensionType extensionType,
                                                 @NotNull Set<TemplateStructureVariableType> variables) {
        return createNode(name, extensionType, TemplateStructureNodeType.FILE, variables);
    }

    public static TemplateStructureNode styleNode(@NotNull String name,
                                                  @NotNull FileExtensionType extensionType,
                                                  @NotNull Set<TemplateStructureVariableType> variables) {
        return createNode(name, extensionType, TemplateStructureNodeType.STYLE, variables);
    }

    public static TemplateStructureNode folderNode(@NotNull String name, @NotNull Set<TemplateStructureVariableType> variables) {
        return createNode(name, null, TemplateStructureNodeType.FOLDER, variables);
    }

    private static TemplateStructureNode createNode(@NotNull String name,
                                                    @Nullable FileExtensionType extensionType,
                                                    @NotNull TemplateStructureNodeType type,
                                                    @NotNull Set<TemplateStructureVariableType> variables) {
        return new TemplateStructureNode(
                name,
                extensionType,
                type,
                variables
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

    public TemplateStructureNodeTemplate getTemplate() {
        return template;
    }

    public void setTemplate(TemplateStructureNodeTemplate template) {
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

    /*public Set<TemplateStructureVariableType> getTemplateVariables() {
        if (StringUtils.isEmpty(this.template)) {
            return Set.of();
        }

        Set<TemplateStructureVariableType> templateVariables = new HashSet<>(getVariables());

        for (TemplateStructureVariableType variableType : TemplateStructureVariableType.values()) {
            if (this.template.contains(variableType.valueToVariableName())) {
                templateVariables.add(variableType);
            }
        }

        return templateVariables;

    }*/

    public void setVariables(Set<TemplateStructureVariableType> variables) {
        this.variables = variables;
    }

    @JsonIgnore
    public Set<TemplateStructureVariableType> getAllVariables() {
        Set<TemplateStructureVariableType> variables = new HashSet<>(getVariables());
        if (getTemplate() != null) {
            variables.addAll(getTemplate().getVariables());
        }
        for (TemplateStructureNode node : nodes) {
            variables.addAll(node.getAllVariables());
        }
        return variables;
    }

    @JsonIgnore
    public String getFilledName(Map<TemplateStructureVariableType, String> variableValueByType) {
        StringBuilder sb = new StringBuilder(this.name);

        for (TemplateStructureVariableType variableType : this.variables) {

            String variableName = variableType.valueToVariableName();

            int variableIndex = 0;

            do {

                variableIndex = sb.indexOf(variableName, variableIndex);

                if (variableIndex >= 0) {
                    sb.replace(variableIndex, variableIndex + variableName.length(), variableValueByType.get(variableType));
                }

            } while (variableIndex >= 0);

        }

        return sb.toString();
    }

    @JsonIgnore
    public String getFilledNameWithExtension(Map<TemplateStructureVariableType, String> variableValueByType) {
        return getFilledName(variableValueByType) + Optional.ofNullable(extensionType).map(FileExtensionType::getValue).orElse("");
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
        return Optional.ofNullable(extensionType).map(FileExtensionType::getValue);
    }

}
