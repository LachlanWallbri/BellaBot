package com.google.cloud.audit;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import com.google.rpc.StatusProto;
import com.google.rpc.context.AttributeContextProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class AuditLogProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\"google/cloud/audit/audit_log.proto\u0012\u0012google.cloud.audit\u001a\u0019google/protobuf/any.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a*google/rpc/context/attribute_context.proto\u001a\u0017google/rpc/status.proto\"ú\u0004\n\bAuditLog\u0012\u0014\n\fservice_name\u0018\u0007 \u0001(\t\u0012\u0013\n\u000bmethod_name\u0018\b \u0001(\t\u0012\u0015\n\rresource_name\u0018\u000b \u0001(\t\u0012?\n\u0011resource_location\u0018\u0014 \u0001(\u000b2$.google.cloud.audit.ResourceLocation\u00128\n\u0017resource_original_state\u0018\u0013 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012\u001a\n\u0012num_response_items\u0018\f \u0001(\u0003\u0012\"\n\u0006status\u0018\u0002 \u0001(\u000b2\u0012.google.rpc.Status\u0012C\n\u0013authentication_info\u0018\u0003 \u0001(\u000b2&.google.cloud.audit.AuthenticationInfo\u0012A\n\u0012authorization_info\u0018\t \u0003(\u000b2%.google.cloud.audit.AuthorizationInfo\u0012=\n\u0010request_metadata\u0018\u0004 \u0001(\u000b2#.google.cloud.audit.RequestMetadata\u0012(\n\u0007request\u0018\u0010 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012)\n\bresponse\u0018\u0011 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012)\n\bmetadata\u0018\u0012 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012*\n\fservice_data\u0018\u000f \u0001(\u000b2\u0014.google.protobuf.Any\"\u0099\u0002\n\u0012AuthenticationInfo\u0012\u0017\n\u000fprincipal_email\u0018\u0001 \u0001(\t\u0012\u001a\n\u0012authority_selector\u0018\u0002 \u0001(\t\u00126\n\u0015third_party_principal\u0018\u0004 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012 \n\u0018service_account_key_name\u0018\u0005 \u0001(\t\u0012Y\n\u001fservice_account_delegation_info\u0018\u0006 \u0003(\u000b20.google.cloud.audit.ServiceAccountDelegationInfo\u0012\u0019\n\u0011principal_subject\u0018\b \u0001(\t\"\u0096\u0001\n\u0011AuthorizationInfo\u0012\u0010\n\bresource\u0018\u0001 \u0001(\t\u0012\u0012\n\npermission\u0018\u0002 \u0001(\t\u0012\u000f\n\u0007granted\u0018\u0003 \u0001(\b\u0012J\n\u0013resource_attributes\u0018\u0005 \u0001(\u000b2-.google.rpc.context.AttributeContext.Resource\"õ\u0001\n\u000fRequestMetadata\u0012\u0011\n\tcaller_ip\u0018\u0001 \u0001(\t\u0012\"\n\u001acaller_supplied_user_agent\u0018\u0002 \u0001(\t\u0012\u0016\n\u000ecaller_network\u0018\u0003 \u0001(\t\u0012H\n\u0012request_attributes\u0018\u0007 \u0001(\u000b2,.google.rpc.context.AttributeContext.Request\u0012I\n\u0016destination_attributes\u0018\b \u0001(\u000b2).google.rpc.context.AttributeContext.Peer\"I\n\u0010ResourceLocation\u0012\u0019\n\u0011current_locations\u0018\u0001 \u0003(\t\u0012\u001a\n\u0012original_locations\u0018\u0002 \u0003(\t\"¨\u0003\n\u001cServiceAccountDelegationInfo\u0012e\n\u0015first_party_principal\u0018\u0001 \u0001(\u000b2D.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalH\u0000\u0012e\n\u0015third_party_principal\u0018\u0002 \u0001(\u000b2D.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipalH\u0000\u001aa\n\u0013FirstPartyPrincipal\u0012\u0017\n\u000fprincipal_email\u0018\u0001 \u0001(\t\u00121\n\u0010service_metadata\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.Struct\u001aJ\n\u0013ThirdPartyPrincipal\u00123\n\u0012third_party_claims\u0018\u0001 \u0001(\u000b2\u0017.google.protobuf.StructB\u000b\n\tAuthorityBe\n\u0016com.google.cloud.auditB\rAuditLogProtoP\u0001Z7google.golang.org/genproto/googleapis/cloud/audit;auditø\u0001\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor(), StructProto.getDescriptor(), AttributeContextProto.getDescriptor(), StatusProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_cloud_audit_AuditLog_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_cloud_audit_AuditLog_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_audit_AuditLog_descriptor, new String[]{"ServiceName", "MethodName", "ResourceName", "ResourceLocation", "ResourceOriginalState", "NumResponseItems", "Status", "AuthenticationInfo", "AuthorizationInfo", "RequestMetadata", "Request", "Response", "Metadata", "ServiceData"});
    static final Descriptors.Descriptor internal_static_google_cloud_audit_AuthenticationInfo_descriptor = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_audit_AuthenticationInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1350xcb2d6a1 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_audit_AuthenticationInfo_descriptor, new String[]{"PrincipalEmail", "AuthoritySelector", "ThirdPartyPrincipal", "ServiceAccountKeyName", "ServiceAccountDelegationInfo", "PrincipalSubject"});
    static final Descriptors.Descriptor internal_static_google_cloud_audit_AuthorizationInfo_descriptor = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_audit_AuthorizationInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1351x7c640bea = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_audit_AuthorizationInfo_descriptor, new String[]{JsonDocumentFields.RESOURCE, "Permission", "Granted", "ResourceAttributes"});
    static final Descriptors.Descriptor internal_static_google_cloud_audit_RequestMetadata_descriptor = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_audit_RequestMetadata_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1352xfa976db3 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_audit_RequestMetadata_descriptor, new String[]{"CallerIp", "CallerSuppliedUserAgent", "CallerNetwork", "RequestAttributes", "DestinationAttributes"});
    static final Descriptors.Descriptor internal_static_google_cloud_audit_ResourceLocation_descriptor = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_audit_ResourceLocation_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1353x8f9abfa4 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_audit_ResourceLocation_descriptor, new String[]{"CurrentLocations", "OriginalLocations"});

    /* renamed from: internal_static_google_cloud_audit_ServiceAccountDelegationInfo_descriptor */
    static final Descriptors.Descriptor f1358xa03d05db = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_audit_ServiceAccountDelegationInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1359x63622159 = new GeneratedMessageV3.FieldAccessorTable(f1358xa03d05db, new String[]{"FirstPartyPrincipal", "ThirdPartyPrincipal", "Authority"});

    /* renamed from: internal_static_google_cloud_audit_ServiceAccountDelegationInfo_FirstPartyPrincipal_descriptor */
    static final Descriptors.Descriptor f1354xaf8193e2 = f1358xa03d05db.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_audit_ServiceAccountDelegationInfo_FirstPartyPrincipal_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1355xf217b860 = new GeneratedMessageV3.FieldAccessorTable(f1354xaf8193e2, new String[]{"PrincipalEmail", "ServiceMetadata"});

    /* renamed from: internal_static_google_cloud_audit_ServiceAccountDelegationInfo_ThirdPartyPrincipal_descriptor */
    static final Descriptors.Descriptor f1356xc1638d0b = f1358xa03d05db.getNestedTypes().get(1);

    /* renamed from: internal_static_google_cloud_audit_ServiceAccountDelegationInfo_ThirdPartyPrincipal_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1357xe1e67889 = new GeneratedMessageV3.FieldAccessorTable(f1356xc1638d0b, new String[]{"ThirdPartyClaims"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private AuditLogProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AnyProto.getDescriptor();
        StructProto.getDescriptor();
        AttributeContextProto.getDescriptor();
        StatusProto.getDescriptor();
    }
}
