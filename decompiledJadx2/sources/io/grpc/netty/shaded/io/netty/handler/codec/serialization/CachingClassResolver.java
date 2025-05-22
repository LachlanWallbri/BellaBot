package io.grpc.netty.shaded.io.netty.handler.codec.serialization;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class CachingClassResolver implements ClassResolver {
    private final Map<String, Class<?>> classCache;
    private final ClassResolver delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CachingClassResolver(ClassResolver classResolver, Map<String, Class<?>> map) {
        this.delegate = classResolver;
        this.classCache = map;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.serialization.ClassResolver
    public Class<?> resolve(String str) throws ClassNotFoundException {
        Class<?> cls = this.classCache.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> resolve = this.delegate.resolve(str);
        this.classCache.put(str, resolve);
        return resolve;
    }
}
