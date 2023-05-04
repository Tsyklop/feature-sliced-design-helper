package design.featuresliced.helper.model.type;

public enum JsLibraryExtensionsType {

    USUAL_VUE(JsLibraryType.VUE, ".js", ".vue", ".js"),
    USUAL_REACT(JsLibraryType.REACT, ".js", ".jsx", ".jsx"),
    TYPESCRIPT_VUE(JsLibraryType.VUE, ".ts", ".vue", ".tsx"),
    TYPESCRIPT_REACT(JsLibraryType.REACT, ".ts", ".tsx", ".tsx");

    private final JsLibraryType library;

    private final String usualExtension;
    private final String componentExtension;
    private final String storyBookExtension;

    JsLibraryExtensionsType(JsLibraryType library, String usualExtension, String componentExtension, String storyBookExtension) {
        this.library = library;
        this.usualExtension = usualExtension;
        this.componentExtension = componentExtension;
        this.storyBookExtension = storyBookExtension;
    }

    public JsLibraryType getLibrary() {
        return library;
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

    public boolean isTypeScript() {
        return this == TYPESCRIPT_VUE || this == TYPESCRIPT_REACT;
    }

}
