package com.google.cloud.dialogflow.v2beta1;

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
/* loaded from: classes3.dex */
public final class AgentProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n+google/cloud/dialogflow/v2beta1/agent.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a7google/cloud/dialogflow/v2beta1/validation_result.proto\u001a#google/longrunning/operations.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u0017google/api/client.proto\"Â\u0006\n\u0005Agent\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012\u001d\n\u0015default_language_code\u0018\u0003 \u0001(\t\u0012 \n\u0018supported_language_codes\u0018\u0004 \u0003(\t\u0012\u0011\n\ttime_zone\u0018\u0005 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0006 \u0001(\t\u0012\u0012\n\navatar_uri\u0018\u0007 \u0001(\t\u0012\u0016\n\u000eenable_logging\u0018\b \u0001(\b\u0012D\n\nmatch_mode\u0018\t \u0001(\u000e20.google.cloud.dialogflow.v2beta1.Agent.MatchMode\u0012 \n\u0018classification_threshold\u0018\n \u0001(\u0002\u0012F\n\u000bapi_version\u0018\u000e \u0001(\u000e21.google.cloud.dialogflow.v2beta1.Agent.ApiVersion\u00129\n\u0004tier\u0018\u000f \u0001(\u000e2+.google.cloud.dialogflow.v2beta1.Agent.Tier\"V\n\tMatchMode\u0012\u001a\n\u0016MATCH_MODE_UNSPECIFIED\u0010\u0000\u0012\u0015\n\u0011MATCH_MODE_HYBRID\u0010\u0001\u0012\u0016\n\u0012MATCH_MODE_ML_ONLY\u0010\u0002\"l\n\nApiVersion\u0012\u001b\n\u0017API_VERSION_UNSPECIFIED\u0010\u0000\u0012\u0012\n\u000eAPI_VERSION_V1\u0010\u0001\u0012\u0012\n\u000eAPI_VERSION_V2\u0010\u0002\u0012\u0019\n\u0015API_VERSION_V2_BETA_1\u0010\u0003\"^\n\u0004Tier\u0012\u0014\n\u0010TIER_UNSPECIFIED\u0010\u0000\u0012\u0011\n\rTIER_STANDARD\u0010\u0001\u0012\u0013\n\u000fTIER_ENTERPRISE\u0010\u0002\u0012\u0018\n\u0014TIER_ENTERPRISE_PLUS\u0010\u0003:mêAj\n\u001fdialogflow.googleapis.com/Agent\u0012\u0018projects/{project}/agent\u0012-projects/{project}/locations/{location}/agent\"!\n\u000fGetAgentRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\"y\n\u000fSetAgentRequest\u00125\n\u0005agent\u0018\u0001 \u0001(\u000b2&.google.cloud.dialogflow.v2beta1.Agent\u0012/\n\u000bupdate_mask\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.FieldMask\"$\n\u0012DeleteAgentRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\"0\n\bSubAgent\u0012\u000f\n\u0007project\u0018\u0001 \u0001(\t\u0012\u0013\n\u000benvironment\u0018\u0002 \u0001(\t\"L\n\u0013SearchAgentsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"g\n\u0014SearchAgentsResponse\u00126\n\u0006agents\u0018\u0001 \u0003(\u000b2&.google.cloud.dialogflow.v2beta1.Agent\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"#\n\u0011TrainAgentRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\"7\n\u0012ExportAgentRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0011\n\tagent_uri\u0018\u0002 \u0001(\t\"L\n\u0013ExportAgentResponse\u0012\u0013\n\tagent_uri\u0018\u0001 \u0001(\tH\u0000\u0012\u0017\n\ragent_content\u0018\u0002 \u0001(\fH\u0000B\u0007\n\u0005agent\"[\n\u0012ImportAgentRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0013\n\tagent_uri\u0018\u0002 \u0001(\tH\u0000\u0012\u0017\n\ragent_content\u0018\u0003 \u0001(\fH\u0000B\u0007\n\u0005agent\"\\\n\u0013RestoreAgentRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0013\n\tagent_uri\u0018\u0002 \u0001(\tH\u0000\u0012\u0017\n\ragent_content\u0018\u0003 \u0001(\fH\u0000B\u0007\n\u0005agent\"M\n\u001aGetValidationResultRequest\u0012\u0013\n\u0006parent\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0003 \u0001(\tB\u0003àA\u00012¯\u000f\n\u0006Agents\u0012Â\u0001\n\bGetAgent\u00120.google.cloud.dialogflow.v2beta1.GetAgentRequest\u001a&.google.cloud.dialogflow.v2beta1.Agent\"\\\u0082Óä\u0093\u0002V\u0012\"/v2beta1/{parent=projects/*}/agentZ0\u0012./v2beta1/{parent=projects/*/locations/*}/agent\u0012Ü\u0001\n\bSetAgent\u00120.google.cloud.dialogflow.v2beta1.SetAgentRequest\u001a&.google.cloud.dialogflow.v2beta1.Agent\"v\u0082Óä\u0093\u0002p\"(/v2beta1/{agent.parent=projects/*}/agent:\u0005agentZ=\"4/v2beta1/{agent.parent=projects/*/locations/*}/agent:\u0005agent\u0012¸\u0001\n\u000bDeleteAgent\u00123.google.cloud.dialogflow.v2beta1.DeleteAgentRequest\u001a\u0016.google.protobuf.Empty\"\\\u0082Óä\u0093\u0002V*\"/v2beta1/{parent=projects/*}/agentZ0*./v2beta1/{parent=projects/*/locations/*}/agent\u0012®\u0001\n\fSearchAgents\u00124.google.cloud.dialogflow.v2beta1.SearchAgentsRequest\u001a5.google.cloud.dialogflow.v2beta1.SearchAgentsResponse\"1\u0082Óä\u0093\u0002+\u0012)/v2beta1/{parent=projects/*}/agent:search\u0012Ï\u0001\n\nTrainAgent\u00122.google.cloud.dialogflow.v2beta1.TrainAgentRequest\u001a\u001d.google.longrunning.Operation\"n\u0082Óä\u0093\u0002h\"(/v2beta1/{parent=projects/*}/agent:train:\u0001*Z9\"4/v2beta1/{parent=projects/*/locations/*}/agent:train:\u0001*\u0012Ó\u0001\n\u000bExportAgent\u00123.google.cloud.dialogflow.v2beta1.ExportAgentRequest\u001a\u001d.google.longrunning.Operation\"p\u0082Óä\u0093\u0002j\")/v2beta1/{parent=projects/*}/agent:export:\u0001*Z:\"5/v2beta1/{parent=projects/*/locations/*}/agent:export:\u0001*\u0012Ó\u0001\n\u000bImportAgent\u00123.google.cloud.dialogflow.v2beta1.ImportAgentRequest\u001a\u001d.google.longrunning.Operation\"p\u0082Óä\u0093\u0002j\")/v2beta1/{parent=projects/*}/agent:import:\u0001*Z:\"5/v2beta1/{parent=projects/*/locations/*}/agent:import:\u0001*\u0012×\u0001\n\fRestoreAgent\u00124.google.cloud.dialogflow.v2beta1.RestoreAgentRequest\u001a\u001d.google.longrunning.Operation\"r\u0082Óä\u0093\u0002l\"*/v2beta1/{parent=projects/*}/agent:restore:\u0001*Z;\"6/v2beta1/{parent=projects/*/locations/*}/agent:restore:\u0001*\u0012Â\u0001\n\u0013GetValidationResult\u0012;.google.cloud.dialogflow.v2beta1.GetValidationResultRequest\u001a1.google.cloud.dialogflow.v2beta1.ValidationResult\";\u0082Óä\u0093\u00025\u00123/v2beta1/{parent=projects/*}/agent/validationResult\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB¨\u0001\n#com.google.cloud.dialogflow.v2beta1B\nAgentProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), ValidationResultProto.getDescriptor(), OperationsProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), ClientProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2beta1_Agent_descriptor = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Agent_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1578xf679de89 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2beta1_Agent_descriptor, new String[]{"Parent", "DisplayName", "DefaultLanguageCode", "SupportedLanguageCodes", "TimeZone", "Description", "AvatarUri", "EnableLogging", "MatchMode", "ClassificationThreshold", "ApiVersion", "Tier"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetAgentRequest_descriptor */
    static final Descriptors.Descriptor f1585x7082d670 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1586x4ac8cee = new GeneratedMessageV3.FieldAccessorTable(f1585x7082d670, new String[]{"Parent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SetAgentRequest_descriptor */
    static final Descriptors.Descriptor f1597x86b2bbe4 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SetAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1598xe229be62 = new GeneratedMessageV3.FieldAccessorTable(f1597x86b2bbe4, new String[]{"Agent", "UpdateMask"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteAgentRequest_descriptor */
    static final Descriptors.Descriptor f1579xef28f5f7 = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1580xf6da3575 = new GeneratedMessageV3.FieldAccessorTable(f1579xef28f5f7, new String[]{"Parent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SubAgent_descriptor */
    static final Descriptors.Descriptor f1599xed7745e7 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SubAgent_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1600xe5919565 = new GeneratedMessageV3.FieldAccessorTable(f1599xed7745e7, new String[]{"Project", "Environment"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SearchAgentsRequest_descriptor */
    static final Descriptors.Descriptor f1593xcf06a37 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SearchAgentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1594x710969b5 = new GeneratedMessageV3.FieldAccessorTable(f1593xcf06a37, new String[]{"Parent", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SearchAgentsResponse_descriptor */
    static final Descriptors.Descriptor f1595x9e88e215 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_SearchAgentsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1596x4d932393 = new GeneratedMessageV3.FieldAccessorTable(f1595x9e88e215, new String[]{"Agents", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_TrainAgentRequest_descriptor */
    static final Descriptors.Descriptor f1601x48362afe = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_TrainAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1602x2bc1737c = new GeneratedMessageV3.FieldAccessorTable(f1601x48362afe, new String[]{"Parent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ExportAgentRequest_descriptor */
    static final Descriptors.Descriptor f1581xab74adce = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ExportAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1582xa8f8264c = new GeneratedMessageV3.FieldAccessorTable(f1581xab74adce, new String[]{"Parent", "AgentUri"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ExportAgentResponse_descriptor */
    static final Descriptors.Descriptor f1583xd08d115e = getDescriptor().getMessageTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ExportAgentResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1584x137bf9dc = new GeneratedMessageV3.FieldAccessorTable(f1583xd08d115e, new String[]{"AgentUri", "AgentContent", "Agent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ImportAgentRequest_descriptor */
    static final Descriptors.Descriptor f1589xf7b8eebd = getDescriptor().getMessageTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ImportAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1590xf8e0083b = new GeneratedMessageV3.FieldAccessorTable(f1589xf7b8eebd, new String[]{"Parent", "AgentUri", "AgentContent", "Agent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_RestoreAgentRequest_descriptor */
    static final Descriptors.Descriptor f1591x1610d338 = getDescriptor().getMessageTypes().get(11);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_RestoreAgentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1592xfff541b6 = new GeneratedMessageV3.FieldAccessorTable(f1591x1610d338, new String[]{"Parent", "AgentUri", "AgentContent", "Agent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetValidationResultRequest_descriptor */
    static final Descriptors.Descriptor f1587x68dccc9 = getDescriptor().getMessageTypes().get(12);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetValidationResultRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1588x3f2c1a47 = new GeneratedMessageV3.FieldAccessorTable(f1587x68dccc9, new String[]{"Parent", "LanguageCode"});

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
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resource);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        ValidationResultProto.getDescriptor();
        OperationsProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
