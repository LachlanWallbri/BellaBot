package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class Loop extends Scope {
    protected AstNode body;

    /* renamed from: lp */
    protected int f10244lp;

    /* renamed from: rp */
    protected int f10245rp;

    public Loop() {
        this.f10244lp = -1;
        this.f10245rp = -1;
    }

    public Loop(int i) {
        super(i);
        this.f10244lp = -1;
        this.f10245rp = -1;
    }

    public Loop(int i, int i2) {
        super(i, i2);
        this.f10244lp = -1;
        this.f10245rp = -1;
    }

    public AstNode getBody() {
        return this.body;
    }

    public void setBody(AstNode astNode) {
        this.body = astNode;
        setLength((astNode.getPosition() + astNode.getLength()) - getPosition());
        astNode.setParent(this);
    }

    public int getLp() {
        return this.f10244lp;
    }

    public void setLp(int i) {
        this.f10244lp = i;
    }

    public int getRp() {
        return this.f10245rp;
    }

    public void setRp(int i) {
        this.f10245rp = i;
    }

    public void setParens(int i, int i2) {
        this.f10244lp = i;
        this.f10245rp = i2;
    }
}
