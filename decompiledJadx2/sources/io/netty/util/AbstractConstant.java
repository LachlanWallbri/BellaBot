package io.netty.util;

import io.netty.util.AbstractConstant;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractConstant<T extends AbstractConstant<T>> implements Constant<T> {
    private static final AtomicLong uniqueIdGenerator = new AtomicLong();

    /* renamed from: id */
    private final int f8576id;
    private final String name;
    private final long uniquifier = uniqueIdGenerator.getAndIncrement();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractConstant(int i, String str) {
        this.f8576id = i;
        this.name = str;
    }

    @Override // io.netty.util.Constant
    public final String name() {
        return this.name;
    }

    @Override // io.netty.util.Constant
    /* renamed from: id */
    public final int mo3943id() {
        return this.f8576id;
    }

    public final String toString() {
        return name();
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.lang.Comparable
    public final int compareTo(T t) {
        if (this == t) {
            return 0;
        }
        int hashCode = hashCode() - t.hashCode();
        if (hashCode != 0) {
            return hashCode;
        }
        long j = this.uniquifier;
        long j2 = t.uniquifier;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        throw new Error("failed to compare two different constants");
    }
}
