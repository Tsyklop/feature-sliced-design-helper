package design.featuresliced.helper.model;

public enum SegmentType {

    UI("ui", "ui.tsx", "ui"),
    API("api", "api.ts", "api"),
    LIB("lib", "lib.ts", "lib"),
    TYPE("types", "types.ts", "types"),
    MODEL("model", "model.ts", "model"),
    CONFIG("config", "config.ts", "config"),
    ASSETS("assets", "assets.ts", "assets");

    private final String label;
    private final String fileName;
    private final String FolderName;

    SegmentType(String label, String fileName, String folderName) {
        this.label = label;
        this.fileName = fileName;
        FolderName = folderName;
    }

    public String getLabel() {
        return label;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFolderName() {
        return FolderName;
    }

}
