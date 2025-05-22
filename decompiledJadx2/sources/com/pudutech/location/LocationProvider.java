package com.pudutech.location;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationProvider.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J+\u0010\u0005\u001a\u00020\u00062!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\bH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/location/LocationProvider;", "Lcom/pudutech/location/ILocationProvider;", "amapConfig", "Lcom/pudutech/location/AmapConfig;", "(Lcom/pudutech/location/AmapConfig;)V", "locationListener", "Lcom/amap/api/location/AMapLocationListener;", "callback", "Lkotlin/Function1;", "Lcom/pudutech/location/Location;", "Lkotlin/ParameterName;", "name", RequestParameters.SUBRESOURCE_LOCATION, "", "locationOption", "Lcom/amap/api/location/AMapLocationClientOption;", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LocationProvider implements ILocationProvider {
    private AmapConfig amapConfig;

    public LocationProvider(AmapConfig amapConfig) {
        Intrinsics.checkParameterIsNotNull(amapConfig, "amapConfig");
        this.amapConfig = amapConfig;
    }

    @Override // com.pudutech.location.ILocationProvider
    public AMapLocationClientOption locationOption() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(this.amapConfig.getLocationMode());
        aMapLocationClientOption.setGpsFirst(false);
        aMapLocationClientOption.setHttpTimeOut(this.amapConfig.getLocationHttpTimeout());
        aMapLocationClientOption.setInterval(this.amapConfig.getLocationInterval());
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClientOption.setOnceLocation(this.amapConfig.getIsOnceLocation());
        aMapLocationClientOption.setOnceLocationLatest(false);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        aMapLocationClientOption.setSensorEnable(false);
        aMapLocationClientOption.setWifiScan(true);
        aMapLocationClientOption.setLocationCacheEnable(this.amapConfig.getIsLocationCacheEnable());
        return aMapLocationClientOption;
    }

    @Override // com.pudutech.location.ILocationProvider
    public AMapLocationListener locationListener(final Function1<? super Location, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        return new LocationListener(new Function1<Location, Unit>() { // from class: com.pudutech.location.LocationProvider$locationListener$1
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1.this.invoke(it);
            }
        });
    }
}
