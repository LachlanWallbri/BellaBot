package com.google.cloud.audit;

import com.google.cloud.audit.AuthenticationInfo;
import com.google.cloud.audit.AuthorizationInfo;
import com.google.cloud.audit.RequestMetadata;
import com.google.cloud.audit.ResourceLocation;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class AuditLog extends GeneratedMessageV3 implements AuditLogOrBuilder {
    public static final int AUTHENTICATION_INFO_FIELD_NUMBER = 3;
    public static final int AUTHORIZATION_INFO_FIELD_NUMBER = 9;
    public static final int METADATA_FIELD_NUMBER = 18;
    public static final int METHOD_NAME_FIELD_NUMBER = 8;
    public static final int NUM_RESPONSE_ITEMS_FIELD_NUMBER = 12;
    public static final int REQUEST_FIELD_NUMBER = 16;
    public static final int REQUEST_METADATA_FIELD_NUMBER = 4;
    public static final int RESOURCE_LOCATION_FIELD_NUMBER = 20;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 11;
    public static final int RESOURCE_ORIGINAL_STATE_FIELD_NUMBER = 19;
    public static final int RESPONSE_FIELD_NUMBER = 17;
    public static final int SERVICE_DATA_FIELD_NUMBER = 15;
    public static final int SERVICE_NAME_FIELD_NUMBER = 7;
    public static final int STATUS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private AuthenticationInfo authenticationInfo_;
    private List<AuthorizationInfo> authorizationInfo_;
    private byte memoizedIsInitialized;
    private Struct metadata_;
    private volatile Object methodName_;
    private long numResponseItems_;
    private RequestMetadata requestMetadata_;
    private Struct request_;
    private ResourceLocation resourceLocation_;
    private volatile Object resourceName_;
    private Struct resourceOriginalState_;
    private Struct response_;
    private Any serviceData_;
    private volatile Object serviceName_;
    private Status status_;
    private static final AuditLog DEFAULT_INSTANCE = new AuditLog();
    private static final Parser<AuditLog> PARSER = new AbstractParser<AuditLog>() { // from class: com.google.cloud.audit.AuditLog.1
        @Override // com.google.protobuf.Parser
        public AuditLog parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuditLog(codedInputStream, extensionRegistryLite);
        }
    };

    private AuditLog(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AuditLog() {
        this.memoizedIsInitialized = (byte) -1;
        this.serviceName_ = "";
        this.methodName_ = "";
        this.resourceName_ = "";
        this.authorizationInfo_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new AuditLog();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0013. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    private AuditLog(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            Status.Builder builder = this.status_ != null ? this.status_.toBuilder() : null;
                            this.status_ = (Status) codedInputStream.readMessage(Status.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.status_);
                                this.status_ = builder.buildPartial();
                            }
                        case 26:
                            AuthenticationInfo.Builder builder2 = this.authenticationInfo_ != null ? this.authenticationInfo_.toBuilder() : null;
                            this.authenticationInfo_ = (AuthenticationInfo) codedInputStream.readMessage(AuthenticationInfo.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.authenticationInfo_);
                                this.authenticationInfo_ = builder2.buildPartial();
                            }
                        case 34:
                            RequestMetadata.Builder builder3 = this.requestMetadata_ != null ? this.requestMetadata_.toBuilder() : null;
                            this.requestMetadata_ = (RequestMetadata) codedInputStream.readMessage(RequestMetadata.parser(), extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom(this.requestMetadata_);
                                this.requestMetadata_ = builder3.buildPartial();
                            }
                        case 58:
                            this.serviceName_ = codedInputStream.readStringRequireUtf8();
                        case 66:
                            this.methodName_ = codedInputStream.readStringRequireUtf8();
                        case 74:
                            boolean z3 = (z2 ? 1 : 0) & true;
                            z2 = z2;
                            if (!z3) {
                                this.authorizationInfo_ = new ArrayList();
                                z2 = (z2 ? 1 : 0) | true;
                            }
                            this.authorizationInfo_.add(codedInputStream.readMessage(AuthorizationInfo.parser(), extensionRegistryLite));
                        case 90:
                            this.resourceName_ = codedInputStream.readStringRequireUtf8();
                        case 96:
                            this.numResponseItems_ = codedInputStream.readInt64();
                        case 122:
                            Any.Builder builder4 = this.serviceData_ != null ? this.serviceData_.toBuilder() : null;
                            this.serviceData_ = (Any) codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                            if (builder4 != null) {
                                builder4.mergeFrom(this.serviceData_);
                                this.serviceData_ = builder4.buildPartial();
                            }
                        case 130:
                            Struct.Builder builder5 = this.request_ != null ? this.request_.toBuilder() : null;
                            this.request_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder5 != null) {
                                builder5.mergeFrom(this.request_);
                                this.request_ = builder5.buildPartial();
                            }
                        case 138:
                            Struct.Builder builder6 = this.response_ != null ? this.response_.toBuilder() : null;
                            this.response_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder6 != null) {
                                builder6.mergeFrom(this.response_);
                                this.response_ = builder6.buildPartial();
                            }
                        case 146:
                            Struct.Builder builder7 = this.metadata_ != null ? this.metadata_.toBuilder() : null;
                            this.metadata_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder7 != null) {
                                builder7.mergeFrom(this.metadata_);
                                this.metadata_ = builder7.buildPartial();
                            }
                        case 154:
                            Struct.Builder builder8 = this.resourceOriginalState_ != null ? this.resourceOriginalState_.toBuilder() : null;
                            this.resourceOriginalState_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder8 != null) {
                                builder8.mergeFrom(this.resourceOriginalState_);
                                this.resourceOriginalState_ = builder8.buildPartial();
                            }
                        case 162:
                            ResourceLocation.Builder builder9 = this.resourceLocation_ != null ? this.resourceLocation_.toBuilder() : null;
                            this.resourceLocation_ = (ResourceLocation) codedInputStream.readMessage(ResourceLocation.parser(), extensionRegistryLite);
                            if (builder9 != null) {
                                builder9.mergeFrom(this.resourceLocation_);
                                this.resourceLocation_ = builder9.buildPartial();
                            }
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
                if ((z2 ? 1 : 0) & true) {
                    this.authorizationInfo_ = Collections.unmodifiableList(this.authorizationInfo_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.internal_static_google_cloud_audit_AuditLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditLog.class, Builder.class);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getServiceName() {
        Object obj = this.serviceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.serviceName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getServiceNameBytes() {
        Object obj = this.serviceName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.serviceName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getMethodName() {
        Object obj = this.methodName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.methodName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getMethodNameBytes() {
        Object obj = this.methodName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.methodName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getResourceName() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getResourceNameBytes() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasResourceLocation() {
        return this.resourceLocation_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ResourceLocation getResourceLocation() {
        ResourceLocation resourceLocation = this.resourceLocation_;
        return resourceLocation == null ? ResourceLocation.getDefaultInstance() : resourceLocation;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ResourceLocationOrBuilder getResourceLocationOrBuilder() {
        return getResourceLocation();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasResourceOriginalState() {
        return this.resourceOriginalState_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getResourceOriginalState() {
        Struct struct = this.resourceOriginalState_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public StructOrBuilder getResourceOriginalStateOrBuilder() {
        return getResourceOriginalState();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public long getNumResponseItems() {
        return this.numResponseItems_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasStatus() {
        return this.status_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Status getStatus() {
        Status status = this.status_;
        return status == null ? Status.getDefaultInstance() : status;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public StatusOrBuilder getStatusOrBuilder() {
        return getStatus();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasAuthenticationInfo() {
        return this.authenticationInfo_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthenticationInfo getAuthenticationInfo() {
        AuthenticationInfo authenticationInfo = this.authenticationInfo_;
        return authenticationInfo == null ? AuthenticationInfo.getDefaultInstance() : authenticationInfo;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder() {
        return getAuthenticationInfo();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public List<AuthorizationInfo> getAuthorizationInfoList() {
        return this.authorizationInfo_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
        return this.authorizationInfo_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public int getAuthorizationInfoCount() {
        return this.authorizationInfo_.size();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthorizationInfo getAuthorizationInfo(int i) {
        return this.authorizationInfo_.get(i);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int i) {
        return this.authorizationInfo_.get(i);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasRequestMetadata() {
        return this.requestMetadata_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public RequestMetadata getRequestMetadata() {
        RequestMetadata requestMetadata = this.requestMetadata_;
        return requestMetadata == null ? RequestMetadata.getDefaultInstance() : requestMetadata;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public RequestMetadataOrBuilder getRequestMetadataOrBuilder() {
        return getRequestMetadata();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasRequest() {
        return this.request_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getRequest() {
        Struct struct = this.request_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public StructOrBuilder getRequestOrBuilder() {
        return getRequest();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasResponse() {
        return this.response_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getResponse() {
        Struct struct = this.response_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public StructOrBuilder getResponseOrBuilder() {
        return getResponse();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getMetadata() {
        Struct struct = this.metadata_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public StructOrBuilder getMetadataOrBuilder() {
        return getMetadata();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasServiceData() {
        return this.serviceData_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Any getServiceData() {
        Any any = this.serviceData_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AnyOrBuilder getServiceDataOrBuilder() {
        return getServiceData();
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
        if (this.status_ != null) {
            codedOutputStream.writeMessage(2, getStatus());
        }
        if (this.authenticationInfo_ != null) {
            codedOutputStream.writeMessage(3, getAuthenticationInfo());
        }
        if (this.requestMetadata_ != null) {
            codedOutputStream.writeMessage(4, getRequestMetadata());
        }
        if (!getServiceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.serviceName_);
        }
        if (!getMethodNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.methodName_);
        }
        for (int i = 0; i < this.authorizationInfo_.size(); i++) {
            codedOutputStream.writeMessage(9, this.authorizationInfo_.get(i));
        }
        if (!getResourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.resourceName_);
        }
        long j = this.numResponseItems_;
        if (j != 0) {
            codedOutputStream.writeInt64(12, j);
        }
        if (this.serviceData_ != null) {
            codedOutputStream.writeMessage(15, getServiceData());
        }
        if (this.request_ != null) {
            codedOutputStream.writeMessage(16, getRequest());
        }
        if (this.response_ != null) {
            codedOutputStream.writeMessage(17, getResponse());
        }
        if (this.metadata_ != null) {
            codedOutputStream.writeMessage(18, getMetadata());
        }
        if (this.resourceOriginalState_ != null) {
            codedOutputStream.writeMessage(19, getResourceOriginalState());
        }
        if (this.resourceLocation_ != null) {
            codedOutputStream.writeMessage(20, getResourceLocation());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.status_ != null ? CodedOutputStream.computeMessageSize(2, getStatus()) + 0 : 0;
        if (this.authenticationInfo_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(3, getAuthenticationInfo());
        }
        if (this.requestMetadata_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(4, getRequestMetadata());
        }
        if (!getServiceNameBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(7, this.serviceName_);
        }
        if (!getMethodNameBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(8, this.methodName_);
        }
        for (int i2 = 0; i2 < this.authorizationInfo_.size(); i2++) {
            computeMessageSize += CodedOutputStream.computeMessageSize(9, this.authorizationInfo_.get(i2));
        }
        if (!getResourceNameBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(11, this.resourceName_);
        }
        long j = this.numResponseItems_;
        if (j != 0) {
            computeMessageSize += CodedOutputStream.computeInt64Size(12, j);
        }
        if (this.serviceData_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(15, getServiceData());
        }
        if (this.request_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(16, getRequest());
        }
        if (this.response_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(17, getResponse());
        }
        if (this.metadata_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(18, getMetadata());
        }
        if (this.resourceOriginalState_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(19, getResourceOriginalState());
        }
        if (this.resourceLocation_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(20, getResourceLocation());
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
        if (!(obj instanceof AuditLog)) {
            return super.equals(obj);
        }
        AuditLog auditLog = (AuditLog) obj;
        if (!getServiceName().equals(auditLog.getServiceName()) || !getMethodName().equals(auditLog.getMethodName()) || !getResourceName().equals(auditLog.getResourceName()) || hasResourceLocation() != auditLog.hasResourceLocation()) {
            return false;
        }
        if ((hasResourceLocation() && !getResourceLocation().equals(auditLog.getResourceLocation())) || hasResourceOriginalState() != auditLog.hasResourceOriginalState()) {
            return false;
        }
        if ((hasResourceOriginalState() && !getResourceOriginalState().equals(auditLog.getResourceOriginalState())) || getNumResponseItems() != auditLog.getNumResponseItems() || hasStatus() != auditLog.hasStatus()) {
            return false;
        }
        if ((hasStatus() && !getStatus().equals(auditLog.getStatus())) || hasAuthenticationInfo() != auditLog.hasAuthenticationInfo()) {
            return false;
        }
        if ((hasAuthenticationInfo() && !getAuthenticationInfo().equals(auditLog.getAuthenticationInfo())) || !getAuthorizationInfoList().equals(auditLog.getAuthorizationInfoList()) || hasRequestMetadata() != auditLog.hasRequestMetadata()) {
            return false;
        }
        if ((hasRequestMetadata() && !getRequestMetadata().equals(auditLog.getRequestMetadata())) || hasRequest() != auditLog.hasRequest()) {
            return false;
        }
        if ((hasRequest() && !getRequest().equals(auditLog.getRequest())) || hasResponse() != auditLog.hasResponse()) {
            return false;
        }
        if ((hasResponse() && !getResponse().equals(auditLog.getResponse())) || hasMetadata() != auditLog.hasMetadata()) {
            return false;
        }
        if ((!hasMetadata() || getMetadata().equals(auditLog.getMetadata())) && hasServiceData() == auditLog.hasServiceData()) {
            return (!hasServiceData() || getServiceData().equals(auditLog.getServiceData())) && this.unknownFields.equals(auditLog.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 7) * 53) + getServiceName().hashCode()) * 37) + 8) * 53) + getMethodName().hashCode()) * 37) + 11) * 53) + getResourceName().hashCode();
        if (hasResourceLocation()) {
            hashCode = (((hashCode * 37) + 20) * 53) + getResourceLocation().hashCode();
        }
        if (hasResourceOriginalState()) {
            hashCode = (((hashCode * 37) + 19) * 53) + getResourceOriginalState().hashCode();
        }
        int hashLong = (((hashCode * 37) + 12) * 53) + Internal.hashLong(getNumResponseItems());
        if (hasStatus()) {
            hashLong = (((hashLong * 37) + 2) * 53) + getStatus().hashCode();
        }
        if (hasAuthenticationInfo()) {
            hashLong = (((hashLong * 37) + 3) * 53) + getAuthenticationInfo().hashCode();
        }
        if (getAuthorizationInfoCount() > 0) {
            hashLong = (((hashLong * 37) + 9) * 53) + getAuthorizationInfoList().hashCode();
        }
        if (hasRequestMetadata()) {
            hashLong = (((hashLong * 37) + 4) * 53) + getRequestMetadata().hashCode();
        }
        if (hasRequest()) {
            hashLong = (((hashLong * 37) + 16) * 53) + getRequest().hashCode();
        }
        if (hasResponse()) {
            hashLong = (((hashLong * 37) + 17) * 53) + getResponse().hashCode();
        }
        if (hasMetadata()) {
            hashLong = (((hashLong * 37) + 18) * 53) + getMetadata().hashCode();
        }
        if (hasServiceData()) {
            hashLong = (((hashLong * 37) + 15) * 53) + getServiceData().hashCode();
        }
        int hashCode2 = (hashLong * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static AuditLog parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static AuditLog parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuditLog parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static AuditLog parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuditLog parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static AuditLog parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuditLog parseFrom(InputStream inputStream) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuditLog parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuditLog parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuditLog parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuditLog parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuditLog parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuditLog auditLog) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(auditLog);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuditLogOrBuilder {
        private SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> authenticationInfoBuilder_;
        private AuthenticationInfo authenticationInfo_;
        private RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> authorizationInfoBuilder_;
        private List<AuthorizationInfo> authorizationInfo_;
        private int bitField0_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> metadataBuilder_;
        private Struct metadata_;
        private Object methodName_;
        private long numResponseItems_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> requestBuilder_;
        private SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> requestMetadataBuilder_;
        private RequestMetadata requestMetadata_;
        private Struct request_;
        private SingleFieldBuilderV3<ResourceLocation, ResourceLocation.Builder, ResourceLocationOrBuilder> resourceLocationBuilder_;
        private ResourceLocation resourceLocation_;
        private Object resourceName_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> resourceOriginalStateBuilder_;
        private Struct resourceOriginalState_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> responseBuilder_;
        private Struct response_;
        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> serviceDataBuilder_;
        private Any serviceData_;
        private Object serviceName_;
        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> statusBuilder_;
        private Status status_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditLog.class, Builder.class);
        }

        private Builder() {
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.authorizationInfo_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.authorizationInfo_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (AuditLog.alwaysUseFieldBuilders) {
                getAuthorizationInfoFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            if (this.resourceLocationBuilder_ == null) {
                this.resourceLocation_ = null;
            } else {
                this.resourceLocation_ = null;
                this.resourceLocationBuilder_ = null;
            }
            if (this.resourceOriginalStateBuilder_ == null) {
                this.resourceOriginalState_ = null;
            } else {
                this.resourceOriginalState_ = null;
                this.resourceOriginalStateBuilder_ = null;
            }
            this.numResponseItems_ = 0L;
            if (this.statusBuilder_ == null) {
                this.status_ = null;
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = null;
            } else {
                this.authenticationInfo_ = null;
                this.authenticationInfoBuilder_ = null;
            }
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.authorizationInfo_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = null;
            } else {
                this.requestMetadata_ = null;
                this.requestMetadataBuilder_ = null;
            }
            if (this.requestBuilder_ == null) {
                this.request_ = null;
            } else {
                this.request_ = null;
                this.requestBuilder_ = null;
            }
            if (this.responseBuilder_ == null) {
                this.response_ = null;
            } else {
                this.response_ = null;
                this.responseBuilder_ = null;
            }
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = null;
            } else {
                this.serviceData_ = null;
                this.serviceDataBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public AuditLog getDefaultInstanceForType() {
            return AuditLog.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AuditLog build() {
            AuditLog buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AuditLog buildPartial() {
            AuditLog auditLog = new AuditLog(this);
            int i = this.bitField0_;
            auditLog.serviceName_ = this.serviceName_;
            auditLog.methodName_ = this.methodName_;
            auditLog.resourceName_ = this.resourceName_;
            SingleFieldBuilderV3<ResourceLocation, ResourceLocation.Builder, ResourceLocationOrBuilder> singleFieldBuilderV3 = this.resourceLocationBuilder_;
            if (singleFieldBuilderV3 == null) {
                auditLog.resourceLocation_ = this.resourceLocation_;
            } else {
                auditLog.resourceLocation_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV32 = this.resourceOriginalStateBuilder_;
            if (singleFieldBuilderV32 == null) {
                auditLog.resourceOriginalState_ = this.resourceOriginalState_;
            } else {
                auditLog.resourceOriginalState_ = singleFieldBuilderV32.build();
            }
            auditLog.numResponseItems_ = this.numResponseItems_;
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV33 = this.statusBuilder_;
            if (singleFieldBuilderV33 == null) {
                auditLog.status_ = this.status_;
            } else {
                auditLog.status_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV34 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV34 == null) {
                auditLog.authenticationInfo_ = this.authenticationInfo_;
            } else {
                auditLog.authenticationInfo_ = singleFieldBuilderV34.build();
            }
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                auditLog.authorizationInfo_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.authorizationInfo_ = Collections.unmodifiableList(this.authorizationInfo_);
                    this.bitField0_ &= -2;
                }
                auditLog.authorizationInfo_ = this.authorizationInfo_;
            }
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV35 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV35 == null) {
                auditLog.requestMetadata_ = this.requestMetadata_;
            } else {
                auditLog.requestMetadata_ = singleFieldBuilderV35.build();
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV36 = this.requestBuilder_;
            if (singleFieldBuilderV36 == null) {
                auditLog.request_ = this.request_;
            } else {
                auditLog.request_ = singleFieldBuilderV36.build();
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV37 = this.responseBuilder_;
            if (singleFieldBuilderV37 == null) {
                auditLog.response_ = this.response_;
            } else {
                auditLog.response_ = singleFieldBuilderV37.build();
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV38 = this.metadataBuilder_;
            if (singleFieldBuilderV38 == null) {
                auditLog.metadata_ = this.metadata_;
            } else {
                auditLog.metadata_ = singleFieldBuilderV38.build();
            }
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV39 = this.serviceDataBuilder_;
            if (singleFieldBuilderV39 == null) {
                auditLog.serviceData_ = this.serviceData_;
            } else {
                auditLog.serviceData_ = singleFieldBuilderV39.build();
            }
            onBuilt();
            return auditLog;
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
            if (message instanceof AuditLog) {
                return mergeFrom((AuditLog) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuditLog auditLog) {
            if (auditLog == AuditLog.getDefaultInstance()) {
                return this;
            }
            if (!auditLog.getServiceName().isEmpty()) {
                this.serviceName_ = auditLog.serviceName_;
                onChanged();
            }
            if (!auditLog.getMethodName().isEmpty()) {
                this.methodName_ = auditLog.methodName_;
                onChanged();
            }
            if (!auditLog.getResourceName().isEmpty()) {
                this.resourceName_ = auditLog.resourceName_;
                onChanged();
            }
            if (auditLog.hasResourceLocation()) {
                mergeResourceLocation(auditLog.getResourceLocation());
            }
            if (auditLog.hasResourceOriginalState()) {
                mergeResourceOriginalState(auditLog.getResourceOriginalState());
            }
            if (auditLog.getNumResponseItems() != 0) {
                setNumResponseItems(auditLog.getNumResponseItems());
            }
            if (auditLog.hasStatus()) {
                mergeStatus(auditLog.getStatus());
            }
            if (auditLog.hasAuthenticationInfo()) {
                mergeAuthenticationInfo(auditLog.getAuthenticationInfo());
            }
            if (this.authorizationInfoBuilder_ == null) {
                if (!auditLog.authorizationInfo_.isEmpty()) {
                    if (this.authorizationInfo_.isEmpty()) {
                        this.authorizationInfo_ = auditLog.authorizationInfo_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureAuthorizationInfoIsMutable();
                        this.authorizationInfo_.addAll(auditLog.authorizationInfo_);
                    }
                    onChanged();
                }
            } else if (!auditLog.authorizationInfo_.isEmpty()) {
                if (!this.authorizationInfoBuilder_.isEmpty()) {
                    this.authorizationInfoBuilder_.addAllMessages(auditLog.authorizationInfo_);
                } else {
                    this.authorizationInfoBuilder_.dispose();
                    this.authorizationInfoBuilder_ = null;
                    this.authorizationInfo_ = auditLog.authorizationInfo_;
                    this.bitField0_ &= -2;
                    this.authorizationInfoBuilder_ = AuditLog.alwaysUseFieldBuilders ? getAuthorizationInfoFieldBuilder() : null;
                }
            }
            if (auditLog.hasRequestMetadata()) {
                mergeRequestMetadata(auditLog.getRequestMetadata());
            }
            if (auditLog.hasRequest()) {
                mergeRequest(auditLog.getRequest());
            }
            if (auditLog.hasResponse()) {
                mergeResponse(auditLog.getResponse());
            }
            if (auditLog.hasMetadata()) {
                mergeMetadata(auditLog.getMetadata());
            }
            if (auditLog.hasServiceData()) {
                mergeServiceData(auditLog.getServiceData());
            }
            mergeUnknownFields(auditLog.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            AuditLog auditLog = null;
            try {
                try {
                    AuditLog auditLog2 = (AuditLog) AuditLog.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (auditLog2 != null) {
                        mergeFrom(auditLog2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    AuditLog auditLog3 = (AuditLog) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        auditLog = auditLog3;
                        if (auditLog != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (auditLog != null) {
                    mergeFrom(auditLog);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getServiceName() {
            Object obj = this.serviceName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.serviceName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getServiceNameBytes() {
            Object obj = this.serviceName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.serviceName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setServiceName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.serviceName_ = str;
            onChanged();
            return this;
        }

        public Builder clearServiceName() {
            this.serviceName_ = AuditLog.getDefaultInstance().getServiceName();
            onChanged();
            return this;
        }

        public Builder setServiceNameBytes(ByteString byteString) {
            if (byteString != null) {
                AuditLog.checkByteStringIsUtf8(byteString);
                this.serviceName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getMethodName() {
            Object obj = this.methodName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.methodName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getMethodNameBytes() {
            Object obj = this.methodName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.methodName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMethodName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.methodName_ = str;
            onChanged();
            return this;
        }

        public Builder clearMethodName() {
            this.methodName_ = AuditLog.getDefaultInstance().getMethodName();
            onChanged();
            return this;
        }

        public Builder setMethodNameBytes(ByteString byteString) {
            if (byteString != null) {
                AuditLog.checkByteStringIsUtf8(byteString);
                this.methodName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getResourceName() {
            Object obj = this.resourceName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.resourceName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
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
            this.resourceName_ = AuditLog.getDefaultInstance().getResourceName();
            onChanged();
            return this;
        }

        public Builder setResourceNameBytes(ByteString byteString) {
            if (byteString != null) {
                AuditLog.checkByteStringIsUtf8(byteString);
                this.resourceName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasResourceLocation() {
            return (this.resourceLocationBuilder_ == null && this.resourceLocation_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ResourceLocation getResourceLocation() {
            SingleFieldBuilderV3<ResourceLocation, ResourceLocation.Builder, ResourceLocationOrBuilder> singleFieldBuilderV3 = this.resourceLocationBuilder_;
            if (singleFieldBuilderV3 == null) {
                ResourceLocation resourceLocation = this.resourceLocation_;
                return resourceLocation == null ? ResourceLocation.getDefaultInstance() : resourceLocation;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setResourceLocation(ResourceLocation resourceLocation) {
            SingleFieldBuilderV3<ResourceLocation, ResourceLocation.Builder, ResourceLocationOrBuilder> singleFieldBuilderV3 = this.resourceLocationBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(resourceLocation);
            } else {
                if (resourceLocation == null) {
                    throw new NullPointerException();
                }
                this.resourceLocation_ = resourceLocation;
                onChanged();
            }
            return this;
        }

        public Builder setResourceLocation(ResourceLocation.Builder builder) {
            SingleFieldBuilderV3<ResourceLocation, ResourceLocation.Builder, ResourceLocationOrBuilder> singleFieldBuilderV3 = this.resourceLocationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.resourceLocation_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeResourceLocation(ResourceLocation resourceLocation) {
            SingleFieldBuilderV3<ResourceLocation, ResourceLocation.Builder, ResourceLocationOrBuilder> singleFieldBuilderV3 = this.resourceLocationBuilder_;
            if (singleFieldBuilderV3 == null) {
                ResourceLocation resourceLocation2 = this.resourceLocation_;
                if (resourceLocation2 != null) {
                    this.resourceLocation_ = ResourceLocation.newBuilder(resourceLocation2).mergeFrom(resourceLocation).buildPartial();
                } else {
                    this.resourceLocation_ = resourceLocation;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(resourceLocation);
            }
            return this;
        }

        public Builder clearResourceLocation() {
            if (this.resourceLocationBuilder_ == null) {
                this.resourceLocation_ = null;
                onChanged();
            } else {
                this.resourceLocation_ = null;
                this.resourceLocationBuilder_ = null;
            }
            return this;
        }

        public ResourceLocation.Builder getResourceLocationBuilder() {
            onChanged();
            return getResourceLocationFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ResourceLocationOrBuilder getResourceLocationOrBuilder() {
            SingleFieldBuilderV3<ResourceLocation, ResourceLocation.Builder, ResourceLocationOrBuilder> singleFieldBuilderV3 = this.resourceLocationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            ResourceLocation resourceLocation = this.resourceLocation_;
            return resourceLocation == null ? ResourceLocation.getDefaultInstance() : resourceLocation;
        }

        private SingleFieldBuilderV3<ResourceLocation, ResourceLocation.Builder, ResourceLocationOrBuilder> getResourceLocationFieldBuilder() {
            if (this.resourceLocationBuilder_ == null) {
                this.resourceLocationBuilder_ = new SingleFieldBuilderV3<>(getResourceLocation(), getParentForChildren(), isClean());
                this.resourceLocation_ = null;
            }
            return this.resourceLocationBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasResourceOriginalState() {
            return (this.resourceOriginalStateBuilder_ == null && this.resourceOriginalState_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getResourceOriginalState() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.resourceOriginalStateBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.resourceOriginalState_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setResourceOriginalState(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.resourceOriginalStateBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.resourceOriginalState_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setResourceOriginalState(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.resourceOriginalStateBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.resourceOriginalState_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeResourceOriginalState(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.resourceOriginalStateBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.resourceOriginalState_;
                if (struct2 != null) {
                    this.resourceOriginalState_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.resourceOriginalState_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearResourceOriginalState() {
            if (this.resourceOriginalStateBuilder_ == null) {
                this.resourceOriginalState_ = null;
                onChanged();
            } else {
                this.resourceOriginalState_ = null;
                this.resourceOriginalStateBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getResourceOriginalStateBuilder() {
            onChanged();
            return getResourceOriginalStateFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public StructOrBuilder getResourceOriginalStateOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.resourceOriginalStateBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.resourceOriginalState_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getResourceOriginalStateFieldBuilder() {
            if (this.resourceOriginalStateBuilder_ == null) {
                this.resourceOriginalStateBuilder_ = new SingleFieldBuilderV3<>(getResourceOriginalState(), getParentForChildren(), isClean());
                this.resourceOriginalState_ = null;
            }
            return this.resourceOriginalStateBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public long getNumResponseItems() {
            return this.numResponseItems_;
        }

        public Builder setNumResponseItems(long j) {
            this.numResponseItems_ = j;
            onChanged();
            return this;
        }

        public Builder clearNumResponseItems() {
            this.numResponseItems_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasStatus() {
            return (this.statusBuilder_ == null && this.status_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Status getStatus() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                Status status = this.status_;
                return status == null ? Status.getDefaultInstance() : status;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setStatus(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(status);
            } else {
                if (status == null) {
                    throw new NullPointerException();
                }
                this.status_ = status;
                onChanged();
            }
            return this;
        }

        public Builder setStatus(Status.Builder builder) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.status_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeStatus(Status status) {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                Status status2 = this.status_;
                if (status2 != null) {
                    this.status_ = Status.newBuilder(status2).mergeFrom(status).buildPartial();
                } else {
                    this.status_ = status;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(status);
            }
            return this;
        }

        public Builder clearStatus() {
            if (this.statusBuilder_ == null) {
                this.status_ = null;
                onChanged();
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            return this;
        }

        public Status.Builder getStatusBuilder() {
            onChanged();
            return getStatusFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public StatusOrBuilder getStatusOrBuilder() {
            SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Status status = this.status_;
            return status == null ? Status.getDefaultInstance() : status;
        }

        private SingleFieldBuilderV3<Status, Status.Builder, StatusOrBuilder> getStatusFieldBuilder() {
            if (this.statusBuilder_ == null) {
                this.statusBuilder_ = new SingleFieldBuilderV3<>(getStatus(), getParentForChildren(), isClean());
                this.status_ = null;
            }
            return this.statusBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasAuthenticationInfo() {
            return (this.authenticationInfoBuilder_ == null && this.authenticationInfo_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthenticationInfo getAuthenticationInfo() {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                AuthenticationInfo authenticationInfo = this.authenticationInfo_;
                return authenticationInfo == null ? AuthenticationInfo.getDefaultInstance() : authenticationInfo;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(authenticationInfo);
            } else {
                if (authenticationInfo == null) {
                    throw new NullPointerException();
                }
                this.authenticationInfo_ = authenticationInfo;
                onChanged();
            }
            return this;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo.Builder builder) {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.authenticationInfo_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAuthenticationInfo(AuthenticationInfo authenticationInfo) {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                AuthenticationInfo authenticationInfo2 = this.authenticationInfo_;
                if (authenticationInfo2 != null) {
                    this.authenticationInfo_ = AuthenticationInfo.newBuilder(authenticationInfo2).mergeFrom(authenticationInfo).buildPartial();
                } else {
                    this.authenticationInfo_ = authenticationInfo;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(authenticationInfo);
            }
            return this;
        }

        public Builder clearAuthenticationInfo() {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = null;
                onChanged();
            } else {
                this.authenticationInfo_ = null;
                this.authenticationInfoBuilder_ = null;
            }
            return this;
        }

        public AuthenticationInfo.Builder getAuthenticationInfoBuilder() {
            onChanged();
            return getAuthenticationInfoFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder() {
            SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            AuthenticationInfo authenticationInfo = this.authenticationInfo_;
            return authenticationInfo == null ? AuthenticationInfo.getDefaultInstance() : authenticationInfo;
        }

        private SingleFieldBuilderV3<AuthenticationInfo, AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> getAuthenticationInfoFieldBuilder() {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfoBuilder_ = new SingleFieldBuilderV3<>(getAuthenticationInfo(), getParentForChildren(), isClean());
                this.authenticationInfo_ = null;
            }
            return this.authenticationInfoBuilder_;
        }

        private void ensureAuthorizationInfoIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.authorizationInfo_ = new ArrayList(this.authorizationInfo_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public List<AuthorizationInfo> getAuthorizationInfoList() {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.authorizationInfo_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public int getAuthorizationInfoCount() {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.authorizationInfo_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthorizationInfo getAuthorizationInfo(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.authorizationInfo_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, authorizationInfo);
            } else {
                if (authorizationInfo == null) {
                    throw new NullPointerException();
                }
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.set(i, authorizationInfo);
                onChanged();
            }
            return this;
        }

        public Builder setAuthorizationInfo(int i, AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(authorizationInfo);
            } else {
                if (authorizationInfo == null) {
                    throw new NullPointerException();
                }
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(authorizationInfo);
                onChanged();
            }
            return this;
        }

        public Builder addAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, authorizationInfo);
            } else {
                if (authorizationInfo == null) {
                    throw new NullPointerException();
                }
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(i, authorizationInfo);
                onChanged();
            }
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAuthorizationInfo(int i, AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> iterable) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.authorizationInfo_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAuthorizationInfo() {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.authorizationInfo_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAuthorizationInfo(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public AuthorizationInfo.Builder getAuthorizationInfoBuilder(int i) {
            return getAuthorizationInfoFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.authorizationInfo_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
            RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.authorizationInfo_);
        }

        public AuthorizationInfo.Builder addAuthorizationInfoBuilder() {
            return getAuthorizationInfoFieldBuilder().addBuilder(AuthorizationInfo.getDefaultInstance());
        }

        public AuthorizationInfo.Builder addAuthorizationInfoBuilder(int i) {
            return getAuthorizationInfoFieldBuilder().addBuilder(i, AuthorizationInfo.getDefaultInstance());
        }

        public List<AuthorizationInfo.Builder> getAuthorizationInfoBuilderList() {
            return getAuthorizationInfoFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthorizationInfo, AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> getAuthorizationInfoFieldBuilder() {
            if (this.authorizationInfoBuilder_ == null) {
                this.authorizationInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.authorizationInfo_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.authorizationInfo_ = null;
            }
            return this.authorizationInfoBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasRequestMetadata() {
            return (this.requestMetadataBuilder_ == null && this.requestMetadata_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public RequestMetadata getRequestMetadata() {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                RequestMetadata requestMetadata = this.requestMetadata_;
                return requestMetadata == null ? RequestMetadata.getDefaultInstance() : requestMetadata;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setRequestMetadata(RequestMetadata requestMetadata) {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(requestMetadata);
            } else {
                if (requestMetadata == null) {
                    throw new NullPointerException();
                }
                this.requestMetadata_ = requestMetadata;
                onChanged();
            }
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata.Builder builder) {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.requestMetadata_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequestMetadata(RequestMetadata requestMetadata) {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                RequestMetadata requestMetadata2 = this.requestMetadata_;
                if (requestMetadata2 != null) {
                    this.requestMetadata_ = RequestMetadata.newBuilder(requestMetadata2).mergeFrom(requestMetadata).buildPartial();
                } else {
                    this.requestMetadata_ = requestMetadata;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(requestMetadata);
            }
            return this;
        }

        public Builder clearRequestMetadata() {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = null;
                onChanged();
            } else {
                this.requestMetadata_ = null;
                this.requestMetadataBuilder_ = null;
            }
            return this;
        }

        public RequestMetadata.Builder getRequestMetadataBuilder() {
            onChanged();
            return getRequestMetadataFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public RequestMetadataOrBuilder getRequestMetadataOrBuilder() {
            SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            RequestMetadata requestMetadata = this.requestMetadata_;
            return requestMetadata == null ? RequestMetadata.getDefaultInstance() : requestMetadata;
        }

        private SingleFieldBuilderV3<RequestMetadata, RequestMetadata.Builder, RequestMetadataOrBuilder> getRequestMetadataFieldBuilder() {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadataBuilder_ = new SingleFieldBuilderV3<>(getRequestMetadata(), getParentForChildren(), isClean());
                this.requestMetadata_ = null;
            }
            return this.requestMetadataBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasRequest() {
            return (this.requestBuilder_ == null && this.request_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getRequest() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.request_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setRequest(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.request_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setRequest(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.request_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequest(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.request_;
                if (struct2 != null) {
                    this.request_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.request_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearRequest() {
            if (this.requestBuilder_ == null) {
                this.request_ = null;
                onChanged();
            } else {
                this.request_ = null;
                this.requestBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getRequestBuilder() {
            onChanged();
            return getRequestFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public StructOrBuilder getRequestOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.request_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getRequestFieldBuilder() {
            if (this.requestBuilder_ == null) {
                this.requestBuilder_ = new SingleFieldBuilderV3<>(getRequest(), getParentForChildren(), isClean());
                this.request_ = null;
            }
            return this.requestBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasResponse() {
            return (this.responseBuilder_ == null && this.response_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getResponse() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.response_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setResponse(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.response_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setResponse(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.response_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeResponse(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.response_;
                if (struct2 != null) {
                    this.response_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.response_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearResponse() {
            if (this.responseBuilder_ == null) {
                this.response_ = null;
                onChanged();
            } else {
                this.response_ = null;
                this.responseBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getResponseBuilder() {
            onChanged();
            return getResponseFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public StructOrBuilder getResponseOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.response_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getResponseFieldBuilder() {
            if (this.responseBuilder_ == null) {
                this.responseBuilder_ = new SingleFieldBuilderV3<>(getResponse(), getParentForChildren(), isClean());
                this.response_ = null;
            }
            return this.responseBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasMetadata() {
            return (this.metadataBuilder_ == null && this.metadata_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getMetadata() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.metadata_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setMetadata(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.metadata_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setMetadata(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.metadata_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMetadata(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.metadata_;
                if (struct2 != null) {
                    this.metadata_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.metadata_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearMetadata() {
            if (this.metadataBuilder_ == null) {
                this.metadata_ = null;
                onChanged();
            } else {
                this.metadata_ = null;
                this.metadataBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getMetadataBuilder() {
            onChanged();
            return getMetadataFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public StructOrBuilder getMetadataOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.metadata_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getMetadataFieldBuilder() {
            if (this.metadataBuilder_ == null) {
                this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                this.metadata_ = null;
            }
            return this.metadataBuilder_;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasServiceData() {
            return (this.serviceDataBuilder_ == null && this.serviceData_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Any getServiceData() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any = this.serviceData_;
                return any == null ? Any.getDefaultInstance() : any;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setServiceData(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                this.serviceData_ = any;
                onChanged();
            }
            return this;
        }

        public Builder setServiceData(Any.Builder builder) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.serviceData_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeServiceData(Any any) {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.serviceData_;
                if (any2 != null) {
                    this.serviceData_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.serviceData_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearServiceData() {
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = null;
                onChanged();
            } else {
                this.serviceData_ = null;
                this.serviceDataBuilder_ = null;
            }
            return this;
        }

        public Any.Builder getServiceDataBuilder() {
            onChanged();
            return getServiceDataFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AnyOrBuilder getServiceDataOrBuilder() {
            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.serviceData_;
            return any == null ? Any.getDefaultInstance() : any;
        }

        private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getServiceDataFieldBuilder() {
            if (this.serviceDataBuilder_ == null) {
                this.serviceDataBuilder_ = new SingleFieldBuilderV3<>(getServiceData(), getParentForChildren(), isClean());
                this.serviceData_ = null;
            }
            return this.serviceDataBuilder_;
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

    public static AuditLog getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuditLog> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<AuditLog> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public AuditLog getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
