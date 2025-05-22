package com.google.api.gax.batching;

import com.google.api.core.SettableApiFuture;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_BatchEntry<ElementT, ElementResultT> extends BatchEntry<ElementT, ElementResultT> {
    private final ElementT element;
    private final SettableApiFuture<ElementResultT> resultFuture;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_BatchEntry(@Nullable ElementT elementt, SettableApiFuture<ElementResultT> settableApiFuture) {
        this.element = elementt;
        if (settableApiFuture == null) {
            throw new NullPointerException("Null resultFuture");
        }
        this.resultFuture = settableApiFuture;
    }

    @Override // com.google.api.gax.batching.BatchEntry
    @Nullable
    public ElementT getElement() {
        return this.element;
    }

    @Override // com.google.api.gax.batching.BatchEntry
    public SettableApiFuture<ElementResultT> getResultFuture() {
        return this.resultFuture;
    }

    public String toString() {
        return "BatchEntry{element=" + this.element + ", resultFuture=" + this.resultFuture + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BatchEntry)) {
            return false;
        }
        BatchEntry batchEntry = (BatchEntry) obj;
        ElementT elementt = this.element;
        if (elementt != null ? elementt.equals(batchEntry.getElement()) : batchEntry.getElement() == null) {
            if (this.resultFuture.equals(batchEntry.getResultFuture())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        ElementT elementt = this.element;
        return (((elementt == null ? 0 : elementt.hashCode()) ^ 1000003) * 1000003) ^ this.resultFuture.hashCode();
    }
}
