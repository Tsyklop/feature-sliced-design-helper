package design.featuresliced.helper.model;

import java.util.Optional;

public enum FsdLayerType {

    SRC("src", "src"),
    APP("app", "App"),
    PAGES("pages", "Page"),
    SHARED("shared", "Shared"),
    WIDGETS("widgets", "Widget"),
    ENTITIES("entities", "Entity"),
    FEATURES("features", "Feature"),
    PROCESSES("processes", "Process");

    private final String name;

    private final String label;

    FsdLayerType(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public static Optional<FsdLayerType> valueOfOptional(String name) {
        try {
            return Optional.of(valueOf(name));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
