package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Agent;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class SetAgentRequest extends GeneratedMessageV3 implements SetAgentRequestOrBuilder {
    public static final int AGENT_FIELD_NUMBER = 1;
    private static final SetAgentRequest DEFAULT_INSTANCE = new SetAgentRequest();
    private static final Parser<SetAgentRequest> PARSER = new AbstractParser<SetAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.SetAgentRequest.1
        @Override // com.google.protobuf.Parser
        public SetAgentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SetAgentRequest(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int UPDATE_MASK_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private Agent agent_;
    private byte memoizedIsInitialized;
    private FieldMask updateMask_;

    private SetAgentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SetAgentRequest() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SetAgentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SetAgentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            Agent.Builder builder = this.agent_ != null ? this.agent_.toBuilder() : null;
                            this.agent_ = (Agent) codedInputStream.readMessage(Agent.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.agent_);
                                this.agent_ = builder.buildPartial();
                            }
                        } else if (readTag == 18) {
                            FieldMask.Builder builder2 = this.updateMask_ != null ? this.updateMask_.toBuilder() : null;
                            this.updateMask_ = (FieldMask) codedInputStream.readMessage(FieldMask.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.updateMask_);
                                this.updateMask_ = builder2.buildPartial();
                            }
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
        return AgentProto.f1597x86b2bbe4;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AgentProto.f1598xe229be62.ensureFieldAccessorsInitialized(SetAgentRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
    public boolean hasAgent() {
        return this.agent_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
    public Agent getAgent() {
        Agent agent = this.agent_;
        return agent == null ? Agent.getDefaultInstance() : agent;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
    public AgentOrBuilder getAgentOrBuilder() {
        return getAgent();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
    public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
        return getUpdateMask();
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
        if (this.agent_ != null) {
            codedOutputStream.writeMessage(1, getAgent());
        }
        if (this.updateMask_ != null) {
            codedOutputStream.writeMessage(2, getUpdateMask());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.agent_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getAgent()) : 0;
        if (this.updateMask_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, getUpdateMask());
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
        if (!(obj instanceof SetAgentRequest)) {
            return super.equals(obj);
        }
        SetAgentRequest setAgentRequest = (SetAgentRequest) obj;
        if (hasAgent() != setAgentRequest.hasAgent()) {
            return false;
        }
        if ((!hasAgent() || getAgent().equals(setAgentRequest.getAgent())) && hasUpdateMask() == setAgentRequest.hasUpdateMask()) {
            return (!hasUpdateMask() || getUpdateMask().equals(setAgentRequest.getUpdateMask())) && this.unknownFields.equals(setAgentRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasAgent()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getAgent().hashCode();
        }
        if (hasUpdateMask()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getUpdateMask().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SetAgentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SetAgentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SetAgentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SetAgentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SetAgentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SetAgentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SetAgentRequest parseFrom(InputStream inputStream) throws IOException {
        return (SetAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SetAgentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SetAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SetAgentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SetAgentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SetAgentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SetAgentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SetAgentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SetAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SetAgentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SetAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SetAgentRequest setAgentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(setAgentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SetAgentRequestOrBuilder {
        private SingleFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> agentBuilder_;
        private Agent agent_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AgentProto.f1597x86b2bbe4;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AgentProto.f1598xe229be62.ensureFieldAccessorsInitialized(SetAgentRequest.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SetAgentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.agentBuilder_ == null) {
                this.agent_ = null;
            } else {
                this.agent_ = null;
                this.agentBuilder_ = null;
            }
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AgentProto.f1597x86b2bbe4;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SetAgentRequest getDefaultInstanceForType() {
            return SetAgentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SetAgentRequest build() {
            SetAgentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SetAgentRequest buildPartial() {
            SetAgentRequest setAgentRequest = new SetAgentRequest(this);
            SingleFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> singleFieldBuilderV3 = this.agentBuilder_;
            if (singleFieldBuilderV3 == null) {
                setAgentRequest.agent_ = this.agent_;
            } else {
                setAgentRequest.agent_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV32 = this.updateMaskBuilder_;
            if (singleFieldBuilderV32 == null) {
                setAgentRequest.updateMask_ = this.updateMask_;
            } else {
                setAgentRequest.updateMask_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return setAgentRequest;
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
            if (message instanceof SetAgentRequest) {
                return mergeFrom((SetAgentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SetAgentRequest setAgentRequest) {
            if (setAgentRequest == SetAgentRequest.getDefaultInstance()) {
                return this;
            }
            if (setAgentRequest.hasAgent()) {
                mergeAgent(setAgentRequest.getAgent());
            }
            if (setAgentRequest.hasUpdateMask()) {
                mergeUpdateMask(setAgentRequest.getUpdateMask());
            }
            mergeUnknownFields(setAgentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SetAgentRequest setAgentRequest = null;
            try {
                try {
                    SetAgentRequest setAgentRequest2 = (SetAgentRequest) SetAgentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (setAgentRequest2 != null) {
                        mergeFrom(setAgentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SetAgentRequest setAgentRequest3 = (SetAgentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        setAgentRequest = setAgentRequest3;
                        if (setAgentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (setAgentRequest != null) {
                    mergeFrom(setAgentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
        public boolean hasAgent() {
            return (this.agentBuilder_ == null && this.agent_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
        public Agent getAgent() {
            SingleFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> singleFieldBuilderV3 = this.agentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Agent agent = this.agent_;
                return agent == null ? Agent.getDefaultInstance() : agent;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setAgent(Agent agent) {
            SingleFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> singleFieldBuilderV3 = this.agentBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(agent);
            } else {
                if (agent == null) {
                    throw new NullPointerException();
                }
                this.agent_ = agent;
                onChanged();
            }
            return this;
        }

        public Builder setAgent(Agent.Builder builder) {
            SingleFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> singleFieldBuilderV3 = this.agentBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.agent_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAgent(Agent agent) {
            SingleFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> singleFieldBuilderV3 = this.agentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Agent agent2 = this.agent_;
                if (agent2 != null) {
                    this.agent_ = Agent.newBuilder(agent2).mergeFrom(agent).buildPartial();
                } else {
                    this.agent_ = agent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(agent);
            }
            return this;
        }

        public Builder clearAgent() {
            if (this.agentBuilder_ == null) {
                this.agent_ = null;
                onChanged();
            } else {
                this.agent_ = null;
                this.agentBuilder_ = null;
            }
            return this;
        }

        public Agent.Builder getAgentBuilder() {
            onChanged();
            return getAgentFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
        public AgentOrBuilder getAgentOrBuilder() {
            SingleFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> singleFieldBuilderV3 = this.agentBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Agent agent = this.agent_;
            return agent == null ? Agent.getDefaultInstance() : agent;
        }

        private SingleFieldBuilderV3<Agent, Agent.Builder, AgentOrBuilder> getAgentFieldBuilder() {
            if (this.agentBuilder_ == null) {
                this.agentBuilder_ = new SingleFieldBuilderV3<>(getAgent(), getParentForChildren(), isClean());
                this.agent_ = null;
            }
            return this.agentBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
        public FieldMask getUpdateMask() {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                FieldMask fieldMask = this.updateMask_;
                return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setUpdateMask(FieldMask fieldMask) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(fieldMask);
            } else {
                if (fieldMask == null) {
                    throw new NullPointerException();
                }
                this.updateMask_ = fieldMask;
                onChanged();
            }
            return this;
        }

        public Builder setUpdateMask(FieldMask.Builder builder) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.updateMask_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUpdateMask(FieldMask fieldMask) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                FieldMask fieldMask2 = this.updateMask_;
                if (fieldMask2 != null) {
                    this.updateMask_ = FieldMask.newBuilder(fieldMask2).mergeFrom(fieldMask).buildPartial();
                } else {
                    this.updateMask_ = fieldMask;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fieldMask);
            }
            return this;
        }

        public Builder clearUpdateMask() {
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
                onChanged();
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            return this;
        }

        public FieldMask.Builder getUpdateMaskBuilder() {
            onChanged();
            return getUpdateMaskFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SetAgentRequestOrBuilder
        public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            FieldMask fieldMask = this.updateMask_;
            return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
        }

        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> getUpdateMaskFieldBuilder() {
            if (this.updateMaskBuilder_ == null) {
                this.updateMaskBuilder_ = new SingleFieldBuilderV3<>(getUpdateMask(), getParentForChildren(), isClean());
                this.updateMask_ = null;
            }
            return this.updateMaskBuilder_;
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

    public static SetAgentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SetAgentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SetAgentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SetAgentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
