package org.yaml.snakeyaml.scanner;

import org.yaml.snakeyaml.tokens.Token;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface Scanner {
    boolean checkToken(Token.EnumC8994ID... enumC8994IDArr);

    Token getToken();

    Token peekToken();
}
