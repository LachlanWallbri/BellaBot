package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.adapter.DoublePathListItem;
import com.pudutech.freeinstall_ui.bean.VirtualItemBean;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.opengl_draw.bean.Line;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AddVirtualViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\u0014\u0010\u0019\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014J\u0006\u0010\u001c\u001a\u00020\u0018J\u0006\u0010\u001d\u001a\u00020\u0018J\u0006\u0010\u001e\u001a\u00020\u0018J\u0014\u0010\u001f\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020!0 J\u0014\u0010\"\u001a\u00020\u00182\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0 R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u001f\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00140\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000b¨\u0006%"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/AddVirtualViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "addPathLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getAddPathLiveData", "()Landroidx/lifecycle/MutableLiveData;", "checkVirtualLiveData", "", "getCheckVirtualLiveData", "doublePathLiveData", "getDoublePathLiveData", "staticMapLiveData", "getStaticMapLiveData", "topoMapLiveData", "", "Lcom/pudutech/opengl_draw/bean/Line;", "getTopoMapLiveData", "addPath", "", "checkVirtualWall", "list", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getDoublePath", "getStaticMap", "getTopoMap", "saveVirtualWall", "", "Lcom/pudutech/freeinstall_ui/bean/VirtualItemBean;", "setDoublePath", "listItems", "Lcom/pudutech/freeinstall_ui/adapter/DoublePathListItem;", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AddVirtualViewModel extends BaseViewModel {
    private final String TAG = "AddVirtualViewModel";
    private final MutableLiveData<String> staticMapLiveData = new MutableLiveData<>();
    private final MutableLiveData<boolean[]> checkVirtualLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Line>> topoMapLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> doublePathLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> addPathLiveData = new MutableLiveData<>();

    public final String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<String> getStaticMapLiveData() {
        return this.staticMapLiveData;
    }

    public final MutableLiveData<boolean[]> getCheckVirtualLiveData() {
        return this.checkVirtualLiveData;
    }

    public final MutableLiveData<List<Line>> getTopoMapLiveData() {
        return this.topoMapLiveData;
    }

    public final MutableLiveData<Boolean> getDoublePathLiveData() {
        return this.doublePathLiveData;
    }

    public final MutableLiveData<Boolean> getAddPathLiveData() {
        return this.addPathLiveData;
    }

    public final void getStaticMap() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddVirtualViewModel$getStaticMap$1(this, null), 3, null);
    }

    public final void checkVirtualWall(List<Vector3d> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddVirtualViewModel$checkVirtualWall$1(this, list, null), 3, null);
    }

    public final void saveVirtualWall(List<VirtualItemBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddVirtualViewModel$saveVirtualWall$1(this, list, null), 3, null);
    }

    public final void getTopoMap() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddVirtualViewModel$getTopoMap$1(this, null), 3, null);
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
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddVirtualViewModel$setDoublePath$1(this, listItems, null), 3, null);
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
