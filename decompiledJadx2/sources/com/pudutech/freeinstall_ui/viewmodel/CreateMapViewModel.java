package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: CreateMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012J\u0006\u0010\u0016\u001a\u00020\u0012J\u0006\u0010\u0017\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/CreateMapViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "chargeTimeLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getChargeTimeLiveData", "()Landroidx/lifecycle/MutableLiveData;", "checkMarkerLiveData", "", "getCheckMarkerLiveData", "isIdentify", "()Z", "setIdentify", "(Z)V", "cancelMap", "", "checkBeginMappingMarkerVisible", "closeCheck", "getChargeTime", "reInitModules", "reinitAlgoModules", "setExtendMap", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CreateMapViewModel extends BaseViewModel {
    private boolean isIdentify;
    private final String TAG = "CreateMapViewModel";
    private final MutableLiveData<Boolean> checkMarkerLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> chargeTimeLiveData = new MutableLiveData<>();

    public final MutableLiveData<Boolean> getCheckMarkerLiveData() {
        return this.checkMarkerLiveData;
    }

    public final MutableLiveData<Integer> getChargeTimeLiveData() {
        return this.chargeTimeLiveData;
    }

    /* renamed from: isIdentify, reason: from getter */
    public final boolean getIsIdentify() {
        return this.isIdentify;
    }

    public final void setIdentify(boolean z) {
        this.isIdentify = z;
    }

    public final void checkBeginMappingMarkerVisible() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new CreateMapViewModel$checkBeginMappingMarkerVisible$1(this, null), 2, null);
    }

    public final void cancelMap() {
        Pdlog.m3273d(this.TAG, "cancelMap");
        closeCheck();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CreateMapViewModel$cancelMap$1(null), 3, null);
    }

    public final void closeCheck() {
        Pdlog.m3273d(this.TAG, "closeCheck");
        this.isIdentify = false;
    }

    public final void reInitModules() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CreateMapViewModel$reInitModules$1(null), 3, null);
    }

    public final void setExtendMap() {
        LocateMappingManager.INSTANCE.initExtendDrawing();
    }

    public final void reinitAlgoModules() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CreateMapViewModel$reinitAlgoModules$1(null), 3, null);
    }

    public final void getChargeTime() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CreateMapViewModel$getChargeTime$1(this, null), 3, null);
    }
}
