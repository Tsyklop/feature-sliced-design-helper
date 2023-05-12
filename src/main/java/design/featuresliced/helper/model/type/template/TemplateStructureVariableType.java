package design.featuresliced.helper.model.type.template;

public enum TemplateStructureVariableType {

    SLICE_NAME("sliceName"),
    LAYER_NAME("layerName"),
    COMPONENT_NAME("componentName");

    private final String value;

    TemplateStructureVariableType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
