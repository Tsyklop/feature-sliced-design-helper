package design.featuresliced.helper.ui.dialog.shared;

import com.intellij.openapi.project.Project;
import design.featuresliced.helper.ui.form.shared.DefaultSharedForm;
import org.jetbrains.annotations.NotNull;

public class NewLibSharedDialog extends BaseSharedDialog<DefaultSharedForm> {

    public NewLibSharedDialog(@NotNull Project project) {
        super("New Shared Lib", new DefaultSharedForm(), project);
        init();
        initValidation();
    }

}
