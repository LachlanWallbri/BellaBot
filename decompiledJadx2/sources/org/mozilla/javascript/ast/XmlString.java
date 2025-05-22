package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class XmlString extends XmlFragment {
    private String xml;

    public XmlString() {
    }

    public XmlString(int i) {
        super(i);
    }

    public XmlString(int i, String str) {
        super(i);
        setXml(str);
    }

    public void setXml(String str) {
        assertNotNull(str);
        this.xml = str;
        setLength(str.length());
    }

    public String getXml() {
        return this.xml;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + this.xml;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
