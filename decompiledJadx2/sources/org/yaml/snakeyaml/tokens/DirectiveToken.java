package org.yaml.snakeyaml.tokens;

import java.util.List;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.tokens.Token;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class DirectiveToken<T> extends Token {
    private final String name;
    private final List<T> value;

    public DirectiveToken(String str, List<T> list, Mark mark, Mark mark2) {
        super(mark, mark2);
        this.name = str;
        if (list != null && list.size() != 2) {
            throw new YAMLException("Two strings must be provided instead of " + String.valueOf(list.size()));
        }
        this.value = list;
    }

    public String getName() {
        return this.name;
    }

    public List<T> getValue() {
        return this.value;
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    protected String getArguments() {
        if (this.value != null) {
            return "name=" + this.name + ", value=[" + this.value.get(0) + ", " + this.value.get(1) + "]";
        }
        return "name=" + this.name;
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    public Token.EnumC8994ID getTokenId() {
        return Token.EnumC8994ID.Directive;
    }
}
