package com.fasterxml.jackson.core.sym;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public final class NameN extends Name {

    /* renamed from: q */
    private final int[] f1264q;

    /* renamed from: q1 */
    private final int f1265q1;

    /* renamed from: q2 */
    private final int f1266q2;

    /* renamed from: q3 */
    private final int f1267q3;

    /* renamed from: q4 */
    private final int f1268q4;
    private final int qlen;

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i, int i2) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i, int i2, int i3) {
        return false;
    }

    NameN(String str, int i, int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        super(str, i);
        this.f1265q1 = i2;
        this.f1266q2 = i3;
        this.f1267q3 = i4;
        this.f1268q4 = i5;
        this.f1264q = iArr;
        this.qlen = i6;
    }

    public static NameN construct(String str, int i, int[] iArr, int i2) {
        if (i2 < 4) {
            throw new IllegalArgumentException();
        }
        return new NameN(str, i, iArr[0], iArr[1], iArr[2], iArr[3], i2 + (-4) > 0 ? Arrays.copyOfRange(iArr, 4, i2) : null, i2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0025. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0040 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0055 A[RETURN] */
    @Override // com.fasterxml.jackson.core.sym.Name
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean equals(int[] iArr, int i) {
        if (i != this.qlen || iArr[0] != this.f1265q1 || iArr[1] != this.f1266q2 || iArr[2] != this.f1267q3 || iArr[3] != this.f1268q4) {
            return false;
        }
        switch (i) {
            case 4:
                return true;
            case 5:
                if (iArr[4] == this.f1264q[0]) {
                    return false;
                }
                break;
            case 6:
                if (iArr[5] != this.f1264q[1]) {
                    return false;
                }
                if (iArr[4] == this.f1264q[0]) {
                }
                break;
            case 7:
                if (iArr[6] != this.f1264q[2]) {
                    return false;
                }
                if (iArr[5] != this.f1264q[1]) {
                }
                if (iArr[4] == this.f1264q[0]) {
                }
                break;
            case 8:
                if (iArr[7] != this.f1264q[3]) {
                    return false;
                }
                if (iArr[6] != this.f1264q[2]) {
                }
                if (iArr[5] != this.f1264q[1]) {
                }
                if (iArr[4] == this.f1264q[0]) {
                }
                break;
            default:
                return _equals2(iArr);
        }
    }

    private final boolean _equals2(int[] iArr) {
        int i = this.qlen - 4;
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2 + 4] != this.f1264q[i2]) {
                return false;
            }
        }
        return true;
    }
}
