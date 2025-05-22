package com.pudutech.mirsdk.map;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapNameWithFloor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/MapNameWithFloor;", "", "name", "", "floor", "(Ljava/lang/String;Ljava/lang/String;)V", "getFloor", "()Ljava/lang/String;", "getName", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapNameWithFloor {
    private final String floor;
    private final String name;

    public MapNameWithFloor(String name, String floor) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        this.name = name;
        this.floor = floor;
    }

    public final String getFloor() {
        return this.floor;
    }

    public final String getName() {
        return this.name;
    }
}
