package com.google.longrunning;

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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Operation extends GeneratedMessageV3 implements OperationOrBuilder {
    public static final int DONE_FIELD_NUMBER = 3;
    public static final int ERROR_FIELD_NUMBER = 4;
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int RESPONSE_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private boolean done_;
    private byte memoizedIsInitialized;
    private Any metadata_;
    private volatile Object name_;
    private int resultCase_;
    private Object result_;
    private static final Operation DEFAULT_INSTANCE = new Operation();
    private static final Parser<Operation> PARSER = new AbstractParser<Operation>() { // from class: com.google.longrunning.Operation.1
        @Override // com.google.protobuf.Parser
        public Operation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Operation(codedInputStream, extensionRegistryLite);
        }
    };

    private Operation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.resultCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Operation() {
        this.resultCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Operation();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Operation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag != 10) {
                            if (readTag == 18) {
                                Any.Builder builder = this.metadata_ != null ? this.metadata_.toBuilder() : null;
                                this.metadata_ = (Any) codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.metadata_);
                                    this.metadata_ = builder.buildPartial();
                                }
                            } else if (readTag == 24) {
                                this.done_ = codedInputStream.readBool();
                            } else if (readTag == 34) {
                                Status.Builder builder2 = this.resultCase_ == 4 ? ((Status) this.result_).toBuilder() : null;
                                this.result_ = codedInputStream.readMessage(Status.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom((Status) this.result_);
                                    this.result_ = builder2.buildPartial();
                                }
                                this.resultCase_ = 4;
                            } else if (readTag == 42) {
                                Any.Builder builder3 = this.resultCase_ == 5 ? ((Any) this.result_).toBuilder() : null;
                                this.result_ = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom((Any) this.result_);
                                    this.result_ = builder3.buildPartial();
                                }
                                this.resultCase_ = 5;
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        } else {
                            this.name_ = codedInputStream.readStringRequireUtf8();
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
        return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return OperationsProto.internal_static_google_longrunning_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum ResultCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        ERROR(4),
        RESPONSE(5),
        RESULT_NOT_SET(0);

        private final int value;

        ResultCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ResultCase valueOf(int i) {
            return forNumber(i);
        }

        public static ResultCase forNumber(int i) {
            if (i == 0) {
                return RESULT_NOT_SET;
            }
            if (i == 4) {
                return ERROR;
            }
            if (i != 5) {
                return null;
            }
            return RESPONSE;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public ResultCase getResultCase() {
        return ResultCase.forNumber(this.resultCase_);
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Any getMetadata() {
        Any any = this.metadata_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public AnyOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean getDone() {
        return this.done_;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasError() {
        return this.resultCase_ == 4;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Status getError() {
        if (this.resultCase_ == 4) {
            return (Status) this.result_;
        }
        return Status.getDefaultInstance();
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public StatusOrBuilder getErrorOrBuilder() {
        if (this.resultCase_ == 4) {
            return (Status) this.result_;
        }
        return Status.getDefaultInstance();
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public boolean hasResponse() {
        return this.resultCase_ == 5;
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public Any getResponse() {
        if (this.resultCase_ == 5) {
            return (Any) this.result_;
        }
        return Any.getDefaultInstance();
    }

    @Override // com.google.longrunning.OperationOrBuilder
    public AnyOrBuilder getResponseOrBuilder() {
        if (this.resultCase_ == 5) {
            return (Any) this.result_;
        }
        return Any.getDefaultInstance();
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(2, getMetadata());
        }
        boolean z = this.done_;
        if (z) {
            codedOutputStream.writeBool(3, z);
        }
        if (this.resultCase_ == 4) {
            codedOutputStream.writeMessage(4, (Status) this.result_);
        }
        if (this.resultCase_ == 5) {
            codedOutputStream.writeMessage(5, (Any) this.result_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getNameBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        if (this.metadata_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getMetadata());
        }
        boolean z = this.done_;
        if (z) {
            computeStringSize += CodedOutputStream.computeBoolSize(3, z);
        }
        if (this.resultCase_ == 4) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, (Status) this.result_);
        }
        if (this.resultCase_ == 5) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, (Any) this.result_);
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
        if (!(obj instanceof Operation)) {
            return super.equals(obj);
        }
        Operation operation = (Operation) obj;
        if (!getName().equals(operation.getName()) || hasMetadata() != operation.hasMetadata()) {
            return false;
        }
        if ((hasMetadata() && !getMetadata().equals(operation.getMetadata())) || getDone() != operation.getDone() || !getResultCase().equals(operation.getResultCase())) {
            return false;
        }
        int i = this.resultCase_;
        if (i == 4) {
            if (!getError().equals(operation.getError())) {
                return false;
            }
        } else if (i == 5 && !getResponse().equals(operation.getResponse())) {
            return false;
        }
        return this.unknownFields.equals(operation.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasMetadata()) {
            hashCode2 = (((hashCode2 * 37) + 2) * 53) + getMetadata().hashCode();
        }
        int hashBoolean = (((hashCode2 * 37) + 3) * 53) + Internal.hashBoolean(getDone());
        int i2 = this.resultCase_;
        if (i2 == 4) {
            i = ((hashBoolean * 37) + 4) * 53;
            hashCode = getError().hashCode();
        } else {
            if (i2 == 5) {
                i = ((hashBoolean * 37) + 5) * 53;
                hashCode = getResponse().hashCode();
            }
            int hashCode3 = (hashBoolean * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }
        hashBoolean = i + hashCode;
        int hashCode32 = (hashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode32;
        return hashCode32;
    }

    public static Operation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Operation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Operation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Operation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Operation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Operation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Operation parseFrom(InputStream inputStream) throws IOException {
        return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Operation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Operation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Operation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Operation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Operation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Operation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Operation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Operation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Operation operation) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(operation);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OperationOrBuilder {
        private boolean done_;
        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> errorBuilder_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> metadataBuilder_;
        private Any metadata_;
        private Object name_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> responseBuilder_;
        private int resultCase_;
        private Object result_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_Operation_fieldAccessorTable.ensureFieldAccessorsInitialized(Operation.class, Builder.class);
        }

        private Builder() {
            this.resultCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.resultCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Operation.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            this.done_ = false;
            this.resultCase_ = 0;
            this.result_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_Operation_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Operation getDefaultInstanceForType() {
            return Operation.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Operation build() {
            Operation buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Operation buildPartial() {
            Operation operation = new Operation(this);
            operation.name_ = this.name_;
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                operation.metadata_ = this.metadata_;
            } else {
                operation.metadata_ = singleFieldBuilderV3.build();
            }
            operation.done_ = this.done_;
            if (this.resultCase_ == 4) {
                SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV32 = this.errorBuilder_;
                if (singleFieldBuilderV32 == null) {
                    operation.result_ = this.result_;
                } else {
                    operation.result_ = singleFieldBuilderV32.build();
                }
            }
            if (this.resultCase_ == 5) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV33 = this.responseBuilder_;
                if (singleFieldBuilderV33 == null) {
                    operation.result_ = this.result_;
                } else {
                    operation.result_ = singleFieldBuilderV33.build();
                }
            }
            operation.resultCase_ = this.resultCase_;
            onBuilt();
            return operation;
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
            if (message instanceof Operation) {
                return mergeFrom((Operation) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Operation operation) {
            if (operation == Operation.getDefaultInstance()) {
                return this;
            }
            if (!operation.getName().isEmpty()) {
                this.name_ = operation.name_;
                onChanged();
            }
            if (operation.hasMetadata()) {
                mergeMetadata(operation.getMetadata());
            }
            if (operation.getDone()) {
                setDone(operation.getDone());
            }
            int i = C32862.$SwitchMap$com$google$longrunning$Operation$ResultCase[operation.getResultCase().ordinal()];
            if (i == 1) {
                mergeError(operation.getError());
            } else if (i == 2) {
                mergeResponse(operation.getResponse());
            }
            mergeUnknownFields(operation.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Operation operation = null;
            try {
                try {
                    Operation operation2 = (Operation) Operation.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (operation2 != null) {
                        mergeFrom(operation2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Operation operation3 = (Operation) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        operation = operation3;
                        if (operation != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (operation != null) {
                    mergeFrom(operation);
                }
                throw th;
            }
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public ResultCase getResultCase() {
            return ResultCase.forNumber(this.resultCase_);
        }

        public Builder clearResult() {
            this.resultCase_ = 0;
            this.result_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Operation.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Operation.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Any getMetadata() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any = this.metadata_;
                return any == null ? Any.getDefaultInstance() : any;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setMetadata(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                this.metadata_ = any;
                onChanged();
            }
            return this;
        }

        public Builder setMetadata(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMetadata(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.metadata_;
                if (any2 != null) {
                    this.metadata_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.metadata_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getMetadataBuilder() {
            onChanged();
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public AnyOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.metadata_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean getDone() {
            return this.done_;
        }

        public Builder setDone(boolean z) {
            this.done_ = z;
            onChanged();
            return this;
        }

        public Builder clearDone() {
            this.done_ = false;
            onChanged();
            return this;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasError() {
            return this.resultCase_ == 4;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Status getError() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ == 4) {
                    return (Status) this.result_;
                }
                return Status.getDefaultInstance();
            }
            if (this.resultCase_ == 4) {
                return singleFieldBuilderV3.getMessage();
            }
            return Status.getDefaultInstance();
        }

        public Builder setError(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(status);
            } else {
                if (status == null) {
                    throw new NullPointerException();
                }
                this.result_ = status;
                onChanged();
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder setError(Status.Builder builder) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.result_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder mergeError(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.errorBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ == 4 && this.result_ != Status.getDefaultInstance()) {
                    this.result_ = Status.newBuilder((Status) this.result_).mergeFrom(status).buildPartial();
                } else {
                    this.result_ = status;
                }
                onChanged();
            } else {
                if (this.resultCase_ == 4) {
                    singleFieldBuilderV3.mergeFrom(status);
                }
                this.errorBuilder_.setMessage(status);
            }
            this.resultCase_ = 4;
            return this;
        }

        public Builder clearError() {
            if (this.errorBuilder_ == null) {
                if (this.resultCase_ == 4) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                    onChanged();
                }
            } else {
                if (this.resultCase_ == 4) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                }
                this.errorBuilder_.clear();
            }
            return this;
        }

        public Status.Builder getErrorBuilder() {
            return getErrorFieldBuilder().getBuilder();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public StatusOrBuilder getErrorOrBuilder() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3;
            if (this.resultCase_ == 4 && (singleFieldBuilderV3 = this.errorBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.resultCase_ == 4) {
                return (Status) this.result_;
            }
            return Status.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> getErrorFieldBuilder() {
            if (this.errorBuilder_ == null) {
                if (this.resultCase_ != 4) {
                    this.result_ = Status.getDefaultInstance();
                }
                this.errorBuilder_ = new SingleFieldBuilderV3<>((Status) this.result_, getParentForChildren(), isClean());
                this.result_ = null;
            }
            this.resultCase_ = 4;
            onChanged();
            return this.errorBuilder_;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public boolean hasResponse() {
            return this.resultCase_ == 5;
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public Any getResponse() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ == 5) {
                    return (Any) this.result_;
                }
                return Any.getDefaultInstance();
            }
            if (this.resultCase_ == 5) {
                return singleFieldBuilderV3.getMessage();
            }
            return Any.getDefaultInstance();
        }

        public Builder setResponse(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                this.result_ = any;
                onChanged();
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder setResponse(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.result_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder mergeResponse(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.resultCase_ == 5 && this.result_ != Any.getDefaultInstance()) {
                    this.result_ = Any.newBuilder((Any) this.result_).mergeFrom(any).buildPartial();
                } else {
                    this.result_ = any;
                }
                onChanged();
            } else {
                if (this.resultCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(any);
                }
                this.responseBuilder_.setMessage(any);
            }
            this.resultCase_ = 5;
            return this;
        }

        public Builder clearResponse() {
            if (this.responseBuilder_ == null) {
                if (this.resultCase_ == 5) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                    onChanged();
                }
            } else {
                if (this.resultCase_ == 5) {
                    this.resultCase_ = 0;
                    this.result_ = null;
                }
                this.responseBuilder_.clear();
            }
            return this;
        }

        public Any.Builder getResponseBuilder() {
            return getResponseFieldBuilder().getBuilder();
        }

        @Override // com.google.longrunning.OperationOrBuilder
        public AnyOrBuilder getResponseOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3;
            if (this.resultCase_ == 5 && (singleFieldBuilderV3 = this.responseBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.resultCase_ == 5) {
                return (Any) this.result_;
            }
            return Any.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getResponseFieldBuilder() {
            if (this.responseBuilder_ == null) {
                if (this.resultCase_ != 5) {
                    this.result_ = Any.getDefaultInstance();
                }
                this.responseBuilder_ = new SingleFieldBuilderV3<>((Any) this.result_, getParentForChildren(), isClean());
                this.result_ = null;
            }
            this.resultCase_ = 5;
            onChanged();
            return this.responseBuilder_;
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
      
     */
    /* renamed from: com.google.longrunning.Operation$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C32862 {
        static final /* synthetic */ int[] $SwitchMap$com$google$longrunning$Operation$ResultCase = new int[ResultCase.values().length];

        static {
            try {
                $SwitchMap$com$google$longrunning$Operation$ResultCase[ResultCase.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$longrunning$Operation$ResultCase[ResultCase.RESPONSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$longrunning$Operation$ResultCase[ResultCase.RESULT_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static Operation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Operation> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Operation> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Operation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
