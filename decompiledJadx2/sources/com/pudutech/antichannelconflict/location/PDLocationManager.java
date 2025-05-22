package com.pudutech.antichannelconflict.location;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.antichannelconflict.location.network.LocationData;
import com.pudutech.antichannelconflict.location.network.LocationReq;
import com.pudutech.antichannelconflict.location.network.LocationUtil;
import com.pudutech.antichannelconflict.location.util.LocateInitStatus;
import com.pudutech.antichannelconflict.location.util.LocationType;
import com.pudutech.base.Pdlog;
import defpackage.CHARSET;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: PDLocationManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010 \u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u0004\u0018\u00010\u0010J\r\u0010#\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010!J\b\u0010$\u001a\u00020\u001aH\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\u001d\u0010)\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010*J(\u0010+\u001a\u00020&2\u0006\u0010\b\u001a\u00020\n2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010-\u001a\u00020\u0007J\b\u0010.\u001a\u00020&H\u0002J\u0011\u0010/\u001a\u00020&H\u0082@ø\u0001\u0000¢\u0006\u0002\u00100J\u0010\u00101\u001a\u00020&2\b\u00102\u001a\u0004\u0018\u00010\u0012J\u000e\u00103\u001a\u00020&2\u0006\u00104\u001a\u00020\u0007J\u0006\u00105\u001a\u00020&J\u0006\u00106\u001a\u00020&J\u0010\u00107\u001a\u00020&2\u0006\u00108\u001a\u000209H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006:"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/location/PDLocationManager;", "", "()V", "LOCATION_HTTP_TIMEOUT", "", "LOCATION_INTERVAL", "TAG", "", "context", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", TransferTable.COLUMN_KEY, "latitude", "", "Ljava/lang/Double;", "locateType", "Lcom/pudutech/antichannelconflict/location/util/LocationType;", "locationChangeListener", "Lcom/pudutech/antichannelconflict/location/LocationChangeListener;", "longitude", "mLocationChangeListener", "mLocationClient", "Lcom/amap/api/location/AMapLocationClient;", "mLocationListener", "Lcom/amap/api/location/AMapLocationListener;", "mLocationOption", "Lcom/amap/api/location/AMapLocationClientOption;", "retryCount", "", "retryTimes", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getCurrenLatitude", "()Ljava/lang/Double;", "getCurrentLocateType", "getCurrentLongitude", "getDefaultOption", "getLocationFromAMap", "", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/antichannelconflict/location/network/LocationReq;", "getWifiMacList", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "init", "type", "ampKey", "initLocation", "locateFromHardware", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setLocationListener", "listener", "startLocateFromBts", "mbts", "startLocateFromWifi", "startLocationFromSDK", "stopLocation", RequestParameters.SUBRESOURCE_LOCATION, "Lcom/amap/api/location/AMapLocation;", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PDLocationManager {
    private static final long LOCATION_HTTP_TIMEOUT = 30000;
    private static final long LOCATION_INTERVAL = 10000;
    private static final String TAG = "PDLocationManager";
    private static WeakReference<Context> context;
    private static Double latitude;
    private static LocationType locateType;
    private static LocationChangeListener locationChangeListener;
    private static Double longitude;
    private static AMapLocationClient mLocationClient;
    private static AMapLocationClientOption mLocationOption;
    private static int retryCount;
    public static final PDLocationManager INSTANCE = new PDLocationManager();
    private static String key = "";
    private static int retryTimes = 5;
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private static LocationChangeListener mLocationChangeListener = new LocationChangeListener() { // from class: com.pudutech.antichannelconflict.location.PDLocationManager$mLocationChangeListener$1
        @Override // com.pudutech.antichannelconflict.location.LocationChangeListener
        public void onLocationChanged(AMapLocation location, LocationType type) {
            LocationChangeListener locationChangeListener2;
            Intrinsics.checkParameterIsNotNull(location, "location");
            Intrinsics.checkParameterIsNotNull(type, "type");
            Pdlog.m3273d("PDLocationManager", "mLocationChangeListener onLocationChanged:" + location + ' ' + type + ' ');
            PDLocationManager pDLocationManager = PDLocationManager.INSTANCE;
            PDLocationManager.longitude = Double.valueOf(location.getLongitude());
            PDLocationManager pDLocationManager2 = PDLocationManager.INSTANCE;
            PDLocationManager.latitude = Double.valueOf(location.getLatitude());
            PDLocationManager pDLocationManager3 = PDLocationManager.INSTANCE;
            PDLocationManager.locateType = type;
            PDLocationManager pDLocationManager4 = PDLocationManager.INSTANCE;
            locationChangeListener2 = PDLocationManager.locationChangeListener;
            if (locationChangeListener2 != null) {
                locationChangeListener2.onLocationChanged(location, type);
            }
        }

        @Override // com.pudutech.antichannelconflict.location.LocationChangeListener
        public void onLocateInit(LocateInitStatus code) {
            LocationChangeListener locationChangeListener2;
            Intrinsics.checkParameterIsNotNull(code, "code");
            Pdlog.m3273d("PDLocationManager", "mLocationChangeListener onLocateInit:" + code + "  ");
            PDLocationManager pDLocationManager = PDLocationManager.INSTANCE;
            locationChangeListener2 = PDLocationManager.locationChangeListener;
            if (locationChangeListener2 != null) {
                locationChangeListener2.onLocateInit(code);
            }
        }
    };
    private static AMapLocationListener mLocationListener = new AMapLocationListener() { // from class: com.pudutech.antichannelconflict.location.PDLocationManager$mLocationListener$1
        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            LocationChangeListener locationChangeListener2;
            if (aMapLocation != null) {
                Pdlog.m3273d("PDLocationManager", "location code " + aMapLocation.getErrorCode() + " locationType " + aMapLocation.getLocationType() + " latitude " + aMapLocation.getLatitude() + " longitude " + aMapLocation.getLongitude() + " accuracy " + aMapLocation.getAccuracy() + " address " + aMapLocation.getAddress());
                if (aMapLocation.getLatitude() != 0.0d && aMapLocation.getLongitude() != 0.0d) {
                    PDLocationManager pDLocationManager = PDLocationManager.INSTANCE;
                    locationChangeListener2 = PDLocationManager.mLocationChangeListener;
                    if (locationChangeListener2 != null) {
                        locationChangeListener2.onLocationChanged(aMapLocation, LocationType.LOCATE_FROM_SDK);
                        locationChangeListener2.onLocateInit(LocateInitStatus.LOCATE_INIT_SUCCESS);
                    }
                }
                PDLocationManager.INSTANCE.stopLocation(aMapLocation);
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LocationType.values().length];

        static {
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_SDK.ordinal()] = 1;
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_HARDWARE_WIFI.ordinal()] = 2;
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_HARDWARE.ordinal()] = 3;
            $EnumSwitchMapping$0[LocationType.LOCATE_FROM_ALL.ordinal()] = 4;
        }
    }

    static {
        Pdlog.m3273d(TAG, "AMap Location init ");
    }

    private PDLocationManager() {
    }

    public static final /* synthetic */ WeakReference access$getContext$p(PDLocationManager pDLocationManager) {
        WeakReference<Context> weakReference = context;
        if (weakReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return weakReference;
    }

    public static /* synthetic */ void init$default(PDLocationManager pDLocationManager, Context context2, LocationType locationType, int i, String str, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str = "";
        }
        pDLocationManager.init(context2, locationType, i, str);
    }

    public final void init(Context context2, LocationType type, int retryTimes2, String ampKey) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(ampKey, "ampKey");
        Pdlog.m3273d(TAG, "init type;" + type + " retryTimes:" + retryTimes2 + " Key:" + CHARSET.encryptTry(ampKey));
        context = new WeakReference<>(context2);
        retryTimes = retryTimes2;
        key = ampKey;
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PDLocationManager$init$1(type, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ba A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object locateFromHardware(Continuation<? super Unit> continuation) {
        PDLocationManager$locateFromHardware$1 pDLocationManager$locateFromHardware$1;
        int i;
        PDLocationManager pDLocationManager;
        String str;
        String str2;
        if (continuation instanceof PDLocationManager$locateFromHardware$1) {
            pDLocationManager$locateFromHardware$1 = (PDLocationManager$locateFromHardware$1) continuation;
            if ((pDLocationManager$locateFromHardware$1.label & Integer.MIN_VALUE) != 0) {
                pDLocationManager$locateFromHardware$1.label -= Integer.MIN_VALUE;
                Object obj = pDLocationManager$locateFromHardware$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pDLocationManager$locateFromHardware$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(TAG, "locateFromHardware");
                    PDLocationManager$locateFromHardware$wifiMacs$1 pDLocationManager$locateFromHardware$wifiMacs$1 = new PDLocationManager$locateFromHardware$wifiMacs$1(null);
                    pDLocationManager$locateFromHardware$1.L$0 = this;
                    pDLocationManager$locateFromHardware$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(5000L, pDLocationManager$locateFromHardware$wifiMacs$1, pDLocationManager$locateFromHardware$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pDLocationManager = this;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        String str3 = (String) pDLocationManager$locateFromHardware$1.L$1;
                        PDLocationManager pDLocationManager2 = (PDLocationManager) pDLocationManager$locateFromHardware$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        str = str3;
                        pDLocationManager = pDLocationManager2;
                        pDLocationManager$locateFromHardware$1.L$0 = pDLocationManager;
                        pDLocationManager$locateFromHardware$1.L$1 = str;
                        pDLocationManager$locateFromHardware$1.label = 3;
                        if (pDLocationManager.locateFromHardware(pDLocationManager$locateFromHardware$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    pDLocationManager = (PDLocationManager) pDLocationManager$locateFromHardware$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                str = (String) obj;
                LocationUtil.INSTANCE.init();
                str2 = str;
                if (!(str2 != null || StringsKt.isBlank(str2))) {
                    Pdlog.m3273d(TAG, "locateFromHardware can not get wifi list");
                    pDLocationManager$locateFromHardware$1.L$0 = pDLocationManager;
                    pDLocationManager$locateFromHardware$1.L$1 = str;
                    pDLocationManager$locateFromHardware$1.label = 2;
                    if (DelayKt.delay(5000L, pDLocationManager$locateFromHardware$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pDLocationManager$locateFromHardware$1.L$0 = pDLocationManager;
                    pDLocationManager$locateFromHardware$1.L$1 = str;
                    pDLocationManager$locateFromHardware$1.label = 3;
                    if (pDLocationManager.locateFromHardware(pDLocationManager$locateFromHardware$1) == coroutine_suspended) {
                    }
                    return Unit.INSTANCE;
                }
                Pdlog.m3273d(TAG, "locateFromHardware from wifi");
                pDLocationManager.getLocationFromAMap(new LocationReq(key, 1, null, null, null, null, null, null, null, null, null, null, null, str, null, 24572, null));
                return Unit.INSTANCE;
            }
        }
        pDLocationManager$locateFromHardware$1 = new PDLocationManager$locateFromHardware$1(this, continuation);
        Object obj2 = pDLocationManager$locateFromHardware$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pDLocationManager$locateFromHardware$1.label;
        if (i != 0) {
        }
        str = (String) obj2;
        LocationUtil.INSTANCE.init();
        str2 = str;
        if (!(str2 != null || StringsKt.isBlank(str2))) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getLocationFromAMap(final LocationReq req) {
        Pdlog.m3273d(TAG, "getLocationFromAMap bts:" + req.getBts() + " accessType:" + req.getAccesstype() + " macs:" + req.getMacs());
        if (Intrinsics.areEqual(req.getKey(), "")) {
            LocationChangeListener locationChangeListener2 = mLocationChangeListener;
            if (locationChangeListener2 != null) {
                locationChangeListener2.onLocateInit(LocateInitStatus.LOCATE_INIT_FAIL);
            }
            Pdlog.m3273d(TAG, "key is '" + key + "' ,please set key first");
            return;
        }
        LocationUtil.INSTANCE.startLocate(req, new Function2<Integer, LocationData, Unit>() { // from class: com.pudutech.antichannelconflict.location.PDLocationManager$getLocationFromAMap$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, LocationData locationData) {
                invoke(num.intValue(), locationData);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, LocationData locationData) {
                int i2;
                int i3;
                int i4;
                LocationChangeListener locationChangeListener3;
                LocationChangeListener locationChangeListener4;
                String str;
                String str2;
                String location;
                if (i == 200) {
                    List split$default = (locationData == null || (location = locationData.getLocation()) == null) ? null : StringsKt.split$default((CharSequence) location, new String[]{","}, false, 0, 6, (Object) null);
                    AMapLocation aMapLocation = new AMapLocation("");
                    if (locationData != null) {
                        aMapLocation.setAdCode(locationData.getAdcode());
                        aMapLocation.setCity(locationData.getCity());
                        aMapLocation.setCityCode(locationData.getCitycode());
                        aMapLocation.setProvince(locationData.getProvince());
                        aMapLocation.setStreet(locationData.getStreet());
                        aMapLocation.setCountry(locationData.getCountry());
                        double d = 0.0d;
                        aMapLocation.setLongitude((split$default == null || (str2 = (String) split$default.get(0)) == null) ? 0.0d : Double.parseDouble(str2));
                        if (split$default != null && (str = (String) split$default.get(1)) != null) {
                            d = Double.parseDouble(str);
                        }
                        aMapLocation.setLatitude(d);
                        aMapLocation.setRoad(locationData.getRoad());
                        String poi = locationData.getPoi();
                        if (poi == null) {
                            Intrinsics.throwNpe();
                        }
                        aMapLocation.setPoiName(poi);
                        aMapLocation.setLocationDetail(locationData.getDesc());
                        aMapLocation.setLocationType(Integer.parseInt(locationData.getType()));
                    }
                    PDLocationManager pDLocationManager = PDLocationManager.INSTANCE;
                    locationChangeListener4 = PDLocationManager.mLocationChangeListener;
                    if (locationChangeListener4 != null) {
                        if (Intrinsics.areEqual("", LocationReq.this.getBts())) {
                            locationChangeListener4.onLocationChanged(aMapLocation, LocationType.LOCATE_FROM_HARDWARE_WIFI);
                        } else {
                            locationChangeListener4.onLocationChanged(aMapLocation, LocationType.LOCATE_FROM_HARDWARE_BTS);
                        }
                        locationChangeListener4.onLocateInit(LocateInitStatus.LOCATE_INIT_SUCCESS);
                    }
                    PDLocationManager pDLocationManager2 = PDLocationManager.INSTANCE;
                    PDLocationManager.retryCount = 0;
                    Object[] objArr = new Object[2];
                    objArr[0] = "locateFromHardware from wifi location:";
                    objArr[1] = locationData != null ? locationData.getLocation() : null;
                    Pdlog.m3273d("PDLocationManager", objArr);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("locateFromHardware from wifi location fail,retry count:");
                PDLocationManager pDLocationManager3 = PDLocationManager.INSTANCE;
                i2 = PDLocationManager.retryCount;
                sb.append(i2);
                Pdlog.m3273d("PDLocationManager", sb.toString(), locationData);
                PDLocationManager pDLocationManager4 = PDLocationManager.INSTANCE;
                i3 = PDLocationManager.retryCount;
                PDLocationManager.retryCount = i3 + 1;
                PDLocationManager pDLocationManager5 = PDLocationManager.INSTANCE;
                i4 = PDLocationManager.retryTimes;
                if (i3 < i4) {
                    PDLocationManager.INSTANCE.getLocationFromAMap(LocationReq.this);
                    return;
                }
                PDLocationManager pDLocationManager6 = PDLocationManager.INSTANCE;
                locationChangeListener3 = PDLocationManager.mLocationChangeListener;
                if (locationChangeListener3 != null) {
                    locationChangeListener3.onLocateInit(LocateInitStatus.LOCATE_INIT_FAIL);
                }
                PDLocationManager pDLocationManager7 = PDLocationManager.INSTANCE;
                PDLocationManager.retryCount = 0;
                Pdlog.m3273d("PDLocationManager", "locateFromHardware from wifi location fail", locationData);
            }
        });
    }

    public final void startLocateFromBts(String mbts) {
        Intrinsics.checkParameterIsNotNull(mbts, "mbts");
        Pdlog.m3273d(TAG, "startLocateFromBts", mbts);
        getLocationFromAMap(new LocationReq(key, 0, null, null, null, null, null, null, null, null, mbts, null, null, null, null, 31740, null));
    }

    public final void startLocateFromWifi() {
        Pdlog.m3273d(TAG, "startLocateFromWifi");
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new PDLocationManager$startLocateFromWifi$1(null), 3, null);
    }

    private final void initLocation() {
        WeakReference<Context> weakReference = context;
        if (weakReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Context context2 = weakReference.get();
        Pdlog.m3273d(TAG, "initLocation context=" + context2);
        if (context2 != null) {
            mLocationClient = new AMapLocationClient(context2);
            mLocationOption = INSTANCE.getDefaultOption();
            AMapLocationClient aMapLocationClient = mLocationClient;
            if (aMapLocationClient != null) {
                AMapLocationClientOption aMapLocationClientOption = mLocationOption;
                if (aMapLocationClientOption == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mLocationOption");
                }
                aMapLocationClient.setLocationOption(aMapLocationClientOption);
                aMapLocationClient.setLocationListener(mLocationListener);
                aMapLocationClient.startLocation();
            }
        }
    }

    public final void startLocationFromSDK() {
        Pdlog.m3273d(TAG, "startLocation " + mLocationClient);
        initLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopLocation(AMapLocation location) {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("stopLocation ");
        sb.append(location.getLatitude());
        sb.append(' ');
        sb.append(location.getLongitude());
        sb.append(' ');
        sb.append(mLocationClient);
        sb.append(" context ");
        WeakReference<Context> weakReference = context;
        if (weakReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        sb.append(weakReference != null ? weakReference.get() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        location.getLatitude();
        location.getLongitude();
        if (location.getLongitude() == 0.0d || location.getLatitude() == 0.0d) {
            return;
        }
        AMapLocationClient aMapLocationClient = mLocationClient;
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
        }
        AMapLocationClient aMapLocationClient2 = mLocationClient;
        if (aMapLocationClient2 != null) {
            aMapLocationClient2.unRegisterLocationListener(mLocationListener);
        }
        AMapLocationClient aMapLocationClient3 = mLocationClient;
        if (aMapLocationClient3 != null) {
            aMapLocationClient3.onDestroy();
        }
        mLocationClient = (AMapLocationClient) null;
    }

    private final AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setGpsFirst(false);
        aMapLocationClientOption.setHttpTimeOut(30000L);
        aMapLocationClientOption.setInterval(LOCATION_INTERVAL);
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClientOption.setOnceLocation(false);
        aMapLocationClientOption.setOnceLocationLatest(false);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        aMapLocationClientOption.setSensorEnable(false);
        aMapLocationClientOption.setWifiScan(true);
        aMapLocationClientOption.setLocationCacheEnable(true);
        return aMapLocationClientOption;
    }

    public final void setLocationListener(LocationChangeListener listener) {
        Pdlog.m3273d(TAG, "addLocationListener", listener);
        locationChangeListener = listener;
    }

    public final Double getCurrenLatitude() {
        return latitude;
    }

    public final Double getCurrentLongitude() {
        return longitude;
    }

    public final LocationType getCurrentLocateType() {
        return locateType;
    }

    public final Object getWifiMacList(Context context2, Continuation<? super String> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3273d(TAG, "getWifiMacList context is ", context2);
        if (context2 == null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(""));
        } else {
            Object systemService = context2.getSystemService("wifi");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
            }
            WifiManager wifiManager = (WifiManager) systemService;
            wifiManager.startScan();
            List<ScanResult> scanResults = wifiManager.getScanResults();
            StringBuilder sb = new StringBuilder();
            for (ScanResult i : scanResults) {
                Intrinsics.checkExpressionValueIsNotNull(i, "i");
                if (!i.isPasspointNetwork()) {
                    sb.append(i.BSSID + "," + i.level + "," + i.SSID);
                    if (scanResults.indexOf(i) > 29 || scanResults.indexOf(i) == scanResults.size()) {
                        break;
                    }
                    sb.append("|");
                }
            }
            Pdlog.m3273d(TAG, "getWifiMacList ", Boxing.boxInt(sb.length()));
            String sb2 = sb.toString();
            Result.Companion companion2 = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(sb2));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
