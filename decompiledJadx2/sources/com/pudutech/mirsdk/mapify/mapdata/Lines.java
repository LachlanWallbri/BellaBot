package com.pudutech.mirsdk.mapify.mapdata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Vertex.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/mapdata/Lines;", "", "left", "Lcom/pudutech/mirsdk/mapify/mapdata/Vertex;", "right", "(Lcom/pudutech/mirsdk/mapify/mapdata/Vertex;Lcom/pudutech/mirsdk/mapify/mapdata/Vertex;)V", "getLeft", "()Lcom/pudutech/mirsdk/mapify/mapdata/Vertex;", "getRight", "trackId", "", "getTrackId", "()I", "setTrackId", "(I)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Lines {
    private final Vertex left;
    private final Vertex right;
    private int trackId;

    public Lines(Vertex left, Vertex right) {
        Intrinsics.checkParameterIsNotNull(left, "left");
        Intrinsics.checkParameterIsNotNull(right, "right");
        this.left = left;
        this.right = right;
    }

    public final Vertex getLeft() {
        return this.left;
    }

    public final Vertex getRight() {
        return this.right;
    }

    public final int getTrackId() {
        return this.trackId;
    }

    public final void setTrackId(int i) {
        this.trackId = i;
    }
}
