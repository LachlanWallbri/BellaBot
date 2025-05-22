package com.pudutech.bumblebee.presenter.lease_task;

import android.app.Application;
import android.content.SharedPreferences;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.lease_task.LeaseContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.leaselib.LeaseSdk;
import com.pudutech.leaselib.OnLeaseStatusChangeListener;
import com.pudutech.leaselib.UseType;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LeasePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003*\u0001\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0014J\b\u0010\u001f\u001a\u00020\u0014H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/lease_task/LeasePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/lease_task/LeaseContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/lease_task/LeaseContract$PresenterInterface;", "()V", "KEY_LAST_TIPS_TIME", "", "SP_NAME", "TAG", "getTAG", "()Ljava/lang/String;", "onLeaseStatusChangeListener", "com/pudutech/bumblebee/presenter/lease_task/LeasePresenter$onLeaseStatusChangeListener$1", "Lcom/pudutech/bumblebee/presenter/lease_task/LeasePresenter$onLeaseStatusChangeListener$1;", "oneDay", "", "sevenDayBySec", "sharedPreferences", "Landroid/content/SharedPreferences;", "checkLease", "", "context", "Landroid/app/Application;", "isTest", "", "isNeedShowLeftTimeTips", "useType", "Lcom/pudutech/leaselib/UseType;", "leftTime", "", "onViewRemoved", "tagShowTips", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LeasePresenter extends BaseOneViewPresenter<LeaseContract.ViewInterface> implements LeaseContract.PresenterInterface {
    private SharedPreferences sharedPreferences;
    private final String TAG = "LeasePresenter";
    private final int sevenDayBySec = 604800;
    private final int oneDay = 86400000;
    private final String SP_NAME = "lease_tips";
    private final String KEY_LAST_TIPS_TIME = "key_last_tips_time";
    private final LeasePresenter$onLeaseStatusChangeListener$1 onLeaseStatusChangeListener = new OnLeaseStatusChangeListener() { // from class: com.pudutech.bumblebee.presenter.lease_task.LeasePresenter$onLeaseStatusChangeListener$1
        @Override // com.pudutech.leaselib.OnLeaseStatusChangeListener
        public void onStatusChange(UseType useType, long endTime, long leftTime) {
            LeaseContract.ViewInterface theView;
            boolean isNeedShowLeftTimeTips;
            Intrinsics.checkParameterIsNotNull(useType, "useType");
            Pdlog.m3273d(LeasePresenter.this.getTAG(), "onStatusChange useType = " + useType + ", endTime = " + endTime + " , leftTime = " + leftTime);
            theView = LeasePresenter.this.getTheView();
            if (theView != null) {
                isNeedShowLeftTimeTips = LeasePresenter.this.isNeedShowLeftTimeTips(useType, leftTime);
                theView.onLeaseStatusChange(new LeaseContract.LeaseStatus(useType, endTime, leftTime, isNeedShowLeftTimeTips));
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isNeedShowLeftTimeTips(UseType useType, long leftTime) {
        if (useType == UseType.TRIAL || useType == UseType.LEASE) {
            long j = this.sevenDayBySec;
            if (0 <= leftTime && j > leftTime) {
                SharedPreferences sharedPreferences = this.sharedPreferences;
                long j2 = sharedPreferences != null ? sharedPreferences.getLong(this.KEY_LAST_TIPS_TIME, 0L) : 0L;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - j2 > this.oneDay) {
                    Pdlog.m3273d(getTAG(), "need to tips , lastTime = " + j2 + " , now = " + currentTimeMillis);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.pudutech.bumblebee.presenter.lease_task.LeaseContract.PresenterInterface
    public void checkLease(Application context, boolean isTest) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        LeaseSdk.INSTANCE.init(context);
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(this.SP_NAME, 0);
        }
        LeaseSdk.INSTANCE.setTestServer(isTest);
        LeaseSdk.INSTANCE.setHardVersion(RobotConfig.INSTANCE.getHardwareVersion());
        LeaseSdk.INSTANCE.setSoftVersion(RobotConfig.INSTANCE.getSoftVersion());
        LeaseSdk leaseSdk = LeaseSdk.INSTANCE;
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        leaseSdk.setMac(mac);
        LeaseSdk.INSTANCE.addOnLeaseStatusChangeListener(this.onLeaseStatusChangeListener);
        LeaseSdk.INSTANCE.startCheckLease();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        LeaseSdk.INSTANCE.removeOnLeaseStatusChangeListener(this.onLeaseStatusChangeListener);
    }

    @Override // com.pudutech.bumblebee.presenter.lease_task.LeaseContract.PresenterInterface
    public void tagShowTips() {
        SharedPreferences.Editor edit;
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null || (edit = sharedPreferences.edit()) == null) {
            return;
        }
        edit.putLong(this.KEY_LAST_TIPS_TIME, System.currentTimeMillis());
        edit.apply();
    }
}
