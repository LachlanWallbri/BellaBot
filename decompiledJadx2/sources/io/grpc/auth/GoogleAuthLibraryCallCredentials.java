package io.grpc.auth;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auth.Credentials;
import com.google.auth.RequestMetadataCallback;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import io.grpc.CallCredentials;
import io.grpc.CallCredentials2;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.StatusException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GoogleAuthLibraryCallCredentials extends CallCredentials2 {
    final Credentials creds;
    private Metadata lastHeaders;
    private Map<String, List<String>> lastMetadata;
    private final boolean requirePrivacy;
    private static final Logger log = Logger.getLogger(GoogleAuthLibraryCallCredentials.class.getName());
    private static final JwtHelper jwtHelper = createJwtHelperOrNull(GoogleAuthLibraryCallCredentials.class.getClassLoader());
    private static final Class<? extends Credentials> googleCredentialsClass = loadGoogleCredentialsClass();

    @Override // io.grpc.CallCredentials
    public void thisUsesUnstableApi() {
    }

    public GoogleAuthLibraryCallCredentials(Credentials credentials) {
        this(credentials, jwtHelper);
    }

    GoogleAuthLibraryCallCredentials(Credentials credentials, JwtHelper jwtHelper2) {
        Preconditions.checkNotNull(credentials, "creds");
        Class<? extends Credentials> cls = googleCredentialsClass;
        boolean isInstance = cls != null ? cls.isInstance(credentials) : false;
        credentials = jwtHelper2 != null ? jwtHelper2.tryServiceAccountToJwt(credentials) : credentials;
        this.requirePrivacy = isInstance;
        this.creds = credentials;
    }

    @Override // io.grpc.CallCredentials2
    public void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, final CallCredentials2.MetadataApplier metadataApplier) {
        SecurityLevel securityLevel = requestInfo.getSecurityLevel();
        if (this.requirePrivacy && securityLevel != SecurityLevel.PRIVACY_AND_INTEGRITY) {
            metadataApplier.fail(Status.UNAUTHENTICATED.withDescription("Credentials require channel with PRIVACY_AND_INTEGRITY security level. Observed security level: " + securityLevel));
            return;
        }
        try {
            this.creds.getRequestMetadata(serviceUri((String) Preconditions.checkNotNull(requestInfo.getAuthority(), "authority"), requestInfo.getMethodDescriptor()), executor, new RequestMetadataCallback() { // from class: io.grpc.auth.GoogleAuthLibraryCallCredentials.1
                @Override // com.google.auth.RequestMetadataCallback
                public void onSuccess(Map<String, List<String>> map) {
                    Metadata metadata;
                    try {
                        synchronized (GoogleAuthLibraryCallCredentials.this) {
                            if (GoogleAuthLibraryCallCredentials.this.lastMetadata == null || GoogleAuthLibraryCallCredentials.this.lastMetadata != map) {
                                GoogleAuthLibraryCallCredentials.this.lastHeaders = GoogleAuthLibraryCallCredentials.toHeaders(map);
                                GoogleAuthLibraryCallCredentials.this.lastMetadata = map;
                            }
                            metadata = GoogleAuthLibraryCallCredentials.this.lastHeaders;
                        }
                        metadataApplier.apply(metadata);
                    } catch (Throwable th) {
                        metadataApplier.fail(Status.UNAUTHENTICATED.withDescription("Failed to convert credential metadata").withCause(th));
                    }
                }

                @Override // com.google.auth.RequestMetadataCallback
                public void onFailure(Throwable th) {
                    if (th instanceof IOException) {
                        metadataApplier.fail(Status.UNAVAILABLE.withDescription("Credentials failed to obtain metadata").withCause(th));
                    } else {
                        metadataApplier.fail(Status.UNAUTHENTICATED.withDescription("Failed computing credential metadata").withCause(th));
                    }
                }
            });
        } catch (StatusException e) {
            metadataApplier.fail(e.getStatus());
        }
    }

    private static URI serviceUri(String str, MethodDescriptor<?, ?> methodDescriptor) throws StatusException {
        try {
            URI uri = new URI("https", str, "/" + methodDescriptor.getServiceName(), null, null);
            return uri.getPort() == 443 ? removePort(uri) : uri;
        } catch (URISyntaxException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to construct service URI for auth").withCause(e).asException();
        }
    }

    private static URI removePort(URI uri) throws StatusException {
        try {
            return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(), uri.getFragment());
        } catch (URISyntaxException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to construct service URI after removing port").withCause(e).asException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Metadata toHeaders(@Nullable Map<String, List<String>> map) {
        Metadata metadata = new Metadata();
        if (map != null) {
            for (String str : map.keySet()) {
                if (str.endsWith(Metadata.BINARY_HEADER_SUFFIX)) {
                    Metadata.Key m3902of = Metadata.Key.m3902of(str, Metadata.BINARY_BYTE_MARSHALLER);
                    Iterator<String> it = map.get(str).iterator();
                    while (it.hasNext()) {
                        metadata.put(m3902of, BaseEncoding.base64().decode(it.next()));
                    }
                } else {
                    Metadata.Key m3901of = Metadata.Key.m3901of(str, Metadata.ASCII_STRING_MARSHALLER);
                    Iterator<String> it2 = map.get(str).iterator();
                    while (it2.hasNext()) {
                        metadata.put(m3901of, it2.next());
                    }
                }
            }
        }
        return metadata;
    }

    @Nullable
    static JwtHelper createJwtHelperOrNull(ClassLoader classLoader) {
        try {
            try {
                return new JwtHelper(Class.forName("com.google.auth.oauth2.ServiceAccountCredentials", false, classLoader), classLoader);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                log.log(Level.WARNING, "Failed to create JWT helper. This is unexpected", e);
                return null;
            }
        } catch (ClassNotFoundException unused) {
        }
    }

    @Nullable
    private static Class<? extends Credentials> loadGoogleCredentialsClass() {
        try {
            return Class.forName("com.google.auth.oauth2.GoogleCredentials").asSubclass(Credentials.class);
        } catch (ClassNotFoundException e) {
            log.log(Level.FINE, "Failed to load GoogleCredentials", (Throwable) e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class MethodPair {
        private final Method builderSetter;
        private final Method getter;

        private MethodPair(Method method, Method method2) {
            this.getter = method;
            this.builderSetter = method2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void apply(Credentials credentials, Object obj) throws InvocationTargetException, IllegalAccessException {
            this.builderSetter.invoke(obj, this.getter.invoke(credentials, new Object[0]));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class JwtHelper {
        private final Method build;
        private final Method getScopes;
        private final List<MethodPair> methodPairs;
        private final Method newJwtBuilder;
        private final Class<? extends Credentials> serviceAccountClass;

        public JwtHelper(Class<?> cls, ClassLoader classLoader) throws ClassNotFoundException, NoSuchMethodException {
            this.serviceAccountClass = cls.asSubclass(Credentials.class);
            this.getScopes = this.serviceAccountClass.getMethod("getScopes", new Class[0]);
            this.newJwtBuilder = Class.forName("com.google.auth.oauth2.ServiceAccountJwtAccessCredentials", false, classLoader).asSubclass(Credentials.class).getDeclaredMethod("newBuilder", new Class[0]);
            Class<?> returnType = this.newJwtBuilder.getReturnType();
            this.build = returnType.getMethod(JsonPOJOBuilder.DEFAULT_BUILD_METHOD, new Class[0]);
            this.methodPairs = new ArrayList();
            Method method = this.serviceAccountClass.getMethod("getClientId", new Class[0]);
            this.methodPairs.add(new MethodPair(method, returnType.getMethod("setClientId", method.getReturnType())));
            Method method2 = this.serviceAccountClass.getMethod("getClientEmail", new Class[0]);
            this.methodPairs.add(new MethodPair(method2, returnType.getMethod("setClientEmail", method2.getReturnType())));
            Method method3 = this.serviceAccountClass.getMethod("getPrivateKey", new Class[0]);
            this.methodPairs.add(new MethodPair(method3, returnType.getMethod("setPrivateKey", method3.getReturnType())));
            Method method4 = this.serviceAccountClass.getMethod("getPrivateKeyId", new Class[0]);
            this.methodPairs.add(new MethodPair(method4, returnType.getMethod("setPrivateKeyId", method4.getReturnType())));
        }

        public Credentials tryServiceAccountToJwt(Credentials credentials) {
            Credentials credentials2;
            Throwable e;
            if (!this.serviceAccountClass.isInstance(credentials)) {
                return credentials;
            }
            try {
                credentials2 = this.serviceAccountClass.cast(credentials);
                try {
                    if (((Collection) this.getScopes.invoke(credentials2, new Object[0])).size() != 0) {
                        return credentials2;
                    }
                    Object invoke = this.newJwtBuilder.invoke(null, new Object[0]);
                    Iterator<MethodPair> it = this.methodPairs.iterator();
                    while (it.hasNext()) {
                        it.next().apply(credentials2, invoke);
                    }
                    return (Credentials) this.build.invoke(invoke, new Object[0]);
                } catch (IllegalAccessException e2) {
                    e = e2;
                    GoogleAuthLibraryCallCredentials.log.log(Level.WARNING, "Failed converting service account credential to JWT. This is unexpected", e);
                    return credentials2;
                } catch (InvocationTargetException e3) {
                    e = e3;
                    GoogleAuthLibraryCallCredentials.log.log(Level.WARNING, "Failed converting service account credential to JWT. This is unexpected", e);
                    return credentials2;
                }
            } catch (IllegalAccessException | InvocationTargetException e4) {
                credentials2 = credentials;
                e = e4;
            }
        }
    }
}
