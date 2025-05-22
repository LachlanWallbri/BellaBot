package com.pudutech.bumblebee.robot_ui.util;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.location.AMapLocationManager;
import com.pudutech.location.AmapConfig;
import com.pudutech.location.SignalMamager;
import com.pudutech.location.utils.FlowCardInfo;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowCardHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/FlowCardHelper;", "", "()V", "TAG", "", "isFourG", "", "()Z", "setFourG", "(Z)V", "init", "", "reportFlowCard", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "setBindFlowCard", "view", "Lcom/pudutech/location/view/SignalView;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FlowCardHelper {
    private static boolean isFourG;
    public static final FlowCardHelper INSTANCE = new FlowCardHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private FlowCardHelper() {
    }

    public final boolean isFourG() {
        return isFourG;
    }

    public final void setFourG(boolean z) {
        isFourG = z;
    }

    public final void init() {
        MachineInfo.LteType lteType;
        MachineInfo.LteType lteType2;
        MachineInfo.LteType lteType3;
        MachineInfo machineInfoClass = MachineInfoHelper.INSTANCE.getMachineInfoClass();
        boolean z = (machineInfoClass == null || (lteType3 = machineInfoClass.getLteType()) == null || lteType3.ordinal() != MachineInfo.LteType.FourthG.ordinal()) ? false : true;
        boolean hasSimCard = FlowCardInfo.INSTANCE.hasSimCard(BaseApplication.INSTANCE.getInstance());
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("存在4G的模块 havedFourthG = ");
        sb.append(z);
        sb.append(" hasSimCard = ");
        sb.append(hasSimCard);
        sb.append("  name = ");
        MachineInfo machineInfoClass2 = MachineInfoHelper.INSTANCE.getMachineInfoClass();
        String str2 = null;
        sb.append((machineInfoClass2 == null || (lteType2 = machineInfoClass2.getLteType()) == null) ? null : lteType2.name());
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (z && hasSimCard) {
            isFourG = true;
            Pdlog.m3273d(TAG, "手机移动 isFourG = " + isFourG);
            return;
        }
        isFourG = false;
        String str3 = TAG;
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("不存在4G的模块 isFourG = ");
        sb2.append(isFourG);
        sb2.append("   ");
        MachineInfo machineInfoClass3 = MachineInfoHelper.INSTANCE.getMachineInfoClass();
        if (machineInfoClass3 != null && (lteType = machineInfoClass3.getLteType()) != null) {
            str2 = lteType.name();
        }
        sb2.append(str2);
        objArr2[0] = sb2.toString();
        Pdlog.m3273d(str3, objArr2);
    }

    public final void setBindFlowCard(SignalView view, AppCompatActivity activity) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        view.setVisibility(isFourG ? 0 : 8);
        if (isFourG) {
            new SignalMamager(activity);
            return;
        }
        Pdlog.m3273d(TAG, "setBindFlowCard 不存在4G模块 " + isFourG);
    }

    public final void reportFlowCard(AppCompatActivity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        AmapConfig amapConfig = new AmapConfig();
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        amapConfig.setMac(mac);
        amapConfig.setSoftver(RobotConfig.INSTANCE.getSoftVersion());
        amapConfig.setOnceLocation(true);
        amapConfig.setHardver(SDK.INSTANCE.getHardwareVersion());
        new AMapLocationManager((Activity) activity, amapConfig);
    }
}
