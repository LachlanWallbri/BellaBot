package org.simpleframework.xml.transform;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

/* loaded from: classes9.dex */
public class Transformer {
    private final Cache<Transform> cache = new ConcurrentCache();
    private final Cache<Object> error = new ConcurrentCache();
    private final Matcher matcher;

    public Transformer(Matcher matcher) {
        this.matcher = new DefaultMatcher(matcher);
    }

    public Object read(String str, Class cls) throws Exception {
        Transform lookup = lookup(cls);
        if (lookup == null) {
            throw new TransformException("Transform of %s not supported", cls);
        }
        return lookup.read(str);
    }

    public String write(Object obj, Class cls) throws Exception {
        Transform lookup = lookup(cls);
        if (lookup == null) {
            throw new TransformException("Transform of %s not supported", cls);
        }
        return lookup.write(obj);
    }

    public boolean valid(Class cls) throws Exception {
        return lookup(cls) != null;
    }

    private Transform lookup(Class cls) throws Exception {
        if (this.error.contains(cls)) {
            return null;
        }
        Transform fetch = this.cache.fetch(cls);
        return fetch != null ? fetch : match(cls);
    }

    private Transform match(Class cls) throws Exception {
        Transform match = this.matcher.match(cls);
        if (match != null) {
            this.cache.cache(cls, match);
        } else {
            this.error.cache(cls, this);
        }
        return match;
    }
}
