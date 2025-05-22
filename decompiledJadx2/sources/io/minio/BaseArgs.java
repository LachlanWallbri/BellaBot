package io.minio;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public abstract class BaseArgs {
    protected Multimap<String, String> extraHeaders = Multimaps.unmodifiableMultimap(HashMultimap.create());
    protected Multimap<String, String> extraQueryParams = Multimaps.unmodifiableMultimap(HashMultimap.create());

    public Multimap<String, String> extraHeaders() {
        return this.extraHeaders;
    }

    public Multimap<String, String> extraQueryParams() {
        return this.extraQueryParams;
    }

    /* loaded from: classes7.dex */
    public static abstract class Builder<B extends Builder<B, A>, A extends BaseArgs> {
        protected List<Consumer<A>> operations = new ArrayList();

        protected abstract void validate(A a);

        /* JADX INFO: Access modifiers changed from: protected */
        public void validateNotNull(Object obj, String str) {
            if (obj != null) {
                return;
            }
            throw new IllegalArgumentException(str + " must not be null.");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void validateNotEmptyString(String str, String str2) {
            validateNotNull(str, str2);
            if (str.isEmpty()) {
                throw new IllegalArgumentException(str2 + " must be a non-empty string.");
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void validateNullOrNotEmptyString(String str, String str2) {
            if (str == null || !str.isEmpty()) {
                return;
            }
            throw new IllegalArgumentException(str2 + " must be a non-empty string.");
        }

        protected void validateNullOrPositive(Number number, String str) {
            if (number == null || number.longValue() >= 0) {
                return;
            }
            throw new IllegalArgumentException(str + " cannot be non-negative.");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Multimap<String, String> copyMultimap(Multimap<String, String> multimap) {
            HashMultimap create = HashMultimap.create();
            if (multimap != null) {
                create.putAll(multimap);
            }
            return Multimaps.unmodifiableMultimap(create);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Multimap<String, String> toMultimap(Map<String, String> map) {
            HashMultimap create = HashMultimap.create();
            if (map != null) {
                create.putAll(Multimaps.forMap(map));
            }
            return Multimaps.unmodifiableMultimap(create);
        }

        public B extraHeaders(Multimap<String, String> multimap) {
            final Multimap<String, String> copyMultimap = copyMultimap(multimap);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$BaseArgs$Builder$vB1VHrmLoe9HAJxoQpqVjlWzbDg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((BaseArgs) obj).extraHeaders = Multimap.this;
                }
            });
            return this;
        }

        public B extraQueryParams(Multimap<String, String> multimap) {
            final Multimap<String, String> copyMultimap = copyMultimap(multimap);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$BaseArgs$Builder$btLKxdK_gV6GQ5MPS2cXa24Xx0s
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((BaseArgs) obj).extraQueryParams = Multimap.this;
                }
            });
            return this;
        }

        public B extraHeaders(Map<String, String> map) {
            final Multimap<String, String> multimap = toMultimap(map);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$BaseArgs$Builder$8vdP31s_A2rvqnRkgjSXG9FAobY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((BaseArgs) obj).extraHeaders = Multimap.this;
                }
            });
            return this;
        }

        public B extraQueryParams(Map<String, String> map) {
            final Multimap<String, String> multimap = toMultimap(map);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$BaseArgs$Builder$dYoWtgqwiGkhvabgR0dr2tlT758
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((BaseArgs) obj).extraQueryParams = Multimap.this;
                }
            });
            return this;
        }

        private A newInstance() {
            try {
                for (Constructor<?> constructor : getClass().getEnclosingClass().getDeclaredConstructors()) {
                    if (constructor.getParameterCount() == 0) {
                        return (A) constructor.newInstance(new Object[0]);
                    }
                }
                throw new RuntimeException(getClass().getEnclosingClass() + " must have no argument constructor");
            } catch (IllegalAccessException | InstantiationException | SecurityException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        public A build() throws IllegalArgumentException {
            final A newInstance = newInstance();
            this.operations.forEach(new Consumer() { // from class: io.minio.-$$Lambda$BaseArgs$Builder$CcoORnL6LCWyynPRv1rrTRdiF_M
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Consumer) obj).accept(BaseArgs.this);
                }
            });
            validate(newInstance);
            return newInstance;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseArgs)) {
            return false;
        }
        BaseArgs baseArgs = (BaseArgs) obj;
        return Objects.equals(this.extraHeaders, baseArgs.extraHeaders) && Objects.equals(this.extraQueryParams, baseArgs.extraQueryParams);
    }

    public int hashCode() {
        return Objects.hash(this.extraHeaders, this.extraQueryParams);
    }
}
