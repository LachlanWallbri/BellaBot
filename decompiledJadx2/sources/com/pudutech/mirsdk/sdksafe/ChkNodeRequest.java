package com.pudutech.mirsdk.sdksafe;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafeRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/ChkNodeRequest;", "", "()V", "appid", "", "getAppid", "()I", "setAppid", "(I)V", "node", "", "getNode", "()Ljava/lang/String;", "setNode", "(Ljava/lang/String;)V", "token", "getToken", "setToken", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ChkNodeRequest {
    private int appid;
    private String token = "";
    private String node = "";

    public final String getToken() {
        return this.token;
    }

    public final void setToken(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.token = str;
    }

    public final int getAppid() {
        return this.appid;
    }

    public final void setAppid(int i) {
        this.appid = i;
    }

    public final String getNode() {
        return this.node;
    }

    public final void setNode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.node = str;
    }
}
