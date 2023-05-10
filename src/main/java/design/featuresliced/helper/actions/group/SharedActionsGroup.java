package design.featuresliced.helper.actions.group;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Separator;
import com.intellij.openapi.util.NlsActions;
import design.featuresliced.helper.actions.shared.NewApiInSharedAction;
import design.featuresliced.helper.actions.shared.NewAssetInSharedAction;
import design.featuresliced.helper.actions.shared.NewConfigInSharedAction;
import design.featuresliced.helper.actions.shared.NewCustomInSharedAction;
import design.featuresliced.helper.actions.shared.NewLibInSharedAction;
import design.featuresliced.helper.actions.shared.NewTypeInSharedAction;
import design.featuresliced.helper.actions.shared.NewUiInSharedAction;
import icons.FSDIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SharedActionsGroup extends DefaultActionGroup {

    public SharedActionsGroup(@Nullable @NlsActions.ActionText String shortName) {
        super(shortName, true);
    }

    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        return new AnAction[]{
                new NewUiInSharedAction("ui", "Create in ui slice", FSDIcons.MAIN_ICON),
                new NewApiInSharedAction("api", "Create in api slice", FSDIcons.MAIN_ICON),
                new NewLibInSharedAction("lib", "Create in lib slice", FSDIcons.MAIN_ICON),
                new NewConfigInSharedAction("config", "Create in config slice", FSDIcons.MAIN_ICON),
                new NewAssetInSharedAction("asset", "Create in asset slice", FSDIcons.MAIN_ICON),
                new NewTypeInSharedAction("TS type", "Create in type slice", FSDIcons.MAIN_ICON),
                Separator.getInstance(),
                new NewCustomInSharedAction("Custom", "", FSDIcons.MAIN_ICON)
        };
    }

}
