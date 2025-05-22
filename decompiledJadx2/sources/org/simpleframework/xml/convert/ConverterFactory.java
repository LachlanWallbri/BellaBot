package org.simpleframework.xml.convert;

import java.lang.reflect.Constructor;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

/* loaded from: classes9.dex */
class ConverterFactory {
    private final Cache<Converter> cache = new ConcurrentCache();

    public Converter getInstance(Class cls) throws Exception {
        Converter fetch = this.cache.fetch(cls);
        return fetch == null ? getConverter(cls) : fetch;
    }

    public Converter getInstance(Convert convert) throws Exception {
        Class<? extends Converter> value = convert.value();
        if (value.isInterface()) {
            throw new ConvertException("Can not instantiate %s", value);
        }
        return getInstance(value);
    }

    private Converter getConverter(Class cls) throws Exception {
        Constructor constructor = getConstructor(cls);
        if (constructor == null) {
            throw new ConvertException("No default constructor for %s", cls);
        }
        return getConverter(cls, constructor);
    }

    private Converter getConverter(Class cls, Constructor constructor) throws Exception {
        Converter converter = (Converter) constructor.newInstance(new Object[0]);
        if (converter != null) {
            this.cache.cache(cls, converter);
        }
        return converter;
    }

    private Constructor getConstructor(Class cls) throws Exception {
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        if (!declaredConstructor.isAccessible()) {
            declaredConstructor.setAccessible(true);
        }
        return declaredConstructor;
    }
}
