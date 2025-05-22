package com.pudutech.mirsdkwrap.lib.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.aidl.serialize.MapListConfig;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotMapManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001c\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002\u0090\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u00109\u001a\u0002072\u0006\u0010:\u001a\u00020\u0004J\u000e\u0010;\u001a\u0002072\u0006\u0010<\u001a\u00020/J\u000e\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0004J\u000e\u0010@\u001a\u00020>2\u0006\u0010A\u001a\u00020\u0004J3\u0010B\u001a\u0002072!\u0010C\u001a\u001d\u0012\u0013\u0012\u00110>¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(G\u0012\u0004\u0012\u0002070D2\b\b\u0002\u0010H\u001a\u00020IJ\u0006\u0010J\u001a\u00020>J\u0016\u0010K\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0014j\b\u0012\u0004\u0012\u00020\u000b`\u0016J8\u0010L\u001a4\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00140Mj\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00040\u0014j\b\u0012\u0004\u0012\u00020\u0004`\u0016`NJ\r\u0010O\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010QJ\u000e\u0010R\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010SJ+\u0010T\u001a\u0002072#\u0010C\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\"¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(U\u0012\u0004\u0012\u0002070DJ3\u0010T\u001a\u0002072\u0006\u0010V\u001a\u00020\u00042#\u0010C\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\"¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(U\u0012\u0004\u0012\u0002070DJ\b\u0010W\u001a\u0004\u0018\u00010XJ\u0006\u0010Y\u001a\u00020\u0004J\b\u0010Z\u001a\u0004\u0018\u00010\u0004J\b\u0010[\u001a\u0004\u0018\u00010\u0004J\b\u0010\\\u001a\u0004\u0018\u00010\u0004J\b\u0010]\u001a\u0004\u0018\u00010\u0004J\b\u0010^\u001a\u0004\u0018\u00010\u0004J\u001e\u0010_\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0014j\b\u0012\u0004\u0012\u00020\u000b`\u00162\u0006\u0010`\u001a\u00020aJ.\u0010b\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0014j\b\u0012\u0004\u0012\u00020\u000b`\u00162\u0016\u0010`\u001a\u0012\u0012\u0004\u0012\u00020a0\u0014j\b\u0012\u0004\u0012\u00020a`\u0016J\u0016\u0010c\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0014j\b\u0012\u0004\u0012\u00020\u0004`\u0016J\u0006\u0010d\u001a\u00020\u001cJ\b\u0010e\u001a\u0004\u0018\u00010fJ3\u0010g\u001a\u0002072\u0006\u0010V\u001a\u00020\u00042#\u0010C\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(h\u0012\u0004\u0012\u0002070DJ\u000e\u0010i\u001a\n\u0012\u0004\u0012\u00020X\u0018\u00010jJ\b\u0010k\u001a\u0004\u0018\u00010lJ9\u0010m\u001a\u00020721\u0010C\u001a-\u0012#\u0012!\u0012\u0004\u0012\u00020\u00040\u0014j\b\u0012\u0004\u0012\u00020\u0004`\u0016¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(F\u0012\u0004\u0012\u0002070DJ\u0016\u0010n\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0014j\b\u0012\u0004\u0012\u00020\u000b`\u0016J\u0016\u0010o\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0014j\b\u0012\u0004\u0012\u00020\u0004`\u0016J\n\u0010p\u001a\u0004\u0018\u00010\u0004H\u0002J\u0016\u0010q\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010S2\u0006\u0010V\u001a\u00020\u0004J\u0016\u0010r\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0014j\b\u0012\u0004\u0012\u00020\u0004`\u0016J%\u0010s\u001a\u0002072\u0006\u0010&\u001a\u00020'2\u0006\u0010t\u001a\u00020u2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\bvJ\b\u0010w\u001a\u000207H\u0002J\u0006\u0010x\u001a\u00020>J\b\u0010y\u001a\u000207H\u0002J\b\u0010z\u001a\u000207H\u0002J\u0010\u0010{\u001a\u0002072\u0006\u0010|\u001a\u00020'H\u0002J\u0006\u0010}\u001a\u000207J\u0006\u0010~\u001a\u000207J\u000f\u0010\u007f\u001a\u0002072\u0007\u0010\u0080\u0001\u001a\u00020\u0004J\u000f\u0010\u0081\u0001\u001a\u0002072\u0006\u0010<\u001a\u00020/J\u0010\u0010\u0082\u0001\u001a\u0002072\u0007\u0010\u0083\u0001\u001a\u00020PJ\u000f\u0010\u0084\u0001\u001a\u00020>2\u0006\u0010F\u001a\u00020\u0004J\u000f\u0010\u0085\u0001\u001a\u00020>2\u0006\u0010F\u001a\u00020\u0004J\u0012\u0010\u0086\u0001\u001a\u0002072\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0088\u0001\u001a\u00020>2\u0007\u0010\u0089\u0001\u001a\u00020\u0004J\u0010\u0010\u008a\u0001\u001a\u0002072\u0007\u0010\u008b\u0001\u001a\u00020\u0004J\u0018\u0010\u008c\u0001\u001a\u0002072\u0007\u0010\u008d\u0001\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u0004J\u0010\u0010\u008e\u0001\u001a\u0002072\u0007\u0010\u008d\u0001\u001a\u00020\u0004J\u0018\u0010\u008f\u0001\u001a\u0002072\u0007\u0010\u008d\u0001\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00048F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u00162\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR*\u0010 \u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"0!j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"`#X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010$\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020%0!j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020%`#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R-\u0010(\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040!j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`#¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b0\u00101R$\u00104\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u000106\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020705X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0091\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/RobotMapManager;", "", "()V", "FILE_NAME", "", "KEY_DASH_WASH", "KEY_DINNING_OUTLET", "KEY_USHER", "TAG", "allDestination", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/Destination;", "context", "Landroid/content/Context;", "customOutlet", "<set-?>", "defaultPdmap", "getDefaultPdmap", "()Ljava/lang/String;", "dinningOutLetGroup", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/Floor;", "Lkotlin/collections/ArrayList;", "floors", "getFloors", "()Ljava/util/ArrayList;", "gson", "Lcom/google/gson/Gson;", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "locateCase", "getLocateCase", "()Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "mapCruiseLineM", "Ljava/util/HashMap;", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruisePathLoader;", "Lkotlin/collections/HashMap;", "mapDrawPathM", "Lcom/pudutech/mirsdkwrap/lib/map/MapDrawPathLoader;", "mirSdk", "Lcom/pudutech/mirsdk/aidl/SDKInterface;", "phoneBooks", "getPhoneBooks", "()Ljava/util/HashMap;", "sp", "Landroid/content/SharedPreferences;", "switchMapResultListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lcom/pudutech/mirsdkwrap/lib/map/RobotMapManager$SwitchMapResultListener;", "getSwitchMapResultListeners", "()Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "switchMapResultListeners$delegate", "Lkotlin/Lazy;", "switchPdmapResultListener", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/SwitchMapResult;", "", "usherGroup", "addRelocationPoint", "relocatePoint", "addSwitchMapResultListener", "switchMapResultListener", "checkDestinationExist", "", LinkFormat.DOMAIN, "checkLegalMap", "path", "checkLocationInit", "cb", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "checkTime", "", "checkMapRule", "getAllDestination", "getAllTableGroup", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getCallElevatorDistance", "", "()Ljava/lang/Double;", "getChargingPiles", "", "getCruisePath", "cl", "mapName", "getCurMap", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "getCurrentFloorName", "getCurrentMapAtlasPath", "getCurrentMapDiningOutLetChosen", "getCurrentMapUsherChosen", "getDashWashChosen", "getDefaultMapName", "getDestinationByType", "type", "Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;", "getDestinationByTypes", "getDinningOutLets", "getLocationCase", "getLocationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "getMapDrawPath", "mapLoader", "getMapList", "", "getMapNameList", "Lcom/pudutech/mirsdk/aidl/serialize/MapPackageConfig;", "getMaps", "getRecyclePoints", "getRelocationPoints", "getSelectMapKey", "getSpecifyMapDestinations", "getUshers", "init", "mirSdkListenerWrap", "Lcom/pudutech/mirsdkwrap/lib/robot/MirSdkListenerWrap;", "init$module_robot_mirsdk_wrapper_release", "initSp", "isLocalizationSuccess", "loadDestinations", "loadMapData", "loadPhoneBook", "sdk", "reloadLocation", "reloadMap", "reloadMapByName", "str", "removeSwitchMapResultListener", "setCallElevatorDistance", "distance", "setCurrentMapDiningOutLetChosen", "setCurrentMapUsherChosen", "setCustomOutlet", "outlet", "setDashWashChosen", "n", "setDefaultPdMap", "defaultMapName", "switchDefMap", "floor", "switchFloor", "switchMap", "SwitchMapResultListener", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotMapManager {
    private static final String FILE_NAME = "MapHelper";
    private static final String KEY_DASH_WASH = "dash_wash_dining";
    private static final String KEY_DINNING_OUTLET = "dining_outlet";
    private static final String KEY_USHER = "usher";
    private static Context context;
    private static SDKInterface mirSdk;
    private static SharedPreferences sp;
    public static final RobotMapManager INSTANCE = new RobotMapManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* renamed from: switchMapResultListeners$delegate, reason: from kotlin metadata */
    private static final Lazy switchMapResultListeners = LazyKt.lazy(new Function0<ListenerList<SwitchMapResultListener>>() { // from class: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$switchMapResultListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RobotMapManager.SwitchMapResultListener> invoke() {
            return new ListenerList<>();
        }
    });
    private static HashMap<String, MapDrawPathLoader> mapDrawPathM = new HashMap<>();
    private static HashMap<String, MapCruisePathLoader> mapCruiseLineM = new HashMap<>();
    private static final Gson gson = new Gson();
    private static final CopyOnWriteArrayList<Destination> allDestination = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<String> dinningOutLetGroup = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<String> usherGroup = new CopyOnWriteArrayList<>();
    private static String customOutlet = "";
    private static String defaultPdmap = "";
    private static LocateCase locateCase = LocateCase.Marker;
    private static ArrayList<Floor> floors = new ArrayList<>();
    private static final HashMap<String, String> phoneBooks = new HashMap<>();
    private static final Function2<SwitchMapResult, String, Unit> switchPdmapResultListener = new Function2<SwitchMapResult, String, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$switchPdmapResultListener$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(SwitchMapResult switchMapResult, String str) {
            invoke2(switchMapResult, str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final SwitchMapResult switchMapResult, String str) {
            String str2;
            ListenerList switchMapResultListeners2;
            RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
            str2 = RobotMapManager.TAG;
            Pdlog.m3273d(str2, " switchPdmapResultListener : result = " + switchMapResult + "; msg = " + str + "; ");
            RobotMapManager.INSTANCE.loadMapData();
            switchMapResultListeners2 = RobotMapManager.INSTANCE.getSwitchMapResultListeners();
            switchMapResultListeners2.forEach(Dispatchers.getMain(), new Function1<RobotMapManager.SwitchMapResultListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$switchPdmapResultListener$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(RobotMapManager.SwitchMapResultListener switchMapResultListener) {
                    invoke2(switchMapResultListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(RobotMapManager.SwitchMapResultListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    if (SwitchMapResult.this == SwitchMapResult.Finish || SwitchMapResult.this == SwitchMapResult.GoalIsCurrent) {
                        it.onResult(true, null);
                    } else {
                        SwitchMapResult switchMapResult2 = SwitchMapResult.this;
                        it.onResult(false, switchMapResult2 != null ? switchMapResult2.name() : null);
                    }
                }
            });
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotMapManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/RobotMapManager$SwitchMapResultListener;", "", "onResult", "", "b", "", IoTConfig.PARAM_ERROR_MSG, "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface SwitchMapResultListener {
        void onResult(boolean b, String errorMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<SwitchMapResultListener> getSwitchMapResultListeners() {
        return (ListenerList) switchMapResultListeners.getValue();
    }

    public final boolean checkMapRule() {
        return true;
    }

    private RobotMapManager() {
    }

    public final String getDefaultPdmap() {
        String defaultMapName = getDefaultMapName();
        return defaultMapName != null ? defaultMapName : "";
    }

    public final LocateCase getLocateCase() {
        return locateCase;
    }

    public final ArrayList<Floor> getFloors() {
        return floors;
    }

    public final HashMap<String, String> getPhoneBooks() {
        return phoneBooks;
    }

    public final void init$module_robot_mirsdk_wrapper_release(SDKInterface mirSdk2, MirSdkListenerWrap mirSdkListenerWrap, Context context2) {
        Intrinsics.checkParameterIsNotNull(mirSdk2, "mirSdk");
        Intrinsics.checkParameterIsNotNull(mirSdkListenerWrap, "mirSdkListenerWrap");
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Pdlog.m3273d(TAG, "init " + Thread.currentThread());
        mirSdk = mirSdk2;
        context = context2;
        mirSdkListenerWrap.getSwitchPdmapResultListeners().addNotSame$module_robot_mirsdk_wrapper_release(switchPdmapResultListener);
        initSp();
        loadMapData();
    }

    public final String getDefaultMapName() {
        MapPackageConfig pdmapNameList;
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface == null || (pdmapNameList = sDKInterface.getPdmapNameList()) == null) {
            return null;
        }
        return pdmapNameList.getDef_map();
    }

    public final String getCurrentFloorName() {
        MapPackageConfig pdmapNameList;
        String def_floor;
        SDKInterface sDKInterface = mirSdk;
        return (sDKInterface == null || (pdmapNameList = sDKInterface.getPdmapNameList()) == null || (def_floor = pdmapNameList.getDef_floor()) == null) ? "" : def_floor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadMapData() {
        String str;
        LocateCase locateCase2;
        List<MapListConfig> list;
        Set<String> keySet;
        String def_floor;
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            MapPackageConfig pdmapNameList = sDKInterface.getPdmapNameList();
            String str2 = "";
            if (pdmapNameList == null || (str = pdmapNameList.getDef_map()) == null) {
                str = "";
            }
            Log.i(TAG, "loadMapData:  " + gson.toJson(sDKInterface.getPdmapNameList()));
            MapPackageConfig pdmapNameList2 = sDKInterface.getPdmapNameList();
            if (pdmapNameList2 != null && (def_floor = pdmapNameList2.getDef_floor()) != null) {
                str2 = def_floor;
            }
            floors.clear();
            MapPackageConfig pdmapNameList3 = sDKInterface.getPdmapNameList();
            if (pdmapNameList3 != null && (list = pdmapNameList3.getList()) != null) {
                for (MapListConfig mapListConfig : list) {
                    ArrayList<Floor> arrayList = floors;
                    String def = mapListConfig.getDef();
                    String floor = mapListConfig.getFloor();
                    int findex = mapListConfig.getFindex();
                    Map<String, String> list2 = mapListConfig.getList();
                    arrayList.add(new Floor(def, floor, findex, (list2 == null || (keySet = list2.keySet()) == null) ? null : CollectionsKt.toMutableList((Collection) keySet)));
                }
            }
            SDKInterface sDKInterface2 = mirSdk;
            if (sDKInterface2 == null || (locateCase2 = sDKInterface2.getLocateCase()) == null) {
                locateCase2 = LocateCase.Marker;
            }
            locateCase = locateCase2;
            Pdlog.m3273d(TAG, "loadMapData " + str + " ， currentFloorName = " + str2 + " , floors = " + floors);
            INSTANCE.loadDestinations();
            INSTANCE.loadPhoneBook(sDKInterface);
        }
    }

    public final List<MapInfo> getMapList() {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            return sDKInterface.getMapInfoList();
        }
        return null;
    }

    public final MapInfo getCurMap() {
        ArrayList arrayList;
        List<MapInfo> mapList = getMapList();
        if (mapList != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : mapList) {
                if (Intrinsics.areEqual(((MapInfo) obj).getMapName(), INSTANCE.getDefaultPdmap())) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        ArrayList arrayList3 = arrayList;
        if (arrayList3 == null || arrayList3.isEmpty()) {
            return null;
        }
        return (MapInfo) arrayList.get(0);
    }

    public final boolean checkLegalMap(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        SDKInterface sDKInterface = mirSdk;
        return sDKInterface != null && sDKInterface.checkLegalMap(path);
    }

    public final void setDefaultPdMap(String defaultMapName) {
        Intrinsics.checkParameterIsNotNull(defaultMapName, "defaultMapName");
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.switchDefaultPdmap(defaultMapName);
        }
    }

    private final void loadPhoneBook(SDKInterface sdk) {
        List<MapListConfig> list;
        String str;
        Pdlog.m3273d(TAG, "loadPhoneBook start");
        phoneBooks.clear();
        MapPackageConfig pdmapNameList = sdk.getPdmapNameList();
        if (pdmapNameList != null && (list = pdmapNameList.getList()) != null) {
            for (MapListConfig mapListConfig : list) {
                Map<String, String> list2 = mapListConfig.getList();
                if (list2 != null) {
                    String def = mapListConfig.getDef();
                    if (def == null) {
                        def = "";
                    }
                    str = list2.get(def);
                } else {
                    str = null;
                }
                String str2 = str;
                if (!(str2 == null || StringsKt.isBlank(str2))) {
                    try {
                        File file = new File((str + File.separator) + "telephone_config.json");
                        if (!file.isFile() || !file.exists()) {
                            Pdlog.m3277w(TAG, "loadPhoneBook : " + mapListConfig.getDef() + " not set phone ");
                        } else {
                            try {
                                String readText = TextStreamsKt.readText(new FileReader(file));
                                Pdlog.m3275i(TAG, "loadPhoneBook data:" + readText);
                                if (!StringsKt.isBlank(readText)) {
                                    TelephoneConfig telephoneConfig = (TelephoneConfig) gson.fromJson(readText, TelephoneConfig.class);
                                    Pdlog.m3273d(TAG, "loadPhoneBook : mc = " + mapListConfig.getDef() + "; config = " + telephoneConfig + ' ');
                                    for (Telephone telephone : telephoneConfig.getPhoneBook()) {
                                        phoneBooks.put(telephone.getDestinationName(), telephone.getPhoneNumber());
                                    }
                                }
                            } catch (Exception e) {
                                Pdlog.m3274e(TAG, "loadPhoneBook FileReader: " + Log.getStackTraceString(e));
                            }
                        }
                    } catch (Exception e2) {
                        Pdlog.m3274e(TAG, "loadPhoneBook : " + Log.getStackTraceString(e2));
                    }
                }
            }
        }
        Pdlog.m3273d(TAG, "loadPhoneBook end");
    }

    private final void loadDestinations() {
        MoveActionInterface moveActionInterface;
        List<com.pudutech.mirsdk.aidl.serialize.Destination> destinations;
        MoveActionInterface moveActionInterface2;
        List<com.pudutech.mirsdk.aidl.serialize.Destination> destinations2;
        Pdlog.m3273d(TAG, "loadDestinations ");
        allDestination.clear();
        dinningOutLetGroup.clear();
        usherGroup.clear();
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("loadDestinations destinations = ");
        SDKInterface sDKInterface = mirSdk;
        sb.append((sDKInterface == null || (moveActionInterface2 = sDKInterface.getMoveActionInterface()) == null || (destinations2 = moveActionInterface2.getDestinations()) == null) ? null : Integer.valueOf(destinations2.size()));
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        SDKInterface sDKInterface2 = mirSdk;
        if (sDKInterface2 != null && (moveActionInterface = sDKInterface2.getMoveActionInterface()) != null && (destinations = moveActionInterface.getDestinations()) != null) {
            for (com.pudutech.mirsdk.aidl.serialize.Destination destination : destinations) {
                allDestination.add(new Destination(destination.getName(), destination.getGroup(), destination.getFloor(), DestinationType.INSTANCE.getTypeByName(destination.getMode()), destination.getX(), destination.getY()));
                if (Intrinsics.areEqual(destination.getMode(), DestinationType.DINING_OUTLET.getTypeName()) && !dinningOutLetGroup.contains(destination.getGroup())) {
                    dinningOutLetGroup.add(destination.getGroup());
                }
                if (Intrinsics.areEqual(destination.getMode(), DestinationType.USHER.getTypeName()) && !usherGroup.contains(destination.getGroup())) {
                    usherGroup.add(destination.getGroup());
                }
            }
        }
        Pdlog.m3273d(TAG, "loadDestinations load allDestination size = " + allDestination + " ; dinningOutLetGroup = " + dinningOutLetGroup + " , usherGroup = " + usherGroup);
    }

    public final List<String> getChargingPiles() {
        MoveActionInterface moveActionInterface;
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
            return null;
        }
        return moveActionInterface.getChargingPiles();
    }

    public final String getCurrentMapDiningOutLetChosen() {
        boolean z = true;
        Pdlog.m3273d(TAG, "getCurrentMapDiningOutLetChosen customOutlet:" + customOutlet + ' ');
        String str = customOutlet;
        if (!(str == null || str.length() == 0)) {
            return customOutlet;
        }
        SharedPreferences sharedPreferences = sp;
        String str2 = null;
        if (sharedPreferences != null) {
            str2 = sharedPreferences.getString("dining_outlet" + getSelectMapKey(), null);
        }
        String str3 = str2;
        if (str3 != null && str3.length() != 0) {
            z = false;
        }
        return (z || !dinningOutLetGroup.contains(str2)) ? (String) CollectionsKt.firstOrNull((List) dinningOutLetGroup) : str2;
    }

    public final void setCustomOutlet(String outlet) {
        customOutlet = outlet;
    }

    public final String getDashWashChosen() {
        SharedPreferences sharedPreferences = sp;
        String string = sharedPreferences != null ? sharedPreferences.getString(KEY_DASH_WASH + getSelectMapKey(), null) : null;
        ArrayList<Destination> recyclePoints = getRecyclePoints();
        String str = string;
        if (!(str == null || str.length() == 0)) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : recyclePoints) {
                if (Intrinsics.areEqual(((Destination) obj).getName(), string)) {
                    arrayList.add(obj);
                }
            }
            if (!arrayList.isEmpty()) {
                return string;
            }
        }
        Destination destination = (Destination) CollectionsKt.firstOrNull((List) recyclePoints);
        if (destination != null) {
            return destination.getName();
        }
        return null;
    }

    public final boolean setDashWashChosen(String n) {
        SharedPreferences.Editor edit;
        Intrinsics.checkParameterIsNotNull(n, "n");
        ArrayList<Destination> recyclePoints = getRecyclePoints();
        ArrayList arrayList = new ArrayList();
        for (Object obj : recyclePoints) {
            if (Intrinsics.areEqual(((Destination) obj).getName(), n)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            SharedPreferences sharedPreferences = sp;
            if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                SharedPreferences.Editor putString = edit.putString(KEY_DASH_WASH + getSelectMapKey(), n);
                if (putString != null) {
                    putString.apply();
                }
            }
            return true;
        }
        Pdlog.m3274e(TAG, "setDashWashChosen : " + n + " not find");
        return false;
    }

    public final boolean setCurrentMapDiningOutLetChosen(String name) {
        SharedPreferences.Editor edit;
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (dinningOutLetGroup.contains(name)) {
            SharedPreferences sharedPreferences = sp;
            if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                SharedPreferences.Editor putString = edit.putString("dining_outlet" + getSelectMapKey(), name);
                if (putString != null) {
                    putString.apply();
                }
            }
            return true;
        }
        Pdlog.m3274e(TAG, "setCurrentMapDiningOutLetChosen : " + name + " not find ; dinningOutLetGroup = " + dinningOutLetGroup);
        return false;
    }

    public final String getCurrentMapUsherChosen() {
        SharedPreferences sharedPreferences = sp;
        String str = null;
        if (sharedPreferences != null) {
            str = sharedPreferences.getString("usher" + getSelectMapKey(), null);
        }
        String str2 = str;
        return ((str2 == null || str2.length() == 0) || !usherGroup.contains(str)) ? (String) CollectionsKt.firstOrNull((List) usherGroup) : str;
    }

    public final boolean setCurrentMapUsherChosen(String name) {
        SharedPreferences.Editor edit;
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (usherGroup.contains(name)) {
            SharedPreferences sharedPreferences = sp;
            if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                SharedPreferences.Editor putString = edit.putString("usher" + getSelectMapKey(), name);
                if (putString != null) {
                    putString.apply();
                }
            }
            return true;
        }
        Pdlog.m3274e(TAG, "setCurrentMapUsherChosen : " + name + " not find ; dinningOutLetGroup = " + dinningOutLetGroup);
        return false;
    }

    public final ArrayList<String> getUshers() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(usherGroup);
        return arrayList;
    }

    public final ArrayList<String> getDinningOutLets() {
        ArrayList<String> arrayList = new ArrayList<>();
        Pdlog.m3273d(TAG, "getDinningOutLets() = " + dinningOutLetGroup);
        arrayList.addAll(dinningOutLetGroup);
        return arrayList;
    }

    public final ArrayList<Destination> getRecyclePoints() {
        return getDestinationByTypes(CollectionsKt.arrayListOf(DestinationType.TRANSLIST, DestinationType.DISHWASHING));
    }

    public final ArrayList<Destination> getDestinationByType(DestinationType type) {
        Destination copy;
        Intrinsics.checkParameterIsNotNull(type, "type");
        ArrayList<Destination> arrayList = new ArrayList<>();
        for (Destination destination : allDestination) {
            if (destination.getType() == type) {
                copy = destination.copy((r18 & 1) != 0 ? destination.name : null, (r18 & 2) != 0 ? destination.group : null, (r18 & 4) != 0 ? destination.floor : null, (r18 & 8) != 0 ? destination.type : null, (r18 & 16) != 0 ? destination.x : 0.0d, (r18 & 32) != 0 ? destination.y : 0.0d);
                arrayList.add(copy);
            }
        }
        return arrayList;
    }

    public final ArrayList<Destination> getDestinationByTypes(ArrayList<DestinationType> type) {
        Destination copy;
        Intrinsics.checkParameterIsNotNull(type, "type");
        ArrayList<Destination> arrayList = new ArrayList<>();
        for (Destination destination : allDestination) {
            if (CollectionsKt.contains(type, destination.getType())) {
                copy = destination.copy((r18 & 1) != 0 ? destination.name : null, (r18 & 2) != 0 ? destination.group : null, (r18 & 4) != 0 ? destination.floor : null, (r18 & 8) != 0 ? destination.type : null, (r18 & 16) != 0 ? destination.x : 0.0d, (r18 & 32) != 0 ? destination.y : 0.0d);
                arrayList.add(copy);
            }
        }
        return arrayList;
    }

    public final boolean checkDestinationExist(String d) {
        Intrinsics.checkParameterIsNotNull(d, "d");
        CopyOnWriteArrayList<Destination> copyOnWriteArrayList = allDestination;
        if ((copyOnWriteArrayList instanceof Collection) && copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator<T> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((Destination) it.next()).getName(), d)) {
                return true;
            }
        }
        return false;
    }

    public final ArrayList<Destination> getAllDestination() {
        Destination copy;
        ArrayList<Destination> arrayList = new ArrayList<>();
        Iterator<T> it = allDestination.iterator();
        while (it.hasNext()) {
            copy = r3.copy((r18 & 1) != 0 ? r3.name : null, (r18 & 2) != 0 ? r3.group : null, (r18 & 4) != 0 ? r3.floor : null, (r18 & 8) != 0 ? r3.type : null, (r18 & 16) != 0 ? r3.x : 0.0d, (r18 & 32) != 0 ? ((Destination) it.next()).y : 0.0d);
            arrayList.add(copy);
        }
        return arrayList;
    }

    public final void addSwitchMapResultListener(SwitchMapResultListener switchMapResultListener) {
        Intrinsics.checkParameterIsNotNull(switchMapResultListener, "switchMapResultListener");
        getSwitchMapResultListeners().addNotSame$module_robot_mirsdk_wrapper_release(switchMapResultListener);
    }

    public final void removeSwitchMapResultListener(SwitchMapResultListener switchMapResultListener) {
        Intrinsics.checkParameterIsNotNull(switchMapResultListener, "switchMapResultListener");
        getSwitchMapResultListeners().remove$module_robot_mirsdk_wrapper_release(switchMapResultListener);
    }

    public final MapPackageConfig getMapNameList() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("loadMapData:  ");
        Gson gson2 = gson;
        SDKInterface sDKInterface = mirSdk;
        sb.append(gson2.toJson(sDKInterface != null ? sDKInterface.getPdmapNameList() : null));
        Log.i(str, sb.toString());
        SDKInterface sDKInterface2 = mirSdk;
        if (sDKInterface2 != null) {
            return sDKInterface2.getPdmapNameList();
        }
        return null;
    }

    public final void switchMap(String floor, String name) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(TAG, "switchCurrentMap : floor = " + floor + "; name = " + name + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$switchMap$1(floor, name, null), 3, null);
    }

    public final void switchFloor(String floor) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Pdlog.m3273d(TAG, "switchFloor : floor = " + floor + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$switchFloor$1(floor, null), 3, null);
    }

    public final void switchDefMap(String floor, String name) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(TAG, "switchDefMap : floor = " + floor + "; name = " + name + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$switchDefMap$1(floor, name, null), 3, null);
    }

    public final List<Destination> getSpecifyMapDestinations(String mapName) {
        MoveActionInterface moveActionInterface;
        List<com.pudutech.mirsdk.aidl.serialize.Destination> specifyMapDestinations;
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = hashMap;
        hashMap2.put("floor", "0");
        hashMap2.put("name", mapName);
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null || (specifyMapDestinations = moveActionInterface.getSpecifyMapDestinations(gson.toJson(hashMap))) == null) {
            return null;
        }
        List<com.pudutech.mirsdk.aidl.serialize.Destination> list = specifyMapDestinations;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (com.pudutech.mirsdk.aidl.serialize.Destination destination : list) {
            arrayList.add(new Destination(destination.getName(), destination.getGroup(), destination.getFloor(), DestinationType.INSTANCE.getTypeByName(destination.getMode()), destination.getX(), destination.getY()));
        }
        return arrayList;
    }

    public static /* synthetic */ void checkLocationInit$default(RobotMapManager robotMapManager, Function1 function1, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 5000;
        }
        robotMapManager.checkLocationInit(function1, j);
    }

    public final void checkLocationInit(Function1<? super Boolean, Unit> cb, long checkTime) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        Pdlog.m3273d(TAG, "checkLocationInit : cb = " + cb + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$checkLocationInit$1(checkTime / ((long) 500), cb, null), 3, null);
    }

    public final boolean isLocalizationSuccess() {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            return sDKInterface.isRelocalizationSuccess();
        }
        return false;
    }

    public final LocalizationStatus getLocationStatus() {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            return sDKInterface.getLocalizationStatus();
        }
        return null;
    }

    public final LinkedHashMap<String, ArrayList<String>> getAllTableGroup() {
        LinkedHashMap<String, ArrayList<String>> linkedHashMap = new LinkedHashMap<>();
        CopyOnWriteArrayList<Destination> copyOnWriteArrayList = allDestination;
        if (copyOnWriteArrayList != null) {
            for (Destination destination : copyOnWriteArrayList) {
                if (destination.getType() == DestinationType.TABLE || destination.getType() == DestinationType.UNKNOWN) {
                    String group = destination.getGroup();
                    if (!(group == null || group.length() == 0)) {
                        ArrayList<String> arrayList = linkedHashMap.get(destination.getGroup());
                        if (arrayList == null) {
                            arrayList = new ArrayList<>();
                            linkedHashMap.put(destination.getGroup(), arrayList);
                        }
                        arrayList.add(destination.getName());
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public final void reloadLocation() {
        Pdlog.m3273d(TAG, "reloadLocation ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$reloadLocation$1(null), 3, null);
    }

    public final void getMapDrawPath(String mapName, Function1<? super MapDrawPathLoader, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        if (!mapDrawPathM.containsKey(mapName)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$getMapDrawPath$1(mapName, cb, null), 3, null);
            return;
        }
        MapDrawPathLoader mapDrawPathLoader = mapDrawPathM.get(mapName);
        if (mapDrawPathLoader == null) {
            Intrinsics.throwNpe();
        }
        cb.invoke(mapDrawPathLoader);
    }

    public final void getCruisePath(Function1<? super MapCruisePathLoader, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        String defaultMapName = getDefaultMapName();
        Pdlog.m3274e(TAG, "getCruisePath defaultPdmap = " + defaultMapName);
        String str = defaultMapName;
        if (str == null || str.length() == 0) {
            return;
        }
        if (!mapCruiseLineM.containsKey(defaultMapName)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$getCruisePath$1(defaultMapName, cb, null), 3, null);
            return;
        }
        Pdlog.m3273d(TAG, "getCruisePath mapCruiseLineM = " + mapCruiseLineM);
        cb.invoke(mapCruiseLineM.get(defaultMapName));
    }

    public final void getCruisePath(String mapName, Function1<? super MapCruisePathLoader, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        String defaultMapName = getDefaultMapName();
        Pdlog.m3274e(TAG, "getCruisePath defaultPdmap = " + defaultMapName);
        if (!mapCruiseLineM.containsKey(mapName)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$getCruisePath$2(mapName, cb, null), 3, null);
        } else {
            cb.invoke(mapCruiseLineM.get(mapName));
        }
    }

    private final void initSp() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        sp = context2.getSharedPreferences(FILE_NAME, 0);
    }

    private final String getSelectMapKey() {
        MapPackageConfig pdmapNameList;
        List<MapListConfig> list;
        StringBuilder sb = new StringBuilder();
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null && (pdmapNameList = sDKInterface.getPdmapNameList()) != null && (list = pdmapNameList.getList()) != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                sb.append(((MapListConfig) it.next()).getDef());
            }
        }
        return sb.toString();
    }

    public final Double getCallElevatorDistance() {
        MoveActionInterface moveActionInterface;
        SDKInterface sDKInterface = mirSdk;
        Double valueOf = (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) ? null : Double.valueOf(moveActionInterface.getCallElevatorDistance());
        Pdlog.m3273d(TAG, "moveActionInterface : distance=" + valueOf);
        return valueOf;
    }

    public final void setCallElevatorDistance(double distance) {
        MoveActionInterface moveActionInterface;
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
            return;
        }
        moveActionInterface.setCallElevatorDistance(distance);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0066 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0020 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e0 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getCurrentMapAtlasPath() {
        String str;
        MapPackageConfig pdmapNameList;
        List<MapListConfig> list;
        boolean z;
        MapPackageConfig pdmapNameList2;
        MapPackageConfig pdmapNameList3;
        try {
            SDKInterface sDKInterface = mirSdk;
            if (sDKInterface == null || (pdmapNameList = sDKInterface.getPdmapNameList()) == null || (list = pdmapNameList.getList()) == null) {
                str = null;
            } else {
                ArrayList<MapListConfig> arrayList = new ArrayList();
                for (Object obj : list) {
                    MapListConfig mapListConfig = (MapListConfig) obj;
                    SDKInterface sDKInterface2 = mirSdk;
                    if (Intrinsics.areEqual((sDKInterface2 == null || (pdmapNameList3 = sDKInterface2.getPdmapNameList()) == null) ? null : pdmapNameList3.getDef_map(), mapListConfig.getDef())) {
                        SDKInterface sDKInterface3 = mirSdk;
                        if (Intrinsics.areEqual((sDKInterface3 == null || (pdmapNameList2 = sDKInterface3.getPdmapNameList()) == null) ? null : pdmapNameList2.getDef_floor(), mapListConfig.getFloor())) {
                            z = true;
                            if (!z) {
                                arrayList.add(obj);
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                    }
                }
                str = null;
                for (MapListConfig mapListConfig2 : arrayList) {
                    try {
                        Map<String, String> list2 = mapListConfig2.getList();
                        if (list2 == null) {
                            Intrinsics.throwNpe();
                        }
                        str = list2.get(mapListConfig2.getDef());
                    } catch (Exception e) {
                        e = e;
                        Pdlog.m3274e(TAG, "getCurrentMapAtlasPath : " + Log.getStackTraceString(e));
                        if (str instanceof String) {
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
            str = null;
        }
        if (str instanceof String) {
            return null;
        }
        return (str + File.separator) + "ATLAS_DATA";
    }

    public final void getMaps(Function1<? super ArrayList<String>, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        Pdlog.m3273d(TAG, "getMaps");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotMapManager$getMaps$1(cb, null), 3, null);
    }

    public final LocateCase getLocationCase() {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            Pdlog.m3273d(TAG, "locateCase:" + sDKInterface.getLocateCase());
            LocateCase locateCase2 = sDKInterface.getLocateCase();
            Intrinsics.checkExpressionValueIsNotNull(locateCase2, "it.locateCase");
            return locateCase2;
        }
        return locateCase;
    }

    public final ArrayList<String> getRelocationPoints() {
        ArrayList<String> arrayList = new ArrayList<>();
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            arrayList.addAll(sDKInterface.getRelocationPoints());
        }
        return arrayList;
    }

    public final void addRelocationPoint(String relocatePoint) {
        Intrinsics.checkParameterIsNotNull(relocatePoint, "relocatePoint");
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.addRelocationPoint(relocatePoint);
        }
    }

    public final void reloadMap() {
        loadMapData();
    }

    public final void reloadMapByName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "str");
        Pdlog.m3273d(TAG, "reloadMapByName: str=" + str);
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.reloadMap(str);
        }
    }
}
