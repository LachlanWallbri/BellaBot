package com.pudutech.location;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B(\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000bH\u0016R)\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/location/LocationListener;", "Lcom/amap/api/location/AMapLocationListener;", "callback", "Lkotlin/Function1;", "Lcom/pudutech/location/Location;", "Lkotlin/ParameterName;", "name", RequestParameters.SUBRESOURCE_LOCATION, "", "(Lkotlin/jvm/functions/Function1;)V", "onLocationChanged", "Lcom/amap/api/location/AMapLocation;", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LocationListener implements AMapLocationListener {
    private final Function1<Location, Unit> callback;

    /* JADX WARN: Multi-variable type inference failed */
    public LocationListener(Function1<? super Location, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation location) {
        Pdlog.m3273d("AMapLocationManager", "onLocationChanged = " + location);
        if (location != null) {
            Function1<Location, Unit> function1 = this.callback;
            String valueOf = String.valueOf(location.getLongitude());
            String valueOf2 = String.valueOf(location.getLatitude());
            String valueOf3 = String.valueOf(location.getLocationType());
            String valueOf4 = String.valueOf(location.getAccuracy());
            String address = location.getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "address");
            function1.invoke(new Location(valueOf, valueOf2, valueOf3, valueOf4, address, location.getErrorCode()));
        }
    }
}
