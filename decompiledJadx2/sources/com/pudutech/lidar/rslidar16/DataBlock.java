package com.pudutech.lidar.rslidar16;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseConfigOfRsLidar16.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B(\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0011\u0010\u0010\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0011\u0010\u0011\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/lidar/rslidar16/DataBlock;", "", "flag", "Lkotlin/UShort;", "azimuth", "firstUnitBlock", "Lcom/pudutech/lidar/rslidar16/UnitBlock;", "secondUnitBlock", "(SSLcom/pudutech/lidar/rslidar16/UnitBlock;Lcom/pudutech/lidar/rslidar16/UnitBlock;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAzimuth", "()S", ExifInterface.LATITUDE_SOUTH, "getFirstUnitBlock", "()Lcom/pudutech/lidar/rslidar16/UnitBlock;", "getFlag", "getSecondUnitBlock", "component1", "component2", "component3", "component4", "copy", "copy-mV9EDXI", "(SSLcom/pudutech/lidar/rslidar16/UnitBlock;Lcom/pudutech/lidar/rslidar16/UnitBlock;)Lcom/pudutech/lidar/rslidar16/DataBlock;", "equals", "", "other", "hashCode", "", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class DataBlock {
    private final short azimuth;
    private final UnitBlock firstUnitBlock;
    private final short flag;
    private final UnitBlock secondUnitBlock;

    /* renamed from: copy-mV9EDXI$default, reason: not valid java name */
    public static /* synthetic */ DataBlock m4396copymV9EDXI$default(DataBlock dataBlock, short s, short s2, UnitBlock unitBlock, UnitBlock unitBlock2, int i, Object obj) {
        if ((i & 1) != 0) {
            s = dataBlock.flag;
        }
        if ((i & 2) != 0) {
            s2 = dataBlock.azimuth;
        }
        if ((i & 4) != 0) {
            unitBlock = dataBlock.firstUnitBlock;
        }
        if ((i & 8) != 0) {
            unitBlock2 = dataBlock.secondUnitBlock;
        }
        return dataBlock.m4397copymV9EDXI(s, s2, unitBlock, unitBlock2);
    }

    /* renamed from: component1, reason: from getter */
    public final short getFlag() {
        return this.flag;
    }

    /* renamed from: component2, reason: from getter */
    public final short getAzimuth() {
        return this.azimuth;
    }

    /* renamed from: component3, reason: from getter */
    public final UnitBlock getFirstUnitBlock() {
        return this.firstUnitBlock;
    }

    /* renamed from: component4, reason: from getter */
    public final UnitBlock getSecondUnitBlock() {
        return this.secondUnitBlock;
    }

    /* renamed from: copy-mV9EDXI, reason: not valid java name */
    public final DataBlock m4397copymV9EDXI(short flag, short azimuth, UnitBlock firstUnitBlock, UnitBlock secondUnitBlock) {
        Intrinsics.checkParameterIsNotNull(firstUnitBlock, "firstUnitBlock");
        Intrinsics.checkParameterIsNotNull(secondUnitBlock, "secondUnitBlock");
        return new DataBlock(flag, azimuth, firstUnitBlock, secondUnitBlock);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataBlock)) {
            return false;
        }
        DataBlock dataBlock = (DataBlock) other;
        return this.flag == dataBlock.flag && this.azimuth == dataBlock.azimuth && Intrinsics.areEqual(this.firstUnitBlock, dataBlock.firstUnitBlock) && Intrinsics.areEqual(this.secondUnitBlock, dataBlock.secondUnitBlock);
    }

    public int hashCode() {
        int i = ((this.flag * 31) + this.azimuth) * 31;
        UnitBlock unitBlock = this.firstUnitBlock;
        int hashCode = (i + (unitBlock != null ? unitBlock.hashCode() : 0)) * 31;
        UnitBlock unitBlock2 = this.secondUnitBlock;
        return hashCode + (unitBlock2 != null ? unitBlock2.hashCode() : 0);
    }

    public String toString() {
        return "DataBlock(flag=" + UShort.m4796toStringimpl(this.flag) + ", azimuth=" + UShort.m4796toStringimpl(this.azimuth) + ", firstUnitBlock=" + this.firstUnitBlock + ", secondUnitBlock=" + this.secondUnitBlock + ")";
    }

    private DataBlock(short s, short s2, UnitBlock unitBlock, UnitBlock unitBlock2) {
        this.flag = s;
        this.azimuth = s2;
        this.firstUnitBlock = unitBlock;
        this.secondUnitBlock = unitBlock2;
    }

    public /* synthetic */ DataBlock(short s, short s2, UnitBlock unitBlock, UnitBlock unitBlock2, DefaultConstructorMarker defaultConstructorMarker) {
        this(s, s2, unitBlock, unitBlock2);
    }

    public final short getFlag() {
        return this.flag;
    }

    public final short getAzimuth() {
        return this.azimuth;
    }

    public final UnitBlock getFirstUnitBlock() {
        return this.firstUnitBlock;
    }

    public final UnitBlock getSecondUnitBlock() {
        return this.secondUnitBlock;
    }
}
