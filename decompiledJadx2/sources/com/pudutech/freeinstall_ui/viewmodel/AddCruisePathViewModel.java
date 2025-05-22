package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AddCruisePathViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0006\u0010\u0010\u001a\u00020\fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/AddCruisePathViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "addPathLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getAddPathLiveData", "()Landroidx/lifecycle/MutableLiveData;", "staticMapLiveData", "", "getStaticMapLiveData", "addPath", "", "tableList", "", "Lcom/pudutech/freeinstall_ui/adapter/CruiseListItem;", "getStaticMap", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AddCruisePathViewModel extends BaseViewModel {
    private final MutableLiveData<Boolean> addPathLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> staticMapLiveData = new MutableLiveData<>();

    public final MutableLiveData<Boolean> getAddPathLiveData() {
        return this.addPathLiveData;
    }

    public final MutableLiveData<String> getStaticMapLiveData() {
        return this.staticMapLiveData;
    }

    public final void getStaticMap() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddCruisePathViewModel$getStaticMap$1(this, null), 3, null);
    }

    public final void addPath(List<CruiseListItem> tableList) {
        Intrinsics.checkParameterIsNotNull(tableList, "tableList");
        for (CruiseListItem cruiseListItem : tableList) {
            cruiseListItem.setDelete(false);
            cruiseListItem.setSelect(false);
        }
        SpDataUtils.INSTANCE.saveAddCruisePath(tableList);
        this.addPathLiveData.postValue(true);
    }
}
