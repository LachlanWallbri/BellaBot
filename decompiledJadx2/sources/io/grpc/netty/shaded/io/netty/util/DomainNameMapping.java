package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.net.IDN;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public class DomainNameMapping<V> implements Mapping<String, V> {
    final V defaultValue;
    private final Map<String, V> map;
    private final Map<String, V> unmodifiableMap;

    @Deprecated
    public DomainNameMapping(V v) {
        this(4, v);
    }

    @Deprecated
    public DomainNameMapping(int i, V v) {
        this(new LinkedHashMap(i), v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DomainNameMapping(Map<String, V> map, V v) {
        this.defaultValue = (V) ObjectUtil.checkNotNull(v, "defaultValue");
        this.map = map;
        this.unmodifiableMap = map != null ? Collections.unmodifiableMap(map) : null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public DomainNameMapping<V> add(String str, V v) {
        this.map.put(normalizeHostname((String) ObjectUtil.checkNotNull(str, "hostname")), ObjectUtil.checkNotNull(v, "output"));
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean matches(String str, String str2) {
        if (str.startsWith("*.")) {
            return str.regionMatches(2, str2, 0, str2.length()) || StringUtil.commonSuffixOfLength(str2, str, str.length() - 1);
        }
        return str.equals(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String normalizeHostname(String str) {
        if (needsNormalization(str)) {
            str = IDN.toASCII(str, 1);
        }
        return str.toLowerCase(Locale.US);
    }

    private static boolean needsNormalization(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > 127) {
                return true;
            }
        }
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.Mapping
    public V map(String str) {
        if (str != null) {
            String normalizeHostname = normalizeHostname(str);
            for (Map.Entry<String, V> entry : this.map.entrySet()) {
                if (matches(entry.getKey(), normalizeHostname)) {
                    return entry.getValue();
                }
            }
        }
        return this.defaultValue;
    }

    public Map<String, V> asMap() {
        return this.unmodifiableMap;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(default: " + this.defaultValue + ", map: " + this.map + ')';
    }
}
