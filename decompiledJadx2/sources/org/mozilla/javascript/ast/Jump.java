package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class Jump extends AstNode {
    private Jump jumpNode;
    public Node target;
    private Node target2;

    public Jump() {
        this.type = -1;
    }

    public Jump(int i) {
        this.type = i;
    }

    public Jump(int i, int i2) {
        this(i);
        setLineno(i2);
    }

    public Jump(int i, Node node) {
        this(i);
        addChildToBack(node);
    }

    public Jump(int i, Node node, int i2) {
        this(i, node);
        setLineno(i2);
    }

    public Jump getJumpStatement() {
        if (this.type != 121 && this.type != 122) {
            codeBug();
        }
        return this.jumpNode;
    }

    public void setJumpStatement(Jump jump) {
        if (this.type != 121 && this.type != 122) {
            codeBug();
        }
        if (jump == null) {
            codeBug();
        }
        if (this.jumpNode != null) {
            codeBug();
        }
        this.jumpNode = jump;
    }

    public Node getDefault() {
        if (this.type != 115) {
            codeBug();
        }
        return this.target2;
    }

    public void setDefault(Node node) {
        if (this.type != 115) {
            codeBug();
        }
        if (node.getType() != 132) {
            codeBug();
        }
        if (this.target2 != null) {
            codeBug();
        }
        this.target2 = node;
    }

    public Node getFinally() {
        if (this.type != 82) {
            codeBug();
        }
        return this.target2;
    }

    public void setFinally(Node node) {
        if (this.type != 82) {
            codeBug();
        }
        if (node.getType() != 132) {
            codeBug();
        }
        if (this.target2 != null) {
            codeBug();
        }
        this.target2 = node;
    }

    public Jump getLoop() {
        if (this.type != 131) {
            codeBug();
        }
        return this.jumpNode;
    }

    public void setLoop(Jump jump) {
        if (this.type != 131) {
            codeBug();
        }
        if (jump == null) {
            codeBug();
        }
        if (this.jumpNode != null) {
            codeBug();
        }
        this.jumpNode = jump;
    }

    public Node getContinue() {
        if (this.type != 133) {
            codeBug();
        }
        return this.target2;
    }

    public void setContinue(Node node) {
        if (this.type != 133) {
            codeBug();
        }
        if (node.getType() != 132) {
            codeBug();
        }
        if (this.target2 != null) {
            codeBug();
        }
        this.target2 = node;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        throw new UnsupportedOperationException(toString());
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        throw new UnsupportedOperationException(toString());
    }
}
