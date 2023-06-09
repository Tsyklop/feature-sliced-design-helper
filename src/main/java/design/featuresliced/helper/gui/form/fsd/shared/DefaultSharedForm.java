package design.featuresliced.helper.gui.form.fsd.shared;

import com.intellij.ui.IdeBorderFactory;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import design.featuresliced.helper.gui.model.FormError;
import design.featuresliced.helper.model.type.FormErrorType;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DefaultSharedForm implements BaseSharedForm {

    private JPanel root;

    private JTextField nameTextField;

    public DefaultSharedForm() {
        removeInsetsAndBorder(getRoot());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 5, 0));
        root.setAlignmentX(0.5f);
        root.setAlignmentY(0.5f);
        root.setMinimumSize(new Dimension(250, 40));
        root.setName("");
        root.setPreferredSize(new Dimension(250, 40));
        root.setToolTipText("");
        root.setBorder(IdeBorderFactory.PlainSmallWithIndent.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Name: ");
        root.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JTextField();
        root.add(nameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    @Override
    public JPanel getRoot() {
        return root;
    }

    @Override
    public String getName() {
        return nameTextField.getText();
    }

    @Override
    public @Nullable FormError validate(/*@NotNull VirtualFile baseDir*/) {
        if (StringUtils.isEmpty(getName())) {
            return new FormError(FormErrorType.NAME_INCORRECT, this.nameTextField);
        }
        /*if (LocalFileSystem.getInstance().findFileByNioFile(Path.of(baseDir.getPath(), getName())) != null) {
            return new FormError(FormErrorType.ALREADY_EXISTS, this.nameTextField);
        }*/
        return null;
    }

}
