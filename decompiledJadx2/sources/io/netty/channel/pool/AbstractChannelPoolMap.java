package io.netty.channel.pool;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.netty.channel.pool.ChannelPool;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ReadOnlyIterator;
import java.io.Closeable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractChannelPoolMap<K, P extends ChannelPool> implements ChannelPoolMap<K, P>, Iterable<Map.Entry<K, P>>, Closeable {
    private final ConcurrentMap<K, P> map = PlatformDependent.newConcurrentHashMap();

    protected abstract P newPool(K k);

    @Override // io.netty.channel.pool.ChannelPoolMap
    public final P get(K k) {
        P p = this.map.get(ObjectUtil.checkNotNull(k, TransferTable.COLUMN_KEY));
        if (p != null) {
            return p;
        }
        P newPool = newPool(k);
        P putIfAbsent = this.map.putIfAbsent(k, newPool);
        if (putIfAbsent == null) {
            return newPool;
        }
        newPool.close();
        return putIfAbsent;
    }

    public final boolean remove(K k) {
        P remove = this.map.remove(ObjectUtil.checkNotNull(k, TransferTable.COLUMN_KEY));
        if (remove == null) {
            return false;
        }
        remove.close();
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator<Map.Entry<K, P>> iterator() {
        return new ReadOnlyIterator(this.map.entrySet().iterator());
    }

    public final int size() {
        return this.map.size();
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // io.netty.channel.pool.ChannelPoolMap
    public final boolean contains(K k) {
        return this.map.containsKey(ObjectUtil.checkNotNull(k, TransferTable.COLUMN_KEY));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Iterator<K> it = this.map.keySet().iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }
}
