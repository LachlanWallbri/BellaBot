package org.conscrypt;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public final class Java8PlatformUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        setSSLParameters(sSLParameters, sSLParametersImpl);
        String sniHostName = getSniHostName(sSLParameters);
        if (sniHostName != null) {
            abstractConscryptSocket.setHostname(sniHostName);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        setSSLParameters(sSLParameters, sSLParametersImpl);
        String sniHostName = getSniHostName(sSLParameters);
        if (sniHostName != null) {
            conscryptEngine.setHostname(sniHostName);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        getSSLParameters(sSLParameters, sSLParametersImpl);
        if (sSLParametersImpl.getUseSni() && AddressUtils.isValidSniHostname(abstractConscryptSocket.getHostname())) {
            sSLParameters.setServerNames(Collections.singletonList(new SNIHostName(abstractConscryptSocket.getHostname())));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        getSSLParameters(sSLParameters, sSLParametersImpl);
        if (sSLParametersImpl.getUseSni() && AddressUtils.isValidSniHostname(conscryptEngine.getHostname())) {
            sSLParameters.setServerNames(Collections.singletonList(new SNIHostName(conscryptEngine.getHostname())));
        }
    }

    private static String getSniHostName(SSLParameters sSLParameters) {
        List<SNIServerName> serverNames = sSLParameters.getServerNames();
        if (serverNames == null) {
            return null;
        }
        for (SNIServerName sNIServerName : serverNames) {
            if (sNIServerName.getType() == 0) {
                return ((SNIHostName) sNIServerName).getAsciiName();
            }
        }
        return null;
    }

    private static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl) {
        sSLParametersImpl.setEndpointIdentificationAlgorithm(sSLParameters.getEndpointIdentificationAlgorithm());
        sSLParametersImpl.setUseCipherSuitesOrder(sSLParameters.getUseCipherSuitesOrder());
        sSLParametersImpl.setSNIMatchers(sSLParameters.getSNIMatchers());
        sSLParametersImpl.setAlgorithmConstraints(sSLParameters.getAlgorithmConstraints());
    }

    private static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl) {
        sSLParameters.setEndpointIdentificationAlgorithm(sSLParametersImpl.getEndpointIdentificationAlgorithm());
        sSLParameters.setUseCipherSuitesOrder(sSLParametersImpl.getUseCipherSuitesOrder());
        sSLParameters.setSNIMatchers(sSLParametersImpl.getSNIMatchers());
        sSLParameters.setAlgorithmConstraints(sSLParametersImpl.getAlgorithmConstraints());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean serverNamePermitted(SSLParametersImpl sSLParametersImpl, String str) {
        Collection<SNIMatcher> sNIMatchers = sSLParametersImpl.getSNIMatchers();
        if (sNIMatchers == null || sNIMatchers.isEmpty()) {
            return true;
        }
        Iterator<SNIMatcher> it = sNIMatchers.iterator();
        while (it.hasNext()) {
            if (it.next().matches(new SNIHostName(str))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLEngine wrapEngine(ConscryptEngine conscryptEngine) {
        return new Java8EngineWrapper(conscryptEngine);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLEngine unwrapEngine(SSLEngine sSLEngine) {
        return Java8EngineWrapper.getDelegate(sSLEngine);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLSession wrapSSLSession(ExternalSession externalSession) {
        return new Java8ExtendedSSLSession(externalSession);
    }

    private Java8PlatformUtil() {
    }
}
