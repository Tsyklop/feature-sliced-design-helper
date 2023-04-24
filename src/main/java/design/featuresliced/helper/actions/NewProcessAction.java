package design.featuresliced.helper.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.NlsActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewProcessAction extends BaseAction {

    public NewProcessAction(@Nullable @NlsActions.ActionText String text,
                            @Nullable @NlsActions.ActionDescription String description,
                            @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

    }

}
