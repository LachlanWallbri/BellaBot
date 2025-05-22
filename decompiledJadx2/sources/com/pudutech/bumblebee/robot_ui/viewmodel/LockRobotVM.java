package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.antichannelconflict.AntiConfig;
import com.pudutech.antichannelconflict.PDEscapeManager;
import com.pudutech.antichannelconflict.escape.util.EscapeStatus;
import com.pudutech.antichannelconflict.escape.util.ProductType;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LockRobotVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0010H\u0014J\u0006\u0010\u0015\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/LockRobotVM;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "_escapeStatusLD", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/antichannelconflict/escape/util/EscapeStatus;", "escapeDetectListener", "com/pudutech/bumblebee/robot_ui/viewmodel/LockRobotVM$escapeDetectListener$1", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/LockRobotVM$escapeDetectListener$1;", "escapeStatusLD", "Landroidx/lifecycle/LiveData;", "getEscapeStatusLD", "()Landroidx/lifecycle/LiveData;", "checkDetectEscape", "", "checkLockState", "getLastLockState", "", "onCleared", "start", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LockRobotVM extends BaseViewModel {
    private final String TAG = "LockRobotVM";
    private final MutableLiveData<EscapeStatus> _escapeStatusLD = new MutableLiveData<>();
    private final LiveData<EscapeStatus> escapeStatusLD = VMExtKt.toLiveData(this._escapeStatusLD);
    private final LockRobotVM$escapeDetectListener$1 escapeDetectListener = new LockRobotVM$escapeDetectListener$1(this);

    public LockRobotVM() {
        PDEscapeManager.INSTANCE.init(BaseApp.INSTANCE.getINSTANCE(), ProductType.BELLABOT, new Function1<AntiConfig, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.LockRobotVM.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AntiConfig receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AntiConfig antiConfig) {
                invoke2(antiConfig);
                return Unit.INSTANCE;
            }
        });
        PDEscapeManager.INSTANCE.addEscapeDetectListener(this.escapeDetectListener);
    }

    public final LiveData<EscapeStatus> getEscapeStatusLD() {
        return this.escapeStatusLD;
    }

    public final void start() {
        PDEscapeManager.INSTANCE.start();
    }

    public final void checkDetectEscape() {
        PDEscapeManager.INSTANCE.startDetectEscape();
    }

    public final void checkLockState() {
        PDEscapeManager.INSTANCE.startCheckLockStatus();
    }

    public final boolean getLastLockState() {
        boolean lastLockStatus = PDEscapeManager.INSTANCE.getLastLockStatus();
        Pdlog.m3273d(this.TAG, "getLastLockState: " + lastLockStatus);
        return lastLockStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        PDEscapeManager.INSTANCE.removeEscapeDetectListener(this.escapeDetectListener);
    }
}
