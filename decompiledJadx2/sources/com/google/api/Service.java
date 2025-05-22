package com.google.api;

import com.google.api.Authentication;
import com.google.api.Backend;
import com.google.api.Billing;
import com.google.api.Context;
import com.google.api.Control;
import com.google.api.Documentation;
import com.google.api.Endpoint;
import com.google.api.Http;
import com.google.api.LogDescriptor;
import com.google.api.Logging;
import com.google.api.MetricDescriptor;
import com.google.api.MonitoredResourceDescriptor;
import com.google.api.Monitoring;
import com.google.api.Quota;
import com.google.api.SourceInfo;
import com.google.api.SystemParameters;
import com.google.api.Usage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Api;
import com.google.protobuf.ApiOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Enum;
import com.google.protobuf.EnumOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Type;
import com.google.protobuf.TypeOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
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
public final class Service extends GeneratedMessageV3 implements ServiceOrBuilder {
    public static final int APIS_FIELD_NUMBER = 3;
    public static final int AUTHENTICATION_FIELD_NUMBER = 11;
    public static final int BACKEND_FIELD_NUMBER = 8;
    public static final int BILLING_FIELD_NUMBER = 26;
    public static final int CONFIG_VERSION_FIELD_NUMBER = 20;
    public static final int CONTEXT_FIELD_NUMBER = 12;
    public static final int CONTROL_FIELD_NUMBER = 21;
    public static final int DOCUMENTATION_FIELD_NUMBER = 6;
    public static final int ENDPOINTS_FIELD_NUMBER = 18;
    public static final int ENUMS_FIELD_NUMBER = 5;
    public static final int HTTP_FIELD_NUMBER = 9;
    public static final int ID_FIELD_NUMBER = 33;
    public static final int LOGGING_FIELD_NUMBER = 27;
    public static final int LOGS_FIELD_NUMBER = 23;
    public static final int METRICS_FIELD_NUMBER = 24;
    public static final int MONITORED_RESOURCES_FIELD_NUMBER = 25;
    public static final int MONITORING_FIELD_NUMBER = 28;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int PRODUCER_PROJECT_ID_FIELD_NUMBER = 22;
    public static final int QUOTA_FIELD_NUMBER = 10;
    public static final int SOURCE_INFO_FIELD_NUMBER = 37;
    public static final int SYSTEM_PARAMETERS_FIELD_NUMBER = 29;
    public static final int TITLE_FIELD_NUMBER = 2;
    public static final int TYPES_FIELD_NUMBER = 4;
    public static final int USAGE_FIELD_NUMBER = 15;
    private static final long serialVersionUID = 0;
    private List<Api> apis_;
    private Authentication authentication_;
    private Backend backend_;
    private Billing billing_;
    private UInt32Value configVersion_;
    private Context context_;
    private Control control_;
    private Documentation documentation_;
    private List<Endpoint> endpoints_;
    private List<Enum> enums_;
    private Http http_;
    private volatile Object id_;
    private Logging logging_;
    private List<LogDescriptor> logs_;
    private byte memoizedIsInitialized;
    private List<MetricDescriptor> metrics_;
    private List<MonitoredResourceDescriptor> monitoredResources_;
    private Monitoring monitoring_;
    private volatile Object name_;
    private volatile Object producerProjectId_;
    private Quota quota_;
    private SourceInfo sourceInfo_;
    private SystemParameters systemParameters_;
    private volatile Object title_;
    private List<Type> types_;
    private Usage usage_;
    private static final Service DEFAULT_INSTANCE = new Service();
    private static final Parser<Service> PARSER = new AbstractParser<Service>() { // from class: com.google.api.Service.1
        @Override // com.google.protobuf.Parser
        public Service parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Service(codedInputStream, extensionRegistryLite);
        }
    };

    private Service(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Service() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.title_ = "";
        this.producerProjectId_ = "";
        this.id_ = "";
        this.apis_ = Collections.emptyList();
        this.types_ = Collections.emptyList();
        this.enums_ = Collections.emptyList();
        this.endpoints_ = Collections.emptyList();
        this.logs_ = Collections.emptyList();
        this.metrics_ = Collections.emptyList();
        this.monitoredResources_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Service();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0013. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    private Service(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    switch (readTag) {
                        case 0:
                            z = true;
                        case 10:
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        case 18:
                            this.title_ = codedInputStream.readStringRequireUtf8();
                        case 26:
                            int i = (c == true ? 1 : 0) & 1;
                            c = c;
                            if (i == 0) {
                                this.apis_ = new ArrayList();
                                c = (c == true ? 1 : 0) | 1;
                            }
                            this.apis_.add(codedInputStream.readMessage(Api.parser(), extensionRegistryLite));
                        case 34:
                            int i2 = (c == true ? 1 : 0) & 2;
                            c = c;
                            if (i2 == 0) {
                                this.types_ = new ArrayList();
                                c = (c == true ? 1 : 0) | 2;
                            }
                            this.types_.add(codedInputStream.readMessage(Type.parser(), extensionRegistryLite));
                        case 42:
                            int i3 = (c == true ? 1 : 0) & 4;
                            c = c;
                            if (i3 == 0) {
                                this.enums_ = new ArrayList();
                                c = (c == true ? 1 : 0) | 4;
                            }
                            this.enums_.add(codedInputStream.readMessage(Enum.parser(), extensionRegistryLite));
                        case 50:
                            Documentation.Builder builder = this.documentation_ != null ? this.documentation_.toBuilder() : null;
                            this.documentation_ = (Documentation) codedInputStream.readMessage(Documentation.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.documentation_);
                                this.documentation_ = builder.buildPartial();
                            }
                        case 66:
                            Backend.Builder builder2 = this.backend_ != null ? this.backend_.toBuilder() : null;
                            this.backend_ = (Backend) codedInputStream.readMessage(Backend.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.backend_);
                                this.backend_ = builder2.buildPartial();
                            }
                        case 74:
                            Http.Builder builder3 = this.http_ != null ? this.http_.toBuilder() : null;
                            this.http_ = (Http) codedInputStream.readMessage(Http.parser(), extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom(this.http_);
                                this.http_ = builder3.buildPartial();
                            }
                        case 82:
                            Quota.Builder builder4 = this.quota_ != null ? this.quota_.toBuilder() : null;
                            this.quota_ = (Quota) codedInputStream.readMessage(Quota.parser(), extensionRegistryLite);
                            if (builder4 != null) {
                                builder4.mergeFrom(this.quota_);
                                this.quota_ = builder4.buildPartial();
                            }
                        case 90:
                            Authentication.Builder builder5 = this.authentication_ != null ? this.authentication_.toBuilder() : null;
                            this.authentication_ = (Authentication) codedInputStream.readMessage(Authentication.parser(), extensionRegistryLite);
                            if (builder5 != null) {
                                builder5.mergeFrom(this.authentication_);
                                this.authentication_ = builder5.buildPartial();
                            }
                        case 98:
                            Context.Builder builder6 = this.context_ != null ? this.context_.toBuilder() : null;
                            this.context_ = (Context) codedInputStream.readMessage(Context.parser(), extensionRegistryLite);
                            if (builder6 != null) {
                                builder6.mergeFrom(this.context_);
                                this.context_ = builder6.buildPartial();
                            }
                        case 122:
                            Usage.Builder builder7 = this.usage_ != null ? this.usage_.toBuilder() : null;
                            this.usage_ = (Usage) codedInputStream.readMessage(Usage.parser(), extensionRegistryLite);
                            if (builder7 != null) {
                                builder7.mergeFrom(this.usage_);
                                this.usage_ = builder7.buildPartial();
                            }
                        case 146:
                            int i4 = (c == true ? 1 : 0) & 8;
                            c = c;
                            if (i4 == 0) {
                                this.endpoints_ = new ArrayList();
                                c = (c == true ? 1 : 0) | '\b';
                            }
                            this.endpoints_.add(codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite));
                        case 162:
                            UInt32Value.Builder builder8 = this.configVersion_ != null ? this.configVersion_.toBuilder() : null;
                            this.configVersion_ = (UInt32Value) codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            if (builder8 != null) {
                                builder8.mergeFrom(this.configVersion_);
                                this.configVersion_ = builder8.buildPartial();
                            }
                        case 170:
                            Control.Builder builder9 = this.control_ != null ? this.control_.toBuilder() : null;
                            this.control_ = (Control) codedInputStream.readMessage(Control.parser(), extensionRegistryLite);
                            if (builder9 != null) {
                                builder9.mergeFrom(this.control_);
                                this.control_ = builder9.buildPartial();
                            }
                        case 178:
                            this.producerProjectId_ = codedInputStream.readStringRequireUtf8();
                        case 186:
                            int i5 = (c == true ? 1 : 0) & 16;
                            c = c;
                            if (i5 == 0) {
                                this.logs_ = new ArrayList();
                                c = (c == true ? 1 : 0) | 16;
                            }
                            this.logs_.add(codedInputStream.readMessage(LogDescriptor.parser(), extensionRegistryLite));
                        case 194:
                            int i6 = (c == true ? 1 : 0) & 32;
                            c = c;
                            if (i6 == 0) {
                                this.metrics_ = new ArrayList();
                                c = (c == true ? 1 : 0) | ' ';
                            }
                            this.metrics_.add(codedInputStream.readMessage(MetricDescriptor.parser(), extensionRegistryLite));
                        case 202:
                            int i7 = (c == true ? 1 : 0) & 64;
                            c = c;
                            if (i7 == 0) {
                                this.monitoredResources_ = new ArrayList();
                                c = (c == true ? 1 : 0) | '@';
                            }
                            this.monitoredResources_.add(codedInputStream.readMessage(MonitoredResourceDescriptor.parser(), extensionRegistryLite));
                        case 210:
                            Billing.Builder builder10 = this.billing_ != null ? this.billing_.toBuilder() : null;
                            this.billing_ = (Billing) codedInputStream.readMessage(Billing.parser(), extensionRegistryLite);
                            if (builder10 != null) {
                                builder10.mergeFrom(this.billing_);
                                this.billing_ = builder10.buildPartial();
                            }
                        case 218:
                            Logging.Builder builder11 = this.logging_ != null ? this.logging_.toBuilder() : null;
                            this.logging_ = (Logging) codedInputStream.readMessage(Logging.parser(), extensionRegistryLite);
                            if (builder11 != null) {
                                builder11.mergeFrom(this.logging_);
                                this.logging_ = builder11.buildPartial();
                            }
                        case 226:
                            Monitoring.Builder builder12 = this.monitoring_ != null ? this.monitoring_.toBuilder() : null;
                            this.monitoring_ = (Monitoring) codedInputStream.readMessage(Monitoring.parser(), extensionRegistryLite);
                            if (builder12 != null) {
                                builder12.mergeFrom(this.monitoring_);
                                this.monitoring_ = builder12.buildPartial();
                            }
                        case 234:
                            SystemParameters.Builder builder13 = this.systemParameters_ != null ? this.systemParameters_.toBuilder() : null;
                            this.systemParameters_ = (SystemParameters) codedInputStream.readMessage(SystemParameters.parser(), extensionRegistryLite);
                            if (builder13 != null) {
                                builder13.mergeFrom(this.systemParameters_);
                                this.systemParameters_ = builder13.buildPartial();
                            }
                        case 266:
                            this.id_ = codedInputStream.readStringRequireUtf8();
                        case 298:
                            SourceInfo.Builder builder14 = this.sourceInfo_ != null ? this.sourceInfo_.toBuilder() : null;
                            this.sourceInfo_ = (SourceInfo) codedInputStream.readMessage(SourceInfo.parser(), extensionRegistryLite);
                            if (builder14 != null) {
                                builder14.mergeFrom(this.sourceInfo_);
                                this.sourceInfo_ = builder14.buildPartial();
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
                if (((c == true ? 1 : 0) & 1) != 0) {
                    this.apis_ = Collections.unmodifiableList(this.apis_);
                }
                if (((c == true ? 1 : 0) & 2) != 0) {
                    this.types_ = Collections.unmodifiableList(this.types_);
                }
                if (((c == true ? 1 : 0) & 4) != 0) {
                    this.enums_ = Collections.unmodifiableList(this.enums_);
                }
                if (((c == true ? 1 : 0) & 8) != 0) {
                    this.endpoints_ = Collections.unmodifiableList(this.endpoints_);
                }
                if (((c == true ? 1 : 0) & 16) != 0) {
                    this.logs_ = Collections.unmodifiableList(this.logs_);
                }
                if (((c == true ? 1 : 0) & 32) != 0) {
                    this.metrics_ = Collections.unmodifiableList(this.metrics_);
                }
                if (((c == true ? 1 : 0) & 64) != 0) {
                    this.monitoredResources_ = Collections.unmodifiableList(this.monitoredResources_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ServiceProto.internal_static_google_api_Service_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ServiceProto.internal_static_google_api_Service_fieldAccessorTable.ensureFieldAccessorsInitialized(Service.class, Builder.class);
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getTitle() {
        Object obj = this.title_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.title_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getTitleBytes() {
        Object obj = this.title_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.title_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getProducerProjectId() {
        Object obj = this.producerProjectId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.producerProjectId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getProducerProjectIdBytes() {
        Object obj = this.producerProjectId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.producerProjectId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getId() {
        Object obj = this.id_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.id_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getIdBytes() {
        Object obj = this.id_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.id_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Api> getApisList() {
        return this.apis_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<? extends ApiOrBuilder> getApisOrBuilderList() {
        return this.apis_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getApisCount() {
        return this.apis_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public Api getApis(int i) {
        return this.apis_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public ApiOrBuilder getApisOrBuilder(int i) {
        return this.apis_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Type> getTypesList() {
        return this.types_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
        return this.types_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getTypesCount() {
        return this.types_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public Type getTypes(int i) {
        return this.types_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public TypeOrBuilder getTypesOrBuilder(int i) {
        return this.types_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Enum> getEnumsList() {
        return this.enums_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
        return this.enums_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getEnumsCount() {
        return this.enums_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public Enum getEnums(int i) {
        return this.enums_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public EnumOrBuilder getEnumsOrBuilder(int i) {
        return this.enums_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasDocumentation() {
        return this.documentation_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Documentation getDocumentation() {
        Documentation documentation = this.documentation_;
        return documentation == null ? Documentation.getDefaultInstance() : documentation;
    }

    @Override // com.google.api.ServiceOrBuilder
    public DocumentationOrBuilder getDocumentationOrBuilder() {
        return getDocumentation();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasBackend() {
        return this.backend_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Backend getBackend() {
        Backend backend = this.backend_;
        return backend == null ? Backend.getDefaultInstance() : backend;
    }

    @Override // com.google.api.ServiceOrBuilder
    public BackendOrBuilder getBackendOrBuilder() {
        return getBackend();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasHttp() {
        return this.http_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Http getHttp() {
        Http http = this.http_;
        return http == null ? Http.getDefaultInstance() : http;
    }

    @Override // com.google.api.ServiceOrBuilder
    public HttpOrBuilder getHttpOrBuilder() {
        return getHttp();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasQuota() {
        return this.quota_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Quota getQuota() {
        Quota quota = this.quota_;
        return quota == null ? Quota.getDefaultInstance() : quota;
    }

    @Override // com.google.api.ServiceOrBuilder
    public QuotaOrBuilder getQuotaOrBuilder() {
        return getQuota();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasAuthentication() {
        return this.authentication_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Authentication getAuthentication() {
        Authentication authentication = this.authentication_;
        return authentication == null ? Authentication.getDefaultInstance() : authentication;
    }

    @Override // com.google.api.ServiceOrBuilder
    public AuthenticationOrBuilder getAuthenticationOrBuilder() {
        return getAuthentication();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasContext() {
        return this.context_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Context getContext() {
        Context context = this.context_;
        return context == null ? Context.getDefaultInstance() : context;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ContextOrBuilder getContextOrBuilder() {
        return getContext();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasUsage() {
        return this.usage_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Usage getUsage() {
        Usage usage = this.usage_;
        return usage == null ? Usage.getDefaultInstance() : usage;
    }

    @Override // com.google.api.ServiceOrBuilder
    public UsageOrBuilder getUsageOrBuilder() {
        return getUsage();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Endpoint> getEndpointsList() {
        return this.endpoints_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public Endpoint getEndpoints(int i) {
        return this.endpoints_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public EndpointOrBuilder getEndpointsOrBuilder(int i) {
        return this.endpoints_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasControl() {
        return this.control_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Control getControl() {
        Control control = this.control_;
        return control == null ? Control.getDefaultInstance() : control;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ControlOrBuilder getControlOrBuilder() {
        return getControl();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<LogDescriptor> getLogsList() {
        return this.logs_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
        return this.logs_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getLogsCount() {
        return this.logs_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public LogDescriptor getLogs(int i) {
        return this.logs_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public LogDescriptorOrBuilder getLogsOrBuilder(int i) {
        return this.logs_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<MetricDescriptor> getMetricsList() {
        return this.metrics_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getMetricsCount() {
        return this.metrics_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public MetricDescriptor getMetrics(int i) {
        return this.metrics_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public MetricDescriptorOrBuilder getMetricsOrBuilder(int i) {
        return this.metrics_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
        return this.monitoredResources_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
        return this.monitoredResources_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getMonitoredResourcesCount() {
        return this.monitoredResources_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public MonitoredResourceDescriptor getMonitoredResources(int i) {
        return this.monitoredResources_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int i) {
        return this.monitoredResources_.get(i);
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasBilling() {
        return this.billing_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Billing getBilling() {
        Billing billing = this.billing_;
        return billing == null ? Billing.getDefaultInstance() : billing;
    }

    @Override // com.google.api.ServiceOrBuilder
    public BillingOrBuilder getBillingOrBuilder() {
        return getBilling();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasLogging() {
        return this.logging_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Logging getLogging() {
        Logging logging = this.logging_;
        return logging == null ? Logging.getDefaultInstance() : logging;
    }

    @Override // com.google.api.ServiceOrBuilder
    public LoggingOrBuilder getLoggingOrBuilder() {
        return getLogging();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasMonitoring() {
        return this.monitoring_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Monitoring getMonitoring() {
        Monitoring monitoring = this.monitoring_;
        return monitoring == null ? Monitoring.getDefaultInstance() : monitoring;
    }

    @Override // com.google.api.ServiceOrBuilder
    public MonitoringOrBuilder getMonitoringOrBuilder() {
        return getMonitoring();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasSystemParameters() {
        return this.systemParameters_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public SystemParameters getSystemParameters() {
        SystemParameters systemParameters = this.systemParameters_;
        return systemParameters == null ? SystemParameters.getDefaultInstance() : systemParameters;
    }

    @Override // com.google.api.ServiceOrBuilder
    public SystemParametersOrBuilder getSystemParametersOrBuilder() {
        return getSystemParameters();
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasSourceInfo() {
        return this.sourceInfo_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public SourceInfo getSourceInfo() {
        SourceInfo sourceInfo = this.sourceInfo_;
        return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
    }

    @Override // com.google.api.ServiceOrBuilder
    public SourceInfoOrBuilder getSourceInfoOrBuilder() {
        return getSourceInfo();
    }

    @Override // com.google.api.ServiceOrBuilder
    @Deprecated
    public boolean hasConfigVersion() {
        return this.configVersion_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    @Deprecated
    public UInt32Value getConfigVersion() {
        UInt32Value uInt32Value = this.configVersion_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // com.google.api.ServiceOrBuilder
    @Deprecated
    public UInt32ValueOrBuilder getConfigVersionOrBuilder() {
        return getConfigVersion();
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getTitleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.title_);
        }
        for (int i = 0; i < this.apis_.size(); i++) {
            codedOutputStream.writeMessage(3, this.apis_.get(i));
        }
        for (int i2 = 0; i2 < this.types_.size(); i2++) {
            codedOutputStream.writeMessage(4, this.types_.get(i2));
        }
        for (int i3 = 0; i3 < this.enums_.size(); i3++) {
            codedOutputStream.writeMessage(5, this.enums_.get(i3));
        }
        if (this.documentation_ != null) {
            codedOutputStream.writeMessage(6, getDocumentation());
        }
        if (this.backend_ != null) {
            codedOutputStream.writeMessage(8, getBackend());
        }
        if (this.http_ != null) {
            codedOutputStream.writeMessage(9, getHttp());
        }
        if (this.quota_ != null) {
            codedOutputStream.writeMessage(10, getQuota());
        }
        if (this.authentication_ != null) {
            codedOutputStream.writeMessage(11, getAuthentication());
        }
        if (this.context_ != null) {
            codedOutputStream.writeMessage(12, getContext());
        }
        if (this.usage_ != null) {
            codedOutputStream.writeMessage(15, getUsage());
        }
        for (int i4 = 0; i4 < this.endpoints_.size(); i4++) {
            codedOutputStream.writeMessage(18, this.endpoints_.get(i4));
        }
        if (this.configVersion_ != null) {
            codedOutputStream.writeMessage(20, getConfigVersion());
        }
        if (this.control_ != null) {
            codedOutputStream.writeMessage(21, getControl());
        }
        if (!getProducerProjectIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 22, this.producerProjectId_);
        }
        for (int i5 = 0; i5 < this.logs_.size(); i5++) {
            codedOutputStream.writeMessage(23, this.logs_.get(i5));
        }
        for (int i6 = 0; i6 < this.metrics_.size(); i6++) {
            codedOutputStream.writeMessage(24, this.metrics_.get(i6));
        }
        for (int i7 = 0; i7 < this.monitoredResources_.size(); i7++) {
            codedOutputStream.writeMessage(25, this.monitoredResources_.get(i7));
        }
        if (this.billing_ != null) {
            codedOutputStream.writeMessage(26, getBilling());
        }
        if (this.logging_ != null) {
            codedOutputStream.writeMessage(27, getLogging());
        }
        if (this.monitoring_ != null) {
            codedOutputStream.writeMessage(28, getMonitoring());
        }
        if (this.systemParameters_ != null) {
            codedOutputStream.writeMessage(29, getSystemParameters());
        }
        if (!getIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 33, this.id_);
        }
        if (this.sourceInfo_ != null) {
            codedOutputStream.writeMessage(37, getSourceInfo());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        if (!getTitleBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.title_);
        }
        int i2 = computeStringSize;
        for (int i3 = 0; i3 < this.apis_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, this.apis_.get(i3));
        }
        for (int i4 = 0; i4 < this.types_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(4, this.types_.get(i4));
        }
        for (int i5 = 0; i5 < this.enums_.size(); i5++) {
            i2 += CodedOutputStream.computeMessageSize(5, this.enums_.get(i5));
        }
        if (this.documentation_ != null) {
            i2 += CodedOutputStream.computeMessageSize(6, getDocumentation());
        }
        if (this.backend_ != null) {
            i2 += CodedOutputStream.computeMessageSize(8, getBackend());
        }
        if (this.http_ != null) {
            i2 += CodedOutputStream.computeMessageSize(9, getHttp());
        }
        if (this.quota_ != null) {
            i2 += CodedOutputStream.computeMessageSize(10, getQuota());
        }
        if (this.authentication_ != null) {
            i2 += CodedOutputStream.computeMessageSize(11, getAuthentication());
        }
        if (this.context_ != null) {
            i2 += CodedOutputStream.computeMessageSize(12, getContext());
        }
        if (this.usage_ != null) {
            i2 += CodedOutputStream.computeMessageSize(15, getUsage());
        }
        for (int i6 = 0; i6 < this.endpoints_.size(); i6++) {
            i2 += CodedOutputStream.computeMessageSize(18, this.endpoints_.get(i6));
        }
        if (this.configVersion_ != null) {
            i2 += CodedOutputStream.computeMessageSize(20, getConfigVersion());
        }
        if (this.control_ != null) {
            i2 += CodedOutputStream.computeMessageSize(21, getControl());
        }
        if (!getProducerProjectIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(22, this.producerProjectId_);
        }
        for (int i7 = 0; i7 < this.logs_.size(); i7++) {
            i2 += CodedOutputStream.computeMessageSize(23, this.logs_.get(i7));
        }
        for (int i8 = 0; i8 < this.metrics_.size(); i8++) {
            i2 += CodedOutputStream.computeMessageSize(24, this.metrics_.get(i8));
        }
        for (int i9 = 0; i9 < this.monitoredResources_.size(); i9++) {
            i2 += CodedOutputStream.computeMessageSize(25, this.monitoredResources_.get(i9));
        }
        if (this.billing_ != null) {
            i2 += CodedOutputStream.computeMessageSize(26, getBilling());
        }
        if (this.logging_ != null) {
            i2 += CodedOutputStream.computeMessageSize(27, getLogging());
        }
        if (this.monitoring_ != null) {
            i2 += CodedOutputStream.computeMessageSize(28, getMonitoring());
        }
        if (this.systemParameters_ != null) {
            i2 += CodedOutputStream.computeMessageSize(29, getSystemParameters());
        }
        if (!getIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(33, this.id_);
        }
        if (this.sourceInfo_ != null) {
            i2 += CodedOutputStream.computeMessageSize(37, getSourceInfo());
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
        if (!(obj instanceof Service)) {
            return super.equals(obj);
        }
        Service service = (Service) obj;
        if (!getName().equals(service.getName()) || !getTitle().equals(service.getTitle()) || !getProducerProjectId().equals(service.getProducerProjectId()) || !getId().equals(service.getId()) || !getApisList().equals(service.getApisList()) || !getTypesList().equals(service.getTypesList()) || !getEnumsList().equals(service.getEnumsList()) || hasDocumentation() != service.hasDocumentation()) {
            return false;
        }
        if ((hasDocumentation() && !getDocumentation().equals(service.getDocumentation())) || hasBackend() != service.hasBackend()) {
            return false;
        }
        if ((hasBackend() && !getBackend().equals(service.getBackend())) || hasHttp() != service.hasHttp()) {
            return false;
        }
        if ((hasHttp() && !getHttp().equals(service.getHttp())) || hasQuota() != service.hasQuota()) {
            return false;
        }
        if ((hasQuota() && !getQuota().equals(service.getQuota())) || hasAuthentication() != service.hasAuthentication()) {
            return false;
        }
        if ((hasAuthentication() && !getAuthentication().equals(service.getAuthentication())) || hasContext() != service.hasContext()) {
            return false;
        }
        if ((hasContext() && !getContext().equals(service.getContext())) || hasUsage() != service.hasUsage()) {
            return false;
        }
        if ((hasUsage() && !getUsage().equals(service.getUsage())) || !getEndpointsList().equals(service.getEndpointsList()) || hasControl() != service.hasControl()) {
            return false;
        }
        if ((hasControl() && !getControl().equals(service.getControl())) || !getLogsList().equals(service.getLogsList()) || !getMetricsList().equals(service.getMetricsList()) || !getMonitoredResourcesList().equals(service.getMonitoredResourcesList()) || hasBilling() != service.hasBilling()) {
            return false;
        }
        if ((hasBilling() && !getBilling().equals(service.getBilling())) || hasLogging() != service.hasLogging()) {
            return false;
        }
        if ((hasLogging() && !getLogging().equals(service.getLogging())) || hasMonitoring() != service.hasMonitoring()) {
            return false;
        }
        if ((hasMonitoring() && !getMonitoring().equals(service.getMonitoring())) || hasSystemParameters() != service.hasSystemParameters()) {
            return false;
        }
        if ((hasSystemParameters() && !getSystemParameters().equals(service.getSystemParameters())) || hasSourceInfo() != service.hasSourceInfo()) {
            return false;
        }
        if ((!hasSourceInfo() || getSourceInfo().equals(service.getSourceInfo())) && hasConfigVersion() == service.hasConfigVersion()) {
            return (!hasConfigVersion() || getConfigVersion().equals(service.getConfigVersion())) && this.unknownFields.equals(service.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getTitle().hashCode()) * 37) + 22) * 53) + getProducerProjectId().hashCode()) * 37) + 33) * 53) + getId().hashCode();
        if (getApisCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getApisList().hashCode();
        }
        if (getTypesCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getTypesList().hashCode();
        }
        if (getEnumsCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getEnumsList().hashCode();
        }
        if (hasDocumentation()) {
            hashCode = (((hashCode * 37) + 6) * 53) + getDocumentation().hashCode();
        }
        if (hasBackend()) {
            hashCode = (((hashCode * 37) + 8) * 53) + getBackend().hashCode();
        }
        if (hasHttp()) {
            hashCode = (((hashCode * 37) + 9) * 53) + getHttp().hashCode();
        }
        if (hasQuota()) {
            hashCode = (((hashCode * 37) + 10) * 53) + getQuota().hashCode();
        }
        if (hasAuthentication()) {
            hashCode = (((hashCode * 37) + 11) * 53) + getAuthentication().hashCode();
        }
        if (hasContext()) {
            hashCode = (((hashCode * 37) + 12) * 53) + getContext().hashCode();
        }
        if (hasUsage()) {
            hashCode = (((hashCode * 37) + 15) * 53) + getUsage().hashCode();
        }
        if (getEndpointsCount() > 0) {
            hashCode = (((hashCode * 37) + 18) * 53) + getEndpointsList().hashCode();
        }
        if (hasControl()) {
            hashCode = (((hashCode * 37) + 21) * 53) + getControl().hashCode();
        }
        if (getLogsCount() > 0) {
            hashCode = (((hashCode * 37) + 23) * 53) + getLogsList().hashCode();
        }
        if (getMetricsCount() > 0) {
            hashCode = (((hashCode * 37) + 24) * 53) + getMetricsList().hashCode();
        }
        if (getMonitoredResourcesCount() > 0) {
            hashCode = (((hashCode * 37) + 25) * 53) + getMonitoredResourcesList().hashCode();
        }
        if (hasBilling()) {
            hashCode = (((hashCode * 37) + 26) * 53) + getBilling().hashCode();
        }
        if (hasLogging()) {
            hashCode = (((hashCode * 37) + 27) * 53) + getLogging().hashCode();
        }
        if (hasMonitoring()) {
            hashCode = (((hashCode * 37) + 28) * 53) + getMonitoring().hashCode();
        }
        if (hasSystemParameters()) {
            hashCode = (((hashCode * 37) + 29) * 53) + getSystemParameters().hashCode();
        }
        if (hasSourceInfo()) {
            hashCode = (((hashCode * 37) + 37) * 53) + getSourceInfo().hashCode();
        }
        if (hasConfigVersion()) {
            hashCode = (((hashCode * 37) + 20) * 53) + getConfigVersion().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Service parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Service parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Service parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Service parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Service parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Service parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Service parseFrom(InputStream inputStream) throws IOException {
        return (Service) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Service parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Service parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Service) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Service parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Service parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Service) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Service parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Service service) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(service);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServiceOrBuilder {
        private RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> apisBuilder_;
        private List<Api> apis_;
        private SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> authenticationBuilder_;
        private Authentication authentication_;
        private SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> backendBuilder_;
        private Backend backend_;
        private SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> billingBuilder_;
        private Billing billing_;
        private int bitField0_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> configVersionBuilder_;
        private UInt32Value configVersion_;
        private SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> contextBuilder_;
        private Context context_;
        private SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> controlBuilder_;
        private Control control_;
        private SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> documentationBuilder_;
        private Documentation documentation_;
        private RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> endpointsBuilder_;
        private List<Endpoint> endpoints_;
        private RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> enumsBuilder_;
        private List<Enum> enums_;
        private SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> httpBuilder_;
        private Http http_;
        private Object id_;
        private SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> loggingBuilder_;
        private Logging logging_;
        private RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> logsBuilder_;
        private List<LogDescriptor> logs_;
        private RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> metricsBuilder_;
        private List<MetricDescriptor> metrics_;
        private RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> monitoredResourcesBuilder_;
        private List<MonitoredResourceDescriptor> monitoredResources_;
        private SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> monitoringBuilder_;
        private Monitoring monitoring_;
        private Object name_;
        private Object producerProjectId_;
        private SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> quotaBuilder_;
        private Quota quota_;
        private SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> sourceInfoBuilder_;
        private SourceInfo sourceInfo_;
        private SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> systemParametersBuilder_;
        private SystemParameters systemParameters_;
        private Object title_;
        private RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> typesBuilder_;
        private List<Type> types_;
        private SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> usageBuilder_;
        private Usage usage_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ServiceProto.internal_static_google_api_Service_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ServiceProto.internal_static_google_api_Service_fieldAccessorTable.ensureFieldAccessorsInitialized(Service.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.title_ = "";
            this.producerProjectId_ = "";
            this.id_ = "";
            this.apis_ = Collections.emptyList();
            this.types_ = Collections.emptyList();
            this.enums_ = Collections.emptyList();
            this.endpoints_ = Collections.emptyList();
            this.logs_ = Collections.emptyList();
            this.metrics_ = Collections.emptyList();
            this.monitoredResources_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.title_ = "";
            this.producerProjectId_ = "";
            this.id_ = "";
            this.apis_ = Collections.emptyList();
            this.types_ = Collections.emptyList();
            this.enums_ = Collections.emptyList();
            this.endpoints_ = Collections.emptyList();
            this.logs_ = Collections.emptyList();
            this.metrics_ = Collections.emptyList();
            this.monitoredResources_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Service.alwaysUseFieldBuilders) {
                getApisFieldBuilder();
                getTypesFieldBuilder();
                getEnumsFieldBuilder();
                getEndpointsFieldBuilder();
                getLogsFieldBuilder();
                getMetricsFieldBuilder();
                getMonitoredResourcesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.title_ = "";
            this.producerProjectId_ = "";
            this.id_ = "";
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.apis_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV32 = this.typesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.types_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV33 = this.enumsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                this.enums_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV33.clear();
            }
            if (this.documentationBuilder_ == null) {
                this.documentation_ = null;
            } else {
                this.documentation_ = null;
                this.documentationBuilder_ = null;
            }
            if (this.backendBuilder_ == null) {
                this.backend_ = null;
            } else {
                this.backend_ = null;
                this.backendBuilder_ = null;
            }
            if (this.httpBuilder_ == null) {
                this.http_ = null;
            } else {
                this.http_ = null;
                this.httpBuilder_ = null;
            }
            if (this.quotaBuilder_ == null) {
                this.quota_ = null;
            } else {
                this.quota_ = null;
                this.quotaBuilder_ = null;
            }
            if (this.authenticationBuilder_ == null) {
                this.authentication_ = null;
            } else {
                this.authentication_ = null;
                this.authenticationBuilder_ = null;
            }
            if (this.contextBuilder_ == null) {
                this.context_ = null;
            } else {
                this.context_ = null;
                this.contextBuilder_ = null;
            }
            if (this.usageBuilder_ == null) {
                this.usage_ = null;
            } else {
                this.usage_ = null;
                this.usageBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV34 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV34 == null) {
                this.endpoints_ = Collections.emptyList();
                this.bitField0_ &= -9;
            } else {
                repeatedFieldBuilderV34.clear();
            }
            if (this.controlBuilder_ == null) {
                this.control_ = null;
            } else {
                this.control_ = null;
                this.controlBuilder_ = null;
            }
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV35 = this.logsBuilder_;
            if (repeatedFieldBuilderV35 == null) {
                this.logs_ = Collections.emptyList();
                this.bitField0_ &= -17;
            } else {
                repeatedFieldBuilderV35.clear();
            }
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV36 = this.metricsBuilder_;
            if (repeatedFieldBuilderV36 == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= -33;
            } else {
                repeatedFieldBuilderV36.clear();
            }
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV37 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV37 == null) {
                this.monitoredResources_ = Collections.emptyList();
                this.bitField0_ &= -65;
            } else {
                repeatedFieldBuilderV37.clear();
            }
            if (this.billingBuilder_ == null) {
                this.billing_ = null;
            } else {
                this.billing_ = null;
                this.billingBuilder_ = null;
            }
            if (this.loggingBuilder_ == null) {
                this.logging_ = null;
            } else {
                this.logging_ = null;
                this.loggingBuilder_ = null;
            }
            if (this.monitoringBuilder_ == null) {
                this.monitoring_ = null;
            } else {
                this.monitoring_ = null;
                this.monitoringBuilder_ = null;
            }
            if (this.systemParametersBuilder_ == null) {
                this.systemParameters_ = null;
            } else {
                this.systemParameters_ = null;
                this.systemParametersBuilder_ = null;
            }
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = null;
            } else {
                this.sourceInfo_ = null;
                this.sourceInfoBuilder_ = null;
            }
            if (this.configVersionBuilder_ == null) {
                this.configVersion_ = null;
            } else {
                this.configVersion_ = null;
                this.configVersionBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ServiceProto.internal_static_google_api_Service_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Service getDefaultInstanceForType() {
            return Service.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Service build() {
            Service buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Service buildPartial() {
            Service service = new Service(this);
            int i = this.bitField0_;
            service.name_ = this.name_;
            service.title_ = this.title_;
            service.producerProjectId_ = this.producerProjectId_;
            service.id_ = this.id_;
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                service.apis_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.apis_ = Collections.unmodifiableList(this.apis_);
                    this.bitField0_ &= -2;
                }
                service.apis_ = this.apis_;
            }
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV32 = this.typesBuilder_;
            if (repeatedFieldBuilderV32 != null) {
                service.types_ = repeatedFieldBuilderV32.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.types_ = Collections.unmodifiableList(this.types_);
                    this.bitField0_ &= -3;
                }
                service.types_ = this.types_;
            }
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV33 = this.enumsBuilder_;
            if (repeatedFieldBuilderV33 != null) {
                service.enums_ = repeatedFieldBuilderV33.build();
            } else {
                if ((this.bitField0_ & 4) != 0) {
                    this.enums_ = Collections.unmodifiableList(this.enums_);
                    this.bitField0_ &= -5;
                }
                service.enums_ = this.enums_;
            }
            SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 == null) {
                service.documentation_ = this.documentation_;
            } else {
                service.documentation_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> singleFieldBuilderV32 = this.backendBuilder_;
            if (singleFieldBuilderV32 == null) {
                service.backend_ = this.backend_;
            } else {
                service.backend_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV33 = this.httpBuilder_;
            if (singleFieldBuilderV33 == null) {
                service.http_ = this.http_;
            } else {
                service.http_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> singleFieldBuilderV34 = this.quotaBuilder_;
            if (singleFieldBuilderV34 == null) {
                service.quota_ = this.quota_;
            } else {
                service.quota_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV35 = this.authenticationBuilder_;
            if (singleFieldBuilderV35 == null) {
                service.authentication_ = this.authentication_;
            } else {
                service.authentication_ = singleFieldBuilderV35.build();
            }
            SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> singleFieldBuilderV36 = this.contextBuilder_;
            if (singleFieldBuilderV36 == null) {
                service.context_ = this.context_;
            } else {
                service.context_ = singleFieldBuilderV36.build();
            }
            SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> singleFieldBuilderV37 = this.usageBuilder_;
            if (singleFieldBuilderV37 == null) {
                service.usage_ = this.usage_;
            } else {
                service.usage_ = singleFieldBuilderV37.build();
            }
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV34 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV34 != null) {
                service.endpoints_ = repeatedFieldBuilderV34.build();
            } else {
                if ((this.bitField0_ & 8) != 0) {
                    this.endpoints_ = Collections.unmodifiableList(this.endpoints_);
                    this.bitField0_ &= -9;
                }
                service.endpoints_ = this.endpoints_;
            }
            SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> singleFieldBuilderV38 = this.controlBuilder_;
            if (singleFieldBuilderV38 == null) {
                service.control_ = this.control_;
            } else {
                service.control_ = singleFieldBuilderV38.build();
            }
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV35 = this.logsBuilder_;
            if (repeatedFieldBuilderV35 != null) {
                service.logs_ = repeatedFieldBuilderV35.build();
            } else {
                if ((this.bitField0_ & 16) != 0) {
                    this.logs_ = Collections.unmodifiableList(this.logs_);
                    this.bitField0_ &= -17;
                }
                service.logs_ = this.logs_;
            }
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV36 = this.metricsBuilder_;
            if (repeatedFieldBuilderV36 != null) {
                service.metrics_ = repeatedFieldBuilderV36.build();
            } else {
                if ((this.bitField0_ & 32) != 0) {
                    this.metrics_ = Collections.unmodifiableList(this.metrics_);
                    this.bitField0_ &= -33;
                }
                service.metrics_ = this.metrics_;
            }
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV37 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV37 != null) {
                service.monitoredResources_ = repeatedFieldBuilderV37.build();
            } else {
                if ((this.bitField0_ & 64) != 0) {
                    this.monitoredResources_ = Collections.unmodifiableList(this.monitoredResources_);
                    this.bitField0_ &= -65;
                }
                service.monitoredResources_ = this.monitoredResources_;
            }
            SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> singleFieldBuilderV39 = this.billingBuilder_;
            if (singleFieldBuilderV39 == null) {
                service.billing_ = this.billing_;
            } else {
                service.billing_ = singleFieldBuilderV39.build();
            }
            SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> singleFieldBuilderV310 = this.loggingBuilder_;
            if (singleFieldBuilderV310 == null) {
                service.logging_ = this.logging_;
            } else {
                service.logging_ = singleFieldBuilderV310.build();
            }
            SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV311 = this.monitoringBuilder_;
            if (singleFieldBuilderV311 == null) {
                service.monitoring_ = this.monitoring_;
            } else {
                service.monitoring_ = singleFieldBuilderV311.build();
            }
            SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV312 = this.systemParametersBuilder_;
            if (singleFieldBuilderV312 == null) {
                service.systemParameters_ = this.systemParameters_;
            } else {
                service.systemParameters_ = singleFieldBuilderV312.build();
            }
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV313 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV313 == null) {
                service.sourceInfo_ = this.sourceInfo_;
            } else {
                service.sourceInfo_ = singleFieldBuilderV313.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV314 = this.configVersionBuilder_;
            if (singleFieldBuilderV314 == null) {
                service.configVersion_ = this.configVersion_;
            } else {
                service.configVersion_ = singleFieldBuilderV314.build();
            }
            onBuilt();
            return service;
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
            if (message instanceof Service) {
                return mergeFrom((Service) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Service service) {
            if (service == Service.getDefaultInstance()) {
                return this;
            }
            if (!service.getName().isEmpty()) {
                this.name_ = service.name_;
                onChanged();
            }
            if (!service.getTitle().isEmpty()) {
                this.title_ = service.title_;
                onChanged();
            }
            if (!service.getProducerProjectId().isEmpty()) {
                this.producerProjectId_ = service.producerProjectId_;
                onChanged();
            }
            if (!service.getId().isEmpty()) {
                this.id_ = service.id_;
                onChanged();
            }
            if (this.apisBuilder_ == null) {
                if (!service.apis_.isEmpty()) {
                    if (this.apis_.isEmpty()) {
                        this.apis_ = service.apis_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureApisIsMutable();
                        this.apis_.addAll(service.apis_);
                    }
                    onChanged();
                }
            } else if (!service.apis_.isEmpty()) {
                if (!this.apisBuilder_.isEmpty()) {
                    this.apisBuilder_.addAllMessages(service.apis_);
                } else {
                    this.apisBuilder_.dispose();
                    this.apisBuilder_ = null;
                    this.apis_ = service.apis_;
                    this.bitField0_ &= -2;
                    this.apisBuilder_ = Service.alwaysUseFieldBuilders ? getApisFieldBuilder() : null;
                }
            }
            if (this.typesBuilder_ == null) {
                if (!service.types_.isEmpty()) {
                    if (this.types_.isEmpty()) {
                        this.types_ = service.types_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureTypesIsMutable();
                        this.types_.addAll(service.types_);
                    }
                    onChanged();
                }
            } else if (!service.types_.isEmpty()) {
                if (!this.typesBuilder_.isEmpty()) {
                    this.typesBuilder_.addAllMessages(service.types_);
                } else {
                    this.typesBuilder_.dispose();
                    this.typesBuilder_ = null;
                    this.types_ = service.types_;
                    this.bitField0_ &= -3;
                    this.typesBuilder_ = Service.alwaysUseFieldBuilders ? getTypesFieldBuilder() : null;
                }
            }
            if (this.enumsBuilder_ == null) {
                if (!service.enums_.isEmpty()) {
                    if (this.enums_.isEmpty()) {
                        this.enums_ = service.enums_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureEnumsIsMutable();
                        this.enums_.addAll(service.enums_);
                    }
                    onChanged();
                }
            } else if (!service.enums_.isEmpty()) {
                if (!this.enumsBuilder_.isEmpty()) {
                    this.enumsBuilder_.addAllMessages(service.enums_);
                } else {
                    this.enumsBuilder_.dispose();
                    this.enumsBuilder_ = null;
                    this.enums_ = service.enums_;
                    this.bitField0_ &= -5;
                    this.enumsBuilder_ = Service.alwaysUseFieldBuilders ? getEnumsFieldBuilder() : null;
                }
            }
            if (service.hasDocumentation()) {
                mergeDocumentation(service.getDocumentation());
            }
            if (service.hasBackend()) {
                mergeBackend(service.getBackend());
            }
            if (service.hasHttp()) {
                mergeHttp(service.getHttp());
            }
            if (service.hasQuota()) {
                mergeQuota(service.getQuota());
            }
            if (service.hasAuthentication()) {
                mergeAuthentication(service.getAuthentication());
            }
            if (service.hasContext()) {
                mergeContext(service.getContext());
            }
            if (service.hasUsage()) {
                mergeUsage(service.getUsage());
            }
            if (this.endpointsBuilder_ == null) {
                if (!service.endpoints_.isEmpty()) {
                    if (this.endpoints_.isEmpty()) {
                        this.endpoints_ = service.endpoints_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureEndpointsIsMutable();
                        this.endpoints_.addAll(service.endpoints_);
                    }
                    onChanged();
                }
            } else if (!service.endpoints_.isEmpty()) {
                if (!this.endpointsBuilder_.isEmpty()) {
                    this.endpointsBuilder_.addAllMessages(service.endpoints_);
                } else {
                    this.endpointsBuilder_.dispose();
                    this.endpointsBuilder_ = null;
                    this.endpoints_ = service.endpoints_;
                    this.bitField0_ &= -9;
                    this.endpointsBuilder_ = Service.alwaysUseFieldBuilders ? getEndpointsFieldBuilder() : null;
                }
            }
            if (service.hasControl()) {
                mergeControl(service.getControl());
            }
            if (this.logsBuilder_ == null) {
                if (!service.logs_.isEmpty()) {
                    if (this.logs_.isEmpty()) {
                        this.logs_ = service.logs_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureLogsIsMutable();
                        this.logs_.addAll(service.logs_);
                    }
                    onChanged();
                }
            } else if (!service.logs_.isEmpty()) {
                if (!this.logsBuilder_.isEmpty()) {
                    this.logsBuilder_.addAllMessages(service.logs_);
                } else {
                    this.logsBuilder_.dispose();
                    this.logsBuilder_ = null;
                    this.logs_ = service.logs_;
                    this.bitField0_ &= -17;
                    this.logsBuilder_ = Service.alwaysUseFieldBuilders ? getLogsFieldBuilder() : null;
                }
            }
            if (this.metricsBuilder_ == null) {
                if (!service.metrics_.isEmpty()) {
                    if (this.metrics_.isEmpty()) {
                        this.metrics_ = service.metrics_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureMetricsIsMutable();
                        this.metrics_.addAll(service.metrics_);
                    }
                    onChanged();
                }
            } else if (!service.metrics_.isEmpty()) {
                if (!this.metricsBuilder_.isEmpty()) {
                    this.metricsBuilder_.addAllMessages(service.metrics_);
                } else {
                    this.metricsBuilder_.dispose();
                    this.metricsBuilder_ = null;
                    this.metrics_ = service.metrics_;
                    this.bitField0_ &= -33;
                    this.metricsBuilder_ = Service.alwaysUseFieldBuilders ? getMetricsFieldBuilder() : null;
                }
            }
            if (this.monitoredResourcesBuilder_ == null) {
                if (!service.monitoredResources_.isEmpty()) {
                    if (this.monitoredResources_.isEmpty()) {
                        this.monitoredResources_ = service.monitoredResources_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureMonitoredResourcesIsMutable();
                        this.monitoredResources_.addAll(service.monitoredResources_);
                    }
                    onChanged();
                }
            } else if (!service.monitoredResources_.isEmpty()) {
                if (!this.monitoredResourcesBuilder_.isEmpty()) {
                    this.monitoredResourcesBuilder_.addAllMessages(service.monitoredResources_);
                } else {
                    this.monitoredResourcesBuilder_.dispose();
                    this.monitoredResourcesBuilder_ = null;
                    this.monitoredResources_ = service.monitoredResources_;
                    this.bitField0_ &= -65;
                    this.monitoredResourcesBuilder_ = Service.alwaysUseFieldBuilders ? getMonitoredResourcesFieldBuilder() : null;
                }
            }
            if (service.hasBilling()) {
                mergeBilling(service.getBilling());
            }
            if (service.hasLogging()) {
                mergeLogging(service.getLogging());
            }
            if (service.hasMonitoring()) {
                mergeMonitoring(service.getMonitoring());
            }
            if (service.hasSystemParameters()) {
                mergeSystemParameters(service.getSystemParameters());
            }
            if (service.hasSourceInfo()) {
                mergeSourceInfo(service.getSourceInfo());
            }
            if (service.hasConfigVersion()) {
                mergeConfigVersion(service.getConfigVersion());
            }
            mergeUnknownFields(service.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Service service = null;
            try {
                try {
                    Service service2 = (Service) Service.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (service2 != null) {
                        mergeFrom(service2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Service service3 = (Service) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        service = service3;
                        if (service != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (service != null) {
                    mergeFrom(service);
                }
                throw th;
            }
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.ServiceOrBuilder
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
            this.name_ = Service.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Service.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getTitle() {
            Object obj = this.title_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.title_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getTitleBytes() {
            Object obj = this.title_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.title_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTitle(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.title_ = str;
            onChanged();
            return this;
        }

        public Builder clearTitle() {
            this.title_ = Service.getDefaultInstance().getTitle();
            onChanged();
            return this;
        }

        public Builder setTitleBytes(ByteString byteString) {
            if (byteString != null) {
                Service.checkByteStringIsUtf8(byteString);
                this.title_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getProducerProjectId() {
            Object obj = this.producerProjectId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.producerProjectId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getProducerProjectIdBytes() {
            Object obj = this.producerProjectId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.producerProjectId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setProducerProjectId(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.producerProjectId_ = str;
            onChanged();
            return this;
        }

        public Builder clearProducerProjectId() {
            this.producerProjectId_ = Service.getDefaultInstance().getProducerProjectId();
            onChanged();
            return this;
        }

        public Builder setProducerProjectIdBytes(ByteString byteString) {
            if (byteString != null) {
                Service.checkByteStringIsUtf8(byteString);
                this.producerProjectId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getId() {
            Object obj = this.id_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.id_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getIdBytes() {
            Object obj = this.id_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.id_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setId(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.id_ = str;
            onChanged();
            return this;
        }

        public Builder clearId() {
            this.id_ = Service.getDefaultInstance().getId();
            onChanged();
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            if (byteString != null) {
                Service.checkByteStringIsUtf8(byteString);
                this.id_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureApisIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.apis_ = new ArrayList(this.apis_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Api> getApisList() {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.apis_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getApisCount() {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.apis_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Api getApis(int i) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.apis_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setApis(int i, Api api) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, api);
            } else {
                if (api == null) {
                    throw new NullPointerException();
                }
                ensureApisIsMutable();
                this.apis_.set(i, api);
                onChanged();
            }
            return this;
        }

        public Builder setApis(int i, Api.Builder builder) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                this.apis_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addApis(Api api) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(api);
            } else {
                if (api == null) {
                    throw new NullPointerException();
                }
                ensureApisIsMutable();
                this.apis_.add(api);
                onChanged();
            }
            return this;
        }

        public Builder addApis(int i, Api api) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, api);
            } else {
                if (api == null) {
                    throw new NullPointerException();
                }
                ensureApisIsMutable();
                this.apis_.add(i, api);
                onChanged();
            }
            return this;
        }

        public Builder addApis(Api.Builder builder) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                this.apis_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addApis(int i, Api.Builder builder) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                this.apis_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllApis(Iterable<? extends Api> iterable) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.apis_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearApis() {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.apis_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeApis(int i) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                this.apis_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Api.Builder getApisBuilder(int i) {
            return getApisFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public ApiOrBuilder getApisOrBuilder(int i) {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.apis_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<? extends ApiOrBuilder> getApisOrBuilderList() {
            RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.apis_);
        }

        public Api.Builder addApisBuilder() {
            return getApisFieldBuilder().addBuilder(Api.getDefaultInstance());
        }

        public Api.Builder addApisBuilder(int i) {
            return getApisFieldBuilder().addBuilder(i, Api.getDefaultInstance());
        }

        public List<Api.Builder> getApisBuilderList() {
            return getApisFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Api, Api.Builder, ApiOrBuilder> getApisFieldBuilder() {
            if (this.apisBuilder_ == null) {
                this.apisBuilder_ = new RepeatedFieldBuilderV3<>(this.apis_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.apis_ = null;
            }
            return this.apisBuilder_;
        }

        private void ensureTypesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.types_ = new ArrayList(this.types_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Type> getTypesList() {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.types_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getTypesCount() {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.types_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Type getTypes(int i) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.types_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setTypes(int i, Type type) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, type);
            } else {
                if (type == null) {
                    throw new NullPointerException();
                }
                ensureTypesIsMutable();
                this.types_.set(i, type);
                onChanged();
            }
            return this;
        }

        public Builder setTypes(int i, Type.Builder builder) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                this.types_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addTypes(Type type) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(type);
            } else {
                if (type == null) {
                    throw new NullPointerException();
                }
                ensureTypesIsMutable();
                this.types_.add(type);
                onChanged();
            }
            return this;
        }

        public Builder addTypes(int i, Type type) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, type);
            } else {
                if (type == null) {
                    throw new NullPointerException();
                }
                ensureTypesIsMutable();
                this.types_.add(i, type);
                onChanged();
            }
            return this;
        }

        public Builder addTypes(Type.Builder builder) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                this.types_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addTypes(int i, Type.Builder builder) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                this.types_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllTypes(Iterable<? extends Type> iterable) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.types_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearTypes() {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.types_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeTypes(int i) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                this.types_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Type.Builder getTypesBuilder(int i) {
            return getTypesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public TypeOrBuilder getTypesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.types_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
            RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.types_);
        }

        public Type.Builder addTypesBuilder() {
            return getTypesFieldBuilder().addBuilder(Type.getDefaultInstance());
        }

        public Type.Builder addTypesBuilder(int i) {
            return getTypesFieldBuilder().addBuilder(i, Type.getDefaultInstance());
        }

        public List<Type.Builder> getTypesBuilderList() {
            return getTypesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Type, Type.Builder, TypeOrBuilder> getTypesFieldBuilder() {
            if (this.typesBuilder_ == null) {
                this.typesBuilder_ = new RepeatedFieldBuilderV3<>(this.types_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.types_ = null;
            }
            return this.typesBuilder_;
        }

        private void ensureEnumsIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.enums_ = new ArrayList(this.enums_);
                this.bitField0_ |= 4;
            }
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Enum> getEnumsList() {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.enums_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getEnumsCount() {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.enums_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Enum getEnums(int i) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.enums_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEnums(int i, Enum r3) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, r3);
            } else {
                if (r3 == null) {
                    throw new NullPointerException();
                }
                ensureEnumsIsMutable();
                this.enums_.set(i, r3);
                onChanged();
            }
            return this;
        }

        public Builder setEnums(int i, Enum.Builder builder) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                this.enums_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEnums(Enum r2) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(r2);
            } else {
                if (r2 == null) {
                    throw new NullPointerException();
                }
                ensureEnumsIsMutable();
                this.enums_.add(r2);
                onChanged();
            }
            return this;
        }

        public Builder addEnums(int i, Enum r3) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, r3);
            } else {
                if (r3 == null) {
                    throw new NullPointerException();
                }
                ensureEnumsIsMutable();
                this.enums_.add(i, r3);
                onChanged();
            }
            return this;
        }

        public Builder addEnums(Enum.Builder builder) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                this.enums_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEnums(int i, Enum.Builder builder) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                this.enums_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEnums(Iterable<? extends Enum> iterable) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.enums_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEnums() {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.enums_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEnums(int i) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                this.enums_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Enum.Builder getEnumsBuilder(int i) {
            return getEnumsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public EnumOrBuilder getEnumsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.enums_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
            RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.enums_);
        }

        public Enum.Builder addEnumsBuilder() {
            return getEnumsFieldBuilder().addBuilder(Enum.getDefaultInstance());
        }

        public Enum.Builder addEnumsBuilder(int i) {
            return getEnumsFieldBuilder().addBuilder(i, Enum.getDefaultInstance());
        }

        public List<Enum.Builder> getEnumsBuilderList() {
            return getEnumsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Enum, Enum.Builder, EnumOrBuilder> getEnumsFieldBuilder() {
            if (this.enumsBuilder_ == null) {
                this.enumsBuilder_ = new RepeatedFieldBuilderV3<>(this.enums_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.enums_ = null;
            }
            return this.enumsBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasDocumentation() {
            return (this.documentationBuilder_ == null && this.documentation_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Documentation getDocumentation() {
            SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 == null) {
                Documentation documentation = this.documentation_;
                return documentation == null ? Documentation.getDefaultInstance() : documentation;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setDocumentation(Documentation documentation) {
            SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(documentation);
            } else {
                if (documentation == null) {
                    throw new NullPointerException();
                }
                this.documentation_ = documentation;
                onChanged();
            }
            return this;
        }

        public Builder setDocumentation(Documentation.Builder builder) {
            SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.documentation_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeDocumentation(Documentation documentation) {
            SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 == null) {
                Documentation documentation2 = this.documentation_;
                if (documentation2 != null) {
                    this.documentation_ = Documentation.newBuilder(documentation2).mergeFrom(documentation).buildPartial();
                } else {
                    this.documentation_ = documentation;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(documentation);
            }
            return this;
        }

        public Builder clearDocumentation() {
            if (this.documentationBuilder_ == null) {
                this.documentation_ = null;
                onChanged();
            } else {
                this.documentation_ = null;
                this.documentationBuilder_ = null;
            }
            return this;
        }

        public Documentation.Builder getDocumentationBuilder() {
            onChanged();
            return getDocumentationFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public DocumentationOrBuilder getDocumentationOrBuilder() {
            SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Documentation documentation = this.documentation_;
            return documentation == null ? Documentation.getDefaultInstance() : documentation;
        }

        private SingleFieldBuilderV3<Documentation, Documentation.Builder, DocumentationOrBuilder> getDocumentationFieldBuilder() {
            if (this.documentationBuilder_ == null) {
                this.documentationBuilder_ = new SingleFieldBuilderV3<>(getDocumentation(), getParentForChildren(), isClean());
                this.documentation_ = null;
            }
            return this.documentationBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasBackend() {
            return (this.backendBuilder_ == null && this.backend_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Backend getBackend() {
            SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 == null) {
                Backend backend = this.backend_;
                return backend == null ? Backend.getDefaultInstance() : backend;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setBackend(Backend backend) {
            SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(backend);
            } else {
                if (backend == null) {
                    throw new NullPointerException();
                }
                this.backend_ = backend;
                onChanged();
            }
            return this;
        }

        public Builder setBackend(Backend.Builder builder) {
            SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.backend_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeBackend(Backend backend) {
            SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 == null) {
                Backend backend2 = this.backend_;
                if (backend2 != null) {
                    this.backend_ = Backend.newBuilder(backend2).mergeFrom(backend).buildPartial();
                } else {
                    this.backend_ = backend;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(backend);
            }
            return this;
        }

        public Builder clearBackend() {
            if (this.backendBuilder_ == null) {
                this.backend_ = null;
                onChanged();
            } else {
                this.backend_ = null;
                this.backendBuilder_ = null;
            }
            return this;
        }

        public Backend.Builder getBackendBuilder() {
            onChanged();
            return getBackendFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public BackendOrBuilder getBackendOrBuilder() {
            SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Backend backend = this.backend_;
            return backend == null ? Backend.getDefaultInstance() : backend;
        }

        private SingleFieldBuilderV3<Backend, Backend.Builder, BackendOrBuilder> getBackendFieldBuilder() {
            if (this.backendBuilder_ == null) {
                this.backendBuilder_ = new SingleFieldBuilderV3<>(getBackend(), getParentForChildren(), isClean());
                this.backend_ = null;
            }
            return this.backendBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasHttp() {
            return (this.httpBuilder_ == null && this.http_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Http getHttp() {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                Http http = this.http_;
                return http == null ? Http.getDefaultInstance() : http;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setHttp(Http http) {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(http);
            } else {
                if (http == null) {
                    throw new NullPointerException();
                }
                this.http_ = http;
                onChanged();
            }
            return this;
        }

        public Builder setHttp(Http.Builder builder) {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.http_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeHttp(Http http) {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                Http http2 = this.http_;
                if (http2 != null) {
                    this.http_ = Http.newBuilder(http2).mergeFrom(http).buildPartial();
                } else {
                    this.http_ = http;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(http);
            }
            return this;
        }

        public Builder clearHttp() {
            if (this.httpBuilder_ == null) {
                this.http_ = null;
                onChanged();
            } else {
                this.http_ = null;
                this.httpBuilder_ = null;
            }
            return this;
        }

        public Http.Builder getHttpBuilder() {
            onChanged();
            return getHttpFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public HttpOrBuilder getHttpOrBuilder() {
            SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Http http = this.http_;
            return http == null ? Http.getDefaultInstance() : http;
        }

        private SingleFieldBuilderV3<Http, Http.Builder, HttpOrBuilder> getHttpFieldBuilder() {
            if (this.httpBuilder_ == null) {
                this.httpBuilder_ = new SingleFieldBuilderV3<>(getHttp(), getParentForChildren(), isClean());
                this.http_ = null;
            }
            return this.httpBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasQuota() {
            return (this.quotaBuilder_ == null && this.quota_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Quota getQuota() {
            SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 == null) {
                Quota quota = this.quota_;
                return quota == null ? Quota.getDefaultInstance() : quota;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setQuota(Quota quota) {
            SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(quota);
            } else {
                if (quota == null) {
                    throw new NullPointerException();
                }
                this.quota_ = quota;
                onChanged();
            }
            return this;
        }

        public Builder setQuota(Quota.Builder builder) {
            SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.quota_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeQuota(Quota quota) {
            SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 == null) {
                Quota quota2 = this.quota_;
                if (quota2 != null) {
                    this.quota_ = Quota.newBuilder(quota2).mergeFrom(quota).buildPartial();
                } else {
                    this.quota_ = quota;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(quota);
            }
            return this;
        }

        public Builder clearQuota() {
            if (this.quotaBuilder_ == null) {
                this.quota_ = null;
                onChanged();
            } else {
                this.quota_ = null;
                this.quotaBuilder_ = null;
            }
            return this;
        }

        public Quota.Builder getQuotaBuilder() {
            onChanged();
            return getQuotaFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public QuotaOrBuilder getQuotaOrBuilder() {
            SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Quota quota = this.quota_;
            return quota == null ? Quota.getDefaultInstance() : quota;
        }

        private SingleFieldBuilderV3<Quota, Quota.Builder, QuotaOrBuilder> getQuotaFieldBuilder() {
            if (this.quotaBuilder_ == null) {
                this.quotaBuilder_ = new SingleFieldBuilderV3<>(getQuota(), getParentForChildren(), isClean());
                this.quota_ = null;
            }
            return this.quotaBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasAuthentication() {
            return (this.authenticationBuilder_ == null && this.authentication_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Authentication getAuthentication() {
            SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 == null) {
                Authentication authentication = this.authentication_;
                return authentication == null ? Authentication.getDefaultInstance() : authentication;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setAuthentication(Authentication authentication) {
            SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(authentication);
            } else {
                if (authentication == null) {
                    throw new NullPointerException();
                }
                this.authentication_ = authentication;
                onChanged();
            }
            return this;
        }

        public Builder setAuthentication(Authentication.Builder builder) {
            SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.authentication_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAuthentication(Authentication authentication) {
            SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 == null) {
                Authentication authentication2 = this.authentication_;
                if (authentication2 != null) {
                    this.authentication_ = Authentication.newBuilder(authentication2).mergeFrom(authentication).buildPartial();
                } else {
                    this.authentication_ = authentication;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(authentication);
            }
            return this;
        }

        public Builder clearAuthentication() {
            if (this.authenticationBuilder_ == null) {
                this.authentication_ = null;
                onChanged();
            } else {
                this.authentication_ = null;
                this.authenticationBuilder_ = null;
            }
            return this;
        }

        public Authentication.Builder getAuthenticationBuilder() {
            onChanged();
            return getAuthenticationFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public AuthenticationOrBuilder getAuthenticationOrBuilder() {
            SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Authentication authentication = this.authentication_;
            return authentication == null ? Authentication.getDefaultInstance() : authentication;
        }

        private SingleFieldBuilderV3<Authentication, Authentication.Builder, AuthenticationOrBuilder> getAuthenticationFieldBuilder() {
            if (this.authenticationBuilder_ == null) {
                this.authenticationBuilder_ = new SingleFieldBuilderV3<>(getAuthentication(), getParentForChildren(), isClean());
                this.authentication_ = null;
            }
            return this.authenticationBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasContext() {
            return (this.contextBuilder_ == null && this.context_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Context getContext() {
            SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 == null) {
                Context context = this.context_;
                return context == null ? Context.getDefaultInstance() : context;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setContext(Context context) {
            SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(context);
            } else {
                if (context == null) {
                    throw new NullPointerException();
                }
                this.context_ = context;
                onChanged();
            }
            return this;
        }

        public Builder setContext(Context.Builder builder) {
            SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.context_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeContext(Context context) {
            SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 == null) {
                Context context2 = this.context_;
                if (context2 != null) {
                    this.context_ = Context.newBuilder(context2).mergeFrom(context).buildPartial();
                } else {
                    this.context_ = context;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(context);
            }
            return this;
        }

        public Builder clearContext() {
            if (this.contextBuilder_ == null) {
                this.context_ = null;
                onChanged();
            } else {
                this.context_ = null;
                this.contextBuilder_ = null;
            }
            return this;
        }

        public Context.Builder getContextBuilder() {
            onChanged();
            return getContextFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ContextOrBuilder getContextOrBuilder() {
            SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Context context = this.context_;
            return context == null ? Context.getDefaultInstance() : context;
        }

        private SingleFieldBuilderV3<Context, Context.Builder, ContextOrBuilder> getContextFieldBuilder() {
            if (this.contextBuilder_ == null) {
                this.contextBuilder_ = new SingleFieldBuilderV3<>(getContext(), getParentForChildren(), isClean());
                this.context_ = null;
            }
            return this.contextBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasUsage() {
            return (this.usageBuilder_ == null && this.usage_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Usage getUsage() {
            SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 == null) {
                Usage usage = this.usage_;
                return usage == null ? Usage.getDefaultInstance() : usage;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setUsage(Usage usage) {
            SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(usage);
            } else {
                if (usage == null) {
                    throw new NullPointerException();
                }
                this.usage_ = usage;
                onChanged();
            }
            return this;
        }

        public Builder setUsage(Usage.Builder builder) {
            SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.usage_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUsage(Usage usage) {
            SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 == null) {
                Usage usage2 = this.usage_;
                if (usage2 != null) {
                    this.usage_ = Usage.newBuilder(usage2).mergeFrom(usage).buildPartial();
                } else {
                    this.usage_ = usage;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(usage);
            }
            return this;
        }

        public Builder clearUsage() {
            if (this.usageBuilder_ == null) {
                this.usage_ = null;
                onChanged();
            } else {
                this.usage_ = null;
                this.usageBuilder_ = null;
            }
            return this;
        }

        public Usage.Builder getUsageBuilder() {
            onChanged();
            return getUsageFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public UsageOrBuilder getUsageOrBuilder() {
            SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Usage usage = this.usage_;
            return usage == null ? Usage.getDefaultInstance() : usage;
        }

        private SingleFieldBuilderV3<Usage, Usage.Builder, UsageOrBuilder> getUsageFieldBuilder() {
            if (this.usageBuilder_ == null) {
                this.usageBuilder_ = new SingleFieldBuilderV3<>(getUsage(), getParentForChildren(), isClean());
                this.usage_ = null;
            }
            return this.usageBuilder_;
        }

        private void ensureEndpointsIsMutable() {
            if ((this.bitField0_ & 8) == 0) {
                this.endpoints_ = new ArrayList(this.endpoints_);
                this.bitField0_ |= 8;
            }
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Endpoint> getEndpointsList() {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.endpoints_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getEndpointsCount() {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.endpoints_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Endpoint getEndpoints(int i) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.endpoints_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEndpoints(int i, Endpoint endpoint) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, endpoint);
            } else {
                if (endpoint == null) {
                    throw new NullPointerException();
                }
                ensureEndpointsIsMutable();
                this.endpoints_.set(i, endpoint);
                onChanged();
            }
            return this;
        }

        public Builder setEndpoints(int i, Endpoint.Builder builder) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEndpoints(Endpoint endpoint) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(endpoint);
            } else {
                if (endpoint == null) {
                    throw new NullPointerException();
                }
                ensureEndpointsIsMutable();
                this.endpoints_.add(endpoint);
                onChanged();
            }
            return this;
        }

        public Builder addEndpoints(int i, Endpoint endpoint) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, endpoint);
            } else {
                if (endpoint == null) {
                    throw new NullPointerException();
                }
                ensureEndpointsIsMutable();
                this.endpoints_.add(i, endpoint);
                onChanged();
            }
            return this;
        }

        public Builder addEndpoints(Endpoint.Builder builder) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEndpoints(int i, Endpoint.Builder builder) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends Endpoint> iterable) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.endpoints_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEndpoints() {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.endpoints_ = Collections.emptyList();
                this.bitField0_ &= -9;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEndpoints(int i) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Endpoint.Builder getEndpointsBuilder(int i) {
            return getEndpointsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public EndpointOrBuilder getEndpointsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.endpoints_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
            RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.endpoints_);
        }

        public Endpoint.Builder addEndpointsBuilder() {
            return getEndpointsFieldBuilder().addBuilder(Endpoint.getDefaultInstance());
        }

        public Endpoint.Builder addEndpointsBuilder(int i) {
            return getEndpointsFieldBuilder().addBuilder(i, Endpoint.getDefaultInstance());
        }

        public List<Endpoint.Builder> getEndpointsBuilderList() {
            return getEndpointsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getEndpointsFieldBuilder() {
            if (this.endpointsBuilder_ == null) {
                this.endpointsBuilder_ = new RepeatedFieldBuilderV3<>(this.endpoints_, (this.bitField0_ & 8) != 0, getParentForChildren(), isClean());
                this.endpoints_ = null;
            }
            return this.endpointsBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasControl() {
            return (this.controlBuilder_ == null && this.control_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Control getControl() {
            SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 == null) {
                Control control = this.control_;
                return control == null ? Control.getDefaultInstance() : control;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setControl(Control control) {
            SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(control);
            } else {
                if (control == null) {
                    throw new NullPointerException();
                }
                this.control_ = control;
                onChanged();
            }
            return this;
        }

        public Builder setControl(Control.Builder builder) {
            SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.control_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeControl(Control control) {
            SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 == null) {
                Control control2 = this.control_;
                if (control2 != null) {
                    this.control_ = Control.newBuilder(control2).mergeFrom(control).buildPartial();
                } else {
                    this.control_ = control;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(control);
            }
            return this;
        }

        public Builder clearControl() {
            if (this.controlBuilder_ == null) {
                this.control_ = null;
                onChanged();
            } else {
                this.control_ = null;
                this.controlBuilder_ = null;
            }
            return this;
        }

        public Control.Builder getControlBuilder() {
            onChanged();
            return getControlFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ControlOrBuilder getControlOrBuilder() {
            SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Control control = this.control_;
            return control == null ? Control.getDefaultInstance() : control;
        }

        private SingleFieldBuilderV3<Control, Control.Builder, ControlOrBuilder> getControlFieldBuilder() {
            if (this.controlBuilder_ == null) {
                this.controlBuilder_ = new SingleFieldBuilderV3<>(getControl(), getParentForChildren(), isClean());
                this.control_ = null;
            }
            return this.controlBuilder_;
        }

        private void ensureLogsIsMutable() {
            if ((this.bitField0_ & 16) == 0) {
                this.logs_ = new ArrayList(this.logs_);
                this.bitField0_ |= 16;
            }
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<LogDescriptor> getLogsList() {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.logs_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getLogsCount() {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.logs_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public LogDescriptor getLogs(int i) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.logs_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLogs(int i, LogDescriptor logDescriptor) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, logDescriptor);
            } else {
                if (logDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureLogsIsMutable();
                this.logs_.set(i, logDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder setLogs(int i, LogDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                this.logs_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLogs(LogDescriptor logDescriptor) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(logDescriptor);
            } else {
                if (logDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureLogsIsMutable();
                this.logs_.add(logDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder addLogs(int i, LogDescriptor logDescriptor) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, logDescriptor);
            } else {
                if (logDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureLogsIsMutable();
                this.logs_.add(i, logDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder addLogs(LogDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                this.logs_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLogs(int i, LogDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                this.logs_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLogs(Iterable<? extends LogDescriptor> iterable) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.logs_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLogs() {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.logs_ = Collections.emptyList();
                this.bitField0_ &= -17;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLogs(int i) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                this.logs_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public LogDescriptor.Builder getLogsBuilder(int i) {
            return getLogsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public LogDescriptorOrBuilder getLogsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.logs_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
            RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.logs_);
        }

        public LogDescriptor.Builder addLogsBuilder() {
            return getLogsFieldBuilder().addBuilder(LogDescriptor.getDefaultInstance());
        }

        public LogDescriptor.Builder addLogsBuilder(int i) {
            return getLogsFieldBuilder().addBuilder(i, LogDescriptor.getDefaultInstance());
        }

        public List<LogDescriptor.Builder> getLogsBuilderList() {
            return getLogsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LogDescriptor, LogDescriptor.Builder, LogDescriptorOrBuilder> getLogsFieldBuilder() {
            if (this.logsBuilder_ == null) {
                this.logsBuilder_ = new RepeatedFieldBuilderV3<>(this.logs_, (this.bitField0_ & 16) != 0, getParentForChildren(), isClean());
                this.logs_ = null;
            }
            return this.logsBuilder_;
        }

        private void ensureMetricsIsMutable() {
            if ((this.bitField0_ & 32) == 0) {
                this.metrics_ = new ArrayList(this.metrics_);
                this.bitField0_ |= 32;
            }
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<MetricDescriptor> getMetricsList() {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.metrics_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getMetricsCount() {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.metrics_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public MetricDescriptor getMetrics(int i) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.metrics_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setMetrics(int i, MetricDescriptor metricDescriptor) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, metricDescriptor);
            } else {
                if (metricDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureMetricsIsMutable();
                this.metrics_.set(i, metricDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder setMetrics(int i, MetricDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMetrics(MetricDescriptor metricDescriptor) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(metricDescriptor);
            } else {
                if (metricDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureMetricsIsMutable();
                this.metrics_.add(metricDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder addMetrics(int i, MetricDescriptor metricDescriptor) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, metricDescriptor);
            } else {
                if (metricDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureMetricsIsMutable();
                this.metrics_.add(i, metricDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder addMetrics(MetricDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMetrics(int i, MetricDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends MetricDescriptor> iterable) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.metrics_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMetrics() {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= -33;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeMetrics(int i) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public MetricDescriptor.Builder getMetricsBuilder(int i) {
            return getMetricsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public MetricDescriptorOrBuilder getMetricsOrBuilder(int i) {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.metrics_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
            RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.metrics_);
        }

        public MetricDescriptor.Builder addMetricsBuilder() {
            return getMetricsFieldBuilder().addBuilder(MetricDescriptor.getDefaultInstance());
        }

        public MetricDescriptor.Builder addMetricsBuilder(int i) {
            return getMetricsFieldBuilder().addBuilder(i, MetricDescriptor.getDefaultInstance());
        }

        public List<MetricDescriptor.Builder> getMetricsBuilderList() {
            return getMetricsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MetricDescriptor, MetricDescriptor.Builder, MetricDescriptorOrBuilder> getMetricsFieldBuilder() {
            if (this.metricsBuilder_ == null) {
                this.metricsBuilder_ = new RepeatedFieldBuilderV3<>(this.metrics_, (this.bitField0_ & 32) != 0, getParentForChildren(), isClean());
                this.metrics_ = null;
            }
            return this.metricsBuilder_;
        }

        private void ensureMonitoredResourcesIsMutable() {
            if ((this.bitField0_ & 64) == 0) {
                this.monitoredResources_ = new ArrayList(this.monitoredResources_);
                this.bitField0_ |= 64;
            }
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.monitoredResources_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getMonitoredResourcesCount() {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.monitoredResources_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public MonitoredResourceDescriptor getMonitoredResources(int i) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.monitoredResources_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, monitoredResourceDescriptor);
            } else {
                if (monitoredResourceDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.set(i, monitoredResourceDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder setMonitoredResources(int i, MonitoredResourceDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor monitoredResourceDescriptor) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(monitoredResourceDescriptor);
            } else {
                if (monitoredResourceDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(monitoredResourceDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder addMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, monitoredResourceDescriptor);
            } else {
                if (monitoredResourceDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(i, monitoredResourceDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMonitoredResources(int i, MonitoredResourceDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> iterable) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.monitoredResources_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMonitoredResources() {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.monitoredResources_ = Collections.emptyList();
                this.bitField0_ &= -65;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeMonitoredResources(int i) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public MonitoredResourceDescriptor.Builder getMonitoredResourcesBuilder(int i) {
            return getMonitoredResourcesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int i) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.monitoredResources_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.monitoredResources_);
        }

        public MonitoredResourceDescriptor.Builder addMonitoredResourcesBuilder() {
            return getMonitoredResourcesFieldBuilder().addBuilder(MonitoredResourceDescriptor.getDefaultInstance());
        }

        public MonitoredResourceDescriptor.Builder addMonitoredResourcesBuilder(int i) {
            return getMonitoredResourcesFieldBuilder().addBuilder(i, MonitoredResourceDescriptor.getDefaultInstance());
        }

        public List<MonitoredResourceDescriptor.Builder> getMonitoredResourcesBuilderList() {
            return getMonitoredResourcesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MonitoredResourceDescriptor, MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesFieldBuilder() {
            if (this.monitoredResourcesBuilder_ == null) {
                this.monitoredResourcesBuilder_ = new RepeatedFieldBuilderV3<>(this.monitoredResources_, (this.bitField0_ & 64) != 0, getParentForChildren(), isClean());
                this.monitoredResources_ = null;
            }
            return this.monitoredResourcesBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasBilling() {
            return (this.billingBuilder_ == null && this.billing_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Billing getBilling() {
            SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 == null) {
                Billing billing = this.billing_;
                return billing == null ? Billing.getDefaultInstance() : billing;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setBilling(Billing billing) {
            SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(billing);
            } else {
                if (billing == null) {
                    throw new NullPointerException();
                }
                this.billing_ = billing;
                onChanged();
            }
            return this;
        }

        public Builder setBilling(Billing.Builder builder) {
            SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.billing_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeBilling(Billing billing) {
            SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 == null) {
                Billing billing2 = this.billing_;
                if (billing2 != null) {
                    this.billing_ = Billing.newBuilder(billing2).mergeFrom(billing).buildPartial();
                } else {
                    this.billing_ = billing;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(billing);
            }
            return this;
        }

        public Builder clearBilling() {
            if (this.billingBuilder_ == null) {
                this.billing_ = null;
                onChanged();
            } else {
                this.billing_ = null;
                this.billingBuilder_ = null;
            }
            return this;
        }

        public Billing.Builder getBillingBuilder() {
            onChanged();
            return getBillingFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public BillingOrBuilder getBillingOrBuilder() {
            SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Billing billing = this.billing_;
            return billing == null ? Billing.getDefaultInstance() : billing;
        }

        private SingleFieldBuilderV3<Billing, Billing.Builder, BillingOrBuilder> getBillingFieldBuilder() {
            if (this.billingBuilder_ == null) {
                this.billingBuilder_ = new SingleFieldBuilderV3<>(getBilling(), getParentForChildren(), isClean());
                this.billing_ = null;
            }
            return this.billingBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasLogging() {
            return (this.loggingBuilder_ == null && this.logging_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Logging getLogging() {
            SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 == null) {
                Logging logging = this.logging_;
                return logging == null ? Logging.getDefaultInstance() : logging;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setLogging(Logging logging) {
            SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(logging);
            } else {
                if (logging == null) {
                    throw new NullPointerException();
                }
                this.logging_ = logging;
                onChanged();
            }
            return this;
        }

        public Builder setLogging(Logging.Builder builder) {
            SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.logging_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeLogging(Logging logging) {
            SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 == null) {
                Logging logging2 = this.logging_;
                if (logging2 != null) {
                    this.logging_ = Logging.newBuilder(logging2).mergeFrom(logging).buildPartial();
                } else {
                    this.logging_ = logging;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(logging);
            }
            return this;
        }

        public Builder clearLogging() {
            if (this.loggingBuilder_ == null) {
                this.logging_ = null;
                onChanged();
            } else {
                this.logging_ = null;
                this.loggingBuilder_ = null;
            }
            return this;
        }

        public Logging.Builder getLoggingBuilder() {
            onChanged();
            return getLoggingFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public LoggingOrBuilder getLoggingOrBuilder() {
            SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Logging logging = this.logging_;
            return logging == null ? Logging.getDefaultInstance() : logging;
        }

        private SingleFieldBuilderV3<Logging, Logging.Builder, LoggingOrBuilder> getLoggingFieldBuilder() {
            if (this.loggingBuilder_ == null) {
                this.loggingBuilder_ = new SingleFieldBuilderV3<>(getLogging(), getParentForChildren(), isClean());
                this.logging_ = null;
            }
            return this.loggingBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasMonitoring() {
            return (this.monitoringBuilder_ == null && this.monitoring_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public Monitoring getMonitoring() {
            SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 == null) {
                Monitoring monitoring = this.monitoring_;
                return monitoring == null ? Monitoring.getDefaultInstance() : monitoring;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setMonitoring(Monitoring monitoring) {
            SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(monitoring);
            } else {
                if (monitoring == null) {
                    throw new NullPointerException();
                }
                this.monitoring_ = monitoring;
                onChanged();
            }
            return this;
        }

        public Builder setMonitoring(Monitoring.Builder builder) {
            SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.monitoring_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMonitoring(Monitoring monitoring) {
            SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 == null) {
                Monitoring monitoring2 = this.monitoring_;
                if (monitoring2 != null) {
                    this.monitoring_ = Monitoring.newBuilder(monitoring2).mergeFrom(monitoring).buildPartial();
                } else {
                    this.monitoring_ = monitoring;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(monitoring);
            }
            return this;
        }

        public Builder clearMonitoring() {
            if (this.monitoringBuilder_ == null) {
                this.monitoring_ = null;
                onChanged();
            } else {
                this.monitoring_ = null;
                this.monitoringBuilder_ = null;
            }
            return this;
        }

        public Monitoring.Builder getMonitoringBuilder() {
            onChanged();
            return getMonitoringFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public MonitoringOrBuilder getMonitoringOrBuilder() {
            SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Monitoring monitoring = this.monitoring_;
            return monitoring == null ? Monitoring.getDefaultInstance() : monitoring;
        }

        private SingleFieldBuilderV3<Monitoring, Monitoring.Builder, MonitoringOrBuilder> getMonitoringFieldBuilder() {
            if (this.monitoringBuilder_ == null) {
                this.monitoringBuilder_ = new SingleFieldBuilderV3<>(getMonitoring(), getParentForChildren(), isClean());
                this.monitoring_ = null;
            }
            return this.monitoringBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasSystemParameters() {
            return (this.systemParametersBuilder_ == null && this.systemParameters_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public SystemParameters getSystemParameters() {
            SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                SystemParameters systemParameters = this.systemParameters_;
                return systemParameters == null ? SystemParameters.getDefaultInstance() : systemParameters;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSystemParameters(SystemParameters systemParameters) {
            SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(systemParameters);
            } else {
                if (systemParameters == null) {
                    throw new NullPointerException();
                }
                this.systemParameters_ = systemParameters;
                onChanged();
            }
            return this;
        }

        public Builder setSystemParameters(SystemParameters.Builder builder) {
            SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.systemParameters_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSystemParameters(SystemParameters systemParameters) {
            SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                SystemParameters systemParameters2 = this.systemParameters_;
                if (systemParameters2 != null) {
                    this.systemParameters_ = SystemParameters.newBuilder(systemParameters2).mergeFrom(systemParameters).buildPartial();
                } else {
                    this.systemParameters_ = systemParameters;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(systemParameters);
            }
            return this;
        }

        public Builder clearSystemParameters() {
            if (this.systemParametersBuilder_ == null) {
                this.systemParameters_ = null;
                onChanged();
            } else {
                this.systemParameters_ = null;
                this.systemParametersBuilder_ = null;
            }
            return this;
        }

        public SystemParameters.Builder getSystemParametersBuilder() {
            onChanged();
            return getSystemParametersFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public SystemParametersOrBuilder getSystemParametersOrBuilder() {
            SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SystemParameters systemParameters = this.systemParameters_;
            return systemParameters == null ? SystemParameters.getDefaultInstance() : systemParameters;
        }

        private SingleFieldBuilderV3<SystemParameters, SystemParameters.Builder, SystemParametersOrBuilder> getSystemParametersFieldBuilder() {
            if (this.systemParametersBuilder_ == null) {
                this.systemParametersBuilder_ = new SingleFieldBuilderV3<>(getSystemParameters(), getParentForChildren(), isClean());
                this.systemParameters_ = null;
            }
            return this.systemParametersBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasSourceInfo() {
            return (this.sourceInfoBuilder_ == null && this.sourceInfo_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        public SourceInfo getSourceInfo() {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                SourceInfo sourceInfo = this.sourceInfo_;
                return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSourceInfo(SourceInfo sourceInfo) {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sourceInfo);
            } else {
                if (sourceInfo == null) {
                    throw new NullPointerException();
                }
                this.sourceInfo_ = sourceInfo;
                onChanged();
            }
            return this;
        }

        public Builder setSourceInfo(SourceInfo.Builder builder) {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sourceInfo_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSourceInfo(SourceInfo sourceInfo) {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                SourceInfo sourceInfo2 = this.sourceInfo_;
                if (sourceInfo2 != null) {
                    this.sourceInfo_ = SourceInfo.newBuilder(sourceInfo2).mergeFrom(sourceInfo).buildPartial();
                } else {
                    this.sourceInfo_ = sourceInfo;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sourceInfo);
            }
            return this;
        }

        public Builder clearSourceInfo() {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = null;
                onChanged();
            } else {
                this.sourceInfo_ = null;
                this.sourceInfoBuilder_ = null;
            }
            return this;
        }

        public SourceInfo.Builder getSourceInfoBuilder() {
            onChanged();
            return getSourceInfoFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        public SourceInfoOrBuilder getSourceInfoOrBuilder() {
            SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SourceInfo sourceInfo = this.sourceInfo_;
            return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
        }

        private SingleFieldBuilderV3<SourceInfo, SourceInfo.Builder, SourceInfoOrBuilder> getSourceInfoFieldBuilder() {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfoBuilder_ = new SingleFieldBuilderV3<>(getSourceInfo(), getParentForChildren(), isClean());
                this.sourceInfo_ = null;
            }
            return this.sourceInfoBuilder_;
        }

        @Override // com.google.api.ServiceOrBuilder
        @Deprecated
        public boolean hasConfigVersion() {
            return (this.configVersionBuilder_ == null && this.configVersion_ == null) ? false : true;
        }

        @Override // com.google.api.ServiceOrBuilder
        @Deprecated
        public UInt32Value getConfigVersion() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value = this.configVersion_;
                return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
            }
            return singleFieldBuilderV3.getMessage();
        }

        @Deprecated
        public Builder setConfigVersion(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(uInt32Value);
            } else {
                if (uInt32Value == null) {
                    throw new NullPointerException();
                }
                this.configVersion_ = uInt32Value;
                onChanged();
            }
            return this;
        }

        @Deprecated
        public Builder setConfigVersion(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configVersion_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        @Deprecated
        public Builder mergeConfigVersion(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.configVersion_;
                if (uInt32Value2 != null) {
                    this.configVersion_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.configVersion_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        @Deprecated
        public Builder clearConfigVersion() {
            if (this.configVersionBuilder_ == null) {
                this.configVersion_ = null;
                onChanged();
            } else {
                this.configVersion_ = null;
                this.configVersionBuilder_ = null;
            }
            return this;
        }

        @Deprecated
        public UInt32Value.Builder getConfigVersionBuilder() {
            onChanged();
            return getConfigVersionFieldBuilder().getBuilder();
        }

        @Override // com.google.api.ServiceOrBuilder
        @Deprecated
        public UInt32ValueOrBuilder getConfigVersionOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.configVersion_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getConfigVersionFieldBuilder() {
            if (this.configVersionBuilder_ == null) {
                this.configVersionBuilder_ = new SingleFieldBuilderV3<>(getConfigVersion(), getParentForChildren(), isClean());
                this.configVersion_ = null;
            }
            return this.configVersionBuilder_;
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

    public static Service getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Service> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Service> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Service getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
