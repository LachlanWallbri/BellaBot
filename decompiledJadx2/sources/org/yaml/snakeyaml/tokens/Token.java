package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.YAMLException;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class Token {
    private final Mark endMark;
    private final Mark startMark;

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.yaml.snakeyaml.tokens.Token$ID */
    /* loaded from: classes9.dex */
    public enum EnumC8994ID {
        Alias,
        Anchor,
        BlockEnd,
        BlockEntry,
        BlockMappingStart,
        BlockSequenceStart,
        Directive,
        DocumentEnd,
        DocumentStart,
        FlowEntry,
        FlowMappingEnd,
        FlowMappingStart,
        FlowSequenceEnd,
        FlowSequenceStart,
        Key,
        Scalar,
        StreamEnd,
        StreamStart,
        Tag,
        Value,
        Whitespace,
        Comment,
        Error
    }

    protected String getArguments() {
        return "";
    }

    public abstract EnumC8994ID getTokenId();

    public Token(Mark mark, Mark mark2) {
        if (mark == null || mark2 == null) {
            throw new YAMLException("Token requires marks.");
        }
        this.startMark = mark;
        this.endMark = mark2;
    }

    public String toString() {
        return "<" + getClass().getName() + "(" + getArguments() + ")>";
    }

    public Mark getStartMark() {
        return this.startMark;
    }

    public Mark getEndMark() {
        return this.endMark;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Token) {
            return toString().equals(obj.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
