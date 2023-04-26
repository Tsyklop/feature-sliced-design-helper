package design.featuresliced.helper.model;

public enum FileLibraryType {

    JS(".js", ".jsx", ".jsx"),
    VUE(".js", ".vue", ".tsx"),
    VUE_TS(".ts", ".vue", ".tsx"),
    TYPE_SCRIPT(".ts", ".tsx", ".tsx");

    private final String usualExtension;
    private final String componentExtension;
    private final String storyBookExtension;

    FileLibraryType(String usualExtension, String componentExtension, String storyBookExtension) {
        this.usualExtension = usualExtension;
        this.componentExtension = componentExtension;
        this.storyBookExtension = storyBookExtension;
    }

    public String withUsualExt(String name) {
        return name + getUsualExt();
    }

    public String withStoryBookExt(String name) {
        return name + getStoryBookExt();
    }

    public String withComponentExt(String name) {
        return name + getComponentExt();
    }

    public String getUsualExt() {
        return usualExtension;
    }

    public String getComponentExt() {
        return componentExtension;
    }

    public String getStoryBookExt() {
        return storyBookExtension;
    }

}
