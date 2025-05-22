package io.grpc.netty.shaded.io.netty.handler.ssl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.net.ssl.SSLException;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSslKeyMaterialManager {
    private static final Map<String, String> KEY_TYPES = new HashMap();
    static final String KEY_TYPE_DH_RSA = "DH_RSA";
    static final String KEY_TYPE_EC = "EC";
    static final String KEY_TYPE_EC_EC = "EC_EC";
    static final String KEY_TYPE_EC_RSA = "EC_RSA";
    static final String KEY_TYPE_RSA = "RSA";
    private final OpenSslKeyMaterialProvider provider;

    static {
        KEY_TYPES.put(KEY_TYPE_RSA, KEY_TYPE_RSA);
        KEY_TYPES.put("DHE_RSA", KEY_TYPE_RSA);
        KEY_TYPES.put("ECDHE_RSA", KEY_TYPE_RSA);
        KEY_TYPES.put("ECDHE_ECDSA", KEY_TYPE_EC);
        KEY_TYPES.put("ECDH_RSA", KEY_TYPE_EC_RSA);
        KEY_TYPES.put("ECDH_ECDSA", KEY_TYPE_EC_EC);
        KEY_TYPES.put(KEY_TYPE_DH_RSA, KEY_TYPE_DH_RSA);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslKeyMaterialManager(OpenSslKeyMaterialProvider openSslKeyMaterialProvider) {
        this.provider = openSslKeyMaterialProvider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setKeyMaterialServerSide(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine) throws SSLException {
        String chooseServerAlias;
        String[] authMethods = referenceCountedOpenSslEngine.authMethods();
        if (authMethods.length == 0) {
            return;
        }
        HashSet hashSet = new HashSet(authMethods.length);
        for (String str : authMethods) {
            String str2 = KEY_TYPES.get(str);
            if (str2 != null && (chooseServerAlias = chooseServerAlias(referenceCountedOpenSslEngine, str2)) != null && hashSet.add(chooseServerAlias) && !setKeyMaterial(referenceCountedOpenSslEngine, chooseServerAlias)) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setKeyMaterialClientSide(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, String[] strArr, X500Principal[] x500PrincipalArr) throws SSLException {
        String chooseClientAlias = chooseClientAlias(referenceCountedOpenSslEngine, strArr, x500PrincipalArr);
        if (chooseClientAlias != null) {
            setKeyMaterial(referenceCountedOpenSslEngine, chooseClientAlias);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0017 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean setKeyMaterial(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, String str) throws SSLException {
        boolean z;
        OpenSslKeyMaterial openSslKeyMaterial = null;
        try {
            try {
                openSslKeyMaterial = this.provider.chooseKeyMaterial(referenceCountedOpenSslEngine.alloc, str);
                if (openSslKeyMaterial != null) {
                    if (!referenceCountedOpenSslEngine.setKeyMaterial(openSslKeyMaterial)) {
                        z = false;
                        return z;
                    }
                }
                z = true;
                return z;
            } catch (SSLException e) {
                throw e;
            } catch (Exception e2) {
                throw new SSLException(e2);
            }
        } finally {
            if (openSslKeyMaterial != null) {
                openSslKeyMaterial.release();
            }
        }
    }

    private String chooseClientAlias(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, String[] strArr, X500Principal[] x500PrincipalArr) {
        X509KeyManager keyManager = this.provider.keyManager();
        if (keyManager instanceof X509ExtendedKeyManager) {
            return ((X509ExtendedKeyManager) keyManager).chooseEngineClientAlias(strArr, x500PrincipalArr, referenceCountedOpenSslEngine);
        }
        return keyManager.chooseClientAlias(strArr, x500PrincipalArr, null);
    }

    private String chooseServerAlias(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, String str) {
        X509KeyManager keyManager = this.provider.keyManager();
        if (keyManager instanceof X509ExtendedKeyManager) {
            return ((X509ExtendedKeyManager) keyManager).chooseEngineServerAlias(str, null, referenceCountedOpenSslEngine);
        }
        return keyManager.chooseServerAlias(str, null, null);
    }
}
