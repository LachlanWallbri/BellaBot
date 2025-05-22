package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class SwitchCase extends AstNode {
    private AstNode expression;
    private List<AstNode> statements;

    public SwitchCase() {
        this.type = 116;
    }

    public SwitchCase(int i) {
        super(i);
        this.type = 116;
    }

    public SwitchCase(int i, int i2) {
        super(i, i2);
        this.type = 116;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        this.expression = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public boolean isDefault() {
        return this.expression == null;
    }

    public List<AstNode> getStatements() {
        return this.statements;
    }

    public void setStatements(List<AstNode> list) {
        List<AstNode> list2 = this.statements;
        if (list2 != null) {
            list2.clear();
        }
        Iterator<AstNode> it = list.iterator();
        while (it.hasNext()) {
            addStatement(it.next());
        }
    }

    public void addStatement(AstNode astNode) {
        assertNotNull(astNode);
        if (this.statements == null) {
            this.statements = new ArrayList();
        }
        setLength((astNode.getPosition() + astNode.getLength()) - getPosition());
        this.statements.add(astNode);
        astNode.setParent(this);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        if (this.expression == null) {
            sb.append("default:\n");
        } else {
            sb.append("case ");
            sb.append(this.expression.toSource(0));
            sb.append(":\n");
        }
        List<AstNode> list = this.statements;
        if (list != null) {
            Iterator<AstNode> it = list.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toSource(i + 1));
            }
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            AstNode astNode = this.expression;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
            List<AstNode> list = this.statements;
            if (list != null) {
                Iterator<AstNode> it = list.iterator();
                while (it.hasNext()) {
                    it.next().visit(nodeVisitor);
                }
            }
        }
    }
}
