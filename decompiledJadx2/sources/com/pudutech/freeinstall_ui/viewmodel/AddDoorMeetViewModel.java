package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AddDoorMeetViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/AddDoorMeetViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "staticMapLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getStaticMapLiveData", "()Landroidx/lifecycle/MutableLiveData;", "getStaticMap", "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AddDoorMeetViewModel extends BaseViewModel {
    private final MutableLiveData<String> staticMapLiveData = new MutableLiveData<>();

    public final MutableLiveData<String> getStaticMapLiveData() {
        return this.staticMapLiveData;
    }

    public final void getStaticMap() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddDoorMeetViewModel$getStaticMap$1(this, null), 3, null);
    }
}
