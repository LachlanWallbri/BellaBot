package com.pudutech.bumblebee.presenter.robot_open_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract;
import com.pudutech.robot.opensdk.interf.ICallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BeeperBindPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fH\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0006H\u0016J\u0012\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperBindPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperBindContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperBindContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "genBindCode", "", "getCallSwitch", "", "getHost", "isLocalServer", "setCallSwitch", "b", "isLocal", "setLocalHost", "s", "unBindAllDevice", "callback", "Lcom/pudutech/robot/opensdk/interf/ICallback;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BeeperBindPresenter extends BaseOneViewPresenter<BeeperBindContract.ViewInterface> implements BeeperBindContract.PresenterInterface {
    private final String TAG = "BeeperBindPresenter";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract.PresenterInterface
    public void genBindCode() {
        Pdlog.m3273d(getTAG(), "genBindCode");
        runOnBusinessThread(new BeeperBindPresenter$genBindCode$1(this));
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract.PresenterInterface
    public void unBindAllDevice(ICallback callback) {
        RobotOpenManager.INSTANCE.unBindAllDevice$module_bumblebee_presenter_robotRelease(callback);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract.PresenterInterface
    public boolean getCallSwitch() {
        return RobotOpenManager.INSTANCE.getSwitch();
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract.PresenterInterface
    public void setLocalHost(String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        Pdlog.m3273d(getTAG(), "setLocalHost : s = " + s + "; ");
        RobotOpenHelper.INSTANCE.setLocalHost(s);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract.PresenterInterface
    public boolean isLocalServer() {
        return RobotOpenManager.INSTANCE.isLocal();
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract.PresenterInterface
    public String getHost() {
        return RobotOpenHelper.INSTANCE.getLocalHost();
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract.PresenterInterface
    public void setCallSwitch(final boolean b, final boolean isLocal) {
        Pdlog.m3273d(getTAG(), "setCallSwitch : b = " + b + "; isLocal = " + isLocal);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindPresenter$setCallSwitch$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RobotOpenManager.INSTANCE.setSwitch(b, isLocal);
            }
        });
    }
}
