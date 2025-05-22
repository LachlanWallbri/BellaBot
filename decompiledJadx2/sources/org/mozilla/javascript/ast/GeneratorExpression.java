package org.mozilla.javascript.ast;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class GeneratorExpression extends Scope {
    private AstNode filter;
    private int ifPosition;
    private List<GeneratorExpressionLoop> loops;

    /* renamed from: lp */
    private int f10238lp;
    private AstNode result;

    /* renamed from: rp */
    private int f10239rp;

    public GeneratorExpression() {
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.f10238lp = -1;
        this.f10239rp = -1;
        this.type = 163;
    }

    public GeneratorExpression(int i) {
        super(i);
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.f10238lp = -1;
        this.f10239rp = -1;
        this.type = 163;
    }

    public GeneratorExpression(int i, int i2) {
        super(i, i2);
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.f10238lp = -1;
        this.f10239rp = -1;
        this.type = 163;
    }

    public AstNode getResult() {
        return this.result;
    }

    public void setResult(AstNode astNode) {
        assertNotNull(astNode);
        this.result = astNode;
        astNode.setParent(this);
    }

    public List<GeneratorExpressionLoop> getLoops() {
        return this.loops;
    }

    public void setLoops(List<GeneratorExpressionLoop> list) {
        assertNotNull(list);
        this.loops.clear();
        Iterator<GeneratorExpressionLoop> it = list.iterator();
        while (it.hasNext()) {
            addLoop(it.next());
        }
    }

    public void addLoop(GeneratorExpressionLoop generatorExpressionLoop) {
        assertNotNull(generatorExpressionLoop);
        this.loops.add(generatorExpressionLoop);
        generatorExpressionLoop.setParent(this);
    }

    public AstNode getFilter() {
        return this.filter;
    }

    public void setFilter(AstNode astNode) {
        this.filter = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public int getIfPosition() {
        return this.ifPosition;
    }

    public void setIfPosition(int i) {
        this.ifPosition = i;
    }

    public int getFilterLp() {
        return this.f10238lp;
    }

    public void setFilterLp(int i) {
        this.f10238lp = i;
    }

    public int getFilterRp() {
        return this.f10239rp;
    }

    public void setFilterRp(int i) {
        this.f10239rp = i;
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        sb.append("(");
        sb.append(this.result.toSource(0));
        Iterator<GeneratorExpressionLoop> it = this.loops.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toSource(0));
        }
        if (this.filter != null) {
            sb.append(" if (");
            sb.append(this.filter.toSource(0));
            sb.append(")");
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.result.visit(nodeVisitor);
            Iterator<GeneratorExpressionLoop> it = this.loops.iterator();
            while (it.hasNext()) {
                it.next().visit(nodeVisitor);
            }
            AstNode astNode = this.filter;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
        }
    }
}
