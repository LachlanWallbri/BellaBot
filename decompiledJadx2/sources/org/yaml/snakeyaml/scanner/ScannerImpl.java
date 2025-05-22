package org.yaml.snakeyaml.scanner;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.tokens.AliasToken;
import org.yaml.snakeyaml.tokens.AnchorToken;
import org.yaml.snakeyaml.tokens.BlockEndToken;
import org.yaml.snakeyaml.tokens.BlockEntryToken;
import org.yaml.snakeyaml.tokens.BlockMappingStartToken;
import org.yaml.snakeyaml.tokens.BlockSequenceStartToken;
import org.yaml.snakeyaml.tokens.DirectiveToken;
import org.yaml.snakeyaml.tokens.DocumentEndToken;
import org.yaml.snakeyaml.tokens.DocumentStartToken;
import org.yaml.snakeyaml.tokens.FlowEntryToken;
import org.yaml.snakeyaml.tokens.FlowMappingEndToken;
import org.yaml.snakeyaml.tokens.FlowMappingStartToken;
import org.yaml.snakeyaml.tokens.FlowSequenceEndToken;
import org.yaml.snakeyaml.tokens.FlowSequenceStartToken;
import org.yaml.snakeyaml.tokens.KeyToken;
import org.yaml.snakeyaml.tokens.ScalarToken;
import org.yaml.snakeyaml.tokens.StreamEndToken;
import org.yaml.snakeyaml.tokens.StreamStartToken;
import org.yaml.snakeyaml.tokens.TagToken;
import org.yaml.snakeyaml.tokens.TagTuple;
import org.yaml.snakeyaml.tokens.Token;
import org.yaml.snakeyaml.tokens.ValueToken;
import org.yaml.snakeyaml.util.ArrayStack;
import org.yaml.snakeyaml.util.UriEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ScannerImpl implements Scanner {
    private final StreamReader reader;
    private static final Pattern NOT_HEXA = Pattern.compile("[^0-9A-Fa-f]");
    public static final Map<Character, String> ESCAPE_REPLACEMENTS = new HashMap();
    public static final Map<Character, Integer> ESCAPE_CODES = new HashMap();
    private boolean done = false;
    private int flowLevel = 0;
    private int tokensTaken = 0;
    private int indent = -1;
    private boolean allowSimpleKey = true;
    private List<Token> tokens = new ArrayList(100);
    private ArrayStack<Integer> indents = new ArrayStack<>(10);
    private Map<Integer, SimpleKey> possibleSimpleKeys = new LinkedHashMap();

    static {
        ESCAPE_REPLACEMENTS.put('0', "\u0000");
        ESCAPE_REPLACEMENTS.put('a', "\u0007");
        ESCAPE_REPLACEMENTS.put('b', "\b");
        ESCAPE_REPLACEMENTS.put('t', "\t");
        ESCAPE_REPLACEMENTS.put('n', "\n");
        ESCAPE_REPLACEMENTS.put('v', "\u000b");
        ESCAPE_REPLACEMENTS.put('f', "\f");
        ESCAPE_REPLACEMENTS.put('r', "\r");
        ESCAPE_REPLACEMENTS.put('e', "\u001b");
        ESCAPE_REPLACEMENTS.put(' ', " ");
        ESCAPE_REPLACEMENTS.put('\"', "\"");
        ESCAPE_REPLACEMENTS.put('\\', "\\");
        ESCAPE_REPLACEMENTS.put('N', "\u0085");
        ESCAPE_REPLACEMENTS.put('_', " ");
        ESCAPE_REPLACEMENTS.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT), "\u2028");
        ESCAPE_REPLACEMENTS.put('P', "\u2029");
        ESCAPE_CODES.put('x', 2);
        ESCAPE_CODES.put('u', 4);
        ESCAPE_CODES.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT), 8);
    }

    public ScannerImpl(StreamReader streamReader) {
        this.reader = streamReader;
        fetchStreamStart();
    }

    @Override // org.yaml.snakeyaml.scanner.Scanner
    public boolean checkToken(Token.EnumC8994ID... enumC8994IDArr) {
        while (needMoreTokens()) {
            fetchMoreTokens();
        }
        if (!this.tokens.isEmpty()) {
            if (enumC8994IDArr.length == 0) {
                return true;
            }
            Token.EnumC8994ID tokenId = this.tokens.get(0).getTokenId();
            for (Token.EnumC8994ID enumC8994ID : enumC8994IDArr) {
                if (tokenId == enumC8994ID) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // org.yaml.snakeyaml.scanner.Scanner
    public Token peekToken() {
        while (needMoreTokens()) {
            fetchMoreTokens();
        }
        return this.tokens.get(0);
    }

    @Override // org.yaml.snakeyaml.scanner.Scanner
    public Token getToken() {
        if (this.tokens.isEmpty()) {
            return null;
        }
        this.tokensTaken++;
        return this.tokens.remove(0);
    }

    private boolean needMoreTokens() {
        if (this.done) {
            return false;
        }
        if (this.tokens.isEmpty()) {
            return true;
        }
        stalePossibleSimpleKeys();
        return nextPossibleSimpleKey() == this.tokensTaken;
    }

    private void fetchMoreTokens() {
        scanToNextToken();
        stalePossibleSimpleKeys();
        unwindIndent(this.reader.getColumn());
        char peek = this.reader.peek();
        if (peek == 0) {
            fetchStreamEnd();
            return;
        }
        if (peek == '*') {
            fetchAlias();
            return;
        }
        if (peek != ':') {
            if (peek == '[') {
                fetchFlowSequenceStart();
                return;
            }
            if (peek == ']') {
                fetchFlowSequenceEnd();
                return;
            }
            if (peek == '!') {
                fetchTag();
                return;
            }
            if (peek == '\"') {
                fetchDouble();
                return;
            }
            if (peek != '>') {
                if (peek != '?') {
                    switch (peek) {
                        case '%':
                            if (checkDirective()) {
                                fetchDirective();
                                return;
                            }
                            break;
                        case '&':
                            fetchAnchor();
                            return;
                        case '\'':
                            fetchSingle();
                            return;
                        default:
                            switch (peek) {
                                case ',':
                                    fetchFlowEntry();
                                    return;
                                case '-':
                                    if (checkDocumentStart()) {
                                        fetchDocumentStart();
                                        return;
                                    } else if (checkBlockEntry()) {
                                        fetchBlockEntry();
                                        return;
                                    }
                                    break;
                                case '.':
                                    if (checkDocumentEnd()) {
                                        fetchDocumentEnd();
                                        return;
                                    }
                                    break;
                                default:
                                    switch (peek) {
                                        case '{':
                                            fetchFlowMappingStart();
                                            return;
                                        case '|':
                                            if (this.flowLevel == 0) {
                                                fetchLiteral();
                                                return;
                                            }
                                            break;
                                        case '}':
                                            fetchFlowMappingEnd();
                                            return;
                                    }
                            }
                    }
                } else if (checkKey()) {
                    fetchKey();
                    return;
                }
            } else if (this.flowLevel == 0) {
                fetchFolded();
                return;
            }
        } else if (checkValue()) {
            fetchValue();
            return;
        }
        if (checkPlain()) {
            fetchPlain();
            return;
        }
        String valueOf = String.valueOf(peek);
        Iterator<Character> it = ESCAPE_REPLACEMENTS.keySet().iterator();
        while (true) {
            if (it.hasNext()) {
                Character next = it.next();
                if (ESCAPE_REPLACEMENTS.get(next).equals(valueOf)) {
                    valueOf = "\\" + next;
                }
            }
        }
        if (peek == '\t') {
            valueOf = valueOf + "(TAB)";
        }
        throw new ScannerException("while scanning for the next token", null, String.format("found character '%s' that cannot start any token. (Do not use %s for indentation)", valueOf, valueOf), this.reader.getMark());
    }

    private int nextPossibleSimpleKey() {
        if (this.possibleSimpleKeys.isEmpty()) {
            return -1;
        }
        return this.possibleSimpleKeys.values().iterator().next().getTokenNumber();
    }

    private void stalePossibleSimpleKeys() {
        if (this.possibleSimpleKeys.isEmpty()) {
            return;
        }
        Iterator<SimpleKey> it = this.possibleSimpleKeys.values().iterator();
        while (it.hasNext()) {
            SimpleKey next = it.next();
            if (next.getLine() != this.reader.getLine() || this.reader.getIndex() - next.getIndex() > 1024) {
                if (next.isRequired()) {
                    throw new ScannerException("while scanning a simple key", next.getMark(), "could not find expected ':'", this.reader.getMark());
                }
                it.remove();
            }
        }
    }

    private void savePossibleSimpleKey() {
        boolean z = this.flowLevel == 0 && this.indent == this.reader.getColumn();
        if (!this.allowSimpleKey && z) {
            throw new YAMLException("A simple key is required only if it is the first token in the current line");
        }
        if (this.allowSimpleKey) {
            removePossibleSimpleKey();
            this.possibleSimpleKeys.put(Integer.valueOf(this.flowLevel), new SimpleKey(this.tokensTaken + this.tokens.size(), z, this.reader.getIndex(), this.reader.getLine(), this.reader.getColumn(), this.reader.getMark()));
        }
    }

    private void removePossibleSimpleKey() {
        SimpleKey remove = this.possibleSimpleKeys.remove(Integer.valueOf(this.flowLevel));
        if (remove != null && remove.isRequired()) {
            throw new ScannerException("while scanning a simple key", remove.getMark(), "could not find expected ':'", this.reader.getMark());
        }
    }

    private void unwindIndent(int i) {
        if (this.flowLevel != 0) {
            return;
        }
        while (this.indent > i) {
            Mark mark = this.reader.getMark();
            this.indent = this.indents.pop().intValue();
            this.tokens.add(new BlockEndToken(mark, mark));
        }
    }

    private boolean addIndent(int i) {
        int i2 = this.indent;
        if (i2 >= i) {
            return false;
        }
        this.indents.push(Integer.valueOf(i2));
        this.indent = i;
        return true;
    }

    private void fetchStreamStart() {
        Mark mark = this.reader.getMark();
        this.tokens.add(new StreamStartToken(mark, mark));
    }

    private void fetchStreamEnd() {
        unwindIndent(-1);
        removePossibleSimpleKey();
        this.allowSimpleKey = false;
        this.possibleSimpleKeys.clear();
        Mark mark = this.reader.getMark();
        this.tokens.add(new StreamEndToken(mark, mark));
        this.done = true;
    }

    private void fetchDirective() {
        unwindIndent(-1);
        removePossibleSimpleKey();
        this.allowSimpleKey = false;
        this.tokens.add(scanDirective());
    }

    private void fetchDocumentStart() {
        fetchDocumentIndicator(true);
    }

    private void fetchDocumentEnd() {
        fetchDocumentIndicator(false);
    }

    private void fetchDocumentIndicator(boolean z) {
        Token documentEndToken;
        unwindIndent(-1);
        removePossibleSimpleKey();
        this.allowSimpleKey = false;
        Mark mark = this.reader.getMark();
        this.reader.forward(3);
        Mark mark2 = this.reader.getMark();
        if (z) {
            documentEndToken = new DocumentStartToken(mark, mark2);
        } else {
            documentEndToken = new DocumentEndToken(mark, mark2);
        }
        this.tokens.add(documentEndToken);
    }

    private void fetchFlowSequenceStart() {
        fetchFlowCollectionStart(false);
    }

    private void fetchFlowMappingStart() {
        fetchFlowCollectionStart(true);
    }

    private void fetchFlowCollectionStart(boolean z) {
        Token flowSequenceStartToken;
        savePossibleSimpleKey();
        this.flowLevel++;
        this.allowSimpleKey = true;
        Mark mark = this.reader.getMark();
        this.reader.forward(1);
        Mark mark2 = this.reader.getMark();
        if (z) {
            flowSequenceStartToken = new FlowMappingStartToken(mark, mark2);
        } else {
            flowSequenceStartToken = new FlowSequenceStartToken(mark, mark2);
        }
        this.tokens.add(flowSequenceStartToken);
    }

    private void fetchFlowSequenceEnd() {
        fetchFlowCollectionEnd(false);
    }

    private void fetchFlowMappingEnd() {
        fetchFlowCollectionEnd(true);
    }

    private void fetchFlowCollectionEnd(boolean z) {
        Token flowSequenceEndToken;
        removePossibleSimpleKey();
        this.flowLevel--;
        this.allowSimpleKey = false;
        Mark mark = this.reader.getMark();
        this.reader.forward();
        Mark mark2 = this.reader.getMark();
        if (z) {
            flowSequenceEndToken = new FlowMappingEndToken(mark, mark2);
        } else {
            flowSequenceEndToken = new FlowSequenceEndToken(mark, mark2);
        }
        this.tokens.add(flowSequenceEndToken);
    }

    private void fetchFlowEntry() {
        this.allowSimpleKey = true;
        removePossibleSimpleKey();
        Mark mark = this.reader.getMark();
        this.reader.forward();
        this.tokens.add(new FlowEntryToken(mark, this.reader.getMark()));
    }

    private void fetchBlockEntry() {
        if (this.flowLevel == 0) {
            if (!this.allowSimpleKey) {
                throw new ScannerException(null, null, "sequence entries are not allowed here", this.reader.getMark());
            }
            if (addIndent(this.reader.getColumn())) {
                Mark mark = this.reader.getMark();
                this.tokens.add(new BlockSequenceStartToken(mark, mark));
            }
        }
        this.allowSimpleKey = true;
        removePossibleSimpleKey();
        Mark mark2 = this.reader.getMark();
        this.reader.forward();
        this.tokens.add(new BlockEntryToken(mark2, this.reader.getMark()));
    }

    private void fetchKey() {
        if (this.flowLevel == 0) {
            if (!this.allowSimpleKey) {
                throw new ScannerException(null, null, "mapping keys are not allowed here", this.reader.getMark());
            }
            if (addIndent(this.reader.getColumn())) {
                Mark mark = this.reader.getMark();
                this.tokens.add(new BlockMappingStartToken(mark, mark));
            }
        }
        this.allowSimpleKey = this.flowLevel == 0;
        removePossibleSimpleKey();
        Mark mark2 = this.reader.getMark();
        this.reader.forward();
        this.tokens.add(new KeyToken(mark2, this.reader.getMark()));
    }

    private void fetchValue() {
        SimpleKey remove = this.possibleSimpleKeys.remove(Integer.valueOf(this.flowLevel));
        if (remove != null) {
            this.tokens.add(remove.getTokenNumber() - this.tokensTaken, new KeyToken(remove.getMark(), remove.getMark()));
            if (this.flowLevel == 0 && addIndent(remove.getColumn())) {
                this.tokens.add(remove.getTokenNumber() - this.tokensTaken, new BlockMappingStartToken(remove.getMark(), remove.getMark()));
            }
            this.allowSimpleKey = false;
        } else {
            if (this.flowLevel == 0 && !this.allowSimpleKey) {
                throw new ScannerException(null, null, "mapping values are not allowed here", this.reader.getMark());
            }
            if (this.flowLevel == 0 && addIndent(this.reader.getColumn())) {
                Mark mark = this.reader.getMark();
                this.tokens.add(new BlockMappingStartToken(mark, mark));
            }
            this.allowSimpleKey = this.flowLevel == 0;
            removePossibleSimpleKey();
        }
        Mark mark2 = this.reader.getMark();
        this.reader.forward();
        this.tokens.add(new ValueToken(mark2, this.reader.getMark()));
    }

    private void fetchAlias() {
        savePossibleSimpleKey();
        this.allowSimpleKey = false;
        this.tokens.add(scanAnchor(false));
    }

    private void fetchAnchor() {
        savePossibleSimpleKey();
        this.allowSimpleKey = false;
        this.tokens.add(scanAnchor(true));
    }

    private void fetchTag() {
        savePossibleSimpleKey();
        this.allowSimpleKey = false;
        this.tokens.add(scanTag());
    }

    private void fetchLiteral() {
        fetchBlockScalar('|');
    }

    private void fetchFolded() {
        fetchBlockScalar(Typography.greater);
    }

    private void fetchBlockScalar(char c) {
        this.allowSimpleKey = true;
        removePossibleSimpleKey();
        this.tokens.add(scanBlockScalar(c));
    }

    private void fetchSingle() {
        fetchFlowScalar('\'');
    }

    private void fetchDouble() {
        fetchFlowScalar('\"');
    }

    private void fetchFlowScalar(char c) {
        savePossibleSimpleKey();
        this.allowSimpleKey = false;
        this.tokens.add(scanFlowScalar(c));
    }

    private void fetchPlain() {
        savePossibleSimpleKey();
        this.allowSimpleKey = false;
        this.tokens.add(scanPlain());
    }

    private boolean checkDirective() {
        return this.reader.getColumn() == 0;
    }

    private boolean checkDocumentStart() {
        return this.reader.getColumn() == 0 && "---".equals(this.reader.prefix(3)) && Constant.NULL_BL_T_LINEBR.has(this.reader.peek(3));
    }

    private boolean checkDocumentEnd() {
        return this.reader.getColumn() == 0 && "...".equals(this.reader.prefix(3)) && Constant.NULL_BL_T_LINEBR.has(this.reader.peek(3));
    }

    private boolean checkBlockEntry() {
        return Constant.NULL_BL_T_LINEBR.has(this.reader.peek(1));
    }

    private boolean checkKey() {
        if (this.flowLevel != 0) {
            return true;
        }
        return Constant.NULL_BL_T_LINEBR.has(this.reader.peek(1));
    }

    private boolean checkValue() {
        if (this.flowLevel != 0) {
            return true;
        }
        return Constant.NULL_BL_T_LINEBR.has(this.reader.peek(1));
    }

    private boolean checkPlain() {
        char peek = this.reader.peek();
        if (Constant.NULL_BL_T_LINEBR.hasNo(peek, "-?:,[]{}#&*!|>'\"%@`")) {
            return true;
        }
        if (Constant.NULL_BL_T_LINEBR.hasNo(this.reader.peek(1))) {
            if (peek == '-') {
                return true;
            }
            if (this.flowLevel == 0 && "?:".indexOf(peek) != -1) {
                return true;
            }
        }
        return false;
    }

    private void scanToNextToken() {
        if (this.reader.getIndex() == 0 && this.reader.peek() == 65279) {
            this.reader.forward();
        }
        boolean z = false;
        while (!z) {
            int i = 0;
            while (this.reader.peek(i) == ' ') {
                i++;
            }
            if (i > 0) {
                this.reader.forward(i);
            }
            if (this.reader.peek() == '#') {
                int i2 = 0;
                while (Constant.NULL_OR_LINEBR.hasNo(this.reader.peek(i2))) {
                    i2++;
                }
                if (i2 > 0) {
                    this.reader.forward(i2);
                }
            }
            if (scanLineBreak().length() == 0) {
                z = true;
            } else if (this.flowLevel == 0) {
                this.allowSimpleKey = true;
            }
        }
    }

    private Token scanDirective() {
        Mark mark;
        List list;
        Mark mark2 = this.reader.getMark();
        this.reader.forward();
        String scanDirectiveName = scanDirectiveName(mark2);
        if ("YAML".equals(scanDirectiveName)) {
            list = scanYamlDirectiveValue(mark2);
            mark = this.reader.getMark();
        } else if ("TAG".equals(scanDirectiveName)) {
            list = scanTagDirectiveValue(mark2);
            mark = this.reader.getMark();
        } else {
            mark = this.reader.getMark();
            int i = 0;
            while (Constant.NULL_OR_LINEBR.hasNo(this.reader.peek(i))) {
                i++;
            }
            if (i > 0) {
                this.reader.forward(i);
            }
            list = null;
        }
        scanDirectiveIgnoredLine(mark2);
        return new DirectiveToken(scanDirectiveName, list, mark2, mark);
    }

    private String scanDirectiveName(Mark mark) {
        int i = 0;
        char peek = this.reader.peek(0);
        while (Constant.ALPHA.has(peek)) {
            i++;
            peek = this.reader.peek(i);
        }
        if (i == 0) {
            throw new ScannerException("while scanning a directive", mark, "expected alphabetic or numeric character, but found " + peek + "(" + ((int) peek) + ")", this.reader.getMark());
        }
        String prefixForward = this.reader.prefixForward(i);
        char peek2 = this.reader.peek();
        if (!Constant.NULL_BL_LINEBR.hasNo(peek2)) {
            return prefixForward;
        }
        throw new ScannerException("while scanning a directive", mark, "expected alphabetic or numeric character, but found " + peek2 + "(" + ((int) peek2) + ")", this.reader.getMark());
    }

    private List<Integer> scanYamlDirectiveValue(Mark mark) {
        while (this.reader.peek() == ' ') {
            this.reader.forward();
        }
        Integer scanYamlDirectiveNumber = scanYamlDirectiveNumber(mark);
        if (this.reader.peek() != '.') {
            throw new ScannerException("while scanning a directive", mark, "expected a digit or '.', but found " + this.reader.peek() + "(" + ((int) this.reader.peek()) + ")", this.reader.getMark());
        }
        this.reader.forward();
        Integer scanYamlDirectiveNumber2 = scanYamlDirectiveNumber(mark);
        if (Constant.NULL_BL_LINEBR.hasNo(this.reader.peek())) {
            throw new ScannerException("while scanning a directive", mark, "expected a digit or ' ', but found " + this.reader.peek() + "(" + ((int) this.reader.peek()) + ")", this.reader.getMark());
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(scanYamlDirectiveNumber);
        arrayList.add(scanYamlDirectiveNumber2);
        return arrayList;
    }

    private Integer scanYamlDirectiveNumber(Mark mark) {
        char peek = this.reader.peek();
        if (!Character.isDigit(peek)) {
            throw new ScannerException("while scanning a directive", mark, "expected a digit, but found " + peek + "(" + ((int) peek) + ")", this.reader.getMark());
        }
        int i = 0;
        while (Character.isDigit(this.reader.peek(i))) {
            i++;
        }
        return Integer.valueOf(Integer.parseInt(this.reader.prefixForward(i)));
    }

    private List<String> scanTagDirectiveValue(Mark mark) {
        while (this.reader.peek() == ' ') {
            this.reader.forward();
        }
        String scanTagDirectiveHandle = scanTagDirectiveHandle(mark);
        while (this.reader.peek() == ' ') {
            this.reader.forward();
        }
        String scanTagDirectivePrefix = scanTagDirectivePrefix(mark);
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(scanTagDirectiveHandle);
        arrayList.add(scanTagDirectivePrefix);
        return arrayList;
    }

    private String scanTagDirectiveHandle(Mark mark) {
        String scanTagHandle = scanTagHandle("directive", mark);
        char peek = this.reader.peek();
        if (peek == ' ') {
            return scanTagHandle;
        }
        throw new ScannerException("while scanning a directive", mark, "expected ' ', but found " + this.reader.peek() + "(" + peek + ")", this.reader.getMark());
    }

    private String scanTagDirectivePrefix(Mark mark) {
        String scanTagUri = scanTagUri("directive", mark);
        if (!Constant.NULL_BL_LINEBR.hasNo(this.reader.peek())) {
            return scanTagUri;
        }
        throw new ScannerException("while scanning a directive", mark, "expected ' ', but found " + this.reader.peek() + "(" + ((int) this.reader.peek()) + ")", this.reader.getMark());
    }

    private String scanDirectiveIgnoredLine(Mark mark) {
        int i = 0;
        int i2 = 0;
        while (this.reader.peek(i2) == ' ') {
            i2++;
        }
        if (i2 > 0) {
            this.reader.forward(i2);
        }
        if (this.reader.peek() == '#') {
            while (Constant.NULL_OR_LINEBR.hasNo(this.reader.peek(i))) {
                i++;
            }
            this.reader.forward(i);
        }
        char peek = this.reader.peek();
        String scanLineBreak = scanLineBreak();
        if (scanLineBreak.length() != 0 || peek == 0) {
            return scanLineBreak;
        }
        throw new ScannerException("while scanning a directive", mark, "expected a comment or a line break, but found " + peek + "(" + ((int) peek) + ")", this.reader.getMark());
    }

    private Token scanAnchor(boolean z) {
        Mark mark = this.reader.getMark();
        String str = this.reader.peek() == '*' ? ConfigJson.ALIAS : "anchor";
        this.reader.forward();
        int i = 0;
        char peek = this.reader.peek(0);
        while (Constant.ALPHA.has(peek)) {
            i++;
            peek = this.reader.peek(i);
        }
        if (i == 0) {
            throw new ScannerException("while scanning an " + str, mark, "expected alphabetic or numeric character, but found " + peek, this.reader.getMark());
        }
        String prefixForward = this.reader.prefixForward(i);
        char peek2 = this.reader.peek();
        if (Constant.NULL_BL_T_LINEBR.hasNo(peek2, "?:,]}%@`")) {
            throw new ScannerException("while scanning an " + str, mark, "expected alphabetic or numeric character, but found " + peek2 + "(" + ((int) this.reader.peek()) + ")", this.reader.getMark());
        }
        Mark mark2 = this.reader.getMark();
        if (z) {
            return new AnchorToken(prefixForward, mark, mark2);
        }
        return new AliasToken(prefixForward, mark, mark2);
    }

    private Token scanTag() {
        Mark mark = this.reader.getMark();
        boolean z = true;
        char peek = this.reader.peek(1);
        String str = "!";
        String str2 = null;
        if (peek == '<') {
            this.reader.forward(2);
            str = scanTagUri(AIUIConstant.KEY_TAG, mark);
            if (this.reader.peek() != '>') {
                throw new ScannerException("while scanning a tag", mark, "expected '>', but found '" + this.reader.peek() + "' (" + ((int) this.reader.peek()) + ")", this.reader.getMark());
            }
            this.reader.forward();
        } else if (Constant.NULL_BL_T_LINEBR.has(peek)) {
            this.reader.forward();
        } else {
            int i = 1;
            while (true) {
                if (!Constant.NULL_BL_LINEBR.hasNo(peek)) {
                    z = false;
                    break;
                }
                if (peek == '!') {
                    break;
                }
                i++;
                peek = this.reader.peek(i);
            }
            if (z) {
                str = scanTagHandle(AIUIConstant.KEY_TAG, mark);
            } else {
                this.reader.forward();
            }
            str2 = str;
            str = scanTagUri(AIUIConstant.KEY_TAG, mark);
        }
        char peek2 = this.reader.peek();
        if (Constant.NULL_BL_LINEBR.hasNo(peek2)) {
            throw new ScannerException("while scanning a tag", mark, "expected ' ', but found '" + peek2 + "' (" + ((int) peek2) + ")", this.reader.getMark());
        }
        return new TagToken(new TagTuple(str2, str), mark, this.reader.getMark());
    }

    private Token scanBlockScalar(char c) {
        int i;
        String str;
        Mark mark;
        Mark mark2;
        boolean z = c == '>';
        StringBuilder sb = new StringBuilder();
        Mark mark3 = this.reader.getMark();
        this.reader.forward();
        Chomping scanBlockScalarIndicators = scanBlockScalarIndicators(mark3);
        int increment = scanBlockScalarIndicators.getIncrement();
        scanBlockScalarIgnoredLine(mark3);
        int i2 = this.indent + 1;
        if (i2 < 1) {
            i2 = 1;
        }
        if (increment == -1) {
            Object[] scanBlockScalarIndentation = scanBlockScalarIndentation();
            str = (String) scanBlockScalarIndentation[0];
            int intValue = ((Integer) scanBlockScalarIndentation[1]).intValue();
            mark = (Mark) scanBlockScalarIndentation[2];
            i = Math.max(i2, intValue);
        } else {
            i = (i2 + increment) - 1;
            Object[] scanBlockScalarBreaks = scanBlockScalarBreaks(i);
            str = (String) scanBlockScalarBreaks[0];
            mark = (Mark) scanBlockScalarBreaks[1];
        }
        String str2 = "";
        while (this.reader.getColumn() == i && this.reader.peek() != 0) {
            sb.append(str);
            boolean z2 = " \t".indexOf(this.reader.peek()) == -1;
            int i3 = 0;
            while (Constant.NULL_OR_LINEBR.hasNo(this.reader.peek(i3))) {
                i3++;
            }
            sb.append(this.reader.prefixForward(i3));
            str2 = scanLineBreak();
            Object[] scanBlockScalarBreaks2 = scanBlockScalarBreaks(i);
            String str3 = (String) scanBlockScalarBreaks2[0];
            mark2 = (Mark) scanBlockScalarBreaks2[1];
            if (this.reader.getColumn() != i || this.reader.peek() == 0) {
                str = str3;
                break;
            }
            if (z && "\n".equals(str2) && z2 && " \t".indexOf(this.reader.peek()) == -1) {
                if (str3.length() == 0) {
                    sb.append(" ");
                }
            } else {
                sb.append(str2);
            }
            mark = mark2;
            str = str3;
        }
        mark2 = mark;
        if (scanBlockScalarIndicators.chompTailIsNotFalse()) {
            sb.append(str2);
        }
        if (scanBlockScalarIndicators.chompTailIsTrue()) {
            sb.append(str);
        }
        return new ScalarToken(sb.toString(), false, mark3, mark2, c);
    }

    private Chomping scanBlockScalarIndicators(Mark mark) {
        Boolean bool;
        Boolean bool2;
        char peek = this.reader.peek();
        Boolean bool3 = null;
        int i = -1;
        if (peek == '-' || peek == '+') {
            if (peek == '+') {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            bool3 = bool;
            this.reader.forward();
            char peek2 = this.reader.peek();
            if (Character.isDigit(peek2)) {
                i = Integer.parseInt(String.valueOf(peek2));
                if (i == 0) {
                    throw new ScannerException("while scanning a block scalar", mark, "expected indentation indicator in the range 1-9, but found 0", this.reader.getMark());
                }
                this.reader.forward();
            }
        } else if (Character.isDigit(peek)) {
            i = Integer.parseInt(String.valueOf(peek));
            if (i == 0) {
                throw new ScannerException("while scanning a block scalar", mark, "expected indentation indicator in the range 1-9, but found 0", this.reader.getMark());
            }
            this.reader.forward();
            char peek3 = this.reader.peek();
            if (peek3 == '-' || peek3 == '+') {
                if (peek3 == '+') {
                    bool2 = Boolean.TRUE;
                } else {
                    bool2 = Boolean.FALSE;
                }
                bool3 = bool2;
                this.reader.forward();
            }
        }
        char peek4 = this.reader.peek();
        if (Constant.NULL_BL_LINEBR.hasNo(peek4)) {
            throw new ScannerException("while scanning a block scalar", mark, "expected chomping or indentation indicators, but found " + peek4, this.reader.getMark());
        }
        return new Chomping(bool3, i);
    }

    private String scanBlockScalarIgnoredLine(Mark mark) {
        int i = 0;
        int i2 = 0;
        while (this.reader.peek(i2) == ' ') {
            i2++;
        }
        if (i2 > 0) {
            this.reader.forward(i2);
        }
        if (this.reader.peek() == '#') {
            while (Constant.NULL_OR_LINEBR.hasNo(this.reader.peek(i))) {
                i++;
            }
            if (i > 0) {
                this.reader.forward(i);
            }
        }
        char peek = this.reader.peek();
        String scanLineBreak = scanLineBreak();
        if (scanLineBreak.length() != 0 || peek == 0) {
            return scanLineBreak;
        }
        throw new ScannerException("while scanning a block scalar", mark, "expected a comment or a line break, but found " + peek, this.reader.getMark());
    }

    private Object[] scanBlockScalarIndentation() {
        StringBuilder sb = new StringBuilder();
        Mark mark = this.reader.getMark();
        int i = 0;
        while (Constant.LINEBR.has(this.reader.peek(), " \r")) {
            if (this.reader.peek() != ' ') {
                sb.append(scanLineBreak());
                mark = this.reader.getMark();
            } else {
                this.reader.forward();
                if (this.reader.getColumn() > i) {
                    i = this.reader.getColumn();
                }
            }
        }
        return new Object[]{sb.toString(), Integer.valueOf(i), mark};
    }

    private Object[] scanBlockScalarBreaks(int i) {
        StringBuilder sb = new StringBuilder();
        Mark mark = this.reader.getMark();
        int i2 = 0;
        for (int column = this.reader.getColumn(); column < i && this.reader.peek(i2) == ' '; column++) {
            i2++;
        }
        if (i2 > 0) {
            this.reader.forward(i2);
        }
        while (true) {
            String scanLineBreak = scanLineBreak();
            if (scanLineBreak.length() == 0) {
                return new Object[]{sb.toString(), mark};
            }
            sb.append(scanLineBreak);
            mark = this.reader.getMark();
            int i3 = 0;
            for (int column2 = this.reader.getColumn(); column2 < i && this.reader.peek(i3) == ' '; column2++) {
                i3++;
            }
            if (i3 > 0) {
                this.reader.forward(i3);
            }
        }
    }

    private Token scanFlowScalar(char c) {
        boolean z = c == '\"';
        StringBuilder sb = new StringBuilder();
        Mark mark = this.reader.getMark();
        char peek = this.reader.peek();
        this.reader.forward();
        sb.append(scanFlowScalarNonSpaces(z, mark));
        while (this.reader.peek() != peek) {
            sb.append(scanFlowScalarSpaces(mark));
            sb.append(scanFlowScalarNonSpaces(z, mark));
        }
        this.reader.forward();
        return new ScalarToken(sb.toString(), false, mark, this.reader.getMark(), c);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
    
        if (r1 == '\'') goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String scanFlowScalarNonSpaces(boolean z, Mark mark) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = 0;
            while (Constant.NULL_BL_T_LINEBR.hasNo(this.reader.peek(i), "'\"\\")) {
                i++;
            }
            if (i != 0) {
                sb.append(this.reader.prefixForward(i));
            }
            char peek = this.reader.peek();
            if (!z && peek == '\'' && this.reader.peek(1) == '\'') {
                sb.append("'");
                this.reader.forward(2);
            } else if (!z && BasicHeaderValueFormatter.UNSAFE_CHARS.indexOf(peek) != -1) {
                sb.append(peek);
                this.reader.forward();
            } else {
                if (!z || peek != '\\') {
                    break;
                }
                this.reader.forward();
                char peek2 = this.reader.peek();
                if (ESCAPE_REPLACEMENTS.containsKey(Character.valueOf(peek2))) {
                    sb.append(ESCAPE_REPLACEMENTS.get(Character.valueOf(peek2)));
                    this.reader.forward();
                } else if (ESCAPE_CODES.containsKey(Character.valueOf(peek2))) {
                    int intValue = ESCAPE_CODES.get(Character.valueOf(peek2)).intValue();
                    this.reader.forward();
                    String prefix = this.reader.prefix(intValue);
                    if (NOT_HEXA.matcher(prefix).find()) {
                        throw new ScannerException("while scanning a double-quoted scalar", mark, "expected escape sequence of " + intValue + " hexadecimal numbers, but found: " + prefix, this.reader.getMark());
                    }
                    sb.append(new String(Character.toChars(Integer.parseInt(prefix, 16))));
                    this.reader.forward(intValue);
                } else if (scanLineBreak().length() != 0) {
                    sb.append(scanFlowScalarBreaks(mark));
                } else {
                    throw new ScannerException("while scanning a double-quoted scalar", mark, "found unknown escape character " + peek2 + "(" + ((int) peek2) + ")", this.reader.getMark());
                }
            }
        }
        return sb.toString();
    }

    private String scanFlowScalarSpaces(Mark mark) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (" \t".indexOf(this.reader.peek(i)) != -1) {
            i++;
        }
        String prefixForward = this.reader.prefixForward(i);
        if (this.reader.peek() == 0) {
            throw new ScannerException("while scanning a quoted scalar", mark, "found unexpected end of stream", this.reader.getMark());
        }
        String scanLineBreak = scanLineBreak();
        if (scanLineBreak.length() != 0) {
            String scanFlowScalarBreaks = scanFlowScalarBreaks(mark);
            if (!"\n".equals(scanLineBreak)) {
                sb.append(scanLineBreak);
            } else if (scanFlowScalarBreaks.length() == 0) {
                sb.append(" ");
            }
            sb.append(scanFlowScalarBreaks);
        } else {
            sb.append(prefixForward);
        }
        return sb.toString();
    }

    private String scanFlowScalarBreaks(Mark mark) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String prefix = this.reader.prefix(3);
            if (("---".equals(prefix) || "...".equals(prefix)) && Constant.NULL_BL_T_LINEBR.has(this.reader.peek(3))) {
                throw new ScannerException("while scanning a quoted scalar", mark, "found unexpected document separator", this.reader.getMark());
            }
            while (" \t".indexOf(this.reader.peek()) != -1) {
                this.reader.forward();
            }
            String scanLineBreak = scanLineBreak();
            if (scanLineBreak.length() != 0) {
                sb.append(scanLineBreak);
            } else {
                return sb.toString();
            }
        }
    }

    private Token scanPlain() {
        char peek;
        StringBuilder sb = new StringBuilder();
        Mark mark = this.reader.getMark();
        int i = this.indent + 1;
        String str = "";
        Mark mark2 = mark;
        while (this.reader.peek() != '#') {
            int i2 = 0;
            while (true) {
                peek = this.reader.peek(i2);
                if (Constant.NULL_BL_T_LINEBR.has(peek) || ((this.flowLevel == 0 && peek == ':' && Constant.NULL_BL_T_LINEBR.has(this.reader.peek(i2 + 1))) || !(this.flowLevel == 0 || ",:?[]{}".indexOf(peek) == -1))) {
                    break;
                }
                i2++;
            }
            if (this.flowLevel == 0 || peek != ':' || !Constant.NULL_BL_T_LINEBR.hasNo(this.reader.peek(i2 + 1), ",[]{}")) {
                if (i2 != 0) {
                    this.allowSimpleKey = false;
                    sb.append(str);
                    sb.append(this.reader.prefixForward(i2));
                    mark2 = this.reader.getMark();
                    str = scanPlainSpaces();
                    if (str.length() == 0 || this.reader.peek() == '#' || (this.flowLevel == 0 && this.reader.getColumn() < i)) {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                this.reader.forward(i2);
                throw new ScannerException("while scanning a plain scalar", mark, "found unexpected ':'", this.reader.getMark(), "Please check http://pyyaml.org/wiki/YAMLColonInFlowContext for details.");
            }
        }
        return new ScalarToken(sb.toString(), mark, mark2, true);
    }

    private String scanPlainSpaces() {
        int i = 0;
        while (true) {
            if (this.reader.peek(i) != ' ' && this.reader.peek(i) != '\t') {
                break;
            }
            i++;
        }
        String prefixForward = this.reader.prefixForward(i);
        String scanLineBreak = scanLineBreak();
        if (scanLineBreak.length() == 0) {
            return prefixForward;
        }
        this.allowSimpleKey = true;
        String prefix = this.reader.prefix(3);
        if ("---".equals(prefix) || ("...".equals(prefix) && Constant.NULL_BL_T_LINEBR.has(this.reader.peek(3)))) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (this.reader.peek() == ' ') {
                this.reader.forward();
            } else {
                String scanLineBreak2 = scanLineBreak();
                if (scanLineBreak2.length() != 0) {
                    sb.append(scanLineBreak2);
                    String prefix2 = this.reader.prefix(3);
                    if ("---".equals(prefix2) || ("...".equals(prefix2) && Constant.NULL_BL_T_LINEBR.has(this.reader.peek(3)))) {
                        break;
                    }
                } else {
                    if ("\n".equals(scanLineBreak)) {
                        return sb.length() == 0 ? " " : sb.toString();
                    }
                    return scanLineBreak + ((Object) sb);
                }
            }
        }
        return "";
    }

    private String scanTagHandle(String str, Mark mark) {
        char peek = this.reader.peek();
        if (peek != '!') {
            throw new ScannerException("while scanning a " + str, mark, "expected '!', but found " + peek + "(" + ((int) peek) + ")", this.reader.getMark());
        }
        int i = 1;
        char peek2 = this.reader.peek(1);
        if (peek2 != ' ') {
            int i2 = 1;
            while (Constant.ALPHA.has(peek2)) {
                i2++;
                peek2 = this.reader.peek(i2);
            }
            if (peek2 != '!') {
                this.reader.forward(i2);
                throw new ScannerException("while scanning a " + str, mark, "expected '!', but found " + peek2 + "(" + ((int) peek2) + ")", this.reader.getMark());
            }
            i = 1 + i2;
        }
        return this.reader.prefixForward(i);
    }

    private String scanTagUri(String str, Mark mark) {
        StringBuilder sb = new StringBuilder();
        char peek = this.reader.peek(0);
        int i = 0;
        while (Constant.URI_CHARS.has(peek)) {
            if (peek == '%') {
                sb.append(this.reader.prefixForward(i));
                sb.append(scanUriEscapes(str, mark));
                i = 0;
            } else {
                i++;
            }
            peek = this.reader.peek(i);
        }
        if (i != 0) {
            sb.append(this.reader.prefixForward(i));
        }
        if (sb.length() == 0) {
            throw new ScannerException("while scanning a " + str, mark, "expected URI, but found " + peek + "(" + ((int) peek) + ")", this.reader.getMark());
        }
        return sb.toString();
    }

    private String scanUriEscapes(String str, Mark mark) {
        int i = 1;
        while (this.reader.peek(i * 3) == '%') {
            i++;
        }
        Mark mark2 = this.reader.getMark();
        ByteBuffer allocate = ByteBuffer.allocate(i);
        while (this.reader.peek() == '%') {
            this.reader.forward();
            try {
                allocate.put((byte) Integer.parseInt(this.reader.prefix(2), 16));
                this.reader.forward(2);
            } catch (NumberFormatException unused) {
                throw new ScannerException("while scanning a " + str, mark, "expected URI escape sequence of 2 hexadecimal numbers, but found " + this.reader.peek() + "(" + ((int) this.reader.peek()) + ") and " + this.reader.peek(1) + "(" + ((int) this.reader.peek(1)) + ")", this.reader.getMark());
            }
        }
        allocate.flip();
        try {
            return UriEncoder.decode(allocate);
        } catch (CharacterCodingException e) {
            throw new ScannerException("while scanning a " + str, mark, "expected URI in UTF-8: " + e.getMessage(), mark2);
        }
    }

    private String scanLineBreak() {
        char peek = this.reader.peek();
        if (peek != '\r' && peek != '\n' && peek != 133) {
            if (peek != 8232 && peek != 8233) {
                return "";
            }
            this.reader.forward();
            return String.valueOf(peek);
        }
        if (peek == '\r' && '\n' == this.reader.peek(1)) {
            this.reader.forward(2);
            return "\n";
        }
        this.reader.forward();
        return "\n";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static class Chomping {
        private final int increment;
        private final Boolean value;

        public Chomping(Boolean bool, int i) {
            this.value = bool;
            this.increment = i;
        }

        public boolean chompTailIsNotFalse() {
            Boolean bool = this.value;
            return bool == null || bool.booleanValue();
        }

        public boolean chompTailIsTrue() {
            Boolean bool = this.value;
            return bool != null && bool.booleanValue();
        }

        public int getIncrement() {
            return this.increment;
        }
    }
}
