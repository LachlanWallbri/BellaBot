package com.pudutech.event_tracking.component;

import android.content.Context;
import android.provider.Settings;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: IDComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0014\u001a\u00020\u0005J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00050\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/event_tracking/component/IDComponent;", "", "context", "Landroid/content/Context;", "baseId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "BASE_REFRESH_COUNT", "", "getBaseId", "()Ljava/lang/String;", "getContext", "()Landroid/content/Context;", "currentRefreshCount", "deviceId", "kotlin.jvm.PlatformType", "getDeviceId", "scope", "Lkotlinx/coroutines/CoroutineScope;", "sessionId", "getSessionId", "startRefreshTimer", "", "uuid", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class IDComponent {
    private final int BASE_REFRESH_COUNT;
    private final String baseId;
    private final Context context;
    private int currentRefreshCount;
    private final String deviceId;
    private final CoroutineScope scope;
    private String sessionId;

    public IDComponent(Context context, String baseId) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(baseId, "baseId");
        this.context = context;
        this.baseId = baseId;
        this.BASE_REFRESH_COUNT = 900;
        this.currentRefreshCount = this.BASE_REFRESH_COUNT;
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.deviceId = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
        this.sessionId = this.baseId + '_' + System.currentTimeMillis();
        startRefreshTimer();
    }

    public final String getBaseId() {
        return this.baseId;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getSessionId() {
        this.currentRefreshCount = this.BASE_REFRESH_COUNT;
        return this.sessionId;
    }

    public final String uuid() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        return StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
    }

    private final void startRefreshTimer() {
        BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getDefault(), null, new IDComponent$startRefreshTimer$1(this, null), 2, null);
    }
}
