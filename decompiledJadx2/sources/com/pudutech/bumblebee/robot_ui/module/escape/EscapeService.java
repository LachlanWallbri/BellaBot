package com.pudutech.bumblebee.robot_ui.module.escape;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.module.escape.encrypt.RSAEncrypt;
import com.pudutech.location.AMapLocationManager;
import com.pudutech.location.Location;
import io.reactivex.subjects.PublishSubject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: EscapeService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010,\u001a\u00020\u000eJ\u0006\u0010-\u001a\u00020\u000eJ\b\u0010.\u001a\u00020\u000eH\u0002J\u0006\u0010/\u001a\u00020\u000eJ\u0012\u00100\u001a\u00020\u000b2\b\u00101\u001a\u0004\u0018\u00010\u000bH\u0002J\u0006\u00102\u001a\u00020\bJ\b\u00103\u001a\u00020\u0011H\u0002J\u0006\u00104\u001a\u00020\u000eR\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001f\u0010 \u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u001c\u0010\"\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010#\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014R\u001c\u0010%\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001c\u0010)\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010*\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0014¨\u00065"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeService;", "", "storage", "Lcom/pudutech/bumblebee/robot_ui/module/escape/IEscapeStorage;", "locationManager", "Lcom/pudutech/location/AMapLocationManager;", "(Lcom/pudutech/bumblebee/robot_ui/module/escape/IEscapeStorage;Lcom/pudutech/location/AMapLocationManager;)V", "LOCATION_COUNT", "", "LOCATION_COUNT_NO_NET", "TAG", "", "escapeAction", "Lkotlin/Function0;", "", "escapeObservable", "Lio/reactivex/subjects/PublishSubject;", "", "kotlin.jvm.PlatformType", "getEscapeObservable", "()Lio/reactivex/subjects/PublishSubject;", "escapeSubject", "isNotNet", RequestParameters.SUBRESOURCE_LOCATION, "Lcom/pudutech/location/Location;", "locationReset", "mapUpdatePresenter", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdatePresenter;", "getMapUpdatePresenter", "()Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdatePresenter;", "mapUpdatePresenter$delegate", "Lkotlin/Lazy;", "networkObservable", "getNetworkObservable", "networkSubject", "newMapObservable", "getNewMapObservable", "newMapSubject", "reqCount", "getStorage", "()Lcom/pudutech/bumblebee/robot_ui/module/escape/IEscapeStorage;", "uploadMapSubject", "uploadObservable", "getUploadObservable", "checkEscape", "checkMapIsNew", "checkMapVersion", "checkNetwork", "encryptParam", "param", "getResetCount", "isAutoLock", "uploadMap", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EscapeService {
    private final int LOCATION_COUNT;
    private final int LOCATION_COUNT_NO_NET;
    private final String TAG;
    private Function0<Unit> escapeAction;
    private final PublishSubject<Boolean> escapeObservable;
    private final PublishSubject<Boolean> escapeSubject;
    private boolean isNotNet;
    private Location location;
    private int locationReset;

    /* renamed from: mapUpdatePresenter$delegate, reason: from kotlin metadata */
    private final Lazy mapUpdatePresenter;
    private final PublishSubject<Boolean> networkObservable;
    private final PublishSubject<Boolean> networkSubject;
    private final PublishSubject<Boolean> newMapObservable;
    private final PublishSubject<Boolean> newMapSubject;
    private int reqCount;
    private final IEscapeStorage storage;
    private final PublishSubject<Boolean> uploadMapSubject;
    private final PublishSubject<Boolean> uploadObservable;

    /* JADX INFO: Access modifiers changed from: private */
    public final MapUpdatePresenter getMapUpdatePresenter() {
        return (MapUpdatePresenter) this.mapUpdatePresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAutoLock() {
        return true;
    }

    public final void uploadMap() {
    }

    public EscapeService(IEscapeStorage storage, AMapLocationManager aMapLocationManager) {
        Intrinsics.checkParameterIsNotNull(storage, "storage");
        this.storage = storage;
        this.TAG = "EscapeService";
        PublishSubject<Boolean> create = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "PublishSubject.create<Boolean>()");
        this.newMapSubject = create;
        this.newMapObservable = this.newMapSubject;
        PublishSubject<Boolean> create2 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "PublishSubject.create<Boolean>()");
        this.escapeSubject = create2;
        this.escapeObservable = this.escapeSubject;
        PublishSubject<Boolean> create3 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create3, "PublishSubject.create<Boolean>()");
        this.uploadMapSubject = create3;
        this.uploadObservable = this.uploadMapSubject;
        PublishSubject<Boolean> create4 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create4, "PublishSubject.create<Boolean>()");
        this.networkSubject = create4;
        this.networkObservable = this.networkSubject;
        this.mapUpdatePresenter = LazyKt.lazy(new Function0<MapUpdatePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeService$mapUpdatePresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MapUpdatePresenter invoke() {
                MapUpdatePresenter mapUpdatePresenter;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    mapUpdatePresenter = new MapUpdatePresenter();
                    presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class).toString(), mapUpdatePresenter);
                } else {
                    if (!(basePresenterInterface instanceof MapUpdatePresenter)) {
                        basePresenterInterface = null;
                    }
                    mapUpdatePresenter = (MapUpdatePresenter) basePresenterInterface;
                }
                if (mapUpdatePresenter != null) {
                    return mapUpdatePresenter;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter");
            }
        });
        this.LOCATION_COUNT = 3;
        this.LOCATION_COUNT_NO_NET = 5;
        if (aMapLocationManager != null) {
            aMapLocationManager.addLocationListener(new Function1<Location, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeService$$special$$inlined$apply$lambda$1
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
                public final void invoke2(Location it) {
                    String str;
                    int i;
                    int i2;
                    Function0 function0;
                    String str2;
                    String str3;
                    int i3;
                    Location location;
                    Function0 function02;
                    String str4;
                    Location location2;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    str = EscapeService.this.TAG;
                    Pdlog.m3273d(str, "addLocationListener = " + it);
                    if (it.getErrorCode() == 0) {
                        EscapeService.this.isNotNet = false;
                        EscapeService.this.location = it;
                        function02 = EscapeService.this.escapeAction;
                        if (function02 != null) {
                        }
                        EscapeService.this.escapeAction = (Function0) null;
                        str4 = EscapeService.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("locationReset  success = ");
                        location2 = EscapeService.this.location;
                        sb.append(location2);
                        Pdlog.m3275i(str4, sb.toString());
                    } else {
                        if (it.getErrorCode() == 4 || it.getErrorCode() == 7 || it.getErrorCode() == 18 || it.getErrorCode() == 19) {
                            EscapeService.this.isNotNet = true;
                        }
                        EscapeService escapeService = EscapeService.this;
                        i = escapeService.locationReset;
                        escapeService.locationReset = i + 1;
                        i2 = EscapeService.this.locationReset;
                        if (i2 >= EscapeService.this.getResetCount()) {
                            function0 = EscapeService.this.escapeAction;
                            if (function0 != null) {
                            }
                            EscapeService.this.escapeAction = (Function0) null;
                            str2 = EscapeService.this.TAG;
                            Pdlog.m3275i(str2, "locationReset = locationReset >= 3");
                        }
                    }
                    str3 = EscapeService.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("locationReset = ");
                    i3 = EscapeService.this.locationReset;
                    sb2.append(i3);
                    sb2.append(" location result=");
                    location = EscapeService.this.location;
                    sb2.append(location);
                    Pdlog.m3275i(str3, sb2.toString());
                }
            });
            if (aMapLocationManager != null) {
                return;
            }
        }
        this.locationReset = getResetCount();
        Pdlog.m3273d(this.TAG, "locationManager is null");
        Unit unit = Unit.INSTANCE;
    }

    public final IEscapeStorage getStorage() {
        return this.storage;
    }

    public final PublishSubject<Boolean> getNewMapObservable() {
        return this.newMapObservable;
    }

    public final PublishSubject<Boolean> getEscapeObservable() {
        return this.escapeObservable;
    }

    public final PublishSubject<Boolean> getUploadObservable() {
        return this.uploadObservable;
    }

    public final PublishSubject<Boolean> getNetworkObservable() {
        return this.networkObservable;
    }

    public final int getResetCount() {
        if (this.isNotNet) {
            return this.LOCATION_COUNT_NO_NET;
        }
        return this.LOCATION_COUNT;
    }

    public final void checkMapIsNew() {
        this.newMapObservable.onNext(Boolean.valueOf(this.storage.mapIsNew()));
    }

    public final void checkEscape() {
        if (this.location == null && this.locationReset < getResetCount()) {
            Pdlog.m3275i(this.TAG, "checkEscape() location failed, wait....");
            this.escapeAction = new EscapeService$checkEscape$1(this);
            return;
        }
        Pdlog.m3275i(this.TAG, "check escape=" + this.location);
        AMapLocationManager locationManager = RobotContext.INSTANCE.getLocationManager();
        if (locationManager != null) {
            locationManager.removeLocationListener();
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EscapeService$checkEscape$2(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String encryptParam(String param) {
        RSAEncrypt rSAEncrypt = RSAEncrypt.INSTANCE;
        if (param == null) {
            param = "";
        }
        return rSAEncrypt.encryptByPublicKey(param);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkMapVersion() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EscapeService$checkMapVersion$1(this, null), 2, null);
    }

    public final void checkNetwork() {
        boolean isNetworkAvailable = WifiUtil.INSTANCE.isNetworkAvailable(RobotContext.INSTANCE.getContext());
        Pdlog.m3273d(this.TAG, "checkNetwork()  networkAvailable = " + isNetworkAvailable);
        this.networkObservable.onNext(Boolean.valueOf(isNetworkAvailable));
    }
}
