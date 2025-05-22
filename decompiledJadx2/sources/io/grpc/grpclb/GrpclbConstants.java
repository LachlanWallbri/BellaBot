package io.grpc.grpclb;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.Metadata;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GrpclbConstants {
    public static final Metadata.Key<String> TOKEN_METADATA_KEY = Metadata.Key.m3901of("lb-token", Metadata.ASCII_STRING_MARSHALLER);
    static final Attributes.Key<String> TOKEN_ATTRIBUTE_KEY = Attributes.Key.create("lb-token");
    public static final Attributes.Key<List<EquivalentAddressGroup>> ATTR_LB_ADDRS = Attributes.Key.create("io.grpc.grpclb.lbAddrs");
    public static final Attributes.Key<String> ATTR_LB_ADDR_AUTHORITY = Attributes.Key.create("io.grpc.grpclb.lbAddrAuthority");
    public static final Attributes.Key<Boolean> ATTR_LB_PROVIDED_BACKEND = Attributes.Key.create("io.grpc.grpclb.lbProvidedBackend");

    private GrpclbConstants() {
    }
}
