package com.google.longrunning;

import com.google.longrunning.Operation;
import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class ListOperationsResponse extends GeneratedMessageV3 implements ListOperationsResponseOrBuilder {
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    public static final int OPERATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private List<Operation> operations_;
    private static final ListOperationsResponse DEFAULT_INSTANCE = new ListOperationsResponse();
    private static final Parser<ListOperationsResponse> PARSER = new AbstractParser<ListOperationsResponse>() { // from class: com.google.longrunning.ListOperationsResponse.1
        @Override // com.google.protobuf.Parser
        public ListOperationsResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListOperationsResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private ListOperationsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListOperationsResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.operations_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListOperationsResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListOperationsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            if (!(z2 & true)) {
                                this.operations_ = new ArrayList();
                                z2 |= true;
                            }
                            this.operations_.add(codedInputStream.readMessage(Operation.parser(), extensionRegistryLite));
                        } else if (readTag == 18) {
                            this.nextPageToken_ = codedInputStream.readStringRequireUtf8();
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
                if (z2 & true) {
                    this.operations_ = Collections.unmodifiableList(this.operations_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return OperationsProto.f2012x63613830;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return OperationsProto.f2013x5aed2eae.ensureFieldAccessorsInitialized(ListOperationsResponse.class, Builder.class);
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public List<Operation> getOperationsList() {
        return this.operations_;
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public List<? extends OperationOrBuilder> getOperationsOrBuilderList() {
        return this.operations_;
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public int getOperationsCount() {
        return this.operations_.size();
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public Operation getOperations(int i) {
        return this.operations_.get(i);
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public OperationOrBuilder getOperationsOrBuilder(int i) {
        return this.operations_.get(i);
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.longrunning.ListOperationsResponseOrBuilder
    public ByteString getNextPageTokenBytes() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.nextPageToken_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
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
        for (int i = 0; i < this.operations_.size(); i++) {
            codedOutputStream.writeMessage(1, this.operations_.get(i));
        }
        if (!getNextPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.nextPageToken_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.operations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.operations_.get(i3));
        }
        if (!getNextPageTokenBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.nextPageToken_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListOperationsResponse)) {
            return super.equals(obj);
        }
        ListOperationsResponse listOperationsResponse = (ListOperationsResponse) obj;
        return getOperationsList().equals(listOperationsResponse.getOperationsList()) && getNextPageToken().equals(listOperationsResponse.getNextPageToken()) && this.unknownFields.equals(listOperationsResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getOperationsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getOperationsList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListOperationsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListOperationsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListOperationsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListOperationsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListOperationsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListOperationsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListOperationsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListOperationsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListOperationsResponse listOperationsResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listOperationsResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListOperationsResponseOrBuilder {
        private int bitField0_;
        private Object nextPageToken_;
        private RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> operationsBuilder_;
        private List<Operation> operations_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return OperationsProto.f2012x63613830;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.f2013x5aed2eae.ensureFieldAccessorsInitialized(ListOperationsResponse.class, Builder.class);
        }

        private Builder() {
            this.operations_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.operations_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListOperationsResponse.alwaysUseFieldBuilders) {
                getOperationsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.operations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return OperationsProto.f2012x63613830;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListOperationsResponse getDefaultInstanceForType() {
            return ListOperationsResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListOperationsResponse build() {
            ListOperationsResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListOperationsResponse buildPartial() {
            ListOperationsResponse listOperationsResponse = new ListOperationsResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.operations_ = Collections.unmodifiableList(this.operations_);
                    this.bitField0_ &= -2;
                }
                listOperationsResponse.operations_ = this.operations_;
            } else {
                listOperationsResponse.operations_ = repeatedFieldBuilderV3.build();
            }
            listOperationsResponse.nextPageToken_ = this.nextPageToken_;
            onBuilt();
            return listOperationsResponse;
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
            if (message instanceof ListOperationsResponse) {
                return mergeFrom((ListOperationsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListOperationsResponse listOperationsResponse) {
            if (listOperationsResponse == ListOperationsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.operationsBuilder_ == null) {
                if (!listOperationsResponse.operations_.isEmpty()) {
                    if (this.operations_.isEmpty()) {
                        this.operations_ = listOperationsResponse.operations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureOperationsIsMutable();
                        this.operations_.addAll(listOperationsResponse.operations_);
                    }
                    onChanged();
                }
            } else if (!listOperationsResponse.operations_.isEmpty()) {
                if (!this.operationsBuilder_.isEmpty()) {
                    this.operationsBuilder_.addAllMessages(listOperationsResponse.operations_);
                } else {
                    this.operationsBuilder_.dispose();
                    this.operationsBuilder_ = null;
                    this.operations_ = listOperationsResponse.operations_;
                    this.bitField0_ &= -2;
                    this.operationsBuilder_ = ListOperationsResponse.alwaysUseFieldBuilders ? getOperationsFieldBuilder() : null;
                }
            }
            if (!listOperationsResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listOperationsResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(listOperationsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListOperationsResponse listOperationsResponse = null;
            try {
                try {
                    ListOperationsResponse listOperationsResponse2 = (ListOperationsResponse) ListOperationsResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listOperationsResponse2 != null) {
                        mergeFrom(listOperationsResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListOperationsResponse listOperationsResponse3 = (ListOperationsResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listOperationsResponse = listOperationsResponse3;
                        if (listOperationsResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listOperationsResponse != null) {
                    mergeFrom(listOperationsResponse);
                }
                throw th;
            }
        }

        private void ensureOperationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.operations_ = new ArrayList(this.operations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public List<Operation> getOperationsList() {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.operations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public int getOperationsCount() {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.operations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public Operation getOperations(int i) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.operations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setOperations(int i, Operation operation) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, operation);
            } else {
                if (operation == null) {
                    throw new NullPointerException();
                }
                ensureOperationsIsMutable();
                this.operations_.set(i, operation);
                onChanged();
            }
            return this;
        }

        public Builder setOperations(int i, Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addOperations(Operation operation) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(operation);
            } else {
                if (operation == null) {
                    throw new NullPointerException();
                }
                ensureOperationsIsMutable();
                this.operations_.add(operation);
                onChanged();
            }
            return this;
        }

        public Builder addOperations(int i, Operation operation) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, operation);
            } else {
                if (operation == null) {
                    throw new NullPointerException();
                }
                ensureOperationsIsMutable();
                this.operations_.add(i, operation);
                onChanged();
            }
            return this;
        }

        public Builder addOperations(Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addOperations(int i, Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllOperations(Iterable<? extends Operation> iterable) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.operations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearOperations() {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.operations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeOperations(int i) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Operation.Builder getOperationsBuilder(int i) {
            return getOperationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public OperationOrBuilder getOperationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.operations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public List<? extends OperationOrBuilder> getOperationsOrBuilderList() {
            RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.operations_);
        }

        public Operation.Builder addOperationsBuilder() {
            return getOperationsFieldBuilder().addBuilder(Operation.getDefaultInstance());
        }

        public Operation.Builder addOperationsBuilder(int i) {
            return getOperationsFieldBuilder().addBuilder(i, Operation.getDefaultInstance());
        }

        public List<Operation.Builder> getOperationsBuilderList() {
            return getOperationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Operation, Operation.Builder, OperationOrBuilder> getOperationsFieldBuilder() {
            if (this.operationsBuilder_ == null) {
                this.operationsBuilder_ = new RepeatedFieldBuilderV3<>(this.operations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.operations_ = null;
            }
            return this.operationsBuilder_;
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.longrunning.ListOperationsResponseOrBuilder
        public ByteString getNextPageTokenBytes() {
            Object obj = this.nextPageToken_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.nextPageToken_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNextPageToken(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.nextPageToken_ = str;
            onChanged();
            return this;
        }

        public Builder clearNextPageToken() {
            this.nextPageToken_ = ListOperationsResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListOperationsResponse.checkByteStringIsUtf8(byteString);
                this.nextPageToken_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
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

    public static ListOperationsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListOperationsResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListOperationsResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListOperationsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
