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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.alts.internal.HandshakerResult;
import io.grpc.alts.internal.HandshakerStatus;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HandshakerResp extends GeneratedMessageV3 implements HandshakerRespOrBuilder {
    public static final int BYTES_CONSUMED_FIELD_NUMBER = 2;
    public static final int OUT_FRAMES_FIELD_NUMBER = 1;
    public static final int RESULT_FIELD_NUMBER = 3;
    public static final int STATUS_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private int bytesConsumed_;
    private byte memoizedIsInitialized;
    private ByteString outFrames_;
    private HandshakerResult result_;
    private HandshakerStatus status_;
    private static final HandshakerResp DEFAULT_INSTANCE = new HandshakerResp();
    private static final Parser<HandshakerResp> PARSER = new AbstractParser<HandshakerResp>() { // from class: io.grpc.alts.internal.HandshakerResp.1
        @Override // com.google.protobuf.Parser
        public HandshakerResp parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HandshakerResp(codedInputStream, extensionRegistryLite);
        }
    };

    private HandshakerResp(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HandshakerResp() {
        this.memoizedIsInitialized = (byte) -1;
        this.outFrames_ = ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new HandshakerResp();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private HandshakerResp(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag == 10) {
                                this.outFrames_ = codedInputStream.readBytes();
                            } else if (readTag != 16) {
                                if (readTag == 26) {
                                    HandshakerResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                    this.result_ = (HandshakerResult) codedInputStream.readMessage(HandshakerResult.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.result_);
                                        this.result_ = builder.buildPartial();
                                    }
                                } else if (readTag == 34) {
                                    HandshakerStatus.Builder builder2 = this.status_ != null ? this.status_.toBuilder() : null;
                                    this.status_ = (HandshakerStatus) codedInputStream.readMessage(HandshakerStatus.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.status_);
                                        this.status_ = builder2.buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.bytesConsumed_ = codedInputStream.readUInt32();
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
        return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerResp.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public ByteString getOutFrames() {
        return this.outFrames_;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public int getBytesConsumed() {
        return this.bytesConsumed_;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public boolean hasResult() {
        return this.result_ != null;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public HandshakerResult getResult() {
        HandshakerResult handshakerResult = this.result_;
        return handshakerResult == null ? HandshakerResult.getDefaultInstance() : handshakerResult;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public HandshakerResultOrBuilder getResultOrBuilder() {
        return getResult();
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public boolean hasStatus() {
        return this.status_ != null;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public HandshakerStatus getStatus() {
        HandshakerStatus handshakerStatus = this.status_;
        return handshakerStatus == null ? HandshakerStatus.getDefaultInstance() : handshakerStatus;
    }

    @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
    public HandshakerStatusOrBuilder getStatusOrBuilder() {
        return getStatus();
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
        if (!this.outFrames_.isEmpty()) {
            codedOutputStream.writeBytes(1, this.outFrames_);
        }
        int i = this.bytesConsumed_;
        if (i != 0) {
            codedOutputStream.writeUInt32(2, i);
        }
        if (this.result_ != null) {
            codedOutputStream.writeMessage(3, getResult());
        }
        if (this.status_ != null) {
            codedOutputStream.writeMessage(4, getStatus());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeBytesSize = this.outFrames_.isEmpty() ? 0 : 0 + CodedOutputStream.computeBytesSize(1, this.outFrames_);
        int i2 = this.bytesConsumed_;
        if (i2 != 0) {
            computeBytesSize += CodedOutputStream.computeUInt32Size(2, i2);
        }
        if (this.result_ != null) {
            computeBytesSize += CodedOutputStream.computeMessageSize(3, getResult());
        }
        if (this.status_ != null) {
            computeBytesSize += CodedOutputStream.computeMessageSize(4, getStatus());
        }
        int serializedSize = computeBytesSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HandshakerResp)) {
            return super.equals(obj);
        }
        HandshakerResp handshakerResp = (HandshakerResp) obj;
        if (!getOutFrames().equals(handshakerResp.getOutFrames()) || getBytesConsumed() != handshakerResp.getBytesConsumed() || hasResult() != handshakerResp.hasResult()) {
            return false;
        }
        if ((!hasResult() || getResult().equals(handshakerResp.getResult())) && hasStatus() == handshakerResp.hasStatus()) {
            return (!hasStatus() || getStatus().equals(handshakerResp.getStatus())) && this.unknownFields.equals(handshakerResp.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getOutFrames().hashCode()) * 37) + 2) * 53) + getBytesConsumed();
        if (hasResult()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getResult().hashCode();
        }
        if (hasStatus()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getStatus().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static HandshakerResp parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static HandshakerResp parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HandshakerResp parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static HandshakerResp parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HandshakerResp parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static HandshakerResp parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HandshakerResp parseFrom(InputStream inputStream) throws IOException {
        return (HandshakerResp) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HandshakerResp parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerResp) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerResp parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HandshakerResp) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HandshakerResp parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerResp) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerResp parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HandshakerResp) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HandshakerResp parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerResp) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HandshakerResp handshakerResp) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(handshakerResp);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HandshakerRespOrBuilder {
        private int bytesConsumed_;
        private ByteString outFrames_;
        private SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> resultBuilder_;
        private HandshakerResult result_;
        private SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> statusBuilder_;
        private HandshakerStatus status_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerResp.class, Builder.class);
        }

        private Builder() {
            this.outFrames_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.outFrames_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HandshakerResp.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.outFrames_ = ByteString.EMPTY;
            this.bytesConsumed_ = 0;
            if (this.resultBuilder_ == null) {
                this.result_ = null;
            } else {
                this.result_ = null;
                this.resultBuilder_ = null;
            }
            if (this.statusBuilder_ == null) {
                this.status_ = null;
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResp_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public HandshakerResp getDefaultInstanceForType() {
            return HandshakerResp.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HandshakerResp build() {
            HandshakerResp buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HandshakerResp buildPartial() {
            HandshakerResp handshakerResp = new HandshakerResp(this);
            handshakerResp.outFrames_ = this.outFrames_;
            handshakerResp.bytesConsumed_ = this.bytesConsumed_;
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 == null) {
                handshakerResp.result_ = this.result_;
            } else {
                handshakerResp.result_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV32 = this.statusBuilder_;
            if (singleFieldBuilderV32 == null) {
                handshakerResp.status_ = this.status_;
            } else {
                handshakerResp.status_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return handshakerResp;
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
            if (message instanceof HandshakerResp) {
                return mergeFrom((HandshakerResp) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HandshakerResp handshakerResp) {
            if (handshakerResp == HandshakerResp.getDefaultInstance()) {
                return this;
            }
            if (handshakerResp.getOutFrames() != ByteString.EMPTY) {
                setOutFrames(handshakerResp.getOutFrames());
            }
            if (handshakerResp.getBytesConsumed() != 0) {
                setBytesConsumed(handshakerResp.getBytesConsumed());
            }
            if (handshakerResp.hasResult()) {
                mergeResult(handshakerResp.getResult());
            }
            if (handshakerResp.hasStatus()) {
                mergeStatus(handshakerResp.getStatus());
            }
            mergeUnknownFields(handshakerResp.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            HandshakerResp handshakerResp = null;
            try {
                try {
                    HandshakerResp handshakerResp2 = (HandshakerResp) HandshakerResp.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (handshakerResp2 != null) {
                        mergeFrom(handshakerResp2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    HandshakerResp handshakerResp3 = (HandshakerResp) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        handshakerResp = handshakerResp3;
                        if (handshakerResp != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (handshakerResp != null) {
                    mergeFrom(handshakerResp);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public ByteString getOutFrames() {
            return this.outFrames_;
        }

        public Builder setOutFrames(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.outFrames_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearOutFrames() {
            this.outFrames_ = HandshakerResp.getDefaultInstance().getOutFrames();
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public int getBytesConsumed() {
            return this.bytesConsumed_;
        }

        public Builder setBytesConsumed(int i) {
            this.bytesConsumed_ = i;
            onChanged();
            return this;
        }

        public Builder clearBytesConsumed() {
            this.bytesConsumed_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public boolean hasResult() {
            return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public HandshakerResult getResult() {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 == null) {
                HandshakerResult handshakerResult = this.result_;
                return handshakerResult == null ? HandshakerResult.getDefaultInstance() : handshakerResult;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setResult(HandshakerResult handshakerResult) {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(handshakerResult);
            } else {
                if (handshakerResult == null) {
                    throw new NullPointerException();
                }
                this.result_ = handshakerResult;
                onChanged();
            }
            return this;
        }

        public Builder setResult(HandshakerResult.Builder builder) {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.result_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeResult(HandshakerResult handshakerResult) {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 == null) {
                HandshakerResult handshakerResult2 = this.result_;
                if (handshakerResult2 != null) {
                    this.result_ = HandshakerResult.newBuilder(handshakerResult2).mergeFrom(handshakerResult).buildPartial();
                } else {
                    this.result_ = handshakerResult;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(handshakerResult);
            }
            return this;
        }

        public Builder clearResult() {
            if (this.resultBuilder_ == null) {
                this.result_ = null;
                onChanged();
            } else {
                this.result_ = null;
                this.resultBuilder_ = null;
            }
            return this;
        }

        public HandshakerResult.Builder getResultBuilder() {
            onChanged();
            return getResultFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public HandshakerResultOrBuilder getResultOrBuilder() {
            SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            HandshakerResult handshakerResult = this.result_;
            return handshakerResult == null ? HandshakerResult.getDefaultInstance() : handshakerResult;
        }

        private SingleFieldBuilderV3<HandshakerResult, HandshakerResult.Builder, HandshakerResultOrBuilder> getResultFieldBuilder() {
            if (this.resultBuilder_ == null) {
                this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                this.result_ = null;
            }
            return this.resultBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public boolean hasStatus() {
            return (this.statusBuilder_ == null && this.status_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public HandshakerStatus getStatus() {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                HandshakerStatus handshakerStatus = this.status_;
                return handshakerStatus == null ? HandshakerStatus.getDefaultInstance() : handshakerStatus;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setStatus(HandshakerStatus handshakerStatus) {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(handshakerStatus);
            } else {
                if (handshakerStatus == null) {
                    throw new NullPointerException();
                }
                this.status_ = handshakerStatus;
                onChanged();
            }
            return this;
        }

        public Builder setStatus(HandshakerStatus.Builder builder) {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.status_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeStatus(HandshakerStatus handshakerStatus) {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                HandshakerStatus handshakerStatus2 = this.status_;
                if (handshakerStatus2 != null) {
                    this.status_ = HandshakerStatus.newBuilder(handshakerStatus2).mergeFrom(handshakerStatus).buildPartial();
                } else {
                    this.status_ = handshakerStatus;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(handshakerStatus);
            }
            return this;
        }

        public Builder clearStatus() {
            if (this.statusBuilder_ == null) {
                this.status_ = null;
                onChanged();
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            return this;
        }

        public HandshakerStatus.Builder getStatusBuilder() {
            onChanged();
            return getStatusFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerRespOrBuilder
        public HandshakerStatusOrBuilder getStatusOrBuilder() {
            SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            HandshakerStatus handshakerStatus = this.status_;
            return handshakerStatus == null ? HandshakerStatus.getDefaultInstance() : handshakerStatus;
        }

        private SingleFieldBuilderV3<HandshakerStatus, HandshakerStatus.Builder, HandshakerStatusOrBuilder> getStatusFieldBuilder() {
            if (this.statusBuilder_ == null) {
                this.statusBuilder_ = new SingleFieldBuilderV3<>(getStatus(), getParentForChildren(), isClean());
                this.status_ = null;
            }
            return this.statusBuilder_;
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

    public static HandshakerResp getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HandshakerResp> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<HandshakerResp> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public HandshakerResp getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
