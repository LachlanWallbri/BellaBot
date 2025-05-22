package androidx.constraintlayout.core.parser;

/* loaded from: classes.dex */
public class CLToken extends CLElement {
    int index;
    char[] tokenFalse;
    char[] tokenNull;
    char[] tokenTrue;
    Type type;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum Type {
        UNKNOWN,
        TRUE,
        FALSE,
        NULL
    }

    public boolean getBoolean() throws CLParsingException {
        if (this.type == Type.TRUE) {
            return true;
        }
        if (this.type == Type.FALSE) {
            return false;
        }
        throw new CLParsingException("this token is not a boolean: <" + content() + ">", this);
    }

    public boolean isNull() throws CLParsingException {
        if (this.type == Type.NULL) {
            return true;
        }
        throw new CLParsingException("this token is not a null: <" + content() + ">", this);
    }

    public CLToken(char[] cArr) {
        super(cArr);
        this.index = 0;
        this.type = Type.UNKNOWN;
        this.tokenTrue = "true".toCharArray();
        this.tokenFalse = "false".toCharArray();
        this.tokenNull = "null".toCharArray();
    }

    public static CLElement allocate(char[] cArr) {
        return new CLToken(cArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        if (CLParser.DEBUG) {
            return "<" + content() + ">";
        }
        return content();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        addIndent(sb, i);
        sb.append(content());
        return sb.toString();
    }

    public Type getType() {
        return this.type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.parser.CLToken$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C01561 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type = new int[Type.values().length];

        static {
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type[Type.TRUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type[Type.FALSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type[Type.NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type[Type.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public boolean validate(char c, long j) {
        int i = C01561.$SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type[this.type.ordinal()];
        if (i == 1) {
            r1 = this.tokenTrue[this.index] == c;
            if (r1 && this.index + 1 == this.tokenTrue.length) {
                setEnd(j);
            }
        } else if (i == 2) {
            r1 = this.tokenFalse[this.index] == c;
            if (r1 && this.index + 1 == this.tokenFalse.length) {
                setEnd(j);
            }
        } else if (i == 3) {
            r1 = this.tokenNull[this.index] == c;
            if (r1 && this.index + 1 == this.tokenNull.length) {
                setEnd(j);
            }
        } else if (i == 4) {
            char[] cArr = this.tokenTrue;
            int i2 = this.index;
            if (cArr[i2] == c) {
                this.type = Type.TRUE;
            } else if (this.tokenFalse[i2] == c) {
                this.type = Type.FALSE;
            } else if (this.tokenNull[i2] == c) {
                this.type = Type.NULL;
            }
            r1 = true;
        }
        this.index++;
        return r1;
    }
}
