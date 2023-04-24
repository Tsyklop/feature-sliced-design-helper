package design.featuresliced.helper.model;

public enum SegmentAsType {

    FILE("File"),
    FOLDER("Folder");

    private final String label;

    SegmentAsType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
