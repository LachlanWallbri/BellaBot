package org.mozilla.javascript;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayComprehensionLoop;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.Comment;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.EmptyStatement;
import org.mozilla.javascript.ast.ErrorNode;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.IdeErrorReporter;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.KeywordLiteral;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.LabeledStatement;
import org.mozilla.javascript.ast.LetNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NewExpression;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.ObjectLiteral;
import org.mozilla.javascript.ast.ObjectProperty;
import org.mozilla.javascript.ast.ParenthesizedExpression;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.RegExpLiteral;
import org.mozilla.javascript.ast.ReturnStatement;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.ast.SwitchCase;
import org.mozilla.javascript.ast.SwitchStatement;
import org.mozilla.javascript.ast.ThrowStatement;
import org.mozilla.javascript.ast.TryStatement;
import org.mozilla.javascript.ast.UnaryExpression;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;
import org.mozilla.javascript.ast.WhileLoop;
import org.mozilla.javascript.ast.WithStatement;
import org.mozilla.javascript.ast.XmlDotQuery;
import org.mozilla.javascript.ast.XmlElemRef;
import org.mozilla.javascript.ast.XmlExpression;
import org.mozilla.javascript.ast.XmlLiteral;
import org.mozilla.javascript.ast.XmlMemberGet;
import org.mozilla.javascript.ast.XmlPropRef;
import org.mozilla.javascript.ast.XmlRef;
import org.mozilla.javascript.ast.XmlString;
import org.mozilla.javascript.ast.Yield;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class Parser {
    public static final int ARGC_LIMIT = 65536;
    static final int CLEAR_TI_MASK = 65535;
    private static final int GET_ENTRY = 2;
    private static final int METHOD_ENTRY = 8;
    private static final int PROP_ENTRY = 1;
    private static final int SET_ENTRY = 4;
    static final int TI_AFTER_EOL = 65536;
    static final int TI_CHECK_LABEL = 131072;
    boolean calledByCompileFunction;
    CompilerEnvirons compilerEnv;
    private int currentFlaggedToken;
    private Comment currentJsDocComment;
    private LabeledStatement currentLabel;
    Scope currentScope;
    ScriptNode currentScriptOrFn;
    private int currentToken;
    private boolean defaultUseStrictDirective;
    private int endFlags;
    private IdeErrorReporter errorCollector;
    private ErrorReporter errorReporter;
    private boolean inDestructuringAssignment;
    private boolean inForInit;
    protected boolean inUseStrictDirective;
    private Map<String, LabeledStatement> labelSet;
    private List<Jump> loopAndSwitchSet;
    private List<Loop> loopSet;
    protected int nestingOfFunction;
    private boolean parseFinished;
    private int prevNameTokenLineno;
    private int prevNameTokenStart;
    private String prevNameTokenString;
    private List<Comment> scannedComments;
    private char[] sourceChars;
    private String sourceURI;
    private int syntaxErrorCount;

    /* renamed from: ts */
    private TokenStream f10207ts;

    private static final boolean nowAllSet(int i, int i2, int i3) {
        return (i & i3) != i3 && (i2 & i3) == i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class ParserException extends RuntimeException {
        static final long serialVersionUID = 5882582646773765630L;

        private ParserException() {
        }
    }

    public Parser() {
        this(new CompilerEnvirons());
    }

    public Parser(CompilerEnvirons compilerEnvirons) {
        this(compilerEnvirons, compilerEnvirons.getErrorReporter());
    }

    public Parser(CompilerEnvirons compilerEnvirons, ErrorReporter errorReporter) {
        this.currentFlaggedToken = 0;
        this.prevNameTokenString = "";
        this.compilerEnv = compilerEnvirons;
        this.errorReporter = errorReporter;
        if (errorReporter instanceof IdeErrorReporter) {
            this.errorCollector = (IdeErrorReporter) errorReporter;
        }
    }

    void addStrictWarning(String str, String str2) {
        int i;
        TokenStream tokenStream = this.f10207ts;
        int i2 = -1;
        if (tokenStream != null) {
            i2 = tokenStream.tokenBeg;
            i = this.f10207ts.tokenEnd - this.f10207ts.tokenBeg;
        } else {
            i = -1;
        }
        addStrictWarning(str, str2, i2, i);
    }

    void addStrictWarning(String str, String str2, int i, int i2) {
        if (this.compilerEnv.isStrictMode()) {
            addWarning(str, str2, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addWarning(String str, String str2) {
        int i;
        TokenStream tokenStream = this.f10207ts;
        int i2 = -1;
        if (tokenStream != null) {
            i2 = tokenStream.tokenBeg;
            i = this.f10207ts.tokenEnd - this.f10207ts.tokenBeg;
        } else {
            i = -1;
        }
        addWarning(str, str2, i2, i);
    }

    void addWarning(String str, int i, int i2) {
        addWarning(str, null, i, i2);
    }

    void addWarning(String str, String str2, int i, int i2) {
        String lookupMessage = lookupMessage(str, str2);
        if (this.compilerEnv.reportWarningAsError()) {
            addError(str, str2, i, i2);
            return;
        }
        IdeErrorReporter ideErrorReporter = this.errorCollector;
        if (ideErrorReporter != null) {
            ideErrorReporter.warning(lookupMessage, this.sourceURI, i, i2);
        } else {
            this.errorReporter.warning(lookupMessage, this.sourceURI, this.f10207ts.getLineno(), this.f10207ts.getLine(), this.f10207ts.getOffset());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addError(String str) {
        addError(str, this.f10207ts.tokenBeg, this.f10207ts.tokenEnd - this.f10207ts.tokenBeg);
    }

    void addError(String str, int i, int i2) {
        addError(str, null, i, i2);
    }

    void addError(String str, String str2) {
        addError(str, str2, this.f10207ts.tokenBeg, this.f10207ts.tokenEnd - this.f10207ts.tokenBeg);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addError(String str, int i) {
        addError(str, Character.toString((char) i), this.f10207ts.tokenBeg, this.f10207ts.tokenEnd - this.f10207ts.tokenBeg);
    }

    void addError(String str, String str2, int i, int i2) {
        String str3;
        int i3;
        int i4;
        this.syntaxErrorCount++;
        String lookupMessage = lookupMessage(str, str2);
        IdeErrorReporter ideErrorReporter = this.errorCollector;
        if (ideErrorReporter != null) {
            ideErrorReporter.error(lookupMessage, this.sourceURI, i, i2);
            return;
        }
        TokenStream tokenStream = this.f10207ts;
        if (tokenStream != null) {
            int lineno = tokenStream.getLineno();
            str3 = this.f10207ts.getLine();
            i4 = this.f10207ts.getOffset();
            i3 = lineno;
        } else {
            str3 = "";
            i3 = 1;
            i4 = 1;
        }
        this.errorReporter.error(lookupMessage, this.sourceURI, i3, str3, i4);
    }

    private void addStrictWarning(String str, String str2, int i, int i2, int i3, String str3, int i4) {
        if (this.compilerEnv.isStrictMode()) {
            addWarning(str, str2, i, i2, i3, str3, i4);
        }
    }

    private void addWarning(String str, String str2, int i, int i2, int i3, String str3, int i4) {
        String lookupMessage = lookupMessage(str, str2);
        if (this.compilerEnv.reportWarningAsError()) {
            addError(str, str2, i, i2, i3, str3, i4);
            return;
        }
        IdeErrorReporter ideErrorReporter = this.errorCollector;
        if (ideErrorReporter != null) {
            ideErrorReporter.warning(lookupMessage, this.sourceURI, i, i2);
        } else {
            this.errorReporter.warning(lookupMessage, this.sourceURI, i3, str3, i4);
        }
    }

    private void addError(String str, String str2, int i, int i2, int i3, String str3, int i4) {
        this.syntaxErrorCount++;
        String lookupMessage = lookupMessage(str, str2);
        IdeErrorReporter ideErrorReporter = this.errorCollector;
        if (ideErrorReporter != null) {
            ideErrorReporter.error(lookupMessage, this.sourceURI, i, i2);
        } else {
            this.errorReporter.error(lookupMessage, this.sourceURI, i3, str3, i4);
        }
    }

    String lookupMessage(String str) {
        return lookupMessage(str, null);
    }

    String lookupMessage(String str, String str2) {
        if (str2 == null) {
            return ScriptRuntime.getMessage0(str);
        }
        return ScriptRuntime.getMessage1(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportError(String str) {
        reportError(str, null);
    }

    void reportError(String str, String str2) {
        TokenStream tokenStream = this.f10207ts;
        if (tokenStream == null) {
            reportError(str, str2, 1, 1);
        } else {
            reportError(str, str2, tokenStream.tokenBeg, this.f10207ts.tokenEnd - this.f10207ts.tokenBeg);
        }
    }

    void reportError(String str, int i, int i2) {
        reportError(str, null, i, i2);
    }

    void reportError(String str, String str2, int i, int i2) {
        addError(str, str2, i, i2);
        if (!this.compilerEnv.recoverFromErrors()) {
            throw new ParserException();
        }
    }

    private int getNodeEnd(AstNode astNode) {
        return astNode.getPosition() + astNode.getLength();
    }

    private void recordComment(int i, String str) {
        if (this.scannedComments == null) {
            this.scannedComments = new ArrayList();
        }
        Comment comment = new Comment(this.f10207ts.tokenBeg, this.f10207ts.getTokenLength(), this.f10207ts.commentType, str);
        if (this.f10207ts.commentType == Token.CommentType.JSDOC && this.compilerEnv.isRecordingLocalJsDocComments()) {
            this.currentJsDocComment = comment;
        }
        comment.setLineno(i);
        this.scannedComments.add(comment);
    }

    private Comment getAndResetJsDoc() {
        Comment comment = this.currentJsDocComment;
        this.currentJsDocComment = null;
        return comment;
    }

    private int getNumberOfEols(String str) {
        int i = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) == '\n') {
                i++;
            }
        }
        return i;
    }

    private int peekToken() throws IOException {
        if (this.currentFlaggedToken != 0) {
            return this.currentToken;
        }
        int lineno = this.f10207ts.getLineno();
        int token = this.f10207ts.getToken();
        boolean z = false;
        while (true) {
            if (token != 1 && token != 162) {
                break;
            }
            if (token == 1) {
                lineno++;
                z = true;
            } else if (this.compilerEnv.isRecordingComments()) {
                String andResetCurrentComment = this.f10207ts.getAndResetCurrentComment();
                recordComment(lineno, andResetCurrentComment);
                lineno += getNumberOfEols(andResetCurrentComment);
            }
            token = this.f10207ts.getToken();
        }
        this.currentToken = token;
        this.currentFlaggedToken = token | (z ? 65536 : 0);
        return this.currentToken;
    }

    private int peekFlaggedToken() throws IOException {
        peekToken();
        return this.currentFlaggedToken;
    }

    private void consumeToken() {
        this.currentFlaggedToken = 0;
    }

    private int nextToken() throws IOException {
        int peekToken = peekToken();
        consumeToken();
        return peekToken;
    }

    private int nextFlaggedToken() throws IOException {
        peekToken();
        int i = this.currentFlaggedToken;
        consumeToken();
        return i;
    }

    private boolean matchToken(int i) throws IOException {
        if (peekToken() != i) {
            return false;
        }
        consumeToken();
        return true;
    }

    private int peekTokenOrEOL() throws IOException {
        int peekToken = peekToken();
        if ((this.currentFlaggedToken & 65536) != 0) {
            return 1;
        }
        return peekToken;
    }

    private boolean mustMatchToken(int i, String str) throws IOException {
        return mustMatchToken(i, str, this.f10207ts.tokenBeg, this.f10207ts.tokenEnd - this.f10207ts.tokenBeg);
    }

    private boolean mustMatchToken(int i, String str, int i2, int i3) throws IOException {
        if (matchToken(i)) {
            return true;
        }
        reportError(str, i2, i3);
        return false;
    }

    private void mustHaveXML() {
        if (this.compilerEnv.isXmlAvailable()) {
            return;
        }
        reportError("msg.XML.not.available");
    }

    public boolean eof() {
        return this.f10207ts.eof();
    }

    boolean insideFunction() {
        return this.nestingOfFunction != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushScope(Scope scope) {
        Scope parentScope = scope.getParentScope();
        if (parentScope != null) {
            if (parentScope != this.currentScope) {
                codeBug();
            }
        } else {
            this.currentScope.addChildScope(scope);
        }
        this.currentScope = scope;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void popScope() {
        this.currentScope = this.currentScope.getParentScope();
    }

    private void enterLoop(Loop loop) {
        if (this.loopSet == null) {
            this.loopSet = new ArrayList();
        }
        this.loopSet.add(loop);
        if (this.loopAndSwitchSet == null) {
            this.loopAndSwitchSet = new ArrayList();
        }
        this.loopAndSwitchSet.add(loop);
        pushScope(loop);
        LabeledStatement labeledStatement = this.currentLabel;
        if (labeledStatement != null) {
            labeledStatement.setStatement(loop);
            this.currentLabel.getFirstLabel().setLoop(loop);
            loop.setRelative(-this.currentLabel.getPosition());
        }
    }

    private void exitLoop() {
        Loop remove = this.loopSet.remove(r0.size() - 1);
        this.loopAndSwitchSet.remove(r1.size() - 1);
        if (remove.getParent() != null) {
            remove.setRelative(remove.getParent().getPosition());
        }
        popScope();
    }

    private void enterSwitch(SwitchStatement switchStatement) {
        if (this.loopAndSwitchSet == null) {
            this.loopAndSwitchSet = new ArrayList();
        }
        this.loopAndSwitchSet.add(switchStatement);
    }

    private void exitSwitch() {
        this.loopAndSwitchSet.remove(r0.size() - 1);
    }

    public AstRoot parse(String str, String str2, int i) {
        if (this.parseFinished) {
            throw new IllegalStateException("parser reused");
        }
        this.sourceURI = str2;
        if (this.compilerEnv.isIdeMode()) {
            this.sourceChars = str.toCharArray();
        }
        this.f10207ts = new TokenStream(this, null, str, i);
        try {
            try {
                return parse();
            } catch (IOException unused) {
                throw new IllegalStateException();
            }
        } finally {
            this.parseFinished = true;
        }
    }

    public AstRoot parse(Reader reader, String str, int i) throws IOException {
        if (this.parseFinished) {
            throw new IllegalStateException("parser reused");
        }
        if (this.compilerEnv.isIdeMode()) {
            return parse(readFully(reader), str, i);
        }
        try {
            this.sourceURI = str;
            this.f10207ts = new TokenStream(this, reader, null, i);
            return parse();
        } finally {
            this.parseFinished = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private AstRoot parse() throws IOException {
        int i;
        List<Comment> list;
        AstNode statement;
        AstRoot astRoot = new AstRoot(0);
        this.currentScriptOrFn = astRoot;
        this.currentScope = astRoot;
        int i2 = this.f10207ts.lineno;
        boolean z = this.inUseStrictDirective;
        boolean z2 = this.defaultUseStrictDirective;
        this.inUseStrictDirective = z2;
        if (z2) {
            astRoot.setInStrictMode(true);
        }
        int i3 = 0;
        boolean z3 = true;
        while (true) {
            try {
                try {
                    int peekToken = peekToken();
                    if (peekToken <= 0) {
                        break;
                    }
                    if (peekToken == 110) {
                        consumeToken();
                        try {
                            statement = function(this.calledByCompileFunction ? 2 : 1);
                        } catch (ParserException unused) {
                        }
                    } else {
                        statement = statement();
                        if (z3) {
                            String directive = getDirective(statement);
                            if (directive == null) {
                                z3 = false;
                            } else if (directive.equals("use strict")) {
                                this.inUseStrictDirective = true;
                                astRoot.setInStrictMode(true);
                            }
                        }
                    }
                    i3 = getNodeEnd(statement);
                    astRoot.addChildToBack(statement);
                    statement.setParent(astRoot);
                } finally {
                    this.inUseStrictDirective = z;
                }
            } catch (StackOverflowError unused2) {
                String lookupMessage = lookupMessage("msg.too.deep.parser.recursion");
                if (!this.compilerEnv.isIdeMode()) {
                    throw Context.reportRuntimeError(lookupMessage, this.sourceURI, this.f10207ts.lineno, null, 0);
                }
            }
            i = this.syntaxErrorCount;
            if (i != 0) {
                String lookupMessage2 = lookupMessage("msg.got.syntax.errors", String.valueOf(i));
                if (!this.compilerEnv.isIdeMode()) {
                    throw this.errorReporter.runtimeError(lookupMessage2, this.sourceURI, i2, null, 0);
                }
            }
            list = this.scannedComments;
            if (list != null) {
                i3 = Math.max(i3, getNodeEnd(this.scannedComments.get(list.size() - 1)));
                Iterator<Comment> it = this.scannedComments.iterator();
                while (it.hasNext()) {
                    astRoot.addComment(it.next());
                }
            }
            astRoot.setLength(i3 - 0);
            astRoot.setSourceName(this.sourceURI);
            astRoot.setBaseLineno(i2);
            astRoot.setEndLineno(this.f10207ts.lineno);
            return astRoot;
        }
        i = this.syntaxErrorCount;
        if (i != 0) {
        }
        list = this.scannedComments;
        if (list != null) {
        }
        astRoot.setLength(i3 - 0);
        astRoot.setSourceName(this.sourceURI);
        astRoot.setBaseLineno(i2);
        astRoot.setEndLineno(this.f10207ts.lineno);
        return astRoot;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(2:3|(9:7|(1:9)(1:57)|10|11|(3:13|(1:15)|16)(2:25|(2:26|(3:30|(2:32|(1:47)(2:34|(2:36|37)(2:39|(2:41|(2:43|44)(1:45))(1:46))))(2:48|49)|38)(3:52|51|50)))|17|(1:21)|22|23)(1:6))|58|(0)(0)|10|11|(0)(0)|17|(2:19|21)|22|23|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00a6, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a7, code lost:
    
        r10.nestingOfFunction--;
        r10.inUseStrictDirective = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ae, code lost:
    
        throw r11;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0040 A[Catch: all -> 0x00a6, ParserException -> 0x00af, TRY_ENTER, TryCatch #2 {ParserException -> 0x00af, all -> 0x00a6, blocks: (B:13:0x0040, B:15:0x005f, B:16:0x0066, B:26:0x006b, B:32:0x007a, B:34:0x0080, B:39:0x0088, B:41:0x0090, B:43:0x0097, B:38:0x00a2, B:48:0x009b), top: B:11:0x003e }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private AstNode parseFunctionBody(int i, FunctionNode functionNode) throws IOException {
        boolean z;
        int i2;
        AstNode function;
        if (!matchToken(86)) {
            if (this.compilerEnv.getLanguageVersion() < 180 && i != 4) {
                reportError("msg.no.brace.body");
            } else {
                z = true;
                boolean z2 = i != 4;
                this.nestingOfFunction++;
                int i3 = this.f10207ts.tokenBeg;
                Block block = new Block(i3);
                boolean z3 = this.inUseStrictDirective;
                block.setLineno(this.f10207ts.lineno);
                if (z) {
                    boolean z4 = true;
                    while (true) {
                        int peekToken = peekToken();
                        if (peekToken == -1 || peekToken == 0 || peekToken == 87) {
                            break;
                        }
                        if (peekToken == 110) {
                            consumeToken();
                            function = function(1);
                        } else {
                            function = statement();
                            if (z4) {
                                String directive = getDirective(function);
                                if (directive == null) {
                                    z4 = false;
                                } else if (directive.equals("use strict")) {
                                    this.inUseStrictDirective = true;
                                    functionNode.setInStrictMode(true);
                                    if (!z3) {
                                        setRequiresActivation();
                                    }
                                }
                            }
                        }
                        block.addStatement(function);
                    }
                } else {
                    AstNode assignExpr = assignExpr();
                    ReturnStatement returnStatement = new ReturnStatement(assignExpr.getPosition(), assignExpr.getLength(), assignExpr);
                    returnStatement.putProp(25, Boolean.TRUE);
                    block.putProp(25, Boolean.TRUE);
                    if (z2) {
                        returnStatement.putProp(27, Boolean.TRUE);
                    }
                    block.addStatement(returnStatement);
                }
                this.nestingOfFunction--;
                this.inUseStrictDirective = z3;
                i2 = this.f10207ts.tokenEnd;
                getAndResetJsDoc();
                if (!z && mustMatchToken(87, "msg.no.brace.after.body")) {
                    i2 = this.f10207ts.tokenEnd;
                }
                block.setLength(i2 - i3);
                return block;
            }
        }
        z = false;
        if (i != 4) {
        }
        this.nestingOfFunction++;
        int i32 = this.f10207ts.tokenBeg;
        Block block2 = new Block(i32);
        boolean z32 = this.inUseStrictDirective;
        block2.setLineno(this.f10207ts.lineno);
        if (z) {
        }
        this.nestingOfFunction--;
        this.inUseStrictDirective = z32;
        i2 = this.f10207ts.tokenEnd;
        getAndResetJsDoc();
        if (!z) {
            i2 = this.f10207ts.tokenEnd;
        }
        block2.setLength(i2 - i32);
        return block2;
    }

    private String getDirective(AstNode astNode) {
        if (!(astNode instanceof ExpressionStatement)) {
            return null;
        }
        AstNode expression = ((ExpressionStatement) astNode).getExpression();
        if (expression instanceof StringLiteral) {
            return ((StringLiteral) expression).getValue();
        }
        return null;
    }

    private void parseFunctionParams(FunctionNode functionNode) throws IOException {
        if (matchToken(89)) {
            functionNode.setRp(this.f10207ts.tokenBeg - functionNode.getPosition());
            return;
        }
        HashMap hashMap = null;
        HashSet hashSet = new HashSet();
        do {
            int peekToken = peekToken();
            if (peekToken == 84 || peekToken == 86) {
                AstNode destructuringPrimaryExpr = destructuringPrimaryExpr();
                markDestructuring(destructuringPrimaryExpr);
                functionNode.addParam(destructuringPrimaryExpr);
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String nextTempName = this.currentScriptOrFn.getNextTempName();
                defineSymbol(88, nextTempName, false);
                hashMap.put(nextTempName, destructuringPrimaryExpr);
            } else if (mustMatchToken(39, "msg.no.parm")) {
                AstNode createNameNode = createNameNode();
                Comment andResetJsDoc = getAndResetJsDoc();
                if (andResetJsDoc != null) {
                    createNameNode.setJsDocNode(andResetJsDoc);
                }
                functionNode.addParam(createNameNode);
                String string = this.f10207ts.getString();
                defineSymbol(88, string);
                if (this.inUseStrictDirective) {
                    if ("eval".equals(string) || "arguments".equals(string)) {
                        reportError("msg.bad.id.strict", string);
                    }
                    if (hashSet.contains(string)) {
                        addError("msg.dup.param.strict", string);
                    }
                    hashSet.add(string);
                }
            } else {
                functionNode.addParam(makeErrorNode());
            }
        } while (matchToken(90));
        if (hashMap != null) {
            Node node = new Node(90);
            for (Map.Entry entry : hashMap.entrySet()) {
                node.addChildToBack(createDestructuringAssignment(123, (Node) entry.getValue(), createName((String) entry.getKey())));
            }
            functionNode.putProp(23, node);
        }
        if (mustMatchToken(89, "msg.no.paren.after.parms")) {
            functionNode.setRp(this.f10207ts.tokenBeg - functionNode.getPosition());
        }
    }

    private FunctionNode function(int i) throws IOException {
        Name name;
        int i2 = this.f10207ts.lineno;
        int i3 = this.f10207ts.tokenBeg;
        AstNode astNode = null;
        if (matchToken(39)) {
            name = createNameNode(true, 39);
            if (this.inUseStrictDirective) {
                String identifier = name.getIdentifier();
                if ("eval".equals(identifier) || "arguments".equals(identifier)) {
                    reportError("msg.bad.id.strict", identifier);
                }
            }
            if (!matchToken(88)) {
                if (this.compilerEnv.isAllowMemberExprAsFunctionName()) {
                    astNode = memberExprTail(false, name);
                    name = null;
                }
                mustMatchToken(88, "msg.no.paren.parms");
            }
        } else if (matchToken(88)) {
            name = null;
        } else {
            AstNode memberExpr = this.compilerEnv.isAllowMemberExprAsFunctionName() ? memberExpr(false) : null;
            mustMatchToken(88, "msg.no.paren.parms");
            astNode = memberExpr;
            name = null;
        }
        int i4 = this.currentToken == 88 ? this.f10207ts.tokenBeg : -1;
        if ((astNode != null ? 2 : i) != 2 && name != null && name.length() > 0) {
            defineSymbol(110, name.getIdentifier());
        }
        FunctionNode functionNode = new FunctionNode(i3, name);
        functionNode.setFunctionType(i);
        if (i4 != -1) {
            functionNode.setLp(i4 - i3);
        }
        functionNode.setJsDocNode(getAndResetJsDoc());
        PerFunctionVariables perFunctionVariables = new PerFunctionVariables(functionNode);
        try {
            parseFunctionParams(functionNode);
            functionNode.setBody(parseFunctionBody(i, functionNode));
            functionNode.setEncodedSourceBounds(i3, this.f10207ts.tokenEnd);
            functionNode.setLength(this.f10207ts.tokenEnd - i3);
            if (this.compilerEnv.isStrictMode() && !functionNode.getBody().hasConsistentReturnUsage()) {
                addStrictWarning((name == null || name.length() <= 0) ? "msg.anon.no.return.value" : "msg.no.return.value", name == null ? "" : name.getIdentifier());
            }
            if (astNode != null) {
                Kit.codeBug();
                functionNode.setMemberExprNode(astNode);
            }
            functionNode.setSourceName(this.sourceURI);
            functionNode.setBaseLineno(i2);
            functionNode.setEndLineno(this.f10207ts.lineno);
            if (this.compilerEnv.isIdeMode()) {
                functionNode.setParentScope(this.currentScope);
            }
            return functionNode;
        } finally {
            perFunctionVariables.restore();
        }
    }

    private AstNode arrowFunction(AstNode astNode) throws IOException {
        int i = this.f10207ts.lineno;
        int position = astNode != null ? astNode.getPosition() : -1;
        FunctionNode functionNode = new FunctionNode(position);
        functionNode.setFunctionType(4);
        functionNode.setJsDocNode(getAndResetJsDoc());
        Map<String, Node> hashMap = new HashMap<>();
        Set<String> hashSet = new HashSet<>();
        PerFunctionVariables perFunctionVariables = new PerFunctionVariables(functionNode);
        try {
            if (astNode instanceof ParenthesizedExpression) {
                functionNode.setParens(0, astNode.getLength());
                AstNode expression = ((ParenthesizedExpression) astNode).getExpression();
                if (!(expression instanceof EmptyExpression)) {
                    arrowFunctionParams(functionNode, expression, hashMap, hashSet);
                }
            } else {
                arrowFunctionParams(functionNode, astNode, hashMap, hashSet);
            }
            if (!hashMap.isEmpty()) {
                Node node = new Node(90);
                for (Map.Entry<String, Node> entry : hashMap.entrySet()) {
                    node.addChildToBack(createDestructuringAssignment(123, entry.getValue(), createName(entry.getKey())));
                }
                functionNode.putProp(23, node);
            }
            functionNode.setBody(parseFunctionBody(4, functionNode));
            functionNode.setEncodedSourceBounds(position, this.f10207ts.tokenEnd);
            functionNode.setLength(this.f10207ts.tokenEnd - position);
            perFunctionVariables.restore();
            if (functionNode.isGenerator()) {
                reportError("msg.arrowfunction.generator");
                return makeErrorNode();
            }
            functionNode.setSourceName(this.sourceURI);
            functionNode.setBaseLineno(i);
            functionNode.setEndLineno(this.f10207ts.lineno);
            return functionNode;
        } catch (Throwable th) {
            perFunctionVariables.restore();
            throw th;
        }
    }

    private void arrowFunctionParams(FunctionNode functionNode, AstNode astNode, Map<String, Node> map, Set<String> set) {
        if ((astNode instanceof ArrayLiteral) || (astNode instanceof ObjectLiteral)) {
            markDestructuring(astNode);
            functionNode.addParam(astNode);
            String nextTempName = this.currentScriptOrFn.getNextTempName();
            defineSymbol(88, nextTempName, false);
            map.put(nextTempName, astNode);
            return;
        }
        if ((astNode instanceof InfixExpression) && astNode.getType() == 90) {
            InfixExpression infixExpression = (InfixExpression) astNode;
            arrowFunctionParams(functionNode, infixExpression.getLeft(), map, set);
            arrowFunctionParams(functionNode, infixExpression.getRight(), map, set);
            return;
        }
        if (astNode instanceof Name) {
            functionNode.addParam(astNode);
            String identifier = ((Name) astNode).getIdentifier();
            defineSymbol(88, identifier);
            if (this.inUseStrictDirective) {
                if ("eval".equals(identifier) || "arguments".equals(identifier)) {
                    reportError("msg.bad.id.strict", identifier);
                }
                if (set.contains(identifier)) {
                    addError("msg.dup.param.strict", identifier);
                }
                set.add(identifier);
                return;
            }
            return;
        }
        reportError("msg.no.parm", astNode.getPosition(), astNode.getLength());
        functionNode.addParam(makeErrorNode());
    }

    private AstNode statements(AstNode astNode) throws IOException {
        if (this.currentToken != 86 && !this.compilerEnv.isIdeMode()) {
            codeBug();
        }
        int i = this.f10207ts.tokenBeg;
        if (astNode == null) {
            astNode = new Block(i);
        }
        astNode.setLineno(this.f10207ts.lineno);
        while (true) {
            int peekToken = peekToken();
            if (peekToken <= 0 || peekToken == 87) {
                break;
            }
            astNode.addChild(statement());
        }
        astNode.setLength(this.f10207ts.tokenBeg - i);
        return astNode;
    }

    private AstNode statements() throws IOException {
        return statements(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class ConditionData {
        AstNode condition;

        /* renamed from: lp */
        int f10208lp;

        /* renamed from: rp */
        int f10209rp;

        private ConditionData() {
            this.f10208lp = -1;
            this.f10209rp = -1;
        }
    }

    private ConditionData condition() throws IOException {
        ConditionData conditionData = new ConditionData();
        if (mustMatchToken(88, "msg.no.paren.cond")) {
            conditionData.f10208lp = this.f10207ts.tokenBeg;
        }
        conditionData.condition = expr();
        if (mustMatchToken(89, "msg.no.paren.after.cond")) {
            conditionData.f10209rp = this.f10207ts.tokenBeg;
        }
        if (conditionData.condition instanceof Assignment) {
            addStrictWarning("msg.equal.as.assign", "", conditionData.condition.getPosition(), conditionData.condition.getLength());
        }
        return conditionData;
    }

    private AstNode statement() throws IOException {
        int peekTokenOrEOL;
        int i = this.f10207ts.tokenBeg;
        try {
            AstNode statementHelper = statementHelper();
            if (statementHelper != null) {
                if (this.compilerEnv.isStrictMode() && !statementHelper.hasSideEffects()) {
                    int position = statementHelper.getPosition();
                    int max = Math.max(position, lineBeginningFor(position));
                    addStrictWarning(statementHelper instanceof EmptyStatement ? "msg.extra.trailing.semi" : "msg.no.side.effects", "", max, nodeEnd(statementHelper) - max);
                }
                return statementHelper;
            }
        } catch (ParserException unused) {
        }
        do {
            peekTokenOrEOL = peekTokenOrEOL();
            consumeToken();
            if (peekTokenOrEOL == -1 || peekTokenOrEOL == 0 || peekTokenOrEOL == 1) {
                break;
            }
        } while (peekTokenOrEOL != 83);
        return new EmptyStatement(i, this.f10207ts.tokenBeg - i);
    }

    private AstNode statementHelper() throws IOException {
        AstNode returnOrYield;
        LabeledStatement labeledStatement = this.currentLabel;
        if (labeledStatement != null && labeledStatement.getStatement() != null) {
            this.currentLabel = null;
        }
        int peekToken = peekToken();
        int i = this.f10207ts.tokenBeg;
        if (peekToken != -1) {
            if (peekToken != 4) {
                if (peekToken == 39) {
                    returnOrYield = nameOrLabel();
                    if (!(returnOrYield instanceof ExpressionStatement)) {
                        return returnOrYield;
                    }
                } else if (peekToken == 50) {
                    returnOrYield = throwStatement();
                } else if (peekToken != 73) {
                    if (peekToken == 86) {
                        return block();
                    }
                    if (peekToken == 110) {
                        consumeToken();
                        return function(3);
                    }
                    if (peekToken == 113) {
                        return ifStatement();
                    }
                    if (peekToken == 115) {
                        return switchStatement();
                    }
                    if (peekToken == 161) {
                        consumeToken();
                        returnOrYield = new KeywordLiteral(this.f10207ts.tokenBeg, this.f10207ts.tokenEnd - this.f10207ts.tokenBeg, peekToken);
                        returnOrYield.setLineno(this.f10207ts.lineno);
                    } else {
                        if (peekToken == 82) {
                            return tryStatement();
                        }
                        if (peekToken == 83) {
                            consumeToken();
                            int i2 = this.f10207ts.tokenBeg;
                            EmptyStatement emptyStatement = new EmptyStatement(i2, this.f10207ts.tokenEnd - i2);
                            emptyStatement.setLineno(this.f10207ts.lineno);
                            return emptyStatement;
                        }
                        if (peekToken != 154) {
                            if (peekToken != 155) {
                                switch (peekToken) {
                                    case 117:
                                        returnOrYield = defaultXmlNamespace();
                                        break;
                                    case 118:
                                        return whileLoop();
                                    case 119:
                                        return doLoop();
                                    case 120:
                                        return forLoop();
                                    case 121:
                                        returnOrYield = breakStatement();
                                        break;
                                    case 122:
                                        returnOrYield = continueStatement();
                                        break;
                                    case 123:
                                        break;
                                    case 124:
                                        if (this.inUseStrictDirective) {
                                            reportError("msg.no.with.strict");
                                        }
                                        return withStatement();
                                    default:
                                        int i3 = this.f10207ts.lineno;
                                        returnOrYield = new ExpressionStatement(expr(), true ^ insideFunction());
                                        returnOrYield.setLineno(i3);
                                        break;
                                }
                            }
                            consumeToken();
                            int i4 = this.f10207ts.lineno;
                            returnOrYield = variables(this.currentToken, this.f10207ts.tokenBeg, true);
                            returnOrYield.setLineno(i4);
                        } else {
                            AstNode letStatement = letStatement();
                            if (!(letStatement instanceof VariableDeclaration) || peekToken() != 83) {
                                return letStatement;
                            }
                            returnOrYield = letStatement;
                        }
                    }
                }
                autoInsertSemicolon(returnOrYield);
                return returnOrYield;
            }
            returnOrYield = returnOrYield(peekToken, false);
            autoInsertSemicolon(returnOrYield);
            return returnOrYield;
        }
        consumeToken();
        return makeErrorNode();
    }

    private void autoInsertSemicolon(AstNode astNode) throws IOException {
        int peekFlaggedToken = peekFlaggedToken();
        int position = astNode.getPosition();
        int i = 65535 & peekFlaggedToken;
        if (i != -1 && i != 0) {
            if (i == 83) {
                consumeToken();
                astNode.setLength(this.f10207ts.tokenEnd - position);
                return;
            } else if (i != 87) {
                if ((peekFlaggedToken & 65536) == 0) {
                    reportError("msg.no.semi.stmt");
                    return;
                } else {
                    warnMissingSemi(position, nodeEnd(astNode));
                    return;
                }
            }
        }
        warnMissingSemi(position, nodeEnd(astNode));
    }

    private IfStatement ifStatement() throws IOException {
        if (this.currentToken != 113) {
            codeBug();
        }
        consumeToken();
        int i = this.f10207ts.tokenBeg;
        int i2 = this.f10207ts.lineno;
        int i3 = -1;
        ConditionData condition = condition();
        AstNode statement = statement();
        AstNode astNode = null;
        if (matchToken(114)) {
            i3 = this.f10207ts.tokenBeg - i;
            astNode = statement();
        }
        IfStatement ifStatement = new IfStatement(i, getNodeEnd(astNode != null ? astNode : statement) - i);
        ifStatement.setCondition(condition.condition);
        ifStatement.setParens(condition.f10208lp - i, condition.f10209rp - i);
        ifStatement.setThenPart(statement);
        ifStatement.setElsePart(astNode);
        ifStatement.setElsePosition(i3);
        ifStatement.setLineno(i2);
        return ifStatement;
    }

    private SwitchStatement switchStatement() throws IOException {
        AstNode expr;
        if (this.currentToken != 115) {
            codeBug();
        }
        consumeToken();
        int i = this.f10207ts.tokenBeg;
        SwitchStatement switchStatement = new SwitchStatement(i);
        if (mustMatchToken(88, "msg.no.paren.switch")) {
            switchStatement.setLp(this.f10207ts.tokenBeg - i);
        }
        switchStatement.setLineno(this.f10207ts.lineno);
        switchStatement.setExpression(expr());
        enterSwitch(switchStatement);
        try {
            if (mustMatchToken(89, "msg.no.paren.after.switch")) {
                switchStatement.setRp(this.f10207ts.tokenBeg - i);
            }
            mustMatchToken(86, "msg.no.brace.switch");
            boolean z = false;
            while (true) {
                int nextToken = nextToken();
                int i2 = this.f10207ts.tokenBeg;
                int i3 = this.f10207ts.lineno;
                if (nextToken == 87) {
                    switchStatement.setLength(this.f10207ts.tokenEnd - i);
                    break;
                }
                if (nextToken == 116) {
                    expr = expr();
                    mustMatchToken(104, "msg.no.colon.case");
                } else if (nextToken == 117) {
                    if (z) {
                        reportError("msg.double.switch.default");
                    }
                    z = true;
                    expr = null;
                    mustMatchToken(104, "msg.no.colon.case");
                } else {
                    reportError("msg.bad.switch");
                    break;
                }
                SwitchCase switchCase = new SwitchCase(i2);
                switchCase.setExpression(expr);
                switchCase.setLength(this.f10207ts.tokenEnd - i);
                switchCase.setLineno(i3);
                while (true) {
                    int peekToken = peekToken();
                    if (peekToken != 87 && peekToken != 116 && peekToken != 117 && peekToken != 0) {
                        switchCase.addStatement(statement());
                    }
                }
                switchStatement.addCase(switchCase);
            }
            return switchStatement;
        } finally {
            exitSwitch();
        }
    }

    private WhileLoop whileLoop() throws IOException {
        if (this.currentToken != 118) {
            codeBug();
        }
        consumeToken();
        int i = this.f10207ts.tokenBeg;
        WhileLoop whileLoop = new WhileLoop(i);
        whileLoop.setLineno(this.f10207ts.lineno);
        enterLoop(whileLoop);
        try {
            ConditionData condition = condition();
            whileLoop.setCondition(condition.condition);
            whileLoop.setParens(condition.f10208lp - i, condition.f10209rp - i);
            AstNode statement = statement();
            whileLoop.setLength(getNodeEnd(statement) - i);
            whileLoop.setBody(statement);
            return whileLoop;
        } finally {
            exitLoop();
        }
    }

    private DoLoop doLoop() throws IOException {
        if (this.currentToken != 119) {
            codeBug();
        }
        consumeToken();
        int i = this.f10207ts.tokenBeg;
        DoLoop doLoop = new DoLoop(i);
        doLoop.setLineno(this.f10207ts.lineno);
        enterLoop(doLoop);
        try {
            AstNode statement = statement();
            mustMatchToken(118, "msg.no.while.do");
            doLoop.setWhilePosition(this.f10207ts.tokenBeg - i);
            ConditionData condition = condition();
            doLoop.setCondition(condition.condition);
            doLoop.setParens(condition.f10208lp - i, condition.f10209rp - i);
            int nodeEnd = getNodeEnd(statement);
            doLoop.setBody(statement);
            exitLoop();
            if (matchToken(83)) {
                nodeEnd = this.f10207ts.tokenEnd;
            }
            doLoop.setLength(nodeEnd - i);
            return doLoop;
        } catch (Throwable th) {
            exitLoop();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004d A[Catch: all -> 0x016f, TryCatch #1 {all -> 0x016f, blocks: (B:6:0x0021, B:9:0x002b, B:11:0x0039, B:12:0x0045, B:14:0x004d, B:15:0x0054, B:17:0x0066, B:18:0x00e5, B:20:0x00ed, B:24:0x00f9, B:25:0x013f, B:28:0x0159, B:37:0x016b, B:38:0x016e, B:39:0x0108, B:41:0x0111, B:43:0x011e, B:46:0x0127, B:47:0x012c, B:49:0x0074, B:51:0x007e, B:53:0x0084, B:55:0x0092, B:56:0x009e, B:58:0x00ab, B:59:0x00c0, B:61:0x00d0, B:63:0x00de, B:64:0x00bc, B:66:0x0040, B:27:0x014a), top: B:5:0x0021, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0066 A[Catch: all -> 0x016f, TryCatch #1 {all -> 0x016f, blocks: (B:6:0x0021, B:9:0x002b, B:11:0x0039, B:12:0x0045, B:14:0x004d, B:15:0x0054, B:17:0x0066, B:18:0x00e5, B:20:0x00ed, B:24:0x00f9, B:25:0x013f, B:28:0x0159, B:37:0x016b, B:38:0x016e, B:39:0x0108, B:41:0x0111, B:43:0x011e, B:46:0x0127, B:47:0x012c, B:49:0x0074, B:51:0x007e, B:53:0x0084, B:55:0x0092, B:56:0x009e, B:58:0x00ab, B:59:0x00c0, B:61:0x00d0, B:63:0x00de, B:64:0x00bc, B:66:0x0040, B:27:0x014a), top: B:5:0x0021, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ed A[Catch: all -> 0x016f, TryCatch #1 {all -> 0x016f, blocks: (B:6:0x0021, B:9:0x002b, B:11:0x0039, B:12:0x0045, B:14:0x004d, B:15:0x0054, B:17:0x0066, B:18:0x00e5, B:20:0x00ed, B:24:0x00f9, B:25:0x013f, B:28:0x0159, B:37:0x016b, B:38:0x016e, B:39:0x0108, B:41:0x0111, B:43:0x011e, B:46:0x0127, B:47:0x012c, B:49:0x0074, B:51:0x007e, B:53:0x0084, B:55:0x0092, B:56:0x009e, B:58:0x00ab, B:59:0x00c0, B:61:0x00d0, B:63:0x00de, B:64:0x00bc, B:66:0x0040, B:27:0x014a), top: B:5:0x0021, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0160 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0074 A[Catch: all -> 0x016f, TryCatch #1 {all -> 0x016f, blocks: (B:6:0x0021, B:9:0x002b, B:11:0x0039, B:12:0x0045, B:14:0x004d, B:15:0x0054, B:17:0x0066, B:18:0x00e5, B:20:0x00ed, B:24:0x00f9, B:25:0x013f, B:28:0x0159, B:37:0x016b, B:38:0x016e, B:39:0x0108, B:41:0x0111, B:43:0x011e, B:46:0x0127, B:47:0x012c, B:49:0x0074, B:51:0x007e, B:53:0x0084, B:55:0x0092, B:56:0x009e, B:58:0x00ab, B:59:0x00c0, B:61:0x00d0, B:63:0x00de, B:64:0x00bc, B:66:0x0040, B:27:0x014a), top: B:5:0x0021, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Loop forLoop() throws IOException {
        boolean z;
        int i;
        AstNode forLoopInit;
        AstNode expr;
        AstNode astNode;
        int i2;
        boolean z2;
        ForInLoop forInLoop;
        if (this.currentToken != 120) {
            codeBug();
        }
        consumeToken();
        int i3 = this.f10207ts.tokenBeg;
        int i4 = this.f10207ts.lineno;
        AstNode astNode2 = null;
        Scope scope = new Scope();
        pushScope(scope);
        try {
            boolean z3 = false;
            try {
                if (matchToken(39)) {
                    if ("each".equals(this.f10207ts.getString())) {
                        i = this.f10207ts.tokenBeg - i3;
                        z = true;
                        int i5 = !mustMatchToken(88, "msg.no.paren.for") ? this.f10207ts.tokenBeg - i3 : -1;
                        forLoopInit = forLoopInit(peekToken());
                        if (!matchToken(52)) {
                            i2 = this.f10207ts.tokenBeg - i3;
                            astNode = expr();
                            z2 = false;
                            z3 = true;
                        } else if (this.compilerEnv.getLanguageVersion() >= 200 && matchToken(39) && "of".equals(this.f10207ts.getString())) {
                            i2 = this.f10207ts.tokenBeg - i3;
                            astNode = expr();
                            z2 = true;
                        } else {
                            mustMatchToken(83, "msg.no.semi.for");
                            if (peekToken() == 83) {
                                expr = new EmptyExpression(this.f10207ts.tokenBeg, 1);
                                expr.setLineno(this.f10207ts.lineno);
                            } else {
                                expr = expr();
                            }
                            AstNode astNode3 = expr;
                            mustMatchToken(83, "msg.no.semi.for.cond");
                            int i6 = this.f10207ts.tokenEnd;
                            if (peekToken() == 89) {
                                EmptyExpression emptyExpression = new EmptyExpression(i6, 1);
                                emptyExpression.setLineno(this.f10207ts.lineno);
                                astNode2 = emptyExpression;
                            } else {
                                astNode2 = expr();
                            }
                            astNode = astNode3;
                            i2 = -1;
                            z2 = false;
                        }
                        int i7 = !mustMatchToken(89, "msg.no.paren.for.ctrl") ? this.f10207ts.tokenBeg - i3 : -1;
                        if (!z3 && !z2) {
                            ForLoop forLoop = new ForLoop(i3);
                            forLoop.setInitializer(forLoopInit);
                            forLoop.setCondition(astNode);
                            forLoop.setIncrement(astNode2);
                            forInLoop = forLoop;
                            this.currentScope.replaceWith(forInLoop);
                            popScope();
                            enterLoop(forInLoop);
                            AstNode statement = statement();
                            forInLoop.setLength(getNodeEnd(statement) - i3);
                            forInLoop.setBody(statement);
                            forInLoop.setParens(i5, i7);
                            forInLoop.setLineno(i4);
                            return forInLoop;
                        }
                        ForInLoop forInLoop2 = new ForInLoop(i3);
                        if ((forLoopInit instanceof VariableDeclaration) && ((VariableDeclaration) forLoopInit).getVariables().size() > 1) {
                            reportError("msg.mult.index");
                        }
                        if (z2 && z) {
                            reportError("msg.invalid.for.each");
                        }
                        forInLoop2.setIterator(forLoopInit);
                        forInLoop2.setIteratedObject(astNode);
                        forInLoop2.setInPosition(i2);
                        forInLoop2.setIsForEach(z);
                        forInLoop2.setEachPosition(i);
                        forInLoop2.setIsForOf(z2);
                        forInLoop = forInLoop2;
                        this.currentScope.replaceWith(forInLoop);
                        popScope();
                        enterLoop(forInLoop);
                        AstNode statement2 = statement();
                        forInLoop.setLength(getNodeEnd(statement2) - i3);
                        forInLoop.setBody(statement2);
                        forInLoop.setParens(i5, i7);
                        forInLoop.setLineno(i4);
                        return forInLoop;
                    }
                    reportError("msg.no.paren.for");
                }
                if (!z3) {
                    ForLoop forLoop2 = new ForLoop(i3);
                    forLoop2.setInitializer(forLoopInit);
                    forLoop2.setCondition(astNode);
                    forLoop2.setIncrement(astNode2);
                    forInLoop = forLoop2;
                    this.currentScope.replaceWith(forInLoop);
                    popScope();
                    enterLoop(forInLoop);
                    AstNode statement22 = statement();
                    forInLoop.setLength(getNodeEnd(statement22) - i3);
                    forInLoop.setBody(statement22);
                    forInLoop.setParens(i5, i7);
                    forInLoop.setLineno(i4);
                    return forInLoop;
                }
                AstNode statement222 = statement();
                forInLoop.setLength(getNodeEnd(statement222) - i3);
                forInLoop.setBody(statement222);
                forInLoop.setParens(i5, i7);
                forInLoop.setLineno(i4);
                return forInLoop;
            } finally {
                exitLoop();
            }
            z = false;
            i = -1;
            if (!mustMatchToken(88, "msg.no.paren.for")) {
            }
            forLoopInit = forLoopInit(peekToken());
            if (!matchToken(52)) {
            }
            if (!mustMatchToken(89, "msg.no.paren.for.ctrl")) {
            }
            ForInLoop forInLoop22 = new ForInLoop(i3);
            if (forLoopInit instanceof VariableDeclaration) {
                reportError("msg.mult.index");
            }
            if (z2) {
                reportError("msg.invalid.for.each");
            }
            forInLoop22.setIterator(forLoopInit);
            forInLoop22.setIteratedObject(astNode);
            forInLoop22.setInPosition(i2);
            forInLoop22.setIsForEach(z);
            forInLoop22.setEachPosition(i);
            forInLoop22.setIsForOf(z2);
            forInLoop = forInLoop22;
            this.currentScope.replaceWith(forInLoop);
            popScope();
            enterLoop(forInLoop);
        } finally {
            if (this.currentScope == scope) {
                popScope();
            }
        }
    }

    private AstNode forLoopInit(int i) throws IOException {
        AstNode variables;
        try {
            this.inForInit = true;
            if (i == 83) {
                variables = new EmptyExpression(this.f10207ts.tokenBeg, 1);
                variables.setLineno(this.f10207ts.lineno);
            } else {
                if (i != 123 && i != 154) {
                    variables = expr();
                    markDestructuring(variables);
                }
                consumeToken();
                variables = variables(i, this.f10207ts.tokenBeg, false);
            }
            return variables;
        } finally {
            this.inForInit = false;
        }
    }

    private TryStatement tryStatement() throws IOException {
        int i;
        ArrayList arrayList;
        int i2;
        AstNode astNode;
        int i3;
        AstNode astNode2;
        if (this.currentToken != 82) {
            codeBug();
        }
        consumeToken();
        Comment andResetJsDoc = getAndResetJsDoc();
        int i4 = this.f10207ts.tokenBeg;
        int i5 = this.f10207ts.lineno;
        int i6 = 86;
        if (peekToken() != 86) {
            reportError("msg.no.brace.try");
        }
        AstNode statement = statement();
        int nodeEnd = getNodeEnd(statement);
        boolean z = false;
        int peekToken = peekToken();
        if (peekToken == 125) {
            arrayList = null;
            for (int i7 = 125; matchToken(i7); i7 = 125) {
                int i8 = this.f10207ts.lineno;
                if (z) {
                    reportError("msg.catch.unreachable");
                }
                int i9 = this.f10207ts.tokenBeg;
                int i10 = mustMatchToken(88, "msg.no.paren.catch") ? this.f10207ts.tokenBeg : -1;
                mustMatchToken(39, "msg.bad.catchcond");
                Name createNameNode = createNameNode();
                Comment andResetJsDoc2 = getAndResetJsDoc();
                if (andResetJsDoc2 != null) {
                    createNameNode.setJsDocNode(andResetJsDoc2);
                }
                String identifier = createNameNode.getIdentifier();
                if (this.inUseStrictDirective && ("eval".equals(identifier) || "arguments".equals(identifier))) {
                    reportError("msg.bad.id.strict", identifier);
                }
                if (matchToken(113)) {
                    i3 = this.f10207ts.tokenBeg;
                    astNode2 = expr();
                } else {
                    z = true;
                    i3 = -1;
                    astNode2 = null;
                }
                int i11 = mustMatchToken(89, "msg.bad.catchcond") ? this.f10207ts.tokenBeg : -1;
                mustMatchToken(i6, "msg.no.brace.catchblock");
                Block block = (Block) statements();
                int nodeEnd2 = getNodeEnd(block);
                CatchClause catchClause = new CatchClause(i9);
                catchClause.setVarName(createNameNode);
                catchClause.setCatchCondition(astNode2);
                catchClause.setBody(block);
                if (i3 != -1) {
                    catchClause.setIfPosition(i3 - i9);
                }
                catchClause.setParens(i10, i11);
                catchClause.setLineno(i8);
                nodeEnd = mustMatchToken(87, "msg.no.brace.after.body") ? this.f10207ts.tokenEnd : nodeEnd2;
                catchClause.setLength(nodeEnd - i9);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(catchClause);
                i6 = 86;
            }
            i = 126;
        } else {
            i = 126;
            if (peekToken != 126) {
                mustMatchToken(126, "msg.try.no.catchfinally");
            }
            arrayList = null;
        }
        if (matchToken(i)) {
            int i12 = this.f10207ts.tokenBeg;
            AstNode statement2 = statement();
            nodeEnd = getNodeEnd(statement2);
            astNode = statement2;
            i2 = i12;
        } else {
            i2 = -1;
            astNode = null;
        }
        TryStatement tryStatement = new TryStatement(i4, nodeEnd - i4);
        tryStatement.setTryBlock(statement);
        tryStatement.setCatchClauses(arrayList);
        tryStatement.setFinallyBlock(astNode);
        if (i2 != -1) {
            tryStatement.setFinallyPosition(i2 - i4);
        }
        tryStatement.setLineno(i5);
        if (andResetJsDoc != null) {
            tryStatement.setJsDocNode(andResetJsDoc);
        }
        return tryStatement;
    }

    private ThrowStatement throwStatement() throws IOException {
        if (this.currentToken != 50) {
            codeBug();
        }
        consumeToken();
        int i = this.f10207ts.tokenBeg;
        int i2 = this.f10207ts.lineno;
        if (peekTokenOrEOL() == 1) {
            reportError("msg.bad.throw.eol");
        }
        AstNode expr = expr();
        ThrowStatement throwStatement = new ThrowStatement(i, getNodeEnd(expr), expr);
        throwStatement.setLineno(i2);
        return throwStatement;
    }

    private LabeledStatement matchJumpLabelName() throws IOException {
        if (peekTokenOrEOL() == 39) {
            consumeToken();
            Map<String, LabeledStatement> map = this.labelSet;
            r1 = map != null ? map.get(this.f10207ts.getString()) : null;
            if (r1 == null) {
                reportError("msg.undef.label");
            }
        }
        return r1;
    }

    private BreakStatement breakStatement() throws IOException {
        int i;
        Name name;
        if (this.currentToken != 121) {
            codeBug();
        }
        consumeToken();
        int i2 = this.f10207ts.lineno;
        int i3 = this.f10207ts.tokenBeg;
        int i4 = this.f10207ts.tokenEnd;
        if (peekTokenOrEOL() == 39) {
            name = createNameNode();
            i = getNodeEnd(name);
        } else {
            i = i4;
            name = null;
        }
        LabeledStatement matchJumpLabelName = matchJumpLabelName();
        Jump firstLabel = matchJumpLabelName != null ? matchJumpLabelName.getFirstLabel() : null;
        if (firstLabel == null && name == null) {
            List<Jump> list = this.loopAndSwitchSet;
            if (list != null && list.size() != 0) {
                firstLabel = this.loopAndSwitchSet.get(r4.size() - 1);
            } else if (name == null) {
                reportError("msg.bad.break", i3, i - i3);
            }
        }
        BreakStatement breakStatement = new BreakStatement(i3, i - i3);
        breakStatement.setBreakLabel(name);
        if (firstLabel != null) {
            breakStatement.setBreakTarget(firstLabel);
        }
        breakStatement.setLineno(i2);
        return breakStatement;
    }

    private ContinueStatement continueStatement() throws IOException {
        int i;
        Name name;
        if (this.currentToken != 122) {
            codeBug();
        }
        consumeToken();
        int i2 = this.f10207ts.lineno;
        int i3 = this.f10207ts.tokenBeg;
        int i4 = this.f10207ts.tokenEnd;
        Loop loop = null;
        if (peekTokenOrEOL() == 39) {
            name = createNameNode();
            i = getNodeEnd(name);
        } else {
            i = i4;
            name = null;
        }
        LabeledStatement matchJumpLabelName = matchJumpLabelName();
        if (matchJumpLabelName == null && name == null) {
            List<Loop> list = this.loopSet;
            if (list == null || list.size() == 0) {
                reportError("msg.continue.outside");
            } else {
                loop = this.loopSet.get(r4.size() - 1);
            }
        } else {
            if (matchJumpLabelName == null || !(matchJumpLabelName.getStatement() instanceof Loop)) {
                reportError("msg.continue.nonloop", i3, i - i3);
            }
            if (matchJumpLabelName != null) {
                loop = (Loop) matchJumpLabelName.getStatement();
            }
        }
        ContinueStatement continueStatement = new ContinueStatement(i3, i - i3);
        if (loop != null) {
            continueStatement.setTarget(loop);
        }
        continueStatement.setLabel(name);
        continueStatement.setLineno(i2);
        return continueStatement;
    }

    private WithStatement withStatement() throws IOException {
        if (this.currentToken != 124) {
            codeBug();
        }
        consumeToken();
        Comment andResetJsDoc = getAndResetJsDoc();
        int i = this.f10207ts.lineno;
        int i2 = this.f10207ts.tokenBeg;
        int i3 = mustMatchToken(88, "msg.no.paren.with") ? this.f10207ts.tokenBeg : -1;
        AstNode expr = expr();
        int i4 = mustMatchToken(89, "msg.no.paren.after.with") ? this.f10207ts.tokenBeg : -1;
        AstNode statement = statement();
        WithStatement withStatement = new WithStatement(i2, getNodeEnd(statement) - i2);
        withStatement.setJsDocNode(andResetJsDoc);
        withStatement.setExpression(expr);
        withStatement.setStatement(statement);
        withStatement.setParens(i3, i4);
        withStatement.setLineno(i);
        return withStatement;
    }

    private AstNode letStatement() throws IOException {
        AstNode variables;
        if (this.currentToken != 154) {
            codeBug();
        }
        consumeToken();
        int i = this.f10207ts.lineno;
        int i2 = this.f10207ts.tokenBeg;
        if (peekToken() == 88) {
            variables = let(true, i2);
        } else {
            variables = variables(154, i2, true);
        }
        variables.setLineno(i);
        return variables;
    }

    private AstNode returnOrYield(int i, boolean z) throws IOException {
        AstNode yield;
        if (!insideFunction()) {
            reportError(i == 4 ? "msg.bad.return" : "msg.bad.yield");
        }
        consumeToken();
        int i2 = this.f10207ts.lineno;
        int i3 = this.f10207ts.tokenBeg;
        int i4 = this.f10207ts.tokenEnd;
        AstNode astNode = null;
        int peekTokenOrEOL = peekTokenOrEOL();
        if (peekTokenOrEOL != -1 && peekTokenOrEOL != 0 && peekTokenOrEOL != 1 && peekTokenOrEOL != 73 && peekTokenOrEOL != 83 && peekTokenOrEOL != 85 && peekTokenOrEOL != 87 && peekTokenOrEOL != 89) {
            astNode = expr();
            i4 = getNodeEnd(astNode);
        }
        int i5 = this.endFlags;
        if (i == 4) {
            this.endFlags = i5 | (astNode == null ? 2 : 4);
            int i6 = i4 - i3;
            yield = new ReturnStatement(i3, i6, astNode);
            if (nowAllSet(i5, this.endFlags, 6)) {
                addStrictWarning("msg.return.inconsistent", "", i3, i6);
            }
        } else {
            if (!insideFunction()) {
                reportError("msg.bad.yield");
            }
            this.endFlags |= 8;
            yield = new Yield(i3, i4 - i3, astNode);
            setRequiresActivation();
            setIsGenerator();
            if (!z) {
                yield = new ExpressionStatement(yield);
            }
        }
        if (insideFunction() && nowAllSet(i5, this.endFlags, 12)) {
            Name functionName = ((FunctionNode) this.currentScriptOrFn).getFunctionName();
            if (functionName == null || functionName.length() == 0) {
                addError("msg.anon.generator.returns", "");
            } else {
                addError("msg.generator.returns", functionName.getIdentifier());
            }
        }
        yield.setLineno(i2);
        return yield;
    }

    private AstNode block() throws IOException {
        if (this.currentToken != 86) {
            codeBug();
        }
        consumeToken();
        int i = this.f10207ts.tokenBeg;
        Scope scope = new Scope(i);
        scope.setLineno(this.f10207ts.lineno);
        pushScope(scope);
        try {
            statements(scope);
            mustMatchToken(87, "msg.no.brace.block");
            scope.setLength(this.f10207ts.tokenEnd - i);
            return scope;
        } finally {
            popScope();
        }
    }

    private AstNode defaultXmlNamespace() throws IOException {
        if (this.currentToken != 117) {
            codeBug();
        }
        consumeToken();
        mustHaveXML();
        setRequiresActivation();
        int i = this.f10207ts.lineno;
        int i2 = this.f10207ts.tokenBeg;
        if (!matchToken(39) || !"xml".equals(this.f10207ts.getString())) {
            reportError("msg.bad.namespace");
        }
        if (!matchToken(39) || !"namespace".equals(this.f10207ts.getString())) {
            reportError("msg.bad.namespace");
        }
        if (!matchToken(91)) {
            reportError("msg.bad.namespace");
        }
        AstNode expr = expr();
        UnaryExpression unaryExpression = new UnaryExpression(i2, getNodeEnd(expr) - i2);
        unaryExpression.setOperator(75);
        unaryExpression.setOperand(expr);
        unaryExpression.setLineno(i);
        return new ExpressionStatement((AstNode) unaryExpression, true);
    }

    private void recordLabel(Label label, LabeledStatement labeledStatement) throws IOException {
        if (peekToken() != 104) {
            codeBug();
        }
        consumeToken();
        String name = label.getName();
        Map<String, LabeledStatement> map = this.labelSet;
        if (map == null) {
            this.labelSet = new HashMap();
        } else {
            LabeledStatement labeledStatement2 = map.get(name);
            if (labeledStatement2 != null) {
                if (this.compilerEnv.isIdeMode()) {
                    Label labelByName = labeledStatement2.getLabelByName(name);
                    reportError("msg.dup.label", labelByName.getAbsolutePosition(), labelByName.getLength());
                }
                reportError("msg.dup.label", label.getPosition(), label.getLength());
            }
        }
        labeledStatement.addLabel(label);
        this.labelSet.put(name, labeledStatement);
    }

    private AstNode nameOrLabel() throws IOException {
        AstNode astNode;
        int nodeEnd;
        if (this.currentToken != 39) {
            throw codeBug();
        }
        int i = this.f10207ts.tokenBeg;
        this.currentFlaggedToken |= 131072;
        AstNode expr = expr();
        if (expr.getType() != 131) {
            ExpressionStatement expressionStatement = new ExpressionStatement(expr, !insideFunction());
            expressionStatement.lineno = expr.lineno;
            return expressionStatement;
        }
        LabeledStatement labeledStatement = new LabeledStatement(i);
        recordLabel((Label) expr, labeledStatement);
        labeledStatement.setLineno(this.f10207ts.lineno);
        while (true) {
            if (peekToken() != 39) {
                astNode = null;
                break;
            }
            this.currentFlaggedToken |= 131072;
            AstNode expr2 = expr();
            if (expr2.getType() != 131) {
                astNode = new ExpressionStatement(expr2, !insideFunction());
                autoInsertSemicolon(astNode);
                break;
            }
            recordLabel((Label) expr2, labeledStatement);
        }
        try {
            this.currentLabel = labeledStatement;
            if (astNode == null) {
                astNode = statementHelper();
            }
            if (astNode.getParent() == null) {
                nodeEnd = getNodeEnd(astNode) - i;
            } else {
                nodeEnd = getNodeEnd(astNode);
            }
            labeledStatement.setLength(nodeEnd);
            labeledStatement.setStatement(astNode);
            return labeledStatement;
        } finally {
            this.currentLabel = null;
            Iterator<Label> it = labeledStatement.getLabels().iterator();
            while (it.hasNext()) {
                this.labelSet.remove(it.next().getName());
            }
        }
    }

    private VariableDeclaration variables(int i, int i2, boolean z) throws IOException {
        AstNode destructuringPrimaryExpr;
        int i3;
        Name name;
        VariableDeclaration variableDeclaration = new VariableDeclaration(i2);
        variableDeclaration.setType(i);
        variableDeclaration.setLineno(this.f10207ts.lineno);
        Comment andResetJsDoc = getAndResetJsDoc();
        if (andResetJsDoc != null) {
            variableDeclaration.setJsDocNode(andResetJsDoc);
        }
        do {
            int peekToken = peekToken();
            int i4 = this.f10207ts.tokenBeg;
            int i5 = this.f10207ts.tokenEnd;
            AstNode astNode = null;
            if (peekToken == 84 || peekToken == 86) {
                destructuringPrimaryExpr = destructuringPrimaryExpr();
                int nodeEnd = getNodeEnd(destructuringPrimaryExpr);
                if (!(destructuringPrimaryExpr instanceof DestructuringForm)) {
                    reportError("msg.bad.assign.left", i4, nodeEnd - i4);
                }
                markDestructuring(destructuringPrimaryExpr);
                i3 = nodeEnd;
                name = null;
            } else {
                mustMatchToken(39, "msg.bad.var");
                Name createNameNode = createNameNode();
                createNameNode.setLineno(this.f10207ts.getLineno());
                if (this.inUseStrictDirective) {
                    String string = this.f10207ts.getString();
                    if ("eval".equals(string) || "arguments".equals(this.f10207ts.getString())) {
                        reportError("msg.bad.id.strict", string);
                    }
                }
                defineSymbol(i, this.f10207ts.getString(), this.inForInit);
                i3 = i5;
                name = createNameNode;
                destructuringPrimaryExpr = null;
            }
            int i6 = this.f10207ts.lineno;
            Comment andResetJsDoc2 = getAndResetJsDoc();
            if (matchToken(91)) {
                astNode = assignExpr();
                i3 = getNodeEnd(astNode);
            }
            VariableInitializer variableInitializer = new VariableInitializer(i4, i3 - i4);
            if (destructuringPrimaryExpr != null) {
                if (astNode == null && !this.inForInit) {
                    reportError("msg.destruct.assign.no.init");
                }
                variableInitializer.setTarget(destructuringPrimaryExpr);
            } else {
                variableInitializer.setTarget(name);
            }
            variableInitializer.setInitializer(astNode);
            variableInitializer.setType(i);
            variableInitializer.setJsDocNode(andResetJsDoc2);
            variableInitializer.setLineno(i6);
            variableDeclaration.addVariable(variableInitializer);
        } while (matchToken(90));
        variableDeclaration.setLength(i3 - i2);
        variableDeclaration.setIsStatement(z);
        return variableDeclaration;
    }

    private AstNode let(boolean z, int i) throws IOException {
        LetNode letNode = new LetNode(i);
        letNode.setLineno(this.f10207ts.lineno);
        if (mustMatchToken(88, "msg.no.paren.after.let")) {
            letNode.setLp(this.f10207ts.tokenBeg - i);
        }
        pushScope(letNode);
        try {
            letNode.setVariables(variables(154, this.f10207ts.tokenBeg, z));
            if (mustMatchToken(89, "msg.no.paren.let")) {
                letNode.setRp(this.f10207ts.tokenBeg - i);
            }
            if (z && peekToken() == 86) {
                consumeToken();
                int i2 = this.f10207ts.tokenBeg;
                AstNode statements = statements();
                mustMatchToken(87, "msg.no.curly.let");
                statements.setLength(this.f10207ts.tokenEnd - i2);
                letNode.setLength(this.f10207ts.tokenEnd - i);
                letNode.setBody(statements);
                letNode.setType(154);
            } else {
                AstNode expr = expr();
                letNode.setLength(getNodeEnd(expr) - i);
                letNode.setBody(expr);
                if (z) {
                    ExpressionStatement expressionStatement = new ExpressionStatement(letNode, !insideFunction());
                    expressionStatement.setLineno(letNode.getLineno());
                    return expressionStatement;
                }
            }
            return letNode;
        } finally {
            popScope();
        }
    }

    void defineSymbol(int i, String str) {
        defineSymbol(i, str, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineSymbol(int i, String str, boolean z) {
        if (str == null) {
            if (this.compilerEnv.isIdeMode()) {
                return;
            } else {
                codeBug();
            }
        }
        Scope definingScope = this.currentScope.getDefiningScope(str);
        org.mozilla.javascript.ast.Symbol symbol = definingScope != null ? definingScope.getSymbol(str) : null;
        int declType = symbol != null ? symbol.getDeclType() : -1;
        String str2 = "msg.var.redecl";
        if (symbol != null && (declType == 155 || i == 155 || (definingScope == this.currentScope && declType == 154))) {
            if (declType == 155) {
                str2 = "msg.const.redecl";
            } else if (declType == 154) {
                str2 = "msg.let.redecl";
            } else if (declType != 123) {
                str2 = declType == 110 ? "msg.fn.redecl" : "msg.parm.redecl";
            }
            addError(str2, str);
            return;
        }
        if (i == 88) {
            if (symbol != null) {
                addWarning("msg.dup.parms", str);
            }
            this.currentScriptOrFn.putSymbol(new org.mozilla.javascript.ast.Symbol(i, str));
            return;
        }
        if (i != 110 && i != 123) {
            if (i == 154) {
                if (!z && (this.currentScope.getType() == 113 || (this.currentScope instanceof Loop))) {
                    addError("msg.let.decl.not.in.block");
                    return;
                } else {
                    this.currentScope.putSymbol(new org.mozilla.javascript.ast.Symbol(i, str));
                    return;
                }
            }
            if (i != 155) {
                throw codeBug();
            }
        }
        if (symbol == null) {
            this.currentScriptOrFn.putSymbol(new org.mozilla.javascript.ast.Symbol(i, str));
        } else if (declType == 123) {
            addStrictWarning("msg.var.redecl", str);
        } else if (declType == 88) {
            addStrictWarning("msg.var.hides.arg", str);
        }
    }

    private AstNode expr() throws IOException {
        AstNode assignExpr = assignExpr();
        int position = assignExpr.getPosition();
        while (matchToken(90)) {
            int i = this.f10207ts.tokenBeg;
            if (this.compilerEnv.isStrictMode() && !assignExpr.hasSideEffects()) {
                addStrictWarning("msg.no.side.effects", "", position, nodeEnd(assignExpr) - position);
            }
            if (peekToken() == 73) {
                reportError("msg.yield.parenthesized");
            }
            assignExpr = new InfixExpression(90, assignExpr, assignExpr(), i);
        }
        return assignExpr;
    }

    private AstNode assignExpr() throws IOException {
        int peekToken = peekToken();
        boolean z = true;
        if (peekToken == 73) {
            return returnOrYield(peekToken, true);
        }
        AstNode condExpr = condExpr();
        int peekTokenOrEOL = peekTokenOrEOL();
        if (peekTokenOrEOL == 1) {
            peekTokenOrEOL = peekToken();
        } else {
            z = false;
        }
        if (91 > peekTokenOrEOL || peekTokenOrEOL > 102) {
            if (peekTokenOrEOL == 83) {
                if (this.currentJsDocComment == null) {
                    return condExpr;
                }
                condExpr.setJsDocNode(getAndResetJsDoc());
                return condExpr;
            }
            if (z || peekTokenOrEOL != 165) {
                return condExpr;
            }
            consumeToken();
            return arrowFunction(condExpr);
        }
        if (this.inDestructuringAssignment) {
            reportError("msg.destruct.default.vals");
        }
        consumeToken();
        Comment andResetJsDoc = getAndResetJsDoc();
        markDestructuring(condExpr);
        Assignment assignment = new Assignment(peekTokenOrEOL, condExpr, assignExpr(), this.f10207ts.tokenBeg);
        if (andResetJsDoc != null) {
            assignment.setJsDocNode(andResetJsDoc);
        }
        return assignment;
    }

    private AstNode condExpr() throws IOException {
        AstNode orExpr = orExpr();
        if (!matchToken(103)) {
            return orExpr;
        }
        int i = this.f10207ts.lineno;
        int i2 = this.f10207ts.tokenBeg;
        boolean z = this.inForInit;
        this.inForInit = false;
        try {
            AstNode assignExpr = assignExpr();
            this.inForInit = z;
            int i3 = mustMatchToken(104, "msg.no.colon.cond") ? this.f10207ts.tokenBeg : -1;
            AstNode assignExpr2 = assignExpr();
            int position = orExpr.getPosition();
            ConditionalExpression conditionalExpression = new ConditionalExpression(position, getNodeEnd(assignExpr2) - position);
            conditionalExpression.setLineno(i);
            conditionalExpression.setTestExpression(orExpr);
            conditionalExpression.setTrueExpression(assignExpr);
            conditionalExpression.setFalseExpression(assignExpr2);
            conditionalExpression.setQuestionMarkPosition(i2 - position);
            conditionalExpression.setColonPosition(i3 - position);
            return conditionalExpression;
        } catch (Throwable th) {
            this.inForInit = z;
            throw th;
        }
    }

    private AstNode orExpr() throws IOException {
        AstNode andExpr = andExpr();
        if (!matchToken(105)) {
            return andExpr;
        }
        return new InfixExpression(105, andExpr, orExpr(), this.f10207ts.tokenBeg);
    }

    private AstNode andExpr() throws IOException {
        AstNode bitOrExpr = bitOrExpr();
        if (!matchToken(106)) {
            return bitOrExpr;
        }
        return new InfixExpression(106, bitOrExpr, andExpr(), this.f10207ts.tokenBeg);
    }

    private AstNode bitOrExpr() throws IOException {
        AstNode bitXorExpr = bitXorExpr();
        while (matchToken(9)) {
            bitXorExpr = new InfixExpression(9, bitXorExpr, bitXorExpr(), this.f10207ts.tokenBeg);
        }
        return bitXorExpr;
    }

    private AstNode bitXorExpr() throws IOException {
        AstNode bitAndExpr = bitAndExpr();
        while (matchToken(10)) {
            bitAndExpr = new InfixExpression(10, bitAndExpr, bitAndExpr(), this.f10207ts.tokenBeg);
        }
        return bitAndExpr;
    }

    private AstNode bitAndExpr() throws IOException {
        AstNode eqExpr = eqExpr();
        while (matchToken(11)) {
            eqExpr = new InfixExpression(11, eqExpr, eqExpr(), this.f10207ts.tokenBeg);
        }
        return eqExpr;
    }

    private AstNode eqExpr() throws IOException {
        AstNode relExpr = relExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.f10207ts.tokenBeg;
            if (peekToken != 12 && peekToken != 13 && peekToken != 46 && peekToken != 47) {
                return relExpr;
            }
            consumeToken();
            if (this.compilerEnv.getLanguageVersion() == 120) {
                if (peekToken == 12) {
                    peekToken = 46;
                } else if (peekToken == 13) {
                    peekToken = 47;
                }
            }
            relExpr = new InfixExpression(peekToken, relExpr, relExpr(), i);
        }
    }

    private AstNode relExpr() throws IOException {
        AstNode shiftExpr = shiftExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.f10207ts.tokenBeg;
            if (peekToken == 52) {
                if (!this.inForInit) {
                    consumeToken();
                    shiftExpr = new InfixExpression(peekToken, shiftExpr, shiftExpr(), i);
                }
            } else {
                if (peekToken != 53) {
                    switch (peekToken) {
                    }
                } else {
                    continue;
                }
                consumeToken();
                shiftExpr = new InfixExpression(peekToken, shiftExpr, shiftExpr(), i);
            }
        }
        return shiftExpr;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private org.mozilla.javascript.ast.AstNode shiftExpr() throws java.io.IOException {
        /*
            r5 = this;
            org.mozilla.javascript.ast.AstNode r0 = r5.addExpr()
        L4:
            int r1 = r5.peekToken()
            org.mozilla.javascript.TokenStream r2 = r5.f10207ts
            int r2 = r2.tokenBeg
            switch(r1) {
                case 18: goto L10;
                case 19: goto L10;
                case 20: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L1e
        L10:
            r5.consumeToken()
            org.mozilla.javascript.ast.InfixExpression r3 = new org.mozilla.javascript.ast.InfixExpression
            org.mozilla.javascript.ast.AstNode r4 = r5.addExpr()
            r3.<init>(r1, r0, r4, r2)
            r0 = r3
            goto L4
        L1e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.shiftExpr():org.mozilla.javascript.ast.AstNode");
    }

    private AstNode addExpr() throws IOException {
        AstNode mulExpr = mulExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.f10207ts.tokenBeg;
            if (peekToken != 21 && peekToken != 22) {
                return mulExpr;
            }
            consumeToken();
            mulExpr = new InfixExpression(peekToken, mulExpr, mulExpr(), i);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private org.mozilla.javascript.ast.AstNode mulExpr() throws java.io.IOException {
        /*
            r5 = this;
            org.mozilla.javascript.ast.AstNode r0 = r5.unaryExpr()
        L4:
            int r1 = r5.peekToken()
            org.mozilla.javascript.TokenStream r2 = r5.f10207ts
            int r2 = r2.tokenBeg
            switch(r1) {
                case 23: goto L10;
                case 24: goto L10;
                case 25: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L1e
        L10:
            r5.consumeToken()
            org.mozilla.javascript.ast.InfixExpression r3 = new org.mozilla.javascript.ast.InfixExpression
            org.mozilla.javascript.ast.AstNode r4 = r5.unaryExpr()
            r3.<init>(r1, r0, r4, r2)
            r0 = r3
            goto L4
        L1e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.mulExpr():org.mozilla.javascript.ast.AstNode");
    }

    private AstNode unaryExpr() throws IOException {
        int peekToken = peekToken();
        int i = this.f10207ts.lineno;
        if (peekToken == -1) {
            consumeToken();
            return makeErrorNode();
        }
        if (peekToken != 14) {
            if (peekToken != 127) {
                if (peekToken == 21) {
                    consumeToken();
                    UnaryExpression unaryExpression = new UnaryExpression(28, this.f10207ts.tokenBeg, unaryExpr());
                    unaryExpression.setLineno(i);
                    return unaryExpression;
                }
                if (peekToken == 22) {
                    consumeToken();
                    UnaryExpression unaryExpression2 = new UnaryExpression(29, this.f10207ts.tokenBeg, unaryExpr());
                    unaryExpression2.setLineno(i);
                    return unaryExpression2;
                }
                if (peekToken != 26 && peekToken != 27) {
                    if (peekToken == 31) {
                        consumeToken();
                        UnaryExpression unaryExpression3 = new UnaryExpression(peekToken, this.f10207ts.tokenBeg, unaryExpr());
                        unaryExpression3.setLineno(i);
                        return unaryExpression3;
                    }
                    if (peekToken != 32) {
                        if (peekToken == 107 || peekToken == 108) {
                            consumeToken();
                            UnaryExpression unaryExpression4 = new UnaryExpression(peekToken, this.f10207ts.tokenBeg, memberExpr(true));
                            unaryExpression4.setLineno(i);
                            checkBadIncDec(unaryExpression4);
                            return unaryExpression4;
                        }
                    }
                }
            }
            consumeToken();
            UnaryExpression unaryExpression5 = new UnaryExpression(peekToken, this.f10207ts.tokenBeg, unaryExpr());
            unaryExpression5.setLineno(i);
            return unaryExpression5;
        }
        if (this.compilerEnv.isXmlAvailable()) {
            consumeToken();
            return memberExprTail(true, xmlInitializer());
        }
        AstNode memberExpr = memberExpr(true);
        int peekTokenOrEOL = peekTokenOrEOL();
        if (peekTokenOrEOL != 107 && peekTokenOrEOL != 108) {
            return memberExpr;
        }
        consumeToken();
        UnaryExpression unaryExpression6 = new UnaryExpression(peekTokenOrEOL, this.f10207ts.tokenBeg, memberExpr, true);
        unaryExpression6.setLineno(i);
        checkBadIncDec(unaryExpression6);
        return unaryExpression6;
    }

    private AstNode xmlInitializer() throws IOException {
        if (this.currentToken != 14) {
            codeBug();
        }
        int i = this.f10207ts.tokenBeg;
        int firstXMLToken = this.f10207ts.getFirstXMLToken();
        if (firstXMLToken != 146 && firstXMLToken != 149) {
            reportError("msg.syntax");
            return makeErrorNode();
        }
        XmlLiteral xmlLiteral = new XmlLiteral(i);
        xmlLiteral.setLineno(this.f10207ts.lineno);
        while (firstXMLToken == 146) {
            xmlLiteral.addFragment(new XmlString(this.f10207ts.tokenBeg, this.f10207ts.getString()));
            mustMatchToken(86, "msg.syntax");
            int i2 = this.f10207ts.tokenBeg;
            AstNode emptyExpression = peekToken() == 87 ? new EmptyExpression(i2, this.f10207ts.tokenEnd - i2) : expr();
            mustMatchToken(87, "msg.syntax");
            XmlExpression xmlExpression = new XmlExpression(i2, emptyExpression);
            xmlExpression.setIsXmlAttribute(this.f10207ts.isXMLAttribute());
            xmlExpression.setLength(this.f10207ts.tokenEnd - i2);
            xmlLiteral.addFragment(xmlExpression);
            firstXMLToken = this.f10207ts.getNextXMLToken();
        }
        if (firstXMLToken == 149) {
            xmlLiteral.addFragment(new XmlString(this.f10207ts.tokenBeg, this.f10207ts.getString()));
            return xmlLiteral;
        }
        reportError("msg.syntax");
        return makeErrorNode();
    }

    private List<AstNode> argumentList() throws IOException {
        if (matchToken(89)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = this.inForInit;
        this.inForInit = false;
        do {
            try {
                if (peekToken() == 73) {
                    reportError("msg.yield.parenthesized");
                }
                AstNode assignExpr = assignExpr();
                if (peekToken() == 120) {
                    try {
                        arrayList.add(generatorExpression(assignExpr, 0, true));
                    } catch (IOException unused) {
                    }
                } else {
                    arrayList.add(assignExpr);
                }
            } catch (Throwable th) {
                this.inForInit = z;
                throw th;
            }
        } while (matchToken(90));
        this.inForInit = z;
        mustMatchToken(89, "msg.no.paren.arg");
        return arrayList;
    }

    private AstNode memberExpr(boolean z) throws IOException {
        AstNode astNode;
        int peekToken = peekToken();
        int i = this.f10207ts.lineno;
        if (peekToken != 30) {
            astNode = primaryExpr();
        } else {
            consumeToken();
            int i2 = this.f10207ts.tokenBeg;
            NewExpression newExpression = new NewExpression(i2);
            AstNode memberExpr = memberExpr(false);
            int nodeEnd = getNodeEnd(memberExpr);
            newExpression.setTarget(memberExpr);
            if (matchToken(88)) {
                int i3 = this.f10207ts.tokenBeg;
                List<AstNode> argumentList = argumentList();
                if (argumentList != null && argumentList.size() > 65536) {
                    reportError("msg.too.many.constructor.args");
                }
                int i4 = this.f10207ts.tokenBeg;
                int i5 = this.f10207ts.tokenEnd;
                if (argumentList != null) {
                    newExpression.setArguments(argumentList);
                }
                newExpression.setParens(i3 - i2, i4 - i2);
                nodeEnd = i5;
            }
            if (matchToken(86)) {
                ObjectLiteral objectLiteral = objectLiteral();
                nodeEnd = getNodeEnd(objectLiteral);
                newExpression.setInitializer(objectLiteral);
            }
            newExpression.setLength(nodeEnd - i2);
            astNode = newExpression;
        }
        astNode.setLineno(i);
        return memberExprTail(z, astNode);
    }

    private AstNode memberExprTail(boolean z, AstNode astNode) throws IOException {
        AstNode astNode2;
        if (astNode == null) {
            codeBug();
        }
        int position = astNode.getPosition();
        while (true) {
            int peekToken = peekToken();
            int i = -1;
            if (peekToken == 84) {
                consumeToken();
                int i2 = this.f10207ts.tokenBeg;
                int i3 = this.f10207ts.lineno;
                AstNode expr = expr();
                int nodeEnd = getNodeEnd(expr);
                if (mustMatchToken(85, "msg.no.bracket.index")) {
                    i = this.f10207ts.tokenBeg;
                    nodeEnd = this.f10207ts.tokenEnd;
                }
                ElementGet elementGet = new ElementGet(position, nodeEnd - position);
                elementGet.setTarget(astNode);
                elementGet.setElement(expr);
                elementGet.setParens(i2, i);
                elementGet.setLineno(i3);
                astNode2 = elementGet;
            } else if (peekToken != 88) {
                if (peekToken == 109 || peekToken == 144) {
                    int i4 = this.f10207ts.lineno;
                    astNode = propertyAccess(peekToken, astNode);
                    astNode.setLineno(i4);
                } else {
                    if (peekToken != 147) {
                        break;
                    }
                    consumeToken();
                    int i5 = this.f10207ts.tokenBeg;
                    int i6 = this.f10207ts.lineno;
                    mustHaveXML();
                    setRequiresActivation();
                    AstNode expr2 = expr();
                    int nodeEnd2 = getNodeEnd(expr2);
                    if (mustMatchToken(89, "msg.no.paren")) {
                        i = this.f10207ts.tokenBeg;
                        nodeEnd2 = this.f10207ts.tokenEnd;
                    }
                    XmlDotQuery xmlDotQuery = new XmlDotQuery(position, nodeEnd2 - position);
                    xmlDotQuery.setLeft(astNode);
                    xmlDotQuery.setRight(expr2);
                    xmlDotQuery.setOperatorPosition(i5);
                    xmlDotQuery.setRp(i - position);
                    xmlDotQuery.setLineno(i6);
                    astNode2 = xmlDotQuery;
                }
            } else {
                if (!z) {
                    break;
                }
                int i7 = this.f10207ts.lineno;
                consumeToken();
                checkCallRequiresActivation(astNode);
                FunctionCall functionCall = new FunctionCall(position);
                functionCall.setTarget(astNode);
                functionCall.setLineno(i7);
                functionCall.setLp(this.f10207ts.tokenBeg - position);
                List<AstNode> argumentList = argumentList();
                if (argumentList != null && argumentList.size() > 65536) {
                    reportError("msg.too.many.function.args");
                }
                functionCall.setArguments(argumentList);
                functionCall.setRp(this.f10207ts.tokenBeg - position);
                functionCall.setLength(this.f10207ts.tokenEnd - position);
                astNode = functionCall;
            }
            astNode = astNode2;
        }
        return astNode;
    }

    private AstNode propertyAccess(int i, AstNode astNode) throws IOException {
        AstNode propertyName;
        String keywordToName;
        if (astNode == null) {
            codeBug();
        }
        int i2 = 0;
        int i3 = this.f10207ts.lineno;
        int i4 = this.f10207ts.tokenBeg;
        consumeToken();
        if (i == 144) {
            mustHaveXML();
            i2 = 4;
        }
        if (!this.compilerEnv.isXmlAvailable()) {
            if (nextToken() != 39 && (!this.compilerEnv.isReservedKeywordAsIdentifier() || !TokenStream.isKeyword(this.f10207ts.getString(), this.compilerEnv.getLanguageVersion(), this.inUseStrictDirective))) {
                reportError("msg.no.name.after.dot");
            }
            PropertyGet propertyGet = new PropertyGet(astNode, createNameNode(true, 33), i4);
            propertyGet.setLineno(i3);
            return propertyGet;
        }
        int nextToken = nextToken();
        if (nextToken == 23) {
            saveNameTokenData(this.f10207ts.tokenBeg, "*", this.f10207ts.lineno);
            propertyName = propertyName(-1, "*", i2);
        } else if (nextToken == 39) {
            propertyName = propertyName(-1, this.f10207ts.getString(), i2);
        } else if (nextToken == 50) {
            saveNameTokenData(this.f10207ts.tokenBeg, "throw", this.f10207ts.lineno);
            propertyName = propertyName(-1, "throw", i2);
        } else if (nextToken == 128) {
            String string = this.f10207ts.getString();
            saveNameTokenData(this.f10207ts.tokenBeg, string, this.f10207ts.lineno);
            propertyName = propertyName(-1, string, i2);
        } else if (nextToken == 148) {
            propertyName = attributeAccess();
        } else if (this.compilerEnv.isReservedKeywordAsIdentifier() && (keywordToName = Token.keywordToName(nextToken)) != null) {
            saveNameTokenData(this.f10207ts.tokenBeg, keywordToName, this.f10207ts.lineno);
            propertyName = propertyName(-1, keywordToName, i2);
        } else {
            reportError("msg.no.name.after.dot");
            return makeErrorNode();
        }
        boolean z = propertyName instanceof XmlRef;
        InfixExpression xmlMemberGet = z ? new XmlMemberGet() : new PropertyGet();
        if (z && i == 109) {
            xmlMemberGet.setType(109);
        }
        int position = astNode.getPosition();
        xmlMemberGet.setPosition(position);
        xmlMemberGet.setLength(getNodeEnd(propertyName) - position);
        xmlMemberGet.setOperatorPosition(i4 - position);
        xmlMemberGet.setLineno(astNode.getLineno());
        xmlMemberGet.setLeft(astNode);
        xmlMemberGet.setRight(propertyName);
        return xmlMemberGet;
    }

    private AstNode attributeAccess() throws IOException {
        int nextToken = nextToken();
        int i = this.f10207ts.tokenBeg;
        if (nextToken == 23) {
            saveNameTokenData(this.f10207ts.tokenBeg, "*", this.f10207ts.lineno);
            return propertyName(i, "*", 0);
        }
        if (nextToken == 39) {
            return propertyName(i, this.f10207ts.getString(), 0);
        }
        if (nextToken == 84) {
            return xmlElemRef(i, null, -1);
        }
        reportError("msg.no.name.after.xmlAttr");
        return makeErrorNode();
    }

    private AstNode propertyName(int i, String str, int i2) throws IOException {
        Name name;
        int i3;
        int i4 = i != -1 ? i : this.f10207ts.tokenBeg;
        int i5 = this.f10207ts.lineno;
        Name createNameNode = createNameNode(true, this.currentToken);
        if (matchToken(145)) {
            i3 = this.f10207ts.tokenBeg;
            int nextToken = nextToken();
            if (nextToken == 23) {
                saveNameTokenData(this.f10207ts.tokenBeg, "*", this.f10207ts.lineno);
                name = createNameNode(false, -1);
            } else {
                if (nextToken != 39) {
                    if (nextToken == 84) {
                        return xmlElemRef(i, createNameNode, i3);
                    }
                    reportError("msg.no.name.after.coloncolon");
                    return makeErrorNode();
                }
                name = createNameNode();
            }
        } else {
            name = createNameNode;
            createNameNode = null;
            i3 = -1;
        }
        if (createNameNode == null && i2 == 0 && i == -1) {
            return name;
        }
        XmlPropRef xmlPropRef = new XmlPropRef(i4, getNodeEnd(name) - i4);
        xmlPropRef.setAtPos(i);
        xmlPropRef.setNamespace(createNameNode);
        xmlPropRef.setColonPos(i3);
        xmlPropRef.setPropName(name);
        xmlPropRef.setLineno(i5);
        return xmlPropRef;
    }

    private XmlElemRef xmlElemRef(int i, Name name, int i2) throws IOException {
        int i3 = this.f10207ts.tokenBeg;
        int i4 = -1;
        int i5 = i != -1 ? i : i3;
        AstNode expr = expr();
        int nodeEnd = getNodeEnd(expr);
        if (mustMatchToken(85, "msg.no.bracket.index")) {
            i4 = this.f10207ts.tokenBeg;
            nodeEnd = this.f10207ts.tokenEnd;
        }
        XmlElemRef xmlElemRef = new XmlElemRef(i5, nodeEnd - i5);
        xmlElemRef.setNamespace(name);
        xmlElemRef.setColonPos(i2);
        xmlElemRef.setAtPos(i);
        xmlElemRef.setExpression(expr);
        xmlElemRef.setBrackets(i3, i4);
        return xmlElemRef;
    }

    private AstNode destructuringPrimaryExpr() throws IOException, ParserException {
        try {
            this.inDestructuringAssignment = true;
            return primaryExpr();
        } finally {
            this.inDestructuringAssignment = false;
        }
    }

    private AstNode primaryExpr() throws IOException {
        int peekFlaggedToken = peekFlaggedToken();
        int i = 65535 & peekFlaggedToken;
        if (i == -1) {
            consumeToken();
        } else {
            if (i != 0) {
                if (i != 24) {
                    if (i == 84) {
                        consumeToken();
                        return arrayLiteral();
                    }
                    if (i == 86) {
                        consumeToken();
                        return objectLiteral();
                    }
                    if (i == 88) {
                        consumeToken();
                        return parenExpr();
                    }
                    if (i != 101) {
                        if (i == 110) {
                            consumeToken();
                            return function(2);
                        }
                        if (i == 128) {
                            consumeToken();
                            reportError("msg.reserved.id", this.f10207ts.getString());
                        } else {
                            if (i == 148) {
                                consumeToken();
                                mustHaveXML();
                                return attributeAccess();
                            }
                            if (i == 154) {
                                consumeToken();
                                return let(false, this.f10207ts.tokenBeg);
                            }
                            switch (i) {
                                case 39:
                                    consumeToken();
                                    return name(peekFlaggedToken, i);
                                case 40:
                                    consumeToken();
                                    String string = this.f10207ts.getString();
                                    if (this.inUseStrictDirective && this.f10207ts.isNumberOldOctal()) {
                                        reportError("msg.no.old.octal.strict");
                                    }
                                    if (this.f10207ts.isNumberBinary()) {
                                        string = "0b" + string;
                                    }
                                    if (this.f10207ts.isNumberOldOctal()) {
                                        string = "0" + string;
                                    }
                                    if (this.f10207ts.isNumberOctal()) {
                                        string = "0o" + string;
                                    }
                                    if (this.f10207ts.isNumberHex()) {
                                        string = "0x" + string;
                                    }
                                    return new NumberLiteral(this.f10207ts.tokenBeg, string, this.f10207ts.getNumber());
                                case 41:
                                    consumeToken();
                                    return createStringLiteral();
                                case 42:
                                case 43:
                                case 44:
                                case 45:
                                    consumeToken();
                                    int i2 = this.f10207ts.tokenBeg;
                                    return new KeywordLiteral(i2, this.f10207ts.tokenEnd - i2, i);
                                default:
                                    consumeToken();
                                    reportError("msg.syntax");
                                    break;
                            }
                        }
                    }
                }
                consumeToken();
                this.f10207ts.readRegExp(i);
                int i3 = this.f10207ts.tokenBeg;
                RegExpLiteral regExpLiteral = new RegExpLiteral(i3, this.f10207ts.tokenEnd - i3);
                regExpLiteral.setValue(this.f10207ts.getString());
                regExpLiteral.setFlags(this.f10207ts.readAndClearRegExpFlags());
                return regExpLiteral;
            }
            consumeToken();
            reportError("msg.unexpected.eof");
        }
        consumeToken();
        return makeErrorNode();
    }

    private AstNode parenExpr() throws IOException {
        boolean z = this.inForInit;
        this.inForInit = false;
        try {
            Comment andResetJsDoc = getAndResetJsDoc();
            int i = this.f10207ts.lineno;
            int i2 = this.f10207ts.tokenBeg;
            AstNode emptyExpression = peekToken() == 89 ? new EmptyExpression(i2) : expr();
            if (peekToken() == 120) {
                return generatorExpression(emptyExpression, i2);
            }
            ParenthesizedExpression parenthesizedExpression = new ParenthesizedExpression(emptyExpression);
            if (andResetJsDoc == null) {
                andResetJsDoc = getAndResetJsDoc();
            }
            if (andResetJsDoc != null) {
                parenthesizedExpression.setJsDocNode(andResetJsDoc);
            }
            mustMatchToken(89, "msg.no.paren");
            if (emptyExpression.getType() == 129 && peekToken() != 165) {
                reportError("msg.syntax");
                return makeErrorNode();
            }
            parenthesizedExpression.setLength(this.f10207ts.tokenEnd - parenthesizedExpression.getPosition());
            parenthesizedExpression.setLineno(i);
            return parenthesizedExpression;
        } finally {
            this.inForInit = z;
        }
    }

    private AstNode name(int i, int i2) throws IOException {
        String string = this.f10207ts.getString();
        int i3 = this.f10207ts.tokenBeg;
        int i4 = this.f10207ts.lineno;
        if ((i & 131072) != 0 && peekToken() == 104) {
            Label label = new Label(i3, this.f10207ts.tokenEnd - i3);
            label.setName(string);
            label.setLineno(this.f10207ts.lineno);
            return label;
        }
        saveNameTokenData(i3, string, i4);
        if (this.compilerEnv.isXmlAvailable()) {
            return propertyName(-1, string, 0);
        }
        return createNameNode(true, 39);
    }

    private AstNode arrayLiteral() throws IOException {
        if (this.currentToken != 84) {
            codeBug();
        }
        int i = this.f10207ts.tokenBeg;
        int i2 = this.f10207ts.tokenEnd;
        ArrayList arrayList = new ArrayList();
        ArrayLiteral arrayLiteral = new ArrayLiteral(i);
        int i3 = 0;
        int i4 = -1;
        loop0: while (true) {
            int i5 = 1;
            while (true) {
                int peekToken = peekToken();
                if (peekToken == 90) {
                    consumeToken();
                    i4 = this.f10207ts.tokenEnd;
                    if (i5 == 0) {
                        break;
                    }
                    arrayList.add(new EmptyExpression(this.f10207ts.tokenBeg, 1));
                    i3++;
                } else if (peekToken == 85) {
                    consumeToken();
                    i2 = this.f10207ts.tokenEnd;
                    arrayLiteral.setDestructuringLength(arrayList.size() + i5);
                    arrayLiteral.setSkipCount(i3);
                    if (i4 != -1) {
                        warnTrailingComma(i, arrayList, i4);
                    }
                } else {
                    if (peekToken == 120 && i5 == 0 && arrayList.size() == 1) {
                        return arrayComprehension((AstNode) arrayList.get(0), i);
                    }
                    if (peekToken == 0) {
                        reportError("msg.no.bracket.arg");
                        break loop0;
                    }
                    if (i5 == 0) {
                        reportError("msg.no.bracket.arg");
                    }
                    arrayList.add(assignExpr());
                    i5 = 0;
                    i4 = -1;
                }
            }
        }
        Iterator<?> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayLiteral.addElement((AstNode) it.next());
        }
        arrayLiteral.setLength(i2 - i);
        return arrayLiteral;
    }

    private AstNode arrayComprehension(AstNode astNode, int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (peekToken() == 120) {
            arrayList.add(arrayComprehensionLoop());
        }
        int i2 = -1;
        ConditionData conditionData = null;
        if (peekToken() == 113) {
            consumeToken();
            i2 = this.f10207ts.tokenBeg - i;
            conditionData = condition();
        }
        mustMatchToken(85, "msg.no.bracket.arg");
        ArrayComprehension arrayComprehension = new ArrayComprehension(i, this.f10207ts.tokenEnd - i);
        arrayComprehension.setResult(astNode);
        arrayComprehension.setLoops(arrayList);
        if (conditionData != null) {
            arrayComprehension.setIfPosition(i2);
            arrayComprehension.setFilter(conditionData.condition);
            arrayComprehension.setFilterLp(conditionData.f10208lp - i);
            arrayComprehension.setFilterRp(conditionData.f10209rp - i);
        }
        return arrayComprehension;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0042 A[Catch: all -> 0x00ee, TryCatch #0 {all -> 0x00ee, blocks: (B:6:0x0019, B:9:0x0022, B:11:0x0030, B:12:0x003a, B:14:0x0042, B:15:0x0049, B:21:0x0058, B:22:0x006d, B:24:0x0074, B:25:0x007f, B:30:0x00ad, B:32:0x00b4, B:34:0x00c2, B:35:0x00c9, B:38:0x00e1, B:45:0x008b, B:46:0x0091, B:49:0x00a1, B:50:0x00a6, B:51:0x005e, B:52:0x0066, B:54:0x0036), top: B:5:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0074 A[Catch: all -> 0x00ee, TryCatch #0 {all -> 0x00ee, blocks: (B:6:0x0019, B:9:0x0022, B:11:0x0030, B:12:0x003a, B:14:0x0042, B:15:0x0049, B:21:0x0058, B:22:0x006d, B:24:0x0074, B:25:0x007f, B:30:0x00ad, B:32:0x00b4, B:34:0x00c2, B:35:0x00c9, B:38:0x00e1, B:45:0x008b, B:46:0x0091, B:49:0x00a1, B:50:0x00a6, B:51:0x005e, B:52:0x0066, B:54:0x0036), top: B:5:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c2 A[Catch: all -> 0x00ee, TryCatch #0 {all -> 0x00ee, blocks: (B:6:0x0019, B:9:0x0022, B:11:0x0030, B:12:0x003a, B:14:0x0042, B:15:0x0049, B:21:0x0058, B:22:0x006d, B:24:0x0074, B:25:0x007f, B:30:0x00ad, B:32:0x00b4, B:34:0x00c2, B:35:0x00c9, B:38:0x00e1, B:45:0x008b, B:46:0x0091, B:49:0x00a1, B:50:0x00a6, B:51:0x005e, B:52:0x0066, B:54:0x0036), top: B:5:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0091 A[Catch: all -> 0x00ee, TryCatch #0 {all -> 0x00ee, blocks: (B:6:0x0019, B:9:0x0022, B:11:0x0030, B:12:0x003a, B:14:0x0042, B:15:0x0049, B:21:0x0058, B:22:0x006d, B:24:0x0074, B:25:0x007f, B:30:0x00ad, B:32:0x00b4, B:34:0x00c2, B:35:0x00c9, B:38:0x00e1, B:45:0x008b, B:46:0x0091, B:49:0x00a1, B:50:0x00a6, B:51:0x005e, B:52:0x0066, B:54:0x0036), top: B:5:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0066 A[Catch: all -> 0x00ee, TryCatch #0 {all -> 0x00ee, blocks: (B:6:0x0019, B:9:0x0022, B:11:0x0030, B:12:0x003a, B:14:0x0042, B:15:0x0049, B:21:0x0058, B:22:0x006d, B:24:0x0074, B:25:0x007f, B:30:0x00ad, B:32:0x00b4, B:34:0x00c2, B:35:0x00c9, B:38:0x00e1, B:45:0x008b, B:46:0x0091, B:49:0x00a1, B:50:0x00a6, B:51:0x005e, B:52:0x0066, B:54:0x0036), top: B:5:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ArrayComprehensionLoop arrayComprehensionLoop() throws IOException {
        int i;
        AstNode astNode;
        int peekToken;
        int nextToken;
        int i2;
        boolean z;
        if (nextToken() != 120) {
            codeBug();
        }
        int i3 = this.f10207ts.tokenBeg;
        ArrayComprehensionLoop arrayComprehensionLoop = new ArrayComprehensionLoop(i3);
        pushScope(arrayComprehensionLoop);
        try {
            if (matchToken(39)) {
                if (this.f10207ts.getString().equals("each")) {
                    i = this.f10207ts.tokenBeg - i3;
                    int i4 = !mustMatchToken(88, "msg.no.paren.for") ? this.f10207ts.tokenBeg - i3 : -1;
                    astNode = null;
                    peekToken = peekToken();
                    if (peekToken != 39) {
                        consumeToken();
                        astNode = createNameNode();
                    } else if (peekToken == 84 || peekToken == 86) {
                        astNode = destructuringPrimaryExpr();
                        markDestructuring(astNode);
                    } else {
                        reportError("msg.bad.var");
                    }
                    boolean z2 = true;
                    if (astNode.getType() == 39) {
                        defineSymbol(154, this.f10207ts.getString(), true);
                    }
                    nextToken = nextToken();
                    if (nextToken == 39) {
                        if (nextToken == 52) {
                            i2 = this.f10207ts.tokenBeg - i3;
                            z = false;
                            AstNode expr = expr();
                            int i5 = !mustMatchToken(89, "msg.no.paren.for.ctrl") ? this.f10207ts.tokenBeg - i3 : -1;
                            arrayComprehensionLoop.setLength(this.f10207ts.tokenEnd - i3);
                            arrayComprehensionLoop.setIterator(astNode);
                            arrayComprehensionLoop.setIteratedObject(expr);
                            arrayComprehensionLoop.setInPosition(i2);
                            arrayComprehensionLoop.setEachPosition(i);
                            if (i != -1) {
                                z2 = false;
                            }
                            arrayComprehensionLoop.setIsForEach(z2);
                            arrayComprehensionLoop.setParens(i4, i5);
                            arrayComprehensionLoop.setIsForOf(z);
                            return arrayComprehensionLoop;
                        }
                    } else if ("of".equals(this.f10207ts.getString())) {
                        if (i != -1) {
                            reportError("msg.invalid.for.each");
                        }
                        i2 = this.f10207ts.tokenBeg - i3;
                        z = true;
                        AstNode expr2 = expr();
                        if (!mustMatchToken(89, "msg.no.paren.for.ctrl")) {
                        }
                        arrayComprehensionLoop.setLength(this.f10207ts.tokenEnd - i3);
                        arrayComprehensionLoop.setIterator(astNode);
                        arrayComprehensionLoop.setIteratedObject(expr2);
                        arrayComprehensionLoop.setInPosition(i2);
                        arrayComprehensionLoop.setEachPosition(i);
                        if (i != -1) {
                        }
                        arrayComprehensionLoop.setIsForEach(z2);
                        arrayComprehensionLoop.setParens(i4, i5);
                        arrayComprehensionLoop.setIsForOf(z);
                        return arrayComprehensionLoop;
                    }
                    reportError("msg.in.after.for.name");
                    i2 = -1;
                    z = false;
                    AstNode expr22 = expr();
                    if (!mustMatchToken(89, "msg.no.paren.for.ctrl")) {
                    }
                    arrayComprehensionLoop.setLength(this.f10207ts.tokenEnd - i3);
                    arrayComprehensionLoop.setIterator(astNode);
                    arrayComprehensionLoop.setIteratedObject(expr22);
                    arrayComprehensionLoop.setInPosition(i2);
                    arrayComprehensionLoop.setEachPosition(i);
                    if (i != -1) {
                    }
                    arrayComprehensionLoop.setIsForEach(z2);
                    arrayComprehensionLoop.setParens(i4, i5);
                    arrayComprehensionLoop.setIsForOf(z);
                    return arrayComprehensionLoop;
                }
                reportError("msg.no.paren.for");
            }
            i = -1;
            if (!mustMatchToken(88, "msg.no.paren.for")) {
            }
            astNode = null;
            peekToken = peekToken();
            if (peekToken != 39) {
            }
            boolean z22 = true;
            if (astNode.getType() == 39) {
            }
            nextToken = nextToken();
            if (nextToken == 39) {
            }
            reportError("msg.in.after.for.name");
            i2 = -1;
            z = false;
            AstNode expr222 = expr();
            if (!mustMatchToken(89, "msg.no.paren.for.ctrl")) {
            }
            arrayComprehensionLoop.setLength(this.f10207ts.tokenEnd - i3);
            arrayComprehensionLoop.setIterator(astNode);
            arrayComprehensionLoop.setIteratedObject(expr222);
            arrayComprehensionLoop.setInPosition(i2);
            arrayComprehensionLoop.setEachPosition(i);
            if (i != -1) {
            }
            arrayComprehensionLoop.setIsForEach(z22);
            arrayComprehensionLoop.setParens(i4, i5);
            arrayComprehensionLoop.setIsForOf(z);
            return arrayComprehensionLoop;
        } finally {
            popScope();
        }
    }

    private AstNode generatorExpression(AstNode astNode, int i) throws IOException {
        return generatorExpression(astNode, i, false);
    }

    private AstNode generatorExpression(AstNode astNode, int i, boolean z) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (peekToken() == 120) {
            arrayList.add(generatorExpressionLoop());
        }
        int i2 = -1;
        ConditionData conditionData = null;
        if (peekToken() == 113) {
            consumeToken();
            i2 = this.f10207ts.tokenBeg - i;
            conditionData = condition();
        }
        if (!z) {
            mustMatchToken(89, "msg.no.paren.let");
        }
        GeneratorExpression generatorExpression = new GeneratorExpression(i, this.f10207ts.tokenEnd - i);
        generatorExpression.setResult(astNode);
        generatorExpression.setLoops(arrayList);
        if (conditionData != null) {
            generatorExpression.setIfPosition(i2);
            generatorExpression.setFilter(conditionData.condition);
            generatorExpression.setFilterLp(conditionData.f10208lp - i);
            generatorExpression.setFilterRp(conditionData.f10209rp - i);
        }
        return generatorExpression;
    }

    private GeneratorExpressionLoop generatorExpressionLoop() throws IOException {
        if (nextToken() != 120) {
            codeBug();
        }
        int i = this.f10207ts.tokenBeg;
        GeneratorExpressionLoop generatorExpressionLoop = new GeneratorExpressionLoop(i);
        pushScope(generatorExpressionLoop);
        try {
            int i2 = mustMatchToken(88, "msg.no.paren.for") ? this.f10207ts.tokenBeg - i : -1;
            AstNode astNode = null;
            int peekToken = peekToken();
            if (peekToken == 39) {
                consumeToken();
                astNode = createNameNode();
            } else if (peekToken == 84 || peekToken == 86) {
                astNode = destructuringPrimaryExpr();
                markDestructuring(astNode);
            } else {
                reportError("msg.bad.var");
            }
            if (astNode.getType() == 39) {
                defineSymbol(154, this.f10207ts.getString(), true);
            }
            int i3 = mustMatchToken(52, "msg.in.after.for.name") ? this.f10207ts.tokenBeg - i : -1;
            AstNode expr = expr();
            int i4 = mustMatchToken(89, "msg.no.paren.for.ctrl") ? this.f10207ts.tokenBeg - i : -1;
            generatorExpressionLoop.setLength(this.f10207ts.tokenEnd - i);
            generatorExpressionLoop.setIterator(astNode);
            generatorExpressionLoop.setIteratedObject(expr);
            generatorExpressionLoop.setInPosition(i3);
            generatorExpressionLoop.setParens(i2, i4);
            return generatorExpressionLoop;
        } finally {
            popScope();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00cf, code lost:
    
        if (r8 != 8) goto L70;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x010c A[LOOP:0: B:5:0x0026->B:38:0x010c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0113 A[EDGE_INSN: B:39:0x0113->B:40:0x0113 BREAK  A[LOOP:0: B:5:0x0026->B:38:0x010c], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ObjectLiteral objectLiteral() throws IOException {
        HashSet hashSet;
        HashSet hashSet2;
        String string;
        int i;
        int i2;
        int i3 = this.f10207ts.tokenBeg;
        int i4 = this.f10207ts.lineno;
        ArrayList arrayList = new ArrayList();
        if (this.inUseStrictDirective) {
            hashSet = new HashSet();
            hashSet2 = new HashSet();
        } else {
            hashSet = null;
            hashSet2 = null;
        }
        Comment andResetJsDoc = getAndResetJsDoc();
        int i5 = -1;
        int i6 = -1;
        while (true) {
            int peekToken = peekToken();
            Comment andResetJsDoc2 = getAndResetJsDoc();
            if (peekToken != 87) {
                AstNode objliteralProperty = objliteralProperty();
                if (objliteralProperty == null) {
                    reportError("msg.bad.prop");
                    string = null;
                } else {
                    string = this.f10207ts.getString();
                    int i7 = this.f10207ts.tokenBeg;
                    consumeToken();
                    int peekToken2 = peekToken();
                    if (peekToken2 == 90 || peekToken2 == 104 || peekToken2 == 87) {
                        objliteralProperty.setJsDocNode(andResetJsDoc2);
                        arrayList.add(plainProperty(objliteralProperty, peekToken));
                    } else {
                        if (peekToken2 == 88) {
                            i = 8;
                        } else {
                            if (objliteralProperty.getType() == 39) {
                                if (TmpConstant.PROPERTY_IDENTIFIER_GET.equals(string)) {
                                    i = 2;
                                } else if (TmpConstant.PROPERTY_IDENTIFIER_SET.equals(string)) {
                                    i = 4;
                                }
                            }
                            i = 1;
                        }
                        if (i == 2 || i == 4) {
                            objliteralProperty = objliteralProperty();
                            if (objliteralProperty == null) {
                                reportError("msg.bad.prop");
                            }
                            consumeToken();
                        }
                        if (objliteralProperty == null) {
                            i2 = i;
                            string = null;
                        } else {
                            String string2 = this.f10207ts.getString();
                            ObjectProperty methodDefinition = methodDefinition(i7, objliteralProperty, i);
                            objliteralProperty.setJsDocNode(andResetJsDoc2);
                            arrayList.add(methodDefinition);
                            i2 = i;
                            string = string2;
                        }
                        if (this.inUseStrictDirective && string != null) {
                            if (i2 != 1) {
                                if (i2 == 2) {
                                    if (hashSet.contains(string)) {
                                        addError("msg.dup.obj.lit.prop.strict", string);
                                    }
                                    hashSet.add(string);
                                } else if (i2 == 4) {
                                    if (hashSet2.contains(string)) {
                                        addError("msg.dup.obj.lit.prop.strict", string);
                                    }
                                    hashSet2.add(string);
                                }
                            }
                            if (!hashSet.contains(string) || hashSet2.contains(string)) {
                                addError("msg.dup.obj.lit.prop.strict", string);
                            }
                            hashSet.add(string);
                            hashSet2.add(string);
                        }
                        getAndResetJsDoc();
                        if (matchToken(90)) {
                            break;
                        }
                        i6 = this.f10207ts.tokenEnd;
                        i5 = -1;
                    }
                }
                i2 = 1;
                if (this.inUseStrictDirective) {
                    if (i2 != 1) {
                    }
                    if (!hashSet.contains(string)) {
                    }
                    addError("msg.dup.obj.lit.prop.strict", string);
                    hashSet.add(string);
                    hashSet2.add(string);
                }
                getAndResetJsDoc();
                if (matchToken(90)) {
                }
            } else if (i6 != i5) {
                warnTrailingComma(i3, arrayList, i6);
            }
        }
        mustMatchToken(87, "msg.no.brace.prop");
        ObjectLiteral objectLiteral = new ObjectLiteral(i3, this.f10207ts.tokenEnd - i3);
        if (andResetJsDoc != null) {
            objectLiteral.setJsDocNode(andResetJsDoc);
        }
        objectLiteral.setElements(arrayList);
        objectLiteral.setLineno(i4);
        return objectLiteral;
    }

    private AstNode objliteralProperty() throws IOException {
        switch (peekToken()) {
            case 39:
                return createNameNode();
            case 40:
                return new NumberLiteral(this.f10207ts.tokenBeg, this.f10207ts.getString(), this.f10207ts.getNumber());
            case 41:
                return createStringLiteral();
            default:
                if (this.compilerEnv.isReservedKeywordAsIdentifier() && TokenStream.isKeyword(this.f10207ts.getString(), this.compilerEnv.getLanguageVersion(), this.inUseStrictDirective)) {
                    return createNameNode();
                }
                return null;
        }
    }

    private ObjectProperty plainProperty(AstNode astNode, int i) throws IOException {
        int peekToken = peekToken();
        if ((peekToken == 90 || peekToken == 87) && i == 39 && this.compilerEnv.getLanguageVersion() >= 180) {
            if (!this.inDestructuringAssignment) {
                reportError("msg.bad.object.init");
            }
            Name name = new Name(astNode.getPosition(), astNode.getString());
            ObjectProperty objectProperty = new ObjectProperty();
            objectProperty.putProp(26, Boolean.TRUE);
            objectProperty.setLeftAndRight(astNode, name);
            return objectProperty;
        }
        mustMatchToken(104, "msg.no.colon.prop");
        ObjectProperty objectProperty2 = new ObjectProperty();
        objectProperty2.setOperatorPosition(this.f10207ts.tokenBeg);
        objectProperty2.setLeftAndRight(astNode, assignExpr());
        return objectProperty2;
    }

    private ObjectProperty methodDefinition(int i, AstNode astNode, int i2) throws IOException {
        FunctionNode function = function(2);
        Name functionName = function.getFunctionName();
        if (functionName != null && functionName.length() != 0) {
            reportError("msg.bad.prop");
        }
        ObjectProperty objectProperty = new ObjectProperty(i);
        if (i2 == 2) {
            objectProperty.setIsGetterMethod();
            function.setFunctionIsGetterMethod();
        } else if (i2 == 4) {
            objectProperty.setIsSetterMethod();
            function.setFunctionIsSetterMethod();
        } else if (i2 == 8) {
            objectProperty.setIsNormalMethod();
            function.setFunctionIsNormalMethod();
        }
        int nodeEnd = getNodeEnd(function);
        objectProperty.setLeft(astNode);
        objectProperty.setRight(function);
        objectProperty.setLength(nodeEnd - i);
        return objectProperty;
    }

    private Name createNameNode() {
        return createNameNode(false, 39);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Name createNameNode(boolean z, int i) {
        int i2 = this.f10207ts.tokenBeg;
        String string = this.f10207ts.getString();
        int i3 = this.f10207ts.lineno;
        String str = "";
        if (!"".equals(this.prevNameTokenString)) {
            i2 = this.prevNameTokenStart;
            string = this.prevNameTokenString;
            i3 = this.prevNameTokenLineno;
            this.prevNameTokenStart = 0;
            this.prevNameTokenString = "";
            this.prevNameTokenLineno = 0;
        }
        if (string == null) {
            if (!this.compilerEnv.isIdeMode()) {
                codeBug();
            }
            Name name = new Name(i2, str);
            name.setLineno(i3);
            if (z) {
                checkActivationName(str, i);
            }
            return name;
        }
        str = string;
        Name name2 = new Name(i2, str);
        name2.setLineno(i3);
        if (z) {
        }
        return name2;
    }

    private StringLiteral createStringLiteral() {
        int i = this.f10207ts.tokenBeg;
        StringLiteral stringLiteral = new StringLiteral(i, this.f10207ts.tokenEnd - i);
        stringLiteral.setLineno(this.f10207ts.lineno);
        stringLiteral.setValue(this.f10207ts.getString());
        stringLiteral.setQuoteCharacter(this.f10207ts.getQuoteChar());
        return stringLiteral;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkActivationName(String str, int i) {
        if (insideFunction()) {
            boolean z = false;
            if (("arguments".equals(str) && ((FunctionNode) this.currentScriptOrFn).getFunctionType() != 4) || ((this.compilerEnv.getActivationNames() != null && this.compilerEnv.getActivationNames().contains(str)) || (org.simpleframework.xml.strategy.Name.LENGTH.equals(str) && i == 33 && this.compilerEnv.getLanguageVersion() == 120))) {
                z = true;
            }
            if (z) {
                setRequiresActivation();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setRequiresActivation() {
        if (insideFunction()) {
            ((FunctionNode) this.currentScriptOrFn).setRequiresActivation();
        }
    }

    private void checkCallRequiresActivation(AstNode astNode) {
        if ((astNode.getType() == 39 && "eval".equals(((Name) astNode).getIdentifier())) || (astNode.getType() == 33 && "eval".equals(((PropertyGet) astNode).getProperty().getIdentifier()))) {
            setRequiresActivation();
        }
    }

    protected void setIsGenerator() {
        if (insideFunction()) {
            ((FunctionNode) this.currentScriptOrFn).setIsGenerator();
        }
    }

    private void checkBadIncDec(UnaryExpression unaryExpression) {
        int type = removeParens(unaryExpression.getOperand()).getType();
        if (type == 39 || type == 33 || type == 36 || type == 68 || type == 38) {
            return;
        }
        reportError(unaryExpression.getType() == 107 ? "msg.bad.incr" : "msg.bad.decr");
    }

    private ErrorNode makeErrorNode() {
        ErrorNode errorNode = new ErrorNode(this.f10207ts.tokenBeg, this.f10207ts.tokenEnd - this.f10207ts.tokenBeg);
        errorNode.setLineno(this.f10207ts.lineno);
        return errorNode;
    }

    private int nodeEnd(AstNode astNode) {
        return astNode.getPosition() + astNode.getLength();
    }

    private void saveNameTokenData(int i, String str, int i2) {
        this.prevNameTokenStart = i;
        this.prevNameTokenString = str;
        this.prevNameTokenLineno = i2;
    }

    private int lineBeginningFor(int i) {
        char[] cArr = this.sourceChars;
        if (cArr == null) {
            return -1;
        }
        if (i <= 0) {
            return 0;
        }
        if (i >= cArr.length) {
            i = cArr.length - 1;
        }
        do {
            i--;
            if (i < 0) {
                return 0;
            }
        } while (!ScriptRuntime.isJSLineTerminator(cArr[i]));
        return i + 1;
    }

    private void warnMissingSemi(int i, int i2) {
        if (this.compilerEnv.isStrictMode()) {
            int[] iArr = new int[2];
            String line = this.f10207ts.getLine(i2, iArr);
            if (this.compilerEnv.isIdeMode()) {
                i = Math.max(i, i2 - iArr[1]);
            }
            int i3 = i;
            if (line != null) {
                addStrictWarning("msg.missing.semi", "", i3, i2 - i3, iArr[0], line, iArr[1]);
            } else {
                addStrictWarning("msg.missing.semi", "", i3, i2 - i3);
            }
        }
    }

    private void warnTrailingComma(int i, List<?> list, int i2) {
        if (this.compilerEnv.getWarnTrailingComma()) {
            if (!list.isEmpty()) {
                i = ((AstNode) list.get(0)).getPosition();
            }
            int max = Math.max(i, lineBeginningFor(i2));
            addWarning("msg.extra.trailing.comma", max, i2 - max);
        }
    }

    private String readFully(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            char[] cArr = new char[1024];
            StringBuilder sb = new StringBuilder(1024);
            while (true) {
                int read = bufferedReader.read(cArr, 0, 1024);
                if (read != -1) {
                    sb.append(cArr, 0, read);
                } else {
                    return sb.toString();
                }
            }
        } finally {
            bufferedReader.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public class PerFunctionVariables {
        private Scope savedCurrentScope;
        private ScriptNode savedCurrentScriptOrFn;
        private int savedEndFlags;
        private boolean savedInForInit;
        private Map<String, LabeledStatement> savedLabelSet;
        private List<Jump> savedLoopAndSwitchSet;
        private List<Loop> savedLoopSet;

        /* JADX INFO: Access modifiers changed from: package-private */
        public PerFunctionVariables(FunctionNode functionNode) {
            this.savedCurrentScriptOrFn = Parser.this.currentScriptOrFn;
            Parser.this.currentScriptOrFn = functionNode;
            this.savedCurrentScope = Parser.this.currentScope;
            Parser.this.currentScope = functionNode;
            this.savedLabelSet = Parser.this.labelSet;
            Parser.this.labelSet = null;
            this.savedLoopSet = Parser.this.loopSet;
            Parser.this.loopSet = null;
            this.savedLoopAndSwitchSet = Parser.this.loopAndSwitchSet;
            Parser.this.loopAndSwitchSet = null;
            this.savedEndFlags = Parser.this.endFlags;
            Parser.this.endFlags = 0;
            this.savedInForInit = Parser.this.inForInit;
            Parser.this.inForInit = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void restore() {
            Parser.this.currentScriptOrFn = this.savedCurrentScriptOrFn;
            Parser.this.currentScope = this.savedCurrentScope;
            Parser.this.labelSet = this.savedLabelSet;
            Parser.this.loopSet = this.savedLoopSet;
            Parser.this.loopAndSwitchSet = this.savedLoopAndSwitchSet;
            Parser.this.endFlags = this.savedEndFlags;
            Parser.this.inForInit = this.savedInForInit;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Node createDestructuringAssignment(int i, Node node, Node node2) {
        String nextTempName = this.currentScriptOrFn.getNextTempName();
        Node destructuringAssignmentHelper = destructuringAssignmentHelper(i, node, node2, nextTempName);
        destructuringAssignmentHelper.getLastChild().addChildToBack(createName(nextTempName));
        return destructuringAssignmentHelper;
    }

    Node destructuringAssignmentHelper(int i, Node node, Node node2, String str) {
        Scope createScopeNode = createScopeNode(159, node.getLineno());
        createScopeNode.addChildToFront(new Node(154, createName(39, str, node2)));
        try {
            pushScope(createScopeNode);
            boolean z = true;
            defineSymbol(154, str, true);
            popScope();
            Node node3 = new Node(90);
            createScopeNode.addChildToBack(node3);
            List<String> arrayList = new ArrayList<>();
            int type = node.getType();
            if (type == 33 || type == 36) {
                if (i == 123 || i == 154 || i == 155) {
                    reportError("msg.bad.assign.left");
                }
                node3.addChildToBack(simpleAssignment(node, createName(str)));
            } else if (type == 66) {
                z = destructuringArray((ArrayLiteral) node, i, str, node3, arrayList);
            } else if (type == 67) {
                z = destructuringObject((ObjectLiteral) node, i, str, node3, arrayList);
            } else {
                reportError("msg.bad.assign.left");
            }
            if (z) {
                node3.addChildToBack(createNumber(0.0d));
            }
            createScopeNode.putProp(22, arrayList);
            return createScopeNode;
        } catch (Throwable th) {
            popScope();
            throw th;
        }
    }

    boolean destructuringArray(ArrayLiteral arrayLiteral, int i, String str, Node node, List<String> list) {
        int i2 = i == 155 ? 156 : 8;
        int i3 = 0;
        boolean z = true;
        for (AstNode astNode : arrayLiteral.getElements()) {
            if (astNode.getType() == 129) {
                i3++;
            } else {
                Node node2 = new Node(36, createName(str), createNumber(i3));
                if (astNode.getType() == 39) {
                    String string = astNode.getString();
                    node.addChildToBack(new Node(i2, createName(49, string, null), node2));
                    if (i != -1) {
                        defineSymbol(i, string, true);
                        list.add(string);
                    }
                } else {
                    node.addChildToBack(destructuringAssignmentHelper(i, astNode, node2, this.currentScriptOrFn.getNextTempName()));
                }
                i3++;
                z = false;
            }
        }
        return z;
    }

    boolean destructuringObject(ObjectLiteral objectLiteral, int i, String str, Node node, List<String> list) {
        Node node2;
        int i2 = i == 155 ? 156 : 8;
        boolean z = true;
        for (ObjectProperty objectProperty : objectLiteral.getElements()) {
            TokenStream tokenStream = this.f10207ts;
            int i3 = tokenStream != null ? tokenStream.lineno : 0;
            AstNode left = objectProperty.getLeft();
            if (left instanceof Name) {
                node2 = new Node(33, createName(str), Node.newString(((Name) left).getIdentifier()));
            } else if (left instanceof StringLiteral) {
                node2 = new Node(33, createName(str), Node.newString(((StringLiteral) left).getValue()));
            } else if (left instanceof NumberLiteral) {
                node2 = new Node(36, createName(str), createNumber((int) ((NumberLiteral) left).getNumber()));
            } else {
                throw codeBug();
            }
            node2.setLineno(i3);
            AstNode right = objectProperty.getRight();
            if (right.getType() == 39) {
                String identifier = ((Name) right).getIdentifier();
                node.addChildToBack(new Node(i2, createName(49, identifier, null), node2));
                if (i != -1) {
                    defineSymbol(i, identifier, true);
                    list.add(identifier);
                }
            } else {
                node.addChildToBack(destructuringAssignmentHelper(i, right, node2, this.currentScriptOrFn.getNextTempName()));
            }
            z = false;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node createName(String str) {
        checkActivationName(str, 39);
        return Node.newString(39, str);
    }

    protected Node createName(int i, String str, Node node) {
        Node createName = createName(str);
        createName.setType(i);
        if (node != null) {
            createName.addChildToBack(node);
        }
        return createName;
    }

    protected Node createNumber(double d) {
        return Node.newNumber(d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Scope createScopeNode(int i, int i2) {
        Scope scope = new Scope();
        scope.setType(i);
        scope.setLineno(i2);
        return scope;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node simpleAssignment(Node node, Node node2) {
        Node firstChild;
        Node lastChild;
        int i;
        int type = node.getType();
        if (type != 33 && type != 36) {
            if (type != 39) {
                if (type == 68) {
                    Node firstChild2 = node.getFirstChild();
                    checkMutableReference(firstChild2);
                    return new Node(69, firstChild2, node2);
                }
                throw codeBug();
            }
            String identifier = ((Name) node).getIdentifier();
            if (this.inUseStrictDirective && ("eval".equals(identifier) || "arguments".equals(identifier))) {
                reportError("msg.bad.id.strict", identifier);
            }
            node.setType(49);
            return new Node(8, node, node2);
        }
        if (node instanceof PropertyGet) {
            PropertyGet propertyGet = (PropertyGet) node;
            firstChild = propertyGet.getTarget();
            lastChild = propertyGet.getProperty();
        } else if (node instanceof ElementGet) {
            ElementGet elementGet = (ElementGet) node;
            firstChild = elementGet.getTarget();
            lastChild = elementGet.getElement();
        } else {
            firstChild = node.getFirstChild();
            lastChild = node.getLastChild();
        }
        if (type == 33) {
            i = 35;
            lastChild.setType(41);
        } else {
            i = 37;
        }
        return new Node(i, firstChild, lastChild, node2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkMutableReference(Node node) {
        if ((node.getIntProp(16, 0) & 4) != 0) {
            reportError("msg.bad.assign.left");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AstNode removeParens(AstNode astNode) {
        while (astNode instanceof ParenthesizedExpression) {
            astNode = ((ParenthesizedExpression) astNode).getExpression();
        }
        return astNode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void markDestructuring(AstNode astNode) {
        if (astNode instanceof DestructuringForm) {
            ((DestructuringForm) astNode).setIsDestructuring(true);
        } else if (astNode instanceof ParenthesizedExpression) {
            markDestructuring(((ParenthesizedExpression) astNode).getExpression());
        }
    }

    private RuntimeException codeBug() throws RuntimeException {
        throw Kit.codeBug("ts.cursor=" + this.f10207ts.cursor + ", ts.tokenBeg=" + this.f10207ts.tokenBeg + ", currentToken=" + this.currentToken);
    }

    public void setDefaultUseStrictDirective(boolean z) {
        this.defaultUseStrictDirective = z;
    }

    public boolean inUseStrictDirective() {
        return this.inUseStrictDirective;
    }
}
