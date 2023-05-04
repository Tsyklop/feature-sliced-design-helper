package design.featuresliced.helper.gui.dialog;

import com.intellij.ide.util.EditorHelper;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiBinaryFile;
import com.intellij.psi.PsiFile;
import com.intellij.refactoring.copy.CopyHandler;
import design.featuresliced.helper.gui.form.fsd.BaseFsdForm;
import design.featuresliced.helper.gui.model.FormError;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.service.ProjectService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class BaseDialogWrapper<F extends BaseFsdForm> extends DialogWrapper {

    protected final F form;

    protected final Project project;

    protected final LayerType layerType;

    protected final JsLibraryType jsLibrary;

    protected ProjectService projectService;

    public BaseDialogWrapper(@NotNull String title,
                             @NotNull F form,
                             @NotNull Project project,
                             @NotNull JsLibraryType jsLibrary,
                             @NotNull LayerType layerType) {
        super(project);
        this.form = form;
        this.project = project;
        this.jsLibrary = jsLibrary;
        this.layerType = layerType;
        this.projectService = ProjectService.getInstance(project);
        setTitle(title + " (" + jsLibrary.getLabel() + ")");
    }

    /**
     * Dialog form
     * @return form in dialog
     */
    public F getForm() {
        return this.form;
    }

    public Project getProject() {
        return this.project;
    }

    public JsLibraryType getJsLibrary() {
        return jsLibrary;
    }

    public LayerType getFsdLayerType() {
        return this.layerType;
    }

    public String getFsdLayerCustomOrDefaultName() {
        return this.projectService.getState().getLayerCustomFolderNameByOrDefault(getFsdLayerType());
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {
        FormError formError = this.form.validate();
        setOKActionEnabled(formError == null);
        return formError != null ? new ValidationInfo(formError.getType().getMessage(), formError.getComponent()) : null;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.getRoot();
    }

    /**
     * Concat src and layer name with '/'.
     * Example: $SOURCES_ROOT$/entities, $SOURCES_ROOT$/features
     * @return path to layer from project root
     */
    /*protected String buildPathToLayer(String sourcesRootWithoutProjectDir, String layerName) {
        return StringUtils.joinWith(
                "/",
                sourcesRootWithoutProjectDir,
                layerName
        );
    }

    protected String buildPathToSegment(SegmentType segmentType) {
        return StringUtils.joinWith(
                "/",
                buildPathToLayer(),
                segmentType.getFolderName()
        );
    }*/

    /**
     * Open file in editor if option was selected
     * @param psiFile to open in editor
     */
    protected void openInEditorIfSelected(PsiFile psiFile) {
        if (myCheckBoxDoNotShowDialog != null && myCheckBoxDoNotShowDialog.isSelected()) {
            CopyHandler.updateSelectionInActiveProjectView(psiFile, project, true);
            if (!(psiFile instanceof PsiBinaryFile)) {
                EditorHelper.openInEditor(psiFile);
                ToolWindowManager.getInstance(project).activateEditorComponent();
            }
        }
    }

    /*protected static List<SliceGroup> loadLayerGroups(@NotNull Project project, @NotNull FsdLayerType fsdLayer) {

        List<SliceGroup> groups = new ArrayList<>();

        VirtualFile projectDir = ProjectUtil.guessProjectDir(project);

        VirtualFile layerDir = VfsUtil.findFile(Path.of(
                projectDir.getPath(),
                StringUtils.joinWith(
                        "/",
                        "src",
                        fsdLayer.getName()
                )
        ), true);

        Set<String> ignoreNames = Set.of("ui", "lib", "api", "model", "index");

        for (VirtualFile layerChild : layerDir.getChildren()) {

            List<VirtualFile> filteredLayerChild = Stream.of(layerChild.getChildren())
                    .filter(layerChildChild -> {
                        String nameWithoutExt = FileUtil.getNameWithoutExtension(layerChildChild.getName());
                        return !ignoreNames.contains(nameWithoutExt);
                    })
                    .collect(Collectors.toList());

            filteredLayerChild.forEach(file -> {
                groups.add(new SliceGroup(file.getName(), file.getPath(), SliceGroupType.EXISTED));
            });

        }

        return groups;

    }*/

}
