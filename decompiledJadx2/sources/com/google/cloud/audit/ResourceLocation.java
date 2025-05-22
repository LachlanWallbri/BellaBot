package com.google.cloud.audit;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class ResourceLocation extends GeneratedMessageV3 implements ResourceLocationOrBuilder {
    public static final int CURRENT_LOCATIONS_FIELD_NUMBER = 1;
    public static final int ORIGINAL_LOCATIONS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private LazyStringList currentLocations_;
    private byte memoizedIsInitialized;
    private LazyStringList originalLocations_;
    private static final ResourceLocation DEFAULT_INSTANCE = new ResourceLocation();
    private static final Parser<ResourceLocation> PARSER = new AbstractParser<ResourceLocation>() { // from class: com.google.cloud.audit.ResourceLocation.1
        @Override // com.google.protobuf.Parser
        public ResourceLocation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ResourceLocation(codedInputStream, extensionRegistryLite);
        }
    };

    private ResourceLocation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ResourceLocation() {
        this.memoizedIsInitialized = (byte) -1;
        this.currentLocations_ = LazyStringArrayList.EMPTY;
        this.originalLocations_ = LazyStringArrayList.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ResourceLocation();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ResourceLocation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if ((i & 1) == 0) {
                                    this.currentLocations_ = new LazyStringArrayList();
                                    i |= 1;
                                }
                                this.currentLocations_.add(readStringRequireUtf8);
                            } else if (readTag == 18) {
                                String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                if ((i & 2) == 0) {
                                    this.originalLocations_ = new LazyStringArrayList();
                                    i |= 2;
                                }
                                this.originalLocations_.add(readStringRequireUtf82);
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
                if ((i & 1) != 0) {
                    this.currentLocations_ = this.currentLocations_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.originalLocations_ = this.originalLocations_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuditLogProto.internal_static_google_cloud_audit_ResourceLocation_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.f1353x8f9abfa4.ensureFieldAccessorsInitialized(ResourceLocation.class, Builder.class);
    }

    @Override // com.google.cloud.audit.ResourceLocationOrBuilder
    public ProtocolStringList getCurrentLocationsList() {
        return this.currentLocations_;
    }

    @Override // com.google.cloud.audit.ResourceLocationOrBuilder
    public int getCurrentLocationsCount() {
        return this.currentLocations_.size();
    }

    @Override // com.google.cloud.audit.ResourceLocationOrBuilder
    public String getCurrentLocations(int i) {
        return (String) this.currentLocations_.get(i);
    }

    @Override // com.google.cloud.audit.ResourceLocationOrBuilder
    public ByteString getCurrentLocationsBytes(int i) {
        return this.currentLocations_.getByteString(i);
    }

    @Override // com.google.cloud.audit.ResourceLocationOrBuilder
    public ProtocolStringList getOriginalLocationsList() {
        return this.originalLocations_;
    }

    @Override // com.google.cloud.audit.ResourceLocationOrBuilder
    public int getOriginalLocationsCount() {
        return this.originalLocations_.size();
    }

    @Override // com.google.cloud.audit.ResourceLocationOrBuilder
    public String getOriginalLocations(int i) {
        return (String) this.originalLocations_.get(i);
    }

    @Override // com.google.cloud.audit.ResourceLocationOrBuilder
    public ByteString getOriginalLocationsBytes(int i) {
        return this.originalLocations_.getByteString(i);
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
        for (int i = 0; i < this.currentLocations_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.currentLocations_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.originalLocations_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.originalLocations_.getRaw(i2));
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
        for (int i3 = 0; i3 < this.currentLocations_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.currentLocations_.getRaw(i3));
        }
        int size = i2 + 0 + (getCurrentLocationsList().size() * 1);
        int i4 = 0;
        for (int i5 = 0; i5 < this.originalLocations_.size(); i5++) {
            i4 += computeStringSizeNoTag(this.originalLocations_.getRaw(i5));
        }
        int size2 = size + i4 + (getOriginalLocationsList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSize = size2;
        return size2;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResourceLocation)) {
            return super.equals(obj);
        }
        ResourceLocation resourceLocation = (ResourceLocation) obj;
        return getCurrentLocationsList().equals(resourceLocation.getCurrentLocationsList()) && getOriginalLocationsList().equals(resourceLocation.getOriginalLocationsList()) && this.unknownFields.equals(resourceLocation.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getCurrentLocationsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getCurrentLocationsList().hashCode();
        }
        if (getOriginalLocationsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getOriginalLocationsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ResourceLocation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ResourceLocation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ResourceLocation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ResourceLocation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ResourceLocation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ResourceLocation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ResourceLocation parseFrom(InputStream inputStream) throws IOException {
        return (ResourceLocation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ResourceLocation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceLocation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceLocation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ResourceLocation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ResourceLocation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceLocation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceLocation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ResourceLocation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ResourceLocation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceLocation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ResourceLocation resourceLocation) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(resourceLocation);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResourceLocationOrBuilder {
        private int bitField0_;
        private LazyStringList currentLocations_;
        private LazyStringList originalLocations_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_ResourceLocation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.f1353x8f9abfa4.ensureFieldAccessorsInitialized(ResourceLocation.class, Builder.class);
        }

        private Builder() {
            this.currentLocations_ = LazyStringArrayList.EMPTY;
            this.originalLocations_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.currentLocations_ = LazyStringArrayList.EMPTY;
            this.originalLocations_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ResourceLocation.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.currentLocations_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.originalLocations_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_ResourceLocation_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ResourceLocation getDefaultInstanceForType() {
            return ResourceLocation.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ResourceLocation build() {
            ResourceLocation buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ResourceLocation buildPartial() {
            ResourceLocation resourceLocation = new ResourceLocation(this);
            if ((this.bitField0_ & 1) != 0) {
                this.currentLocations_ = this.currentLocations_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            resourceLocation.currentLocations_ = this.currentLocations_;
            if ((this.bitField0_ & 2) != 0) {
                this.originalLocations_ = this.originalLocations_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            resourceLocation.originalLocations_ = this.originalLocations_;
            onBuilt();
            return resourceLocation;
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
            if (message instanceof ResourceLocation) {
                return mergeFrom((ResourceLocation) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ResourceLocation resourceLocation) {
            if (resourceLocation == ResourceLocation.getDefaultInstance()) {
                return this;
            }
            if (!resourceLocation.currentLocations_.isEmpty()) {
                if (this.currentLocations_.isEmpty()) {
                    this.currentLocations_ = resourceLocation.currentLocations_;
                    this.bitField0_ &= -2;
                } else {
                    ensureCurrentLocationsIsMutable();
                    this.currentLocations_.addAll(resourceLocation.currentLocations_);
                }
                onChanged();
            }
            if (!resourceLocation.originalLocations_.isEmpty()) {
                if (this.originalLocations_.isEmpty()) {
                    this.originalLocations_ = resourceLocation.originalLocations_;
                    this.bitField0_ &= -3;
                } else {
                    ensureOriginalLocationsIsMutable();
                    this.originalLocations_.addAll(resourceLocation.originalLocations_);
                }
                onChanged();
            }
            mergeUnknownFields(resourceLocation.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ResourceLocation resourceLocation = null;
            try {
                try {
                    ResourceLocation resourceLocation2 = (ResourceLocation) ResourceLocation.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (resourceLocation2 != null) {
                        mergeFrom(resourceLocation2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ResourceLocation resourceLocation3 = (ResourceLocation) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        resourceLocation = resourceLocation3;
                        if (resourceLocation != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (resourceLocation != null) {
                    mergeFrom(resourceLocation);
                }
                throw th;
            }
        }

        private void ensureCurrentLocationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.currentLocations_ = new LazyStringArrayList(this.currentLocations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.audit.ResourceLocationOrBuilder
        public ProtocolStringList getCurrentLocationsList() {
            return this.currentLocations_.getUnmodifiableView();
        }

        @Override // com.google.cloud.audit.ResourceLocationOrBuilder
        public int getCurrentLocationsCount() {
            return this.currentLocations_.size();
        }

        @Override // com.google.cloud.audit.ResourceLocationOrBuilder
        public String getCurrentLocations(int i) {
            return (String) this.currentLocations_.get(i);
        }

        @Override // com.google.cloud.audit.ResourceLocationOrBuilder
        public ByteString getCurrentLocationsBytes(int i) {
            return this.currentLocations_.getByteString(i);
        }

        public Builder setCurrentLocations(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureCurrentLocationsIsMutable();
            this.currentLocations_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addCurrentLocations(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureCurrentLocationsIsMutable();
            this.currentLocations_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllCurrentLocations(Iterable<String> iterable) {
            ensureCurrentLocationsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.currentLocations_);
            onChanged();
            return this;
        }

        public Builder clearCurrentLocations() {
            this.currentLocations_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addCurrentLocationsBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceLocation.checkByteStringIsUtf8(byteString);
                ensureCurrentLocationsIsMutable();
                this.currentLocations_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureOriginalLocationsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.originalLocations_ = new LazyStringArrayList(this.originalLocations_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.cloud.audit.ResourceLocationOrBuilder
        public ProtocolStringList getOriginalLocationsList() {
            return this.originalLocations_.getUnmodifiableView();
        }

        @Override // com.google.cloud.audit.ResourceLocationOrBuilder
        public int getOriginalLocationsCount() {
            return this.originalLocations_.size();
        }

        @Override // com.google.cloud.audit.ResourceLocationOrBuilder
        public String getOriginalLocations(int i) {
            return (String) this.originalLocations_.get(i);
        }

        @Override // com.google.cloud.audit.ResourceLocationOrBuilder
        public ByteString getOriginalLocationsBytes(int i) {
            return this.originalLocations_.getByteString(i);
        }

        public Builder setOriginalLocations(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureOriginalLocationsIsMutable();
            this.originalLocations_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addOriginalLocations(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureOriginalLocationsIsMutable();
            this.originalLocations_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllOriginalLocations(Iterable<String> iterable) {
            ensureOriginalLocationsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.originalLocations_);
            onChanged();
            return this;
        }

        public Builder clearOriginalLocations() {
            this.originalLocations_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addOriginalLocationsBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceLocation.checkByteStringIsUtf8(byteString);
                ensureOriginalLocationsIsMutable();
                this.originalLocations_.add(byteString);
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

    public static ResourceLocation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourceLocation> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ResourceLocation> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ResourceLocation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
