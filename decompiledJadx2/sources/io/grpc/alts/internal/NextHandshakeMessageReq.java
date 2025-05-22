package io.grpc.alts.internal;

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
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NextHandshakeMessageReq extends GeneratedMessageV3 implements NextHandshakeMessageReqOrBuilder {
    public static final int IN_BYTES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private ByteString inBytes_;
    private byte memoizedIsInitialized;
    private static final NextHandshakeMessageReq DEFAULT_INSTANCE = new NextHandshakeMessageReq();
    private static final Parser<NextHandshakeMessageReq> PARSER = new AbstractParser<NextHandshakeMessageReq>() { // from class: io.grpc.alts.internal.NextHandshakeMessageReq.1
        @Override // com.google.protobuf.Parser
        public NextHandshakeMessageReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new NextHandshakeMessageReq(codedInputStream, extensionRegistryLite);
        }
    };

    private NextHandshakeMessageReq(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private NextHandshakeMessageReq() {
        this.memoizedIsInitialized = (byte) -1;
        this.inBytes_ = ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new NextHandshakeMessageReq();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private NextHandshakeMessageReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.inBytes_ = codedInputStream.readBytes();
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
        return HandshakerProto.internal_static_grpc_gcp_NextHandshakeMessageReq_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.f8311xc0f6410e.ensureFieldAccessorsInitialized(NextHandshakeMessageReq.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.NextHandshakeMessageReqOrBuilder
    public ByteString getInBytes() {
        return this.inBytes_;
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
        if (!this.inBytes_.isEmpty()) {
            codedOutputStream.writeBytes(1, this.inBytes_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeBytesSize = (this.inBytes_.isEmpty() ? 0 : 0 + CodedOutputStream.computeBytesSize(1, this.inBytes_)) + this.unknownFields.getSerializedSize();
        this.memoizedSize = computeBytesSize;
        return computeBytesSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NextHandshakeMessageReq)) {
            return super.equals(obj);
        }
        NextHandshakeMessageReq nextHandshakeMessageReq = (NextHandshakeMessageReq) obj;
        return getInBytes().equals(nextHandshakeMessageReq.getInBytes()) && this.unknownFields.equals(nextHandshakeMessageReq.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getInBytes().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static NextHandshakeMessageReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static NextHandshakeMessageReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static NextHandshakeMessageReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static NextHandshakeMessageReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static NextHandshakeMessageReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static NextHandshakeMessageReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static NextHandshakeMessageReq parseFrom(InputStream inputStream) throws IOException {
        return (NextHandshakeMessageReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static NextHandshakeMessageReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NextHandshakeMessageReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static NextHandshakeMessageReq parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (NextHandshakeMessageReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static NextHandshakeMessageReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NextHandshakeMessageReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static NextHandshakeMessageReq parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (NextHandshakeMessageReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static NextHandshakeMessageReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NextHandshakeMessageReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NextHandshakeMessageReq nextHandshakeMessageReq) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(nextHandshakeMessageReq);
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
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements NextHandshakeMessageReqOrBuilder {
        private ByteString inBytes_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_NextHandshakeMessageReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.f8311xc0f6410e.ensureFieldAccessorsInitialized(NextHandshakeMessageReq.class, Builder.class);
        }

        private Builder() {
            this.inBytes_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.inBytes_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = NextHandshakeMessageReq.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.inBytes_ = ByteString.EMPTY;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_NextHandshakeMessageReq_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public NextHandshakeMessageReq getDefaultInstanceForType() {
            return NextHandshakeMessageReq.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public NextHandshakeMessageReq build() {
            NextHandshakeMessageReq buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public NextHandshakeMessageReq buildPartial() {
            NextHandshakeMessageReq nextHandshakeMessageReq = new NextHandshakeMessageReq(this);
            nextHandshakeMessageReq.inBytes_ = this.inBytes_;
            onBuilt();
            return nextHandshakeMessageReq;
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
            if (message instanceof NextHandshakeMessageReq) {
                return mergeFrom((NextHandshakeMessageReq) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(NextHandshakeMessageReq nextHandshakeMessageReq) {
            if (nextHandshakeMessageReq == NextHandshakeMessageReq.getDefaultInstance()) {
                return this;
            }
            if (nextHandshakeMessageReq.getInBytes() != ByteString.EMPTY) {
                setInBytes(nextHandshakeMessageReq.getInBytes());
            }
            mergeUnknownFields(nextHandshakeMessageReq.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            NextHandshakeMessageReq nextHandshakeMessageReq = null;
            try {
                try {
                    NextHandshakeMessageReq nextHandshakeMessageReq2 = (NextHandshakeMessageReq) NextHandshakeMessageReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (nextHandshakeMessageReq2 != null) {
                        mergeFrom(nextHandshakeMessageReq2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    NextHandshakeMessageReq nextHandshakeMessageReq3 = (NextHandshakeMessageReq) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        nextHandshakeMessageReq = nextHandshakeMessageReq3;
                        if (nextHandshakeMessageReq != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (nextHandshakeMessageReq != null) {
                    mergeFrom(nextHandshakeMessageReq);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.NextHandshakeMessageReqOrBuilder
        public ByteString getInBytes() {
            return this.inBytes_;
        }

        public Builder setInBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.inBytes_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearInBytes() {
            this.inBytes_ = NextHandshakeMessageReq.getDefaultInstance().getInBytes();
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

    public static NextHandshakeMessageReq getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NextHandshakeMessageReq> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<NextHandshakeMessageReq> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public NextHandshakeMessageReq getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
