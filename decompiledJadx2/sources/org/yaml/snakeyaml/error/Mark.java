package org.yaml.snakeyaml.error;

import org.yaml.snakeyaml.scanner.Constant;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class Mark {
    private String buffer;
    private int column;
    private int index;
    private int line;
    private String name;
    private int pointer;

    public Mark(String str, int i, int i2, int i3, String str2, int i4) {
        this.name = str;
        this.index = i;
        this.line = i2;
        this.column = i3;
        this.buffer = str2;
        this.pointer = i4;
    }

    private boolean isLineBreak(char c) {
        return Constant.NULL_OR_LINEBR.has(c);
    }

    public String get_snippet(int i, int i2) {
        String str;
        int i3;
        String str2;
        if (this.buffer == null) {
            return null;
        }
        float f = (i2 / 2) - 1;
        int i4 = this.pointer;
        do {
            str = " ... ";
            if (i4 <= 0 || isLineBreak(this.buffer.charAt(i4 - 1))) {
                i3 = i4;
                str2 = "";
                break;
            }
            i4--;
        } while (this.pointer - i4 <= f);
        i3 = i4 + 5;
        str2 = " ... ";
        int i5 = this.pointer;
        while (i5 < this.buffer.length() && !isLineBreak(this.buffer.charAt(i5))) {
            i5++;
            if (i5 - this.pointer > f) {
                i5 -= 5;
                break;
            }
        }
        str = "";
        String substring = this.buffer.substring(i3, i5);
        StringBuilder sb = new StringBuilder();
        for (int i6 = 0; i6 < i; i6++) {
            sb.append(" ");
        }
        sb.append(str2);
        sb.append(substring);
        sb.append(str);
        sb.append("\n");
        for (int i7 = 0; i7 < ((this.pointer + i) - i3) + str2.length(); i7++) {
            sb.append(" ");
        }
        sb.append("^");
        return sb.toString();
    }

    public String get_snippet() {
        return get_snippet(4, 75);
    }

    public String toString() {
        String str = get_snippet();
        StringBuilder sb = new StringBuilder(" in ");
        sb.append(this.name);
        sb.append(", line ");
        sb.append(this.line + 1);
        sb.append(", column ");
        sb.append(this.column + 1);
        if (str != null) {
            sb.append(":\n");
            sb.append(str);
        }
        return sb.toString();
    }

    public String getName() {
        return this.name;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public int getIndex() {
        return this.index;
    }
}
