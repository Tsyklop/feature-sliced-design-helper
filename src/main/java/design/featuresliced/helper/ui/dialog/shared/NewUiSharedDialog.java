package design.featuresliced.helper.ui.dialog.shared;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import design.featuresliced.helper.model.JsLibraryExtensionsType;
import design.featuresliced.helper.model.JsLibraryType;
import design.featuresliced.helper.model.SegmentType;
import design.featuresliced.helper.ui.form.shared.UiSharedForm;
import design.featuresliced.helper.util.FileTemplateUtil;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.JsLibraryUtil;
import design.featuresliced.helper.util.NotifyUtil;
import design.featuresliced.helper.util.StyleUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NewUiSharedDialog extends BaseSharedDialog<UiSharedForm> {

    public NewUiSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New Shared Ui Component", new UiSharedForm(), project, jsLibrary);
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

        final String componentName = this.form.getName();

        final VirtualFile projectRoot = ProjectUtil.guessProjectDir(this.project);

        final JsLibraryExtensionsType jsLibraryExtensions = JsLibraryUtil.resolveLibraryExtension(jsLibrary, projectRoot);

        CommandProcessor.getInstance().executeCommand(project, () -> {

            try {

                VirtualFile createdComponentFile = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                    final VirtualFile sharedUiDir = VfsUtil.createDirectoryIfMissing(projectRoot, buildPathToSegment(SegmentType.UI));

                    VirtualFile componentDir = VfsUtil.createDirectoryIfMissing(sharedUiDir, componentName);

                    VirtualFile componentFile = FileUtil.createFile(componentName + jsLibraryExtensions.getComponentExt(), componentDir);

                    String componentTemplate = String.format(FileTemplateUtil.uiComponentTemplateBy(jsLibraryExtensions), componentName);

                    if (this.form.isCreateStyle()) {
                        componentTemplate = StyleUtil.createStyleDependsOnTypeAndReturnImportStatementPart(
                                componentName,
                                componentDir,
                                this.form.getStyleType()
                        ) + componentTemplate;
                    }

                    FileUtil.writeContentToFile(componentFile, componentTemplate);

                    FileUtil.writeContentToFile(
                            FileUtil.createFile(jsLibraryExtensions.withUsualExt("index"), componentDir),
                            String.format("export * from './%s';", componentName)
                    );

                    if (this.form.isCreateStoryBook()) {
                        FileUtil.createFile(jsLibraryExtensions.withStoryBookExt(componentName + ".stories"), componentDir);
                    }

                    return componentFile;

                });

                openInEditorIfSelected(findPsiFileAndNavigate(createdComponentFile));

                NotifyUtil.show(this.project, "UI component created!", NotificationType.INFORMATION);

                super.doOKAction();

            } catch (IOException e) {
                NotifyUtil.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
            }

        }, "Create New Ui Component in Shared Layer", null);

    }

}
