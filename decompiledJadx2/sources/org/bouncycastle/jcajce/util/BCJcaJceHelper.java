package org.bouncycastle.jcajce.util;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* loaded from: classes9.dex */
public class BCJcaJceHelper extends ProviderJcaJceHelper {
    private static volatile Provider bcProvider;

    public BCJcaJceHelper() {
        super(getBouncyCastleProvider());
    }

    private static synchronized Provider getBouncyCastleProvider() {
        synchronized (BCJcaJceHelper.class) {
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) != null) {
                return Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
            }
            if (bcProvider != null) {
                return bcProvider;
            }
            bcProvider = new BouncyCastleProvider();
            return bcProvider;
        }
    }
}
