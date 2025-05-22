package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/* loaded from: classes9.dex */
public abstract class AbstractTlsKeyExchange implements TlsKeyExchange {
    protected TlsContext context;
    protected int keyExchange;
    protected Vector supportedSignatureAlgorithms;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractTlsKeyExchange(int i, Vector vector) {
        this.keyExchange = i;
        this.supportedSignatureAlgorithms = vector;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws IOException {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 80);
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0022. Please report as an issue. */
    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void init(TlsContext tlsContext) {
        Vector defaultRSASignatureAlgorithms;
        this.context = tlsContext;
        ProtocolVersion clientVersion = tlsContext.getClientVersion();
        if (!TlsUtils.isSignatureAlgorithmsExtensionAllowed(clientVersion)) {
            if (this.supportedSignatureAlgorithms == null) {
                return;
            }
            throw new IllegalStateException("supported_signature_algorithms not allowed for " + clientVersion);
        }
        if (this.supportedSignatureAlgorithms == null) {
            int i = this.keyExchange;
            if (i != 1) {
                if (i != 3) {
                    if (i != 5) {
                        if (i != 7) {
                            if (i != 9) {
                                switch (i) {
                                    case 13:
                                    case 14:
                                        return;
                                    case 15:
                                    case 18:
                                    case 19:
                                        break;
                                    case 16:
                                    case 17:
                                        defaultRSASignatureAlgorithms = TlsUtils.getDefaultECDSASignatureAlgorithms();
                                        this.supportedSignatureAlgorithms = defaultRSASignatureAlgorithms;
                                    default:
                                        switch (i) {
                                            case 21:
                                            case 24:
                                                return;
                                            case 22:
                                                break;
                                            case 23:
                                                break;
                                            default:
                                                throw new IllegalStateException("unsupported key exchange algorithm");
                                        }
                                        this.supportedSignatureAlgorithms = defaultRSASignatureAlgorithms;
                                }
                            }
                        }
                    }
                }
                defaultRSASignatureAlgorithms = TlsUtils.getDefaultDSSSignatureAlgorithms();
                this.supportedSignatureAlgorithms = defaultRSASignatureAlgorithms;
            }
            defaultRSASignatureAlgorithms = TlsUtils.getDefaultRSASignatureAlgorithms();
            this.supportedSignatureAlgorithms = defaultRSASignatureAlgorithms;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DigitallySigned parseSignature(InputStream inputStream) throws IOException {
        DigitallySigned parse = DigitallySigned.parse(this.context, inputStream);
        SignatureAndHashAlgorithm algorithm = parse.getAlgorithm();
        if (algorithm != null) {
            TlsUtils.verifySupportedSignatureAlgorithm(this.supportedSignatureAlgorithms, algorithm);
        }
        return parse;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processClientCertificate(Certificate certificate) throws IOException {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) throws IOException {
        Vector vector = this.supportedSignatureAlgorithms;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        processServerCertificate(tlsCredentials.getCertificate());
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        if (!requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return false;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipClientCredentials() throws IOException {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipServerKeyExchange() throws IOException {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
    }
}
