package design.featuresliced.helper.model.type.template;

public enum TemplateStructureVariableType {

    LAYER_NAME("layerName", "Layer name", "Layer name. Default or custom from settings", true),
    GROUP_NAME("groupName", "Group name", "Group name. If slice or segment will be in group"),
    COMPONENT_NAME("componentName", "Component name", "Component name. Example: slice name is 'auth-form', component name will be 'AuthForm'"),
    SLICE_OR_SEGMENT_NAME("sliceOrSegmentName", "Slice or segment name", "Slice or segment name");

    private static final String VARIABLE_SYMBOL = "$$";

    private final String value;

    private final String label;

    private final String description;

    private final boolean byDefault;

    TemplateStructureVariableType(String value, String label, String description) {
        this(value, label, description, false);
    }

    TemplateStructureVariableType(String value, String label, String description, boolean byDefault) {
        this.value = value;
        this.label = label;
        this.description = description;
        this.byDefault = byDefault;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public boolean isByDefault() {
        return byDefault;
    }

    public String getDescription() {
        return description;
    }

    public String valueToVariable() {
        return VARIABLE_SYMBOL + this.value + VARIABLE_SYMBOL;
    }

    @Override
    public String toString() {
        return this.value + " (" + this.description + ")";
    }

}
