package com.google.api.gax.grpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.core.GaxProperties;
import io.grpc.Channel;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public class GaxGrpcProperties {
    private static final String GAX_GRPC_VERSION = GaxProperties.getLibraryVersion(GaxGrpcProperties.class, "version.gax_grpc");
    private static final String GRPC_VERSION = GaxProperties.getLibraryVersion(Channel.class, "version.io_grpc");
    private static final Pattern DEFAULT_API_CLIENT_HEADER_PATTERN = Pattern.compile("gl-java/.+ gapic/.* gax/.+ grpc/.+");

    public static String getGrpcTokenName() {
        return "grpc";
    }

    private GaxGrpcProperties() {
    }

    public static String getGrpcVersion() {
        return GRPC_VERSION;
    }

    public static String getGaxGrpcVersion() {
        return GAX_GRPC_VERSION;
    }

    public static Pattern getDefaultApiClientHeaderPattern() {
        return DEFAULT_API_CLIENT_HEADER_PATTERN;
    }
}
