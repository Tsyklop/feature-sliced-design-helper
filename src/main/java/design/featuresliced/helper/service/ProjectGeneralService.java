package design.featuresliced.helper.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.xmlb.annotations.Transient;
import design.featuresliced.helper.model.settings.ProjectGeneralSettings;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@State(name = "FeatureSlicedDesignGeneral", storages = {@Storage("feature-sliced-design/general.xml")})
public final class ProjectGeneralService implements PersistentStateComponent<ProjectGeneralSettings> {

    @Transient
    private final Project project;

    @Transient
    private final VirtualFile projectRoot;

    private ProjectGeneralSettings settings;

    public ProjectGeneralService(Project project) {
        this.project = project;
        this.projectRoot = ProjectUtil.guessProjectDir(project);
    }

    public static ProjectGeneralService getInstance(Project project) {
        return project.getService(ProjectGeneralService.class);
    }

    @Override
    public @NotNull ProjectGeneralSettings getState() {
        if (settings == null) {
            settings = new ProjectGeneralSettings(this.project.getBasePath() + "/src");
        }
        return settings;
    }

    @Override
    public void loadState(@NotNull ProjectGeneralSettings settings) {
        this.settings = settings;
    }

    public Project getProject() {
        return project;
    }

    public VirtualFile getProjectRoot() {
        return this.projectRoot;
    }

    public VirtualFile getSourcesRoot() {
        return VfsUtil.findFile(Path.of(getState().getSourcesFolder()), true);
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
