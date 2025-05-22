package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HedgingPolicy {
    final long hedgingDelayNanos;
    final int maxAttempts;
    final Set<Status.Code> nonFatalStatusCodes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HedgingPolicy(int i, long j, Set<Status.Code> set) {
        this.maxAttempts = i;
        this.hedgingDelayNanos = j;
        this.nonFatalStatusCodes = ImmutableSet.copyOf((Collection) set);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HedgingPolicy hedgingPolicy = (HedgingPolicy) obj;
        return this.maxAttempts == hedgingPolicy.maxAttempts && this.hedgingDelayNanos == hedgingPolicy.hedgingDelayNanos && Objects.equal(this.nonFatalStatusCodes, hedgingPolicy.nonFatalStatusCodes);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.hedgingDelayNanos), this.nonFatalStatusCodes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxAttempts", this.maxAttempts).add("hedgingDelayNanos", this.hedgingDelayNanos).add("nonFatalStatusCodes", this.nonFatalStatusCodes).toString();
    }
}
