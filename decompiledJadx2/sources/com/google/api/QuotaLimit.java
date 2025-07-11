package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class QuotaLimit extends GeneratedMessageV3 implements QuotaLimitOrBuilder {
    public static final int DEFAULT_LIMIT_FIELD_NUMBER = 3;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 12;
    public static final int DURATION_FIELD_NUMBER = 5;
    public static final int FREE_TIER_FIELD_NUMBER = 7;
    public static final int MAX_LIMIT_FIELD_NUMBER = 4;
    public static final int METRIC_FIELD_NUMBER = 8;
    public static final int NAME_FIELD_NUMBER = 6;
    public static final int UNIT_FIELD_NUMBER = 9;
    public static final int VALUES_FIELD_NUMBER = 10;
    private static final long serialVersionUID = 0;
    private long defaultLimit_;
    private volatile Object description_;
    private volatile Object displayName_;
    private volatile Object duration_;
    private long freeTier_;
    private long maxLimit_;
    private byte memoizedIsInitialized;
    private volatile Object metric_;
    private volatile Object name_;
    private volatile Object unit_;
    private MapField<String, Long> values_;
    private static final QuotaLimit DEFAULT_INSTANCE = new QuotaLimit();
    private static final Parser<QuotaLimit> PARSER = new AbstractParser<QuotaLimit>() { // from class: com.google.api.QuotaLimit.1
        @Override // com.google.protobuf.Parser
        public QuotaLimit parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new QuotaLimit(codedInputStream, extensionRegistryLite);
        }
    };

    private QuotaLimit(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private QuotaLimit() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.description_ = "";
        this.duration_ = "";
        this.metric_ = "";
        this.unit_ = "";
        this.displayName_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new QuotaLimit();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0012. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    private QuotaLimit(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    switch (readTag) {
                        case 0:
                            z = true;
                        case 18:
                            this.description_ = codedInputStream.readStringRequireUtf8();
                        case 24:
                            this.defaultLimit_ = codedInputStream.readInt64();
                        case 32:
                            this.maxLimit_ = codedInputStream.readInt64();
                        case 42:
                            this.duration_ = codedInputStream.readStringRequireUtf8();
                        case 50:
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        case 56:
                            this.freeTier_ = codedInputStream.readInt64();
                        case 66:
                            this.metric_ = codedInputStream.readStringRequireUtf8();
                        case 74:
                            this.unit_ = codedInputStream.readStringRequireUtf8();
                        case 82:
                            if (!(z2 & true)) {
                                this.values_ = MapField.newMapField(ValuesDefaultEntryHolder.defaultEntry);
                                z2 |= true;
                            }
                            MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(ValuesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.values_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                        case 98:
                            this.displayName_ = codedInputStream.readStringRequireUtf8();
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                z = true;
                            }
                    }
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
        return QuotaProto.internal_static_google_api_QuotaLimit_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 10) {
            return internalGetValues();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return QuotaProto.internal_static_google_api_QuotaLimit_fieldAccessorTable.ensureFieldAccessorsInitialized(QuotaLimit.class, Builder.class);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getDefaultLimit() {
        return this.defaultLimit_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getMaxLimit() {
        return this.maxLimit_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getFreeTier() {
        return this.freeTier_;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getDuration() {
        Object obj = this.duration_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.duration_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getDurationBytes() {
        Object obj = this.duration_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.duration_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getMetric() {
        Object obj = this.metric_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.metric_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getMetricBytes() {
        Object obj = this.metric_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.metric_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getUnit() {
        Object obj = this.unit_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.unit_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getUnitBytes() {
        Object obj = this.unit_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.unit_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class ValuesDefaultEntryHolder {
        static final MapEntry<String, Long> defaultEntry = MapEntry.newDefaultInstance(QuotaProto.internal_static_google_api_QuotaLimit_ValuesEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.INT64, 0L);

        private ValuesDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Long> internalGetValues() {
        MapField<String, Long> mapField = this.values_;
        return mapField == null ? MapField.emptyMapField(ValuesDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public int getValuesCount() {
        return internalGetValues().getMap().size();
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public boolean containsValues(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        return internalGetValues().getMap().containsKey(str);
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    @Deprecated
    public Map<String, Long> getValues() {
        return getValuesMap();
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public Map<String, Long> getValuesMap() {
        return internalGetValues().getMap();
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getValuesOrDefault(String str, long j) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, Long> map = internalGetValues().getMap();
        return map.containsKey(str) ? map.get(str).longValue() : j;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public long getValuesOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, Long> map = internalGetValues().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return map.get(str).longValue();
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.QuotaLimitOrBuilder
    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = copyFromUtf8;
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
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.description_);
        }
        long j = this.defaultLimit_;
        if (j != 0) {
            codedOutputStream.writeInt64(3, j);
        }
        long j2 = this.maxLimit_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(4, j2);
        }
        if (!getDurationBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.duration_);
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.name_);
        }
        long j3 = this.freeTier_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(7, j3);
        }
        if (!getMetricBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.metric_);
        }
        if (!getUnitBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.unit_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetValues(), ValuesDefaultEntryHolder.defaultEntry, 10);
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 12, this.displayName_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getDescriptionBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(2, this.description_);
        long j = this.defaultLimit_;
        if (j != 0) {
            computeStringSize += CodedOutputStream.computeInt64Size(3, j);
        }
        long j2 = this.maxLimit_;
        if (j2 != 0) {
            computeStringSize += CodedOutputStream.computeInt64Size(4, j2);
        }
        if (!getDurationBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.duration_);
        }
        if (!getNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(6, this.name_);
        }
        long j3 = this.freeTier_;
        if (j3 != 0) {
            computeStringSize += CodedOutputStream.computeInt64Size(7, j3);
        }
        if (!getMetricBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(8, this.metric_);
        }
        if (!getUnitBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(9, this.unit_);
        }
        for (Map.Entry<String, Long> entry : internalGetValues().getMap().entrySet()) {
            computeStringSize += CodedOutputStream.computeMessageSize(10, ValuesDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        if (!getDisplayNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(12, this.displayName_);
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
        if (!(obj instanceof QuotaLimit)) {
            return super.equals(obj);
        }
        QuotaLimit quotaLimit = (QuotaLimit) obj;
        return getName().equals(quotaLimit.getName()) && getDescription().equals(quotaLimit.getDescription()) && getDefaultLimit() == quotaLimit.getDefaultLimit() && getMaxLimit() == quotaLimit.getMaxLimit() && getFreeTier() == quotaLimit.getFreeTier() && getDuration().equals(quotaLimit.getDuration()) && getMetric().equals(quotaLimit.getMetric()) && getUnit().equals(quotaLimit.getUnit()) && internalGetValues().equals(quotaLimit.internalGetValues()) && getDisplayName().equals(quotaLimit.getDisplayName()) && this.unknownFields.equals(quotaLimit.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 6) * 53) + getName().hashCode()) * 37) + 2) * 53) + getDescription().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getDefaultLimit())) * 37) + 4) * 53) + Internal.hashLong(getMaxLimit())) * 37) + 7) * 53) + Internal.hashLong(getFreeTier())) * 37) + 5) * 53) + getDuration().hashCode()) * 37) + 8) * 53) + getMetric().hashCode()) * 37) + 9) * 53) + getUnit().hashCode();
        if (!internalGetValues().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 10) * 53) + internalGetValues().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 12) * 53) + getDisplayName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static QuotaLimit parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static QuotaLimit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static QuotaLimit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static QuotaLimit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(InputStream inputStream) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static QuotaLimit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QuotaLimit parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static QuotaLimit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static QuotaLimit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(QuotaLimit quotaLimit) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(quotaLimit);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QuotaLimitOrBuilder {
        private int bitField0_;
        private long defaultLimit_;
        private Object description_;
        private Object displayName_;
        private Object duration_;
        private long freeTier_;
        private long maxLimit_;
        private Object metric_;
        private Object name_;
        private Object unit_;
        private MapField<String, Long> values_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return QuotaProto.internal_static_google_api_QuotaLimit_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 10) {
                return internalGetValues();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 10) {
                return internalGetMutableValues();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return QuotaProto.internal_static_google_api_QuotaLimit_fieldAccessorTable.ensureFieldAccessorsInitialized(QuotaLimit.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.description_ = "";
            this.duration_ = "";
            this.metric_ = "";
            this.unit_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.description_ = "";
            this.duration_ = "";
            this.metric_ = "";
            this.unit_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = QuotaLimit.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.description_ = "";
            this.defaultLimit_ = 0L;
            this.maxLimit_ = 0L;
            this.freeTier_ = 0L;
            this.duration_ = "";
            this.metric_ = "";
            this.unit_ = "";
            internalGetMutableValues().clear();
            this.displayName_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return QuotaProto.internal_static_google_api_QuotaLimit_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public QuotaLimit getDefaultInstanceForType() {
            return QuotaLimit.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public QuotaLimit build() {
            QuotaLimit buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public QuotaLimit buildPartial() {
            QuotaLimit quotaLimit = new QuotaLimit(this);
            int i = this.bitField0_;
            quotaLimit.name_ = this.name_;
            quotaLimit.description_ = this.description_;
            quotaLimit.defaultLimit_ = this.defaultLimit_;
            quotaLimit.maxLimit_ = this.maxLimit_;
            quotaLimit.freeTier_ = this.freeTier_;
            quotaLimit.duration_ = this.duration_;
            quotaLimit.metric_ = this.metric_;
            quotaLimit.unit_ = this.unit_;
            quotaLimit.values_ = internalGetValues();
            quotaLimit.values_.makeImmutable();
            quotaLimit.displayName_ = this.displayName_;
            onBuilt();
            return quotaLimit;
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
            if (message instanceof QuotaLimit) {
                return mergeFrom((QuotaLimit) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(QuotaLimit quotaLimit) {
            if (quotaLimit == QuotaLimit.getDefaultInstance()) {
                return this;
            }
            if (!quotaLimit.getName().isEmpty()) {
                this.name_ = quotaLimit.name_;
                onChanged();
            }
            if (!quotaLimit.getDescription().isEmpty()) {
                this.description_ = quotaLimit.description_;
                onChanged();
            }
            if (quotaLimit.getDefaultLimit() != 0) {
                setDefaultLimit(quotaLimit.getDefaultLimit());
            }
            if (quotaLimit.getMaxLimit() != 0) {
                setMaxLimit(quotaLimit.getMaxLimit());
            }
            if (quotaLimit.getFreeTier() != 0) {
                setFreeTier(quotaLimit.getFreeTier());
            }
            if (!quotaLimit.getDuration().isEmpty()) {
                this.duration_ = quotaLimit.duration_;
                onChanged();
            }
            if (!quotaLimit.getMetric().isEmpty()) {
                this.metric_ = quotaLimit.metric_;
                onChanged();
            }
            if (!quotaLimit.getUnit().isEmpty()) {
                this.unit_ = quotaLimit.unit_;
                onChanged();
            }
            internalGetMutableValues().mergeFrom(quotaLimit.internalGetValues());
            if (!quotaLimit.getDisplayName().isEmpty()) {
                this.displayName_ = quotaLimit.displayName_;
                onChanged();
            }
            mergeUnknownFields(quotaLimit.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            QuotaLimit quotaLimit = null;
            try {
                try {
                    QuotaLimit quotaLimit2 = (QuotaLimit) QuotaLimit.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (quotaLimit2 != null) {
                        mergeFrom(quotaLimit2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    QuotaLimit quotaLimit3 = (QuotaLimit) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        quotaLimit = quotaLimit3;
                        if (quotaLimit != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (quotaLimit != null) {
                    mergeFrom(quotaLimit);
                }
                throw th;
            }
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = QuotaLimit.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
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
            this.description_ = QuotaLimit.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getDefaultLimit() {
            return this.defaultLimit_;
        }

        public Builder setDefaultLimit(long j) {
            this.defaultLimit_ = j;
            onChanged();
            return this;
        }

        public Builder clearDefaultLimit() {
            this.defaultLimit_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getMaxLimit() {
            return this.maxLimit_;
        }

        public Builder setMaxLimit(long j) {
            this.maxLimit_ = j;
            onChanged();
            return this;
        }

        public Builder clearMaxLimit() {
            this.maxLimit_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getFreeTier() {
            return this.freeTier_;
        }

        public Builder setFreeTier(long j) {
            this.freeTier_ = j;
            onChanged();
            return this;
        }

        public Builder clearFreeTier() {
            this.freeTier_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getDuration() {
            Object obj = this.duration_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.duration_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getDurationBytes() {
            Object obj = this.duration_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.duration_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDuration(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.duration_ = str;
            onChanged();
            return this;
        }

        public Builder clearDuration() {
            this.duration_ = QuotaLimit.getDefaultInstance().getDuration();
            onChanged();
            return this;
        }

        public Builder setDurationBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.duration_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getMetric() {
            Object obj = this.metric_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.metric_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getMetricBytes() {
            Object obj = this.metric_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.metric_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMetric(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.metric_ = str;
            onChanged();
            return this;
        }

        public Builder clearMetric() {
            this.metric_ = QuotaLimit.getDefaultInstance().getMetric();
            onChanged();
            return this;
        }

        public Builder setMetricBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.metric_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getUnit() {
            Object obj = this.unit_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.unit_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getUnitBytes() {
            Object obj = this.unit_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.unit_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setUnit(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.unit_ = str;
            onChanged();
            return this;
        }

        public Builder clearUnit() {
            this.unit_ = QuotaLimit.getDefaultInstance().getUnit();
            onChanged();
            return this;
        }

        public Builder setUnitBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.unit_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private MapField<String, Long> internalGetValues() {
            MapField<String, Long> mapField = this.values_;
            return mapField == null ? MapField.emptyMapField(ValuesDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Long> internalGetMutableValues() {
            onChanged();
            if (this.values_ == null) {
                this.values_ = MapField.newMapField(ValuesDefaultEntryHolder.defaultEntry);
            }
            if (!this.values_.isMutable()) {
                this.values_ = this.values_.copy();
            }
            return this.values_;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public int getValuesCount() {
            return internalGetValues().getMap().size();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public boolean containsValues(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            return internalGetValues().getMap().containsKey(str);
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        @Deprecated
        public Map<String, Long> getValues() {
            return getValuesMap();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public Map<String, Long> getValuesMap() {
            return internalGetValues().getMap();
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getValuesOrDefault(String str, long j) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, Long> map = internalGetValues().getMap();
            return map.containsKey(str) ? map.get(str).longValue() : j;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public long getValuesOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, Long> map = internalGetValues().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return map.get(str).longValue();
        }

        public Builder clearValues() {
            internalGetMutableValues().getMutableMap().clear();
            return this;
        }

        public Builder removeValues(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            internalGetMutableValues().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Long> getMutableValues() {
            return internalGetMutableValues().getMutableMap();
        }

        public Builder putValues(String str, long j) {
            if (str == null) {
                throw new NullPointerException();
            }
            internalGetMutableValues().getMutableMap().put(str, Long.valueOf(j));
            return this;
        }

        public Builder putAllValues(Map<String, Long> map) {
            internalGetMutableValues().getMutableMap().putAll(map);
            return this;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public String getDisplayName() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.displayName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.QuotaLimitOrBuilder
        public ByteString getDisplayNameBytes() {
            Object obj = this.displayName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.displayName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDisplayName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.displayName_ = str;
            onChanged();
            return this;
        }

        public Builder clearDisplayName() {
            this.displayName_ = QuotaLimit.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString;
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

    public static QuotaLimit getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<QuotaLimit> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<QuotaLimit> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public QuotaLimit getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
