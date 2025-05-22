package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.ext.util.StringExtKt;
import com.pudutech.freeinstall_ui.adapter.ChargeListItem;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.bean.CruiseDestination;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.CruiseTracks;
import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MapSelectViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tJ\u001c\u0010\u000e\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0006R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/MapSelectViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "isEdit", "Landroidx/lifecycle/MutableLiveData;", "", "()Landroidx/lifecycle/MutableLiveData;", "mapListData", "", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "getMapListData", "", "jumpMap", "item", "setMapData", "analyMap", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "userMap", "name", "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MapSelectViewModel extends BaseViewModel {
    private final MutableLiveData<List<MapInfo>> mapListData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isEdit = new MutableLiveData<>();

    public final MutableLiveData<List<MapInfo>> getMapListData() {
        return this.mapListData;
    }

    public final MutableLiveData<Boolean> isEdit() {
        return this.isEdit;
    }

    /* renamed from: getMapListData, reason: collision with other method in class */
    public final void m4340getMapListData() {
        this.mapListData.postValue(MapUpdateManager.INSTANCE.getLocalMapList());
    }

    public final void jumpMap(MapInfo item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MapSelectViewModel$jumpMap$1(this, item, null), 2, null);
    }

    public final void setMapData(List<Destination> analyMap, MapInfo item) {
        Vector3d vector;
        Intrinsics.checkParameterIsNotNull(analyMap, "analyMap");
        Intrinsics.checkParameterIsNotNull(item, "item");
        SpDataUtils.INSTANCE.saveMapName(item.getMapName());
        int i = 1;
        Pdlog.m3273d("MapListViewModel", "saveMapName " + item.getMapName());
        SpDataUtils.INSTANCE.saveMapLevel(item.getMapVersion());
        Pdlog.m3273d("MapListViewModel", "saveMapLevel " + item.getMapVersion());
        List<DockerResult> dockerChargeList = MapingFuntionManager.INSTANCE.getDockerChargeList();
        char c = ' ';
        if (dockerChargeList != null) {
            ArrayList arrayList = new ArrayList();
            for (DockerResult dockerResult : dockerChargeList) {
                Pdlog.m3273d("MapListViewModel", "dockerChargeList " + dockerResult.getName() + ' ' + dockerResult.getDocker().getX());
                arrayList.add(new ChargeListItem(dockerResult, false, false, 6, null));
            }
            SpDataUtils.INSTANCE.saveAddChargePile(arrayList);
        }
        List<CruiseTracks> cruiseTracksList = MapingFuntionManager.INSTANCE.getCruiseTracksList();
        if (cruiseTracksList != null) {
            ArrayList arrayList2 = new ArrayList();
            for (CruiseTracks cruiseTracks : cruiseTracksList) {
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("cruiseTracksList ");
                sb.append(cruiseTracks.getName());
                sb.append(' ');
                sb.append(cruiseTracks.getTracks());
                sb.append(" size is ");
                List<Vector3d> tracks = cruiseTracks.getTracks();
                sb.append((tracks != null ? Integer.valueOf(tracks.size()) : null).intValue());
                objArr[0] = sb.toString();
                Pdlog.m3273d("MapListViewModel", objArr);
                Pdlog.m3273d("MapListViewModel", StringExtKt.toJson(cruiseTracks.getTracks()));
                arrayList2.add(new CruiseListItem(new CruiseDestination(cruiseTracks.getName(), cruiseTracks.getTracks()), false, false, 6, null));
            }
            SpDataUtils.INSTANCE.saveAddCruisePath(arrayList2);
        }
        List<Destination> list = analyMap;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : list) {
            if (Intrinsics.areEqual(((Destination) obj).getMode(), Constants.POINT_TYPE_DOOR)) {
                arrayList3.add(obj);
            }
        }
        ArrayList<Destination> arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList();
        for (Object obj2 : list) {
            if (Intrinsics.areEqual(((Destination) obj2).getMode(), "parking")) {
                arrayList5.add(obj2);
            }
        }
        ArrayList<Destination> arrayList6 = arrayList5;
        ArrayList arrayList7 = new ArrayList();
        for (Object obj3 : list) {
            if (Intrinsics.areEqual(((Destination) obj3).getMode(), Constants.POINT_TYPE_TABLE)) {
                arrayList7.add(obj3);
            }
        }
        ArrayList<Destination> arrayList8 = arrayList7;
        ArrayList arrayList9 = new ArrayList();
        for (Object obj4 : list) {
            if (Intrinsics.areEqual(((Destination) obj4).getMode(), "dining_outlet")) {
                arrayList9.add(obj4);
            }
        }
        ArrayList<Destination> arrayList10 = arrayList9;
        ArrayList arrayList11 = new ArrayList();
        for (Destination destination : arrayList4) {
            Pdlog.m3273d("doors", destination.getName() + c + destination.getVector().getX());
            arrayList11.add(new TableListItem(destination, false, false, 6, null));
            c = ' ';
        }
        SpDataUtils.INSTANCE.saveAddDoorPoint(arrayList11);
        ArrayList arrayList12 = new ArrayList();
        for (Destination destination2 : arrayList6) {
            Object[] objArr2 = new Object[i];
            objArr2[0] = destination2.getName() + ' ' + destination2.getVector().getX();
            Pdlog.m3273d("parking", objArr2);
            arrayList12.add(new TableListItem(destination2, false, false, 6, null));
            i = 1;
        }
        SpDataUtils.INSTANCE.saveAddStation(arrayList12);
        ArrayList arrayList13 = new ArrayList();
        for (Destination destination3 : arrayList8) {
            Pdlog.m3273d(Constants.POINT_TYPE_TABLE, destination3.getName() + ' ' + destination3.getVector().getX());
            arrayList13.add(new TableListItem(destination3, false, false, 6, null));
        }
        SpDataUtils.INSTANCE.saveAddTable(arrayList13);
        ArrayList arrayList14 = new ArrayList();
        for (Destination destination4 : arrayList10) {
            Pdlog.m3273d("takeMeal", destination4.getName() + ' ' + destination4.getVector().getX());
            arrayList14.add(new TableListItem(destination4, false, false, 6, null));
        }
        SpDataUtils.INSTANCE.saveAddMeal(arrayList14);
        SpDataUtils.Companion companion = SpDataUtils.INSTANCE;
        CameraType cameraType = LocateMappingManager.INSTANCE.getCameraType();
        if (cameraType == null) {
            cameraType = CameraType.MARKER_CAMERA;
        }
        companion.saveCameraType(cameraType);
        Destination mapInitPoint = LocateMappingManager.INSTANCE.getMapInitPoint(item.getMapName());
        Object[] objArr3 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("---mapStartPoint");
        sb2.append((mapInitPoint == null || (vector = mapInitPoint.getVector()) == null) ? null : Double.valueOf(vector.getX()));
        objArr3[0] = sb2.toString();
        Pdlog.m3273d("MapListViewModel", objArr3);
        SpDataUtils.INSTANCE.saveMapStartPoint(mapInitPoint);
        this.isEdit.postValue(true);
    }

    public final void userMap(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MapSelectViewModel$userMap$1(name, null), 3, null);
    }
}
