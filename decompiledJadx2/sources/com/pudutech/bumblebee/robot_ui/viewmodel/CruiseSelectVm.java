package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.pudutech.bumblebee.robot_ui.advertise.AdvertiseVm;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import kotlin.Metadata;

/* compiled from: CruiseSelectVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/CruiseSelectVm;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "mAdverVm", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdvertiseVm;", "getMAdverVm", "()Lcom/pudutech/bumblebee/robot_ui/advertise/AdvertiseVm;", "onCleared", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruiseSelectVm extends BaseViewModel {
    private final AdvertiseVm mAdverVm = new AdvertiseVm(ViewModelKt.getViewModelScope(this));

    public final AdvertiseVm getMAdverVm() {
        return this.mAdverVm;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        this.mAdverVm.onCleared();
    }
}
