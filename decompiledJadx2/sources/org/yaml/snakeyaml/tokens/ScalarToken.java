package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.tokens.Token;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ScalarToken extends Token {
    private final boolean plain;
    private final char style;
    private final String value;

    public ScalarToken(String str, Mark mark, Mark mark2, boolean z) {
        this(str, z, mark, mark2, (char) 0);
    }

    public ScalarToken(String str, boolean z, Mark mark, Mark mark2, char c) {
        super(mark, mark2);
        this.value = str;
        this.plain = z;
        this.style = c;
    }

    public boolean getPlain() {
        return this.plain;
    }

    public String getValue() {
        return this.value;
    }

    public char getStyle() {
        return this.style;
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    protected String getArguments() {
        return "value=" + this.value + ", plain=" + this.plain + ", style=" + this.style;
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    public Token.EnumC8994ID getTokenId() {
        return Token.EnumC8994ID.Scalar;
    }
}
