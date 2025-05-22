package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class SwitchStatement extends Jump {
    private static final List<SwitchCase> NO_CASES = Collections.unmodifiableList(new ArrayList());
    private List<SwitchCase> cases;
    private AstNode expression;

    /* renamed from: lp */
    private int f10246lp = -1;

    /* renamed from: rp */
    private int f10247rp = -1;

    public SwitchStatement() {
        this.type = 115;
    }

    public SwitchStatement(int i) {
        this.type = 115;
        this.position = i;
    }

    public SwitchStatement(int i, int i2) {
        this.type = 115;
        this.position = i;
        this.length = i2;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    public List<SwitchCase> getCases() {
        List<SwitchCase> list = this.cases;
        return list != null ? list : NO_CASES;
    }

    public void setCases(List<SwitchCase> list) {
        if (list == null) {
            this.cases = null;
            return;
        }
        List<SwitchCase> list2 = this.cases;
        if (list2 != null) {
            list2.clear();
        }
        Iterator<SwitchCase> it = list.iterator();
        while (it.hasNext()) {
            addCase(it.next());
        }
    }

    public void addCase(SwitchCase switchCase) {
        assertNotNull(switchCase);
        if (this.cases == null) {
            this.cases = new ArrayList();
        }
        this.cases.add(switchCase);
        switchCase.setParent(this);
    }

    public int getLp() {
        return this.f10246lp;
    }

    public void setLp(int i) {
        this.f10246lp = i;
    }

    public int getRp() {
        return this.f10247rp;
    }

    public void setRp(int i) {
        this.f10247rp = i;
    }

    public void setParens(int i, int i2) {
        this.f10246lp = i;
        this.f10247rp = i2;
    }

    @Override // org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        String makeIndent = makeIndent(i);
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent);
        sb.append("switch (");
        sb.append(this.expression.toSource(0));
        sb.append(") {\n");
        List<SwitchCase> list = this.cases;
        if (list != null) {
            Iterator<SwitchCase> it = list.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toSource(i + 1));
            }
        }
        sb.append(makeIndent);
        sb.append("}\n");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
            Iterator<SwitchCase> it = getCases().iterator();
            while (it.hasNext()) {
                it.next().visit(nodeVisitor);
            }
        }
    }
}
