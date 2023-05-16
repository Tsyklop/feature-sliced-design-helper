package design.featuresliced.helper.util;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import design.featuresliced.helper.PluginConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class NotifyUtil {

    private static final String NOTIFICATION_GROUP_ID = "feature-sliced-design-helper-notify-group";

    private static final NotificationGroup NOTIFICATION_GROUP =
            new NotificationGroup(NOTIFICATION_GROUP_ID, NotificationDisplayType.BALLOON, true);

    /**
     * Shows notification.
     *
     * @param project current project
     * @param content notification text
     * @param type    notification type
     */
    public static void show(@Nullable Project project,
                            @Nullable String content,
                            @NotNull NotificationType type) {
        NotificationGroup notificationGroup = NOTIFICATION_GROUP;
        if (content == null) {
            notificationGroup.createNotification(PluginConstant.TITLE, type).notify(project);
        } else {
            notificationGroup.createNotification(PluginConstant.TITLE, content, type).notify(project);
        }
    }

    public static void showWithActions(@Nullable Project project,
                                       @Nullable String content,
                                       @NotNull NotificationType type,
                                       @NotNull List<AnAction> actions) {

        NotificationGroup notificationGroup = NOTIFICATION_GROUP;

        notificationGroup.createNotification(PluginConstant.TITLE, content != null ? content : "", type)
                .addActions((Collection<? extends AnAction>) actions)
                .notify(project);

    }

}
