package org.yaml.snakeyaml.emitter;

import com.loc.C3898x;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.events.CollectionEndEvent;
import org.yaml.snakeyaml.events.CollectionStartEvent;
import org.yaml.snakeyaml.events.DocumentEndEvent;
import org.yaml.snakeyaml.events.DocumentStartEvent;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.MappingEndEvent;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.NodeEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.SequenceEndEvent;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.events.StreamEndEvent;
import org.yaml.snakeyaml.events.StreamStartEvent;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.scanner.Constant;
import org.yaml.snakeyaml.util.ArrayStack;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class Emitter implements Emitable {
    private static final Pattern ANCHOR_FORMAT;
    private static final Map<String, String> DEFAULT_TAG_PREFIXES;
    private static final Pattern HANDLE_FORMAT;
    public static final int MAX_INDENT = 10;
    public static final int MIN_INDENT = 1;
    private boolean allowUnicode;
    private ScalarAnalysis analysis;
    private int bestIndent;
    private char[] bestLineBreak;
    private int bestWidth;
    private Boolean canonical;
    private int indicatorIndent;
    private String preparedAnchor;
    private String preparedTag;
    private Boolean prettyFlow;
    private boolean rootContext;
    private boolean splitLines;
    private final Writer stream;
    private Character style;
    private Map<String, String> tagPrefixes;
    private static final Map<Character, String> ESCAPE_REPLACEMENTS = new HashMap();
    private static final char[] SPACE = {' '};
    private final ArrayStack<EmitterState> states = new ArrayStack<>(100);
    private EmitterState state = new ExpectStreamStart();
    private final Queue<Event> events = new ArrayBlockingQueue(100);
    private Event event = null;
    private final ArrayStack<Integer> indents = new ArrayStack<>(10);
    private Integer indent = null;
    private int flowLevel = 0;
    private boolean mappingContext = false;
    private boolean simpleKeyContext = false;
    private int column = 0;
    private boolean whitespace = true;
    private boolean indention = true;
    private boolean openEnded = false;

    void writeStreamStart() {
    }

    static /* synthetic */ int access$2010(Emitter emitter) {
        int i = emitter.flowLevel;
        emitter.flowLevel = i - 1;
        return i;
    }

    static {
        ESCAPE_REPLACEMENTS.put((char) 0, "0");
        ESCAPE_REPLACEMENTS.put((char) 7, "a");
        ESCAPE_REPLACEMENTS.put('\b', "b");
        ESCAPE_REPLACEMENTS.put('\t', "t");
        ESCAPE_REPLACEMENTS.put('\n', "n");
        ESCAPE_REPLACEMENTS.put((char) 11, "v");
        ESCAPE_REPLACEMENTS.put('\f', C3898x.f4339h);
        ESCAPE_REPLACEMENTS.put('\r', "r");
        ESCAPE_REPLACEMENTS.put((char) 27, C3898x.f4338g);
        ESCAPE_REPLACEMENTS.put('\"', "\"");
        ESCAPE_REPLACEMENTS.put('\\', "\\");
        ESCAPE_REPLACEMENTS.put((char) 133, "N");
        ESCAPE_REPLACEMENTS.put(Character.valueOf(Typography.nbsp), "_");
        ESCAPE_REPLACEMENTS.put((char) 8232, "L");
        ESCAPE_REPLACEMENTS.put((char) 8233, "P");
        DEFAULT_TAG_PREFIXES = new LinkedHashMap();
        DEFAULT_TAG_PREFIXES.put("!", "!");
        DEFAULT_TAG_PREFIXES.put(Tag.PREFIX, "!!");
        HANDLE_FORMAT = Pattern.compile("^![-_\\w]*!$");
        ANCHOR_FORMAT = Pattern.compile("^[-_\\w]*$");
    }

    public Emitter(Writer writer, DumperOptions dumperOptions) {
        this.stream = writer;
        this.canonical = Boolean.valueOf(dumperOptions.isCanonical());
        this.prettyFlow = Boolean.valueOf(dumperOptions.isPrettyFlow());
        this.allowUnicode = dumperOptions.isAllowUnicode();
        this.bestIndent = 2;
        if (dumperOptions.getIndent() > 1 && dumperOptions.getIndent() < 10) {
            this.bestIndent = dumperOptions.getIndent();
        }
        this.indicatorIndent = dumperOptions.getIndicatorIndent();
        this.bestWidth = 80;
        if (dumperOptions.getWidth() > this.bestIndent * 2) {
            this.bestWidth = dumperOptions.getWidth();
        }
        this.bestLineBreak = dumperOptions.getLineBreak().getString().toCharArray();
        this.splitLines = dumperOptions.getSplitLines();
        this.tagPrefixes = new LinkedHashMap();
        this.preparedAnchor = null;
        this.preparedTag = null;
        this.analysis = null;
        this.style = null;
    }

    @Override // org.yaml.snakeyaml.emitter.Emitable
    public void emit(Event event) throws IOException {
        this.events.add(event);
        while (!needMoreEvents()) {
            this.event = this.events.poll();
            this.state.expect();
            this.event = null;
        }
    }

    private boolean needMoreEvents() {
        if (this.events.isEmpty()) {
            return true;
        }
        Event peek = this.events.peek();
        if (peek instanceof DocumentStartEvent) {
            return needEvents(1);
        }
        if (peek instanceof SequenceStartEvent) {
            return needEvents(2);
        }
        if (peek instanceof MappingStartEvent) {
            return needEvents(3);
        }
        return false;
    }

    private boolean needEvents(int i) {
        Iterator<Event> it = this.events.iterator();
        it.next();
        int i2 = 0;
        while (it.hasNext()) {
            Event next = it.next();
            if ((next instanceof DocumentStartEvent) || (next instanceof CollectionStartEvent)) {
                i2++;
            } else if ((next instanceof DocumentEndEvent) || (next instanceof CollectionEndEvent)) {
                i2--;
            } else if (next instanceof StreamEndEvent) {
                i2 = -1;
            }
            if (i2 < 0) {
                return false;
            }
        }
        return this.events.size() < i + 1;
    }

    private void increaseIndent(boolean z, boolean z2) {
        this.indents.push(this.indent);
        Integer num = this.indent;
        if (num != null) {
            if (z2) {
                return;
            }
            this.indent = Integer.valueOf(num.intValue() + this.bestIndent);
        } else if (z) {
            this.indent = Integer.valueOf(this.bestIndent);
        } else {
            this.indent = 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectStreamStart implements EmitterState {
        private ExpectStreamStart() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (!(Emitter.this.event instanceof StreamStartEvent)) {
                throw new EmitterException("expected StreamStartEvent, but got " + Emitter.this.event);
            }
            Emitter.this.writeStreamStart();
            Emitter emitter = Emitter.this;
            emitter.state = new ExpectFirstDocumentStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ExpectNothing implements EmitterState {
        private ExpectNothing() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            throw new EmitterException("expecting nothing, but got " + Emitter.this.event);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectFirstDocumentStart implements EmitterState {
        private ExpectFirstDocumentStart() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            new ExpectDocumentStart(true).expect();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectDocumentStart implements EmitterState {
        private boolean first;

        public ExpectDocumentStart(boolean z) {
            this.first = z;
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (Emitter.this.event instanceof DocumentStartEvent) {
                DocumentStartEvent documentStartEvent = (DocumentStartEvent) Emitter.this.event;
                if ((documentStartEvent.getVersion() != null || documentStartEvent.getTags() != null) && Emitter.this.openEnded) {
                    Emitter.this.writeIndicator("...", true, false, false);
                    Emitter.this.writeIndent();
                }
                if (documentStartEvent.getVersion() != null) {
                    Emitter.this.writeVersionDirective(Emitter.this.prepareVersion(documentStartEvent.getVersion()));
                }
                Emitter.this.tagPrefixes = new LinkedHashMap(Emitter.DEFAULT_TAG_PREFIXES);
                if (documentStartEvent.getTags() != null) {
                    for (String str : new TreeSet(documentStartEvent.getTags().keySet())) {
                        String str2 = documentStartEvent.getTags().get(str);
                        Emitter.this.tagPrefixes.put(str2, str);
                        Emitter.this.writeTagDirective(Emitter.this.prepareTagHandle(str), Emitter.this.prepareTagPrefix(str2));
                    }
                }
                if (!(this.first && !documentStartEvent.getExplicit() && !Emitter.this.canonical.booleanValue() && documentStartEvent.getVersion() == null && (documentStartEvent.getTags() == null || documentStartEvent.getTags().isEmpty()) && !Emitter.this.checkEmptyDocument())) {
                    Emitter.this.writeIndent();
                    Emitter.this.writeIndicator("---", true, false, false);
                    if (Emitter.this.canonical.booleanValue()) {
                        Emitter.this.writeIndent();
                    }
                }
                Emitter emitter = Emitter.this;
                emitter.state = new ExpectDocumentRoot();
                return;
            }
            if (!(Emitter.this.event instanceof StreamEndEvent)) {
                throw new EmitterException("expected DocumentStartEvent, but got " + Emitter.this.event);
            }
            Emitter.this.writeStreamEnd();
            Emitter emitter2 = Emitter.this;
            emitter2.state = new ExpectNothing();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectDocumentEnd implements EmitterState {
        private ExpectDocumentEnd() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (!(Emitter.this.event instanceof DocumentEndEvent)) {
                throw new EmitterException("expected DocumentEndEvent, but got " + Emitter.this.event);
            }
            Emitter.this.writeIndent();
            if (((DocumentEndEvent) Emitter.this.event).getExplicit()) {
                Emitter.this.writeIndicator("...", true, false, false);
                Emitter.this.writeIndent();
            }
            Emitter.this.flushStream();
            Emitter emitter = Emitter.this;
            emitter.state = new ExpectDocumentStart(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ExpectDocumentRoot implements EmitterState {
        private ExpectDocumentRoot() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            Emitter.this.states.push(new ExpectDocumentEnd());
            Emitter.this.expectNode(true, false, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void expectNode(boolean z, boolean z2, boolean z3) throws IOException {
        this.rootContext = z;
        this.mappingContext = z2;
        this.simpleKeyContext = z3;
        Event event = this.event;
        if (event instanceof AliasEvent) {
            expectAlias();
            return;
        }
        if ((event instanceof ScalarEvent) || (event instanceof CollectionStartEvent)) {
            processAnchor("&");
            processTag();
            Event event2 = this.event;
            if (event2 instanceof ScalarEvent) {
                expectScalar();
                return;
            }
            if (event2 instanceof SequenceStartEvent) {
                if (this.flowLevel != 0 || this.canonical.booleanValue() || ((SequenceStartEvent) this.event).getFlowStyle().booleanValue() || checkEmptySequence()) {
                    expectFlowSequence();
                    return;
                } else {
                    expectBlockSequence();
                    return;
                }
            }
            if (this.flowLevel != 0 || this.canonical.booleanValue() || ((MappingStartEvent) this.event).getFlowStyle().booleanValue() || checkEmptyMapping()) {
                expectFlowMapping();
                return;
            } else {
                expectBlockMapping();
                return;
            }
        }
        throw new EmitterException("expected NodeEvent, but got " + this.event);
    }

    private void expectAlias() throws IOException {
        if (((NodeEvent) this.event).getAnchor() == null) {
            throw new EmitterException("anchor is not specified for alias");
        }
        processAnchor("*");
        this.state = this.states.pop();
    }

    private void expectScalar() throws IOException {
        increaseIndent(true, false);
        processScalar();
        this.indent = this.indents.pop();
        this.state = this.states.pop();
    }

    private void expectFlowSequence() throws IOException {
        writeIndicator("[", true, true, false);
        this.flowLevel++;
        increaseIndent(true, false);
        if (this.prettyFlow.booleanValue()) {
            writeIndent();
        }
        this.state = new ExpectFirstFlowSequenceItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ExpectFirstFlowSequenceItem implements EmitterState {
        private ExpectFirstFlowSequenceItem() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (!(Emitter.this.event instanceof SequenceEndEvent)) {
                if (Emitter.this.canonical.booleanValue() || ((Emitter.this.column > Emitter.this.bestWidth && Emitter.this.splitLines) || Emitter.this.prettyFlow.booleanValue())) {
                    Emitter.this.writeIndent();
                }
                Emitter.this.states.push(new ExpectFlowSequenceItem());
                Emitter.this.expectNode(false, false, false);
                return;
            }
            Emitter emitter = Emitter.this;
            emitter.indent = (Integer) emitter.indents.pop();
            Emitter.access$2010(Emitter.this);
            Emitter.this.writeIndicator("]", false, false, false);
            Emitter emitter2 = Emitter.this;
            emitter2.state = (EmitterState) emitter2.states.pop();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectFlowSequenceItem implements EmitterState {
        private ExpectFlowSequenceItem() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (Emitter.this.event instanceof SequenceEndEvent) {
                Emitter emitter = Emitter.this;
                emitter.indent = (Integer) emitter.indents.pop();
                Emitter.access$2010(Emitter.this);
                if (Emitter.this.canonical.booleanValue()) {
                    Emitter.this.writeIndicator(",", false, false, false);
                    Emitter.this.writeIndent();
                }
                Emitter.this.writeIndicator("]", false, false, false);
                if (Emitter.this.prettyFlow.booleanValue()) {
                    Emitter.this.writeIndent();
                }
                Emitter emitter2 = Emitter.this;
                emitter2.state = (EmitterState) emitter2.states.pop();
                return;
            }
            Emitter.this.writeIndicator(",", false, false, false);
            if (Emitter.this.canonical.booleanValue() || ((Emitter.this.column > Emitter.this.bestWidth && Emitter.this.splitLines) || Emitter.this.prettyFlow.booleanValue())) {
                Emitter.this.writeIndent();
            }
            Emitter.this.states.push(new ExpectFlowSequenceItem());
            Emitter.this.expectNode(false, false, false);
        }
    }

    private void expectFlowMapping() throws IOException {
        writeIndicator("{", true, true, false);
        this.flowLevel++;
        increaseIndent(true, false);
        if (this.prettyFlow.booleanValue()) {
            writeIndent();
        }
        this.state = new ExpectFirstFlowMappingKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ExpectFirstFlowMappingKey implements EmitterState {
        private ExpectFirstFlowMappingKey() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (!(Emitter.this.event instanceof MappingEndEvent)) {
                if (Emitter.this.canonical.booleanValue() || ((Emitter.this.column > Emitter.this.bestWidth && Emitter.this.splitLines) || Emitter.this.prettyFlow.booleanValue())) {
                    Emitter.this.writeIndent();
                }
                if (!Emitter.this.canonical.booleanValue() && Emitter.this.checkSimpleKey()) {
                    Emitter.this.states.push(new ExpectFlowMappingSimpleValue());
                    Emitter.this.expectNode(false, true, true);
                    return;
                } else {
                    Emitter.this.writeIndicator("?", true, false, false);
                    Emitter.this.states.push(new ExpectFlowMappingValue());
                    Emitter.this.expectNode(false, true, false);
                    return;
                }
            }
            Emitter emitter = Emitter.this;
            emitter.indent = (Integer) emitter.indents.pop();
            Emitter.access$2010(Emitter.this);
            Emitter.this.writeIndicator("}", false, false, false);
            Emitter emitter2 = Emitter.this;
            emitter2.state = (EmitterState) emitter2.states.pop();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectFlowMappingKey implements EmitterState {
        private ExpectFlowMappingKey() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (Emitter.this.event instanceof MappingEndEvent) {
                Emitter emitter = Emitter.this;
                emitter.indent = (Integer) emitter.indents.pop();
                Emitter.access$2010(Emitter.this);
                if (Emitter.this.canonical.booleanValue()) {
                    Emitter.this.writeIndicator(",", false, false, false);
                    Emitter.this.writeIndent();
                }
                if (Emitter.this.prettyFlow.booleanValue()) {
                    Emitter.this.writeIndent();
                }
                Emitter.this.writeIndicator("}", false, false, false);
                Emitter emitter2 = Emitter.this;
                emitter2.state = (EmitterState) emitter2.states.pop();
                return;
            }
            Emitter.this.writeIndicator(",", false, false, false);
            if (Emitter.this.canonical.booleanValue() || ((Emitter.this.column > Emitter.this.bestWidth && Emitter.this.splitLines) || Emitter.this.prettyFlow.booleanValue())) {
                Emitter.this.writeIndent();
            }
            if (!Emitter.this.canonical.booleanValue() && Emitter.this.checkSimpleKey()) {
                Emitter.this.states.push(new ExpectFlowMappingSimpleValue());
                Emitter.this.expectNode(false, true, true);
            } else {
                Emitter.this.writeIndicator("?", true, false, false);
                Emitter.this.states.push(new ExpectFlowMappingValue());
                Emitter.this.expectNode(false, true, false);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectFlowMappingSimpleValue implements EmitterState {
        private ExpectFlowMappingSimpleValue() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            Emitter.this.writeIndicator(":", false, false, false);
            Emitter.this.states.push(new ExpectFlowMappingKey());
            Emitter.this.expectNode(false, true, false);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectFlowMappingValue implements EmitterState {
        private ExpectFlowMappingValue() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (Emitter.this.canonical.booleanValue() || Emitter.this.column > Emitter.this.bestWidth || Emitter.this.prettyFlow.booleanValue()) {
                Emitter.this.writeIndent();
            }
            Emitter.this.writeIndicator(":", true, false, false);
            Emitter.this.states.push(new ExpectFlowMappingKey());
            Emitter.this.expectNode(false, true, false);
        }
    }

    private void expectBlockSequence() throws IOException {
        increaseIndent(false, this.mappingContext && !this.indention);
        this.state = new ExpectFirstBlockSequenceItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ExpectFirstBlockSequenceItem implements EmitterState {
        private ExpectFirstBlockSequenceItem() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            new ExpectBlockSequenceItem(true).expect();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectBlockSequenceItem implements EmitterState {
        private boolean first;

        public ExpectBlockSequenceItem(boolean z) {
            this.first = z;
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (!this.first && (Emitter.this.event instanceof SequenceEndEvent)) {
                Emitter emitter = Emitter.this;
                emitter.indent = (Integer) emitter.indents.pop();
                Emitter emitter2 = Emitter.this;
                emitter2.state = (EmitterState) emitter2.states.pop();
                return;
            }
            Emitter.this.writeIndent();
            Emitter emitter3 = Emitter.this;
            emitter3.writeWhitespace(emitter3.indicatorIndent);
            Emitter.this.writeIndicator("-", true, false, true);
            Emitter.this.states.push(new ExpectBlockSequenceItem(false));
            Emitter.this.expectNode(false, false, false);
        }
    }

    private void expectBlockMapping() throws IOException {
        increaseIndent(false, false);
        this.state = new ExpectFirstBlockMappingKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ExpectFirstBlockMappingKey implements EmitterState {
        private ExpectFirstBlockMappingKey() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            new ExpectBlockMappingKey(true).expect();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ExpectBlockMappingKey implements EmitterState {
        private boolean first;

        public ExpectBlockMappingKey(boolean z) {
            this.first = z;
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            if (!this.first && (Emitter.this.event instanceof MappingEndEvent)) {
                Emitter emitter = Emitter.this;
                emitter.indent = (Integer) emitter.indents.pop();
                Emitter emitter2 = Emitter.this;
                emitter2.state = (EmitterState) emitter2.states.pop();
                return;
            }
            Emitter.this.writeIndent();
            if (Emitter.this.checkSimpleKey()) {
                Emitter.this.states.push(new ExpectBlockMappingSimpleValue());
                Emitter.this.expectNode(false, true, true);
            } else {
                Emitter.this.writeIndicator("?", true, false, true);
                Emitter.this.states.push(new ExpectBlockMappingValue());
                Emitter.this.expectNode(false, true, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ExpectBlockMappingSimpleValue implements EmitterState {
        private ExpectBlockMappingSimpleValue() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            Emitter.this.writeIndicator(":", false, false, false);
            Emitter.this.states.push(new ExpectBlockMappingKey(false));
            Emitter.this.expectNode(false, true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ExpectBlockMappingValue implements EmitterState {
        private ExpectBlockMappingValue() {
        }

        @Override // org.yaml.snakeyaml.emitter.EmitterState
        public void expect() throws IOException {
            Emitter.this.writeIndent();
            Emitter.this.writeIndicator(":", true, false, true);
            Emitter.this.states.push(new ExpectBlockMappingKey(false));
            Emitter.this.expectNode(false, true, false);
        }
    }

    private boolean checkEmptySequence() {
        return (this.event instanceof SequenceStartEvent) && !this.events.isEmpty() && (this.events.peek() instanceof SequenceEndEvent);
    }

    private boolean checkEmptyMapping() {
        return (this.event instanceof MappingStartEvent) && !this.events.isEmpty() && (this.events.peek() instanceof MappingEndEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkEmptyDocument() {
        if (!(this.event instanceof DocumentStartEvent) || this.events.isEmpty()) {
            return false;
        }
        Event peek = this.events.peek();
        if (!(peek instanceof ScalarEvent)) {
            return false;
        }
        ScalarEvent scalarEvent = (ScalarEvent) peek;
        return scalarEvent.getAnchor() == null && scalarEvent.getTag() == null && scalarEvent.getImplicit() != null && scalarEvent.getValue().length() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkSimpleKey() {
        int i;
        Event event = this.event;
        if (!(event instanceof NodeEvent) || ((NodeEvent) event).getAnchor() == null) {
            i = 0;
        } else {
            if (this.preparedAnchor == null) {
                this.preparedAnchor = prepareAnchor(((NodeEvent) this.event).getAnchor());
            }
            i = this.preparedAnchor.length() + 0;
        }
        String str = null;
        Event event2 = this.event;
        if (event2 instanceof ScalarEvent) {
            str = ((ScalarEvent) event2).getTag();
        } else if (event2 instanceof CollectionStartEvent) {
            str = ((CollectionStartEvent) event2).getTag();
        }
        if (str != null) {
            if (this.preparedTag == null) {
                this.preparedTag = prepareTag(str);
            }
            i += this.preparedTag.length();
        }
        Event event3 = this.event;
        if (event3 instanceof ScalarEvent) {
            if (this.analysis == null) {
                this.analysis = analyzeScalar(((ScalarEvent) event3).getValue());
            }
            i += this.analysis.scalar.length();
        }
        if (i >= 128) {
            return false;
        }
        Event event4 = this.event;
        return (event4 instanceof AliasEvent) || !(!(event4 instanceof ScalarEvent) || this.analysis.empty || this.analysis.multiline) || checkEmptySequence() || checkEmptyMapping();
    }

    private void processAnchor(String str) throws IOException {
        NodeEvent nodeEvent = (NodeEvent) this.event;
        if (nodeEvent.getAnchor() == null) {
            this.preparedAnchor = null;
            return;
        }
        if (this.preparedAnchor == null) {
            this.preparedAnchor = prepareAnchor(nodeEvent.getAnchor());
        }
        writeIndicator(str + this.preparedAnchor, true, false, false);
        this.preparedAnchor = null;
    }

    private void processTag() throws IOException {
        String tag;
        Event event = this.event;
        if (event instanceof ScalarEvent) {
            ScalarEvent scalarEvent = (ScalarEvent) event;
            tag = scalarEvent.getTag();
            if (this.style == null) {
                this.style = chooseScalarStyle();
            }
            if ((!this.canonical.booleanValue() || tag == null) && ((this.style == null && scalarEvent.getImplicit().canOmitTagInPlainScalar()) || (this.style != null && scalarEvent.getImplicit().canOmitTagInNonPlainScalar()))) {
                this.preparedTag = null;
                return;
            } else if (scalarEvent.getImplicit().canOmitTagInPlainScalar() && tag == null) {
                this.preparedTag = null;
                tag = "!";
            }
        } else {
            CollectionStartEvent collectionStartEvent = (CollectionStartEvent) event;
            tag = collectionStartEvent.getTag();
            if ((!this.canonical.booleanValue() || tag == null) && collectionStartEvent.getImplicit()) {
                this.preparedTag = null;
                return;
            }
        }
        if (tag == null) {
            throw new EmitterException("tag is not specified");
        }
        if (this.preparedTag == null) {
            this.preparedTag = prepareTag(tag);
        }
        writeIndicator(this.preparedTag, true, false, false);
        this.preparedTag = null;
    }

    private Character chooseScalarStyle() {
        ScalarEvent scalarEvent = (ScalarEvent) this.event;
        if (this.analysis == null) {
            this.analysis = analyzeScalar(scalarEvent.getValue());
        }
        if ((scalarEvent.getStyle() != null && scalarEvent.getStyle().charValue() == '\"') || this.canonical.booleanValue()) {
            return '\"';
        }
        if (scalarEvent.getStyle() == null && scalarEvent.getImplicit().canOmitTagInPlainScalar() && (!this.simpleKeyContext || (!this.analysis.empty && !this.analysis.multiline))) {
            if (this.flowLevel != 0 && this.analysis.allowFlowPlain) {
                return null;
            }
            if (this.flowLevel == 0 && this.analysis.allowBlockPlain) {
                return null;
            }
        }
        if (scalarEvent.getStyle() != null && ((scalarEvent.getStyle().charValue() == '|' || scalarEvent.getStyle().charValue() == '>') && this.flowLevel == 0 && !this.simpleKeyContext && this.analysis.allowBlock)) {
            return scalarEvent.getStyle();
        }
        if ((scalarEvent.getStyle() == null || scalarEvent.getStyle().charValue() == '\'') && this.analysis.allowSingleQuoted && (!this.simpleKeyContext || !this.analysis.multiline)) {
            return '\'';
        }
        return '\"';
    }

    private void processScalar() throws IOException {
        ScalarEvent scalarEvent = (ScalarEvent) this.event;
        if (this.analysis == null) {
            this.analysis = analyzeScalar(scalarEvent.getValue());
        }
        if (this.style == null) {
            this.style = chooseScalarStyle();
        }
        boolean z = !this.simpleKeyContext && this.splitLines;
        Character ch = this.style;
        if (ch == null) {
            writePlain(this.analysis.scalar, z);
        } else {
            char charValue = ch.charValue();
            if (charValue == '\"') {
                writeDoubleQuoted(this.analysis.scalar, z);
            } else if (charValue == '\'') {
                writeSingleQuoted(this.analysis.scalar, z);
            } else if (charValue == '>') {
                writeFolded(this.analysis.scalar, z);
            } else if (charValue == '|') {
                writeLiteral(this.analysis.scalar);
            } else {
                throw new YAMLException("Unexpected style: " + this.style);
            }
        }
        this.analysis = null;
        this.style = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String prepareVersion(DumperOptions.Version version) {
        if (version.major() != 1) {
            throw new EmitterException("unsupported YAML version: " + version);
        }
        return version.getRepresentation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String prepareTagHandle(String str) {
        if (str.length() == 0) {
            throw new EmitterException("tag handle must not be empty");
        }
        if (str.charAt(0) != '!' || str.charAt(str.length() - 1) != '!') {
            throw new EmitterException("tag handle must start and end with '!': " + str);
        }
        if ("!".equals(str) || HANDLE_FORMAT.matcher(str).matches()) {
            return str;
        }
        throw new EmitterException("invalid character in the tag handle: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String prepareTagPrefix(String str) {
        if (str.length() == 0) {
            throw new EmitterException("tag prefix must not be empty");
        }
        StringBuilder sb = new StringBuilder();
        int i = str.charAt(0) == '!' ? 1 : 0;
        while (i < str.length()) {
            i++;
        }
        if (i > 0) {
            sb.append(str.substring(0, i));
        }
        return sb.toString();
    }

    private String prepareTag(String str) {
        if (str.length() == 0) {
            throw new EmitterException("tag must not be empty");
        }
        if ("!".equals(str)) {
            return str;
        }
        String str2 = null;
        for (String str3 : this.tagPrefixes.keySet()) {
            if (str.startsWith(str3) && ("!".equals(str3) || str3.length() < str.length())) {
                str2 = str3;
            }
        }
        if (str2 != null) {
            str = str.substring(str2.length());
            str2 = this.tagPrefixes.get(str2);
        }
        int length = str.length();
        String substring = length > 0 ? str.substring(0, length) : "";
        if (str2 != null) {
            return str2 + substring;
        }
        return "!<" + substring + ">";
    }

    static String prepareAnchor(String str) {
        if (str.length() == 0) {
            throw new EmitterException("anchor must not be empty");
        }
        if (ANCHOR_FORMAT.matcher(str).matches()) {
            return str;
        }
        throw new EmitterException("invalid character in the anchor: " + str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:90:0x00df, code lost:
    
        if (r20.allowUnicode == false) goto L81;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0128 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ScalarAnalysis analyzeScalar(String str) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean has;
        char c;
        boolean z9;
        if (str.length() == 0) {
            return new ScalarAnalysis(str, true, false, false, true, true, false);
        }
        if (str.startsWith("---") || str.startsWith("...")) {
            z = true;
            z2 = true;
        } else {
            z = false;
            z2 = false;
        }
        boolean z10 = z;
        boolean z11 = true;
        boolean z12 = z2;
        boolean z13 = str.length() == 1 || Constant.NULL_BL_T_LINEBR.has(str.charAt(1));
        int i = 0;
        boolean z14 = false;
        boolean z15 = false;
        boolean z16 = false;
        boolean z17 = false;
        boolean z18 = false;
        boolean z19 = false;
        boolean z20 = false;
        boolean z21 = false;
        boolean z22 = false;
        boolean z23 = false;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (i == 0) {
                if ("#,[]{}&*!|>'\"%@`".indexOf(charAt) != -1) {
                    z9 = true;
                    z12 = true;
                } else {
                    z9 = z10;
                }
                if (charAt == '?' || charAt == ':') {
                    if (z13) {
                        z9 = true;
                    }
                    z12 = true;
                }
                if (charAt != '-' || !z13) {
                    z10 = z9;
                    has = Constant.LINEBR.has(charAt);
                    if (has) {
                        z21 = true;
                    }
                    if (charAt == '\n' && (' ' > charAt || charAt > '~')) {
                        if ((charAt == 133 || ((160 <= charAt && charAt <= 55295) || (57344 <= charAt && charAt <= 65533))) && charAt != 65279) {
                        }
                        c = ' ';
                        z20 = true;
                        if (charAt == c) {
                            if (i == 0) {
                                z14 = true;
                            }
                            if (i == str.length() - 1) {
                                z16 = true;
                            }
                            if (z23) {
                                z19 = true;
                            }
                            z22 = true;
                        } else if (has) {
                            if (i == 0) {
                                z15 = true;
                            }
                            if (i == str.length() - 1) {
                                z17 = true;
                            }
                            if (z22) {
                                z18 = true;
                            }
                            z22 = false;
                            z23 = true;
                            int i2 = i + 1;
                            z11 = !Constant.NULL_BL_T.has(charAt) || has;
                            int i3 = i2 + 1;
                            z13 = i3 < str.length() || Constant.NULL_BL_T.has(str.charAt(i3)) || has;
                            i = i2;
                        } else {
                            z22 = false;
                        }
                        z23 = false;
                        int i22 = i + 1;
                        if (Constant.NULL_BL_T.has(charAt)) {
                        }
                        int i32 = i22 + 1;
                        z13 = i32 < str.length() || Constant.NULL_BL_T.has(str.charAt(i32)) || has;
                        i = i22;
                    }
                    c = ' ';
                    if (charAt == c) {
                    }
                    z23 = false;
                    int i222 = i + 1;
                    if (Constant.NULL_BL_T.has(charAt)) {
                    }
                    int i322 = i222 + 1;
                    z13 = i322 < str.length() || Constant.NULL_BL_T.has(str.charAt(i322)) || has;
                    i = i222;
                }
                z12 = true;
                z10 = true;
                has = Constant.LINEBR.has(charAt);
                if (has) {
                }
                if (charAt == '\n') {
                }
                c = ' ';
                if (charAt == c) {
                }
                z23 = false;
                int i2222 = i + 1;
                if (Constant.NULL_BL_T.has(charAt)) {
                }
                int i3222 = i2222 + 1;
                z13 = i3222 < str.length() || Constant.NULL_BL_T.has(str.charAt(i3222)) || has;
                i = i2222;
            } else {
                boolean z24 = z10;
                boolean z25 = ",?[]{}".indexOf(charAt) != -1 ? true : z12;
                if (charAt == ':') {
                    z8 = z13 ? true : z24;
                    z7 = true;
                } else {
                    z7 = z25;
                    z8 = z24;
                }
                if (charAt != '#' || !z11) {
                    z10 = z8;
                    z12 = z7;
                    has = Constant.LINEBR.has(charAt);
                    if (has) {
                    }
                    if (charAt == '\n') {
                    }
                    c = ' ';
                    if (charAt == c) {
                    }
                    z23 = false;
                    int i22222 = i + 1;
                    if (Constant.NULL_BL_T.has(charAt)) {
                    }
                    int i32222 = i22222 + 1;
                    z13 = i32222 < str.length() || Constant.NULL_BL_T.has(str.charAt(i32222)) || has;
                    i = i22222;
                }
                z12 = true;
                z10 = true;
                has = Constant.LINEBR.has(charAt);
                if (has) {
                }
                if (charAt == '\n') {
                }
                c = ' ';
                if (charAt == c) {
                }
                z23 = false;
                int i222222 = i + 1;
                if (Constant.NULL_BL_T.has(charAt)) {
                }
                int i322222 = i222222 + 1;
                z13 = i322222 < str.length() || Constant.NULL_BL_T.has(str.charAt(i322222)) || has;
                i = i222222;
            }
        }
        boolean z26 = z10;
        if (z14 || z15 || z16 || z17) {
            z3 = false;
            z4 = false;
        } else {
            z3 = true;
            z4 = true;
        }
        boolean z27 = !z16;
        if (z19) {
            z3 = false;
            z4 = false;
        }
        boolean z28 = true ^ z19;
        if (z18 || z20) {
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
        } else {
            z5 = z28;
            z6 = z27;
        }
        if (z21) {
            z4 = false;
        }
        return new ScalarAnalysis(str, false, z21, z12 ? false : z4, z26 ? false : z3, z5, z6);
    }

    void flushStream() throws IOException {
        this.stream.flush();
    }

    void writeStreamEnd() throws IOException {
        flushStream();
    }

    void writeIndicator(String str, boolean z, boolean z2, boolean z3) throws IOException {
        if (!this.whitespace && z) {
            this.column++;
            this.stream.write(SPACE);
        }
        this.whitespace = z2;
        this.indention = this.indention && z3;
        this.column += str.length();
        this.openEnded = false;
        this.stream.write(str);
    }

    void writeIndent() throws IOException {
        int i;
        Integer num = this.indent;
        int intValue = num != null ? num.intValue() : 0;
        if (!this.indention || (i = this.column) > intValue || (i == intValue && !this.whitespace)) {
            writeLineBreak(null);
        }
        writeWhitespace(intValue - this.column);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeWhitespace(int i) throws IOException {
        if (i <= 0) {
            return;
        }
        this.whitespace = true;
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = ' ';
        }
        this.column += i;
        this.stream.write(cArr);
    }

    private void writeLineBreak(String str) throws IOException {
        this.whitespace = true;
        this.indention = true;
        this.column = 0;
        if (str == null) {
            this.stream.write(this.bestLineBreak);
        } else {
            this.stream.write(str);
        }
    }

    void writeVersionDirective(String str) throws IOException {
        this.stream.write("%YAML ");
        this.stream.write(str);
        writeLineBreak(null);
    }

    void writeTagDirective(String str, String str2) throws IOException {
        this.stream.write("%TAG ");
        this.stream.write(str);
        this.stream.write(SPACE);
        this.stream.write(str2);
        writeLineBreak(null);
    }

    private void writeSingleQuoted(String str, boolean z) throws IOException {
        writeIndicator("'", true, false, false);
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        while (i <= str.length()) {
            char charAt = i < str.length() ? str.charAt(i) : (char) 0;
            if (z2) {
                if (charAt == 0 || charAt != ' ') {
                    if (i2 + 1 == i && this.column > this.bestWidth && z && i2 != 0 && i != str.length()) {
                        writeIndent();
                    } else {
                        int i3 = i - i2;
                        this.column += i3;
                        this.stream.write(str, i2, i3);
                    }
                    i2 = i;
                }
            } else if (z3) {
                if (charAt == 0 || Constant.LINEBR.hasNo(charAt)) {
                    if (str.charAt(i2) == '\n') {
                        writeLineBreak(null);
                    }
                    for (char c : str.substring(i2, i).toCharArray()) {
                        if (c == '\n') {
                            writeLineBreak(null);
                        } else {
                            writeLineBreak(String.valueOf(c));
                        }
                    }
                    writeIndent();
                    i2 = i;
                }
            } else if (Constant.LINEBR.has(charAt, "\u0000 '") && i2 < i) {
                int i4 = i - i2;
                this.column += i4;
                this.stream.write(str, i2, i4);
                i2 = i;
            }
            if (charAt == '\'') {
                this.column += 2;
                this.stream.write("''");
                i2 = i + 1;
            }
            if (charAt != 0) {
                z2 = charAt == ' ';
                z3 = Constant.LINEBR.has(charAt);
            }
            i++;
        }
        writeIndicator("'", false, false, false);
    }

    private void writeDoubleQuoted(String str, boolean z) throws IOException {
        String str2;
        String str3;
        writeIndicator("\"", true, false, false);
        int i = 0;
        int i2 = 0;
        while (i <= str.length()) {
            Character valueOf = i < str.length() ? Character.valueOf(str.charAt(i)) : null;
            if (valueOf == null || "\"\\\u0085\u2028\u2029\ufeff".indexOf(valueOf.charValue()) != -1 || ' ' > valueOf.charValue() || valueOf.charValue() > '~') {
                if (i2 < i) {
                    int i3 = i - i2;
                    this.column += i3;
                    this.stream.write(str, i2, i3);
                    i2 = i;
                }
                if (valueOf != null) {
                    if (ESCAPE_REPLACEMENTS.containsKey(valueOf)) {
                        str2 = "\\" + ESCAPE_REPLACEMENTS.get(valueOf);
                    } else if (!this.allowUnicode || !StreamReader.isPrintable(valueOf.charValue())) {
                        if (valueOf.charValue() <= 255) {
                            str2 = "\\x" + ("0" + Integer.toString(valueOf.charValue(), 16)).substring(r4.length() - 2);
                        } else if (valueOf.charValue() >= 55296 && valueOf.charValue() <= 56319) {
                            int i4 = i + 1;
                            if (i4 < str.length()) {
                                Character valueOf2 = Character.valueOf(str.charAt(i4));
                                str2 = "\\U" + ("000" + Long.toHexString(Character.toCodePoint(valueOf.charValue(), valueOf2.charValue()))).substring(r3.length() - 8);
                                i = i4;
                            } else {
                                str2 = "\\u" + ("000" + Integer.toString(valueOf.charValue(), 16)).substring(r4.length() - 4);
                            }
                        } else {
                            str2 = "\\u" + ("000" + Integer.toString(valueOf.charValue(), 16)).substring(r4.length() - 4);
                        }
                    } else {
                        str2 = String.valueOf(valueOf);
                    }
                    this.column += str2.length();
                    this.stream.write(str2);
                    i2 = i + 1;
                }
            }
            if (i > 0 && i < str.length() - 1 && ((valueOf.charValue() == ' ' || i2 >= i) && this.column + (i - i2) > this.bestWidth && z)) {
                if (i2 >= i) {
                    str3 = "\\";
                } else {
                    str3 = str.substring(i2, i) + "\\";
                }
                if (i2 < i) {
                    i2 = i;
                }
                this.column += str3.length();
                this.stream.write(str3);
                writeIndent();
                this.whitespace = false;
                this.indention = false;
                if (str.charAt(i2) == ' ') {
                    this.column++;
                    this.stream.write("\\");
                }
            }
            i++;
        }
        writeIndicator("\"", false, false, false);
    }

    private String determineBlockHints(String str) {
        StringBuilder sb = new StringBuilder();
        if (Constant.LINEBR.has(str.charAt(0), " ")) {
            sb.append(this.bestIndent);
        }
        if (Constant.LINEBR.hasNo(str.charAt(str.length() - 1))) {
            sb.append("-");
        } else if (str.length() == 1 || Constant.LINEBR.has(str.charAt(str.length() - 2))) {
            sb.append("+");
        }
        return sb.toString();
    }

    void writeFolded(String str, boolean z) throws IOException {
        String determineBlockHints = determineBlockHints(str);
        writeIndicator(">" + determineBlockHints, true, false, false);
        if (determineBlockHints.length() > 0 && determineBlockHints.charAt(determineBlockHints.length() - 1) == '+') {
            this.openEnded = true;
        }
        writeLineBreak(null);
        int i = 0;
        boolean z2 = false;
        int i2 = 0;
        boolean z3 = true;
        boolean z4 = true;
        while (i <= str.length()) {
            char charAt = i < str.length() ? str.charAt(i) : (char) 0;
            if (z3) {
                if (charAt == 0 || Constant.LINEBR.hasNo(charAt)) {
                    if (!z4 && charAt != 0 && charAt != ' ' && str.charAt(i2) == '\n') {
                        writeLineBreak(null);
                    }
                    z4 = charAt == ' ';
                    for (char c : str.substring(i2, i).toCharArray()) {
                        if (c == '\n') {
                            writeLineBreak(null);
                        } else {
                            writeLineBreak(String.valueOf(c));
                        }
                    }
                    if (charAt != 0) {
                        writeIndent();
                    }
                    i2 = i;
                }
            } else if (z2) {
                if (charAt != ' ') {
                    if (i2 + 1 == i && this.column > this.bestWidth && z) {
                        writeIndent();
                    } else {
                        int i3 = i - i2;
                        this.column += i3;
                        this.stream.write(str, i2, i3);
                    }
                    i2 = i;
                }
            } else if (Constant.LINEBR.has(charAt, "\u0000 ")) {
                int i4 = i - i2;
                this.column += i4;
                this.stream.write(str, i2, i4);
                if (charAt == 0) {
                    writeLineBreak(null);
                }
                i2 = i;
            }
            if (charAt != 0) {
                z3 = Constant.LINEBR.has(charAt);
                z2 = charAt == ' ';
            }
            i++;
        }
    }

    void writeLiteral(String str) throws IOException {
        String determineBlockHints = determineBlockHints(str);
        boolean z = true;
        writeIndicator("|" + determineBlockHints, true, false, false);
        if (determineBlockHints.length() > 0 && determineBlockHints.charAt(determineBlockHints.length() - 1) == '+') {
            this.openEnded = true;
        }
        writeLineBreak(null);
        int i = 0;
        int i2 = 0;
        while (i <= str.length()) {
            char charAt = i < str.length() ? str.charAt(i) : (char) 0;
            if (z) {
                if (charAt == 0 || Constant.LINEBR.hasNo(charAt)) {
                    for (char c : str.substring(i2, i).toCharArray()) {
                        if (c == '\n') {
                            writeLineBreak(null);
                        } else {
                            writeLineBreak(String.valueOf(c));
                        }
                    }
                    if (charAt != 0) {
                        writeIndent();
                    }
                    i2 = i;
                }
            } else if (charAt == 0 || Constant.LINEBR.has(charAt)) {
                this.stream.write(str, i2, i - i2);
                if (charAt == 0) {
                    writeLineBreak(null);
                }
                i2 = i;
            }
            if (charAt != 0) {
                z = Constant.LINEBR.has(charAt);
            }
            i++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void writePlain(String str, boolean z) throws IOException {
        if (this.rootContext) {
            this.openEnded = true;
        }
        if (str.length() == 0) {
            return;
        }
        if (!this.whitespace) {
            this.column++;
            this.stream.write(SPACE);
        }
        this.whitespace = false;
        this.indention = false;
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        while (i <= str.length()) {
            char charAt = i < str.length() ? str.charAt(i) : (char) 0;
            if (z2) {
                if (charAt != ' ') {
                    if (i2 + 1 == i && this.column > this.bestWidth && z) {
                        writeIndent();
                        this.whitespace = false;
                        this.indention = false;
                    } else {
                        int i3 = i - i2;
                        this.column += i3;
                        this.stream.write(str, i2, i3);
                    }
                    i2 = i;
                }
                if (charAt != 0) {
                    z2 = charAt == ' ';
                    z3 = Constant.LINEBR.has(charAt);
                }
                i++;
            } else if (z3) {
                if (Constant.LINEBR.hasNo(charAt)) {
                    if (str.charAt(i2) == '\n') {
                        writeLineBreak(null);
                    }
                    for (char c : str.substring(i2, i).toCharArray()) {
                        if (c == '\n') {
                            writeLineBreak(null);
                        } else {
                            writeLineBreak(String.valueOf(c));
                        }
                    }
                    writeIndent();
                    this.whitespace = false;
                    this.indention = false;
                    i2 = i;
                }
                if (charAt != 0) {
                }
                i++;
            } else {
                if (charAt == 0 || Constant.LINEBR.has(charAt)) {
                    int i4 = i - i2;
                    this.column += i4;
                    this.stream.write(str, i2, i4);
                    i2 = i;
                }
                if (charAt != 0) {
                }
                i++;
            }
        }
    }
}
