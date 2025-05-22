package org.yaml.snakeyaml.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.events.DocumentEndEvent;
import org.yaml.snakeyaml.events.DocumentStartEvent;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.ImplicitTuple;
import org.yaml.snakeyaml.events.MappingEndEvent;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.SequenceEndEvent;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.events.StreamEndEvent;
import org.yaml.snakeyaml.events.StreamStartEvent;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.scanner.Scanner;
import org.yaml.snakeyaml.scanner.ScannerImpl;
import org.yaml.snakeyaml.tokens.AliasToken;
import org.yaml.snakeyaml.tokens.AnchorToken;
import org.yaml.snakeyaml.tokens.BlockEntryToken;
import org.yaml.snakeyaml.tokens.DirectiveToken;
import org.yaml.snakeyaml.tokens.ScalarToken;
import org.yaml.snakeyaml.tokens.StreamEndToken;
import org.yaml.snakeyaml.tokens.StreamStartToken;
import org.yaml.snakeyaml.tokens.TagToken;
import org.yaml.snakeyaml.tokens.TagTuple;
import org.yaml.snakeyaml.tokens.Token;
import org.yaml.snakeyaml.util.ArrayStack;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ParserImpl implements Parser {
    private static final Map<String, String> DEFAULT_TAGS = new HashMap();
    private Event currentEvent;
    private VersionTagsTuple directives;
    private final ArrayStack<Mark> marks;
    protected final Scanner scanner;
    private Production state;
    private final ArrayStack<Production> states;

    static {
        DEFAULT_TAGS.put("!", "!");
        DEFAULT_TAGS.put("!!", Tag.PREFIX);
    }

    public ParserImpl(StreamReader streamReader) {
        this(new ScannerImpl(streamReader));
    }

    public ParserImpl(Scanner scanner) {
        this.scanner = scanner;
        this.currentEvent = null;
        this.directives = new VersionTagsTuple(null, new HashMap(DEFAULT_TAGS));
        this.states = new ArrayStack<>(100);
        this.marks = new ArrayStack<>(10);
        this.state = new ParseStreamStart();
    }

    @Override // org.yaml.snakeyaml.parser.Parser
    public boolean checkEvent(Event.EnumC8987ID enumC8987ID) {
        peekEvent();
        Event event = this.currentEvent;
        return event != null && event.mo4255is(enumC8987ID);
    }

    @Override // org.yaml.snakeyaml.parser.Parser
    public Event peekEvent() {
        Production production;
        if (this.currentEvent == null && (production = this.state) != null) {
            this.currentEvent = production.produce();
        }
        return this.currentEvent;
    }

    @Override // org.yaml.snakeyaml.parser.Parser
    public Event getEvent() {
        peekEvent();
        Event event = this.currentEvent;
        this.currentEvent = null;
        return event;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseStreamStart implements Production {
        private ParseStreamStart() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            StreamStartToken streamStartToken = (StreamStartToken) ParserImpl.this.scanner.getToken();
            StreamStartEvent streamStartEvent = new StreamStartEvent(streamStartToken.getStartMark(), streamStartToken.getEndMark());
            ParserImpl parserImpl = ParserImpl.this;
            parserImpl.state = new ParseImplicitDocumentStart();
            return streamStartEvent;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseImplicitDocumentStart implements Production {
        private ParseImplicitDocumentStart() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Directive, Token.EnumC8994ID.DocumentStart, Token.EnumC8994ID.StreamEnd)) {
                ParserImpl.this.directives = new VersionTagsTuple(null, ParserImpl.DEFAULT_TAGS);
                Mark startMark = ParserImpl.this.scanner.peekToken().getStartMark();
                DocumentStartEvent documentStartEvent = new DocumentStartEvent(startMark, startMark, false, null, null);
                ParserImpl.this.states.push(new ParseDocumentEnd());
                ParserImpl parserImpl = ParserImpl.this;
                parserImpl.state = new ParseBlockNode();
                return documentStartEvent;
            }
            return new ParseDocumentStart().produce();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseDocumentStart implements Production {
        private ParseDocumentStart() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            while (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.DocumentEnd)) {
                ParserImpl.this.scanner.getToken();
            }
            if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.StreamEnd)) {
                Mark startMark = ParserImpl.this.scanner.peekToken().getStartMark();
                VersionTagsTuple processDirectives = ParserImpl.this.processDirectives();
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.DocumentStart)) {
                    throw new ParserException(null, null, "expected '<document start>', but found " + ParserImpl.this.scanner.peekToken().getTokenId(), ParserImpl.this.scanner.peekToken().getStartMark());
                }
                DocumentStartEvent documentStartEvent = new DocumentStartEvent(startMark, ParserImpl.this.scanner.getToken().getEndMark(), true, processDirectives.getVersion(), processDirectives.getTags());
                ParserImpl.this.states.push(new ParseDocumentEnd());
                ParserImpl parserImpl = ParserImpl.this;
                parserImpl.state = new ParseDocumentContent();
                return documentStartEvent;
            }
            StreamEndToken streamEndToken = (StreamEndToken) ParserImpl.this.scanner.getToken();
            StreamEndEvent streamEndEvent = new StreamEndEvent(streamEndToken.getStartMark(), streamEndToken.getEndMark());
            if (ParserImpl.this.states.isEmpty()) {
                if (ParserImpl.this.marks.isEmpty()) {
                    ParserImpl.this.state = null;
                    return streamEndEvent;
                }
                throw new YAMLException("Unexpected end of stream. Marks left: " + ParserImpl.this.marks);
            }
            throw new YAMLException("Unexpected end of stream. States left: " + ParserImpl.this.states);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseDocumentEnd implements Production {
        private ParseDocumentEnd() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            Mark mark;
            Mark startMark = ParserImpl.this.scanner.peekToken().getStartMark();
            boolean z = true;
            if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.DocumentEnd)) {
                mark = ParserImpl.this.scanner.getToken().getEndMark();
            } else {
                mark = startMark;
                z = false;
            }
            DocumentEndEvent documentEndEvent = new DocumentEndEvent(startMark, mark, z);
            ParserImpl parserImpl = ParserImpl.this;
            parserImpl.state = new ParseDocumentStart();
            return documentEndEvent;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseDocumentContent implements Production {
        private ParseDocumentContent() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Directive, Token.EnumC8994ID.DocumentStart, Token.EnumC8994ID.DocumentEnd, Token.EnumC8994ID.StreamEnd)) {
                ParserImpl parserImpl = ParserImpl.this;
                Event processEmptyScalar = parserImpl.processEmptyScalar(parserImpl.scanner.peekToken().getStartMark());
                ParserImpl parserImpl2 = ParserImpl.this;
                parserImpl2.state = (Production) parserImpl2.states.pop();
                return processEmptyScalar;
            }
            return new ParseBlockNode().produce();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VersionTagsTuple processDirectives() {
        HashMap hashMap = new HashMap();
        DumperOptions.Version version = null;
        while (this.scanner.checkToken(Token.EnumC8994ID.Directive)) {
            DirectiveToken directiveToken = (DirectiveToken) this.scanner.getToken();
            if (directiveToken.getName().equals("YAML")) {
                if (version != null) {
                    throw new ParserException(null, null, "found duplicate YAML directive", directiveToken.getStartMark());
                }
                List value = directiveToken.getValue();
                if (((Integer) value.get(0)).intValue() != 1) {
                    throw new ParserException(null, null, "found incompatible YAML document (version 1.* is required)", directiveToken.getStartMark());
                }
                if (((Integer) value.get(1)).intValue() == 0) {
                    version = DumperOptions.Version.V1_0;
                } else {
                    version = DumperOptions.Version.V1_1;
                }
            } else if (directiveToken.getName().equals("TAG")) {
                List value2 = directiveToken.getValue();
                String str = (String) value2.get(0);
                String str2 = (String) value2.get(1);
                if (hashMap.containsKey(str)) {
                    throw new ParserException(null, null, "duplicate tag handle " + str, directiveToken.getStartMark());
                }
                hashMap.put(str, str2);
            } else {
                continue;
            }
        }
        if (version != null || !hashMap.isEmpty()) {
            for (String str3 : DEFAULT_TAGS.keySet()) {
                if (!hashMap.containsKey(str3)) {
                    hashMap.put(str3, DEFAULT_TAGS.get(str3));
                }
            }
            this.directives = new VersionTagsTuple(version, hashMap);
        }
        return this.directives;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseBlockNode implements Production {
        private ParseBlockNode() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            return ParserImpl.this.parseNode(true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Event parseFlowNode() {
        return parseNode(false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Event parseBlockNodeOrIndentlessSequence() {
        return parseNode(true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Event parseNode(boolean z, boolean z2) {
        TagTuple tagTuple;
        Mark mark;
        Mark mark2;
        Mark mark3;
        String str;
        String str2;
        Mark mark4;
        Mark mark5;
        ScalarEvent scalarEvent;
        ImplicitTuple implicitTuple;
        Mark mark6;
        TagTuple tagTuple2;
        if (this.scanner.checkToken(Token.EnumC8994ID.Alias)) {
            AliasToken aliasToken = (AliasToken) this.scanner.getToken();
            AliasEvent aliasEvent = new AliasEvent(aliasToken.getValue(), aliasToken.getStartMark(), aliasToken.getEndMark());
            this.state = this.states.pop();
            return aliasEvent;
        }
        if (this.scanner.checkToken(Token.EnumC8994ID.Anchor)) {
            AnchorToken anchorToken = (AnchorToken) this.scanner.getToken();
            mark = anchorToken.getStartMark();
            Mark endMark = anchorToken.getEndMark();
            String value = anchorToken.getValue();
            if (this.scanner.checkToken(Token.EnumC8994ID.Tag)) {
                TagToken tagToken = (TagToken) this.scanner.getToken();
                mark3 = tagToken.getStartMark();
                mark6 = tagToken.getEndMark();
                tagTuple2 = tagToken.getValue();
            } else {
                mark3 = null;
                mark6 = endMark;
                tagTuple2 = null;
            }
            str = value;
            tagTuple = tagTuple2;
            mark2 = mark6;
        } else if (this.scanner.checkToken(Token.EnumC8994ID.Tag)) {
            TagToken tagToken2 = (TagToken) this.scanner.getToken();
            mark = tagToken2.getStartMark();
            mark2 = tagToken2.getEndMark();
            tagTuple = tagToken2.getValue();
            if (this.scanner.checkToken(Token.EnumC8994ID.Anchor)) {
                AnchorToken anchorToken2 = (AnchorToken) this.scanner.getToken();
                Mark endMark2 = anchorToken2.getEndMark();
                str = anchorToken2.getValue();
                mark2 = endMark2;
            } else {
                str = null;
            }
            mark3 = mark;
        } else {
            tagTuple = null;
            mark = null;
            mark2 = null;
            mark3 = null;
            str = null;
        }
        if (tagTuple != null) {
            String handle = tagTuple.getHandle();
            String suffix = tagTuple.getSuffix();
            if (handle != null) {
                if (!this.directives.getTags().containsKey(handle)) {
                    throw new ParserException("while parsing a node", mark, "found undefined tag handle " + handle, mark3);
                }
                suffix = this.directives.getTags().get(handle) + suffix;
            }
            str2 = suffix;
        } else {
            str2 = null;
        }
        if (mark == null) {
            mark4 = this.scanner.peekToken().getStartMark();
            mark5 = mark4;
        } else {
            mark4 = mark;
            mark5 = mark2;
        }
        boolean z3 = str2 == null || str2.equals("!");
        if (z2 && this.scanner.checkToken(Token.EnumC8994ID.BlockEntry)) {
            SequenceStartEvent sequenceStartEvent = new SequenceStartEvent(str, str2, z3, mark4, this.scanner.peekToken().getEndMark(), Boolean.FALSE);
            this.state = new ParseIndentlessSequenceEntry();
            return sequenceStartEvent;
        }
        if (this.scanner.checkToken(Token.EnumC8994ID.Scalar)) {
            ScalarToken scalarToken = (ScalarToken) this.scanner.getToken();
            Mark endMark3 = scalarToken.getEndMark();
            if ((scalarToken.getPlain() && str2 == null) || "!".equals(str2)) {
                implicitTuple = new ImplicitTuple(true, false);
            } else if (str2 == null) {
                implicitTuple = new ImplicitTuple(false, true);
            } else {
                implicitTuple = new ImplicitTuple(false, false);
            }
            scalarEvent = new ScalarEvent(str, str2, implicitTuple, scalarToken.getValue(), mark4, endMark3, Character.valueOf(scalarToken.getStyle()));
            this.state = this.states.pop();
        } else {
            if (this.scanner.checkToken(Token.EnumC8994ID.FlowSequenceStart)) {
                SequenceStartEvent sequenceStartEvent2 = new SequenceStartEvent(str, str2, z3, mark4, this.scanner.peekToken().getEndMark(), Boolean.TRUE);
                this.state = new ParseFlowSequenceFirstEntry();
                return sequenceStartEvent2;
            }
            if (this.scanner.checkToken(Token.EnumC8994ID.FlowMappingStart)) {
                MappingStartEvent mappingStartEvent = new MappingStartEvent(str, str2, z3, mark4, this.scanner.peekToken().getEndMark(), Boolean.TRUE);
                this.state = new ParseFlowMappingFirstKey();
                return mappingStartEvent;
            }
            if (z && this.scanner.checkToken(Token.EnumC8994ID.BlockSequenceStart)) {
                SequenceStartEvent sequenceStartEvent3 = new SequenceStartEvent(str, str2, z3, mark4, this.scanner.peekToken().getStartMark(), Boolean.FALSE);
                this.state = new ParseBlockSequenceFirstEntry();
                return sequenceStartEvent3;
            }
            if (z && this.scanner.checkToken(Token.EnumC8994ID.BlockMappingStart)) {
                MappingStartEvent mappingStartEvent2 = new MappingStartEvent(str, str2, z3, mark4, this.scanner.peekToken().getStartMark(), Boolean.FALSE);
                this.state = new ParseBlockMappingFirstKey();
                return mappingStartEvent2;
            }
            if (str != null || str2 != null) {
                scalarEvent = new ScalarEvent(str, str2, new ImplicitTuple(z3, false), "", mark4, mark5, (char) 0);
                this.state = this.states.pop();
            } else {
                String str3 = z ? "block" : "flow";
                Token peekToken = this.scanner.peekToken();
                throw new ParserException("while parsing a " + str3 + " node", mark4, "expected the node content, but found " + peekToken.getTokenId(), peekToken.getStartMark());
            }
        }
        return scalarEvent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseBlockSequenceFirstEntry implements Production {
        private ParseBlockSequenceFirstEntry() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            ParserImpl.this.marks.push(ParserImpl.this.scanner.getToken().getStartMark());
            return new ParseBlockSequenceEntry().produce();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseBlockSequenceEntry implements Production {
        private ParseBlockSequenceEntry() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.BlockEntry)) {
                BlockEntryToken blockEntryToken = (BlockEntryToken) ParserImpl.this.scanner.getToken();
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.BlockEntry, Token.EnumC8994ID.BlockEnd)) {
                    ParserImpl.this.states.push(new ParseBlockSequenceEntry());
                    return new ParseBlockNode().produce();
                }
                ParserImpl parserImpl = ParserImpl.this;
                parserImpl.state = new ParseBlockSequenceEntry();
                return ParserImpl.this.processEmptyScalar(blockEntryToken.getEndMark());
            }
            if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.BlockEnd)) {
                Token peekToken = ParserImpl.this.scanner.peekToken();
                throw new ParserException("while parsing a block collection", (Mark) ParserImpl.this.marks.pop(), "expected <block end>, but found " + peekToken.getTokenId(), peekToken.getStartMark());
            }
            Token token = ParserImpl.this.scanner.getToken();
            SequenceEndEvent sequenceEndEvent = new SequenceEndEvent(token.getStartMark(), token.getEndMark());
            ParserImpl parserImpl2 = ParserImpl.this;
            parserImpl2.state = (Production) parserImpl2.states.pop();
            ParserImpl.this.marks.pop();
            return sequenceEndEvent;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseIndentlessSequenceEntry implements Production {
        private ParseIndentlessSequenceEntry() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.BlockEntry)) {
                Token token = ParserImpl.this.scanner.getToken();
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.BlockEntry, Token.EnumC8994ID.Key, Token.EnumC8994ID.Value, Token.EnumC8994ID.BlockEnd)) {
                    ParserImpl.this.states.push(new ParseIndentlessSequenceEntry());
                    return new ParseBlockNode().produce();
                }
                ParserImpl parserImpl = ParserImpl.this;
                parserImpl.state = new ParseIndentlessSequenceEntry();
                return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
            Token peekToken = ParserImpl.this.scanner.peekToken();
            SequenceEndEvent sequenceEndEvent = new SequenceEndEvent(peekToken.getStartMark(), peekToken.getEndMark());
            ParserImpl parserImpl2 = ParserImpl.this;
            parserImpl2.state = (Production) parserImpl2.states.pop();
            return sequenceEndEvent;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseBlockMappingFirstKey implements Production {
        private ParseBlockMappingFirstKey() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            ParserImpl.this.marks.push(ParserImpl.this.scanner.getToken().getStartMark());
            return new ParseBlockMappingKey().produce();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseBlockMappingKey implements Production {
        private ParseBlockMappingKey() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Key)) {
                Token token = ParserImpl.this.scanner.getToken();
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Key, Token.EnumC8994ID.Value, Token.EnumC8994ID.BlockEnd)) {
                    ParserImpl.this.states.push(new ParseBlockMappingValue());
                    return ParserImpl.this.parseBlockNodeOrIndentlessSequence();
                }
                ParserImpl parserImpl = ParserImpl.this;
                parserImpl.state = new ParseBlockMappingValue();
                return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
            if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.BlockEnd)) {
                Token peekToken = ParserImpl.this.scanner.peekToken();
                throw new ParserException("while parsing a block mapping", (Mark) ParserImpl.this.marks.pop(), "expected <block end>, but found " + peekToken.getTokenId(), peekToken.getStartMark());
            }
            Token token2 = ParserImpl.this.scanner.getToken();
            MappingEndEvent mappingEndEvent = new MappingEndEvent(token2.getStartMark(), token2.getEndMark());
            ParserImpl parserImpl2 = ParserImpl.this;
            parserImpl2.state = (Production) parserImpl2.states.pop();
            ParserImpl.this.marks.pop();
            return mappingEndEvent;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseBlockMappingValue implements Production {
        private ParseBlockMappingValue() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Value)) {
                Token token = ParserImpl.this.scanner.getToken();
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Key, Token.EnumC8994ID.Value, Token.EnumC8994ID.BlockEnd)) {
                    ParserImpl.this.states.push(new ParseBlockMappingKey());
                    return ParserImpl.this.parseBlockNodeOrIndentlessSequence();
                }
                ParserImpl parserImpl = ParserImpl.this;
                parserImpl.state = new ParseBlockMappingKey();
                return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
            ParserImpl parserImpl2 = ParserImpl.this;
            parserImpl2.state = new ParseBlockMappingKey();
            return ParserImpl.this.processEmptyScalar(ParserImpl.this.scanner.peekToken().getStartMark());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseFlowSequenceFirstEntry implements Production {
        private ParseFlowSequenceFirstEntry() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            ParserImpl.this.marks.push(ParserImpl.this.scanner.getToken().getStartMark());
            return new ParseFlowSequenceEntry(true).produce();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseFlowSequenceEntry implements Production {
        private boolean first;

        public ParseFlowSequenceEntry(boolean z) {
            this.first = false;
            this.first = z;
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.FlowSequenceEnd)) {
                if (!this.first) {
                    if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.FlowEntry)) {
                        ParserImpl.this.scanner.getToken();
                    } else {
                        Token peekToken = ParserImpl.this.scanner.peekToken();
                        throw new ParserException("while parsing a flow sequence", (Mark) ParserImpl.this.marks.pop(), "expected ',' or ']', but got " + peekToken.getTokenId(), peekToken.getStartMark());
                    }
                }
                if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Key)) {
                    Token peekToken2 = ParserImpl.this.scanner.peekToken();
                    MappingStartEvent mappingStartEvent = new MappingStartEvent(null, null, true, peekToken2.getStartMark(), peekToken2.getEndMark(), Boolean.TRUE);
                    ParserImpl parserImpl = ParserImpl.this;
                    parserImpl.state = new ParseFlowSequenceEntryMappingKey();
                    return mappingStartEvent;
                }
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.FlowSequenceEnd)) {
                    ParserImpl.this.states.push(new ParseFlowSequenceEntry(false));
                    return ParserImpl.this.parseFlowNode();
                }
            }
            Token token = ParserImpl.this.scanner.getToken();
            SequenceEndEvent sequenceEndEvent = new SequenceEndEvent(token.getStartMark(), token.getEndMark());
            ParserImpl parserImpl2 = ParserImpl.this;
            parserImpl2.state = (Production) parserImpl2.states.pop();
            ParserImpl.this.marks.pop();
            return sequenceEndEvent;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseFlowSequenceEntryMappingKey implements Production {
        private ParseFlowSequenceEntryMappingKey() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            Token token = ParserImpl.this.scanner.getToken();
            if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Value, Token.EnumC8994ID.FlowEntry, Token.EnumC8994ID.FlowSequenceEnd)) {
                ParserImpl.this.states.push(new ParseFlowSequenceEntryMappingValue());
                return ParserImpl.this.parseFlowNode();
            }
            ParserImpl parserImpl = ParserImpl.this;
            parserImpl.state = new ParseFlowSequenceEntryMappingValue();
            return ParserImpl.this.processEmptyScalar(token.getEndMark());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseFlowSequenceEntryMappingValue implements Production {
        private ParseFlowSequenceEntryMappingValue() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Value)) {
                Token token = ParserImpl.this.scanner.getToken();
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.FlowEntry, Token.EnumC8994ID.FlowSequenceEnd)) {
                    ParserImpl.this.states.push(new ParseFlowSequenceEntryMappingEnd());
                    return ParserImpl.this.parseFlowNode();
                }
                ParserImpl parserImpl = ParserImpl.this;
                parserImpl.state = new ParseFlowSequenceEntryMappingEnd();
                return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
            ParserImpl parserImpl2 = ParserImpl.this;
            parserImpl2.state = new ParseFlowSequenceEntryMappingEnd();
            return ParserImpl.this.processEmptyScalar(ParserImpl.this.scanner.peekToken().getStartMark());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseFlowSequenceEntryMappingEnd implements Production {
        private ParseFlowSequenceEntryMappingEnd() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            ParserImpl parserImpl = ParserImpl.this;
            parserImpl.state = new ParseFlowSequenceEntry(false);
            Token peekToken = ParserImpl.this.scanner.peekToken();
            return new MappingEndEvent(peekToken.getStartMark(), peekToken.getEndMark());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseFlowMappingFirstKey implements Production {
        private ParseFlowMappingFirstKey() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            ParserImpl.this.marks.push(ParserImpl.this.scanner.getToken().getStartMark());
            return new ParseFlowMappingKey(true).produce();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private class ParseFlowMappingKey implements Production {
        private boolean first;

        public ParseFlowMappingKey(boolean z) {
            this.first = false;
            this.first = z;
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.FlowMappingEnd)) {
                if (!this.first) {
                    if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.FlowEntry)) {
                        ParserImpl.this.scanner.getToken();
                    } else {
                        Token peekToken = ParserImpl.this.scanner.peekToken();
                        throw new ParserException("while parsing a flow mapping", (Mark) ParserImpl.this.marks.pop(), "expected ',' or '}', but got " + peekToken.getTokenId(), peekToken.getStartMark());
                    }
                }
                if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Key)) {
                    Token token = ParserImpl.this.scanner.getToken();
                    if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Value, Token.EnumC8994ID.FlowEntry, Token.EnumC8994ID.FlowMappingEnd)) {
                        ParserImpl.this.states.push(new ParseFlowMappingValue());
                        return ParserImpl.this.parseFlowNode();
                    }
                    ParserImpl parserImpl = ParserImpl.this;
                    parserImpl.state = new ParseFlowMappingValue();
                    return ParserImpl.this.processEmptyScalar(token.getEndMark());
                }
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.FlowMappingEnd)) {
                    ParserImpl.this.states.push(new ParseFlowMappingEmptyValue());
                    return ParserImpl.this.parseFlowNode();
                }
            }
            Token token2 = ParserImpl.this.scanner.getToken();
            MappingEndEvent mappingEndEvent = new MappingEndEvent(token2.getStartMark(), token2.getEndMark());
            ParserImpl parserImpl2 = ParserImpl.this;
            parserImpl2.state = (Production) parserImpl2.states.pop();
            ParserImpl.this.marks.pop();
            return mappingEndEvent;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseFlowMappingValue implements Production {
        private ParseFlowMappingValue() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            if (ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.Value)) {
                Token token = ParserImpl.this.scanner.getToken();
                if (!ParserImpl.this.scanner.checkToken(Token.EnumC8994ID.FlowEntry, Token.EnumC8994ID.FlowMappingEnd)) {
                    ParserImpl.this.states.push(new ParseFlowMappingKey(false));
                    return ParserImpl.this.parseFlowNode();
                }
                ParserImpl parserImpl = ParserImpl.this;
                parserImpl.state = new ParseFlowMappingKey(false);
                return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
            ParserImpl parserImpl2 = ParserImpl.this;
            parserImpl2.state = new ParseFlowMappingKey(false);
            return ParserImpl.this.processEmptyScalar(ParserImpl.this.scanner.peekToken().getStartMark());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ParseFlowMappingEmptyValue implements Production {
        private ParseFlowMappingEmptyValue() {
        }

        @Override // org.yaml.snakeyaml.parser.Production
        public Event produce() {
            ParserImpl parserImpl = ParserImpl.this;
            parserImpl.state = new ParseFlowMappingKey(false);
            ParserImpl parserImpl2 = ParserImpl.this;
            return parserImpl2.processEmptyScalar(parserImpl2.scanner.peekToken().getStartMark());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Event processEmptyScalar(Mark mark) {
        return new ScalarEvent(null, null, new ImplicitTuple(true, false), "", mark, mark, (char) 0);
    }
}
