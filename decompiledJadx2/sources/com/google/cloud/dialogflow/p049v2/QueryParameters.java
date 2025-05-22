package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.Context;
import com.google.cloud.dialogflow.p049v2.SentimentAnalysisRequestConfig;
import com.google.cloud.dialogflow.p049v2.SessionEntityType;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.type.LatLng;
import com.google.type.LatLngOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class QueryParameters extends GeneratedMessageV3 implements QueryParametersOrBuilder {
    public static final int CONTEXTS_FIELD_NUMBER = 3;
    public static final int GEO_LOCATION_FIELD_NUMBER = 2;
    public static final int PAYLOAD_FIELD_NUMBER = 6;
    public static final int RESET_CONTEXTS_FIELD_NUMBER = 4;
    public static final int SENTIMENT_ANALYSIS_REQUEST_CONFIG_FIELD_NUMBER = 10;
    public static final int SESSION_ENTITY_TYPES_FIELD_NUMBER = 5;
    public static final int TIME_ZONE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<Context> contexts_;
    private LatLng geoLocation_;
    private byte memoizedIsInitialized;
    private Struct payload_;
    private boolean resetContexts_;
    private SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig_;
    private List<SessionEntityType> sessionEntityTypes_;
    private volatile Object timeZone_;
    private static final QueryParameters DEFAULT_INSTANCE = new QueryParameters();
    private static final Parser<QueryParameters> PARSER = new AbstractParser<QueryParameters>() { // from class: com.google.cloud.dialogflow.v2.QueryParameters.1
        @Override // com.google.protobuf.Parser
        public QueryParameters parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new QueryParameters(codedInputStream, extensionRegistryLite);
        }
    };

    private QueryParameters(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private QueryParameters() {
        this.memoizedIsInitialized = (byte) -1;
        this.timeZone_ = "";
        this.contexts_ = Collections.emptyList();
        this.sessionEntityTypes_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new QueryParameters();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    private QueryParameters(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag != 10) {
                                if (readTag == 18) {
                                    LatLng.Builder builder = this.geoLocation_ != null ? this.geoLocation_.toBuilder() : null;
                                    this.geoLocation_ = (LatLng) codedInputStream.readMessage(LatLng.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.geoLocation_);
                                        this.geoLocation_ = builder.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    int i = (c == true ? 1 : 0) & 1;
                                    c = c;
                                    if (i == 0) {
                                        this.contexts_ = new ArrayList();
                                        c = (c == true ? 1 : 0) | 1;
                                    }
                                    this.contexts_.add(codedInputStream.readMessage(Context.parser(), extensionRegistryLite));
                                } else if (readTag == 32) {
                                    this.resetContexts_ = codedInputStream.readBool();
                                } else if (readTag == 42) {
                                    int i2 = (c == true ? 1 : 0) & 2;
                                    c = c;
                                    if (i2 == 0) {
                                        this.sessionEntityTypes_ = new ArrayList();
                                        c = (c == true ? 1 : 0) | 2;
                                    }
                                    this.sessionEntityTypes_.add(codedInputStream.readMessage(SessionEntityType.parser(), extensionRegistryLite));
                                } else if (readTag == 50) {
                                    Struct.Builder builder2 = this.payload_ != null ? this.payload_.toBuilder() : null;
                                    this.payload_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.payload_);
                                        this.payload_ = builder2.buildPartial();
                                    }
                                } else if (readTag == 82) {
                                    SentimentAnalysisRequestConfig.Builder builder3 = this.sentimentAnalysisRequestConfig_ != null ? this.sentimentAnalysisRequestConfig_.toBuilder() : null;
                                    this.sentimentAnalysisRequestConfig_ = (SentimentAnalysisRequestConfig) codedInputStream.readMessage(SentimentAnalysisRequestConfig.parser(), extensionRegistryLite);
                                    if (builder3 != null) {
                                        builder3.mergeFrom(this.sentimentAnalysisRequestConfig_);
                                        this.sentimentAnalysisRequestConfig_ = builder3.buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.timeZone_ = codedInputStream.readStringRequireUtf8();
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
                if (((c == true ? 1 : 0) & 1) != 0) {
                    this.contexts_ = Collections.unmodifiableList(this.contexts_);
                }
                if (((c == true ? 1 : 0) & 2) != 0) {
                    this.sessionEntityTypes_ = Collections.unmodifiableList(this.sessionEntityTypes_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionProto.f1552x3112f977;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1553x9e48b8f5.ensureFieldAccessorsInitialized(QueryParameters.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public String getTimeZone() {
        Object obj = this.timeZone_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.timeZone_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public ByteString getTimeZoneBytes() {
        Object obj = this.timeZone_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.timeZone_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public boolean hasGeoLocation() {
        return this.geoLocation_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public LatLng getGeoLocation() {
        LatLng latLng = this.geoLocation_;
        return latLng == null ? LatLng.getDefaultInstance() : latLng;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public LatLngOrBuilder getGeoLocationOrBuilder() {
        return getGeoLocation();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public List<Context> getContextsList() {
        return this.contexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public List<? extends ContextOrBuilder> getContextsOrBuilderList() {
        return this.contexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public int getContextsCount() {
        return this.contexts_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public Context getContexts(int i) {
        return this.contexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public ContextOrBuilder getContextsOrBuilder(int i) {
        return this.contexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public boolean getResetContexts() {
        return this.resetContexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public List<SessionEntityType> getSessionEntityTypesList() {
        return this.sessionEntityTypes_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList() {
        return this.sessionEntityTypes_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public int getSessionEntityTypesCount() {
        return this.sessionEntityTypes_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public SessionEntityType getSessionEntityTypes(int i) {
        return this.sessionEntityTypes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i) {
        return this.sessionEntityTypes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public boolean hasPayload() {
        return this.payload_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public Struct getPayload() {
        Struct struct = this.payload_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public StructOrBuilder getPayloadOrBuilder() {
        return getPayload();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public boolean hasSentimentAnalysisRequestConfig() {
        return this.sentimentAnalysisRequestConfig_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public SentimentAnalysisRequestConfig getSentimentAnalysisRequestConfig() {
        SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig = this.sentimentAnalysisRequestConfig_;
        return sentimentAnalysisRequestConfig == null ? SentimentAnalysisRequestConfig.getDefaultInstance() : sentimentAnalysisRequestConfig;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
    public SentimentAnalysisRequestConfigOrBuilder getSentimentAnalysisRequestConfigOrBuilder() {
        return getSentimentAnalysisRequestConfig();
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
        if (!getTimeZoneBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.timeZone_);
        }
        if (this.geoLocation_ != null) {
            codedOutputStream.writeMessage(2, getGeoLocation());
        }
        for (int i = 0; i < this.contexts_.size(); i++) {
            codedOutputStream.writeMessage(3, this.contexts_.get(i));
        }
        boolean z = this.resetContexts_;
        if (z) {
            codedOutputStream.writeBool(4, z);
        }
        for (int i2 = 0; i2 < this.sessionEntityTypes_.size(); i2++) {
            codedOutputStream.writeMessage(5, this.sessionEntityTypes_.get(i2));
        }
        if (this.payload_ != null) {
            codedOutputStream.writeMessage(6, getPayload());
        }
        if (this.sentimentAnalysisRequestConfig_ != null) {
            codedOutputStream.writeMessage(10, getSentimentAnalysisRequestConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getTimeZoneBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.timeZone_) + 0 : 0;
        if (this.geoLocation_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getGeoLocation());
        }
        int i2 = computeStringSize;
        for (int i3 = 0; i3 < this.contexts_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, this.contexts_.get(i3));
        }
        boolean z = this.resetContexts_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(4, z);
        }
        for (int i4 = 0; i4 < this.sessionEntityTypes_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(5, this.sessionEntityTypes_.get(i4));
        }
        if (this.payload_ != null) {
            i2 += CodedOutputStream.computeMessageSize(6, getPayload());
        }
        if (this.sentimentAnalysisRequestConfig_ != null) {
            i2 += CodedOutputStream.computeMessageSize(10, getSentimentAnalysisRequestConfig());
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
        if (!(obj instanceof QueryParameters)) {
            return super.equals(obj);
        }
        QueryParameters queryParameters = (QueryParameters) obj;
        if (!getTimeZone().equals(queryParameters.getTimeZone()) || hasGeoLocation() != queryParameters.hasGeoLocation()) {
            return false;
        }
        if ((hasGeoLocation() && !getGeoLocation().equals(queryParameters.getGeoLocation())) || !getContextsList().equals(queryParameters.getContextsList()) || getResetContexts() != queryParameters.getResetContexts() || !getSessionEntityTypesList().equals(queryParameters.getSessionEntityTypesList()) || hasPayload() != queryParameters.hasPayload()) {
            return false;
        }
        if ((!hasPayload() || getPayload().equals(queryParameters.getPayload())) && hasSentimentAnalysisRequestConfig() == queryParameters.hasSentimentAnalysisRequestConfig()) {
            return (!hasSentimentAnalysisRequestConfig() || getSentimentAnalysisRequestConfig().equals(queryParameters.getSentimentAnalysisRequestConfig())) && this.unknownFields.equals(queryParameters.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTimeZone().hashCode();
        if (hasGeoLocation()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getGeoLocation().hashCode();
        }
        if (getContextsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getContextsList().hashCode();
        }
        int hashBoolean = (((hashCode * 37) + 4) * 53) + Internal.hashBoolean(getResetContexts());
        if (getSessionEntityTypesCount() > 0) {
            hashBoolean = (((hashBoolean * 37) + 5) * 53) + getSessionEntityTypesList().hashCode();
        }
        if (hasPayload()) {
            hashBoolean = (((hashBoolean * 37) + 6) * 53) + getPayload().hashCode();
        }
        if (hasSentimentAnalysisRequestConfig()) {
            hashBoolean = (((hashBoolean * 37) + 10) * 53) + getSentimentAnalysisRequestConfig().hashCode();
        }
        int hashCode2 = (hashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static QueryParameters parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static QueryParameters parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static QueryParameters parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static QueryParameters parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static QueryParameters parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static QueryParameters parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static QueryParameters parseFrom(InputStream inputStream) throws IOException {
        return (QueryParameters) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static QueryParameters parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryParameters) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QueryParameters parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (QueryParameters) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static QueryParameters parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryParameters) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QueryParameters parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (QueryParameters) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static QueryParameters parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryParameters) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(QueryParameters queryParameters) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(queryParameters);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QueryParametersOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> contextsBuilder_;
        private List<Context> contexts_;
        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> geoLocationBuilder_;
        private LatLng geoLocation_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> payloadBuilder_;
        private Struct payload_;
        private boolean resetContexts_;
        private SingleFieldBuilderV3<SentimentAnalysisRequestConfig, SentimentAnalysisRequestConfig.Builder, SentimentAnalysisRequestConfigOrBuilder> sentimentAnalysisRequestConfigBuilder_;
        private SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig_;
        private RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> sessionEntityTypesBuilder_;
        private List<SessionEntityType> sessionEntityTypes_;
        private Object timeZone_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1552x3112f977;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1553x9e48b8f5.ensureFieldAccessorsInitialized(QueryParameters.class, Builder.class);
        }

        private Builder() {
            this.timeZone_ = "";
            this.contexts_ = Collections.emptyList();
            this.sessionEntityTypes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.timeZone_ = "";
            this.contexts_ = Collections.emptyList();
            this.sessionEntityTypes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (QueryParameters.alwaysUseFieldBuilders) {
                getContextsFieldBuilder();
                getSessionEntityTypesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.timeZone_ = "";
            if (this.geoLocationBuilder_ == null) {
                this.geoLocation_ = null;
            } else {
                this.geoLocation_ = null;
                this.geoLocationBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.contexts_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.resetContexts_ = false;
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV32 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.sessionEntityTypes_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            if (this.payloadBuilder_ == null) {
                this.payload_ = null;
            } else {
                this.payload_ = null;
                this.payloadBuilder_ = null;
            }
            if (this.sentimentAnalysisRequestConfigBuilder_ == null) {
                this.sentimentAnalysisRequestConfig_ = null;
            } else {
                this.sentimentAnalysisRequestConfig_ = null;
                this.sentimentAnalysisRequestConfigBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.f1552x3112f977;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public QueryParameters getDefaultInstanceForType() {
            return QueryParameters.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public QueryParameters build() {
            QueryParameters buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public QueryParameters buildPartial() {
            QueryParameters queryParameters = new QueryParameters(this);
            int i = this.bitField0_;
            queryParameters.timeZone_ = this.timeZone_;
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.geoLocationBuilder_;
            if (singleFieldBuilderV3 == null) {
                queryParameters.geoLocation_ = this.geoLocation_;
            } else {
                queryParameters.geoLocation_ = singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                queryParameters.contexts_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.contexts_ = Collections.unmodifiableList(this.contexts_);
                    this.bitField0_ &= -2;
                }
                queryParameters.contexts_ = this.contexts_;
            }
            queryParameters.resetContexts_ = this.resetContexts_;
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV32 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV32 != null) {
                queryParameters.sessionEntityTypes_ = repeatedFieldBuilderV32.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.sessionEntityTypes_ = Collections.unmodifiableList(this.sessionEntityTypes_);
                    this.bitField0_ &= -3;
                }
                queryParameters.sessionEntityTypes_ = this.sessionEntityTypes_;
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV32 = this.payloadBuilder_;
            if (singleFieldBuilderV32 == null) {
                queryParameters.payload_ = this.payload_;
            } else {
                queryParameters.payload_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<SentimentAnalysisRequestConfig, SentimentAnalysisRequestConfig.Builder, SentimentAnalysisRequestConfigOrBuilder> singleFieldBuilderV33 = this.sentimentAnalysisRequestConfigBuilder_;
            if (singleFieldBuilderV33 == null) {
                queryParameters.sentimentAnalysisRequestConfig_ = this.sentimentAnalysisRequestConfig_;
            } else {
                queryParameters.sentimentAnalysisRequestConfig_ = singleFieldBuilderV33.build();
            }
            onBuilt();
            return queryParameters;
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
            if (message instanceof QueryParameters) {
                return mergeFrom((QueryParameters) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(QueryParameters queryParameters) {
            if (queryParameters == QueryParameters.getDefaultInstance()) {
                return this;
            }
            if (!queryParameters.getTimeZone().isEmpty()) {
                this.timeZone_ = queryParameters.timeZone_;
                onChanged();
            }
            if (queryParameters.hasGeoLocation()) {
                mergeGeoLocation(queryParameters.getGeoLocation());
            }
            if (this.contextsBuilder_ == null) {
                if (!queryParameters.contexts_.isEmpty()) {
                    if (this.contexts_.isEmpty()) {
                        this.contexts_ = queryParameters.contexts_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureContextsIsMutable();
                        this.contexts_.addAll(queryParameters.contexts_);
                    }
                    onChanged();
                }
            } else if (!queryParameters.contexts_.isEmpty()) {
                if (!this.contextsBuilder_.isEmpty()) {
                    this.contextsBuilder_.addAllMessages(queryParameters.contexts_);
                } else {
                    this.contextsBuilder_.dispose();
                    this.contextsBuilder_ = null;
                    this.contexts_ = queryParameters.contexts_;
                    this.bitField0_ &= -2;
                    this.contextsBuilder_ = QueryParameters.alwaysUseFieldBuilders ? getContextsFieldBuilder() : null;
                }
            }
            if (queryParameters.getResetContexts()) {
                setResetContexts(queryParameters.getResetContexts());
            }
            if (this.sessionEntityTypesBuilder_ == null) {
                if (!queryParameters.sessionEntityTypes_.isEmpty()) {
                    if (this.sessionEntityTypes_.isEmpty()) {
                        this.sessionEntityTypes_ = queryParameters.sessionEntityTypes_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureSessionEntityTypesIsMutable();
                        this.sessionEntityTypes_.addAll(queryParameters.sessionEntityTypes_);
                    }
                    onChanged();
                }
            } else if (!queryParameters.sessionEntityTypes_.isEmpty()) {
                if (!this.sessionEntityTypesBuilder_.isEmpty()) {
                    this.sessionEntityTypesBuilder_.addAllMessages(queryParameters.sessionEntityTypes_);
                } else {
                    this.sessionEntityTypesBuilder_.dispose();
                    this.sessionEntityTypesBuilder_ = null;
                    this.sessionEntityTypes_ = queryParameters.sessionEntityTypes_;
                    this.bitField0_ &= -3;
                    this.sessionEntityTypesBuilder_ = QueryParameters.alwaysUseFieldBuilders ? getSessionEntityTypesFieldBuilder() : null;
                }
            }
            if (queryParameters.hasPayload()) {
                mergePayload(queryParameters.getPayload());
            }
            if (queryParameters.hasSentimentAnalysisRequestConfig()) {
                mergeSentimentAnalysisRequestConfig(queryParameters.getSentimentAnalysisRequestConfig());
            }
            mergeUnknownFields(queryParameters.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            QueryParameters queryParameters = null;
            try {
                try {
                    QueryParameters queryParameters2 = (QueryParameters) QueryParameters.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (queryParameters2 != null) {
                        mergeFrom(queryParameters2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    QueryParameters queryParameters3 = (QueryParameters) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        queryParameters = queryParameters3;
                        if (queryParameters != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (queryParameters != null) {
                    mergeFrom(queryParameters);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public String getTimeZone() {
            Object obj = this.timeZone_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.timeZone_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public ByteString getTimeZoneBytes() {
            Object obj = this.timeZone_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timeZone_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTimeZone(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.timeZone_ = str;
            onChanged();
            return this;
        }

        public Builder clearTimeZone() {
            this.timeZone_ = QueryParameters.getDefaultInstance().getTimeZone();
            onChanged();
            return this;
        }

        public Builder setTimeZoneBytes(ByteString byteString) {
            if (byteString != null) {
                QueryParameters.checkByteStringIsUtf8(byteString);
                this.timeZone_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public boolean hasGeoLocation() {
            return (this.geoLocationBuilder_ == null && this.geoLocation_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public LatLng getGeoLocation() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.geoLocationBuilder_;
            if (singleFieldBuilderV3 == null) {
                LatLng latLng = this.geoLocation_;
                return latLng == null ? LatLng.getDefaultInstance() : latLng;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setGeoLocation(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.geoLocationBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(latLng);
            } else {
                if (latLng == null) {
                    throw new NullPointerException();
                }
                this.geoLocation_ = latLng;
                onChanged();
            }
            return this;
        }

        public Builder setGeoLocation(LatLng.Builder builder) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.geoLocationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.geoLocation_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeGeoLocation(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.geoLocationBuilder_;
            if (singleFieldBuilderV3 == null) {
                LatLng latLng2 = this.geoLocation_;
                if (latLng2 != null) {
                    this.geoLocation_ = LatLng.newBuilder(latLng2).mergeFrom(latLng).buildPartial();
                } else {
                    this.geoLocation_ = latLng;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(latLng);
            }
            return this;
        }

        public Builder clearGeoLocation() {
            if (this.geoLocationBuilder_ == null) {
                this.geoLocation_ = null;
                onChanged();
            } else {
                this.geoLocation_ = null;
                this.geoLocationBuilder_ = null;
            }
            return this;
        }

        public LatLng.Builder getGeoLocationBuilder() {
            onChanged();
            return getGeoLocationFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public LatLngOrBuilder getGeoLocationOrBuilder() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.geoLocationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            LatLng latLng = this.geoLocation_;
            return latLng == null ? LatLng.getDefaultInstance() : latLng;
        }

        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> getGeoLocationFieldBuilder() {
            if (this.geoLocationBuilder_ == null) {
                this.geoLocationBuilder_ = new SingleFieldBuilderV3<>(getGeoLocation(), getParentForChildren(), isClean());
                this.geoLocation_ = null;
            }
            return this.geoLocationBuilder_;
        }

        private void ensureContextsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.contexts_ = new ArrayList(this.contexts_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public List<Context> getContextsList() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.contexts_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public int getContextsCount() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.contexts_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public ContextOrBuilder getContextsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.contextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.contexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public boolean getResetContexts() {
            return this.resetContexts_;
        }

        public Builder setResetContexts(boolean z) {
            this.resetContexts_ = z;
            onChanged();
            return this;
        }

        public Builder clearResetContexts() {
            this.resetContexts_ = false;
            onChanged();
            return this;
        }

        private void ensureSessionEntityTypesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.sessionEntityTypes_ = new ArrayList(this.sessionEntityTypes_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public List<SessionEntityType> getSessionEntityTypesList() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.sessionEntityTypes_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public int getSessionEntityTypesCount() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sessionEntityTypes_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public SessionEntityType getSessionEntityTypes(int i) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sessionEntityTypes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSessionEntityTypes(int i, SessionEntityType sessionEntityType) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, sessionEntityType);
            } else {
                if (sessionEntityType == null) {
                    throw new NullPointerException();
                }
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.set(i, sessionEntityType);
                onChanged();
            }
            return this;
        }

        public Builder setSessionEntityTypes(int i, SessionEntityType.Builder builder) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addSessionEntityTypes(SessionEntityType sessionEntityType) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(sessionEntityType);
            } else {
                if (sessionEntityType == null) {
                    throw new NullPointerException();
                }
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.add(sessionEntityType);
                onChanged();
            }
            return this;
        }

        public Builder addSessionEntityTypes(int i, SessionEntityType sessionEntityType) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, sessionEntityType);
            } else {
                if (sessionEntityType == null) {
                    throw new NullPointerException();
                }
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.add(i, sessionEntityType);
                onChanged();
            }
            return this;
        }

        public Builder addSessionEntityTypes(SessionEntityType.Builder builder) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addSessionEntityTypes(int i, SessionEntityType.Builder builder) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllSessionEntityTypes(Iterable<? extends SessionEntityType> iterable) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.sessionEntityTypes_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSessionEntityTypes() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.sessionEntityTypes_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSessionEntityTypes(int i) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSessionEntityTypesIsMutable();
                this.sessionEntityTypes_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public SessionEntityType.Builder getSessionEntityTypesBuilder(int i) {
            return getSessionEntityTypesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sessionEntityTypes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.sessionEntityTypes_);
        }

        public SessionEntityType.Builder addSessionEntityTypesBuilder() {
            return getSessionEntityTypesFieldBuilder().addBuilder(SessionEntityType.getDefaultInstance());
        }

        public SessionEntityType.Builder addSessionEntityTypesBuilder(int i) {
            return getSessionEntityTypesFieldBuilder().addBuilder(i, SessionEntityType.getDefaultInstance());
        }

        public List<SessionEntityType.Builder> getSessionEntityTypesBuilderList() {
            return getSessionEntityTypesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> getSessionEntityTypesFieldBuilder() {
            if (this.sessionEntityTypesBuilder_ == null) {
                this.sessionEntityTypesBuilder_ = new RepeatedFieldBuilderV3<>(this.sessionEntityTypes_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.sessionEntityTypes_ = null;
            }
            return this.sessionEntityTypesBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public boolean hasPayload() {
            return (this.payloadBuilder_ == null && this.payload_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public Struct getPayload() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.payload_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setPayload(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.payload_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setPayload(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.payload_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergePayload(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.payload_;
                if (struct2 != null) {
                    this.payload_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.payload_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearPayload() {
            if (this.payloadBuilder_ == null) {
                this.payload_ = null;
                onChanged();
            } else {
                this.payload_ = null;
                this.payloadBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getPayloadBuilder() {
            onChanged();
            return getPayloadFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public StructOrBuilder getPayloadOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.payload_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getPayloadFieldBuilder() {
            if (this.payloadBuilder_ == null) {
                this.payloadBuilder_ = new SingleFieldBuilderV3<>(getPayload(), getParentForChildren(), isClean());
                this.payload_ = null;
            }
            return this.payloadBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public boolean hasSentimentAnalysisRequestConfig() {
            return (this.sentimentAnalysisRequestConfigBuilder_ == null && this.sentimentAnalysisRequestConfig_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public SentimentAnalysisRequestConfig getSentimentAnalysisRequestConfig() {
            SingleFieldBuilderV3<SentimentAnalysisRequestConfig, SentimentAnalysisRequestConfig.Builder, SentimentAnalysisRequestConfigOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisRequestConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig = this.sentimentAnalysisRequestConfig_;
                return sentimentAnalysisRequestConfig == null ? SentimentAnalysisRequestConfig.getDefaultInstance() : sentimentAnalysisRequestConfig;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSentimentAnalysisRequestConfig(SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig) {
            SingleFieldBuilderV3<SentimentAnalysisRequestConfig, SentimentAnalysisRequestConfig.Builder, SentimentAnalysisRequestConfigOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisRequestConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sentimentAnalysisRequestConfig);
            } else {
                if (sentimentAnalysisRequestConfig == null) {
                    throw new NullPointerException();
                }
                this.sentimentAnalysisRequestConfig_ = sentimentAnalysisRequestConfig;
                onChanged();
            }
            return this;
        }

        public Builder setSentimentAnalysisRequestConfig(SentimentAnalysisRequestConfig.Builder builder) {
            SingleFieldBuilderV3<SentimentAnalysisRequestConfig, SentimentAnalysisRequestConfig.Builder, SentimentAnalysisRequestConfigOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisRequestConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sentimentAnalysisRequestConfig_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSentimentAnalysisRequestConfig(SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig) {
            SingleFieldBuilderV3<SentimentAnalysisRequestConfig, SentimentAnalysisRequestConfig.Builder, SentimentAnalysisRequestConfigOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisRequestConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig2 = this.sentimentAnalysisRequestConfig_;
                if (sentimentAnalysisRequestConfig2 != null) {
                    this.sentimentAnalysisRequestConfig_ = SentimentAnalysisRequestConfig.newBuilder(sentimentAnalysisRequestConfig2).mergeFrom(sentimentAnalysisRequestConfig).buildPartial();
                } else {
                    this.sentimentAnalysisRequestConfig_ = sentimentAnalysisRequestConfig;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sentimentAnalysisRequestConfig);
            }
            return this;
        }

        public Builder clearSentimentAnalysisRequestConfig() {
            if (this.sentimentAnalysisRequestConfigBuilder_ == null) {
                this.sentimentAnalysisRequestConfig_ = null;
                onChanged();
            } else {
                this.sentimentAnalysisRequestConfig_ = null;
                this.sentimentAnalysisRequestConfigBuilder_ = null;
            }
            return this;
        }

        public SentimentAnalysisRequestConfig.Builder getSentimentAnalysisRequestConfigBuilder() {
            onChanged();
            return getSentimentAnalysisRequestConfigFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryParametersOrBuilder
        public SentimentAnalysisRequestConfigOrBuilder getSentimentAnalysisRequestConfigOrBuilder() {
            SingleFieldBuilderV3<SentimentAnalysisRequestConfig, SentimentAnalysisRequestConfig.Builder, SentimentAnalysisRequestConfigOrBuilder> singleFieldBuilderV3 = this.sentimentAnalysisRequestConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SentimentAnalysisRequestConfig sentimentAnalysisRequestConfig = this.sentimentAnalysisRequestConfig_;
            return sentimentAnalysisRequestConfig == null ? SentimentAnalysisRequestConfig.getDefaultInstance() : sentimentAnalysisRequestConfig;
        }

        private SingleFieldBuilderV3<SentimentAnalysisRequestConfig, SentimentAnalysisRequestConfig.Builder, SentimentAnalysisRequestConfigOrBuilder> getSentimentAnalysisRequestConfigFieldBuilder() {
            if (this.sentimentAnalysisRequestConfigBuilder_ == null) {
                this.sentimentAnalysisRequestConfigBuilder_ = new SingleFieldBuilderV3<>(getSentimentAnalysisRequestConfig(), getParentForChildren(), isClean());
                this.sentimentAnalysisRequestConfig_ = null;
            }
            return this.sentimentAnalysisRequestConfigBuilder_;
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

    public static QueryParameters getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<QueryParameters> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<QueryParameters> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public QueryParameters getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
