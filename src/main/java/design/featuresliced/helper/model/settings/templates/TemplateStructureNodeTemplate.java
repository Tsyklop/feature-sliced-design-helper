package design.featuresliced.helper.model.settings.templates;

import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TemplateStructureNodeTemplate {

    private String value;

    private Set<TemplateStructureVariableType> variables;

    public TemplateStructureNodeTemplate() {
        this(null);
    }

    public TemplateStructureNodeTemplate(String value) {
        this(value, new HashSet<>());
    }

    public TemplateStructureNodeTemplate(String value, Set<TemplateStructureVariableType> variables) {
        this.value = value;
        this.variables = variables;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValueAndParseVariables(String value) {

        this.value = value;

        this.variables.clear();

        for (TemplateStructureVariableType variableType: TemplateStructureVariableType.values()) {
            if (value.contains(variableType.valueToVariableName())) {
                this.variables.add(variableType);
            }
        }

    }

    public Set<TemplateStructureVariableType> getVariables() {
        return variables;
    }

    public void setVariables(Set<TemplateStructureVariableType> variables) {
        this.variables = variables;
    }

    public String fillValueWithVariablesValues(Map<TemplateStructureVariableType, String> variableValueByType) {
        String finalValue = value;
        for (TemplateStructureVariableType variableType : this.variables) {
            finalValue = finalValue.replace(variableType.valueToVariableName(), variableValueByType.get(variableType));
        }
        return finalValue;
    }

}
