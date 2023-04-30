package design.featuresliced.helper.model;

public enum SegmentType {

    UI("ui", "ui", "ui"),
    API("api", "api", "api"),
    LIB("lib", "lib", "lib"),
    TYPE("types", "types", "types"),
    MODEL("model", "model", "model"),
    CONFIG("config", "config", "config"),
    ASSETS("assets", "assets", "assets");

    private final String label;
    private final String fileName;
    private final String folderName;

    SegmentType(String label, String fileName, String folderName) {
        this.label = label;
        this.fileName = fileName;
        this.folderName = folderName;
    }

    public String getLabel() {
        return label;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFolderName() {
        return folderName;
    }

}
