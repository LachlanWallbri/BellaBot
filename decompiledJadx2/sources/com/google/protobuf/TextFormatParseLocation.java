package com.google.protobuf;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public final class TextFormatParseLocation {
    public static final TextFormatParseLocation EMPTY = new TextFormatParseLocation(-1, -1);
    private final int column;
    private final int line;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TextFormatParseLocation create(int i, int i2) {
        if (i == -1 && i2 == -1) {
            return EMPTY;
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException(String.format("line and column values must be >= 0: line %d, column: %d", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        return new TextFormatParseLocation(i, i2);
    }

    private TextFormatParseLocation(int i, int i2) {
        this.line = i;
        this.column = i2;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public String toString() {
        return String.format("ParseLocation{line=%d, column=%d}", Integer.valueOf(this.line), Integer.valueOf(this.column));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextFormatParseLocation)) {
            return false;
        }
        TextFormatParseLocation textFormatParseLocation = (TextFormatParseLocation) obj;
        return this.line == textFormatParseLocation.getLine() && this.column == textFormatParseLocation.getColumn();
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{this.line, this.column});
    }
}
