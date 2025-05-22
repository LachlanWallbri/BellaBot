package org.simpleframework.xml.transform;

import java.util.Date;

/* loaded from: classes9.dex */
class DateTransform<T extends Date> implements Transform<T> {
    private final DateFactory<T> factory;

    public DateTransform(Class<T> cls) throws Exception {
        this.factory = new DateFactory<>(cls);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public synchronized T read(String str) throws Exception {
        return this.factory.getInstance(Long.valueOf(DateType.getDate(str).getTime()));
    }

    @Override // org.simpleframework.xml.transform.Transform
    public synchronized String write(T t) throws Exception {
        return DateType.getText(t);
    }
}
