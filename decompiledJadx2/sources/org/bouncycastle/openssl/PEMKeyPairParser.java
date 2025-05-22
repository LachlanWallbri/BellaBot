package org.bouncycastle.openssl;

import java.io.IOException;

/* loaded from: classes9.dex */
interface PEMKeyPairParser {
    PEMKeyPair parse(byte[] bArr) throws IOException;
}
