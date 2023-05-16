package design.featuresliced.helper.model.type.fsd;

import java.util.Optional;

public enum LayerType {

    APP("app", "App"),
    PAGES("pages", "Page"),
    SHARED("shared", "Shared"),
    WIDGETS("widgets", "Widget"),
    ENTITIES("entities", "Entity"),
    FEATURES("features", "Feature"),
    PROCESSES("processes", "Process");

    private final String name;

    private final String label;

    private final String defaultFolderName;

    LayerType(String name, String label) {
        this(name, label, name);
    }

    LayerType(String name, String label, String defaultFolderName) {
        this.name = name;
        this.label = label;
        this.defaultFolderName = defaultFolderName;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getDefaultFolderName() {
        return defaultFolderName;
    }

    public static Optional<LayerType> valueOfOptional(String name) {
        try {
            return Optional.of(valueOf(name));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
