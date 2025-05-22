package com.pudutech.location;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.location.AMapLocationClient;
import com.pudutech.base.Pdlog;
import com.pudutech.location.net.Data;
import com.pudutech.location.net.FlowCardReportReq;
import com.pudutech.location.net.NetWorkManager;
import com.pudutech.location.utils.FlowCardInfo;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AMapLocationManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ)\u0010\u001a\u001a\u00020\u00192!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0014J\b\u0010\u001c\u001a\u00020\u0019H\u0002J\u0006\u0010\u001d\u001a\u00020\u0019J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0016J\b\u0010 \u001a\u00020\u0019H\u0016J\u0006\u0010!\u001a\u00020\u0019J)\u0010!\u001a\u00020\u00192!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0014J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0015H\u0002J\u0006\u0010#\u001a\u00020\u0019J\u0006\u0010$\u001a\u00020\u0019R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\u0012\u001a#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/location/AMapLocationManager;", "Lcom/pudutech/location/LifecycleManager;", "context", "Landroid/content/Context;", "amapConfig", "Lcom/pudutech/location/AmapConfig;", "(Landroid/content/Context;Lcom/pudutech/location/AmapConfig;)V", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;Lcom/pudutech/location/AmapConfig;)V", "TAG", "", "mAmapConfig", "mContext", "mLocationClient", "Lcom/amap/api/location/AMapLocationClient;", "mNetWorkManager", "Lcom/pudutech/location/net/NetWorkManager;", "onLocationCallbackList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function1;", "Lcom/pudutech/location/Location;", "Lkotlin/ParameterName;", "name", RequestParameters.SUBRESOURCE_LOCATION, "", "addLocationListener", "callback", "createNet", "initLocation", "onCreate", "onDestroy", "onStart", "removeLocationListener", "reportData", "startLocation", "stopLocation", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AMapLocationManager extends LifecycleManager {
    private final String TAG;
    private AmapConfig mAmapConfig;
    private Context mContext;
    private AMapLocationClient mLocationClient;
    private NetWorkManager mNetWorkManager;
    private CopyOnWriteArrayList<Function1<Location, Unit>> onLocationCallbackList;

    public AMapLocationManager(Context context, AmapConfig amapConfig) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(amapConfig, "amapConfig");
        this.TAG = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        this.onLocationCallbackList = new CopyOnWriteArrayList<>();
        this.mContext = context;
        this.mAmapConfig = amapConfig;
        initLocation();
    }

    public AMapLocationManager(Activity activity, AmapConfig amapConfig) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(amapConfig, "amapConfig");
        this.TAG = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        this.onLocationCallbackList = new CopyOnWriteArrayList<>();
        Context applicationContext = activity.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "activity.applicationContext");
        this.mContext = applicationContext;
        this.mAmapConfig = amapConfig;
        addLifecycle(activity);
    }

    private final void createNet() {
        if (this.mNetWorkManager == null) {
            this.mNetWorkManager = new NetWorkManager(this.mContext);
        }
    }

    public final void initLocation() {
        if (this.mLocationClient == null) {
            LocationProvider locationProvider = new LocationProvider(this.mAmapConfig);
            this.mLocationClient = new AMapLocationClient(this.mContext);
            AMapLocationClient aMapLocationClient = this.mLocationClient;
            if (aMapLocationClient != null) {
                aMapLocationClient.setLocationOption(locationProvider.locationOption());
            }
            AMapLocationClient aMapLocationClient2 = this.mLocationClient;
            if (aMapLocationClient2 != null) {
                aMapLocationClient2.setLocationListener(locationProvider.locationListener(new Function1<Location, Unit>() { // from class: com.pudutech.location.AMapLocationManager$initLocation$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                        invoke2(location);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Location location) {
                        CopyOnWriteArrayList copyOnWriteArrayList;
                        AmapConfig amapConfig;
                        String str;
                        Intrinsics.checkParameterIsNotNull(location, "location");
                        copyOnWriteArrayList = AMapLocationManager.this.onLocationCallbackList;
                        Iterator it = copyOnWriteArrayList.iterator();
                        while (it.hasNext()) {
                            ((Function1) it.next()).invoke(location);
                            str = AMapLocationManager.this.TAG;
                            Pdlog.m3273d(str, "onLocationCallbackList location = " + location);
                        }
                        amapConfig = AMapLocationManager.this.mAmapConfig;
                        if (amapConfig.getIsOpenReport()) {
                            AMapLocationManager.this.reportData(location);
                        }
                    }
                }));
            }
            Pdlog.m3273d(this.TAG, "initLocation mAmapConfig = " + this.mAmapConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r11v0, types: [T, com.pudutech.location.net.FlowCardReportReq] */
    public final void reportData(Location location) {
        if (location.getErrorCode() == 0) {
            createScope();
            createNet();
            String simSerialNumber = FlowCardInfo.INSTANCE.getSimSerialNumber(this.mContext);
            if (!TextUtils.isEmpty(simSerialNumber)) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (simSerialNumber == null) {
                    Intrinsics.throwNpe();
                }
                objectRef.element = new FlowCardReportReq(new Data(simSerialNumber, CollectionsKt.mutableListOf(location.getLongitude(), location.getLatitude())), this.mAmapConfig.getHardver(), this.mAmapConfig.getMac(), this.mAmapConfig.getSoftver(), System.currentTimeMillis() / 1000);
                Pdlog.m3273d(this.TAG, "reportData flowCardReportReq = " + ((FlowCardReportReq) objectRef.element));
                CoroutineScope mScope = getMScope();
                setMJob(mScope != null ? BuildersKt__Builders_commonKt.launch$default(mScope, null, null, new AMapLocationManager$reportData$1(this, objectRef, null), 3, null) : null);
                return;
            }
            Pdlog.m3273d(this.TAG, "reportData simSerialNumber is null");
        }
    }

    public final void startLocation() {
        AMapLocationClient aMapLocationClient = this.mLocationClient;
        if (aMapLocationClient != null) {
            aMapLocationClient.startLocation();
        }
        Pdlog.m3273d(this.TAG, "startLocation");
    }

    public final void stopLocation() {
        AMapLocationClient aMapLocationClient = this.mLocationClient;
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
        }
        this.onLocationCallbackList.clear();
        Pdlog.m3273d(this.TAG, "stopLocation");
    }

    public final void addLocationListener(Function1<? super Location, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.onLocationCallbackList.add(callback);
        Pdlog.m3273d(this.TAG, "addLocationListener");
    }

    public final void removeLocationListener() {
        this.onLocationCallbackList.clear();
        Pdlog.m3273d(this.TAG, "removeLocationListener");
    }

    public final void removeLocationListener(final Function1<? super Location, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.onLocationCallbackList.removeIf(new Predicate<Function1<? super Location, ? extends Unit>>() { // from class: com.pudutech.location.AMapLocationManager$removeLocationListener$1
            @Override // java.util.function.Predicate
            public /* bridge */ /* synthetic */ boolean test(Function1<? super Location, ? extends Unit> function1) {
                return test2((Function1<? super Location, Unit>) function1);
            }

            /* renamed from: test, reason: avoid collision after fix types in other method */
            public final boolean test2(Function1<? super Location, Unit> function1) {
                return function1 == null || Intrinsics.areEqual(function1, Function1.this);
            }
        });
        Pdlog.m3273d(this.TAG, "removeLocationListener");
    }

    @Override // com.pudutech.location.LifecycleManager
    public void onCreate() {
        initLocation();
    }

    @Override // com.pudutech.location.LifecycleManager
    public void onStart() {
        startLocation();
    }

    @Override // com.pudutech.location.LifecycleManager
    public void onDestroy() {
        stopLocation();
    }
}
