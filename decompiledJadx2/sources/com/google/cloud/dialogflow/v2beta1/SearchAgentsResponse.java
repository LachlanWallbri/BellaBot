package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Agent;
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
public final class SearchAgentsResponse extends GeneratedMessageV3 implements SearchAgentsResponseOrBuilder {
    public static final int AGENTS_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private List<Agent> agents_;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private static final SearchAgentsResponse DEFAULT_INSTANCE = new SearchAgentsResponse();
    private static final Parser<SearchAgentsResponse> PARSER = new AbstractParser<SearchAgentsResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.SearchAgentsResponse.1
        @Override // com.google.protobuf.Parser
        public SearchAgentsResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SearchAgentsResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private SearchAgentsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SearchAgentsResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.agents_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SearchAgentsResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SearchAgentsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.agents_ = new ArrayList();
                                z2 |= true;
                            }
                            this.agents_.add(codedInputStream.readMessage(Agent.parser(), extensionRegistryLite));
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
                    this.agents_ = Collections.unmodifiableList(this.agents_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AgentProto.f1595x9e88e215;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AgentProto.f1596x4d932393.ensureFieldAccessorsInitialized(SearchAgentsResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
    public List<Agent> getAgentsList() {
        return this.agents_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
    public List<? extends AgentOrBuilder> getAgentsOrBuilderList() {
        return this.agents_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
    public int getAgentsCount() {
        return this.agents_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
    public Agent getAgents(int i) {
        return this.agents_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
    public AgentOrBuilder getAgentsOrBuilder(int i) {
        return this.agents_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
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
        for (int i = 0; i < this.agents_.size(); i++) {
            codedOutputStream.writeMessage(1, this.agents_.get(i));
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
        for (int i3 = 0; i3 < this.agents_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.agents_.get(i3));
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
        if (!(obj instanceof SearchAgentsResponse)) {
            return super.equals(obj);
        }
        SearchAgentsResponse searchAgentsResponse = (SearchAgentsResponse) obj;
        return getAgentsList().equals(searchAgentsResponse.getAgentsList()) && getNextPageToken().equals(searchAgentsResponse.getNextPageToken()) && this.unknownFields.equals(searchAgentsResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getAgentsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getAgentsList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SearchAgentsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SearchAgentsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SearchAgentsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SearchAgentsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SearchAgentsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SearchAgentsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SearchAgentsResponse parseFrom(InputStream inputStream) throws IOException {
        return (SearchAgentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SearchAgentsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SearchAgentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SearchAgentsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SearchAgentsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SearchAgentsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SearchAgentsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SearchAgentsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SearchAgentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SearchAgentsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SearchAgentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SearchAgentsResponse searchAgentsResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(searchAgentsResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SearchAgentsResponseOrBuilder {
        private RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> agentsBuilder_;
        private List<Agent> agents_;
        private int bitField0_;
        private Object nextPageToken_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AgentProto.f1595x9e88e215;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AgentProto.f1596x4d932393.ensureFieldAccessorsInitialized(SearchAgentsResponse.class, Builder.class);
        }

        private Builder() {
            this.agents_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.agents_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SearchAgentsResponse.alwaysUseFieldBuilders) {
                getAgentsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.agents_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AgentProto.f1595x9e88e215;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SearchAgentsResponse getDefaultInstanceForType() {
            return SearchAgentsResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SearchAgentsResponse build() {
            SearchAgentsResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SearchAgentsResponse buildPartial() {
            SearchAgentsResponse searchAgentsResponse = new SearchAgentsResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.agents_ = Collections.unmodifiableList(this.agents_);
                    this.bitField0_ &= -2;
                }
                searchAgentsResponse.agents_ = this.agents_;
            } else {
                searchAgentsResponse.agents_ = repeatedFieldBuilderV3.build();
            }
            searchAgentsResponse.nextPageToken_ = this.nextPageToken_;
            onBuilt();
            return searchAgentsResponse;
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
            if (message instanceof SearchAgentsResponse) {
                return mergeFrom((SearchAgentsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SearchAgentsResponse searchAgentsResponse) {
            if (searchAgentsResponse == SearchAgentsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.agentsBuilder_ == null) {
                if (!searchAgentsResponse.agents_.isEmpty()) {
                    if (this.agents_.isEmpty()) {
                        this.agents_ = searchAgentsResponse.agents_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureAgentsIsMutable();
                        this.agents_.addAll(searchAgentsResponse.agents_);
                    }
                    onChanged();
                }
            } else if (!searchAgentsResponse.agents_.isEmpty()) {
                if (!this.agentsBuilder_.isEmpty()) {
                    this.agentsBuilder_.addAllMessages(searchAgentsResponse.agents_);
                } else {
                    this.agentsBuilder_.dispose();
                    this.agentsBuilder_ = null;
                    this.agents_ = searchAgentsResponse.agents_;
                    this.bitField0_ &= -2;
                    this.agentsBuilder_ = SearchAgentsResponse.alwaysUseFieldBuilders ? getAgentsFieldBuilder() : null;
                }
            }
            if (!searchAgentsResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = searchAgentsResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(searchAgentsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SearchAgentsResponse searchAgentsResponse = null;
            try {
                try {
                    SearchAgentsResponse searchAgentsResponse2 = (SearchAgentsResponse) SearchAgentsResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (searchAgentsResponse2 != null) {
                        mergeFrom(searchAgentsResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SearchAgentsResponse searchAgentsResponse3 = (SearchAgentsResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        searchAgentsResponse = searchAgentsResponse3;
                        if (searchAgentsResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (searchAgentsResponse != null) {
                    mergeFrom(searchAgentsResponse);
                }
                throw th;
            }
        }

        private void ensureAgentsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.agents_ = new ArrayList(this.agents_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
        public List<Agent> getAgentsList() {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.agents_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
        public int getAgentsCount() {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.agents_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
        public Agent getAgents(int i) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.agents_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAgents(int i, Agent agent) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, agent);
            } else {
                if (agent == null) {
                    throw new NullPointerException();
                }
                ensureAgentsIsMutable();
                this.agents_.set(i, agent);
                onChanged();
            }
            return this;
        }

        public Builder setAgents(int i, Agent.Builder builder) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAgentsIsMutable();
                this.agents_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAgents(Agent agent) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(agent);
            } else {
                if (agent == null) {
                    throw new NullPointerException();
                }
                ensureAgentsIsMutable();
                this.agents_.add(agent);
                onChanged();
            }
            return this;
        }

        public Builder addAgents(int i, Agent agent) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, agent);
            } else {
                if (agent == null) {
                    throw new NullPointerException();
                }
                ensureAgentsIsMutable();
                this.agents_.add(i, agent);
                onChanged();
            }
            return this;
        }

        public Builder addAgents(Agent.Builder builder) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAgentsIsMutable();
                this.agents_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAgents(int i, Agent.Builder builder) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAgentsIsMutable();
                this.agents_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAgents(Iterable<? extends Agent> iterable) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAgentsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.agents_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAgents() {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.agents_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAgents(int i) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAgentsIsMutable();
                this.agents_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Agent.Builder getAgentsBuilder(int i) {
            return getAgentsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
        public AgentOrBuilder getAgentsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.agents_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
        public List<? extends AgentOrBuilder> getAgentsOrBuilderList() {
            RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> repeatedFieldBuilderV3 = this.agentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.agents_);
        }

        public Agent.Builder addAgentsBuilder() {
            return getAgentsFieldBuilder().addBuilder(Agent.getDefaultInstance());
        }

        public Agent.Builder addAgentsBuilder(int i) {
            return getAgentsFieldBuilder().addBuilder(i, Agent.getDefaultInstance());
        }

        public List<Agent.Builder> getAgentsBuilderList() {
            return getAgentsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> getAgentsFieldBuilder() {
            if (this.agentsBuilder_ == null) {
                this.agentsBuilder_ = new RepeatedFieldBuilderV3<>(this.agents_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.agents_ = null;
            }
            return this.agentsBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SearchAgentsResponseOrBuilder
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
            this.nextPageToken_ = SearchAgentsResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                SearchAgentsResponse.checkByteStringIsUtf8(byteString);
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

    public static SearchAgentsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SearchAgentsResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SearchAgentsResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SearchAgentsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
