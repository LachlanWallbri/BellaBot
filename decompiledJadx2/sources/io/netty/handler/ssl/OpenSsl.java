package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.internal.tcnative.Buffer;
import io.netty.internal.tcnative.Library;
import io.netty.internal.tcnative.SSL;
import io.netty.internal.tcnative.SSLContext;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.NativeLibraryLoader;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.codec.language.bm.Rule;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class OpenSsl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final Set<String> AVAILABLE_CIPHER_SUITES;
    private static final Set<String> AVAILABLE_JAVA_CIPHER_SUITES;
    private static final Set<String> AVAILABLE_OPENSSL_CIPHER_SUITES;
    static final List<String> DEFAULT_CIPHERS;
    static final Set<String> SUPPORTED_PROTOCOLS_SET;
    private static final boolean SUPPORTS_HOSTNAME_VALIDATION;
    private static final boolean SUPPORTS_KEYMANAGER_FACTORY;
    private static final boolean SUPPORTS_OCSP;
    private static final Throwable UNAVAILABILITY_CAUSE;
    private static final boolean USE_KEYMANAGER_FACTORY;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OpenSsl.class);

    /* JADX WARN: Removed duplicated region for block: B:35:0x0136 A[Catch: all -> 0x0142, TryCatch #1 {all -> 0x0142, blocks: (B:33:0x012f, B:35:0x0136, B:37:0x013b), top: B:32:0x012f }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x013b A[Catch: all -> 0x0142, TRY_LEAVE, TryCatch #1 {all -> 0x0142, blocks: (B:33:0x012f, B:35:0x0136, B:37:0x013b), top: B:32:0x012f }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0191 A[LOOP:1: B:41:0x018b->B:43:0x0191, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0151 A[Catch: all -> 0x015a, TryCatch #11 {all -> 0x015a, blocks: (B:80:0x014a, B:82:0x0151, B:84:0x0156, B:85:0x0159), top: B:79:0x014a }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0156 A[Catch: all -> 0x015a, TryCatch #11 {all -> 0x015a, blocks: (B:80:0x014a, B:82:0x0151, B:84:0x0156, B:85:0x0159), top: B:79:0x014a }] */
    static {
        ClassNotFoundException classNotFoundException;
        boolean z;
        boolean z2;
        long make;
        long newSSL;
        long j;
        SelfSignedCertificate selfSignedCertificate;
        boolean z3 = false;
        r1 = null;
        r1 = null;
        SelfSignedCertificate selfSignedCertificate2 = null;
        try {
            Class.forName("io.netty.internal.tcnative.SSL", false, OpenSsl.class.getClassLoader());
            e = null;
        } catch (ClassNotFoundException e) {
            e = e;
            logger.debug("netty-tcnative not in the classpath; " + OpenSslEngine.class.getSimpleName() + " will be unavailable.");
        }
        if (e == null) {
            try {
                loadTcNative();
                classNotFoundException = e;
            } catch (Throwable th) {
                classNotFoundException = th;
                logger.debug("Failed to load netty-tcnative; " + OpenSslEngine.class.getSimpleName() + " will be unavailable, unless the application has already loaded the symbols by some other means. See http://netty.io/wiki/forked-tomcat-native.html for more information.", (Throwable) classNotFoundException);
            }
            try {
                initializeTcNative();
                e = null;
            } catch (Throwable th2) {
                e = classNotFoundException == null ? th2 : classNotFoundException;
                logger.debug("Failed to initialize netty-tcnative; " + OpenSslEngine.class.getSimpleName() + " will be unavailable. See http://netty.io/wiki/forked-tomcat-native.html for more information.", (Throwable) th2);
            }
        }
        UNAVAILABILITY_CAUSE = e;
        if (e == null) {
            logger.debug("netty-tcnative using native library: {}", SSL.versionString());
            ArrayList arrayList = new ArrayList();
            LinkedHashSet linkedHashSet = new LinkedHashSet(128);
            try {
                try {
                    make = SSLContext.make(31, 1);
                    try {
                        SSLContext.setCipherSuite(make, Rule.ALL);
                        newSSL = SSL.newSSL(make, true);
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Exception e2) {
                    e = e2;
                    logger.warn("Failed to get the list of available OpenSSL cipher suites.", (Throwable) e);
                    AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                    LinkedHashSet linkedHashSet2 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                    while (r2.hasNext()) {
                    }
                    SslUtils.addIfSupported(linkedHashSet2, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                    SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet2);
                    DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                    AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet2);
                    LinkedHashSet linkedHashSet3 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                    linkedHashSet3.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                    linkedHashSet3.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                    AVAILABLE_CIPHER_SUITES = linkedHashSet3;
                    SUPPORTS_KEYMANAGER_FACTORY = z2;
                    SUPPORTS_HOSTNAME_VALIDATION = z;
                    USE_KEYMANAGER_FACTORY = z3;
                    LinkedHashSet linkedHashSet4 = new LinkedHashSet(6);
                    linkedHashSet4.add("SSLv2Hello");
                    if (doesSupportProtocol(1)) {
                    }
                    if (doesSupportProtocol(2)) {
                    }
                    if (doesSupportProtocol(4)) {
                    }
                    if (doesSupportProtocol(8)) {
                    }
                    if (doesSupportProtocol(16)) {
                    }
                    SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet4);
                    SUPPORTS_OCSP = doesSupportOcsp();
                    if (logger.isDebugEnabled()) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                z3 = false;
                z = false;
                z2 = false;
                logger.warn("Failed to get the list of available OpenSSL cipher suites.", (Throwable) e);
                AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                LinkedHashSet linkedHashSet22 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                while (r2.hasNext()) {
                }
                SslUtils.addIfSupported(linkedHashSet22, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet22);
                DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet22);
                LinkedHashSet linkedHashSet32 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                linkedHashSet32.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                linkedHashSet32.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                AVAILABLE_CIPHER_SUITES = linkedHashSet32;
                SUPPORTS_KEYMANAGER_FACTORY = z2;
                SUPPORTS_HOSTNAME_VALIDATION = z;
                USE_KEYMANAGER_FACTORY = z3;
                LinkedHashSet linkedHashSet42 = new LinkedHashSet(6);
                linkedHashSet42.add("SSLv2Hello");
                if (doesSupportProtocol(1)) {
                }
                if (doesSupportProtocol(2)) {
                }
                if (doesSupportProtocol(4)) {
                }
                if (doesSupportProtocol(8)) {
                }
                if (doesSupportProtocol(16)) {
                }
                SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet42);
                SUPPORTS_OCSP = doesSupportOcsp();
                if (logger.isDebugEnabled()) {
                }
            }
            try {
                for (String str : SSL.getCiphers(newSSL)) {
                    try {
                        if (str != null && !str.isEmpty() && !linkedHashSet.contains(str)) {
                            linkedHashSet.add(str);
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        j = 0;
                        try {
                            SSL.freeSSL(newSSL);
                            if (j != 0) {
                            }
                            if (selfSignedCertificate2 != null) {
                            }
                            throw th;
                        } catch (Throwable th5) {
                            th = th5;
                            SSLContext.free(make);
                            throw th;
                        }
                    }
                }
                try {
                    SSL.setHostNameValidation(newSSL, 0, "netty.io");
                    z = true;
                } catch (Throwable unused) {
                    logger.debug("Hostname Verification not supported.");
                    z = false;
                }
                try {
                    selfSignedCertificate = new SelfSignedCertificate();
                } catch (Throwable unused2) {
                }
                try {
                    j = ReferenceCountedOpenSslContext.toBIO(selfSignedCertificate.cert());
                    try {
                        SSL.setCertificateChainBio(newSSL, j, false);
                        try {
                            try {
                                z3 = ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: io.netty.handler.ssl.OpenSsl.1
                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // java.security.PrivilegedAction
                                    public Boolean run() {
                                        return Boolean.valueOf(SystemPropertyUtil.getBoolean("io.netty.handler.ssl.openssl.useKeyManagerFactory", true));
                                    }
                                })).booleanValue();
                            } catch (Throwable unused3) {
                                selfSignedCertificate2 = selfSignedCertificate;
                                z2 = true;
                                try {
                                    logger.debug("KeyManagerFactory not supported.");
                                    selfSignedCertificate = selfSignedCertificate2;
                                    z3 = false;
                                    SSL.freeSSL(newSSL);
                                    if (j != 0) {
                                    }
                                    if (selfSignedCertificate != null) {
                                    }
                                    SSLContext.free(make);
                                    AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                                    LinkedHashSet linkedHashSet222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                                    while (r2.hasNext()) {
                                    }
                                    SslUtils.addIfSupported(linkedHashSet222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                                    SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet222);
                                    DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                                    AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet222);
                                    LinkedHashSet linkedHashSet322 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                                    linkedHashSet322.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                                    linkedHashSet322.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                                    AVAILABLE_CIPHER_SUITES = linkedHashSet322;
                                    SUPPORTS_KEYMANAGER_FACTORY = z2;
                                    SUPPORTS_HOSTNAME_VALIDATION = z;
                                    USE_KEYMANAGER_FACTORY = z3;
                                    LinkedHashSet linkedHashSet422 = new LinkedHashSet(6);
                                    linkedHashSet422.add("SSLv2Hello");
                                    if (doesSupportProtocol(1)) {
                                    }
                                    if (doesSupportProtocol(2)) {
                                    }
                                    if (doesSupportProtocol(4)) {
                                    }
                                    if (doesSupportProtocol(8)) {
                                    }
                                    if (doesSupportProtocol(16)) {
                                    }
                                    SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet422);
                                    SUPPORTS_OCSP = doesSupportOcsp();
                                    if (logger.isDebugEnabled()) {
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    SSL.freeSSL(newSSL);
                                    if (j != 0) {
                                        SSL.freeBIO(j);
                                    }
                                    if (selfSignedCertificate2 != null) {
                                        selfSignedCertificate2.delete();
                                    }
                                    throw th;
                                }
                            }
                        } catch (Throwable unused4) {
                            logger.debug("Failed to get useKeyManagerFactory system property.");
                            z3 = false;
                        }
                        z2 = true;
                    } catch (Throwable unused5) {
                        selfSignedCertificate2 = selfSignedCertificate;
                        z2 = false;
                    }
                } catch (Throwable unused6) {
                    selfSignedCertificate2 = selfSignedCertificate;
                    z2 = false;
                    j = 0;
                    logger.debug("KeyManagerFactory not supported.");
                    selfSignedCertificate = selfSignedCertificate2;
                    z3 = false;
                    SSL.freeSSL(newSSL);
                    if (j != 0) {
                    }
                    if (selfSignedCertificate != null) {
                    }
                    SSLContext.free(make);
                    AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                    LinkedHashSet linkedHashSet2222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                    while (r2.hasNext()) {
                    }
                    SslUtils.addIfSupported(linkedHashSet2222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                    SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet2222);
                    DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                    AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet2222);
                    LinkedHashSet linkedHashSet3222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                    linkedHashSet3222.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                    linkedHashSet3222.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                    AVAILABLE_CIPHER_SUITES = linkedHashSet3222;
                    SUPPORTS_KEYMANAGER_FACTORY = z2;
                    SUPPORTS_HOSTNAME_VALIDATION = z;
                    USE_KEYMANAGER_FACTORY = z3;
                    LinkedHashSet linkedHashSet4222 = new LinkedHashSet(6);
                    linkedHashSet4222.add("SSLv2Hello");
                    if (doesSupportProtocol(1)) {
                    }
                    if (doesSupportProtocol(2)) {
                    }
                    if (doesSupportProtocol(4)) {
                    }
                    if (doesSupportProtocol(8)) {
                    }
                    if (doesSupportProtocol(16)) {
                    }
                    SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet4222);
                    SUPPORTS_OCSP = doesSupportOcsp();
                    if (logger.isDebugEnabled()) {
                    }
                }
                try {
                    SSL.freeSSL(newSSL);
                    if (j != 0) {
                        SSL.freeBIO(j);
                    }
                    if (selfSignedCertificate != null) {
                        selfSignedCertificate.delete();
                    }
                    SSLContext.free(make);
                    AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                    LinkedHashSet linkedHashSet22222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                    for (String str2 : AVAILABLE_OPENSSL_CIPHER_SUITES) {
                        linkedHashSet22222.add(CipherSuiteConverter.toJava(str2, "TLS"));
                        linkedHashSet22222.add(CipherSuiteConverter.toJava(str2, "SSL"));
                    }
                    SslUtils.addIfSupported(linkedHashSet22222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                    SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet22222);
                    DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                    AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet22222);
                    LinkedHashSet linkedHashSet32222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                    linkedHashSet32222.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                    linkedHashSet32222.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                    AVAILABLE_CIPHER_SUITES = linkedHashSet32222;
                    SUPPORTS_KEYMANAGER_FACTORY = z2;
                    SUPPORTS_HOSTNAME_VALIDATION = z;
                    USE_KEYMANAGER_FACTORY = z3;
                    LinkedHashSet linkedHashSet42222 = new LinkedHashSet(6);
                    linkedHashSet42222.add("SSLv2Hello");
                    if (doesSupportProtocol(1)) {
                        linkedHashSet42222.add("SSLv2");
                    }
                    if (doesSupportProtocol(2)) {
                        linkedHashSet42222.add("SSLv3");
                    }
                    if (doesSupportProtocol(4)) {
                        linkedHashSet42222.add("TLSv1");
                    }
                    if (doesSupportProtocol(8)) {
                        linkedHashSet42222.add("TLSv1.1");
                    }
                    if (doesSupportProtocol(16)) {
                        linkedHashSet42222.add("TLSv1.2");
                    }
                    SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet42222);
                    SUPPORTS_OCSP = doesSupportOcsp();
                    if (logger.isDebugEnabled()) {
                        logger.debug("Supported protocols (OpenSSL): {} ", Arrays.asList(SUPPORTED_PROTOCOLS_SET));
                        logger.debug("Default cipher suites (OpenSSL): {}", DEFAULT_CIPHERS);
                    }
                } catch (Throwable th7) {
                    th = th7;
                    SSLContext.free(make);
                    throw th;
                }
            } catch (Throwable th8) {
                th = th8;
                j = 0;
            }
        } else {
            DEFAULT_CIPHERS = Collections.emptyList();
            AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.emptySet();
            AVAILABLE_JAVA_CIPHER_SUITES = Collections.emptySet();
            AVAILABLE_CIPHER_SUITES = Collections.emptySet();
            SUPPORTS_KEYMANAGER_FACTORY = false;
            SUPPORTS_HOSTNAME_VALIDATION = false;
            USE_KEYMANAGER_FACTORY = false;
            SUPPORTED_PROTOCOLS_SET = Collections.emptySet();
            SUPPORTS_OCSP = false;
        }
    }

    private static boolean doesSupportOcsp() {
        long j;
        if (version() >= 268443648) {
            try {
                j = SSLContext.make(16, 1);
            } catch (Exception unused) {
                j = -1;
            } catch (Throwable th) {
                th = th;
                j = -1;
            }
            try {
                SSLContext.enableOcsp(j, false);
                if (j == -1) {
                    return true;
                }
                SSLContext.free(j);
                return true;
            } catch (Exception unused2) {
                if (j != -1) {
                    SSLContext.free(j);
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (j != -1) {
                    SSLContext.free(j);
                }
                throw th;
            }
        }
        return false;
    }

    private static boolean doesSupportProtocol(int i) {
        try {
            long make = SSLContext.make(i, 2);
            if (make != -1) {
                SSLContext.free(make);
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isAvailable() {
        return UNAVAILABILITY_CAUSE == null;
    }

    public static boolean isAlpnSupported() {
        return ((long) version()) >= 268443648;
    }

    public static boolean isOcspSupported() {
        return SUPPORTS_OCSP;
    }

    public static int version() {
        if (isAvailable()) {
            return SSL.version();
        }
        return -1;
    }

    public static String versionString() {
        if (isAvailable()) {
            return SSL.versionString();
        }
        return null;
    }

    public static void ensureAvailability() {
        if (UNAVAILABILITY_CAUSE != null) {
            throw ((Error) new UnsatisfiedLinkError("failed to load the required native library").initCause(UNAVAILABILITY_CAUSE));
        }
    }

    public static Throwable unavailabilityCause() {
        return UNAVAILABILITY_CAUSE;
    }

    @Deprecated
    public static Set<String> availableCipherSuites() {
        return availableOpenSslCipherSuites();
    }

    public static Set<String> availableOpenSslCipherSuites() {
        return AVAILABLE_OPENSSL_CIPHER_SUITES;
    }

    public static Set<String> availableJavaCipherSuites() {
        return AVAILABLE_JAVA_CIPHER_SUITES;
    }

    public static boolean isCipherSuiteAvailable(String str) {
        String openSsl = CipherSuiteConverter.toOpenSsl(str);
        if (openSsl != null) {
            str = openSsl;
        }
        return AVAILABLE_OPENSSL_CIPHER_SUITES.contains(str);
    }

    public static boolean supportsKeyManagerFactory() {
        return SUPPORTS_KEYMANAGER_FACTORY;
    }

    public static boolean supportsHostnameValidation() {
        return SUPPORTS_HOSTNAME_VALIDATION;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean useKeyManagerFactory() {
        return USE_KEYMANAGER_FACTORY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long memoryAddress(ByteBuf byteBuf) {
        return byteBuf.hasMemoryAddress() ? byteBuf.memoryAddress() : Buffer.address(byteBuf.nioBuffer());
    }

    private OpenSsl() {
    }

    private static void loadTcNative() throws Exception {
        String normalizedOs = PlatformDependent.normalizedOs();
        String normalizedArch = PlatformDependent.normalizedArch();
        LinkedHashSet linkedHashSet = new LinkedHashSet(4);
        linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch);
        if ("linux".equalsIgnoreCase(normalizedOs)) {
            linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch + "_fedora");
        }
        linkedHashSet.add("netty_tcnative_" + normalizedArch);
        linkedHashSet.add("netty_tcnative");
        NativeLibraryLoader.loadFirstAvailable(SSL.class.getClassLoader(), (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]));
    }

    private static boolean initializeTcNative() throws Exception {
        return Library.initialize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void releaseIfNeeded(ReferenceCounted referenceCounted) {
        if (referenceCounted.refCnt() > 0) {
            ReferenceCountUtil.safeRelease(referenceCounted);
        }
    }
}
