package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Sentiment;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class SentimentAnalysisResult extends GeneratedMessageV3 implements SentimentAnalysisResultOrBuilder {
    private static final SentimentAnalysisResult DEFAULT_INSTANCE = new SentimentAnalysisResult();
    private static final Parser<SentimentAnalysisResult> PARSER = new AbstractParser<SentimentAnalysisResult>() { // from class: com.google.cloud.dialogflow.v2beta1.SentimentAnalysisResult.1
        @Override // com.google.protobuf.Parser
        public SentimentAnalysisResult parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SentimentAnalysisResult(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int QUERY_TEXT_SENTIMENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private Sentiment queryTextSentiment_;

    private SentimentAnalysisResult(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SentimentAnalysisResult() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SentimentAnalysisResult();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SentimentAnalysisResult(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 10) {
                            Sentiment.Builder builder = this.queryTextSentiment_ != null ? this.queryTextSentiment_.toBuilder() : null;
                            this.queryTextSentiment_ = (Sentiment) codedInputStream.readMessage(Sentiment.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.queryTextSentiment_);
                                this.queryTextSentiment_ = builder.buildPartial();
                            }
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
        return SessionProto.f1856x40ea3508;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1857xa777d386.ensureFieldAccessorsInitialized(SentimentAnalysisResult.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SentimentAnalysisResultOrBuilder
    public boolean hasQueryTextSentiment() {
        return this.queryTextSentiment_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SentimentAnalysisResultOrBuilder
    public Sentiment getQueryTextSentiment() {
        Sentiment sentiment = this.queryTextSentiment_;
        return sentiment == null ? Sentiment.getDefaultInstance() : sentiment;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SentimentAnalysisResultOrBuilder
    public SentimentOrBuilder getQueryTextSentimentOrBuilder() {
        return getQueryTextSentiment();
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
        if (this.queryTextSentiment_ != null) {
            codedOutputStream.writeMessage(1, getQueryTextSentiment());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = (this.queryTextSentiment_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getQueryTextSentiment()) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = computeMessageSize;
        return computeMessageSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SentimentAnalysisResult)) {
            return super.equals(obj);
        }
        SentimentAnalysisResult sentimentAnalysisResult = (SentimentAnalysisResult) obj;
        if (hasQueryTextSentiment() != sentimentAnalysisResult.hasQueryTextSentiment()) {
            return false;
        }
        return (!hasQueryTextSentiment() || getQueryTextSentiment().equals(sentimentAnalysisResult.getQueryTextSentiment())) && this.unknownFields.equals(sentimentAnalysisResult.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasQueryTextSentiment()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getQueryTextSentiment().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SentimentAnalysisResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SentimentAnalysisResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SentimentAnalysisResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SentimentAnalysisResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SentimentAnalysisResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SentimentAnalysisResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SentimentAnalysisResult parseFrom(InputStream inputStream) throws IOException {
        return (SentimentAnalysisResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SentimentAnalysisResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SentimentAnalysisResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SentimentAnalysisResult parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SentimentAnalysisResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SentimentAnalysisResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SentimentAnalysisResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SentimentAnalysisResult parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SentimentAnalysisResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SentimentAnalysisResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SentimentAnalysisResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SentimentAnalysisResult sentimentAnalysisResult) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(sentimentAnalysisResult);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SentimentAnalysisResultOrBuilder {
        private SingleFieldBuilderV3<Sentiment, Sentiment.Builder, SentimentOrBuilder> queryTextSentimentBuilder_;
        private Sentiment queryTextSentiment_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1856x40ea3508;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1857xa777d386.ensureFieldAccessorsInitialized(SentimentAnalysisResult.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SentimentAnalysisResult.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.queryTextSentimentBuilder_ == null) {
                this.queryTextSentiment_ = null;
            } else {
                this.queryTextSentiment_ = null;
                this.queryTextSentimentBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.f1856x40ea3508;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SentimentAnalysisResult getDefaultInstanceForType() {
            return SentimentAnalysisResult.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SentimentAnalysisResult build() {
            SentimentAnalysisResult buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SentimentAnalysisResult buildPartial() {
            SentimentAnalysisResult sentimentAnalysisResult = new SentimentAnalysisResult(this);
            SingleFieldBuilderV3<Sentiment, Sentiment.Builder, SentimentOrBuilder> singleFieldBuilderV3 = this.queryTextSentimentBuilder_;
            if (singleFieldBuilderV3 == null) {
                sentimentAnalysisResult.queryTextSentiment_ = this.queryTextSentiment_;
            } else {
                sentimentAnalysisResult.queryTextSentiment_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return sentimentAnalysisResult;
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
            if (message instanceof SentimentAnalysisResult) {
                return mergeFrom((SentimentAnalysisResult) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SentimentAnalysisResult sentimentAnalysisResult) {
            if (sentimentAnalysisResult == SentimentAnalysisResult.getDefaultInstance()) {
                return this;
            }
            if (sentimentAnalysisResult.hasQueryTextSentiment()) {
                mergeQueryTextSentiment(sentimentAnalysisResult.getQueryTextSentiment());
            }
            mergeUnknownFields(sentimentAnalysisResult.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SentimentAnalysisResult sentimentAnalysisResult = null;
            try {
                try {
                    SentimentAnalysisResult sentimentAnalysisResult2 = (SentimentAnalysisResult) SentimentAnalysisResult.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (sentimentAnalysisResult2 != null) {
                        mergeFrom(sentimentAnalysisResult2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SentimentAnalysisResult sentimentAnalysisResult3 = (SentimentAnalysisResult) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        sentimentAnalysisResult = sentimentAnalysisResult3;
                        if (sentimentAnalysisResult != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (sentimentAnalysisResult != null) {
                    mergeFrom(sentimentAnalysisResult);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SentimentAnalysisResultOrBuilder
        public boolean hasQueryTextSentiment() {
            return (this.queryTextSentimentBuilder_ == null && this.queryTextSentiment_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SentimentAnalysisResultOrBuilder
        public Sentiment getQueryTextSentiment() {
            SingleFieldBuilderV3<Sentiment, Sentiment.Builder, SentimentOrBuilder> singleFieldBuilderV3 = this.queryTextSentimentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Sentiment sentiment = this.queryTextSentiment_;
                return sentiment == null ? Sentiment.getDefaultInstance() : sentiment;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setQueryTextSentiment(Sentiment sentiment) {
            SingleFieldBuilderV3<Sentiment, Sentiment.Builder, SentimentOrBuilder> singleFieldBuilderV3 = this.queryTextSentimentBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sentiment);
            } else {
                if (sentiment == null) {
                    throw new NullPointerException();
                }
                this.queryTextSentiment_ = sentiment;
                onChanged();
            }
            return this;
        }

        public Builder setQueryTextSentiment(Sentiment.Builder builder) {
            SingleFieldBuilderV3<Sentiment, Sentiment.Builder, SentimentOrBuilder> singleFieldBuilderV3 = this.queryTextSentimentBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.queryTextSentiment_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeQueryTextSentiment(Sentiment sentiment) {
            SingleFieldBuilderV3<Sentiment, Sentiment.Builder, SentimentOrBuilder> singleFieldBuilderV3 = this.queryTextSentimentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Sentiment sentiment2 = this.queryTextSentiment_;
                if (sentiment2 != null) {
                    this.queryTextSentiment_ = Sentiment.newBuilder(sentiment2).mergeFrom(sentiment).buildPartial();
                } else {
                    this.queryTextSentiment_ = sentiment;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sentiment);
            }
            return this;
        }

        public Builder clearQueryTextSentiment() {
            if (this.queryTextSentimentBuilder_ == null) {
                this.queryTextSentiment_ = null;
                onChanged();
            } else {
                this.queryTextSentiment_ = null;
                this.queryTextSentimentBuilder_ = null;
            }
            return this;
        }

        public Sentiment.Builder getQueryTextSentimentBuilder() {
            onChanged();
            return getQueryTextSentimentFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SentimentAnalysisResultOrBuilder
        public SentimentOrBuilder getQueryTextSentimentOrBuilder() {
            SingleFieldBuilderV3<Sentiment, Sentiment.Builder, SentimentOrBuilder> singleFieldBuilderV3 = this.queryTextSentimentBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Sentiment sentiment = this.queryTextSentiment_;
            return sentiment == null ? Sentiment.getDefaultInstance() : sentiment;
        }

        private SingleFieldBuilderV3<Sentiment, Sentiment.Builder, SentimentOrBuilder> getQueryTextSentimentFieldBuilder() {
            if (this.queryTextSentimentBuilder_ == null) {
                this.queryTextSentimentBuilder_ = new SingleFieldBuilderV3<>(getQueryTextSentiment(), getParentForChildren(), isClean());
                this.queryTextSentiment_ = null;
            }
            return this.queryTextSentimentBuilder_;
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

    public static SentimentAnalysisResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SentimentAnalysisResult> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SentimentAnalysisResult> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SentimentAnalysisResult getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
