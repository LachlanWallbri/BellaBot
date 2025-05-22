package com.pudutech.bumblebee.presenter.cruise_task;

import kotlin.Metadata;

/* compiled from: LineModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/LineModel;", "", "startX", "", "startY", "endX", "endY", "(IIII)V", "getEndX", "()I", "setEndX", "(I)V", "getEndY", "setEndY", "getStartX", "setStartX", "getStartY", "setStartY", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LineModel {
    private int endX;
    private int endY;
    private int startX;
    private int startY;

    public static /* synthetic */ LineModel copy$default(LineModel lineModel, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = lineModel.startX;
        }
        if ((i5 & 2) != 0) {
            i2 = lineModel.startY;
        }
        if ((i5 & 4) != 0) {
            i3 = lineModel.endX;
        }
        if ((i5 & 8) != 0) {
            i4 = lineModel.endY;
        }
        return lineModel.copy(i, i2, i3, i4);
    }

    /* renamed from: component1, reason: from getter */
    public final int getStartX() {
        return this.startX;
    }

    /* renamed from: component2, reason: from getter */
    public final int getStartY() {
        return this.startY;
    }

    /* renamed from: component3, reason: from getter */
    public final int getEndX() {
        return this.endX;
    }

    /* renamed from: component4, reason: from getter */
    public final int getEndY() {
        return this.endY;
    }

    public final LineModel copy(int startX, int startY, int endX, int endY) {
        return new LineModel(startX, startY, endX, endY);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LineModel)) {
            return false;
        }
        LineModel lineModel = (LineModel) other;
        return this.startX == lineModel.startX && this.startY == lineModel.startY && this.endX == lineModel.endX && this.endY == lineModel.endY;
    }

    public int hashCode() {
        return (((((this.startX * 31) + this.startY) * 31) + this.endX) * 31) + this.endY;
    }

    public String toString() {
        return "LineModel(startX=" + this.startX + ", startY=" + this.startY + ", endX=" + this.endX + ", endY=" + this.endY + ")";
    }

    public LineModel(int i, int i2, int i3, int i4) {
        this.startX = i;
        this.startY = i2;
        this.endX = i3;
        this.endY = i4;
    }

    public final int getEndX() {
        return this.endX;
    }

    public final int getEndY() {
        return this.endY;
    }

    public final int getStartX() {
        return this.startX;
    }

    public final int getStartY() {
        return this.startY;
    }

    public final void setEndX(int i) {
        this.endX = i;
    }

    public final void setEndY(int i) {
        this.endY = i;
    }

    public final void setStartX(int i) {
        this.startX = i;
    }

    public final void setStartY(int i) {
        this.startY = i;
    }
}
