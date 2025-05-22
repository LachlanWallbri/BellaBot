package io.grpc.internal;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class InUseStateAggregator<T> {
    private final Set<T> inUseObjects = Collections.newSetFromMap(new IdentityHashMap());

    protected abstract void handleInUse();

    protected abstract void handleNotInUse();

    public final void updateObjectInUse(T t, boolean z) {
        int size = this.inUseObjects.size();
        if (z) {
            this.inUseObjects.add(t);
            if (size == 0) {
                handleInUse();
                return;
            }
            return;
        }
        if (this.inUseObjects.remove(t) && size == 1) {
            handleNotInUse();
        }
    }

    public final boolean isInUse() {
        return !this.inUseObjects.isEmpty();
    }
}
