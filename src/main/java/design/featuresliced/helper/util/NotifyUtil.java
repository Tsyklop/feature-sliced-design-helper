package design.featuresliced.helper.util;

import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NotifyUtil {

    private static final String NOTIFICATION_GROUP_ID = "fsd-idea";

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
        show(project, "Feature Sliced Design", content, type);
    }

    /**
     * Shows notification.
     *
     * @param project current project
     * @param title   notification title
     * @param content notification text
     * @param type    notification type
     */
    public static void show(@Nullable Project project,
                            @NotNull String title,
                            @Nullable String content,
                            @NotNull NotificationType type) {
        NotificationGroup notificationGroup = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID);
        if (content == null) {
            notificationGroup.createNotification(title, type).notify(project);
        } else {
            notificationGroup.createNotification(title, content, type).notify(project);
        }
    }

}
