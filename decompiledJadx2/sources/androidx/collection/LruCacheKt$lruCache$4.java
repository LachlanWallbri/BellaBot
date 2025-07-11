package androidx.collection;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: LruCache.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001J\u0017\u0010\u0002\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0003\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0004J/\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u00012\b\u0010\n\u001a\u0004\u0018\u00018\u0001H\u0014¢\u0006\u0002\u0010\u000bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u0001H\u0014¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, m3961d2 = {"androidx/collection/LruCacheKt$lruCache$4", "Landroidx/collection/LruCache;", "create", TransferTable.COLUMN_KEY, "(Ljava/lang/Object;)Ljava/lang/Object;", "entryRemoved", "", "evicted", "", "oldValue", "newValue", "(ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "sizeOf", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Ljava/lang/Object;)I", "collection-ktx"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes.dex */
public final class LruCacheKt$lruCache$4<K, V> extends LruCache<K, V> {
    final /* synthetic */ Function1 $create;
    final /* synthetic */ int $maxSize;
    final /* synthetic */ Function4 $onEntryRemoved;
    final /* synthetic */ Function2 $sizeOf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LruCacheKt$lruCache$4(Function2 function2, Function1 function1, Function4 function4, int i, int i2) {
        super(i2);
        this.$sizeOf = function2;
        this.$create = function1;
        this.$onEntryRemoved = function4;
        this.$maxSize = i;
    }

    @Override // androidx.collection.LruCache
    protected int sizeOf(K key, V value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return ((Number) this.$sizeOf.invoke(key, value)).intValue();
    }

    @Override // androidx.collection.LruCache
    protected V create(K key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (V) this.$create.invoke(key);
    }

    @Override // androidx.collection.LruCache
    protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(oldValue, "oldValue");
        this.$onEntryRemoved.invoke(Boolean.valueOf(evicted), key, oldValue, newValue);
    }
}
