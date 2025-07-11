package org.bouncycastle.cms.p084bc;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.GenericKey;

/* loaded from: classes9.dex */
class CMSUtils {
    CMSUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CipherParameters getBcKey(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof CipherParameters) {
            return (CipherParameters) genericKey.getRepresentation();
        }
        if (genericKey.getRepresentation() instanceof byte[]) {
            return new KeyParameter((byte[]) genericKey.getRepresentation());
        }
        throw new IllegalArgumentException("unknown generic key type");
    }
}
