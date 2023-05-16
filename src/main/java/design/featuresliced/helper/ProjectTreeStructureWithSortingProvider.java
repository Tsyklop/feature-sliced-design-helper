package design.featuresliced.helper;

import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import design.featuresliced.helper.model.settings.ProjectGeneralSettings;
import design.featuresliced.helper.model.type.fsd.LayerType;
import design.featuresliced.helper.service.ProjectGeneralService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectTreeStructureWithSortingProvider implements TreeStructureProvider {

    private static final Map<LayerType, Integer> orderByLayer = new HashMap<>();

    static {
        orderByLayer.put(LayerType.APP, 1);
        orderByLayer.put(LayerType.PROCESSES, 2);
        orderByLayer.put(LayerType.PAGES, 3);
        orderByLayer.put(LayerType.WIDGETS, 4);
        orderByLayer.put(LayerType.FEATURES, 5);
        orderByLayer.put(LayerType.ENTITIES, 6);
        orderByLayer.put(LayerType.SHARED, 7);
    }

    @Override
    public @NotNull Collection<AbstractTreeNode<?>> modify(@NotNull AbstractTreeNode<?> parent, @NotNull Collection<AbstractTreeNode<?>> children, ViewSettings settings) {

        ProjectGeneralService projectGeneralService = ProjectGeneralService.getInstance(parent.getProject());

        VirtualFile sourcesRoot = projectGeneralService.getSourcesRoot();

        if (!sourcesRoot.exists() || !(parent.getValue() instanceof PsiDirectory)) {
            return children;
        }

        VirtualFile directoryFile = ((PsiDirectory) parent.getValue()).getVirtualFile();

        if (!directoryFile.getPath().equals(sourcesRoot.getPath())) {
            return children;
        }

        /*for (AbstractTreeNode<?> childNode: children) {

            if (!(childNode.getValue() instanceof PsiDirectory)) {
                continue;
            }

            VirtualFile PsiUtilBase.asVirtualFile((PsiDirectory) childNode.getValue());

        }*/

        // Sort nodes according to FSD folders structure
        return children.stream()
                .sorted(new Comparator<AbstractTreeNode<?>>() {
                    @Override
                    public int compare(AbstractTreeNode<?> o1, AbstractTreeNode<?> o2) {

                        if (!(o1.getValue() instanceof PsiDirectory) || !(o2.getValue() instanceof PsiDirectory)) {
                            return 0;
                        }

                        VirtualFile o1Dir = ((PsiDirectory) o1.getValue()).getVirtualFile();
                        VirtualFile o2Dir = ((PsiDirectory) o2.getValue()).getVirtualFile();

                        LayerType o1Layer = determineDirectoryLayer(o1Dir, projectGeneralService.getState());
                        LayerType o2Layer = determineDirectoryLayer(o2Dir, projectGeneralService.getState());

                        if (o1Layer == null || o2Layer == null) {
                            return 0;
                        }

                        o1.setChildrenSortingStamp(orderByLayer.get(o1Layer));
                        o1.setChildrenSortingStamp(orderByLayer.get(o2Layer));

                        return orderByLayer.get(o2Layer) - orderByLayer.get(o1Layer);

                    }
                })
                .collect(Collectors.toList());

    }

    private static @Nullable LayerType determineDirectoryLayer(@NotNull VirtualFile dir, @NotNull ProjectGeneralSettings generalSettings) {

        for (LayerType layerType : LayerType.values()) {

            String layerDirectoryName = generalSettings.getLayerCustomFolderNameByOrDefault(layerType);

            if (dir.getName().equals(layerDirectoryName)) {
                return layerType;
            }

        }

        return null;

    }

}
