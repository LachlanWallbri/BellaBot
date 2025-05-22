package org.apache.http.impl.client;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.util.Args;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SystemDefaultCredentialsProvider implements CredentialsProvider {
    private static final Map<String, String> SCHEME_MAP = new ConcurrentHashMap();
    private final BasicCredentialsProvider internal = new BasicCredentialsProvider();

    static {
        SCHEME_MAP.put("Basic".toUpperCase(Locale.ROOT), "Basic");
        SCHEME_MAP.put("Digest".toUpperCase(Locale.ROOT), "Digest");
        SCHEME_MAP.put("NTLM".toUpperCase(Locale.ROOT), "NTLM");
        SCHEME_MAP.put("Negotiate".toUpperCase(Locale.ROOT), "SPNEGO");
        SCHEME_MAP.put("Kerberos".toUpperCase(Locale.ROOT), "Kerberos");
    }

    private static String translateScheme(String str) {
        if (str == null) {
            return null;
        }
        String str2 = SCHEME_MAP.get(str);
        return str2 != null ? str2 : str;
    }

    @Override // org.apache.http.client.CredentialsProvider
    public void setCredentials(AuthScope authScope, Credentials credentials) {
        this.internal.setCredentials(authScope, credentials);
    }

    private static PasswordAuthentication getSystemCreds(String str, AuthScope authScope, Authenticator.RequestorType requestorType) {
        return Authenticator.requestPasswordAuthentication(authScope.getHost(), null, authScope.getPort(), str, null, translateScheme(authScope.getScheme()), null, requestorType);
    }

    @Override // org.apache.http.client.CredentialsProvider
    public Credentials getCredentials(AuthScope authScope) {
        String str;
        Args.notNull(authScope, "Auth scope");
        Credentials credentials = this.internal.getCredentials(authScope);
        if (credentials != null) {
            return credentials;
        }
        if (authScope.getHost() != null) {
            HttpHost origin = authScope.getOrigin();
            if (origin != null) {
                str = origin.getSchemeName();
            } else {
                str = authScope.getPort() == 443 ? "https" : HttpHost.DEFAULT_SCHEME_NAME;
            }
            PasswordAuthentication systemCreds = getSystemCreds(str, authScope, Authenticator.RequestorType.SERVER);
            if (systemCreds == null) {
                systemCreds = getSystemCreds(str, authScope, Authenticator.RequestorType.PROXY);
            }
            if (systemCreds == null && (systemCreds = getProxyCredentials(HttpHost.DEFAULT_SCHEME_NAME, authScope)) == null) {
                systemCreds = getProxyCredentials("https", authScope);
            }
            if (systemCreds != null) {
                String property = System.getProperty("http.auth.ntlm.domain");
                if (property != null) {
                    return new NTCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()), null, property);
                }
                return "NTLM".equalsIgnoreCase(authScope.getScheme()) ? new NTCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()), null, null) : new UsernamePasswordCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()));
            }
        }
        return null;
    }

    private static PasswordAuthentication getProxyCredentials(String str, AuthScope authScope) {
        String property = System.getProperty(str + ".proxyHost");
        if (property == null) {
            return null;
        }
        String property2 = System.getProperty(str + ".proxyPort");
        if (property2 == null) {
            return null;
        }
        try {
            if (authScope.match(new AuthScope(property, Integer.parseInt(property2))) >= 0) {
                String property3 = System.getProperty(str + ".proxyUser");
                if (property3 == null) {
                    return null;
                }
                String property4 = System.getProperty(str + ".proxyPassword");
                return new PasswordAuthentication(property3, property4 != null ? property4.toCharArray() : new char[0]);
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    @Override // org.apache.http.client.CredentialsProvider
    public void clear() {
        this.internal.clear();
    }
}
