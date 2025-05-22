package org.apache.commons.codec.language.bm;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public enum NameType {
    ASHKENAZI("ash"),
    GENERIC("gen"),
    SEPHARDIC("sep");

    private final String name;

    NameType(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
