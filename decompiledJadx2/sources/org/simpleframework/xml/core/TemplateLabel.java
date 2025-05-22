package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

/* loaded from: classes9.dex */
abstract class TemplateLabel implements Label {
    private final KeyBuilder builder = new KeyBuilder(this);

    @Override // org.simpleframework.xml.core.Label
    public Type getDependent() throws Exception {
        return null;
    }

    @Override // org.simpleframework.xml.core.Label
    public String getEntry() throws Exception {
        return null;
    }

    @Override // org.simpleframework.xml.core.Label
    public Label getLabel(Class cls) throws Exception {
        return this;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isAttribute() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isCollection() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isInline() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isText() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isTextList() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isUnion() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public Type getType(Class cls) throws Exception {
        return getContact();
    }

    @Override // org.simpleframework.xml.core.Label
    public String[] getNames() throws Exception {
        return new String[]{getPath(), getName()};
    }

    @Override // org.simpleframework.xml.core.Label
    public String[] getPaths() throws Exception {
        return new String[]{getPath()};
    }

    @Override // org.simpleframework.xml.core.Label
    public Object getKey() throws Exception {
        return this.builder.getKey();
    }
}
