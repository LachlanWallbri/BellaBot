package org.mozilla.javascript;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NodeTransformer {
    private boolean hasFinally;
    private ObjArray loopEnds;
    private ObjArray loops;

    /* JADX INFO: Access modifiers changed from: protected */
    public void visitCall(Node node, ScriptNode scriptNode) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void visitNew(Node node, ScriptNode scriptNode) {
    }

    public final void transform(ScriptNode scriptNode, CompilerEnvirons compilerEnvirons) {
        transform(scriptNode, false, compilerEnvirons);
    }

    public final void transform(ScriptNode scriptNode, boolean z, CompilerEnvirons compilerEnvirons) {
        if (compilerEnvirons.getLanguageVersion() >= 200 && scriptNode.isInStrictMode()) {
            z = true;
        }
        transformCompilationUnit(scriptNode, z);
        for (int i = 0; i != scriptNode.getFunctionCount(); i++) {
            transform(scriptNode.getFunctionNode(i), z, compilerEnvirons);
        }
    }

    private void transformCompilationUnit(ScriptNode scriptNode, boolean z) {
        this.loops = new ObjArray();
        this.loopEnds = new ObjArray();
        this.hasFinally = false;
        boolean z2 = scriptNode.getType() != 110 || ((FunctionNode) scriptNode).requiresActivation();
        scriptNode.flattenSymbolTable(!z2);
        transformCompilationUnit_r(scriptNode, scriptNode, scriptNode, z2, z);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:45:0x00b2. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:46:0x00b5. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:47:0x00b8. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:48:0x00bb. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x016f  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r13v7, types: [org.mozilla.javascript.ast.Scope] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void transformCompilationUnit_r(ScriptNode scriptNode, Node node, Scope scope, boolean z, boolean z2) {
        Node node2;
        Node next;
        Node node3;
        int i;
        Node node4;
        Node firstChild;
        Scope definingScope;
        Node firstChild2;
        ?? r10 = 0;
        Node node5 = null;
        while (true) {
            if (node5 == null) {
                next = node.getFirstChild();
                node2 = r10;
            } else {
                node2 = node5;
                next = node5.getNext();
            }
            if (next == null) {
                return;
            }
            int type = next.getType();
            if (z && ((type == 130 || type == 133 || type == 158) && (next instanceof Scope))) {
                ?? r13 = (Scope) next;
                if (r13.getSymbolTable() != null) {
                    Node node6 = new Node(type == 158 ? 159 : 154);
                    Node node7 = new Node(154);
                    node6.addChildToBack(node7);
                    Iterator<String> it = r13.getSymbolTable().keySet().iterator();
                    while (it.hasNext()) {
                        node7.addChildToBack(Node.newString(39, it.next()));
                    }
                    r13.setSymbolTable(r10);
                    Node replaceCurrent = replaceCurrent(node, node2, next, node6);
                    int type2 = replaceCurrent.getType();
                    node6.addChildToBack(next);
                    next = replaceCurrent;
                    type = type2;
                }
            }
            if (type != 3) {
                boolean z3 = false;
                if (type == 4) {
                    if (scriptNode.getType() == 110 && ((FunctionNode) scriptNode).isGenerator()) {
                        z3 = true;
                    }
                    if (z3) {
                        i = 1;
                        next.putIntProp(20, 1);
                    } else {
                        i = 1;
                    }
                    if (this.hasFinally) {
                        int size = this.loops.size() - i;
                        Node node8 = null;
                        while (size >= 0) {
                            Node node9 = (Node) this.loops.get(size);
                            int type3 = node9.getType();
                            if (type3 == 82 || type3 == 124) {
                                if (type3 == 82) {
                                    Jump jump = new Jump(136);
                                    jump.target = ((Jump) node9).getFinally();
                                    node4 = jump;
                                } else {
                                    node4 = new Node(3);
                                }
                                node8 = node8 == null ? new Node(130, next.getLineno()) : node8;
                                node8.addChildToBack(node4);
                            }
                            size--;
                            node8 = node8;
                        }
                        if (node8 != null) {
                            Node firstChild3 = next.getFirstChild();
                            node3 = replaceCurrent(node, node2, next, node8);
                            if (firstChild3 == null || z3) {
                                node8.addChildToBack(next);
                            } else {
                                Node node10 = new Node(135, firstChild3);
                                node8.addChildToFront(node10);
                                node8.addChildToBack(new Node(65));
                                transformCompilationUnit_r(scriptNode, node10, scope, z, z2);
                            }
                            node5 = node3;
                            r10 = 0;
                        }
                    }
                } else {
                    if (type != 7) {
                        if (type != 8) {
                            if (type == 38) {
                                visitCall(next, scriptNode);
                            } else if (type != 39) {
                                if (type == 73) {
                                    ((FunctionNode) scriptNode).addResumptionPoint(next);
                                } else if (type != 82) {
                                    if (type != 115) {
                                        if (type != 138) {
                                            if (type != 159) {
                                                switch (type) {
                                                    case 30:
                                                        visitNew(next, scriptNode);
                                                        break;
                                                    case 31:
                                                        break;
                                                    case 32:
                                                        break;
                                                    default:
                                                        switch (type) {
                                                            case 121:
                                                            case 122:
                                                                Jump jump2 = (Jump) next;
                                                                Jump jumpStatement = jump2.getJumpStatement();
                                                                if (jumpStatement == null) {
                                                                    Kit.codeBug();
                                                                }
                                                                int size2 = this.loops.size();
                                                                while (size2 != 0) {
                                                                    size2--;
                                                                    Node node11 = (Node) this.loops.get(size2);
                                                                    if (node11 != jumpStatement) {
                                                                        int type4 = node11.getType();
                                                                        if (type4 == 124) {
                                                                            node2 = addBeforeCurrent(node, node2, next, new Node(3));
                                                                        } else if (type4 == 82) {
                                                                            Jump jump3 = new Jump(136);
                                                                            jump3.target = ((Jump) node11).getFinally();
                                                                            node2 = addBeforeCurrent(node, node2, next, jump3);
                                                                        }
                                                                    } else {
                                                                        if (type == 121) {
                                                                            jump2.target = jumpStatement.target;
                                                                        } else {
                                                                            jump2.target = jumpStatement.getContinue();
                                                                        }
                                                                        jump2.setType(5);
                                                                        break;
                                                                    }
                                                                }
                                                                throw Kit.codeBug();
                                                            case 123:
                                                                Node node12 = new Node(130);
                                                                firstChild2 = next.getFirstChild();
                                                                while (firstChild2 != null) {
                                                                    Node next2 = firstChild2.getNext();
                                                                    if (firstChild2.getType() == 39) {
                                                                        if (firstChild2.hasChildren()) {
                                                                            Node firstChild4 = firstChild2.getFirstChild();
                                                                            firstChild2.removeChild(firstChild4);
                                                                            firstChild2.setType(49);
                                                                            firstChild2 = new Node(type == 155 ? 156 : 8, firstChild2, firstChild4);
                                                                        } else {
                                                                            firstChild2 = next2;
                                                                        }
                                                                    } else if (firstChild2.getType() != 159) {
                                                                        throw Kit.codeBug();
                                                                    }
                                                                    node12.addChildToBack(new Node(134, firstChild2, next.getLineno()));
                                                                    firstChild2 = next2;
                                                                }
                                                                next = replaceCurrent(node, node2, next, node12);
                                                                break;
                                                            case 124:
                                                                this.loops.push(next);
                                                                Node next3 = next.getNext();
                                                                if (next3.getType() != 3) {
                                                                    Kit.codeBug();
                                                                }
                                                                this.loopEnds.push(next3);
                                                                break;
                                                            default:
                                                                switch (type) {
                                                                    case 131:
                                                                    case 133:
                                                                        break;
                                                                    case 132:
                                                                        break;
                                                                    default:
                                                                        switch (type) {
                                                                        }
                                                                }
                                                        }
                                                }
                                            }
                                            if (next.getFirstChild().getType() == 154) {
                                                next = visitLet(scriptNode.getType() != 110 || ((FunctionNode) scriptNode).requiresActivation(), node, node2, next);
                                            }
                                            Node node122 = new Node(130);
                                            firstChild2 = next.getFirstChild();
                                            while (firstChild2 != null) {
                                            }
                                            next = replaceCurrent(node, node2, next, node122);
                                        } else {
                                            Scope definingScope2 = scope.getDefiningScope(next.getString());
                                            if (definingScope2 != null) {
                                                next.setScope(definingScope2);
                                            }
                                        }
                                    }
                                    this.loops.push(next);
                                    this.loopEnds.push(((Jump) next).target);
                                } else {
                                    Node node13 = ((Jump) next).getFinally();
                                    if (node13 != null) {
                                        this.hasFinally = true;
                                        this.loops.push(next);
                                        this.loopEnds.push(node13);
                                    }
                                }
                            }
                        } else if (z2) {
                            next.setType(74);
                        }
                        if (!z) {
                            if (type == 39) {
                                firstChild = next;
                            } else {
                                firstChild = next.getFirstChild();
                                if (firstChild.getType() != 49) {
                                    if (type != 31) {
                                        throw Kit.codeBug();
                                    }
                                }
                            }
                            if (firstChild.getScope() == null && (definingScope = scope.getDefiningScope(firstChild.getString())) != null) {
                                firstChild.setScope(definingScope);
                                if (type == 39) {
                                    next.setType(55);
                                } else if (type == 8 || type == 74) {
                                    next.setType(56);
                                    firstChild.setType(41);
                                } else if (type == 156) {
                                    next.setType(157);
                                    firstChild.setType(41);
                                } else if (type == 31) {
                                    next = replaceCurrent(node, node2, next, new Node(44));
                                } else {
                                    throw Kit.codeBug();
                                }
                            }
                        }
                    }
                    Node firstChild5 = next.getFirstChild();
                    if (type == 7) {
                        while (firstChild5.getType() == 26) {
                            firstChild5 = firstChild5.getFirstChild();
                        }
                        if (firstChild5.getType() == 12 || firstChild5.getType() == 13) {
                            Node firstChild6 = firstChild5.getFirstChild();
                            Node lastChild = firstChild5.getLastChild();
                            if (firstChild6.getType() == 39 && firstChild6.getString().equals("undefined")) {
                                firstChild5 = lastChild;
                            } else if (lastChild.getType() == 39 && lastChild.getString().equals("undefined")) {
                                firstChild5 = firstChild6;
                            }
                        }
                    }
                    if (firstChild5.getType() == 33) {
                        firstChild5.setType(34);
                    }
                }
                node3 = next;
                transformCompilationUnit_r(scriptNode, node3, !(node3 instanceof Scope) ? (Scope) node3 : scope, z, z2);
                node5 = node3;
                r10 = 0;
            }
            if (!this.loopEnds.isEmpty() && this.loopEnds.peek() == next) {
                this.loopEnds.pop();
                this.loops.pop();
            }
            node3 = next;
            transformCompilationUnit_r(scriptNode, node3, !(node3 instanceof Scope) ? (Scope) node3 : scope, z, z2);
            node5 = node3;
            r10 = 0;
        }
    }

    protected Node visitLet(boolean z, Node node, Node node2, Node node3) {
        Node replaceCurrent;
        Node node4;
        Node node5;
        Node node6;
        Node node7;
        Node firstChild = node3.getFirstChild();
        Node next = firstChild.getNext();
        node3.removeChild(firstChild);
        node3.removeChild(next);
        int i = 159;
        boolean z2 = node3.getType() == 159;
        int i2 = 154;
        int i3 = 90;
        if (z) {
            replaceCurrent = replaceCurrent(node, node2, node3, new Node(z2 ? 160 : 130));
            ArrayList arrayList = new ArrayList();
            Node node8 = new Node(67);
            Node firstChild2 = firstChild.getFirstChild();
            while (firstChild2 != null) {
                if (firstChild2.getType() == i) {
                    List list = (List) firstChild2.getProp(22);
                    Node firstChild3 = firstChild2.getFirstChild();
                    if (firstChild3.getType() != i2) {
                        throw Kit.codeBug();
                    }
                    if (z2) {
                        node6 = new Node(i3, firstChild3.getNext(), next);
                    } else {
                        node6 = new Node(130, new Node(134, firstChild3.getNext()), next);
                    }
                    if (list != null) {
                        arrayList.addAll(list);
                        for (int i4 = 0; i4 < list.size(); i4++) {
                            node8.addChildToBack(new Node(127, Node.newNumber(0.0d)));
                        }
                    }
                    node7 = firstChild3.getFirstChild();
                } else {
                    node6 = next;
                    node7 = firstChild2;
                }
                if (node7.getType() != 39) {
                    throw Kit.codeBug();
                }
                arrayList.add(ScriptRuntime.getIndexObject(node7.getString()));
                Node firstChild4 = node7.getFirstChild();
                if (firstChild4 == null) {
                    firstChild4 = new Node(127, Node.newNumber(0.0d));
                }
                node8.addChildToBack(firstChild4);
                firstChild2 = firstChild2.getNext();
                next = node6;
                i = 159;
                i2 = 154;
                i3 = 90;
            }
            node8.putProp(12, arrayList.toArray());
            replaceCurrent.addChildToBack(new Node(2, node8));
            replaceCurrent.addChildToBack(new Node(124, next));
            replaceCurrent.addChildToBack(new Node(3));
        } else {
            replaceCurrent = replaceCurrent(node, node2, node3, new Node(z2 ? 90 : 130));
            Node node9 = new Node(90);
            Node firstChild5 = firstChild.getFirstChild();
            while (firstChild5 != null) {
                if (firstChild5.getType() == 159) {
                    Node firstChild6 = firstChild5.getFirstChild();
                    if (firstChild6.getType() != 154) {
                        throw Kit.codeBug();
                    }
                    if (z2) {
                        node4 = new Node(90, firstChild6.getNext(), next);
                    } else {
                        node4 = new Node(130, new Node(134, firstChild6.getNext()), next);
                    }
                    Scope.joinScopes((Scope) firstChild5, (Scope) node3);
                    node5 = firstChild6.getFirstChild();
                } else {
                    node4 = next;
                    node5 = firstChild5;
                }
                if (node5.getType() != 39) {
                    throw Kit.codeBug();
                }
                Node newString = Node.newString(node5.getString());
                newString.setScope((Scope) node3);
                Node firstChild7 = node5.getFirstChild();
                if (firstChild7 == null) {
                    firstChild7 = new Node(127, Node.newNumber(0.0d));
                }
                node9.addChildToBack(new Node(56, newString, firstChild7));
                firstChild5 = firstChild5.getNext();
                next = node4;
            }
            if (z2) {
                replaceCurrent.addChildToBack(node9);
                node3.setType(90);
                replaceCurrent.addChildToBack(node3);
                node3.addChildToBack(next);
                if (next instanceof Scope) {
                    Scope scope = (Scope) next;
                    Scope parentScope = scope.getParentScope();
                    Scope scope2 = (Scope) node3;
                    scope.setParentScope(scope2);
                    scope2.setParentScope(parentScope);
                }
            } else {
                replaceCurrent.addChildToBack(new Node(134, node9));
                node3.setType(130);
                replaceCurrent.addChildToBack(node3);
                node3.addChildrenToBack(next);
                if (next instanceof Scope) {
                    Scope scope3 = (Scope) next;
                    Scope parentScope2 = scope3.getParentScope();
                    Scope scope4 = (Scope) node3;
                    scope3.setParentScope(scope4);
                    scope4.setParentScope(parentScope2);
                }
            }
        }
        return replaceCurrent;
    }

    private static Node addBeforeCurrent(Node node, Node node2, Node node3, Node node4) {
        if (node2 == null) {
            if (node3 != node.getFirstChild()) {
                Kit.codeBug();
            }
            node.addChildToFront(node4);
        } else {
            if (node3 != node2.getNext()) {
                Kit.codeBug();
            }
            node.addChildAfter(node4, node2);
        }
        return node4;
    }

    private static Node replaceCurrent(Node node, Node node2, Node node3, Node node4) {
        if (node2 == null) {
            if (node3 != node.getFirstChild()) {
                Kit.codeBug();
            }
            node.replaceChild(node3, node4);
        } else if (node2.next == node3) {
            node.replaceChildAfter(node2, node4);
        } else {
            node.replaceChild(node3, node4);
        }
        return node4;
    }
}
