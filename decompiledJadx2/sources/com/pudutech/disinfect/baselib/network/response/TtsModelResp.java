package com.pudutech.disinfect.baselib.network.response;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsModelResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/TtsModelResp;", "", "code", "", "data", "", "Lcom/pudutech/disinfect/baselib/network/response/Model;", "(ILjava/util/List;)V", "getCode", "()I", "getData", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TtsModelResp {
    private final int code;
    private final List<Model> data;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TtsModelResp copy$default(TtsModelResp ttsModelResp, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = ttsModelResp.code;
        }
        if ((i2 & 2) != 0) {
            list = ttsModelResp.data;
        }
        return ttsModelResp.copy(i, list);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    public final List<Model> component2() {
        return this.data;
    }

    public final TtsModelResp copy(int code, List<Model> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return new TtsModelResp(code, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsModelResp)) {
            return false;
        }
        TtsModelResp ttsModelResp = (TtsModelResp) other;
        return this.code == ttsModelResp.code && Intrinsics.areEqual(this.data, ttsModelResp.data);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.code) * 31;
        List<Model> list = this.data;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "TtsModelResp(code=" + this.code + ", data=" + this.data + ")";
    }

    public TtsModelResp(int i, List<Model> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.code = i;
        this.data = data;
    }

    public final int getCode() {
        return this.code;
    }

    public final List<Model> getData() {
        return this.data;
    }
}
