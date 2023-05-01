package design.featuresliced.helper.ui.dialog;

import com.intellij.ide.util.EditorHelper;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.roots.ContentIterator;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileFilter;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiBinaryFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.refactoring.copy.CopyHandler;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.model.SegmentType;
import design.featuresliced.helper.ui.form.BaseForm;
import design.featuresliced.helper.ui.model.FormError;
import design.featuresliced.helper.ui.model.SliceGroup;
import design.featuresliced.helper.ui.model.SliceGroupType;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseDialogWrapper<F extends BaseForm> extends DialogWrapper {

    protected final F form;

    protected final Project project;

    protected final JsLibraryType jsLibrary;

    protected final FsdLayerType fsdLayerType;

    public BaseDialogWrapper(@NotNull String title,
                             @NotNull F form,
                             @NotNull Project project,
                             @NotNull JsLibraryType jsLibrary,
                             @NotNull FsdLayerType fsdLayerType) {
        super(project);
        this.form = form;
        this.project = project;
        this.jsLibrary = jsLibrary;
        this.fsdLayerType = fsdLayerType;
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

    public FsdLayerType getFsdLayerType() {
        return this.fsdLayerType;
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {
        try {
            ApplicationManager.getApplication().runWriteAction((ThrowableComputable<@Nullable ValidationInfo, IOException>) () -> {

                VirtualFile baseDir = VfsUtil.createDirectoryIfMissing(ProjectUtil.guessProjectDir(project), buildPathToLayer());

                FormError formError = this.form.validate(baseDir);

                setOKActionEnabled(formError == null);

                return formError != null ? new ValidationInfo(formError.getType().getMessage(), formError.getComponent()) : null;

            });
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.getRoot();
    }

    /**
     * Concat src and layer name with '/'
     * @return path to layer from project root
     */
    protected String buildPathToLayer() {
        return StringUtils.joinWith(
                "/",
                "src",
                getFsdLayerType().getName()
        );
    }

    protected String buildPathToSegment(SegmentType segmentType) {
        return StringUtils.joinWith(
                buildPathToLayer(),
                segmentType.getFolderName()
        );
    }

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
