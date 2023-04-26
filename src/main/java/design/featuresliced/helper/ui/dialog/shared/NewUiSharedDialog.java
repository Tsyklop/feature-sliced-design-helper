package design.featuresliced.helper.ui.dialog.shared;

import com.intellij.ide.util.EditorHelper;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiBinaryFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.refactoring.copy.CopyHandler;
import design.featuresliced.helper.model.ComponentStyleType;
import design.featuresliced.helper.model.FileLibraryType;
import design.featuresliced.helper.model.SegmentType;
import design.featuresliced.helper.ui.form.shared.UiSharedForm;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.NotifyUtil;
import design.featuresliced.helper.util.SegmentUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NewUiSharedDialog extends BaseSharedDialog<UiSharedForm> {

    public NewUiSharedDialog(@NotNull Project project) {
        super("New Shared Ui Component", new UiSharedForm(), project);
        setDoNotAskOption(new DoNotAskOption.Adapter() {
            @Override
            public void rememberChoice(boolean selected, int exitCode) {
                PropertiesComponent.getInstance().setValue("NewUiSharedDialog.OpenInEditor", selected, true);
            }
            @Override
            public boolean isSelectedByDefault() {
                return PropertiesComponent.getInstance().getBoolean("NewUiSharedDialog.OpenInEditor", true);
            }
            @NotNull
            @Override
            public String getDoNotShowMessage() {
                return "&Open in editor";
            }
        });
        init();
        initValidation();
    }

    @Override
    protected void doOKAction() {

        FileLibraryType fileLibrary = FileUtil.determineProjectFileLibrary(project);

        CommandProcessor.getInstance().executeCommand(project, () -> {

            final String componentName = this.form.getName();

            final VirtualFile projectRoot = ProjectUtil.guessProjectDir(this.project);

            try {

                VirtualFile createdComponentFile = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                    final VirtualFile sharedUiDir = VfsUtil.createDirectoryIfMissing(projectRoot, buildPathToSegment(SegmentType.UI));

                    VirtualFile componentDir = VfsUtil.createDirectoryIfMissing(sharedUiDir, componentName);

                    VirtualFile componentFile = FileUtil.createFile(componentName + fileLibrary.getComponentExt(), componentDir);

                    String componentTemplate = String.format(SegmentUtil.UI_DEFAULT_CONTENT, componentName);

                    if (this.form.isCreateStyle()) {

                        ComponentStyleType styleType = this.form.getStyleType();

                        switch (styleType) {
                            case CSS:
                            case SCSS:
                            case INLINE:
                            case CSS_MODULES:

                                String styleFileName = componentName + styleType.getExtension();

                                String importStatementTemplate = "import" + (styleType == ComponentStyleType.CSS_MODULES ? " cls from " : " ") + "'./%s'";

                                componentTemplate = String.format(importStatementTemplate, styleFileName) + "\n\n" + componentTemplate;

                                FileUtil.createFile(styleFileName, componentDir);

                                break;
                            case STYLED_COMPONENTS:
                                componentTemplate = "import styled from 'styled-components';" + "\n\n" + componentTemplate;
                                break;
                        }

                    }

                    FileUtil.writeContentToFile(componentFile, componentTemplate);

                    FileUtil.writeContentToFile(
                            FileUtil.createFile(fileLibrary.withUsualExt("index"), componentDir),
                            String.format("export * from './%s';", componentName)
                    );

                    if (this.form.isCreateStoryBook()) {
                        FileUtil.createFile(fileLibrary.withStoryBookExt(componentName + ".stories"), componentDir);
                    }

                    return componentFile;

                });

                PsiFile psiComponentFile = PsiManager.getInstance(project).findFile(createdComponentFile);
                psiComponentFile.navigate(true);
                openInEditorIfSelected(psiComponentFile);

                NotifyUtil.show(this.project, "UI component created!", NotificationType.INFORMATION);

                super.doOKAction();

            } catch (IOException e) {
                NotifyUtil.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
            }

        }, "Create New Ui Component in Shared Layer", null);

    }

}
