package design.featuresliced.helper.model.type;

public enum TemplateStructureNodeType {

    ROOT,
    FILE,
    FOLDER;

    public boolean isFile() {
        return this == FILE;
    }

}
