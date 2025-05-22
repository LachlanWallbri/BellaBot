package com.google.cloud.dialogflow.v2beta1;

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
/* loaded from: classes3.dex */
public final class IntentProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n,google/cloud/dialogflow/v2beta1/intent.proto\u0012\u001fgoogle.cloud.dialogflow.v2beta1\u001a\u001cgoogle/api/annotations.proto\u001a\u001fgoogle/api/field_behavior.proto\u001a\u0019google/api/resource.proto\u001a2google/cloud/dialogflow/v2beta1/audio_config.proto\u001a-google/cloud/dialogflow/v2beta1/context.proto\u001a#google/longrunning/operations.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a google/protobuf/field_mask.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u0017google/api/client.proto\"\u008cN\n\u0006Intent\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012K\n\rwebhook_state\u0018\u0006 \u0001(\u000e24.google.cloud.dialogflow.v2beta1.Intent.WebhookState\u0012\u0010\n\bpriority\u0018\u0003 \u0001(\u0005\u0012\u0013\n\u000bis_fallback\u0018\u0004 \u0001(\b\u0012\u0016\n\nml_enabled\u0018\u0005 \u0001(\bB\u0002\u0018\u0001\u0012\u0013\n\u000bml_disabled\u0018\u0013 \u0001(\b\u0012\u0017\n\u000fend_interaction\u0018\u0015 \u0001(\b\u0012\u001b\n\u0013input_context_names\u0018\u0007 \u0003(\t\u0012\u000e\n\u0006events\u0018\b \u0003(\t\u0012P\n\u0010training_phrases\u0018\t \u0003(\u000b26.google.cloud.dialogflow.v2beta1.Intent.TrainingPhrase\u0012\u000e\n\u0006action\u0018\n \u0001(\t\u0012A\n\u000foutput_contexts\u0018\u000b \u0003(\u000b2(.google.cloud.dialogflow.v2beta1.Context\u0012\u0016\n\u000ereset_contexts\u0018\f \u0001(\b\u0012E\n\nparameters\u0018\r \u0003(\u000b21.google.cloud.dialogflow.v2beta1.Intent.Parameter\u0012A\n\bmessages\u0018\u000e \u0003(\u000b2/.google.cloud.dialogflow.v2beta1.Intent.Message\u0012\\\n\u001adefault_response_platforms\u0018\u000f \u0003(\u000e28.google.cloud.dialogflow.v2beta1.Intent.Message.Platform\u0012!\n\u0019root_followup_intent_name\u0018\u0010 \u0001(\t\u0012#\n\u001bparent_followup_intent_name\u0018\u0011 \u0001(\t\u0012X\n\u0014followup_intent_info\u0018\u0012 \u0003(\u000b2:.google.cloud.dialogflow.v2beta1.Intent.FollowupIntentInfo\u001aÝ\u0002\n\u000eTrainingPhrase\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012I\n\u0004type\u0018\u0002 \u0001(\u000e2;.google.cloud.dialogflow.v2beta1.Intent.TrainingPhrase.Type\u0012J\n\u0005parts\u0018\u0003 \u0003(\u000b2;.google.cloud.dialogflow.v2beta1.Intent.TrainingPhrase.Part\u0012\u0019\n\u0011times_added_count\u0018\u0004 \u0001(\u0005\u001aN\n\u0004Part\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bentity_type\u0018\u0002 \u0001(\t\u0012\r\n\u0005alias\u0018\u0003 \u0001(\t\u0012\u0014\n\fuser_defined\u0018\u0004 \u0001(\b\";\n\u0004Type\u0012\u0014\n\u0010TYPE_UNSPECIFIED\u0010\u0000\u0012\u000b\n\u0007EXAMPLE\u0010\u0001\u0012\u0010\n\bTEMPLATE\u0010\u0002\u001a\u0002\b\u0001\u001a¬\u0001\n\tParameter\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012\r\n\u0005value\u0018\u0003 \u0001(\t\u0012\u0015\n\rdefault_value\u0018\u0004 \u0001(\t\u0012 \n\u0018entity_type_display_name\u0018\u0005 \u0001(\t\u0012\u0011\n\tmandatory\u0018\u0006 \u0001(\b\u0012\u000f\n\u0007prompts\u0018\u0007 \u0003(\t\u0012\u000f\n\u0007is_list\u0018\b \u0001(\b\u001a½@\n\u0007Message\u0012D\n\u0004text\u0018\u0001 \u0001(\u000b24.google.cloud.dialogflow.v2beta1.Intent.Message.TextH\u0000\u0012F\n\u0005image\u0018\u0002 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.Intent.Message.ImageH\u0000\u0012U\n\rquick_replies\u0018\u0003 \u0001(\u000b2<.google.cloud.dialogflow.v2beta1.Intent.Message.QuickRepliesH\u0000\u0012D\n\u0004card\u0018\u0004 \u0001(\u000b24.google.cloud.dialogflow.v2beta1.Intent.Message.CardH\u0000\u0012*\n\u0007payload\u0018\u0005 \u0001(\u000b2\u0017.google.protobuf.StructH\u0000\u0012[\n\u0010simple_responses\u0018\u0007 \u0001(\u000b2?.google.cloud.dialogflow.v2beta1.Intent.Message.SimpleResponsesH\u0000\u0012O\n\nbasic_card\u0018\b \u0001(\u000b29.google.cloud.dialogflow.v2beta1.Intent.Message.BasicCardH\u0000\u0012R\n\u000bsuggestions\u0018\t \u0001(\u000b2;.google.cloud.dialogflow.v2beta1.Intent.Message.SuggestionsH\u0000\u0012`\n\u0013link_out_suggestion\u0018\n \u0001(\u000b2A.google.cloud.dialogflow.v2beta1.Intent.Message.LinkOutSuggestionH\u0000\u0012Q\n\u000blist_select\u0018\u000b \u0001(\u000b2:.google.cloud.dialogflow.v2beta1.Intent.Message.ListSelectH\u0000\u0012Y\n\u000fcarousel_select\u0018\f \u0001(\u000b2>.google.cloud.dialogflow.v2beta1.Intent.Message.CarouselSelectH\u0000\u0012b\n\u0014telephony_play_audio\u0018\r \u0001(\u000b2B.google.cloud.dialogflow.v2beta1.Intent.Message.TelephonyPlayAudioH\u0000\u0012p\n\u001btelephony_synthesize_speech\u0018\u000e \u0001(\u000b2I.google.cloud.dialogflow.v2beta1.Intent.Message.TelephonySynthesizeSpeechH\u0000\u0012h\n\u0017telephony_transfer_call\u0018\u000f \u0001(\u000b2E.google.cloud.dialogflow.v2beta1.Intent.Message.TelephonyTransferCallH\u0000\u0012K\n\brbm_text\u0018\u0012 \u0001(\u000b27.google.cloud.dialogflow.v2beta1.Intent.Message.RbmTextH\u0000\u0012e\n\u0018rbm_standalone_rich_card\u0018\u0013 \u0001(\u000b2A.google.cloud.dialogflow.v2beta1.Intent.Message.RbmStandaloneCardH\u0000\u0012a\n\u0016rbm_carousel_rich_card\u0018\u0014 \u0001(\u000b2?.google.cloud.dialogflow.v2beta1.Intent.Message.RbmCarouselCardH\u0000\u0012b\n\u0014browse_carousel_card\u0018\u0016 \u0001(\u000b2B.google.cloud.dialogflow.v2beta1.Intent.Message.BrowseCarouselCardH\u0000\u0012O\n\ntable_card\u0018\u0017 \u0001(\u000b29.google.cloud.dialogflow.v2beta1.Intent.Message.TableCardH\u0000\u0012U\n\rmedia_content\u0018\u0018 \u0001(\u000b2<.google.cloud.dialogflow.v2beta1.Intent.Message.MediaContentH\u0000\u0012J\n\bplatform\u0018\u0006 \u0001(\u000e28.google.cloud.dialogflow.v2beta1.Intent.Message.Platform\u001a\u0014\n\u0004Text\u0012\f\n\u0004text\u0018\u0001 \u0003(\t\u001a6\n\u0005Image\u0012\u0011\n\timage_uri\u0018\u0001 \u0001(\t\u0012\u001a\n\u0012accessibility_text\u0018\u0002 \u0001(\t\u001a4\n\fQuickReplies\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012\u0015\n\rquick_replies\u0018\u0002 \u0003(\t\u001a²\u0001\n\u0004Card\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012\u0010\n\bsubtitle\u0018\u0002 \u0001(\t\u0012\u0011\n\timage_uri\u0018\u0003 \u0001(\t\u0012L\n\u0007buttons\u0018\u0004 \u0003(\u000b2;.google.cloud.dialogflow.v2beta1.Intent.Message.Card.Button\u001a(\n\u0006Button\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\u0012\u0010\n\bpostback\u0018\u0002 \u0001(\t\u001aL\n\u000eSimpleResponse\u0012\u0016\n\u000etext_to_speech\u0018\u0001 \u0001(\t\u0012\f\n\u0004ssml\u0018\u0002 \u0001(\t\u0012\u0014\n\fdisplay_text\u0018\u0003 \u0001(\t\u001ak\n\u000fSimpleResponses\u0012X\n\u0010simple_responses\u0018\u0001 \u0003(\u000b2>.google.cloud.dialogflow.v2beta1.Intent.Message.SimpleResponse\u001aþ\u0002\n\tBasicCard\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012\u0010\n\bsubtitle\u0018\u0002 \u0001(\t\u0012\u0016\n\u000eformatted_text\u0018\u0003 \u0001(\t\u0012D\n\u0005image\u0018\u0004 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.Intent.Message.Image\u0012Q\n\u0007buttons\u0018\u0005 \u0003(\u000b2@.google.cloud.dialogflow.v2beta1.Intent.Message.BasicCard.Button\u001a\u009e\u0001\n\u0006Button\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012g\n\u000fopen_uri_action\u0018\u0002 \u0001(\u000b2N.google.cloud.dialogflow.v2beta1.Intent.Message.BasicCard.Button.OpenUriAction\u001a\u001c\n\rOpenUriAction\u0012\u000b\n\u0003uri\u0018\u0001 \u0001(\t\u001a\u001b\n\nSuggestion\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u001a^\n\u000bSuggestions\u0012O\n\u000bsuggestions\u0018\u0001 \u0003(\u000b2:.google.cloud.dialogflow.v2beta1.Intent.Message.Suggestion\u001a:\n\u0011LinkOutSuggestion\u0012\u0018\n\u0010destination_name\u0018\u0001 \u0001(\t\u0012\u000b\n\u0003uri\u0018\u0002 \u0001(\t\u001aÃ\u0002\n\nListSelect\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012N\n\u0005items\u0018\u0002 \u0003(\u000b2?.google.cloud.dialogflow.v2beta1.Intent.Message.ListSelect.Item\u0012\u0015\n\bsubtitle\u0018\u0003 \u0001(\tB\u0003àA\u0001\u001a¾\u0001\n\u0004Item\u0012L\n\u0004info\u0018\u0001 \u0001(\u000b2>.google.cloud.dialogflow.v2beta1.Intent.Message.SelectItemInfo\u0012\r\n\u0005title\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\u0012D\n\u0005image\u0018\u0004 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.Intent.Message.Image\u001a¥\u0002\n\u000eCarouselSelect\u0012R\n\u0005items\u0018\u0001 \u0003(\u000b2C.google.cloud.dialogflow.v2beta1.Intent.Message.CarouselSelect.Item\u001a¾\u0001\n\u0004Item\u0012L\n\u0004info\u0018\u0001 \u0001(\u000b2>.google.cloud.dialogflow.v2beta1.Intent.Message.SelectItemInfo\u0012\r\n\u0005title\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\u0012D\n\u0005image\u0018\u0004 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.Intent.Message.Image\u001a/\n\u000eSelectItemInfo\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\u0010\n\bsynonyms\u0018\u0002 \u0003(\t\u001a'\n\u0012TelephonyPlayAudio\u0012\u0011\n\taudio_uri\u0018\u0001 \u0001(\t\u001aE\n\u0019TelephonySynthesizeSpeech\u0012\u000e\n\u0004text\u0018\u0001 \u0001(\tH\u0000\u0012\u000e\n\u0004ssml\u0018\u0002 \u0001(\tH\u0000B\b\n\u0006source\u001a-\n\u0015TelephonyTransferCall\u0012\u0014\n\fphone_number\u0018\u0001 \u0001(\t\u001an\n\u0007RbmText\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\u0012U\n\u000erbm_suggestion\u0018\u0002 \u0003(\u000b2=.google.cloud.dialogflow.v2beta1.Intent.Message.RbmSuggestion\u001a\u0087\u0002\n\u000fRbmCarouselCard\u0012]\n\ncard_width\u0018\u0001 \u0001(\u000e2I.google.cloud.dialogflow.v2beta1.Intent.Message.RbmCarouselCard.CardWidth\u0012U\n\rcard_contents\u0018\u0002 \u0003(\u000b2>.google.cloud.dialogflow.v2beta1.Intent.Message.RbmCardContent\">\n\tCardWidth\u0012\u001a\n\u0016CARD_WIDTH_UNSPECIFIED\u0010\u0000\u0012\t\n\u0005SMALL\u0010\u0001\u0012\n\n\u0006MEDIUM\u0010\u0002\u001a\u0082\u0004\n\u0011RbmStandaloneCard\u0012k\n\u0010card_orientation\u0018\u0001 \u0001(\u000e2Q.google.cloud.dialogflow.v2beta1.Intent.Message.RbmStandaloneCard.CardOrientation\u0012|\n\u0019thumbnail_image_alignment\u0018\u0002 \u0001(\u000e2Y.google.cloud.dialogflow.v2beta1.Intent.Message.RbmStandaloneCard.ThumbnailImageAlignment\u0012T\n\fcard_content\u0018\u0003 \u0001(\u000b2>.google.cloud.dialogflow.v2beta1.Intent.Message.RbmCardContent\"Q\n\u000fCardOrientation\u0012 \n\u001cCARD_ORIENTATION_UNSPECIFIED\u0010\u0000\u0012\u000e\n\nHORIZONTAL\u0010\u0001\u0012\f\n\bVERTICAL\u0010\u0002\"Y\n\u0017ThumbnailImageAlignment\u0012)\n%THUMBNAIL_IMAGE_ALIGNMENT_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004LEFT\u0010\u0001\u0012\t\n\u0005RIGHT\u0010\u0002\u001a¹\u0003\n\u000eRbmCardContent\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012V\n\u0005media\u0018\u0003 \u0001(\u000b2G.google.cloud.dialogflow.v2beta1.Intent.Message.RbmCardContent.RbmMedia\u0012R\n\u000bsuggestions\u0018\u0004 \u0003(\u000b2=.google.cloud.dialogflow.v2beta1.Intent.Message.RbmSuggestion\u001aÖ\u0001\n\bRbmMedia\u0012\u0010\n\bfile_uri\u0018\u0001 \u0001(\t\u0012\u0015\n\rthumbnail_uri\u0018\u0002 \u0001(\t\u0012^\n\u0006height\u0018\u0003 \u0001(\u000e2N.google.cloud.dialogflow.v2beta1.Intent.Message.RbmCardContent.RbmMedia.Height\"A\n\u0006Height\u0012\u0016\n\u0012HEIGHT_UNSPECIFIED\u0010\u0000\u0012\t\n\u0005SHORT\u0010\u0001\u0012\n\n\u0006MEDIUM\u0010\u0002\u0012\b\n\u0004TALL\u0010\u0003\u001aÇ\u0001\n\rRbmSuggestion\u0012R\n\u0005reply\u0018\u0001 \u0001(\u000b2A.google.cloud.dialogflow.v2beta1.Intent.Message.RbmSuggestedReplyH\u0000\u0012T\n\u0006action\u0018\u0002 \u0001(\u000b2B.google.cloud.dialogflow.v2beta1.Intent.Message.RbmSuggestedActionH\u0000B\f\n\nsuggestion\u001a8\n\u0011RbmSuggestedReply\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\u0012\u0015\n\rpostback_data\u0018\u0002 \u0001(\t\u001a\u009b\u0004\n\u0012RbmSuggestedAction\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\u0012\u0015\n\rpostback_data\u0018\u0002 \u0001(\t\u0012i\n\u0004dial\u0018\u0003 \u0001(\u000b2Y.google.cloud.dialogflow.v2beta1.Intent.Message.RbmSuggestedAction.RbmSuggestedActionDialH\u0000\u0012p\n\bopen_url\u0018\u0004 \u0001(\u000b2\\.google.cloud.dialogflow.v2beta1.Intent.Message.RbmSuggestedAction.RbmSuggestedActionOpenUriH\u0000\u0012|\n\u000eshare_location\u0018\u0005 \u0001(\u000b2b.google.cloud.dialogflow.v2beta1.Intent.Message.RbmSuggestedAction.RbmSuggestedActionShareLocationH\u0000\u001a.\n\u0016RbmSuggestedActionDial\u0012\u0014\n\fphone_number\u0018\u0001 \u0001(\t\u001a(\n\u0019RbmSuggestedActionOpenUri\u0012\u000b\n\u0003uri\u0018\u0001 \u0001(\t\u001a!\n\u001fRbmSuggestedActionShareLocationB\b\n\u0006action\u001a\u008e\u0004\n\fMediaContent\u0012b\n\nmedia_type\u0018\u0001 \u0001(\u000e2N.google.cloud.dialogflow.v2beta1.Intent.Message.MediaContent.ResponseMediaType\u0012g\n\rmedia_objects\u0018\u0002 \u0003(\u000b2P.google.cloud.dialogflow.v2beta1.Intent.Message.MediaContent.ResponseMediaObject\u001aë\u0001\n\u0013ResponseMediaObject\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012L\n\u000blarge_image\u0018\u0003 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.Intent.Message.ImageH\u0000\u0012E\n\u0004icon\u0018\u0004 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.Intent.Message.ImageH\u0000\u0012\u0013\n\u000bcontent_url\u0018\u0005 \u0001(\tB\u0007\n\u0005image\"C\n\u0011ResponseMediaType\u0012#\n\u001fRESPONSE_MEDIA_TYPE_UNSPECIFIED\u0010\u0000\u0012\t\n\u0005AUDIO\u0010\u0001\u001a\u0080\u0007\n\u0012BrowseCarouselCard\u0012h\n\u0005items\u0018\u0001 \u0003(\u000b2Y.google.cloud.dialogflow.v2beta1.Intent.Message.BrowseCarouselCard.BrowseCarouselCardItem\u0012u\n\u0015image_display_options\u0018\u0002 \u0001(\u000e2V.google.cloud.dialogflow.v2beta1.Intent.Message.BrowseCarouselCard.ImageDisplayOptions\u001a\u0090\u0004\n\u0016BrowseCarouselCardItem\u0012\u0080\u0001\n\u000fopen_uri_action\u0018\u0001 \u0001(\u000b2g.google.cloud.dialogflow.v2beta1.Intent.Message.BrowseCarouselCard.BrowseCarouselCardItem.OpenUrlAction\u0012\r\n\u0005title\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\u0012D\n\u0005image\u0018\u0004 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.Intent.Message.Image\u0012\u000e\n\u0006footer\u0018\u0005 \u0001(\t\u001aø\u0001\n\rOpenUrlAction\u0012\u000b\n\u0003url\u0018\u0001 \u0001(\t\u0012\u008a\u0001\n\rurl_type_hint\u0018\u0003 \u0001(\u000e2s.google.cloud.dialogflow.v2beta1.Intent.Message.BrowseCarouselCard.BrowseCarouselCardItem.OpenUrlAction.UrlTypeHint\"M\n\u000bUrlTypeHint\u0012\u001d\n\u0019URL_TYPE_HINT_UNSPECIFIED\u0010\u0000\u0012\u000e\n\nAMP_ACTION\u0010\u0001\u0012\u000f\n\u000bAMP_CONTENT\u0010\u0002\"v\n\u0013ImageDisplayOptions\u0012%\n!IMAGE_DISPLAY_OPTIONS_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004GRAY\u0010\u0001\u0012\t\n\u0005WHITE\u0010\u0002\u0012\u000b\n\u0007CROPPED\u0010\u0003\u0012\u0016\n\u0012BLURRED_BACKGROUND\u0010\u0004\u001aî\u0002\n\tTableCard\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012\u0010\n\bsubtitle\u0018\u0002 \u0001(\t\u0012D\n\u0005image\u0018\u0003 \u0001(\u000b25.google.cloud.dialogflow.v2beta1.Intent.Message.Image\u0012[\n\u0011column_properties\u0018\u0004 \u0003(\u000b2@.google.cloud.dialogflow.v2beta1.Intent.Message.ColumnProperties\u0012J\n\u0004rows\u0018\u0005 \u0003(\u000b2<.google.cloud.dialogflow.v2beta1.Intent.Message.TableCardRow\u0012Q\n\u0007buttons\u0018\u0006 \u0003(\u000b2@.google.cloud.dialogflow.v2beta1.Intent.Message.BasicCard.Button\u001aú\u0001\n\u0010ColumnProperties\u0012\u000e\n\u0006header\u0018\u0001 \u0001(\t\u0012r\n\u0014horizontal_alignment\u0018\u0002 \u0001(\u000e2T.google.cloud.dialogflow.v2beta1.Intent.Message.ColumnProperties.HorizontalAlignment\"b\n\u0013HorizontalAlignment\u0012$\n HORIZONTAL_ALIGNMENT_UNSPECIFIED\u0010\u0000\u0012\u000b\n\u0007LEADING\u0010\u0001\u0012\n\n\u0006CENTER\u0010\u0002\u0012\f\n\bTRAILING\u0010\u0003\u001as\n\fTableCardRow\u0012L\n\u0005cells\u0018\u0001 \u0003(\u000b2=.google.cloud.dialogflow.v2beta1.Intent.Message.TableCardCell\u0012\u0015\n\rdivider_after\u0018\u0002 \u0001(\b\u001a\u001d\n\rTableCardCell\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\"¯\u0001\n\bPlatform\u0012\u0018\n\u0014PLATFORM_UNSPECIFIED\u0010\u0000\u0012\f\n\bFACEBOOK\u0010\u0001\u0012\t\n\u0005SLACK\u0010\u0002\u0012\f\n\bTELEGRAM\u0010\u0003\u0012\u0007\n\u0003KIK\u0010\u0004\u0012\t\n\u0005SKYPE\u0010\u0005\u0012\b\n\u0004LINE\u0010\u0006\u0012\t\n\u0005VIBER\u0010\u0007\u0012\u0015\n\u0011ACTIONS_ON_GOOGLE\u0010\b\u0012\r\n\tTELEPHONY\u0010\n\u0012\u0013\n\u000fGOOGLE_HANGOUTS\u0010\u000bB\t\n\u0007message\u001aW\n\u0012FollowupIntentInfo\u0012\u001c\n\u0014followup_intent_name\u0018\u0001 \u0001(\t\u0012#\n\u001bparent_followup_intent_name\u0018\u0002 \u0001(\t\"t\n\fWebhookState\u0012\u001d\n\u0019WEBHOOK_STATE_UNSPECIFIED\u0010\u0000\u0012\u0019\n\u0015WEBHOOK_STATE_ENABLED\u0010\u0001\u0012*\n&WEBHOOK_STATE_ENABLED_FOR_SLOT_FILLING\u0010\u0002:\u0091\u0001êA\u008d\u0001\n dialogflow.googleapis.com/Intent\u0012)projects/{project}/agent/intents/{intent}\u0012>projects/{project}/locations/{location}/agent/intents/{intent}\"¤\u0001\n\u0012ListIntentsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u0002 \u0001(\t\u0012@\n\u000bintent_view\u0018\u0003 \u0001(\u000e2+.google.cloud.dialogflow.v2beta1.IntentView\u0012\u0011\n\tpage_size\u0018\u0004 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0005 \u0001(\t\"h\n\u0013ListIntentsResponse\u00128\n\u0007intents\u0018\u0001 \u0003(\u000b2'.google.cloud.dialogflow.v2beta1.Intent\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"y\n\u0010GetIntentRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u0002 \u0001(\t\u0012@\n\u000bintent_view\u0018\u0003 \u0001(\u000e2+.google.cloud.dialogflow.v2beta1.IntentView\"·\u0001\n\u0013CreateIntentRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u00127\n\u0006intent\u0018\u0002 \u0001(\u000b2'.google.cloud.dialogflow.v2beta1.Intent\u0012\u0015\n\rlanguage_code\u0018\u0003 \u0001(\t\u0012@\n\u000bintent_view\u0018\u0004 \u0001(\u000e2+.google.cloud.dialogflow.v2beta1.IntentView\"Ø\u0001\n\u0013UpdateIntentRequest\u00127\n\u0006intent\u0018\u0001 \u0001(\u000b2'.google.cloud.dialogflow.v2beta1.Intent\u0012\u0015\n\rlanguage_code\u0018\u0002 \u0001(\t\u0012/\n\u000bupdate_mask\u0018\u0003 \u0001(\u000b2\u001a.google.protobuf.FieldMask\u0012@\n\u000bintent_view\u0018\u0004 \u0001(\u000e2+.google.cloud.dialogflow.v2beta1.IntentView\"#\n\u0013DeleteIntentRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"®\u0002\n\u0019BatchUpdateIntentsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u0012\u001a\n\u0010intent_batch_uri\u0018\u0002 \u0001(\tH\u0000\u0012K\n\u0013intent_batch_inline\u0018\u0003 \u0001(\u000b2,.google.cloud.dialogflow.v2beta1.IntentBatchH\u0000\u0012\u0015\n\rlanguage_code\u0018\u0004 \u0001(\t\u0012/\n\u000bupdate_mask\u0018\u0005 \u0001(\u000b2\u001a.google.protobuf.FieldMask\u0012@\n\u000bintent_view\u0018\u0006 \u0001(\u000e2+.google.cloud.dialogflow.v2beta1.IntentViewB\u000e\n\fintent_batch\"V\n\u001aBatchUpdateIntentsResponse\u00128\n\u0007intents\u0018\u0001 \u0003(\u000b2'.google.cloud.dialogflow.v2beta1.Intent\"e\n\u0019BatchDeleteIntentsRequest\u0012\u000e\n\u0006parent\u0018\u0001 \u0001(\t\u00128\n\u0007intents\u0018\u0002 \u0003(\u000b2'.google.cloud.dialogflow.v2beta1.Intent\"G\n\u000bIntentBatch\u00128\n\u0007intents\u0018\u0001 \u0003(\u000b2'.google.cloud.dialogflow.v2beta1.Intent*?\n\nIntentView\u0012\u001b\n\u0017INTENT_VIEW_UNSPECIFIED\u0010\u0000\u0012\u0014\n\u0010INTENT_VIEW_FULL\u0010\u00012ý\r\n\u0007Intents\u0012æ\u0001\n\u000bListIntents\u00123.google.cloud.dialogflow.v2beta1.ListIntentsRequest\u001a4.google.cloud.dialogflow.v2beta1.ListIntentsResponse\"l\u0082Óä\u0093\u0002f\u0012*/v2beta1/{parent=projects/*/agent}/intentsZ8\u00126/v2beta1/{parent=projects/*/locations/*/agent}/intents\u0012Õ\u0001\n\tGetIntent\u00121.google.cloud.dialogflow.v2beta1.GetIntentRequest\u001a'.google.cloud.dialogflow.v2beta1.Intent\"l\u0082Óä\u0093\u0002f\u0012*/v2beta1/{name=projects/*/agent/intents/*}Z8\u00126/v2beta1/{name=projects/*/locations/*/agent/intents/*}\u0012ë\u0001\n\fCreateIntent\u00124.google.cloud.dialogflow.v2beta1.CreateIntentRequest\u001a'.google.cloud.dialogflow.v2beta1.Intent\"|\u0082Óä\u0093\u0002v\"*/v2beta1/{parent=projects/*/agent}/intents:\u0006intentZ@\"6/v2beta1/{parent=projects/*/locations/*/agent}/intents:\u0006intent\u0012û\u0001\n\fUpdateIntent\u00124.google.cloud.dialogflow.v2beta1.UpdateIntentRequest\u001a'.google.cloud.dialogflow.v2beta1.Intent\"\u008b\u0001\u0082Óä\u0093\u0002\u0084\u000121/v2beta1/{intent.name=projects/*/agent/intents/*}:\u0006intentZG2=/v2beta1/{intent.name=projects/*/locations/*/agent/intents/*}:\u0006intent\u0012Ê\u0001\n\fDeleteIntent\u00124.google.cloud.dialogflow.v2beta1.DeleteIntentRequest\u001a\u0016.google.protobuf.Empty\"l\u0082Óä\u0093\u0002f**/v2beta1/{name=projects/*/agent/intents/*}Z8*6/v2beta1/{name=projects/*/locations/*/agent/intents/*}\u0012ý\u0001\n\u0012BatchUpdateIntents\u0012:.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsRequest\u001a\u001d.google.longrunning.Operation\"\u008b\u0001\u0082Óä\u0093\u0002\u0084\u0001\"6/v2beta1/{parent=projects/*/agent}/intents:batchUpdate:\u0001*ZG\"B/v2beta1/{parent=projects/*/locations/*/agent}/intents:batchUpdate:\u0001*\u0012ý\u0001\n\u0012BatchDeleteIntents\u0012:.google.cloud.dialogflow.v2beta1.BatchDeleteIntentsRequest\u001a\u001d.google.longrunning.Operation\"\u008b\u0001\u0082Óä\u0093\u0002\u0084\u0001\"6/v2beta1/{parent=projects/*/agent}/intents:batchDelete:\u0001*ZG\"B/v2beta1/{parent=projects/*/locations/*/agent}/intents:batchDelete:\u0001*\u001axÊA\u0019dialogflow.googleapis.comÒAYhttps://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/dialogflowB©\u0001\n#com.google.cloud.dialogflow.v2beta1B\u000bIntentProtoP\u0001ZIgoogle.golang.org/genproto/googleapis/cloud/dialogflow/v2beta1;dialogflowø\u0001\u0001¢\u0002\u0002DFª\u0002\u001fGoogle.Cloud.Dialogflow.V2beta1b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), FieldBehaviorProto.getDescriptor(), ResourceProto.getDescriptor(), AudioConfigProto.getDescriptor(), ContextProto.getDescriptor(), OperationsProto.getDescriptor(), DurationProto.getDescriptor(), EmptyProto.getDescriptor(), FieldMaskProto.getDescriptor(), StructProto.getDescriptor(), ClientProto.getDescriptor()});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_descriptor */
    static final Descriptors.Descriptor f1797xe5ca8b10 = getDescriptor().getMessageTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1798xbc5a18e = new GeneratedMessageV3.FieldAccessorTable(f1797xe5ca8b10, new String[]{"Name", "DisplayName", "WebhookState", "Priority", "IsFallback", "MlEnabled", "MlDisabled", "EndInteraction", "InputContextNames", "Events", "TrainingPhrases", JsonDocumentFields.ACTION, "OutputContexts", "ResetContexts", "Parameters", "Messages", "DefaultResponsePlatforms", "RootFollowupIntentName", "ParentFollowupIntentName", "FollowupIntentInfo"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_TrainingPhrase_descriptor */
    static final Descriptors.Descriptor f1795xcfcf651a = f1797xe5ca8b10.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_TrainingPhrase_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1796xc2fcd198 = new GeneratedMessageV3.FieldAccessorTable(f1795xcfcf651a, new String[]{"Name", "Type", "Parts", "TimesAddedCount"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_TrainingPhrase_Part_descriptor */
    static final Descriptors.Descriptor f1793x390a5850 = f1795xcfcf651a.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_TrainingPhrase_Part_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1794x8e042ece = new GeneratedMessageV3.FieldAccessorTable(f1793x390a5850, new String[]{"Text", "EntityType", "Alias", "UserDefined"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Parameter_descriptor */
    static final Descriptors.Descriptor f1791x13735846 = f1797xe5ca8b10.getNestedTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Parameter_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1792x24c0d8c4 = new GeneratedMessageV3.FieldAccessorTable(f1791x13735846, new String[]{"Name", "DisplayName", "Value", "DefaultValue", "EntityTypeDisplayName", "Mandatory", "Prompts", "IsList"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_descriptor */
    static final Descriptors.Descriptor f1789x55dff168 = f1797xe5ca8b10.getNestedTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1790xd29b2fe6 = new GeneratedMessageV3.FieldAccessorTable(f1789x55dff168, new String[]{"Text", "Image", "QuickReplies", "Card", "Payload", "SimpleResponses", "BasicCard", "Suggestions", "LinkOutSuggestion", "ListSelect", "CarouselSelect", "TelephonyPlayAudio", "TelephonySynthesizeSpeech", "TelephonyTransferCall", "RbmText", "RbmStandaloneRichCard", "RbmCarouselRichCard", "BrowseCarouselCard", "TableCard", "MediaContent", DataRecordKey.PLATFORM, "Message"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Text_descriptor */
    static final Descriptors.Descriptor f1787xee824348 = f1789x55dff168.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Text_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1788xc3da1c6 = new GeneratedMessageV3.FieldAccessorTable(f1787xee824348, new String[]{"Text"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Image_descriptor */
    static final Descriptors.Descriptor f1729x40ffec2c = f1789x55dff168.getNestedTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Image_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1730x878626aa = new GeneratedMessageV3.FieldAccessorTable(f1729x40ffec2c, new String[]{"ImageUri", "AccessibilityText"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_QuickReplies_descriptor */
    static final Descriptors.Descriptor f1741xc028fa5a = f1789x55dff168.getNestedTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_QuickReplies_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1742x300d26d8 = new GeneratedMessageV3.FieldAccessorTable(f1741xc028fa5a, new String[]{"Title", "QuickReplies"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Card_descriptor */
    static final Descriptors.Descriptor f1721x5d4c9185 = f1789x55dff168.getNestedTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Card_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1722x3f286303 = new GeneratedMessageV3.FieldAccessorTable(f1721x5d4c9185, new String[]{"Title", "Subtitle", "ImageUri", "Buttons"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Card_Button_descriptor */
    static final Descriptors.Descriptor f1719xa1a98ba6 = f1721x5d4c9185.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Card_Button_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1720x58bdac24 = new GeneratedMessageV3.FieldAccessorTable(f1719xa1a98ba6, new String[]{"Text", "Postback"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_SimpleResponse_descriptor */
    static final Descriptors.Descriptor f1767xc25c07e2 = f1789x55dff168.getNestedTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_SimpleResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1768x8d3e2c60 = new GeneratedMessageV3.FieldAccessorTable(f1767xc25c07e2, new String[]{"TextToSpeech", "Ssml", "DisplayText"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_SimpleResponses_descriptor */
    static final Descriptors.Descriptor f1769x9e136e67 = f1789x55dff168.getNestedTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_SimpleResponses_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1770xdbd3de5 = new GeneratedMessageV3.FieldAccessorTable(f1769x9e136e67, new String[]{"SimpleResponses"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BasicCard_descriptor */
    static final Descriptors.Descriptor f1711x48c954c9 = f1789x55dff168.getNestedTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BasicCard_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1712x715fa247 = new GeneratedMessageV3.FieldAccessorTable(f1711x48c954c9, new String[]{"Title", "Subtitle", "FormattedText", "Image", "Buttons"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BasicCard_Button_descriptor */
    static final Descriptors.Descriptor f1709x2fab53e2 = f1711x48c954c9.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BasicCard_Button_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1710x8c817860 = new GeneratedMessageV3.FieldAccessorTable(f1709x2fab53e2, new String[]{"Title", "OpenUriAction"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BasicCard_Button_OpenUriAction_descriptor */
    static final Descriptors.Descriptor f1707x30e8fdc9 = f1709x2fab53e2.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BasicCard_Button_OpenUriAction_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1708xf7c64b47 = new GeneratedMessageV3.FieldAccessorTable(f1707x30e8fdc9, new String[]{"Uri"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Suggestion_descriptor */
    static final Descriptors.Descriptor f1771x674fa0d1 = f1789x55dff168.getNestedTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Suggestion_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1772x9cfd664f = new GeneratedMessageV3.FieldAccessorTable(f1771x674fa0d1, new String[]{"Title"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Suggestions_descriptor */
    static final Descriptors.Descriptor f1773x9792f358 = f1789x55dff168.getNestedTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_Suggestions_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1774xf5e541d6 = new GeneratedMessageV3.FieldAccessorTable(f1773x9792f358, new String[]{"Suggestions"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_LinkOutSuggestion_descriptor */
    static final Descriptors.Descriptor f1731x184a9dcf = f1789x55dff168.getNestedTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_LinkOutSuggestion_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1732x2d22854d = new GeneratedMessageV3.FieldAccessorTable(f1731x184a9dcf, new String[]{"DestinationName", "Uri"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_ListSelect_descriptor */
    static final Descriptors.Descriptor f1735x7ab749db = f1789x55dff168.getNestedTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_ListSelect_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1736x51586559 = new GeneratedMessageV3.FieldAccessorTable(f1735x7ab749db, new String[]{"Title", "Items", "Subtitle"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_ListSelect_Item_descriptor */
    static final Descriptors.Descriptor f1733x9cbb790f = f1735x7ab749db.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_ListSelect_Item_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1734xdda4208d = new GeneratedMessageV3.FieldAccessorTable(f1733x9cbb790f, new String[]{"Info", "Title", "Description", "Image"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_CarouselSelect_descriptor */
    static final Descriptors.Descriptor f1725x11f379d9 = f1789x55dff168.getNestedTypes().get(11);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_CarouselSelect_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1726x98dbb757 = new GeneratedMessageV3.FieldAccessorTable(f1725x11f379d9, new String[]{"Items"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_CarouselSelect_Item_descriptor */
    static final Descriptors.Descriptor f1723x103397d1 = f1725x11f379d9.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_CarouselSelect_Item_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1724xb9fa5d4f = new GeneratedMessageV3.FieldAccessorTable(f1723x103397d1, new String[]{"Info", "Title", "Description", "Image"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_SelectItemInfo_descriptor */
    static final Descriptors.Descriptor f1765xf6c68a38 = f1789x55dff168.getNestedTypes().get(12);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_SelectItemInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1766x4703f8b6 = new GeneratedMessageV3.FieldAccessorTable(f1765xf6c68a38, new String[]{"Key", "Synonyms"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TelephonyPlayAudio_descriptor */
    static final Descriptors.Descriptor f1781xdfe744b = f1789x55dff168.getNestedTypes().get(13);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TelephonyPlayAudio_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1782xc5c61fc9 = new GeneratedMessageV3.FieldAccessorTable(f1781xdfe744b, new String[]{"AudioUri"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TelephonySynthesizeSpeech_descriptor */
    static final Descriptors.Descriptor f1783xd3336983 = f1789x55dff168.getNestedTypes().get(14);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TelephonySynthesizeSpeech_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1784x42e5d01 = new GeneratedMessageV3.FieldAccessorTable(f1783xd3336983, new String[]{"Text", "Ssml", "Source"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TelephonyTransferCall_descriptor */
    static final Descriptors.Descriptor f1785xe1e16ea6 = f1789x55dff168.getNestedTypes().get(15);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TelephonyTransferCall_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1786x20628f24 = new GeneratedMessageV3.FieldAccessorTable(f1785xe1e16ea6, new String[]{"PhoneNumber"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmText_descriptor */
    static final Descriptors.Descriptor f1763xa82784dd = f1789x55dff168.getNestedTypes().get(16);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmText_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1764xfce67e5b = new GeneratedMessageV3.FieldAccessorTable(f1763xa82784dd, new String[]{"Text", "RbmSuggestion"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmCarouselCard_descriptor */
    static final Descriptors.Descriptor f1747x7bfa619a = f1789x55dff168.getNestedTypes().get(17);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmCarouselCard_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1748x24a34e18 = new GeneratedMessageV3.FieldAccessorTable(f1747x7bfa619a, new String[]{"CardWidth", "CardContents"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmStandaloneCard_descriptor */
    static final Descriptors.Descriptor f1749xe1e04515 = f1789x55dff168.getNestedTypes().get(18);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmStandaloneCard_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1750xc0d78693 = new GeneratedMessageV3.FieldAccessorTable(f1749xe1e04515, new String[]{"CardOrientation", "ThumbnailImageAlignment", "CardContent"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmCardContent_descriptor */
    static final Descriptors.Descriptor f1745xdd6a8b89 = f1789x55dff168.getNestedTypes().get(19);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmCardContent_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1746x77be1907 = new GeneratedMessageV3.FieldAccessorTable(f1745xdd6a8b89, new String[]{"Title", "Description", "Media", "Suggestions"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmCardContent_RbmMedia_descriptor */
    static final Descriptors.Descriptor f1743xd682120d = f1745xdd6a8b89.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmCardContent_RbmMedia_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1744xaf38db8b = new GeneratedMessageV3.FieldAccessorTable(f1743xd682120d, new String[]{"FileUri", "ThumbnailUri", "Height"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestion_descriptor */
    static final Descriptors.Descriptor f1761xd0409ea6 = f1789x55dff168.getNestedTypes().get(20);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestion_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1762x1491bf24 = new GeneratedMessageV3.FieldAccessorTable(f1761xd0409ea6, new String[]{"Reply", JsonDocumentFields.ACTION, "Suggestion"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedReply_descriptor */
    static final Descriptors.Descriptor f1759x542ac543 = f1789x55dff168.getNestedTypes().get(21);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedReply_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1760x24edf8c1 = new GeneratedMessageV3.FieldAccessorTable(f1759x542ac543, new String[]{"Text", "PostbackData"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedAction_descriptor */
    static final Descriptors.Descriptor f1757x71eeb399 = f1789x55dff168.getNestedTypes().get(22);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedAction_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1758x3de13117 = new GeneratedMessageV3.FieldAccessorTable(f1757x71eeb399, new String[]{"Text", "PostbackData", "Dial", "OpenUrl", "ShareLocation", JsonDocumentFields.ACTION});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedAction_RbmSuggestedActionDial_descriptor */
    static final Descriptors.Descriptor f1751x45af338 = f1757x71eeb399.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedAction_RbmSuggestedActionDial_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1752x921f61b6 = new GeneratedMessageV3.FieldAccessorTable(f1751x45af338, new String[]{"PhoneNumber"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedAction_RbmSuggestedActionOpenUri_descriptor */
    static final Descriptors.Descriptor f1753x3273f732 = f1757x71eeb399.getNestedTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedAction_RbmSuggestedActionOpenUri_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1754x2f59cbb0 = new GeneratedMessageV3.FieldAccessorTable(f1753x3273f732, new String[]{"Uri"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedAction_RbmSuggestedActionShareLocation_descriptor */
    static final Descriptors.Descriptor f1755x8604fd80 = f1757x71eeb399.getNestedTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_RbmSuggestedAction_RbmSuggestedActionShareLocation_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1756x295ea3fe = new GeneratedMessageV3.FieldAccessorTable(f1755x8604fd80, new String[0]);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_MediaContent_descriptor */
    static final Descriptors.Descriptor f1739xe5d11080 = f1789x55dff168.getNestedTypes().get(23);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_MediaContent_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1740x1167b6fe = new GeneratedMessageV3.FieldAccessorTable(f1739xe5d11080, new String[]{"MediaType", "MediaObjects"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_MediaContent_ResponseMediaObject_descriptor */
    static final Descriptors.Descriptor f1737xe383ad3d = f1739xe5d11080.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_MediaContent_ResponseMediaObject_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1738x674446bb = new GeneratedMessageV3.FieldAccessorTable(f1737xe383ad3d, new String[]{"Name", "Description", "LargeImage", "Icon", "ContentUrl", "Image"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BrowseCarouselCard_descriptor */
    static final Descriptors.Descriptor f1717x64ddb9db = f1789x55dff168.getNestedTypes().get(24);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BrowseCarouselCard_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1718xa60ed559 = new GeneratedMessageV3.FieldAccessorTable(f1717x64ddb9db, new String[]{"Items", "ImageDisplayOptions"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BrowseCarouselCard_BrowseCarouselCardItem_descriptor */
    static final Descriptors.Descriptor f1715xa9789475 = f1717x64ddb9db.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BrowseCarouselCard_BrowseCarouselCardItem_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1716x285a75f3 = new GeneratedMessageV3.FieldAccessorTable(f1715xa9789475, new String[]{"OpenUriAction", "Title", "Description", "Image", "Footer"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BrowseCarouselCard_BrowseCarouselCardItem_OpenUrlAction_descriptor */
    static final Descriptors.Descriptor f1713xc139e779 = f1715xa9789475.getNestedTypes().get(0);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_BrowseCarouselCard_BrowseCarouselCardItem_OpenUrlAction_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1714x702a84f7 = new GeneratedMessageV3.FieldAccessorTable(f1713xc139e779, new String[]{"Url", "UrlTypeHint"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TableCard_descriptor */
    static final Descriptors.Descriptor f1779x399bb929 = f1789x55dff168.getNestedTypes().get(25);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TableCard_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1780xc537a6a7 = new GeneratedMessageV3.FieldAccessorTable(f1779x399bb929, new String[]{"Title", "Subtitle", "Image", "ColumnProperties", "Rows", "Buttons"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_ColumnProperties_descriptor */
    static final Descriptors.Descriptor f1727xc535d74c = f1789x55dff168.getNestedTypes().get(26);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_ColumnProperties_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1728x612ef1ca = new GeneratedMessageV3.FieldAccessorTable(f1727xc535d74c, new String[]{"Header", "HorizontalAlignment"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TableCardRow_descriptor */
    static final Descriptors.Descriptor f1777x68db2519 = f1789x55dff168.getNestedTypes().get(27);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TableCardRow_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1778xe0042297 = new GeneratedMessageV3.FieldAccessorTable(f1777x68db2519, new String[]{"Cells", "DividerAfter"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TableCardCell_descriptor */
    static final Descriptors.Descriptor f1775x3b7610e7 = f1789x55dff168.getNestedTypes().get(28);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_Message_TableCardCell_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1776x99956065 = new GeneratedMessageV3.FieldAccessorTable(f1775x3b7610e7, new String[]{"Text"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_FollowupIntentInfo_descriptor */
    static final Descriptors.Descriptor f1705x6011f957 = f1797xe5ca8b10.getNestedTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_Intent_FollowupIntentInfo_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1706xd5b9d8d5 = new GeneratedMessageV3.FieldAccessorTable(f1705x6011f957, new String[]{"FollowupIntentName", "ParentFollowupIntentName"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListIntentsRequest_descriptor */
    static final Descriptors.Descriptor f1799x1e27faf6 = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListIntentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1800x78bfcb74 = new GeneratedMessageV3.FieldAccessorTable(f1799x1e27faf6, new String[]{"Parent", "LanguageCode", "IntentView", "PageSize", "PageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListIntentsResponse_descriptor */
    static final Descriptors.Descriptor f1801xb4436936 = getDescriptor().getMessageTypes().get(2);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_ListIntentsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1802x3ca8f9b4 = new GeneratedMessageV3.FieldAccessorTable(f1801xb4436936, new String[]{"Intents", "NextPageToken"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetIntentRequest_descriptor */
    static final Descriptors.Descriptor f1701x672bf0ef = getDescriptor().getMessageTypes().get(3);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_GetIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1702xc38eb86d = new GeneratedMessageV3.FieldAccessorTable(f1701x672bf0ef, new String[]{"Name", "LanguageCode", "IntentView"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateIntentRequest_descriptor */
    static final Descriptors.Descriptor f1697x5ab03a99 = getDescriptor().getMessageTypes().get(4);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_CreateIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1698xec2bb817 = new GeneratedMessageV3.FieldAccessorTable(f1697x5ab03a99, new String[]{"Parent", "Intent", "LanguageCode", "IntentView"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateIntentRequest_descriptor */
    static final Descriptors.Descriptor f1803x23637b66 = getDescriptor().getMessageTypes().get(5);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_UpdateIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1804x286bdbe4 = new GeneratedMessageV3.FieldAccessorTable(f1803x23637b66, new String[]{"Intent", "LanguageCode", "UpdateMask", "IntentView"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteIntentRequest_descriptor */
    static final Descriptors.Descriptor f1699xbd49c248 = getDescriptor().getMessageTypes().get(6);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_DeleteIntentRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1700x171620c6 = new GeneratedMessageV3.FieldAccessorTable(f1699xbd49c248, new String[]{"Name"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateIntentsRequest_descriptor */
    static final Descriptors.Descriptor f1693x50eb1a95 = getDescriptor().getMessageTypes().get(7);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateIntentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1694x9874dc13 = new GeneratedMessageV3.FieldAccessorTable(f1693x50eb1a95, new String[]{"Parent", "IntentBatchUri", "IntentBatchInline", "LanguageCode", "UpdateMask", "IntentView", "IntentBatch"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateIntentsResponse_descriptor */
    static final Descriptors.Descriptor f1695xd9e43d77 = getDescriptor().getMessageTypes().get(8);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchUpdateIntentsResponse_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1696x1395fcf5 = new GeneratedMessageV3.FieldAccessorTable(f1695xd9e43d77, new String[]{"Intents"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchDeleteIntentsRequest_descriptor */
    static final Descriptors.Descriptor f1691xf3cdaff3 = getDescriptor().getMessageTypes().get(9);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_BatchDeleteIntentsRequest_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1692x7f133371 = new GeneratedMessageV3.FieldAccessorTable(f1691xf3cdaff3, new String[]{"Parent", "Intents"});

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_IntentBatch_descriptor */
    static final Descriptors.Descriptor f1703x46873cf2 = getDescriptor().getMessageTypes().get(10);

    /* renamed from: internal_static_google_cloud_dialogflow_v2beta1_IntentBatch_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1704x25ab5170 = new GeneratedMessageV3.FieldAccessorTable(f1703x46873cf2, new String[]{"Intents"});

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
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        newInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ResourceProto.resource);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        FieldBehaviorProto.getDescriptor();
        ResourceProto.getDescriptor();
        AudioConfigProto.getDescriptor();
        ContextProto.getDescriptor();
        OperationsProto.getDescriptor();
        DurationProto.getDescriptor();
        EmptyProto.getDescriptor();
        FieldMaskProto.getDescriptor();
        StructProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
