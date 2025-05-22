package com.pudutech.event_tracking.component;

import kotlin.Metadata;

/* compiled from: LocationComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/event_tracking/component/LocationSource;", "", "activate", "", "listener", "Lcom/pudutech/event_tracking/component/OnLocationChangedListener;", "deactivate", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface LocationSource {
    void activate(OnLocationChangedListener listener);

    void deactivate();
}
