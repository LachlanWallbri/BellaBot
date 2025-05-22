package com.pudutech.lidar.eaitg30;

import kotlin.Metadata;

/* compiled from: Tg30Filter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/lidar/eaitg30/FilterRange;", "", "()V", "endIndex", "", "getEndIndex", "()I", "setEndIndex", "(I)V", "filterOffset", "", "getFilterOffset", "()D", "setFilterOffset", "(D)V", "startIndex", "getStartIndex", "setStartIndex", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FilterRange {
    private int endIndex;
    private double filterOffset;
    private int startIndex;

    public final int getStartIndex() {
        return this.startIndex;
    }

    public final void setStartIndex(int i) {
        this.startIndex = i;
    }

    public final int getEndIndex() {
        return this.endIndex;
    }

    public final void setEndIndex(int i) {
        this.endIndex = i;
    }

    public final double getFilterOffset() {
        return this.filterOffset;
    }

    public final void setFilterOffset(double d) {
        this.filterOffset = d;
    }
}
