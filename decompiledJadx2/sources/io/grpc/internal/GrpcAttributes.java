package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.SecurityLevel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GrpcAttributes {
    public static final Attributes.Key<SecurityLevel> ATTR_SECURITY_LEVEL = Attributes.Key.create("io.grpc.internal.GrpcAttributes.securityLevel");
    public static final Attributes.Key<Attributes> ATTR_CLIENT_EAG_ATTRS = Attributes.Key.create("io.grpc.internal.GrpcAttributes.clientEagAttrs");

    private GrpcAttributes() {
    }
}
