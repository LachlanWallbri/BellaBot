package com.pudutech.event_tracking.component;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.app.ActivityCompat;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.event_tracking.utils.IpApiInfo;
import com.pudutech.event_tracking.utils.IpApiInfoUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* compiled from: LocationComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000K\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005*\u0001\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0014\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0003J\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u0006\u0010\u001b\u001a\u00020\u0013J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u000e\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u000bJ\r\u0010 \u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, m3961d2 = {"Lcom/pudutech/event_tracking/component/LocationComponent;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "locationChangedListener", "com/pudutech/event_tracking/component/LocationComponent$locationChangedListener$1", "Lcom/pudutech/event_tracking/component/LocationComponent$locationChangedListener$1;", "locationSource", "Lcom/pudutech/event_tracking/component/LocationSource;", "getLocationSource", "()Lcom/pudutech/event_tracking/component/LocationSource;", "setLocationSource", "(Lcom/pudutech/event_tracking/component/LocationSource;)V", "mIpApiInfo", "Lcom/pudutech/event_tracking/utils/IpApiInfo;", "mapSourceLocation", "Lcom/pudutech/event_tracking/component/TrackingLocation;", "fetchIpApiInfo", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchMobileLocation", "Landroid/location/Location;", "getIpAddress", "", "getLastLocation", "havePermission", "", "registerLocationSource", "mLocationSource", "unRegisterLocationSource", "()Lkotlin/Unit;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LocationComponent {
    private final Context context;
    private final LocationComponent$locationChangedListener$1 locationChangedListener;
    private LocationSource locationSource;
    private IpApiInfo mIpApiInfo;
    private TrackingLocation mapSourceLocation;

    /* JADX WARN: Type inference failed for: r7v5, types: [com.pudutech.event_tracking.component.LocationComponent$locationChangedListener$1] */
    public LocationComponent(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(ThreadPoolDispatcherKt.newSingleThreadContext("FETCH_IP_ADDRESS_TD")), null, null, new C44551(null), 3, null);
        this.locationChangedListener = new OnLocationChangedListener() { // from class: com.pudutech.event_tracking.component.LocationComponent$locationChangedListener$1
            @Override // com.pudutech.event_tracking.component.OnLocationChangedListener
            public void onLocationChanged(TrackingLocation mLocation) {
                Intrinsics.checkParameterIsNotNull(mLocation, "mLocation");
                LocationComponent.this.mapSourceLocation = mLocation;
            }
        };
    }

    public final Context getContext() {
        return this.context;
    }

    /* compiled from: LocationComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.event_tracking.component.LocationComponent$1", m3970f = "LocationComponent.kt", m3971i = {0, 1, 2, 3}, m3972l = {24, 27, 29, 31}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0", "L$0"})
    /* renamed from: com.pudutech.event_tracking.component.LocationComponent$1 */
    /* loaded from: classes5.dex */
    static final class C44551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5121p$;

        C44551(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C44551 c44551 = new C44551(completion);
            c44551.f5121p$ = (CoroutineScope) obj;
            return c44551;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C44551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0076 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0074 -> B:13:0x0040). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C44551 c44551;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2 || i == 3) {
                        coroutineScope = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        c44551 = this;
                        LocationComponent locationComponent = LocationComponent.this;
                        c44551.L$0 = coroutineScope;
                        c44551.label = 4;
                        if (locationComponent.fetchIpApiInfo(c44551) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (CoroutineScopeKt.isActive(coroutineScope)) {
                            if (LocationComponent.this.mIpApiInfo == null) {
                                c44551.L$0 = coroutineScope;
                                c44551.label = 2;
                                if (DelayKt.delay(300000L, c44551) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                c44551.L$0 = coroutineScope;
                                c44551.label = 3;
                                if (DelayKt.delay(3600000L, c44551) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            LocationComponent locationComponent2 = LocationComponent.this;
                            c44551.L$0 = coroutineScope;
                            c44551.label = 4;
                            if (locationComponent2.fetchIpApiInfo(c44551) == coroutine_suspended) {
                            }
                            if (CoroutineScopeKt.isActive(coroutineScope)) {
                            }
                        } else {
                            return Unit.INSTANCE;
                        }
                    } else if (i != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5121p$;
                LocationComponent locationComponent3 = LocationComponent.this;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (locationComponent3.fetchIpApiInfo(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            c44551 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
    }

    public final LocationSource getLocationSource() {
        return this.locationSource;
    }

    public final void setLocationSource(LocationSource locationSource) {
        this.locationSource = locationSource;
    }

    public final void registerLocationSource(LocationSource mLocationSource) {
        Intrinsics.checkParameterIsNotNull(mLocationSource, "mLocationSource");
        this.locationSource = mLocationSource;
        mLocationSource.activate(this.locationChangedListener);
    }

    public final Unit unRegisterLocationSource() {
        LocationSource locationSource = this.locationSource;
        if (locationSource == null) {
            return null;
        }
        locationSource.deactivate();
        return Unit.INSTANCE;
    }

    public final String getIpAddress() {
        IpApiInfo ipApiInfo = this.mIpApiInfo;
        if (ipApiInfo != null) {
            return ipApiInfo.getQuery();
        }
        return null;
    }

    public final TrackingLocation getLastLocation() {
        TrackingLocation trackingLocation;
        String city;
        String regionName;
        TrackingLocation trackingLocation2 = this.mapSourceLocation;
        if (trackingLocation2 != null) {
            if (trackingLocation2 == null) {
                Intrinsics.throwNpe();
            }
            return trackingLocation2;
        }
        Location fetchMobileLocation = fetchMobileLocation();
        if (fetchMobileLocation != null) {
            double latitude = fetchMobileLocation.getLatitude();
            double longitude = fetchMobileLocation.getLongitude();
            IpApiInfo ipApiInfo = this.mIpApiInfo;
            String str = (ipApiInfo == null || (regionName = ipApiInfo.getRegionName()) == null) ? "" : regionName;
            IpApiInfo ipApiInfo2 = this.mIpApiInfo;
            trackingLocation = new TrackingLocation(latitude, longitude, str, (ipApiInfo2 == null || (city = ipApiInfo2.getCity()) == null) ? "" : city, 1);
        } else {
            trackingLocation = this.mIpApiInfo != null ? new TrackingLocation(-1.0d, -1.0d, "", "", 0) : null;
        }
        return trackingLocation != null ? trackingLocation : TrackingLocation.INSTANCE.defaultLocation();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(2:3|(7:5|6|7|(1:(2:10|11)(2:17|18))(3:19|20|(1:22)(1:23))|12|13|14))|26|6|7|(0)(0)|12|13|14) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
    
        r5.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object fetchIpApiInfo(Continuation<? super Unit> continuation) {
        LocationComponent$fetchIpApiInfo$1 locationComponent$fetchIpApiInfo$1;
        int i;
        LocationComponent locationComponent;
        if (continuation instanceof LocationComponent$fetchIpApiInfo$1) {
            locationComponent$fetchIpApiInfo$1 = (LocationComponent$fetchIpApiInfo$1) continuation;
            if ((locationComponent$fetchIpApiInfo$1.label & Integer.MIN_VALUE) != 0) {
                locationComponent$fetchIpApiInfo$1.label -= Integer.MIN_VALUE;
                Object obj = locationComponent$fetchIpApiInfo$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = locationComponent$fetchIpApiInfo$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    IpApiInfoUtils ipApiInfoUtils = IpApiInfoUtils.INSTANCE;
                    locationComponent$fetchIpApiInfo$1.L$0 = this;
                    locationComponent$fetchIpApiInfo$1.L$1 = this;
                    locationComponent$fetchIpApiInfo$1.label = 1;
                    obj = ipApiInfoUtils.getIpApiInfo(locationComponent$fetchIpApiInfo$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    locationComponent = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    locationComponent = (LocationComponent) locationComponent$fetchIpApiInfo$1.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                locationComponent.mIpApiInfo = (IpApiInfo) obj;
                return Unit.INSTANCE;
            }
        }
        locationComponent$fetchIpApiInfo$1 = new LocationComponent$fetchIpApiInfo$1(this, continuation);
        Object obj2 = locationComponent$fetchIpApiInfo$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = locationComponent$fetchIpApiInfo$1.label;
        if (i != 0) {
        }
        locationComponent.mIpApiInfo = (IpApiInfo) obj2;
        return Unit.INSTANCE;
    }

    private final Location fetchMobileLocation() {
        Location lastKnownLocation;
        if (!havePermission()) {
            return null;
        }
        Object systemService = this.context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        if (!(systemService instanceof LocationManager)) {
            systemService = null;
        }
        LocationManager locationManager = (LocationManager) systemService;
        List<String> providers = locationManager != null ? locationManager.getProviders(true) : null;
        if (providers != null) {
            String str = "passive";
            if (!providers.contains("passive")) {
                if (providers.contains("gps")) {
                    str = "gps";
                } else {
                    str = providers.contains("network") ? "network" : null;
                }
            }
            if (str != null && (lastKnownLocation = locationManager.getLastKnownLocation(str)) != null) {
                return lastKnownLocation;
            }
        }
        return null;
    }

    private final boolean havePermission() {
        return !((ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") == 0) ? false : true);
    }
}
