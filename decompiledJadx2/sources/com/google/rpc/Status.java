package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class Status extends GeneratedMessageV3 implements StatusOrBuilder {
    public static final int CODE_FIELD_NUMBER = 1;
    public static final int DETAILS_FIELD_NUMBER = 3;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private int code_;
    private List<Any> details_;
    private byte memoizedIsInitialized;
    private volatile Object message_;
    private static final Status DEFAULT_INSTANCE = new Status();
    private static final Parser<Status> PARSER = new AbstractParser<Status>() { // from class: com.google.rpc.Status.1
        @Override // com.google.protobuf.Parser
        public Status parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Status(codedInputStream, extensionRegistryLite);
        }
    };

    private Status(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Status() {
        this.memoizedIsInitialized = (byte) -1;
        this.message_ = "";
        this.details_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Status();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Status(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag == 8) {
                                this.code_ = codedInputStream.readInt32();
                            } else if (readTag == 18) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                if (!(z2 & true)) {
                                    this.details_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.details_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.details_ = Collections.unmodifiableList(this.details_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StatusProto.internal_static_google_rpc_Status_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StatusProto.internal_static_google_rpc_Status_fieldAccessorTable.ensureFieldAccessorsInitialized(Status.class, Builder.class);
    }

    @Override // com.google.rpc.StatusOrBuilder
    public int getCode() {
        return this.code_;
    }

    @Override // com.google.rpc.StatusOrBuilder
    public String getMessage() {
        Object obj = this.message_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.message_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.StatusOrBuilder
    public ByteString getMessageBytes() {
        Object obj = this.message_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.message_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.StatusOrBuilder
    public List<Any> getDetailsList() {
        return this.details_;
    }

    @Override // com.google.rpc.StatusOrBuilder
    public List<? extends AnyOrBuilder> getDetailsOrBuilderList() {
        return this.details_;
    }

    @Override // com.google.rpc.StatusOrBuilder
    public int getDetailsCount() {
        return this.details_.size();
    }

    @Override // com.google.rpc.StatusOrBuilder
    public Any getDetails(int i) {
        return this.details_.get(i);
    }

    @Override // com.google.rpc.StatusOrBuilder
    public AnyOrBuilder getDetailsOrBuilder(int i) {
        return this.details_.get(i);
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
        int i = this.code_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        if (!getMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
        }
        for (int i2 = 0; i2 < this.details_.size(); i2++) {
            codedOutputStream.writeMessage(3, this.details_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.code_;
        int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) + 0 : 0;
        if (!getMessageBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.message_);
        }
        for (int i3 = 0; i3 < this.details_.size(); i3++) {
            computeInt32Size += CodedOutputStream.computeMessageSize(3, this.details_.get(i3));
        }
        int serializedSize = computeInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Status)) {
            return super.equals(obj);
        }
        Status status = (Status) obj;
        return getCode() == status.getCode() && getMessage().equals(status.getMessage()) && getDetailsList().equals(status.getDetailsList()) && this.unknownFields.equals(status.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCode()) * 37) + 2) * 53) + getMessage().hashCode();
        if (getDetailsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getDetailsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Status parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Status parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Status parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Status parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Status parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Status parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Status parseFrom(InputStream inputStream) throws IOException {
        return (Status) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Status parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Status) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Status parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Status) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Status parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Status) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Status parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Status) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Status parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Status) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Status status) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(status);
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
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StatusOrBuilder {
        private int bitField0_;
        private int code_;
        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> detailsBuilder_;
        private List<Any> details_;
        private Object message_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StatusProto.internal_static_google_rpc_Status_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StatusProto.internal_static_google_rpc_Status_fieldAccessorTable.ensureFieldAccessorsInitialized(Status.class, Builder.class);
        }

        private Builder() {
            this.message_ = "";
            this.details_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.message_ = "";
            this.details_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Status.alwaysUseFieldBuilders) {
                getDetailsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.code_ = 0;
            this.message_ = "";
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.details_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return StatusProto.internal_static_google_rpc_Status_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Status getDefaultInstanceForType() {
            return Status.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Status build() {
            Status buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Status buildPartial() {
            Status status = new Status(this);
            int i = this.bitField0_;
            status.code_ = this.code_;
            status.message_ = this.message_;
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                status.details_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.details_ = Collections.unmodifiableList(this.details_);
                    this.bitField0_ &= -2;
                }
                status.details_ = this.details_;
            }
            onBuilt();
            return status;
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
            if (message instanceof Status) {
                return mergeFrom((Status) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Status status) {
            if (status == Status.getDefaultInstance()) {
                return this;
            }
            if (status.getCode() != 0) {
                setCode(status.getCode());
            }
            if (!status.getMessage().isEmpty()) {
                this.message_ = status.message_;
                onChanged();
            }
            if (this.detailsBuilder_ == null) {
                if (!status.details_.isEmpty()) {
                    if (this.details_.isEmpty()) {
                        this.details_ = status.details_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureDetailsIsMutable();
                        this.details_.addAll(status.details_);
                    }
                    onChanged();
                }
            } else if (!status.details_.isEmpty()) {
                if (!this.detailsBuilder_.isEmpty()) {
                    this.detailsBuilder_.addAllMessages(status.details_);
                } else {
                    this.detailsBuilder_.dispose();
                    this.detailsBuilder_ = null;
                    this.details_ = status.details_;
                    this.bitField0_ &= -2;
                    this.detailsBuilder_ = Status.alwaysUseFieldBuilders ? getDetailsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(status.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Status status = null;
            try {
                try {
                    Status status2 = (Status) Status.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (status2 != null) {
                        mergeFrom(status2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Status status3 = (Status) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        status = status3;
                        if (status != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (status != null) {
                    mergeFrom(status);
                }
                throw th;
            }
        }

        @Override // com.google.rpc.StatusOrBuilder
        public int getCode() {
            return this.code_;
        }

        public Builder setCode(int i) {
            this.code_ = i;
            onChanged();
            return this;
        }

        public Builder clearCode() {
            this.code_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.rpc.StatusOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.message_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.StatusOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMessage(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.message_ = str;
            onChanged();
            return this;
        }

        public Builder clearMessage() {
            this.message_ = Status.getDefaultInstance().getMessage();
            onChanged();
            return this;
        }

        public Builder setMessageBytes(ByteString byteString) {
            if (byteString != null) {
                Status.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureDetailsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.details_ = new ArrayList(this.details_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.rpc.StatusOrBuilder
        public List<Any> getDetailsList() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.details_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.rpc.StatusOrBuilder
        public int getDetailsCount() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.details_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.rpc.StatusOrBuilder
        public Any getDetails(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.details_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setDetails(int i, Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureDetailsIsMutable();
                this.details_.set(i, any);
                onChanged();
            }
            return this;
        }

        public Builder setDetails(int i, Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                this.details_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addDetails(Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureDetailsIsMutable();
                this.details_.add(any);
                onChanged();
            }
            return this;
        }

        public Builder addDetails(int i, Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureDetailsIsMutable();
                this.details_.add(i, any);
                onChanged();
            }
            return this;
        }

        public Builder addDetails(Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                this.details_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addDetails(int i, Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                this.details_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllDetails(Iterable<? extends Any> iterable) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.details_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearDetails() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.details_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeDetails(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                this.details_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Any.Builder getDetailsBuilder(int i) {
            return getDetailsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.rpc.StatusOrBuilder
        public AnyOrBuilder getDetailsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.details_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.rpc.StatusOrBuilder
        public List<? extends AnyOrBuilder> getDetailsOrBuilderList() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.details_);
        }

        public Any.Builder addDetailsBuilder() {
            return getDetailsFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public Any.Builder addDetailsBuilder(int i) {
            return getDetailsFieldBuilder().addBuilder(i, Any.getDefaultInstance());
        }

        public List<Any.Builder> getDetailsBuilderList() {
            return getDetailsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getDetailsFieldBuilder() {
            if (this.detailsBuilder_ == null) {
                this.detailsBuilder_ = new RepeatedFieldBuilderV3<>(this.details_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.details_ = null;
            }
            return this.detailsBuilder_;
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

    public static Status getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Status> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Status> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Status getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
