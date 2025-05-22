package com.pudutech.remotemaintenance.net.req;

import com.pudutech.remotemaintenance.App;
import com.pudutech.remotemaintenance.utils.SystemTool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/net/req/BaseReq;", "", "()V", "mac", "", "getMac", "()Ljava/lang/String;", "setMac", "(Ljava/lang/String;)V", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public class BaseReq {
    private String mac;

    public BaseReq() {
        this.mac = "";
        this.mac = SystemTool.INSTANCE.getMac(App.INSTANCE.getInstance());
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }
}
