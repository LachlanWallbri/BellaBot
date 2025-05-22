package com.pudutech.freeinstall_ui.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.preference.MMKVPreference;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.adapter.ChargeListItem;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.adapter.DoublePathListItem;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.bean.VirtualItemBean;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpDataUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/SpDataUtils;", "", "()V", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SpDataUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_ADD_CHARGE_LIST = "key_add_charge_list";
    private static final String KEY_ADD_DOOR_LIST = "key_add_door_list";
    private static final String KEY_ADD_MEAL_LIST = "key_add_meal_list";
    private static final String KEY_ADD_STATION_LIST = "key_add_station_list";
    private static final String KEY_ADD_TABLE_LIST = "key_add_table_list";
    private static final String KEY_CAMERA_TYPE = "key_camera_type";
    private static final String KEY_DOUBLE_PATH = "key_double_path";
    private static final String KEY_ERROR_POINT = "key_error_point";
    private static final String KEY_MAP_CRUISE_PATH = "key_map_cruise_path";
    private static final String KEY_MAP_CRUISE_PATH_BACK = "key_map_cruise_path_back";
    private static final String KEY_MAP_DOWNLOAD_LIST = "key_map_download_list";
    private static final String KEY_MAP_LEVEL = "key_map_level";
    private static final String KEY_MAP_NAME = "key_map_name";
    private static final String KEY_MAP_START_POINT = "key_map_start_point";
    private static final String KEY_SAVE_VIRTUAL = "key_save_virtual";

    /* compiled from: SpDataUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016J\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016J\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0016J\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0016J\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0016J\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0016J\u0006\u0010\u001f\u001a\u00020 J\u0014\u0010!\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"\u0018\u00010\u0016J\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\"J\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\"J\r\u0010(\u001a\u0004\u0018\u00010)¢\u0006\u0002\u0010*J\b\u0010+\u001a\u0004\u0018\u00010\u0004J\b\u0010,\u001a\u0004\u0018\u00010'J\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u0016J\u0014\u0010/\u001a\u00020\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\u0014\u00101\u001a\u00020\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00190\u0016J\u0014\u00102\u001a\u00020\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016J\u0014\u00103\u001a\u00020\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016J\u0014\u00104\u001a\u00020\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016J\u0014\u00105\u001a\u00020\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016J\u0012\u00106\u001a\u00020\u00142\n\b\u0002\u00107\u001a\u0004\u0018\u00010 J\u001a\u00108\u001a\u00020\u00142\u0012\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0\u0016J\u0014\u00109\u001a\u00020\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020%0\"J\u0016\u0010:\u001a\u00020\u00142\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\"J\u000e\u0010;\u001a\u00020\u00142\u0006\u0010<\u001a\u00020)J\u000e\u0010=\u001a\u00020\u00142\u0006\u0010>\u001a\u00020\u0004J\u0010\u0010?\u001a\u00020\u00142\b\u0010@\u001a\u0004\u0018\u00010'J\u0014\u0010A\u001a\u00020\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020.0\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006B"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/SpDataUtils$Companion;", "", "()V", "KEY_ADD_CHARGE_LIST", "", "KEY_ADD_DOOR_LIST", "KEY_ADD_MEAL_LIST", "KEY_ADD_STATION_LIST", "KEY_ADD_TABLE_LIST", "KEY_CAMERA_TYPE", "KEY_DOUBLE_PATH", "KEY_ERROR_POINT", "KEY_MAP_CRUISE_PATH", "KEY_MAP_CRUISE_PATH_BACK", "KEY_MAP_DOWNLOAD_LIST", "KEY_MAP_LEVEL", "KEY_MAP_NAME", "KEY_MAP_START_POINT", "KEY_SAVE_VIRTUAL", "clear", "", "getAddChargePile", "", "Lcom/pudutech/freeinstall_ui/adapter/ChargeListItem;", "getAddCruisePath", "Lcom/pudutech/freeinstall_ui/adapter/CruiseListItem;", "getAddDoorPoint", "Lcom/pudutech/freeinstall_ui/adapter/TableListItem;", "getAddMeal", "getAddStation", "getAddTable", "getCameraType", "Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "getCruisePathBack", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getDoublePath", "Lcom/pudutech/freeinstall_ui/adapter/DoublePathListItem;", "getExceptionPoint", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "getMapLevel", "", "()Ljava/lang/Integer;", "getMapName", "getMapStartPoint", "getVirtual", "Lcom/pudutech/freeinstall_ui/bean/VirtualItemBean;", "saveAddChargePile", "list", "saveAddCruisePath", "saveAddDoorPoint", "saveAddMeal", "saveAddStation", "saveAddTable", "saveCameraType", "cameraType", "saveCruisePathBack", "saveDoublePath", "saveExceptionPoint", "saveMapLevel", "level", "saveMapName", "name", "saveMapStartPoint", "destination", "saveVirtual", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<TableListItem> getAddTable() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_ADD_TABLE_LIST, "");
            if (decodeString != null && !Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<TableListItem>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getAddTable$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveAddTable(List<TableListItem> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_ADD_TABLE_LIST, new Gson().toJson(list));
        }

        public final List<TableListItem> getAddDoorPoint() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_ADD_DOOR_LIST, "");
            if (decodeString != null && !Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<TableListItem>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getAddDoorPoint$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveAddDoorPoint(List<TableListItem> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_ADD_DOOR_LIST, new Gson().toJson(list));
        }

        public final List<TableListItem> getAddMeal() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_ADD_MEAL_LIST, "");
            if (decodeString != null && !Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<TableListItem>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getAddMeal$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveAddMeal(List<TableListItem> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_ADD_MEAL_LIST, new Gson().toJson(list));
        }

        public final List<TableListItem> getAddStation() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_ADD_STATION_LIST, "");
            if (decodeString != null && !Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<TableListItem>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getAddStation$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveAddStation(List<TableListItem> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_ADD_STATION_LIST, new Gson().toJson(list));
        }

        public final List<ChargeListItem> getAddChargePile() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_ADD_CHARGE_LIST, "");
            if (decodeString != null && !Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<ChargeListItem>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getAddChargePile$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveAddChargePile(List<ChargeListItem> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_ADD_CHARGE_LIST, new Gson().toJson(list));
        }

        public final List<Destination> getExceptionPoint() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_ERROR_POINT, "");
            if (decodeString != null && !Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<Destination>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getExceptionPoint$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveExceptionPoint(List<Destination> list) {
            if (list == null) {
                return;
            }
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_ERROR_POINT, new Gson().toJson(list));
        }

        public final List<CruiseListItem> getAddCruisePath() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_MAP_CRUISE_PATH, "");
            if (decodeString != null && !Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<CruiseListItem>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getAddCruisePath$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveAddCruisePath(List<CruiseListItem> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_MAP_CRUISE_PATH, new Gson().toJson(list));
        }

        public final List<List<Vector3d>> getCruisePathBack() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_MAP_CRUISE_PATH_BACK, "");
            if (decodeString != null && !Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<List<? extends Vector3d>>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getCruisePathBack$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveCruisePathBack(List<List<Vector3d>> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_MAP_CRUISE_PATH_BACK, new Gson().toJson(list));
        }

        public final List<VirtualItemBean> getVirtual() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_SAVE_VIRTUAL, "");
            if (decodeString == null) {
                decodeString = "";
            }
            if (!Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<VirtualItemBean>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getVirtual$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveVirtual(List<VirtualItemBean> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_SAVE_VIRTUAL, new Gson().toJson(list));
        }

        public final List<DoublePathListItem> getDoublePath() {
            String decodeString = MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_DOUBLE_PATH, "");
            if (decodeString == null) {
                decodeString = "";
            }
            if (!Intrinsics.areEqual(decodeString, "null")) {
                if (!(decodeString.length() == 0)) {
                    try {
                        return (ArrayList) new Gson().fromJson(decodeString, new TypeToken<ArrayList<DoublePathListItem>>() { // from class: com.pudutech.freeinstall_ui.utils.SpDataUtils$Companion$getDoublePath$list$1
                        }.getType());
                    } catch (Exception unused) {
                    }
                }
            }
            return null;
        }

        public final void saveDoublePath(List<DoublePathListItem> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_DOUBLE_PATH, new Gson().toJson(list));
        }

        public final void saveMapName(String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Pdlog.m3273d(getClass().getName(), "saveMapName");
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_MAP_NAME, name);
        }

        public final String getMapName() {
            return MMKVPreference.INSTANCE.decodeString(SpDataUtils.KEY_MAP_NAME);
        }

        public final void saveMapLevel(int level) {
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_MAP_LEVEL, Integer.valueOf(level));
        }

        public final Integer getMapLevel() {
            return MMKVPreference.INSTANCE.decodeInt(SpDataUtils.KEY_MAP_LEVEL, 0);
        }

        public static /* synthetic */ void saveCameraType$default(Companion companion, CameraType cameraType, int i, Object obj) {
            if ((i & 1) != 0) {
                cameraType = CameraType.MARKER_CAMERA;
            }
            companion.saveCameraType(cameraType);
        }

        public final void saveCameraType(CameraType cameraType) {
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_CAMERA_TYPE, (String) cameraType);
        }

        public final CameraType getCameraType() {
            CameraType cameraType = (CameraType) MMKVPreference.INSTANCE.decodeParcelable(SpDataUtils.KEY_CAMERA_TYPE, CameraType.class);
            return cameraType != null ? cameraType : CameraType.MARKER_CAMERA;
        }

        public final void saveMapStartPoint(Destination destination) {
            if (destination != null) {
                String string = AppContext.INSTANCE.getContext().getString(C5362R.string.start_point);
                Intrinsics.checkExpressionValueIsNotNull(string, "AppContext.context.getString(R.string.start_point)");
                destination.setName(string);
            }
            MMKVPreference.INSTANCE.encode(SpDataUtils.KEY_MAP_START_POINT, (String) destination);
        }

        public final Destination getMapStartPoint() {
            return (Destination) MMKVPreference.INSTANCE.decodeParcelable(SpDataUtils.KEY_MAP_START_POINT, Destination.class);
        }

        public final void clear() {
            Companion companion = this;
            companion.saveMapName("");
            companion.saveMapLevel(0);
            companion.saveAddTable(new ArrayList());
            companion.saveAddDoorPoint(new ArrayList());
            companion.saveAddCruisePath(new ArrayList());
            companion.saveAddChargePile(new ArrayList());
            companion.saveAddStation(new ArrayList());
            companion.saveAddMeal(new ArrayList());
            companion.saveDoublePath(new ArrayList());
            companion.saveVirtual(new ArrayList());
            companion.saveCruisePathBack(new ArrayList());
            companion.saveExceptionPoint(new ArrayList());
            companion.saveCameraType(CameraType.MARKER_CAMERA);
            companion.saveMapStartPoint(null);
        }
    }
}
