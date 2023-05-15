package design.featuresliced.helper.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Transient;
import design.featuresliced.helper.model.settings.ProjectTemplatesSettings;
import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.model.type.JsLibraryExtensionsType;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.util.JsLibraryUtil;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

@State(name = "FeatureSlicedDesignTemplates", storages = {@Storage("feature-sliced-design/templates.xml")})
public final class ProjectTemplatesService implements PersistentStateComponent<ProjectTemplatesSettings> {

    private static final Logger log = Logger.getInstance(ProjectTemplatesService.class);

    @Transient
    private final Project project;

    @Transient
    private final ObjectMapper objectMapper;

    private ProjectTemplatesSettings settings;

    public ProjectTemplatesService(Project project) {
        this.project = project;
        this.objectMapper = new ObjectMapper();
    }

    public static ProjectTemplatesService getInstance(Project project) {
        return project.getService(ProjectTemplatesService.class);
    }

    public Project getProject() {
        return project;
    }

    @Override
    public @NotNull ProjectTemplatesSettings getState() {
        if (this.settings == null) {
            this.settings = loadDefaultTemplatesSettings();
        }
        return this.settings;
    }

    @Override
    public void loadState(@NotNull ProjectTemplatesSettings settings) {
        this.settings = new ProjectTemplatesSettings();
        XmlSerializerUtil.copyBean(settings, this.settings);
    }

    private ProjectTemplatesSettings loadDefaultTemplatesSettings() {

        try {

            ProjectGeneralService projectGeneralService = ProjectGeneralService.getInstance(project);

            VirtualFile sourcesRoot = projectGeneralService.getSourcesRoot();

            if (sourcesRoot == null || !sourcesRoot.exists()) {
                sourcesRoot = projectGeneralService.getProjectRoot();
            }

            JsLibraryType jsLibrary = JsLibraryUtil.resolveType(sourcesRoot);

            JsLibraryExtensionsType jsLibraryExtensions = JsLibraryUtil.resolveLibraryExtension(jsLibrary, sourcesRoot);

            String defaultTemplatesFileName = jsLibrary.name().toLowerCase() + (jsLibraryExtensions.isTypeScript() ? "-ts" : "");

            Map<LayerType, Set<Template>> templates = this.objectMapper.readValue(
                    IOUtils.resourceToString("templates/" + defaultTemplatesFileName + ".json", StandardCharsets.UTF_8, ProjectTemplatesService.class.getClassLoader()),
                    new TypeReference<>() {}
            );

            for (Map.Entry<LayerType, Set<Template>> entry : templates.entrySet()) {
                for (Template template: entry.getValue()) {
                    template.changeStatusToNewAndGenerateUuid();
                    template.changeStatusToSavedIfPossible();
                }
            }

            return new ProjectTemplatesSettings(templates);

        } catch (Exception e) {
            log.error(e);
        }

        return new ProjectTemplatesSettings();

    }

}
