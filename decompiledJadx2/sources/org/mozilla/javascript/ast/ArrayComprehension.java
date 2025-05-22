package org.mozilla.javascript.ast;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ArrayComprehension extends Scope {
    private AstNode filter;
    private int ifPosition;
    private List<ArrayComprehensionLoop> loops;

    /* renamed from: lp */
    private int f10228lp;
    private AstNode result;

    /* renamed from: rp */
    private int f10229rp;

    public ArrayComprehension() {
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.f10228lp = -1;
        this.f10229rp = -1;
        this.type = 158;
    }

    public ArrayComprehension(int i) {
        super(i);
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.f10228lp = -1;
        this.f10229rp = -1;
        this.type = 158;
    }

    public ArrayComprehension(int i, int i2) {
        super(i, i2);
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.f10228lp = -1;
        this.f10229rp = -1;
        this.type = 158;
    }

    public AstNode getResult() {
        return this.result;
    }

    public void setResult(AstNode astNode) {
        assertNotNull(astNode);
        this.result = astNode;
        astNode.setParent(this);
    }

    public List<ArrayComprehensionLoop> getLoops() {
        return this.loops;
    }

    public void setLoops(List<ArrayComprehensionLoop> list) {
        assertNotNull(list);
        this.loops.clear();
        Iterator<ArrayComprehensionLoop> it = list.iterator();
        while (it.hasNext()) {
            addLoop(it.next());
        }
    }

    public void addLoop(ArrayComprehensionLoop arrayComprehensionLoop) {
        assertNotNull(arrayComprehensionLoop);
        this.loops.add(arrayComprehensionLoop);
        arrayComprehensionLoop.setParent(this);
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
        return this.f10228lp;
    }

    public void setFilterLp(int i) {
        this.f10228lp = i;
    }

    public int getFilterRp() {
        return this.f10229rp;
    }

    public void setFilterRp(int i) {
        this.f10229rp = i;
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        sb.append("[");
        sb.append(this.result.toSource(0));
        Iterator<ArrayComprehensionLoop> it = this.loops.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toSource(0));
        }
        if (this.filter != null) {
            sb.append(" if (");
            sb.append(this.filter.toSource(0));
            sb.append(")");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.result.visit(nodeVisitor);
            Iterator<ArrayComprehensionLoop> it = this.loops.iterator();
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
