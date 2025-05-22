package org.simpleframework.xml.core;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class ScannerFactory {
    private final Cache<Scanner> cache = new ConcurrentCache();
    private final Support support;

    public ScannerFactory(Support support) {
        this.support = support;
    }

    public Scanner getInstance(Class cls) throws Exception {
        Scanner objectScanner;
        Scanner fetch = this.cache.fetch(cls);
        if (fetch != null) {
            return fetch;
        }
        Detail detail = this.support.getDetail(cls);
        if (this.support.isPrimitive(cls)) {
            objectScanner = new PrimitiveScanner(detail);
        } else {
            objectScanner = new ObjectScanner(detail, this.support);
            if (objectScanner.isPrimitive() && !this.support.isContainer(cls)) {
                objectScanner = new DefaultScanner(detail, this.support);
            }
        }
        Scanner scanner = objectScanner;
        this.cache.cache(cls, scanner);
        return scanner;
    }
}
