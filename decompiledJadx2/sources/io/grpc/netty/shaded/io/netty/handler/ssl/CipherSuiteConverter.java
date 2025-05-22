package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.Soundex;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CipherSuiteConverter {
    private static final Map<String, String> j2oTls13;
    private static final Map<String, Map<String, String>> o2jTls13;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CipherSuiteConverter.class);
    private static final Pattern JAVA_CIPHERSUITE_PATTERN = Pattern.compile("^(?:TLS|SSL)_((?:(?!_WITH_).)+)_WITH_(.*)_(.*)$");
    private static final Pattern OPENSSL_CIPHERSUITE_PATTERN = Pattern.compile("^(?:((?:(?:EXP-)?(?:(?:DHE|EDH|ECDH|ECDHE|SRP|RSA)-(?:DSS|RSA|ECDSA|PSK)|(?:ADH|AECDH|KRB5|PSK|SRP)))|EXP)-)?(.*)-(.*)$");
    private static final Pattern JAVA_AES_CBC_PATTERN = Pattern.compile("^(AES)_([0-9]+)_CBC$");
    private static final Pattern JAVA_AES_PATTERN = Pattern.compile("^(AES)_([0-9]+)_(.*)$");
    private static final Pattern OPENSSL_AES_CBC_PATTERN = Pattern.compile("^(AES)([0-9]+)$");
    private static final Pattern OPENSSL_AES_PATTERN = Pattern.compile("^(AES)([0-9]+)-(.*)$");
    private static final ConcurrentMap<String, String> j2o = PlatformDependent.newConcurrentHashMap();
    private static final ConcurrentMap<String, Map<String, String>> o2j = PlatformDependent.newConcurrentHashMap();

    private static String toJavaHmacAlgo(String str) {
        return str;
    }

    private static String toOpenSslHmacAlgo(String str) {
        return str;
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("TLS_AES_128_GCM_SHA256", "AEAD-AES128-GCM-SHA256");
        hashMap.put("TLS_AES_256_GCM_SHA384", "AEAD-AES256-GCM-SHA384");
        hashMap.put("TLS_CHACHA20_POLY1305_SHA256", "AEAD-CHACHA20-POLY1305-SHA256");
        j2oTls13 = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("TLS_AES_128_GCM_SHA256", Collections.singletonMap("TLS", "TLS_AES_128_GCM_SHA256"));
        hashMap2.put("TLS_AES_256_GCM_SHA384", Collections.singletonMap("TLS", "TLS_AES_256_GCM_SHA384"));
        hashMap2.put("TLS_CHACHA20_POLY1305_SHA256", Collections.singletonMap("TLS", "TLS_CHACHA20_POLY1305_SHA256"));
        hashMap2.put("AEAD-AES128-GCM-SHA256", Collections.singletonMap("TLS", "TLS_AES_128_GCM_SHA256"));
        hashMap2.put("AEAD-AES256-GCM-SHA384", Collections.singletonMap("TLS", "TLS_AES_256_GCM_SHA384"));
        hashMap2.put("AEAD-CHACHA20-POLY1305-SHA256", Collections.singletonMap("TLS", "TLS_CHACHA20_POLY1305_SHA256"));
        o2jTls13 = Collections.unmodifiableMap(hashMap2);
    }

    static void clearCache() {
        j2o.clear();
        o2j.clear();
    }

    static boolean isJ2OCached(String str, String str2) {
        return str2.equals(j2o.get(str));
    }

    static boolean isO2JCached(String str, String str2, String str3) {
        Map<String, String> map = o2j.get(str);
        if (map == null) {
            return false;
        }
        return str3.equals(map.get(str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toOpenSsl(String str, boolean z) {
        String str2 = j2o.get(str);
        return str2 != null ? str2 : cacheFromJava(str, z);
    }

    private static String cacheFromJava(String str, boolean z) {
        String str2 = j2oTls13.get(str);
        if (str2 != null) {
            return z ? str2 : str;
        }
        String openSslUncached = toOpenSslUncached(str, z);
        if (openSslUncached == null) {
            return null;
        }
        j2o.putIfAbsent(str, openSslUncached);
        String substring = str.substring(4);
        HashMap hashMap = new HashMap(4);
        hashMap.put("", substring);
        hashMap.put("SSL", "SSL_" + substring);
        hashMap.put("TLS", "TLS_" + substring);
        o2j.put(openSslUncached, hashMap);
        logger.debug("Cipher suite mapping: {} => {}", str, openSslUncached);
        return openSslUncached;
    }

    static String toOpenSslUncached(String str, boolean z) {
        String str2 = j2oTls13.get(str);
        if (str2 != null) {
            return z ? str2 : str;
        }
        Matcher matcher = JAVA_CIPHERSUITE_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        String openSslHandshakeAlgo = toOpenSslHandshakeAlgo(matcher.group(1));
        String openSslBulkCipher = toOpenSslBulkCipher(matcher.group(2));
        String openSslHmacAlgo = toOpenSslHmacAlgo(matcher.group(3));
        if (openSslHandshakeAlgo.isEmpty()) {
            return openSslBulkCipher + Soundex.SILENT_MARKER + openSslHmacAlgo;
        }
        if (openSslBulkCipher.contains("CHACHA20")) {
            return openSslHandshakeAlgo + Soundex.SILENT_MARKER + openSslBulkCipher;
        }
        return openSslHandshakeAlgo + Soundex.SILENT_MARKER + openSslBulkCipher + Soundex.SILENT_MARKER + openSslHmacAlgo;
    }

    private static String toOpenSslHandshakeAlgo(String str) {
        boolean endsWith = str.endsWith("_EXPORT");
        if (endsWith) {
            str = str.substring(0, str.length() - 7);
        }
        if ("RSA".equals(str)) {
            str = "";
        } else if (str.endsWith("_anon")) {
            str = 'A' + str.substring(0, str.length() - 5);
        }
        if (endsWith) {
            if (str.isEmpty()) {
                str = "EXP";
            } else {
                str = "EXP-" + str;
            }
        }
        return str.replace('_', Soundex.SILENT_MARKER);
    }

    private static String toOpenSslBulkCipher(String str) {
        if (str.startsWith("AES_")) {
            Matcher matcher = JAVA_AES_CBC_PATTERN.matcher(str);
            if (matcher.matches()) {
                return matcher.replaceFirst("$1$2");
            }
            Matcher matcher2 = JAVA_AES_PATTERN.matcher(str);
            if (matcher2.matches()) {
                return matcher2.replaceFirst("$1$2-$3");
            }
        }
        return "3DES_EDE_CBC".equals(str) ? "DES-CBC3" : ("RC4_128".equals(str) || "RC4_40".equals(str)) ? "RC4" : ("DES40_CBC".equals(str) || "DES_CBC_40".equals(str)) ? "DES-CBC" : "RC2_CBC_40".equals(str) ? "RC2-CBC" : str.replace('_', Soundex.SILENT_MARKER);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toJava(String str, String str2) {
        Map<String, String> map = o2j.get(str);
        if (map == null && (map = cacheFromOpenSsl(str)) == null) {
            return null;
        }
        String str3 = map.get(str2);
        if (str3 != null) {
            return str3;
        }
        String str4 = map.get("");
        if (str4 == null) {
            return null;
        }
        return str2 + '_' + str4;
    }

    private static Map<String, String> cacheFromOpenSsl(String str) {
        Map<String, String> map = o2jTls13.get(str);
        if (map != null) {
            return map;
        }
        String javaUncached0 = toJavaUncached0(str, false);
        if (javaUncached0 == null) {
            return null;
        }
        String str2 = "SSL_" + javaUncached0;
        String str3 = "TLS_" + javaUncached0;
        HashMap hashMap = new HashMap(4);
        hashMap.put("", javaUncached0);
        hashMap.put("SSL", str2);
        hashMap.put("TLS", str3);
        o2j.putIfAbsent(str, hashMap);
        j2o.putIfAbsent(str3, str);
        j2o.putIfAbsent(str2, str);
        logger.debug("Cipher suite mapping: {} => {}", str3, str);
        logger.debug("Cipher suite mapping: {} => {}", str2, str);
        return hashMap;
    }

    static String toJavaUncached(String str) {
        return toJavaUncached0(str, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String toJavaUncached0(String str, boolean z) {
        String javaBulkCipher;
        Map<String, String> map;
        if (z && (map = o2jTls13.get(str)) != null) {
            return map.get("TLS");
        }
        Matcher matcher = OPENSSL_CIPHERSUITE_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        boolean z2 = true;
        String group = matcher.group(1);
        if (group != null) {
            if (group.startsWith("EXP-")) {
                group = group.substring(4);
            } else if (!"EXP".equals(group)) {
                z2 = false;
            }
            String javaHandshakeAlgo = toJavaHandshakeAlgo(group, z2);
            javaBulkCipher = toJavaBulkCipher(matcher.group(2), z2);
            String str2 = javaHandshakeAlgo + "_WITH_" + javaBulkCipher + '_' + toJavaHmacAlgo(matcher.group(3));
            if (javaBulkCipher.contains("CHACHA20")) {
                return str2;
            }
            return str2 + "_SHA256";
        }
        z2 = false;
        group = "";
        String javaHandshakeAlgo2 = toJavaHandshakeAlgo(group, z2);
        javaBulkCipher = toJavaBulkCipher(matcher.group(2), z2);
        String str22 = javaHandshakeAlgo2 + "_WITH_" + javaBulkCipher + '_' + toJavaHmacAlgo(matcher.group(3));
        if (javaBulkCipher.contains("CHACHA20")) {
        }
    }

    private static String toJavaHandshakeAlgo(String str, boolean z) {
        if (str.isEmpty()) {
            str = "RSA";
        } else if ("ADH".equals(str)) {
            str = "DH_anon";
        } else if ("AECDH".equals(str)) {
            str = "ECDH_anon";
        }
        String replace = str.replace(Soundex.SILENT_MARKER, '_');
        if (!z) {
            return replace;
        }
        return replace + "_EXPORT";
    }

    private static String toJavaBulkCipher(String str, boolean z) {
        if (str.startsWith(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM)) {
            Matcher matcher = OPENSSL_AES_CBC_PATTERN.matcher(str);
            if (matcher.matches()) {
                return matcher.replaceFirst("$1_$2_CBC");
            }
            Matcher matcher2 = OPENSSL_AES_PATTERN.matcher(str);
            if (matcher2.matches()) {
                return matcher2.replaceFirst("$1_$2_$3");
            }
        }
        if ("DES-CBC3".equals(str)) {
            return "3DES_EDE_CBC";
        }
        if ("RC4".equals(str)) {
            return z ? "RC4_40" : "RC4_128";
        }
        if ("DES-CBC".equals(str)) {
            return z ? "DES_CBC_40" : "DES_CBC";
        }
        if ("RC2-CBC".equals(str)) {
            return z ? "RC2_CBC_40" : "RC2_CBC";
        }
        return str.replace(Soundex.SILENT_MARKER, '_');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void convertToCipherStrings(Iterable<String> iterable, StringBuilder sb, StringBuilder sb2, boolean z) {
        String next;
        Iterator<String> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            String openSsl = toOpenSsl(next, z);
            if (openSsl == null) {
                openSsl = next;
            }
            if (!OpenSsl.isCipherSuiteAvailable(openSsl)) {
                throw new IllegalArgumentException("unsupported cipher suite: " + next + '(' + openSsl + ')');
            }
            if (SslUtils.isTLSv13Cipher(openSsl) || SslUtils.isTLSv13Cipher(next)) {
                sb2.append(openSsl);
                sb2.append(':');
            } else {
                sb.append(openSsl);
                sb.append(':');
            }
        }
        if (sb.length() == 0 && sb2.length() == 0) {
            throw new IllegalArgumentException("empty cipher suites");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        if (sb2.length() > 0) {
            sb2.setLength(sb2.length() - 1);
        }
    }

    private CipherSuiteConverter() {
    }
}
