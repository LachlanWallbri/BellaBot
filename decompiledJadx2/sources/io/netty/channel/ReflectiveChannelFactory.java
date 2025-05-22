package io.netty.channel;

import io.netty.channel.Channel;
import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class ReflectiveChannelFactory<T extends Channel> implements ChannelFactory<T> {
    private final Class<? extends T> clazz;

    public ReflectiveChannelFactory(Class<? extends T> cls) {
        if (cls == null) {
            throw new NullPointerException("clazz");
        }
        this.clazz = cls;
    }

    @Override // io.netty.channel.ChannelFactory, io.netty.bootstrap.ChannelFactory
    public T newChannel() {
        try {
            return this.clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            throw new ChannelException("Unable to create Channel from class " + this.clazz, th);
        }
    }

    public String toString() {
        return StringUtil.simpleClassName((Class<?>) this.clazz) + ".class";
    }
}
