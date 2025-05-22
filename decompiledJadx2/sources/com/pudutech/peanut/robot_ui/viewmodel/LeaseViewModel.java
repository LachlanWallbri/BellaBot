package com.pudutech.peanut.robot_ui.viewmodel;

import android.app.Application;
import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.leaselib.LeaseSdk;
import com.pudutech.leaselib.OnLeaseStatusChangeListener;
import com.pudutech.leaselib.UseType;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import com.pudutech.peanut.presenter.utils.WifiUtil;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.UrlManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LeaseViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000=\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/LeaseViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "KEY_LAST_TIPS_TIME", "", "TAG", "onLeaseStatusChangeListener", "com/pudutech/peanut/robot_ui/viewmodel/LeaseViewModel$onLeaseStatusChangeListener$1", "Lcom/pudutech/peanut/robot_ui/viewmodel/LeaseViewModel$onLeaseStatusChangeListener$1;", "oneDay", "", "sevenDayBySec", "checkLease", "", "isNeedShowLeftTimeTips", "", "useType", "Lcom/pudutech/leaselib/UseType;", "leftTime", "", "tagShowTips", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LeaseViewModel extends BaseViewModel {
    private final String TAG = "LeasePresenter";
    private final int sevenDayBySec = 604800;
    private final int oneDay = 86400000;
    private final String KEY_LAST_TIPS_TIME = "key_last_tips_time";
    private final LeaseViewModel$onLeaseStatusChangeListener$1 onLeaseStatusChangeListener = new OnLeaseStatusChangeListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.LeaseViewModel$onLeaseStatusChangeListener$1
        @Override // com.pudutech.leaselib.OnLeaseStatusChangeListener
        public void onStatusChange(UseType useType, long endTime, long leftTime) {
            String str;
            Intrinsics.checkParameterIsNotNull(useType, "useType");
            str = LeaseViewModel.this.TAG;
            Pdlog.m3273d(str, "onStatusChange useType = " + useType + ", endTime = " + endTime + " , leftTime = " + leftTime);
        }
    };

    public final void checkLease() {
        LeaseSdk leaseSdk = LeaseSdk.INSTANCE;
        Context context = RobotContext.INSTANCE.getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
        }
        leaseSdk.init((Application) context);
        LeaseSdk.INSTANCE.setTestServer(UrlManager.INSTANCE.isTest());
        LeaseSdk.INSTANCE.setHardVersion(RobotConfig.INSTANCE.getHardwareVersion());
        LeaseSdk leaseSdk2 = LeaseSdk.INSTANCE;
        String str = RobotContext.INSTANCE.getContext().getPackageManager().getPackageInfo(RobotContext.INSTANCE.getContext().getPackageName(), 0).versionName;
        Intrinsics.checkExpressionValueIsNotNull(str, "RobotContext.context.pac…  0\n        ).versionName");
        leaseSdk2.setSoftVersion(str);
        LeaseSdk leaseSdk3 = LeaseSdk.INSTANCE;
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        leaseSdk3.setMac(mac);
        LeaseSdk.INSTANCE.addOnLeaseStatusChangeListener(this.onLeaseStatusChangeListener);
        LeaseSdk.INSTANCE.startCheckLease();
    }

    private final boolean isNeedShowLeftTimeTips(UseType useType, long leftTime) {
        Pdlog.m3273d(this.TAG, "isNeedShowLeftTimeTips : useType = " + useType + "; leftTime = " + leftTime + "; ");
        if (useType == UseType.TRIAL || useType == UseType.LEASE) {
            long j = this.sevenDayBySec;
            if (0 <= leftTime && j > leftTime) {
                long j2 = SpUtils.get(RobotContext.INSTANCE.getContext(), this.KEY_LAST_TIPS_TIME, 0L);
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - j2 > this.oneDay) {
                    Pdlog.m3273d(this.TAG, "need to tips , lastTime = " + j2 + " , now = " + currentTimeMillis);
                    return true;
                }
            }
        }
        return false;
    }

    public final void tagShowTips() {
        Pdlog.m3273d(this.TAG, "tagShowTips ");
        SpUtils.set(RobotContext.INSTANCE.getContext(), this.KEY_LAST_TIPS_TIME, System.currentTimeMillis());
    }
}
