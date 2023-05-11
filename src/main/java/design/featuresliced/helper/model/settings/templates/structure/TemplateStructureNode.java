package design.featuresliced.helper.model.settings.templates.structure;

import design.featuresliced.helper.model.type.FileExtensionType;
import design.featuresliced.helper.model.type.TemplateStructureNodeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TemplateStructureNode {

    private String name;

    private String template;

    private FileExtensionType extensionType;

    private final List<TemplateStructureNode> nodes;

    private final @NotNull TemplateStructureNodeType nodeType;

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

    public static TemplateStructureNode fileNode(String name, FileExtensionType extensionType) {
        return new TemplateStructureNode(
                name,
                extensionType,
                TemplateStructureNodeType.FILE
        );
    }

    public static TemplateStructureNode folderNode(String name) {
        return new TemplateStructureNode(
                name,
                TemplateStructureNodeType.FOLDER
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

    @Override
    public String toString() {
        return name + getExtensionIfNodeFile().orElse("");
    }

    private Optional<String> getExtensionIfNodeFile() {
        if (!nodeType.isFile()) {
            return Optional.empty();
        }
        return Optional.of(extensionType != null ? extensionType.getValue() : "");
    }
}
