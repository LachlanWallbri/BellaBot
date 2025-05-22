package com.pudutech.lidar.rslidar16;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseConfigOfRsLidar16.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/lidar/rslidar16/UnitBlock;", "", "unitBlockId", "", "channelDataList", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/rslidar16/ChannelData;", "Lkotlin/collections/ArrayList;", "(ILjava/util/ArrayList;)V", "getChannelDataList", "()Ljava/util/ArrayList;", "getUnitBlockId", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class UnitBlock {
    private final ArrayList<ChannelData> channelDataList;
    private final int unitBlockId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UnitBlock copy$default(UnitBlock unitBlock, int i, ArrayList arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = unitBlock.unitBlockId;
        }
        if ((i2 & 2) != 0) {
            arrayList = unitBlock.channelDataList;
        }
        return unitBlock.copy(i, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final int getUnitBlockId() {
        return this.unitBlockId;
    }

    public final ArrayList<ChannelData> component2() {
        return this.channelDataList;
    }

    public final UnitBlock copy(int unitBlockId, ArrayList<ChannelData> channelDataList) {
        Intrinsics.checkParameterIsNotNull(channelDataList, "channelDataList");
        return new UnitBlock(unitBlockId, channelDataList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UnitBlock)) {
            return false;
        }
        UnitBlock unitBlock = (UnitBlock) other;
        return this.unitBlockId == unitBlock.unitBlockId && Intrinsics.areEqual(this.channelDataList, unitBlock.channelDataList);
    }

    public int hashCode() {
        int i = this.unitBlockId * 31;
        ArrayList<ChannelData> arrayList = this.channelDataList;
        return i + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "UnitBlock(unitBlockId=" + this.unitBlockId + ", channelDataList=" + this.channelDataList + ")";
    }

    public UnitBlock(int i, ArrayList<ChannelData> channelDataList) {
        Intrinsics.checkParameterIsNotNull(channelDataList, "channelDataList");
        this.unitBlockId = i;
        this.channelDataList = channelDataList;
    }

    public final int getUnitBlockId() {
        return this.unitBlockId;
    }

    public final ArrayList<ChannelData> getChannelDataList() {
        return this.channelDataList;
    }
}
