package org.jboss.netty.util;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.util.internal.ConcurrentIdentityWeakKeyHashMap;

/* loaded from: classes7.dex */
public class DefaultObjectSizeEstimator implements ObjectSizeEstimator {
    private final ConcurrentMap<Class<?>, Integer> class2size = new ConcurrentIdentityWeakKeyHashMap();

    public DefaultObjectSizeEstimator() {
        this.class2size.put(Boolean.TYPE, 4);
        this.class2size.put(Byte.TYPE, 1);
        this.class2size.put(Character.TYPE, 2);
        this.class2size.put(Integer.TYPE, 4);
        this.class2size.put(Short.TYPE, 2);
        this.class2size.put(Long.TYPE, 8);
        this.class2size.put(Float.TYPE, 4);
        this.class2size.put(Double.TYPE, 8);
        this.class2size.put(Void.TYPE, 0);
    }

    @Override // org.jboss.netty.util.ObjectSizeEstimator
    public int estimateSize(Object obj) {
        int length;
        if (obj == null) {
            return 8;
        }
        int estimateSize = estimateSize(obj.getClass(), null) + 8;
        if (obj instanceof EstimatableObjectWrapper) {
            length = estimateSize(((EstimatableObjectWrapper) obj).unwrap());
        } else if (obj instanceof MessageEvent) {
            length = estimateSize(((MessageEvent) obj).getMessage());
        } else if (obj instanceof ChannelBuffer) {
            length = ((ChannelBuffer) obj).capacity();
        } else if (obj instanceof byte[]) {
            length = ((byte[]) obj).length;
        } else if (obj instanceof ByteBuffer) {
            length = ((ByteBuffer) obj).remaining();
        } else if (obj instanceof CharSequence) {
            length = ((CharSequence) obj).length() << 1;
        } else {
            if (obj instanceof Iterable) {
                Iterator it = ((Iterable) obj).iterator();
                while (it.hasNext()) {
                    estimateSize += estimateSize(it.next());
                }
            }
            return align(estimateSize);
        }
        estimateSize += length;
        return align(estimateSize);
    }

    private int estimateSize(Class<?> cls, Set<Class<?>> set) {
        Integer num = this.class2size.get(cls);
        if (num != null) {
            return num.intValue();
        }
        if (set != null) {
            if (set.contains(cls)) {
                return 0;
            }
        } else {
            set = new HashSet<>();
        }
        set.add(cls);
        Class<?> cls2 = cls;
        int i = 8;
        while (cls2 != null) {
            int i2 = i;
            for (Field field : cls2.getDeclaredFields()) {
                if ((field.getModifiers() & 8) == 0) {
                    i2 += estimateSize(field.getType(), set);
                }
            }
            cls2 = cls2.getSuperclass();
            i = i2;
        }
        set.remove(cls);
        int align = align(i);
        this.class2size.putIfAbsent(cls, Integer.valueOf(align));
        return align;
    }

    private static int align(int i) {
        int i2 = i % 8;
        return i2 != 0 ? i + (8 - i2) : i;
    }
}
