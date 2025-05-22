package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes9.dex */
public interface MacCalculatorProvider {
    MacCalculator get(AlgorithmIdentifier algorithmIdentifier);
}
