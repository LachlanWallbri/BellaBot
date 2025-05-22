package com.google.cloud.dialogflow.p049v2;

import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.api.FieldBehaviorProto;
import com.google.api.ResourceProto;
import com.google.longrunning.OperationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class AgentProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n&google/cloud/dialogflow/v2/agent.proto\u0012\u001agoogle.cloud.dialogflow.v2\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a2google/cloud/dialogflow/v2/validation_result.proto\u001a#google/longrunning/operations.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\"ð\u0006\n\u0005Agent\u0012C\n\u0006parent\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+cloudresourcemanager.googleapis.com/Project\u0012\u0019\n\fdisplay_name\u0018\u0002 \u0001(\tB\u0003àA\u0002\u0012\"\n\u0015default_language_code\u0018\u0003 \u0001(\tB\u0003àA\u0002\u0012%\n\u0018supported_language_codes\u0018\u0004 \u0003(\tB\u0003àA\u0001\u0012\u0016\n\ttime_zone\u0018\u0005 \u0001(\tB\u0003àA\u0002\u0012\u0018\n\u000bdescription\u0018\u0006 \u0001(\tB\u0003àA\u0001\u0012\u0017\n\navatar_uri\u0018\u0007 \u0001(\tB\u0003àA\u0001\u0012\u001b\n\u000eenable_logging\u0018\b \u0001(\bB\u0003àA\u0001\u0012D\n\nmatch_mode\u0018\t \u0001(\u000e2+.google.cloud.dialogflow.v2.Agent.MatchModeB\u0003àA\u0001\u0012%\n\u0018classification_threshold\u0018\n \u0001(\u0002B\u0003àA\u0001\u0012F\n\u000bapi_version\u0018\u000e \u0001(\u000e2,.google.cloud.dialogflow.v2.Agent.ApiVersionB\u0003àA\u0001\u00129\n\u0004tier\u0018\u000f \u0001(\u000e2&.google.cloud.dialogflow.v2.Agent.TierB\u0003àA\u0001\"V\n\tMatchMode\u0012\u001a\n\u0016MATCH_MODE_UNSPECIFIED\u0010\u0000\u0012\u0015\n\u0011MATCH_MODE_HYBRID\u0010\u0001\u0012\u0016\n\u0012MATCH_MODE_ML_ONLY\u0010\u0002\"l\n\nApiVersion\u0012\u001b\n\u0017API_VERSION_UNSPECIFIED\u0010\u0000\u0012\u0012\n\u000eAPI_VERSION_V1\u0010\u0001\u0012\u0012\n\u000eAPI_VERSION_V2\u0010\u0002\u0012\u0019\n\u0015API_VERSION_V2_BETA_1\u0010\u0003\"^\n\u0004Tier\u0012\u0014\n\u0010TIER_UNSPECIFIED\u0010\u0000\u0012\u0011\n\rTIER_STANDARD\u0010\u0001\u0012\u0013\n\u000fTIER_ENTERPRISE\u0010\u0002\u0012\u0018\n\u0014TIER_ENTERPRISE_PLUS\u0010\u0003:>êA;\n\u001fdialogflow.googleapis.com/Agent\u0012\u0018projects/{project}/agent\"V\n\u000fGetAgentRequest\u0012C\n\u0006parent\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+cloudresourcemanager.googleapis.com/Project\"~\n\u000fSetAgentRequest\u00125\n\u0005agent\u0018\u0001 \u0001(\u000b2!.google.cloud.dialogflow.v2.AgentB\u0003àA\u0002\u00124\n\u000bupdate_mask\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0003àA\u0001\"Y\n\u0012DeleteAgentRequest\u0012C\n\u0006parent\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+cloudresourcemanager.googleapis.com/Project\"\u0086\u0001\n\u0013SearchAgentsRequest\u0012C\n\u0006parent\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+cloudresourcemanager.googleapis.com/Project\u0012\u0016\n\tpage_size\u0018\u0002 \u0001(\u0005B\u0003àA\u0001\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"b\n\u0014SearchAgentsResponse\u00121\n\u0006agents\u0018\u0001 \u0003(\u000b2!.google.cloud.dialogflow.v2.Agent\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"X\n\u0011TrainAgentRequest\u0012C\n\u0006parent\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+cloudresourcemanager.googleapis.com/Project\"q\n\u0012ExportAgentRequest\u0012C\n\u0006parent\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+cloudresourcemanager.googleapis.com/Project\u0012\u0016\n\tagent_uri\u0018\u0002 \u0001(\tB\u0003àA\u0002\"L\n\u0013ExportAgentResponse\u0012\u0013\n\tagent_uri\u0018\u0001 \u0001(\tH\u0000\u0012\u0017\n\ragent_content\u0018\u0002 \u0001(\fH\u0000B\u0007\n\u0005agent\"\u0090\u0001\n\u0012ImportAgentRequest\u0012C\n\u0006parent\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+cloudresourcemanager.googleapis.com/Project\u0012\u0013\n\tagent_uri\u0018\u0002 \u0001(\tH\u0000\u0012\u0017\n\ragent_content\u0018\u0003 \u0001(\fH\u0000B\u0007\n\u0005agent\"\u0091\u0001\n\u0013RestoreAgentRequest\u0012C\n\u0006parent\u0018\u0001 \u0001(\tB3àA\u0002úA-\n+cloudresourcemanager.googleapis.com/Project\u0012\u0013\n\tagent_uri\u0018\u0002 \u0001(\tH\u0000\u0012\u0017\n\ragent_content\u0018\u0003 \u0001(\fH\u0000B\u0007\n\u0005agent\"M\n\u001aGetValidationResultRequest\u0012\u0013\n\u0006parent\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0003 \u0001(\tB\u0003àA\u00012Å\r\n\u0006Agents\u0012\u008a\u0001\n\bGetAgent\u0012+.google.cloud.dialogflow.v2.GetAgentRequest\u001a!.google.cloud.dialogflow.v2.Agent\".\u0082Óä\u0093\u0002\u001f\u0012\u001d/v2/{parent=projects/*}/agentÚA\u0006parent\u0012\u0096\u0001\n\bSetAgent\u0012+.google.cloud.dialogflow.v2.SetAgentRequest\u001a!.google.cloud.dialogflow.v2.Agent\":\u0082Óä\u0093\u0002,\"#/v2/{agent.parent=projects/*}/agent:\u0005agentÚA\u0005agent\u0012\u0085\u0001\n\u000bDeleteAgent\u0012..google.cloud.dialogflow.v2.DeleteAgentRequest\u001a\u0016.google.protobuf.Empty\".\u0082Óä\u0093\u0002\u001f*\u001d/v2/{parent=projects/*}/agentÚA\u0006parent\u0012¨\u0001\n\fSearchAgents\u0012/.google.cloud.dialogflow.v2.SearchAgentsRequest\u001a0.google.cloud.dialogflow.v2.SearchAgentsResponse\"5\u0082Óä\u0093\u0002&\u0012$/v2/{parent=projects/*}/agent:searchÚA\u0006parent\u0012Å\u0001\n\nTrainAgent\u0012-.google.cloud.dialogflow.v2.TrainAgentRequest\u001a\u001d.google.longrunning.Operation\"i\u0082Óä\u0093\u0002(\"#/v2/{parent=projects/*}/agent:train:\u0001*ÚA\u0006parentÊA/\n\u0015google.protobuf.Empty\u0012\u0016google.protobuf.Struct\u0012â\u0001\n\u000bExportAgent\u0012..google.cloud.dialogflow.v2.ExportAgentRequest\u001a\u001d.google.longrunning.Operation\"\u0083\u0001\u0082Óä\u0093\u0002)\"$/v2/{parent=projects/*}/agent:export:\u0001*ÚA\u0006parentÊAH\n.google.cloud.dialogflow.v2.ExportAgentResponse\u0012\u0016google.protobuf.Struct\u0012¿\u0001\n\u000bImportAgent\u0012..google.cloud.dialogflow.v2.ImportAgentRequest\u001a\u001d.google.longrunning.Operation\"a\u0082Óä\u0093\u0002)\"$/v2/{parent=projects/*}/agent:import:\u0001*ÊA/\n\u0015google.protobuf.Empty\u0012\u0016google.protobuf.Struct\u0012Â\u0001\n\fRestoreAgent\u0012/.google.cloud.dialogflow.v2.RestoreAgentRequest\u001a\u001d.google.longrunning.Operation\"b\u0082Óä\u0093\u0002*\"%/v2/{parent=projects/*}/agent:restore:\u0001*ÊA/\n\u0015google.protobuf.Empty\u0012\u0016google.protobuf.Struct\u0012³\u0001\n\u0013GetValidationResult\u00126.google.cloud.dialogflow.v2.GetValidationResultRequest\u001a,.google.cloud.dialogflow.v2.ValidationResult\"6\u0082Óä\u0093\u00020\u0012./v2/{parent=projects/*}/agent/validationResult\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB\u0099\u0001\n\u001ecom.google.cloud.dialogflow.v2B\nAgentProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001aGoogle.Cloud.Dialogflow.V2b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), ValidationResultProto.getDescriptor(), OperationsProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2_Agent_descriptor = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Agent_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1361xa05f55a2 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2_Agent_descriptor, new String[]{"Parent", "DisplayName", "DefaultLanguageCode", "SupportedLanguageCodes", "TimeZone", "Description", "AvatarUri", "EnableLogging", "MatchMode", "ClassificationThreshold", "ApiVersion", "Tier"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetAgentRequest_descriptor */
    static final Descriptors.Descriptor f1368x36e6eb49 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1369x2dbeb8c7 = new GeneratedMessageV3.FieldAccessorTable(f1368x36e6eb49, new String[]{"Parent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SetAgentRequest_descriptor */
    static final Descriptors.Descriptor f1380x4d16d0bd = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SetAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1381xb3bea3b = new GeneratedMessageV3.FieldAccessorTable(f1380x4d16d0bd, new String[]{"Agent", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteAgentRequest_descriptor */
    static final Descriptors.Descriptor f1362xe7bf067e = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1363x7076cefc = new GeneratedMessageV3.FieldAccessorTable(f1362xe7bf067e, new String[]{"Parent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SearchAgentsRequest_descriptor */
    static final Descriptors.Descriptor f1376x271c6a90 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SearchAgentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1377x2b00010e = new GeneratedMessageV3.FieldAccessorTable(f1376x271c6a90, new String[]{"Parent", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SearchAgentsResponse_descriptor */
    static final Descriptors.Descriptor f1378xc9dcecdc = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_SearchAgentsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1379xd26f775a = new GeneratedMessageV3.FieldAccessorTable(f1378xc9dcecdc, new String[]{"Agents", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_TrainAgentRequest_descriptor */
    static final Descriptors.Descriptor f1382x5e86d97 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_TrainAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1383x58f80d15 = new GeneratedMessageV3.FieldAccessorTable(f1382x5e86d97, new String[]{"Parent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ExportAgentRequest_descriptor */
    static final Descriptors.Descriptor f1364xa40abe55 = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ExportAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1365x2294bfd3 = new GeneratedMessageV3.FieldAccessorTable(f1364xa40abe55, new String[]{"Parent", "AgentUri"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ExportAgentResponse_descriptor */
    static final Descriptors.Descriptor f1366xeab911b7 = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ExportAgentResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1367xcd729135 = new GeneratedMessageV3.FieldAccessorTable(f1366xeab911b7, new String[]{"AgentUri", "AgentContent", "Agent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ImportAgentRequest_descriptor */
    static final Descriptors.Descriptor f1372xf04eff44 = getDescriptor().getMessageTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ImportAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1373x727ca1c2 = new GeneratedMessageV3.FieldAccessorTable(f1372xf04eff44, new String[]{"Parent", "AgentUri", "AgentContent", "Agent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_RestoreAgentRequest_descriptor */
    static final Descriptors.Descriptor f1374x303cd391 = getDescriptor().getMessageTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_RestoreAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1375xb9ebd90f = new GeneratedMessageV3.FieldAccessorTable(f1374x303cd391, new String[]{"Parent", "AgentUri", "AgentContent", "Agent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetValidationResultRequest_descriptor */
    static final Descriptors.Descriptor f1370x802a6650 = getDescriptor().getMessageTypes().get(11);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetValidationResultRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1371x73363cce = new GeneratedMessageV3.FieldAccessorTable(f1370x802a6650, new String[]{"Parent", "LanguageCode"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private AgentProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        ExtensionRegistry newInstance = ExtensionRegistry.newInstance();
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.defaultHost);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) FieldBehaviorProto.fieldBehavior);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) AnnotationsProto.http);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.methodSignature);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resource);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resourceReference);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) OperationsProto.operationInfo);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        ClientProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        ValidationResultProto.getDescriptor();
        OperationsProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
    }
}
