package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: MapsJVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0081\b\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0001\u001a2\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\f\u001aY\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u000e\"\u000e\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u000f\"\u0004\b\u0001\u0010\n2*\u0010\u0010\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\f0\u0011\"\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\f¢\u0006\u0002\u0010\u0012\u001a@\u0010\u0013\u001a\u0002H\n\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u00142\u0006\u0010\u0015\u001a\u0002H\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\n0\u0017H\u0086\b¢\u0006\u0002\u0010\u0018\u001a\u0019\u0010\u0019\u001a\u00020\u001a*\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0\bH\u0087\b\u001a2\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\bH\u0000\u001a1\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\bH\u0081\b\u001a:\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u000e\"\u000e\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u000f\"\u0004\b\u0001\u0010\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b\u001a@\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u000e\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b2\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\t0 \"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"INT_MAX_POWER_OF_TWO", "", "checkBuilderCapacity", "", "capacity", "mapCapacity", "expectedSize", "mapOf", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "pair", "Lkotlin/Pair;", "sortedMapOf", "Ljava/util/SortedMap;", "", "pairs", "", "([Lkotlin/Pair;)Ljava/util/SortedMap;", "getOrPut", "Ljava/util/concurrent/ConcurrentMap;", TransferTable.COLUMN_KEY, "defaultValue", "Lkotlin/Function0;", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toProperties", "Ljava/util/Properties;", "", "toSingletonMap", "toSingletonMapOrSelf", "toSortedMap", "comparator", "Ljava/util/Comparator;", "kotlin-stdlib"}, m3962k = 5, m3963mv = {1, 1, 16}, m3965xi = 1, m3966xs = "kotlin/collections/MapsKt")
/* loaded from: classes2.dex */
public class MapsKt__MapsJVMKt extends MapsKt__MapWithDefaultKt {
    private static final int INT_MAX_POWER_OF_TWO = 1073741824;

    private static final void checkBuilderCapacity(int i) {
    }

    public static final int mapCapacity(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static final <K, V> Map<K, V> mapOf(Pair<? extends K, ? extends V> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        Map<K, V> singletonMap = Collections.singletonMap(pair.getFirst(), pair.getSecond());
        Intrinsics.checkExpressionValueIsNotNull(singletonMap, "java.util.Collections.si…(pair.first, pair.second)");
        return singletonMap;
    }

    public static final <K, V> V getOrPut(ConcurrentMap<K, V> getOrPut, K k, Function0<? extends V> defaultValue) {
        Intrinsics.checkParameterIsNotNull(getOrPut, "$this$getOrPut");
        Intrinsics.checkParameterIsNotNull(defaultValue, "defaultValue");
        V v = getOrPut.get(k);
        if (v != null) {
            return v;
        }
        V invoke = defaultValue.invoke();
        V putIfAbsent = getOrPut.putIfAbsent(k, invoke);
        return putIfAbsent != null ? putIfAbsent : invoke;
    }

    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> toSortedMap(Map<? extends K, ? extends V> toSortedMap) {
        Intrinsics.checkParameterIsNotNull(toSortedMap, "$this$toSortedMap");
        return new TreeMap(toSortedMap);
    }

    public static final <K, V> SortedMap<K, V> toSortedMap(Map<? extends K, ? extends V> toSortedMap, Comparator<? super K> comparator) {
        Intrinsics.checkParameterIsNotNull(toSortedMap, "$this$toSortedMap");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        TreeMap treeMap = new TreeMap(comparator);
        treeMap.putAll(toSortedMap);
        return treeMap;
    }

    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> sortedMapOf(Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        TreeMap treeMap = new TreeMap();
        MapsKt.putAll(treeMap, pairs);
        return treeMap;
    }

    private static final Properties toProperties(Map<String, String> map) {
        Properties properties = new Properties();
        properties.putAll(map);
        return properties;
    }

    private static final <K, V> Map<K, V> toSingletonMapOrSelf(Map<K, ? extends V> map) {
        return MapsKt.toSingletonMap(map);
    }

    public static final <K, V> Map<K, V> toSingletonMap(Map<? extends K, ? extends V> toSingletonMap) {
        Intrinsics.checkParameterIsNotNull(toSingletonMap, "$this$toSingletonMap");
        Map.Entry<? extends K, ? extends V> next = toSingletonMap.entrySet().iterator().next();
        Map<K, V> singletonMap = Collections.singletonMap(next.getKey(), next.getValue());
        Intrinsics.checkExpressionValueIsNotNull(singletonMap, "java.util.Collections.singletonMap(key, value)");
        Intrinsics.checkExpressionValueIsNotNull(singletonMap, "with(entries.iterator().…ingletonMap(key, value) }");
        return singletonMap;
    }
}
