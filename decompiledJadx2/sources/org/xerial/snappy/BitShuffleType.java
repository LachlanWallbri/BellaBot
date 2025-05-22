package org.xerial.snappy;

/* loaded from: classes9.dex */
public enum BitShuffleType {
    BYTE(1),
    SHORT(2),
    INT(4),
    LONG(8),
    FLOAT(4),
    DOUBLE(8);


    /* renamed from: id */
    public final int f10296id;

    BitShuffleType(int i) {
        this.f10296id = i;
    }

    public int getTypeSize() {
        return this.f10296id;
    }
}
