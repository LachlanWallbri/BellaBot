package com.google.cloud.texttospeech.v1beta1;

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
public final class Timepoint extends GeneratedMessageV3 implements TimepointOrBuilder {
    public static final int MARK_NAME_FIELD_NUMBER = 4;
    public static final int TIME_SECONDS_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private volatile Object markName_;
    private byte memoizedIsInitialized;
    private double timeSeconds_;
    private static final Timepoint DEFAULT_INSTANCE = new Timepoint();
    private static final Parser<Timepoint> PARSER = new AbstractParser<Timepoint>() { // from class: com.google.cloud.texttospeech.v1beta1.Timepoint.1
        @Override // com.google.protobuf.Parser
        public Timepoint parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Timepoint(codedInputStream, extensionRegistryLite);
        }
    };

    private Timepoint(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Timepoint() {
        this.memoizedIsInitialized = (byte) -1;
        this.markName_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Timepoint();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Timepoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag == 25) {
                                this.timeSeconds_ = codedInputStream.readDouble();
                            } else if (readTag == 34) {
                                this.markName_ = codedInputStream.readStringRequireUtf8();
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
        return TextToSpeechProto.f1907x77182a98;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TextToSpeechProto.f1908x455f3916.ensureFieldAccessorsInitialized(Timepoint.class, Builder.class);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.TimepointOrBuilder
    public String getMarkName() {
        Object obj = this.markName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.markName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.TimepointOrBuilder
    public ByteString getMarkNameBytes() {
        Object obj = this.markName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.markName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.TimepointOrBuilder
    public double getTimeSeconds() {
        return this.timeSeconds_;
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
        double d = this.timeSeconds_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(3, d);
        }
        if (!getMarkNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.markName_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        double d = this.timeSeconds_;
        int computeDoubleSize = d != 0.0d ? 0 + CodedOutputStream.computeDoubleSize(3, d) : 0;
        if (!getMarkNameBytes().isEmpty()) {
            computeDoubleSize += GeneratedMessageV3.computeStringSize(4, this.markName_);
        }
        int serializedSize = computeDoubleSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Timepoint)) {
            return super.equals(obj);
        }
        Timepoint timepoint = (Timepoint) obj;
        return getMarkName().equals(timepoint.getMarkName()) && Double.doubleToLongBits(getTimeSeconds()) == Double.doubleToLongBits(timepoint.getTimeSeconds()) && this.unknownFields.equals(timepoint.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 4) * 53) + getMarkName().hashCode()) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getTimeSeconds()))) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Timepoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Timepoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Timepoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Timepoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Timepoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Timepoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Timepoint parseFrom(InputStream inputStream) throws IOException {
        return (Timepoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Timepoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Timepoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Timepoint parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Timepoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Timepoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Timepoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Timepoint parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Timepoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Timepoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Timepoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Timepoint timepoint) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(timepoint);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TimepointOrBuilder {
        private Object markName_;
        private double timeSeconds_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TextToSpeechProto.f1907x77182a98;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TextToSpeechProto.f1908x455f3916.ensureFieldAccessorsInitialized(Timepoint.class, Builder.class);
        }

        private Builder() {
            this.markName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.markName_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Timepoint.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.markName_ = "";
            this.timeSeconds_ = 0.0d;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TextToSpeechProto.f1907x77182a98;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Timepoint getDefaultInstanceForType() {
            return Timepoint.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Timepoint build() {
            Timepoint buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Timepoint buildPartial() {
            Timepoint timepoint = new Timepoint(this);
            timepoint.markName_ = this.markName_;
            timepoint.timeSeconds_ = this.timeSeconds_;
            onBuilt();
            return timepoint;
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
            if (message instanceof Timepoint) {
                return mergeFrom((Timepoint) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Timepoint timepoint) {
            if (timepoint == Timepoint.getDefaultInstance()) {
                return this;
            }
            if (!timepoint.getMarkName().isEmpty()) {
                this.markName_ = timepoint.markName_;
                onChanged();
            }
            if (timepoint.getTimeSeconds() != 0.0d) {
                setTimeSeconds(timepoint.getTimeSeconds());
            }
            mergeUnknownFields(timepoint.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Timepoint timepoint = null;
            try {
                try {
                    Timepoint timepoint2 = (Timepoint) Timepoint.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (timepoint2 != null) {
                        mergeFrom(timepoint2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Timepoint timepoint3 = (Timepoint) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        timepoint = timepoint3;
                        if (timepoint != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (timepoint != null) {
                    mergeFrom(timepoint);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.texttospeech.v1beta1.TimepointOrBuilder
        public String getMarkName() {
            Object obj = this.markName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.markName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.TimepointOrBuilder
        public ByteString getMarkNameBytes() {
            Object obj = this.markName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.markName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMarkName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.markName_ = str;
            onChanged();
            return this;
        }

        public Builder clearMarkName() {
            this.markName_ = Timepoint.getDefaultInstance().getMarkName();
            onChanged();
            return this;
        }

        public Builder setMarkNameBytes(ByteString byteString) {
            if (byteString != null) {
                Timepoint.checkByteStringIsUtf8(byteString);
                this.markName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.TimepointOrBuilder
        public double getTimeSeconds() {
            return this.timeSeconds_;
        }

        public Builder setTimeSeconds(double d) {
            this.timeSeconds_ = d;
            onChanged();
            return this;
        }

        public Builder clearTimeSeconds() {
            this.timeSeconds_ = 0.0d;
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

    public static Timepoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Timepoint> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Timepoint> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Timepoint getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
