package com.pudutech.pd_network.log;

import android.util.Log;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.aiui.AIUIConstant;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetWorkLog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0001J\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/pd_network/log/NetWorkLog;", "Lcom/pudutech/pd_network/log/ILog;", "()V", "mLog", "getMLog", "()Lcom/pudutech/pd_network/log/ILog;", "setMLog", "(Lcom/pudutech/pd_network/log/ILog;)V", LinkFormat.DOMAIN, "", AIUIConstant.KEY_TAG, "", AIUIConstant.KEY_CONTENT, C3898x.f4338g, "i", "setLog", "log", "w", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NetWorkLog implements ILog {
    public static final NetWorkLog INSTANCE = new NetWorkLog();
    private static ILog mLog;

    private NetWorkLog() {
    }

    public final ILog getMLog() {
        return mLog;
    }

    public final void setMLog(ILog iLog) {
        mLog = iLog;
    }

    public final void setLog(ILog log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        mLog = log;
    }

    @Override // com.pudutech.pd_network.log.ILog
    /* renamed from: i */
    public void mo3280i(String tag, String content) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(content, "content");
        ILog iLog = mLog;
        if (iLog == null) {
            Log.i(tag, content);
        } else if (iLog != null) {
            iLog.mo3280i(tag, content);
        }
    }

    @Override // com.pudutech.pd_network.log.ILog
    /* renamed from: w */
    public void mo3281w(String tag, String content) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(content, "content");
        ILog iLog = mLog;
        if (iLog == null) {
            Log.w(tag, content);
        } else if (iLog != null) {
            iLog.mo3281w(tag, content);
        }
    }

    @Override // com.pudutech.pd_network.log.ILog
    /* renamed from: d */
    public void mo3278d(String tag, String content) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(content, "content");
        ILog iLog = mLog;
        if (iLog == null) {
            Log.d(tag, content);
        } else if (iLog != null) {
            iLog.mo3278d(tag, content);
        }
    }

    @Override // com.pudutech.pd_network.log.ILog
    /* renamed from: e */
    public void mo3279e(String tag, String content) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(content, "content");
        ILog iLog = mLog;
        if (iLog == null) {
            Log.e(tag, content);
        } else if (iLog != null) {
            iLog.mo3279e(tag, content);
        }
    }
}
