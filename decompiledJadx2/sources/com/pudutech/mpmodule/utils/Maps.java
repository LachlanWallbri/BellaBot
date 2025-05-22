package com.pudutech.mpmodule.utils;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class Maps {
    public static <T, N> HashMap<T, N> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> InnerMap<K, V> newHashMap(K k, V v) {
        return InnerMap.init(k, v);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class InnerMap<K, V> {
        private HashMap<K, V> mValue = new HashMap<>();

        private InnerMap(K k, V v) {
            this.mValue.put(k, v);
        }

        public InnerMap<K, V> add(K k, V v) {
            this.mValue.put(k, v);
            return this;
        }

        public HashMap<K, V> commit() {
            return this.mValue;
        }

        protected static <M, N> InnerMap<M, N> init(M m, N n) {
            return new InnerMap<>(m, n);
        }
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }
}
