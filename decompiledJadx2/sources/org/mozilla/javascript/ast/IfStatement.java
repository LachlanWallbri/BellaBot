package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class IfStatement extends AstNode {
    private AstNode condition;
    private AstNode elsePart;
    private int elsePosition;

    /* renamed from: lp */
    private int f10240lp;

    /* renamed from: rp */
    private int f10241rp;
    private AstNode thenPart;

    public IfStatement() {
        this.elsePosition = -1;
        this.f10240lp = -1;
        this.f10241rp = -1;
        this.type = 113;
    }

    public IfStatement(int i) {
        super(i);
        this.elsePosition = -1;
        this.f10240lp = -1;
        this.f10241rp = -1;
        this.type = 113;
    }

    public IfStatement(int i, int i2) {
        super(i, i2);
        this.elsePosition = -1;
        this.f10240lp = -1;
        this.f10241rp = -1;
        this.type = 113;
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode astNode) {
        assertNotNull(astNode);
        this.condition = astNode;
        astNode.setParent(this);
    }

    public AstNode getThenPart() {
        return this.thenPart;
    }

    public void setThenPart(AstNode astNode) {
        assertNotNull(astNode);
        this.thenPart = astNode;
        astNode.setParent(this);
    }

    public AstNode getElsePart() {
        return this.elsePart;
    }

    public void setElsePart(AstNode astNode) {
        this.elsePart = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public int getElsePosition() {
        return this.elsePosition;
    }

    public void setElsePosition(int i) {
        this.elsePosition = i;
    }

    public int getLp() {
        return this.f10240lp;
    }

    public void setLp(int i) {
        this.f10240lp = i;
    }

    public int getRp() {
        return this.f10241rp;
    }

    public void setRp(int i) {
        this.f10241rp = i;
    }

    public void setParens(int i, int i2) {
        this.f10240lp = i;
        this.f10241rp = i2;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        String makeIndent = makeIndent(i);
        StringBuilder sb = new StringBuilder(32);
        sb.append(makeIndent);
        sb.append("if (");
        sb.append(this.condition.toSource(0));
        sb.append(") ");
        if (this.thenPart.getType() != 130) {
            sb.append("\n");
            sb.append(makeIndent(i + 1));
        }
        sb.append(this.thenPart.toSource(i).trim());
        if (this.elsePart != null) {
            if (this.thenPart.getType() != 130) {
                sb.append("\n");
                sb.append(makeIndent);
                sb.append("else ");
            } else {
                sb.append(" else ");
            }
            if (this.elsePart.getType() != 130 && this.elsePart.getType() != 113) {
                sb.append("\n");
                sb.append(makeIndent(i + 1));
            }
            sb.append(this.elsePart.toSource(i).trim());
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.condition.visit(nodeVisitor);
            this.thenPart.visit(nodeVisitor);
            AstNode astNode = this.elsePart;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
        }
    }
}
