package design.featuresliced.helper.actions.group;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
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
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.service.ProjectService;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FSDActionsGroup extends ActionGroup {

    private static final AnAction[] EMPTY_ACTIONS = new AnAction[0];

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

    @Override
    public void update(@NotNull AnActionEvent e) {

        Project project = e.getProject();

        VirtualFile selectedFile = e.getData(CommonDataKeys.VIRTUAL_FILE);

        if (project == null || selectedFile == null || !selectedFile.isDirectory()) {
            e.getPresentation().setVisible(false);
            return;
        }

        e.getPresentation().setVisible(isSourcesRootOrProjectRoot(project, selectedFile) || LayerType.valueOfOptional(selectedFile.getName().toUpperCase()).isPresent());

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

        if (isSourcesRootOrProjectRoot(project, selectedFile)) {
            return buildAllActionsArray();
        }

        Optional<LayerType> fsdLayerType = LayerType.valueOfOptional(selectedFile.getName().toUpperCase());

        return fsdLayerType.map(type -> switch (type) {
            case PAGES -> new AnAction[]{buildCreatePageAction()};
            case SHARED -> new AnAction[]{buildCreateSharedAction()};
            case WIDGETS -> new AnAction[]{buildCreateWidgetAction()};
            case FEATURES -> new AnAction[]{buildCreateFeatureAction()};
            case ENTITIES -> new AnAction[]{buildCreateEntityAction()};
            default -> EMPTY_ACTIONS;
        }).orElse(EMPTY_ACTIONS);

    }

    private boolean isSourcesRootOrProjectRoot(Project project, VirtualFile selectedFile) {
        return (project.getBasePath() != null && project.getBasePath().equals(selectedFile.getPath()))
                || ProjectService.getInstance(project).getSourcesRoot().getPath().equals(selectedFile.getPath());
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
