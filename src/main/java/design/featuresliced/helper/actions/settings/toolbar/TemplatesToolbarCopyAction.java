package design.featuresliced.helper.actions.settings.toolbar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.TextTransferable;
import design.featuresliced.helper.PluginConstant;
import design.featuresliced.helper.model.settings.templates.Template;
import org.jetbrains.annotations.NotNull;

public class TemplatesToolbarCopyAction extends BaseToolbarSettingsAction {

    private static final Logger log = Logger.getInstance(TemplatesToolbarCopyAction.class);

    private final ObjectMapper objectMapper;

    private final JBList<Template> templatesList;

    public TemplatesToolbarCopyAction(JBList<Template> templatesList) {
        super("Copy", AllIcons.Actions.Copy);
        this.objectMapper = new ObjectMapper();
        this.templatesList = templatesList;
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        Template template = this.templatesList.getSelectedValue();

        if (template == null) {
            return;
        }

        try {
            CopyPasteManager.getInstance().setContents(new TextTransferable(objectMapper.writeValueAsString(template)));
        } catch (JsonProcessingException ex) {
            log.error(ex);
            Messages.showErrorDialog(e.getProject(), "Copy is fail: " + ex.getMessage(), PluginConstant.ERROR_TITLE);
        }

    }

}
