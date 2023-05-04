package design.featuresliced.helper.model;

import com.intellij.util.xmlb.annotations.MapAnnotation;
import design.featuresliced.helper.model.type.fsd.LayerType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProjectSettings {

    private String sourcesFolder = "/src";

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

    public Map<LayerType, String> getLayerCustomNameByType() {
        return layerCustomNameByType;
    }

    public void setLayerCustomNameByType(Map<LayerType, String> layerCustomNameByType) {
        this.layerCustomNameByType = layerCustomNameByType;
    }
}
