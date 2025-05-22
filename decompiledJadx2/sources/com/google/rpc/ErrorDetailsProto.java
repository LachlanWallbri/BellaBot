package com.google.rpc;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class ErrorDetailsProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/rpc/error_details.proto\u0012\ngoogle.rpc\u001a\u001egoogle/protobuf/duration.proto\";\n\tRetryInfo\u0012.\n\u000bretry_delay\u0018\u0001 \u0001(\u000b2\u0019.google.protobuf.Duration\"2\n\tDebugInfo\u0012\u0015\n\rstack_entries\u0018\u0001 \u0003(\t\u0012\u000e\n\u0006detail\u0018\u0002 \u0001(\t\"y\n\fQuotaFailure\u00126\n\nviolations\u0018\u0001 \u0003(\u000b2\".google.rpc.QuotaFailure.Violation\u001a1\n\tViolation\u0012\u000f\n\u0007subject\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\"\u0093\u0001\n\tErrorInfo\u0012\u000e\n\u0006reason\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006domain\u0018\u0002 \u0001(\t\u00125\n\bmetadata\u0018\u0003 \u0003(\u000b2#.google.rpc.ErrorInfo.MetadataEntry\u001a/\n\rMetadataEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"\u0095\u0001\n\u0013PreconditionFailure\u0012=\n\nviolations\u0018\u0001 \u0003(\u000b2).google.rpc.PreconditionFailure.Violation\u001a?\n\tViolation\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007subject\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\"\u0083\u0001\n\nBadRequest\u0012?\n\u0010field_violations\u0018\u0001 \u0003(\u000b2%.google.rpc.BadRequest.FieldViolation\u001a4\n\u000eFieldViolation\u0012\r\n\u0005field\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\"7\n\u000bRequestInfo\u0012\u0012\n\nrequest_id\u0018\u0001 \u0001(\t\u0012\u0014\n\fserving_data\u0018\u0002 \u0001(\t\"`\n\fResourceInfo\u0012\u0015\n\rresource_type\u0018\u0001 \u0001(\t\u0012\u0015\n\rresource_name\u0018\u0002 \u0001(\t\u0012\r\n\u0005owner\u0018\u0003 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0004 \u0001(\t\"V\n\u0004Help\u0012$\n\u0005links\u0018\u0001 \u0003(\u000b2\u0015.google.rpc.Help.Link\u001a(\n\u0004Link\u0012\u0013\n\u000bdescription\u0018\u0001 \u0001(\t\u0012\u000b\n\u0003url\u0018\u0002 \u0001(\t\"3\n\u0010LocalizedMessage\u0012\u000e\n\u0006locale\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\tBl\n\u000ecom.google.rpcB\u0011ErrorDetailsProtoP\u0001Z?google.golang.org/genproto/googleapis/rpc/errdetails;errdetails¢\u0002\u0003RPCb\u0006proto3"}, new Descriptors.FileDescriptor[]{DurationProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_rpc_RetryInfo_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_RetryInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_RetryInfo_descriptor, new String[]{"RetryDelay"});
    static final Descriptors.Descriptor internal_static_google_rpc_DebugInfo_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_DebugInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_DebugInfo_descriptor, new String[]{"StackEntries", "Detail"});
    static final Descriptors.Descriptor internal_static_google_rpc_QuotaFailure_descriptor = getDescriptor().getMessageTypes().get(2);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_QuotaFailure_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_QuotaFailure_descriptor, new String[]{"Violations"});
    static final Descriptors.Descriptor internal_static_google_rpc_QuotaFailure_Violation_descriptor = internal_static_google_rpc_QuotaFailure_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_QuotaFailure_Violation_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2065x11d2cec3 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_QuotaFailure_Violation_descriptor, new String[]{"Subject", "Description"});
    static final Descriptors.Descriptor internal_static_google_rpc_ErrorInfo_descriptor = getDescriptor().getMessageTypes().get(3);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_ErrorInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_ErrorInfo_descriptor, new String[]{"Reason", "Domain", "Metadata"});
    static final Descriptors.Descriptor internal_static_google_rpc_ErrorInfo_MetadataEntry_descriptor = internal_static_google_rpc_ErrorInfo_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_ErrorInfo_MetadataEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2061x4dc80343 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_ErrorInfo_MetadataEntry_descriptor, new String[]{"Key", "Value"});
    static final Descriptors.Descriptor internal_static_google_rpc_PreconditionFailure_descriptor = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_rpc_PreconditionFailure_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2064x68e0fceb = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_PreconditionFailure_descriptor, new String[]{"Violations"});

    /* renamed from: internal_static_google_rpc_PreconditionFailure_Violation_descriptor */
    static final Descriptors.Descriptor f2062xcaaf1d67 = internal_static_google_rpc_PreconditionFailure_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_PreconditionFailure_Violation_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2063x3739ece5 = new GeneratedMessageV3.FieldAccessorTable(f2062xcaaf1d67, new String[]{"Type", "Subject", "Description"});
    static final Descriptors.Descriptor internal_static_google_rpc_BadRequest_descriptor = getDescriptor().getMessageTypes().get(5);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_BadRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_BadRequest_descriptor, new String[]{"FieldViolations"});
    static final Descriptors.Descriptor internal_static_google_rpc_BadRequest_FieldViolation_descriptor = internal_static_google_rpc_BadRequest_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2060xfd742e7d = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_BadRequest_FieldViolation_descriptor, new String[]{"Field", "Description"});
    static final Descriptors.Descriptor internal_static_google_rpc_RequestInfo_descriptor = getDescriptor().getMessageTypes().get(6);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_RequestInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_RequestInfo_descriptor, new String[]{"RequestId", "ServingData"});
    static final Descriptors.Descriptor internal_static_google_rpc_ResourceInfo_descriptor = getDescriptor().getMessageTypes().get(7);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_ResourceInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_ResourceInfo_descriptor, new String[]{"ResourceType", "ResourceName", "Owner", "Description"});
    static final Descriptors.Descriptor internal_static_google_rpc_Help_descriptor = getDescriptor().getMessageTypes().get(8);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_Help_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_Help_descriptor, new String[]{"Links"});
    static final Descriptors.Descriptor internal_static_google_rpc_Help_Link_descriptor = internal_static_google_rpc_Help_descriptor.getNestedTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_Help_Link_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_Help_Link_descriptor, new String[]{"Description", "Url"});
    static final Descriptors.Descriptor internal_static_google_rpc_LocalizedMessage_descriptor = getDescriptor().getMessageTypes().get(9);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_LocalizedMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_LocalizedMessage_descriptor, new String[]{"Locale", "Message"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ErrorDetailsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        DurationProto.getDescriptor();
    }
}
