package com.freightconnect.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0004J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/freightconnect/utils/LanguageHelper;", "", "()V", "KEY_LANGUAGE", "", "PREF_NAME", "getCurrentLanguage", "context", "Landroid/content/Context;", "getLanguageDisplayName", "code", "loadLocale", "", "setLocale", "languageCode", "updateResources", "app_release"})
public final class LanguageHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREF_NAME = "app_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LANGUAGE = "selected_language";
    @org.jetbrains.annotations.NotNull()
    public static final com.freightconnect.utils.LanguageHelper INSTANCE = null;
    
    private LanguageHelper() {
        super();
    }
    
    public final void setLocale(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String languageCode) {
    }
    
    public final void loadLocale(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentLanguage(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    private final void updateResources(android.content.Context context, java.lang.String languageCode) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLanguageDisplayName(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
        return null;
    }
}