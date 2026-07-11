package com.freightconnect.model;

/**
 * Posted by FLEET_OWNER: "I have a truck going from X to Y, can carry Z tons"
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\bG\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0099\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001d\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0010\u0012\b\b\u0002\u0010 \u001a\u00020\u001a\u0012\b\b\u0002\u0010!\u001a\u00020\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020#\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010%J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\t\u0010O\u001a\u00020\u0010H\u00c6\u0003J\t\u0010P\u001a\u00020\u0012H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0003H\u00c6\u0003J\t\u0010R\u001a\u00020\u0010H\u00c6\u0003J\t\u0010S\u001a\u00020\u0003H\u00c6\u0003J\t\u0010T\u001a\u00020\u0017H\u00c6\u0003J\t\u0010U\u001a\u00020\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\u001aH\u00c6\u0003J\t\u0010W\u001a\u00020\u0003H\u00c6\u0003J\t\u0010X\u001a\u00020\u001aH\u00c6\u0003J\t\u0010Y\u001a\u00020\u001dH\u00c6\u0003J\t\u0010Z\u001a\u00020\u0003H\u00c6\u0003J\t\u0010[\u001a\u00020\u0010H\u00c6\u0003J\t\u0010\\\u001a\u00020\u001aH\u00c6\u0003J\t\u0010]\u001a\u00020\u0003H\u00c6\u0003J\t\u0010^\u001a\u00020#H\u00c6\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010`\u001a\u00020\u0003H\u00c6\u0003J\t\u0010a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u009d\u0002\u0010g\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00102\b\b\u0002\u0010 \u001a\u00020\u001a2\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010h\u001a\u00020#2\b\u0010i\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010j\u001a\u00020kH\u00d6\u0001J\t\u0010l\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\"\u001a\u00020#\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u0014\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u001e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u001f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010)R\u0011\u0010 \u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010+R\u0013\u0010$\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010.R\u0011\u0010\u001b\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010.R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010)R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010+R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010+R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010+R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010+R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010+R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010+R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u00101R\u0011\u0010<\u001a\u00020#8G\u00a2\u0006\u0006\u001a\u0004\b<\u0010\'R\u0011\u0010!\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010+R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\bA\u0010)R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010+R\u0011\u0010\u001c\u001a\u00020\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u0010+R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010+R\u0013\u0010\f\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u00101R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010+R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u0010+R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010K\u00a8\u0006m"}, d2 = {"Lcom/freightconnect/model/TruckRoute;", "", "routeId", "", "fleetOwnerUid", "fleetOwnerName", "fleetOwnerPhone", "fleetOwnerCompany", "fromLocation", "Lcom/google/firebase/firestore/GeoPoint;", "fromAddress", "fromCity", "toLocation", "toAddress", "toCity", "estimatedDistanceKm", "", "vehicleType", "Lcom/freightconnect/model/VehicleType;", "vehicleNumber", "availableCapacityTons", "vehicleImageUrl", "pricePerTon", "", "currency", "departureDate", "", "estimatedArrivalDate", "status", "Lcom/freightconnect/model/RouteStatus;", "bookedByUid", "bookedWeightTons", "createdAt", "notes", "acceptsPartialLoad", "", "currentLocation", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/firestore/GeoPoint;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/firestore/GeoPoint;Ljava/lang/String;Ljava/lang/String;FLcom/freightconnect/model/VehicleType;Ljava/lang/String;FLjava/lang/String;DLjava/lang/String;JJLcom/freightconnect/model/RouteStatus;Ljava/lang/String;FJLjava/lang/String;ZLcom/google/firebase/firestore/GeoPoint;)V", "getAcceptsPartialLoad", "()Z", "getAvailableCapacityTons", "()F", "getBookedByUid", "()Ljava/lang/String;", "getBookedWeightTons", "getCreatedAt", "()J", "getCurrency", "getCurrentLocation", "()Lcom/google/firebase/firestore/GeoPoint;", "getDepartureDate", "getEstimatedArrivalDate", "getEstimatedDistanceKm", "getFleetOwnerCompany", "getFleetOwnerName", "getFleetOwnerPhone", "getFleetOwnerUid", "getFromAddress", "getFromCity", "getFromLocation", "isAvailable", "getNotes", "getPricePerTon", "()D", "remainingCapacityTons", "getRemainingCapacityTons", "getRouteId", "getStatus", "()Lcom/freightconnect/model/RouteStatus;", "getToAddress", "getToCity", "getToLocation", "getVehicleImageUrl", "getVehicleNumber", "getVehicleType", "()Lcom/freightconnect/model/VehicleType;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class TruckRoute {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fleetOwnerUid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fleetOwnerName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fleetOwnerPhone = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fleetOwnerCompany = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.firestore.GeoPoint fromLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fromAddress = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fromCity = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.firestore.GeoPoint toLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String toAddress = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String toCity = null;
    private final float estimatedDistanceKm = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.model.VehicleType vehicleType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vehicleNumber = null;
    private final float availableCapacityTons = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vehicleImageUrl = null;
    private final double pricePerTon = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currency = null;
    private final long departureDate = 0L;
    private final long estimatedArrivalDate = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.model.RouteStatus status = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String bookedByUid = null;
    private final float bookedWeightTons = 0.0F;
    private final long createdAt = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String notes = null;
    private final boolean acceptsPartialLoad = false;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.firestore.GeoPoint currentLocation = null;
    
    public TruckRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String fleetOwnerUid, @org.jetbrains.annotations.NotNull()
    java.lang.String fleetOwnerName, @org.jetbrains.annotations.NotNull()
    java.lang.String fleetOwnerPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String fleetOwnerCompany, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint fromLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String fromAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String fromCity, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint toLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String toAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String toCity, float estimatedDistanceKm, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.VehicleType vehicleType, @org.jetbrains.annotations.NotNull()
    java.lang.String vehicleNumber, float availableCapacityTons, @org.jetbrains.annotations.NotNull()
    java.lang.String vehicleImageUrl, double pricePerTon, @org.jetbrains.annotations.NotNull()
    java.lang.String currency, long departureDate, long estimatedArrivalDate, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.RouteStatus status, @org.jetbrains.annotations.NotNull()
    java.lang.String bookedByUid, float bookedWeightTons, long createdAt, @org.jetbrains.annotations.NotNull()
    java.lang.String notes, boolean acceptsPartialLoad, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint currentLocation) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFleetOwnerUid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFleetOwnerName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFleetOwnerPhone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFleetOwnerCompany() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint getFromLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFromAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFromCity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint getToLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToCity() {
        return null;
    }
    
    public final float getEstimatedDistanceKm() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.VehicleType getVehicleType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVehicleNumber() {
        return null;
    }
    
    public final float getAvailableCapacityTons() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVehicleImageUrl() {
        return null;
    }
    
    public final double getPricePerTon() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrency() {
        return null;
    }
    
    public final long getDepartureDate() {
        return 0L;
    }
    
    public final long getEstimatedArrivalDate() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.RouteStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBookedByUid() {
        return null;
    }
    
    public final float getBookedWeightTons() {
        return 0.0F;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNotes() {
        return null;
    }
    
    public final boolean getAcceptsPartialLoad() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint getCurrentLocation() {
        return null;
    }
    
    @com.google.firebase.firestore.PropertyName(value = "isAvailable")
    public final boolean isAvailable() {
        return false;
    }
    
    public final float getRemainingCapacityTons() {
        return 0.0F;
    }
    
    public TruckRoute() {
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
    
    public final float component12() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.VehicleType component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    public final float component15() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    public final double component17() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    public final long component19() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component20() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.RouteStatus component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component22() {
        return null;
    }
    
    public final float component23() {
        return 0.0F;
    }
    
    public final long component24() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component25() {
        return null;
    }
    
    public final boolean component26() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint component27() {
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
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.firestore.GeoPoint component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.freightconnect.model.TruckRoute copy(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String fleetOwnerUid, @org.jetbrains.annotations.NotNull()
    java.lang.String fleetOwnerName, @org.jetbrains.annotations.NotNull()
    java.lang.String fleetOwnerPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String fleetOwnerCompany, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint fromLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String fromAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String fromCity, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint toLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String toAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String toCity, float estimatedDistanceKm, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.VehicleType vehicleType, @org.jetbrains.annotations.NotNull()
    java.lang.String vehicleNumber, float availableCapacityTons, @org.jetbrains.annotations.NotNull()
    java.lang.String vehicleImageUrl, double pricePerTon, @org.jetbrains.annotations.NotNull()
    java.lang.String currency, long departureDate, long estimatedArrivalDate, @org.jetbrains.annotations.NotNull()
    com.freightconnect.model.RouteStatus status, @org.jetbrains.annotations.NotNull()
    java.lang.String bookedByUid, float bookedWeightTons, long createdAt, @org.jetbrains.annotations.NotNull()
    java.lang.String notes, boolean acceptsPartialLoad, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.GeoPoint currentLocation) {
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