package com.freightconnect.model;

/**
 * Posted by BUSINESS_OWNER: "I need to transport X tons from A to B"
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b>\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0089\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\f\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0003\u0012\b\b\u0002\u0010 \u001a\u00020\u0003\u0012\b\b\u0002\u0010!\u001a\u00020\u0019\u0012\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030#\u00a2\u0006\u0002\u0010$J\t\u0010G\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\t\u0010J\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\fH\u00c6\u0003J\t\u0010O\u001a\u00020\u0017H\u00c6\u0003J\t\u0010P\u001a\u00020\u0019H\u00c6\u0003J\t\u0010Q\u001a\u00020\u001bH\u00c6\u0003J\t\u0010R\u001a\u00020\u0003H\u00c6\u0003J\t\u0010S\u001a\u00020\u0003H\u00c6\u0003J\t\u0010T\u001a\u00020\u001eH\u00c6\u0003J\t\u0010U\u001a\u00020\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\u0003H\u00c6\u0003J\t\u0010W\u001a\u00020\u0019H\u00c6\u0003J\u000f\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00030#H\u00c6\u0003J\t\u0010Y\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Z\u001a\u00020\u0003H\u00c6\u0003J\t\u0010[\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\\\u001a\u00020\tH\u00c6\u0003J\t\u0010]\u001a\u00020\u0003H\u00c6\u0003J\t\u0010^\u001a\u00020\fH\u00c6\u0003J\t\u0010_\u001a\u00020\u0003H\u00c6\u0003J\u008d\u0002\u0010`\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00192\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030#H\u00c6\u0001J\u0013\u0010a\u001a\u00020b2\b\u0010c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010d\u001a\u00020eH\u00d6\u0001J\t\u0010f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010 \u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u001f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010&R\u0011\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010&R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010&R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u0011\u0010!\u001a\u00020\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010&R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010&R\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010&R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0015\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010&R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030#\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010&R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010&R\u0011\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u00100R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u00105R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010&R\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u00107\u00a8\u0006g"}, d2 = {"Lcom/freightconnect/model/CargoRequest;", "", "cargoId", "", "businessOwnerUid", "businessOwnerName", "businessOwnerPhone", "businessOwnerCompany", "goodsType", "Lcom/freightconnect/model/GoodsType;", "goodsDescription", "weightTons", "", "specialRequirements", "pickupLocation", "Lcom/google/firebase/firestore/GeoPoint;", "pickupAddress", "pickupCity", "deliveryLocation", "deliveryAddress", "deliveryCity", "estimatedDistanceKm", "requiredVehicleType", "Lcom/freightconnect/model/VehicleType;", "pickupDate", "", "budgetAmount", "", "currency", "status", "Lcom/freightconnect/model/CargoStatus;", "assignedRouteId", "assignedFleetOwnerUid", "createdAt", "imageUrls", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/freightconnect/model/GoodsType;Ljava/lang/String;FLjava/lang/String;Lcom/google/firebase/firestore/GeoPoint;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/firestore/GeoPoint;Ljava/lang/String;Ljava/lang/String;FLcom/freightconnect/model/VehicleType;JDLjava/lang/String;Lcom/freightconnect/model/CargoStatus;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V", "getAssignedFleetOwnerUid", "()Ljava/lang/String;", "getAssignedRouteId", "getBudgetAmount", "()D", "getBusinessOwnerCompany", "getBusinessOwnerName", "getBusinessOwnerPhone", "getBusinessOwnerUid", "getCargoId", "getCreatedAt", "()J", "getCurrency", "getDeliveryAddress", "getDeliveryCity", "getDeliveryLocation", "()Lcom/google/firebase/firestore/GeoPoint;", "getEstimatedDistanceKm", "()F", "getGoodsDescription", "getGoodsType", "()Lcom/freightconnect/model/GoodsType;", "getImageUrls", "()Ljava/util/List;", "getPickupAddress", "getPickupCity", "getPickupDate", "getPickupLocation", "getRequiredVehicleType", "()Lcom/freightconnect/model/VehicleType;", "getSpecialRequirements", "getStatus", "()Lcom/freightconnect/model/CargoStatus;", "getWeightTons", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class CargoRequest {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String cargoId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String businessOwnerUid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String businessOwnerName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String businessOwnerPhone = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String businessOwnerCompany = null;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.model.GoodsType goodsType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String goodsDescription = null;
    private final float weightTons = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String specialRequirements = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.firestore.GeoPoint pickupLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String pickupAddress = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String pickupCity = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.firestore.GeoPoint deliveryLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String deliveryAddress = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String deliveryCity = null;
    private final float estimatedDistanceKm = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.model.VehicleType requiredVehicleType = null;
    private final long pickupDate = 0L;
    private final double budgetAmount = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currency = null;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.model.CargoStatus status = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String assignedRouteId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String assignedFleetOwnerUid = null;
    private final long createdAt = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> imageUrls = null;
    
    public CargoRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String cargoId, @org.jetbrains.annotations.NotNull()
    java.lang.String businessOwnerUid, @org.jetbrains.annotations.NotNull()
    java.lang.String businessOwnerName, @org.jetbrains.annotations.NotNull()
    java.lang.String businessOwnerPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String businessOwnerCompany, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.GoodsType goodsType, @org.jetbrains.annotations.NotNull()
    java.lang.String goodsDescription, float weightTons, @org.jetbrains.annotations.NotNull()
    java.lang.String specialRequirements, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint pickupLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String pickupAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String pickupCity, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint deliveryLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String deliveryAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String deliveryCity, float estimatedDistanceKm, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.VehicleType requiredVehicleType, long pickupDate, double budgetAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String currency, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.CargoStatus status, @org.jetbrains.annotations.NotNull()
    java.lang.String assignedRouteId, @org.jetbrains.annotations.NotNull()
    java.lang.String assignedFleetOwnerUid, long createdAt, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> imageUrls) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCargoId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusinessOwnerUid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusinessOwnerName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusinessOwnerPhone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusinessOwnerCompany() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.GoodsType getGoodsType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGoodsDescription() {
        return null;
    }
    
    public final float getWeightTons() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSpecialRequirements() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint getPickupLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPickupAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPickupCity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint getDeliveryLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeliveryAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeliveryCity() {
        return null;
    }
    
    public final float getEstimatedDistanceKm() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.VehicleType getRequiredVehicleType() {
        return null;
    }
    
    public final long getPickupDate() {
        return 0L;
    }
    
    public final double getBudgetAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrency() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.CargoStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAssignedRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAssignedFleetOwnerUid() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getImageUrls() {
        return null;
    }
    
    public CargoRequest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    public final float component16() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.VehicleType component17() {
        return null;
    }
    
    public final long component18() {
        return 0L;
    }
    
    public final double component19() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.CargoStatus component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component23() {
        return null;
    }
    
    public final long component24() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component25() {
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
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.GoodsType component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final float component8() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.CargoRequest copy(@org.jetbrains.annotations.NotNull()
    java.lang.String cargoId, @org.jetbrains.annotations.NotNull()
    java.lang.String businessOwnerUid, @org.jetbrains.annotations.NotNull()
    java.lang.String businessOwnerName, @org.jetbrains.annotations.NotNull()
    java.lang.String businessOwnerPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String businessOwnerCompany, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.GoodsType goodsType, @org.jetbrains.annotations.NotNull()
    java.lang.String goodsDescription, float weightTons, @org.jetbrains.annotations.NotNull()
    java.lang.String specialRequirements, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint pickupLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String pickupAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String pickupCity, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint deliveryLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String deliveryAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String deliveryCity, float estimatedDistanceKm, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.VehicleType requiredVehicleType, long pickupDate, double budgetAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String currency, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.CargoStatus status, @org.jetbrains.annotations.NotNull()
    java.lang.String assignedRouteId, @org.jetbrains.annotations.NotNull()
    java.lang.String assignedFleetOwnerUid, long createdAt, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> imageUrls) {
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