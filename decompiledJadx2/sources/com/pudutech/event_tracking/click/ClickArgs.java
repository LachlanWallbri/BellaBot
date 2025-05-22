package com.pudutech.event_tracking.click;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: track.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/event_tracking/click/ClickArgs;", "", "params", "", "", LogFactory.PRIORITY_KEY, "", "(Ljava/util/Map;I)V", "getParams", "()Ljava/util/Map;", "getPriority", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ClickArgs {
    private final Map<String, Object> params;
    private final int priority;

    public ClickArgs() {
        this(null, 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ClickArgs copy$default(ClickArgs clickArgs, Map map, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            map = clickArgs.params;
        }
        if ((i2 & 2) != 0) {
            i = clickArgs.priority;
        }
        return clickArgs.copy(map, i);
    }

    public final Map<String, Object> component1() {
        return this.params;
    }

    /* renamed from: component2, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    public final ClickArgs copy(Map<String, ? extends Object> params, int priority) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        return new ClickArgs(params, priority);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClickArgs)) {
            return false;
        }
        ClickArgs clickArgs = (ClickArgs) other;
        return Intrinsics.areEqual(this.params, clickArgs.params) && this.priority == clickArgs.priority;
    }

    public int hashCode() {
        Map<String, Object> map = this.params;
        return ((map != null ? map.hashCode() : 0) * 31) + Integer.hashCode(this.priority);
    }

    public String toString() {
        return "ClickArgs(params=" + this.params + ", priority=" + this.priority + ")";
    }

    public ClickArgs(Map<String, ? extends Object> params, int i) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.params = params;
        this.priority = i;
    }

    public /* synthetic */ ClickArgs(Map map, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? MapsKt.emptyMap() : map, (i2 & 2) != 0 ? 0 : i);
    }

    public final Map<String, Object> getParams() {
        return this.params;
    }

    public final int getPriority() {
        return this.priority;
    }
}
