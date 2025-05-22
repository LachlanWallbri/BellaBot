package io.netty.handler.codec.serialization;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface ClassResolver {
    Class<?> resolve(String str) throws ClassNotFoundException;
}
