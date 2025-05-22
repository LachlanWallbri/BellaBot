package org.mozilla.javascript.ast;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Node;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class FunctionNode extends ScriptNode {
    public static final int ARROW_FUNCTION = 4;
    public static final int FUNCTION_EXPRESSION = 2;
    public static final int FUNCTION_EXPRESSION_STATEMENT = 3;
    public static final int FUNCTION_STATEMENT = 1;
    private static final List<AstNode> NO_PARAMS = Collections.unmodifiableList(new ArrayList());
    private AstNode body;
    private Form functionForm;
    private Name functionName;
    private int functionType;
    private List<Node> generatorResumePoints;
    private boolean isExpressionClosure;
    private boolean isGenerator;
    private Map<Node, int[]> liveLocals;

    /* renamed from: lp */
    private int f10236lp;
    private AstNode memberExprNode;
    private boolean needsActivation;
    private List<AstNode> params;

    /* renamed from: rp */
    private int f10237rp;

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public enum Form {
        FUNCTION,
        GETTER,
        SETTER,
        METHOD
    }

    public FunctionNode() {
        this.functionForm = Form.FUNCTION;
        this.f10236lp = -1;
        this.f10237rp = -1;
        this.type = 110;
    }

    public FunctionNode(int i) {
        super(i);
        this.functionForm = Form.FUNCTION;
        this.f10236lp = -1;
        this.f10237rp = -1;
        this.type = 110;
    }

    public FunctionNode(int i, Name name) {
        super(i);
        this.functionForm = Form.FUNCTION;
        this.f10236lp = -1;
        this.f10237rp = -1;
        this.type = 110;
        setFunctionName(name);
    }

    public Name getFunctionName() {
        return this.functionName;
    }

    public void setFunctionName(Name name) {
        this.functionName = name;
        if (name != null) {
            name.setParent(this);
        }
    }

    public String getName() {
        Name name = this.functionName;
        return name != null ? name.getIdentifier() : "";
    }

    public List<AstNode> getParams() {
        List<AstNode> list = this.params;
        return list != null ? list : NO_PARAMS;
    }

    public void setParams(List<AstNode> list) {
        if (list == null) {
            this.params = null;
            return;
        }
        List<AstNode> list2 = this.params;
        if (list2 != null) {
            list2.clear();
        }
        Iterator<AstNode> it = list.iterator();
        while (it.hasNext()) {
            addParam(it.next());
        }
    }

    public void addParam(AstNode astNode) {
        assertNotNull(astNode);
        if (this.params == null) {
            this.params = new ArrayList();
        }
        this.params.add(astNode);
        astNode.setParent(this);
    }

    public boolean isParam(AstNode astNode) {
        List<AstNode> list = this.params;
        if (list == null) {
            return false;
        }
        return list.contains(astNode);
    }

    public AstNode getBody() {
        return this.body;
    }

    public void setBody(AstNode astNode) {
        assertNotNull(astNode);
        this.body = astNode;
        if (Boolean.TRUE.equals(astNode.getProp(25))) {
            setIsExpressionClosure(true);
        }
        int position = astNode.getPosition() + astNode.getLength();
        astNode.setParent(this);
        setLength(position - this.position);
        setEncodedSourceBounds(this.position, position);
    }

    public int getLp() {
        return this.f10236lp;
    }

    public void setLp(int i) {
        this.f10236lp = i;
    }

    public int getRp() {
        return this.f10237rp;
    }

    public void setRp(int i) {
        this.f10237rp = i;
    }

    public void setParens(int i, int i2) {
        this.f10236lp = i;
        this.f10237rp = i2;
    }

    public boolean isExpressionClosure() {
        return this.isExpressionClosure;
    }

    public void setIsExpressionClosure(boolean z) {
        this.isExpressionClosure = z;
    }

    public boolean requiresActivation() {
        return this.needsActivation;
    }

    public void setRequiresActivation() {
        this.needsActivation = true;
    }

    public boolean isGenerator() {
        return this.isGenerator;
    }

    public void setIsGenerator() {
        this.isGenerator = true;
    }

    public void addResumptionPoint(Node node) {
        if (this.generatorResumePoints == null) {
            this.generatorResumePoints = new ArrayList();
        }
        this.generatorResumePoints.add(node);
    }

    public List<Node> getResumptionPoints() {
        return this.generatorResumePoints;
    }

    public Map<Node, int[]> getLiveLocals() {
        return this.liveLocals;
    }

    public void addLiveLocals(Node node, int[] iArr) {
        if (this.liveLocals == null) {
            this.liveLocals = new HashMap();
        }
        this.liveLocals.put(node, iArr);
    }

    @Override // org.mozilla.javascript.ast.ScriptNode
    public int addFunction(FunctionNode functionNode) {
        int addFunction = super.addFunction(functionNode);
        if (getFunctionCount() > 0) {
            this.needsActivation = true;
        }
        return addFunction;
    }

    public int getFunctionType() {
        return this.functionType;
    }

    public void setFunctionType(int i) {
        this.functionType = i;
    }

    public boolean isMethod() {
        return this.functionForm == Form.GETTER || this.functionForm == Form.SETTER || this.functionForm == Form.METHOD;
    }

    public boolean isGetterMethod() {
        return this.functionForm == Form.GETTER;
    }

    public boolean isSetterMethod() {
        return this.functionForm == Form.SETTER;
    }

    public boolean isNormalMethod() {
        return this.functionForm == Form.METHOD;
    }

    public void setFunctionIsGetterMethod() {
        this.functionForm = Form.GETTER;
    }

    public void setFunctionIsSetterMethod() {
        this.functionForm = Form.SETTER;
    }

    public void setFunctionIsNormalMethod() {
        this.functionForm = Form.METHOD;
    }

    public void setMemberExprNode(AstNode astNode) {
        this.memberExprNode = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public AstNode getMemberExprNode() {
        return this.memberExprNode;
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        boolean z = this.functionType == 4;
        if (!isMethod()) {
            sb.append(makeIndent(i));
            if (!z) {
                sb.append("function");
            }
        }
        if (this.functionName != null) {
            sb.append(" ");
            sb.append(this.functionName.toSource(0));
        }
        List<AstNode> list = this.params;
        if (list == null) {
            sb.append("() ");
        } else if (z && this.f10236lp == -1) {
            printList(list, sb);
            sb.append(" ");
        } else {
            sb.append("(");
            printList(this.params, sb);
            sb.append(") ");
        }
        if (z) {
            sb.append("=> ");
        }
        if (this.isExpressionClosure) {
            AstNode body = getBody();
            if (body.getLastChild() instanceof ReturnStatement) {
                sb.append(((ReturnStatement) body.getLastChild()).getReturnValue().toSource(0));
                if (this.functionType == 1) {
                    sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
            } else {
                sb.append(" ");
                sb.append(body.toSource(0));
            }
        } else {
            sb.append(getBody().toSource(i).trim());
        }
        if (this.functionType == 1 || isMethod()) {
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.ScriptNode, org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        AstNode astNode;
        if (nodeVisitor.visit(this)) {
            Name name = this.functionName;
            if (name != null) {
                name.visit(nodeVisitor);
            }
            Iterator<AstNode> it = getParams().iterator();
            while (it.hasNext()) {
                it.next().visit(nodeVisitor);
            }
            getBody().visit(nodeVisitor);
            if (this.isExpressionClosure || (astNode = this.memberExprNode) == null) {
                return;
            }
            astNode.visit(nodeVisitor);
        }
    }
}
