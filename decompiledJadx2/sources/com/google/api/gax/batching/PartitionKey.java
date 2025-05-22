package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.common.collect.ImmutableList;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class PartitionKey {
    private final int hash;
    private final ImmutableList<Object> keys;

    public PartitionKey(Object... objArr) {
        this.keys = ImmutableList.copyOf(objArr);
        int i = 1;
        for (Object obj : objArr) {
            i = (i * 31) + obj.hashCode();
        }
        this.hash = i;
    }

    public int hashCode() {
        return this.hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PartitionKey)) {
            return false;
        }
        PartitionKey partitionKey = (PartitionKey) obj;
        if (this.keys.size() != partitionKey.keys.size()) {
            return false;
        }
        for (int i = 0; i < this.keys.size(); i++) {
            if (!this.keys.get(i).equals(partitionKey.keys.get(i))) {
                return false;
            }
        }
        return true;
    }
}
