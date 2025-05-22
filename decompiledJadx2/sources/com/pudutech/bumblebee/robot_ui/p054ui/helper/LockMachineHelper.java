package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.app.Activity;
import android.app.Application;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import com.pudutech.antichannelconflict.escape.util.EscapeStatus;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestManager;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LockMachineDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.LockMachineHelper;
import com.pudutech.bumblebee.robot_ui.viewmodel.LockRobotVM;
import com.pudutech.disinfect.baselib.base.BaseApp;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LockMachineHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\u0006\u0010\u0018\u001a\u00020\u0015J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\b\u0010\u001a\u001a\u00020\u0015H\u0016J)\u0010\u001b\u001a\u00020\u00152!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00150\u0010J\b\u0010\u001d\u001a\u00020\u0015H\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/LockMachineHelper;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BaseLifecycleObserver;", "activity", "Landroid/app/Activity;", "owner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroid/app/Activity;Landroidx/lifecycle/LifecycleOwner;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "lockDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LockMachineDialog;", "lockRobotVM", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/LockRobotVM;", "onHavedLocked", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isHavedlocked", "", "create", "destroy", "onNewIntent", "pause", "resume", "setOnHavedLocked", "isHavedLocked", "showLockDialog", "start", "stop", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LockMachineHelper implements BaseLifecycleObserver {
    private final String TAG;
    private Activity activity;
    private LockMachineDialog lockDialog;
    private final LockRobotVM lockRobotVM;
    private Function1<? super Boolean, Unit> onHavedLocked;
    private LifecycleOwner owner;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[EscapeStatus.values().length];

        static {
            $EnumSwitchMapping$0[EscapeStatus.UNLOCKED.ordinal()] = 1;
            $EnumSwitchMapping$0[EscapeStatus.LOCKED.ordinal()] = 2;
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void any() {
        BaseLifecycleObserver.DefaultImpls.any(this);
    }

    public final String getTAG() {
        return this.TAG;
    }

    public LockMachineHelper(Activity activity, LifecycleOwner owner) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        this.TAG = "LockMachineHelper";
        Application instance = BaseApp.INSTANCE.getINSTANCE();
        if (instance == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.disinfect.baselib.base.BaseApp");
        }
        ViewModel viewModel = ((BaseApp) instance).getAppViewModelProvider().get(LockRobotVM.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "(BaseApp.INSTANCE as Bas…(LockRobotVM::class.java)");
        this.lockRobotVM = (LockRobotVM) viewModel;
        this.activity = activity;
        this.owner = owner;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void create() {
        BaseLifecycleObserver.DefaultImpls.create(this);
        Pdlog.m3273d(this.TAG, "create()");
        if (AgentTestManager.INSTANCE.isHaveTestData()) {
            return;
        }
        if (this.lockRobotVM.getLastLockState()) {
            showLockDialog();
        }
        this.lockRobotVM.getEscapeStatusLD().observe(this.owner, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.LockMachineHelper$create$$inlined$observe$1
            /* JADX WARN: Code restructure failed: missing block: B:16:0x004f, code lost:
            
                r6 = r5.this$0.lockDialog;
             */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onChanged(T t) {
                LockMachineDialog lockMachineDialog;
                LockMachineDialog lockMachineDialog2;
                Function1 function1;
                LockMachineDialog lockMachineDialog3;
                EscapeStatus escapeStatus = (EscapeStatus) t;
                Pdlog.m3273d(LockMachineHelper.this.getTAG(), "initObserver escapeStatusLD: " + escapeStatus);
                lockMachineDialog = LockMachineHelper.this.lockDialog;
                if (lockMachineDialog != null) {
                    lockMachineDialog.loadImgShow(false);
                }
                int i = LockMachineHelper.WhenMappings.$EnumSwitchMapping$0[escapeStatus.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        return;
                    }
                    LockMachineHelper.this.showLockDialog();
                    return;
                }
                lockMachineDialog2 = LockMachineHelper.this.lockDialog;
                if (lockMachineDialog2 != null && lockMachineDialog2.isShowing() && lockMachineDialog3 != null) {
                    lockMachineDialog3.dismiss();
                }
                Constans.INSTANCE.setLockedMachine(false);
                Behavior.INSTANCE.setHaveLocked(false);
                function1 = LockMachineHelper.this.onHavedLocked;
                if (function1 != null) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLockDialog() {
        LockMachineDialog lockMachineDialog = this.lockDialog;
        if (lockMachineDialog != null) {
            lockMachineDialog.loadImgShow(false);
        }
        if (this.lockDialog == null) {
            final LockMachineDialog lockMachineDialog2 = new LockMachineDialog(this.activity);
            lockMachineDialog2.setOnNegativeButtonClicked(new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.LockMachineHelper$showLockDialog$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    LockRobotVM lockRobotVM;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    LockMachineDialog.this.loadImgShow(true);
                    lockRobotVM = this.lockRobotVM;
                    lockRobotVM.checkLockState();
                }
            });
            this.lockDialog = lockMachineDialog2;
        }
        LockMachineDialog lockMachineDialog3 = this.lockDialog;
        if (lockMachineDialog3 == null || !lockMachineDialog3.isShowing()) {
            Constans.INSTANCE.setLockedMachine(true);
            Behavior.INSTANCE.setHaveLocked(true);
            Function1<? super Boolean, Unit> function1 = this.onHavedLocked;
            if (function1 != null) {
                function1.invoke(true);
            }
            LockMachineDialog lockMachineDialog4 = this.lockDialog;
            if (lockMachineDialog4 != null) {
                lockMachineDialog4.show();
            }
        }
    }

    public final void setOnHavedLocked(Function1<? super Boolean, Unit> onHavedLocked) {
        Intrinsics.checkParameterIsNotNull(onHavedLocked, "onHavedLocked");
        this.onHavedLocked = onHavedLocked;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void start() {
        BaseLifecycleObserver.DefaultImpls.start(this);
        Pdlog.m3273d(this.TAG, "start()");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void resume() {
        BaseLifecycleObserver.DefaultImpls.resume(this);
        Pdlog.m3273d(this.TAG, "resume()");
    }

    public final void onNewIntent() {
        Pdlog.m3273d(this.TAG, "onNewIntent()");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void pause() {
        BaseLifecycleObserver.DefaultImpls.pause(this);
        Pdlog.m3273d(this.TAG, "pause()");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void stop() {
        BaseLifecycleObserver.DefaultImpls.stop(this);
        Pdlog.m3273d(this.TAG, "stop()");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void destroy() {
        LockMachineDialog lockMachineDialog;
        BaseLifecycleObserver.DefaultImpls.destroy(this);
        Pdlog.m3273d(this.TAG, "destroy()");
        LockMachineDialog lockMachineDialog2 = this.lockDialog;
        if (lockMachineDialog2 == null || !lockMachineDialog2.isShowing() || (lockMachineDialog = this.lockDialog) == null) {
            return;
        }
        lockMachineDialog.dismiss();
    }
}
