package com.pudutech.log;

import android.util.Log;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.aiui.AIUIConstant;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProxyLog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0001J\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/log/ProxyLog;", "Lcom/pudutech/log/ILog;", "()V", "mLog", "getMLog", "()Lcom/pudutech/log/ILog;", "setMLog", "(Lcom/pudutech/log/ILog;)V", LinkFormat.DOMAIN, "", AIUIConstant.KEY_TAG, "", AIUIConstant.KEY_CONTENT, C3898x.f4338g, "i", "proxyLog", "log", "w", "log_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ProxyLog implements ILog {
    public static final ProxyLog INSTANCE = new ProxyLog();
    private static ILog mLog;

    private ProxyLog() {
    }

    public final ILog getMLog() {
        return mLog;
    }

    public final void setMLog(ILog iLog) {
        mLog = iLog;
    }

    public final void proxyLog(ILog log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        mLog = log;
    }

    @Override // com.pudutech.log.ILog
    /* renamed from: i */
    public void mo3285i(String tag, String content) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(content, "content");
        ILog iLog = mLog;
        if (iLog == null) {
            Log.i(tag, content);
        } else if (iLog != null) {
            iLog.mo3285i(tag, content);
        }
    }

    @Override // com.pudutech.log.ILog
    /* renamed from: w */
    public void mo3286w(String tag, String content) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(content, "content");
        ILog iLog = mLog;
        if (iLog == null) {
            Log.w(tag, content);
        } else if (iLog != null) {
            iLog.mo3286w(tag, content);
        }
    }

    @Override // com.pudutech.log.ILog
    /* renamed from: d */
    public void mo3283d(String tag, String content) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(content, "content");
        ILog iLog = mLog;
        if (iLog == null) {
            Log.d(tag, content);
        } else if (iLog != null) {
            iLog.mo3283d(tag, content);
        }
    }

    @Override // com.pudutech.log.ILog
    /* renamed from: e */
    public void mo3284e(String tag, String content) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(content, "content");
        ILog iLog = mLog;
        if (iLog == null) {
            Log.e(tag, content);
        } else if (iLog != null) {
            iLog.mo3284e(tag, content);
        }
    }
}
