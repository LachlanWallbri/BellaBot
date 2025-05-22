package com.pudutech.bumblebee.robot_ui.module.escape;

import android.app.Dialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LockMachineDialog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EscapeObserver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeObserver;", "Landroidx/lifecycle/LifecycleEventObserver;", "presenter", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapePresenter;", "(Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapePresenter;)V", "TAG", "", "checkEndObserver", "Lkotlin/Function0;", "", "getCheckEndObserver", "()Lkotlin/jvm/functions/Function0;", "setCheckEndObserver", "(Lkotlin/jvm/functions/Function0;)V", "newMapDialog", "Landroid/app/Dialog;", "getPresenter", "()Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapePresenter;", "view", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeView;", "observe", MapElement.Source.SOURCE, "Landroidx/lifecycle/LifecycleOwner;", "onStateChanged", "event", "Landroidx/lifecycle/Lifecycle$Event;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EscapeObserver implements LifecycleEventObserver {
    private final String TAG;
    private Function0<Unit> checkEndObserver;
    private Dialog newMapDialog;
    private final EscapePresenter presenter;
    private final EscapeView view;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Lifecycle.Event.values().length];

        static {
            $EnumSwitchMapping$0[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
        }
    }

    public EscapeObserver(EscapePresenter presenter) {
        Intrinsics.checkParameterIsNotNull(presenter, "presenter");
        this.presenter = presenter;
        this.TAG = "EscapeObserver";
        EscapeModule.IView view = this.presenter.getView();
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.module.escape.EscapeView");
        }
        this.view = (EscapeView) view;
    }

    public final EscapePresenter getPresenter() {
        return this.presenter;
    }

    public final Function0<Unit> getCheckEndObserver() {
        return this.checkEndObserver;
    }

    public final void setCheckEndObserver(Function0<Unit> function0) {
        this.checkEndObserver = function0;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (WhenMappings.$EnumSwitchMapping$0[event.ordinal()] != 1) {
            return;
        }
        observe(source);
    }

    private final void observe(LifecycleOwner source) {
        if (source != null) {
            final AppCompatActivity appCompatActivity = (AppCompatActivity) source;
            AppCompatActivity appCompatActivity2 = appCompatActivity;
            this.view.getNewMapLiveData().observe(appCompatActivity2, new EscapeObserver$observe$$inlined$apply$lambda$1(appCompatActivity, this));
            this.view.getNeedSyncMapLiveData().observe(appCompatActivity2, new EscapeObserver$observe$$inlined$apply$lambda$2(appCompatActivity, this));
            this.view.getSyncedMapLiveData().observe(appCompatActivity2, new EscapeObserver$observe$$inlined$apply$lambda$3(appCompatActivity, this));
            this.view.getNotNetworkLiveData().observe(appCompatActivity2, new EscapeObserver$observe$$inlined$apply$lambda$4(appCompatActivity, this));
            this.view.getCheckNetworkEndLiveData().observe(appCompatActivity2, new Observer<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeObserver$observe$$inlined$apply$lambda$5
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Unit unit) {
                    Dialog dialog;
                    String str;
                    dialog = EscapeObserver.this.newMapDialog;
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    EscapeObserver.this.getPresenter().getDelegate().checkEscape();
                    str = EscapeObserver.this.TAG;
                    Pdlog.m3273d(str, "checkNetworkEndLiveData()");
                }
            });
            this.view.getCheckEndLiveData().observe(appCompatActivity2, new Observer<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeObserver$observe$$inlined$apply$lambda$6
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Unit unit) {
                    String str;
                    Function0<Unit> checkEndObserver = EscapeObserver.this.getCheckEndObserver();
                    if (checkEndObserver != null) {
                        checkEndObserver.invoke();
                    }
                    str = EscapeObserver.this.TAG;
                    Pdlog.m3273d(str, "checkEndLiveData() = " + EscapeObserver.this.getCheckEndObserver());
                }
            });
            this.view.getRobotLockLiveData().observe(appCompatActivity2, new Observer<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeObserver$observe$$inlined$apply$lambda$7
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Unit unit) {
                    String str;
                    LockMachineDialog lockMachineDialog = new LockMachineDialog(AppCompatActivity.this);
                    lockMachineDialog.setClickable(false);
                    lockMachineDialog.setAllCancelable(false);
                    lockMachineDialog.show();
                    str = this.TAG;
                    Pdlog.m3273d(str, "LockMachineDialog.show()");
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }
}
