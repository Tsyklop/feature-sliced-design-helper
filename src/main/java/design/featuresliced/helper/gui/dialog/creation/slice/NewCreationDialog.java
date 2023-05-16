package design.featuresliced.helper.gui.dialog.creation.slice;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.PluginConstant;
import design.featuresliced.helper.gui.dialog.BaseDialogWrapper;
import design.featuresliced.helper.gui.form.fsd.slice.SliceAddForm;
import design.featuresliced.helper.model.settings.ProjectGeneralSettings;
import design.featuresliced.helper.model.settings.templates.Template;
import design.featuresliced.helper.model.settings.templates.TemplateStructureNode;
import design.featuresliced.helper.model.type.JsLibraryType;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.model.type.template.TemplateStructureVariableType;
import design.featuresliced.helper.service.ProjectGeneralService;
import design.featuresliced.helper.service.ProjectTemplatesService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

public class NewCreationDialog extends BaseDialogWrapper<SliceAddForm> {

    public NewCreationDialog(@NotNull String title,
                             @NotNull Project project,
                             @NotNull LayerType layerType,
                             @NotNull JsLibraryType jsLibrary) {
        super(
                title,
                new SliceAddForm(layerType, ProjectTemplatesService.getInstance(project).getState().getTemplatesBy(layerType)),
                project,
                jsLibrary,
                layerType
        );
        init();
        initValidation();
    }

    @Override
    protected void doOKAction() {

        Optional<Template> selectedTemplateOptional = this.form.getSelectedTemplate();

        if (selectedTemplateOptional.isEmpty()) {
            Messages.showErrorDialog(project, "Template not found", PluginConstant.TITLE);
            return;
        }

        ProjectGeneralService generalService = ProjectGeneralService.getInstance(project);

        ProjectGeneralSettings generalSettings = generalService.getState();

        Map<TemplateStructureVariableType, String> variableValueByType = this.form.getVariableValueByType();
        variableValueByType.put(TemplateStructureVariableType.LAYER_NAME, generalSettings.getLayerCustomFolderNameByOrDefault(getFsdLayerType()));

        Template template = selectedTemplateOptional.get();

        CommandProcessor.getInstance().executeCommand(project, () -> {

            try {
                ApplicationManager.getApplication().runWriteAction((ThrowableComputable<Void, IOException>) () -> {
                    createStructure(generalService.getSourcesRoot(), template.getRootNode(), variableValueByType);
                    return null;
                });
            } catch (IOException e) {
                Messages.showErrorDialog(project, "Error occurred: " + e.getMessage(), PluginConstant.ERROR_TITLE);
            }

        }, getTitle(), null);

        super.doOKAction();

    }

    private void createStructure(VirtualFile source, TemplateStructureNode node, Map<TemplateStructureVariableType, String> variableValueByType) throws IOException {

        String nodeFilledName = node.getFilledNameWithExtension(variableValueByType);

        VirtualFile nodeFile = null;

        switch (node.getNodeType()) {
            case FILE, STYLE -> {
                nodeFile = source.findOrCreateChildData(this, nodeFilledName);
                if (node.getTemplate() != null && node.getTemplate().getValue() != null) {
                    String content = new String(nodeFile.contentsToByteArray()) + "\n\n" + node.getTemplate().fillValueWithVariablesValues(variableValueByType);
                    nodeFile.setBinaryContent(content.getBytes(StandardCharsets.UTF_8));
                }
            }
            case ROOT, FOLDER -> {
                nodeFile = source.findChild(nodeFilledName);
                if (nodeFile == null) {
                    nodeFile = source.createChildDirectory(this, nodeFilledName);
                }
            }
        }

        for (TemplateStructureNode childNode: node.getNodes()) {
            createStructure(nodeFile, childNode, variableValueByType);
        }

    }

}
