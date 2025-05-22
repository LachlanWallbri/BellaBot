package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.SessionEntityType;
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
/* loaded from: classes2.dex */
public final class ListSessionEntityTypesResponse extends GeneratedMessageV3 implements ListSessionEntityTypesResponseOrBuilder {
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    public static final int SESSION_ENTITY_TYPES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private List<SessionEntityType> sessionEntityTypes_;
    private static final ListSessionEntityTypesResponse DEFAULT_INSTANCE = new ListSessionEntityTypesResponse();
    private static final Parser<ListSessionEntityTypesResponse> PARSER = new AbstractParser<ListSessionEntityTypesResponse>() { // from class: com.google.cloud.dialogflow.v2.ListSessionEntityTypesResponse.1
        @Override // com.google.protobuf.Parser
        public ListSessionEntityTypesResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListSessionEntityTypesResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private ListSessionEntityTypesResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListSessionEntityTypesResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.sessionEntityTypes_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListSessionEntityTypesResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListSessionEntityTypesResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.sessionEntityTypes_ = new ArrayList();
                                z2 |= true;
                            }
                            this.sessionEntityTypes_.add(codedInputStream.readMessage(SessionEntityType.parser(), extensionRegistryLite));
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
                    this.sessionEntityTypes_ = Collections.unmodifiableList(this.sessionEntityTypes_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionEntityTypeProto.f1540x1ecf2854;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionEntityTypeProto.f1541x5b0abad2.ensureFieldAccessorsInitialized(ListSessionEntityTypesResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
    public List<SessionEntityType> getSessionEntityTypesList() {
        return this.sessionEntityTypes_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
    public List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList() {
        return this.sessionEntityTypes_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
    public int getSessionEntityTypesCount() {
        return this.sessionEntityTypes_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
    public SessionEntityType getSessionEntityTypes(int i) {
        return this.sessionEntityTypes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
    public SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i) {
        return this.sessionEntityTypes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
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
        for (int i = 0; i < this.sessionEntityTypes_.size(); i++) {
            codedOutputStream.writeMessage(1, this.sessionEntityTypes_.get(i));
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
        for (int i3 = 0; i3 < this.sessionEntityTypes_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.sessionEntityTypes_.get(i3));
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
        if (!(obj instanceof ListSessionEntityTypesResponse)) {
            return super.equals(obj);
        }
        ListSessionEntityTypesResponse listSessionEntityTypesResponse = (ListSessionEntityTypesResponse) obj;
        return getSessionEntityTypesList().equals(listSessionEntityTypesResponse.getSessionEntityTypesList()) && getNextPageToken().equals(listSessionEntityTypesResponse.getNextPageToken()) && this.unknownFields.equals(listSessionEntityTypesResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getSessionEntityTypesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getSessionEntityTypesList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListSessionEntityTypesResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListSessionEntityTypesResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListSessionEntityTypesResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListSessionEntityTypesResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListSessionEntityTypesResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListSessionEntityTypesResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListSessionEntityTypesResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListSessionEntityTypesResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListSessionEntityTypesResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListSessionEntityTypesResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListSessionEntityTypesResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListSessionEntityTypesResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListSessionEntityTypesResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListSessionEntityTypesResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListSessionEntityTypesResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListSessionEntityTypesResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListSessionEntityTypesResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListSessionEntityTypesResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListSessionEntityTypesResponse listSessionEntityTypesResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listSessionEntityTypesResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListSessionEntityTypesResponseOrBuilder {
        private int bitField0_;
        private Object nextPageToken_;
        private RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> sessionEntityTypesBuilder_;
        private List<SessionEntityType> sessionEntityTypes_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionEntityTypeProto.f1540x1ecf2854;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionEntityTypeProto.f1541x5b0abad2.ensureFieldAccessorsInitialized(ListSessionEntityTypesResponse.class, Builder.class);
        }

        private Builder() {
            this.sessionEntityTypes_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.sessionEntityTypes_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListSessionEntityTypesResponse.alwaysUseFieldBuilders) {
                getSessionEntityTypesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.sessionEntityTypes_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionEntityTypeProto.f1540x1ecf2854;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListSessionEntityTypesResponse getDefaultInstanceForType() {
            return ListSessionEntityTypesResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListSessionEntityTypesResponse build() {
            ListSessionEntityTypesResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListSessionEntityTypesResponse buildPartial() {
            ListSessionEntityTypesResponse listSessionEntityTypesResponse = new ListSessionEntityTypesResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.sessionEntityTypes_ = Collections.unmodifiableList(this.sessionEntityTypes_);
                    this.bitField0_ &= -2;
                }
                listSessionEntityTypesResponse.sessionEntityTypes_ = this.sessionEntityTypes_;
            } else {
                listSessionEntityTypesResponse.sessionEntityTypes_ = repeatedFieldBuilderV3.build();
            }
            listSessionEntityTypesResponse.nextPageToken_ = this.nextPageToken_;
            onBuilt();
            return listSessionEntityTypesResponse;
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
            if (message instanceof ListSessionEntityTypesResponse) {
                return mergeFrom((ListSessionEntityTypesResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListSessionEntityTypesResponse listSessionEntityTypesResponse) {
            if (listSessionEntityTypesResponse == ListSessionEntityTypesResponse.getDefaultInstance()) {
                return this;
            }
            if (this.sessionEntityTypesBuilder_ == null) {
                if (!listSessionEntityTypesResponse.sessionEntityTypes_.isEmpty()) {
                    if (this.sessionEntityTypes_.isEmpty()) {
                        this.sessionEntityTypes_ = listSessionEntityTypesResponse.sessionEntityTypes_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureSessionEntityTypesIsMutable();
                        this.sessionEntityTypes_.addAll(listSessionEntityTypesResponse.sessionEntityTypes_);
                    }
                    onChanged();
                }
            } else if (!listSessionEntityTypesResponse.sessionEntityTypes_.isEmpty()) {
                if (!this.sessionEntityTypesBuilder_.isEmpty()) {
                    this.sessionEntityTypesBuilder_.addAllMessages(listSessionEntityTypesResponse.sessionEntityTypes_);
                } else {
                    this.sessionEntityTypesBuilder_.dispose();
                    this.sessionEntityTypesBuilder_ = null;
                    this.sessionEntityTypes_ = listSessionEntityTypesResponse.sessionEntityTypes_;
                    this.bitField0_ &= -2;
                    this.sessionEntityTypesBuilder_ = ListSessionEntityTypesResponse.alwaysUseFieldBuilders ? getSessionEntityTypesFieldBuilder() : null;
                }
            }
            if (!listSessionEntityTypesResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listSessionEntityTypesResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(listSessionEntityTypesResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListSessionEntityTypesResponse listSessionEntityTypesResponse = null;
            try {
                try {
                    ListSessionEntityTypesResponse listSessionEntityTypesResponse2 = (ListSessionEntityTypesResponse) ListSessionEntityTypesResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listSessionEntityTypesResponse2 != null) {
                        mergeFrom(listSessionEntityTypesResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListSessionEntityTypesResponse listSessionEntityTypesResponse3 = (ListSessionEntityTypesResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listSessionEntityTypesResponse = listSessionEntityTypesResponse3;
                        if (listSessionEntityTypesResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listSessionEntityTypesResponse != null) {
                    mergeFrom(listSessionEntityTypesResponse);
                }
                throw th;
            }
        }

        private void ensureSessionEntityTypesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.sessionEntityTypes_ = new ArrayList(this.sessionEntityTypes_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
        public List<SessionEntityType> getSessionEntityTypesList() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.sessionEntityTypes_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
        public int getSessionEntityTypesCount() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sessionEntityTypes_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
        public SessionEntityType getSessionEntityTypes(int i) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sessionEntityTypes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSessionEntityTypes(int i, SessionEntityType sessionEntityType) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, sessionEntityType);
            } else {
                if (sessionEntityType == null) {
                    throw new NullPointerException();
                }
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.set(i, sessionEntityType);
                onChanged();
            }
            return this;
        }

        public Builder setSessionEntityTypes(int i, SessionEntityType.Builder builder) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addSessionEntityTypes(SessionEntityType sessionEntityType) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(sessionEntityType);
            } else {
                if (sessionEntityType == null) {
                    throw new NullPointerException();
                }
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.add(sessionEntityType);
                onChanged();
            }
            return this;
        }

        public Builder addSessionEntityTypes(int i, SessionEntityType sessionEntityType) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, sessionEntityType);
            } else {
                if (sessionEntityType == null) {
                    throw new NullPointerException();
                }
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.add(i, sessionEntityType);
                onChanged();
            }
            return this;
        }

        public Builder addSessionEntityTypes(SessionEntityType.Builder builder) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addSessionEntityTypes(int i, SessionEntityType.Builder builder) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllSessionEntityTypes(Iterable<? extends SessionEntityType> iterable) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.sessionEntityTypes_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSessionEntityTypes() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.sessionEntityTypes_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSessionEntityTypes(int i) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public SessionEntityType.Builder getSessionEntityTypesBuilder(int i) {
            return getSessionEntityTypesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
        public SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sessionEntityTypes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
        public List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.sessionEntityTypes_);
        }

        public SessionEntityType.Builder addSessionEntityTypesBuilder() {
            return getSessionEntityTypesFieldBuilder().addBuilder(SessionEntityType.getDefaultInstance());
        }

        public SessionEntityType.Builder addSessionEntityTypesBuilder(int i) {
            return getSessionEntityTypesFieldBuilder().addBuilder(i, SessionEntityType.getDefaultInstance());
        }

        public List<SessionEntityType.Builder> getSessionEntityTypesBuilderList() {
            return getSessionEntityTypesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> getSessionEntityTypesFieldBuilder() {
            if (this.sessionEntityTypesBuilder_ == null) {
                this.sessionEntityTypesBuilder_ = new RepeatedFieldBuilderV3<>(this.sessionEntityTypes_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.sessionEntityTypes_ = null;
            }
            return this.sessionEntityTypesBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListSessionEntityTypesResponseOrBuilder
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
            this.nextPageToken_ = ListSessionEntityTypesResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListSessionEntityTypesResponse.checkByteStringIsUtf8(byteString);
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

    public static ListSessionEntityTypesResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListSessionEntityTypesResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListSessionEntityTypesResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListSessionEntityTypesResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
