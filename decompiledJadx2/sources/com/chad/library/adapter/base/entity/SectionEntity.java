package com.chad.library.adapter.base.entity;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class SectionEntity<T> implements Serializable {
    public String header;
    public boolean isHeader;

    /* renamed from: t */
    public T f1246t;

    public SectionEntity(boolean z, String str) {
        this.isHeader = z;
        this.header = str;
        this.f1246t = null;
    }

    public SectionEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.f1246t = t;
    }
}
