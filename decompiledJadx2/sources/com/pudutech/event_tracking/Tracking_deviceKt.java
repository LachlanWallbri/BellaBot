package com.pudutech.event_tracking;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: tracking_device.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"bella", "Lcom/pudutech/event_tracking/TrackDeviceInfo;", "firfox", "hls", "hls2", "hlsVSLAM", "hola", "peanut", "peanutAD", "phoenix", "supportDeviceInfoList", "", "fetchDeviceInfo", "context", "Landroid/content/Context;", "getAppName", "", "event_tracking_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Tracking_deviceKt {
    private static final TrackDeviceInfo hls = new TrackDeviceInfo("com.pudutech.pdrobot", 1, "欢乐送", "hls");
    private static final TrackDeviceInfo bella = new TrackDeviceInfo(com.pudutech.bumblebee.BuildConfig.APPLICATION_ID, 2, "贝拉", "Bella");
    private static final TrackDeviceInfo hola = new TrackDeviceInfo("com.pudutech.recycle.robot", 3, "好啦", "Hola");
    private static final TrackDeviceInfo peanut = new TrackDeviceInfo("com.pudutech.robot.peanut", 4, "葫芦", "peanut");
    private static final TrackDeviceInfo peanutAD = new TrackDeviceInfo("com.pudutech.robot.adplayer", 7, "葫芦广告屏", "AdPlayer");
    private static final TrackDeviceInfo phoenix = new TrackDeviceInfo("com.pudutech.phoenix", 5, "凤凰", "Phoenix");
    private static final TrackDeviceInfo hls2 = new TrackDeviceInfo("com.pudutech.hls2robot", 6, "欢乐送2", "hls2");
    private static final TrackDeviceInfo hlsVSLAM = new TrackDeviceInfo("com.pudutech.hlsvslam", 12, "欢乐送Vslam", "hlsVslam");
    private static final TrackDeviceInfo firfox = new TrackDeviceInfo("com.pudutech.robot.firefox", 13, "闪电匣", "firefox");
    private static final List<TrackDeviceInfo> supportDeviceInfoList = CollectionsKt.listOf((Object[]) new TrackDeviceInfo[]{hls, bella, hola, peanut, peanutAD, phoenix, hls2, hlsVSLAM, firfox});

    public static final TrackDeviceInfo fetchDeviceInfo(Context context) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Iterator<T> it = supportDeviceInfoList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((TrackDeviceInfo) obj).getAppPackage(), context.getPackageName())) {
                break;
            }
        }
        TrackDeviceInfo trackDeviceInfo = (TrackDeviceInfo) obj;
        if (trackDeviceInfo != null) {
            return trackDeviceInfo;
        }
        String packageName = context.getPackageName();
        Intrinsics.checkExpressionValueIsNotNull(packageName, "context.packageName");
        return new TrackDeviceInfo(packageName, -1, getAppName(context), "UnKnow");
    }

    private static final String getAppName(Context context) {
        int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes;
        if (i > 0) {
            String string = context.getResources().getString(i);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getString(labelRes)");
            return string;
        }
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }
}
