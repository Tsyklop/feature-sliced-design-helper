package design.featuresliced.helper.model.type;

public enum TemplateStructureNodeType {

    ROOT,
    FILE,
    STYLE,
    FOLDER;

    public boolean isFile() {
        return this == FILE || this == STYLE;
    }

}
