package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.OutputAudioConfig;
import com.google.cloud.dialogflow.v2beta1.QueryResult;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class DetectIntentResponse extends GeneratedMessageV3 implements DetectIntentResponseOrBuilder {
    public static final int ALTERNATIVE_QUERY_RESULTS_FIELD_NUMBER = 5;
    public static final int OUTPUT_AUDIO_CONFIG_FIELD_NUMBER = 6;
    public static final int OUTPUT_AUDIO_FIELD_NUMBER = 4;
    public static final int QUERY_RESULT_FIELD_NUMBER = 2;
    public static final int RESPONSE_ID_FIELD_NUMBER = 1;
    public static final int WEBHOOK_STATUS_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private List<QueryResult> alternativeQueryResults_;
    private byte memoizedIsInitialized;
    private OutputAudioConfig outputAudioConfig_;
    private ByteString outputAudio_;
    private QueryResult queryResult_;
    private volatile Object responseId_;
    private Status webhookStatus_;
    private static final DetectIntentResponse DEFAULT_INSTANCE = new DetectIntentResponse();
    private static final Parser<DetectIntentResponse> PARSER = new AbstractParser<DetectIntentResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.DetectIntentResponse.1
        @Override // com.google.protobuf.Parser
        public DetectIntentResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DetectIntentResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private DetectIntentResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DetectIntentResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.responseId_ = "";
        this.alternativeQueryResults_ = Collections.emptyList();
        this.outputAudio_ = ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new DetectIntentResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DetectIntentResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag != 10) {
                                if (readTag == 18) {
                                    QueryResult.Builder builder = this.queryResult_ != null ? this.queryResult_.toBuilder() : null;
                                    this.queryResult_ = (QueryResult) codedInputStream.readMessage(QueryResult.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.queryResult_);
                                        this.queryResult_ = builder.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    Status.Builder builder2 = this.webhookStatus_ != null ? this.webhookStatus_.toBuilder() : null;
                                    this.webhookStatus_ = (Status) codedInputStream.readMessage(Status.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.webhookStatus_);
                                        this.webhookStatus_ = builder2.buildPartial();
                                    }
                                } else if (readTag == 34) {
                                    this.outputAudio_ = codedInputStream.readBytes();
                                } else if (readTag == 42) {
                                    boolean z3 = (z2 ? 1 : 0) & true;
                                    z2 = z2;
                                    if (!z3) {
                                        this.alternativeQueryResults_ = new ArrayList();
                                        z2 = (z2 ? 1 : 0) | true;
                                    }
                                    this.alternativeQueryResults_.add(codedInputStream.readMessage(QueryResult.parser(), extensionRegistryLite));
                                } else if (readTag == 50) {
                                    OutputAudioConfig.Builder builder3 = this.outputAudioConfig_ != null ? this.outputAudioConfig_.toBuilder() : null;
                                    this.outputAudioConfig_ = (OutputAudioConfig) codedInputStream.readMessage(OutputAudioConfig.parser(), extensionRegistryLite);
                                    if (builder3 != null) {
                                        builder3.mergeFrom(this.outputAudioConfig_);
                                        this.outputAudioConfig_ = builder3.buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.responseId_ = codedInputStream.readStringRequireUtf8();
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((z2 ? 1 : 0) & true) {
                    this.alternativeQueryResults_ = Collections.unmodifiableList(this.alternativeQueryResults_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionProto.f1838x9133722c;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1839xc6d3acaa.ensureFieldAccessorsInitialized(DetectIntentResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public String getResponseId() {
        Object obj = this.responseId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.responseId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public ByteString getResponseIdBytes() {
        Object obj = this.responseId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.responseId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public boolean hasQueryResult() {
        return this.queryResult_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public QueryResult getQueryResult() {
        QueryResult queryResult = this.queryResult_;
        return queryResult == null ? QueryResult.getDefaultInstance() : queryResult;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public QueryResultOrBuilder getQueryResultOrBuilder() {
        return getQueryResult();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public List<QueryResult> getAlternativeQueryResultsList() {
        return this.alternativeQueryResults_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public List<? extends QueryResultOrBuilder> getAlternativeQueryResultsOrBuilderList() {
        return this.alternativeQueryResults_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public int getAlternativeQueryResultsCount() {
        return this.alternativeQueryResults_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public QueryResult getAlternativeQueryResults(int i) {
        return this.alternativeQueryResults_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public QueryResultOrBuilder getAlternativeQueryResultsOrBuilder(int i) {
        return this.alternativeQueryResults_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public boolean hasWebhookStatus() {
        return this.webhookStatus_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public Status getWebhookStatus() {
        Status status = this.webhookStatus_;
        return status == null ? Status.getDefaultInstance() : status;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public StatusOrBuilder getWebhookStatusOrBuilder() {
        return getWebhookStatus();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public ByteString getOutputAudio() {
        return this.outputAudio_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public boolean hasOutputAudioConfig() {
        return this.outputAudioConfig_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public OutputAudioConfig getOutputAudioConfig() {
        OutputAudioConfig outputAudioConfig = this.outputAudioConfig_;
        return outputAudioConfig == null ? OutputAudioConfig.getDefaultInstance() : outputAudioConfig;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
    public OutputAudioConfigOrBuilder getOutputAudioConfigOrBuilder() {
        return getOutputAudioConfig();
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
        if (!getResponseIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.responseId_);
        }
        if (this.queryResult_ != null) {
            codedOutputStream.writeMessage(2, getQueryResult());
        }
        if (this.webhookStatus_ != null) {
            codedOutputStream.writeMessage(3, getWebhookStatus());
        }
        if (!this.outputAudio_.isEmpty()) {
            codedOutputStream.writeBytes(4, this.outputAudio_);
        }
        for (int i = 0; i < this.alternativeQueryResults_.size(); i++) {
            codedOutputStream.writeMessage(5, this.alternativeQueryResults_.get(i));
        }
        if (this.outputAudioConfig_ != null) {
            codedOutputStream.writeMessage(6, getOutputAudioConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getResponseIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.responseId_) + 0 : 0;
        if (this.queryResult_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getQueryResult());
        }
        if (this.webhookStatus_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, getWebhookStatus());
        }
        if (!this.outputAudio_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeBytesSize(4, this.outputAudio_);
        }
        for (int i2 = 0; i2 < this.alternativeQueryResults_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, this.alternativeQueryResults_.get(i2));
        }
        if (this.outputAudioConfig_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(6, getOutputAudioConfig());
        }
        int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DetectIntentResponse)) {
            return super.equals(obj);
        }
        DetectIntentResponse detectIntentResponse = (DetectIntentResponse) obj;
        if (!getResponseId().equals(detectIntentResponse.getResponseId()) || hasQueryResult() != detectIntentResponse.hasQueryResult()) {
            return false;
        }
        if ((hasQueryResult() && !getQueryResult().equals(detectIntentResponse.getQueryResult())) || !getAlternativeQueryResultsList().equals(detectIntentResponse.getAlternativeQueryResultsList()) || hasWebhookStatus() != detectIntentResponse.hasWebhookStatus()) {
            return false;
        }
        if ((!hasWebhookStatus() || getWebhookStatus().equals(detectIntentResponse.getWebhookStatus())) && getOutputAudio().equals(detectIntentResponse.getOutputAudio()) && hasOutputAudioConfig() == detectIntentResponse.hasOutputAudioConfig()) {
            return (!hasOutputAudioConfig() || getOutputAudioConfig().equals(detectIntentResponse.getOutputAudioConfig())) && this.unknownFields.equals(detectIntentResponse.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getResponseId().hashCode();
        if (hasQueryResult()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getQueryResult().hashCode();
        }
        if (getAlternativeQueryResultsCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getAlternativeQueryResultsList().hashCode();
        }
        if (hasWebhookStatus()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getWebhookStatus().hashCode();
        }
        int hashCode2 = (((hashCode * 37) + 4) * 53) + getOutputAudio().hashCode();
        if (hasOutputAudioConfig()) {
            hashCode2 = (((hashCode2 * 37) + 6) * 53) + getOutputAudioConfig().hashCode();
        }
        int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    public static DetectIntentResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static DetectIntentResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DetectIntentResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static DetectIntentResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DetectIntentResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static DetectIntentResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DetectIntentResponse parseFrom(InputStream inputStream) throws IOException {
        return (DetectIntentResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DetectIntentResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DetectIntentResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DetectIntentResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DetectIntentResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DetectIntentResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DetectIntentResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DetectIntentResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DetectIntentResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DetectIntentResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DetectIntentResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DetectIntentResponse detectIntentResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(detectIntentResponse);
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
    /* loaded from: classes3.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DetectIntentResponseOrBuilder {
        private RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> alternativeQueryResultsBuilder_;
        private List<QueryResult> alternativeQueryResults_;
        private int bitField0_;
        private SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> outputAudioConfigBuilder_;
        private OutputAudioConfig outputAudioConfig_;
        private ByteString outputAudio_;
        private SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> queryResultBuilder_;
        private QueryResult queryResult_;
        private Object responseId_;
        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> webhookStatusBuilder_;
        private Status webhookStatus_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1838x9133722c;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1839xc6d3acaa.ensureFieldAccessorsInitialized(DetectIntentResponse.class, Builder.class);
        }

        private Builder() {
            this.responseId_ = "";
            this.alternativeQueryResults_ = Collections.emptyList();
            this.outputAudio_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.responseId_ = "";
            this.alternativeQueryResults_ = Collections.emptyList();
            this.outputAudio_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (DetectIntentResponse.alwaysUseFieldBuilders) {
                getAlternativeQueryResultsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.responseId_ = "";
            if (this.queryResultBuilder_ == null) {
                this.queryResult_ = null;
            } else {
                this.queryResult_ = null;
                this.queryResultBuilder_ = null;
            }
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.alternativeQueryResults_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.webhookStatusBuilder_ == null) {
                this.webhookStatus_ = null;
            } else {
                this.webhookStatus_ = null;
                this.webhookStatusBuilder_ = null;
            }
            this.outputAudio_ = ByteString.EMPTY;
            if (this.outputAudioConfigBuilder_ == null) {
                this.outputAudioConfig_ = null;
            } else {
                this.outputAudioConfig_ = null;
                this.outputAudioConfigBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.f1838x9133722c;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DetectIntentResponse getDefaultInstanceForType() {
            return DetectIntentResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DetectIntentResponse build() {
            DetectIntentResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DetectIntentResponse buildPartial() {
            DetectIntentResponse detectIntentResponse = new DetectIntentResponse(this);
            int i = this.bitField0_;
            detectIntentResponse.responseId_ = this.responseId_;
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                detectIntentResponse.queryResult_ = this.queryResult_;
            } else {
                detectIntentResponse.queryResult_ = singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                detectIntentResponse.alternativeQueryResults_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.alternativeQueryResults_ = Collections.unmodifiableList(this.alternativeQueryResults_);
                    this.bitField0_ &= -2;
                }
                detectIntentResponse.alternativeQueryResults_ = this.alternativeQueryResults_;
            }
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV32 = this.webhookStatusBuilder_;
            if (singleFieldBuilderV32 == null) {
                detectIntentResponse.webhookStatus_ = this.webhookStatus_;
            } else {
                detectIntentResponse.webhookStatus_ = singleFieldBuilderV32.build();
            }
            detectIntentResponse.outputAudio_ = this.outputAudio_;
            SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> singleFieldBuilderV33 = this.outputAudioConfigBuilder_;
            if (singleFieldBuilderV33 == null) {
                detectIntentResponse.outputAudioConfig_ = this.outputAudioConfig_;
            } else {
                detectIntentResponse.outputAudioConfig_ = singleFieldBuilderV33.build();
            }
            onBuilt();
            return detectIntentResponse;
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
            if (message instanceof DetectIntentResponse) {
                return mergeFrom((DetectIntentResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DetectIntentResponse detectIntentResponse) {
            if (detectIntentResponse == DetectIntentResponse.getDefaultInstance()) {
                return this;
            }
            if (!detectIntentResponse.getResponseId().isEmpty()) {
                this.responseId_ = detectIntentResponse.responseId_;
                onChanged();
            }
            if (detectIntentResponse.hasQueryResult()) {
                mergeQueryResult(detectIntentResponse.getQueryResult());
            }
            if (this.alternativeQueryResultsBuilder_ == null) {
                if (!detectIntentResponse.alternativeQueryResults_.isEmpty()) {
                    if (this.alternativeQueryResults_.isEmpty()) {
                        this.alternativeQueryResults_ = detectIntentResponse.alternativeQueryResults_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureAlternativeQueryResultsIsMutable();
                        this.alternativeQueryResults_.addAll(detectIntentResponse.alternativeQueryResults_);
                    }
                    onChanged();
                }
            } else if (!detectIntentResponse.alternativeQueryResults_.isEmpty()) {
                if (!this.alternativeQueryResultsBuilder_.isEmpty()) {
                    this.alternativeQueryResultsBuilder_.addAllMessages(detectIntentResponse.alternativeQueryResults_);
                } else {
                    this.alternativeQueryResultsBuilder_.dispose();
                    this.alternativeQueryResultsBuilder_ = null;
                    this.alternativeQueryResults_ = detectIntentResponse.alternativeQueryResults_;
                    this.bitField0_ &= -2;
                    this.alternativeQueryResultsBuilder_ = DetectIntentResponse.alwaysUseFieldBuilders ? getAlternativeQueryResultsFieldBuilder() : null;
                }
            }
            if (detectIntentResponse.hasWebhookStatus()) {
                mergeWebhookStatus(detectIntentResponse.getWebhookStatus());
            }
            if (detectIntentResponse.getOutputAudio() != ByteString.EMPTY) {
                setOutputAudio(detectIntentResponse.getOutputAudio());
            }
            if (detectIntentResponse.hasOutputAudioConfig()) {
                mergeOutputAudioConfig(detectIntentResponse.getOutputAudioConfig());
            }
            mergeUnknownFields(detectIntentResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            DetectIntentResponse detectIntentResponse = null;
            try {
                try {
                    DetectIntentResponse detectIntentResponse2 = (DetectIntentResponse) DetectIntentResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (detectIntentResponse2 != null) {
                        mergeFrom(detectIntentResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    DetectIntentResponse detectIntentResponse3 = (DetectIntentResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        detectIntentResponse = detectIntentResponse3;
                        if (detectIntentResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (detectIntentResponse != null) {
                    mergeFrom(detectIntentResponse);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public String getResponseId() {
            Object obj = this.responseId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.responseId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public ByteString getResponseIdBytes() {
            Object obj = this.responseId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.responseId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResponseId(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.responseId_ = str;
            onChanged();
            return this;
        }

        public Builder clearResponseId() {
            this.responseId_ = DetectIntentResponse.getDefaultInstance().getResponseId();
            onChanged();
            return this;
        }

        public Builder setResponseIdBytes(ByteString byteString) {
            if (byteString != null) {
                DetectIntentResponse.checkByteStringIsUtf8(byteString);
                this.responseId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public boolean hasQueryResult() {
            return (this.queryResultBuilder_ == null && this.queryResult_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public QueryResult getQueryResult() {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                QueryResult queryResult = this.queryResult_;
                return queryResult == null ? QueryResult.getDefaultInstance() : queryResult;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setQueryResult(QueryResult queryResult) {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(queryResult);
            } else {
                if (queryResult == null) {
                    throw new NullPointerException();
                }
                this.queryResult_ = queryResult;
                onChanged();
            }
            return this;
        }

        public Builder setQueryResult(QueryResult.Builder builder) {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.queryResult_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeQueryResult(QueryResult queryResult) {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                QueryResult queryResult2 = this.queryResult_;
                if (queryResult2 != null) {
                    this.queryResult_ = QueryResult.newBuilder(queryResult2).mergeFrom(queryResult).buildPartial();
                } else {
                    this.queryResult_ = queryResult;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(queryResult);
            }
            return this;
        }

        public Builder clearQueryResult() {
            if (this.queryResultBuilder_ == null) {
                this.queryResult_ = null;
                onChanged();
            } else {
                this.queryResult_ = null;
                this.queryResultBuilder_ = null;
            }
            return this;
        }

        public QueryResult.Builder getQueryResultBuilder() {
            onChanged();
            return getQueryResultFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public QueryResultOrBuilder getQueryResultOrBuilder() {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            QueryResult queryResult = this.queryResult_;
            return queryResult == null ? QueryResult.getDefaultInstance() : queryResult;
        }

        private SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> getQueryResultFieldBuilder() {
            if (this.queryResultBuilder_ == null) {
                this.queryResultBuilder_ = new SingleFieldBuilderV3<>(getQueryResult(), getParentForChildren(), isClean());
                this.queryResult_ = null;
            }
            return this.queryResultBuilder_;
        }

        private void ensureAlternativeQueryResultsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.alternativeQueryResults_ = new ArrayList(this.alternativeQueryResults_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public List<QueryResult> getAlternativeQueryResultsList() {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.alternativeQueryResults_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public int getAlternativeQueryResultsCount() {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.alternativeQueryResults_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public QueryResult getAlternativeQueryResults(int i) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.alternativeQueryResults_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAlternativeQueryResults(int i, QueryResult queryResult) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, queryResult);
            } else {
                if (queryResult == null) {
                    throw new NullPointerException();
                }
                ensureAlternativeQueryResultsIsMutable();
                this.alternativeQueryResults_.set(i, queryResult);
                onChanged();
            }
            return this;
        }

        public Builder setAlternativeQueryResults(int i, QueryResult.Builder builder) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAlternativeQueryResultsIsMutable();
                this.alternativeQueryResults_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAlternativeQueryResults(QueryResult queryResult) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(queryResult);
            } else {
                if (queryResult == null) {
                    throw new NullPointerException();
                }
                ensureAlternativeQueryResultsIsMutable();
                this.alternativeQueryResults_.add(queryResult);
                onChanged();
            }
            return this;
        }

        public Builder addAlternativeQueryResults(int i, QueryResult queryResult) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, queryResult);
            } else {
                if (queryResult == null) {
                    throw new NullPointerException();
                }
                ensureAlternativeQueryResultsIsMutable();
                this.alternativeQueryResults_.add(i, queryResult);
                onChanged();
            }
            return this;
        }

        public Builder addAlternativeQueryResults(QueryResult.Builder builder) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAlternativeQueryResultsIsMutable();
                this.alternativeQueryResults_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAlternativeQueryResults(int i, QueryResult.Builder builder) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAlternativeQueryResultsIsMutable();
                this.alternativeQueryResults_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAlternativeQueryResults(Iterable<? extends QueryResult> iterable) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAlternativeQueryResultsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.alternativeQueryResults_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAlternativeQueryResults() {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.alternativeQueryResults_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAlternativeQueryResults(int i) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAlternativeQueryResultsIsMutable();
                this.alternativeQueryResults_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public QueryResult.Builder getAlternativeQueryResultsBuilder(int i) {
            return getAlternativeQueryResultsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public QueryResultOrBuilder getAlternativeQueryResultsOrBuilder(int i) {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.alternativeQueryResults_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public List<? extends QueryResultOrBuilder> getAlternativeQueryResultsOrBuilderList() {
            RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> repeatedFieldBuilderV3 = this.alternativeQueryResultsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.alternativeQueryResults_);
        }

        public QueryResult.Builder addAlternativeQueryResultsBuilder() {
            return getAlternativeQueryResultsFieldBuilder().addBuilder(QueryResult.getDefaultInstance());
        }

        public QueryResult.Builder addAlternativeQueryResultsBuilder(int i) {
            return getAlternativeQueryResultsFieldBuilder().addBuilder(i, QueryResult.getDefaultInstance());
        }

        public List<QueryResult.Builder> getAlternativeQueryResultsBuilderList() {
            return getAlternativeQueryResultsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> getAlternativeQueryResultsFieldBuilder() {
            if (this.alternativeQueryResultsBuilder_ == null) {
                this.alternativeQueryResultsBuilder_ = new RepeatedFieldBuilderV3<>(this.alternativeQueryResults_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.alternativeQueryResults_ = null;
            }
            return this.alternativeQueryResultsBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public boolean hasWebhookStatus() {
            return (this.webhookStatusBuilder_ == null && this.webhookStatus_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public Status getWebhookStatus() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.webhookStatusBuilder_;
            if (singleFieldBuilderV3 == null) {
                Status status = this.webhookStatus_;
                return status == null ? Status.getDefaultInstance() : status;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setWebhookStatus(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.webhookStatusBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(status);
            } else {
                if (status == null) {
                    throw new NullPointerException();
                }
                this.webhookStatus_ = status;
                onChanged();
            }
            return this;
        }

        public Builder setWebhookStatus(Status.Builder builder) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.webhookStatusBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.webhookStatus_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeWebhookStatus(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.webhookStatusBuilder_;
            if (singleFieldBuilderV3 == null) {
                Status status2 = this.webhookStatus_;
                if (status2 != null) {
                    this.webhookStatus_ = Status.newBuilder(status2).mergeFrom(status).buildPartial();
                } else {
                    this.webhookStatus_ = status;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(status);
            }
            return this;
        }

        public Builder clearWebhookStatus() {
            if (this.webhookStatusBuilder_ == null) {
                this.webhookStatus_ = null;
                onChanged();
            } else {
                this.webhookStatus_ = null;
                this.webhookStatusBuilder_ = null;
            }
            return this;
        }

        public Status.Builder getWebhookStatusBuilder() {
            onChanged();
            return getWebhookStatusFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public StatusOrBuilder getWebhookStatusOrBuilder() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.webhookStatusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Status status = this.webhookStatus_;
            return status == null ? Status.getDefaultInstance() : status;
        }

        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> getWebhookStatusFieldBuilder() {
            if (this.webhookStatusBuilder_ == null) {
                this.webhookStatusBuilder_ = new SingleFieldBuilderV3<>(getWebhookStatus(), getParentForChildren(), isClean());
                this.webhookStatus_ = null;
            }
            return this.webhookStatusBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public ByteString getOutputAudio() {
            return this.outputAudio_;
        }

        public Builder setOutputAudio(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.outputAudio_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearOutputAudio() {
            this.outputAudio_ = DetectIntentResponse.getDefaultInstance().getOutputAudio();
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public boolean hasOutputAudioConfig() {
            return (this.outputAudioConfigBuilder_ == null && this.outputAudioConfig_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public OutputAudioConfig getOutputAudioConfig() {
            SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                OutputAudioConfig outputAudioConfig = this.outputAudioConfig_;
                return outputAudioConfig == null ? OutputAudioConfig.getDefaultInstance() : outputAudioConfig;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setOutputAudioConfig(OutputAudioConfig outputAudioConfig) {
            SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(outputAudioConfig);
            } else {
                if (outputAudioConfig == null) {
                    throw new NullPointerException();
                }
                this.outputAudioConfig_ = outputAudioConfig;
                onChanged();
            }
            return this;
        }

        public Builder setOutputAudioConfig(OutputAudioConfig.Builder builder) {
            SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.outputAudioConfig_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeOutputAudioConfig(OutputAudioConfig outputAudioConfig) {
            SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                OutputAudioConfig outputAudioConfig2 = this.outputAudioConfig_;
                if (outputAudioConfig2 != null) {
                    this.outputAudioConfig_ = OutputAudioConfig.newBuilder(outputAudioConfig2).mergeFrom(outputAudioConfig).buildPartial();
                } else {
                    this.outputAudioConfig_ = outputAudioConfig;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(outputAudioConfig);
            }
            return this;
        }

        public Builder clearOutputAudioConfig() {
            if (this.outputAudioConfigBuilder_ == null) {
                this.outputAudioConfig_ = null;
                onChanged();
            } else {
                this.outputAudioConfig_ = null;
                this.outputAudioConfigBuilder_ = null;
            }
            return this;
        }

        public OutputAudioConfig.Builder getOutputAudioConfigBuilder() {
            onChanged();
            return getOutputAudioConfigFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DetectIntentResponseOrBuilder
        public OutputAudioConfigOrBuilder getOutputAudioConfigOrBuilder() {
            SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            OutputAudioConfig outputAudioConfig = this.outputAudioConfig_;
            return outputAudioConfig == null ? OutputAudioConfig.getDefaultInstance() : outputAudioConfig;
        }

        private SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> getOutputAudioConfigFieldBuilder() {
            if (this.outputAudioConfigBuilder_ == null) {
                this.outputAudioConfigBuilder_ = new SingleFieldBuilderV3<>(getOutputAudioConfig(), getParentForChildren(), isClean());
                this.outputAudioConfig_ = null;
            }
            return this.outputAudioConfigBuilder_;
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

    public static DetectIntentResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DetectIntentResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<DetectIntentResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public DetectIntentResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
