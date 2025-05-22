package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.tokens.Token;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class DocumentStartToken extends Token {
    public DocumentStartToken(Mark mark, Mark mark2) {
        super(mark, mark2);
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    public Token.EnumC8994ID getTokenId() {
        return Token.EnumC8994ID.DocumentStart;
    }
}
