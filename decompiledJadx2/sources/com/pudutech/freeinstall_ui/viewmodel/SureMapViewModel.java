package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.adapter.DoublePathListItem;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: SureMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0014\u0010\u000f\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/SureMapViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "addPathLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getAddPathLiveData", "()Landroidx/lifecycle/MutableLiveData;", "doublePathLiveData", "getDoublePathLiveData", "addPath", "", "getDoublePath", "setDoublePath", "listItems", "", "Lcom/pudutech/freeinstall_ui/adapter/DoublePathListItem;", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SureMapViewModel extends BaseViewModel {
    private final String TAG = "SureMapViewModel";
    private final MutableLiveData<Boolean> doublePathLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> addPathLiveData = new MutableLiveData<>();

    public final MutableLiveData<Boolean> getDoublePathLiveData() {
        return this.doublePathLiveData;
    }

    public final MutableLiveData<Boolean> getAddPathLiveData() {
        return this.addPathLiveData;
    }

    public final void getDoublePath() {
        ArrayList doubleRoadTracks = LocateMappingManager.INSTANCE.getDoubleRoadTracks();
        if (doubleRoadTracks == null) {
            doubleRoadTracks = new ArrayList();
        }
        Pdlog.m3273d(this.TAG, "getDoublePath " + doubleRoadTracks.size());
        this.doublePathLiveData.postValue(Boolean.valueOf(doubleRoadTracks.size() != 0));
    }

    public final void setDoublePath(List<DoublePathListItem> listItems) {
        Intrinsics.checkParameterIsNotNull(listItems, "listItems");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SureMapViewModel$setDoublePath$1(this, listItems, null), 3, null);
    }

    public final void addPath() {
        List<CruiseListItem> addCruisePath = SpDataUtils.INSTANCE.getAddCruisePath();
        ArrayList arrayList = new ArrayList();
        if (addCruisePath != null) {
            for (CruiseListItem cruiseListItem : addCruisePath) {
                List<Vector3d> createCruise = LocateMappingManager.INSTANCE.createCruise(cruiseListItem.getDestination().getId(), cruiseListItem.getDestination().getVector3s());
                if (createCruise != null) {
                    arrayList.add(createCruise);
                }
            }
        }
        Pdlog.m3273d(this.TAG, "saveCruisePathBack" + arrayList);
        SpDataUtils.INSTANCE.saveCruisePathBack(arrayList);
        Integer mapLevel = SpDataUtils.INSTANCE.getMapLevel();
        int intValue = mapLevel == null ? 1 : mapLevel.intValue() + 1;
        LocateMappingManager locateMappingManager = LocateMappingManager.INSTANCE;
        String mapName = SpDataUtils.INSTANCE.getMapName();
        if (mapName == null) {
            mapName = "";
        }
        locateMappingManager.updateMapVersion(mapName, intValue);
        LocateMappingManager.INSTANCE.saveTopMap();
        this.addPathLiveData.postValue(true);
    }
}
