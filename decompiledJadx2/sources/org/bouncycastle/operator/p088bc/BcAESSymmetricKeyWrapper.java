package org.bouncycastle.operator.p088bc;

import org.bouncycastle.crypto.engines.AESWrapEngine;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes9.dex */
public class BcAESSymmetricKeyWrapper extends BcSymmetricKeyWrapper {
    public BcAESSymmetricKeyWrapper(KeyParameter keyParameter) {
        super(AESUtil.determineKeyEncAlg(keyParameter), new AESWrapEngine(), keyParameter);
    }
}
