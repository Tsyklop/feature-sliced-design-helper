package design.featuresliced.helper.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import design.featuresliced.helper.model.JsLibraryExtensionsType;
import design.featuresliced.helper.model.JsLibraryType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;

public class JsLibraryUtil {

    private static final Configuration JSPN_PATH_CONFIGURATION = Configuration.builder()
            .options(Option.DEFAULT_PATH_LEAF_TO_NULL)
            .build();

    public static JsLibraryType resolveType(@NotNull Project project) {
        return resolveType(ProjectUtil.guessProjectDir(project));
    }

    public static JsLibraryType resolveType(@NotNull VirtualFile projectDir) {

        if (projectDir == null) {
            throw new IllegalArgumentException("Project dir not found");
        }

        VirtualFile packageJsonFile = VfsUtil.findFile(Path.of(projectDir.getPath(), "package.json"), true);

        if (packageJsonFile == null) {
            throw new IllegalArgumentException("package.json not found in project " + projectDir.getName());
        }

        try {

            String packageJsonText = VfsUtil.loadText(packageJsonFile);

            DocumentContext documentContext = JsonPath.using(JSPN_PATH_CONFIGURATION).parse(packageJsonText);

            String reactVersion = documentContext.read("$['dependencies']['react']", String.class);

            if (reactVersion != null) {
                return JsLibraryType.REACT;
            }

            String vueVersion = documentContext.read("$['dependencies']['vue']", String.class);

            if (vueVersion != null) {
                return JsLibraryType.VUE;
            }

        } catch (IOException e) {
            throw new IllegalStateException("Library type recognizing error: " + e.getMessage());
        }

        throw new IllegalArgumentException("Library type is not recognized in project: " + projectDir.getName());

    }

    public static JsLibraryExtensionsType resolveLibraryExtension(@NotNull JsLibraryType jsLibraryType, @NotNull Project project) {
        return resolveLibraryExtension(jsLibraryType, ProjectUtil.guessProjectDir(project));
    }

    public static JsLibraryExtensionsType resolveLibraryExtension(@NotNull JsLibraryType jsLibraryType, @NotNull VirtualFile projectDir) {

        if (projectDir == null) {
            throw new IllegalArgumentException("Project dir not found");
        }

        VirtualFile tsConfigFile = VfsUtil.findFile(Path.of(projectDir.getPath(), "tsconfig.json"), true);

        switch (jsLibraryType) {
            case VUE:
                return tsConfigFile != null ? JsLibraryExtensionsType.TYPESCRIPT_VUE : JsLibraryExtensionsType.USUAL_VUE;
            case REACT:
                return tsConfigFile != null ? JsLibraryExtensionsType.TYPESCRIPT_REACT : JsLibraryExtensionsType.USUAL_REACT;
        }

        throw new IllegalArgumentException("Library Extension type is not recognized in project: " + projectDir.getName());

    }

}
