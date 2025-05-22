package io.grpc.alts.internal;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.alts.internal.NextHandshakeMessageReq;
import io.grpc.alts.internal.StartClientHandshakeReq;
import io.grpc.alts.internal.StartServerHandshakeReq;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HandshakerReq extends GeneratedMessageV3 implements HandshakerReqOrBuilder {
    public static final int CLIENT_START_FIELD_NUMBER = 1;
    public static final int NEXT_FIELD_NUMBER = 3;
    public static final int SERVER_START_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int reqOneofCase_;
    private Object reqOneof_;
    private static final HandshakerReq DEFAULT_INSTANCE = new HandshakerReq();
    private static final Parser<HandshakerReq> PARSER = new AbstractParser<HandshakerReq>() { // from class: io.grpc.alts.internal.HandshakerReq.1
        @Override // com.google.protobuf.Parser
        public HandshakerReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HandshakerReq(codedInputStream, extensionRegistryLite);
        }
    };

    private HandshakerReq(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.reqOneofCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HandshakerReq() {
        this.reqOneofCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new HandshakerReq();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private HandshakerReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            StartClientHandshakeReq.Builder builder = this.reqOneofCase_ == 1 ? ((StartClientHandshakeReq) this.reqOneof_).toBuilder() : null;
                            this.reqOneof_ = codedInputStream.readMessage(StartClientHandshakeReq.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom((StartClientHandshakeReq) this.reqOneof_);
                                this.reqOneof_ = builder.buildPartial();
                            }
                            this.reqOneofCase_ = 1;
                        } else if (readTag == 18) {
                            StartServerHandshakeReq.Builder builder2 = this.reqOneofCase_ == 2 ? ((StartServerHandshakeReq) this.reqOneof_).toBuilder() : null;
                            this.reqOneof_ = codedInputStream.readMessage(StartServerHandshakeReq.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom((StartServerHandshakeReq) this.reqOneof_);
                                this.reqOneof_ = builder2.buildPartial();
                            }
                            this.reqOneofCase_ = 2;
                        } else if (readTag == 26) {
                            NextHandshakeMessageReq.Builder builder3 = this.reqOneofCase_ == 3 ? ((NextHandshakeMessageReq) this.reqOneof_).toBuilder() : null;
                            this.reqOneof_ = codedInputStream.readMessage(NextHandshakeMessageReq.parser(), extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom((NextHandshakeMessageReq) this.reqOneof_);
                                this.reqOneof_ = builder3.buildPartial();
                            }
                            this.reqOneofCase_ = 3;
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
        return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerReq.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum ReqOneofCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        CLIENT_START(1),
        SERVER_START(2),
        NEXT(3),
        REQONEOF_NOT_SET(0);

        private final int value;

        ReqOneofCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ReqOneofCase valueOf(int i) {
            return forNumber(i);
        }

        public static ReqOneofCase forNumber(int i) {
            if (i == 0) {
                return REQONEOF_NOT_SET;
            }
            if (i == 1) {
                return CLIENT_START;
            }
            if (i == 2) {
                return SERVER_START;
            }
            if (i != 3) {
                return null;
            }
            return NEXT;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public ReqOneofCase getReqOneofCase() {
        return ReqOneofCase.forNumber(this.reqOneofCase_);
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public boolean hasClientStart() {
        return this.reqOneofCase_ == 1;
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public StartClientHandshakeReq getClientStart() {
        if (this.reqOneofCase_ == 1) {
            return (StartClientHandshakeReq) this.reqOneof_;
        }
        return StartClientHandshakeReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public StartClientHandshakeReqOrBuilder getClientStartOrBuilder() {
        if (this.reqOneofCase_ == 1) {
            return (StartClientHandshakeReq) this.reqOneof_;
        }
        return StartClientHandshakeReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public boolean hasServerStart() {
        return this.reqOneofCase_ == 2;
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public StartServerHandshakeReq getServerStart() {
        if (this.reqOneofCase_ == 2) {
            return (StartServerHandshakeReq) this.reqOneof_;
        }
        return StartServerHandshakeReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public StartServerHandshakeReqOrBuilder getServerStartOrBuilder() {
        if (this.reqOneofCase_ == 2) {
            return (StartServerHandshakeReq) this.reqOneof_;
        }
        return StartServerHandshakeReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public boolean hasNext() {
        return this.reqOneofCase_ == 3;
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public NextHandshakeMessageReq getNext() {
        if (this.reqOneofCase_ == 3) {
            return (NextHandshakeMessageReq) this.reqOneof_;
        }
        return NextHandshakeMessageReq.getDefaultInstance();
    }

    @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
    public NextHandshakeMessageReqOrBuilder getNextOrBuilder() {
        if (this.reqOneofCase_ == 3) {
            return (NextHandshakeMessageReq) this.reqOneof_;
        }
        return NextHandshakeMessageReq.getDefaultInstance();
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
        if (this.reqOneofCase_ == 1) {
            codedOutputStream.writeMessage(1, (StartClientHandshakeReq) this.reqOneof_);
        }
        if (this.reqOneofCase_ == 2) {
            codedOutputStream.writeMessage(2, (StartServerHandshakeReq) this.reqOneof_);
        }
        if (this.reqOneofCase_ == 3) {
            codedOutputStream.writeMessage(3, (NextHandshakeMessageReq) this.reqOneof_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.reqOneofCase_ == 1 ? 0 + CodedOutputStream.computeMessageSize(1, (StartClientHandshakeReq) this.reqOneof_) : 0;
        if (this.reqOneofCase_ == 2) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, (StartServerHandshakeReq) this.reqOneof_);
        }
        if (this.reqOneofCase_ == 3) {
            computeMessageSize += CodedOutputStream.computeMessageSize(3, (NextHandshakeMessageReq) this.reqOneof_);
        }
        int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HandshakerReq)) {
            return super.equals(obj);
        }
        HandshakerReq handshakerReq = (HandshakerReq) obj;
        if (!getReqOneofCase().equals(handshakerReq.getReqOneofCase())) {
            return false;
        }
        int i = this.reqOneofCase_;
        if (i != 1) {
            if (i == 2) {
                if (!getServerStart().equals(handshakerReq.getServerStart())) {
                    return false;
                }
            } else if (i == 3 && !getNext().equals(handshakerReq.getNext())) {
                return false;
            }
        } else if (!getClientStart().equals(handshakerReq.getClientStart())) {
            return false;
        }
        return this.unknownFields.equals(handshakerReq.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.reqOneofCase_;
        if (i2 == 1) {
            i = ((hashCode2 * 37) + 1) * 53;
            hashCode = getClientStart().hashCode();
        } else if (i2 == 2) {
            i = ((hashCode2 * 37) + 2) * 53;
            hashCode = getServerStart().hashCode();
        } else {
            if (i2 == 3) {
                i = ((hashCode2 * 37) + 3) * 53;
                hashCode = getNext().hashCode();
            }
            int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }
        hashCode2 = i + hashCode;
        int hashCode32 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode32;
        return hashCode32;
    }

    public static HandshakerReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static HandshakerReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HandshakerReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static HandshakerReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HandshakerReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static HandshakerReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HandshakerReq parseFrom(InputStream inputStream) throws IOException {
        return (HandshakerReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HandshakerReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerReq parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HandshakerReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HandshakerReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerReq parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HandshakerReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HandshakerReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HandshakerReq handshakerReq) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(handshakerReq);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HandshakerReqOrBuilder {
        private SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> clientStartBuilder_;
        private SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> nextBuilder_;
        private int reqOneofCase_;
        private Object reqOneof_;
        private SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> serverStartBuilder_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerReq.class, Builder.class);
        }

        private Builder() {
            this.reqOneofCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.reqOneofCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HandshakerReq.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.reqOneofCase_ = 0;
            this.reqOneof_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerReq_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public HandshakerReq getDefaultInstanceForType() {
            return HandshakerReq.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HandshakerReq build() {
            HandshakerReq buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HandshakerReq buildPartial() {
            HandshakerReq handshakerReq = new HandshakerReq(this);
            if (this.reqOneofCase_ == 1) {
                SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
                if (singleFieldBuilderV3 == null) {
                    handshakerReq.reqOneof_ = this.reqOneof_;
                } else {
                    handshakerReq.reqOneof_ = singleFieldBuilderV3.build();
                }
            }
            if (this.reqOneofCase_ == 2) {
                SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV32 = this.serverStartBuilder_;
                if (singleFieldBuilderV32 == null) {
                    handshakerReq.reqOneof_ = this.reqOneof_;
                } else {
                    handshakerReq.reqOneof_ = singleFieldBuilderV32.build();
                }
            }
            if (this.reqOneofCase_ == 3) {
                SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV33 = this.nextBuilder_;
                if (singleFieldBuilderV33 == null) {
                    handshakerReq.reqOneof_ = this.reqOneof_;
                } else {
                    handshakerReq.reqOneof_ = singleFieldBuilderV33.build();
                }
            }
            handshakerReq.reqOneofCase_ = this.reqOneofCase_;
            onBuilt();
            return handshakerReq;
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
            if (message instanceof HandshakerReq) {
                return mergeFrom((HandshakerReq) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HandshakerReq handshakerReq) {
            if (handshakerReq == HandshakerReq.getDefaultInstance()) {
                return this;
            }
            int i = C60102.$SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[handshakerReq.getReqOneofCase().ordinal()];
            if (i == 1) {
                mergeClientStart(handshakerReq.getClientStart());
            } else if (i == 2) {
                mergeServerStart(handshakerReq.getServerStart());
            } else if (i == 3) {
                mergeNext(handshakerReq.getNext());
            }
            mergeUnknownFields(handshakerReq.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            HandshakerReq handshakerReq = null;
            try {
                try {
                    HandshakerReq handshakerReq2 = (HandshakerReq) HandshakerReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (handshakerReq2 != null) {
                        mergeFrom(handshakerReq2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    HandshakerReq handshakerReq3 = (HandshakerReq) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        handshakerReq = handshakerReq3;
                        if (handshakerReq != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (handshakerReq != null) {
                    mergeFrom(handshakerReq);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public ReqOneofCase getReqOneofCase() {
            return ReqOneofCase.forNumber(this.reqOneofCase_);
        }

        public Builder clearReqOneof() {
            this.reqOneofCase_ = 0;
            this.reqOneof_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public boolean hasClientStart() {
            return this.reqOneofCase_ == 1;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public StartClientHandshakeReq getClientStart() {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 1) {
                    return (StartClientHandshakeReq) this.reqOneof_;
                }
                return StartClientHandshakeReq.getDefaultInstance();
            }
            if (this.reqOneofCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return StartClientHandshakeReq.getDefaultInstance();
        }

        public Builder setClientStart(StartClientHandshakeReq startClientHandshakeReq) {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(startClientHandshakeReq);
            } else {
                if (startClientHandshakeReq == null) {
                    throw new NullPointerException();
                }
                this.reqOneof_ = startClientHandshakeReq;
                onChanged();
            }
            this.reqOneofCase_ = 1;
            return this;
        }

        public Builder setClientStart(StartClientHandshakeReq.Builder builder) {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.reqOneof_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.reqOneofCase_ = 1;
            return this;
        }

        public Builder mergeClientStart(StartClientHandshakeReq startClientHandshakeReq) {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3 = this.clientStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 1 && this.reqOneof_ != StartClientHandshakeReq.getDefaultInstance()) {
                    this.reqOneof_ = StartClientHandshakeReq.newBuilder((StartClientHandshakeReq) this.reqOneof_).mergeFrom(startClientHandshakeReq).buildPartial();
                } else {
                    this.reqOneof_ = startClientHandshakeReq;
                }
                onChanged();
            } else {
                if (this.reqOneofCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(startClientHandshakeReq);
                }
                this.clientStartBuilder_.setMessage(startClientHandshakeReq);
            }
            this.reqOneofCase_ = 1;
            return this;
        }

        public Builder clearClientStart() {
            if (this.clientStartBuilder_ == null) {
                if (this.reqOneofCase_ == 1) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                    onChanged();
                }
            } else {
                if (this.reqOneofCase_ == 1) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                }
                this.clientStartBuilder_.clear();
            }
            return this;
        }

        public StartClientHandshakeReq.Builder getClientStartBuilder() {
            return getClientStartFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public StartClientHandshakeReqOrBuilder getClientStartOrBuilder() {
            SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> singleFieldBuilderV3;
            if (this.reqOneofCase_ == 1 && (singleFieldBuilderV3 = this.clientStartBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.reqOneofCase_ == 1) {
                return (StartClientHandshakeReq) this.reqOneof_;
            }
            return StartClientHandshakeReq.getDefaultInstance();
        }

        private SingleFieldBuilderV3<StartClientHandshakeReq, StartClientHandshakeReq.Builder, StartClientHandshakeReqOrBuilder> getClientStartFieldBuilder() {
            if (this.clientStartBuilder_ == null) {
                if (this.reqOneofCase_ != 1) {
                    this.reqOneof_ = StartClientHandshakeReq.getDefaultInstance();
                }
                this.clientStartBuilder_ = new SingleFieldBuilderV3<>((StartClientHandshakeReq) this.reqOneof_, getParentForChildren(), isClean());
                this.reqOneof_ = null;
            }
            this.reqOneofCase_ = 1;
            onChanged();
            return this.clientStartBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public boolean hasServerStart() {
            return this.reqOneofCase_ == 2;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public StartServerHandshakeReq getServerStart() {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 2) {
                    return (StartServerHandshakeReq) this.reqOneof_;
                }
                return StartServerHandshakeReq.getDefaultInstance();
            }
            if (this.reqOneofCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return StartServerHandshakeReq.getDefaultInstance();
        }

        public Builder setServerStart(StartServerHandshakeReq startServerHandshakeReq) {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(startServerHandshakeReq);
            } else {
                if (startServerHandshakeReq == null) {
                    throw new NullPointerException();
                }
                this.reqOneof_ = startServerHandshakeReq;
                onChanged();
            }
            this.reqOneofCase_ = 2;
            return this;
        }

        public Builder setServerStart(StartServerHandshakeReq.Builder builder) {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.reqOneof_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.reqOneofCase_ = 2;
            return this;
        }

        public Builder mergeServerStart(StartServerHandshakeReq startServerHandshakeReq) {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3 = this.serverStartBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 2 && this.reqOneof_ != StartServerHandshakeReq.getDefaultInstance()) {
                    this.reqOneof_ = StartServerHandshakeReq.newBuilder((StartServerHandshakeReq) this.reqOneof_).mergeFrom(startServerHandshakeReq).buildPartial();
                } else {
                    this.reqOneof_ = startServerHandshakeReq;
                }
                onChanged();
            } else {
                if (this.reqOneofCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(startServerHandshakeReq);
                }
                this.serverStartBuilder_.setMessage(startServerHandshakeReq);
            }
            this.reqOneofCase_ = 2;
            return this;
        }

        public Builder clearServerStart() {
            if (this.serverStartBuilder_ == null) {
                if (this.reqOneofCase_ == 2) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                    onChanged();
                }
            } else {
                if (this.reqOneofCase_ == 2) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                }
                this.serverStartBuilder_.clear();
            }
            return this;
        }

        public StartServerHandshakeReq.Builder getServerStartBuilder() {
            return getServerStartFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public StartServerHandshakeReqOrBuilder getServerStartOrBuilder() {
            SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> singleFieldBuilderV3;
            if (this.reqOneofCase_ == 2 && (singleFieldBuilderV3 = this.serverStartBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.reqOneofCase_ == 2) {
                return (StartServerHandshakeReq) this.reqOneof_;
            }
            return StartServerHandshakeReq.getDefaultInstance();
        }

        private SingleFieldBuilderV3<StartServerHandshakeReq, StartServerHandshakeReq.Builder, StartServerHandshakeReqOrBuilder> getServerStartFieldBuilder() {
            if (this.serverStartBuilder_ == null) {
                if (this.reqOneofCase_ != 2) {
                    this.reqOneof_ = StartServerHandshakeReq.getDefaultInstance();
                }
                this.serverStartBuilder_ = new SingleFieldBuilderV3<>((StartServerHandshakeReq) this.reqOneof_, getParentForChildren(), isClean());
                this.reqOneof_ = null;
            }
            this.reqOneofCase_ = 2;
            onChanged();
            return this.serverStartBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public boolean hasNext() {
            return this.reqOneofCase_ == 3;
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public NextHandshakeMessageReq getNext() {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 3) {
                    return (NextHandshakeMessageReq) this.reqOneof_;
                }
                return NextHandshakeMessageReq.getDefaultInstance();
            }
            if (this.reqOneofCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return NextHandshakeMessageReq.getDefaultInstance();
        }

        public Builder setNext(NextHandshakeMessageReq nextHandshakeMessageReq) {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(nextHandshakeMessageReq);
            } else {
                if (nextHandshakeMessageReq == null) {
                    throw new NullPointerException();
                }
                this.reqOneof_ = nextHandshakeMessageReq;
                onChanged();
            }
            this.reqOneofCase_ = 3;
            return this;
        }

        public Builder setNext(NextHandshakeMessageReq.Builder builder) {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.reqOneof_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.reqOneofCase_ = 3;
            return this;
        }

        public Builder mergeNext(NextHandshakeMessageReq nextHandshakeMessageReq) {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3 = this.nextBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.reqOneofCase_ == 3 && this.reqOneof_ != NextHandshakeMessageReq.getDefaultInstance()) {
                    this.reqOneof_ = NextHandshakeMessageReq.newBuilder((NextHandshakeMessageReq) this.reqOneof_).mergeFrom(nextHandshakeMessageReq).buildPartial();
                } else {
                    this.reqOneof_ = nextHandshakeMessageReq;
                }
                onChanged();
            } else {
                if (this.reqOneofCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(nextHandshakeMessageReq);
                }
                this.nextBuilder_.setMessage(nextHandshakeMessageReq);
            }
            this.reqOneofCase_ = 3;
            return this;
        }

        public Builder clearNext() {
            if (this.nextBuilder_ == null) {
                if (this.reqOneofCase_ == 3) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                    onChanged();
                }
            } else {
                if (this.reqOneofCase_ == 3) {
                    this.reqOneofCase_ = 0;
                    this.reqOneof_ = null;
                }
                this.nextBuilder_.clear();
            }
            return this;
        }

        public NextHandshakeMessageReq.Builder getNextBuilder() {
            return getNextFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerReqOrBuilder
        public NextHandshakeMessageReqOrBuilder getNextOrBuilder() {
            SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> singleFieldBuilderV3;
            if (this.reqOneofCase_ == 3 && (singleFieldBuilderV3 = this.nextBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.reqOneofCase_ == 3) {
                return (NextHandshakeMessageReq) this.reqOneof_;
            }
            return NextHandshakeMessageReq.getDefaultInstance();
        }

        private SingleFieldBuilderV3<NextHandshakeMessageReq, NextHandshakeMessageReq.Builder, NextHandshakeMessageReqOrBuilder> getNextFieldBuilder() {
            if (this.nextBuilder_ == null) {
                if (this.reqOneofCase_ != 3) {
                    this.reqOneof_ = NextHandshakeMessageReq.getDefaultInstance();
                }
                this.nextBuilder_ = new SingleFieldBuilderV3<>((NextHandshakeMessageReq) this.reqOneof_, getParentForChildren(), isClean());
                this.reqOneof_ = null;
            }
            this.reqOneofCase_ = 3;
            onChanged();
            return this.nextBuilder_;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.alts.internal.HandshakerReq$2 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C60102 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase = new int[ReqOneofCase.values().length];

        static {
            try {
                $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[ReqOneofCase.CLIENT_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[ReqOneofCase.SERVER_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[ReqOneofCase.NEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$HandshakerReq$ReqOneofCase[ReqOneofCase.REQONEOF_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static HandshakerReq getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HandshakerReq> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<HandshakerReq> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public HandshakerReq getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
