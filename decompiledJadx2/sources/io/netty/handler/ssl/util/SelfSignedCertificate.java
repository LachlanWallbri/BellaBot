package io.netty.handler.ssl.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SelfSignedCertificate {
    private final X509Certificate cert;
    private final File certificate;
    private final PrivateKey key;
    private final File privateKey;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SelfSignedCertificate.class);
    private static final Date DEFAULT_NOT_BEFORE = new Date(SystemPropertyUtil.getLong("io.netty.selfSignedCertificate.defaultNotBefore", System.currentTimeMillis() - 31536000000L));
    private static final Date DEFAULT_NOT_AFTER = new Date(SystemPropertyUtil.getLong("io.netty.selfSignedCertificate.defaultNotAfter", 253402300799000L));

    public SelfSignedCertificate() throws CertificateException {
        this(DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER);
    }

    public SelfSignedCertificate(Date date, Date date2) throws CertificateException {
        this("example.com", date, date2);
    }

    public SelfSignedCertificate(String str) throws CertificateException {
        this(str, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER);
    }

    public SelfSignedCertificate(String str, Date date, Date date2) throws CertificateException {
        this(str, ThreadLocalInsecureRandom.current(), 1024, date, date2);
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i) throws CertificateException {
        this(str, secureRandom, i, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i, Date date, Date date2) throws CertificateException {
        CertificateException certificateException;
        String[] generate;
        ?? file;
        Throwable th;
        Exception e;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(i, secureRandom);
            KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
            try {
                generate = OpenJdkSelfSignedCertGenerator.generate(str, generateKeyPair, secureRandom, date, date2);
            } finally {
                try {
                    this.certificate = new File(generate[0]);
                    file = new File(generate[1]);
                    this.privateKey = file;
                    this.key = generateKeyPair.getPrivate();
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.certificate);
                    } catch (Exception e2) {
                        e = e2;
                    } catch (Throwable th2) {
                        file = 0;
                        th = th2;
                        if (file != 0) {
                        }
                        throw th;
                    }
                    try {
                        this.cert = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(fileInputStream);
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            logger.warn("Failed to close a file: " + this.certificate, (Throwable) e3);
                            return;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        throw new CertificateEncodingException(e);
                    }
                } catch (Throwable th3) {
                }
            }
            this.certificate = new File(generate[0]);
            file = new File(generate[1]);
            this.privateKey = file;
            this.key = generateKeyPair.getPrivate();
            try {
                FileInputStream fileInputStream2 = new FileInputStream(this.certificate);
                this.cert = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(fileInputStream2);
                fileInputStream2.close();
            } catch (Throwable th4) {
                th = th4;
                if (file != 0) {
                    try {
                        file.close();
                    } catch (IOException e5) {
                        logger.warn("Failed to close a file: " + this.certificate, (Throwable) e5);
                    }
                }
                throw th;
            }
        } catch (NoSuchAlgorithmException e6) {
            throw new Error(e6);
        }
    }

    public File certificate() {
        return this.certificate;
    }

    public File privateKey() {
        return this.privateKey;
    }

    public X509Certificate cert() {
        return this.cert;
    }

    public PrivateKey key() {
        return this.key;
    }

    public void delete() {
        safeDelete(this.certificate);
        safeDelete(this.privateKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] newSelfSignedCertificate(String str, PrivateKey privateKey, X509Certificate x509Certificate) throws IOException, CertificateEncodingException {
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(privateKey.getEncoded());
        try {
            try {
                String str2 = "-----BEGIN PRIVATE KEY-----\n" + Base64.encode(wrappedBuffer, true).toString(CharsetUtil.US_ASCII) + "\n-----END PRIVATE KEY-----\n";
                wrappedBuffer.release();
                File createTempFile = File.createTempFile("keyutil_" + str + '_', ".key");
                createTempFile.deleteOnExit();
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                try {
                    fileOutputStream.write(str2.getBytes(CharsetUtil.US_ASCII));
                    fileOutputStream.close();
                    wrappedBuffer = Unpooled.wrappedBuffer(x509Certificate.getEncoded());
                    try {
                        try {
                            String str3 = "-----BEGIN CERTIFICATE-----\n" + Base64.encode(wrappedBuffer, true).toString(CharsetUtil.US_ASCII) + "\n-----END CERTIFICATE-----\n";
                            wrappedBuffer.release();
                            File createTempFile2 = File.createTempFile("keyutil_" + str + '_', ".crt");
                            createTempFile2.deleteOnExit();
                            FileOutputStream fileOutputStream2 = new FileOutputStream(createTempFile2);
                            try {
                                fileOutputStream2.write(str3.getBytes(CharsetUtil.US_ASCII));
                                fileOutputStream2.close();
                                return new String[]{createTempFile2.getPath(), createTempFile.getPath()};
                            } catch (Throwable th) {
                                safeClose(createTempFile2, fileOutputStream2);
                                safeDelete(createTempFile2);
                                safeDelete(createTempFile);
                                throw th;
                            }
                        } finally {
                        }
                    } finally {
                    }
                } catch (Throwable th2) {
                    safeClose(createTempFile, fileOutputStream);
                    safeDelete(createTempFile);
                    throw th2;
                }
            } finally {
            }
        } finally {
        }
    }

    private static void safeDelete(File file) {
        if (file.delete()) {
            return;
        }
        logger.warn("Failed to delete a file: " + file);
    }

    private static void safeClose(File file, OutputStream outputStream) {
        try {
            outputStream.close();
        } catch (IOException e) {
            logger.warn("Failed to close a file: " + file, (Throwable) e);
        }
    }
}
