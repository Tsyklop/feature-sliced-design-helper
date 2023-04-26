package design.featuresliced.helper.actions.group;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.actions.NewEntityAction;
import design.featuresliced.helper.actions.NewFeatureAction;
import design.featuresliced.helper.actions.NewPageAction;
import design.featuresliced.helper.actions.NewWidgetAction;
import design.featuresliced.helper.model.FsdLayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FSDActionsGroup extends ActionGroup {

    private static final AnAction[] EMPTY_ACTIONS = new AnAction[0];

    @Override
    public void update(@NotNull AnActionEvent e) {

        VirtualFile virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);

        if (virtualFile == null || !virtualFile.isDirectory()) {
            e.getPresentation().setVisible(false);
            return;
        }

        e.getPresentation().setVisible(FsdLayerType.valueOfOptional(virtualFile.getName().toUpperCase()).isPresent());

    }

    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {

        if (e == null) {
            return EMPTY_ACTIONS;
        }

        VirtualFile virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);

        if (virtualFile == null) {
            return EMPTY_ACTIONS;
        }

        Optional<FsdLayerType> fsdLayerType = FsdLayerType.valueOfOptional(virtualFile.getName().toUpperCase());

        if (fsdLayerType.isEmpty()) {
            return EMPTY_ACTIONS;
        }

        switch (fsdLayerType.get()) {
            case SRC:
                return new AnAction[]{
                        new NewPageAction("Create Page", "Create new page", FSDIcons.MAIN_ICON),
                        new NewEntityAction("Create Entity", "Create new entity", FSDIcons.MAIN_ICON),
                        new NewWidgetAction("Create Widget", "Create new widget", FSDIcons.MAIN_ICON),
                        new NewFeatureAction("Create Feature", "Create new feature", FSDIcons.MAIN_ICON),
                        new SharedActionsGroup("Shared"),
                };
            /*case APP:
                return new AnAction[]{
                        new CreateAppAction("Create App", "Create new app", FSDIcons.MAIN_ICON)
                };*/
            case PAGES:
                return new AnAction[]{
                        new NewPageAction("Create Page", "Create new page", FSDIcons.MAIN_ICON)
                };
            case SHARED:
                return new AnAction[]{
                        new SharedActionsGroup("Shared"),
                };
            case WIDGETS:
                return new AnAction[]{
                        new NewWidgetAction("Create Widget", "Create new Widget", FSDIcons.MAIN_ICON),
                };
            case FEATURES:
                return new AnAction[]{
                        new NewFeatureAction("Create Feature", "Create new feature", FSDIcons.MAIN_ICON),
                };
            case ENTITIES:
                return new AnAction[]{
                        new NewEntityAction("Create Entity", "Create new entity", FSDIcons.MAIN_ICON)
                };
            /*case PROCESSES:
                return new AnAction[]{
                        new NewProcessAction("Create Process", "Create new process", FSDIcons.MAIN_ICON)
                };*/
        }

        return EMPTY_ACTIONS;

    }

}
