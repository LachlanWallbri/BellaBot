package com.google.logging.type;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class HttpRequestProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n&google/logging/type/http_request.proto\u0012\u0013google.logging.type\u001a\u001egoogle/protobuf/duration.proto\u001a\u001cgoogle/api/annotations.proto\"ï\u0002\n\u000bHttpRequest\u0012\u0016\n\u000erequest_method\u0018\u0001 \u0001(\t\u0012\u0013\n\u000brequest_url\u0018\u0002 \u0001(\t\u0012\u0014\n\frequest_size\u0018\u0003 \u0001(\u0003\u0012\u000e\n\u0006status\u0018\u0004 \u0001(\u0005\u0012\u0015\n\rresponse_size\u0018\u0005 \u0001(\u0003\u0012\u0012\n\nuser_agent\u0018\u0006 \u0001(\t\u0012\u0011\n\tremote_ip\u0018\u0007 \u0001(\t\u0012\u0011\n\tserver_ip\u0018\r \u0001(\t\u0012\u000f\n\u0007referer\u0018\b \u0001(\t\u0012*\n\u0007latency\u0018\u000e \u0001(\u000b2\u0019.google.protobuf.Duration\u0012\u0014\n\fcache_lookup\u0018\u000b \u0001(\b\u0012\u0011\n\tcache_hit\u0018\t \u0001(\b\u0012*\n\"cache_validated_with_origin_server\u0018\n \u0001(\b\u0012\u0018\n\u0010cache_fill_bytes\u0018\f \u0001(\u0003\u0012\u0010\n\bprotocol\u0018\u000f \u0001(\tB¾\u0001\n\u0017com.google.logging.typeB\u0010HttpRequestProtoP\u0001Z8google.golang.org/genproto/googleapis/logging/type;ltypeª\u0002\u0019Google.Cloud.Logging.TypeÊ\u0002\u0019Google\\Cloud\\Logging\\Typeê\u0002\u001cGoogle::Cloud::Logging::Typeb\u0006proto3"}, new Descriptors.FileDescriptor[]{DurationProto.getDescriptor(), AnnotationsProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_logging_type_HttpRequest_descriptor = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_logging_type_HttpRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2003x9533af93 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_logging_type_HttpRequest_descriptor, new String[]{"RequestMethod", "RequestUrl", "RequestSize", "Status", "ResponseSize", "UserAgent", "RemoteIp", "ServerIp", "Referer", "Latency", "CacheLookup", "CacheHit", "CacheValidatedWithOriginServer", "CacheFillBytes", "Protocol"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private HttpRequestProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        DurationProto.getDescriptor();
        AnnotationsProto.getDescriptor();
    }
}
