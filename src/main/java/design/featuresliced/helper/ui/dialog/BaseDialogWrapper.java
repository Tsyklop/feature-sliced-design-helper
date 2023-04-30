package design.featuresliced.helper.ui.dialog;

import com.intellij.ide.util.EditorHelper;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
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
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.nio.file.Path;

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

    public F getForm() {
        return this.form;
    }

    public Project getProject() {
        return this.project;
    }

    public VirtualFile getBaseDir() {
        return LocalFileSystem.getInstance().findFileByNioFile(
                Path.of(
                        ProjectUtil.guessProjectDir(project).getPath(),
                        "src",
                        getFsdLayerType().getName()
                )
        );
    }

    public JsLibraryType getJsLibrary() {
        return jsLibrary;
    }

    public FsdLayerType getFsdLayerType() {
        return this.fsdLayerType;
    }

    protected PsiFile findPsiFile(VirtualFile file) {
        return PsiManager.getInstance(this.project).findFile(file);
    }

    protected PsiFile findPsiFileAndNavigate(VirtualFile file) {
        PsiFile psiFile = findPsiFile(file);
        psiFile.navigate(false);
        return psiFile;
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {

        FormError formError = this.form.validate(getBaseDir());

        setOKActionEnabled(formError == null);

        return formError != null ? new ValidationInfo(formError.getType().getMessage(), formError.getComponent()) : null;

    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.getRoot();
    }

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

    protected void openInEditorIfSelected(PsiFile psiFile) {
        if (myCheckBoxDoNotShowDialog != null && myCheckBoxDoNotShowDialog.isSelected()) {
            CopyHandler.updateSelectionInActiveProjectView(psiFile, project, true);
            if (!(psiFile instanceof PsiBinaryFile)) {
                EditorHelper.openInEditor(psiFile);
                ToolWindowManager.getInstance(project).activateEditorComponent();
            }
        }
    }

}
