package com.pudutech.lidar;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ListLidarNodePool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rR\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/lidar/ListLidarNodePool;", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/LidarNode;", "Lkotlin/collections/ArrayList;", "capacity", "", "(I)V", "flags", "isInUse", "", "()Z", ES6Iterator.NEXT_METHOD, "clearAllNode", "", "recycle", "recycleUnchecked", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ListLidarNodePool extends ArrayList<LidarNode> {
    private static final int DEFAULT_ARRAYLIST_CAPACITY = 1000;
    public static final int FLAG_IN_USE = 1;
    private static final int MAX_POOL_SIZE = 20;
    private static ListLidarNodePool sPool;
    private static int sPoolSize;
    private int flags;
    private ListLidarNodePool next;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object sPoolSync = new Object();

    @JvmStatic
    public static final ListLidarNodePool obtain() {
        return INSTANCE.obtain();
    }

    @JvmStatic
    public static final ListLidarNodePool obtain(int i) {
        return INSTANCE.obtain(i);
    }

    private ListLidarNodePool(int i) {
        ensureCapacity(i);
    }

    public /* synthetic */ ListLidarNodePool(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public /* bridge */ boolean contains(LidarNode lidarNode) {
        return super.contains((Object) lidarNode);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof LidarNode) {
            return contains((LidarNode) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(LidarNode lidarNode) {
        return super.indexOf((Object) lidarNode);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof LidarNode) {
            return indexOf((LidarNode) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(LidarNode lidarNode) {
        return super.lastIndexOf((Object) lidarNode);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof LidarNode) {
            return lastIndexOf((LidarNode) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ LidarNode remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(LidarNode lidarNode) {
        return super.remove((Object) lidarNode);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof LidarNode) {
            return remove((LidarNode) obj);
        }
        return false;
    }

    public /* bridge */ LidarNode removeAt(int i) {
        return (LidarNode) super.remove(i);
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

    public final void clearAllNode() {
        Iterator<LidarNode> it = iterator();
        while (it.hasNext()) {
            it.next().recycle();
        }
        clear();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ListLidarNodePool.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\bH\u0007J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/lidar/ListLidarNodePool$Companion;", "", "()V", "DEFAULT_ARRAYLIST_CAPACITY", "", "FLAG_IN_USE", "MAX_POOL_SIZE", "sPool", "Lcom/pudutech/lidar/ListLidarNodePool;", "sPoolSize", "sPoolSync", "getSPoolSync", "()Ljava/lang/Object;", "obtain", "capacity", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Object getSPoolSync() {
            return ListLidarNodePool.sPoolSync;
        }

        @JvmStatic
        public final ListLidarNodePool obtain(int capacity) {
            synchronized (getSPoolSync()) {
                DefaultConstructorMarker defaultConstructorMarker = null;
                if (ListLidarNodePool.sPool != null) {
                    ListLidarNodePool listLidarNodePool = ListLidarNodePool.sPool;
                    if (listLidarNodePool == null) {
                        Intrinsics.throwNpe();
                    }
                    ListLidarNodePool.sPool = listLidarNodePool.next;
                    listLidarNodePool.next = (ListLidarNodePool) null;
                    listLidarNodePool.flags = 0;
                    listLidarNodePool.ensureCapacity(capacity);
                    ListLidarNodePool.sPoolSize--;
                    return listLidarNodePool;
                }
                Unit unit = Unit.INSTANCE;
                return new ListLidarNodePool(capacity, defaultConstructorMarker);
            }
        }

        @JvmStatic
        public final ListLidarNodePool obtain() {
            return obtain(1000);
        }
    }
}
