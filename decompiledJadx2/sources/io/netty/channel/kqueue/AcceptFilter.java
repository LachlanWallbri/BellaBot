package io.netty.channel.kqueue;

import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class AcceptFilter {
    static final AcceptFilter PLATFORM_UNSUPPORTED = new AcceptFilter("", "");
    private final String filterArgs;
    private final String filterName;

    public AcceptFilter(String str, String str2) {
        this.filterName = (String) ObjectUtil.checkNotNull(str, "filterName");
        this.filterArgs = (String) ObjectUtil.checkNotNull(str2, "filterArgs");
    }

    public String filterName() {
        return this.filterName;
    }

    public String filterArgs() {
        return this.filterArgs;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AcceptFilter)) {
            return false;
        }
        AcceptFilter acceptFilter = (AcceptFilter) obj;
        return this.filterName.equals(acceptFilter.filterName) && this.filterArgs.equals(acceptFilter.filterArgs);
    }

    public int hashCode() {
        return ((this.filterName.hashCode() + 31) * 31) + this.filterArgs.hashCode();
    }

    public String toString() {
        return this.filterName + ", " + this.filterArgs;
    }
}
