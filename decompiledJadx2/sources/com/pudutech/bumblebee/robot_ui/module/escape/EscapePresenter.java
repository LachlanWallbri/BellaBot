package com.pudutech.bumblebee.robot_ui.module.escape;

import com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EscapePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0012H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapePresenter;", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IViewDelegate;", "()V", "delegate", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IInteractor;", "getDelegate", "()Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IInteractor;", "setDelegate", "(Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IInteractor;)V", "view", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IView;", "getView", "()Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IView;", "setView", "(Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IView;)V", "didCheckEscape", "", "hasEscape", "", "didCheckMapHasNew", "hasNew", "didCheckMapVersion", "needUpload", "didCheckNetwork", "available", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EscapePresenter implements EscapeModule.IViewDelegate {
    public EscapeModule.IInteractor delegate;
    public EscapeModule.IView view;

    public final EscapeModule.IView getView() {
        EscapeModule.IView iView = this.view;
        if (iView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        return iView;
    }

    public final void setView(EscapeModule.IView iView) {
        Intrinsics.checkParameterIsNotNull(iView, "<set-?>");
        this.view = iView;
    }

    public final EscapeModule.IInteractor getDelegate() {
        EscapeModule.IInteractor iInteractor = this.delegate;
        if (iInteractor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delegate");
        }
        return iInteractor;
    }

    public final void setDelegate(EscapeModule.IInteractor iInteractor) {
        Intrinsics.checkParameterIsNotNull(iInteractor, "<set-?>");
        this.delegate = iInteractor;
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IViewDelegate
    public void didCheckMapHasNew(boolean hasNew) {
        if (hasNew) {
            EscapeModule.IView iView = this.view;
            if (iView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            iView.showCheckNewMapView();
            return;
        }
        EscapeModule.IView iView2 = this.view;
        if (iView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        iView2.checkEnd();
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IViewDelegate
    public void didCheckEscape(boolean hasEscape) {
        if (hasEscape) {
            EscapeModule.IView iView = this.view;
            if (iView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            iView.showLockView();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IViewDelegate
    public void didCheckMapVersion(boolean needUpload) {
        if (needUpload) {
            EscapeModule.IView iView = this.view;
            if (iView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            iView.showSyncMapView();
            return;
        }
        EscapeModule.IView iView2 = this.view;
        if (iView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        iView2.showSyncedMapView();
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IViewDelegate
    public void didCheckNetwork(boolean available) {
        if (available) {
            EscapeModule.IView iView = this.view;
            if (iView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            iView.checkNetworkNext();
            return;
        }
        EscapeModule.IView iView2 = this.view;
        if (iView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        iView2.showDisNetworkView();
    }
}
