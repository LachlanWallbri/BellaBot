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
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class KnowledgeAnswers extends GeneratedMessageV3 implements KnowledgeAnswersOrBuilder {
    public static final int ANSWERS_FIELD_NUMBER = 1;
    private static final KnowledgeAnswers DEFAULT_INSTANCE = new KnowledgeAnswers();
    private static final Parser<KnowledgeAnswers> PARSER = new AbstractParser<KnowledgeAnswers>() { // from class: com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.1
        @Override // com.google.protobuf.Parser
        public KnowledgeAnswers parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new KnowledgeAnswers(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private List<Answer> answers_;
    private byte memoizedIsInitialized;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public interface AnswerOrBuilder extends MessageOrBuilder {
        String getAnswer();

        ByteString getAnswerBytes();

        String getFaqQuestion();

        ByteString getFaqQuestionBytes();

        float getMatchConfidence();

        Answer.MatchConfidenceLevel getMatchConfidenceLevel();

        int getMatchConfidenceLevelValue();

        String getSource();

        ByteString getSourceBytes();
    }

    private KnowledgeAnswers(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private KnowledgeAnswers() {
        this.memoizedIsInitialized = (byte) -1;
        this.answers_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new KnowledgeAnswers();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private KnowledgeAnswers(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!(z2 & true)) {
                                    this.answers_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.answers_.add(codedInputStream.readMessage(Answer.parser(), extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.answers_ = Collections.unmodifiableList(this.answers_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionProto.f1844x741e4b35;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1845x583d6cb3.ensureFieldAccessorsInitialized(KnowledgeAnswers.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static final class Answer extends GeneratedMessageV3 implements AnswerOrBuilder {
        public static final int ANSWER_FIELD_NUMBER = 3;
        public static final int FAQ_QUESTION_FIELD_NUMBER = 2;
        public static final int MATCH_CONFIDENCE_FIELD_NUMBER = 5;
        public static final int MATCH_CONFIDENCE_LEVEL_FIELD_NUMBER = 4;
        public static final int SOURCE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object answer_;
        private volatile Object faqQuestion_;
        private int matchConfidenceLevel_;
        private float matchConfidence_;
        private byte memoizedIsInitialized;
        private volatile Object source_;
        private static final Answer DEFAULT_INSTANCE = new Answer();
        private static final Parser<Answer> PARSER = new AbstractParser<Answer>() { // from class: com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.Answer.1
            @Override // com.google.protobuf.Parser
            public Answer parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Answer(codedInputStream, extensionRegistryLite);
            }
        };

        private Answer(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Answer() {
            this.memoizedIsInitialized = (byte) -1;
            this.source_ = "";
            this.faqQuestion_ = "";
            this.answer_ = "";
            this.matchConfidenceLevel_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Answer();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Answer(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    this.source_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.faqQuestion_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 26) {
                                    this.answer_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 32) {
                                    this.matchConfidenceLevel_ = codedInputStream.readEnum();
                                } else if (readTag == 45) {
                                    this.matchConfidence_ = codedInputStream.readFloat();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1842x2fb3e3ea;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1843x251d8068.ensureFieldAccessorsInitialized(Answer.class, Builder.class);
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes3.dex */
        public enum MatchConfidenceLevel implements ProtocolMessageEnum {
            MATCH_CONFIDENCE_LEVEL_UNSPECIFIED(0),
            LOW(1),
            MEDIUM(2),
            HIGH(3),
            UNRECOGNIZED(-1);

            public static final int HIGH_VALUE = 3;
            public static final int LOW_VALUE = 1;
            public static final int MATCH_CONFIDENCE_LEVEL_UNSPECIFIED_VALUE = 0;
            public static final int MEDIUM_VALUE = 2;
            private final int value;
            private static final Internal.EnumLiteMap<MatchConfidenceLevel> internalValueMap = new Internal.EnumLiteMap<MatchConfidenceLevel>() { // from class: com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.Answer.MatchConfidenceLevel.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public MatchConfidenceLevel findValueByNumber(int i) {
                    return MatchConfidenceLevel.forNumber(i);
                }
            };
            private static final MatchConfidenceLevel[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static MatchConfidenceLevel valueOf(int i) {
                return forNumber(i);
            }

            public static MatchConfidenceLevel forNumber(int i) {
                if (i == 0) {
                    return MATCH_CONFIDENCE_LEVEL_UNSPECIFIED;
                }
                if (i == 1) {
                    return LOW;
                }
                if (i == 2) {
                    return MEDIUM;
                }
                if (i != 3) {
                    return null;
                }
                return HIGH;
            }

            public static Internal.EnumLiteMap<MatchConfidenceLevel> internalGetValueMap() {
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
                return Answer.getDescriptor().getEnumTypes().get(0);
            }

            public static MatchConfidenceLevel valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            MatchConfidenceLevel(int i) {
                this.value = i;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public String getSource() {
            Object obj = this.source_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.source_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public ByteString getSourceBytes() {
            Object obj = this.source_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.source_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public String getFaqQuestion() {
            Object obj = this.faqQuestion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.faqQuestion_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public ByteString getFaqQuestionBytes() {
            Object obj = this.faqQuestion_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.faqQuestion_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public String getAnswer() {
            Object obj = this.answer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.answer_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public ByteString getAnswerBytes() {
            Object obj = this.answer_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.answer_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public int getMatchConfidenceLevelValue() {
            return this.matchConfidenceLevel_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public MatchConfidenceLevel getMatchConfidenceLevel() {
            MatchConfidenceLevel valueOf = MatchConfidenceLevel.valueOf(this.matchConfidenceLevel_);
            return valueOf == null ? MatchConfidenceLevel.UNRECOGNIZED : valueOf;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
        public float getMatchConfidence() {
            return this.matchConfidence_;
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
            if (!getSourceBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.source_);
            }
            if (!getFaqQuestionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.faqQuestion_);
            }
            if (!getAnswerBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.answer_);
            }
            if (this.matchConfidenceLevel_ != MatchConfidenceLevel.MATCH_CONFIDENCE_LEVEL_UNSPECIFIED.getNumber()) {
                codedOutputStream.writeEnum(4, this.matchConfidenceLevel_);
            }
            float f = this.matchConfidence_;
            if (f != 0.0f) {
                codedOutputStream.writeFloat(5, f);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getSourceBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.source_);
            if (!getFaqQuestionBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(2, this.faqQuestion_);
            }
            if (!getAnswerBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(3, this.answer_);
            }
            if (this.matchConfidenceLevel_ != MatchConfidenceLevel.MATCH_CONFIDENCE_LEVEL_UNSPECIFIED.getNumber()) {
                computeStringSize += CodedOutputStream.computeEnumSize(4, this.matchConfidenceLevel_);
            }
            float f = this.matchConfidence_;
            if (f != 0.0f) {
                computeStringSize += CodedOutputStream.computeFloatSize(5, f);
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
            if (!(obj instanceof Answer)) {
                return super.equals(obj);
            }
            Answer answer = (Answer) obj;
            return getSource().equals(answer.getSource()) && getFaqQuestion().equals(answer.getFaqQuestion()) && getAnswer().equals(answer.getAnswer()) && this.matchConfidenceLevel_ == answer.matchConfidenceLevel_ && Float.floatToIntBits(getMatchConfidence()) == Float.floatToIntBits(answer.getMatchConfidence()) && this.unknownFields.equals(answer.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSource().hashCode()) * 37) + 2) * 53) + getFaqQuestion().hashCode()) * 37) + 3) * 53) + getAnswer().hashCode()) * 37) + 4) * 53) + this.matchConfidenceLevel_) * 37) + 5) * 53) + Float.floatToIntBits(getMatchConfidence())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Answer parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Answer parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Answer parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Answer parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Answer parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Answer parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Answer parseFrom(InputStream inputStream) throws IOException {
            return (Answer) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Answer parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Answer) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Answer parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Answer) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Answer parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Answer) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Answer parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Answer) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Answer parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Answer) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Answer answer) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(answer);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AnswerOrBuilder {
            private Object answer_;
            private Object faqQuestion_;
            private int matchConfidenceLevel_;
            private float matchConfidence_;
            private Object source_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SessionProto.f1842x2fb3e3ea;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SessionProto.f1843x251d8068.ensureFieldAccessorsInitialized(Answer.class, Builder.class);
            }

            private Builder() {
                this.source_ = "";
                this.faqQuestion_ = "";
                this.answer_ = "";
                this.matchConfidenceLevel_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.source_ = "";
                this.faqQuestion_ = "";
                this.answer_ = "";
                this.matchConfidenceLevel_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Answer.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.source_ = "";
                this.faqQuestion_ = "";
                this.answer_ = "";
                this.matchConfidenceLevel_ = 0;
                this.matchConfidence_ = 0.0f;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SessionProto.f1842x2fb3e3ea;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Answer getDefaultInstanceForType() {
                return Answer.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Answer build() {
                Answer buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Answer buildPartial() {
                Answer answer = new Answer(this);
                answer.source_ = this.source_;
                answer.faqQuestion_ = this.faqQuestion_;
                answer.answer_ = this.answer_;
                answer.matchConfidenceLevel_ = this.matchConfidenceLevel_;
                answer.matchConfidence_ = this.matchConfidence_;
                onBuilt();
                return answer;
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
                if (message instanceof Answer) {
                    return mergeFrom((Answer) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Answer answer) {
                if (answer == Answer.getDefaultInstance()) {
                    return this;
                }
                if (!answer.getSource().isEmpty()) {
                    this.source_ = answer.source_;
                    onChanged();
                }
                if (!answer.getFaqQuestion().isEmpty()) {
                    this.faqQuestion_ = answer.faqQuestion_;
                    onChanged();
                }
                if (!answer.getAnswer().isEmpty()) {
                    this.answer_ = answer.answer_;
                    onChanged();
                }
                if (answer.matchConfidenceLevel_ != 0) {
                    setMatchConfidenceLevelValue(answer.getMatchConfidenceLevelValue());
                }
                if (answer.getMatchConfidence() != 0.0f) {
                    setMatchConfidence(answer.getMatchConfidence());
                }
                mergeUnknownFields(answer.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Answer answer = null;
                try {
                    try {
                        Answer answer2 = (Answer) Answer.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (answer2 != null) {
                            mergeFrom(answer2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Answer answer3 = (Answer) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            answer = answer3;
                            if (answer != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (answer != null) {
                        mergeFrom(answer);
                    }
                    throw th;
                }
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
            public String getSource() {
                Object obj = this.source_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.source_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
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
                this.source_ = Answer.getDefaultInstance().getSource();
                onChanged();
                return this;
            }

            public Builder setSourceBytes(ByteString byteString) {
                if (byteString != null) {
                    Answer.checkByteStringIsUtf8(byteString);
                    this.source_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
            public String getFaqQuestion() {
                Object obj = this.faqQuestion_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.faqQuestion_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
            public ByteString getFaqQuestionBytes() {
                Object obj = this.faqQuestion_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.faqQuestion_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setFaqQuestion(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.faqQuestion_ = str;
                onChanged();
                return this;
            }

            public Builder clearFaqQuestion() {
                this.faqQuestion_ = Answer.getDefaultInstance().getFaqQuestion();
                onChanged();
                return this;
            }

            public Builder setFaqQuestionBytes(ByteString byteString) {
                if (byteString != null) {
                    Answer.checkByteStringIsUtf8(byteString);
                    this.faqQuestion_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
            public String getAnswer() {
                Object obj = this.answer_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.answer_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
            public ByteString getAnswerBytes() {
                Object obj = this.answer_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.answer_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setAnswer(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.answer_ = str;
                onChanged();
                return this;
            }

            public Builder clearAnswer() {
                this.answer_ = Answer.getDefaultInstance().getAnswer();
                onChanged();
                return this;
            }

            public Builder setAnswerBytes(ByteString byteString) {
                if (byteString != null) {
                    Answer.checkByteStringIsUtf8(byteString);
                    this.answer_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
            public int getMatchConfidenceLevelValue() {
                return this.matchConfidenceLevel_;
            }

            public Builder setMatchConfidenceLevelValue(int i) {
                this.matchConfidenceLevel_ = i;
                onChanged();
                return this;
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
            public MatchConfidenceLevel getMatchConfidenceLevel() {
                MatchConfidenceLevel valueOf = MatchConfidenceLevel.valueOf(this.matchConfidenceLevel_);
                return valueOf == null ? MatchConfidenceLevel.UNRECOGNIZED : valueOf;
            }

            public Builder setMatchConfidenceLevel(MatchConfidenceLevel matchConfidenceLevel) {
                if (matchConfidenceLevel == null) {
                    throw new NullPointerException();
                }
                this.matchConfidenceLevel_ = matchConfidenceLevel.getNumber();
                onChanged();
                return this;
            }

            public Builder clearMatchConfidenceLevel() {
                this.matchConfidenceLevel_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers.AnswerOrBuilder
            public float getMatchConfidence() {
                return this.matchConfidence_;
            }

            public Builder setMatchConfidence(float f) {
                this.matchConfidence_ = f;
                onChanged();
                return this;
            }

            public Builder clearMatchConfidence() {
                this.matchConfidence_ = 0.0f;
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

        public static Answer getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Answer> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Answer> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Answer getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
    public List<Answer> getAnswersList() {
        return this.answers_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
    public List<? extends AnswerOrBuilder> getAnswersOrBuilderList() {
        return this.answers_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
    public int getAnswersCount() {
        return this.answers_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
    public Answer getAnswers(int i) {
        return this.answers_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
    public AnswerOrBuilder getAnswersOrBuilder(int i) {
        return this.answers_.get(i);
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
        for (int i = 0; i < this.answers_.size(); i++) {
            codedOutputStream.writeMessage(1, this.answers_.get(i));
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
        for (int i3 = 0; i3 < this.answers_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.answers_.get(i3));
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
        if (!(obj instanceof KnowledgeAnswers)) {
            return super.equals(obj);
        }
        KnowledgeAnswers knowledgeAnswers = (KnowledgeAnswers) obj;
        return getAnswersList().equals(knowledgeAnswers.getAnswersList()) && this.unknownFields.equals(knowledgeAnswers.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getAnswersCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getAnswersList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static KnowledgeAnswers parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static KnowledgeAnswers parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static KnowledgeAnswers parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static KnowledgeAnswers parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static KnowledgeAnswers parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static KnowledgeAnswers parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static KnowledgeAnswers parseFrom(InputStream inputStream) throws IOException {
        return (KnowledgeAnswers) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static KnowledgeAnswers parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KnowledgeAnswers) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static KnowledgeAnswers parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KnowledgeAnswers) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static KnowledgeAnswers parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KnowledgeAnswers) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static KnowledgeAnswers parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KnowledgeAnswers) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static KnowledgeAnswers parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KnowledgeAnswers) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KnowledgeAnswers knowledgeAnswers) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(knowledgeAnswers);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements KnowledgeAnswersOrBuilder {
        private RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> answersBuilder_;
        private List<Answer> answers_;
        private int bitField0_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1844x741e4b35;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1845x583d6cb3.ensureFieldAccessorsInitialized(KnowledgeAnswers.class, Builder.class);
        }

        private Builder() {
            this.answers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.answers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (KnowledgeAnswers.alwaysUseFieldBuilders) {
                getAnswersFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.answers_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.f1844x741e4b35;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public KnowledgeAnswers getDefaultInstanceForType() {
            return KnowledgeAnswers.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public KnowledgeAnswers build() {
            KnowledgeAnswers buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public KnowledgeAnswers buildPartial() {
            KnowledgeAnswers knowledgeAnswers = new KnowledgeAnswers(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.answers_ = Collections.unmodifiableList(this.answers_);
                    this.bitField0_ &= -2;
                }
                knowledgeAnswers.answers_ = this.answers_;
            } else {
                knowledgeAnswers.answers_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return knowledgeAnswers;
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
            if (message instanceof KnowledgeAnswers) {
                return mergeFrom((KnowledgeAnswers) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(KnowledgeAnswers knowledgeAnswers) {
            if (knowledgeAnswers == KnowledgeAnswers.getDefaultInstance()) {
                return this;
            }
            if (this.answersBuilder_ == null) {
                if (!knowledgeAnswers.answers_.isEmpty()) {
                    if (this.answers_.isEmpty()) {
                        this.answers_ = knowledgeAnswers.answers_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureAnswersIsMutable();
                        this.answers_.addAll(knowledgeAnswers.answers_);
                    }
                    onChanged();
                }
            } else if (!knowledgeAnswers.answers_.isEmpty()) {
                if (!this.answersBuilder_.isEmpty()) {
                    this.answersBuilder_.addAllMessages(knowledgeAnswers.answers_);
                } else {
                    this.answersBuilder_.dispose();
                    this.answersBuilder_ = null;
                    this.answers_ = knowledgeAnswers.answers_;
                    this.bitField0_ &= -2;
                    this.answersBuilder_ = KnowledgeAnswers.alwaysUseFieldBuilders ? getAnswersFieldBuilder() : null;
                }
            }
            mergeUnknownFields(knowledgeAnswers.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            KnowledgeAnswers knowledgeAnswers = null;
            try {
                try {
                    KnowledgeAnswers knowledgeAnswers2 = (KnowledgeAnswers) KnowledgeAnswers.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (knowledgeAnswers2 != null) {
                        mergeFrom(knowledgeAnswers2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    KnowledgeAnswers knowledgeAnswers3 = (KnowledgeAnswers) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        knowledgeAnswers = knowledgeAnswers3;
                        if (knowledgeAnswers != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (knowledgeAnswers != null) {
                    mergeFrom(knowledgeAnswers);
                }
                throw th;
            }
        }

        private void ensureAnswersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.answers_ = new ArrayList(this.answers_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
        public List<Answer> getAnswersList() {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.answers_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
        public int getAnswersCount() {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.answers_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
        public Answer getAnswers(int i) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.answers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAnswers(int i, Answer answer) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, answer);
            } else {
                if (answer == null) {
                    throw new NullPointerException();
                }
                ensureAnswersIsMutable();
                this.answers_.set(i, answer);
                onChanged();
            }
            return this;
        }

        public Builder setAnswers(int i, Answer.Builder builder) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAnswersIsMutable();
                this.answers_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAnswers(Answer answer) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(answer);
            } else {
                if (answer == null) {
                    throw new NullPointerException();
                }
                ensureAnswersIsMutable();
                this.answers_.add(answer);
                onChanged();
            }
            return this;
        }

        public Builder addAnswers(int i, Answer answer) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, answer);
            } else {
                if (answer == null) {
                    throw new NullPointerException();
                }
                ensureAnswersIsMutable();
                this.answers_.add(i, answer);
                onChanged();
            }
            return this;
        }

        public Builder addAnswers(Answer.Builder builder) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAnswersIsMutable();
                this.answers_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAnswers(int i, Answer.Builder builder) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAnswersIsMutable();
                this.answers_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAnswers(Iterable<? extends Answer> iterable) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAnswersIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.answers_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAnswers() {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.answers_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAnswers(int i) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAnswersIsMutable();
                this.answers_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Answer.Builder getAnswersBuilder(int i) {
            return getAnswersFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
        public AnswerOrBuilder getAnswersOrBuilder(int i) {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.answers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeAnswersOrBuilder
        public List<? extends AnswerOrBuilder> getAnswersOrBuilderList() {
            RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> repeatedFieldBuilderV3 = this.answersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.answers_);
        }

        public Answer.Builder addAnswersBuilder() {
            return getAnswersFieldBuilder().addBuilder(Answer.getDefaultInstance());
        }

        public Answer.Builder addAnswersBuilder(int i) {
            return getAnswersFieldBuilder().addBuilder(i, Answer.getDefaultInstance());
        }

        public List<Answer.Builder> getAnswersBuilderList() {
            return getAnswersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Answer, Answer.Builder, AnswerOrBuilder> getAnswersFieldBuilder() {
            if (this.answersBuilder_ == null) {
                this.answersBuilder_ = new RepeatedFieldBuilderV3<>(this.answers_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.answers_ = null;
            }
            return this.answersBuilder_;
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

    public static KnowledgeAnswers getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KnowledgeAnswers> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<KnowledgeAnswers> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public KnowledgeAnswers getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
