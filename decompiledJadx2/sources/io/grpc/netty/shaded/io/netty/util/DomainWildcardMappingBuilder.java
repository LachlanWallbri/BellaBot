package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DomainWildcardMappingBuilder<V> {
    private final V defaultValue;
    private final Map<String, V> map;

    public DomainWildcardMappingBuilder(V v) {
        this(4, v);
    }

    public DomainWildcardMappingBuilder(int i, V v) {
        this.defaultValue = (V) ObjectUtil.checkNotNull(v, "defaultValue");
        this.map = new LinkedHashMap(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DomainWildcardMappingBuilder<V> add(String str, V v) {
        this.map.put(normalizeHostName(str), ObjectUtil.checkNotNull(v, "output"));
        return this;
    }

    private String normalizeHostName(String str) {
        ObjectUtil.checkNotNull(str, "hostname");
        if (str.isEmpty() || str.charAt(0) == '.') {
            throw new IllegalArgumentException("Hostname '" + str + "' not valid");
        }
        String normalize = ImmutableDomainWildcardMapping.normalize((String) ObjectUtil.checkNotNull(str, "hostname"));
        if (normalize.charAt(0) != '*') {
            return normalize;
        }
        if (normalize.length() < 3 || normalize.charAt(1) != '.') {
            throw new IllegalArgumentException("Wildcard Hostname '" + normalize + "'not valid");
        }
        return normalize.substring(1);
    }

    public Mapping<String, V> build() {
        return new ImmutableDomainWildcardMapping(this.defaultValue, this.map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ImmutableDomainWildcardMapping<V> implements Mapping<String, V> {
        private static final String REPR_HEADER = "ImmutableDomainWildcardMapping(default: ";
        private static final String REPR_MAP_CLOSING = ")";
        private static final String REPR_MAP_OPENING = ", map: ";
        private final V defaultValue;
        private final Map<String, V> map;

        ImmutableDomainWildcardMapping(V v, Map<String, V> map) {
            this.defaultValue = v;
            this.map = new LinkedHashMap(map);
        }

        @Override // io.grpc.netty.shaded.io.netty.util.Mapping
        public V map(String str) {
            V v;
            if (str != null) {
                String normalize = normalize(str);
                V v2 = this.map.get(normalize);
                if (v2 != null) {
                    return v2;
                }
                int indexOf = normalize.indexOf(46);
                if (indexOf != -1 && (v = this.map.get(normalize.substring(indexOf))) != null) {
                    return v;
                }
            }
            return this.defaultValue;
        }

        static String normalize(String str) {
            return DomainNameMapping.normalizeHostname(str);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(REPR_HEADER);
            sb.append(this.defaultValue);
            sb.append(REPR_MAP_OPENING);
            sb.append('{');
            for (Map.Entry<String, V> entry : this.map.entrySet()) {
                String key = entry.getKey();
                if (key.charAt(0) == '.') {
                    key = '*' + key;
                }
                sb.append(key);
                sb.append('=');
                sb.append(entry.getValue());
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append('}');
            sb.append(REPR_MAP_CLOSING);
            return sb.toString();
        }
    }
}
