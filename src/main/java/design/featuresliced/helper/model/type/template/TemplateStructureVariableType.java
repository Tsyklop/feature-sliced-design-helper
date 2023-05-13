package design.featuresliced.helper.model.type.template;

public enum TemplateStructureVariableType {

    LAYER_NAME("layerName", "Layer name. Default or custom from settings"),
    GROUP_NAME("groupName", "Group name. If slice or segment will be in group"),
    COMPONENT_NAME("componentName", "Component name. Example: slice name is 'auth-form', component name will be 'AuthForm'"),
    SLICE_OR_SEGMENT_NAME("sliceOrSegmentName", "Slice or segment name");

    private final String value;

    private final String description;

    TemplateStructureVariableType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String valueToVariable() {
        return "<" + this.value + ">";
    }

    @Override
    public String toString() {
        return this.value + " (" + this.description + ")";
    }

}
