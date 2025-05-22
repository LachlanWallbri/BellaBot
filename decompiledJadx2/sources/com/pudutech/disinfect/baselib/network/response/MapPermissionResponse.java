package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: MapPermissionResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/MapPermissionResponse;", "", "code", "", "data", "", "(ILjava/lang/String;)V", "getCode", "()I", "getData", "()Ljava/lang/String;", "setData", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class MapPermissionResponse {
    private final int code;
    private String data;

    public static /* synthetic */ MapPermissionResponse copy$default(MapPermissionResponse mapPermissionResponse, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = mapPermissionResponse.code;
        }
        if ((i2 & 2) != 0) {
            str = mapPermissionResponse.data;
        }
        return mapPermissionResponse.copy(i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final String getData() {
        return this.data;
    }

    public final MapPermissionResponse copy(int code, String data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return new MapPermissionResponse(code, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapPermissionResponse)) {
            return false;
        }
        MapPermissionResponse mapPermissionResponse = (MapPermissionResponse) other;
        return this.code == mapPermissionResponse.code && Intrinsics.areEqual(this.data, mapPermissionResponse.data);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.code) * 31;
        String str = this.data;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "MapPermissionResponse(code=" + this.code + ", data=" + this.data + ")";
    }

    public MapPermissionResponse(int i, String data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.code = i;
        this.data = data;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.data = str;
    }
}
