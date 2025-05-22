package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequest;
import com.google.cloud.dialogflow.p049v2.QueryResult;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class WebhookRequest extends GeneratedMessageV3 implements WebhookRequestOrBuilder {
    public static final int ORIGINAL_DETECT_INTENT_REQUEST_FIELD_NUMBER = 3;
    public static final int QUERY_RESULT_FIELD_NUMBER = 2;
    public static final int RESPONSE_ID_FIELD_NUMBER = 1;
    public static final int SESSION_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private OriginalDetectIntentRequest originalDetectIntentRequest_;
    private QueryResult queryResult_;
    private volatile Object responseId_;
    private volatile Object session_;
    private static final WebhookRequest DEFAULT_INSTANCE = new WebhookRequest();
    private static final Parser<WebhookRequest> PARSER = new AbstractParser<WebhookRequest>() { // from class: com.google.cloud.dialogflow.v2.WebhookRequest.1
        @Override // com.google.protobuf.Parser
        public WebhookRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new WebhookRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private WebhookRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private WebhookRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.session_ = "";
        this.responseId_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new WebhookRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private WebhookRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 10) {
                                if (readTag == 18) {
                                    QueryResult.Builder builder = this.queryResult_ != null ? this.queryResult_.toBuilder() : null;
                                    this.queryResult_ = (QueryResult) codedInputStream.readMessage(QueryResult.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.queryResult_);
                                        this.queryResult_ = builder.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    OriginalDetectIntentRequest.Builder builder2 = this.originalDetectIntentRequest_ != null ? this.originalDetectIntentRequest_.toBuilder() : null;
                                    this.originalDetectIntentRequest_ = (OriginalDetectIntentRequest) codedInputStream.readMessage(OriginalDetectIntentRequest.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.originalDetectIntentRequest_);
                                        this.originalDetectIntentRequest_ = builder2.buildPartial();
                                    }
                                } else if (readTag == 34) {
                                    this.session_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.responseId_ = codedInputStream.readStringRequireUtf8();
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
        return WebhookProto.f1574xbe30fa1b;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebhookProto.f1575x293dd599.ensureFieldAccessorsInitialized(WebhookRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public String getSession() {
        Object obj = this.session_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.session_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public ByteString getSessionBytes() {
        Object obj = this.session_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.session_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public String getResponseId() {
        Object obj = this.responseId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.responseId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public ByteString getResponseIdBytes() {
        Object obj = this.responseId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.responseId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public boolean hasQueryResult() {
        return this.queryResult_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public QueryResult getQueryResult() {
        QueryResult queryResult = this.queryResult_;
        return queryResult == null ? QueryResult.getDefaultInstance() : queryResult;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public QueryResultOrBuilder getQueryResultOrBuilder() {
        return getQueryResult();
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public boolean hasOriginalDetectIntentRequest() {
        return this.originalDetectIntentRequest_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
        OriginalDetectIntentRequest originalDetectIntentRequest = this.originalDetectIntentRequest_;
        return originalDetectIntentRequest == null ? OriginalDetectIntentRequest.getDefaultInstance() : originalDetectIntentRequest;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
    public OriginalDetectIntentRequestOrBuilder getOriginalDetectIntentRequestOrBuilder() {
        return getOriginalDetectIntentRequest();
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
        if (!getResponseIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.responseId_);
        }
        if (this.queryResult_ != null) {
            codedOutputStream.writeMessage(2, getQueryResult());
        }
        if (this.originalDetectIntentRequest_ != null) {
            codedOutputStream.writeMessage(3, getOriginalDetectIntentRequest());
        }
        if (!getSessionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.session_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getResponseIdBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.responseId_);
        if (this.queryResult_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getQueryResult());
        }
        if (this.originalDetectIntentRequest_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, getOriginalDetectIntentRequest());
        }
        if (!getSessionBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.session_);
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
        if (!(obj instanceof WebhookRequest)) {
            return super.equals(obj);
        }
        WebhookRequest webhookRequest = (WebhookRequest) obj;
        if (!getSession().equals(webhookRequest.getSession()) || !getResponseId().equals(webhookRequest.getResponseId()) || hasQueryResult() != webhookRequest.hasQueryResult()) {
            return false;
        }
        if ((!hasQueryResult() || getQueryResult().equals(webhookRequest.getQueryResult())) && hasOriginalDetectIntentRequest() == webhookRequest.hasOriginalDetectIntentRequest()) {
            return (!hasOriginalDetectIntentRequest() || getOriginalDetectIntentRequest().equals(webhookRequest.getOriginalDetectIntentRequest())) && this.unknownFields.equals(webhookRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 4) * 53) + getSession().hashCode()) * 37) + 1) * 53) + getResponseId().hashCode();
        if (hasQueryResult()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getQueryResult().hashCode();
        }
        if (hasOriginalDetectIntentRequest()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOriginalDetectIntentRequest().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static WebhookRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static WebhookRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static WebhookRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static WebhookRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static WebhookRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static WebhookRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static WebhookRequest parseFrom(InputStream inputStream) throws IOException {
        return (WebhookRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static WebhookRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WebhookRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static WebhookRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WebhookRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static WebhookRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WebhookRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static WebhookRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WebhookRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static WebhookRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WebhookRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WebhookRequest webhookRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(webhookRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebhookRequestOrBuilder {
        private SingleFieldBuilderV3<OriginalDetectIntentRequest, OriginalDetectIntentRequest.Builder, OriginalDetectIntentRequestOrBuilder> originalDetectIntentRequestBuilder_;
        private OriginalDetectIntentRequest originalDetectIntentRequest_;
        private SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> queryResultBuilder_;
        private QueryResult queryResult_;
        private Object responseId_;
        private Object session_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return WebhookProto.f1574xbe30fa1b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WebhookProto.f1575x293dd599.ensureFieldAccessorsInitialized(WebhookRequest.class, Builder.class);
        }

        private Builder() {
            this.session_ = "";
            this.responseId_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.session_ = "";
            this.responseId_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = WebhookRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.session_ = "";
            this.responseId_ = "";
            if (this.queryResultBuilder_ == null) {
                this.queryResult_ = null;
            } else {
                this.queryResult_ = null;
                this.queryResultBuilder_ = null;
            }
            if (this.originalDetectIntentRequestBuilder_ == null) {
                this.originalDetectIntentRequest_ = null;
            } else {
                this.originalDetectIntentRequest_ = null;
                this.originalDetectIntentRequestBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return WebhookProto.f1574xbe30fa1b;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public WebhookRequest getDefaultInstanceForType() {
            return WebhookRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public WebhookRequest build() {
            WebhookRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public WebhookRequest buildPartial() {
            WebhookRequest webhookRequest = new WebhookRequest(this);
            webhookRequest.session_ = this.session_;
            webhookRequest.responseId_ = this.responseId_;
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                webhookRequest.queryResult_ = this.queryResult_;
            } else {
                webhookRequest.queryResult_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<OriginalDetectIntentRequest, OriginalDetectIntentRequest.Builder, OriginalDetectIntentRequestOrBuilder> singleFieldBuilderV32 = this.originalDetectIntentRequestBuilder_;
            if (singleFieldBuilderV32 == null) {
                webhookRequest.originalDetectIntentRequest_ = this.originalDetectIntentRequest_;
            } else {
                webhookRequest.originalDetectIntentRequest_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return webhookRequest;
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
            if (message instanceof WebhookRequest) {
                return mergeFrom((WebhookRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(WebhookRequest webhookRequest) {
            if (webhookRequest == WebhookRequest.getDefaultInstance()) {
                return this;
            }
            if (!webhookRequest.getSession().isEmpty()) {
                this.session_ = webhookRequest.session_;
                onChanged();
            }
            if (!webhookRequest.getResponseId().isEmpty()) {
                this.responseId_ = webhookRequest.responseId_;
                onChanged();
            }
            if (webhookRequest.hasQueryResult()) {
                mergeQueryResult(webhookRequest.getQueryResult());
            }
            if (webhookRequest.hasOriginalDetectIntentRequest()) {
                mergeOriginalDetectIntentRequest(webhookRequest.getOriginalDetectIntentRequest());
            }
            mergeUnknownFields(webhookRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            WebhookRequest webhookRequest = null;
            try {
                try {
                    WebhookRequest webhookRequest2 = (WebhookRequest) WebhookRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (webhookRequest2 != null) {
                        mergeFrom(webhookRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    WebhookRequest webhookRequest3 = (WebhookRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        webhookRequest = webhookRequest3;
                        if (webhookRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (webhookRequest != null) {
                    mergeFrom(webhookRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public String getSession() {
            Object obj = this.session_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.session_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public ByteString getSessionBytes() {
            Object obj = this.session_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.session_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSession(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.session_ = str;
            onChanged();
            return this;
        }

        public Builder clearSession() {
            this.session_ = WebhookRequest.getDefaultInstance().getSession();
            onChanged();
            return this;
        }

        public Builder setSessionBytes(ByteString byteString) {
            if (byteString != null) {
                WebhookRequest.checkByteStringIsUtf8(byteString);
                this.session_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public String getResponseId() {
            Object obj = this.responseId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.responseId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public ByteString getResponseIdBytes() {
            Object obj = this.responseId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.responseId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResponseId(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.responseId_ = str;
            onChanged();
            return this;
        }

        public Builder clearResponseId() {
            this.responseId_ = WebhookRequest.getDefaultInstance().getResponseId();
            onChanged();
            return this;
        }

        public Builder setResponseIdBytes(ByteString byteString) {
            if (byteString != null) {
                WebhookRequest.checkByteStringIsUtf8(byteString);
                this.responseId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public boolean hasQueryResult() {
            return (this.queryResultBuilder_ == null && this.queryResult_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public QueryResult getQueryResult() {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                QueryResult queryResult = this.queryResult_;
                return queryResult == null ? QueryResult.getDefaultInstance() : queryResult;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setQueryResult(QueryResult queryResult) {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(queryResult);
            } else {
                if (queryResult == null) {
                    throw new NullPointerException();
                }
                this.queryResult_ = queryResult;
                onChanged();
            }
            return this;
        }

        public Builder setQueryResult(QueryResult.Builder builder) {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.queryResult_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeQueryResult(QueryResult queryResult) {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 == null) {
                QueryResult queryResult2 = this.queryResult_;
                if (queryResult2 != null) {
                    this.queryResult_ = QueryResult.newBuilder(queryResult2).mergeFrom(queryResult).buildPartial();
                } else {
                    this.queryResult_ = queryResult;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(queryResult);
            }
            return this;
        }

        public Builder clearQueryResult() {
            if (this.queryResultBuilder_ == null) {
                this.queryResult_ = null;
                onChanged();
            } else {
                this.queryResult_ = null;
                this.queryResultBuilder_ = null;
            }
            return this;
        }

        public QueryResult.Builder getQueryResultBuilder() {
            onChanged();
            return getQueryResultFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public QueryResultOrBuilder getQueryResultOrBuilder() {
            SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> singleFieldBuilderV3 = this.queryResultBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            QueryResult queryResult = this.queryResult_;
            return queryResult == null ? QueryResult.getDefaultInstance() : queryResult;
        }

        private SingleFieldBuilderV3<QueryResult, QueryResult.Builder, QueryResultOrBuilder> getQueryResultFieldBuilder() {
            if (this.queryResultBuilder_ == null) {
                this.queryResultBuilder_ = new SingleFieldBuilderV3<>(getQueryResult(), getParentForChildren(), isClean());
                this.queryResult_ = null;
            }
            return this.queryResultBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public boolean hasOriginalDetectIntentRequest() {
            return (this.originalDetectIntentRequestBuilder_ == null && this.originalDetectIntentRequest_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
            SingleFieldBuilderV3<OriginalDetectIntentRequest, OriginalDetectIntentRequest.Builder, OriginalDetectIntentRequestOrBuilder> singleFieldBuilderV3 = this.originalDetectIntentRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                OriginalDetectIntentRequest originalDetectIntentRequest = this.originalDetectIntentRequest_;
                return originalDetectIntentRequest == null ? OriginalDetectIntentRequest.getDefaultInstance() : originalDetectIntentRequest;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setOriginalDetectIntentRequest(OriginalDetectIntentRequest originalDetectIntentRequest) {
            SingleFieldBuilderV3<OriginalDetectIntentRequest, OriginalDetectIntentRequest.Builder, OriginalDetectIntentRequestOrBuilder> singleFieldBuilderV3 = this.originalDetectIntentRequestBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(originalDetectIntentRequest);
            } else {
                if (originalDetectIntentRequest == null) {
                    throw new NullPointerException();
                }
                this.originalDetectIntentRequest_ = originalDetectIntentRequest;
                onChanged();
            }
            return this;
        }

        public Builder setOriginalDetectIntentRequest(OriginalDetectIntentRequest.Builder builder) {
            SingleFieldBuilderV3<OriginalDetectIntentRequest, OriginalDetectIntentRequest.Builder, OriginalDetectIntentRequestOrBuilder> singleFieldBuilderV3 = this.originalDetectIntentRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.originalDetectIntentRequest_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeOriginalDetectIntentRequest(OriginalDetectIntentRequest originalDetectIntentRequest) {
            SingleFieldBuilderV3<OriginalDetectIntentRequest, OriginalDetectIntentRequest.Builder, OriginalDetectIntentRequestOrBuilder> singleFieldBuilderV3 = this.originalDetectIntentRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                OriginalDetectIntentRequest originalDetectIntentRequest2 = this.originalDetectIntentRequest_;
                if (originalDetectIntentRequest2 != null) {
                    this.originalDetectIntentRequest_ = OriginalDetectIntentRequest.newBuilder(originalDetectIntentRequest2).mergeFrom(originalDetectIntentRequest).buildPartial();
                } else {
                    this.originalDetectIntentRequest_ = originalDetectIntentRequest;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(originalDetectIntentRequest);
            }
            return this;
        }

        public Builder clearOriginalDetectIntentRequest() {
            if (this.originalDetectIntentRequestBuilder_ == null) {
                this.originalDetectIntentRequest_ = null;
                onChanged();
            } else {
                this.originalDetectIntentRequest_ = null;
                this.originalDetectIntentRequestBuilder_ = null;
            }
            return this;
        }

        public OriginalDetectIntentRequest.Builder getOriginalDetectIntentRequestBuilder() {
            onChanged();
            return getOriginalDetectIntentRequestFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookRequestOrBuilder
        public OriginalDetectIntentRequestOrBuilder getOriginalDetectIntentRequestOrBuilder() {
            SingleFieldBuilderV3<OriginalDetectIntentRequest, OriginalDetectIntentRequest.Builder, OriginalDetectIntentRequestOrBuilder> singleFieldBuilderV3 = this.originalDetectIntentRequestBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            OriginalDetectIntentRequest originalDetectIntentRequest = this.originalDetectIntentRequest_;
            return originalDetectIntentRequest == null ? OriginalDetectIntentRequest.getDefaultInstance() : originalDetectIntentRequest;
        }

        private SingleFieldBuilderV3<OriginalDetectIntentRequest, OriginalDetectIntentRequest.Builder, OriginalDetectIntentRequestOrBuilder> getOriginalDetectIntentRequestFieldBuilder() {
            if (this.originalDetectIntentRequestBuilder_ == null) {
                this.originalDetectIntentRequestBuilder_ = new SingleFieldBuilderV3<>(getOriginalDetectIntentRequest(), getParentForChildren(), isClean());
                this.originalDetectIntentRequest_ = null;
            }
            return this.originalDetectIntentRequestBuilder_;
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

    public static WebhookRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WebhookRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<WebhookRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public WebhookRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
