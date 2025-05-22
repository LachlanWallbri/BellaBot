package com.fasterxml.jackson.core.sym;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public final class Name3 extends Name {

    /* renamed from: q1 */
    private final int f1261q1;

    /* renamed from: q2 */
    private final int f1262q2;

    /* renamed from: q3 */
    private final int f1263q3;

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i, int i2) {
        return false;
    }

    Name3(String str, int i, int i2, int i3, int i4) {
        super(str, i);
        this.f1261q1 = i2;
        this.f1262q2 = i3;
        this.f1263q3 = i4;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i, int i2, int i3) {
        return this.f1261q1 == i && this.f1262q2 == i2 && this.f1263q3 == i3;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int[] iArr, int i) {
        return i == 3 && iArr[0] == this.f1261q1 && iArr[1] == this.f1262q2 && iArr[2] == this.f1263q3;
    }
}
