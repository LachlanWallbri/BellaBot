package io.grpc.alts;

import io.grpc.ServerCall;
import io.grpc.alts.internal.AltsInternalContext;
import io.grpc.alts.internal.AltsProtocolNegotiator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsContextUtil {
    private AltsContextUtil() {
    }

    public static AltsContext createFrom(ServerCall<?, ?> serverCall) {
        Object obj = serverCall.getAttributes().get(AltsProtocolNegotiator.AUTH_CONTEXT_KEY);
        if (!(obj instanceof AltsInternalContext)) {
            throw new IllegalArgumentException("No ALTS context information found");
        }
        return new AltsContext((AltsInternalContext) obj);
    }

    public static boolean check(ServerCall<?, ?> serverCall) {
        return serverCall.getAttributes().get(AltsProtocolNegotiator.AUTH_CONTEXT_KEY) instanceof AltsInternalContext;
    }
}
