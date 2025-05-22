package com.google.cloud.dialogflow.v2beta1;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class SentimentAnalysisRequestConfig extends GeneratedMessageV3 implements SentimentAnalysisRequestConfigOrBuilder {
    public static final int ANALYZE_QUERY_TEXT_SENTIMENT_FIELD_NUMBER = 1;
    private static final SentimentAnalysisRequestConfig DEFAULT_INSTANCE = new SentimentAnalysisRequestConfig();
    private static final Parser<SentimentAnalysisRequestConfig> PARSER = new AbstractParser<SentimentAnalysisRequestConfig>() { // from class: com.google.cloud.dialogflow.v2beta1.SentimentAnalysisRequestConfig.1
        @Override // com.google.protobuf.Parser
        public SentimentAnalysisRequestConfig parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SentimentAnalysisRequestConfig(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private boolean analyzeQueryTextSentiment_;
    private byte memoizedIsInitialized;

    private SentimentAnalysisRequestConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SentimentAnalysisRequestConfig() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SentimentAnalysisRequestConfig();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SentimentAnalysisRequestConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 8) {
                            this.analyzeQueryTextSentiment_ = codedInputStream.readBool();
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionProto.f1854x2018d1a6;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1855xae86f224.ensureFieldAccessorsInitialized(SentimentAnalysisRequestConfig.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SentimentAnalysisRequestConfigOrBuilder
    public boolean getAnalyzeQueryTextSentiment() {
        return this.analyzeQueryTextSentiment_;
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
        boolean z = this.analyzeQueryTextSentiment_;
        if (z) {
            codedOutputStream.writeBool(1, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        boolean z = this.analyzeQueryTextSentiment_;
        int computeBoolSize = (z ? 0 + CodedOutputStream.computeBoolSize(1, z) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = computeBoolSize;
        return computeBoolSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SentimentAnalysisRequestConfig)) {
            return super.equals(obj);
        }
        SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig = (SentimentAnalysisRequestConfig) obj;
        return getAnalyzeQueryTextSentiment() == sentimentAnalysisRequestConfig.getAnalyzeQueryTextSentiment() && this.unknownFields.equals(sentimentAnalysisRequestConfig.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getAnalyzeQueryTextSentiment())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static SentimentAnalysisRequestConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SentimentAnalysisRequestConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SentimentAnalysisRequestConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SentimentAnalysisRequestConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SentimentAnalysisRequestConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SentimentAnalysisRequestConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SentimentAnalysisRequestConfig parseFrom(InputStream inputStream) throws IOException {
        return (SentimentAnalysisRequestConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SentimentAnalysisRequestConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SentimentAnalysisRequestConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SentimentAnalysisRequestConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SentimentAnalysisRequestConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SentimentAnalysisRequestConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SentimentAnalysisRequestConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SentimentAnalysisRequestConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SentimentAnalysisRequestConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SentimentAnalysisRequestConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SentimentAnalysisRequestConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(sentimentAnalysisRequestConfig);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SentimentAnalysisRequestConfigOrBuilder {
        private boolean analyzeQueryTextSentiment_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1854x2018d1a6;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1855xae86f224.ensureFieldAccessorsInitialized(SentimentAnalysisRequestConfig.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SentimentAnalysisRequestConfig.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.analyzeQueryTextSentiment_ = false;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.f1854x2018d1a6;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SentimentAnalysisRequestConfig getDefaultInstanceForType() {
            return SentimentAnalysisRequestConfig.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SentimentAnalysisRequestConfig build() {
            SentimentAnalysisRequestConfig buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SentimentAnalysisRequestConfig buildPartial() {
            SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig = new SentimentAnalysisRequestConfig(this);
            sentimentAnalysisRequestConfig.analyzeQueryTextSentiment_ = this.analyzeQueryTextSentiment_;
            onBuilt();
            return sentimentAnalysisRequestConfig;
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
            if (message instanceof SentimentAnalysisRequestConfig) {
                return mergeFrom((SentimentAnalysisRequestConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig) {
            if (sentimentAnalysisRequestConfig == SentimentAnalysisRequestConfig.getDefaultInstance()) {
                return this;
            }
            if (sentimentAnalysisRequestConfig.getAnalyzeQueryTextSentiment()) {
                setAnalyzeQueryTextSentiment(sentimentAnalysisRequestConfig.getAnalyzeQueryTextSentiment());
            }
            mergeUnknownFields(sentimentAnalysisRequestConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig = null;
            try {
                try {
                    SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig2 = (SentimentAnalysisRequestConfig) SentimentAnalysisRequestConfig.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (sentimentAnalysisRequestConfig2 != null) {
                        mergeFrom(sentimentAnalysisRequestConfig2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig3 = (SentimentAnalysisRequestConfig) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        sentimentAnalysisRequestConfig = sentimentAnalysisRequestConfig3;
                        if (sentimentAnalysisRequestConfig != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (sentimentAnalysisRequestConfig != null) {
                    mergeFrom(sentimentAnalysisRequestConfig);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SentimentAnalysisRequestConfigOrBuilder
        public boolean getAnalyzeQueryTextSentiment() {
            return this.analyzeQueryTextSentiment_;
        }

        public Builder setAnalyzeQueryTextSentiment(boolean z) {
            this.analyzeQueryTextSentiment_ = z;
            onChanged();
            return this;
        }

        public Builder clearAnalyzeQueryTextSentiment() {
            this.analyzeQueryTextSentiment_ = false;
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

    public static SentimentAnalysisRequestConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SentimentAnalysisRequestConfig> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SentimentAnalysisRequestConfig> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SentimentAnalysisRequestConfig getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
