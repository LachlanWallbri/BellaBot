package com.pudutech.location;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: ILocationProvider.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\u00020\u00032!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005H&J\b\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/location/ILocationProvider;", "", "locationListener", "Lcom/amap/api/location/AMapLocationListener;", "callback", "Lkotlin/Function1;", "Lcom/pudutech/location/Location;", "Lkotlin/ParameterName;", "name", RequestParameters.SUBRESOURCE_LOCATION, "", "locationOption", "Lcom/amap/api/location/AMapLocationClientOption;", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface ILocationProvider {
    AMapLocationListener locationListener(Function1<? super Location, Unit> callback);

    AMapLocationClientOption locationOption();
}
