package io.netty.util;

import io.netty.util.Constant;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class ConstantPool<T extends Constant<T>> {
    private final ConcurrentMap<String, T> constants = PlatformDependent.newConcurrentHashMap();
    private final AtomicInteger nextId = new AtomicInteger(1);

    protected abstract T newConstant(int i, String str);

    public T valueOf(Class<?> cls, String str) {
        if (cls == null) {
            throw new NullPointerException("firstNameComponent");
        }
        if (str == null) {
            throw new NullPointerException("secondNameComponent");
        }
        return valueOf(cls.getName() + '#' + str);
    }

    public T valueOf(String str) {
        checkNotNullAndNotEmpty(str);
        return getOrCreate(str);
    }

    private T getOrCreate(String str) {
        T t = this.constants.get(str);
        if (t != null) {
            return t;
        }
        T newConstant = newConstant(nextId(), str);
        T putIfAbsent = this.constants.putIfAbsent(str, newConstant);
        return putIfAbsent == null ? newConstant : putIfAbsent;
    }

    public boolean exists(String str) {
        checkNotNullAndNotEmpty(str);
        return this.constants.containsKey(str);
    }

    public T newInstance(String str) {
        checkNotNullAndNotEmpty(str);
        return createOrThrow(str);
    }

    private T createOrThrow(String str) {
        if (this.constants.get(str) == null) {
            T newConstant = newConstant(nextId(), str);
            if (this.constants.putIfAbsent(str, newConstant) == null) {
                return newConstant;
            }
        }
        throw new IllegalArgumentException(String.format("'%s' is already in use", str));
    }

    private static String checkNotNullAndNotEmpty(String str) {
        ObjectUtil.checkNotNull(str, "name");
        if (str.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }
        return str;
    }

    @Deprecated
    public final int nextId() {
        return this.nextId.getAndIncrement();
    }
}
