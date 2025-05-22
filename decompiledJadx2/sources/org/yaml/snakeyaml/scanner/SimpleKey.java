package org.yaml.snakeyaml.scanner;

import org.yaml.snakeyaml.error.Mark;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
final class SimpleKey {
    private int column;
    private int index;
    private int line;
    private Mark mark;
    private boolean required;
    private int tokenNumber;

    public SimpleKey(int i, boolean z, int i2, int i3, int i4, Mark mark) {
        this.tokenNumber = i;
        this.required = z;
        this.index = i2;
        this.line = i3;
        this.column = i4;
        this.mark = mark;
    }

    public int getTokenNumber() {
        return this.tokenNumber;
    }

    public int getColumn() {
        return this.column;
    }

    public Mark getMark() {
        return this.mark;
    }

    public int getIndex() {
        return this.index;
    }

    public int getLine() {
        return this.line;
    }

    public boolean isRequired() {
        return this.required;
    }

    public String toString() {
        return "SimpleKey - tokenNumber=" + this.tokenNumber + " required=" + this.required + " index=" + this.index + " line=" + this.line + " column=" + this.column;
    }
}
