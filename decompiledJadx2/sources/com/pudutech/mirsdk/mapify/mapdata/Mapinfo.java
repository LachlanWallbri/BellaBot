package com.pudutech.mirsdk.mapify.mapdata;

import kotlin.Metadata;

/* compiled from: Mapinfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/mapdata/Mapinfo;", "", "linexSeparation", "", "lineySeparation", "xOriginShift", "yOriginShift", "(IIII)V", "getLinexSeparation", "()I", "setLinexSeparation", "(I)V", "getLineySeparation", "setLineySeparation", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Mapinfo {
    private int linexSeparation;
    private int lineySeparation;
    private final int xOriginShift;
    private final int yOriginShift;

    public Mapinfo(int i, int i2, int i3, int i4) {
        this.linexSeparation = i;
        this.lineySeparation = i2;
        this.xOriginShift = i3;
        this.yOriginShift = i4;
    }

    public final int getLinexSeparation() {
        return this.linexSeparation;
    }

    public final int getLineySeparation() {
        return this.lineySeparation;
    }

    public final void setLinexSeparation(int i) {
        this.linexSeparation = i;
    }

    public final void setLineySeparation(int i) {
        this.lineySeparation = i;
    }
}
