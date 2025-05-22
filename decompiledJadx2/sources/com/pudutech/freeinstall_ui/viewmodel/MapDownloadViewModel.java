package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
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
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.CruiseTracks;
import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: MapDownloadViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\u0012\u001a\u00020\u0010J\u001c\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00052\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0006J'\u0010\u0018\u001a\u00020\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0015\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001cR\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/MapDownloadViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "data", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "getData", "()Landroidx/lifecycle/MutableLiveData;", "isEdit", "", "isReload", "", "toast", "getToast", "download", "", "it1", "getMapListData", "getTempFileList", "jumpMap", "item", "reloadMap", "robotMapResp", "setMapData", "analyMap", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "(Ljava/util/List;Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MapDownloadViewModel extends BaseViewModel {
    private final MutableLiveData<List<RobotMapResp>> data = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isEdit = new MutableLiveData<>();
    private final MutableLiveData<String> isReload = new MutableLiveData<>();
    private final MutableLiveData<String> toast = new MutableLiveData<>();

    public final MutableLiveData<List<RobotMapResp>> getData() {
        return this.data;
    }

    public final MutableLiveData<Boolean> isEdit() {
        return this.isEdit;
    }

    public final MutableLiveData<String> isReload() {
        return this.isReload;
    }

    public final MutableLiveData<String> getToast() {
        return this.toast;
    }

    public final void getMapListData() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MapDownloadViewModel$getMapListData$1(this, null), 3, null);
    }

    public final List<String> getTempFileList(List<RobotMapResp> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (data.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (RobotMapResp robotMapResp : data) {
            if (FileUtils.isFileExists(MapUpdateManager.INSTANCE.getTempMapName(robotMapResp.getName()))) {
                arrayList.add(robotMapResp.getName());
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object setMapData(List<Destination> list, RobotMapResp robotMapResp, Continuation<? super Unit> continuation) {
        MapDownloadViewModel$setMapData$1 mapDownloadViewModel$setMapData$1;
        int i;
        Object obj;
        Vector3d vector;
        if (continuation instanceof MapDownloadViewModel$setMapData$1) {
            mapDownloadViewModel$setMapData$1 = (MapDownloadViewModel$setMapData$1) continuation;
            if ((mapDownloadViewModel$setMapData$1.label & Integer.MIN_VALUE) != 0) {
                mapDownloadViewModel$setMapData$1.label -= Integer.MIN_VALUE;
                Object obj2 = mapDownloadViewModel$setMapData$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mapDownloadViewModel$setMapData$1.label;
                int i2 = 1;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    SpDataUtils.INSTANCE.saveMapName(robotMapResp.getName());
                    Pdlog.m3273d("MapDownloadViewModel", "saveMapName " + robotMapResp.getName());
                    SpDataUtils.INSTANCE.saveMapLevel(robotMapResp.getLv());
                    Pdlog.m3273d("MapDownloadViewModel", "saveMapLevel " + robotMapResp.getLv());
                    List<DockerResult> dockerChargeList = MapingFuntionManager.INSTANCE.getDockerChargeList();
                    if (dockerChargeList != null) {
                        ArrayList arrayList = new ArrayList();
                        for (DockerResult dockerResult : dockerChargeList) {
                            Pdlog.m3273d("MapDownloadViewModel", "dockerChargeList " + dockerResult.getName() + ' ' + dockerResult.getDocker().getX());
                            arrayList.add(new ChargeListItem(dockerResult, false, false, 6, null));
                        }
                        SpDataUtils.INSTANCE.saveAddChargePile(arrayList);
                        Unit unit = Unit.INSTANCE;
                    }
                    List<CruiseTracks> cruiseTracksList = MapingFuntionManager.INSTANCE.getCruiseTracksList();
                    if (cruiseTracksList != null) {
                        ArrayList arrayList2 = new ArrayList();
                        for (CruiseTracks cruiseTracks : cruiseTracksList) {
                            Pdlog.m3273d("MapDownloadViewModel", "cruiseTracksList " + cruiseTracks.getName() + ' ' + cruiseTracks.getTracks() + " size is " + cruiseTracks.getTracks().size());
                            arrayList2.add(new CruiseListItem(new CruiseDestination(cruiseTracks.getName(), cruiseTracks.getTracks()), false, false, 6, null));
                        }
                        SpDataUtils.INSTANCE.saveAddCruisePath(arrayList2);
                        Unit unit2 = Unit.INSTANCE;
                    }
                    List<Destination> list2 = list;
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj3 : list2) {
                        if (Boxing.boxBoolean(Intrinsics.areEqual(((Destination) obj3).getMode(), Constants.POINT_TYPE_DOOR)).booleanValue()) {
                            arrayList3.add(obj3);
                        }
                    }
                    ArrayList<Destination> arrayList4 = arrayList3;
                    ArrayList arrayList5 = new ArrayList();
                    for (Object obj4 : list2) {
                        if (Boxing.boxBoolean(Intrinsics.areEqual(((Destination) obj4).getMode(), "parking")).booleanValue()) {
                            arrayList5.add(obj4);
                        }
                    }
                    ArrayList<Destination> arrayList6 = arrayList5;
                    ArrayList arrayList7 = new ArrayList();
                    for (Object obj5 : list2) {
                        if (Boxing.boxBoolean(Intrinsics.areEqual(((Destination) obj5).getMode(), Constants.POINT_TYPE_TABLE)).booleanValue()) {
                            arrayList7.add(obj5);
                        }
                    }
                    ArrayList<Destination> arrayList8 = arrayList7;
                    ArrayList arrayList9 = new ArrayList();
                    for (Object obj6 : list2) {
                        if (Boxing.boxBoolean(Intrinsics.areEqual(((Destination) obj6).getMode(), "dining_outlet")).booleanValue()) {
                            arrayList9.add(obj6);
                        }
                    }
                    ArrayList<Destination> arrayList10 = arrayList9;
                    ArrayList arrayList11 = new ArrayList();
                    for (Destination destination : arrayList4) {
                        Object[] objArr = new Object[i2];
                        objArr[0] = "doors " + destination.getName() + ' ' + destination.getVector().getX() + ' ';
                        Pdlog.m3273d("MapDownloadViewModel", objArr);
                        arrayList11.add(new TableListItem(destination, false, false, 6, null));
                        dockerChargeList = dockerChargeList;
                        i2 = 1;
                    }
                    List<DockerResult> list3 = dockerChargeList;
                    SpDataUtils.INSTANCE.saveAddDoorPoint(arrayList11);
                    Unit unit3 = Unit.INSTANCE;
                    ArrayList arrayList12 = new ArrayList();
                    for (Destination destination2 : arrayList6) {
                        Pdlog.m3273d("MapDownloadViewModel", "parking " + destination2.getName() + ' ' + destination2.getVector().getX() + ' ');
                        arrayList12.add(new TableListItem(destination2, false, false, 6, null));
                    }
                    SpDataUtils.INSTANCE.saveAddStation(arrayList12);
                    Unit unit4 = Unit.INSTANCE;
                    ArrayList arrayList13 = new ArrayList();
                    for (Destination destination3 : arrayList8) {
                        Pdlog.m3273d("MapDownloadViewModel", "table " + destination3.getName() + ' ' + destination3.getVector().getX() + ' ');
                        arrayList13.add(new TableListItem(destination3, false, false, 6, null));
                    }
                    SpDataUtils.INSTANCE.saveAddTable(arrayList13);
                    Unit unit5 = Unit.INSTANCE;
                    ArrayList arrayList14 = new ArrayList();
                    for (Destination destination4 : arrayList10) {
                        Pdlog.m3273d("MapDownloadViewModel", "takeMeal " + destination4.getName() + ' ' + destination4.getVector().getX() + ' ');
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
                    Destination mapInitPoint = LocateMappingManager.INSTANCE.getMapInitPoint(robotMapResp.getName());
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("---mapStartPoint");
                    sb.append((mapInitPoint == null || (vector = mapInitPoint.getVector()) == null) ? null : Boxing.boxDouble(vector.getX()));
                    objArr2[0] = sb.toString();
                    Pdlog.m3273d("MapDownloadViewModel", objArr2);
                    SpDataUtils.INSTANCE.saveMapStartPoint(mapInitPoint);
                    List<Vector3d> virtualWallList = LocateMappingManager.INSTANCE.getVirtualWallList();
                    Object[] objArr3 = new Object[1];
                    int i3 = 0;
                    objArr3[0] = virtualWallList != null ? Boxing.boxInt(virtualWallList.size()) : null;
                    Pdlog.m3273d("MapDownloadViewModel", objArr3);
                    if (virtualWallList == null) {
                        SpDataUtils.INSTANCE.saveVirtual(new ArrayList());
                        obj = coroutine_suspended;
                    } else {
                        ArrayList arrayList15 = new ArrayList();
                        int size = virtualWallList.size() / 2;
                        while (i3 < size) {
                            ArrayList arrayList16 = new ArrayList();
                            int i4 = i3 * 2;
                            arrayList16.add(virtualWallList.get(i4));
                            arrayList16.add(virtualWallList.get(i4 + 1));
                            arrayList15.add(new VirtualItemBean(arrayList16, AppContext.INSTANCE.getContext().getString(C5362R.string.title_virtual) + i3, false, 4, null));
                            i3++;
                            size = size;
                            coroutine_suspended = coroutine_suspended;
                        }
                        obj = coroutine_suspended;
                        SpDataUtils.INSTANCE.saveVirtual(arrayList15);
                    }
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    MapDownloadViewModel$setMapData$8 mapDownloadViewModel$setMapData$8 = new MapDownloadViewModel$setMapData$8(this, null);
                    mapDownloadViewModel$setMapData$1.L$0 = this;
                    mapDownloadViewModel$setMapData$1.L$1 = list;
                    mapDownloadViewModel$setMapData$1.L$2 = robotMapResp;
                    mapDownloadViewModel$setMapData$1.L$3 = list3;
                    mapDownloadViewModel$setMapData$1.L$4 = cruiseTracksList;
                    mapDownloadViewModel$setMapData$1.L$5 = arrayList4;
                    mapDownloadViewModel$setMapData$1.L$6 = arrayList6;
                    mapDownloadViewModel$setMapData$1.L$7 = arrayList8;
                    mapDownloadViewModel$setMapData$1.L$8 = arrayList10;
                    mapDownloadViewModel$setMapData$1.L$9 = mapInitPoint;
                    mapDownloadViewModel$setMapData$1.L$10 = virtualWallList;
                    mapDownloadViewModel$setMapData$1.label = 1;
                    Object obj7 = obj;
                    if (BuildersKt.withContext(main, mapDownloadViewModel$setMapData$8, mapDownloadViewModel$setMapData$1) == obj7) {
                        return obj7;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj2);
                }
                return Unit.INSTANCE;
            }
        }
        mapDownloadViewModel$setMapData$1 = new MapDownloadViewModel$setMapData$1(this, continuation);
        Object obj22 = mapDownloadViewModel$setMapData$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mapDownloadViewModel$setMapData$1.label;
        int i22 = 1;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    public final void download(List<RobotMapResp> it1) {
        Intrinsics.checkParameterIsNotNull(it1, "it1");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MapDownloadViewModel$download$1(it1, null), 2, null);
    }

    public final void reloadMap(RobotMapResp robotMapResp) {
        Intrinsics.checkParameterIsNotNull(robotMapResp, "robotMapResp");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MapDownloadViewModel$reloadMap$1(this, robotMapResp, null), 3, null);
    }

    public final void jumpMap(RobotMapResp item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MapDownloadViewModel$jumpMap$1(this, item, null), 2, null);
    }
}
