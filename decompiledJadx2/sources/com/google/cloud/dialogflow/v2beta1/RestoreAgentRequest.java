package com.google.cloud.dialogflow.v2beta1;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class RestoreAgentRequest extends GeneratedMessageV3 implements RestoreAgentRequestOrBuilder {
    public static final int AGENT_CONTENT_FIELD_NUMBER = 3;
    public static final int AGENT_URI_FIELD_NUMBER = 2;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private int agentCase_;
    private Object agent_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final RestoreAgentRequest DEFAULT_INSTANCE = new RestoreAgentRequest();
    private static final Parser<RestoreAgentRequest> PARSER = new AbstractParser<RestoreAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.RestoreAgentRequest.1
        @Override // com.google.protobuf.Parser
        public RestoreAgentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RestoreAgentRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private RestoreAgentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.agentCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private RestoreAgentRequest() {
        this.agentCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new RestoreAgentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RestoreAgentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.parent_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.agentCase_ = 2;
                                this.agent_ = readStringRequireUtf8;
                            } else if (readTag == 26) {
                                this.agentCase_ = 3;
                                this.agent_ = codedInputStream.readBytes();
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
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AgentProto.f1591x1610d338;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AgentProto.f1592xfff541b6.ensureFieldAccessorsInitialized(RestoreAgentRequest.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum AgentCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        AGENT_URI(2),
        AGENT_CONTENT(3),
        AGENT_NOT_SET(0);

        private final int value;

        AgentCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static AgentCase valueOf(int i) {
            return forNumber(i);
        }

        public static AgentCase forNumber(int i) {
            if (i == 0) {
                return AGENT_NOT_SET;
            }
            if (i == 2) {
                return AGENT_URI;
            }
            if (i != 3) {
                return null;
            }
            return AGENT_CONTENT;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
    public AgentCase getAgentCase() {
        return AgentCase.forNumber(this.agentCase_);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
    public String getAgentUri() {
        String str = this.agentCase_ == 2 ? this.agent_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.agentCase_ == 2) {
            this.agent_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
    public ByteString getAgentUriBytes() {
        String str = this.agentCase_ == 2 ? this.agent_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.agentCase_ == 2) {
                this.agent_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
    public ByteString getAgentContent() {
        if (this.agentCase_ == 3) {
            return (ByteString) this.agent_;
        }
        return ByteString.EMPTY;
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
        if (!getParentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.parent_);
        }
        if (this.agentCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.agent_);
        }
        if (this.agentCase_ == 3) {
            codedOutputStream.writeBytes(3, (ByteString) this.agent_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getParentBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.parent_);
        if (this.agentCase_ == 2) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.agent_);
        }
        if (this.agentCase_ == 3) {
            computeStringSize += CodedOutputStream.computeBytesSize(3, (ByteString) this.agent_);
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
        if (!(obj instanceof RestoreAgentRequest)) {
            return super.equals(obj);
        }
        RestoreAgentRequest restoreAgentRequest = (RestoreAgentRequest) obj;
        if (!getParent().equals(restoreAgentRequest.getParent()) || !getAgentCase().equals(restoreAgentRequest.getAgentCase())) {
            return false;
        }
        int i = this.agentCase_;
        if (i == 2) {
            if (!getAgentUri().equals(restoreAgentRequest.getAgentUri())) {
                return false;
            }
        } else if (i == 3 && !getAgentContent().equals(restoreAgentRequest.getAgentContent())) {
            return false;
        }
        return this.unknownFields.equals(restoreAgentRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        int i2 = this.agentCase_;
        if (i2 == 2) {
            i = ((hashCode2 * 37) + 2) * 53;
            hashCode = getAgentUri().hashCode();
        } else {
            if (i2 == 3) {
                i = ((hashCode2 * 37) + 3) * 53;
                hashCode = getAgentContent().hashCode();
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

    public static RestoreAgentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static RestoreAgentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RestoreAgentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static RestoreAgentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RestoreAgentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static RestoreAgentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RestoreAgentRequest parseFrom(InputStream inputStream) throws IOException {
        return (RestoreAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RestoreAgentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RestoreAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RestoreAgentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RestoreAgentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RestoreAgentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RestoreAgentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RestoreAgentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RestoreAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RestoreAgentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RestoreAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RestoreAgentRequest restoreAgentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(restoreAgentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RestoreAgentRequestOrBuilder {
        private int agentCase_;
        private Object agent_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AgentProto.f1591x1610d338;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AgentProto.f1592xfff541b6.ensureFieldAccessorsInitialized(RestoreAgentRequest.class, Builder.class);
        }

        private Builder() {
            this.agentCase_ = 0;
            this.parent_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.agentCase_ = 0;
            this.parent_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RestoreAgentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            this.agentCase_ = 0;
            this.agent_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AgentProto.f1591x1610d338;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RestoreAgentRequest getDefaultInstanceForType() {
            return RestoreAgentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RestoreAgentRequest build() {
            RestoreAgentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RestoreAgentRequest buildPartial() {
            RestoreAgentRequest restoreAgentRequest = new RestoreAgentRequest(this);
            restoreAgentRequest.parent_ = this.parent_;
            if (this.agentCase_ == 2) {
                restoreAgentRequest.agent_ = this.agent_;
            }
            if (this.agentCase_ == 3) {
                restoreAgentRequest.agent_ = this.agent_;
            }
            restoreAgentRequest.agentCase_ = this.agentCase_;
            onBuilt();
            return restoreAgentRequest;
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
            if (message instanceof RestoreAgentRequest) {
                return mergeFrom((RestoreAgentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RestoreAgentRequest restoreAgentRequest) {
            if (restoreAgentRequest == RestoreAgentRequest.getDefaultInstance()) {
                return this;
            }
            if (!restoreAgentRequest.getParent().isEmpty()) {
                this.parent_ = restoreAgentRequest.parent_;
                onChanged();
            }
            int i = C25322.f1821xd3b72d33[restoreAgentRequest.getAgentCase().ordinal()];
            if (i == 1) {
                this.agentCase_ = 2;
                this.agent_ = restoreAgentRequest.agent_;
                onChanged();
            } else if (i == 2) {
                setAgentContent(restoreAgentRequest.getAgentContent());
            }
            mergeUnknownFields(restoreAgentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            RestoreAgentRequest restoreAgentRequest = null;
            try {
                try {
                    RestoreAgentRequest restoreAgentRequest2 = (RestoreAgentRequest) RestoreAgentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (restoreAgentRequest2 != null) {
                        mergeFrom(restoreAgentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    RestoreAgentRequest restoreAgentRequest3 = (RestoreAgentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        restoreAgentRequest = restoreAgentRequest3;
                        if (restoreAgentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (restoreAgentRequest != null) {
                    mergeFrom(restoreAgentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
        public AgentCase getAgentCase() {
            return AgentCase.forNumber(this.agentCase_);
        }

        public Builder clearAgent() {
            this.agentCase_ = 0;
            this.agent_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
        public ByteString getParentBytes() {
            Object obj = this.parent_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.parent_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setParent(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.parent_ = str;
            onChanged();
            return this;
        }

        public Builder clearParent() {
            this.parent_ = RestoreAgentRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                RestoreAgentRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
        public String getAgentUri() {
            String str = this.agentCase_ == 2 ? this.agent_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.agentCase_ == 2) {
                    this.agent_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
        public ByteString getAgentUriBytes() {
            String str = this.agentCase_ == 2 ? this.agent_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.agentCase_ == 2) {
                    this.agent_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setAgentUri(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.agentCase_ = 2;
            this.agent_ = str;
            onChanged();
            return this;
        }

        public Builder clearAgentUri() {
            if (this.agentCase_ == 2) {
                this.agentCase_ = 0;
                this.agent_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setAgentUriBytes(ByteString byteString) {
            if (byteString != null) {
                RestoreAgentRequest.checkByteStringIsUtf8(byteString);
                this.agentCase_ = 2;
                this.agent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.RestoreAgentRequestOrBuilder
        public ByteString getAgentContent() {
            if (this.agentCase_ == 3) {
                return (ByteString) this.agent_;
            }
            return ByteString.EMPTY;
        }

        public Builder setAgentContent(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.agentCase_ = 3;
            this.agent_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAgentContent() {
            if (this.agentCase_ == 3) {
                this.agentCase_ = 0;
                this.agent_ = null;
                onChanged();
            }
            return this;
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
    /* renamed from: com.google.cloud.dialogflow.v2beta1.RestoreAgentRequest$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C25322 {

        /* renamed from: $SwitchMap$com$google$cloud$dialogflow$v2beta1$RestoreAgentRequest$AgentCase */
        static final /* synthetic */ int[] f1821xd3b72d33 = new int[AgentCase.values().length];

        static {
            try {
                f1821xd3b72d33[AgentCase.AGENT_URI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1821xd3b72d33[AgentCase.AGENT_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1821xd3b72d33[AgentCase.AGENT_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static RestoreAgentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RestoreAgentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<RestoreAgentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public RestoreAgentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
