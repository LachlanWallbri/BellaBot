package com.pudutech.mirsdk.map;

import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: FloorsTopomapPath.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0006\u0010\r\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/FloorsTopomapPath;", "", "floor", "", "floor_index", "", "topo", "(Ljava/lang/String;ILjava/lang/String;)V", "getFloor", "()Ljava/lang/String;", "getFloor_index", "()I", "getTopo", "toJson", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FloorsTopomapPath {
    private final String floor;
    private final int floor_index;
    private final String topo;

    public FloorsTopomapPath(String floor, int i, String topo) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(topo, "topo");
        this.floor = floor;
        this.floor_index = i;
        this.topo = topo;
    }

    public final String getFloor() {
        return this.floor;
    }

    public final int getFloor_index() {
        return this.floor_index;
    }

    public final String getTopo() {
        return this.topo;
    }

    public final String toJson() {
        String json = new Gson().toJson(this);
        Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(this)");
        return json;
    }
}
