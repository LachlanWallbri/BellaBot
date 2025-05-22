package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ReqApkUrlUpdate.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/ReqApkUrlUpdate;", "", "url", "", "md5", "(Ljava/lang/String;Ljava/lang/String;)V", "getMd5", "()Ljava/lang/String;", "setMd5", "(Ljava/lang/String;)V", "getUrl", "setUrl", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReqApkUrlUpdate {
    private String md5;
    private String url;

    public ReqApkUrlUpdate(String url, String md5) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        this.url = url;
        this.md5 = md5;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setMd5(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.md5 = str;
    }

    public final void setUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.url = str;
    }

    public String toString() {
        return "ReqApkUrlUpdate(url='" + this.url + "', md5='" + this.md5 + "')";
    }
}
