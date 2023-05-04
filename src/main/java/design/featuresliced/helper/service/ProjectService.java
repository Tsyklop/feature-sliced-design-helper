package design.featuresliced.helper.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.model.ProjectSettings;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@State(name = "FeatureSlicedDesign", storages = {@Storage("feature-sliced-design.xml")})
public final class ProjectService implements PersistentStateComponent<ProjectSettings> {

    private ProjectSettings settings;

    private final Project project;

    private final VirtualFile projectRoot;

    public ProjectService(Project project) {
        this.project = project;
        this.projectRoot = ProjectUtil.guessProjectDir(project);
    }

    public static ProjectService getInstance(Project project) {
        return project.getService(ProjectService.class);
    }

    @Override
    public @NotNull ProjectSettings getState() {
        if (settings == null) {
            settings = new ProjectSettings(this.project.getBasePath() + "/src");
        }
        return settings;
    }

    @Override
    public void loadState(@NotNull ProjectSettings settings) {
        this.settings = settings;
    }

    public Project getProject() {
        return project;
    }

    public VirtualFile getProjectRoot() {
        return this.projectRoot;
    }

    public VirtualFile getSourcesRoot() {
        return VfsUtil.findFile(Path.of(this.settings.getSourcesFolder()), true);
    }

    /**
     * Get sources root without project path.
     * Example: $PROJECT_ROOT$ is E:/project, $SOURCES_ROOT$ is E:/project/packages/frontend.
     *          Result will be next: /packages/frontend
     * @return path to sources root from project dir
     */
    public String getSourcesRootWithoutProjectPath() {
        return getSourcesRoot().getPath().replace(getProjectRoot().getPath(), "");
    }

}
