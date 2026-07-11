package com.freightconnect.model;

/**
 * When business owner shows interest in a truck route
 * OR when fleet owner shows interest in a cargo request
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00a5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\u0002\u0010\u0018J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0010H\u00c6\u0003J\t\u00103\u001a\u00020\u0012H\u00c6\u0003J\t\u00104\u001a\u00020\u0014H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0017H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\bH\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\t\u0010=\u001a\u00020\bH\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\u00a9\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u00c6\u0001J\u0013\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010C\u001a\u00020DH\u00d6\u0001J\t\u0010E\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\"R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001a\u00a8\u0006F"}, d2 = {"Lcom/freightconnect/model/BookingInterest;", "", "interestId", "", "initiatorUid", "initiatorName", "initiatorPhone", "initiatorRole", "Lcom/freightconnect/model/UserRole;", "targetUid", "targetName", "targetRole", "routeId", "cargoId", "message", "offeredPrice", "", "goodsWeightTons", "", "status", "Lcom/freightconnect/model/InterestStatus;", "rejectionReason", "createdAt", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/freightconnect/model/UserRole;Ljava/lang/String;Ljava/lang/String;Lcom/freightconnect/model/UserRole;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DFLcom/freightconnect/model/InterestStatus;Ljava/lang/String;J)V", "getCargoId", "()Ljava/lang/String;", "getCreatedAt", "()J", "getGoodsWeightTons", "()F", "getInitiatorName", "getInitiatorPhone", "getInitiatorRole", "()Lcom/freightconnect/model/UserRole;", "getInitiatorUid", "getInterestId", "getMessage", "getOfferedPrice", "()D", "getRejectionReason", "getRouteId", "getStatus", "()Lcom/freightconnect/model/InterestStatus;", "getTargetName", "getTargetRole", "getTargetUid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"})
public final class BookingInterest {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String interestId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String initiatorUid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String initiatorName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String initiatorPhone = null;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.model.UserRole initiatorRole = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String targetUid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String targetName = null;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.model.UserRole targetRole = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String cargoId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String message = null;
    private final double offeredPrice = 0.0;
    private final float goodsWeightTons = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.model.InterestStatus status = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String rejectionReason = null;
    private final long createdAt = 0L;
    
    public BookingInterest(@org.jetbrains.annotations.NotNull()
    java.lang.String interestId, @org.jetbrains.annotations.NotNull()
    java.lang.String initiatorUid, @org.jetbrains.annotations.NotNull()
    java.lang.String initiatorName, @org.jetbrains.annotations.NotNull()
    java.lang.String initiatorPhone, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.UserRole initiatorRole, @org.jetbrains.annotations.NotNull()
    java.lang.String targetUid, @org.jetbrains.annotations.NotNull()
    java.lang.String targetName, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.UserRole targetRole, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String cargoId, @org.jetbrains.annotations.NotNull()
    java.lang.String message, double offeredPrice, float goodsWeightTons, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.InterestStatus status, @org.jetbrains.annotations.NotNull()
    java.lang.String rejectionReason, long createdAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInterestId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInitiatorUid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInitiatorName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInitiatorPhone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.UserRole getInitiatorRole() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTargetUid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTargetName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.UserRole getTargetRole() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCargoId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final double getOfferedPrice() {
        return 0.0;
    }
    
    public final float getGoodsWeightTons() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.InterestStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRejectionReason() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public BookingInterest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final double component12() {
        return 0.0;
    }
    
    public final float component13() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.InterestStatus component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    public final long component16() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.UserRole component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.UserRole component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.BookingInterest copy(@org.jetbrains.annotations.NotNull()
    java.lang.String interestId, @org.jetbrains.annotations.NotNull()
    java.lang.String initiatorUid, @org.jetbrains.annotations.NotNull()
    java.lang.String initiatorName, @org.jetbrains.annotations.NotNull()
    java.lang.String initiatorPhone, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.UserRole initiatorRole, @org.jetbrains.annotations.NotNull()
    java.lang.String targetUid, @org.jetbrains.annotations.NotNull()
    java.lang.String targetName, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.UserRole targetRole, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String cargoId, @org.jetbrains.annotations.NotNull()
    java.lang.String message, double offeredPrice, float goodsWeightTons, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.InterestStatus status, @org.jetbrains.annotations.NotNull()
    java.lang.String rejectionReason, long createdAt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}