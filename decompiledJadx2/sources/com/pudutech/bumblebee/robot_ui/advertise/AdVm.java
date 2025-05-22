package com.pudutech.bumblebee.robot_ui.advertise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.bumblebee.robot_ui.viewmodel.VMExtKt;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import kotlin.Metadata;

/* compiled from: AdVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdVm;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "_controlAdPlay", "Landroidx/lifecycle/MutableLiveData;", "", "_controlAdShow", "controlAdPlay", "Landroidx/lifecycle/LiveData;", "getControlAdPlay", "()Landroidx/lifecycle/LiveData;", "controlAdShow", "getControlAdShow", "", "play", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AdVm extends BaseViewModel {
    private final MutableLiveData<Boolean> _controlAdPlay = new MutableLiveData<>();
    private final LiveData<Boolean> controlAdPlay = VMExtKt.toLiveData(this._controlAdPlay);
    private final MutableLiveData<Boolean> _controlAdShow = new MutableLiveData<>();
    private final LiveData<Boolean> controlAdShow = VMExtKt.toLiveData(this._controlAdShow);

    public final LiveData<Boolean> getControlAdPlay() {
        return this.controlAdPlay;
    }

    public final LiveData<Boolean> getControlAdShow() {
        return this.controlAdShow;
    }

    public final void controlAdPlay(boolean play) {
        this._controlAdPlay.postValue(Boolean.valueOf(play));
    }

    public final void controlAdShow(boolean play) {
        this._controlAdShow.postValue(Boolean.valueOf(play));
    }
}
