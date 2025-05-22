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
public final class ImportAgentRequest extends GeneratedMessageV3 implements ImportAgentRequestOrBuilder {
    public static final int AGENT_CONTENT_FIELD_NUMBER = 3;
    public static final int AGENT_URI_FIELD_NUMBER = 2;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private int agentCase_;
    private Object agent_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final ImportAgentRequest DEFAULT_INSTANCE = new ImportAgentRequest();
    private static final Parser<ImportAgentRequest> PARSER = new AbstractParser<ImportAgentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.ImportAgentRequest.1
        @Override // com.google.protobuf.Parser
        public ImportAgentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ImportAgentRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private ImportAgentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.agentCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ImportAgentRequest() {
        this.agentCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ImportAgentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ImportAgentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return AgentProto.f1589xf7b8eebd;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AgentProto.f1590xf8e0083b.ensureFieldAccessorsInitialized(ImportAgentRequest.class, Builder.class);
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

    @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
    public AgentCase getAgentCase() {
        return AgentCase.forNumber(this.agentCase_);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
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

    @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
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

    @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
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
        if (!(obj instanceof ImportAgentRequest)) {
            return super.equals(obj);
        }
        ImportAgentRequest importAgentRequest = (ImportAgentRequest) obj;
        if (!getParent().equals(importAgentRequest.getParent()) || !getAgentCase().equals(importAgentRequest.getAgentCase())) {
            return false;
        }
        int i = this.agentCase_;
        if (i == 2) {
            if (!getAgentUri().equals(importAgentRequest.getAgentUri())) {
                return false;
            }
        } else if (i == 3 && !getAgentContent().equals(importAgentRequest.getAgentContent())) {
            return false;
        }
        return this.unknownFields.equals(importAgentRequest.unknownFields);
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

    public static ImportAgentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ImportAgentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ImportAgentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ImportAgentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ImportAgentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ImportAgentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ImportAgentRequest parseFrom(InputStream inputStream) throws IOException {
        return (ImportAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ImportAgentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ImportAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ImportAgentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ImportAgentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ImportAgentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ImportAgentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ImportAgentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ImportAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ImportAgentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ImportAgentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ImportAgentRequest importAgentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(importAgentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ImportAgentRequestOrBuilder {
        private int agentCase_;
        private Object agent_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AgentProto.f1589xf7b8eebd;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AgentProto.f1590xf8e0083b.ensureFieldAccessorsInitialized(ImportAgentRequest.class, Builder.class);
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
            boolean unused = ImportAgentRequest.alwaysUseFieldBuilders;
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
            return AgentProto.f1589xf7b8eebd;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ImportAgentRequest getDefaultInstanceForType() {
            return ImportAgentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ImportAgentRequest build() {
            ImportAgentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ImportAgentRequest buildPartial() {
            ImportAgentRequest importAgentRequest = new ImportAgentRequest(this);
            importAgentRequest.parent_ = this.parent_;
            if (this.agentCase_ == 2) {
                importAgentRequest.agent_ = this.agent_;
            }
            if (this.agentCase_ == 3) {
                importAgentRequest.agent_ = this.agent_;
            }
            importAgentRequest.agentCase_ = this.agentCase_;
            onBuilt();
            return importAgentRequest;
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
            if (message instanceof ImportAgentRequest) {
                return mergeFrom((ImportAgentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ImportAgentRequest importAgentRequest) {
            if (importAgentRequest == ImportAgentRequest.getDefaultInstance()) {
                return this;
            }
            if (!importAgentRequest.getParent().isEmpty()) {
                this.parent_ = importAgentRequest.parent_;
                onChanged();
            }
            int i = C24322.f1685xf9052276[importAgentRequest.getAgentCase().ordinal()];
            if (i == 1) {
                this.agentCase_ = 2;
                this.agent_ = importAgentRequest.agent_;
                onChanged();
            } else if (i == 2) {
                setAgentContent(importAgentRequest.getAgentContent());
            }
            mergeUnknownFields(importAgentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ImportAgentRequest importAgentRequest = null;
            try {
                try {
                    ImportAgentRequest importAgentRequest2 = (ImportAgentRequest) ImportAgentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (importAgentRequest2 != null) {
                        mergeFrom(importAgentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ImportAgentRequest importAgentRequest3 = (ImportAgentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        importAgentRequest = importAgentRequest3;
                        if (importAgentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (importAgentRequest != null) {
                    mergeFrom(importAgentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
        public AgentCase getAgentCase() {
            return AgentCase.forNumber(this.agentCase_);
        }

        public Builder clearAgent() {
            this.agentCase_ = 0;
            this.agent_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
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
            this.parent_ = ImportAgentRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                ImportAgentRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
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
                ImportAgentRequest.checkByteStringIsUtf8(byteString);
                this.agentCase_ = 2;
                this.agent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ImportAgentRequestOrBuilder
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
    /* renamed from: com.google.cloud.dialogflow.v2beta1.ImportAgentRequest$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C24322 {

        /* renamed from: $SwitchMap$com$google$cloud$dialogflow$v2beta1$ImportAgentRequest$AgentCase */
        static final /* synthetic */ int[] f1685xf9052276 = new int[AgentCase.values().length];

        static {
            try {
                f1685xf9052276[AgentCase.AGENT_URI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1685xf9052276[AgentCase.AGENT_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1685xf9052276[AgentCase.AGENT_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static ImportAgentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ImportAgentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ImportAgentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ImportAgentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
