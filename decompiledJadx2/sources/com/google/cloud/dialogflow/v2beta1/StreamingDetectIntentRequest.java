package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.OutputAudioConfig;
import com.google.cloud.dialogflow.v2beta1.QueryInput;
import com.google.cloud.dialogflow.v2beta1.QueryParameters;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class StreamingDetectIntentRequest extends GeneratedMessageV3 implements StreamingDetectIntentRequestOrBuilder {
    public static final int INPUT_AUDIO_FIELD_NUMBER = 6;
    public static final int OUTPUT_AUDIO_CONFIG_FIELD_NUMBER = 5;
    public static final int OUTPUT_AUDIO_CONFIG_MASK_FIELD_NUMBER = 7;
    public static final int QUERY_INPUT_FIELD_NUMBER = 3;
    public static final int QUERY_PARAMS_FIELD_NUMBER = 2;
    public static final int SESSION_FIELD_NUMBER = 1;
    public static final int SINGLE_UTTERANCE_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private ByteString inputAudio_;
    private byte memoizedIsInitialized;
    private FieldMask outputAudioConfigMask_;
    private OutputAudioConfig outputAudioConfig_;
    private QueryInput queryInput_;
    private QueryParameters queryParams_;
    private volatile Object session_;
    private boolean singleUtterance_;
    private static final StreamingDetectIntentRequest DEFAULT_INSTANCE = new StreamingDetectIntentRequest();
    private static final Parser<StreamingDetectIntentRequest> PARSER = new AbstractParser<StreamingDetectIntentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequest.1
        @Override // com.google.protobuf.Parser
        public StreamingDetectIntentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new StreamingDetectIntentRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private StreamingDetectIntentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private StreamingDetectIntentRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.session_ = "";
        this.inputAudio_ = ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new StreamingDetectIntentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private StreamingDetectIntentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag != 10) {
                                if (readTag == 18) {
                                    QueryParameters.Builder builder = this.queryParams_ != null ? this.queryParams_.toBuilder() : null;
                                    this.queryParams_ = (QueryParameters) codedInputStream.readMessage(QueryParameters.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.queryParams_);
                                        this.queryParams_ = builder.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    QueryInput.Builder builder2 = this.queryInput_ != null ? this.queryInput_.toBuilder() : null;
                                    this.queryInput_ = (QueryInput) codedInputStream.readMessage(QueryInput.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.queryInput_);
                                        this.queryInput_ = builder2.buildPartial();
                                    }
                                } else if (readTag == 32) {
                                    this.singleUtterance_ = codedInputStream.readBool();
                                } else if (readTag == 42) {
                                    OutputAudioConfig.Builder builder3 = this.outputAudioConfig_ != null ? this.outputAudioConfig_.toBuilder() : null;
                                    this.outputAudioConfig_ = (OutputAudioConfig) codedInputStream.readMessage(OutputAudioConfig.parser(), extensionRegistryLite);
                                    if (builder3 != null) {
                                        builder3.mergeFrom(this.outputAudioConfig_);
                                        this.outputAudioConfig_ = builder3.buildPartial();
                                    }
                                } else if (readTag == 50) {
                                    this.inputAudio_ = codedInputStream.readBytes();
                                } else if (readTag == 58) {
                                    FieldMask.Builder builder4 = this.outputAudioConfigMask_ != null ? this.outputAudioConfigMask_.toBuilder() : null;
                                    this.outputAudioConfigMask_ = (FieldMask) codedInputStream.readMessage(FieldMask.parser(), extensionRegistryLite);
                                    if (builder4 != null) {
                                        builder4.mergeFrom(this.outputAudioConfigMask_);
                                        this.outputAudioConfigMask_ = builder4.buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.session_ = codedInputStream.readStringRequireUtf8();
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
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionProto.f1860x89fc013e;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1861x1e6d09bc.ensureFieldAccessorsInitialized(StreamingDetectIntentRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public String getSession() {
        Object obj = this.session_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.session_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public ByteString getSessionBytes() {
        Object obj = this.session_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.session_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public boolean hasQueryParams() {
        return this.queryParams_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public QueryParameters getQueryParams() {
        QueryParameters queryParameters = this.queryParams_;
        return queryParameters == null ? QueryParameters.getDefaultInstance() : queryParameters;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public QueryParametersOrBuilder getQueryParamsOrBuilder() {
        return getQueryParams();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public boolean hasQueryInput() {
        return this.queryInput_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public QueryInput getQueryInput() {
        QueryInput queryInput = this.queryInput_;
        return queryInput == null ? QueryInput.getDefaultInstance() : queryInput;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public QueryInputOrBuilder getQueryInputOrBuilder() {
        return getQueryInput();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public boolean getSingleUtterance() {
        return this.singleUtterance_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public boolean hasOutputAudioConfig() {
        return this.outputAudioConfig_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public OutputAudioConfig getOutputAudioConfig() {
        OutputAudioConfig outputAudioConfig = this.outputAudioConfig_;
        return outputAudioConfig == null ? OutputAudioConfig.getDefaultInstance() : outputAudioConfig;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public OutputAudioConfigOrBuilder getOutputAudioConfigOrBuilder() {
        return getOutputAudioConfig();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public boolean hasOutputAudioConfigMask() {
        return this.outputAudioConfigMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public FieldMask getOutputAudioConfigMask() {
        FieldMask fieldMask = this.outputAudioConfigMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public FieldMaskOrBuilder getOutputAudioConfigMaskOrBuilder() {
        return getOutputAudioConfigMask();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
    public ByteString getInputAudio() {
        return this.inputAudio_;
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
        if (!getSessionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.session_);
        }
        if (this.queryParams_ != null) {
            codedOutputStream.writeMessage(2, getQueryParams());
        }
        if (this.queryInput_ != null) {
            codedOutputStream.writeMessage(3, getQueryInput());
        }
        boolean z = this.singleUtterance_;
        if (z) {
            codedOutputStream.writeBool(4, z);
        }
        if (this.outputAudioConfig_ != null) {
            codedOutputStream.writeMessage(5, getOutputAudioConfig());
        }
        if (!this.inputAudio_.isEmpty()) {
            codedOutputStream.writeBytes(6, this.inputAudio_);
        }
        if (this.outputAudioConfigMask_ != null) {
            codedOutputStream.writeMessage(7, getOutputAudioConfigMask());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getSessionBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.session_);
        if (this.queryParams_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getQueryParams());
        }
        if (this.queryInput_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, getQueryInput());
        }
        boolean z = this.singleUtterance_;
        if (z) {
            computeStringSize += CodedOutputStream.computeBoolSize(4, z);
        }
        if (this.outputAudioConfig_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, getOutputAudioConfig());
        }
        if (!this.inputAudio_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeBytesSize(6, this.inputAudio_);
        }
        if (this.outputAudioConfigMask_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(7, getOutputAudioConfigMask());
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
        if (!(obj instanceof StreamingDetectIntentRequest)) {
            return super.equals(obj);
        }
        StreamingDetectIntentRequest streamingDetectIntentRequest = (StreamingDetectIntentRequest) obj;
        if (!getSession().equals(streamingDetectIntentRequest.getSession()) || hasQueryParams() != streamingDetectIntentRequest.hasQueryParams()) {
            return false;
        }
        if ((hasQueryParams() && !getQueryParams().equals(streamingDetectIntentRequest.getQueryParams())) || hasQueryInput() != streamingDetectIntentRequest.hasQueryInput()) {
            return false;
        }
        if ((hasQueryInput() && !getQueryInput().equals(streamingDetectIntentRequest.getQueryInput())) || getSingleUtterance() != streamingDetectIntentRequest.getSingleUtterance() || hasOutputAudioConfig() != streamingDetectIntentRequest.hasOutputAudioConfig()) {
            return false;
        }
        if ((!hasOutputAudioConfig() || getOutputAudioConfig().equals(streamingDetectIntentRequest.getOutputAudioConfig())) && hasOutputAudioConfigMask() == streamingDetectIntentRequest.hasOutputAudioConfigMask()) {
            return (!hasOutputAudioConfigMask() || getOutputAudioConfigMask().equals(streamingDetectIntentRequest.getOutputAudioConfigMask())) && getInputAudio().equals(streamingDetectIntentRequest.getInputAudio()) && this.unknownFields.equals(streamingDetectIntentRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSession().hashCode();
        if (hasQueryParams()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getQueryParams().hashCode();
        }
        if (hasQueryInput()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getQueryInput().hashCode();
        }
        int hashBoolean = (((hashCode * 37) + 4) * 53) + Internal.hashBoolean(getSingleUtterance());
        if (hasOutputAudioConfig()) {
            hashBoolean = (((hashBoolean * 37) + 5) * 53) + getOutputAudioConfig().hashCode();
        }
        if (hasOutputAudioConfigMask()) {
            hashBoolean = (((hashBoolean * 37) + 7) * 53) + getOutputAudioConfigMask().hashCode();
        }
        int hashCode2 = (((((hashBoolean * 37) + 6) * 53) + getInputAudio().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static StreamingDetectIntentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static StreamingDetectIntentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static StreamingDetectIntentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static StreamingDetectIntentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static StreamingDetectIntentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static StreamingDetectIntentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static StreamingDetectIntentRequest parseFrom(InputStream inputStream) throws IOException {
        return (StreamingDetectIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static StreamingDetectIntentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamingDetectIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StreamingDetectIntentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StreamingDetectIntentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static StreamingDetectIntentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamingDetectIntentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StreamingDetectIntentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StreamingDetectIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static StreamingDetectIntentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamingDetectIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StreamingDetectIntentRequest streamingDetectIntentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(streamingDetectIntentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StreamingDetectIntentRequestOrBuilder {
        private ByteString inputAudio_;
        private SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> outputAudioConfigBuilder_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> outputAudioConfigMaskBuilder_;
        private FieldMask outputAudioConfigMask_;
        private OutputAudioConfig outputAudioConfig_;
        private SingleFieldBuilderV3<QueryInput, QueryInput.Builder, QueryInputOrBuilder> queryInputBuilder_;
        private QueryInput queryInput_;
        private SingleFieldBuilderV3<QueryParameters, QueryParameters.Builder, QueryParametersOrBuilder> queryParamsBuilder_;
        private QueryParameters queryParams_;
        private Object session_;
        private boolean singleUtterance_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1860x89fc013e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1861x1e6d09bc.ensureFieldAccessorsInitialized(StreamingDetectIntentRequest.class, Builder.class);
        }

        private Builder() {
            this.session_ = "";
            this.inputAudio_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.session_ = "";
            this.inputAudio_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = StreamingDetectIntentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.session_ = "";
            if (this.queryParamsBuilder_ == null) {
                this.queryParams_ = null;
            } else {
                this.queryParams_ = null;
                this.queryParamsBuilder_ = null;
            }
            if (this.queryInputBuilder_ == null) {
                this.queryInput_ = null;
            } else {
                this.queryInput_ = null;
                this.queryInputBuilder_ = null;
            }
            this.singleUtterance_ = false;
            if (this.outputAudioConfigBuilder_ == null) {
                this.outputAudioConfig_ = null;
            } else {
                this.outputAudioConfig_ = null;
                this.outputAudioConfigBuilder_ = null;
            }
            if (this.outputAudioConfigMaskBuilder_ == null) {
                this.outputAudioConfigMask_ = null;
            } else {
                this.outputAudioConfigMask_ = null;
                this.outputAudioConfigMaskBuilder_ = null;
            }
            this.inputAudio_ = ByteString.EMPTY;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.f1860x89fc013e;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public StreamingDetectIntentRequest getDefaultInstanceForType() {
            return StreamingDetectIntentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public StreamingDetectIntentRequest build() {
            StreamingDetectIntentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public StreamingDetectIntentRequest buildPartial() {
            StreamingDetectIntentRequest streamingDetectIntentRequest = new StreamingDetectIntentRequest(this);
            streamingDetectIntentRequest.session_ = this.session_;
            SingleFieldBuilderV3<QueryParameters, QueryParameters.Builder, QueryParametersOrBuilder> singleFieldBuilderV3 = this.queryParamsBuilder_;
            if (singleFieldBuilderV3 == null) {
                streamingDetectIntentRequest.queryParams_ = this.queryParams_;
            } else {
                streamingDetectIntentRequest.queryParams_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<QueryInput, QueryInput.Builder, QueryInputOrBuilder> singleFieldBuilderV32 = this.queryInputBuilder_;
            if (singleFieldBuilderV32 == null) {
                streamingDetectIntentRequest.queryInput_ = this.queryInput_;
            } else {
                streamingDetectIntentRequest.queryInput_ = singleFieldBuilderV32.build();
            }
            streamingDetectIntentRequest.singleUtterance_ = this.singleUtterance_;
            SingleFieldBuilderV3<OutputAudioConfig, OutputAudioConfig.Builder, OutputAudioConfigOrBuilder> singleFieldBuilderV33 = this.outputAudioConfigBuilder_;
            if (singleFieldBuilderV33 == null) {
                streamingDetectIntentRequest.outputAudioConfig_ = this.outputAudioConfig_;
            } else {
                streamingDetectIntentRequest.outputAudioConfig_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV34 = this.outputAudioConfigMaskBuilder_;
            if (singleFieldBuilderV34 == null) {
                streamingDetectIntentRequest.outputAudioConfigMask_ = this.outputAudioConfigMask_;
            } else {
                streamingDetectIntentRequest.outputAudioConfigMask_ = singleFieldBuilderV34.build();
            }
            streamingDetectIntentRequest.inputAudio_ = this.inputAudio_;
            onBuilt();
            return streamingDetectIntentRequest;
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
            if (message instanceof StreamingDetectIntentRequest) {
                return mergeFrom((StreamingDetectIntentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(StreamingDetectIntentRequest streamingDetectIntentRequest) {
            if (streamingDetectIntentRequest == StreamingDetectIntentRequest.getDefaultInstance()) {
                return this;
            }
            if (!streamingDetectIntentRequest.getSession().isEmpty()) {
                this.session_ = streamingDetectIntentRequest.session_;
                onChanged();
            }
            if (streamingDetectIntentRequest.hasQueryParams()) {
                mergeQueryParams(streamingDetectIntentRequest.getQueryParams());
            }
            if (streamingDetectIntentRequest.hasQueryInput()) {
                mergeQueryInput(streamingDetectIntentRequest.getQueryInput());
            }
            if (streamingDetectIntentRequest.getSingleUtterance()) {
                setSingleUtterance(streamingDetectIntentRequest.getSingleUtterance());
            }
            if (streamingDetectIntentRequest.hasOutputAudioConfig()) {
                mergeOutputAudioConfig(streamingDetectIntentRequest.getOutputAudioConfig());
            }
            if (streamingDetectIntentRequest.hasOutputAudioConfigMask()) {
                mergeOutputAudioConfigMask(streamingDetectIntentRequest.getOutputAudioConfigMask());
            }
            if (streamingDetectIntentRequest.getInputAudio() != ByteString.EMPTY) {
                setInputAudio(streamingDetectIntentRequest.getInputAudio());
            }
            mergeUnknownFields(streamingDetectIntentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            StreamingDetectIntentRequest streamingDetectIntentRequest = null;
            try {
                try {
                    StreamingDetectIntentRequest streamingDetectIntentRequest2 = (StreamingDetectIntentRequest) StreamingDetectIntentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (streamingDetectIntentRequest2 != null) {
                        mergeFrom(streamingDetectIntentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    StreamingDetectIntentRequest streamingDetectIntentRequest3 = (StreamingDetectIntentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        streamingDetectIntentRequest = streamingDetectIntentRequest3;
                        if (streamingDetectIntentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (streamingDetectIntentRequest != null) {
                    mergeFrom(streamingDetectIntentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public String getSession() {
            Object obj = this.session_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.session_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public ByteString getSessionBytes() {
            Object obj = this.session_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.session_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSession(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.session_ = str;
            onChanged();
            return this;
        }

        public Builder clearSession() {
            this.session_ = StreamingDetectIntentRequest.getDefaultInstance().getSession();
            onChanged();
            return this;
        }

        public Builder setSessionBytes(ByteString byteString) {
            if (byteString != null) {
                StreamingDetectIntentRequest.checkByteStringIsUtf8(byteString);
                this.session_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public boolean hasQueryParams() {
            return (this.queryParamsBuilder_ == null && this.queryParams_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public QueryParameters getQueryParams() {
            SingleFieldBuilderV3<QueryParameters, QueryParameters.Builder, QueryParametersOrBuilder> singleFieldBuilderV3 = this.queryParamsBuilder_;
            if (singleFieldBuilderV3 == null) {
                QueryParameters queryParameters = this.queryParams_;
                return queryParameters == null ? QueryParameters.getDefaultInstance() : queryParameters;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setQueryParams(QueryParameters queryParameters) {
            SingleFieldBuilderV3<QueryParameters, QueryParameters.Builder, QueryParametersOrBuilder> singleFieldBuilderV3 = this.queryParamsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(queryParameters);
            } else {
                if (queryParameters == null) {
                    throw new NullPointerException();
                }
                this.queryParams_ = queryParameters;
                onChanged();
            }
            return this;
        }

        public Builder setQueryParams(QueryParameters.Builder builder) {
            SingleFieldBuilderV3<QueryParameters, QueryParameters.Builder, QueryParametersOrBuilder> singleFieldBuilderV3 = this.queryParamsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.queryParams_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeQueryParams(QueryParameters queryParameters) {
            SingleFieldBuilderV3<QueryParameters, QueryParameters.Builder, QueryParametersOrBuilder> singleFieldBuilderV3 = this.queryParamsBuilder_;
            if (singleFieldBuilderV3 == null) {
                QueryParameters queryParameters2 = this.queryParams_;
                if (queryParameters2 != null) {
                    this.queryParams_ = QueryParameters.newBuilder(queryParameters2).mergeFrom(queryParameters).buildPartial();
                } else {
                    this.queryParams_ = queryParameters;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(queryParameters);
            }
            return this;
        }

        public Builder clearQueryParams() {
            if (this.queryParamsBuilder_ == null) {
                this.queryParams_ = null;
                onChanged();
            } else {
                this.queryParams_ = null;
                this.queryParamsBuilder_ = null;
            }
            return this;
        }

        public QueryParameters.Builder getQueryParamsBuilder() {
            onChanged();
            return getQueryParamsFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public QueryParametersOrBuilder getQueryParamsOrBuilder() {
            SingleFieldBuilderV3<QueryParameters, QueryParameters.Builder, QueryParametersOrBuilder> singleFieldBuilderV3 = this.queryParamsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            QueryParameters queryParameters = this.queryParams_;
            return queryParameters == null ? QueryParameters.getDefaultInstance() : queryParameters;
        }

        private SingleFieldBuilderV3<QueryParameters, QueryParameters.Builder, QueryParametersOrBuilder> getQueryParamsFieldBuilder() {
            if (this.queryParamsBuilder_ == null) {
                this.queryParamsBuilder_ = new SingleFieldBuilderV3<>(getQueryParams(), getParentForChildren(), isClean());
                this.queryParams_ = null;
            }
            return this.queryParamsBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public boolean hasQueryInput() {
            return (this.queryInputBuilder_ == null && this.queryInput_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public QueryInput getQueryInput() {
            SingleFieldBuilderV3<QueryInput, QueryInput.Builder, QueryInputOrBuilder> singleFieldBuilderV3 = this.queryInputBuilder_;
            if (singleFieldBuilderV3 == null) {
                QueryInput queryInput = this.queryInput_;
                return queryInput == null ? QueryInput.getDefaultInstance() : queryInput;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setQueryInput(QueryInput queryInput) {
            SingleFieldBuilderV3<QueryInput, QueryInput.Builder, QueryInputOrBuilder> singleFieldBuilderV3 = this.queryInputBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(queryInput);
            } else {
                if (queryInput == null) {
                    throw new NullPointerException();
                }
                this.queryInput_ = queryInput;
                onChanged();
            }
            return this;
        }

        public Builder setQueryInput(QueryInput.Builder builder) {
            SingleFieldBuilderV3<QueryInput, QueryInput.Builder, QueryInputOrBuilder> singleFieldBuilderV3 = this.queryInputBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.queryInput_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeQueryInput(QueryInput queryInput) {
            SingleFieldBuilderV3<QueryInput, QueryInput.Builder, QueryInputOrBuilder> singleFieldBuilderV3 = this.queryInputBuilder_;
            if (singleFieldBuilderV3 == null) {
                QueryInput queryInput2 = this.queryInput_;
                if (queryInput2 != null) {
                    this.queryInput_ = QueryInput.newBuilder(queryInput2).mergeFrom(queryInput).buildPartial();
                } else {
                    this.queryInput_ = queryInput;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(queryInput);
            }
            return this;
        }

        public Builder clearQueryInput() {
            if (this.queryInputBuilder_ == null) {
                this.queryInput_ = null;
                onChanged();
            } else {
                this.queryInput_ = null;
                this.queryInputBuilder_ = null;
            }
            return this;
        }

        public QueryInput.Builder getQueryInputBuilder() {
            onChanged();
            return getQueryInputFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public QueryInputOrBuilder getQueryInputOrBuilder() {
            SingleFieldBuilderV3<QueryInput, QueryInput.Builder, QueryInputOrBuilder> singleFieldBuilderV3 = this.queryInputBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            QueryInput queryInput = this.queryInput_;
            return queryInput == null ? QueryInput.getDefaultInstance() : queryInput;
        }

        private SingleFieldBuilderV3<QueryInput, QueryInput.Builder, QueryInputOrBuilder> getQueryInputFieldBuilder() {
            if (this.queryInputBuilder_ == null) {
                this.queryInputBuilder_ = new SingleFieldBuilderV3<>(getQueryInput(), getParentForChildren(), isClean());
                this.queryInput_ = null;
            }
            return this.queryInputBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public boolean getSingleUtterance() {
            return this.singleUtterance_;
        }

        public Builder setSingleUtterance(boolean z) {
            this.singleUtterance_ = z;
            onChanged();
            return this;
        }

        public Builder clearSingleUtterance() {
            this.singleUtterance_ = false;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public boolean hasOutputAudioConfig() {
            return (this.outputAudioConfigBuilder_ == null && this.outputAudioConfig_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public boolean hasOutputAudioConfigMask() {
            return (this.outputAudioConfigMaskBuilder_ == null && this.outputAudioConfigMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public FieldMask getOutputAudioConfigMask() {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                FieldMask fieldMask = this.outputAudioConfigMask_;
                return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setOutputAudioConfigMask(FieldMask fieldMask) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigMaskBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(fieldMask);
            } else {
                if (fieldMask == null) {
                    throw new NullPointerException();
                }
                this.outputAudioConfigMask_ = fieldMask;
                onChanged();
            }
            return this;
        }

        public Builder setOutputAudioConfigMask(FieldMask.Builder builder) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.outputAudioConfigMask_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeOutputAudioConfigMask(FieldMask fieldMask) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                FieldMask fieldMask2 = this.outputAudioConfigMask_;
                if (fieldMask2 != null) {
                    this.outputAudioConfigMask_ = FieldMask.newBuilder(fieldMask2).mergeFrom(fieldMask).buildPartial();
                } else {
                    this.outputAudioConfigMask_ = fieldMask;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fieldMask);
            }
            return this;
        }

        public Builder clearOutputAudioConfigMask() {
            if (this.outputAudioConfigMaskBuilder_ == null) {
                this.outputAudioConfigMask_ = null;
                onChanged();
            } else {
                this.outputAudioConfigMask_ = null;
                this.outputAudioConfigMaskBuilder_ = null;
            }
            return this;
        }

        public FieldMask.Builder getOutputAudioConfigMaskBuilder() {
            onChanged();
            return getOutputAudioConfigMaskFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public FieldMaskOrBuilder getOutputAudioConfigMaskOrBuilder() {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.outputAudioConfigMaskBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            FieldMask fieldMask = this.outputAudioConfigMask_;
            return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
        }

        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> getOutputAudioConfigMaskFieldBuilder() {
            if (this.outputAudioConfigMaskBuilder_ == null) {
                this.outputAudioConfigMaskBuilder_ = new SingleFieldBuilderV3<>(getOutputAudioConfigMask(), getParentForChildren(), isClean());
                this.outputAudioConfigMask_ = null;
            }
            return this.outputAudioConfigMaskBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequestOrBuilder
        public ByteString getInputAudio() {
            return this.inputAudio_;
        }

        public Builder setInputAudio(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.inputAudio_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearInputAudio() {
            this.inputAudio_ = StreamingDetectIntentRequest.getDefaultInstance().getInputAudio();
            onChanged();
            return this;
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

    public static StreamingDetectIntentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StreamingDetectIntentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<StreamingDetectIntentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public StreamingDetectIntentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
