package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.tokens.Token;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class TagToken extends Token {
    private final TagTuple value;

    public TagToken(TagTuple tagTuple, Mark mark, Mark mark2) {
        super(mark, mark2);
        this.value = tagTuple;
    }

    public TagTuple getValue() {
        return this.value;
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    protected String getArguments() {
        return "value=[" + this.value.getHandle() + ", " + this.value.getSuffix() + "]";
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    public Token.EnumC8994ID getTokenId() {
        return Token.EnumC8994ID.Tag;
    }
}
