package com.pudutech.bumblebee.robot_ui.module.escape;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule;
import kotlin.Metadata;
import kotlin.Unit;

/* compiled from: EscapeView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0015\u001a\u00020\u0006H\u0016J\b\u0010\u0016\u001a\u00020\u0006H\u0016J\b\u0010\u0017\u001a\u00020\u0006H\u0016J\b\u0010\u0018\u001a\u00020\u0006H\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\b\u0010\u001a\u001a\u00020\u0006H\u0016J\b\u0010\u001b\u001a\u00020\u0006H\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeView;", "Landroidx/lifecycle/ViewModel;", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IView;", "()V", "checkEndLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getCheckEndLiveData", "()Landroidx/lifecycle/MutableLiveData;", "checkNetworkEndLiveData", "getCheckNetworkEndLiveData", "needSyncMapLiveData", "getNeedSyncMapLiveData", "newMapLiveData", "getNewMapLiveData", "notNetworkLiveData", "getNotNetworkLiveData", "robotLockLiveData", "getRobotLockLiveData", "syncedMapLiveData", "getSyncedMapLiveData", "checkEnd", "checkNetworkNext", "showCheckNewMapView", "showDisNetworkView", "showLockView", "showSyncMapView", "showSyncedMapView", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EscapeView extends ViewModel implements EscapeModule.IView {
    private final MutableLiveData<Unit> newMapLiveData = new MutableLiveData<>();
    private final MutableLiveData<Unit> checkEndLiveData = new MutableLiveData<>();
    private final MutableLiveData<Unit> robotLockLiveData = new MutableLiveData<>();
    private final MutableLiveData<Unit> needSyncMapLiveData = new MutableLiveData<>();
    private final MutableLiveData<Unit> syncedMapLiveData = new MutableLiveData<>();
    private final MutableLiveData<Unit> checkNetworkEndLiveData = new MutableLiveData<>();
    private final MutableLiveData<Unit> notNetworkLiveData = new MutableLiveData<>();

    public final MutableLiveData<Unit> getNewMapLiveData() {
        return this.newMapLiveData;
    }

    public final MutableLiveData<Unit> getCheckEndLiveData() {
        return this.checkEndLiveData;
    }

    public final MutableLiveData<Unit> getRobotLockLiveData() {
        return this.robotLockLiveData;
    }

    public final MutableLiveData<Unit> getNeedSyncMapLiveData() {
        return this.needSyncMapLiveData;
    }

    public final MutableLiveData<Unit> getSyncedMapLiveData() {
        return this.syncedMapLiveData;
    }

    public final MutableLiveData<Unit> getCheckNetworkEndLiveData() {
        return this.checkNetworkEndLiveData;
    }

    public final MutableLiveData<Unit> getNotNetworkLiveData() {
        return this.notNetworkLiveData;
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IView
    public void showCheckNewMapView() {
        this.newMapLiveData.postValue(null);
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IView
    public void showLockView() {
        this.robotLockLiveData.postValue(null);
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IView
    public void showSyncMapView() {
        this.needSyncMapLiveData.postValue(null);
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IView
    public void showSyncedMapView() {
        this.syncedMapLiveData.postValue(null);
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IView
    public void showDisNetworkView() {
        this.notNetworkLiveData.postValue(null);
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IView
    public void checkEnd() {
        this.checkEndLiveData.postValue(null);
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IView
    public void checkNetworkNext() {
        this.checkNetworkEndLiveData.postValue(null);
    }
}
