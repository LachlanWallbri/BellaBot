package org.mozilla.javascript.regexp;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class SubString {
    int index;
    int length;
    String str;

    public SubString() {
    }

    public SubString(String str) {
        this.str = str;
        this.index = 0;
        this.length = str.length();
    }

    public SubString(String str, int i, int i2) {
        this.str = str;
        this.index = i;
        this.length = i2;
    }

    public String toString() {
        String str = this.str;
        if (str == null) {
            return "";
        }
        int i = this.index;
        return str.substring(i, this.length + i);
    }
}
