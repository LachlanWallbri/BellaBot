package org.apache.http.impl.auth;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.KerberosCredentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public abstract class GGSSchemeBase extends AuthSchemeBase {
    private final Base64 base64codec;
    private final Log log;
    private State state;
    private final boolean stripPort;
    private byte[] token;
    private final boolean useCanonicalHostname;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum State {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        TOKEN_GENERATED,
        FAILED
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public byte[] generateToken(byte[] bArr, String str) throws GSSException {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GGSSchemeBase(boolean z, boolean z2) {
        this.log = LogFactory.getLog(getClass());
        this.base64codec = new Base64(0);
        this.stripPort = z;
        this.useCanonicalHostname = z2;
        this.state = State.UNINITIATED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GGSSchemeBase(boolean z) {
        this(z, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GGSSchemeBase() {
        this(true, true);
    }

    protected GSSManager getManager() {
        return GSSManager.getInstance();
    }

    protected byte[] generateGSSToken(byte[] bArr, Oid oid, String str) throws GSSException {
        return generateGSSToken(bArr, oid, str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] generateGSSToken(byte[] bArr, Oid oid, String str, Credentials credentials) throws GSSException {
        GSSManager manager = getManager();
        GSSContext createGSSContext = createGSSContext(manager, oid, manager.createName("HTTP@" + str, GSSName.NT_HOSTBASED_SERVICE), credentials instanceof KerberosCredentials ? ((KerberosCredentials) credentials).getGSSCredential() : null);
        return bArr != null ? createGSSContext.initSecContext(bArr, 0, bArr.length) : createGSSContext.initSecContext(new byte[0], 0, 0);
    }

    GSSContext createGSSContext(GSSManager gSSManager, Oid oid, GSSName gSSName, GSSCredential gSSCredential) throws GSSException {
        GSSContext createContext = gSSManager.createContext(gSSName.canonicalize(oid), oid, gSSCredential, 0);
        createContext.requestMutualAuth(true);
        return createContext;
    }

    protected byte[] generateToken(byte[] bArr, String str, Credentials credentials) throws GSSException {
        return generateToken(bArr, str);
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isComplete() {
        return this.state == State.TOKEN_GENERATED || this.state == State.FAILED;
    }

    @Override // org.apache.http.auth.AuthScheme
    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        return authenticate(credentials, httpRequest, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* renamed from: org.apache.http.impl.auth.GGSSchemeBase$1 */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class C81471 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.UNINITIATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.CHALLENGE_RECEIVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.TOKEN_GENERATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // org.apache.http.impl.auth.AuthSchemeBase, org.apache.http.auth.ContextAwareAuthScheme
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) throws AuthenticationException {
        HttpHost targetHost;
        Args.notNull(httpRequest, "HTTP request");
        int i = C81471.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[this.state.ordinal()];
        if (i == 1) {
            throw new AuthenticationException(getSchemeName() + " authentication has not been initiated");
        }
        if (i == 2) {
            throw new AuthenticationException(getSchemeName() + " authentication has failed");
        }
        if (i == 3) {
            try {
                HttpRoute httpRoute = (HttpRoute) httpContext.getAttribute("http.route");
                if (httpRoute == null) {
                    throw new AuthenticationException("Connection route is not available");
                }
                if (isProxy()) {
                    targetHost = httpRoute.getProxyHost();
                    if (targetHost == null) {
                        targetHost = httpRoute.getTargetHost();
                    }
                } else {
                    targetHost = httpRoute.getTargetHost();
                }
                String hostName = targetHost.getHostName();
                if (this.useCanonicalHostname) {
                    try {
                        hostName = resolveCanonicalHostname(hostName);
                    } catch (UnknownHostException unused) {
                    }
                }
                if (!this.stripPort) {
                    hostName = hostName + ":" + targetHost.getPort();
                }
                if (this.log.isDebugEnabled()) {
                    this.log.debug("init " + hostName);
                }
                this.token = generateToken(this.token, hostName, credentials);
                this.state = State.TOKEN_GENERATED;
            } catch (GSSException e) {
                this.state = State.FAILED;
                if (e.getMajor() == 9 || e.getMajor() == 8) {
                    throw new InvalidCredentialsException(e.getMessage(), e);
                }
                if (e.getMajor() == 13) {
                    throw new InvalidCredentialsException(e.getMessage(), e);
                }
                if (e.getMajor() == 10 || e.getMajor() == 19 || e.getMajor() == 20) {
                    throw new AuthenticationException(e.getMessage(), e);
                }
                throw new AuthenticationException(e.getMessage());
            }
        } else if (i != 4) {
            throw new IllegalStateException("Illegal state: " + this.state);
        }
        String str = new String(this.base64codec.encode(this.token));
        if (this.log.isDebugEnabled()) {
            this.log.debug("Sending response '" + str + "' back to the auth server");
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
        if (isProxy()) {
            charArrayBuffer.append("Proxy-Authorization");
        } else {
            charArrayBuffer.append("Authorization");
        }
        charArrayBuffer.append(": Negotiate ");
        charArrayBuffer.append(str);
        return new BufferedHeader(charArrayBuffer);
    }

    @Override // org.apache.http.impl.auth.AuthSchemeBase
    protected void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException {
        String substringTrimmed = charArrayBuffer.substringTrimmed(i, i2);
        if (this.log.isDebugEnabled()) {
            this.log.debug("Received challenge '" + substringTrimmed + "' from the auth server");
        }
        if (this.state == State.UNINITIATED) {
            this.token = Base64.decodeBase64(substringTrimmed.getBytes());
            this.state = State.CHALLENGE_RECEIVED;
        } else {
            this.log.debug("Authentication already attempted");
            this.state = State.FAILED;
        }
    }

    private String resolveCanonicalHostname(String str) throws UnknownHostException {
        InetAddress byName = InetAddress.getByName(str);
        String canonicalHostName = byName.getCanonicalHostName();
        return byName.getHostAddress().contentEquals(canonicalHostName) ? str : canonicalHostName;
    }
}
