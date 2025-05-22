package com.pudutech.disinfect.baselib.state;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: AppException.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\u0003J\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/state/Error;", "", "code", "", NotificationCompat.CATEGORY_ERROR, "", "(Ljava/lang/String;IILjava/lang/String;)V", "getKey", "getValue", "UNKNOWN", "PARSE_ERROR", "NETWORK_ERROR", "SSL_ERROR", "TIMEOUT_ERROR", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public enum Error {
    UNKNOWN(1000, "请求失败，请稍后再试"),
    PARSE_ERROR(1001, "解析错误,请稍后重试"),
    NETWORK_ERROR(1002, "网络连接错误,请稍后重试"),
    SSL_ERROR(1004, "证书出错,请稍后重试"),
    TIMEOUT_ERROR(1005, "网络连接超时,请稍后重试");

    private final int code;
    private final String err;

    Error(int i, String str) {
        this.code = i;
        this.err = str;
    }

    /* renamed from: getValue, reason: from getter */
    public final String getErr() {
        return this.err;
    }

    /* renamed from: getKey, reason: from getter */
    public final int getCode() {
        return this.code;
    }
}
