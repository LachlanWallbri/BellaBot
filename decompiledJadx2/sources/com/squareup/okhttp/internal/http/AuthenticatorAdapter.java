package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Challenge;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AuthenticatorAdapter implements Authenticator {
    public static final Authenticator INSTANCE = new AuthenticatorAdapter();

    @Override // com.squareup.okhttp.Authenticator
    public Request authenticate(Proxy proxy, Response response) throws IOException {
        PasswordAuthentication requestPasswordAuthentication;
        List<Challenge> challenges = response.challenges();
        Request request = response.request();
        HttpUrl httpUrl = request.httpUrl();
        int size = challenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.getScheme()) && (requestPasswordAuthentication = java.net.Authenticator.requestPasswordAuthentication(httpUrl.host(), getConnectToInetAddress(proxy, httpUrl), httpUrl.port(), httpUrl.scheme(), challenge.getRealm(), challenge.getScheme(), httpUrl.url(), Authenticator.RequestorType.SERVER)) != null) {
                return request.newBuilder().header("Authorization", Credentials.basic(requestPasswordAuthentication.getUserName(), new String(requestPasswordAuthentication.getPassword()))).build();
            }
        }
        return null;
    }

    @Override // com.squareup.okhttp.Authenticator
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        List<Challenge> challenges = response.challenges();
        Request request = response.request();
        HttpUrl httpUrl = request.httpUrl();
        int size = challenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.getScheme())) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();
                PasswordAuthentication requestPasswordAuthentication = java.net.Authenticator.requestPasswordAuthentication(inetSocketAddress.getHostName(), getConnectToInetAddress(proxy, httpUrl), inetSocketAddress.getPort(), httpUrl.scheme(), challenge.getRealm(), challenge.getScheme(), httpUrl.url(), Authenticator.RequestorType.PROXY);
                if (requestPasswordAuthentication != null) {
                    return request.newBuilder().header("Proxy-Authorization", Credentials.basic(requestPasswordAuthentication.getUserName(), new String(requestPasswordAuthentication.getPassword()))).build();
                }
            }
        }
        return null;
    }

    private InetAddress getConnectToInetAddress(Proxy proxy, HttpUrl httpUrl) throws IOException {
        return (proxy == null || proxy.type() == Proxy.Type.DIRECT) ? InetAddress.getByName(httpUrl.host()) : ((InetSocketAddress) proxy.address()).getAddress();
    }
}
