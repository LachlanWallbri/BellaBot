package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class WithStatement extends AstNode {
    private AstNode expression;

    /* renamed from: lp */
    private int f10248lp;

    /* renamed from: rp */
    private int f10249rp;
    private AstNode statement;

    public WithStatement() {
        this.f10248lp = -1;
        this.f10249rp = -1;
        this.type = 124;
    }

    public WithStatement(int i) {
        super(i);
        this.f10248lp = -1;
        this.f10249rp = -1;
        this.type = 124;
    }

    public WithStatement(int i, int i2) {
        super(i, i2);
        this.f10248lp = -1;
        this.f10249rp = -1;
        this.type = 124;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    public AstNode getStatement() {
        return this.statement;
    }

    public void setStatement(AstNode astNode) {
        assertNotNull(astNode);
        this.statement = astNode;
        astNode.setParent(this);
    }

    public int getLp() {
        return this.f10248lp;
    }

    public void setLp(int i) {
        this.f10248lp = i;
    }

    public int getRp() {
        return this.f10249rp;
    }

    public void setRp(int i) {
        this.f10249rp = i;
    }

    public void setParens(int i, int i2) {
        this.f10248lp = i;
        this.f10249rp = i2;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("with (");
        sb.append(this.expression.toSource(0));
        sb.append(") ");
        if (this.statement.getType() == 130) {
            sb.append(this.statement.toSource(i).trim());
            sb.append("\n");
        } else {
            sb.append("\n");
            sb.append(this.statement.toSource(i + 1));
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
            this.statement.visit(nodeVisitor);
        }
    }
}
