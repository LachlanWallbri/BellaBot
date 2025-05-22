package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

/* loaded from: classes9.dex */
public class Registry {
    private final Cache<Converter> cache = new ConcurrentCache();
    private final RegistryBinder binder = new RegistryBinder();

    public Converter lookup(Class cls) throws Exception {
        Converter fetch = this.cache.fetch(cls);
        return fetch == null ? create(cls) : fetch;
    }

    private Converter create(Class cls) throws Exception {
        Converter lookup = this.binder.lookup(cls);
        if (lookup != null) {
            this.cache.cache(cls, lookup);
        }
        return lookup;
    }

    public Registry bind(Class cls, Class cls2) throws Exception {
        if (cls != null) {
            this.binder.bind(cls, cls2);
        }
        return this;
    }

    public Registry bind(Class cls, Converter converter) throws Exception {
        if (cls != null) {
            this.cache.cache(cls, converter);
        }
        return this;
    }
}
