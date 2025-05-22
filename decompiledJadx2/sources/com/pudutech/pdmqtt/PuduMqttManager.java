package com.pudutech.pdmqtt;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PuduMqttManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\u0002\b\fJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J\u0014\u0010\u0012\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0013J\u000e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/pdmqtt/PuduMqttManager;", "", "()V", "_managerWrap", "Lcom/pudutech/pdmqtt/PuduMqttManagerWrap;", "fetchClient", "", "context", "Landroid/content/Context;", "block", "Lkotlin/Function1;", "Lcom/pudutech/pdmqtt/MqttClientBuilder;", "Lkotlin/ExtensionFunctionType;", "init", "localLog", AIUIConstant.KEY_TAG, "", NotificationCompat.CATEGORY_MESSAGE, "onServiceUnbind", "Lkotlin/Function0;", "release", "setLog", "logger", "Lcom/pudutech/pdmqtt/MqttLogger;", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PuduMqttManager {
    public static final PuduMqttManager INSTANCE = new PuduMqttManager();
    private static final PuduMqttManagerWrap _managerWrap = new PuduMqttManagerWrap();

    private PuduMqttManager() {
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        PuduMqttManagerWrap puduMqttManagerWrap = _managerWrap;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        puduMqttManagerWrap.init(applicationContext);
    }

    public final void fetchClient(Context context, Function1<? super MqttClientBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(block, "block");
        PuduMqttManagerWrap puduMqttManagerWrap = _managerWrap;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        puduMqttManagerWrap.fetchClient(applicationContext, block);
    }

    public final void release(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        PuduMqttManagerWrap puduMqttManagerWrap = _managerWrap;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        puduMqttManagerWrap.release(applicationContext);
    }

    public final void onServiceUnbind(Function0<Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        _managerWrap.onServiceUnbind(block);
    }

    public final void setLog(MqttLogger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        _managerWrap.setLog(logger);
    }

    public final void localLog(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        _managerWrap.localLog(tag, msg);
    }
}
