package org.bouncycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes9.dex */
public class ECNamedCurveGenParameterSpec implements AlgorithmParameterSpec {
    private String name;

    public ECNamedCurveGenParameterSpec(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
