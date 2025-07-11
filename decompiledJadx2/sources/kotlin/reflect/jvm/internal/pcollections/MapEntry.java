package kotlin.reflect.jvm.internal.pcollections;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
final class MapEntry<K, V> implements Serializable {
    public final K key;
    public final V value;

    public MapEntry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MapEntry)) {
            return false;
        }
        MapEntry mapEntry = (MapEntry) obj;
        K k = this.key;
        if (k == null) {
            if (mapEntry.key != null) {
                return false;
            }
        } else if (!k.equals(mapEntry.key)) {
            return false;
        }
        V v = this.value;
        V v2 = mapEntry.value;
        if (v == null) {
            if (v2 != null) {
                return false;
            }
        } else if (!v.equals(v2)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        K k = this.key;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        return hashCode ^ (v != null ? v.hashCode() : 0);
    }

    public String toString() {
        return this.key + "=" + this.value;
    }
}
