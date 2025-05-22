package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/* loaded from: classes9.dex */
class NamedEACHelper implements EACHelper {
    private final String providerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamedEACHelper(String str) {
        this.providerName = str;
    }

    @Override // org.bouncycastle.eac.jcajce.EACHelper
    public KeyFactory createKeyFactory(String str) throws NoSuchProviderException, NoSuchAlgorithmException {
        return KeyFactory.getInstance(str, this.providerName);
    }
}
