package com.pudutech.antichannelconflict;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.pudutech.antichannelconflict.escape.EscapeManager;
import com.pudutech.antichannelconflict.escape.listener.EscapeDetectListener;
import com.pudutech.antichannelconflict.escape.util.EscapeStatus;
import com.pudutech.antichannelconflict.escape.util.ProductType;
import com.pudutech.antichannelconflict.location.LocationChangeListener;
import com.pudutech.antichannelconflict.location.PDLocationManager;
import com.pudutech.antichannelconflict.location.util.LocateInitStatus;
import com.pudutech.antichannelconflict.location.util.LocationType;
import com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager;
import com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener;
import com.pudutech.antichannelconflict.upgrade.util.UpgradeStatus;
import com.pudutech.base.Pdlog;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PDEscapeManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000}\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0003\u000e\u001a\u001d\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0012J\u000e\u0010$\u001a\u00020\"2\u0006\u0010#\u001a\u00020 J\u000e\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0004J\u0006\u0010'\u001a\u00020(J\r\u0010)\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010*J\r\u0010+\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010*J/\u0010,\u001a\u00020\"2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010-\u001a\u00020.2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\"00¢\u0006\u0002\b1J\u0006\u00102\u001a\u00020\"J\u000e\u00103\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0012J\u000e\u00104\u001a\u00020\"2\u0006\u0010#\u001a\u00020 J\u0006\u00105\u001a\u00020\"J\u0006\u00106\u001a\u00020\"J\b\u00107\u001a\u00020\"H\u0002J\b\u00108\u001a\u00020\"H\u0002J\u0006\u00109\u001a\u00020\"J\u0018\u0010:\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00182\b\b\u0002\u0010;\u001a\u00020<J\b\u0010=\u001a\u00020\"H\u0002J\u000e\u0010>\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0011j\b\u0012\u0004\u0012\u00020\u0018`\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u001e\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020 0\u0011j\b\u0012\u0004\u0012\u00020 `\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/PDEscapeManager;", "", "()V", "TAG", "", "antiConfigBuilder", "Lcom/pudutech/antichannelconflict/AntiConfig;", "getAntiConfigBuilder", "()Lcom/pudutech/antichannelconflict/AntiConfig;", "setAntiConfigBuilder", "(Lcom/pudutech/antichannelconflict/AntiConfig;)V", "context", "Landroid/content/Context;", "escapeListener", "com/pudutech/antichannelconflict/PDEscapeManager$escapeListener$1", "Lcom/pudutech/antichannelconflict/PDEscapeManager$escapeListener$1;", "escapeListeners", "Ljava/util/ArrayList;", "Lcom/pudutech/antichannelconflict/escape/listener/EscapeDetectListener;", "Lkotlin/collections/ArrayList;", "lastLatitute", "", "Ljava/lang/Double;", "listeners", "Lcom/pudutech/antichannelconflict/location/LocationChangeListener;", "locateListener", "com/pudutech/antichannelconflict/PDEscapeManager$locateListener$1", "Lcom/pudutech/antichannelconflict/PDEscapeManager$locateListener$1;", "periodStatusListener", "com/pudutech/antichannelconflict/PDEscapeManager$periodStatusListener$1", "Lcom/pudutech/antichannelconflict/PDEscapeManager$periodStatusListener$1;", "periodStatusListeners", "Lcom/pudutech/antichannelconflict/upgrade/listener/PeriodStatusListener;", "addEscapeDetectListener", "", "listener", "addUpdatePeriodListener", "changeSupportBTS", "model", "getLastLockStatus", "", "getLatitude", "()Ljava/lang/Double;", "getLongitude", "init", "product", "Lcom/pudutech/antichannelconflict/escape/util/ProductType;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "release", "removeEscapeDetectListener", "removeUpdatePeriodListener", "start", "startCheckLockStatus", "startCheckLockTask", "startCheckPeriodOf4G", "startDetectEscape", "startLocation", "locationType", "Lcom/pudutech/antichannelconflict/location/util/LocationType;", "stopEscapeTask", "stopLocation", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PDEscapeManager {
    public static AntiConfig antiConfigBuilder;
    private static Context context;
    private static Double lastLatitute;
    public static final PDEscapeManager INSTANCE = new PDEscapeManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static ArrayList<LocationChangeListener> listeners = new ArrayList<>();
    private static ArrayList<EscapeDetectListener> escapeListeners = new ArrayList<>();
    private static PDEscapeManager$locateListener$1 locateListener = new LocationChangeListener() { // from class: com.pudutech.antichannelconflict.PDEscapeManager$locateListener$1
        @Override // com.pudutech.antichannelconflict.location.LocationChangeListener
        public void onLocationChanged(AMapLocation location, LocationType type) {
            String str;
            ArrayList arrayList;
            Double d;
            Intrinsics.checkParameterIsNotNull(location, "location");
            Intrinsics.checkParameterIsNotNull(type, "type");
            PDEscapeManager pDEscapeManager = PDEscapeManager.INSTANCE;
            str = PDEscapeManager.TAG;
            Pdlog.m3273d(str, "onLocationChanged", type, location);
            PDEscapeManager pDEscapeManager2 = PDEscapeManager.INSTANCE;
            arrayList = PDEscapeManager.listeners;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((LocationChangeListener) it.next()).onLocationChanged(location, type);
            }
            if (type != LocationType.LOCATE_FROM_HARDWARE_BTS) {
                PDEscapeManager pDEscapeManager3 = PDEscapeManager.INSTANCE;
                d = PDEscapeManager.lastLatitute;
                if (d != null) {
                    return;
                }
            }
            PDEscapeManager.INSTANCE.startDetectEscape();
        }

        @Override // com.pudutech.antichannelconflict.location.LocationChangeListener
        public void onLocateInit(LocateInitStatus code) {
            String str;
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(code, "code");
            PDEscapeManager pDEscapeManager = PDEscapeManager.INSTANCE;
            str = PDEscapeManager.TAG;
            Pdlog.m3273d(str, "onLocateInit", code);
            PDEscapeManager pDEscapeManager2 = PDEscapeManager.INSTANCE;
            arrayList = PDEscapeManager.listeners;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((LocationChangeListener) it.next()).onLocateInit(code);
            }
        }
    };
    private static PDEscapeManager$escapeListener$1 escapeListener = new EscapeDetectListener() { // from class: com.pudutech.antichannelconflict.PDEscapeManager$escapeListener$1
        @Override // com.pudutech.antichannelconflict.escape.listener.EscapeDetectListener
        public void onEscapeDetect(EscapeStatus status) {
            String str;
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(status, "status");
            PDEscapeManager pDEscapeManager = PDEscapeManager.INSTANCE;
            str = PDEscapeManager.TAG;
            Pdlog.m3273d(str, "onEscapeDetect", status);
            PDEscapeManager pDEscapeManager2 = PDEscapeManager.INSTANCE;
            arrayList = PDEscapeManager.escapeListeners;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((EscapeDetectListener) it.next()).onEscapeDetect(status);
            }
        }

        @Override // com.pudutech.antichannelconflict.escape.listener.EscapeDetectListener
        public void onPeriodUpgrade(UpgradeStatus status) {
            String str;
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(status, "status");
            PDEscapeManager pDEscapeManager = PDEscapeManager.INSTANCE;
            str = PDEscapeManager.TAG;
            Pdlog.m3273d(str, "onPeriodUpgrade", status);
            PDEscapeManager pDEscapeManager2 = PDEscapeManager.INSTANCE;
            arrayList = PDEscapeManager.escapeListeners;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((EscapeDetectListener) it.next()).onPeriodUpgrade(status);
            }
        }
    };
    private static ArrayList<PeriodStatusListener> periodStatusListeners = new ArrayList<>();
    private static final PDEscapeManager$periodStatusListener$1 periodStatusListener = new PeriodStatusListener() { // from class: com.pudutech.antichannelconflict.PDEscapeManager$periodStatusListener$1
        @Override // com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener
        public void onUpdateProgressCB(String res, String progress, UpgradeStatus status) {
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(status, "status");
            PDEscapeManager pDEscapeManager = PDEscapeManager.INSTANCE;
            arrayList = PDEscapeManager.periodStatusListeners;
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((PeriodStatusListener) it.next()).onUpdateProgressCB(res, progress, status);
                }
            }
        }

        @Override // com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener
        public void onCheckPeriodCallback(UpgradeStatus status) {
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(status, "status");
            PDEscapeManager pDEscapeManager = PDEscapeManager.INSTANCE;
            arrayList = PDEscapeManager.periodStatusListeners;
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((PeriodStatusListener) it.next()).onCheckPeriodCallback(status);
                }
            }
        }

        @Override // com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener
        public void onObtainBTS(String bts) {
            Intrinsics.checkParameterIsNotNull(bts, "bts");
            PDLocationManager.INSTANCE.startLocateFromBts(bts);
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LocationType.values().length];

        static {
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_SDK.ordinal()] = 1;
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_HARDWARE.ordinal()] = 2;
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_HARDWARE_WIFI.ordinal()] = 3;
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_HARDWARE_BTS.ordinal()] = 4;
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_ALL.ordinal()] = 5;
        }
    }

    private PDEscapeManager() {
    }

    public final AntiConfig getAntiConfigBuilder() {
        AntiConfig antiConfig = antiConfigBuilder;
        if (antiConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        return antiConfig;
    }

    public final void setAntiConfigBuilder(AntiConfig antiConfig) {
        Intrinsics.checkParameterIsNotNull(antiConfig, "<set-?>");
        antiConfigBuilder = antiConfig;
    }

    public final void init(Context context2, ProductType product, Function1<? super AntiConfig, Unit> block) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(product, "product");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Pdlog.m3273d(TAG, "init");
        Context applicationContext = context2.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        context = applicationContext;
        Context applicationContext2 = context2.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "context.applicationContext");
        AntiConfig antiConfig = new AntiConfig(applicationContext2);
        block.invoke(antiConfig);
        antiConfigBuilder = antiConfig;
        AntiConfig antiConfig2 = antiConfigBuilder;
        if (antiConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        antiConfig2.setProduct_type$AntiChannelConflict_release(product);
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = "init";
        AntiConfig antiConfig3 = antiConfigBuilder;
        if (antiConfig3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        objArr[1] = antiConfig3.toString();
        Pdlog.m3273d(str, objArr);
        PDLocationManager.INSTANCE.setLocationListener(locateListener);
        EscapeManager.INSTANCE.addEscapeDetectListener(escapeListener);
        PeriodOf4GManager.INSTANCE.setPeriodStatusListener(periodStatusListener);
        PeriodOf4GManager periodOf4GManager = PeriodOf4GManager.INSTANCE;
        Context context3 = context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        AntiConfig antiConfig4 = antiConfigBuilder;
        if (antiConfig4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        periodOf4GManager.init(context3, antiConfig4.getAutoUpdate());
        PDLocationManager pDLocationManager = PDLocationManager.INSTANCE;
        Context context4 = context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        AntiConfig antiConfig5 = antiConfigBuilder;
        if (antiConfig5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        LocationType defaultLocateType = antiConfig5.getDefaultLocateType();
        AntiConfig antiConfig6 = antiConfigBuilder;
        if (antiConfig6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        int locate_retry_times = antiConfig6.getLocate_retry_times();
        AntiConfig antiConfig7 = antiConfigBuilder;
        if (antiConfig7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        pDLocationManager.init(context4, defaultLocateType, locate_retry_times, antiConfig7.getAmap_key());
        startCheckPeriodOf4G();
    }

    public final void start() {
        startCheckLockStatus();
        startDetectEscape();
        startCheckLockTask();
    }

    public static /* synthetic */ void startLocation$default(PDEscapeManager pDEscapeManager, LocationChangeListener locationChangeListener, LocationType locationType, int i, Object obj) {
        if ((i & 2) != 0) {
            locationType = LocationType.LOCATE_FROM_HARDWARE;
        }
        pDEscapeManager.startLocation(locationChangeListener, locationType);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startLocation(LocationChangeListener listener, LocationType locationType) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Intrinsics.checkParameterIsNotNull(locationType, "locationType");
        Pdlog.m3273d(TAG, "startLocation locationType:" + locationType);
        listeners.add(listener);
        AntiConfig antiConfig = antiConfigBuilder;
        if (antiConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        antiConfig.setDefaultLocateType(locationType);
        int i = WhenMappings.$EnumSwitchMapping$0[locationType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                PDLocationManager.INSTANCE.startLocateFromWifi();
            } else if (i == 3) {
                PDLocationManager.INSTANCE.startLocateFromWifi();
            } else if (i != 4) {
                if (i == 5) {
                    PDLocationManager.INSTANCE.startLocationFromSDK();
                    PDLocationManager.INSTANCE.startLocateFromWifi();
                }
            }
            z = true;
            if (z) {
                return;
            }
            if ((!StringsKt.isBlank(PeriodOf4GManager.INSTANCE.get4GDevice())) && PeriodOf4GManager.INSTANCE.checkIfPeriod4GSupportBTS()) {
                PeriodOf4GManager.INSTANCE.resetBTS();
                PeriodOf4GManager.INSTANCE.startObtainBTS();
                return;
            }
            Iterator<T> it = listeners.iterator();
            while (it.hasNext()) {
                ((LocationChangeListener) it.next()).onLocateInit(LocateInitStatus.LOCATE_INIT_FAIL_BTS);
            }
            String str = TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("period_4g_usb is ");
            AntiConfig antiConfig2 = antiConfigBuilder;
            if (antiConfig2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
            }
            sb.append(antiConfig2);
            sb.append(".period_4g_usb or not support BTS Locate");
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            return;
        }
        PDLocationManager.INSTANCE.startLocationFromSDK();
        z = false;
        if (z) {
        }
    }

    public final void stopLocation(LocationChangeListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        listeners.remove(listener);
        PDLocationManager.INSTANCE.setLocationListener(null);
    }

    private final void startCheckPeriodOf4G() {
        String str = TAG;
        Object[] objArr = new Object[3];
        objArr[0] = "startCheckPeriodOf4G";
        AntiConfig antiConfig = antiConfigBuilder;
        if (antiConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        objArr[1] = antiConfig.getPid();
        AntiConfig antiConfig2 = antiConfigBuilder;
        if (antiConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        objArr[2] = antiConfig2.getVid();
        Pdlog.m3273d(str, objArr);
        PeriodOf4GManager periodOf4GManager = PeriodOf4GManager.INSTANCE;
        AntiConfig antiConfig3 = antiConfigBuilder;
        if (antiConfig3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        String pid = antiConfig3.getPid();
        AntiConfig antiConfig4 = antiConfigBuilder;
        if (antiConfig4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        periodOf4GManager.startCheckPeriod(pid, antiConfig4.getVid());
    }

    public final void startCheckLockStatus() {
        Pdlog.m3273d(TAG, "startCheckLockStatus");
        EscapeManager escapeManager = EscapeManager.INSTANCE;
        AntiConfig antiConfig = antiConfigBuilder;
        if (antiConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        String mac = antiConfig.getMac();
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        escapeManager.checkLockStatus(mac, context2);
    }

    public final void startDetectEscape() {
        Pdlog.m3273d(TAG, "startDetectEscape");
        lastLatitute = getLatitude();
        EscapeManager escapeManager = EscapeManager.INSTANCE;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        AntiConfig antiConfig = antiConfigBuilder;
        if (antiConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        String mac = antiConfig.getMac();
        Double longitude = getLongitude();
        Double latitude = getLatitude();
        AntiConfig antiConfig2 = antiConfigBuilder;
        if (antiConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        boolean autoLock = antiConfig2.getAutoLock();
        String bts = PeriodOf4GManager.INSTANCE.getBTS();
        AntiConfig antiConfig3 = antiConfigBuilder;
        if (antiConfig3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        ProductType product_type = antiConfig3.getProduct_type();
        AntiConfig antiConfig4 = antiConfigBuilder;
        if (antiConfig4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        String softVersion = antiConfig4.getSoftVersion();
        Intrinsics.checkExpressionValueIsNotNull(softVersion, "antiConfigBuilder.softVersion");
        escapeManager.checkEscapeStatus(context2, mac, longitude, latitude, autoLock, bts, product_type, softVersion);
    }

    public final void addEscapeDetectListener(EscapeDetectListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "addEscapeDetectListener", listener);
        if (escapeListeners.contains(listener)) {
            return;
        }
        escapeListeners.add(listener);
    }

    public final void removeEscapeDetectListener(EscapeDetectListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "removeEscapeDetectListener", listener);
        escapeListeners.remove(listener);
    }

    private final void startCheckLockTask() {
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("startCheckLockTask");
        AntiConfig antiConfig = antiConfigBuilder;
        if (antiConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        sb.append(antiConfig.getPeriod());
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        EscapeManager escapeManager = EscapeManager.INSTANCE;
        AntiConfig antiConfig2 = antiConfigBuilder;
        if (antiConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("antiConfigBuilder");
        }
        escapeManager.startCheckLockTask(antiConfig2.getPeriod());
    }

    private final void stopEscapeTask() {
        Pdlog.m3273d(TAG, "stopEscapeTask");
        EscapeManager.INSTANCE.stopCheckLockTask();
    }

    public final void release() {
        Pdlog.m3273d(TAG, "release");
        stopEscapeTask();
        PDLocationManager.INSTANCE.setLocationListener(null);
        EscapeManager.INSTANCE.removeEscapeDetectListener();
        listeners.clear();
    }

    public final Double getLatitude() {
        return PDLocationManager.INSTANCE.getCurrenLatitude();
    }

    public final Double getLongitude() {
        return PDLocationManager.INSTANCE.getCurrentLongitude();
    }

    public final void addUpdatePeriodListener(PeriodStatusListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (!periodStatusListeners.contains(listener)) {
            periodStatusListeners.add(listener);
        }
        Pdlog.m3273d(TAG, "addUpdatePeriodListener");
    }

    public final void removeUpdatePeriodListener(PeriodStatusListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "removeUpdatePeriodListener", listener);
        periodStatusListeners.remove(listener);
    }

    public final void changeSupportBTS(String model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        PeriodOf4GManager.INSTANCE.changeSupportBTS(model);
    }

    public final boolean getLastLockStatus() {
        EscapeManager escapeManager = EscapeManager.INSTANCE;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Context applicationContext = context2.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        return escapeManager.getLastEscapeStatus(applicationContext);
    }
}
