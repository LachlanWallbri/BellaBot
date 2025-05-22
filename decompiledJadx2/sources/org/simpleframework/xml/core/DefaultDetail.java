package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/* loaded from: classes9.dex */
class DefaultDetail implements Detail {
    private final DefaultType access;
    private final Detail detail;

    public DefaultDetail(Detail detail, DefaultType defaultType) {
        this.detail = detail;
        this.access = defaultType;
    }

    @Override // org.simpleframework.xml.core.Detail
    public boolean isStrict() {
        return this.detail.isStrict();
    }

    @Override // org.simpleframework.xml.core.Detail
    public boolean isRequired() {
        return this.detail.isRequired();
    }

    @Override // org.simpleframework.xml.core.Detail
    public boolean isInstantiable() {
        return this.detail.isInstantiable();
    }

    @Override // org.simpleframework.xml.core.Detail
    public boolean isPrimitive() {
        return this.detail.isPrimitive();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Class getSuper() {
        return this.detail.getSuper();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Class getType() {
        return this.detail.getType();
    }

    @Override // org.simpleframework.xml.core.Detail
    public String getName() {
        return this.detail.getName();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Root getRoot() {
        return this.detail.getRoot();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Order getOrder() {
        return this.detail.getOrder();
    }

    @Override // org.simpleframework.xml.core.Detail
    public DefaultType getAccess() {
        return this.detail.getAccess();
    }

    @Override // org.simpleframework.xml.core.Detail
    public DefaultType getOverride() {
        return this.access;
    }

    @Override // org.simpleframework.xml.core.Detail
    public Namespace getNamespace() {
        return this.detail.getNamespace();
    }

    @Override // org.simpleframework.xml.core.Detail
    public NamespaceList getNamespaceList() {
        return this.detail.getNamespaceList();
    }

    @Override // org.simpleframework.xml.core.Detail
    public List<MethodDetail> getMethods() {
        return this.detail.getMethods();
    }

    @Override // org.simpleframework.xml.core.Detail
    public List<FieldDetail> getFields() {
        return this.detail.getFields();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Annotation[] getAnnotations() {
        return this.detail.getAnnotations();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Constructor[] getConstructors() {
        return this.detail.getConstructors();
    }

    public String toString() {
        return this.detail.toString();
    }
}
