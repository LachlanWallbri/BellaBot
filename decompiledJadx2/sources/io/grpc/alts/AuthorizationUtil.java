package io.grpc.alts;

import io.grpc.ServerCall;
import io.grpc.Status;
import io.grpc.alts.internal.AltsInternalContext;
import io.grpc.alts.internal.AltsProtocolNegotiator;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AuthorizationUtil {
    private AuthorizationUtil() {
    }

    public static Status clientAuthorizationCheck(ServerCall<?, ?> serverCall, Collection<String> collection) {
        AltsInternalContext altsInternalContext = (AltsInternalContext) serverCall.getAttributes().get(AltsProtocolNegotiator.AUTH_CONTEXT_KEY);
        if (altsInternalContext == null) {
            return Status.PERMISSION_DENIED.withDescription("Peer ALTS AuthContext not found");
        }
        if (collection.contains(altsInternalContext.getPeerServiceAccount())) {
            return Status.f8305OK;
        }
        return Status.PERMISSION_DENIED.withDescription("Client " + altsInternalContext.getPeerServiceAccount() + " is not authorized");
    }
}
