package com.freightconnect.network;

/**
 * Task 12: FCMService - Handle push notifications from Firebase Cloud Messaging
 *
 * Handles:
 * - Interest received notifications
 * - Interest accepted/rejected notifications
 * - Cargo/route status updates
 * - FCM token management
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\"\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/freightconnect/network/FCMService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "repo", "Lcom/freightconnect/repository/FreightRepository;", "createChannel", "", "onMessageReceived", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "showNotification", "title", "body", "showNotificationHighPriority", "prefix", "Companion", "app_release"})
public final class FCMService extends com.google.firebase.messaging.FirebaseMessagingService {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "freight_notifications";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_NAME = "Freight Notifications";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID_HIGH = "freight_notifications_high";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_NAME_HIGH = "Freight Alerts";
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.repository.FreightRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.freightconnect.network.FCMService.Companion Companion = null;
    
    public FCMService() {
        super();
    }
    
    @java.lang.Override()
    public void onNewToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    @java.lang.Override()
    public void onMessageReceived(@org.jetbrains.annotations.NotNull()
    com.google.firebase.messaging.RemoteMessage message) {
    }
    
    /**
     * Show high-priority notification (interest/booking updates)
     */
    private final void showNotificationHighPriority(java.lang.String title, java.lang.String body, java.lang.String prefix) {
    }
    
    /**
     * Show standard notification
     */
    private final void showNotification(java.lang.String title, java.lang.String body) {
    }
    
    private final void createChannel() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/freightconnect/network/FCMService$Companion;", "", "()V", "CHANNEL_ID", "", "CHANNEL_ID_HIGH", "CHANNEL_NAME", "CHANNEL_NAME_HIGH", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}