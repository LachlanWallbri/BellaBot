package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class XmlMemberGet extends InfixExpression {
    public XmlMemberGet() {
        this.type = 144;
    }

    public XmlMemberGet(int i) {
        super(i);
        this.type = 144;
    }

    public XmlMemberGet(int i, int i2) {
        super(i, i2);
        this.type = 144;
    }

    public XmlMemberGet(int i, int i2, AstNode astNode, XmlRef xmlRef) {
        super(i, i2, astNode, xmlRef);
        this.type = 144;
    }

    public XmlMemberGet(AstNode astNode, XmlRef xmlRef) {
        super(astNode, xmlRef);
        this.type = 144;
    }

    public XmlMemberGet(AstNode astNode, XmlRef xmlRef, int i) {
        super(144, astNode, xmlRef, i);
        this.type = 144;
    }

    public AstNode getTarget() {
        return getLeft();
    }

    public void setTarget(AstNode astNode) {
        setLeft(astNode);
    }

    public XmlRef getMemberRef() {
        return (XmlRef) getRight();
    }

    public void setProperty(XmlRef xmlRef) {
        setRight(xmlRef);
    }

    @Override // org.mozilla.javascript.ast.InfixExpression, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + getLeft().toSource(0) + operatorToString(getType()) + getRight().toSource(0);
    }
}
