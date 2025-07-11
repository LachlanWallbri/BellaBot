package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class NaturalOrdering extends Ordering<Comparable> implements Serializable {
    static final NaturalOrdering INSTANCE = new NaturalOrdering();
    private static final long serialVersionUID = 0;
    private transient Ordering<Comparable> nullsFirst;
    private transient Ordering<Comparable> nullsLast;

    public String toString() {
        return "Ordering.natural()";
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Comparable comparable, Comparable comparable2) {
        Preconditions.checkNotNull(comparable);
        Preconditions.checkNotNull(comparable2);
        return comparable.compareTo(comparable2);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsFirst() {
        Ordering<S> ordering = (Ordering<S>) this.nullsFirst;
        if (ordering != null) {
            return ordering;
        }
        Ordering<S> nullsFirst = super.nullsFirst();
        this.nullsFirst = nullsFirst;
        return nullsFirst;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsLast() {
        Ordering<S> ordering = (Ordering<S>) this.nullsLast;
        if (ordering != null) {
            return ordering;
        }
        Ordering<S> nullsLast = super.nullsLast();
        this.nullsLast = nullsLast;
        return nullsLast;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> reverse() {
        return ReverseNaturalOrdering.INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    private NaturalOrdering() {
    }
}
