package org.conscrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public final class ClientSessionContext extends AbstractSessionContext {
    private SSLClientSessionCache persistentCache;
    private final Map<HostAndPort, List<NativeSslSession>> sessionsByHostAndPort;

    @Override // org.conscrypt.AbstractSessionContext
    NativeSslSession getSessionFromPersistentCache(byte[] bArr) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientSessionContext() {
        super(10);
        this.sessionsByHostAndPort = new HashMap();
    }

    public void setPersistentCache(SSLClientSessionCache sSLClientSessionCache) {
        this.persistentCache = sSLClientSessionCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized NativeSslSession getCachedSession(String str, int i, SSLParametersImpl sSLParametersImpl) {
        boolean z;
        if (str == null) {
            return null;
        }
        NativeSslSession session = getSession(str, i);
        if (session == null) {
            return null;
        }
        String protocol = session.getProtocol();
        String[] strArr = sSLParametersImpl.enabledProtocols;
        int length = strArr.length;
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            }
            if (protocol.equals(strArr[i2])) {
                z = true;
                break;
            }
            i2++;
        }
        if (!z) {
            return null;
        }
        String cipherSuite = session.getCipherSuite();
        String[] enabledCipherSuites = sSLParametersImpl.getEnabledCipherSuites();
        int length2 = enabledCipherSuites.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length2) {
                break;
            }
            if (cipherSuite.equals(enabledCipherSuites[i3])) {
                z2 = true;
                break;
            }
            i3++;
        }
        if (!z2) {
            return null;
        }
        if (session.isSingleUse()) {
            removeSession(session);
        }
        return session;
    }

    int size() {
        int i;
        synchronized (this.sessionsByHostAndPort) {
            Iterator<List<NativeSslSession>> it = this.sessionsByHostAndPort.values().iterator();
            i = 0;
            while (it.hasNext()) {
                i += it.next().size();
            }
        }
        return i;
    }

    private NativeSslSession getSession(String str, int i) {
        NativeSslSession nativeSslSession;
        byte[] sessionData;
        NativeSslSession newInstance;
        if (str == null) {
            return null;
        }
        HostAndPort hostAndPort = new HostAndPort(str, i);
        synchronized (this.sessionsByHostAndPort) {
            List<NativeSslSession> list = this.sessionsByHostAndPort.get(hostAndPort);
            nativeSslSession = (list == null || list.size() <= 0) ? null : list.get(0);
        }
        if (nativeSslSession != null && nativeSslSession.isValid()) {
            return nativeSslSession;
        }
        SSLClientSessionCache sSLClientSessionCache = this.persistentCache;
        if (sSLClientSessionCache == null || (sessionData = sSLClientSessionCache.getSessionData(str, i)) == null || (newInstance = NativeSslSession.newInstance(this, sessionData, str, i)) == null || !newInstance.isValid()) {
            return null;
        }
        putSession(hostAndPort, newInstance);
        return newInstance;
    }

    private void putSession(HostAndPort hostAndPort, NativeSslSession nativeSslSession) {
        synchronized (this.sessionsByHostAndPort) {
            List<NativeSslSession> list = this.sessionsByHostAndPort.get(hostAndPort);
            if (list == null) {
                list = new ArrayList<>();
                this.sessionsByHostAndPort.put(hostAndPort, list);
            }
            if (list.size() > 0 && list.get(0).isSingleUse() != nativeSslSession.isSingleUse()) {
                while (!list.isEmpty()) {
                    removeSession(list.get(0));
                }
                this.sessionsByHostAndPort.put(hostAndPort, list);
            }
            list.add(nativeSslSession);
        }
    }

    private void removeSession(HostAndPort hostAndPort, NativeSslSession nativeSslSession) {
        synchronized (this.sessionsByHostAndPort) {
            List<NativeSslSession> list = this.sessionsByHostAndPort.get(hostAndPort);
            if (list != null) {
                list.remove(nativeSslSession);
                if (list.isEmpty()) {
                    this.sessionsByHostAndPort.remove(hostAndPort);
                }
            }
        }
    }

    @Override // org.conscrypt.AbstractSessionContext
    void onBeforeAddSession(NativeSslSession nativeSslSession) {
        byte[] bytes;
        String peerHost = nativeSslSession.getPeerHost();
        int peerPort = nativeSslSession.getPeerPort();
        if (peerHost == null) {
            return;
        }
        putSession(new HostAndPort(peerHost, peerPort), nativeSslSession);
        if (this.persistentCache == null || nativeSslSession.isSingleUse() || (bytes = nativeSslSession.toBytes()) == null) {
            return;
        }
        this.persistentCache.putSessionData(nativeSslSession.toSSLSession(), bytes);
    }

    @Override // org.conscrypt.AbstractSessionContext
    void onBeforeRemoveSession(NativeSslSession nativeSslSession) {
        String peerHost = nativeSslSession.getPeerHost();
        if (peerHost == null) {
            return;
        }
        removeSession(new HostAndPort(peerHost, nativeSslSession.getPeerPort()), nativeSslSession);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static final class HostAndPort {
        final String host;
        final int port;

        HostAndPort(String str, int i) {
            this.host = str;
            this.port = i;
        }

        public int hashCode() {
            return (this.host.hashCode() * 31) + this.port;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof HostAndPort)) {
                return false;
            }
            HostAndPort hostAndPort = (HostAndPort) obj;
            return this.host.equals(hostAndPort.host) && this.port == hostAndPort.port;
        }
    }
}
