package com.pudutech.mirsdk.sdksafe;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafeRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/TokenRequest;", "", "()V", "appid", "", "getAppid", "()Ljava/lang/String;", "setAppid", "(Ljava/lang/String;)V", "signature", "getSignature", "setSignature", "ticket", "getTicket", "setTicket", "timestamp", "", "getTimestamp", "()J", "setTimestamp", "(J)V", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class TokenRequest {
    private long timestamp;
    private String ticket = "";
    private String appid = "";
    private String signature = "";

    public final String getTicket() {
        return this.ticket;
    }

    public final void setTicket(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ticket = str;
    }

    public final String getAppid() {
        return this.appid;
    }

    public final void setAppid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appid = str;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final String getSignature() {
        return this.signature;
    }

    public final void setSignature(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.signature = str;
    }
}
