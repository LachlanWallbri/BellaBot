package com.google.api;

import com.google.api.CustomHttpPattern;
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
public final class HttpRule extends GeneratedMessageV3 implements HttpRuleOrBuilder {
    public static final int ADDITIONAL_BINDINGS_FIELD_NUMBER = 11;
    public static final int BODY_FIELD_NUMBER = 7;
    public static final int CUSTOM_FIELD_NUMBER = 8;
    public static final int DELETE_FIELD_NUMBER = 5;
    public static final int GET_FIELD_NUMBER = 2;
    public static final int PATCH_FIELD_NUMBER = 6;
    public static final int POST_FIELD_NUMBER = 4;
    public static final int PUT_FIELD_NUMBER = 3;
    public static final int RESPONSE_BODY_FIELD_NUMBER = 12;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<HttpRule> additionalBindings_;
    private volatile Object body_;
    private byte memoizedIsInitialized;
    private int patternCase_;
    private Object pattern_;
    private volatile Object responseBody_;
    private volatile Object selector_;
    private static final HttpRule DEFAULT_INSTANCE = new HttpRule();
    private static final Parser<HttpRule> PARSER = new AbstractParser<HttpRule>() { // from class: com.google.api.HttpRule.1
        @Override // com.google.protobuf.Parser
        public HttpRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpRule(codedInputStream, extensionRegistryLite);
        }
    };

    private HttpRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.patternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HttpRule() {
        this.patternCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
        this.body_ = "";
        this.responseBody_ = "";
        this.additionalBindings_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new HttpRule();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0012. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    private HttpRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.selector_ = codedInputStream.readStringRequireUtf8();
                            case 18:
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 2;
                                this.pattern_ = readStringRequireUtf8;
                            case 26:
                                String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 3;
                                this.pattern_ = readStringRequireUtf82;
                            case 34:
                                String readStringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 4;
                                this.pattern_ = readStringRequireUtf83;
                            case 42:
                                String readStringRequireUtf84 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 5;
                                this.pattern_ = readStringRequireUtf84;
                            case 50:
                                String readStringRequireUtf85 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 6;
                                this.pattern_ = readStringRequireUtf85;
                            case 58:
                                this.body_ = codedInputStream.readStringRequireUtf8();
                            case 66:
                                CustomHttpPattern.Builder builder = this.patternCase_ == 8 ? ((CustomHttpPattern) this.pattern_).toBuilder() : null;
                                this.pattern_ = codedInputStream.readMessage(CustomHttpPattern.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((CustomHttpPattern) this.pattern_);
                                    this.pattern_ = builder.buildPartial();
                                }
                                this.patternCase_ = 8;
                            case 90:
                                if (!(z2 & true)) {
                                    this.additionalBindings_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.additionalBindings_.add(codedInputStream.readMessage(parser(), extensionRegistryLite));
                            case 98:
                                this.responseBody_ = codedInputStream.readStringRequireUtf8();
                            default:
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    z = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpProto.internal_static_google_api_HttpRule_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpProto.internal_static_google_api_HttpRule_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRule.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum PatternCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        GET(2),
        PUT(3),
        POST(4),
        DELETE(5),
        PATCH(6),
        CUSTOM(8),
        PATTERN_NOT_SET(0);

        private final int value;

        PatternCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static PatternCase valueOf(int i) {
            return forNumber(i);
        }

        public static PatternCase forNumber(int i) {
            if (i == 0) {
                return PATTERN_NOT_SET;
            }
            if (i == 8) {
                return CUSTOM;
            }
            if (i == 2) {
                return GET;
            }
            if (i == 3) {
                return PUT;
            }
            if (i == 4) {
                return POST;
            }
            if (i == 5) {
                return DELETE;
            }
            if (i != 6) {
                return null;
            }
            return PATCH;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public PatternCase getPatternCase() {
        return PatternCase.forNumber(this.patternCase_);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasGet() {
        return this.patternCase_ == 2;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getGet() {
        String str = this.patternCase_ == 2 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 2) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getGetBytes() {
        String str = this.patternCase_ == 2 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 2) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasPut() {
        return this.patternCase_ == 3;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPut() {
        String str = this.patternCase_ == 3 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 3) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPutBytes() {
        String str = this.patternCase_ == 3 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 3) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasPost() {
        return this.patternCase_ == 4;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPost() {
        String str = this.patternCase_ == 4 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 4) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPostBytes() {
        String str = this.patternCase_ == 4 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 4) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasDelete() {
        return this.patternCase_ == 5;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getDelete() {
        String str = this.patternCase_ == 5 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 5) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getDeleteBytes() {
        String str = this.patternCase_ == 5 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 5) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasPatch() {
        return this.patternCase_ == 6;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPatch() {
        String str = this.patternCase_ == 6 ? this.pattern_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.patternCase_ == 6) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPatchBytes() {
        String str = this.patternCase_ == 6 ? this.pattern_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.patternCase_ == 6) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasCustom() {
        return this.patternCase_ == 8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public CustomHttpPattern getCustom() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public CustomHttpPatternOrBuilder getCustomOrBuilder() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getBody() {
        Object obj = this.body_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.body_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getBodyBytes() {
        Object obj = this.body_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.body_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getResponseBody() {
        Object obj = this.responseBody_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.responseBody_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getResponseBodyBytes() {
        Object obj = this.responseBody_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.responseBody_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public List<HttpRule> getAdditionalBindingsList() {
        return this.additionalBindings_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
        return this.additionalBindings_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public int getAdditionalBindingsCount() {
        return this.additionalBindings_.size();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public HttpRule getAdditionalBindings(int i) {
        return this.additionalBindings_.get(i);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i) {
        return this.additionalBindings_.get(i);
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
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        if (this.patternCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.pattern_);
        }
        if (this.patternCase_ == 3) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.pattern_);
        }
        if (this.patternCase_ == 4) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.pattern_);
        }
        if (this.patternCase_ == 5) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.pattern_);
        }
        if (this.patternCase_ == 6) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.pattern_);
        }
        if (!getBodyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.body_);
        }
        if (this.patternCase_ == 8) {
            codedOutputStream.writeMessage(8, (CustomHttpPattern) this.pattern_);
        }
        for (int i = 0; i < this.additionalBindings_.size(); i++) {
            codedOutputStream.writeMessage(11, this.additionalBindings_.get(i));
        }
        if (!getResponseBodyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 12, this.responseBody_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) + 0 : 0;
        if (this.patternCase_ == 2) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.pattern_);
        }
        if (this.patternCase_ == 3) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.pattern_);
        }
        if (this.patternCase_ == 4) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.pattern_);
        }
        if (this.patternCase_ == 5) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.pattern_);
        }
        if (this.patternCase_ == 6) {
            computeStringSize += GeneratedMessageV3.computeStringSize(6, this.pattern_);
        }
        if (!getBodyBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(7, this.body_);
        }
        if (this.patternCase_ == 8) {
            computeStringSize += CodedOutputStream.computeMessageSize(8, (CustomHttpPattern) this.pattern_);
        }
        for (int i2 = 0; i2 < this.additionalBindings_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(11, this.additionalBindings_.get(i2));
        }
        if (!getResponseBodyBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(12, this.responseBody_);
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
        if (!(obj instanceof HttpRule)) {
            return super.equals(obj);
        }
        HttpRule httpRule = (HttpRule) obj;
        if (!getSelector().equals(httpRule.getSelector()) || !getBody().equals(httpRule.getBody()) || !getResponseBody().equals(httpRule.getResponseBody()) || !getAdditionalBindingsList().equals(httpRule.getAdditionalBindingsList()) || !getPatternCase().equals(httpRule.getPatternCase())) {
            return false;
        }
        int i = this.patternCase_;
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i == 6) {
                            if (!getPatch().equals(httpRule.getPatch())) {
                                return false;
                            }
                        } else if (i == 8 && !getCustom().equals(httpRule.getCustom())) {
                            return false;
                        }
                    } else if (!getDelete().equals(httpRule.getDelete())) {
                        return false;
                    }
                } else if (!getPost().equals(httpRule.getPost())) {
                    return false;
                }
            } else if (!getPut().equals(httpRule.getPut())) {
                return false;
            }
        } else if (!getGet().equals(httpRule.getGet())) {
            return false;
        }
        return this.unknownFields.equals(httpRule.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 7) * 53) + getBody().hashCode()) * 37) + 12) * 53) + getResponseBody().hashCode();
        if (getAdditionalBindingsCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 11) * 53) + getAdditionalBindingsList().hashCode();
        }
        int i2 = this.patternCase_;
        if (i2 == 2) {
            i = ((hashCode2 * 37) + 2) * 53;
            hashCode = getGet().hashCode();
        } else if (i2 == 3) {
            i = ((hashCode2 * 37) + 3) * 53;
            hashCode = getPut().hashCode();
        } else if (i2 == 4) {
            i = ((hashCode2 * 37) + 4) * 53;
            hashCode = getPost().hashCode();
        } else if (i2 == 5) {
            i = ((hashCode2 * 37) + 5) * 53;
            hashCode = getDelete().hashCode();
        } else if (i2 == 6) {
            i = ((hashCode2 * 37) + 6) * 53;
            hashCode = getPatch().hashCode();
        } else {
            if (i2 == 8) {
                i = ((hashCode2 * 37) + 8) * 53;
                hashCode = getCustom().hashCode();
            }
            int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }
        hashCode2 = i + hashCode;
        int hashCode32 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode32;
        return hashCode32;
    }

    public static HttpRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static HttpRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static HttpRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static HttpRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpRule parseFrom(InputStream inputStream) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HttpRule httpRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(httpRule);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HttpRuleOrBuilder {
        private RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> additionalBindingsBuilder_;
        private List<HttpRule> additionalBindings_;
        private int bitField0_;
        private Object body_;
        private SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> customBuilder_;
        private int patternCase_;
        private Object pattern_;
        private Object responseBody_;
        private Object selector_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpProto.internal_static_google_api_HttpRule_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpProto.internal_static_google_api_HttpRule_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRule.class, Builder.class);
        }

        private Builder() {
            this.patternCase_ = 0;
            this.selector_ = "";
            this.body_ = "";
            this.responseBody_ = "";
            this.additionalBindings_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.patternCase_ = 0;
            this.selector_ = "";
            this.body_ = "";
            this.responseBody_ = "";
            this.additionalBindings_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (HttpRule.alwaysUseFieldBuilders) {
                getAdditionalBindingsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.body_ = "";
            this.responseBody_ = "";
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.additionalBindings_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.patternCase_ = 0;
            this.pattern_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HttpProto.internal_static_google_api_HttpRule_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public HttpRule getDefaultInstanceForType() {
            return HttpRule.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HttpRule build() {
            HttpRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HttpRule buildPartial() {
            HttpRule httpRule = new HttpRule(this);
            int i = this.bitField0_;
            httpRule.selector_ = this.selector_;
            if (this.patternCase_ == 2) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 3) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 4) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 5) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 6) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 8) {
                SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
                if (singleFieldBuilderV3 == null) {
                    httpRule.pattern_ = this.pattern_;
                } else {
                    httpRule.pattern_ = singleFieldBuilderV3.build();
                }
            }
            httpRule.body_ = this.body_;
            httpRule.responseBody_ = this.responseBody_;
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                httpRule.additionalBindings_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
                    this.bitField0_ &= -2;
                }
                httpRule.additionalBindings_ = this.additionalBindings_;
            }
            httpRule.patternCase_ = this.patternCase_;
            onBuilt();
            return httpRule;
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
            if (message instanceof HttpRule) {
                return mergeFrom((HttpRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpRule httpRule) {
            if (httpRule == HttpRule.getDefaultInstance()) {
                return this;
            }
            if (!httpRule.getSelector().isEmpty()) {
                this.selector_ = httpRule.selector_;
                onChanged();
            }
            if (!httpRule.getBody().isEmpty()) {
                this.body_ = httpRule.body_;
                onChanged();
            }
            if (!httpRule.getResponseBody().isEmpty()) {
                this.responseBody_ = httpRule.responseBody_;
                onChanged();
            }
            if (this.additionalBindingsBuilder_ == null) {
                if (!httpRule.additionalBindings_.isEmpty()) {
                    if (this.additionalBindings_.isEmpty()) {
                        this.additionalBindings_ = httpRule.additionalBindings_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureAdditionalBindingsIsMutable();
                        this.additionalBindings_.addAll(httpRule.additionalBindings_);
                    }
                    onChanged();
                }
            } else if (!httpRule.additionalBindings_.isEmpty()) {
                if (!this.additionalBindingsBuilder_.isEmpty()) {
                    this.additionalBindingsBuilder_.addAllMessages(httpRule.additionalBindings_);
                } else {
                    this.additionalBindingsBuilder_.dispose();
                    this.additionalBindingsBuilder_ = null;
                    this.additionalBindings_ = httpRule.additionalBindings_;
                    this.bitField0_ &= -2;
                    this.additionalBindingsBuilder_ = HttpRule.alwaysUseFieldBuilders ? getAdditionalBindingsFieldBuilder() : null;
                }
            }
            switch (httpRule.getPatternCase()) {
                case GET:
                    this.patternCase_ = 2;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case PUT:
                    this.patternCase_ = 3;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case POST:
                    this.patternCase_ = 4;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case DELETE:
                    this.patternCase_ = 5;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case PATCH:
                    this.patternCase_ = 6;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case CUSTOM:
                    mergeCustom(httpRule.getCustom());
                    break;
            }
            mergeUnknownFields(httpRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            HttpRule httpRule = null;
            try {
                try {
                    HttpRule httpRule2 = (HttpRule) HttpRule.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (httpRule2 != null) {
                        mergeFrom(httpRule2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    HttpRule httpRule3 = (HttpRule) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        httpRule = httpRule3;
                        if (httpRule != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (httpRule != null) {
                    mergeFrom(httpRule);
                }
                throw th;
            }
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public PatternCase getPatternCase() {
            return PatternCase.forNumber(this.patternCase_);
        }

        public Builder clearPattern() {
            this.patternCase_ = 0;
            this.pattern_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.selector_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSelector(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.selector_ = str;
            onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = HttpRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasGet() {
            return this.patternCase_ == 2;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getGet() {
            String str = this.patternCase_ == 2 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 2) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getGetBytes() {
            String str = this.patternCase_ == 2 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 2) {
                    this.pattern_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setGet(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 2;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        public Builder clearGet() {
            if (this.patternCase_ == 2) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setGetBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 2;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasPut() {
            return this.patternCase_ == 3;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPut() {
            String str = this.patternCase_ == 3 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 3) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPutBytes() {
            String str = this.patternCase_ == 3 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 3) {
                    this.pattern_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPut(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 3;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        public Builder clearPut() {
            if (this.patternCase_ == 3) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setPutBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 3;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasPost() {
            return this.patternCase_ == 4;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPost() {
            String str = this.patternCase_ == 4 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 4) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPostBytes() {
            String str = this.patternCase_ == 4 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 4) {
                    this.pattern_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPost(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 4;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        public Builder clearPost() {
            if (this.patternCase_ == 4) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setPostBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 4;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasDelete() {
            return this.patternCase_ == 5;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getDelete() {
            String str = this.patternCase_ == 5 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 5) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getDeleteBytes() {
            String str = this.patternCase_ == 5 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 5) {
                    this.pattern_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setDelete(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 5;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        public Builder clearDelete() {
            if (this.patternCase_ == 5) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setDeleteBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 5;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasPatch() {
            return this.patternCase_ == 6;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPatch() {
            String str = this.patternCase_ == 6 ? this.pattern_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.patternCase_ == 6) {
                    this.pattern_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPatchBytes() {
            String str = this.patternCase_ == 6 ? this.pattern_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.patternCase_ == 6) {
                    this.pattern_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPatch(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.patternCase_ = 6;
            this.pattern_ = str;
            onChanged();
            return this;
        }

        public Builder clearPatch() {
            if (this.patternCase_ == 6) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setPatchBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 6;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasCustom() {
            return this.patternCase_ == 8;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public CustomHttpPattern getCustom() {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.patternCase_ == 8) {
                    return (CustomHttpPattern) this.pattern_;
                }
                return CustomHttpPattern.getDefaultInstance();
            }
            if (this.patternCase_ == 8) {
                return singleFieldBuilderV3.getMessage();
            }
            return CustomHttpPattern.getDefaultInstance();
        }

        public Builder setCustom(CustomHttpPattern customHttpPattern) {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(customHttpPattern);
            } else {
                if (customHttpPattern == null) {
                    throw new NullPointerException();
                }
                this.pattern_ = customHttpPattern;
                onChanged();
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder setCustom(CustomHttpPattern.Builder builder) {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.pattern_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder mergeCustom(CustomHttpPattern customHttpPattern) {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.patternCase_ == 8 && this.pattern_ != CustomHttpPattern.getDefaultInstance()) {
                    this.pattern_ = CustomHttpPattern.newBuilder((CustomHttpPattern) this.pattern_).mergeFrom(customHttpPattern).buildPartial();
                } else {
                    this.pattern_ = customHttpPattern;
                }
                onChanged();
            } else {
                if (this.patternCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(customHttpPattern);
                }
                this.customBuilder_.setMessage(customHttpPattern);
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder clearCustom() {
            if (this.customBuilder_ == null) {
                if (this.patternCase_ == 8) {
                    this.patternCase_ = 0;
                    this.pattern_ = null;
                    onChanged();
                }
            } else {
                if (this.patternCase_ == 8) {
                    this.patternCase_ = 0;
                    this.pattern_ = null;
                }
                this.customBuilder_.clear();
            }
            return this;
        }

        public CustomHttpPattern.Builder getCustomBuilder() {
            return getCustomFieldBuilder().getBuilder();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public CustomHttpPatternOrBuilder getCustomOrBuilder() {
            SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3;
            if (this.patternCase_ == 8 && (singleFieldBuilderV3 = this.customBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.patternCase_ == 8) {
                return (CustomHttpPattern) this.pattern_;
            }
            return CustomHttpPattern.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CustomHttpPattern, CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> getCustomFieldBuilder() {
            if (this.customBuilder_ == null) {
                if (this.patternCase_ != 8) {
                    this.pattern_ = CustomHttpPattern.getDefaultInstance();
                }
                this.customBuilder_ = new SingleFieldBuilderV3<>((CustomHttpPattern) this.pattern_, getParentForChildren(), isClean());
                this.pattern_ = null;
            }
            this.patternCase_ = 8;
            onChanged();
            return this.customBuilder_;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getBody() {
            Object obj = this.body_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.body_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getBodyBytes() {
            Object obj = this.body_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.body_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setBody(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.body_ = str;
            onChanged();
            return this;
        }

        public Builder clearBody() {
            this.body_ = HttpRule.getDefaultInstance().getBody();
            onChanged();
            return this;
        }

        public Builder setBodyBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.body_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getResponseBody() {
            Object obj = this.responseBody_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.responseBody_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getResponseBodyBytes() {
            Object obj = this.responseBody_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.responseBody_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setResponseBody(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.responseBody_ = str;
            onChanged();
            return this;
        }

        public Builder clearResponseBody() {
            this.responseBody_ = HttpRule.getDefaultInstance().getResponseBody();
            onChanged();
            return this;
        }

        public Builder setResponseBodyBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.responseBody_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureAdditionalBindingsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.additionalBindings_ = new ArrayList(this.additionalBindings_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public List<HttpRule> getAdditionalBindingsList() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.additionalBindings_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public int getAdditionalBindingsCount() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.additionalBindings_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public HttpRule getAdditionalBindings(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.additionalBindings_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAdditionalBindings(int i, HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, httpRule);
            } else {
                if (httpRule == null) {
                    throw new NullPointerException();
                }
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.set(i, httpRule);
                onChanged();
            }
            return this;
        }

        public Builder setAdditionalBindings(int i, Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAdditionalBindings(HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(httpRule);
            } else {
                if (httpRule == null) {
                    throw new NullPointerException();
                }
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(httpRule);
                onChanged();
            }
            return this;
        }

        public Builder addAdditionalBindings(int i, HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, httpRule);
            } else {
                if (httpRule == null) {
                    throw new NullPointerException();
                }
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(i, httpRule);
                onChanged();
            }
            return this;
        }

        public Builder addAdditionalBindings(Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAdditionalBindings(int i, Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAdditionalBindings(Iterable<? extends HttpRule> iterable) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.additionalBindings_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAdditionalBindings() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.additionalBindings_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAdditionalBindings(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getAdditionalBindingsBuilder(int i) {
            return getAdditionalBindingsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.additionalBindings_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.additionalBindings_);
        }

        public Builder addAdditionalBindingsBuilder() {
            return getAdditionalBindingsFieldBuilder().addBuilder(HttpRule.getDefaultInstance());
        }

        public Builder addAdditionalBindingsBuilder(int i) {
            return getAdditionalBindingsFieldBuilder().addBuilder(i, HttpRule.getDefaultInstance());
        }

        public List<Builder> getAdditionalBindingsBuilderList() {
            return getAdditionalBindingsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> getAdditionalBindingsFieldBuilder() {
            if (this.additionalBindingsBuilder_ == null) {
                this.additionalBindingsBuilder_ = new RepeatedFieldBuilderV3<>(this.additionalBindings_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.additionalBindings_ = null;
            }
            return this.additionalBindingsBuilder_;
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

    public static HttpRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRule> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<HttpRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public HttpRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
