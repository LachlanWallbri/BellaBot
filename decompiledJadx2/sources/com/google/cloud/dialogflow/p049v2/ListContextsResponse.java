package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.Context;
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
public final class ListContextsResponse extends GeneratedMessageV3 implements ListContextsResponseOrBuilder {
    public static final int CONTEXTS_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private List<Context> contexts_;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private static final ListContextsResponse DEFAULT_INSTANCE = new ListContextsResponse();
    private static final Parser<ListContextsResponse> PARSER = new AbstractParser<ListContextsResponse>() { // from class: com.google.cloud.dialogflow.v2.ListContextsResponse.1
        @Override // com.google.protobuf.Parser
        public ListContextsResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListContextsResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private ListContextsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListContextsResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.contexts_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListContextsResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListContextsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.contexts_ = new ArrayList();
                                z2 |= true;
                            }
                            this.contexts_.add(codedInputStream.readMessage(Context.parser(), extensionRegistryLite));
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
                    this.contexts_ = Collections.unmodifiableList(this.contexts_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ContextProto.f1409x300c83d0;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ContextProto.f1410x3e2da4e.ensureFieldAccessorsInitialized(ListContextsResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
    public List<Context> getContextsList() {
        return this.contexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
    public List<? extends ContextOrBuilder> getContextsOrBuilderList() {
        return this.contexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
    public int getContextsCount() {
        return this.contexts_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
    public Context getContexts(int i) {
        return this.contexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
    public ContextOrBuilder getContextsOrBuilder(int i) {
        return this.contexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
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
        for (int i = 0; i < this.contexts_.size(); i++) {
            codedOutputStream.writeMessage(1, this.contexts_.get(i));
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
        for (int i3 = 0; i3 < this.contexts_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.contexts_.get(i3));
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
        if (!(obj instanceof ListContextsResponse)) {
            return super.equals(obj);
        }
        ListContextsResponse listContextsResponse = (ListContextsResponse) obj;
        return getContextsList().equals(listContextsResponse.getContextsList()) && getNextPageToken().equals(listContextsResponse.getNextPageToken()) && this.unknownFields.equals(listContextsResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getContextsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getContextsList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListContextsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListContextsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListContextsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListContextsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListContextsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListContextsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListContextsResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListContextsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListContextsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListContextsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListContextsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListContextsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListContextsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListContextsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListContextsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListContextsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListContextsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListContextsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListContextsResponse listContextsResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listContextsResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListContextsResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> contextsBuilder_;
        private List<Context> contexts_;
        private Object nextPageToken_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ContextProto.f1409x300c83d0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ContextProto.f1410x3e2da4e.ensureFieldAccessorsInitialized(ListContextsResponse.class, Builder.class);
        }

        private Builder() {
            this.contexts_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.contexts_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListContextsResponse.alwaysUseFieldBuilders) {
                getContextsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.contexts_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ContextProto.f1409x300c83d0;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListContextsResponse getDefaultInstanceForType() {
            return ListContextsResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListContextsResponse build() {
            ListContextsResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListContextsResponse buildPartial() {
            ListContextsResponse listContextsResponse = new ListContextsResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.contexts_ = Collections.unmodifiableList(this.contexts_);
                    this.bitField0_ &= -2;
                }
                listContextsResponse.contexts_ = this.contexts_;
            } else {
                listContextsResponse.contexts_ = repeatedFieldBuilderV3.build();
            }
            listContextsResponse.nextPageToken_ = this.nextPageToken_;
            onBuilt();
            return listContextsResponse;
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
            if (message instanceof ListContextsResponse) {
                return mergeFrom((ListContextsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListContextsResponse listContextsResponse) {
            if (listContextsResponse == ListContextsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.contextsBuilder_ == null) {
                if (!listContextsResponse.contexts_.isEmpty()) {
                    if (this.contexts_.isEmpty()) {
                        this.contexts_ = listContextsResponse.contexts_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureContextsIsMutable();
                        this.contexts_.addAll(listContextsResponse.contexts_);
                    }
                    onChanged();
                }
            } else if (!listContextsResponse.contexts_.isEmpty()) {
                if (!this.contextsBuilder_.isEmpty()) {
                    this.contextsBuilder_.addAllMessages(listContextsResponse.contexts_);
                } else {
                    this.contextsBuilder_.dispose();
                    this.contextsBuilder_ = null;
                    this.contexts_ = listContextsResponse.contexts_;
                    this.bitField0_ &= -2;
                    this.contextsBuilder_ = ListContextsResponse.alwaysUseFieldBuilders ? getContextsFieldBuilder() : null;
                }
            }
            if (!listContextsResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listContextsResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(listContextsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListContextsResponse listContextsResponse = null;
            try {
                try {
                    ListContextsResponse listContextsResponse2 = (ListContextsResponse) ListContextsResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listContextsResponse2 != null) {
                        mergeFrom(listContextsResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListContextsResponse listContextsResponse3 = (ListContextsResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listContextsResponse = listContextsResponse3;
                        if (listContextsResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listContextsResponse != null) {
                    mergeFrom(listContextsResponse);
                }
                throw th;
            }
        }

        private void ensureContextsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.contexts_ = new ArrayList(this.contexts_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
        public List<Context> getContextsList() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.contexts_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
        public int getContextsCount() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.contexts_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
        public Context getContexts(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.contexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setContexts(int i, Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureContextsIsMutable();
                this.contexts_.set(i, context);
                onChanged();
            }
            return this;
        }

        public Builder setContexts(int i, Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureContextsIsMutable();
                this.contexts_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addContexts(Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureContextsIsMutable();
                this.contexts_.add(context);
                onChanged();
            }
            return this;
        }

        public Builder addContexts(int i, Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureContextsIsMutable();
                this.contexts_.add(i, context);
                onChanged();
            }
            return this;
        }

        public Builder addContexts(Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureContextsIsMutable();
                this.contexts_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addContexts(int i, Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureContextsIsMutable();
                this.contexts_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllContexts(Iterable<? extends Context> iterable) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureContextsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.contexts_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearContexts() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.contexts_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeContexts(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureContextsIsMutable();
                this.contexts_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Context.Builder getContextsBuilder(int i) {
            return getContextsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
        public ContextOrBuilder getContextsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.contexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
        public List<? extends ContextOrBuilder> getContextsOrBuilderList() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.contexts_);
        }

        public Context.Builder addContextsBuilder() {
            return getContextsFieldBuilder().addBuilder(Context.getDefaultInstance());
        }

        public Context.Builder addContextsBuilder(int i) {
            return getContextsFieldBuilder().addBuilder(i, Context.getDefaultInstance());
        }

        public List<Context.Builder> getContextsBuilderList() {
            return getContextsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> getContextsFieldBuilder() {
            if (this.contextsBuilder_ == null) {
                this.contextsBuilder_ = new RepeatedFieldBuilderV3<>(this.contexts_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.contexts_ = null;
            }
            return this.contextsBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.ListContextsResponseOrBuilder
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
            this.nextPageToken_ = ListContextsResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListContextsResponse.checkByteStringIsUtf8(byteString);
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

    public static ListContextsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListContextsResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListContextsResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListContextsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
