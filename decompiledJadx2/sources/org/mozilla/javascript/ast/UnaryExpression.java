package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class UnaryExpression extends AstNode {
    private boolean isPostfix;
    private AstNode operand;

    public UnaryExpression() {
    }

    public UnaryExpression(int i) {
        super(i);
    }

    public UnaryExpression(int i, int i2) {
        super(i, i2);
    }

    public UnaryExpression(int i, int i2, AstNode astNode) {
        this(i, i2, astNode, false);
    }

    public UnaryExpression(int i, int i2, AstNode astNode, boolean z) {
        assertNotNull(astNode);
        setBounds(z ? astNode.getPosition() : i2, z ? i2 + 2 : astNode.getPosition() + astNode.getLength());
        setOperator(i);
        setOperand(astNode);
        this.isPostfix = z;
    }

    public int getOperator() {
        return this.type;
    }

    public void setOperator(int i) {
        if (!Token.isValidToken(i)) {
            throw new IllegalArgumentException("Invalid token: " + i);
        }
        setType(i);
    }

    public AstNode getOperand() {
        return this.operand;
    }

    public void setOperand(AstNode astNode) {
        assertNotNull(astNode);
        this.operand = astNode;
        astNode.setParent(this);
    }

    public boolean isPostfix() {
        return this.isPostfix;
    }

    public boolean isPrefix() {
        return !this.isPostfix;
    }

    public void setIsPostfix(boolean z) {
        this.isPostfix = z;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        int type = getType();
        if (!this.isPostfix) {
            sb.append(operatorToString(type));
            if (type == 32 || type == 31 || type == 127) {
                sb.append(" ");
            }
        }
        sb.append(this.operand.toSource());
        if (this.isPostfix) {
            sb.append(operatorToString(type));
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.operand.visit(nodeVisitor);
        }
    }
}
