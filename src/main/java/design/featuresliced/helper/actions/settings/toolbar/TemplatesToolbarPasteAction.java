package design.featuresliced.helper.actions.settings.toolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.components.JBList;
import design.featuresliced.helper.gui.dialog.settings.TemplateAddEditDialog;
import design.featuresliced.helper.model.settings.templates.Template;
import org.jetbrains.annotations.NotNull;

import java.awt.datatransfer.DataFlavor;

public class TemplatesToolbarPasteAction extends BaseToolbarSettingsAction {

    private static final Logger log = Logger.getInstance(TemplatesToolbarPasteAction.class);

    private final ObjectMapper objectMapper;

    private final JBList<Template> templatesList;

    public TemplatesToolbarPasteAction(JBList<Template> templatesList) {
        super("Paste", AllIcons.Actions.MenuPaste);
        this.objectMapper = new ObjectMapper();
        this.templatesList = templatesList;
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        try {

            Template newTemplate = this.objectMapper.readValue((String) CopyPasteManager.getInstance().getContents(DataFlavor.stringFlavor), Template.class);

            TemplateAddEditDialog dialog = new TemplateAddEditDialog(
                    newTemplate.getName() + "(Copy)",
                    newTemplate.getDescription(),
                    e.getProject(),
                    this.templatesList
            );

            if (!dialog.showAndGet()) {
                return;
            }

            newTemplate.setName(dialog.getName());
            newTemplate.changeStatusToNewAndGenerateUuid();

            ((CollectionListModel<Template>) templatesList.getModel()).add(newTemplate);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
