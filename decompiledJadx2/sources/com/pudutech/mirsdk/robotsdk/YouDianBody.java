package com.pudutech.mirsdk.robotsdk;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: YouDianBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/YouDianBody;", "", "access_id", "", "status", "(Ljava/lang/String;Ljava/lang/String;)V", "getAccess_id", "()Ljava/lang/String;", "setAccess_id", "(Ljava/lang/String;)V", "getStatus", "setStatus", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class YouDianBody {
    private String access_id;
    private String status;

    public YouDianBody(String str, String str2) {
        this.access_id = str;
        this.status = str2;
    }

    public final String getAccess_id() {
        return this.access_id;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setAccess_id(String str) {
        this.access_id = str;
    }

    public final void setStatus(String str) {
        this.status = str;
    }
}
