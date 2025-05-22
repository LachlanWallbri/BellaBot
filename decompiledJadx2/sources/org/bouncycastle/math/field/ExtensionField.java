package org.bouncycastle.math.field;

/* loaded from: classes9.dex */
public interface ExtensionField extends FiniteField {
    int getDegree();

    FiniteField getSubfield();
}
