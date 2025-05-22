package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.agreement.DHStandardGroups;
import org.bouncycastle.crypto.params.DHParameters;

/* loaded from: classes9.dex */
public abstract class DefaultTlsServer extends AbstractTlsServer {
    public DefaultTlsServer() {
    }

    public DefaultTlsServer(TlsCipherFactory tlsCipherFactory) {
        super(tlsCipherFactory);
    }

    protected TlsKeyExchange createDHEKeyExchange(int i) {
        return new TlsDHEKeyExchange(i, this.supportedSignatureAlgorithms, null, getDHParameters());
    }

    protected TlsKeyExchange createDHKeyExchange(int i) {
        return new TlsDHKeyExchange(i, this.supportedSignatureAlgorithms, null, getDHParameters());
    }

    protected TlsKeyExchange createECDHEKeyExchange(int i) {
        return new TlsECDHEKeyExchange(i, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    protected TlsKeyExchange createECDHKeyExchange(int i) {
        return new TlsECDHKeyExchange(i, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    protected TlsKeyExchange createRSAKeyExchange() {
        return new TlsRSAKeyExchange(this.supportedSignatureAlgorithms);
    }

    @Override // org.bouncycastle.crypto.tls.AbstractTlsServer
    protected int[] getCipherSuites() {
        return new int[]{CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, 159, 158, 107, 103, 57, 51, 157, 156, 61, 60, 53, 47};
    }

    @Override // org.bouncycastle.crypto.tls.TlsServer
    public TlsCredentials getCredentials() throws IOException {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        if (keyExchangeAlgorithm == 1) {
            return getRSAEncryptionCredentials();
        }
        if (keyExchangeAlgorithm == 3) {
            return getDSASignerCredentials();
        }
        if (keyExchangeAlgorithm != 5) {
            if (keyExchangeAlgorithm == 11) {
                return null;
            }
            if (keyExchangeAlgorithm == 17) {
                return getECDSASignerCredentials();
            }
            if (keyExchangeAlgorithm != 19) {
                if (keyExchangeAlgorithm == 20) {
                    return null;
                }
                throw new TlsFatalAlert((short) 80);
            }
        }
        return getRSASignerCredentials();
    }

    protected DHParameters getDHParameters() {
        return DHStandardGroups.rfc7919_ffdhe2048;
    }

    protected TlsSignerCredentials getDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsSignerCredentials getECDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.crypto.tls.TlsServer
    public TlsKeyExchange getKeyExchange() throws IOException {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        if (keyExchangeAlgorithm == 1) {
            return createRSAKeyExchange();
        }
        if (keyExchangeAlgorithm == 3 || keyExchangeAlgorithm == 5) {
            return createDHEKeyExchange(keyExchangeAlgorithm);
        }
        if (keyExchangeAlgorithm == 7 || keyExchangeAlgorithm == 9 || keyExchangeAlgorithm == 11) {
            return createDHKeyExchange(keyExchangeAlgorithm);
        }
        switch (keyExchangeAlgorithm) {
            case 16:
            case 18:
            case 20:
                return createECDHKeyExchange(keyExchangeAlgorithm);
            case 17:
            case 19:
                return createECDHEKeyExchange(keyExchangeAlgorithm);
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    protected TlsEncryptionCredentials getRSAEncryptionCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsSignerCredentials getRSASignerCredentials() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }
}
