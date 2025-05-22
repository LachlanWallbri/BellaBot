package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.Context;
import com.google.cloud.dialogflow.p049v2.EventInput;
import com.google.cloud.dialogflow.p049v2.Intent;
import com.google.cloud.dialogflow.p049v2.SessionEntityType;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class WebhookResponse extends GeneratedMessageV3 implements WebhookResponseOrBuilder {
    public static final int FOLLOWUP_EVENT_INPUT_FIELD_NUMBER = 6;
    public static final int FULFILLMENT_MESSAGES_FIELD_NUMBER = 2;
    public static final int FULFILLMENT_TEXT_FIELD_NUMBER = 1;
    public static final int OUTPUT_CONTEXTS_FIELD_NUMBER = 5;
    public static final int PAYLOAD_FIELD_NUMBER = 4;
    public static final int SESSION_ENTITY_TYPES_FIELD_NUMBER = 10;
    public static final int SOURCE_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private EventInput followupEventInput_;
    private List<Intent.Message> fulfillmentMessages_;
    private volatile Object fulfillmentText_;
    private byte memoizedIsInitialized;
    private List<Context> outputContexts_;
    private Struct payload_;
    private List<SessionEntityType> sessionEntityTypes_;
    private volatile Object source_;
    private static final WebhookResponse DEFAULT_INSTANCE = new WebhookResponse();
    private static final Parser<WebhookResponse> PARSER = new AbstractParser<WebhookResponse>() { // from class: com.google.cloud.dialogflow.v2.WebhookResponse.1
        @Override // com.google.protobuf.Parser
        public WebhookResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new WebhookResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private WebhookResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private WebhookResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.fulfillmentText_ = "";
        this.fulfillmentMessages_ = Collections.emptyList();
        this.source_ = "";
        this.outputContexts_ = Collections.emptyList();
        this.sessionEntityTypes_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new WebhookResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8 */
    private WebhookResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.fulfillmentText_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            int i = (c == true ? 1 : 0) & 1;
                            c = c;
                            if (i == 0) {
                                this.fulfillmentMessages_ = new ArrayList();
                                c = (c == true ? 1 : 0) | 1;
                            }
                            this.fulfillmentMessages_.add(codedInputStream.readMessage(Intent.Message.parser(), extensionRegistryLite));
                        } else if (readTag != 26) {
                            if (readTag == 34) {
                                Struct.Builder builder = this.payload_ != null ? this.payload_.toBuilder() : null;
                                this.payload_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.payload_);
                                    this.payload_ = builder.buildPartial();
                                }
                            } else if (readTag == 42) {
                                int i2 = (c == true ? 1 : 0) & 2;
                                c = c;
                                if (i2 == 0) {
                                    this.outputContexts_ = new ArrayList();
                                    c = (c == true ? 1 : 0) | 2;
                                }
                                this.outputContexts_.add(codedInputStream.readMessage(Context.parser(), extensionRegistryLite));
                            } else if (readTag == 50) {
                                EventInput.Builder builder2 = this.followupEventInput_ != null ? this.followupEventInput_.toBuilder() : null;
                                this.followupEventInput_ = (EventInput) codedInputStream.readMessage(EventInput.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.followupEventInput_);
                                    this.followupEventInput_ = builder2.buildPartial();
                                }
                            } else if (readTag == 82) {
                                int i3 = (c == true ? 1 : 0) & 4;
                                c = c;
                                if (i3 == 0) {
                                    this.sessionEntityTypes_ = new ArrayList();
                                    c = (c == true ? 1 : 0) | 4;
                                }
                                this.sessionEntityTypes_.add(codedInputStream.readMessage(SessionEntityType.parser(), extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        } else {
                            this.source_ = codedInputStream.readStringRequireUtf8();
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (((c == true ? 1 : 0) & 1) != 0) {
                    this.fulfillmentMessages_ = Collections.unmodifiableList(this.fulfillmentMessages_);
                }
                if (((c == true ? 1 : 0) & 2) != 0) {
                    this.outputContexts_ = Collections.unmodifiableList(this.outputContexts_);
                }
                if (((c == true ? 1 : 0) & 4) != 0) {
                    this.sessionEntityTypes_ = Collections.unmodifiableList(this.sessionEntityTypes_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return WebhookProto.f1576x155a4eb1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebhookProto.f1577x9bec342f.ensureFieldAccessorsInitialized(WebhookResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public String getFulfillmentText() {
        Object obj = this.fulfillmentText_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.fulfillmentText_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public ByteString getFulfillmentTextBytes() {
        Object obj = this.fulfillmentText_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.fulfillmentText_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public List<Intent.Message> getFulfillmentMessagesList() {
        return this.fulfillmentMessages_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public List<? extends Intent.MessageOrBuilder> getFulfillmentMessagesOrBuilderList() {
        return this.fulfillmentMessages_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public int getFulfillmentMessagesCount() {
        return this.fulfillmentMessages_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public Intent.Message getFulfillmentMessages(int i) {
        return this.fulfillmentMessages_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public Intent.MessageOrBuilder getFulfillmentMessagesOrBuilder(int i) {
        return this.fulfillmentMessages_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public String getSource() {
        Object obj = this.source_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.source_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public ByteString getSourceBytes() {
        Object obj = this.source_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.source_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public boolean hasPayload() {
        return this.payload_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public Struct getPayload() {
        Struct struct = this.payload_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public StructOrBuilder getPayloadOrBuilder() {
        return getPayload();
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public List<Context> getOutputContextsList() {
        return this.outputContexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public List<? extends ContextOrBuilder> getOutputContextsOrBuilderList() {
        return this.outputContexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public int getOutputContextsCount() {
        return this.outputContexts_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public Context getOutputContexts(int i) {
        return this.outputContexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public ContextOrBuilder getOutputContextsOrBuilder(int i) {
        return this.outputContexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public boolean hasFollowupEventInput() {
        return this.followupEventInput_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public EventInput getFollowupEventInput() {
        EventInput eventInput = this.followupEventInput_;
        return eventInput == null ? EventInput.getDefaultInstance() : eventInput;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public EventInputOrBuilder getFollowupEventInputOrBuilder() {
        return getFollowupEventInput();
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public List<SessionEntityType> getSessionEntityTypesList() {
        return this.sessionEntityTypes_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public List<? extends SessionEntityTypeOrBuilder> getSessionEntityTypesOrBuilderList() {
        return this.sessionEntityTypes_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public int getSessionEntityTypesCount() {
        return this.sessionEntityTypes_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public SessionEntityType getSessionEntityTypes(int i) {
        return this.sessionEntityTypes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
    public SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i) {
        return this.sessionEntityTypes_.get(i);
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
        if (!getFulfillmentTextBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.fulfillmentText_);
        }
        for (int i = 0; i < this.fulfillmentMessages_.size(); i++) {
            codedOutputStream.writeMessage(2, this.fulfillmentMessages_.get(i));
        }
        if (!getSourceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.source_);
        }
        if (this.payload_ != null) {
            codedOutputStream.writeMessage(4, getPayload());
        }
        for (int i2 = 0; i2 < this.outputContexts_.size(); i2++) {
            codedOutputStream.writeMessage(5, this.outputContexts_.get(i2));
        }
        if (this.followupEventInput_ != null) {
            codedOutputStream.writeMessage(6, getFollowupEventInput());
        }
        for (int i3 = 0; i3 < this.sessionEntityTypes_.size(); i3++) {
            codedOutputStream.writeMessage(10, this.sessionEntityTypes_.get(i3));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getFulfillmentTextBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.fulfillmentText_) + 0 : 0;
        for (int i2 = 0; i2 < this.fulfillmentMessages_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, this.fulfillmentMessages_.get(i2));
        }
        if (!getSourceBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.source_);
        }
        if (this.payload_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, getPayload());
        }
        for (int i3 = 0; i3 < this.outputContexts_.size(); i3++) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, this.outputContexts_.get(i3));
        }
        if (this.followupEventInput_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(6, getFollowupEventInput());
        }
        for (int i4 = 0; i4 < this.sessionEntityTypes_.size(); i4++) {
            computeStringSize += CodedOutputStream.computeMessageSize(10, this.sessionEntityTypes_.get(i4));
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
        if (!(obj instanceof WebhookResponse)) {
            return super.equals(obj);
        }
        WebhookResponse webhookResponse = (WebhookResponse) obj;
        if (!getFulfillmentText().equals(webhookResponse.getFulfillmentText()) || !getFulfillmentMessagesList().equals(webhookResponse.getFulfillmentMessagesList()) || !getSource().equals(webhookResponse.getSource()) || hasPayload() != webhookResponse.hasPayload()) {
            return false;
        }
        if ((!hasPayload() || getPayload().equals(webhookResponse.getPayload())) && getOutputContextsList().equals(webhookResponse.getOutputContextsList()) && hasFollowupEventInput() == webhookResponse.hasFollowupEventInput()) {
            return (!hasFollowupEventInput() || getFollowupEventInput().equals(webhookResponse.getFollowupEventInput())) && getSessionEntityTypesList().equals(webhookResponse.getSessionEntityTypesList()) && this.unknownFields.equals(webhookResponse.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getFulfillmentText().hashCode();
        if (getFulfillmentMessagesCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getFulfillmentMessagesList().hashCode();
        }
        int hashCode2 = (((hashCode * 37) + 3) * 53) + getSource().hashCode();
        if (hasPayload()) {
            hashCode2 = (((hashCode2 * 37) + 4) * 53) + getPayload().hashCode();
        }
        if (getOutputContextsCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 5) * 53) + getOutputContextsList().hashCode();
        }
        if (hasFollowupEventInput()) {
            hashCode2 = (((hashCode2 * 37) + 6) * 53) + getFollowupEventInput().hashCode();
        }
        if (getSessionEntityTypesCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 10) * 53) + getSessionEntityTypesList().hashCode();
        }
        int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    public static WebhookResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static WebhookResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static WebhookResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static WebhookResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static WebhookResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static WebhookResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static WebhookResponse parseFrom(InputStream inputStream) throws IOException {
        return (WebhookResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static WebhookResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WebhookResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static WebhookResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WebhookResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static WebhookResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WebhookResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static WebhookResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WebhookResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static WebhookResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WebhookResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WebhookResponse webhookResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(webhookResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebhookResponseOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> followupEventInputBuilder_;
        private EventInput followupEventInput_;
        private RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> fulfillmentMessagesBuilder_;
        private List<Intent.Message> fulfillmentMessages_;
        private Object fulfillmentText_;
        private RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> outputContextsBuilder_;
        private List<Context> outputContexts_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> payloadBuilder_;
        private Struct payload_;
        private RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> sessionEntityTypesBuilder_;
        private List<SessionEntityType> sessionEntityTypes_;
        private Object source_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return WebhookProto.f1576x155a4eb1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WebhookProto.f1577x9bec342f.ensureFieldAccessorsInitialized(WebhookResponse.class, Builder.class);
        }

        private Builder() {
            this.fulfillmentText_ = "";
            this.fulfillmentMessages_ = Collections.emptyList();
            this.source_ = "";
            this.outputContexts_ = Collections.emptyList();
            this.sessionEntityTypes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.fulfillmentText_ = "";
            this.fulfillmentMessages_ = Collections.emptyList();
            this.source_ = "";
            this.outputContexts_ = Collections.emptyList();
            this.sessionEntityTypes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (WebhookResponse.alwaysUseFieldBuilders) {
                getFulfillmentMessagesFieldBuilder();
                getOutputContextsFieldBuilder();
                getSessionEntityTypesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.fulfillmentText_ = "";
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fulfillmentMessages_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.source_ = "";
            if (this.payloadBuilder_ == null) {
                this.payload_ = null;
            } else {
                this.payload_ = null;
                this.payloadBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV32 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.outputContexts_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            if (this.followupEventInputBuilder_ == null) {
                this.followupEventInput_ = null;
            } else {
                this.followupEventInput_ = null;
                this.followupEventInputBuilder_ = null;
            }
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV33 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                this.sessionEntityTypes_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV33.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return WebhookProto.f1576x155a4eb1;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public WebhookResponse getDefaultInstanceForType() {
            return WebhookResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public WebhookResponse build() {
            WebhookResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public WebhookResponse buildPartial() {
            WebhookResponse webhookResponse = new WebhookResponse(this);
            int i = this.bitField0_;
            webhookResponse.fulfillmentText_ = this.fulfillmentText_;
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                webhookResponse.fulfillmentMessages_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.fulfillmentMessages_ = Collections.unmodifiableList(this.fulfillmentMessages_);
                    this.bitField0_ &= -2;
                }
                webhookResponse.fulfillmentMessages_ = this.fulfillmentMessages_;
            }
            webhookResponse.source_ = this.source_;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                webhookResponse.payload_ = this.payload_;
            } else {
                webhookResponse.payload_ = singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV32 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV32 != null) {
                webhookResponse.outputContexts_ = repeatedFieldBuilderV32.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.outputContexts_ = Collections.unmodifiableList(this.outputContexts_);
                    this.bitField0_ &= -3;
                }
                webhookResponse.outputContexts_ = this.outputContexts_;
            }
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV32 = this.followupEventInputBuilder_;
            if (singleFieldBuilderV32 == null) {
                webhookResponse.followupEventInput_ = this.followupEventInput_;
            } else {
                webhookResponse.followupEventInput_ = singleFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV33 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV33 != null) {
                webhookResponse.sessionEntityTypes_ = repeatedFieldBuilderV33.build();
            } else {
                if ((this.bitField0_ & 4) != 0) {
                    this.sessionEntityTypes_ = Collections.unmodifiableList(this.sessionEntityTypes_);
                    this.bitField0_ &= -5;
                }
                webhookResponse.sessionEntityTypes_ = this.sessionEntityTypes_;
            }
            onBuilt();
            return webhookResponse;
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
            if (message instanceof WebhookResponse) {
                return mergeFrom((WebhookResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(WebhookResponse webhookResponse) {
            if (webhookResponse == WebhookResponse.getDefaultInstance()) {
                return this;
            }
            if (!webhookResponse.getFulfillmentText().isEmpty()) {
                this.fulfillmentText_ = webhookResponse.fulfillmentText_;
                onChanged();
            }
            if (this.fulfillmentMessagesBuilder_ == null) {
                if (!webhookResponse.fulfillmentMessages_.isEmpty()) {
                    if (this.fulfillmentMessages_.isEmpty()) {
                        this.fulfillmentMessages_ = webhookResponse.fulfillmentMessages_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFulfillmentMessagesIsMutable();
                        this.fulfillmentMessages_.addAll(webhookResponse.fulfillmentMessages_);
                    }
                    onChanged();
                }
            } else if (!webhookResponse.fulfillmentMessages_.isEmpty()) {
                if (!this.fulfillmentMessagesBuilder_.isEmpty()) {
                    this.fulfillmentMessagesBuilder_.addAllMessages(webhookResponse.fulfillmentMessages_);
                } else {
                    this.fulfillmentMessagesBuilder_.dispose();
                    this.fulfillmentMessagesBuilder_ = null;
                    this.fulfillmentMessages_ = webhookResponse.fulfillmentMessages_;
                    this.bitField0_ &= -2;
                    this.fulfillmentMessagesBuilder_ = WebhookResponse.alwaysUseFieldBuilders ? getFulfillmentMessagesFieldBuilder() : null;
                }
            }
            if (!webhookResponse.getSource().isEmpty()) {
                this.source_ = webhookResponse.source_;
                onChanged();
            }
            if (webhookResponse.hasPayload()) {
                mergePayload(webhookResponse.getPayload());
            }
            if (this.outputContextsBuilder_ == null) {
                if (!webhookResponse.outputContexts_.isEmpty()) {
                    if (this.outputContexts_.isEmpty()) {
                        this.outputContexts_ = webhookResponse.outputContexts_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureOutputContextsIsMutable();
                        this.outputContexts_.addAll(webhookResponse.outputContexts_);
                    }
                    onChanged();
                }
            } else if (!webhookResponse.outputContexts_.isEmpty()) {
                if (!this.outputContextsBuilder_.isEmpty()) {
                    this.outputContextsBuilder_.addAllMessages(webhookResponse.outputContexts_);
                } else {
                    this.outputContextsBuilder_.dispose();
                    this.outputContextsBuilder_ = null;
                    this.outputContexts_ = webhookResponse.outputContexts_;
                    this.bitField0_ &= -3;
                    this.outputContextsBuilder_ = WebhookResponse.alwaysUseFieldBuilders ? getOutputContextsFieldBuilder() : null;
                }
            }
            if (webhookResponse.hasFollowupEventInput()) {
                mergeFollowupEventInput(webhookResponse.getFollowupEventInput());
            }
            if (this.sessionEntityTypesBuilder_ == null) {
                if (!webhookResponse.sessionEntityTypes_.isEmpty()) {
                    if (this.sessionEntityTypes_.isEmpty()) {
                        this.sessionEntityTypes_ = webhookResponse.sessionEntityTypes_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureSessionEntityTypesIsMutable();
                        this.sessionEntityTypes_.addAll(webhookResponse.sessionEntityTypes_);
                    }
                    onChanged();
                }
            } else if (!webhookResponse.sessionEntityTypes_.isEmpty()) {
                if (!this.sessionEntityTypesBuilder_.isEmpty()) {
                    this.sessionEntityTypesBuilder_.addAllMessages(webhookResponse.sessionEntityTypes_);
                } else {
                    this.sessionEntityTypesBuilder_.dispose();
                    this.sessionEntityTypesBuilder_ = null;
                    this.sessionEntityTypes_ = webhookResponse.sessionEntityTypes_;
                    this.bitField0_ &= -5;
                    this.sessionEntityTypesBuilder_ = WebhookResponse.alwaysUseFieldBuilders ? getSessionEntityTypesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(webhookResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            WebhookResponse webhookResponse = null;
            try {
                try {
                    WebhookResponse webhookResponse2 = (WebhookResponse) WebhookResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (webhookResponse2 != null) {
                        mergeFrom(webhookResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    WebhookResponse webhookResponse3 = (WebhookResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        webhookResponse = webhookResponse3;
                        if (webhookResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (webhookResponse != null) {
                    mergeFrom(webhookResponse);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public String getFulfillmentText() {
            Object obj = this.fulfillmentText_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.fulfillmentText_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public ByteString getFulfillmentTextBytes() {
            Object obj = this.fulfillmentText_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.fulfillmentText_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setFulfillmentText(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.fulfillmentText_ = str;
            onChanged();
            return this;
        }

        public Builder clearFulfillmentText() {
            this.fulfillmentText_ = WebhookResponse.getDefaultInstance().getFulfillmentText();
            onChanged();
            return this;
        }

        public Builder setFulfillmentTextBytes(ByteString byteString) {
            if (byteString != null) {
                WebhookResponse.checkByteStringIsUtf8(byteString);
                this.fulfillmentText_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureFulfillmentMessagesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.fulfillmentMessages_ = new ArrayList(this.fulfillmentMessages_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public List<Intent.Message> getFulfillmentMessagesList() {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.fulfillmentMessages_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public int getFulfillmentMessagesCount() {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fulfillmentMessages_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public Intent.Message getFulfillmentMessages(int i) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fulfillmentMessages_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setFulfillmentMessages(int i, Intent.Message message) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, message);
            } else {
                if (message == null) {
                    throw new NullPointerException();
                }
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.set(i, message);
                onChanged();
            }
            return this;
        }

        public Builder setFulfillmentMessages(int i, Intent.Message.Builder builder) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addFulfillmentMessages(Intent.Message message) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(message);
            } else {
                if (message == null) {
                    throw new NullPointerException();
                }
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.add(message);
                onChanged();
            }
            return this;
        }

        public Builder addFulfillmentMessages(int i, Intent.Message message) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, message);
            } else {
                if (message == null) {
                    throw new NullPointerException();
                }
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.add(i, message);
                onChanged();
            }
            return this;
        }

        public Builder addFulfillmentMessages(Intent.Message.Builder builder) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addFulfillmentMessages(int i, Intent.Message.Builder builder) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllFulfillmentMessages(Iterable<? extends Intent.Message> iterable) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.fulfillmentMessages_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearFulfillmentMessages() {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fulfillmentMessages_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeFulfillmentMessages(int i) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFulfillmentMessagesIsMutable();
                this.fulfillmentMessages_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Intent.Message.Builder getFulfillmentMessagesBuilder(int i) {
            return getFulfillmentMessagesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public Intent.MessageOrBuilder getFulfillmentMessagesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fulfillmentMessages_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public List<? extends Intent.MessageOrBuilder> getFulfillmentMessagesOrBuilderList() {
            RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> repeatedFieldBuilderV3 = this.fulfillmentMessagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.fulfillmentMessages_);
        }

        public Intent.Message.Builder addFulfillmentMessagesBuilder() {
            return getFulfillmentMessagesFieldBuilder().addBuilder(Intent.Message.getDefaultInstance());
        }

        public Intent.Message.Builder addFulfillmentMessagesBuilder(int i) {
            return getFulfillmentMessagesFieldBuilder().addBuilder(i, Intent.Message.getDefaultInstance());
        }

        public List<Intent.Message.Builder> getFulfillmentMessagesBuilderList() {
            return getFulfillmentMessagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Intent.Message, Intent.Message.Builder, Intent.MessageOrBuilder> getFulfillmentMessagesFieldBuilder() {
            if (this.fulfillmentMessagesBuilder_ == null) {
                this.fulfillmentMessagesBuilder_ = new RepeatedFieldBuilderV3<>(this.fulfillmentMessages_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.fulfillmentMessages_ = null;
            }
            return this.fulfillmentMessagesBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public String getSource() {
            Object obj = this.source_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.source_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public ByteString getSourceBytes() {
            Object obj = this.source_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.source_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSource(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.source_ = str;
            onChanged();
            return this;
        }

        public Builder clearSource() {
            this.source_ = WebhookResponse.getDefaultInstance().getSource();
            onChanged();
            return this;
        }

        public Builder setSourceBytes(ByteString byteString) {
            if (byteString != null) {
                WebhookResponse.checkByteStringIsUtf8(byteString);
                this.source_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public boolean hasPayload() {
            return (this.payloadBuilder_ == null && this.payload_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
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

        private void ensureOutputContextsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.outputContexts_ = new ArrayList(this.outputContexts_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public List<Context> getOutputContextsList() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.outputContexts_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public int getOutputContextsCount() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.outputContexts_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public Context getOutputContexts(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.outputContexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setOutputContexts(int i, Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureOutputContextsIsMutable();
                this.outputContexts_.set(i, context);
                onChanged();
            }
            return this;
        }

        public Builder setOutputContexts(int i, Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                this.outputContexts_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addOutputContexts(Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureOutputContextsIsMutable();
                this.outputContexts_.add(context);
                onChanged();
            }
            return this;
        }

        public Builder addOutputContexts(int i, Context context) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                ensureOutputContextsIsMutable();
                this.outputContexts_.add(i, context);
                onChanged();
            }
            return this;
        }

        public Builder addOutputContexts(Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                this.outputContexts_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addOutputContexts(int i, Context.Builder builder) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                this.outputContexts_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllOutputContexts(Iterable<? extends Context> iterable) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.outputContexts_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearOutputContexts() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.outputContexts_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeOutputContexts(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOutputContextsIsMutable();
                this.outputContexts_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Context.Builder getOutputContextsBuilder(int i) {
            return getOutputContextsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public ContextOrBuilder getOutputContextsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.outputContexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public List<? extends ContextOrBuilder> getOutputContextsOrBuilderList() {
            RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> repeatedFieldBuilderV3 = this.outputContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.outputContexts_);
        }

        public Context.Builder addOutputContextsBuilder() {
            return getOutputContextsFieldBuilder().addBuilder(Context.getDefaultInstance());
        }

        public Context.Builder addOutputContextsBuilder(int i) {
            return getOutputContextsFieldBuilder().addBuilder(i, Context.getDefaultInstance());
        }

        public List<Context.Builder> getOutputContextsBuilderList() {
            return getOutputContextsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> getOutputContextsFieldBuilder() {
            if (this.outputContextsBuilder_ == null) {
                this.outputContextsBuilder_ = new RepeatedFieldBuilderV3<>(this.outputContexts_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.outputContexts_ = null;
            }
            return this.outputContextsBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public boolean hasFollowupEventInput() {
            return (this.followupEventInputBuilder_ == null && this.followupEventInput_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public EventInput getFollowupEventInput() {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.followupEventInputBuilder_;
            if (singleFieldBuilderV3 == null) {
                EventInput eventInput = this.followupEventInput_;
                return eventInput == null ? EventInput.getDefaultInstance() : eventInput;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setFollowupEventInput(EventInput eventInput) {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.followupEventInputBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(eventInput);
            } else {
                if (eventInput == null) {
                    throw new NullPointerException();
                }
                this.followupEventInput_ = eventInput;
                onChanged();
            }
            return this;
        }

        public Builder setFollowupEventInput(EventInput.Builder builder) {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.followupEventInputBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.followupEventInput_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeFollowupEventInput(EventInput eventInput) {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.followupEventInputBuilder_;
            if (singleFieldBuilderV3 == null) {
                EventInput eventInput2 = this.followupEventInput_;
                if (eventInput2 != null) {
                    this.followupEventInput_ = EventInput.newBuilder(eventInput2).mergeFrom(eventInput).buildPartial();
                } else {
                    this.followupEventInput_ = eventInput;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(eventInput);
            }
            return this;
        }

        public Builder clearFollowupEventInput() {
            if (this.followupEventInputBuilder_ == null) {
                this.followupEventInput_ = null;
                onChanged();
            } else {
                this.followupEventInput_ = null;
                this.followupEventInputBuilder_ = null;
            }
            return this;
        }

        public EventInput.Builder getFollowupEventInputBuilder() {
            onChanged();
            return getFollowupEventInputFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public EventInputOrBuilder getFollowupEventInputOrBuilder() {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.followupEventInputBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            EventInput eventInput = this.followupEventInput_;
            return eventInput == null ? EventInput.getDefaultInstance() : eventInput;
        }

        private SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> getFollowupEventInputFieldBuilder() {
            if (this.followupEventInputBuilder_ == null) {
                this.followupEventInputBuilder_ = new SingleFieldBuilderV3<>(getFollowupEventInput(), getParentForChildren(), isClean());
                this.followupEventInput_ = null;
            }
            return this.followupEventInputBuilder_;
        }

        private void ensureSessionEntityTypesIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.sessionEntityTypes_ = new ArrayList(this.sessionEntityTypes_);
                this.bitField0_ |= 4;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public List<SessionEntityType> getSessionEntityTypesList() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.sessionEntityTypes_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public int getSessionEntityTypesCount() {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sessionEntityTypes_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
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
                this.bitField0_ &= -5;
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

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
        public SessionEntityTypeOrBuilder getSessionEntityTypesOrBuilder(int i) {
            RepeatedFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> repeatedFieldBuilderV3 = this.sessionEntityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sessionEntityTypes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.WebhookResponseOrBuilder
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
                this.sessionEntityTypesBuilder_ = new RepeatedFieldBuilderV3<>(this.sessionEntityTypes_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.sessionEntityTypes_ = null;
            }
            return this.sessionEntityTypesBuilder_;
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

    public static WebhookResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WebhookResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<WebhookResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public WebhookResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
