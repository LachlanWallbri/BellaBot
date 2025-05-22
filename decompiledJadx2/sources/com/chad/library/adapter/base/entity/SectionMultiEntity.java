package com.chad.library.adapter.base.entity;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class SectionMultiEntity<T> implements Serializable, MultiItemEntity {
    public String header;
    public boolean isHeader;

    /* renamed from: t */
    public T f1247t;

    public SectionMultiEntity(boolean z, String str) {
        this.isHeader = z;
        this.header = str;
        this.f1247t = null;
    }

    public SectionMultiEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.f1247t = t;
    }
}
