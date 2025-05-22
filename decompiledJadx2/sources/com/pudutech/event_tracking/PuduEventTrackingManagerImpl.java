package com.pudutech.event_tracking;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.event_tracking.bean.Event;
import com.pudutech.event_tracking.bean.TrackEvent;
import com.pudutech.event_tracking.click.ClickArgs;
import com.pudutech.event_tracking.component.BaseDataComponent;
import com.pudutech.event_tracking.component.IDComponent;
import com.pudutech.event_tracking.component.LifeCycleComponent;
import com.pudutech.event_tracking.component.LocationComponent;
import com.pudutech.event_tracking.component.LocationSource;
import com.pudutech.event_tracking.component.StorageEventComponent;
import com.pudutech.event_tracking.component.TrackingCoreComponent;
import com.pudutech.event_tracking.component.TrackingLocation;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.pd_network.utils.NetDataUtils;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: PuduEventTrackingManagerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00180\u0017H\u0016JX\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u00042\b\b\u0002\u0010!\u001a\u00020\"H\u0002J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\u0004H\u0016J-\u0010)\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\n2\u001b\u0010*\u001a\u0017\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00150+j\u0002`,¢\u0006\u0002\b-H\u0016J,\u0010.\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020/2\u0006\u00100\u001a\u00020\u00042\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001801H\u0002J\u0018\u00102\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u000203H\u0016J \u00104\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u00105\u001a\u00020\u00152\u0006\u00100\u001a\u00020\u0004H\u0016J\u0010\u00106\u001a\u00020\u00152\u0006\u00100\u001a\u00020\u0004H\u0016J\u0010\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u0004H\u0016J\u0010\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u0010<\u001a\u00020\u00152\u0006\u0010=\u001a\u00020\u0004H\u0016J\u0010\u0010>\u001a\u00020\u00152\u0006\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u00020\u0015H\u0016J\u0010\u0010B\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, m3961d2 = {"Lcom/pudutech/event_tracking/PuduEventTrackingManagerImpl;", "Lcom/pudutech/event_tracking/IEventTrackingManager;", "()V", "TAG", "", "baseDataComponent", "Lcom/pudutech/event_tracking/component/BaseDataComponent;", "builder", "Lcom/pudutech/event_tracking/_EventTrackingManagerBuilder;", "context", "Landroid/content/Context;", "coreComponent", "Lcom/pudutech/event_tracking/component/TrackingCoreComponent;", "idComponent", "Lcom/pudutech/event_tracking/component/IDComponent;", "lifeCycleComponent", "Lcom/pudutech/event_tracking/component/LifeCycleComponent;", "locationComponent", "Lcom/pudutech/event_tracking/component/LocationComponent;", "pagePreId", "addHeaderCustom", "", "pair", "Lkotlin/Pair;", "", "builderEvent", "Lcom/pudutech/event_tracking/bean/TrackEvent;", "customEvent", "elementId", "event", "header", "pageId", "params", LogFactory.PRIORITY_KEY, "", "Lcom/pudutech/event_tracking/custom/CustomArgs;", "getSingleViewName", "v", "Landroid/view/View;", "hardwareVersion", "version", "init", "block", "Lkotlin/Function1;", "Lcom/pudutech/event_tracking/EventTrackingManagerBuilder;", "Lkotlin/ExtensionFunctionType;", "onBrowseEvent", "Lcom/pudutech/event_tracking/bean/Event;", "name", "", "onClick", "Lcom/pudutech/event_tracking/click/ClickArgs;", "onClickEvent", "onPageStart", "onPageStop", "refreshUserId", "id", "registerLocationSource", "mLocationSource", "Lcom/pudutech/event_tracking/component/LocationSource;", "removeHeaderCustom", TransferTable.COLUMN_KEY, "switchReportData", "open", "", "unRegisterLocationSource", "view2ElementId", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PuduEventTrackingManagerImpl implements IEventTrackingManager {
    private BaseDataComponent baseDataComponent;
    private _EventTrackingManagerBuilder builder;
    private Context context;
    private TrackingCoreComponent coreComponent;
    private IDComponent idComponent;
    private LifeCycleComponent lifeCycleComponent;
    private LocationComponent locationComponent;
    private final String TAG = "PuduUserActionManager";
    private String pagePreId = "";

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void init(Context context, Function1<? super _EventTrackingManagerBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.context = context;
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder = new _EventTrackingManagerBuilder();
        block.invoke(_eventtrackingmanagerbuilder);
        this.builder = _eventtrackingmanagerbuilder;
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder2 = this.builder;
        if (_eventtrackingmanagerbuilder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        _eventtrackingmanagerbuilder2.check();
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder3 = this.builder;
        if (_eventtrackingmanagerbuilder3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        this.coreComponent = new TrackingCoreComponent(context, _eventtrackingmanagerbuilder3);
        LifeCycleComponent lifeCycleComponent = new LifeCycleComponent(context);
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder4 = this.builder;
        if (_eventtrackingmanagerbuilder4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        lifeCycleComponent.addFilterActivity(_eventtrackingmanagerbuilder4.getFilterActivity());
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder5 = this.builder;
        if (_eventtrackingmanagerbuilder5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        lifeCycleComponent.addFilterFragment(_eventtrackingmanagerbuilder5.getFilterFragment());
        PuduEventTrackingManagerImpl puduEventTrackingManagerImpl = this;
        lifeCycleComponent.setOnBrowseEventLis(new PuduEventTrackingManagerImpl$init$1$1(puduEventTrackingManagerImpl));
        this.lifeCycleComponent = lifeCycleComponent;
        LocationComponent locationComponent = new LocationComponent(context);
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder6 = this.builder;
        if (_eventtrackingmanagerbuilder6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        LocationSource locationSource = _eventtrackingmanagerbuilder6.getLocationSource();
        if (locationSource != null) {
            locationComponent.registerLocationSource(locationSource);
        }
        this.locationComponent = locationComponent;
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder7 = this.builder;
        if (_eventtrackingmanagerbuilder7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        TrackDeviceInfo forceTrackDeviceInfo = _eventtrackingmanagerbuilder7.getForceTrackDeviceInfo();
        if (forceTrackDeviceInfo == null) {
            forceTrackDeviceInfo = Tracking_deviceKt.fetchDeviceInfo(context);
        }
        this.baseDataComponent = new BaseDataComponent(context, forceTrackDeviceInfo);
        this.idComponent = new IDComponent(context, String.valueOf(forceTrackDeviceInfo.getAppId()));
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder8 = this.builder;
        if (_eventtrackingmanagerbuilder8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        if (_eventtrackingmanagerbuilder8.getAutoReportStorage()) {
            new StorageEventComponent().onStorageEventLis(new PuduEventTrackingManagerImpl$init$3$1(puduEventTrackingManagerImpl));
        }
        onBrowseEvent(Event.OnCreate.INSTANCE, "", MapsKt.emptyMap());
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void onPageStart(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        onBrowseEvent(Event.PageResume.INSTANCE, name, MapsKt.emptyMap());
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void onPageStop(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        onBrowseEvent(Event.PagePause.INSTANCE, name, MapsKt.emptyMap());
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void onClick(View v, ClickArgs params) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(params, "params");
        String json = PuduEventTrackingManagerImplKt.toJson(params.getParams());
        String view2ElementId = view2ElementId(v);
        Intrinsics.checkExpressionValueIsNotNull(json, "json");
        onClickEvent(view2ElementId, json, params.getPriority());
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void customEvent(CustomArgs event) {
        String str;
        Class<?> cls;
        Intrinsics.checkParameterIsNotNull(event, "event");
        String event2 = event.getEvent();
        String tag = Event.Custom.INSTANCE.getTag();
        LifeCycleComponent lifeCycleComponent = this.lifeCycleComponent;
        if (lifeCycleComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lifeCycleComponent");
        }
        Activity crtResumeActivity = lifeCycleComponent.getCrtResumeActivity();
        if (crtResumeActivity == null || (cls = crtResumeActivity.getClass()) == null || (str = cls.getName()) == null) {
            str = "";
        }
        String str2 = this.pagePreId;
        String json = PuduEventTrackingManagerImplKt.toJson(event.getParams());
        Intrinsics.checkExpressionValueIsNotNull(json, "event.params.toJson()");
        TrackEvent builderEvent = builderEvent(event2, "", tag, "", str, str2, json, event.getPriority());
        TrackingCoreComponent trackingCoreComponent = this.coreComponent;
        if (trackingCoreComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coreComponent");
        }
        trackingCoreComponent.onEvent(builderEvent);
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void refreshUserId(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder = this.builder;
        if (_eventtrackingmanagerbuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        _eventtrackingmanagerbuilder.setUserId(id);
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void hardwareVersion(String version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder = this.builder;
        if (_eventtrackingmanagerbuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        _eventtrackingmanagerbuilder.setHardwareVersion(version);
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void registerLocationSource(LocationSource mLocationSource) {
        Intrinsics.checkParameterIsNotNull(mLocationSource, "mLocationSource");
        LocationComponent locationComponent = this.locationComponent;
        if (locationComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationComponent");
        }
        locationComponent.registerLocationSource(mLocationSource);
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void unRegisterLocationSource() {
        LocationComponent locationComponent = this.locationComponent;
        if (locationComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationComponent");
        }
        locationComponent.unRegisterLocationSource();
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void addHeaderCustom(Pair<String, ? extends Object> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder = this.builder;
        if (_eventtrackingmanagerbuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        _eventtrackingmanagerbuilder.getHeadCustom().put(pair.getFirst(), pair.getSecond());
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void removeHeaderCustom(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder = this.builder;
        if (_eventtrackingmanagerbuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        _eventtrackingmanagerbuilder.getHeadCustom().remove(key);
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void switchReportData(boolean open) {
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder = this.builder;
        if (_eventtrackingmanagerbuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        _eventtrackingmanagerbuilder.setReportData(open);
    }

    private final void onClickEvent(String elementId, String params, int priority) {
        String str;
        Class<?> cls;
        String tag = Event.Click.INSTANCE.getTag();
        LifeCycleComponent lifeCycleComponent = this.lifeCycleComponent;
        if (lifeCycleComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lifeCycleComponent");
        }
        Activity crtResumeActivity = lifeCycleComponent.getCrtResumeActivity();
        if (crtResumeActivity == null || (cls = crtResumeActivity.getClass()) == null || (str = cls.getName()) == null) {
            str = "";
        }
        TrackEvent builderEvent$default = builderEvent$default(this, null, elementId, tag, null, str, this.pagePreId, params, priority, 9, null);
        TrackingCoreComponent trackingCoreComponent = this.coreComponent;
        if (trackingCoreComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coreComponent");
        }
        trackingCoreComponent.onEvent(builderEvent$default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBrowseEvent(Event event, String name, Map<String, ? extends Object> params) {
        String tag = event.getTag();
        String json = PuduEventTrackingManagerImplKt.toJson(params);
        Intrinsics.checkExpressionValueIsNotNull(json, "params.toJson()");
        TrackEvent builderEvent$default = builderEvent$default(this, null, null, tag, null, name, this.pagePreId, json, 0, 139, null);
        TrackingCoreComponent trackingCoreComponent = this.coreComponent;
        if (trackingCoreComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coreComponent");
        }
        trackingCoreComponent.onEvent(builderEvent$default);
        if (Intrinsics.areEqual(event, Event.PageResume.INSTANCE)) {
            this.pagePreId = name;
        }
    }

    static /* synthetic */ TrackEvent builderEvent$default(PuduEventTrackingManagerImpl puduEventTrackingManagerImpl, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        if ((i2 & 2) != 0) {
            str2 = "";
        }
        if ((i2 & 4) != 0) {
            str3 = "";
        }
        if ((i2 & 8) != 0) {
            str4 = "";
        }
        if ((i2 & 16) != 0) {
            str5 = "";
        }
        if ((i2 & 32) != 0) {
            str6 = "";
        }
        if ((i2 & 64) != 0) {
            str7 = "";
        }
        if ((i2 & 128) != 0) {
            i = 0;
        }
        return puduEventTrackingManagerImpl.builderEvent(str, str2, str3, str4, str5, str6, str7, i);
    }

    private final TrackEvent builderEvent(String customEvent, String elementId, String event, String header, String pageId, String pagePreId, String params, int priority) {
        LocationComponent locationComponent = this.locationComponent;
        if (locationComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationComponent");
        }
        TrackingLocation lastLocation = locationComponent.getLastLocation();
        BaseDataComponent baseDataComponent = this.baseDataComponent;
        if (baseDataComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseDataComponent");
        }
        IDComponent iDComponent = this.idComponent;
        if (iDComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("idComponent");
        }
        String uuid = iDComponent.uuid();
        String versionCode = baseDataComponent.getVersionCode();
        String packageName = baseDataComponent.getPackageName();
        String mChannel = baseDataComponent.getMChannel();
        int appId = baseDataComponent.getAppId();
        String appName = baseDataComponent.getAppName();
        String versionName = baseDataComponent.getVersionName();
        String appLanguage = baseDataComponent.getAppLanguage();
        String deviceType = baseDataComponent.getDeviceType();
        IDComponent iDComponent2 = this.idComponent;
        if (iDComponent2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("idComponent");
        }
        String deviceId = iDComponent2.getDeviceId();
        Intrinsics.checkExpressionValueIsNotNull(deviceId, "idComponent.deviceId");
        String mac = NetDataUtils.INSTANCE.mac();
        LocationComponent locationComponent2 = this.locationComponent;
        if (locationComponent2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationComponent");
        }
        String ipAddress = locationComponent2.getIpAddress();
        if (ipAddress == null) {
            ipAddress = "";
        }
        String deviceModel = baseDataComponent.getDeviceModel();
        String timeZone = baseDataComponent.getTimeZone();
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder = this.builder;
        if (_eventtrackingmanagerbuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        String json = PuduEventTrackingManagerImplKt.toJson(_eventtrackingmanagerbuilder.getHeadCustom());
        Intrinsics.checkExpressionValueIsNotNull(json, "builder.headCustom.toJson()");
        String valueOf = String.valueOf(lastLocation.getLat());
        String city = lastLocation.getCity();
        String province = lastLocation.getProvince();
        String valueOf2 = String.valueOf(lastLocation.getLng());
        String fetchNetwork = baseDataComponent.fetchNetwork();
        String osVersion = baseDataComponent.getOsVersion();
        String density = baseDataComponent.getDensity();
        String screenResolution = baseDataComponent.getScreenResolution();
        IDComponent iDComponent3 = this.idComponent;
        if (iDComponent3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("idComponent");
        }
        String sessionId = iDComponent3.getSessionId();
        long currentTimeMillis = System.currentTimeMillis();
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder2 = this.builder;
        if (_eventtrackingmanagerbuilder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        String userId = _eventtrackingmanagerbuilder2.getUserId();
        StringBuilder sb = new StringBuilder();
        sb.append(baseDataComponent.getAppId());
        sb.append('_');
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder3 = this.builder;
        if (_eventtrackingmanagerbuilder3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        sb.append(_eventtrackingmanagerbuilder3.getUserId());
        String sb2 = sb.toString();
        _EventTrackingManagerBuilder _eventtrackingmanagerbuilder4 = this.builder;
        if (_eventtrackingmanagerbuilder4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        return new TrackEvent(0L, uuid, versionCode, packageName, mChannel, appId, appName, versionName, appLanguage, customEvent, deviceType, deviceId, mac, ipAddress, deviceModel, timeZone, elementId, event, header, json, valueOf, city, province, valueOf2, fetchNetwork, osVersion, pageId, pagePreId, params, density, screenResolution, sessionId, currentTimeMillis, userId, "", "", sb2, _eventtrackingmanagerbuilder4.getHardwareVersion(), 0, priority, 1, 0, null);
    }

    private final String view2ElementId(View v) {
        String singleViewName = getSingleViewName(v);
        while (v.getParent() != null && (v.getParent() instanceof View)) {
            Object parent = v.getParent();
            if (parent == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.View");
            }
            v = (View) parent;
            singleViewName = getSingleViewName(v) + "$" + singleViewName;
        }
        return singleViewName;
    }

    private final String getSingleViewName(View v) {
        StringBuilder sb = new StringBuilder();
        sb.append(v.getClass().getSimpleName());
        String str = "";
        if (v.getId() > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("$");
            try {
                Context context = this.context;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                str = context.getResources().getResourceEntryName(v.getId());
            } catch (Exception unused) {
            }
            sb2.append(str);
            str = sb2.toString();
        }
        sb.append(str);
        return sb.toString();
    }
}
