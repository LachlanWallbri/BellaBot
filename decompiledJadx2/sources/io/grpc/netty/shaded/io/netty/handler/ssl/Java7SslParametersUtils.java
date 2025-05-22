package io.grpc.netty.shaded.io.netty.handler.ssl;

import java.security.AlgorithmConstraints;
import javax.net.ssl.SSLParameters;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class Java7SslParametersUtils {
    private Java7SslParametersUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setAlgorithmConstraints(SSLParameters sSLParameters, Object obj) {
        sSLParameters.setAlgorithmConstraints((AlgorithmConstraints) obj);
    }
}
