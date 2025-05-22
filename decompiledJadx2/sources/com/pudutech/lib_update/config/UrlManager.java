package com.pudutech.lib_update.config;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: UrlManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\t¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/lib_update/config/UrlManager;", "", "()V", "CHANNEL_NAME", "", "CHANNEL_NAME$annotations", "getCHANNEL_NAME", "()Ljava/lang/String;", "setCHANNEL_NAME", "(Ljava/lang/String;)V", "UPDATE_HOST", "UPDATE_HOST$annotations", "getUPDATE_HOST", "setUPDATE_HOST", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UrlManager {
    public static final UrlManager INSTANCE = new UrlManager();
    private static String UPDATE_HOST = "https://service.client.pudutech.com/";
    private static String CHANNEL_NAME = "";

    @JvmStatic
    public static /* synthetic */ void CHANNEL_NAME$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void UPDATE_HOST$annotations() {
    }

    private UrlManager() {
    }

    public static final String getUPDATE_HOST() {
        return UPDATE_HOST;
    }

    public static final void setUPDATE_HOST(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        UPDATE_HOST = str;
    }

    public static final String getCHANNEL_NAME() {
        return CHANNEL_NAME;
    }

    public static final void setCHANNEL_NAME(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        CHANNEL_NAME = str;
    }
}
