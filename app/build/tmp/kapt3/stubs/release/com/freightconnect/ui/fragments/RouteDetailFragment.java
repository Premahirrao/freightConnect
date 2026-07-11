package com.freightconnect.ui.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0011H\u0002J$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0011H\u0016J\u001a\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J \u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\'\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/freightconnect/ui/fragments/RouteDetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/freightconnect/databinding/FragmentRouteDetailBinding;", "args", "Lcom/freightconnect/ui/fragments/RouteDetailFragmentArgs;", "getArgs", "()Lcom/freightconnect/ui/fragments/RouteDetailFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "getBinding", "()Lcom/freightconnect/databinding/FragmentRouteDetailBinding;", "repo", "Lcom/freightconnect/repository/FreightRepository;", "cancelRoute", "", "routeId", "", "dialPhone", "phone", "loadRouteDetails", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "sendInterest", "message", "offeredPrice", "", "showCancelRouteDialog", "showSendInterestDialog", "showSnackbar", "msg", "app_release"})
public final class RouteDetailFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.freightconnect.databinding.FragmentRouteDetailBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.freightconnect.repository.FreightRepository repo = null;
    
    public RouteDetailFragment() {
        super();
    }
    
    private final com.freightconnect.databinding.FragmentRouteDetailBinding getBinding() {
        return null;
    }
    
    private final com.freightconnect.ui.fragments.RouteDetailFragmentArgs getArgs() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadRouteDetails() {
    }
    
    private final void showSendInterestDialog(java.lang.String routeId) {
    }
    
    private final void sendInterest(java.lang.String routeId, java.lang.String message, double offeredPrice) {
    }
    
    private final void dialPhone(java.lang.String phone) {
    }
    
    private final void showSnackbar(java.lang.String msg) {
    }
    
    private final void showCancelRouteDialog(java.lang.String routeId) {
    }
    
    private final void cancelRoute(java.lang.String routeId) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}