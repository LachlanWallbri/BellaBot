package com.google.cloud.dialogflow.p049v2;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class Sentiment extends GeneratedMessageV3 implements SentimentOrBuilder {
    public static final int MAGNITUDE_FIELD_NUMBER = 2;
    public static final int SCORE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private float magnitude_;
    private byte memoizedIsInitialized;
    private float score_;
    private static final Sentiment DEFAULT_INSTANCE = new Sentiment();
    private static final Parser<Sentiment> PARSER = new AbstractParser<Sentiment>() { // from class: com.google.cloud.dialogflow.v2.Sentiment.1
        @Override // com.google.protobuf.Parser
        public Sentiment parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Sentiment(codedInputStream, extensionRegistryLite);
        }
    };

    private Sentiment(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Sentiment() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Sentiment();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Sentiment(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag == 13) {
                                this.score_ = codedInputStream.readFloat();
                            } else if (readTag == 21) {
                                this.magnitude_ = codedInputStream.readFloat();
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
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
        return SessionProto.internal_static_google_cloud_dialogflow_v2_Sentiment_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1560x7f2fd638.ensureFieldAccessorsInitialized(Sentiment.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.SentimentOrBuilder
    public float getScore() {
        return this.score_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SentimentOrBuilder
    public float getMagnitude() {
        return this.magnitude_;
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
        float f = this.score_;
        if (f != 0.0f) {
            codedOutputStream.writeFloat(1, f);
        }
        float f2 = this.magnitude_;
        if (f2 != 0.0f) {
            codedOutputStream.writeFloat(2, f2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        float f = this.score_;
        int computeFloatSize = f != 0.0f ? 0 + CodedOutputStream.computeFloatSize(1, f) : 0;
        float f2 = this.magnitude_;
        if (f2 != 0.0f) {
            computeFloatSize += CodedOutputStream.computeFloatSize(2, f2);
        }
        int serializedSize = computeFloatSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Sentiment)) {
            return super.equals(obj);
        }
        Sentiment sentiment = (Sentiment) obj;
        return Float.floatToIntBits(getScore()) == Float.floatToIntBits(sentiment.getScore()) && Float.floatToIntBits(getMagnitude()) == Float.floatToIntBits(sentiment.getMagnitude()) && this.unknownFields.equals(sentiment.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Float.floatToIntBits(getScore())) * 37) + 2) * 53) + Float.floatToIntBits(getMagnitude())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Sentiment parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Sentiment parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Sentiment parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Sentiment parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Sentiment parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Sentiment parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Sentiment parseFrom(InputStream inputStream) throws IOException {
        return (Sentiment) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Sentiment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Sentiment) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Sentiment parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Sentiment) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Sentiment parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Sentiment) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Sentiment parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Sentiment) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Sentiment parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Sentiment) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Sentiment sentiment) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(sentiment);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SentimentOrBuilder {
        private float magnitude_;
        private float score_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.internal_static_google_cloud_dialogflow_v2_Sentiment_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1560x7f2fd638.ensureFieldAccessorsInitialized(Sentiment.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Sentiment.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.score_ = 0.0f;
            this.magnitude_ = 0.0f;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.internal_static_google_cloud_dialogflow_v2_Sentiment_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Sentiment getDefaultInstanceForType() {
            return Sentiment.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Sentiment build() {
            Sentiment buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Sentiment buildPartial() {
            Sentiment sentiment = new Sentiment(this);
            sentiment.score_ = this.score_;
            sentiment.magnitude_ = this.magnitude_;
            onBuilt();
            return sentiment;
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
            if (message instanceof Sentiment) {
                return mergeFrom((Sentiment) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Sentiment sentiment) {
            if (sentiment == Sentiment.getDefaultInstance()) {
                return this;
            }
            if (sentiment.getScore() != 0.0f) {
                setScore(sentiment.getScore());
            }
            if (sentiment.getMagnitude() != 0.0f) {
                setMagnitude(sentiment.getMagnitude());
            }
            mergeUnknownFields(sentiment.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Sentiment sentiment = null;
            try {
                try {
                    Sentiment sentiment2 = (Sentiment) Sentiment.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (sentiment2 != null) {
                        mergeFrom(sentiment2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Sentiment sentiment3 = (Sentiment) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        sentiment = sentiment3;
                        if (sentiment != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (sentiment != null) {
                    mergeFrom(sentiment);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.SentimentOrBuilder
        public float getScore() {
            return this.score_;
        }

        public Builder setScore(float f) {
            this.score_ = f;
            onChanged();
            return this;
        }

        public Builder clearScore() {
            this.score_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.SentimentOrBuilder
        public float getMagnitude() {
            return this.magnitude_;
        }

        public Builder setMagnitude(float f) {
            this.magnitude_ = f;
            onChanged();
            return this;
        }

        public Builder clearMagnitude() {
            this.magnitude_ = 0.0f;
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

    public static Sentiment getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Sentiment> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Sentiment> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Sentiment getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
