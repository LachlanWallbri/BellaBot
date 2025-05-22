package com.pudutech.event_tracking.custom;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: custom.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/event_tracking/custom/CustomArgs;", "", "event", "", "params", "", LogFactory.PRIORITY_KEY, "", "(Ljava/lang/String;Ljava/util/Map;I)V", "getEvent", "()Ljava/lang/String;", "getParams", "()Ljava/util/Map;", "getPriority", "()I", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class CustomArgs {
    private final String event;
    private final Map<String, Object> params;
    private final int priority;

    public CustomArgs(String event, Map<String, ? extends Object> params, int i) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.event = event;
        this.params = params;
        this.priority = i;
    }

    public final String getEvent() {
        return this.event;
    }

    public /* synthetic */ CustomArgs(String str, Map map, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? MapsKt.emptyMap() : map, (i2 & 4) != 0 ? 0 : i);
    }

    public final Map<String, Object> getParams() {
        return this.params;
    }

    public final int getPriority() {
        return this.priority;
    }
}
