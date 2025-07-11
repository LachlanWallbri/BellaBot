package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import javax.crypto.interfaces.DHPrivateKey;

/* loaded from: classes9.dex */
public interface ElGamalPrivateKey extends ElGamalKey, DHPrivateKey {
    BigInteger getX();
}
