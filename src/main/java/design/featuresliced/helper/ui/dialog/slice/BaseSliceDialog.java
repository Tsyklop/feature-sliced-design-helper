package design.featuresliced.helper.ui.dialog.slice;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import design.featuresliced.helper.model.FileLibraryType;
import design.featuresliced.helper.model.FsdLayerType;
import design.featuresliced.helper.ui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.ui.form.slice.BaseSliceForm;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.NotifyUtil;
import design.featuresliced.helper.util.SegmentUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public abstract class BaseSliceDialog<F extends BaseSliceForm> extends BaseDialogWrapper<F> {

    public BaseSliceDialog(@NotNull String title, @NotNull F form, @NotNull Project project, @NotNull FsdLayerType fsdLayerType) {
        super(title, form, project, fsdLayerType);
        init();
        initValidation();
    }

    @Override
    protected void doOKAction() {

        final String sliceName = this.form.getName();

        final VirtualFile projectRoot = ProjectUtil.guessProjectDir(this.project);

        final FileLibraryType fileLibrary = FileUtil.determineProjectFileLibrary(project);

        try {

            VirtualFile createdSliceDir = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                VirtualFile featuresDir = VfsUtil.createDirectoryIfMissing(projectRoot, "/src/" + getFsdLayerType().getName());

                VirtualFile newSliceDir = FileUtil.createDirectory(sliceName, featuresDir);

                if (form.isCreateUiSegment()) {
                    SegmentUtil.createUiDependsOnAsType(sliceName, form.getUiSegmentAsType(), newSliceDir);
                }

                if (form.isCreateLibSegment()) {
                    SegmentUtil.createLibDependsOnAsType(form.getLibSegmentAsType(), newSliceDir);
                }

                if (form.isCreateModelSegment()) {
                    SegmentUtil.createModelDependsOnAsType(form.getModelSegmentAsType(), newSliceDir);
                }

                VirtualFile newSliceIndexFile = FileUtil.createFile("index.ts", newSliceDir);

                if (form.isCreateUiSegment()) {
                    FileUtil.writeContentToFile(newSliceIndexFile, String.format(SegmentUtil.UI_DEFAULT_IMPORT_CONTENT, sliceName, sliceName));
                }

                return newSliceDir;

            });

            PsiManager.getInstance(project).findDirectory(createdSliceDir).navigate(true);

            NotifyUtil.show(this.project, "Entity created!", NotificationType.INFORMATION);

            super.doOKAction();

        } catch (IOException e) {
            NotifyUtil.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
        }

    }

}
