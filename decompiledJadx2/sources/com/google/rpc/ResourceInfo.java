package com.google.rpc;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class ResourceInfo extends GeneratedMessageV3 implements ResourceInfoOrBuilder {
    public static final int DESCRIPTION_FIELD_NUMBER = 4;
    public static final int OWNER_FIELD_NUMBER = 3;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 2;
    public static final int RESOURCE_TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private volatile Object description_;
    private byte memoizedIsInitialized;
    private volatile Object owner_;
    private volatile Object resourceName_;
    private volatile Object resourceType_;
    private static final ResourceInfo DEFAULT_INSTANCE = new ResourceInfo();
    private static final Parser<ResourceInfo> PARSER = new AbstractParser<ResourceInfo>() { // from class: com.google.rpc.ResourceInfo.1
        @Override // com.google.protobuf.Parser
        public ResourceInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ResourceInfo(codedInputStream, extensionRegistryLite);
        }
    };

    private ResourceInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ResourceInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.resourceType_ = "";
        this.resourceName_ = "";
        this.owner_ = "";
        this.description_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ResourceInfo();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ResourceInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.resourceType_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            this.resourceName_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 26) {
                            this.owner_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 34) {
                            this.description_ = codedInputStream.readStringRequireUtf8();
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
        return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceInfo.class, Builder.class);
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getResourceType() {
        Object obj = this.resourceType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getResourceTypeBytes() {
        Object obj = this.resourceType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getResourceName() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getResourceNameBytes() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getOwner() {
        Object obj = this.owner_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.owner_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getOwnerBytes() {
        Object obj = this.owner_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.owner_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
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
        if (!getResourceTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.resourceType_);
        }
        if (!getResourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.resourceName_);
        }
        if (!getOwnerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.owner_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.description_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getResourceTypeBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.resourceType_);
        if (!getResourceNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.resourceName_);
        }
        if (!getOwnerBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.owner_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.description_);
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
        if (!(obj instanceof ResourceInfo)) {
            return super.equals(obj);
        }
        ResourceInfo resourceInfo = (ResourceInfo) obj;
        return getResourceType().equals(resourceInfo.getResourceType()) && getResourceName().equals(resourceInfo.getResourceName()) && getOwner().equals(resourceInfo.getOwner()) && getDescription().equals(resourceInfo.getDescription()) && this.unknownFields.equals(resourceInfo.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getResourceType().hashCode()) * 37) + 2) * 53) + getResourceName().hashCode()) * 37) + 3) * 53) + getOwner().hashCode()) * 37) + 4) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static ResourceInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ResourceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ResourceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ResourceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(InputStream inputStream) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ResourceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ResourceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ResourceInfo resourceInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(resourceInfo);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResourceInfoOrBuilder {
        private Object description_;
        private Object owner_;
        private Object resourceName_;
        private Object resourceType_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceInfo.class, Builder.class);
        }

        private Builder() {
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ResourceInfo.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ResourceInfo getDefaultInstanceForType() {
            return ResourceInfo.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ResourceInfo build() {
            ResourceInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ResourceInfo buildPartial() {
            ResourceInfo resourceInfo = new ResourceInfo(this);
            resourceInfo.resourceType_ = this.resourceType_;
            resourceInfo.resourceName_ = this.resourceName_;
            resourceInfo.owner_ = this.owner_;
            resourceInfo.description_ = this.description_;
            onBuilt();
            return resourceInfo;
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
            if (message instanceof ResourceInfo) {
                return mergeFrom((ResourceInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ResourceInfo resourceInfo) {
            if (resourceInfo == ResourceInfo.getDefaultInstance()) {
                return this;
            }
            if (!resourceInfo.getResourceType().isEmpty()) {
                this.resourceType_ = resourceInfo.resourceType_;
                onChanged();
            }
            if (!resourceInfo.getResourceName().isEmpty()) {
                this.resourceName_ = resourceInfo.resourceName_;
                onChanged();
            }
            if (!resourceInfo.getOwner().isEmpty()) {
                this.owner_ = resourceInfo.owner_;
                onChanged();
            }
            if (!resourceInfo.getDescription().isEmpty()) {
                this.description_ = resourceInfo.description_;
                onChanged();
            }
            mergeUnknownFields(resourceInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ResourceInfo resourceInfo = null;
            try {
                try {
                    ResourceInfo resourceInfo2 = (ResourceInfo) ResourceInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (resourceInfo2 != null) {
                        mergeFrom(resourceInfo2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ResourceInfo resourceInfo3 = (ResourceInfo) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        resourceInfo = resourceInfo3;
                        if (resourceInfo != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (resourceInfo != null) {
                    mergeFrom(resourceInfo);
                }
                throw th;
            }
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getResourceType() {
            Object obj = this.resourceType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resourceType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getResourceTypeBytes() {
            Object obj = this.resourceType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.resourceType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResourceType(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.resourceType_ = str;
            onChanged();
            return this;
        }

        public Builder clearResourceType() {
            this.resourceType_ = ResourceInfo.getDefaultInstance().getResourceType();
            onChanged();
            return this;
        }

        public Builder setResourceTypeBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceInfo.checkByteStringIsUtf8(byteString);
                this.resourceType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getResourceName() {
            Object obj = this.resourceName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resourceName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getResourceNameBytes() {
            Object obj = this.resourceName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.resourceName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResourceName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.resourceName_ = str;
            onChanged();
            return this;
        }

        public Builder clearResourceName() {
            this.resourceName_ = ResourceInfo.getDefaultInstance().getResourceName();
            onChanged();
            return this;
        }

        public Builder setResourceNameBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceInfo.checkByteStringIsUtf8(byteString);
                this.resourceName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getOwner() {
            Object obj = this.owner_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.owner_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getOwnerBytes() {
            Object obj = this.owner_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.owner_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setOwner(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.owner_ = str;
            onChanged();
            return this;
        }

        public Builder clearOwner() {
            this.owner_ = ResourceInfo.getDefaultInstance().getOwner();
            onChanged();
            return this;
        }

        public Builder setOwnerBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceInfo.checkByteStringIsUtf8(byteString);
                this.owner_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDescription(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.description_ = str;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = ResourceInfo.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceInfo.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
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

    public static ResourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourceInfo> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ResourceInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ResourceInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
