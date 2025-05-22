package org.simpleframework.xml.core;

/* loaded from: classes9.dex */
abstract class TemplateParameter implements Parameter {
    @Override // org.simpleframework.xml.core.Parameter
    public boolean isAttribute() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isText() {
        return false;
    }
}
