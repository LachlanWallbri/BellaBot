package com.google.cloud.dialogflow.v2beta1;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class ValidationResultProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n7google/cloud/dialogflow/v2beta1/validation_result.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\"Ü\u0001\n\u000fValidationError\u0012K\n\bseverity\u0018\u0001 \u0001(\u000e29.google.cloud.dialogflow.v2beta1.ValidationError.Severity\u0012\u000f\n\u0007entries\u0018\u0003 \u0003(\t\u0012\u0015\n\rerror_message\u0018\u0004 \u0001(\t\"T\n\bSeverity\u0012\u0018\n\u0014SEVERITY_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004INFO\u0010\u0001\u0012\u000b\n\u0007WARNING\u0010\u0002\u0012\t\n\u0005ERROR\u0010\u0003\u0012\f\n\bCRITICAL\u0010\u0004\"_\n\u0010ValidationResult\u0012K\n\u0011validation_errors\u0018\u0001 \u0003(\u000b20.google.cloud.dialogflow.v2beta1.ValidationErrorB³\u0001\n#com.google.cloud.dialogflow.v2beta1B\u0015ValidationResultProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ValidationError_descriptor */
    static final Descriptors.Descriptor f1868xfb63ac41 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ValidationError_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1869xabc701bf = new GeneratedMessageV3.FieldAccessorTable(f1868xfb63ac41, new String[]{"Severity", "Entries", "ErrorMessage"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ValidationResult_descriptor */
    static final Descriptors.Descriptor f1870x3fe5f9f6 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ValidationResult_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1871xb80eca74 = new GeneratedMessageV3.FieldAccessorTable(f1870x3fe5f9f6, new String[]{"ValidationErrors"});

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
