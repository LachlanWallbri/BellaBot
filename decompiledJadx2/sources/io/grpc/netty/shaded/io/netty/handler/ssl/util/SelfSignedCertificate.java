package io.grpc.netty.shaded.io.netty.handler.ssl.util;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
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
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SelfSignedCertificate {
    private final X509Certificate cert;
    private final File certificate;
    private final PrivateKey key;
    private final File privateKey;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SelfSignedCertificate.class);
    private static final Date DEFAULT_NOT_BEFORE = new Date(SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.selfSignedCertificate.defaultNotBefore", System.currentTimeMillis() - 31536000000L));
    private static final Date DEFAULT_NOT_AFTER = new Date(SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.selfSignedCertificate.defaultNotAfter", 253402300799000L));
    private static final int DEFAULT_KEY_LENGTH_BITS = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.handler.ssl.util.selfSignedKeyStrength", 2048);

    public SelfSignedCertificate() throws CertificateException {
        this(DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(Date date, Date date2) throws CertificateException {
        this("localhost", date, date2, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(Date date, Date date2, String str, int i) throws CertificateException {
        this("localhost", date, date2, str, i);
    }

    public SelfSignedCertificate(String str) throws CertificateException {
        this(str, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(String str, String str2, int i) throws CertificateException {
        this(str, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, str2, i);
    }

    public SelfSignedCertificate(String str, Date date, Date date2) throws CertificateException {
        this(str, ThreadLocalInsecureRandom.current(), DEFAULT_KEY_LENGTH_BITS, date, date2, "RSA");
    }

    public SelfSignedCertificate(String str, Date date, Date date2, String str2, int i) throws CertificateException {
        this(str, ThreadLocalInsecureRandom.current(), i, date, date2, str2);
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i) throws CertificateException {
        this(str, secureRandom, i, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA");
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, String str2, int i) throws CertificateException {
        this(str, secureRandom, i, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, str2);
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i, Date date, Date date2) throws CertificateException {
        this(str, secureRandom, i, date, date2, "RSA");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:8|9|10|(4:(2:11|12)|16|17|(3:18|19|21))|13|14|15) */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b0, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b1, code lost:
    
        r11 = null;
        r10 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ab, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ac, code lost:
    
        r11 = null;
        r10 = r11;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i, Date date, Date date2, String str2) throws CertificateException {
        CertificateException certificateException;
        String[] generate;
        FileInputStream fileInputStream;
        if (!str2.equalsIgnoreCase("EC") && !str2.equalsIgnoreCase("RSA")) {
            throw new IllegalArgumentException("Algorithm not valid: " + str2);
        }
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(str2);
            keyPairGenerator.initialize(i, secureRandom);
            KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
            try {
                try {
                    try {
                        generate = OpenJdkSelfSignedCertGenerator.generate(str, generateKeyPair, secureRandom, date, date2, str2);
                    } finally {
                        try {
                            this.certificate = new File(generate[0]);
                            this.privateKey = new File(generate[1]);
                            this.key = generateKeyPair.getPrivate();
                            fileInputStream = new FileInputStream(this.certificate);
                            this.cert = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(fileInputStream);
                            fileInputStream.close();
                            return;
                        } catch (Throwable th) {
                        }
                    }
                    this.cert = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(fileInputStream);
                    try {
                        fileInputStream.close();
                        return;
                    } catch (IOException e) {
                        if (logger.isWarnEnabled()) {
                            logger.warn("Failed to close a file: " + this.certificate, (Throwable) e);
                            return;
                        }
                        return;
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            if (logger.isWarnEnabled()) {
                                logger.warn("Failed to close a file: " + this.certificate, (Throwable) e2);
                            }
                        }
                    }
                    throw th3;
                }
            } catch (Exception e3) {
                Exception e4 = e3;
                throw new CertificateEncodingException(e4);
            }
            this.certificate = new File(generate[0]);
            this.privateKey = new File(generate[1]);
            this.key = generateKeyPair.getPrivate();
            fileInputStream = new FileInputStream(this.certificate);
        } catch (NoSuchAlgorithmException e5) {
            throw new Error(e5);
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
        if (file.delete() || !logger.isWarnEnabled()) {
            return;
        }
        logger.warn("Failed to delete a file: " + file);
    }

    private static void safeClose(File file, OutputStream outputStream) {
        try {
            outputStream.close();
        } catch (IOException e) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to close a file: " + file, (Throwable) e);
            }
        }
    }
}
