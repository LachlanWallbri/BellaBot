package com.google.cloud.dialogflow.p049v2;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.api.FieldBehaviorProto;
import com.google.api.ResourceProto;
import com.google.longrunning.OperationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMaskProto;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class IntentProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n'google/cloud/dialogflow/v2/intent.proto\u0012\u001agoogle.cloud.dialogflow.v2\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/client.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a-google/cloud/dialogflow/v2/audio_config.proto\u001a(google/cloud/dialogflow/v2/context.proto\u001a#google/longrunning/operations.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u001cgoogle/protobuf/struct.proto\"Ç7\n\u0006Intent\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0019\n\fdisplay_name\u0018\u0002 \u0001(\tB\u0003àA\u0002\u0012K\n\rwebhook_state\u0018\u0006 \u0001(\u000e2/.google.cloud.dialogflow.v2.Intent.WebhookStateB\u0003àA\u0001\u0012\u0015\n\bpriority\u0018\u0003 \u0001(\u0005B\u0003àA\u0001\u0012\u0018\n\u000bis_fallback\u0018\u0004 \u0001(\bB\u0003àA\u0001\u0012\u0018\n\u000bml_disabled\u0018\u0013 \u0001(\bB\u0003àA\u0001\u0012 \n\u0013input_context_names\u0018\u0007 \u0003(\tB\u0003àA\u0001\u0012\u0013\n\u0006events\u0018\b \u0003(\tB\u0003àA\u0001\u0012P\n\u0010training_phrases\u0018\t \u0003(\u000b21.google.cloud.dialogflow.v2.Intent.TrainingPhraseB\u0003àA\u0001\u0012\u0013\n\u0006action\u0018\n \u0001(\tB\u0003àA\u0001\u0012A\n\u000foutput_contexts\u0018\u000b \u0003(\u000b2#.google.cloud.dialogflow.v2.ContextB\u0003àA\u0001\u0012\u001b\n\u000ereset_contexts\u0018\f \u0001(\bB\u0003àA\u0001\u0012E\n\nparameters\u0018\r \u0003(\u000b2,.google.cloud.dialogflow.v2.Intent.ParameterB\u0003àA\u0001\u0012A\n\bmessages\u0018\u000e \u0003(\u000b2*.google.cloud.dialogflow.v2.Intent.MessageB\u0003àA\u0001\u0012\\\n\u001adefault_response_platforms\u0018\u000f \u0003(\u000e23.google.cloud.dialogflow.v2.Intent.Message.PlatformB\u0003àA\u0001\u0012!\n\u0019root_followup_intent_name\u0018\u0010 \u0001(\t\u0012#\n\u001bparent_followup_intent_name\u0018\u0011 \u0001(\t\u0012S\n\u0014followup_intent_info\u0018\u0012 \u0003(\u000b25.google.cloud.dialogflow.v2.Intent.FollowupIntentInfo\u001añ\u0002\n\u000eTrainingPhrase\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012I\n\u0004type\u0018\u0002 \u0001(\u000e26.google.cloud.dialogflow.v2.Intent.TrainingPhrase.TypeB\u0003àA\u0002\u0012J\n\u0005parts\u0018\u0003 \u0003(\u000b26.google.cloud.dialogflow.v2.Intent.TrainingPhrase.PartB\u0003àA\u0002\u0012\u001e\n\u0011times_added_count\u0018\u0004 \u0001(\u0005B\u0003àA\u0001\u001a]\n\u0004Part\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\u0012\u0018\n\u000bentity_type\u0018\u0002 \u0001(\tB\u0003àA\u0001\u0012\u0012\n\u0005alias\u0018\u0003 \u0001(\tB\u0003àA\u0001\u0012\u0019\n\fuser_defined\u0018\u0004 \u0001(\bB\u0003àA\u0001\";\n\u0004Type\u0012\u0014\n\u0010TYPE_UNSPECIFIED\u0010\u0000\u0012\u000b\n\u0007EXAMPLE\u0010\u0001\u0012\u0010\n\bTEMPLATE\u0010\u0002\u001a\u0002\b\u0001\u001aÊ\u0001\n\tParameter\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012\u0012\n\u0005value\u0018\u0003 \u0001(\tB\u0003àA\u0001\u0012\u001a\n\rdefault_value\u0018\u0004 \u0001(\tB\u0003àA\u0001\u0012%\n\u0018entity_type_display_name\u0018\u0005 \u0001(\tB\u0003àA\u0001\u0012\u0016\n\tmandatory\u0018\u0006 \u0001(\bB\u0003àA\u0001\u0012\u0014\n\u0007prompts\u0018\u0007 \u0003(\tB\u0003àA\u0001\u0012\u0014\n\u0007is_list\u0018\b \u0001(\bB\u0003àA\u0001\u001a\u0096*\n\u0007Message\u0012?\n\u0004text\u0018\u0001 \u0001(\u000b2/.google.cloud.dialogflow.v2.Intent.Message.TextH\u0000\u0012A\n\u0005image\u0018\u0002 \u0001(\u000b20.google.cloud.dialogflow.v2.Intent.Message.ImageH\u0000\u0012P\n\rquick_replies\u0018\u0003 \u0001(\u000b27.google.cloud.dialogflow.v2.Intent.Message.QuickRepliesH\u0000\u0012?\n\u0004card\u0018\u0004 \u0001(\u000b2/.google.cloud.dialogflow.v2.Intent.Message.CardH\u0000\u0012*\n\u0007payload\u0018\u0005 \u0001(\u000b2\u0017.google.protobuf.StructH\u0000\u0012V\n\u0010simple_responses\u0018\u0007 \u0001(\u000b2:.google.cloud.dialogflow.v2.Intent.Message.SimpleResponsesH\u0000\u0012J\n\nbasic_card\u0018\b \u0001(\u000b24.google.cloud.dialogflow.v2.Intent.Message.BasicCardH\u0000\u0012M\n\u000bsuggestions\u0018\t \u0001(\u000b26.google.cloud.dialogflow.v2.Intent.Message.SuggestionsH\u0000\u0012[\n\u0013link_out_suggestion\u0018\n \u0001(\u000b2<.google.cloud.dialogflow.v2.Intent.Message.LinkOutSuggestionH\u0000\u0012L\n\u000blist_select\u0018\u000b \u0001(\u000b25.google.cloud.dialogflow.v2.Intent.Message.ListSelectH\u0000\u0012T\n\u000fcarousel_select\u0018\f \u0001(\u000b29.google.cloud.dialogflow.v2.Intent.Message.CarouselSelectH\u0000\u0012]\n\u0014browse_carousel_card\u0018\u0016 \u0001(\u000b2=.google.cloud.dialogflow.v2.Intent.Message.BrowseCarouselCardH\u0000\u0012J\n\ntable_card\u0018\u0017 \u0001(\u000b24.google.cloud.dialogflow.v2.Intent.Message.TableCardH\u0000\u0012P\n\rmedia_content\u0018\u0018 \u0001(\u000b27.google.cloud.dialogflow.v2.Intent.Message.MediaContentH\u0000\u0012J\n\bplatform\u0018\u0006 \u0001(\u000e23.google.cloud.dialogflow.v2.Intent.Message.PlatformB\u0003àA\u0001\u001a\u0019\n\u0004Text\u0012\u0011\n\u0004text\u0018\u0001 \u0003(\tB\u0003àA\u0001\u001a@\n\u0005Image\u0012\u0016\n\timage_uri\u0018\u0001 \u0001(\tB\u0003àA\u0001\u0012\u001f\n\u0012accessibility_text\u0018\u0002 \u0001(\tB\u0003àA\u0001\u001a>\n\fQuickReplies\u0012\u0012\n\u0005title\u0018\u0001 \u0001(\tB\u0003àA\u0001\u0012\u001a\n\rquick_replies\u0018\u0002 \u0003(\tB\u0003àA\u0001\u001aË\u0001\n\u0004Card\u0012\u0012\n\u0005title\u0018\u0001 \u0001(\tB\u0003àA\u0001\u0012\u0015\n\bsubtitle\u0018\u0002 \u0001(\tB\u0003àA\u0001\u0012\u0016\n\timage_uri\u0018\u0003 \u0001(\tB\u0003àA\u0001\u0012L\n\u0007buttons\u0018\u0004 \u0003(\u000b26.google.cloud.dialogflow.v2.Intent.Message.Card.ButtonB\u0003àA\u0001\u001a2\n\u0006Button\u0012\u0011\n\u0004text\u0018\u0001 \u0001(\tB\u0003àA\u0001\u0012\u0015\n\bpostback\u0018\u0002 \u0001(\tB\u0003àA\u0001\u001aQ\n\u000eSimpleResponse\u0012\u0016\n\u000etext_to_speech\u0018\u0001 \u0001(\t\u0012\f\n\u0004ssml\u0018\u0002 \u0001(\t\u0012\u0019\n\fdisplay_text\u0018\u0003 \u0001(\tB\u0003àA\u0001\u001ak\n\u000fSimpleResponses\u0012X\n\u0010simple_responses\u0018\u0001 \u0003(\u000b29.google.cloud.dialogflow.v2.Intent.Message.SimpleResponseB\u0003àA\u0002\u001a\u0088\u0003\n\tBasicCard\u0012\u0012\n\u0005title\u0018\u0001 \u0001(\tB\u0003àA\u0001\u0012\u0015\n\bsubtitle\u0018\u0002 \u0001(\tB\u0003àA\u0001\u0012\u0016\n\u000eformatted_text\u0018\u0003 \u0001(\t\u0012D\n\u0005image\u0018\u0004 \u0001(\u000b20.google.cloud.dialogflow.v2.Intent.Message.ImageB\u0003àA\u0001\u0012Q\n\u0007buttons\u0018\u0005 \u0003(\u000b2;.google.cloud.dialogflow.v2.Intent.Message.BasicCard.ButtonB\u0003àA\u0001\u001a\u009e\u0001\n\u0006Button\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012g\n\u000fopen_uri_action\u0018\u0002 \u0001(\u000b2I.google.cloud.dialogflow.v2.Intent.Message.BasicCard.Button.OpenUriActionB\u0003àA\u0002\u001a\u001c\n\rOpenUriAction\u0012\u000b\n\u0003uri\u0018\u0001 \u0001(\t\u001a \n\nSuggestion\u0012\u0012\n\u0005title\u0018\u0001 \u0001(\tB\u0003àA\u0002\u001a^\n\u000bSuggestions\u0012O\n\u000bsuggestions\u0018\u0001 \u0003(\u000b25.google.cloud.dialogflow.v2.Intent.Message.SuggestionB\u0003àA\u0002\u001aD\n\u0011LinkOutSuggestion\u0012\u001d\n\u0010destination_name\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u0010\n\u0003uri\u0018\u0002 \u0001(\tB\u0003àA\u0002\u001aÒ\u0002\n\nListSelect\u0012\u0012\n\u0005title\u0018\u0001 \u0001(\tB\u0003àA\u0001\u0012N\n\u0005items\u0018\u0002 \u0003(\u000b2:.google.cloud.dialogflow.v2.Intent.Message.ListSelect.ItemB\u0003àA\u0002\u0012\u0015\n\bsubtitle\u0018\u0003 \u0001(\tB\u0003àA\u0001\u001aÈ\u0001\n\u0004Item\u0012L\n\u0004info\u0018\u0001 \u0001(\u000b29.google.cloud.dialogflow.v2.Intent.Message.SelectItemInfoB\u0003àA\u0002\u0012\u0012\n\u0005title\u0018\u0002 \u0001(\tB\u0003àA\u0002\u0012\u0018\n\u000bdescription\u0018\u0003 \u0001(\tB\u0003àA\u0001\u0012D\n\u0005image\u0018\u0004 \u0001(\u000b20.google.cloud.dialogflow.v2.Intent.Message.ImageB\u0003àA\u0001\u001a¯\u0002\n\u000eCarouselSelect\u0012R\n\u0005items\u0018\u0001 \u0003(\u000b2>.google.cloud.dialogflow.v2.Intent.Message.CarouselSelect.ItemB\u0003àA\u0002\u001aÈ\u0001\n\u0004Item\u0012L\n\u0004info\u0018\u0001 \u0001(\u000b29.google.cloud.dialogflow.v2.Intent.Message.SelectItemInfoB\u0003àA\u0002\u0012\u0012\n\u0005title\u0018\u0002 \u0001(\tB\u0003àA\u0002\u0012\u0018\n\u000bdescription\u0018\u0003 \u0001(\tB\u0003àA\u0001\u0012D\n\u0005image\u0018\u0004 \u0001(\u000b20.google.cloud.dialogflow.v2.Intent.Message.ImageB\u0003àA\u0001\u001a9\n\u000eSelectItemInfo\u0012\u0010\n\u0003key\u0018\u0001 \u0001(\tB\u0003àA\u0002\u0012\u0015\n\bsynonyms\u0018\u0002 \u0003(\tB\u0003àA\u0001\u001a\u008e\u0004\n\fMediaContent\u0012b\n\nmedia_type\u0018\u0001 \u0001(\u000e2I.google.cloud.dialogflow.v2.Intent.Message.MediaContent.ResponseMediaTypeB\u0003àA\u0001\u0012b\n\rmedia_objects\u0018\u0002 \u0003(\u000b2K.google.cloud.dialogflow.v2.Intent.Message.MediaContent.ResponseMediaObject\u001að\u0001\n\u0013ResponseMediaObject\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0018\n\u000bdescription\u0018\u0002 \u0001(\tB\u0003àA\u0001\u0012L\n\u000blarge_image\u0018\u0003 \u0001(\u000b20.google.cloud.dialogflow.v2.Intent.Message.ImageB\u0003àA\u0001H\u0000\u0012E\n\u0004icon\u0018\u0004 \u0001(\u000b20.google.cloud.dialogflow.v2.Intent.Message.ImageB\u0003àA\u0001H\u0000\u0012\u0013\n\u000bcontent_url\u0018\u0005 \u0001(\tB\u0007\n\u0005image\"C\n\u0011ResponseMediaType\u0012#\n\u001fRESPONSE_MEDIA_TYPE_UNSPECIFIED\u0010\u0000\u0012\t\n\u0005AUDIO\u0010\u0001\u001aÿ\u0006\n\u0012BrowseCarouselCard\u0012c\n\u0005items\u0018\u0001 \u0003(\u000b2T.google.cloud.dialogflow.v2.Intent.Message.BrowseCarouselCard.BrowseCarouselCardItem\u0012u\n\u0015image_display_options\u0018\u0002 \u0001(\u000e2Q.google.cloud.dialogflow.v2.Intent.Message.BrowseCarouselCard.ImageDisplayOptionsB\u0003àA\u0001\u001a\u0094\u0004\n\u0016BrowseCarouselCardItem\u0012{\n\u000fopen_uri_action\u0018\u0001 \u0001(\u000b2b.google.cloud.dialogflow.v2.Intent.Message.BrowseCarouselCard.BrowseCarouselCardItem.OpenUrlAction\u0012\r\n\u0005title\u0018\u0002 \u0001(\t\u0012\u0018\n\u000bdescription\u0018\u0003 \u0001(\tB\u0003àA\u0001\u0012D\n\u0005image\u0018\u0004 \u0001(\u000b20.google.cloud.dialogflow.v2.Intent.Message.ImageB\u0003àA\u0001\u0012\u0013\n\u0006footer\u0018\u0005 \u0001(\tB\u0003àA\u0001\u001aø\u0001\n\rOpenUrlAction\u0012\u000b\n\u0003url\u0018\u0001 \u0001(\t\u0012\u008a\u0001\n\rurl_type_hint\u0018\u0003 \u0001(\u000e2n.google.cloud.dialogflow.v2.Intent.Message.BrowseCarouselCard.BrowseCarouselCardItem.OpenUrlAction.UrlTypeHintB\u0003àA\u0001\"M\n\u000bUrlTypeHint\u0012\u001d\n\u0019URL_TYPE_HINT_UNSPECIFIED\u0010\u0000\u0012\u000e\n\nAMP_ACTION\u0010\u0001\u0012\u000f\n\u000bAMP_CONTENT\u0010\u0002\"v\n\u0013ImageDisplayOptions\u0012%\n!IMAGE_DISPLAY_OPTIONS_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004GRAY\u0010\u0001\u0012\t\n\u0005WHITE\u0010\u0002\u0012\u000b\n\u0007CROPPED\u0010\u0003\u0012\u0016\n\u0012BLURRED_BACKGROUND\u0010\u0004\u001aó\u0002\n\tTableCard\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012\u0015\n\bsubtitle\u0018\u0002 \u0001(\tB\u0003àA\u0001\u0012D\n\u0005image\u0018\u0003 \u0001(\u000b20.google.cloud.dialogflow.v2.Intent.Message.ImageB\u0003àA\u0001\u0012[\n\u0011column_properties\u0018\u0004 \u0003(\u000b2;.google.cloud.dialogflow.v2.Intent.Message.ColumnPropertiesB\u0003àA\u0001\u0012J\n\u0004rows\u0018\u0005 \u0003(\u000b27.google.cloud.dialogflow.v2.Intent.Message.TableCardRowB\u0003àA\u0001\u0012Q\n\u0007buttons\u0018\u0006 \u0003(\u000b2;.google.cloud.dialogflow.v2.Intent.Message.BasicCard.ButtonB\u0003àA\u0001\u001aú\u0001\n\u0010ColumnProperties\u0012\u000e\n\u0006header\u0018\u0001 \u0001(\t\u0012r\n\u0014horizontal_alignment\u0018\u0002 \u0001(\u000e2O.google.cloud.dialogflow.v2.Intent.Message.ColumnProperties.HorizontalAlignmentB\u0003àA\u0001\"b\n\u0013HorizontalAlignment\u0012$\n HORIZONTAL_ALIGNMENT_UNSPECIFIED\u0010\u0000\u0012\u000b\n\u0007LEADING\u0010\u0001\u0012\n\n\u0006CENTER\u0010\u0002\u0012\f\n\bTRAILING\u0010\u0003\u001ax\n\fTableCardRow\u0012L\n\u0005cells\u0018\u0001 \u0003(\u000b28.google.cloud.dialogflow.v2.Intent.Message.TableCardCellB\u0003àA\u0001\u0012\u001a\n\rdivider_after\u0018\u0002 \u0001(\bB\u0003àA\u0001\u001a\u001d\n\rTableCardCell\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\" \u0001\n\bPlatform\u0012\u0018\n\u0014PLATFORM_UNSPECIFIED\u0010\u0000\u0012\f\n\bFACEBOOK\u0010\u0001\u0012\t\n\u0005SLACK\u0010\u0002\u0012\f\n\bTELEGRAM\u0010\u0003\u0012\u0007\n\u0003KIK\u0010\u0004\u0012\t\n\u0005SKYPE\u0010\u0005\u0012\b\n\u0004LINE\u0010\u0006\u0012\t\n\u0005VIBER\u0010\u0007\u0012\u0015\n\u0011ACTIONS_ON_GOOGLE\u0010\b\u0012\u0013\n\u000fGOOGLE_HANGOUTS\u0010\u000bB\t\n\u0007message\u001aW\n\u0012FollowupIntentInfo\u0012\u001c\n\u0014followup_intent_name\u0018\u0001 \u0001(\t\u0012#\n\u001bparent_followup_intent_name\u0018\u0002 \u0001(\t\"t\n\fWebhookState\u0012\u001d\n\u0019WEBHOOK_STATE_UNSPECIFIED\u0010\u0000\u0012\u0019\n\u0015WEBHOOK_STATE_ENABLED\u0010\u0001\u0012*\n&WEBHOOK_STATE_ENABLED_FOR_SLOT_FILLING\u0010\u0002:PêAM\n dialogflow.googleapis.com/Intent\u0012)projects/{project}/agent/intents/{intent}\"Ü\u0001\n\u0012ListIntentsRequest\u00127\n\u0006parent\u0018\u0001 \u0001(\tB'àA\u0002úA!\n\u001fdialogflow.googleapis.com/Agent\u0012\u001a\n\rlanguage_code\u0018\u0002 \u0001(\tB\u0003àA\u0001\u0012@\n\u000bintent_view\u0018\u0003 \u0001(\u000e2&.google.cloud.dialogflow.v2.IntentViewB\u0003àA\u0001\u0012\u0016\n\tpage_size\u0018\u0004 \u0001(\u0005B\u0003àA\u0001\u0012\u0017\n\npage_token\u0018\u0005 \u0001(\tB\u0003àA\u0001\"c\n\u0013ListIntentsResponse\u00123\n\u0007intents\u0018\u0001 \u0003(\u000b2\".google.cloud.dialogflow.v2.Intent\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"¨\u0001\n\u0010GetIntentRequest\u00126\n\u0004name\u0018\u0001 \u0001(\tB(àA\u0002úA\"\n dialogflow.googleapis.com/Intent\u0012\u001a\n\rlanguage_code\u0018\u0002 \u0001(\tB\u0003àA\u0001\u0012@\n\u000bintent_view\u0018\u0003 \u0001(\u000e2&.google.cloud.dialogflow.v2.IntentViewB\u0003àA\u0001\"å\u0001\n\u0013CreateIntentRequest\u00127\n\u0006parent\u0018\u0001 \u0001(\tB'àA\u0002úA!\n\u001fdialogflow.googleapis.com/Agent\u00127\n\u0006intent\u0018\u0002 \u0001(\u000b2\".google.cloud.dialogflow.v2.IntentB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0003 \u0001(\tB\u0003àA\u0001\u0012@\n\u000bintent_view\u0018\u0004 \u0001(\u000e2&.google.cloud.dialogflow.v2.IntentViewB\u0003àA\u0001\"â\u0001\n\u0013UpdateIntentRequest\u00127\n\u0006intent\u0018\u0001 \u0001(\u000b2\".google.cloud.dialogflow.v2.IntentB\u0003àA\u0002\u0012\u001a\n\rlanguage_code\u0018\u0002 \u0001(\tB\u0003àA\u0001\u00124\n\u000bupdate_mask\u0018\u0003 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0003àA\u0001\u0012@\n\u000bintent_view\u0018\u0004 \u0001(\u000e2&.google.cloud.dialogflow.v2.IntentViewB\u0003àA\u0001\"M\n\u0013DeleteIntentRequest\u00126\n\u0004name\u0018\u0001 \u0001(\tB(àA\u0002úA\"\n dialogflow.googleapis.com/Intent\"Ü\u0002\n\u0019BatchUpdateIntentsRequest\u00127\n\u0006parent\u0018\u0001 \u0001(\tB'àA\u0002úA!\n\u001fdialogflow.googleapis.com/Agent\u0012\u001a\n\u0010intent_batch_uri\u0018\u0002 \u0001(\tH\u0000\u0012F\n\u0013intent_batch_inline\u0018\u0003 \u0001(\u000b2'.google.cloud.dialogflow.v2.IntentBatchH\u0000\u0012\u001a\n\rlanguage_code\u0018\u0004 \u0001(\tB\u0003àA\u0001\u00124\n\u000bupdate_mask\u0018\u0005 \u0001(\u000b2\u001a.google.protobuf.FieldMaskB\u0003àA\u0001\u0012@\n\u000bintent_view\u0018\u0006 \u0001(\u000e2&.google.cloud.dialogflow.v2.IntentViewB\u0003àA\u0001B\u000e\n\fintent_batch\"Q\n\u001aBatchUpdateIntentsResponse\u00123\n\u0007intents\u0018\u0001 \u0003(\u000b2\".google.cloud.dialogflow.v2.Intent\"\u008e\u0001\n\u0019BatchDeleteIntentsRequest\u00127\n\u0006parent\u0018\u0001 \u0001(\tB'àA\u0002úA!\n\u001fdialogflow.googleapis.com/Agent\u00128\n\u0007intents\u0018\u0002 \u0003(\u000b2\".google.cloud.dialogflow.v2.IntentB\u0003àA\u0002\"B\n\u000bIntentBatch\u00123\n\u0007intents\u0018\u0001 \u0003(\u000b2\".google.cloud.dialogflow.v2.Intent*?\n\nIntentView\u0012\u001b\n\u0017INTENT_VIEW_UNSPECIFIED\u0010\u0000\u0012\u0014\n\u0010INTENT_VIEW_FULL\u0010\u00012\u0093\f\n\u0007Intents\u0012½\u0001\n\u000bListIntents\u0012..google.cloud.dialogflow.v2.ListIntentsRequest\u001a/.google.cloud.dialogflow.v2.ListIntentsResponse\"M\u0082Óä\u0093\u0002'\u0012%/v2/{parent=projects/*/agent}/intentsÚA\u0006parentÚA\u0014parent,language_code\u0012¨\u0001\n\tGetIntent\u0012,.google.cloud.dialogflow.v2.GetIntentRequest\u001a\".google.cloud.dialogflow.v2.Intent\"I\u0082Óä\u0093\u0002'\u0012%/v2/{name=projects/*/agent/intents/*}ÚA\u0004nameÚA\u0012name,language_code\u0012È\u0001\n\fCreateIntent\u0012/.google.cloud.dialogflow.v2.CreateIntentRequest\u001a\".google.cloud.dialogflow.v2.Intent\"c\u0082Óä\u0093\u0002/\"%/v2/{parent=projects/*/agent}/intents:\u0006intentÚA\rparent,intentÚA\u001bparent,intent,language_code\u0012Û\u0001\n\fUpdateIntent\u0012/.google.cloud.dialogflow.v2.UpdateIntentRequest\u001a\".google.cloud.dialogflow.v2.Intent\"v\u0082Óä\u0093\u000262,/v2/{intent.name=projects/*/agent/intents/*}:\u0006intentÚA\u0014intent,language_codeÚA intent,language_code,update_mask\u0012\u008d\u0001\n\fDeleteIntent\u0012/.google.cloud.dialogflow.v2.DeleteIntentRequest\u001a\u0016.google.protobuf.Empty\"4\u0082Óä\u0093\u0002'*%/v2/{name=projects/*/agent/intents/*}ÚA\u0004name\u0012û\u0001\n\u0012BatchUpdateIntents\u00125.google.cloud.dialogflow.v2.BatchUpdateIntentsRequest\u001a\u001d.google.longrunning.Operation\"\u008e\u0001\u0082Óä\u0093\u00026\"1/v2/{parent=projects/*/agent}/intents:batchUpdate:\u0001*ÊAO\n5google.cloud.dialogflow.v2.BatchUpdateIntentsResponse\u0012\u0016google.protobuf.Struct\u0012ë\u0001\n\u0012BatchDeleteIntents\u00125.google.cloud.dialogflow.v2.BatchDeleteIntentsRequest\u001a\u001d.google.longrunning.Operation\"\u007f\u0082Óä\u0093\u00026\"1/v2/{parent=projects/*/agent}/intents:batchDelete:\u0001*ÚA\u000eparent,intentsÊA/\n\u0015google.protobuf.Empty\u0012\u0016google.protobuf.Struct\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB\u009a\u0001\n\u001ecom.google.cloud.dialogflow.v2B\u000bIntentProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001aGoogle.Cloud.Dialogflow.V2b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), ClientProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), AudioConfigProto.getDescriptor(), ContextProto.getDescriptor(), OperationsProto.getDescriptor(), DurationProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), StructProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_cloud_dialogflow_v2_Intent_descriptor = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1524x9e8f0d95 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_cloud_dialogflow_v2_Intent_descriptor, new String[]{"Name", "DisplayName", "WebhookState", "Priority", "IsFallback", "MlDisabled", "InputContextNames", "Events", "TrainingPhrases", JsonDocumentFields.ACTION, "OutputContexts", "ResetContexts", "Parameters", "Messages", "DefaultResponsePlatforms", "RootFollowupIntentName", "ParentFollowupIntentName", "FollowupIntentInfo"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_TrainingPhrase_descriptor */
    static final Descriptors.Descriptor f1522xefcb333 = internal_static_google_cloud_dialogflow_v2_Intent_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_TrainingPhrase_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1523xd9aaf6b1 = new GeneratedMessageV3.FieldAccessorTable(f1522xefcb333, new String[]{"Name", "Type", "Parts", "TimesAddedCount"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_TrainingPhrase_Part_descriptor */
    static final Descriptors.Descriptor f1520xb2a6f1d7 = f1522xefcb333.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_TrainingPhrase_Part_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1521xc20e5155 = new GeneratedMessageV3.FieldAccessorTable(f1520xb2a6f1d7, new String[]{"Text", "EntityType", "Alias", "UserDefined"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Parameter_descriptor */
    static final Descriptors.Descriptor f1518x1991de8d = internal_static_google_cloud_dialogflow_v2_Intent_descriptor.getNestedTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Parameter_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1519x1df4280b = new GeneratedMessageV3.FieldAccessorTable(f1518x1991de8d, new String[]{"Name", "DisplayName", "Value", "DefaultValue", "EntityTypeDisplayName", "Mandatory", "Prompts", "IsList"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_descriptor */
    static final Descriptors.Descriptor f1516xe8a95d6f = internal_static_google_cloud_dialogflow_v2_Intent_descriptor.getNestedTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1517x7917a4ed = new GeneratedMessageV3.FieldAccessorTable(f1516xe8a95d6f, new String[]{"Text", "Image", "QuickReplies", "Card", "Payload", "SimpleResponses", "BasicCard", "Suggestions", "LinkOutSuggestion", "ListSelect", "CarouselSelect", "BrowseCarouselCard", "TableCard", "MediaContent", DataRecordKey.PLATFORM, "Message"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Text_descriptor */
    static final Descriptors.Descriptor f1514x8ae43a1 = f1516xe8a95d6f.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Text_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1515xc634391f = new GeneratedMessageV3.FieldAccessorTable(f1514x8ae43a1, new String[]{"Text"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Image_descriptor */
    static final Descriptors.Descriptor f1484x6c53f6f3 = f1516xe8a95d6f.getNestedTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Image_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1485xc627a71 = new GeneratedMessageV3.FieldAccessorTable(f1484x6c53f6f3, new String[]{"ImageUri", "AccessibilityText"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_QuickReplies_descriptor */
    static final Descriptors.Descriptor f1496x7a1f91b3 = f1516xe8a95d6f.getNestedTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_QuickReplies_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1497x7d475531 = new GeneratedMessageV3.FieldAccessorTable(f1496x7a1f91b3, new String[]{"Title", "QuickReplies"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Card_descriptor */
    static final Descriptors.Descriptor f1476x777891de = f1516xe8a95d6f.getNestedTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Card_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1477xf91efa5c = new GeneratedMessageV3.FieldAccessorTable(f1476x777891de, new String[]{"Title", "Subtitle", "ImageUri", "Buttons"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Card_Button_descriptor */
    static final Descriptors.Descriptor f1474x1b46252d = f1476x777891de.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Card_Button_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1475x8cc7ceab = new GeneratedMessageV3.FieldAccessorTable(f1474x1b46252d, new String[]{"Text", "Postback"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_SimpleResponse_descriptor */
    static final Descriptors.Descriptor f1500xd90a2cfb = f1516xe8a95d6f.getNestedTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_SimpleResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1501x74a62879 = new GeneratedMessageV3.FieldAccessorTable(f1500xd90a2cfb, new String[]{"TextToSpeech", "Ssml", "DisplayText"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_SimpleResponses_descriptor */
    static final Descriptors.Descriptor f1502x5d29ec6e = f1516xe8a95d6f.getNestedTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_SimpleResponses_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1503x1354c4ec = new GeneratedMessageV3.FieldAccessorTable(f1502x5d29ec6e, new String[]{"SimpleResponses"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BasicCard_descriptor */
    static final Descriptors.Descriptor f1466x41fca410 = f1516xe8a95d6f.getNestedTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BasicCard_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1467xc4ceba8e = new GeneratedMessageV3.FieldAccessorTable(f1466x41fca410, new String[]{"Title", "Subtitle", "FormattedText", "Image", "Buttons"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BasicCard_Button_descriptor */
    static final Descriptors.Descriptor f1464x536496bb = f1466x41fca410.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BasicCard_Button_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1465x39dad239 = new GeneratedMessageV3.FieldAccessorTable(f1464x536496bb, new String[]{"Title", "OpenUriAction"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BasicCard_Button_OpenUriAction_descriptor */
    static final Descriptors.Descriptor f1462x9943d0e2 = f1464x536496bb.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BasicCard_Button_OpenUriAction_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1463x484cf560 = new GeneratedMessageV3.FieldAccessorTable(f1462x9943d0e2, new String[]{"Uri"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Suggestion_descriptor */
    static final Descriptors.Descriptor f1504x94863a6a = f1516xe8a95d6f.getNestedTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Suggestion_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1505xb77156e8 = new GeneratedMessageV3.FieldAccessorTable(f1504x94863a6a, new String[]{"Title"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Suggestions_descriptor */
    static final Descriptors.Descriptor f1506x112f8cdf = f1516xe8a95d6f.getNestedTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_Suggestions_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1507x29ef645d = new GeneratedMessageV3.FieldAccessorTable(f1506x112f8cdf, new String[]{"Suggestions"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_LinkOutSuggestion_descriptor */
    static final Descriptors.Descriptor f1486x6bb9b616 = f1516xe8a95d6f.getNestedTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_LinkOutSuggestion_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1487x2af46694 = new GeneratedMessageV3.FieldAccessorTable(f1486x6bb9b616, new String[]{"DestinationName", "Uri"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_ListSelect_descriptor */
    static final Descriptors.Descriptor f1490xa7ede374 = f1516xe8a95d6f.getNestedTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_ListSelect_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1491x6bcc55f2 = new GeneratedMessageV3.FieldAccessorTable(f1490xa7ede374, new String[]{"Title", "Items", "Subtitle"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_ListSelect_Item_descriptor */
    static final Descriptors.Descriptor f1488x5bd1f716 = f1490xa7ede374.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_ListSelect_Item_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1489xe33ba794 = new GeneratedMessageV3.FieldAccessorTable(f1488x5bd1f716, new String[]{"Info", "Title", "Description", "Image"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_CarouselSelect_descriptor */
    static final Descriptors.Descriptor f1480x28a19ef2 = f1516xe8a95d6f.getNestedTypes().get(11);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_CarouselSelect_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1481x8043b370 = new GeneratedMessageV3.FieldAccessorTable(f1480x28a19ef2, new String[]{"Items"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_CarouselSelect_Item_descriptor */
    static final Descriptors.Descriptor f1478x443dba58 = f1480x28a19ef2.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_CarouselSelect_Item_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1479x8ad908d6 = new GeneratedMessageV3.FieldAccessorTable(f1478x443dba58, new String[]{"Info", "Title", "Description", "Image"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_SelectItemInfo_descriptor */
    static final Descriptors.Descriptor f1498xd74af51 = f1516xe8a95d6f.getNestedTypes().get(12);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_SelectItemInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1499x2e6bf4cf = new GeneratedMessageV3.FieldAccessorTable(f1498xd74af51, new String[]{"Key", "Synonyms"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_MediaContent_descriptor */
    static final Descriptors.Descriptor f1494x9fc7a7d9 = f1516xe8a95d6f.getNestedTypes().get(13);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_MediaContent_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1495x5ea1e557 = new GeneratedMessageV3.FieldAccessorTable(f1494x9fc7a7d9, new String[]{"MediaType", "MediaObjects"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_MediaContent_ResponseMediaObject_descriptor */
    static final Descriptors.Descriptor f1492xa0761e16 = f1494x9fc7a7d9.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_MediaContent_ResponseMediaObject_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1493xb0c8ce94 = new GeneratedMessageV3.FieldAccessorTable(f1492xa0761e16, new String[]{"Name", "Description", "LargeImage", "Icon", "ContentUrl", "Image"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BrowseCarouselCard_descriptor */
    static final Descriptors.Descriptor f1472x7f51aa74 = f1516xe8a95d6f.getNestedTypes().get(14);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BrowseCarouselCard_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1473x62791cf2 = new GeneratedMessageV3.FieldAccessorTable(f1472x7f51aa74, new String[]{"Items", "ImageDisplayOptions"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BrowseCarouselCard_BrowseCarouselCardItem_descriptor */
    static final Descriptors.Descriptor f1470x908507bc = f1472x7f51aa74.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BrowseCarouselCard_BrowseCarouselCardItem_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1471x203eb23a = new GeneratedMessageV3.FieldAccessorTable(f1470x908507bc, new String[]{"OpenUriAction", "Title", "Description", "Image", "Footer"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BrowseCarouselCard_BrowseCarouselCardItem_OpenUrlAction_descriptor */
    static final Descriptors.Descriptor f1468xdfdb9280 = f1470x908507bc.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_BrowseCarouselCard_BrowseCarouselCardItem_OpenUrlAction_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1469x21d038fe = new GeneratedMessageV3.FieldAccessorTable(f1468xdfdb9280, new String[]{"Url", "UrlTypeHint"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_TableCard_descriptor */
    static final Descriptors.Descriptor f1512x32cf0870 = f1516xe8a95d6f.getNestedTypes().get(15);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_TableCard_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1513x18a6beee = new GeneratedMessageV3.FieldAccessorTable(f1512x32cf0870, new String[]{"Title", "Subtitle", "Image", "ColumnProperties", "Rows", "Buttons"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_ColumnProperties_descriptor */
    static final Descriptors.Descriptor f1482xe8ef1a25 = f1516xe8a95d6f.getNestedTypes().get(16);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_ColumnProperties_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1483xe884ba3 = new GeneratedMessageV3.FieldAccessorTable(f1482xe8ef1a25, new String[]{"Header", "HorizontalAlignment"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_TableCardRow_descriptor */
    static final Descriptors.Descriptor f1510x22d1bc72 = f1516xe8a95d6f.getNestedTypes().get(17);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_TableCardRow_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1511x2d3e50f0 = new GeneratedMessageV3.FieldAccessorTable(f1510x22d1bc72, new String[]{"Cells", "DividerAfter"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_TableCardCell_descriptor */
    static final Descriptors.Descriptor f1508xc05264ae = f1516xe8a95d6f.getNestedTypes().get(18);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_Message_TableCardCell_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1509xf3a0fd2c = new GeneratedMessageV3.FieldAccessorTable(f1508xc05264ae, new String[]{"Text"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_FollowupIntentInfo_descriptor */
    static final Descriptors.Descriptor f1460x8d4892f0 = internal_static_google_cloud_dialogflow_v2_Intent_descriptor.getNestedTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_Intent_FollowupIntentInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1461xf02dc96e = new GeneratedMessageV3.FieldAccessorTable(f1460x8d4892f0, new String[]{"FollowupIntentName", "ParentFollowupIntentName"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListIntentsRequest_descriptor */
    static final Descriptors.Descriptor f1525x16be0b7d = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListIntentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1526xf25c64fb = new GeneratedMessageV3.FieldAccessorTable(f1525x16be0b7d, new String[]{"Parent", "LanguageCode", "IntentView", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListIntentsResponse_descriptor */
    static final Descriptors.Descriptor f1527xce6f698f = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_ListIntentsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1528xf69f910d = new GeneratedMessageV3.FieldAccessorTable(f1527xce6f698f, new String[]{"Intents", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetIntentRequest_descriptor */
    static final Descriptors.Descriptor f1456x6d4a7736 = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_GetIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1457xbcc207b4 = new GeneratedMessageV3.FieldAccessorTable(f1456x6d4a7736, new String[]{"Name", "LanguageCode", "IntentView"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_CreateIntentRequest_descriptor */
    static final Descriptors.Descriptor f1452x74dc3af2 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_CreateIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1453xa6224f70 = new GeneratedMessageV3.FieldAccessorTable(f1452x74dc3af2, new String[]{"Parent", "Intent", "LanguageCode", "IntentView"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_UpdateIntentRequest_descriptor */
    static final Descriptors.Descriptor f1529x3d8f7bbf = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_UpdateIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1530xe262733d = new GeneratedMessageV3.FieldAccessorTable(f1529x3d8f7bbf, new String[]{"Intent", "LanguageCode", "UpdateMask", "IntentView"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteIntentRequest_descriptor */
    static final Descriptors.Descriptor f1454xd775c2a1 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_DeleteIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1455xd10cb81f = new GeneratedMessageV3.FieldAccessorTable(f1454xd775c2a1, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateIntentsRequest_descriptor */
    static final Descriptors.Descriptor f1448x7e21b42e = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateIntentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1449xb2e8ccac = new GeneratedMessageV3.FieldAccessorTable(f1448x7e21b42e, new String[]{"Parent", "IntentBatchUri", "IntentBatchInline", "LanguageCode", "UpdateMask", "IntentView", "IntentBatch"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateIntentsResponse_descriptor */
    static final Descriptors.Descriptor f1450x5380d6fe = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchUpdateIntentsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1451x47a01f7c = new GeneratedMessageV3.FieldAccessorTable(f1450x5380d6fe, new String[]{"Intents"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchDeleteIntentsRequest_descriptor */
    static final Descriptors.Descriptor f1446x2104498c = getDescriptor().getMessageTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_BatchDeleteIntentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1447x9987240a = new GeneratedMessageV3.FieldAccessorTable(f1446x2104498c, new String[]{"Parent", "Intents"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2_IntentBatch_descriptor */
    static final Descriptors.Descriptor f1458x3d61a64b = getDescriptor().getMessageTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2_IntentBatch_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1459x3fd751c9 = new GeneratedMessageV3.FieldAccessorTable(f1458x3d61a64b, new String[]{"Intents"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private IntentProto() {
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
        AudioConfigProto.getDescriptor();
        ContextProto.getDescriptor();
        OperationsProto.getDescriptor();
        DurationProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        StructProto.getDescriptor();
    }
}
