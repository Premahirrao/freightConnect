package com.freightconnect.ui.adapters;

/**
 * NotificationsAdapter - Display list of app notifications
 *
 * Used in:
 * - NotificationsFragment (inbox of all notifications)
 *
 * Features:
 * - Shows notification title, body, and timestamp
 * - Displays notification type as icon and color
 * - Shows read/unread status
 * - Click handling to mark as read
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0010B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/freightconnect/ui/adapters/NotificationsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/freightconnect/model/notifications;", "Lcom/freightconnect/ui/adapters/NotificationsAdapter$NotificationViewHolder;", "onNotificationClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "NotificationViewHolder", "app_debug"})
public final class NotificationsAdapter extends androidx.recyclerview.widget.ListAdapter<com.freightconnect.model.notifications, com.freightconnect.ui.adapters.NotificationsAdapter.NotificationViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.freightconnect.model.notifications, kotlin.Unit> onNotificationClick = null;
    
    public NotificationsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.freightconnect.model.notifications, kotlin.Unit> onNotificationClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.freightconnect.ui.adapters.NotificationsAdapter.NotificationViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.freightconnect.ui.adapters.NotificationsAdapter.NotificationViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001c\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/freightconnect/ui/adapters/NotificationsAdapter$NotificationViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/freightconnect/databinding/ItemNotificationCardBinding;", "(Lcom/freightconnect/ui/adapters/NotificationsAdapter;Lcom/freightconnect/databinding/ItemNotificationCardBinding;)V", "bind", "", "notification", "Lcom/freightconnect/model/notifications;", "formatTimestamp", "", "timestamp", "", "getTypeIconAndColor", "Lkotlin/Pair;", "", "type", "Lcom/freightconnect/model/NotificationType;", "getTypeLabel", "app_debug"})
    public final class NotificationViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.freightconnect.databinding.ItemNotificationCardBinding binding = null;
        
        public NotificationViewHolder(@org.jetbrains.annotations.NotNull()
        com.freightconnect.databinding.ItemNotificationCardBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.freightconnect.model.notifications notification) {
        }
        
        private final java.lang.String formatTimestamp(long timestamp) {
            return null;
        }
        
        private final java.lang.String getTypeLabel(com.freightconnect.model.NotificationType type) {
            return null;
        }
        
        private final kotlin.Pair<java.lang.Integer, java.lang.Integer> getTypeIconAndColor(com.freightconnect.model.NotificationType type) {
            return null;
        }
    }
}