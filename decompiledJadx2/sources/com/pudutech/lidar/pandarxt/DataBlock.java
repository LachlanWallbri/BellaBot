package com.pudutech.lidar.pandarxt;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseConfigOfPandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B2\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tø\u0001\u0000¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0013\u001a\u00020\u0005HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0019\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tHÆ\u0003JA\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/DataBlock;", "", "dataBlockId", "", "azimuth", "Lkotlin/UShort;", "channelList", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/pandarxt/Channel;", "Lkotlin/collections/ArrayList;", "(ISLjava/util/ArrayList;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAzimuth", "()S", ExifInterface.LATITUDE_SOUTH, "getChannelList", "()Ljava/util/ArrayList;", "getDataBlockId", "()I", "component1", "component2", "component3", "copy", "copy-jp45LAs", "(ISLjava/util/ArrayList;)Lcom/pudutech/lidar/pandarxt/DataBlock;", "equals", "", "other", "hashCode", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class DataBlock {
    private final short azimuth;
    private final ArrayList<Channel> channelList;
    private final int dataBlockId;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: copy-jp45LAs$default, reason: not valid java name */
    public static /* synthetic */ DataBlock m4381copyjp45LAs$default(DataBlock dataBlock, int i, short s, ArrayList arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = dataBlock.dataBlockId;
        }
        if ((i2 & 2) != 0) {
            s = dataBlock.azimuth;
        }
        if ((i2 & 4) != 0) {
            arrayList = dataBlock.channelList;
        }
        return dataBlock.m4382copyjp45LAs(i, s, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final int getDataBlockId() {
        return this.dataBlockId;
    }

    /* renamed from: component2, reason: from getter */
    public final short getAzimuth() {
        return this.azimuth;
    }

    public final ArrayList<Channel> component3() {
        return this.channelList;
    }

    /* renamed from: copy-jp45LAs, reason: not valid java name */
    public final DataBlock m4382copyjp45LAs(int dataBlockId, short azimuth, ArrayList<Channel> channelList) {
        Intrinsics.checkParameterIsNotNull(channelList, "channelList");
        return new DataBlock(dataBlockId, azimuth, channelList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataBlock)) {
            return false;
        }
        DataBlock dataBlock = (DataBlock) other;
        return this.dataBlockId == dataBlock.dataBlockId && this.azimuth == dataBlock.azimuth && Intrinsics.areEqual(this.channelList, dataBlock.channelList);
    }

    public int hashCode() {
        int i = ((this.dataBlockId * 31) + this.azimuth) * 31;
        ArrayList<Channel> arrayList = this.channelList;
        return i + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "DataBlock(dataBlockId=" + this.dataBlockId + ", azimuth=" + UShort.m4796toStringimpl(this.azimuth) + ", channelList=" + this.channelList + ")";
    }

    private DataBlock(int i, short s, ArrayList<Channel> arrayList) {
        this.dataBlockId = i;
        this.azimuth = s;
        this.channelList = arrayList;
    }

    public /* synthetic */ DataBlock(int i, short s, ArrayList arrayList, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, s, arrayList);
    }

    public /* synthetic */ DataBlock(int i, short s, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, s, arrayList);
    }

    public final int getDataBlockId() {
        return this.dataBlockId;
    }

    public final short getAzimuth() {
        return this.azimuth;
    }

    public final ArrayList<Channel> getChannelList() {
        return this.channelList;
    }
}
