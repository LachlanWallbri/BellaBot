package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.Context;
import com.google.cloud.dialogflow.p049v2.Intent;
import com.google.cloud.dialogflow.p049v2.SentimentAnalysisResult;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class QueryResult extends GeneratedMessageV3 implements QueryResultOrBuilder {
    public static final int ACTION_FIELD_NUMBER = 3;
    public static final int ALL_REQUIRED_PARAMS_PRESENT_FIELD_NUMBER = 5;
    public static final int DIAGNOSTIC_INFO_FIELD_NUMBER = 14;
    public static final int FULFILLMENT_MESSAGES_FIELD_NUMBER = 7;
    public static final int FULFILLMENT_TEXT_FIELD_NUMBER = 6;
    public static final int INTENT_DETECTION_CONFIDENCE_FIELD_NUMBER = 12;
    public static final int INTENT_FIELD_NUMBER = 11;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 15;
    public static final int OUTPUT_CONTEXTS_FIELD_NUMBER = 10;
    public static final int PARAMETERS_FIELD_NUMBER = 4;
    public static final int QUERY_TEXT_FIELD_NUMBER = 1;
    public static final int SENTIMENT_ANALYSIS_RESULT_FIELD_NUMBER = 17;
    public static final int SPEECH_RECOGNITION_CONFIDENCE_FIELD_NUMBER = 2;
    public static final int WEBHOOK_PAYLOAD_FIELD_NUMBER = 9;
    public static final int WEBHOOK_SOURCE_FIELD_NUMBER = 8;
    private static final long serialVersionUID = 0;
    private volatile Object action_;
    private boolean allRequiredParamsPresent_;
    private Struct diagnosticInfo_;
    private List<Intent.Message> fulfillmentMessages_;
    private volatile Object fulfillmentText_;
    private float intentDetectionConfidence_;
    private Intent intent_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private List<Context> outputContexts_;
    private Struct parameters_;
    private volatile Object queryText_;
    private SentimentAnalysisResult sentimentAnalysisResult_;
    private float speechRecognitionConfidence_;
    private Struct webhookPayload_;
    private volatile Object webhookSource_;
    private static final QueryResult DEFAULT_INSTANCE = new QueryResult();
    private static final Parser<QueryResult> PARSER = new AbstractParser<QueryResult>() { // from class: com.google.cloud.dialogflow.v2.QueryResult.1
        @Override // com.google.protobuf.Parser
        public QueryResult parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new QueryResult(codedInputStream, extensionRegistryLite);
        }
    };

    private QueryResult(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private QueryResult() {
        this.memoizedIsInitialized = (byte) -1;
        this.queryText_ = "";
        this.languageCode_ = "";
        this.action_ = "";
        this.fulfillmentText_ = "";
        this.fulfillmentMessages_ = Collections.emptyList();
        this.webhookSource_ = "";
        this.outputContexts_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new QueryResult();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0013. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v5 */
    private QueryResult(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            z = true;
                        case 10:
                            this.queryText_ = codedInputStream.readStringRequireUtf8();
                        case 21:
                            this.speechRecognitionConfidence_ = codedInputStream.readFloat();
                        case 26:
                            this.action_ = codedInputStream.readStringRequireUtf8();
                        case 34:
                            Struct.Builder builder = this.parameters_ != null ? this.parameters_.toBuilder() : null;
                            this.parameters_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.parameters_);
                                this.parameters_ = builder.buildPartial();
                            }
                        case 40:
                            this.allRequiredParamsPresent_ = codedInputStream.readBool();
                        case 50:
                            this.fulfillmentText_ = codedInputStream.readStringRequireUtf8();
                        case 58:
                            int i = (c == true ? 1 : 0) & 1;
                            c = c;
                            if (i == 0) {
                                this.fulfillmentMessages_ = new ArrayList();
                                c = (c == true ? 1 : 0) | 1;
                            }
                            this.fulfillmentMessages_.add(codedInputStream.readMessage(Intent.Message.parser(), extensionRegistryLite));
                        case 66:
                            this.webhookSource_ = codedInputStream.readStringRequireUtf8();
                        case 74:
                            Struct.Builder builder2 = this.webhookPayload_ != null ? this.webhookPayload_.toBuilder() : null;
                            this.webhookPayload_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.webhookPayload_);
                                this.webhookPayload_ = builder2.buildPartial();
                            }
                        case 82:
                            int i2 = (c == true ? 1 : 0) & 2;
                            c = c;
                            if (i2 == 0) {
                                this.outputContexts_ = new ArrayList();
                                c = (c == true ? 1 : 0) | 2;
                            }
                            this.outputContexts_.add(codedInputStream.readMessage(Context.parser(), extensionRegistryLite));
                        case 90:
                            Intent.Builder builder3 = this.intent_ != null ? this.intent_.toBuilder() : null;
                            this.intent_ = (Intent) codedInputStream.readMessage(Intent.parser(), extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom(this.intent_);
                                this.intent_ = builder3.buildPartial();
                            }
                        case 101:
                            this.intentDetectionConfidence_ = codedInputStream.readFloat();
                        case 114:
                            Struct.Builder builder4 = this.diagnosticInfo_ != null ? this.diagnosticInfo_.toBuilder() : null;
                            this.diagnosticInfo_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder4 != null) {
                                builder4.mergeFrom(this.diagnosticInfo_);
                                this.diagnosticInfo_ = builder4.buildPartial();
                            }
                        case 122:
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
                        case 138:
                            SentimentAnalysisResult.Builder builder5 = this.sentimentAnalysisResult_ != null ? this.sentimentAnalysisResult_.toBuilder() : null;
                            this.sentimentAnalysisResult_ = (SentimentAnalysisResult) codedInputStream.readMessage(SentimentAnalysisResult.parser(), extensionRegistryLite);
                            if (builder5 != null) {
                                builder5.mergeFrom(this.sentimentAnalysisResult_);
                                this.sentimentAnalysisResult_ = builder5.buildPartial();
                            }
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                z = true;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (((c == true ? 1 : 0) & 1) != 0) {
                    this.fulfillmentMessages_ = Collections.unmodifiableList(this.fulfillmentMessages_);
                }
                if (((c == true ? 1 : 0) & 2) != 0) {
                    this.outputContexts_ = Collections.unmodifiableList(this.outputContexts_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionProto.f1554x2dca66e4;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1555x38666962.ensureFieldAccessorsInitialized(QueryResult.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public String getQueryText() {
        Object obj = this.queryText_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.queryText_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public ByteString getQueryTextBytes() {
        Object obj = this.queryText_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.queryText_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public float getSpeechRecognitionConfidence() {
        return this.speechRecognitionConfidence_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public String getAction() {
        Object obj = this.action_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.action_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public ByteString getActionBytes() {
        Object obj = this.action_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.action_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public boolean hasParameters() {
        return this.parameters_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public Struct getParameters() {
        Struct struct = this.parameters_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public StructOrBuilder getParametersOrBuilder() {
        return getParameters();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public boolean getAllRequiredParamsPresent() {
        return this.allRequiredParamsPresent_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public String getFulfillmentText() {
        Object obj = this.fulfillmentText_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.fulfillmentText_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public ByteString getFulfillmentTextBytes() {
        Object obj = this.fulfillmentText_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.fulfillmentText_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public List<Intent.Message> getFulfillmentMessagesList() {
        return this.fulfillmentMessages_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public List<? extends Intent.MessageOrBuilder> getFulfillmentMessagesOrBuilderList() {
        return this.fulfillmentMessages_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public int getFulfillmentMessagesCount() {
        return this.fulfillmentMessages_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public Intent.Message getFulfillmentMessages(int i) {
        return this.fulfillmentMessages_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public Intent.MessageOrBuilder getFulfillmentMessagesOrBuilder(int i) {
        return this.fulfillmentMessages_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public String getWebhookSource() {
        Object obj = this.webhookSource_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.webhookSource_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public ByteString getWebhookSourceBytes() {
        Object obj = this.webhookSource_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.webhookSource_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public boolean hasWebhookPayload() {
        return this.webhookPayload_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public Struct getWebhookPayload() {
        Struct struct = this.webhookPayload_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public StructOrBuilder getWebhookPayloadOrBuilder() {
        return getWebhookPayload();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public List<Context> getOutputContextsList() {
        return this.outputContexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public List<? extends ContextOrBuilder> getOutputContextsOrBuilderList() {
        return this.outputContexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public int getOutputContextsCount() {
        return this.outputContexts_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public Context getOutputContexts(int i) {
        return this.outputContexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public ContextOrBuilder getOutputContextsOrBuilder(int i) {
        return this.outputContexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public boolean hasIntent() {
        return this.intent_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public Intent getIntent() {
        Intent intent = this.intent_;
        return intent == null ? Intent.getDefaultInstance() : intent;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public IntentOrBuilder getIntentOrBuilder() {
        return getIntent();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public float getIntentDetectionConfidence() {
        return this.intentDetectionConfidence_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public boolean hasDiagnosticInfo() {
        return this.diagnosticInfo_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public Struct getDiagnosticInfo() {
        Struct struct = this.diagnosticInfo_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public StructOrBuilder getDiagnosticInfoOrBuilder() {
        return getDiagnosticInfo();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public boolean hasSentimentAnalysisResult() {
        return this.sentimentAnalysisResult_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public SentimentAnalysisResult getSentimentAnalysisResult() {
        SentimentAnalysisResult sentimentAnalysisResult = this.sentimentAnalysisResult_;
        return sentimentAnalysisResult == null ? SentimentAnalysisResult.getDefaultInstance() : sentimentAnalysisResult;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
    public SentimentAnalysisResultOrBuilder getSentimentAnalysisResultOrBuilder() {
        return getSentimentAnalysisResult();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getQueryTextBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.queryText_);
        }
        float f = this.speechRecognitionConfidence_;
        if (f != 0.0f) {
            codedOutputStream.writeFloat(2, f);
        }
        if (!getActionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.action_);
        }
        if (this.parameters_ != null) {
            codedOutputStream.writeMessage(4, getParameters());
        }
        boolean z = this.allRequiredParamsPresent_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        if (!getFulfillmentTextBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.fulfillmentText_);
        }
        for (int i = 0; i < this.fulfillmentMessages_.size(); i++) {
            codedOutputStream.writeMessage(7, this.fulfillmentMessages_.get(i));
        }
        if (!getWebhookSourceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.webhookSource_);
        }
        if (this.webhookPayload_ != null) {
            codedOutputStream.writeMessage(9, getWebhookPayload());
        }
        for (int i2 = 0; i2 < this.outputContexts_.size(); i2++) {
            codedOutputStream.writeMessage(10, this.outputContexts_.get(i2));
        }
        if (this.intent_ != null) {
            codedOutputStream.writeMessage(11, getIntent());
        }
        float f2 = this.intentDetectionConfidence_;
        if (f2 != 0.0f) {
            codedOutputStream.writeFloat(12, f2);
        }
        if (this.diagnosticInfo_ != null) {
            codedOutputStream.writeMessage(14, getDiagnosticInfo());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 15, this.languageCode_);
        }
        if (this.sentimentAnalysisResult_ != null) {
            codedOutputStream.writeMessage(17, getSentimentAnalysisResult());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getQueryTextBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.queryText_) + 0 : 0;
        float f = this.speechRecognitionConfidence_;
        if (f != 0.0f) {
            computeStringSize += CodedOutputStream.computeFloatSize(2, f);
        }
        if (!getActionBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.action_);
        }
        if (this.parameters_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, getParameters());
        }
        boolean z = this.allRequiredParamsPresent_;
        if (z) {
            computeStringSize += CodedOutputStream.computeBoolSize(5, z);
        }
        if (!getFulfillmentTextBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(6, this.fulfillmentText_);
        }
        int i2 = computeStringSize;
        for (int i3 = 0; i3 < this.fulfillmentMessages_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(7, this.fulfillmentMessages_.get(i3));
        }
        if (!getWebhookSourceBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(8, this.webhookSource_);
        }
        if (this.webhookPayload_ != null) {
            i2 += CodedOutputStream.computeMessageSize(9, getWebhookPayload());
        }
        for (int i4 = 0; i4 < this.outputContexts_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(10, this.outputContexts_.get(i4));
        }
        if (this.intent_ != null) {
            i2 += CodedOutputStream.computeMessageSize(11, getIntent());
        }
        float f2 = this.intentDetectionConfidence_;
        if (f2 != 0.0f) {
            i2 += CodedOutputStream.computeFloatSize(12, f2);
        }
        if (this.diagnosticInfo_ != null) {
            i2 += CodedOutputStream.computeMessageSize(14, getDiagnosticInfo());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(15, this.languageCode_);
        }
        if (this.sentimentAnalysisResult_ != null) {
            i2 += CodedOutputStream.computeMessageSize(17, getSentimentAnalysisResult());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QueryResult)) {
            return super.equals(obj);
        }
        QueryResult queryResult = (QueryResult) obj;
        if (!getQueryText().equals(queryResult.getQueryText()) || !getLanguageCode().equals(queryResult.getLanguageCode()) || Float.floatToIntBits(getSpeechRecognitionConfidence()) != Float.floatToIntBits(queryResult.getSpeechRecognitionConfidence()) || !getAction().equals(queryResult.getAction()) || hasParameters() != queryResult.hasParameters()) {
            return false;
        }
        if ((hasParameters() && !getParameters().equals(queryResult.getParameters())) || getAllRequiredParamsPresent() != queryResult.getAllRequiredParamsPresent() || !getFulfillmentText().equals(queryResult.getFulfillmentText()) || !getFulfillmentMessagesList().equals(queryResult.getFulfillmentMessagesList()) || !getWebhookSource().equals(queryResult.getWebhookSource()) || hasWebhookPayload() != queryResult.hasWebhookPayload()) {
            return false;
        }
        if ((hasWebhookPayload() && !getWebhookPayload().equals(queryResult.getWebhookPayload())) || !getOutputContextsList().equals(queryResult.getOutputContextsList()) || hasIntent() != queryResult.hasIntent()) {
            return false;
        }
        if ((hasIntent() && !getIntent().equals(queryResult.getIntent())) || Float.floatToIntBits(getIntentDetectionConfidence()) != Float.floatToIntBits(queryResult.getIntentDetectionConfidence()) || hasDiagnosticInfo() != queryResult.hasDiagnosticInfo()) {
            return false;
        }
        if ((!hasDiagnosticInfo() || getDiagnosticInfo().equals(queryResult.getDiagnosticInfo())) && hasSentimentAnalysisResult() == queryResult.hasSentimentAnalysisResult()) {
            return (!hasSentimentAnalysisResult() || getSentimentAnalysisResult().equals(queryResult.getSentimentAnalysisResult())) && this.unknownFields.equals(queryResult.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getQueryText().hashCode()) * 37) + 15) * 53) + getLanguageCode().hashCode()) * 37) + 2) * 53) + Float.floatToIntBits(getSpeechRecognitionConfidence())) * 37) + 3) * 53) + getAction().hashCode();
        if (hasParameters()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getParameters().hashCode();
        }
        int hashBoolean = (((((((hashCode * 37) + 5) * 53) + Internal.hashBoolean(getAllRequiredParamsPresent())) * 37) + 6) * 53) + getFulfillmentText().hashCode();
        if (getFulfillmentMessagesCount() > 0) {
            hashBoolean = (((hashBoolean * 37) + 7) * 53) + getFulfillmentMessagesList().hashCode();
        }
        int hashCode2 = (((hashBoolean * 37) + 8) * 53) + getWebhookSource().hashCode();
        if (hasWebhookPayload()) {
            hashCode2 = (((hashCode2 * 37) + 9) * 53) + getWebhookPayload().hashCode();
        }
        if (getOutputContextsCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 10) * 53) + getOutputContextsList().hashCode();
        }
        if (hasIntent()) {
            hashCode2 = (((hashCode2 * 37) + 11) * 53) + getIntent().hashCode();
        }
        int floatToIntBits = (((hashCode2 * 37) + 12) * 53) + Float.floatToIntBits(getIntentDetectionConfidence());
        if (hasDiagnosticInfo()) {
            floatToIntBits = (((floatToIntBits * 37) + 14) * 53) + getDiagnosticInfo().hashCode();
        }
        if (hasSentimentAnalysisResult()) {
            floatToIntBits = (((floatToIntBits * 37) + 17) * 53) + getSentimentAnalysisResult().hashCode();
        }
        int hashCode3 = (floatToIntBits * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    public static QueryResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static QueryResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static QueryResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static QueryResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static QueryResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static QueryResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static QueryResult parseFrom(InputStream inputStream) throws IOException {
        return (QueryResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static QueryResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QueryResult parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (QueryResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static QueryResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QueryResult parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (QueryResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static QueryResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(QueryResult queryResult) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(queryResult);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QueryResultOrBuilder {
        private Object action_;
        private boolean allRequiredParamsPresent_;
        private int bitField0_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> diagnosticInfoBuilder_;
        private Struct diagnosticInfo_;
        private RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> fulfillmentMessagesBuilder_;
        private List<Intent.Message> fulfillmentMessages_;
        private Object fulfillmentText_;
        private SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> intentBuilder_;
        private float intentDetectionConfidence_;
        private Intent intent_;
        private Object languageCode_;
        private RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> outputContextsBuilder_;
        private List<Context> outputContexts_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> parametersBuilder_;
        private Struct parameters_;
        private Object queryText_;
        private SingleFieldBuilderV3<SentimentAnalysisResult, SentimentAnalysisResult.Builder, SentimentAnalysisResultOrBuilder> sentimentAnalysisResultBuilder_;
        private SentimentAnalysisResult sentimentAnalysisResult_;
        private float speechRecognitionConfidence_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> webhookPayloadBuilder_;
        private Struct webhookPayload_;
        private Object webhookSource_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1554x2dca66e4;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1555x38666962.ensureFieldAccessorsInitialized(QueryResult.class, Builder.class);
        }

        private Builder() {
            this.queryText_ = "";
            this.languageCode_ = "";
            this.action_ = "";
            this.fulfillmentText_ = "";
            this.fulfillmentMessages_ = Collections.emptyList();
            this.webhookSource_ = "";
            this.outputContexts_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.queryText_ = "";
            this.languageCode_ = "";
            this.action_ = "";
            this.fulfillmentText_ = "";
            this.fulfillmentMessages_ = Collections.emptyList();
            this.webhookSource_ = "";
            this.outputContexts_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (QueryResult.alwaysUseFieldBuilders) {
                getFulfillmentMessagesFieldBuilder();
                getOutputContextsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.queryText_ = "";
            this.languageCode_ = "";
            this.speechRecognitionConfidence_ = 0.0f;
            this.action_ = "";
            if (this.parametersBuilder_ == null) {
                this.parameters_ = null;
            } else {
                this.parameters_ = null;
                this.parametersBuilder_ = null;
            }
            this.allRequiredParamsPresent_ = false;
            this.fulfillmentText_ = "";
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fulfillmentMessages_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.webhookSource_ = "";
            if (this.webhookPayloadBuilder_ == null) {
                this.webhookPayload_ = null;
            } else {
                this.webhookPayload_ = null;
                this.webhookPayloadBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV32 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.outputContexts_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            if (this.intentBuilder_ == null) {
                this.intent_ = null;
            } else {
                this.intent_ = null;
                this.intentBuilder_ = null;
            }
            this.intentDetectionConfidence_ = 0.0f;
            if (this.diagnosticInfoBuilder_ == null) {
                this.diagnosticInfo_ = null;
            } else {
                this.diagnosticInfo_ = null;
                this.diagnosticInfoBuilder_ = null;
            }
            if (this.sentimentAnalysisResultBuilder_ == null) {
                this.sentimentAnalysisResult_ = null;
            } else {
                this.sentimentAnalysisResult_ = null;
                this.sentimentAnalysisResultBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.f1554x2dca66e4;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public QueryResult getDefaultInstanceForType() {
            return QueryResult.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public QueryResult build() {
            QueryResult buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public QueryResult buildPartial() {
            QueryResult queryResult = new QueryResult(this);
            int i = this.bitField0_;
            queryResult.queryText_ = this.queryText_;
            queryResult.languageCode_ = this.languageCode_;
            queryResult.speechRecognitionConfidence_ = this.speechRecognitionConfidence_;
            queryResult.action_ = this.action_;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                queryResult.parameters_ = this.parameters_;
            } else {
                queryResult.parameters_ = singleFieldBuilderV3.build();
            }
            queryResult.allRequiredParamsPresent_ = this.allRequiredParamsPresent_;
            queryResult.fulfillmentText_ = this.fulfillmentText_;
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                queryResult.fulfillmentMessages_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.fulfillmentMessages_ = Collections.unmodifiableList(this.fulfillmentMessages_);
                    this.bitField0_ &= -2;
                }
                queryResult.fulfillmentMessages_ = this.fulfillmentMessages_;
            }
            queryResult.webhookSource_ = this.webhookSource_;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV32 = this.webhookPayloadBuilder_;
            if (singleFieldBuilderV32 == null) {
                queryResult.webhookPayload_ = this.webhookPayload_;
            } else {
                queryResult.webhookPayload_ = singleFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV32 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV32 != null) {
                queryResult.outputContexts_ = repeatedFieldBuilderV32.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.outputContexts_ = Collections.unmodifiableList(this.outputContexts_);
                    this.bitField0_ &= -3;
                }
                queryResult.outputContexts_ = this.outputContexts_;
            }
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV33 = this.intentBuilder_;
            if (singleFieldBuilderV33 == null) {
                queryResult.intent_ = this.intent_;
            } else {
                queryResult.intent_ = singleFieldBuilderV33.build();
            }
            queryResult.intentDetectionConfidence_ = this.intentDetectionConfidence_;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV34 = this.diagnosticInfoBuilder_;
            if (singleFieldBuilderV34 == null) {
                queryResult.diagnosticInfo_ = this.diagnosticInfo_;
            } else {
                queryResult.diagnosticInfo_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<SentimentAnalysisResult, SentimentAnalysisResult.Builder, SentimentAnalysisResultOrBuilder> singleFieldBuilderV35 = this.sentimentAnalysisResultBuilder_;
            if (singleFieldBuilderV35 == null) {
                queryResult.sentimentAnalysisResult_ = this.sentimentAnalysisResult_;
            } else {
                queryResult.sentimentAnalysisResult_ = singleFieldBuilderV35.build();
            }
            onBuilt();
            return queryResult;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public Builder mo4275clone() {
            return (Builder) super.mo4275clone();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof QueryResult) {
                return mergeFrom((QueryResult) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(QueryResult queryResult) {
            if (queryResult == QueryResult.getDefaultInstance()) {
                return this;
            }
            if (!queryResult.getQueryText().isEmpty()) {
                this.queryText_ = queryResult.queryText_;
                onChanged();
            }
            if (!queryResult.getLanguageCode().isEmpty()) {
                this.languageCode_ = queryResult.languageCode_;
                onChanged();
            }
            if (queryResult.getSpeechRecognitionConfidence() != 0.0f) {
                setSpeechRecognitionConfidence(queryResult.getSpeechRecognitionConfidence());
            }
            if (!queryResult.getAction().isEmpty()) {
                this.action_ = queryResult.action_;
                onChanged();
            }
            if (queryResult.hasParameters()) {
                mergeParameters(queryResult.getParameters());
            }
            if (queryResult.getAllRequiredParamsPresent()) {
                setAllRequiredParamsPresent(queryResult.getAllRequiredParamsPresent());
            }
            if (!queryResult.getFulfillmentText().isEmpty()) {
                this.fulfillmentText_ = queryResult.fulfillmentText_;
                onChanged();
            }
            if (this.fulfillmentMessagesBuilder_ == null) {
                if (!queryResult.fulfillmentMessages_.isEmpty()) {
                    if (this.fulfillmentMessages_.isEmpty()) {
                        this.fulfillmentMessages_ = queryResult.fulfillmentMessages_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFulfillmentMessagesIsMutable();
                        this.fulfillmentMessages_.addAll(queryResult.fulfillmentMessages_);
                    }
                    onChanged();
                }
            } else if (!queryResult.fulfillmentMessages_.isEmpty()) {
                if (!this.fulfillmentMessagesBuilder_.isEmpty()) {
                    this.fulfillmentMessagesBuilder_.addAllMessages(queryResult.fulfillmentMessages_);
                } else {
                    this.fulfillmentMessagesBuilder_.dispose();
                    this.fulfillmentMessagesBuilder_ = null;
                    this.fulfillmentMessages_ = queryResult.fulfillmentMessages_;
                    this.bitField0_ &= -2;
                    this.fulfillmentMessagesBuilder_ = QueryResult.alwaysUseFieldBuilders ? getFulfillmentMessagesFieldBuilder() : null;
                }
            }
            if (!queryResult.getWebhookSource().isEmpty()) {
                this.webhookSource_ = queryResult.webhookSource_;
                onChanged();
            }
            if (queryResult.hasWebhookPayload()) {
                mergeWebhookPayload(queryResult.getWebhookPayload());
            }
            if (this.outputContextsBuilder_ == null) {
                if (!queryResult.outputContexts_.isEmpty()) {
                    if (this.outputContexts_.isEmpty()) {
                        this.outputContexts_ = queryResult.outputContexts_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureOutputContextsIsMutable();
                        this.outputContexts_.addAll(queryResult.outputContexts_);
                    }
                    onChanged();
                }
            } else if (!queryResult.outputContexts_.isEmpty()) {
                if (!this.outputContextsBuilder_.isEmpty()) {
                    this.outputContextsBuilder_.addAllMessages(queryResult.outputContexts_);
                } else {
                    this.outputContextsBuilder_.dispose();
                    this.outputContextsBuilder_ = null;
                    this.outputContexts_ = queryResult.outputContexts_;
                    this.bitField0_ &= -3;
                    this.outputContextsBuilder_ = QueryResult.alwaysUseFieldBuilders ? getOutputContextsFieldBuilder() : null;
                }
            }
            if (queryResult.hasIntent()) {
                mergeIntent(queryResult.getIntent());
            }
            if (queryResult.getIntentDetectionConfidence() != 0.0f) {
                setIntentDetectionConfidence(queryResult.getIntentDetectionConfidence());
            }
            if (queryResult.hasDiagnosticInfo()) {
                mergeDiagnosticInfo(queryResult.getDiagnosticInfo());
            }
            if (queryResult.hasSentimentAnalysisResult()) {
                mergeSentimentAnalysisResult(queryResult.getSentimentAnalysisResult());
            }
            mergeUnknownFields(queryResult.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            QueryResult queryResult = null;
            try {
                try {
                    QueryResult queryResult2 = (QueryResult) QueryResult.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (queryResult2 != null) {
                        mergeFrom(queryResult2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    QueryResult queryResult3 = (QueryResult) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        queryResult = queryResult3;
                        if (queryResult != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (queryResult != null) {
                    mergeFrom(queryResult);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public String getQueryText() {
            Object obj = this.queryText_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.queryText_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public ByteString getQueryTextBytes() {
            Object obj = this.queryText_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.queryText_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setQueryText(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.queryText_ = str;
            onChanged();
            return this;
        }

        public Builder clearQueryText() {
            this.queryText_ = QueryResult.getDefaultInstance().getQueryText();
            onChanged();
            return this;
        }

        public Builder setQueryTextBytes(ByteString byteString) {
            if (byteString != null) {
                QueryResult.checkByteStringIsUtf8(byteString);
                this.queryText_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public ByteString getLanguageCodeBytes() {
            Object obj = this.languageCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.languageCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLanguageCode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.languageCode_ = str;
            onChanged();
            return this;
        }

        public Builder clearLanguageCode() {
            this.languageCode_ = QueryResult.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                QueryResult.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public float getSpeechRecognitionConfidence() {
            return this.speechRecognitionConfidence_;
        }

        public Builder setSpeechRecognitionConfidence(float f) {
            this.speechRecognitionConfidence_ = f;
            onChanged();
            return this;
        }

        public Builder clearSpeechRecognitionConfidence() {
            this.speechRecognitionConfidence_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public String getAction() {
            Object obj = this.action_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.action_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public ByteString getActionBytes() {
            Object obj = this.action_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.action_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAction(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.action_ = str;
            onChanged();
            return this;
        }

        public Builder clearAction() {
            this.action_ = QueryResult.getDefaultInstance().getAction();
            onChanged();
            return this;
        }

        public Builder setActionBytes(ByteString byteString) {
            if (byteString != null) {
                QueryResult.checkByteStringIsUtf8(byteString);
                this.action_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public boolean hasParameters() {
            return (this.parametersBuilder_ == null && this.parameters_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public Struct getParameters() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.parameters_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setParameters(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.parameters_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setParameters(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.parameters_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeParameters(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.parameters_;
                if (struct2 != null) {
                    this.parameters_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.parameters_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearParameters() {
            if (this.parametersBuilder_ == null) {
                this.parameters_ = null;
                onChanged();
            } else {
                this.parameters_ = null;
                this.parametersBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getParametersBuilder() {
            onChanged();
            return getParametersFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public StructOrBuilder getParametersOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.parameters_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getParametersFieldBuilder() {
            if (this.parametersBuilder_ == null) {
                this.parametersBuilder_ = new SingleFieldBuilderV3<>(getParameters(), getParentForChildren(), isClean());
                this.parameters_ = null;
            }
            return this.parametersBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public boolean getAllRequiredParamsPresent() {
            return this.allRequiredParamsPresent_;
        }

        public Builder setAllRequiredParamsPresent(boolean z) {
            this.allRequiredParamsPresent_ = z;
            onChanged();
            return this;
        }

        public Builder clearAllRequiredParamsPresent() {
            this.allRequiredParamsPresent_ = false;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public String getFulfillmentText() {
            Object obj = this.fulfillmentText_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.fulfillmentText_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public ByteString getFulfillmentTextBytes() {
            Object obj = this.fulfillmentText_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.fulfillmentText_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setFulfillmentText(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.fulfillmentText_ = str;
            onChanged();
            return this;
        }

        public Builder clearFulfillmentText() {
            this.fulfillmentText_ = QueryResult.getDefaultInstance().getFulfillmentText();
            onChanged();
            return this;
        }

        public Builder setFulfillmentTextBytes(ByteString byteString) {
            if (byteString != null) {
                QueryResult.checkByteStringIsUtf8(byteString);
                this.fulfillmentText_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureFulfillmentMessagesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.fulfillmentMessages_ = new ArrayList(this.fulfillmentMessages_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public List<Intent.Message> getFulfillmentMessagesList() {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.fulfillmentMessages_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public int getFulfillmentMessagesCount() {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fulfillmentMessages_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public Intent.Message getFulfillmentMessages(int i) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fulfillmentMessages_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setFulfillmentMessages(int i, Intent.Message message) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, message);
            } else {
                if (message == null) {
                    throw new NullPointerException();
                }
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.set(i, message);
                onChanged();
            }
            return this;
        }

        public Builder setFulfillmentMessages(int i, Intent.Message.Builder builder) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addFulfillmentMessages(Intent.Message message) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(message);
            } else {
                if (message == null) {
                    throw new NullPointerException();
                }
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.add(message);
                onChanged();
            }
            return this;
        }

        public Builder addFulfillmentMessages(int i, Intent.Message message) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, message);
            } else {
                if (message == null) {
                    throw new NullPointerException();
                }
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.add(i, message);
                onChanged();
            }
            return this;
        }

        public Builder addFulfillmentMessages(Intent.Message.Builder builder) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addFulfillmentMessages(int i, Intent.Message.Builder builder) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllFulfillmentMessages(Iterable<? extends Intent.Message> iterable) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.fulfillmentMessages_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearFulfillmentMessages() {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fulfillmentMessages_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeFulfillmentMessages(int i) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Intent.Message.Builder getFulfillmentMessagesBuilder(int i) {
            return getFulfillmentMessagesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public Intent.MessageOrBuilder getFulfillmentMessagesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fulfillmentMessages_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public List<? extends Intent.MessageOrBuilder> getFulfillmentMessagesOrBuilderList() {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.fulfillmentMessages_);
        }

        public Intent.Message.Builder addFulfillmentMessagesBuilder() {
            return getFulfillmentMessagesFieldBuilder().addBuilder(Intent.Message.getDefaultInstance());
        }

        public Intent.Message.Builder addFulfillmentMessagesBuilder(int i) {
            return getFulfillmentMessagesFieldBuilder().addBuilder(i, Intent.Message.getDefaultInstance());
        }

        public List<Intent.Message.Builder> getFulfillmentMessagesBuilderList() {
            return getFulfillmentMessagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> getFulfillmentMessagesFieldBuilder() {
            if (this.fulfillmentMessagesBuilder_ == null) {
                this.fulfillmentMessagesBuilder_ = new RepeatedFieldBuilderV3<>(this.fulfillmentMessages_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.fulfillmentMessages_ = null;
            }
            return this.fulfillmentMessagesBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public String getWebhookSource() {
            Object obj = this.webhookSource_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.webhookSource_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public ByteString getWebhookSourceBytes() {
            Object obj = this.webhookSource_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.webhookSource_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setWebhookSource(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.webhookSource_ = str;
            onChanged();
            return this;
        }

        public Builder clearWebhookSource() {
            this.webhookSource_ = QueryResult.getDefaultInstance().getWebhookSource();
            onChanged();
            return this;
        }

        public Builder setWebhookSourceBytes(ByteString byteString) {
            if (byteString != null) {
                QueryResult.checkByteStringIsUtf8(byteString);
                this.webhookSource_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public boolean hasWebhookPayload() {
            return (this.webhookPayloadBuilder_ == null && this.webhookPayload_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public Struct getWebhookPayload() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.webhookPayloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.webhookPayload_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setWebhookPayload(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.webhookPayloadBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.webhookPayload_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setWebhookPayload(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.webhookPayloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.webhookPayload_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeWebhookPayload(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.webhookPayloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.webhookPayload_;
                if (struct2 != null) {
                    this.webhookPayload_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.webhookPayload_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearWebhookPayload() {
            if (this.webhookPayloadBuilder_ == null) {
                this.webhookPayload_ = null;
                onChanged();
            } else {
                this.webhookPayload_ = null;
                this.webhookPayloadBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getWebhookPayloadBuilder() {
            onChanged();
            return getWebhookPayloadFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public StructOrBuilder getWebhookPayloadOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.webhookPayloadBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.webhookPayload_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getWebhookPayloadFieldBuilder() {
            if (this.webhookPayloadBuilder_ == null) {
                this.webhookPayloadBuilder_ = new SingleFieldBuilderV3<>(getWebhookPayload(), getParentForChildren(), isClean());
                this.webhookPayload_ = null;
            }
            return this.webhookPayloadBuilder_;
        }

        private void ensureOutputContextsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.outputContexts_ = new ArrayList(this.outputContexts_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public List<Context> getOutputContextsList() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.outputContexts_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public int getOutputContextsCount() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.outputContexts_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public Context getOutputContexts(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.outputContexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setOutputContexts(int i, Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureOutputContextsIsMutable();
                this.outputContexts_.set(i, context);
                onChanged();
            }
            return this;
        }

        public Builder setOutputContexts(int i, Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                this.outputContexts_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addOutputContexts(Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureOutputContextsIsMutable();
                this.outputContexts_.add(context);
                onChanged();
            }
            return this;
        }

        public Builder addOutputContexts(int i, Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureOutputContextsIsMutable();
                this.outputContexts_.add(i, context);
                onChanged();
            }
            return this;
        }

        public Builder addOutputContexts(Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                this.outputContexts_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addOutputContexts(int i, Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                this.outputContexts_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllOutputContexts(Iterable<? extends Context> iterable) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.outputContexts_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearOutputContexts() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.outputContexts_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeOutputContexts(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                this.outputContexts_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Context.Builder getOutputContextsBuilder(int i) {
            return getOutputContextsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public ContextOrBuilder getOutputContextsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.outputContexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public List<? extends ContextOrBuilder> getOutputContextsOrBuilderList() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.outputContexts_);
        }

        public Context.Builder addOutputContextsBuilder() {
            return getOutputContextsFieldBuilder().addBuilder(Context.getDefaultInstance());
        }

        public Context.Builder addOutputContextsBuilder(int i) {
            return getOutputContextsFieldBuilder().addBuilder(i, Context.getDefaultInstance());
        }

        public List<Context.Builder> getOutputContextsBuilderList() {
            return getOutputContextsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> getOutputContextsFieldBuilder() {
            if (this.outputContextsBuilder_ == null) {
                this.outputContextsBuilder_ = new RepeatedFieldBuilderV3<>(this.outputContexts_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.outputContexts_ = null;
            }
            return this.outputContextsBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public boolean hasIntent() {
            return (this.intentBuilder_ == null && this.intent_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public Intent getIntent() {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Intent intent = this.intent_;
                return intent == null ? Intent.getDefaultInstance() : intent;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setIntent(Intent intent) {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(intent);
            } else {
                if (intent == null) {
                    throw new NullPointerException();
                }
                this.intent_ = intent;
                onChanged();
            }
            return this;
        }

        public Builder setIntent(Intent.Builder builder) {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.intent_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeIntent(Intent intent) {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Intent intent2 = this.intent_;
                if (intent2 != null) {
                    this.intent_ = Intent.newBuilder(intent2).mergeFrom(intent).buildPartial();
                } else {
                    this.intent_ = intent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(intent);
            }
            return this;
        }

        public Builder clearIntent() {
            if (this.intentBuilder_ == null) {
                this.intent_ = null;
                onChanged();
            } else {
                this.intent_ = null;
                this.intentBuilder_ = null;
            }
            return this;
        }

        public Intent.Builder getIntentBuilder() {
            onChanged();
            return getIntentFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public IntentOrBuilder getIntentOrBuilder() {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Intent intent = this.intent_;
            return intent == null ? Intent.getDefaultInstance() : intent;
        }

        private SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> getIntentFieldBuilder() {
            if (this.intentBuilder_ == null) {
                this.intentBuilder_ = new SingleFieldBuilderV3<>(getIntent(), getParentForChildren(), isClean());
                this.intent_ = null;
            }
            return this.intentBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public float getIntentDetectionConfidence() {
            return this.intentDetectionConfidence_;
        }

        public Builder setIntentDetectionConfidence(float f) {
            this.intentDetectionConfidence_ = f;
            onChanged();
            return this;
        }

        public Builder clearIntentDetectionConfidence() {
            this.intentDetectionConfidence_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public boolean hasDiagnosticInfo() {
            return (this.diagnosticInfoBuilder_ == null && this.diagnosticInfo_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public Struct getDiagnosticInfo() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.diagnosticInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.diagnosticInfo_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setDiagnosticInfo(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.diagnosticInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.diagnosticInfo_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setDiagnosticInfo(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.diagnosticInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.diagnosticInfo_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeDiagnosticInfo(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.diagnosticInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.diagnosticInfo_;
                if (struct2 != null) {
                    this.diagnosticInfo_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.diagnosticInfo_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearDiagnosticInfo() {
            if (this.diagnosticInfoBuilder_ == null) {
                this.diagnosticInfo_ = null;
                onChanged();
            } else {
                this.diagnosticInfo_ = null;
                this.diagnosticInfoBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getDiagnosticInfoBuilder() {
            onChanged();
            return getDiagnosticInfoFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public StructOrBuilder getDiagnosticInfoOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.diagnosticInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.diagnosticInfo_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getDiagnosticInfoFieldBuilder() {
            if (this.diagnosticInfoBuilder_ == null) {
                this.diagnosticInfoBuilder_ = new SingleFieldBuilderV3<>(getDiagnosticInfo(), getParentForChildren(), isClean());
                this.diagnosticInfo_ = null;
            }
            return this.diagnosticInfoBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public boolean hasSentimentAnalysisResult() {
            return (this.sentimentAnalysisResultBuilder_ == null && this.sentimentAnalysisResult_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public SentimentAnalysisResult getSentimentAnalysisResult() {
            SingleFieldBuilderV3<SentimentAnalysisResult, SentimentAnalysisResult.Builder, SentimentAnalysisResultOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                SentimentAnalysisResult sentimentAnalysisResult = this.sentimentAnalysisResult_;
                return sentimentAnalysisResult == null ? SentimentAnalysisResult.getDefaultInstance() : sentimentAnalysisResult;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSentimentAnalysisResult(SentimentAnalysisResult sentimentAnalysisResult) {
            SingleFieldBuilderV3<SentimentAnalysisResult, SentimentAnalysisResult.Builder, SentimentAnalysisResultOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisResultBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sentimentAnalysisResult);
            } else {
                if (sentimentAnalysisResult == null) {
                    throw new NullPointerException();
                }
                this.sentimentAnalysisResult_ = sentimentAnalysisResult;
                onChanged();
            }
            return this;
        }

        public Builder setSentimentAnalysisResult(SentimentAnalysisResult.Builder builder) {
            SingleFieldBuilderV3<SentimentAnalysisResult, SentimentAnalysisResult.Builder, SentimentAnalysisResultOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sentimentAnalysisResult_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSentimentAnalysisResult(SentimentAnalysisResult sentimentAnalysisResult) {
            SingleFieldBuilderV3<SentimentAnalysisResult, SentimentAnalysisResult.Builder, SentimentAnalysisResultOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                SentimentAnalysisResult sentimentAnalysisResult2 = this.sentimentAnalysisResult_;
                if (sentimentAnalysisResult2 != null) {
                    this.sentimentAnalysisResult_ = SentimentAnalysisResult.newBuilder(sentimentAnalysisResult2).mergeFrom(sentimentAnalysisResult).buildPartial();
                } else {
                    this.sentimentAnalysisResult_ = sentimentAnalysisResult;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sentimentAnalysisResult);
            }
            return this;
        }

        public Builder clearSentimentAnalysisResult() {
            if (this.sentimentAnalysisResultBuilder_ == null) {
                this.sentimentAnalysisResult_ = null;
                onChanged();
            } else {
                this.sentimentAnalysisResult_ = null;
                this.sentimentAnalysisResultBuilder_ = null;
            }
            return this;
        }

        public SentimentAnalysisResult.Builder getSentimentAnalysisResultBuilder() {
            onChanged();
            return getSentimentAnalysisResultFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryResultOrBuilder
        public SentimentAnalysisResultOrBuilder getSentimentAnalysisResultOrBuilder() {
            SingleFieldBuilderV3<SentimentAnalysisResult, SentimentAnalysisResult.Builder, SentimentAnalysisResultOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisResultBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SentimentAnalysisResult sentimentAnalysisResult = this.sentimentAnalysisResult_;
            return sentimentAnalysisResult == null ? SentimentAnalysisResult.getDefaultInstance() : sentimentAnalysisResult;
        }

        private SingleFieldBuilderV3<SentimentAnalysisResult, SentimentAnalysisResult.Builder, SentimentAnalysisResultOrBuilder> getSentimentAnalysisResultFieldBuilder() {
            if (this.sentimentAnalysisResultBuilder_ == null) {
                this.sentimentAnalysisResultBuilder_ = new SingleFieldBuilderV3<>(getSentimentAnalysisResult(), getParentForChildren(), isClean());
                this.sentimentAnalysisResult_ = null;
            }
            return this.sentimentAnalysisResultBuilder_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static QueryResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<QueryResult> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<QueryResult> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public QueryResult getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
