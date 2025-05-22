package com.pudutech.robot.module.report.track2;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: device_ext_info.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\tJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003JY\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006#"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/DeviceExtInfo;", "", "rgbd_list", "", "Lcom/pudutech/robot/module/report/track2/DeviceInfo;", "lidar_list", "camera_list", "battery_list", "main_board_list", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getBattery_list", "()Ljava/util/List;", "setBattery_list", "(Ljava/util/List;)V", "getCamera_list", "setCamera_list", "getLidar_list", "setLidar_list", "getMain_board_list", "setMain_board_list", "getRgbd_list", "setRgbd_list", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DeviceExtInfo {
    private List<DeviceInfo> battery_list;
    private List<DeviceInfo> camera_list;
    private List<DeviceInfo> lidar_list;
    private List<DeviceInfo> main_board_list;
    private List<DeviceInfo> rgbd_list;

    public DeviceExtInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ DeviceExtInfo copy$default(DeviceExtInfo deviceExtInfo, List list, List list2, List list3, List list4, List list5, int i, Object obj) {
        if ((i & 1) != 0) {
            list = deviceExtInfo.rgbd_list;
        }
        if ((i & 2) != 0) {
            list2 = deviceExtInfo.lidar_list;
        }
        List list6 = list2;
        if ((i & 4) != 0) {
            list3 = deviceExtInfo.camera_list;
        }
        List list7 = list3;
        if ((i & 8) != 0) {
            list4 = deviceExtInfo.battery_list;
        }
        List list8 = list4;
        if ((i & 16) != 0) {
            list5 = deviceExtInfo.main_board_list;
        }
        return deviceExtInfo.copy(list, list6, list7, list8, list5);
    }

    public final List<DeviceInfo> component1() {
        return this.rgbd_list;
    }

    public final List<DeviceInfo> component2() {
        return this.lidar_list;
    }

    public final List<DeviceInfo> component3() {
        return this.camera_list;
    }

    public final List<DeviceInfo> component4() {
        return this.battery_list;
    }

    public final List<DeviceInfo> component5() {
        return this.main_board_list;
    }

    public final DeviceExtInfo copy(List<DeviceInfo> rgbd_list, List<DeviceInfo> lidar_list, List<DeviceInfo> camera_list, List<DeviceInfo> battery_list, List<DeviceInfo> main_board_list) {
        Intrinsics.checkParameterIsNotNull(rgbd_list, "rgbd_list");
        Intrinsics.checkParameterIsNotNull(lidar_list, "lidar_list");
        Intrinsics.checkParameterIsNotNull(camera_list, "camera_list");
        Intrinsics.checkParameterIsNotNull(battery_list, "battery_list");
        Intrinsics.checkParameterIsNotNull(main_board_list, "main_board_list");
        return new DeviceExtInfo(rgbd_list, lidar_list, camera_list, battery_list, main_board_list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceExtInfo)) {
            return false;
        }
        DeviceExtInfo deviceExtInfo = (DeviceExtInfo) other;
        return Intrinsics.areEqual(this.rgbd_list, deviceExtInfo.rgbd_list) && Intrinsics.areEqual(this.lidar_list, deviceExtInfo.lidar_list) && Intrinsics.areEqual(this.camera_list, deviceExtInfo.camera_list) && Intrinsics.areEqual(this.battery_list, deviceExtInfo.battery_list) && Intrinsics.areEqual(this.main_board_list, deviceExtInfo.main_board_list);
    }

    public int hashCode() {
        List<DeviceInfo> list = this.rgbd_list;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        List<DeviceInfo> list2 = this.lidar_list;
        int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<DeviceInfo> list3 = this.camera_list;
        int hashCode3 = (hashCode2 + (list3 != null ? list3.hashCode() : 0)) * 31;
        List<DeviceInfo> list4 = this.battery_list;
        int hashCode4 = (hashCode3 + (list4 != null ? list4.hashCode() : 0)) * 31;
        List<DeviceInfo> list5 = this.main_board_list;
        return hashCode4 + (list5 != null ? list5.hashCode() : 0);
    }

    public String toString() {
        return "DeviceExtInfo(rgbd_list=" + this.rgbd_list + ", lidar_list=" + this.lidar_list + ", camera_list=" + this.camera_list + ", battery_list=" + this.battery_list + ", main_board_list=" + this.main_board_list + ")";
    }

    public DeviceExtInfo(List<DeviceInfo> rgbd_list, List<DeviceInfo> lidar_list, List<DeviceInfo> camera_list, List<DeviceInfo> battery_list, List<DeviceInfo> main_board_list) {
        Intrinsics.checkParameterIsNotNull(rgbd_list, "rgbd_list");
        Intrinsics.checkParameterIsNotNull(lidar_list, "lidar_list");
        Intrinsics.checkParameterIsNotNull(camera_list, "camera_list");
        Intrinsics.checkParameterIsNotNull(battery_list, "battery_list");
        Intrinsics.checkParameterIsNotNull(main_board_list, "main_board_list");
        this.rgbd_list = rgbd_list;
        this.lidar_list = lidar_list;
        this.camera_list = camera_list;
        this.battery_list = battery_list;
        this.main_board_list = main_board_list;
    }

    public /* synthetic */ DeviceExtInfo(List list, List list2, List list3, List list4, List list5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? CollectionsKt.emptyList() : list3, (i & 8) != 0 ? CollectionsKt.emptyList() : list4, (i & 16) != 0 ? CollectionsKt.emptyList() : list5);
    }

    public final List<DeviceInfo> getRgbd_list() {
        return this.rgbd_list;
    }

    public final void setRgbd_list(List<DeviceInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.rgbd_list = list;
    }

    public final List<DeviceInfo> getLidar_list() {
        return this.lidar_list;
    }

    public final void setLidar_list(List<DeviceInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.lidar_list = list;
    }

    public final List<DeviceInfo> getCamera_list() {
        return this.camera_list;
    }

    public final void setCamera_list(List<DeviceInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.camera_list = list;
    }

    public final List<DeviceInfo> getBattery_list() {
        return this.battery_list;
    }

    public final void setBattery_list(List<DeviceInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.battery_list = list;
    }

    public final List<DeviceInfo> getMain_board_list() {
        return this.main_board_list;
    }

    public final void setMain_board_list(List<DeviceInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.main_board_list = list;
    }
}
