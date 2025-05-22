package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.KnowledgeBase;
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
public final class ListKnowledgeBasesResponse extends GeneratedMessageV3 implements ListKnowledgeBasesResponseOrBuilder {
    public static final int KNOWLEDGE_BASES_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private List<KnowledgeBase> knowledgeBases_;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private static final ListKnowledgeBasesResponse DEFAULT_INSTANCE = new ListKnowledgeBasesResponse();
    private static final Parser<ListKnowledgeBasesResponse> PARSER = new AbstractParser<ListKnowledgeBasesResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponse.1
        @Override // com.google.protobuf.Parser
        public ListKnowledgeBasesResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListKnowledgeBasesResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private ListKnowledgeBasesResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListKnowledgeBasesResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.knowledgeBases_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListKnowledgeBasesResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListKnowledgeBasesResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.knowledgeBases_ = new ArrayList();
                                z2 |= true;
                            }
                            this.knowledgeBases_.add(codedInputStream.readMessage(KnowledgeBase.parser(), extensionRegistryLite));
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
                    this.knowledgeBases_ = Collections.unmodifiableList(this.knowledgeBases_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return KnowledgeBaseProto.f1815x7112bfc9;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return KnowledgeBaseProto.f1816xdb0e0d47.ensureFieldAccessorsInitialized(ListKnowledgeBasesResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
    public List<KnowledgeBase> getKnowledgeBasesList() {
        return this.knowledgeBases_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
    public List<? extends KnowledgeBaseOrBuilder> getKnowledgeBasesOrBuilderList() {
        return this.knowledgeBases_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
    public int getKnowledgeBasesCount() {
        return this.knowledgeBases_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
    public KnowledgeBase getKnowledgeBases(int i) {
        return this.knowledgeBases_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
    public KnowledgeBaseOrBuilder getKnowledgeBasesOrBuilder(int i) {
        return this.knowledgeBases_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
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
        for (int i = 0; i < this.knowledgeBases_.size(); i++) {
            codedOutputStream.writeMessage(1, this.knowledgeBases_.get(i));
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
        for (int i3 = 0; i3 < this.knowledgeBases_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.knowledgeBases_.get(i3));
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
        if (!(obj instanceof ListKnowledgeBasesResponse)) {
            return super.equals(obj);
        }
        ListKnowledgeBasesResponse listKnowledgeBasesResponse = (ListKnowledgeBasesResponse) obj;
        return getKnowledgeBasesList().equals(listKnowledgeBasesResponse.getKnowledgeBasesList()) && getNextPageToken().equals(listKnowledgeBasesResponse.getNextPageToken()) && this.unknownFields.equals(listKnowledgeBasesResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getKnowledgeBasesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getKnowledgeBasesList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListKnowledgeBasesResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListKnowledgeBasesResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListKnowledgeBasesResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListKnowledgeBasesResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListKnowledgeBasesResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListKnowledgeBasesResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListKnowledgeBasesResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListKnowledgeBasesResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListKnowledgeBasesResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListKnowledgeBasesResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListKnowledgeBasesResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListKnowledgeBasesResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListKnowledgeBasesResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListKnowledgeBasesResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListKnowledgeBasesResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListKnowledgeBasesResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListKnowledgeBasesResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListKnowledgeBasesResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListKnowledgeBasesResponse listKnowledgeBasesResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listKnowledgeBasesResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListKnowledgeBasesResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> knowledgeBasesBuilder_;
        private List<KnowledgeBase> knowledgeBases_;
        private Object nextPageToken_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return KnowledgeBaseProto.f1815x7112bfc9;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return KnowledgeBaseProto.f1816xdb0e0d47.ensureFieldAccessorsInitialized(ListKnowledgeBasesResponse.class, Builder.class);
        }

        private Builder() {
            this.knowledgeBases_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.knowledgeBases_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListKnowledgeBasesResponse.alwaysUseFieldBuilders) {
                getKnowledgeBasesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.knowledgeBases_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return KnowledgeBaseProto.f1815x7112bfc9;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListKnowledgeBasesResponse getDefaultInstanceForType() {
            return ListKnowledgeBasesResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListKnowledgeBasesResponse build() {
            ListKnowledgeBasesResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListKnowledgeBasesResponse buildPartial() {
            ListKnowledgeBasesResponse listKnowledgeBasesResponse = new ListKnowledgeBasesResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.knowledgeBases_ = Collections.unmodifiableList(this.knowledgeBases_);
                    this.bitField0_ &= -2;
                }
                listKnowledgeBasesResponse.knowledgeBases_ = this.knowledgeBases_;
            } else {
                listKnowledgeBasesResponse.knowledgeBases_ = repeatedFieldBuilderV3.build();
            }
            listKnowledgeBasesResponse.nextPageToken_ = this.nextPageToken_;
            onBuilt();
            return listKnowledgeBasesResponse;
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
            if (message instanceof ListKnowledgeBasesResponse) {
                return mergeFrom((ListKnowledgeBasesResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListKnowledgeBasesResponse listKnowledgeBasesResponse) {
            if (listKnowledgeBasesResponse == ListKnowledgeBasesResponse.getDefaultInstance()) {
                return this;
            }
            if (this.knowledgeBasesBuilder_ == null) {
                if (!listKnowledgeBasesResponse.knowledgeBases_.isEmpty()) {
                    if (this.knowledgeBases_.isEmpty()) {
                        this.knowledgeBases_ = listKnowledgeBasesResponse.knowledgeBases_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureKnowledgeBasesIsMutable();
                        this.knowledgeBases_.addAll(listKnowledgeBasesResponse.knowledgeBases_);
                    }
                    onChanged();
                }
            } else if (!listKnowledgeBasesResponse.knowledgeBases_.isEmpty()) {
                if (!this.knowledgeBasesBuilder_.isEmpty()) {
                    this.knowledgeBasesBuilder_.addAllMessages(listKnowledgeBasesResponse.knowledgeBases_);
                } else {
                    this.knowledgeBasesBuilder_.dispose();
                    this.knowledgeBasesBuilder_ = null;
                    this.knowledgeBases_ = listKnowledgeBasesResponse.knowledgeBases_;
                    this.bitField0_ &= -2;
                    this.knowledgeBasesBuilder_ = ListKnowledgeBasesResponse.alwaysUseFieldBuilders ? getKnowledgeBasesFieldBuilder() : null;
                }
            }
            if (!listKnowledgeBasesResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listKnowledgeBasesResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(listKnowledgeBasesResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListKnowledgeBasesResponse listKnowledgeBasesResponse = null;
            try {
                try {
                    ListKnowledgeBasesResponse listKnowledgeBasesResponse2 = (ListKnowledgeBasesResponse) ListKnowledgeBasesResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listKnowledgeBasesResponse2 != null) {
                        mergeFrom(listKnowledgeBasesResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListKnowledgeBasesResponse listKnowledgeBasesResponse3 = (ListKnowledgeBasesResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listKnowledgeBasesResponse = listKnowledgeBasesResponse3;
                        if (listKnowledgeBasesResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listKnowledgeBasesResponse != null) {
                    mergeFrom(listKnowledgeBasesResponse);
                }
                throw th;
            }
        }

        private void ensureKnowledgeBasesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.knowledgeBases_ = new ArrayList(this.knowledgeBases_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
        public List<KnowledgeBase> getKnowledgeBasesList() {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.knowledgeBases_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
        public int getKnowledgeBasesCount() {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.knowledgeBases_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
        public KnowledgeBase getKnowledgeBases(int i) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.knowledgeBases_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setKnowledgeBases(int i, KnowledgeBase knowledgeBase) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, knowledgeBase);
            } else {
                if (knowledgeBase == null) {
                    throw new NullPointerException();
                }
                ensureKnowledgeBasesIsMutable();
                this.knowledgeBases_.set(i, knowledgeBase);
                onChanged();
            }
            return this;
        }

        public Builder setKnowledgeBases(int i, KnowledgeBase.Builder builder) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKnowledgeBasesIsMutable();
                this.knowledgeBases_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addKnowledgeBases(KnowledgeBase knowledgeBase) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(knowledgeBase);
            } else {
                if (knowledgeBase == null) {
                    throw new NullPointerException();
                }
                ensureKnowledgeBasesIsMutable();
                this.knowledgeBases_.add(knowledgeBase);
                onChanged();
            }
            return this;
        }

        public Builder addKnowledgeBases(int i, KnowledgeBase knowledgeBase) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, knowledgeBase);
            } else {
                if (knowledgeBase == null) {
                    throw new NullPointerException();
                }
                ensureKnowledgeBasesIsMutable();
                this.knowledgeBases_.add(i, knowledgeBase);
                onChanged();
            }
            return this;
        }

        public Builder addKnowledgeBases(KnowledgeBase.Builder builder) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKnowledgeBasesIsMutable();
                this.knowledgeBases_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addKnowledgeBases(int i, KnowledgeBase.Builder builder) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKnowledgeBasesIsMutable();
                this.knowledgeBases_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllKnowledgeBases(Iterable<? extends KnowledgeBase> iterable) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKnowledgeBasesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.knowledgeBases_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearKnowledgeBases() {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.knowledgeBases_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeKnowledgeBases(int i) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureKnowledgeBasesIsMutable();
                this.knowledgeBases_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public KnowledgeBase.Builder getKnowledgeBasesBuilder(int i) {
            return getKnowledgeBasesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
        public KnowledgeBaseOrBuilder getKnowledgeBasesOrBuilder(int i) {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.knowledgeBases_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
        public List<? extends KnowledgeBaseOrBuilder> getKnowledgeBasesOrBuilderList() {
            RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> repeatedFieldBuilderV3 = this.knowledgeBasesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.knowledgeBases_);
        }

        public KnowledgeBase.Builder addKnowledgeBasesBuilder() {
            return getKnowledgeBasesFieldBuilder().addBuilder(KnowledgeBase.getDefaultInstance());
        }

        public KnowledgeBase.Builder addKnowledgeBasesBuilder(int i) {
            return getKnowledgeBasesFieldBuilder().addBuilder(i, KnowledgeBase.getDefaultInstance());
        }

        public List<KnowledgeBase.Builder> getKnowledgeBasesBuilderList() {
            return getKnowledgeBasesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> getKnowledgeBasesFieldBuilder() {
            if (this.knowledgeBasesBuilder_ == null) {
                this.knowledgeBasesBuilder_ = new RepeatedFieldBuilderV3<>(this.knowledgeBases_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.knowledgeBases_ = null;
            }
            return this.knowledgeBasesBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListKnowledgeBasesResponseOrBuilder
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
            this.nextPageToken_ = ListKnowledgeBasesResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListKnowledgeBasesResponse.checkByteStringIsUtf8(byteString);
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

    public static ListKnowledgeBasesResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListKnowledgeBasesResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListKnowledgeBasesResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListKnowledgeBasesResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
