package com.pudutech.antichannelconflict.location;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.location.AMapLocation;
import com.pudutech.antichannelconflict.location.util.LocateInitStatus;
import com.pudutech.antichannelconflict.location.util.LocationType;
import kotlin.Metadata;

/* compiled from: LocationChangeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/location/LocationChangeListener;", "", "onLocateInit", "", "code", "Lcom/pudutech/antichannelconflict/location/util/LocateInitStatus;", "onLocationChanged", RequestParameters.SUBRESOURCE_LOCATION, "Lcom/amap/api/location/AMapLocation;", "type", "Lcom/pudutech/antichannelconflict/location/util/LocationType;", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface LocationChangeListener {
    void onLocateInit(LocateInitStatus code);

    void onLocationChanged(AMapLocation location, LocationType type);
}
