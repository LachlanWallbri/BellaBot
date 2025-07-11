package org.mozilla.javascript.ast;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class CatchClause extends AstNode {
    private Block body;
    private AstNode catchCondition;
    private int ifPosition;

    /* renamed from: lp */
    private int f10230lp;

    /* renamed from: rp */
    private int f10231rp;
    private Name varName;

    public CatchClause() {
        this.ifPosition = -1;
        this.f10230lp = -1;
        this.f10231rp = -1;
        this.type = 125;
    }

    public CatchClause(int i) {
        super(i);
        this.ifPosition = -1;
        this.f10230lp = -1;
        this.f10231rp = -1;
        this.type = 125;
    }

    public CatchClause(int i, int i2) {
        super(i, i2);
        this.ifPosition = -1;
        this.f10230lp = -1;
        this.f10231rp = -1;
        this.type = 125;
    }

    public Name getVarName() {
        return this.varName;
    }

    public void setVarName(Name name) {
        assertNotNull(name);
        this.varName = name;
        name.setParent(this);
    }

    public AstNode getCatchCondition() {
        return this.catchCondition;
    }

    public void setCatchCondition(AstNode astNode) {
        this.catchCondition = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public Block getBody() {
        return this.body;
    }

    public void setBody(Block block) {
        assertNotNull(block);
        this.body = block;
        block.setParent(this);
    }

    public int getLp() {
        return this.f10230lp;
    }

    public void setLp(int i) {
        this.f10230lp = i;
    }

    public int getRp() {
        return this.f10231rp;
    }

    public void setRp(int i) {
        this.f10231rp = i;
    }

    public void setParens(int i, int i2) {
        this.f10230lp = i;
        this.f10231rp = i2;
    }

    public int getIfPosition() {
        return this.ifPosition;
    }

    public void setIfPosition(int i) {
        this.ifPosition = i;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("catch (");
        sb.append(this.varName.toSource(0));
        if (this.catchCondition != null) {
            sb.append(" if ");
            sb.append(this.catchCondition.toSource(0));
        }
        sb.append(") ");
        sb.append(this.body.toSource(0));
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.varName.visit(nodeVisitor);
            AstNode astNode = this.catchCondition;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
            this.body.visit(nodeVisitor);
        }
    }
}
