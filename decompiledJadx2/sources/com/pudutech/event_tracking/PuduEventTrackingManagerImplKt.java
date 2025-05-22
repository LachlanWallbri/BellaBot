package com.pudutech.event_tracking;

import com.google.gson.Gson;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PuduEventTrackingManagerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\u001a\u001e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"gson", "Lcom/google/gson/Gson;", "toJson", "", "kotlin.jvm.PlatformType", "", "", "event_tracking_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PuduEventTrackingManagerImplKt {
    private static final Gson gson = new Gson();

    public static final String toJson(Map<String, ? extends Object> toJson) {
        Intrinsics.checkParameterIsNotNull(toJson, "$this$toJson");
        return gson.toJson(toJson);
    }
}
