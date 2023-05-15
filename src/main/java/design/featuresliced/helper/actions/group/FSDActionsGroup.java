package design.featuresliced.helper.actions.group;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Separator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import design.featuresliced.helper.actions.creation.slice.NewEntityCreationAction;
import design.featuresliced.helper.actions.creation.slice.NewFeatureCreationAction;
import design.featuresliced.helper.actions.creation.slice.NewPageCreationAction;
import design.featuresliced.helper.actions.creation.slice.NewWidgetCreationAction;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.service.ProjectGeneralService;
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

        VirtualFile sourcesRoot = ProjectGeneralService.getInstance(project).getSourcesRoot();

        e.getPresentation().setEnabled(sourcesRoot != null);
        e.getPresentation().setDescription(sourcesRoot != null ? "Specify sources root in settings" : "Creates fsd things");

        if (sourcesRoot != null) {
            e.getPresentation().setVisible(
                    isSourcesRootOrProjectRoot(project, sourcesRoot, selectedFile)
                            || LayerType.valueOfOptional(selectedFile.getName().toUpperCase()).isPresent()
            );
        }

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

        VirtualFile sourcesRoot = ProjectGeneralService.getInstance(project).getSourcesRoot();

        if (sourcesRoot == null) {
            return EMPTY_ACTIONS;
        }

        if (isSourcesRootOrProjectRoot(project, sourcesRoot, selectedFile)) {
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

    private boolean isSourcesRootOrProjectRoot(Project project, VirtualFile sourcesRoot, VirtualFile selectedFile) {
        return (project.getBasePath() != null && project.getBasePath().equals(selectedFile.getPath())) || sourcesRoot.getPath().equals(selectedFile.getPath());
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
        return new NewPageCreationAction();
    }

    private @NotNull AnAction buildCreateEntityAction() {
        return new NewEntityCreationAction();
    }

    private @NotNull AnAction buildCreateWidgetAction() {
        return new NewWidgetCreationAction();
    }

    private @NotNull AnAction buildCreateFeatureAction() {
        return new NewFeatureCreationAction();
    }

    private @NotNull AnAction buildCreateSharedAction() {
        return new SharedActionsGroup("Shared");
    }

}
