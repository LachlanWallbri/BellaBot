package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import com.google.gson.annotations.SerializedName;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PositionInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PositionInfo;", "", "shopId", "", "mapName", "", "mac", "timestamp", "positionVector", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PositionVector;", "(JLjava/lang/String;Ljava/lang/String;JLcom/pudutech/bumblebee/presenter/robot_open_task/bean/PositionVector;)V", "getMac", "()Ljava/lang/String;", "getMapName", "getPositionVector", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PositionVector;", "getShopId", "()J", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class PositionInfo {

    @SerializedName("pid")
    private final String mac;

    @SerializedName("map_name")
    private final String mapName;

    @SerializedName(MapElement.Key.VECTOR)
    private final PositionVector positionVector;

    @SerializedName("shop_id")
    private final long shopId;

    @SerializedName("timestamp")
    private final long timestamp;

    /* renamed from: component1, reason: from getter */
    public final long getShopId() {
        return this.shopId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMapName() {
        return this.mapName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component4, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component5, reason: from getter */
    public final PositionVector getPositionVector() {
        return this.positionVector;
    }

    public final PositionInfo copy(long shopId, String mapName, String mac, long timestamp, PositionVector positionVector) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(positionVector, "positionVector");
        return new PositionInfo(shopId, mapName, mac, timestamp, positionVector);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PositionInfo)) {
            return false;
        }
        PositionInfo positionInfo = (PositionInfo) other;
        return this.shopId == positionInfo.shopId && Intrinsics.areEqual(this.mapName, positionInfo.mapName) && Intrinsics.areEqual(this.mac, positionInfo.mac) && this.timestamp == positionInfo.timestamp && Intrinsics.areEqual(this.positionVector, positionInfo.positionVector);
    }

    public int hashCode() {
        long j = this.shopId;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.mapName;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.mac;
        int hashCode2 = str2 != null ? str2.hashCode() : 0;
        long j2 = this.timestamp;
        int i2 = (((hashCode + hashCode2) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31;
        PositionVector positionVector = this.positionVector;
        return i2 + (positionVector != null ? positionVector.hashCode() : 0);
    }

    public String toString() {
        return "PositionInfo(shopId=" + this.shopId + ", mapName=" + this.mapName + ", mac=" + this.mac + ", timestamp=" + this.timestamp + ", positionVector=" + this.positionVector + ")";
    }

    public PositionInfo(long j, String mapName, String mac, long j2, PositionVector positionVector) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(positionVector, "positionVector");
        this.shopId = j;
        this.mapName = mapName;
        this.mac = mac;
        this.timestamp = j2;
        this.positionVector = positionVector;
    }

    public final long getShopId() {
        return this.shopId;
    }

    public final String getMapName() {
        return this.mapName;
    }

    public final String getMac() {
        return this.mac;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final PositionVector getPositionVector() {
        return this.positionVector;
    }
}
