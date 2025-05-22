package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class XmlRef extends AstNode {
    protected int atPos;
    protected int colonPos;
    protected Name namespace;

    public XmlRef() {
        this.atPos = -1;
        this.colonPos = -1;
    }

    public XmlRef(int i) {
        super(i);
        this.atPos = -1;
        this.colonPos = -1;
    }

    public XmlRef(int i, int i2) {
        super(i, i2);
        this.atPos = -1;
        this.colonPos = -1;
    }

    public Name getNamespace() {
        return this.namespace;
    }

    public void setNamespace(Name name) {
        this.namespace = name;
        if (name != null) {
            name.setParent(this);
        }
    }

    public boolean isAttributeAccess() {
        return this.atPos >= 0;
    }

    public int getAtPos() {
        return this.atPos;
    }

    public void setAtPos(int i) {
        this.atPos = i;
    }

    public int getColonPos() {
        return this.colonPos;
    }

    public void setColonPos(int i) {
        this.colonPos = i;
    }
}
