package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.UnpooledByteBufAllocator;
import io.grpc.netty.shaded.io.netty.internal.tcnative.Buffer;
import io.grpc.netty.shaded.io.netty.internal.tcnative.Library;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSL;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSLContext;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.NativeLibraryLoader;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.codec.language.bm.Rule;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSsl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final Set<String> AVAILABLE_CIPHER_SUITES;
    private static final Set<String> AVAILABLE_JAVA_CIPHER_SUITES;
    private static final Set<String> AVAILABLE_OPENSSL_CIPHER_SUITES;
    private static final String CERT = "-----BEGIN CERTIFICATE-----\nMIICrjCCAZagAwIBAgIIdSvQPv1QAZQwDQYJKoZIhvcNAQELBQAwFjEUMBIGA1UEAxMLZXhhbXBs\nZS5jb20wIBcNMTgwNDA2MjIwNjU5WhgPOTk5OTEyMzEyMzU5NTlaMBYxFDASBgNVBAMTC2V4YW1w\nbGUuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAggbWsmDQ6zNzRZ5AW8E3eoGl\nqWvOBDb5Fs1oBRrVQHuYmVAoaqwDzXYJ0LOwa293AgWEQ1jpcbZ2hpoYQzqEZBTLnFhMrhRFlH6K\nbJND8Y33kZ/iSVBBDuGbdSbJShlM+4WwQ9IAso4MZ4vW3S1iv5fGGpLgbtXRmBf/RU8omN0Gijlv\nWlLWHWijLN8xQtySFuBQ7ssW8RcKAary3pUm6UUQB+Co6lnfti0Tzag8PgjhAJq2Z3wbsGRnP2YS\nvYoaK6qzmHXRYlp/PxrjBAZAmkLJs4YTm/XFF+fkeYx4i9zqHbyone5yerRibsHaXZWLnUL+rFoe\nMdKvr0VS3sGmhQIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQADQi441pKmXf9FvUV5EHU4v8nJT9Iq\nyqwsKwXnr7AsUlDGHBD7jGrjAXnG5rGxuNKBQ35wRxJATKrUtyaquFUL6H8O6aGQehiFTk6zmPbe\n12Gu44vqqTgIUxnv3JQJiox8S2hMxsSddpeCmSdvmalvD6WG4NthH6B9ZaBEiep1+0s0RUaBYn73\nI7CCUaAtbjfR6pcJjrFk5ei7uwdQZFSJtkP2z8r7zfeANJddAKFlkaMWn7u+OIVuB4XPooWicObk\nNAHFtP65bocUYnDpTVdiyvn8DdqyZ/EO8n1bBKBzuSLplk2msW4pdgaFgY7Vw/0wzcFXfUXmL1uy\nG8sQD/wx\n-----END CERTIFICATE-----";
    static final List<String> DEFAULT_CIPHERS;
    static final String[] EXTRA_SUPPORTED_TLS_1_3_CIPHERS;
    private static final boolean IS_BORINGSSL;
    private static final String KEY = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCBtayYNDrM3NFnkBbwTd6gaWp\na84ENvkWzWgFGtVAe5iZUChqrAPNdgnQs7Brb3cCBYRDWOlxtnaGmhhDOoRkFMucWEyuFEWUfops\nk0PxjfeRn+JJUEEO4Zt1JslKGUz7hbBD0gCyjgxni9bdLWK/l8YakuBu1dGYF/9FTyiY3QaKOW9a\nUtYdaKMs3zFC3JIW4FDuyxbxFwoBqvLelSbpRRAH4KjqWd+2LRPNqDw+COEAmrZnfBuwZGc/ZhK9\nihorqrOYddFiWn8/GuMEBkCaQsmzhhOb9cUX5+R5jHiL3OodvKid7nJ6tGJuwdpdlYudQv6sWh4x\n0q+vRVLewaaFAgMBAAECggEAP8tPJvFtTxhNJAkCloHz0D0vpDHqQBMgntlkgayqmBqLwhyb18pR\ni0qwgh7HHc7wWqOOQuSqlEnrWRrdcI6TSe8R/sErzfTQNoznKWIPYcI/hskk4sdnQ//Yn9/Jvnsv\nU/BBjOTJxtD+sQbhAl80JcA3R+5sArURQkfzzHOL/YMqzAsn5hTzp7HZCxUqBk3KaHRxV7NefeOE\nxlZuWSmxYWfbFIs4kx19/1t7h8CHQWezw+G60G2VBtSBBxDnhBWvqG6R/wpzJ3nEhPLLY9T+XIHe\nipzdMOOOUZorfIg7M+pyYPji+ZIZxIpY5OjrOzXHciAjRtr5Y7l99K1CG1LguQKBgQDrQfIMxxtZ\nvxU/1cRmUV9l7pt5bjV5R6byXq178LxPKVYNjdZ840Q0/OpZEVqaT1xKVi35ohP1QfNjxPLlHD+K\niDAR9z6zkwjIrbwPCnb5kuXy4lpwPcmmmkva25fI7qlpHtbcuQdoBdCfr/KkKaUCMPyY89LCXgEw\n5KTDj64UywKBgQCNfbO+eZLGzhiHhtNJurresCsIGWlInv322gL8CSfBMYl6eNfUTZvUDdFhPISL\nUljKWzXDrjw0ujFSPR0XhUGtiq89H+HUTuPPYv25gVXO+HTgBFZEPl4PpA+BUsSVZy0NddneyqLk\n42Wey9omY9Q8WsdNQS5cbUvy0uG6WFoX7wKBgQDZ1jpW8pa0x2bZsQsm4vo+3G5CRnZlUp+XlWt2\ndDcp5dC0xD1zbs1dc0NcLeGDOTDv9FSl7hok42iHXXq8AygjEm/QcuwwQ1nC2HxmQP5holAiUs4D\nWHM8PWs3wFYPzE459EBoKTxeaeP/uWAn+he8q7d5uWvSZlEcANs/6e77eQKBgD21Ar0hfFfj7mK8\n9E0FeRZBsqK3omkfnhcYgZC11Xa2SgT1yvs2Va2n0RcdM5kncr3eBZav2GYOhhAdwyBM55XuE/sO\neokDVutNeuZ6d5fqV96TRaRBpvgfTvvRwxZ9hvKF4Vz+9wfn/JvCwANaKmegF6ejs7pvmF3whq2k\ndrZVAoGAX5YxQ5XMTD0QbMAl7/6qp6S58xNoVdfCkmkj1ZLKaHKIjS/benkKGlySVQVPexPfnkZx\np/Vv9yyphBoudiTBS9Uog66ueLYZqpgxlM/6OhYg86Gm3U2ycvMxYjBM1NFiyze21AqAhI+HX+Ot\nmraV2/guSgDgZAhukRZzeQ2RucI=\n-----END PRIVATE KEY-----";
    static final Set<String> SUPPORTED_PROTOCOLS_SET;
    private static final boolean SUPPORTS_KEYMANAGER_FACTORY;
    private static final boolean SUPPORTS_OCSP;
    private static final boolean TLSV13_SUPPORTED;
    private static final Throwable UNAVAILABILITY_CAUSE;
    private static final boolean USE_KEYMANAGER_FACTORY;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OpenSsl.class);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0279 A[Catch: all -> 0x02c7, TryCatch #13 {all -> 0x02c7, blocks: (B:87:0x0272, B:89:0x0279, B:92:0x0280, B:95:0x0287, B:98:0x028e, B:151:0x02a7, B:153:0x02ae, B:156:0x02b5, B:159:0x02bc, B:162:0x02c3, B:163:0x02c6), top: B:32:0x016c }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0280 A[Catch: all -> 0x02c7, TryCatch #13 {all -> 0x02c7, blocks: (B:87:0x0272, B:89:0x0279, B:92:0x0280, B:95:0x0287, B:98:0x028e, B:151:0x02a7, B:153:0x02ae, B:156:0x02b5, B:159:0x02bc, B:162:0x02c3, B:163:0x02c6), top: B:32:0x016c }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0287 A[Catch: all -> 0x02c7, TryCatch #13 {all -> 0x02c7, blocks: (B:87:0x0272, B:89:0x0279, B:92:0x0280, B:95:0x0287, B:98:0x028e, B:151:0x02a7, B:153:0x02ae, B:156:0x02b5, B:159:0x02bc, B:162:0x02c3, B:163:0x02c6), top: B:32:0x016c }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x028e A[Catch: all -> 0x02c7, TRY_LEAVE, TryCatch #13 {all -> 0x02c7, blocks: (B:87:0x0272, B:89:0x0279, B:92:0x0280, B:95:0x0287, B:98:0x028e, B:151:0x02a7, B:153:0x02ae, B:156:0x02b5, B:159:0x02bc, B:162:0x02c3, B:163:0x02c6), top: B:32:0x016c }] */
    static {
        Throwable th;
        boolean z;
        boolean z2;
        long newSSL;
        long j;
        long j2;
        long j3;
        long j4;
        PemPrivateKey valueOf;
        long j5;
        boolean z3;
        long j6;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        String str = "io.grpc.netty.shaded.io.netty.handler.ssl.openssl.useKeyManagerFactory";
        boolean z9 = false;
        if (SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.handler.ssl.noOpenSsl", false)) {
            e = new UnsupportedOperationException("OpenSSL was explicit disabled with -Dio.netty.handler.ssl.noOpenSsl=true");
            logger.debug("netty-tcnative explicit disabled; " + OpenSslEngine.class.getSimpleName() + " will be unavailable.", e);
        } else {
            try {
                Class.forName("io.grpc.netty.shaded.io.netty.internal.tcnative.SSLContext", false, PlatformDependent.getClassLoader(OpenSsl.class));
                e = null;
            } catch (ClassNotFoundException e) {
                e = e;
                logger.debug("netty-tcnative not in the classpath; " + OpenSslEngine.class.getSimpleName() + " will be unavailable.");
            }
            if (e == null) {
                try {
                    loadTcNative();
                    th = e;
                } catch (Throwable th2) {
                    th = th2;
                    logger.debug("Failed to load netty-tcnative; " + OpenSslEngine.class.getSimpleName() + " will be unavailable, unless the application has already loaded the symbols by some other means. See https://netty.io/wiki/forked-tomcat-native.html for more information.", th);
                }
                try {
                    String str2 = SystemPropertyUtil.get("io.grpc.netty.shaded.io.netty.handler.ssl.openssl.engine", null);
                    if (str2 == null) {
                        logger.debug("Initialize netty-tcnative using engine: 'default'");
                    } else {
                        logger.debug("Initialize netty-tcnative using engine: '{}'", str2);
                    }
                    initializeTcNative(str2);
                    e = null;
                } catch (Throwable th3) {
                    if (th == null) {
                        th = th3;
                    }
                    logger.debug("Failed to initialize netty-tcnative; " + OpenSslEngine.class.getSimpleName() + " will be unavailable. See https://netty.io/wiki/forked-tomcat-native.html for more information.", th3);
                    e = th;
                }
            }
        }
        UNAVAILABILITY_CAUSE = e;
        if (e == null) {
            logger.debug("netty-tcnative using native library: {}", SSL.versionString());
            ArrayList arrayList = new ArrayList();
            LinkedHashSet linkedHashSet = new LinkedHashSet(128);
            IS_BORINGSSL = "BoringSSL".equals(versionString());
            if (IS_BORINGSSL) {
                EXTRA_SUPPORTED_TLS_1_3_CIPHERS = new String[]{"TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384", "TLS_CHACHA20_POLY1305_SHA256"};
            } else {
                EXTRA_SUPPORTED_TLS_1_3_CIPHERS = EmptyArrays.EMPTY_STRINGS;
            }
            try {
                try {
                    long make = SSLContext.make(63, 1);
                    try {
                        StringBuilder sb = new StringBuilder();
                        Iterator<String> it = SslUtils.TLSV13_CIPHERS.iterator();
                        while (it.hasNext()) {
                            String openSsl = CipherSuiteConverter.toOpenSsl(it.next(), IS_BORINGSSL);
                            if (openSsl != null) {
                                sb.append(openSsl);
                                sb.append(':');
                            }
                        }
                        if (sb.length() == 0) {
                            z8 = false;
                        } else {
                            sb.setLength(sb.length() - 1);
                            SSLContext.setCipherSuite(make, sb.toString(), true);
                            z8 = true;
                        }
                        z = z8;
                    } catch (Exception unused) {
                        z = false;
                    } catch (Throwable th4) {
                        th = th4;
                        SSLContext.free(make);
                        throw th;
                    }
                    try {
                        SSLContext.setCipherSuite(make, Rule.ALL, false);
                        newSSL = SSL.newSSL(make, true);
                    } catch (Throwable th5) {
                        th = th5;
                    }
                    try {
                        try {
                            for (String str3 : SSL.getCiphers(newSSL)) {
                                if (str3 != null && !str3.isEmpty() && !linkedHashSet.contains(str3) && (z || !SslUtils.isTLSv13Cipher(str3))) {
                                    linkedHashSet.add(str3);
                                }
                            }
                            if (IS_BORINGSSL) {
                                Collections.addAll(linkedHashSet, EXTRA_SUPPORTED_TLS_1_3_CIPHERS);
                                Collections.addAll(linkedHashSet, "AEAD-AES128-GCM-SHA256", "AEAD-AES256-GCM-SHA384", "AEAD-CHACHA20-POLY1305-SHA256");
                            }
                            try {
                                valueOf = PemPrivateKey.valueOf(KEY.getBytes(CharsetUtil.US_ASCII));
                                try {
                                    SSLContext.setCertificateCallback(make, null);
                                    j = ReferenceCountedOpenSslContext.toBIO(ByteBufAllocator.DEFAULT, selfSignedCertificate());
                                    try {
                                        j2 = SSL.parseX509Chain(j);
                                    } catch (Error unused2) {
                                        z3 = false;
                                        z9 = false;
                                        j2 = 0;
                                        j3 = j2;
                                        z4 = z3;
                                        j4 = j3;
                                        z6 = z4;
                                        try {
                                            logger.debug("KeyManagerFactory not supported.");
                                            valueOf.release();
                                            z5 = z6;
                                            SSL.freeSSL(newSSL);
                                            if (j != 0) {
                                            }
                                            if (j4 != 0) {
                                            }
                                            if (j2 != 0) {
                                            }
                                            if (j3 != 0) {
                                            }
                                            SSLContext.free(make);
                                            z2 = z5;
                                            AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                                            LinkedHashSet linkedHashSet2 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                                            while (r4.hasNext()) {
                                            }
                                            SslUtils.addIfSupported(linkedHashSet2, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                                            SslUtils.addIfSupported(linkedHashSet2, arrayList, SslUtils.TLSV13_CIPHER_SUITES);
                                            SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet2);
                                            DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                                            AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet2);
                                            LinkedHashSet linkedHashSet3 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                                            linkedHashSet3.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                                            linkedHashSet3.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                                            AVAILABLE_CIPHER_SUITES = linkedHashSet3;
                                            SUPPORTS_KEYMANAGER_FACTORY = z2;
                                            USE_KEYMANAGER_FACTORY = z9;
                                            LinkedHashSet linkedHashSet4 = new LinkedHashSet(6);
                                            linkedHashSet4.add("SSLv2Hello");
                                            if (doesSupportProtocol(1, SSL.SSL_OP_NO_SSLv2)) {
                                            }
                                            if (doesSupportProtocol(2, SSL.SSL_OP_NO_SSLv3)) {
                                            }
                                            if (doesSupportProtocol(4, SSL.SSL_OP_NO_TLSv1)) {
                                            }
                                            if (doesSupportProtocol(8, SSL.SSL_OP_NO_TLSv1_1)) {
                                            }
                                            if (doesSupportProtocol(16, SSL.SSL_OP_NO_TLSv1_2)) {
                                            }
                                            if (!z) {
                                            }
                                            TLSV13_SUPPORTED = false;
                                            SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet4);
                                            SUPPORTS_OCSP = doesSupportOcsp();
                                            if (logger.isDebugEnabled()) {
                                            }
                                        } catch (Throwable th6) {
                                            th = th6;
                                            valueOf.release();
                                            throw th;
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                        j5 = 0;
                                        j6 = j5;
                                        valueOf.release();
                                        throw th;
                                    }
                                    try {
                                        long bio = ReferenceCountedOpenSslContext.toBIO(UnpooledByteBufAllocator.DEFAULT, valueOf.retain());
                                        try {
                                            j3 = SSL.parsePrivateKey(bio, null);
                                            j4 = bio;
                                            try {
                                                SSL.setKeyMaterial(newSSL, j2, j3);
                                                try {
                                                    boolean contains = SystemPropertyUtil.contains("io.grpc.netty.shaded.io.netty.handler.ssl.openssl.useKeyManagerFactory");
                                                    if (IS_BORINGSSL) {
                                                        if (contains) {
                                                            try {
                                                                logger.info("System property 'io.netty.handler.ssl.openssl.useKeyManagerFactory' is deprecated and will be ignored when using BoringSSL");
                                                            } catch (Throwable unused3) {
                                                                z7 = true;
                                                                logger.debug("Failed to get useKeyManagerFactory system property.");
                                                                valueOf.release();
                                                                z9 = z7;
                                                                z5 = true;
                                                                SSL.freeSSL(newSSL);
                                                                if (j != 0) {
                                                                }
                                                                if (j4 != 0) {
                                                                }
                                                                if (j2 != 0) {
                                                                }
                                                                if (j3 != 0) {
                                                                }
                                                                SSLContext.free(make);
                                                                z2 = z5;
                                                                AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                                                                LinkedHashSet linkedHashSet22 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                                                                while (r4.hasNext()) {
                                                                }
                                                                SslUtils.addIfSupported(linkedHashSet22, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                                                                SslUtils.addIfSupported(linkedHashSet22, arrayList, SslUtils.TLSV13_CIPHER_SUITES);
                                                                SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet22);
                                                                DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                                                                AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet22);
                                                                LinkedHashSet linkedHashSet32 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                                                                linkedHashSet32.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                                                                linkedHashSet32.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                                                                AVAILABLE_CIPHER_SUITES = linkedHashSet32;
                                                                SUPPORTS_KEYMANAGER_FACTORY = z2;
                                                                USE_KEYMANAGER_FACTORY = z9;
                                                                LinkedHashSet linkedHashSet42 = new LinkedHashSet(6);
                                                                linkedHashSet42.add("SSLv2Hello");
                                                                if (doesSupportProtocol(1, SSL.SSL_OP_NO_SSLv2)) {
                                                                }
                                                                if (doesSupportProtocol(2, SSL.SSL_OP_NO_SSLv3)) {
                                                                }
                                                                if (doesSupportProtocol(4, SSL.SSL_OP_NO_TLSv1)) {
                                                                }
                                                                if (doesSupportProtocol(8, SSL.SSL_OP_NO_TLSv1_1)) {
                                                                }
                                                                if (doesSupportProtocol(16, SSL.SSL_OP_NO_TLSv1_2)) {
                                                                }
                                                                if (!z) {
                                                                }
                                                                TLSV13_SUPPORTED = false;
                                                                SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet42);
                                                                SUPPORTS_OCSP = doesSupportOcsp();
                                                                if (logger.isDebugEnabled()) {
                                                                }
                                                            }
                                                        }
                                                        z7 = true;
                                                    } else {
                                                        z7 = SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.handler.ssl.openssl.useKeyManagerFactory", true);
                                                        if (contains) {
                                                            try {
                                                                logger.info("System property 'io.netty.handler.ssl.openssl.useKeyManagerFactory' is deprecated and so will be ignored in the future");
                                                            } catch (Throwable unused4) {
                                                                try {
                                                                    logger.debug("Failed to get useKeyManagerFactory system property.");
                                                                    valueOf.release();
                                                                    z9 = z7;
                                                                    z5 = true;
                                                                } catch (Error unused5) {
                                                                    z9 = z7;
                                                                    z6 = true;
                                                                    logger.debug("KeyManagerFactory not supported.");
                                                                    valueOf.release();
                                                                    z5 = z6;
                                                                    SSL.freeSSL(newSSL);
                                                                    if (j != 0) {
                                                                    }
                                                                    if (j4 != 0) {
                                                                    }
                                                                    if (j2 != 0) {
                                                                    }
                                                                    if (j3 != 0) {
                                                                    }
                                                                    SSLContext.free(make);
                                                                    z2 = z5;
                                                                    AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                                                                    LinkedHashSet linkedHashSet222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                                                                    while (r4.hasNext()) {
                                                                    }
                                                                    SslUtils.addIfSupported(linkedHashSet222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                                                                    SslUtils.addIfSupported(linkedHashSet222, arrayList, SslUtils.TLSV13_CIPHER_SUITES);
                                                                    SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet222);
                                                                    DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                                                                    AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet222);
                                                                    LinkedHashSet linkedHashSet322 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                                                                    linkedHashSet322.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                                                                    linkedHashSet322.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                                                                    AVAILABLE_CIPHER_SUITES = linkedHashSet322;
                                                                    SUPPORTS_KEYMANAGER_FACTORY = z2;
                                                                    USE_KEYMANAGER_FACTORY = z9;
                                                                    LinkedHashSet linkedHashSet422 = new LinkedHashSet(6);
                                                                    linkedHashSet422.add("SSLv2Hello");
                                                                    if (doesSupportProtocol(1, SSL.SSL_OP_NO_SSLv2)) {
                                                                    }
                                                                    if (doesSupportProtocol(2, SSL.SSL_OP_NO_SSLv3)) {
                                                                    }
                                                                    if (doesSupportProtocol(4, SSL.SSL_OP_NO_TLSv1)) {
                                                                    }
                                                                    if (doesSupportProtocol(8, SSL.SSL_OP_NO_TLSv1_1)) {
                                                                    }
                                                                    if (doesSupportProtocol(16, SSL.SSL_OP_NO_TLSv1_2)) {
                                                                    }
                                                                    if (!z) {
                                                                    }
                                                                    TLSV13_SUPPORTED = false;
                                                                    SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet422);
                                                                    SUPPORTS_OCSP = doesSupportOcsp();
                                                                    if (logger.isDebugEnabled()) {
                                                                    }
                                                                } catch (Throwable th8) {
                                                                    th = th8;
                                                                    valueOf.release();
                                                                    throw th;
                                                                }
                                                                SSL.freeSSL(newSSL);
                                                                if (j != 0) {
                                                                }
                                                                if (j4 != 0) {
                                                                }
                                                                if (j2 != 0) {
                                                                }
                                                                if (j3 != 0) {
                                                                }
                                                                SSLContext.free(make);
                                                                z2 = z5;
                                                                AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                                                                LinkedHashSet linkedHashSet2222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                                                                while (r4.hasNext()) {
                                                                }
                                                                SslUtils.addIfSupported(linkedHashSet2222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                                                                SslUtils.addIfSupported(linkedHashSet2222, arrayList, SslUtils.TLSV13_CIPHER_SUITES);
                                                                SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet2222);
                                                                DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                                                                AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet2222);
                                                                LinkedHashSet linkedHashSet3222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                                                                linkedHashSet3222.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                                                                linkedHashSet3222.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                                                                AVAILABLE_CIPHER_SUITES = linkedHashSet3222;
                                                                SUPPORTS_KEYMANAGER_FACTORY = z2;
                                                                USE_KEYMANAGER_FACTORY = z9;
                                                                LinkedHashSet linkedHashSet4222 = new LinkedHashSet(6);
                                                                linkedHashSet4222.add("SSLv2Hello");
                                                                if (doesSupportProtocol(1, SSL.SSL_OP_NO_SSLv2)) {
                                                                }
                                                                if (doesSupportProtocol(2, SSL.SSL_OP_NO_SSLv3)) {
                                                                }
                                                                if (doesSupportProtocol(4, SSL.SSL_OP_NO_TLSv1)) {
                                                                }
                                                                if (doesSupportProtocol(8, SSL.SSL_OP_NO_TLSv1_1)) {
                                                                }
                                                                if (doesSupportProtocol(16, SSL.SSL_OP_NO_TLSv1_2)) {
                                                                }
                                                                if (!z) {
                                                                }
                                                                TLSV13_SUPPORTED = false;
                                                                SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet4222);
                                                                SUPPORTS_OCSP = doesSupportOcsp();
                                                                if (logger.isDebugEnabled()) {
                                                                }
                                                            }
                                                        }
                                                    }
                                                } catch (Throwable unused6) {
                                                    z7 = false;
                                                }
                                            } catch (Error unused7) {
                                                z6 = false;
                                                z9 = false;
                                            } catch (Throwable th9) {
                                                th = th9;
                                            }
                                        } catch (Error unused8) {
                                            j4 = bio;
                                            z6 = false;
                                            z9 = false;
                                            j3 = 0;
                                        } catch (Throwable th10) {
                                            th = th10;
                                        }
                                    } catch (Error unused9) {
                                        z4 = false;
                                        z9 = false;
                                        j3 = 0;
                                        j4 = j3;
                                        z6 = z4;
                                        logger.debug("KeyManagerFactory not supported.");
                                        valueOf.release();
                                        z5 = z6;
                                        SSL.freeSSL(newSSL);
                                        if (j != 0) {
                                        }
                                        if (j4 != 0) {
                                        }
                                        if (j2 != 0) {
                                        }
                                        if (j3 != 0) {
                                        }
                                        SSLContext.free(make);
                                        z2 = z5;
                                        AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                                        LinkedHashSet linkedHashSet22222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                                        while (r4.hasNext()) {
                                        }
                                        SslUtils.addIfSupported(linkedHashSet22222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                                        SslUtils.addIfSupported(linkedHashSet22222, arrayList, SslUtils.TLSV13_CIPHER_SUITES);
                                        SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet22222);
                                        DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                                        AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet22222);
                                        LinkedHashSet linkedHashSet32222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                                        linkedHashSet32222.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                                        linkedHashSet32222.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                                        AVAILABLE_CIPHER_SUITES = linkedHashSet32222;
                                        SUPPORTS_KEYMANAGER_FACTORY = z2;
                                        USE_KEYMANAGER_FACTORY = z9;
                                        LinkedHashSet linkedHashSet42222 = new LinkedHashSet(6);
                                        linkedHashSet42222.add("SSLv2Hello");
                                        if (doesSupportProtocol(1, SSL.SSL_OP_NO_SSLv2)) {
                                        }
                                        if (doesSupportProtocol(2, SSL.SSL_OP_NO_SSLv3)) {
                                        }
                                        if (doesSupportProtocol(4, SSL.SSL_OP_NO_TLSv1)) {
                                        }
                                        if (doesSupportProtocol(8, SSL.SSL_OP_NO_TLSv1_1)) {
                                        }
                                        if (doesSupportProtocol(16, SSL.SSL_OP_NO_TLSv1_2)) {
                                        }
                                        if (!z) {
                                        }
                                        TLSV13_SUPPORTED = false;
                                        SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet42222);
                                        SUPPORTS_OCSP = doesSupportOcsp();
                                        if (logger.isDebugEnabled()) {
                                        }
                                    } catch (Throwable th11) {
                                        th = th11;
                                        j6 = 0;
                                        valueOf.release();
                                        throw th;
                                    }
                                } catch (Error unused10) {
                                    z3 = false;
                                    z9 = false;
                                    j = 0;
                                    j2 = 0;
                                } catch (Throwable th12) {
                                    th = th12;
                                    j5 = 0;
                                }
                            } catch (Throwable th13) {
                                th = th13;
                            }
                        } catch (Throwable th14) {
                            th = th14;
                            j = 0;
                            j2 = 0;
                            j3 = 0;
                            j4 = 0;
                        }
                        try {
                            valueOf.release();
                            z9 = z7;
                            z5 = true;
                            SSL.freeSSL(newSSL);
                            if (j != 0) {
                                SSL.freeBIO(j);
                            }
                            if (j4 != 0) {
                                SSL.freeBIO(j4);
                            }
                            if (j2 != 0) {
                                SSL.freeX509Chain(j2);
                            }
                            if (j3 != 0) {
                                SSL.freePrivateKey(j3);
                            }
                            SSLContext.free(make);
                            z2 = z5;
                        } catch (Throwable th15) {
                            th = th15;
                            SSL.freeSSL(newSSL);
                            if (j != 0) {
                                SSL.freeBIO(j);
                            }
                            if (j4 != 0) {
                                SSL.freeBIO(j4);
                            }
                            if (j2 != 0) {
                                SSL.freeX509Chain(j2);
                            }
                            if (j3 != 0) {
                                SSL.freePrivateKey(j3);
                            }
                            throw th;
                        }
                    } catch (Throwable th16) {
                        th = th16;
                        SSLContext.free(make);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    str = null;
                    z9 = false;
                    z = false;
                    logger.warn("Failed to get the list of available OpenSSL cipher suites.", (Throwable) e);
                    z2 = str;
                    AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                    LinkedHashSet linkedHashSet222222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                    while (r4.hasNext()) {
                    }
                    SslUtils.addIfSupported(linkedHashSet222222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                    SslUtils.addIfSupported(linkedHashSet222222, arrayList, SslUtils.TLSV13_CIPHER_SUITES);
                    SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet222222);
                    DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                    AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet222222);
                    LinkedHashSet linkedHashSet322222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                    linkedHashSet322222.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                    linkedHashSet322222.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                    AVAILABLE_CIPHER_SUITES = linkedHashSet322222;
                    SUPPORTS_KEYMANAGER_FACTORY = z2;
                    USE_KEYMANAGER_FACTORY = z9;
                    LinkedHashSet linkedHashSet422222 = new LinkedHashSet(6);
                    linkedHashSet422222.add("SSLv2Hello");
                    if (doesSupportProtocol(1, SSL.SSL_OP_NO_SSLv2)) {
                    }
                    if (doesSupportProtocol(2, SSL.SSL_OP_NO_SSLv3)) {
                    }
                    if (doesSupportProtocol(4, SSL.SSL_OP_NO_TLSv1)) {
                    }
                    if (doesSupportProtocol(8, SSL.SSL_OP_NO_TLSv1_1)) {
                    }
                    if (doesSupportProtocol(16, SSL.SSL_OP_NO_TLSv1_2)) {
                    }
                    if (!z) {
                    }
                    TLSV13_SUPPORTED = false;
                    SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet422222);
                    SUPPORTS_OCSP = doesSupportOcsp();
                    if (logger.isDebugEnabled()) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                logger.warn("Failed to get the list of available OpenSSL cipher suites.", (Throwable) e);
                z2 = str;
                AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
                LinkedHashSet linkedHashSet2222222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
                while (r4.hasNext()) {
                }
                SslUtils.addIfSupported(linkedHashSet2222222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
                SslUtils.addIfSupported(linkedHashSet2222222, arrayList, SslUtils.TLSV13_CIPHER_SUITES);
                SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet2222222);
                DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
                AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet2222222);
                LinkedHashSet linkedHashSet3222222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
                linkedHashSet3222222.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
                linkedHashSet3222222.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
                AVAILABLE_CIPHER_SUITES = linkedHashSet3222222;
                SUPPORTS_KEYMANAGER_FACTORY = z2;
                USE_KEYMANAGER_FACTORY = z9;
                LinkedHashSet linkedHashSet4222222 = new LinkedHashSet(6);
                linkedHashSet4222222.add("SSLv2Hello");
                if (doesSupportProtocol(1, SSL.SSL_OP_NO_SSLv2)) {
                }
                if (doesSupportProtocol(2, SSL.SSL_OP_NO_SSLv3)) {
                }
                if (doesSupportProtocol(4, SSL.SSL_OP_NO_TLSv1)) {
                }
                if (doesSupportProtocol(8, SSL.SSL_OP_NO_TLSv1_1)) {
                }
                if (doesSupportProtocol(16, SSL.SSL_OP_NO_TLSv1_2)) {
                }
                if (!z) {
                }
                TLSV13_SUPPORTED = false;
                SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet4222222);
                SUPPORTS_OCSP = doesSupportOcsp();
                if (logger.isDebugEnabled()) {
                }
            }
            AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet);
            LinkedHashSet linkedHashSet22222222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() * 2);
            for (String str4 : AVAILABLE_OPENSSL_CIPHER_SUITES) {
                if (!SslUtils.isTLSv13Cipher(str4)) {
                    linkedHashSet22222222.add(CipherSuiteConverter.toJava(str4, "TLS"));
                    linkedHashSet22222222.add(CipherSuiteConverter.toJava(str4, "SSL"));
                } else {
                    linkedHashSet22222222.add(str4);
                }
            }
            SslUtils.addIfSupported(linkedHashSet22222222, arrayList, SslUtils.DEFAULT_CIPHER_SUITES);
            SslUtils.addIfSupported(linkedHashSet22222222, arrayList, SslUtils.TLSV13_CIPHER_SUITES);
            SslUtils.useFallbackCiphersIfDefaultIsEmpty(arrayList, linkedHashSet22222222);
            DEFAULT_CIPHERS = Collections.unmodifiableList(arrayList);
            AVAILABLE_JAVA_CIPHER_SUITES = Collections.unmodifiableSet(linkedHashSet22222222);
            LinkedHashSet linkedHashSet32222222 = new LinkedHashSet(AVAILABLE_OPENSSL_CIPHER_SUITES.size() + AVAILABLE_JAVA_CIPHER_SUITES.size());
            linkedHashSet32222222.addAll(AVAILABLE_OPENSSL_CIPHER_SUITES);
            linkedHashSet32222222.addAll(AVAILABLE_JAVA_CIPHER_SUITES);
            AVAILABLE_CIPHER_SUITES = linkedHashSet32222222;
            SUPPORTS_KEYMANAGER_FACTORY = z2;
            USE_KEYMANAGER_FACTORY = z9;
            LinkedHashSet linkedHashSet42222222 = new LinkedHashSet(6);
            linkedHashSet42222222.add("SSLv2Hello");
            if (doesSupportProtocol(1, SSL.SSL_OP_NO_SSLv2)) {
                linkedHashSet42222222.add("SSLv2");
            }
            if (doesSupportProtocol(2, SSL.SSL_OP_NO_SSLv3)) {
                linkedHashSet42222222.add("SSLv3");
            }
            if (doesSupportProtocol(4, SSL.SSL_OP_NO_TLSv1)) {
                linkedHashSet42222222.add("TLSv1");
            }
            if (doesSupportProtocol(8, SSL.SSL_OP_NO_TLSv1_1)) {
                linkedHashSet42222222.add("TLSv1.1");
            }
            if (doesSupportProtocol(16, SSL.SSL_OP_NO_TLSv1_2)) {
                linkedHashSet42222222.add("TLSv1.2");
            }
            if (!z && doesSupportProtocol(32, SSL.SSL_OP_NO_TLSv1_3)) {
                linkedHashSet42222222.add("TLSv1.3");
                TLSV13_SUPPORTED = true;
            } else {
                TLSV13_SUPPORTED = false;
            }
            SUPPORTED_PROTOCOLS_SET = Collections.unmodifiableSet(linkedHashSet42222222);
            SUPPORTS_OCSP = doesSupportOcsp();
            if (logger.isDebugEnabled()) {
                return;
            }
            logger.debug("Supported protocols (OpenSSL): {} ", SUPPORTED_PROTOCOLS_SET);
            logger.debug("Default cipher suites (OpenSSL): {}", DEFAULT_CIPHERS);
            return;
        }
        DEFAULT_CIPHERS = Collections.emptyList();
        AVAILABLE_OPENSSL_CIPHER_SUITES = Collections.emptySet();
        AVAILABLE_JAVA_CIPHER_SUITES = Collections.emptySet();
        AVAILABLE_CIPHER_SUITES = Collections.emptySet();
        SUPPORTS_KEYMANAGER_FACTORY = false;
        USE_KEYMANAGER_FACTORY = false;
        SUPPORTED_PROTOCOLS_SET = Collections.emptySet();
        SUPPORTS_OCSP = false;
        TLSV13_SUPPORTED = false;
        IS_BORINGSSL = false;
        EXTRA_SUPPORTED_TLS_1_3_CIPHERS = EmptyArrays.EMPTY_STRINGS;
    }

    static X509Certificate selfSignedCertificate() throws CertificateException {
        return (X509Certificate) SslContext.X509_CERT_FACTORY.generateCertificate(new ByteArrayInputStream(CERT.getBytes(CharsetUtil.US_ASCII)));
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

    private static boolean doesSupportProtocol(int i, int i2) {
        if (i2 == 0) {
            return false;
        }
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

    @Deprecated
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
        String openSsl = CipherSuiteConverter.toOpenSsl(str, IS_BORINGSSL);
        if (openSsl != null) {
            str = openSsl;
        }
        return AVAILABLE_OPENSSL_CIPHER_SUITES.contains(str);
    }

    public static boolean supportsKeyManagerFactory() {
        return SUPPORTS_KEYMANAGER_FACTORY;
    }

    @Deprecated
    public static boolean supportsHostnameValidation() {
        return isAvailable();
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
        LinkedHashSet linkedHashSet = new LinkedHashSet(5);
        if ("linux".equalsIgnoreCase(normalizedOs)) {
            Iterator<String> it = PlatformDependent.normalizedLinuxClassifiers().iterator();
            while (it.hasNext()) {
                linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch + "_" + it.next());
            }
            linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch);
            linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch + "_fedora");
        } else {
            linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch);
        }
        linkedHashSet.add("netty_tcnative_" + normalizedArch);
        linkedHashSet.add("netty_tcnative");
        NativeLibraryLoader.loadFirstAvailable(PlatformDependent.getClassLoader(SSLContext.class), (String[]) linkedHashSet.toArray(new String[0]));
    }

    private static boolean initializeTcNative(String str) throws Exception {
        return Library.initialize("provided", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void releaseIfNeeded(ReferenceCounted referenceCounted) {
        if (referenceCounted.refCnt() > 0) {
            ReferenceCountUtil.safeRelease(referenceCounted);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTlsv13Supported() {
        return TLSV13_SUPPORTED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isBoringSSL() {
        return IS_BORINGSSL;
    }
}
