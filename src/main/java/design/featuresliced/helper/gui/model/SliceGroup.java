package design.featuresliced.helper.gui.model;

public class SliceGroup {

    private String name;

    private String path;

    private SliceGroupType type;

    public SliceGroup(String name, String path, SliceGroupType type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SliceGroupType getType() {
        return type;
    }

    public void setType(SliceGroupType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

}
