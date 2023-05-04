package design.featuresliced.helper.model;

import design.featuresliced.helper.model.type.fsd.LayerType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ProjectSettings {

    private String sourcesFolder;

    private Map<LayerType, String> layerCustomNameByType = new HashMap<>();

    {
        Arrays.stream(LayerType.values()).forEach(layerType -> {
            layerCustomNameByType.put(layerType, layerType.getDefaultFolderName());
        });
    }

    public ProjectSettings() {

    }

    public ProjectSettings(String sourcesFolder) {
        this.sourcesFolder = sourcesFolder;
    }

    public String getSourcesFolder() {
        return sourcesFolder;
    }

    public void setSourcesFolder(String sourcesFolder) {
        this.sourcesFolder = sourcesFolder;
    }

    public void setLayerCustomFolderNameBy(LayerType type, String value) {
        this.layerCustomNameByType.put(type, value);
    }

    public Optional<String> getLayerCustomFolderNameBy(LayerType type) {
        return Optional.ofNullable(this.layerCustomNameByType.get(type));
    }

    public String getLayerCustomFolderNameByOrDefault(LayerType type) {
        return getLayerCustomFolderNameBy(type).orElse(type.getName());
    }

    public Map<LayerType, String> getLayerCustomNameByType() {
        return layerCustomNameByType;
    }

    public void setLayerCustomNameByType(Map<LayerType, String> layerCustomNameByType) {
        this.layerCustomNameByType = layerCustomNameByType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectSettings that = (ProjectSettings) o;
        return Objects.equals(sourcesFolder, that.sourcesFolder) && Objects.equals(layerCustomNameByType, that.layerCustomNameByType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourcesFolder, layerCustomNameByType);
    }
}
