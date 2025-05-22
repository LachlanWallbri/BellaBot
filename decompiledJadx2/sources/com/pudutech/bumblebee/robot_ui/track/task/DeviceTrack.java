package com.pudutech.bumblebee.robot_ui.track.task;

import com.pudutech.mirsdk.aidl.serialize.BoardInfo;
import com.pudutech.mirsdk.aidl.serialize.LidarDeviceInfo;
import com.pudutech.mirsdk.aidl.serialize.RgbdDeviceInfo;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.DeviceExtInfo;
import com.pudutech.robot.module.report.track2.DeviceInfo;
import com.pudutech.robot.module.report.track2.Device_ext_infoKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: DeviceTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/DeviceTrack;", "", "()V", "report", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeviceTrack {
    public static final DeviceTrack INSTANCE = new DeviceTrack();

    private DeviceTrack() {
    }

    public final void report() {
        List emptyList;
        TrackingReportManager trackingReportManager = TrackingReportManager.INSTANCE;
        List<RgbdDeviceInfo> rgbdInfo = MirSdkManager.INSTANCE.getRgbdInfo();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(rgbdInfo, 10));
        for (RgbdDeviceInfo rgbdDeviceInfo : rgbdInfo) {
            arrayList.add(new DeviceInfo(rgbdDeviceInfo.getName(), rgbdDeviceInfo.getSn()));
        }
        ArrayList arrayList2 = arrayList;
        List<LidarDeviceInfo> lidarInfo = MirSdkManager.INSTANCE.getLidarInfo();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(lidarInfo, 10));
        for (LidarDeviceInfo lidarDeviceInfo : lidarInfo) {
            arrayList3.add(new DeviceInfo(lidarDeviceInfo.getName(), lidarDeviceInfo.getSn()));
        }
        ArrayList arrayList4 = arrayList3;
        List<String> cameraInfo = MirSdkManager.INSTANCE.getCameraInfo();
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(cameraInfo, 10));
        Iterator<T> it = cameraInfo.iterator();
        while (it.hasNext()) {
            arrayList5.add(new DeviceInfo("", (String) it.next()));
        }
        ArrayList arrayList6 = arrayList5;
        String batteryInfo = MirSdkManager.INSTANCE.getBatteryInfo();
        if (batteryInfo.length() > 0) {
            emptyList = CollectionsKt.listOf(new DeviceInfo("", batteryInfo));
        } else {
            emptyList = CollectionsKt.emptyList();
        }
        List list = emptyList;
        List<BoardInfo> boardInfo = MirSdkManager.INSTANCE.getBoardInfo();
        ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(boardInfo, 10));
        for (BoardInfo boardInfo2 : boardInfo) {
            arrayList7.add(new DeviceInfo(boardInfo2.getHardwareBoardName(), boardInfo2.getUid()));
        }
        Device_ext_infoKt.onDeviceExtInfo(trackingReportManager, new DeviceExtInfo(arrayList2, arrayList4, arrayList6, list, arrayList7));
    }
}
