package com.pudutech.freeinstall_ui.viewmodel;

import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.freeinstall_ui.adapter.BusinessSetItem;
import com.pudutech.freeinstall_ui.adapter.ChargeListItem;
import com.pudutech.freeinstall_ui.adapter.FunctionSelectItem;
import com.pudutech.freeinstall_ui.bean.VirtualItemBean;
import com.pudutech.freeinstall_ui.utils.BusinessFunction;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.StepDataHelper;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: EditMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001bJ\b\u0010\u001d\u001a\u00020\u001bH\u0002J\u001e\u0010\u001e\u001a\u00020\u001b2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010 2\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\u001bJ\u0006\u0010$\u001a\u00020\u001bJ\u0006\u0010%\u001a\u00020\u001bJ\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u0004J\u0006\u0010(\u001a\u00020\u001bJ\u000e\u0010)\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001a\u0010\u000e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\tR\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\tR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\t¨\u0006+"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/viewmodel/EditMapViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "addPathLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getAddPathLiveData", "()Landroidx/lifecycle/MutableLiveData;", "functionData", "", "Lcom/pudutech/freeinstall_ui/adapter/FunctionSelectItem;", "getFunctionData", "loadLocateFinish", "getLoadLocateFinish", "()Z", "setLoadLocateFinish", "(Z)V", "mapStatusLiveData", "getMapStatusLiveData", "pointData", "Lcom/pudutech/freeinstall_ui/adapter/BusinessSetItem;", "getPointData", "timeOutLiveData", "getTimeOutLiveData", "addChargePile", "", "addPoint", "checkVirtual", "getBusinessItemData", "functionItemList", "", "from", "", "getFunctionItemData", "getMapData", "getMapInitStatus", "initLocalization", "mapName", "reinitAlgoModules", "setLoadLocate", "b", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class EditMapViewModel extends BaseViewModel {
    private boolean loadLocateFinish;
    private final String TAG = "EditMapViewModel";
    private final MutableLiveData<List<BusinessSetItem>> pointData = new MutableLiveData<>();
    private final MutableLiveData<List<FunctionSelectItem>> functionData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mapStatusLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> addPathLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> timeOutLiveData = new MutableLiveData<>();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BusinessFunction.values().length];

        static {
            $EnumSwitchMapping$0[BusinessFunction.FUNCTION_ASSURES.ordinal()] = 1;
            $EnumSwitchMapping$0[BusinessFunction.FUNCTION_RECEPTIONIST.ordinal()] = 2;
            $EnumSwitchMapping$0[BusinessFunction.FUNCTION_CRUISE.ordinal()] = 3;
            $EnumSwitchMapping$0[BusinessFunction.FUNCTION_MEALS.ordinal()] = 4;
        }
    }

    public final MutableLiveData<List<BusinessSetItem>> getPointData() {
        return this.pointData;
    }

    public final MutableLiveData<List<FunctionSelectItem>> getFunctionData() {
        return this.functionData;
    }

    public final MutableLiveData<Boolean> getMapStatusLiveData() {
        return this.mapStatusLiveData;
    }

    public final MutableLiveData<Boolean> getAddPathLiveData() {
        return this.addPathLiveData;
    }

    public final MutableLiveData<Boolean> getTimeOutLiveData() {
        return this.timeOutLiveData;
    }

    public final boolean getLoadLocateFinish() {
        return this.loadLocateFinish;
    }

    public final void setLoadLocateFinish(boolean z) {
        this.loadLocateFinish = z;
    }

    public final void setLoadLocate(boolean b) {
        this.loadLocateFinish = b;
    }

    public final void getFunctionItemData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EditMapViewModel$getFunctionItemData$1(this, null), 3, null);
    }

    public final void getBusinessItemData(List<FunctionSelectItem> functionItemList, int from) {
        if (functionItemList == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EditMapViewModel$getBusinessItemData$1(this, functionItemList, from, null), 3, null);
    }

    public final void getMapInitStatus() {
        RobotMapManager.checkLocationInit$default(RobotMapManager.INSTANCE, new Function1<Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.viewmodel.EditMapViewModel$getMapInitStatus$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                str = EditMapViewModel.this.TAG;
                Pdlog.m3273d(str, "getMapInitStatus " + z);
                EditMapViewModel.this.getMapStatusLiveData().postValue(Boolean.valueOf(z));
            }
        }, 0L, 2, null);
    }

    public final void initLocalization(String mapName) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Pdlog.m3273d(this.TAG, "initLocalization " + mapName);
        if (TextUtils.isEmpty(mapName)) {
            return;
        }
        RobotMapManager.INSTANCE.loadLocateCore(mapName);
        StepDataHelper.INSTANCE.getInstance().setInitLocation(true);
    }

    public final void addPoint() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EditMapViewModel$addPoint$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkVirtual() {
        ArrayList virtual = SpDataUtils.INSTANCE.getVirtual();
        ArrayList arrayList = new ArrayList();
        if (virtual != null) {
            for (VirtualItemBean virtualItemBean : virtual) {
                boolean[] checkVirtualWall = LocateMappingManager.INSTANCE.checkVirtualWall(virtualItemBean.getVector3ds());
                Pdlog.m3273d(this.TAG, "checkVirtualWall " + checkVirtualWall);
                if (checkVirtualWall != null && !checkVirtualWall[0]) {
                    arrayList.add(virtualItemBean);
                }
            }
        }
        if (virtual != null) {
            virtual.removeAll(arrayList);
        }
        SpDataUtils.Companion companion = SpDataUtils.INSTANCE;
        if (virtual == null) {
            virtual = new ArrayList();
        }
        companion.saveVirtual(virtual);
    }

    public final void addChargePile() {
        List<ChargeListItem> addChargePile = SpDataUtils.INSTANCE.getAddChargePile();
        ArrayList arrayList = new ArrayList();
        if (addChargePile != null) {
            Iterator<T> it = addChargePile.iterator();
            while (it.hasNext()) {
                arrayList.add(((ChargeListItem) it.next()).getDockerResult());
            }
        }
        LocateMappingManager.INSTANCE.saveChargeDockerList(arrayList);
        this.addPathLiveData.postValue(true);
    }

    public final void reinitAlgoModules() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EditMapViewModel$reinitAlgoModules$1(this, null), 3, null);
    }

    public final void getMapData() {
        Vector3d vector;
        String staticMapName = RobotMapManager.INSTANCE.getStaticMapName();
        if (staticMapName == null) {
            staticMapName = "";
        }
        CameraType cameraType = RobotMapManager.INSTANCE.getCameraType();
        if (cameraType == null) {
            cameraType = CameraType.MARKER_CAMERA;
        }
        Destination mapInitPoint = LocateMappingManager.INSTANCE.getMapInitPoint(staticMapName);
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("getMapData--mapName:");
        sb.append(staticMapName);
        sb.append(" cameraType:");
        sb.append(cameraType);
        sb.append("-mapInitPoint");
        sb.append((mapInitPoint == null || (vector = mapInitPoint.getVector()) == null) ? null : Double.valueOf(vector.getX()));
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        SpDataUtils.INSTANCE.saveMapName(staticMapName);
        SpDataUtils.INSTANCE.saveCameraType(cameraType);
        SpDataUtils.INSTANCE.saveMapStartPoint(mapInitPoint);
    }
}
