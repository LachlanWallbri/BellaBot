package org.bouncycastle.util.io.pem;

import java.io.IOException;

/* loaded from: classes9.dex */
public interface PemObjectParser {
    Object parseObject(PemObject pemObject) throws IOException;
}
