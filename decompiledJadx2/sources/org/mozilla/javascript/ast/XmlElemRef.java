package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class XmlElemRef extends XmlRef {
    private AstNode indexExpr;

    /* renamed from: lb */
    private int f10251lb;

    /* renamed from: rb */
    private int f10252rb;

    public XmlElemRef() {
        this.f10251lb = -1;
        this.f10252rb = -1;
        this.type = 78;
    }

    public XmlElemRef(int i) {
        super(i);
        this.f10251lb = -1;
        this.f10252rb = -1;
        this.type = 78;
    }

    public XmlElemRef(int i, int i2) {
        super(i, i2);
        this.f10251lb = -1;
        this.f10252rb = -1;
        this.type = 78;
    }

    public AstNode getExpression() {
        return this.indexExpr;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.indexExpr = astNode;
        astNode.setParent(this);
    }

    public int getLb() {
        return this.f10251lb;
    }

    public void setLb(int i) {
        this.f10251lb = i;
    }

    public int getRb() {
        return this.f10252rb;
    }

    public void setRb(int i) {
        this.f10252rb = i;
    }

    public void setBrackets(int i, int i2) {
        this.f10251lb = i;
        this.f10252rb = i2;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        if (isAttributeAccess()) {
            sb.append("@");
        }
        if (this.namespace != null) {
            sb.append(this.namespace.toSource(0));
            sb.append("::");
        }
        sb.append("[");
        sb.append(this.indexExpr.toSource(0));
        sb.append("]");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            if (this.namespace != null) {
                this.namespace.visit(nodeVisitor);
            }
            this.indexExpr.visit(nodeVisitor);
        }
    }
}
