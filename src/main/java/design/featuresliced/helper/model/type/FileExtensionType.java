package design.featuresliced.helper.model.type;

public enum FileExtensionType {

    JS(".js", ".js"),
    TS(".ts", ".ts"),
    JSX(".jsx", ".jsx"),
    TSX(".tsx", ".tsx"),
    VUE(".vue", ".vue"),
    AUTO("Auto", "auto");

    private final String label;
    private final String value;

    FileExtensionType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public boolean isAuto() {
        return this == AUTO;
    }

}
