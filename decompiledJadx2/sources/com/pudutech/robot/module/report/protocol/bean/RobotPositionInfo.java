package com.pudutech.robot.module.report.protocol.bean;

import com.google.gson.annotations.SerializedName;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotPosition.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionInfo;", "", "shopId", "", "mapName", "", "timestamp", MapElement.Key.VECTOR, "Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionVector;", "(JLjava/lang/String;JLcom/pudutech/robot/module/report/protocol/bean/RobotPositionVector;)V", "getMapName", "()Ljava/lang/String;", "getShopId", "()J", "getTimestamp", "getVector", "()Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionVector;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class RobotPositionInfo {

    @SerializedName("map_name")
    private final String mapName;

    @SerializedName("shop_id")
    private final long shopId;

    @SerializedName("timestamp")
    private final long timestamp;

    @SerializedName(MapElement.Key.VECTOR)
    private final RobotPositionVector vector;

    public static /* synthetic */ RobotPositionInfo copy$default(RobotPositionInfo robotPositionInfo, long j, String str, long j2, RobotPositionVector robotPositionVector, int i, Object obj) {
        if ((i & 1) != 0) {
            j = robotPositionInfo.shopId;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            str = robotPositionInfo.mapName;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            j2 = robotPositionInfo.timestamp;
        }
        long j4 = j2;
        if ((i & 8) != 0) {
            robotPositionVector = robotPositionInfo.vector;
        }
        return robotPositionInfo.copy(j3, str2, j4, robotPositionVector);
    }

    /* renamed from: component1, reason: from getter */
    public final long getShopId() {
        return this.shopId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMapName() {
        return this.mapName;
    }

    /* renamed from: component3, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component4, reason: from getter */
    public final RobotPositionVector getVector() {
        return this.vector;
    }

    public final RobotPositionInfo copy(long shopId, String mapName, long timestamp, RobotPositionVector vector) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(vector, "vector");
        return new RobotPositionInfo(shopId, mapName, timestamp, vector);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotPositionInfo)) {
            return false;
        }
        RobotPositionInfo robotPositionInfo = (RobotPositionInfo) other;
        return this.shopId == robotPositionInfo.shopId && Intrinsics.areEqual(this.mapName, robotPositionInfo.mapName) && this.timestamp == robotPositionInfo.timestamp && Intrinsics.areEqual(this.vector, robotPositionInfo.vector);
    }

    public int hashCode() {
        long j = this.shopId;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.mapName;
        int hashCode = str != null ? str.hashCode() : 0;
        long j2 = this.timestamp;
        int i2 = (((i + hashCode) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31;
        RobotPositionVector robotPositionVector = this.vector;
        return i2 + (robotPositionVector != null ? robotPositionVector.hashCode() : 0);
    }

    public String toString() {
        return "RobotPositionInfo(shopId=" + this.shopId + ", mapName=" + this.mapName + ", timestamp=" + this.timestamp + ", vector=" + this.vector + ")";
    }

    public RobotPositionInfo(long j, String mapName, long j2, RobotPositionVector vector) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(vector, "vector");
        this.shopId = j;
        this.mapName = mapName;
        this.timestamp = j2;
        this.vector = vector;
    }

    public final long getShopId() {
        return this.shopId;
    }

    public final String getMapName() {
        return this.mapName;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final RobotPositionVector getVector() {
        return this.vector;
    }
}
