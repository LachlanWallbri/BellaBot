package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.internal.tcnative.SSL;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLException;
import javax.net.ssl.X509KeyManager;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
class OpenSslKeyMaterialProvider {
    private final X509KeyManager keyManager;
    private final String password;

    void destroy() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslKeyMaterialProvider(X509KeyManager x509KeyManager, String str) {
        this.keyManager = x509KeyManager;
        this.password = str;
    }

    static void validateKeyMaterialSupported(X509Certificate[] x509CertificateArr, PrivateKey privateKey, String str) throws SSLException {
        validateSupported(x509CertificateArr);
        validateSupported(privateKey, str);
    }

    private static void validateSupported(PrivateKey privateKey, String str) throws SSLException {
        if (privateKey == null) {
            return;
        }
        long j = 0;
        try {
            try {
                long bio = ReferenceCountedOpenSslContext.toBIO(UnpooledByteBufAllocator.DEFAULT, privateKey);
                try {
                    long parsePrivateKey = SSL.parsePrivateKey(bio, str);
                    SSL.freeBIO(bio);
                    if (parsePrivateKey != 0) {
                        SSL.freePrivateKey(parsePrivateKey);
                    }
                } catch (Exception e) {
                    e = e;
                    throw new SSLException("PrivateKey type not supported " + privateKey.getFormat(), e);
                } catch (Throwable th) {
                    th = th;
                    j = bio;
                    SSL.freeBIO(j);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void validateSupported(X509Certificate[] x509CertificateArr) throws SSLException {
        if (x509CertificateArr == null || x509CertificateArr.length == 0) {
            return;
        }
        PemEncoded pemEncoded = null;
        long j = 0;
        try {
            try {
                pemEncoded = PemX509Certificate.toPEM(UnpooledByteBufAllocator.DEFAULT, true, x509CertificateArr);
                long bio = ReferenceCountedOpenSslContext.toBIO(UnpooledByteBufAllocator.DEFAULT, pemEncoded.retain());
                try {
                    long parseX509Chain = SSL.parseX509Chain(bio);
                    SSL.freeBIO(bio);
                    if (parseX509Chain != 0) {
                        SSL.freeX509Chain(parseX509Chain);
                    }
                    if (pemEncoded != null) {
                        pemEncoded.release();
                    }
                } catch (Exception e) {
                    e = e;
                    throw new SSLException("Certificate type not supported", e);
                } catch (Throwable th) {
                    th = th;
                    j = bio;
                    SSL.freeBIO(j);
                    if (pemEncoded != null) {
                        pemEncoded.release();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    X509KeyManager keyManager() {
        return this.keyManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public OpenSslKeyMaterial chooseKeyMaterial(ByteBufAllocator byteBufAllocator, String str) throws Exception {
        long j;
        long j2;
        long j3;
        long j4;
        OpenSslKeyMaterial defaultOpenSslKeyMaterial;
        X509Certificate[] certificateChain = this.keyManager.getCertificateChain(str);
        if (certificateChain == null || certificateChain.length == 0) {
            return null;
        }
        PrivateKey privateKey = this.keyManager.getPrivateKey(str);
        PemEncoded pem = PemX509Certificate.toPEM(byteBufAllocator, true, certificateChain);
        try {
            j = ReferenceCountedOpenSslContext.toBIO(byteBufAllocator, pem.retain());
            try {
                j2 = SSL.parseX509Chain(j);
            } catch (Throwable th) {
                th = th;
                j2 = 0;
                j3 = j2;
                j4 = j3;
                SSL.freeBIO(j);
                SSL.freeBIO(j4);
                if (j2 != 0) {
                }
                if (j3 != 0) {
                }
                pem.release();
                throw th;
            }
            try {
                if (privateKey instanceof OpenSslPrivateKey) {
                    defaultOpenSslKeyMaterial = ((OpenSslPrivateKey) privateKey).newKeyMaterial(j2, certificateChain);
                    j4 = 0;
                } else {
                    long bio = ReferenceCountedOpenSslContext.toBIO(byteBufAllocator, privateKey);
                    if (privateKey == null) {
                        j3 = 0;
                    } else {
                        try {
                            j3 = SSL.parsePrivateKey(bio, this.password);
                        } catch (Throwable th2) {
                            th = th2;
                            j4 = bio;
                            j3 = 0;
                            SSL.freeBIO(j);
                            SSL.freeBIO(j4);
                            if (j2 != 0) {
                                SSL.freeX509Chain(j2);
                            }
                            if (j3 != 0) {
                                SSL.freePrivateKey(j3);
                            }
                            pem.release();
                            throw th;
                        }
                    }
                    try {
                        j4 = bio;
                        try {
                            defaultOpenSslKeyMaterial = new DefaultOpenSslKeyMaterial(j2, j3, certificateChain);
                        } catch (Throwable th3) {
                            th = th3;
                            SSL.freeBIO(j);
                            SSL.freeBIO(j4);
                            if (j2 != 0) {
                            }
                            if (j3 != 0) {
                            }
                            pem.release();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        j4 = bio;
                    }
                }
                SSL.freeBIO(j);
                SSL.freeBIO(j4);
                pem.release();
                return defaultOpenSslKeyMaterial;
            } catch (Throwable th5) {
                th = th5;
                j3 = 0;
                j4 = j3;
                SSL.freeBIO(j);
                SSL.freeBIO(j4);
                if (j2 != 0) {
                }
                if (j3 != 0) {
                }
                pem.release();
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            j = 0;
            j2 = 0;
        }
    }
}
