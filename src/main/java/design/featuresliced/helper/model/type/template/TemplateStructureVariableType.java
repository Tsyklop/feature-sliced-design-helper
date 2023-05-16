package design.featuresliced.helper.model.type.template;

import java.util.Arrays;
import java.util.List;

public enum TemplateStructureVariableType {

    LAYER_NAME(1, "layerName", "Layer name", "Layer name. Default or custom from settings", true),
    GROUP_NAME(2, "groupName", "Group name", "Group name. If slice or segment will be in group"),
    COMPONENT_NAME(4, "componentName", "Component name", "Component name. Example: slice name is 'auth-form', component name will be 'AuthForm'"),
    SLICE_OR_SEGMENT_NAME(3, "sliceOrSegmentName", "Slice or segment name", "Slice or segment name");

    private static final String VARIABLE_SYMBOL = "$$";

    public static final List<TemplateStructureVariableType> AS_LIST = Arrays.stream(values()).toList();

    private final int order;

    private final String value;

    private final String label;

    private final String description;

    private final boolean byDefault;

    TemplateStructureVariableType(int order, String value, String label, String description) {
        this(order, value, label, description, false);
    }

    TemplateStructureVariableType(int order, String value, String label, String description, boolean byDefault) {
        this.order = order;
        this.value = value;
        this.label = label;
        this.description = description;
        this.byDefault = byDefault;
    }

    public int getOrder() {
        return this.order;
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

    public String valueToVariableName() {
        return VARIABLE_SYMBOL + this.value + VARIABLE_SYMBOL;
    }

    public String valueWithDescription() {
        return this.value + " (" + this.description + ")";
    }

}
