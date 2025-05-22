package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.tokens.Token;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class AliasToken extends Token {
    private final String value;

    public AliasToken(String str, Mark mark, Mark mark2) {
        super(mark, mark2);
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    protected String getArguments() {
        return "value=" + this.value;
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    public Token.EnumC8994ID getTokenId() {
        return Token.EnumC8994ID.Alias;
    }
}
