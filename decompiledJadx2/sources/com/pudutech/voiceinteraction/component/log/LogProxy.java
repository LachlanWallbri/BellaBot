package com.pudutech.voiceinteraction.component.log;

import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.aiui.AIUIConstant;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogProxy.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0018\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\u0019\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\u001a\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\u001b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005RL\u0010\u0003\u001a4\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eRL\u0010\u000f\u001a4\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eRL\u0010\u0012\u001a4\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eRL\u0010\u0015\u001a4\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000e¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/log/LogProxy;", "", "()V", "dlog", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", AIUIConstant.KEY_TAG, NotificationCompat.CATEGORY_MESSAGE, "", "getDlog", "()Lkotlin/jvm/functions/Function2;", "setDlog", "(Lkotlin/jvm/functions/Function2;)V", "elog", "getElog", "setElog", "ilog", "getIlog", "setIlog", "wlog", "getWlog", "setWlog", LinkFormat.DOMAIN, C3898x.f4338g, "i", "w", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class LogProxy {
    public static final LogProxy INSTANCE = new LogProxy();
    private static Function2<? super String, ? super String, Unit> dlog;
    private static Function2<? super String, ? super String, Unit> elog;
    private static Function2<? super String, ? super String, Unit> ilog;
    private static Function2<? super String, ? super String, Unit> wlog;

    private LogProxy() {
    }

    public final Function2<String, String, Unit> getDlog() {
        return dlog;
    }

    public final void setDlog(Function2<? super String, ? super String, Unit> function2) {
        dlog = function2;
    }

    public final Function2<String, String, Unit> getElog() {
        return elog;
    }

    public final void setElog(Function2<? super String, ? super String, Unit> function2) {
        elog = function2;
    }

    public final Function2<String, String, Unit> getIlog() {
        return ilog;
    }

    public final void setIlog(Function2<? super String, ? super String, Unit> function2) {
        ilog = function2;
    }

    public final Function2<String, String, Unit> getWlog() {
        return wlog;
    }

    public final void setWlog(Function2<? super String, ? super String, Unit> function2) {
        wlog = function2;
    }

    /* renamed from: d */
    public final void m3305d(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Function2<? super String, ? super String, Unit> function2 = dlog;
        if (function2 != null) {
            function2.invoke(tag, msg);
        }
    }

    /* renamed from: e */
    public final void m3306e(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Function2<? super String, ? super String, Unit> function2 = elog;
        if (function2 != null) {
            function2.invoke(tag, msg);
        }
    }

    /* renamed from: i */
    public final void m3307i(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Function2<? super String, ? super String, Unit> function2 = ilog;
        if (function2 != null) {
            function2.invoke(tag, msg);
        }
    }

    /* renamed from: w */
    public final void m3308w(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Function2<? super String, ? super String, Unit> function2 = wlog;
        if (function2 != null) {
            function2.invoke(tag, msg);
        }
    }
}
