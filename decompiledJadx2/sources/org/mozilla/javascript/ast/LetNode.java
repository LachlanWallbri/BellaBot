package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class LetNode extends Scope {
    private AstNode body;

    /* renamed from: lp */
    private int f10242lp;

    /* renamed from: rp */
    private int f10243rp;
    private VariableDeclaration variables;

    public LetNode() {
        this.f10242lp = -1;
        this.f10243rp = -1;
        this.type = 159;
    }

    public LetNode(int i) {
        super(i);
        this.f10242lp = -1;
        this.f10243rp = -1;
        this.type = 159;
    }

    public LetNode(int i, int i2) {
        super(i, i2);
        this.f10242lp = -1;
        this.f10243rp = -1;
        this.type = 159;
    }

    public VariableDeclaration getVariables() {
        return this.variables;
    }

    public void setVariables(VariableDeclaration variableDeclaration) {
        assertNotNull(variableDeclaration);
        this.variables = variableDeclaration;
        variableDeclaration.setParent(this);
    }

    public AstNode getBody() {
        return this.body;
    }

    public void setBody(AstNode astNode) {
        this.body = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public int getLp() {
        return this.f10242lp;
    }

    public void setLp(int i) {
        this.f10242lp = i;
    }

    public int getRp() {
        return this.f10243rp;
    }

    public void setRp(int i) {
        this.f10243rp = i;
    }

    public void setParens(int i, int i2) {
        this.f10242lp = i;
        this.f10243rp = i2;
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        String makeIndent = makeIndent(i);
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent);
        sb.append("let (");
        printList(this.variables.getVariables(), sb);
        sb.append(") ");
        AstNode astNode = this.body;
        if (astNode != null) {
            sb.append(astNode.toSource(i));
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.variables.visit(nodeVisitor);
            AstNode astNode = this.body;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
        }
    }
}
