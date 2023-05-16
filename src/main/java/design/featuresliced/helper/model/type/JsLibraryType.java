package design.featuresliced.helper.model.type;

public enum JsLibraryType {

    VUE("Vue"),
    REACT("React");

    private final String label;

    JsLibraryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
