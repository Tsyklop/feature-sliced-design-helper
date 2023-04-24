package design.featuresliced.helper.ui.dialog;

import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.ui.form.NewEntityForm;
import design.featuresliced.helper.util.Notify;
import design.featuresliced.helper.util.SegmentUtil;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;

public class NewEntityDialog extends BaseDialogWrapper<NewEntityForm> {

    public NewEntityDialog(@NotNull Project project) {
        super("Create Entity", new NewEntityForm(), project, FsdLayerType.ENTITIES);
    }

   /* @Override
    protected @Nullable ValidationInfo doValidate() {
        if (StringUtils.isEmpty(this.form.getNameTextField().getText())) {
            setOKActionEnabled(false);
            return new ValidationInfo("Please enter a feature name", this.form.getNameTextField());
        }
        VirtualFile alreadyExistsEntity = LocalFileSystem.getInstance().findFileByNioFile(
                Path.of(
                        ProjectUtil.guessProjectDir(this.project).getPath(),
                        "src",
                        getFsdLayerType().getName(),
                        this.form.getNameTextField().getText()
                )
        );
        if (alreadyExistsEntity != null) {
            setOKActionEnabled(false);
            return new ValidationInfo("Feature already exists", this.form.getNameTextField());
        }
        if (!this.form.isCreateApiSegment()
                && !this.form.isCreateUiSegment()
                && !this.form.isCreateLibSegment()
                && !this.form.isCreateModelSegment()) {
            setOKActionEnabled(false);
            return new ValidationInfo("Entity already exists", this.form.getNameTextField());
        }
        setOKActionEnabled(true);
        return null;
    }*/

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return this.form.$$$getRootComponent$$$();
    }

    @Override
    protected void doOKAction() {

        final String sliceName = this.form.getNameTextField().getText();

        final VirtualFile projectRoot = ProjectUtil.guessProjectDir(this.project);

        try {

            VirtualFile createdSliceDir = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                VirtualFile featuresDir = VfsUtil.createDirectoryIfMissing(projectRoot, "/src/" + getFsdLayerType().getName());

                VirtualFile newSliceDir = SegmentUtil.createDirectory(sliceName, featuresDir);

                if (form.isCreateUiSegment()) {
                    SegmentUtil.createUiDependsOnAsType(sliceName, form.getUiSegmentAsType(), newSliceDir);
                }

                if (form.isCreateLibSegment()) {
                    SegmentUtil.createLibDependsOnAsType(form.getLibSegmentAsType(), newSliceDir);
                }

                if (form.isCreateModelSegment()) {
                    SegmentUtil.createModelDependsOnAsType(form.getModelSegmentAsType(), newSliceDir);
                }

                VirtualFile newSliceIndexFile = SegmentUtil.createFile("index.ts", newSliceDir);

                if (form.isCreateUiSegment()) {
                    SegmentUtil.writeContentToFile(newSliceIndexFile, String.format(SegmentUtil.UI_DEFAULT_IMPORT_CONTENT, sliceName, sliceName));
                }

                return newSliceDir;

            });

            PsiManager.getInstance(project).findDirectory(createdSliceDir).navigate(true);

        } catch (IOException e) {
            Notify.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
        }

        Notify.show(this.project, "Entity created!", NotificationType.INFORMATION);

        super.doOKAction();

    }
}
