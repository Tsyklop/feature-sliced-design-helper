package design.featuresliced.helper.util;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import design.featuresliced.helper.model.type.JsLibraryExtensionsType;
import design.featuresliced.helper.model.type.JsLibraryType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class JsLibraryUtil {

    private static final Logger log = Logger.getInstance(JsLibraryUtil.class);

    private static final Configuration JSPN_PATH_CONFIGURATION = Configuration.builder()
            .options(Option.DEFAULT_PATH_LEAF_TO_NULL)
            .build();

   /* public static JsLibraryType resolveType(@NotNull Project project) {
        return resolveType(ProjectUtil.guessProjectDir(project));
    }*/

    public static JsLibraryType resolveType(@NotNull VirtualFile rootDir) {

        VirtualFile searchDir = rootDir;

        do {

            Optional<VirtualFile> packageJsonFile = getPackageJsonFile(searchDir);

            if (packageJsonFile.isEmpty()) {
                continue;
            }

            try {

                DocumentContext documentContext = JsonPath.using(JSPN_PATH_CONFIGURATION).parse(VfsUtil.loadText(packageJsonFile.get()));

                String reactVersion = documentContext.read("$['dependencies']['react']", String.class);

                if (reactVersion != null) {
                    return JsLibraryType.REACT;
                }

                String vueVersion = documentContext.read("$['dependencies']['vue']", String.class);

                if (vueVersion != null) {
                    return JsLibraryType.VUE;
                }

            } catch (IOException e) {
                log.error("Library type recognizing error: " + e.getMessage());
            }


        } while ((searchDir = searchDir.getParent()) != null);

        throw new IllegalArgumentException("Library type is not recognized in root: " + rootDir.getPath());

    }

    /*public static JsLibraryExtensionsType resolveLibraryExtension(@NotNull JsLibraryType jsLibraryType, @NotNull Project project) {
        return resolveLibraryExtension(jsLibraryType, ProjectUtil.guessProjectDir(project));
    }*/

    public static JsLibraryExtensionsType resolveLibraryExtension(@NotNull JsLibraryType jsLibraryType, @NotNull VirtualFile rootDir) {

        boolean isTypeScriptUsed = isTypeScriptUsed(rootDir);

        return switch (jsLibraryType) {
            case VUE -> isTypeScriptUsed ? JsLibraryExtensionsType.TYPESCRIPT_VUE : JsLibraryExtensionsType.USUAL_VUE;
            case REACT ->
                    isTypeScriptUsed ? JsLibraryExtensionsType.TYPESCRIPT_REACT : JsLibraryExtensionsType.USUAL_REACT;
        };

    }

    public static boolean isTypeScriptUsed(@NotNull VirtualFile rootDir) {

        VirtualFile searchDir = rootDir;

        do {

            VirtualFile tsConfigFile = VfsUtil.findFile(Path.of(searchDir.getPath(), "tsconfig.json"), true);

            if (tsConfigFile != null) {
                return true;
            }

            try {

                Optional<VirtualFile> packageJsonFile = getPackageJsonFile(searchDir);

                if (packageJsonFile.isEmpty()) {
                    continue;
                }

                DocumentContext documentContext = JsonPath.using(JSPN_PATH_CONFIGURATION).parse(VfsUtil.loadText(packageJsonFile.get()));

                String typeScriptVersion = documentContext.read("$['devDependencies']['typescript']", String.class);

                if (typeScriptVersion != null) {
                    return true;
                }

            } catch (IOException e) {
                log.error("Is type script used error : " + e.getMessage());
            }

        } while ((searchDir = searchDir.getParent()) != null);

        return false;

    }

    /*@Deprecated
    public static VirtualFile getPackageJsonFileOrThrow(@NotNull VirtualFile projectDir) {

        VirtualFile packageJsonFile = VfsUtil.findFile(Path.of(projectDir.getPath(), "package.json"), true);

        if (packageJsonFile == null) {
            throw new IllegalArgumentException("package.json not found in project " + projectDir.getName());
        }

        return packageJsonFile;

    }*/

    public static Optional<VirtualFile> getPackageJsonFile(@NotNull VirtualFile projectDir) {
        return Optional.ofNullable(VfsUtil.findFile(Path.of(projectDir.getPath(), "package.json"), true));
    }

}
