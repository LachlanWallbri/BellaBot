package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.lang.reflect.Constructor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ReflectiveChannelFactory<T extends Channel> implements ChannelFactory<T> {
    private final Constructor<? extends T> constructor;

    public ReflectiveChannelFactory(Class<? extends T> cls) {
        ObjectUtil.checkNotNull(cls, "clazz");
        try {
            this.constructor = cls.getConstructor(new Class[0]);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + StringUtil.simpleClassName((Class<?>) cls) + " does not have a public non-arg constructor", e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFactory, io.grpc.netty.shaded.io.netty.bootstrap.ChannelFactory
    public T newChannel() {
        try {
            return this.constructor.newInstance(new Object[0]);
        } catch (Throwable th) {
            throw new ChannelException("Unable to create Channel from class " + this.constructor.getDeclaringClass(), th);
        }
    }

    public String toString() {
        return StringUtil.simpleClassName((Class<?>) ReflectiveChannelFactory.class) + '(' + StringUtil.simpleClassName((Class<?>) this.constructor.getDeclaringClass()) + ".class)";
    }
}
