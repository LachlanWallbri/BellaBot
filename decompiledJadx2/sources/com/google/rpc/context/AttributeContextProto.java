package com.google.rpc.context;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import com.google.protobuf.TimestampProto;
import com.liulishuo.okdownload.core.Util;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.apache.http.HttpHeaders;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class AttributeContextProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n*google/rpc/context/attribute_context.proto\u0012\u0012google.rpc.context\u001a\u0019google/protobuf/any.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001fgoogle/protobuf/timestamp.proto\"\u0083\u0010\n\u0010AttributeContext\u00129\n\u0006origin\u0018\u0007 \u0001(\u000b2).google.rpc.context.AttributeContext.Peer\u00129\n\u0006source\u0018\u0001 \u0001(\u000b2).google.rpc.context.AttributeContext.Peer\u0012>\n\u000bdestination\u0018\u0002 \u0001(\u000b2).google.rpc.context.AttributeContext.Peer\u0012=\n\u0007request\u0018\u0003 \u0001(\u000b2,.google.rpc.context.AttributeContext.Request\u0012?\n\bresponse\u0018\u0004 \u0001(\u000b2-.google.rpc.context.AttributeContext.Response\u0012?\n\bresource\u0018\u0005 \u0001(\u000b2-.google.rpc.context.AttributeContext.Resource\u00125\n\u0003api\u0018\u0006 \u0001(\u000b2(.google.rpc.context.AttributeContext.Api\u0012(\n\nextensions\u0018\b \u0003(\u000b2\u0014.google.protobuf.Any\u001a¾\u0001\n\u0004Peer\u0012\n\n\u0002ip\u0018\u0001 \u0001(\t\u0012\f\n\u0004port\u0018\u0002 \u0001(\u0003\u0012E\n\u0006labels\u0018\u0006 \u0003(\u000b25.google.rpc.context.AttributeContext.Peer.LabelsEntry\u0012\u0011\n\tprincipal\u0018\u0007 \u0001(\t\u0012\u0013\n\u000bregion_code\u0018\b \u0001(\t\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\u001aL\n\u0003Api\u0012\u000f\n\u0007service\u0018\u0001 \u0001(\t\u0012\u0011\n\toperation\u0018\u0002 \u0001(\t\u0012\u0010\n\bprotocol\u0018\u0003 \u0001(\t\u0012\u000f\n\u0007version\u0018\u0004 \u0001(\t\u001a\u007f\n\u0004Auth\u0012\u0011\n\tprincipal\u0018\u0001 \u0001(\t\u0012\u0011\n\taudiences\u0018\u0002 \u0003(\t\u0012\u0011\n\tpresenter\u0018\u0003 \u0001(\t\u0012'\n\u0006claims\u0018\u0004 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012\u0015\n\raccess_levels\u0018\u0005 \u0003(\t\u001aï\u0002\n\u0007Request\u0012\n\n\u0002id\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006method\u0018\u0002 \u0001(\t\u0012J\n\u0007headers\u0018\u0003 \u0003(\u000b29.google.rpc.context.AttributeContext.Request.HeadersEntry\u0012\f\n\u0004path\u0018\u0004 \u0001(\t\u0012\f\n\u0004host\u0018\u0005 \u0001(\t\u0012\u000e\n\u0006scheme\u0018\u0006 \u0001(\t\u0012\r\n\u0005query\u0018\u0007 \u0001(\t\u0012(\n\u0004time\u0018\t \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012\f\n\u0004size\u0018\n \u0001(\u0003\u0012\u0010\n\bprotocol\u0018\u000b \u0001(\t\u0012\u000e\n\u0006reason\u0018\f \u0001(\t\u00127\n\u0004auth\u0018\r \u0001(\u000b2).google.rpc.context.AttributeContext.Auth\u001a.\n\fHeadersEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\u001a\u0081\u0002\n\bResponse\u0012\f\n\u0004code\u0018\u0001 \u0001(\u0003\u0012\f\n\u0004size\u0018\u0002 \u0001(\u0003\u0012K\n\u0007headers\u0018\u0003 \u0003(\u000b2:.google.rpc.context.AttributeContext.Response.HeadersEntry\u0012(\n\u0004time\u0018\u0004 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u00122\n\u000fbackend_latency\u0018\u0005 \u0001(\u000b2\u0019.google.protobuf.Duration\u001a.\n\fHeadersEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\u001a\u0090\u0004\n\bResource\u0012\u000f\n\u0007service\u0018\u0001 \u0001(\t\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012\f\n\u0004type\u0018\u0003 \u0001(\t\u0012I\n\u0006labels\u0018\u0004 \u0003(\u000b29.google.rpc.context.AttributeContext.Resource.LabelsEntry\u0012\u000b\n\u0003uid\u0018\u0005 \u0001(\t\u0012S\n\u000bannotations\u0018\u0006 \u0003(\u000b2>.google.rpc.context.AttributeContext.Resource.AnnotationsEntry\u0012\u0014\n\fdisplay_name\u0018\u0007 \u0001(\t\u0012/\n\u000bcreate_time\u0018\b \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012/\n\u000bupdate_time\u0018\t \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012/\n\u000bdelete_time\u0018\n \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012\f\n\u0004etag\u0018\u000b \u0001(\t\u0012\u0010\n\blocation\u0018\f \u0001(\t\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\u001a2\n\u0010AnnotationsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001B\u008b\u0001\n\u0016com.google.rpc.contextB\u0015AttributeContextProtoP\u0001ZUgoogle.golang.org/genproto/googleapis/rpc/context/attribute_context;attribute_contextø\u0001\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor(), DurationProto.getDescriptor(), StructProto.getDescriptor(), TimestampProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_rpc_context_AttributeContext_descriptor = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2088x12bbe218 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_context_AttributeContext_descriptor, new String[]{"Origin", "Source", HttpHeaders.DESTINATION, "Request", "Response", JsonDocumentFields.RESOURCE, "Api", "Extensions"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Peer_descriptor */
    static final Descriptors.Descriptor f2072xa9059f01 = internal_static_google_rpc_context_AttributeContext_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Peer_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2073xcaa347f = new GeneratedMessageV3.FieldAccessorTable(f2072xa9059f01, new String[]{"Ip", "Port", "Labels", JsonDocumentFields.PRINCIPAL, "RegionCode"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Peer_LabelsEntry_descriptor */
    static final Descriptors.Descriptor f2070xb0dc382d = f2072xa9059f01.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Peer_LabelsEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2071x409ae1ab = new GeneratedMessageV3.FieldAccessorTable(f2070xb0dc382d, new String[]{"Key", "Value"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Api_descriptor */
    static final Descriptors.Descriptor f2066x71c21b7f = internal_static_google_rpc_context_AttributeContext_descriptor.getNestedTypes().get(1);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Api_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2067x78d952fd = new GeneratedMessageV3.FieldAccessorTable(f2066x71c21b7f, new String[]{"Service", "Operation", "Protocol", JsonDocumentFields.VERSION});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Auth_descriptor */
    static final Descriptors.Descriptor f2068x2fc3eafb = internal_static_google_rpc_context_AttributeContext_descriptor.getNestedTypes().get(2);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Auth_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2069xccc1e679 = new GeneratedMessageV3.FieldAccessorTable(f2068x2fc3eafb, new String[]{JsonDocumentFields.PRINCIPAL, "Audiences", "Presenter", "Claims", "AccessLevels"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Request_descriptor */
    static final Descriptors.Descriptor f2076x4d46da6a = internal_static_google_rpc_context_AttributeContext_descriptor.getNestedTypes().get(3);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Request_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2077x7591f6e8 = new GeneratedMessageV3.FieldAccessorTable(f2076x4d46da6a, new String[]{JsonDocumentFields.POLICY_ID, "Method", "Headers", "Path", "Host", "Scheme", "Query", "Time", "Size", "Protocol", "Reason", "Auth"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Request_HeadersEntry_descriptor */
    static final Descriptors.Descriptor f2074x9697edc7 = f2076x4d46da6a.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Request_HeadersEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2075xcefc5d45 = new GeneratedMessageV3.FieldAccessorTable(f2074x9697edc7, new String[]{"Key", "Value"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Response_descriptor */
    static final Descriptors.Descriptor f2086x69007842 = internal_static_google_rpc_context_AttributeContext_descriptor.getNestedTypes().get(4);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Response_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2087xda1c3cc0 = new GeneratedMessageV3.FieldAccessorTable(f2086x69007842, new String[]{AttributeLayout.ATTRIBUTE_CODE, "Size", "Headers", "Time", "BackendLatency"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Response_HeadersEntry_descriptor */
    static final Descriptors.Descriptor f2084xa7478eef = f2086x69007842.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Response_HeadersEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2085xf52c566d = new GeneratedMessageV3.FieldAccessorTable(f2084xa7478eef, new String[]{"Key", "Value"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Resource_descriptor */
    static final Descriptors.Descriptor f2082x41d9d655 = internal_static_google_rpc_context_AttributeContext_descriptor.getNestedTypes().get(5);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Resource_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2083xebcbd7d3 = new GeneratedMessageV3.FieldAccessorTable(f2082x41d9d655, new String[]{"Service", "Name", "Type", "Labels", "Uid", "Annotations", "DisplayName", "CreateTime", "UpdateTime", "DeleteTime", Util.ETAG, "Location"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Resource_LabelsEntry_descriptor */
    static final Descriptors.Descriptor f2080x15141181 = f2082x41d9d655.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Resource_LabelsEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2081x265e26ff = new GeneratedMessageV3.FieldAccessorTable(f2080x15141181, new String[]{"Key", "Value"});

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Resource_AnnotationsEntry_descriptor */
    static final Descriptors.Descriptor f2078xafc40a5a = f2082x41d9d655.getNestedTypes().get(1);

    /* renamed from: internal_static_google_rpc_context_AttributeContext_Resource_AnnotationsEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2079x9b9836d8 = new GeneratedMessageV3.FieldAccessorTable(f2078xafc40a5a, new String[]{"Key", "Value"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private AttributeContextProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AnyProto.getDescriptor();
        DurationProto.getDescriptor();
        StructProto.getDescriptor();
        TimestampProto.getDescriptor();
    }
}
