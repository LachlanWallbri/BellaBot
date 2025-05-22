package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.params.DHParameters;

/* loaded from: classes9.dex */
public interface TlsDHVerifier {
    boolean accept(DHParameters dHParameters);
}
