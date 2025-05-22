package io.grpc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Attributes {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Attributes EMPTY = new Attributes(Collections.emptyMap());
    private final Map<Key<?>, Object> data;

    private Attributes(Map<Key<?>, Object> map) {
        this.data = map;
    }

    @Nullable
    public <T> T get(Key<T> key) {
        return (T) this.data.get(key);
    }

    @Deprecated
    public Set<Key<?>> keys() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    Set<Key<?>> keysForTest() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    @Deprecated
    public static Builder newBuilder(Attributes attributes) {
        Preconditions.checkNotNull(attributes, "base");
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Key<T> {
        private final String debugString;

        private Key(String str) {
            this.debugString = str;
        }

        public String toString() {
            return this.debugString;
        }

        @Deprecated
        /* renamed from: of */
        public static <T> Key<T> m3898of(String str) {
            return new Key<>(str);
        }

        public static <T> Key<T> create(String str) {
            return new Key<>(str);
        }
    }

    public String toString() {
        return this.data.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attributes attributes = (Attributes) obj;
        if (this.data.size() != attributes.data.size()) {
            return false;
        }
        for (Map.Entry<Key<?>, Object> entry : this.data.entrySet()) {
            if (!attributes.data.containsKey(entry.getKey()) || !Objects.equal(entry.getValue(), attributes.data.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (Map.Entry<Key<?>, Object> entry : this.data.entrySet()) {
            i += Objects.hashCode(entry.getKey(), entry.getValue());
        }
        return i;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Attributes base;
        private Map<Key<?>, Object> newdata;

        private Builder(Attributes attributes) {
            this.base = attributes;
        }

        private Map<Key<?>, Object> data(int i) {
            if (this.newdata == null) {
                this.newdata = new IdentityHashMap(i);
            }
            return this.newdata;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public <T> Builder set(Key<T> key, T t) {
            data(1).put(key, t);
            return this;
        }

        public <T> Builder discard(Key<T> key) {
            if (this.base.data.containsKey(key)) {
                IdentityHashMap identityHashMap = new IdentityHashMap(this.base.data);
                identityHashMap.remove(key);
                this.base = new Attributes(identityHashMap);
            }
            Map<Key<?>, Object> map = this.newdata;
            if (map != null) {
                map.remove(key);
            }
            return this;
        }

        public Builder setAll(Attributes attributes) {
            data(attributes.data.size()).putAll(attributes.data);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Attributes build() {
            if (this.newdata != null) {
                for (Map.Entry entry : this.base.data.entrySet()) {
                    if (!this.newdata.containsKey(entry.getKey())) {
                        this.newdata.put(entry.getKey(), entry.getValue());
                    }
                }
                this.base = new Attributes(this.newdata);
                this.newdata = null;
            }
            return this.base;
        }
    }
}
