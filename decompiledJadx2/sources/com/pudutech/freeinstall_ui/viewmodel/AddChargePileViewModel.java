package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AddChargePileViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/AddChargePileViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "identityChargeLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/mirsdk/mircore/coreparcel/DockerDetectResult;", "getIdentityChargeLiveData", "()Landroidx/lifecycle/MutableLiveData;", "staticMapLiveData", "", "getStaticMapLiveData", "getStaticMap", "", "identityChargePile", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AddChargePileViewModel extends BaseViewModel {
    private final MutableLiveData<DockerDetectResult> identityChargeLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> staticMapLiveData = new MutableLiveData<>();

    public final MutableLiveData<DockerDetectResult> getIdentityChargeLiveData() {
        return this.identityChargeLiveData;
    }

    public final MutableLiveData<String> getStaticMapLiveData() {
        return this.staticMapLiveData;
    }

    public final void getStaticMap() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddChargePileViewModel$getStaticMap$1(this, null), 3, null);
    }

    public final void identityChargePile() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddChargePileViewModel$identityChargePile$1(this, null), 3, null);
    }
}
