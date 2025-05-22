package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.ext.util.StringExtKt;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.adapter.ChargeListItem;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.bean.CruiseDestination;
import com.pudutech.freeinstall_ui.bean.VirtualItemBean;
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
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MapListViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J3\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042#\u0010\u001a\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00180\u001bJ)\u0010\u001e\u001a\u00020\u00182!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00180\u001bJ\u0016\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0004J\u0006\u0010!\u001a\u00020\u0018J\u0006\u0010\u0014\u001a\u00020\u0018J\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\bJ'\u0010#\u001a\u00020\u00182\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010 \u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010'J\u000e\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u001c\u0010\r\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/MapListViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "data", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "getData", "()Landroidx/lifecycle/MutableLiveData;", "isCopySuccess", "", "isCopyValue", "()Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "setCopyValue", "(Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;)V", "isEdit", "isReload", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "isShouldUpdate", "toast", "getToast", "checkCode", "", "code", SpeechUtility.TAG_RESOURCE_RESULT, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "checkEditAuth", "copyData", "item", "getMapList", "jumpMap", "setMapData", "analyMap", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "(Ljava/util/List;Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadResult", "isSuccess", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MapListViewModel extends BaseViewModel {
    private MapInfo isCopyValue;
    private final String TAG = "MapListViewModel";
    private final MutableLiveData<List<MapInfo>> data = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isShouldUpdate = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isEdit = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isCopySuccess = new MutableLiveData<>();
    private final MutableLiveData<RobotMapResp> isReload = new MutableLiveData<>();
    private final MutableLiveData<String> toast = new MutableLiveData<>();

    public final MutableLiveData<List<MapInfo>> getData() {
        return this.data;
    }

    public final MutableLiveData<Boolean> isShouldUpdate() {
        return this.isShouldUpdate;
    }

    public final MutableLiveData<Boolean> isEdit() {
        return this.isEdit;
    }

    public final MutableLiveData<Boolean> isCopySuccess() {
        return this.isCopySuccess;
    }

    /* renamed from: isCopyValue, reason: from getter */
    public final MapInfo getIsCopyValue() {
        return this.isCopyValue;
    }

    public final void setCopyValue(MapInfo mapInfo) {
        this.isCopyValue = mapInfo;
    }

    public final MutableLiveData<RobotMapResp> isReload() {
        return this.isReload;
    }

    public final MutableLiveData<String> getToast() {
        return this.toast;
    }

    public final void copyData(MapInfo item, String name) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(name, "name");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MapListViewModel$copyData$1(this, item, name, null), 2, null);
    }

    public final void uploadResult(boolean isSuccess) {
        if (isSuccess && this.isCopyValue != null) {
            this.isCopySuccess.postValue(true);
            List<MapInfo> value = this.data.getValue();
            if (value != null) {
                MapInfo mapInfo = this.isCopyValue;
                if (mapInfo == null) {
                    Intrinsics.throwNpe();
                }
                value.add(mapInfo);
            }
            MutableLiveData<List<MapInfo>> mutableLiveData = this.data;
            mutableLiveData.postValue(mutableLiveData.getValue());
            return;
        }
        this.isCopySuccess.postValue(false);
    }

    public final void checkCode(String code, Function1<? super String, Unit> result) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        Intrinsics.checkParameterIsNotNull(result, "result");
        try {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MapListViewModel$checkCode$1(code, result, null), 2, null);
        } catch (Exception e) {
            Pdlog.m3274e("viewModel", e.getMessage());
        }
    }

    public final void checkEditAuth(Function1<? super Boolean, Unit> result) {
        Intrinsics.checkParameterIsNotNull(result, "result");
        try {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MapListViewModel$checkEditAuth$1(this, result, null), 2, null);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e.getMessage());
            result.invoke(false);
        }
    }

    /* renamed from: isShouldUpdate, reason: collision with other method in class */
    public final void m4339isShouldUpdate() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MapListViewModel$isShouldUpdate$1(this, null), 2, null);
    }

    public final void getMapList() {
        this.data.postValue(MapUpdateManager.INSTANCE.getLocalMapList());
    }

    public final Object setMapData(List<Destination> list, MapInfo mapInfo, Continuation<? super Unit> continuation) {
        Vector3d vector;
        SpDataUtils.INSTANCE.saveMapName(mapInfo.getMapName());
        Pdlog.m3273d("MapListViewModel", "saveMapName " + mapInfo.getMapName());
        SpDataUtils.INSTANCE.saveMapLevel(mapInfo.getMapVersion());
        Pdlog.m3273d("MapListViewModel", "saveMapLevel " + mapInfo.getMapVersion());
        List<DockerResult> dockerChargeList = MapingFuntionManager.INSTANCE.getDockerChargeList();
        char c = ' ';
        if (dockerChargeList != null) {
            ArrayList arrayList = new ArrayList();
            for (DockerResult dockerResult : dockerChargeList) {
                Pdlog.m3273d("MapListViewModel", "dockerChargeList " + dockerResult.getName() + ' ' + dockerResult.getDocker().getX());
                arrayList.add(new ChargeListItem(dockerResult, false, false, 6, null));
            }
            SpDataUtils.INSTANCE.saveAddChargePile(arrayList);
            Unit unit = Unit.INSTANCE;
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
                sb.append((tracks != null ? Boxing.boxInt(tracks.size()) : null).intValue());
                objArr[0] = sb.toString();
                Pdlog.m3273d("MapListViewModel", objArr);
                Pdlog.m3273d("MapListViewModel", StringExtKt.toJson(cruiseTracks.getTracks()));
                arrayList2.add(new CruiseListItem(new CruiseDestination(cruiseTracks.getName(), cruiseTracks.getTracks()), false, false, 6, null));
            }
            SpDataUtils.INSTANCE.saveAddCruisePath(arrayList2);
            Unit unit2 = Unit.INSTANCE;
        }
        List<Destination> list2 = list;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : list2) {
            if (Boxing.boxBoolean(Intrinsics.areEqual(((Destination) obj).getMode(), Constants.POINT_TYPE_DOOR)).booleanValue()) {
                arrayList3.add(obj);
            }
        }
        ArrayList arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList();
        for (Object obj2 : list2) {
            if (Boxing.boxBoolean(Intrinsics.areEqual(((Destination) obj2).getMode(), "parking")).booleanValue()) {
                arrayList5.add(obj2);
            }
        }
        ArrayList arrayList6 = arrayList5;
        ArrayList arrayList7 = new ArrayList();
        for (Object obj3 : list2) {
            if (Boxing.boxBoolean(Intrinsics.areEqual(((Destination) obj3).getMode(), Constants.POINT_TYPE_TABLE)).booleanValue()) {
                arrayList7.add(obj3);
            }
        }
        ArrayList<Destination> arrayList8 = arrayList7;
        ArrayList arrayList9 = new ArrayList();
        for (Object obj4 : list2) {
            if (Boxing.boxBoolean(Intrinsics.areEqual(((Destination) obj4).getMode(), "dining_outlet")).booleanValue()) {
                arrayList9.add(obj4);
            }
        }
        ArrayList<Destination> arrayList10 = arrayList9;
        ArrayList arrayList11 = new ArrayList();
        for (Iterator it = arrayList4.iterator(); it.hasNext(); it = it) {
            Destination destination = (Destination) it.next();
            Pdlog.m3273d("doors", destination.getName() + ' ' + destination.getVector().getX());
            arrayList11.add(new TableListItem(destination, false, false, 6, null));
        }
        SpDataUtils.INSTANCE.saveAddDoorPoint(arrayList11);
        Unit unit3 = Unit.INSTANCE;
        ArrayList arrayList12 = new ArrayList();
        Iterator it2 = arrayList6.iterator();
        while (it2.hasNext()) {
            Destination destination2 = (Destination) it2.next();
            Pdlog.m3273d("parking", destination2.getName() + c + destination2.getVector().getX());
            arrayList12.add(new TableListItem(destination2, false, false, 6, null));
            it2 = it2;
            c = ' ';
        }
        SpDataUtils.INSTANCE.saveAddStation(arrayList12);
        Unit unit4 = Unit.INSTANCE;
        ArrayList arrayList13 = new ArrayList();
        for (Destination destination3 : arrayList8) {
            Pdlog.m3273d(Constants.POINT_TYPE_TABLE, destination3.getName() + ' ' + destination3.getVector().getX());
            arrayList13.add(new TableListItem(destination3, false, false, 6, null));
        }
        SpDataUtils.INSTANCE.saveAddTable(arrayList13);
        Unit unit5 = Unit.INSTANCE;
        ArrayList arrayList14 = new ArrayList();
        for (Destination destination4 : arrayList10) {
            Pdlog.m3273d("takeMeal", destination4.getName() + ' ' + destination4.getVector().getX());
            arrayList14.add(new TableListItem(destination4, false, false, 6, null));
        }
        SpDataUtils.INSTANCE.saveAddMeal(arrayList14);
        Unit unit6 = Unit.INSTANCE;
        SpDataUtils.Companion companion = SpDataUtils.INSTANCE;
        CameraType cameraType = LocateMappingManager.INSTANCE.getCameraType();
        if (cameraType == null) {
            cameraType = CameraType.MARKER_CAMERA;
        }
        companion.saveCameraType(cameraType);
        Destination mapInitPoint = LocateMappingManager.INSTANCE.getMapInitPoint(mapInfo.getMapName());
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("---mapStartPoint");
        sb2.append((mapInitPoint == null || (vector = mapInitPoint.getVector()) == null) ? null : Boxing.boxDouble(vector.getX()));
        objArr2[0] = sb2.toString();
        Pdlog.m3273d("MapListViewModel", objArr2);
        SpDataUtils.INSTANCE.saveMapStartPoint(mapInitPoint);
        List<Vector3d> virtualWallList = LocateMappingManager.INSTANCE.getVirtualWallList();
        Object[] objArr3 = new Object[1];
        objArr3[0] = virtualWallList != null ? Boxing.boxInt(virtualWallList.size()) : null;
        Pdlog.m3273d("MapListViewModel", objArr3);
        if (virtualWallList == null) {
            SpDataUtils.INSTANCE.saveVirtual(new ArrayList());
        } else {
            ArrayList arrayList15 = new ArrayList();
            int size = virtualWallList.size() / 2;
            for (int i = 0; i < size; i++) {
                ArrayList arrayList16 = new ArrayList();
                int i2 = i * 2;
                arrayList16.add(virtualWallList.get(i2));
                arrayList16.add(virtualWallList.get(i2 + 1));
                arrayList15.add(new VirtualItemBean(arrayList16, AppContext.INSTANCE.getContext().getString(C5362R.string.title_virtual) + i, false, 4, null));
            }
            SpDataUtils.INSTANCE.saveVirtual(arrayList15);
        }
        this.isEdit.postValue(Boxing.boxBoolean(true));
        return Unit.INSTANCE;
    }

    public final void jumpMap(MapInfo item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MapListViewModel$jumpMap$1(this, item, null), 2, null);
    }
}
