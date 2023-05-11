package design.featuresliced.helper.gui.dialog.shared;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.gui.form.fsd.shared.UiSharedForm;
import design.featuresliced.helper.model.type.JsLibraryExtensionsType;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.SegmentType;
import design.featuresliced.helper.service.ProjectGeneralService;
import design.featuresliced.helper.util.FileTemplateUtil;
import design.featuresliced.helper.util.FileUtil;
import design.featuresliced.helper.util.JsLibraryUtil;
import design.featuresliced.helper.util.NotifyUtil;
import design.featuresliced.helper.util.PsiUtil;
import design.featuresliced.helper.util.StyleUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NewUiSharedDialog extends BaseSharedDialog<UiSharedForm> {

    public NewUiSharedDialog(@NotNull Project project, @NotNull JsLibraryType jsLibrary) {
        super("New Shared Ui Component", new UiSharedForm(), project, SegmentType.UI, jsLibrary);
        setDoNotAskOption(new com.intellij.openapi.ui.DoNotAskOption.Adapter() {
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

        ProjectGeneralService projectGeneralService = ProjectGeneralService.getInstance(project);

        final String componentName = this.form.getName();

        final VirtualFile sourcesRoot = projectGeneralService.getSourcesRoot();

        final JsLibraryExtensionsType jsLibraryExtensions = JsLibraryUtil.resolveLibraryExtension(jsLibrary, projectGeneralService.getProjectRoot());

        CommandProcessor.getInstance().executeCommand(project, () -> {

            try {

                VirtualFile createdComponentFile = ApplicationManager.getApplication().runWriteAction((ThrowableComputable<VirtualFile, IOException>) () -> {

                    final VirtualFile sharedUiDir = VfsUtil.createDirectoryIfMissing(
                            sourcesRoot,
                            StringUtils.joinWith(
                                    "/",
                                    getFsdLayerCustomOrDefaultName(),
                                    this.segment.getFolderName()
                            )
                    );

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

                openInEditorIfSelected(PsiUtil.findPsiFileAndNavigate(project, createdComponentFile));

                NotifyUtil.show(this.project, "UI component created!", NotificationType.INFORMATION);

                super.doOKAction();

            } catch (IOException e) {
                NotifyUtil.show(this.project, "Error occurred: " + e.getMessage(), NotificationType.ERROR);
            }

        }, "Create New Ui Component in Shared Layer", null);

    }

}
