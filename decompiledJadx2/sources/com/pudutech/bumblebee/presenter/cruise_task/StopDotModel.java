package com.pudutech.bumblebee.presenter.cruise_task;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StopDotModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/StopDotModel;", "", "name", "", "posX", "", "posY", "(Ljava/lang/String;DD)V", "getName", "()Ljava/lang/String;", "getPosX", "()D", "getPosY", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class StopDotModel {
    private final String name;
    private final double posX;
    private final double posY;

    public static /* synthetic */ StopDotModel copy$default(StopDotModel stopDotModel, String str, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = stopDotModel.name;
        }
        if ((i & 2) != 0) {
            d = stopDotModel.posX;
        }
        double d3 = d;
        if ((i & 4) != 0) {
            d2 = stopDotModel.posY;
        }
        return stopDotModel.copy(str, d3, d2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final double getPosX() {
        return this.posX;
    }

    /* renamed from: component3, reason: from getter */
    public final double getPosY() {
        return this.posY;
    }

    public final StopDotModel copy(String name, double posX, double posY) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new StopDotModel(name, posX, posY);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StopDotModel)) {
            return false;
        }
        StopDotModel stopDotModel = (StopDotModel) other;
        return Intrinsics.areEqual(this.name, stopDotModel.name) && Double.compare(this.posX, stopDotModel.posX) == 0 && Double.compare(this.posY, stopDotModel.posY) == 0;
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = str != null ? str.hashCode() : 0;
        long doubleToLongBits = Double.doubleToLongBits(this.posX);
        int i = ((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.posY);
        return i + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public String toString() {
        return "StopDotModel(name=" + this.name + ", posX=" + this.posX + ", posY=" + this.posY + ")";
    }

    public StopDotModel(String name, double d, double d2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.posX = d;
        this.posY = d2;
    }

    public final String getName() {
        return this.name;
    }

    public final double getPosX() {
        return this.posX;
    }

    public final double getPosY() {
        return this.posY;
    }
}
