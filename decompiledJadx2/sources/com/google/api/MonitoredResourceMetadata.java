package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class MonitoredResourceMetadata extends GeneratedMessageV3 implements MonitoredResourceMetadataOrBuilder {
    private static final MonitoredResourceMetadata DEFAULT_INSTANCE = new MonitoredResourceMetadata();
    private static final Parser<MonitoredResourceMetadata> PARSER = new AbstractParser<MonitoredResourceMetadata>() { // from class: com.google.api.MonitoredResourceMetadata.1
        @Override // com.google.protobuf.Parser
        public MonitoredResourceMetadata parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MonitoredResourceMetadata(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SYSTEM_LABELS_FIELD_NUMBER = 1;
    public static final int USER_LABELS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private Struct systemLabels_;
    private MapField<String, String> userLabels_;

    private MonitoredResourceMetadata(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MonitoredResourceMetadata() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new MonitoredResourceMetadata();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MonitoredResourceMetadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            Struct.Builder builder = this.systemLabels_ != null ? this.systemLabels_.toBuilder() : null;
                            this.systemLabels_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.systemLabels_);
                                this.systemLabels_ = builder.buildPartial();
                            }
                        } else if (readTag == 18) {
                            if (!(z2 & true)) {
                                this.userLabels_ = MapField.newMapField(UserLabelsDefaultEntryHolder.defaultEntry);
                                z2 |= true;
                            }
                            MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(UserLabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.userLabels_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
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
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetUserLabels();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.f1331xb523e532.ensureFieldAccessorsInitialized(MonitoredResourceMetadata.class, Builder.class);
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public boolean hasSystemLabels() {
        return this.systemLabels_ != null;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public Struct getSystemLabels() {
        Struct struct = this.systemLabels_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public StructOrBuilder getSystemLabelsOrBuilder() {
        return getSystemLabels();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class UserLabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(MonitoredResourceProto.f1329xf517ba8b, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private UserLabelsDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetUserLabels() {
        MapField<String, String> mapField = this.userLabels_;
        return mapField == null ? MapField.emptyMapField(UserLabelsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public int getUserLabelsCount() {
        return internalGetUserLabels().getMap().size();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public boolean containsUserLabels(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        return internalGetUserLabels().getMap().containsKey(str);
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    @Deprecated
    public Map<String, String> getUserLabels() {
        return getUserLabelsMap();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public Map<String, String> getUserLabelsMap() {
        return internalGetUserLabels().getMap();
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public String getUserLabelsOrDefault(String str, String str2) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, String> map = internalGetUserLabels().getMap();
        return map.containsKey(str) ? map.get(str) : str2;
    }

    @Override // com.google.api.MonitoredResourceMetadataOrBuilder
    public String getUserLabelsOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, String> map = internalGetUserLabels().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return map.get(str);
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
        if (this.systemLabels_ != null) {
            codedOutputStream.writeMessage(1, getSystemLabels());
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetUserLabels(), UserLabelsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.systemLabels_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getSystemLabels()) : 0;
        for (Map.Entry<String, String> entry : internalGetUserLabels().getMap().entrySet()) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, UserLabelsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
        if (!(obj instanceof MonitoredResourceMetadata)) {
            return super.equals(obj);
        }
        MonitoredResourceMetadata monitoredResourceMetadata = (MonitoredResourceMetadata) obj;
        if (hasSystemLabels() != monitoredResourceMetadata.hasSystemLabels()) {
            return false;
        }
        return (!hasSystemLabels() || getSystemLabels().equals(monitoredResourceMetadata.getSystemLabels())) && internalGetUserLabels().equals(monitoredResourceMetadata.internalGetUserLabels()) && this.unknownFields.equals(monitoredResourceMetadata.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasSystemLabels()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getSystemLabels().hashCode();
        }
        if (!internalGetUserLabels().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 2) * 53) + internalGetUserLabels().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream inputStream) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MonitoredResourceMetadata monitoredResourceMetadata) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoredResourceMetadata);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MonitoredResourceMetadataOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> systemLabelsBuilder_;
        private Struct systemLabels_;
        private MapField<String, String> userLabels_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetUserLabels();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableUserLabels();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.f1331xb523e532.ensureFieldAccessorsInitialized(MonitoredResourceMetadata.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = MonitoredResourceMetadata.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = null;
            } else {
                this.systemLabels_ = null;
                this.systemLabelsBuilder_ = null;
            }
            internalGetMutableUserLabels().clear();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MonitoredResourceMetadata getDefaultInstanceForType() {
            return MonitoredResourceMetadata.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MonitoredResourceMetadata build() {
            MonitoredResourceMetadata buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MonitoredResourceMetadata buildPartial() {
            MonitoredResourceMetadata monitoredResourceMetadata = new MonitoredResourceMetadata(this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                monitoredResourceMetadata.systemLabels_ = this.systemLabels_;
            } else {
                monitoredResourceMetadata.systemLabels_ = singleFieldBuilderV3.build();
            }
            monitoredResourceMetadata.userLabels_ = internalGetUserLabels();
            monitoredResourceMetadata.userLabels_.makeImmutable();
            onBuilt();
            return monitoredResourceMetadata;
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
            if (message instanceof MonitoredResourceMetadata) {
                return mergeFrom((MonitoredResourceMetadata) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MonitoredResourceMetadata monitoredResourceMetadata) {
            if (monitoredResourceMetadata == MonitoredResourceMetadata.getDefaultInstance()) {
                return this;
            }
            if (monitoredResourceMetadata.hasSystemLabels()) {
                mergeSystemLabels(monitoredResourceMetadata.getSystemLabels());
            }
            internalGetMutableUserLabels().mergeFrom(monitoredResourceMetadata.internalGetUserLabels());
            mergeUnknownFields(monitoredResourceMetadata.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            MonitoredResourceMetadata monitoredResourceMetadata = null;
            try {
                try {
                    MonitoredResourceMetadata monitoredResourceMetadata2 = (MonitoredResourceMetadata) MonitoredResourceMetadata.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (monitoredResourceMetadata2 != null) {
                        mergeFrom(monitoredResourceMetadata2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    MonitoredResourceMetadata monitoredResourceMetadata3 = (MonitoredResourceMetadata) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        monitoredResourceMetadata = monitoredResourceMetadata3;
                        if (monitoredResourceMetadata != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (monitoredResourceMetadata != null) {
                    mergeFrom(monitoredResourceMetadata);
                }
                throw th;
            }
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public boolean hasSystemLabels() {
            return (this.systemLabelsBuilder_ == null && this.systemLabels_ == null) ? false : true;
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public Struct getSystemLabels() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.systemLabels_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSystemLabels(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.systemLabels_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setSystemLabels(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.systemLabels_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSystemLabels(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.systemLabels_;
                if (struct2 != null) {
                    this.systemLabels_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.systemLabels_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearSystemLabels() {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = null;
                onChanged();
            } else {
                this.systemLabels_ = null;
                this.systemLabelsBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getSystemLabelsBuilder() {
            onChanged();
            return getSystemLabelsFieldBuilder().getBuilder();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public StructOrBuilder getSystemLabelsOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.systemLabels_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getSystemLabelsFieldBuilder() {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabelsBuilder_ = new SingleFieldBuilderV3<>(getSystemLabels(), getParentForChildren(), isClean());
                this.systemLabels_ = null;
            }
            return this.systemLabelsBuilder_;
        }

        private MapField<String, String> internalGetUserLabels() {
            MapField<String, String> mapField = this.userLabels_;
            return mapField == null ? MapField.emptyMapField(UserLabelsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableUserLabels() {
            onChanged();
            if (this.userLabels_ == null) {
                this.userLabels_ = MapField.newMapField(UserLabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.userLabels_.isMutable()) {
                this.userLabels_ = this.userLabels_.copy();
            }
            return this.userLabels_;
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public int getUserLabelsCount() {
            return internalGetUserLabels().getMap().size();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public boolean containsUserLabels(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            return internalGetUserLabels().getMap().containsKey(str);
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        @Deprecated
        public Map<String, String> getUserLabels() {
            return getUserLabelsMap();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public Map<String, String> getUserLabelsMap() {
            return internalGetUserLabels().getMap();
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public String getUserLabelsOrDefault(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, String> map = internalGetUserLabels().getMap();
            return map.containsKey(str) ? map.get(str) : str2;
        }

        @Override // com.google.api.MonitoredResourceMetadataOrBuilder
        public String getUserLabelsOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, String> map = internalGetUserLabels().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return map.get(str);
        }

        public Builder clearUserLabels() {
            internalGetMutableUserLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeUserLabels(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            internalGetMutableUserLabels().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableUserLabels() {
            return internalGetMutableUserLabels().getMutableMap();
        }

        public Builder putUserLabels(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (str2 == null) {
                throw new NullPointerException();
            }
            internalGetMutableUserLabels().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllUserLabels(Map<String, String> map) {
            internalGetMutableUserLabels().getMutableMap().putAll(map);
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

    public static MonitoredResourceMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResourceMetadata> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<MonitoredResourceMetadata> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public MonitoredResourceMetadata getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
