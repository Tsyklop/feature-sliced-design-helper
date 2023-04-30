package design.featuresliced.helper.model;

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
