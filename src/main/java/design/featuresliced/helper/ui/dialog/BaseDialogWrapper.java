package design.featuresliced.helper.ui.dialog;

import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.ui.form.BaseForm;
import design.featuresliced.helper.ui.model.FormError;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public abstract class BaseDialogWrapper<F extends BaseForm> extends DialogWrapper {

    protected final F form;

    protected final Project project;

    protected final FsdLayerType fsdLayerType;

    public BaseDialogWrapper(@NotNull String title,
                             @NotNull F form,
                             @NotNull Project project,
                             @NotNull FsdLayerType fsdLayerType) {
        super(project);
        this.form = form;
        this.project = project;
        this.fsdLayerType = fsdLayerType;
        setTitle(title);
        init();
        initValidation();
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

    public FsdLayerType getFsdLayerType() {
        return this.fsdLayerType;
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {

        FormError formError = this.form.validate(getBaseDir());

        setOKActionEnabled(formError == null);

        return formError != null ? new ValidationInfo(formError.getType().getMessage(), formError.getComponent()) : null;

    }

}
