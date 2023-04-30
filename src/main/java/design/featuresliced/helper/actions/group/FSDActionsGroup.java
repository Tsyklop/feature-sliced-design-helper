package design.featuresliced.helper.actions.group;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Separator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.actions.slice.NewEntityAction;
import design.featuresliced.helper.actions.slice.NewFeatureAction;
import design.featuresliced.helper.actions.slice.NewPageAction;
import design.featuresliced.helper.actions.slice.NewWidgetAction;
import design.featuresliced.helper.model.FsdLayerType;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FSDActionsGroup extends ActionGroup {

    private static final AnAction[] EMPTY_ACTIONS = new AnAction[0];

    @Override
    public void update(@NotNull AnActionEvent e) {

        Project project = e.getProject();

        VirtualFile selectedFile = e.getData(CommonDataKeys.VIRTUAL_FILE);

        if (project == null || selectedFile == null || !selectedFile.isDirectory()) {
            e.getPresentation().setVisible(false);
            return;
        }

        e.getPresentation().setVisible(isSrcFolderOrProjectRoot(project, selectedFile) || FsdLayerType.valueOfOptional(selectedFile.getName().toUpperCase()).isPresent());

    }

    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {

        if (e == null) {
            return EMPTY_ACTIONS;
        }

        Project project = e.getProject();

        VirtualFile selectedFile = e.getData(CommonDataKeys.VIRTUAL_FILE);

        if (project == null || selectedFile == null) {
            return EMPTY_ACTIONS;
        }

        if (isSrcFolderOrProjectRoot(project, selectedFile)) {
            return buildAllActionsArray();
        }

        Optional<FsdLayerType> fsdLayerType = FsdLayerType.valueOfOptional(selectedFile.getName().toUpperCase());

        if (fsdLayerType.isEmpty()) {
            return EMPTY_ACTIONS;
        }

        switch (fsdLayerType.get()) {
            case PAGES:
                return new AnAction[]{buildCreatePageAction()};
            case SHARED:
                return new AnAction[]{buildCreateSharedAction()};
            case WIDGETS:
                return new AnAction[]{buildCreateWidgetAction()};
            case FEATURES:
                return new AnAction[]{buildCreateFeatureAction()};
            case ENTITIES:
                return new AnAction[]{buildCreateEntityAction()};
        }

        return EMPTY_ACTIONS;

    }

    private boolean isSrcFolderOrProjectRoot(Project project, VirtualFile selectedFile) {
        return (project.getBasePath() != null && project.getBasePath().equals(selectedFile.getPath())) || selectedFile.getPath().endsWith("src");
    }

    private @NotNull AnAction[] buildAllActionsArray() {
        return new AnAction[]{
                buildCreatePageAction(),
                buildCreateEntityAction(),
                buildCreateWidgetAction(),
                buildCreateFeatureAction(),
                Separator.getInstance(),
                buildCreateSharedAction(),
        };
    }

    private @NotNull AnAction buildCreatePageAction() {
        return new NewPageAction("Create Page", "Create new page", FSDIcons.MAIN_ICON);
    }

    private @NotNull AnAction buildCreateEntityAction() {
        return new NewEntityAction("Create Entity", "Create new entity", FSDIcons.MAIN_ICON);
    }

    private @NotNull AnAction buildCreateWidgetAction() {
        return new NewWidgetAction("Create Widget", "Create new widget", FSDIcons.MAIN_ICON);
    }

    private @NotNull AnAction buildCreateFeatureAction() {
        return new NewFeatureAction("Create Feature", "Create new feature", FSDIcons.MAIN_ICON);
    }

    private @NotNull AnAction buildCreateSharedAction() {
        return new SharedActionsGroup("Shared");
    }

}
