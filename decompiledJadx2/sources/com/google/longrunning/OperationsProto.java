package com.google.longrunning;

import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.protobuf.AnyProto;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;
import com.google.rpc.StatusProto;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import org.apache.http.HttpHeaders;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class OperationsProto {
    public static final int OPERATION_INFO_FIELD_NUMBER = 1049;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, OperationInfo> operationInfo = GeneratedMessage.newFileScopedGeneratedExtension(OperationInfo.class, OperationInfo.getDefaultInstance());
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n#google/longrunning/operations.proto\u0012\u0012google.longrunning\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u0019google/protobuf/any.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a\u0017google/rpc/status.proto\u001a google/protobuf/descriptor.proto\"¨\u0001\n\tOperation\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012&\n\bmetadata\u0018\u0002 \u0001(\u000b2\u0014.google.protobuf.Any\u0012\f\n\u0004done\u0018\u0003 \u0001(\b\u0012#\n\u0005error\u0018\u0004 \u0001(\u000b2\u0012.google.rpc.StatusH\u0000\u0012(\n\bresponse\u0018\u0005 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000B\b\n\u0006result\"#\n\u0013GetOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\\\n\u0015ListOperationsRequest\u0012\f\n\u0004name\u0018\u0004 \u0001(\t\u0012\u000e\n\u0006filter\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"d\n\u0016ListOperationsResponse\u00121\n\noperations\u0018\u0001 \u0003(\u000b2\u001d.google.longrunning.Operation\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"&\n\u0016CancelOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"&\n\u0016DeleteOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"P\n\u0014WaitOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012*\n\u0007timeout\u0018\u0002 \u0001(\u000b2\u0019.google.protobuf.Duration\"=\n\rOperationInfo\u0012\u0015\n\rresponse_type\u0018\u0001 \u0001(\t\u0012\u0015\n\rmetadata_type\u0018\u0002 \u0001(\t2ª\u0005\n\nOperations\u0012\u0094\u0001\n\u000eListOperations\u0012).google.longrunning.ListOperationsRequest\u001a*.google.longrunning.ListOperationsResponse\"+\u0082Óä\u0093\u0002\u0017\u0012\u0015/v1/{name=operations}ÚA\u000bname,filter\u0012\u007f\n\fGetOperation\u0012'.google.longrunning.GetOperationRequest\u001a\u001d.google.longrunning.Operation\"'\u0082Óä\u0093\u0002\u001a\u0012\u0018/v1/{name=operations/**}ÚA\u0004name\u0012~\n\u000fDeleteOperation\u0012*.google.longrunning.DeleteOperationRequest\u001a\u0016.google.protobuf.Empty\"'\u0082Óä\u0093\u0002\u001a*\u0018/v1/{name=operations/**}ÚA\u0004name\u0012\u0088\u0001\n\u000fCancelOperation\u0012*.google.longrunning.CancelOperationRequest\u001a\u0016.google.protobuf.Empty\"1\u0082Óä\u0093\u0002$\"\u001f/v1/{name=operations/**}:cancel:\u0001*ÚA\u0004name\u0012Z\n\rWaitOperation\u0012(.google.longrunning.WaitOperationRequest\u001a\u001d.google.longrunning.Operation\"\u0000\u001a\u001dÊA\u001alongrunning.googleapis.com:Z\n\u000eoperation_info\u0012\u001e.google.protobuf.MethodOptions\u0018\u0099\b \u0001(\u000b2!.google.longrunning.OperationInfoB\u0097\u0001\n\u0016com.google.longrunningB\u000fOperationsProtoP\u0001Z=google.golang.org/genproto/googleapis/longrunning;longrunningø\u0001\u0001ª\u0002\u0012Google.LongRunningÊ\u0002\u0012Google\\LongRunningb\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), AnyProto.getDescriptor(), DurationProto.getDescriptor(), EmptyProto.getDescriptor(), StatusProto.getDescriptor(), DescriptorProtos.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_longrunning_Operation_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_longrunning_Operation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_longrunning_Operation_descriptor, new String[]{"Name", "Metadata", "Done", MoveError.LEVEL_ERROR, "Response", "Result"});

    /* renamed from: internal_static_google_longrunning_GetOperationRequest_descriptor */
    static final Descriptors.Descriptor f2008x1e58d943 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_longrunning_GetOperationRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2009x39c80cc1 = new GeneratedMessageV3.FieldAccessorTable(f2008x1e58d943, new String[]{"Name"});

    /* renamed from: internal_static_google_longrunning_ListOperationsRequest_descriptor */
    static final Descriptors.Descriptor f2010xc8f764bc = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_longrunning_ListOperationsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2011xa3040f3a = new GeneratedMessageV3.FieldAccessorTable(f2010xc8f764bc, new String[]{"Name", "Filter", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_longrunning_ListOperationsResponse_descriptor */
    static final Descriptors.Descriptor f2012x63613830 = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_longrunning_ListOperationsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2013x5aed2eae = new GeneratedMessageV3.FieldAccessorTable(f2012x63613830, new String[]{"Operations", "NextPageToken"});

    /* renamed from: internal_static_google_longrunning_CancelOperationRequest_descriptor */
    static final Descriptors.Descriptor f2004xa2574399 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_longrunning_CancelOperationRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2005x4b9c117 = new GeneratedMessageV3.FieldAccessorTable(f2004xa2574399, new String[]{"Name"});

    /* renamed from: internal_static_google_longrunning_DeleteOperationRequest_descriptor */
    static final Descriptors.Descriptor f2006xee6ef5c8 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_longrunning_DeleteOperationRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2007x178fd446 = new GeneratedMessageV3.FieldAccessorTable(f2006xee6ef5c8, new String[]{"Name"});

    /* renamed from: internal_static_google_longrunning_WaitOperationRequest_descriptor */
    static final Descriptors.Descriptor f2015xa44ddafe = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_longrunning_WaitOperationRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2016x8d29237c = new GeneratedMessageV3.FieldAccessorTable(f2015xa44ddafe, new String[]{"Name", HttpHeaders.TIMEOUT});
    static final Descriptors.Descriptor internal_static_google_longrunning_OperationInfo_descriptor = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_longrunning_OperationInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2014x762618a = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_longrunning_OperationInfo_descriptor, new String[]{"ResponseType", "MetadataType"});

    private OperationsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(operationInfo);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    static {
        operationInfo.internalInit(descriptor.getExtensions().get(0));
        ExtensionRegistry newInstance = ExtensionRegistry.newInstance();
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.defaultHost);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) AnnotationsProto.http);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.methodSignature);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        ClientProto.getDescriptor();
        AnyProto.getDescriptor();
        DurationProto.getDescriptor();
        EmptyProto.getDescriptor();
        StatusProto.getDescriptor();
        DescriptorProtos.getDescriptor();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
