package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.adapter.DoublePathListItem;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.TopoTrack;
import com.pudutech.opengl_draw.bean.Line;
import com.pudutech.opengl_draw.geometry.Vector3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AddDoublePathViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0016J\u0014\u0010\u001a\u001a\u00020\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001cR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001f\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u001f\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000b¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/AddDoublePathViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "addPathLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getAddPathLiveData", "()Landroidx/lifecycle/MutableLiveData;", "dataLiveData", "", "Lcom/pudutech/freeinstall_ui/adapter/DoublePathListItem;", "getDataLiveData", "staticMapLiveData", "getStaticMapLiveData", "topoMapLiveData", "Lcom/pudutech/opengl_draw/bean/Line;", "getTopoMapLiveData", "addPath", "", "getDoublePath", "getStaticMap", "getTopoMap", "setDoublePath", "listItems", "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AddDoublePathViewModel extends BaseViewModel {
    private final String TAG = "AddDoublePathViewModel";
    private final MutableLiveData<String> staticMapLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DoublePathListItem>> dataLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Line>> topoMapLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> addPathLiveData = new MutableLiveData<>();

    public final String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<String> getStaticMapLiveData() {
        return this.staticMapLiveData;
    }

    public final MutableLiveData<List<DoublePathListItem>> getDataLiveData() {
        return this.dataLiveData;
    }

    public final MutableLiveData<List<Line>> getTopoMapLiveData() {
        return this.topoMapLiveData;
    }

    public final MutableLiveData<Boolean> getAddPathLiveData() {
        return this.addPathLiveData;
    }

    public final void getStaticMap() {
        Pdlog.m3273d(this.TAG, "getStaticMap");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddDoublePathViewModel$getStaticMap$1(this, null), 3, null);
    }

    public final void setDoublePath(List<DoublePathListItem> listItems) {
        Intrinsics.checkParameterIsNotNull(listItems, "listItems");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddDoublePathViewModel$setDoublePath$1(this, listItems, null), 3, null);
    }

    /* JADX WARN: Type inference failed for: r7v14, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.util.List, T] */
    public final void getDoublePath() {
        List list;
        List list2;
        ArrayList doubleRoadTracks = LocateMappingManager.INSTANCE.getDoubleRoadTracks();
        if (doubleRoadTracks == null) {
            doubleRoadTracks = new ArrayList();
        }
        char c = 0;
        Pdlog.m3273d(this.TAG, "getDoublePath " + doubleRoadTracks.size());
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (TopoTrack topoTrack : doubleRoadTracks) {
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            String str = this.TAG;
            Object[] objArr = new Object[1];
            objArr[c] = " list topo_id " + topoTrack.getTopo_id();
            Pdlog.m3273d(str, objArr);
            if (hashMap.get(String.valueOf(topoTrack.getTopo_id())) == null) {
                String str2 = this.TAG;
                Object[] objArr2 = new Object[1];
                objArr2[c] = "if vector3List " + ((List) hashMap.get(String.valueOf(topoTrack.getTopo_id()))) + "  topo_id " + topoTrack.getTopo_id();
                Pdlog.m3273d(str2, objArr2);
                objectRef.element = new ArrayList();
                hashMap.put(String.valueOf(topoTrack.getTopo_id()), (List) objectRef.element);
            } else {
                String str3 = this.TAG;
                Object[] objArr3 = new Object[1];
                objArr3[c] = "else vector3List " + ((List) hashMap.get(String.valueOf(topoTrack.getTopo_id()))) + "  topo_id " + topoTrack.getTopo_id();
                Pdlog.m3273d(str3, objArr3);
                objectRef.element = (List) hashMap.get(String.valueOf(topoTrack.getTopo_id()));
            }
            Utils.Companion companion = Utils.INSTANCE;
            Vector3d start_pose = topoTrack.getStart_pose();
            if (start_pose == null) {
                Intrinsics.throwNpe();
            }
            double x = start_pose.getX();
            Vector3d start_pose2 = topoTrack.getStart_pose();
            if (start_pose2 == null) {
                Intrinsics.throwNpe();
            }
            Vector3 vector3dToVector3 = companion.vector3dToVector3(new Vector3d(x, start_pose2.getY(), 0.0d));
            if (vector3dToVector3 != null && (list2 = (List) objectRef.element) != null) {
                list2.add(vector3dToVector3);
            }
            Utils.Companion companion2 = Utils.INSTANCE;
            Vector3d end_pose = topoTrack.getEnd_pose();
            if (end_pose == null) {
                Intrinsics.throwNpe();
            }
            double x2 = end_pose.getX();
            Vector3d end_pose2 = topoTrack.getEnd_pose();
            if (end_pose2 == null) {
                Intrinsics.throwNpe();
            }
            Vector3 vector3dToVector32 = companion2.vector3dToVector3(new Vector3d(x2, end_pose2.getY(), 0.0d));
            if (vector3dToVector32 != null && (list = (List) objectRef.element) != null) {
                list.add(vector3dToVector32);
            }
            c = 0;
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            String str4 = (String) entry.getKey();
            List list3 = (List) entry.getValue();
            arrayList.add(new DoublePathListItem(list3, str4, false, 4, null));
            Pdlog.m3273d(this.TAG, "vector3List " + list3 + "  topo_id " + str4);
        }
        this.dataLiveData.postValue(arrayList);
    }

    public final void getTopoMap() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddDoublePathViewModel$getTopoMap$1(this, null), 3, null);
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
