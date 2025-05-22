package com.google.cloud.dialogflow.p049v2;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class ValidationResultProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n2google/cloud/dialogflow/v2/validation_result.proto\u0012\u001agoogle.cloud.dialogflow.v2\u001a\u001cgoogle/api/annotations.proto\"×\u0001\n\u000fValidationError\u0012F\n\bseverity\u0018\u0001 \u0001(\u000e24.google.cloud.dialogflow.v2.ValidationError.Severity\u0012\u000f\n\u0007entries\u0018\u0003 \u0003(\t\u0012\u0015\n\rerror_message\u0018\u0004 \u0001(\t\"T\n\bSeverity\u0012\u0018\n\u0014SEVERITY_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004INFO\u0010\u0001\u0012\u000b\n\u0007WARNING\u0010\u0002\u0012\t\n\u0005ERROR\u0010\u0003\u0012\f\n\bCRITICAL\u0010\u0004\"Z\n\u0010ValidationResult\u0012F\n\u0011validation_errors\u0018\u0001 \u0003(\u000b2+.google.cloud.dialogflow.v2.ValidationErrorB¤\u0001\n\u001ecom.google.cloud.dialogflow.v2B\u0015ValidationResultProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001aGoogle.Cloud.Dialogflow.V2b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ValidationError_descriptor */
    static final Descriptors.Descriptor f1568xc1c7c11a = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ValidationError_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1569xd4d92d98 = new GeneratedMessageV3.FieldAccessorTable(f1568xc1c7c11a, new String[]{"Severity", "Entries", "ErrorMessage"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ValidationResult_descriptor */
    static final Descriptors.Descriptor f1570x4604803d = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ValidationResult_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1571xb14219bb = new GeneratedMessageV3.FieldAccessorTable(f1570x4604803d, new String[]{"ValidationErrors"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ValidationResultProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AnnotationsProto.getDescriptor();
    }
}
