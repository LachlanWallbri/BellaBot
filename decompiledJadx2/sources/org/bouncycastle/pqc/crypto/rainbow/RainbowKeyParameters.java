package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes9.dex */
public class RainbowKeyParameters extends AsymmetricKeyParameter {
    private int docLength;

    public RainbowKeyParameters(boolean z, int i) {
        super(z);
        this.docLength = i;
    }

    public int getDocLength() {
        return this.docLength;
    }
}
