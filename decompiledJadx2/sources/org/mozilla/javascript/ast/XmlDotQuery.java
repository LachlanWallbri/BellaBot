package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class XmlDotQuery extends InfixExpression {

    /* renamed from: rp */
    private int f10250rp;

    public XmlDotQuery() {
        this.f10250rp = -1;
        this.type = 147;
    }

    public XmlDotQuery(int i) {
        super(i);
        this.f10250rp = -1;
        this.type = 147;
    }

    public XmlDotQuery(int i, int i2) {
        super(i, i2);
        this.f10250rp = -1;
        this.type = 147;
    }

    public int getRp() {
        return this.f10250rp;
    }

    public void setRp(int i) {
        this.f10250rp = i;
    }

    @Override // org.mozilla.javascript.ast.InfixExpression, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + getLeft().toSource(0) + ".(" + getRight().toSource(0) + ")";
    }
}
