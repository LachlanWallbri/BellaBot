package com.pudutech.disinfect.baselib.network.response;

import androidx.core.app.NotificationCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsInfoResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J7\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/TtsInfoResp;", "", "code", "", "data", "", "", NotificationCompat.CATEGORY_MESSAGE, "(ILjava/util/Map;Ljava/lang/String;)V", "getCode", "()I", "getData", "()Ljava/util/Map;", "getMsg", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TtsInfoResp {
    private final int code;
    private final Map<String, Object> data;
    private final String msg;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TtsInfoResp copy$default(TtsInfoResp ttsInfoResp, int i, Map map, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = ttsInfoResp.code;
        }
        if ((i2 & 2) != 0) {
            map = ttsInfoResp.data;
        }
        if ((i2 & 4) != 0) {
            str = ttsInfoResp.msg;
        }
        return ttsInfoResp.copy(i, map, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    public final Map<String, Object> component2() {
        return this.data;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    public final TtsInfoResp copy(int code, Map<String, ? extends Object> data, String msg) {
        return new TtsInfoResp(code, data, msg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsInfoResp)) {
            return false;
        }
        TtsInfoResp ttsInfoResp = (TtsInfoResp) other;
        return this.code == ttsInfoResp.code && Intrinsics.areEqual(this.data, ttsInfoResp.data) && Intrinsics.areEqual(this.msg, ttsInfoResp.msg);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.code) * 31;
        Map<String, Object> map = this.data;
        int hashCode2 = (hashCode + (map != null ? map.hashCode() : 0)) * 31;
        String str = this.msg;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "TtsInfoResp(code=" + this.code + ", data=" + this.data + ", msg=" + this.msg + ")";
    }

    public TtsInfoResp(int i, Map<String, ? extends Object> map, String str) {
        this.code = i;
        this.data = map;
        this.msg = str;
    }

    public final int getCode() {
        return this.code;
    }

    public final Map<String, Object> getData() {
        return this.data;
    }

    public final String getMsg() {
        return this.msg;
    }
}
