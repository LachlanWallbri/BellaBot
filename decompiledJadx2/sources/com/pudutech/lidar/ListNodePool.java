package com.pudutech.lidar;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ListNodePool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000e*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/lidar/ListNodePool;", ExifInterface.LONGITUDE_EAST, "Ljava/util/ArrayList;", "capacity", "", "(I)V", "flags", "isInUse", "", "()Z", ES6Iterator.NEXT_METHOD, "recycle", "", "recycleUnchecked", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ListNodePool<E> extends ArrayList<E> {
    private static final int DEFAULT_ARRAYLIST_CAPACITY = 500;
    public static final int FLAG_IN_USE = 1;
    private static final int MAX_POOL_SIZE = 20;
    private static ListNodePool<?> sPool;
    private static int sPoolSize;
    private int flags;
    private ListNodePool<?> next;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object sPoolSync = new Object();

    @JvmStatic
    public static final ListNodePool<?> obtain() {
        return INSTANCE.obtain();
    }

    @JvmStatic
    public static final ListNodePool<?> obtain(int i) {
        return INSTANCE.obtain(i);
    }

    private ListNodePool(int i) {
        ensureCapacity(i);
    }

    public /* synthetic */ ListNodePool(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ E remove(int i) {
        return (E) removeAt(i);
    }

    public /* bridge */ Object removeAt(int i) {
        return super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }

    public final void recycle() {
        if (isInUse()) {
            return;
        }
        recycleUnchecked();
    }

    public final boolean isInUse() {
        return (this.flags & 1) == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void recycleUnchecked() {
        this.flags = 1;
        clear();
        synchronized (sPoolSync) {
            if (sPoolSize < 20) {
                this.next = sPool;
                sPool = this;
                sPoolSize++;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ListNodePool.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bH\u0007J\u0018\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b2\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/lidar/ListNodePool$Companion;", "", "()V", "DEFAULT_ARRAYLIST_CAPACITY", "", "FLAG_IN_USE", "MAX_POOL_SIZE", "sPool", "Lcom/pudutech/lidar/ListNodePool;", "sPoolSize", "sPoolSync", "getSPoolSync", "()Ljava/lang/Object;", "obtain", "capacity", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Object getSPoolSync() {
            return ListNodePool.sPoolSync;
        }

        public static /* synthetic */ ListNodePool obtain$default(Companion companion, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = 1000;
            }
            return companion.obtain(i);
        }

        @JvmStatic
        public final ListNodePool<?> obtain(int capacity) {
            synchronized (getSPoolSync()) {
                DefaultConstructorMarker defaultConstructorMarker = null;
                if (ListNodePool.sPool != null) {
                    ListNodePool<?> listNodePool = ListNodePool.sPool;
                    if (listNodePool == null) {
                        Intrinsics.throwNpe();
                    }
                    ListNodePool.sPool = ((ListNodePool) listNodePool).next;
                    ((ListNodePool) listNodePool).next = (ListNodePool) null;
                    ((ListNodePool) listNodePool).flags = 0;
                    listNodePool.ensureCapacity(capacity);
                    ListNodePool.sPoolSize--;
                    return listNodePool;
                }
                Unit unit = Unit.INSTANCE;
                return new ListNodePool<>(capacity, defaultConstructorMarker);
            }
        }

        @JvmStatic
        public final ListNodePool<?> obtain() {
            return obtain(500);
        }
    }
}
