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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Agent extends GeneratedMessageV3 implements AgentOrBuilder {
    public static final int API_VERSION_FIELD_NUMBER = 14;
    public static final int AVATAR_URI_FIELD_NUMBER = 7;
    public static final int CLASSIFICATION_THRESHOLD_FIELD_NUMBER = 10;
    public static final int DEFAULT_LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int DESCRIPTION_FIELD_NUMBER = 6;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
    public static final int ENABLE_LOGGING_FIELD_NUMBER = 8;
    public static final int MATCH_MODE_FIELD_NUMBER = 9;
    public static final int PARENT_FIELD_NUMBER = 1;
    public static final int SUPPORTED_LANGUAGE_CODES_FIELD_NUMBER = 4;
    public static final int TIER_FIELD_NUMBER = 15;
    public static final int TIME_ZONE_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private int apiVersion_;
    private volatile Object avatarUri_;
    private float classificationThreshold_;
    private volatile Object defaultLanguageCode_;
    private volatile Object description_;
    private volatile Object displayName_;
    private boolean enableLogging_;
    private int matchMode_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private LazyStringList supportedLanguageCodes_;
    private int tier_;
    private volatile Object timeZone_;
    private static final Agent DEFAULT_INSTANCE = new Agent();
    private static final Parser<Agent> PARSER = new AbstractParser<Agent>() { // from class: com.google.cloud.dialogflow.v2beta1.Agent.1
        @Override // com.google.protobuf.Parser
        public Agent parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Agent(codedInputStream, extensionRegistryLite);
        }
    };

    private Agent(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Agent() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.displayName_ = "";
        this.defaultLanguageCode_ = "";
        this.supportedLanguageCodes_ = LazyStringArrayList.EMPTY;
        this.timeZone_ = "";
        this.description_ = "";
        this.avatarUri_ = "";
        this.matchMode_ = 0;
        this.apiVersion_ = 0;
        this.tier_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Agent();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0012. Please report as an issue. */
    private Agent(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                            case 10:
                                this.parent_ = codedInputStream.readStringRequireUtf8();
                            case 18:
                                this.displayName_ = codedInputStream.readStringRequireUtf8();
                            case 26:
                                this.defaultLanguageCode_ = codedInputStream.readStringRequireUtf8();
                            case 34:
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!(z2 & true)) {
                                    this.supportedLanguageCodes_ = new LazyStringArrayList();
                                    z2 |= true;
                                }
                                this.supportedLanguageCodes_.add(readStringRequireUtf8);
                            case 42:
                                this.timeZone_ = codedInputStream.readStringRequireUtf8();
                            case 50:
                                this.description_ = codedInputStream.readStringRequireUtf8();
                            case 58:
                                this.avatarUri_ = codedInputStream.readStringRequireUtf8();
                            case 64:
                                this.enableLogging_ = codedInputStream.readBool();
                            case 72:
                                this.matchMode_ = codedInputStream.readEnum();
                            case 85:
                                this.classificationThreshold_ = codedInputStream.readFloat();
                            case 112:
                                this.apiVersion_ = codedInputStream.readEnum();
                            case 120:
                                this.tier_ = codedInputStream.readEnum();
                            default:
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    z = true;
                                }
                        }
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.supportedLanguageCodes_ = this.supportedLanguageCodes_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AgentProto.internal_static_google_cloud_dialogflow_v2beta1_Agent_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AgentProto.f1578xf679de89.ensureFieldAccessorsInitialized(Agent.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum MatchMode implements ProtocolMessageEnum {
        MATCH_MODE_UNSPECIFIED(0),
        MATCH_MODE_HYBRID(1),
        MATCH_MODE_ML_ONLY(2),
        UNRECOGNIZED(-1);

        public static final int MATCH_MODE_HYBRID_VALUE = 1;
        public static final int MATCH_MODE_ML_ONLY_VALUE = 2;
        public static final int MATCH_MODE_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<MatchMode> internalValueMap = new Internal.EnumLiteMap<MatchMode>() { // from class: com.google.cloud.dialogflow.v2beta1.Agent.MatchMode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MatchMode findValueByNumber(int i) {
                return MatchMode.forNumber(i);
            }
        };
        private static final MatchMode[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static MatchMode valueOf(int i) {
            return forNumber(i);
        }

        public static MatchMode forNumber(int i) {
            if (i == 0) {
                return MATCH_MODE_UNSPECIFIED;
            }
            if (i == 1) {
                return MATCH_MODE_HYBRID;
            }
            if (i != 2) {
                return null;
            }
            return MATCH_MODE_ML_ONLY;
        }

        public static Internal.EnumLiteMap<MatchMode> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Agent.getDescriptor().getEnumTypes().get(0);
        }

        public static MatchMode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        MatchMode(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum ApiVersion implements ProtocolMessageEnum {
        API_VERSION_UNSPECIFIED(0),
        API_VERSION_V1(1),
        API_VERSION_V2(2),
        API_VERSION_V2_BETA_1(3),
        UNRECOGNIZED(-1);

        public static final int API_VERSION_UNSPECIFIED_VALUE = 0;
        public static final int API_VERSION_V1_VALUE = 1;
        public static final int API_VERSION_V2_BETA_1_VALUE = 3;
        public static final int API_VERSION_V2_VALUE = 2;
        private final int value;
        private static final Internal.EnumLiteMap<ApiVersion> internalValueMap = new Internal.EnumLiteMap<ApiVersion>() { // from class: com.google.cloud.dialogflow.v2beta1.Agent.ApiVersion.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ApiVersion findValueByNumber(int i) {
                return ApiVersion.forNumber(i);
            }
        };
        private static final ApiVersion[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static ApiVersion valueOf(int i) {
            return forNumber(i);
        }

        public static ApiVersion forNumber(int i) {
            if (i == 0) {
                return API_VERSION_UNSPECIFIED;
            }
            if (i == 1) {
                return API_VERSION_V1;
            }
            if (i == 2) {
                return API_VERSION_V2;
            }
            if (i != 3) {
                return null;
            }
            return API_VERSION_V2_BETA_1;
        }

        public static Internal.EnumLiteMap<ApiVersion> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Agent.getDescriptor().getEnumTypes().get(1);
        }

        public static ApiVersion valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        ApiVersion(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum Tier implements ProtocolMessageEnum {
        TIER_UNSPECIFIED(0),
        TIER_STANDARD(1),
        TIER_ENTERPRISE(2),
        TIER_ENTERPRISE_PLUS(3),
        UNRECOGNIZED(-1);

        public static final int TIER_ENTERPRISE_PLUS_VALUE = 3;
        public static final int TIER_ENTERPRISE_VALUE = 2;
        public static final int TIER_STANDARD_VALUE = 1;
        public static final int TIER_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<Tier> internalValueMap = new Internal.EnumLiteMap<Tier>() { // from class: com.google.cloud.dialogflow.v2beta1.Agent.Tier.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Tier findValueByNumber(int i) {
                return Tier.forNumber(i);
            }
        };
        private static final Tier[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static Tier valueOf(int i) {
            return forNumber(i);
        }

        public static Tier forNumber(int i) {
            if (i == 0) {
                return TIER_UNSPECIFIED;
            }
            if (i == 1) {
                return TIER_STANDARD;
            }
            if (i == 2) {
                return TIER_ENTERPRISE;
            }
            if (i != 3) {
                return null;
            }
            return TIER_ENTERPRISE_PLUS;
        }

        public static Internal.EnumLiteMap<Tier> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Agent.getDescriptor().getEnumTypes().get(2);
        }

        public static Tier valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        Tier(int i) {
            this.value = i;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public String getDefaultLanguageCode() {
        Object obj = this.defaultLanguageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.defaultLanguageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ByteString getDefaultLanguageCodeBytes() {
        Object obj = this.defaultLanguageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.defaultLanguageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ProtocolStringList getSupportedLanguageCodesList() {
        return this.supportedLanguageCodes_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public int getSupportedLanguageCodesCount() {
        return this.supportedLanguageCodes_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public String getSupportedLanguageCodes(int i) {
        return (String) this.supportedLanguageCodes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ByteString getSupportedLanguageCodesBytes(int i) {
        return this.supportedLanguageCodes_.getByteString(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public String getTimeZone() {
        Object obj = this.timeZone_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.timeZone_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ByteString getTimeZoneBytes() {
        Object obj = this.timeZone_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.timeZone_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public String getAvatarUri() {
        Object obj = this.avatarUri_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.avatarUri_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ByteString getAvatarUriBytes() {
        Object obj = this.avatarUri_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.avatarUri_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public boolean getEnableLogging() {
        return this.enableLogging_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public int getMatchModeValue() {
        return this.matchMode_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public MatchMode getMatchMode() {
        MatchMode valueOf = MatchMode.valueOf(this.matchMode_);
        return valueOf == null ? MatchMode.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public float getClassificationThreshold() {
        return this.classificationThreshold_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public int getApiVersionValue() {
        return this.apiVersion_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public ApiVersion getApiVersion() {
        ApiVersion valueOf = ApiVersion.valueOf(this.apiVersion_);
        return valueOf == null ? ApiVersion.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public int getTierValue() {
        return this.tier_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
    public Tier getTier() {
        Tier valueOf = Tier.valueOf(this.tier_);
        return valueOf == null ? Tier.UNRECOGNIZED : valueOf;
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
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.displayName_);
        }
        if (!getDefaultLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.defaultLanguageCode_);
        }
        for (int i = 0; i < this.supportedLanguageCodes_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.supportedLanguageCodes_.getRaw(i));
        }
        if (!getTimeZoneBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.timeZone_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.description_);
        }
        if (!getAvatarUriBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.avatarUri_);
        }
        boolean z = this.enableLogging_;
        if (z) {
            codedOutputStream.writeBool(8, z);
        }
        if (this.matchMode_ != MatchMode.MATCH_MODE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(9, this.matchMode_);
        }
        float f = this.classificationThreshold_;
        if (f != 0.0f) {
            codedOutputStream.writeFloat(10, f);
        }
        if (this.apiVersion_ != ApiVersion.API_VERSION_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(14, this.apiVersion_);
        }
        if (this.tier_ != Tier.TIER_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(15, this.tier_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getParentBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.parent_) + 0 : 0;
        if (!getDisplayNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.displayName_);
        }
        if (!getDefaultLanguageCodeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.defaultLanguageCode_);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.supportedLanguageCodes_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.supportedLanguageCodes_.getRaw(i3));
        }
        int size = computeStringSize + i2 + (getSupportedLanguageCodesList().size() * 1);
        if (!getTimeZoneBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(5, this.timeZone_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(6, this.description_);
        }
        if (!getAvatarUriBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(7, this.avatarUri_);
        }
        boolean z = this.enableLogging_;
        if (z) {
            size += CodedOutputStream.computeBoolSize(8, z);
        }
        if (this.matchMode_ != MatchMode.MATCH_MODE_UNSPECIFIED.getNumber()) {
            size += CodedOutputStream.computeEnumSize(9, this.matchMode_);
        }
        float f = this.classificationThreshold_;
        if (f != 0.0f) {
            size += CodedOutputStream.computeFloatSize(10, f);
        }
        if (this.apiVersion_ != ApiVersion.API_VERSION_UNSPECIFIED.getNumber()) {
            size += CodedOutputStream.computeEnumSize(14, this.apiVersion_);
        }
        if (this.tier_ != Tier.TIER_UNSPECIFIED.getNumber()) {
            size += CodedOutputStream.computeEnumSize(15, this.tier_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Agent)) {
            return super.equals(obj);
        }
        Agent agent = (Agent) obj;
        return getParent().equals(agent.getParent()) && getDisplayName().equals(agent.getDisplayName()) && getDefaultLanguageCode().equals(agent.getDefaultLanguageCode()) && getSupportedLanguageCodesList().equals(agent.getSupportedLanguageCodesList()) && getTimeZone().equals(agent.getTimeZone()) && getDescription().equals(agent.getDescription()) && getAvatarUri().equals(agent.getAvatarUri()) && getEnableLogging() == agent.getEnableLogging() && this.matchMode_ == agent.matchMode_ && Float.floatToIntBits(getClassificationThreshold()) == Float.floatToIntBits(agent.getClassificationThreshold()) && this.apiVersion_ == agent.apiVersion_ && this.tier_ == agent.tier_ && this.unknownFields.equals(agent.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode()) * 37) + 2) * 53) + getDisplayName().hashCode()) * 37) + 3) * 53) + getDefaultLanguageCode().hashCode();
        if (getSupportedLanguageCodesCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getSupportedLanguageCodesList().hashCode();
        }
        int hashCode2 = (((((((((((((((((((((((((((((((((hashCode * 37) + 5) * 53) + getTimeZone().hashCode()) * 37) + 6) * 53) + getDescription().hashCode()) * 37) + 7) * 53) + getAvatarUri().hashCode()) * 37) + 8) * 53) + Internal.hashBoolean(getEnableLogging())) * 37) + 9) * 53) + this.matchMode_) * 37) + 10) * 53) + Float.floatToIntBits(getClassificationThreshold())) * 37) + 14) * 53) + this.apiVersion_) * 37) + 15) * 53) + this.tier_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Agent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Agent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Agent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Agent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Agent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Agent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Agent parseFrom(InputStream inputStream) throws IOException {
        return (Agent) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Agent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Agent) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Agent parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Agent) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Agent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Agent) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Agent parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Agent) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Agent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Agent) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Agent agent) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(agent);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AgentOrBuilder {
        private int apiVersion_;
        private Object avatarUri_;
        private int bitField0_;
        private float classificationThreshold_;
        private Object defaultLanguageCode_;
        private Object description_;
        private Object displayName_;
        private boolean enableLogging_;
        private int matchMode_;
        private Object parent_;
        private LazyStringList supportedLanguageCodes_;
        private int tier_;
        private Object timeZone_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AgentProto.internal_static_google_cloud_dialogflow_v2beta1_Agent_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AgentProto.f1578xf679de89.ensureFieldAccessorsInitialized(Agent.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            this.displayName_ = "";
            this.defaultLanguageCode_ = "";
            this.supportedLanguageCodes_ = LazyStringArrayList.EMPTY;
            this.timeZone_ = "";
            this.description_ = "";
            this.avatarUri_ = "";
            this.matchMode_ = 0;
            this.apiVersion_ = 0;
            this.tier_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            this.displayName_ = "";
            this.defaultLanguageCode_ = "";
            this.supportedLanguageCodes_ = LazyStringArrayList.EMPTY;
            this.timeZone_ = "";
            this.description_ = "";
            this.avatarUri_ = "";
            this.matchMode_ = 0;
            this.apiVersion_ = 0;
            this.tier_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Agent.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            this.displayName_ = "";
            this.defaultLanguageCode_ = "";
            this.supportedLanguageCodes_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.timeZone_ = "";
            this.description_ = "";
            this.avatarUri_ = "";
            this.enableLogging_ = false;
            this.matchMode_ = 0;
            this.classificationThreshold_ = 0.0f;
            this.apiVersion_ = 0;
            this.tier_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AgentProto.internal_static_google_cloud_dialogflow_v2beta1_Agent_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Agent getDefaultInstanceForType() {
            return Agent.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Agent build() {
            Agent buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Agent buildPartial() {
            Agent agent = new Agent(this);
            int i = this.bitField0_;
            agent.parent_ = this.parent_;
            agent.displayName_ = this.displayName_;
            agent.defaultLanguageCode_ = this.defaultLanguageCode_;
            if ((this.bitField0_ & 1) != 0) {
                this.supportedLanguageCodes_ = this.supportedLanguageCodes_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            agent.supportedLanguageCodes_ = this.supportedLanguageCodes_;
            agent.timeZone_ = this.timeZone_;
            agent.description_ = this.description_;
            agent.avatarUri_ = this.avatarUri_;
            agent.enableLogging_ = this.enableLogging_;
            agent.matchMode_ = this.matchMode_;
            agent.classificationThreshold_ = this.classificationThreshold_;
            agent.apiVersion_ = this.apiVersion_;
            agent.tier_ = this.tier_;
            onBuilt();
            return agent;
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
            if (message instanceof Agent) {
                return mergeFrom((Agent) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Agent agent) {
            if (agent == Agent.getDefaultInstance()) {
                return this;
            }
            if (!agent.getParent().isEmpty()) {
                this.parent_ = agent.parent_;
                onChanged();
            }
            if (!agent.getDisplayName().isEmpty()) {
                this.displayName_ = agent.displayName_;
                onChanged();
            }
            if (!agent.getDefaultLanguageCode().isEmpty()) {
                this.defaultLanguageCode_ = agent.defaultLanguageCode_;
                onChanged();
            }
            if (!agent.supportedLanguageCodes_.isEmpty()) {
                if (this.supportedLanguageCodes_.isEmpty()) {
                    this.supportedLanguageCodes_ = agent.supportedLanguageCodes_;
                    this.bitField0_ &= -2;
                } else {
                    ensureSupportedLanguageCodesIsMutable();
                    this.supportedLanguageCodes_.addAll(agent.supportedLanguageCodes_);
                }
                onChanged();
            }
            if (!agent.getTimeZone().isEmpty()) {
                this.timeZone_ = agent.timeZone_;
                onChanged();
            }
            if (!agent.getDescription().isEmpty()) {
                this.description_ = agent.description_;
                onChanged();
            }
            if (!agent.getAvatarUri().isEmpty()) {
                this.avatarUri_ = agent.avatarUri_;
                onChanged();
            }
            if (agent.getEnableLogging()) {
                setEnableLogging(agent.getEnableLogging());
            }
            if (agent.matchMode_ != 0) {
                setMatchModeValue(agent.getMatchModeValue());
            }
            if (agent.getClassificationThreshold() != 0.0f) {
                setClassificationThreshold(agent.getClassificationThreshold());
            }
            if (agent.apiVersion_ != 0) {
                setApiVersionValue(agent.getApiVersionValue());
            }
            if (agent.tier_ != 0) {
                setTierValue(agent.getTierValue());
            }
            mergeUnknownFields(agent.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Agent agent = null;
            try {
                try {
                    Agent agent2 = (Agent) Agent.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (agent2 != null) {
                        mergeFrom(agent2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Agent agent3 = (Agent) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        agent = agent3;
                        if (agent != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (agent != null) {
                    mergeFrom(agent);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
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
            this.parent_ = Agent.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                Agent.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public String getDisplayName() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.displayName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
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
            this.displayName_ = Agent.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                Agent.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public String getDefaultLanguageCode() {
            Object obj = this.defaultLanguageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.defaultLanguageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public ByteString getDefaultLanguageCodeBytes() {
            Object obj = this.defaultLanguageCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.defaultLanguageCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDefaultLanguageCode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.defaultLanguageCode_ = str;
            onChanged();
            return this;
        }

        public Builder clearDefaultLanguageCode() {
            this.defaultLanguageCode_ = Agent.getDefaultInstance().getDefaultLanguageCode();
            onChanged();
            return this;
        }

        public Builder setDefaultLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                Agent.checkByteStringIsUtf8(byteString);
                this.defaultLanguageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureSupportedLanguageCodesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.supportedLanguageCodes_ = new LazyStringArrayList(this.supportedLanguageCodes_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public ProtocolStringList getSupportedLanguageCodesList() {
            return this.supportedLanguageCodes_.getUnmodifiableView();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public int getSupportedLanguageCodesCount() {
            return this.supportedLanguageCodes_.size();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public String getSupportedLanguageCodes(int i) {
            return (String) this.supportedLanguageCodes_.get(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public ByteString getSupportedLanguageCodesBytes(int i) {
            return this.supportedLanguageCodes_.getByteString(i);
        }

        public Builder setSupportedLanguageCodes(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureSupportedLanguageCodesIsMutable();
            this.supportedLanguageCodes_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addSupportedLanguageCodes(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureSupportedLanguageCodesIsMutable();
            this.supportedLanguageCodes_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllSupportedLanguageCodes(Iterable<String> iterable) {
            ensureSupportedLanguageCodesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.supportedLanguageCodes_);
            onChanged();
            return this;
        }

        public Builder clearSupportedLanguageCodes() {
            this.supportedLanguageCodes_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addSupportedLanguageCodesBytes(ByteString byteString) {
            if (byteString != null) {
                Agent.checkByteStringIsUtf8(byteString);
                ensureSupportedLanguageCodesIsMutable();
                this.supportedLanguageCodes_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public String getTimeZone() {
            Object obj = this.timeZone_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.timeZone_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
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
            this.timeZone_ = Agent.getDefaultInstance().getTimeZone();
            onChanged();
            return this;
        }

        public Builder setTimeZoneBytes(ByteString byteString) {
            if (byteString != null) {
                Agent.checkByteStringIsUtf8(byteString);
                this.timeZone_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
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
            this.description_ = Agent.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                Agent.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public String getAvatarUri() {
            Object obj = this.avatarUri_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.avatarUri_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public ByteString getAvatarUriBytes() {
            Object obj = this.avatarUri_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.avatarUri_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAvatarUri(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.avatarUri_ = str;
            onChanged();
            return this;
        }

        public Builder clearAvatarUri() {
            this.avatarUri_ = Agent.getDefaultInstance().getAvatarUri();
            onChanged();
            return this;
        }

        public Builder setAvatarUriBytes(ByteString byteString) {
            if (byteString != null) {
                Agent.checkByteStringIsUtf8(byteString);
                this.avatarUri_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public boolean getEnableLogging() {
            return this.enableLogging_;
        }

        public Builder setEnableLogging(boolean z) {
            this.enableLogging_ = z;
            onChanged();
            return this;
        }

        public Builder clearEnableLogging() {
            this.enableLogging_ = false;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public int getMatchModeValue() {
            return this.matchMode_;
        }

        public Builder setMatchModeValue(int i) {
            this.matchMode_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public MatchMode getMatchMode() {
            MatchMode valueOf = MatchMode.valueOf(this.matchMode_);
            return valueOf == null ? MatchMode.UNRECOGNIZED : valueOf;
        }

        public Builder setMatchMode(MatchMode matchMode) {
            if (matchMode == null) {
                throw new NullPointerException();
            }
            this.matchMode_ = matchMode.getNumber();
            onChanged();
            return this;
        }

        public Builder clearMatchMode() {
            this.matchMode_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public float getClassificationThreshold() {
            return this.classificationThreshold_;
        }

        public Builder setClassificationThreshold(float f) {
            this.classificationThreshold_ = f;
            onChanged();
            return this;
        }

        public Builder clearClassificationThreshold() {
            this.classificationThreshold_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public int getApiVersionValue() {
            return this.apiVersion_;
        }

        public Builder setApiVersionValue(int i) {
            this.apiVersion_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public ApiVersion getApiVersion() {
            ApiVersion valueOf = ApiVersion.valueOf(this.apiVersion_);
            return valueOf == null ? ApiVersion.UNRECOGNIZED : valueOf;
        }

        public Builder setApiVersion(ApiVersion apiVersion) {
            if (apiVersion == null) {
                throw new NullPointerException();
            }
            this.apiVersion_ = apiVersion.getNumber();
            onChanged();
            return this;
        }

        public Builder clearApiVersion() {
            this.apiVersion_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public int getTierValue() {
            return this.tier_;
        }

        public Builder setTierValue(int i) {
            this.tier_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.AgentOrBuilder
        public Tier getTier() {
            Tier valueOf = Tier.valueOf(this.tier_);
            return valueOf == null ? Tier.UNRECOGNIZED : valueOf;
        }

        public Builder setTier(Tier tier) {
            if (tier == null) {
                throw new NullPointerException();
            }
            this.tier_ = tier.getNumber();
            onChanged();
            return this;
        }

        public Builder clearTier() {
            this.tier_ = 0;
            onChanged();
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

    public static Agent getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Agent> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Agent> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Agent getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
