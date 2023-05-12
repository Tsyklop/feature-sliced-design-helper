package design.featuresliced.helper.model.type.template;

public enum TemplateStructureNodeType {

    ROOT,
    FILE,
    STYLE,
    FOLDER;

    public boolean isFile() {
        return this == FILE || this == STYLE;
    }

    public boolean isFolderOrRoot() {
        return this == TemplateStructureNodeType.FOLDER || this == TemplateStructureNodeType.ROOT;
    }

}
