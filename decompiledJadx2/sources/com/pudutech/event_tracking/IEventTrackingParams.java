package com.pudutech.event_tracking;

import com.pudutech.event_tracking.bean.Event;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: PuduEventTrackingManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/event_tracking/IEventTrackingParams;", "", "eventParams", "", "", "event", "Lcom/pudutech/event_tracking/bean/Event;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface IEventTrackingParams {
    Map<String, Object> eventParams(Event event);
}
