package com.google.api;

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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
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
public final class Distribution extends GeneratedMessageV3 implements DistributionOrBuilder {
    public static final int BUCKET_COUNTS_FIELD_NUMBER = 7;
    public static final int BUCKET_OPTIONS_FIELD_NUMBER = 6;
    public static final int COUNT_FIELD_NUMBER = 1;
    public static final int EXEMPLARS_FIELD_NUMBER = 10;
    public static final int MEAN_FIELD_NUMBER = 2;
    public static final int RANGE_FIELD_NUMBER = 4;
    public static final int SUM_OF_SQUARED_DEVIATION_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private int bucketCountsMemoizedSerializedSize;
    private Internal.LongList bucketCounts_;
    private BucketOptions bucketOptions_;
    private long count_;
    private List<Exemplar> exemplars_;
    private double mean_;
    private byte memoizedIsInitialized;
    private Range range_;
    private double sumOfSquaredDeviation_;
    private static final Distribution DEFAULT_INSTANCE = new Distribution();
    private static final Parser<Distribution> PARSER = new AbstractParser<Distribution>() { // from class: com.google.api.Distribution.1
        @Override // com.google.protobuf.Parser
        public Distribution parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Distribution(codedInputStream, extensionRegistryLite);
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface BucketOptionsOrBuilder extends MessageOrBuilder {
        BucketOptions.Explicit getExplicitBuckets();

        BucketOptions.ExplicitOrBuilder getExplicitBucketsOrBuilder();

        BucketOptions.Exponential getExponentialBuckets();

        BucketOptions.ExponentialOrBuilder getExponentialBucketsOrBuilder();

        BucketOptions.Linear getLinearBuckets();

        BucketOptions.LinearOrBuilder getLinearBucketsOrBuilder();

        BucketOptions.OptionsCase getOptionsCase();

        boolean hasExplicitBuckets();

        boolean hasExponentialBuckets();

        boolean hasLinearBuckets();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface ExemplarOrBuilder extends MessageOrBuilder {
        Any getAttachments(int i);

        int getAttachmentsCount();

        List<Any> getAttachmentsList();

        AnyOrBuilder getAttachmentsOrBuilder(int i);

        List<? extends AnyOrBuilder> getAttachmentsOrBuilderList();

        Timestamp getTimestamp();

        TimestampOrBuilder getTimestampOrBuilder();

        double getValue();

        boolean hasTimestamp();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface RangeOrBuilder extends MessageOrBuilder {
        double getMax();

        double getMin();
    }

    static /* synthetic */ Internal.LongList access$6400() {
        return emptyLongList();
    }

    static /* synthetic */ Internal.LongList access$7600() {
        return emptyLongList();
    }

    static /* synthetic */ Internal.LongList access$7800() {
        return emptyLongList();
    }

    private Distribution(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.bucketCountsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Distribution() {
        this.bucketCountsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.bucketCounts_ = emptyLongList();
        this.exemplars_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Distribution();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8 */
    private Distribution(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag == 8) {
                                this.count_ = codedInputStream.readInt64();
                            } else if (readTag == 17) {
                                this.mean_ = codedInputStream.readDouble();
                            } else if (readTag != 25) {
                                if (readTag == 34) {
                                    Range.Builder builder = this.range_ != null ? this.range_.toBuilder() : null;
                                    this.range_ = (Range) codedInputStream.readMessage(Range.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.range_);
                                        this.range_ = builder.buildPartial();
                                    }
                                } else if (readTag == 50) {
                                    BucketOptions.Builder builder2 = this.bucketOptions_ != null ? this.bucketOptions_.toBuilder() : null;
                                    this.bucketOptions_ = (BucketOptions) codedInputStream.readMessage(BucketOptions.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.bucketOptions_);
                                        this.bucketOptions_ = builder2.buildPartial();
                                    }
                                } else if (readTag == 56) {
                                    int i = (c == true ? 1 : 0) & 1;
                                    c = c;
                                    if (i == 0) {
                                        this.bucketCounts_ = newLongList();
                                        c = (c == true ? 1 : 0) | 1;
                                    }
                                    this.bucketCounts_.addLong(codedInputStream.readInt64());
                                } else if (readTag == 58) {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    int i2 = (c == true ? 1 : 0) & 1;
                                    c = c;
                                    if (i2 == 0) {
                                        c = c;
                                        if (codedInputStream.getBytesUntilLimit() > 0) {
                                            this.bucketCounts_ = newLongList();
                                            c = (c == true ? 1 : 0) | 1;
                                        }
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.bucketCounts_.addLong(codedInputStream.readInt64());
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                } else if (readTag == 82) {
                                    int i3 = (c == true ? 1 : 0) & 2;
                                    c = c;
                                    if (i3 == 0) {
                                        this.exemplars_ = new ArrayList();
                                        c = (c == true ? 1 : 0) | 2;
                                    }
                                    this.exemplars_.add(codedInputStream.readMessage(Exemplar.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.sumOfSquaredDeviation_ = codedInputStream.readDouble();
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
                    this.bucketCounts_.makeImmutable();
                }
                if (((c == true ? 1 : 0) & 2) != 0) {
                    this.exemplars_ = Collections.unmodifiableList(this.exemplars_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DistributionProto.internal_static_google_api_Distribution_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DistributionProto.internal_static_google_api_Distribution_fieldAccessorTable.ensureFieldAccessorsInitialized(Distribution.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Range extends GeneratedMessageV3 implements RangeOrBuilder {
        public static final int MAX_FIELD_NUMBER = 2;
        public static final int MIN_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private double max_;
        private byte memoizedIsInitialized;
        private double min_;
        private static final Range DEFAULT_INSTANCE = new Range();
        private static final Parser<Range> PARSER = new AbstractParser<Range>() { // from class: com.google.api.Distribution.Range.1
            @Override // com.google.protobuf.Parser
            public Range parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Range(codedInputStream, extensionRegistryLite);
            }
        };

        private Range(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Range() {
            this.memoizedIsInitialized = (byte) -1;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Range();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Range(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 9) {
                                    this.min_ = codedInputStream.readDouble();
                                } else if (readTag == 17) {
                                    this.max_ = codedInputStream.readDouble();
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
            return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Range.class, Builder.class);
        }

        @Override // com.google.api.Distribution.RangeOrBuilder
        public double getMin() {
            return this.min_;
        }

        @Override // com.google.api.Distribution.RangeOrBuilder
        public double getMax() {
            return this.max_;
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
            double d = this.min_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(1, d);
            }
            double d2 = this.max_;
            if (d2 != 0.0d) {
                codedOutputStream.writeDouble(2, d2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            double d = this.min_;
            int computeDoubleSize = d != 0.0d ? 0 + CodedOutputStream.computeDoubleSize(1, d) : 0;
            double d2 = this.max_;
            if (d2 != 0.0d) {
                computeDoubleSize += CodedOutputStream.computeDoubleSize(2, d2);
            }
            int serializedSize = computeDoubleSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Range)) {
                return super.equals(obj);
            }
            Range range = (Range) obj;
            return Double.doubleToLongBits(getMin()) == Double.doubleToLongBits(range.getMin()) && Double.doubleToLongBits(getMax()) == Double.doubleToLongBits(range.getMax()) && this.unknownFields.equals(range.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getMin()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getMax()))) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Range parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Range parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Range parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Range parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Range parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Range parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Range parseFrom(InputStream inputStream) throws IOException {
            return (Range) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Range parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Range) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Range parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Range) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Range parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Range) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Range parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Range) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Range parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Range) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Range range) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(range);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RangeOrBuilder {
            private double max_;
            private double min_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Range.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Range.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.min_ = 0.0d;
                this.max_ = 0.0d;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Range getDefaultInstanceForType() {
                return Range.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Range build() {
                Range buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Range buildPartial() {
                Range range = new Range(this);
                range.min_ = this.min_;
                range.max_ = this.max_;
                onBuilt();
                return range;
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
                if (message instanceof Range) {
                    return mergeFrom((Range) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Range range) {
                if (range == Range.getDefaultInstance()) {
                    return this;
                }
                if (range.getMin() != 0.0d) {
                    setMin(range.getMin());
                }
                if (range.getMax() != 0.0d) {
                    setMax(range.getMax());
                }
                mergeUnknownFields(range.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Range range = null;
                try {
                    try {
                        Range range2 = (Range) Range.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (range2 != null) {
                            mergeFrom(range2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Range range3 = (Range) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            range = range3;
                            if (range != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (range != null) {
                        mergeFrom(range);
                    }
                    throw th;
                }
            }

            @Override // com.google.api.Distribution.RangeOrBuilder
            public double getMin() {
                return this.min_;
            }

            public Builder setMin(double d) {
                this.min_ = d;
                onChanged();
                return this;
            }

            public Builder clearMin() {
                this.min_ = 0.0d;
                onChanged();
                return this;
            }

            @Override // com.google.api.Distribution.RangeOrBuilder
            public double getMax() {
                return this.max_;
            }

            public Builder setMax(double d) {
                this.max_ = d;
                onChanged();
                return this;
            }

            public Builder clearMax() {
                this.max_ = 0.0d;
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

        public static Range getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Range> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Range> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Range getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class BucketOptions extends GeneratedMessageV3 implements BucketOptionsOrBuilder {
        public static final int EXPLICIT_BUCKETS_FIELD_NUMBER = 3;
        public static final int EXPONENTIAL_BUCKETS_FIELD_NUMBER = 2;
        public static final int LINEAR_BUCKETS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int optionsCase_;
        private Object options_;
        private static final BucketOptions DEFAULT_INSTANCE = new BucketOptions();
        private static final Parser<BucketOptions> PARSER = new AbstractParser<BucketOptions>() { // from class: com.google.api.Distribution.BucketOptions.1
            @Override // com.google.protobuf.Parser
            public BucketOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BucketOptions(codedInputStream, extensionRegistryLite);
            }
        };

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public interface ExplicitOrBuilder extends MessageOrBuilder {
            double getBounds(int i);

            int getBoundsCount();

            List<Double> getBoundsList();
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public interface ExponentialOrBuilder extends MessageOrBuilder {
            double getGrowthFactor();

            int getNumFiniteBuckets();

            double getScale();
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public interface LinearOrBuilder extends MessageOrBuilder {
            int getNumFiniteBuckets();

            double getOffset();

            double getWidth();
        }

        private BucketOptions(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.optionsCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private BucketOptions() {
            this.optionsCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new BucketOptions();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BucketOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw new NullPointerException();
            }
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                Linear.Builder builder = this.optionsCase_ == 1 ? ((Linear) this.options_).toBuilder() : null;
                                this.options_ = codedInputStream.readMessage(Linear.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((Linear) this.options_);
                                    this.options_ = builder.buildPartial();
                                }
                                this.optionsCase_ = 1;
                            } else if (readTag == 18) {
                                Exponential.Builder builder2 = this.optionsCase_ == 2 ? ((Exponential) this.options_).toBuilder() : null;
                                this.options_ = codedInputStream.readMessage(Exponential.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom((Exponential) this.options_);
                                    this.options_ = builder2.buildPartial();
                                }
                                this.optionsCase_ = 2;
                            } else if (readTag == 26) {
                                Explicit.Builder builder3 = this.optionsCase_ == 3 ? ((Explicit) this.options_).toBuilder() : null;
                                this.options_ = codedInputStream.readMessage(Explicit.parser(), extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom((Explicit) this.options_);
                                    this.options_ = builder3.buildPartial();
                                }
                                this.optionsCase_ = 3;
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
            return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.f1321x433aff57.ensureFieldAccessorsInitialized(BucketOptions.class, Builder.class);
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public static final class Linear extends GeneratedMessageV3 implements LinearOrBuilder {
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            public static final int OFFSET_FIELD_NUMBER = 3;
            public static final int WIDTH_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private byte memoizedIsInitialized;
            private int numFiniteBuckets_;
            private double offset_;
            private double width_;
            private static final Linear DEFAULT_INSTANCE = new Linear();
            private static final Parser<Linear> PARSER = new AbstractParser<Linear>() { // from class: com.google.api.Distribution.BucketOptions.Linear.1
                @Override // com.google.protobuf.Parser
                public Linear parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Linear(codedInputStream, extensionRegistryLite);
                }
            };

            private Linear(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Linear() {
                this.memoizedIsInitialized = (byte) -1;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new Linear();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Linear(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite == null) {
                    throw new NullPointerException();
                }
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.numFiniteBuckets_ = codedInputStream.readInt32();
                                } else if (readTag == 17) {
                                    this.width_ = codedInputStream.readDouble();
                                } else if (readTag == 25) {
                                    this.offset_ = codedInputStream.readDouble();
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
                return DistributionProto.f1319x65ad46ff;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.f1320xf2a0fe7d.ensureFieldAccessorsInitialized(Linear.class, Builder.class);
            }

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public double getWidth() {
                return this.width_;
            }

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public double getOffset() {
                return this.offset_;
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
                int i = this.numFiniteBuckets_;
                if (i != 0) {
                    codedOutputStream.writeInt32(1, i);
                }
                double d = this.width_;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(2, d);
                }
                double d2 = this.offset_;
                if (d2 != 0.0d) {
                    codedOutputStream.writeDouble(3, d2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.numFiniteBuckets_;
                int computeInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0;
                double d = this.width_;
                if (d != 0.0d) {
                    computeInt32Size += CodedOutputStream.computeDoubleSize(2, d);
                }
                double d2 = this.offset_;
                if (d2 != 0.0d) {
                    computeInt32Size += CodedOutputStream.computeDoubleSize(3, d2);
                }
                int serializedSize = computeInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Linear)) {
                    return super.equals(obj);
                }
                Linear linear = (Linear) obj;
                return getNumFiniteBuckets() == linear.getNumFiniteBuckets() && Double.doubleToLongBits(getWidth()) == Double.doubleToLongBits(linear.getWidth()) && Double.doubleToLongBits(getOffset()) == Double.doubleToLongBits(linear.getOffset()) && this.unknownFields.equals(linear.unknownFields);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getNumFiniteBuckets()) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getWidth()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getOffset()))) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            public static Linear parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Linear parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Linear parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Linear parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Linear parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Linear parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Linear parseFrom(InputStream inputStream) throws IOException {
                return (Linear) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Linear parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Linear) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Linear parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Linear) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Linear parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Linear) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Linear parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Linear) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Linear parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Linear) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Linear linear) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(linear);
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
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LinearOrBuilder {
                private int numFiniteBuckets_;
                private double offset_;
                private double width_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.f1319x65ad46ff;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.f1320xf2a0fe7d.ensureFieldAccessorsInitialized(Linear.class, Builder.class);
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Linear.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.numFiniteBuckets_ = 0;
                    this.width_ = 0.0d;
                    this.offset_ = 0.0d;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.f1319x65ad46ff;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Linear getDefaultInstanceForType() {
                    return Linear.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Linear build() {
                    Linear buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Linear buildPartial() {
                    Linear linear = new Linear(this);
                    linear.numFiniteBuckets_ = this.numFiniteBuckets_;
                    linear.width_ = this.width_;
                    linear.offset_ = this.offset_;
                    onBuilt();
                    return linear;
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
                    if (message instanceof Linear) {
                        return mergeFrom((Linear) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Linear linear) {
                    if (linear == Linear.getDefaultInstance()) {
                        return this;
                    }
                    if (linear.getNumFiniteBuckets() != 0) {
                        setNumFiniteBuckets(linear.getNumFiniteBuckets());
                    }
                    if (linear.getWidth() != 0.0d) {
                        setWidth(linear.getWidth());
                    }
                    if (linear.getOffset() != 0.0d) {
                        setOffset(linear.getOffset());
                    }
                    mergeUnknownFields(linear.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    Linear linear = null;
                    try {
                        try {
                            Linear linear2 = (Linear) Linear.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                            if (linear2 != null) {
                                mergeFrom(linear2);
                            }
                            return this;
                        } catch (InvalidProtocolBufferException e) {
                            Linear linear3 = (Linear) e.getUnfinishedMessage();
                            try {
                                throw e.unwrapIOException();
                            } catch (Throwable th) {
                                th = th;
                                linear = linear3;
                                if (linear != null) {
                                }
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (linear != null) {
                            mergeFrom(linear);
                        }
                        throw th;
                    }
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public int getNumFiniteBuckets() {
                    return this.numFiniteBuckets_;
                }

                public Builder setNumFiniteBuckets(int i) {
                    this.numFiniteBuckets_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    this.numFiniteBuckets_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public double getWidth() {
                    return this.width_;
                }

                public Builder setWidth(double d) {
                    this.width_ = d;
                    onChanged();
                    return this;
                }

                public Builder clearWidth() {
                    this.width_ = 0.0d;
                    onChanged();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public double getOffset() {
                    return this.offset_;
                }

                public Builder setOffset(double d) {
                    this.offset_ = d;
                    onChanged();
                    return this;
                }

                public Builder clearOffset() {
                    this.offset_ = 0.0d;
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

            public static Linear getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Linear> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<Linear> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Linear getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public static final class Exponential extends GeneratedMessageV3 implements ExponentialOrBuilder {
            public static final int GROWTH_FACTOR_FIELD_NUMBER = 2;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            public static final int SCALE_FIELD_NUMBER = 3;
            private static final long serialVersionUID = 0;
            private double growthFactor_;
            private byte memoizedIsInitialized;
            private int numFiniteBuckets_;
            private double scale_;
            private static final Exponential DEFAULT_INSTANCE = new Exponential();
            private static final Parser<Exponential> PARSER = new AbstractParser<Exponential>() { // from class: com.google.api.Distribution.BucketOptions.Exponential.1
                @Override // com.google.protobuf.Parser
                public Exponential parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Exponential(codedInputStream, extensionRegistryLite);
                }
            };

            private Exponential(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Exponential() {
                this.memoizedIsInitialized = (byte) -1;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new Exponential();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Exponential(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite == null) {
                    throw new NullPointerException();
                }
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.numFiniteBuckets_ = codedInputStream.readInt32();
                                } else if (readTag == 17) {
                                    this.growthFactor_ = codedInputStream.readDouble();
                                } else if (readTag == 25) {
                                    this.scale_ = codedInputStream.readDouble();
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
                return DistributionProto.f1317xdd8d7e33;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.f1318x5c40c1b1.ensureFieldAccessorsInitialized(Exponential.class, Builder.class);
            }

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public double getGrowthFactor() {
                return this.growthFactor_;
            }

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public double getScale() {
                return this.scale_;
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
                int i = this.numFiniteBuckets_;
                if (i != 0) {
                    codedOutputStream.writeInt32(1, i);
                }
                double d = this.growthFactor_;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(2, d);
                }
                double d2 = this.scale_;
                if (d2 != 0.0d) {
                    codedOutputStream.writeDouble(3, d2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.numFiniteBuckets_;
                int computeInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0;
                double d = this.growthFactor_;
                if (d != 0.0d) {
                    computeInt32Size += CodedOutputStream.computeDoubleSize(2, d);
                }
                double d2 = this.scale_;
                if (d2 != 0.0d) {
                    computeInt32Size += CodedOutputStream.computeDoubleSize(3, d2);
                }
                int serializedSize = computeInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Exponential)) {
                    return super.equals(obj);
                }
                Exponential exponential = (Exponential) obj;
                return getNumFiniteBuckets() == exponential.getNumFiniteBuckets() && Double.doubleToLongBits(getGrowthFactor()) == Double.doubleToLongBits(exponential.getGrowthFactor()) && Double.doubleToLongBits(getScale()) == Double.doubleToLongBits(exponential.getScale()) && this.unknownFields.equals(exponential.unknownFields);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getNumFiniteBuckets()) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getGrowthFactor()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getScale()))) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            public static Exponential parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Exponential parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Exponential parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Exponential parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Exponential parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Exponential parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Exponential parseFrom(InputStream inputStream) throws IOException {
                return (Exponential) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Exponential parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Exponential) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Exponential parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Exponential) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Exponential parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Exponential) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Exponential parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Exponential) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Exponential parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Exponential) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Exponential exponential) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(exponential);
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
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExponentialOrBuilder {
                private double growthFactor_;
                private int numFiniteBuckets_;
                private double scale_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.f1317xdd8d7e33;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.f1318x5c40c1b1.ensureFieldAccessorsInitialized(Exponential.class, Builder.class);
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Exponential.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.numFiniteBuckets_ = 0;
                    this.growthFactor_ = 0.0d;
                    this.scale_ = 0.0d;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.f1317xdd8d7e33;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Exponential getDefaultInstanceForType() {
                    return Exponential.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Exponential build() {
                    Exponential buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Exponential buildPartial() {
                    Exponential exponential = new Exponential(this);
                    exponential.numFiniteBuckets_ = this.numFiniteBuckets_;
                    exponential.growthFactor_ = this.growthFactor_;
                    exponential.scale_ = this.scale_;
                    onBuilt();
                    return exponential;
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
                    if (message instanceof Exponential) {
                        return mergeFrom((Exponential) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Exponential exponential) {
                    if (exponential == Exponential.getDefaultInstance()) {
                        return this;
                    }
                    if (exponential.getNumFiniteBuckets() != 0) {
                        setNumFiniteBuckets(exponential.getNumFiniteBuckets());
                    }
                    if (exponential.getGrowthFactor() != 0.0d) {
                        setGrowthFactor(exponential.getGrowthFactor());
                    }
                    if (exponential.getScale() != 0.0d) {
                        setScale(exponential.getScale());
                    }
                    mergeUnknownFields(exponential.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    Exponential exponential = null;
                    try {
                        try {
                            Exponential exponential2 = (Exponential) Exponential.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                            if (exponential2 != null) {
                                mergeFrom(exponential2);
                            }
                            return this;
                        } catch (InvalidProtocolBufferException e) {
                            Exponential exponential3 = (Exponential) e.getUnfinishedMessage();
                            try {
                                throw e.unwrapIOException();
                            } catch (Throwable th) {
                                th = th;
                                exponential = exponential3;
                                if (exponential != null) {
                                }
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (exponential != null) {
                            mergeFrom(exponential);
                        }
                        throw th;
                    }
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public int getNumFiniteBuckets() {
                    return this.numFiniteBuckets_;
                }

                public Builder setNumFiniteBuckets(int i) {
                    this.numFiniteBuckets_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    this.numFiniteBuckets_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public double getGrowthFactor() {
                    return this.growthFactor_;
                }

                public Builder setGrowthFactor(double d) {
                    this.growthFactor_ = d;
                    onChanged();
                    return this;
                }

                public Builder clearGrowthFactor() {
                    this.growthFactor_ = 0.0d;
                    onChanged();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public double getScale() {
                    return this.scale_;
                }

                public Builder setScale(double d) {
                    this.scale_ = d;
                    onChanged();
                    return this;
                }

                public Builder clearScale() {
                    this.scale_ = 0.0d;
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

            public static Exponential getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Exponential> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<Exponential> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Exponential getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public static final class Explicit extends GeneratedMessageV3 implements ExplicitOrBuilder {
            public static final int BOUNDS_FIELD_NUMBER = 1;
            private static final Explicit DEFAULT_INSTANCE = new Explicit();
            private static final Parser<Explicit> PARSER = new AbstractParser<Explicit>() { // from class: com.google.api.Distribution.BucketOptions.Explicit.1
                @Override // com.google.protobuf.Parser
                public Explicit parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Explicit(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private int boundsMemoizedSerializedSize;
            private Internal.DoubleList bounds_;
            private byte memoizedIsInitialized;

            static /* synthetic */ Internal.DoubleList access$3200() {
                return emptyDoubleList();
            }

            static /* synthetic */ Internal.DoubleList access$3700() {
                return emptyDoubleList();
            }

            static /* synthetic */ Internal.DoubleList access$3900() {
                return emptyDoubleList();
            }

            private Explicit(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.boundsMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
            }

            private Explicit() {
                this.boundsMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
                this.bounds_ = emptyDoubleList();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new Explicit();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Explicit(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 9) {
                                    if (!(z2 & true)) {
                                        this.bounds_ = newDoubleList();
                                        z2 |= true;
                                    }
                                    this.bounds_.addDouble(codedInputStream.readDouble());
                                } else if (readTag == 10) {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.bounds_ = newDoubleList();
                                        z2 |= true;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.bounds_.addDouble(codedInputStream.readDouble());
                                    }
                                    codedInputStream.popLimit(pushLimit);
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
                        if (z2 & true) {
                            this.bounds_.makeImmutable();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.f1315x47eb0b10;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.f1316x8566218e.ensureFieldAccessorsInitialized(Explicit.class, Builder.class);
            }

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public List<Double> getBoundsList() {
                return this.bounds_;
            }

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public int getBoundsCount() {
                return this.bounds_.size();
            }

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public double getBounds(int i) {
                return this.bounds_.getDouble(i);
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
                getSerializedSize();
                if (getBoundsList().size() > 0) {
                    codedOutputStream.writeUInt32NoTag(10);
                    codedOutputStream.writeUInt32NoTag(this.boundsMemoizedSerializedSize);
                }
                for (int i = 0; i < this.bounds_.size(); i++) {
                    codedOutputStream.writeDoubleNoTag(this.bounds_.getDouble(i));
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int size = getBoundsList().size() * 8;
                int i2 = size + 0;
                if (!getBoundsList().isEmpty()) {
                    i2 = i2 + 1 + CodedOutputStream.computeInt32SizeNoTag(size);
                }
                this.boundsMemoizedSerializedSize = size;
                int serializedSize = i2 + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Explicit)) {
                    return super.equals(obj);
                }
                Explicit explicit = (Explicit) obj;
                return getBoundsList().equals(explicit.getBoundsList()) && this.unknownFields.equals(explicit.unknownFields);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + getDescriptor().hashCode();
                if (getBoundsCount() > 0) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getBoundsList().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static Explicit parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Explicit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Explicit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Explicit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Explicit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Explicit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Explicit parseFrom(InputStream inputStream) throws IOException {
                return (Explicit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Explicit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Explicit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Explicit parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Explicit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Explicit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Explicit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Explicit parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Explicit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Explicit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Explicit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Explicit explicit) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(explicit);
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
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExplicitOrBuilder {
                private int bitField0_;
                private Internal.DoubleList bounds_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return DistributionProto.f1315x47eb0b10;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.f1316x8566218e.ensureFieldAccessorsInitialized(Explicit.class, Builder.class);
                }

                private Builder() {
                    this.bounds_ = Explicit.access$3700();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.bounds_ = Explicit.access$3700();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Explicit.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.bounds_ = Explicit.access$3200();
                    this.bitField0_ &= -2;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DistributionProto.f1315x47eb0b10;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Explicit getDefaultInstanceForType() {
                    return Explicit.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Explicit build() {
                    Explicit buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Explicit buildPartial() {
                    Explicit explicit = new Explicit(this);
                    if ((this.bitField0_ & 1) != 0) {
                        this.bounds_.makeImmutable();
                        this.bitField0_ &= -2;
                    }
                    explicit.bounds_ = this.bounds_;
                    onBuilt();
                    return explicit;
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
                    if (message instanceof Explicit) {
                        return mergeFrom((Explicit) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Explicit explicit) {
                    if (explicit == Explicit.getDefaultInstance()) {
                        return this;
                    }
                    if (!explicit.bounds_.isEmpty()) {
                        if (this.bounds_.isEmpty()) {
                            this.bounds_ = explicit.bounds_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureBoundsIsMutable();
                            this.bounds_.addAll(explicit.bounds_);
                        }
                        onChanged();
                    }
                    mergeUnknownFields(explicit.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    Explicit explicit = null;
                    try {
                        try {
                            Explicit explicit2 = (Explicit) Explicit.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                            if (explicit2 != null) {
                                mergeFrom(explicit2);
                            }
                            return this;
                        } catch (InvalidProtocolBufferException e) {
                            Explicit explicit3 = (Explicit) e.getUnfinishedMessage();
                            try {
                                throw e.unwrapIOException();
                            } catch (Throwable th) {
                                th = th;
                                explicit = explicit3;
                                if (explicit != null) {
                                }
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (explicit != null) {
                            mergeFrom(explicit);
                        }
                        throw th;
                    }
                }

                private void ensureBoundsIsMutable() {
                    if ((this.bitField0_ & 1) == 0) {
                        this.bounds_ = Explicit.mutableCopy(this.bounds_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public List<Double> getBoundsList() {
                    return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.bounds_) : this.bounds_;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public int getBoundsCount() {
                    return this.bounds_.size();
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public double getBounds(int i) {
                    return this.bounds_.getDouble(i);
                }

                public Builder setBounds(int i, double d) {
                    ensureBoundsIsMutable();
                    this.bounds_.setDouble(i, d);
                    onChanged();
                    return this;
                }

                public Builder addBounds(double d) {
                    ensureBoundsIsMutable();
                    this.bounds_.addDouble(d);
                    onChanged();
                    return this;
                }

                public Builder addAllBounds(Iterable<? extends Double> iterable) {
                    ensureBoundsIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.bounds_);
                    onChanged();
                    return this;
                }

                public Builder clearBounds() {
                    this.bounds_ = Explicit.access$3900();
                    this.bitField0_ &= -2;
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

            public static Explicit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Explicit> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<Explicit> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Explicit getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public enum OptionsCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
            LINEAR_BUCKETS(1),
            EXPONENTIAL_BUCKETS(2),
            EXPLICIT_BUCKETS(3),
            OPTIONS_NOT_SET(0);

            private final int value;

            OptionsCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static OptionsCase valueOf(int i) {
                return forNumber(i);
            }

            public static OptionsCase forNumber(int i) {
                if (i == 0) {
                    return OPTIONS_NOT_SET;
                }
                if (i == 1) {
                    return LINEAR_BUCKETS;
                }
                if (i == 2) {
                    return EXPONENTIAL_BUCKETS;
                }
                if (i != 3) {
                    return null;
                }
                return EXPLICIT_BUCKETS;
            }

            @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public OptionsCase getOptionsCase() {
            return OptionsCase.forNumber(this.optionsCase_);
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasLinearBuckets() {
            return this.optionsCase_ == 1;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Linear getLinearBuckets() {
            if (this.optionsCase_ == 1) {
                return (Linear) this.options_;
            }
            return Linear.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public LinearOrBuilder getLinearBucketsOrBuilder() {
            if (this.optionsCase_ == 1) {
                return (Linear) this.options_;
            }
            return Linear.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasExponentialBuckets() {
            return this.optionsCase_ == 2;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Exponential getExponentialBuckets() {
            if (this.optionsCase_ == 2) {
                return (Exponential) this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public ExponentialOrBuilder getExponentialBucketsOrBuilder() {
            if (this.optionsCase_ == 2) {
                return (Exponential) this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasExplicitBuckets() {
            return this.optionsCase_ == 3;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Explicit getExplicitBuckets() {
            if (this.optionsCase_ == 3) {
                return (Explicit) this.options_;
            }
            return Explicit.getDefaultInstance();
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public ExplicitOrBuilder getExplicitBucketsOrBuilder() {
            if (this.optionsCase_ == 3) {
                return (Explicit) this.options_;
            }
            return Explicit.getDefaultInstance();
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
            if (this.optionsCase_ == 1) {
                codedOutputStream.writeMessage(1, (Linear) this.options_);
            }
            if (this.optionsCase_ == 2) {
                codedOutputStream.writeMessage(2, (Exponential) this.options_);
            }
            if (this.optionsCase_ == 3) {
                codedOutputStream.writeMessage(3, (Explicit) this.options_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.optionsCase_ == 1 ? 0 + CodedOutputStream.computeMessageSize(1, (Linear) this.options_) : 0;
            if (this.optionsCase_ == 2) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, (Exponential) this.options_);
            }
            if (this.optionsCase_ == 3) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, (Explicit) this.options_);
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
            if (!(obj instanceof BucketOptions)) {
                return super.equals(obj);
            }
            BucketOptions bucketOptions = (BucketOptions) obj;
            if (!getOptionsCase().equals(bucketOptions.getOptionsCase())) {
                return false;
            }
            int i = this.optionsCase_;
            if (i != 1) {
                if (i == 2) {
                    if (!getExponentialBuckets().equals(bucketOptions.getExponentialBuckets())) {
                        return false;
                    }
                } else if (i == 3 && !getExplicitBuckets().equals(bucketOptions.getExplicitBuckets())) {
                    return false;
                }
            } else if (!getLinearBuckets().equals(bucketOptions.getLinearBuckets())) {
                return false;
            }
            return this.unknownFields.equals(bucketOptions.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i;
            int hashCode;
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode2 = 779 + getDescriptor().hashCode();
            int i2 = this.optionsCase_;
            if (i2 == 1) {
                i = ((hashCode2 * 37) + 1) * 53;
                hashCode = getLinearBuckets().hashCode();
            } else if (i2 == 2) {
                i = ((hashCode2 * 37) + 2) * 53;
                hashCode = getExponentialBuckets().hashCode();
            } else {
                if (i2 == 3) {
                    i = ((hashCode2 * 37) + 3) * 53;
                    hashCode = getExplicitBuckets().hashCode();
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

        public static BucketOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static BucketOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static BucketOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static BucketOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(InputStream inputStream) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BucketOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BucketOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BucketOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BucketOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BucketOptions bucketOptions) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(bucketOptions);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BucketOptionsOrBuilder {
            private SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> explicitBucketsBuilder_;
            private SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> exponentialBucketsBuilder_;
            private SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> linearBucketsBuilder_;
            private int optionsCase_;
            private Object options_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.f1321x433aff57.ensureFieldAccessorsInitialized(BucketOptions.class, Builder.class);
            }

            private Builder() {
                this.optionsCase_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.optionsCase_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BucketOptions.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.optionsCase_ = 0;
                this.options_ = null;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public BucketOptions getDefaultInstanceForType() {
                return BucketOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public BucketOptions build() {
                BucketOptions buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public BucketOptions buildPartial() {
                BucketOptions bucketOptions = new BucketOptions(this);
                if (this.optionsCase_ == 1) {
                    SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV3.build();
                    }
                }
                if (this.optionsCase_ == 2) {
                    SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV32 = this.exponentialBucketsBuilder_;
                    if (singleFieldBuilderV32 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV32.build();
                    }
                }
                if (this.optionsCase_ == 3) {
                    SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV33 = this.explicitBucketsBuilder_;
                    if (singleFieldBuilderV33 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV33.build();
                    }
                }
                bucketOptions.optionsCase_ = this.optionsCase_;
                onBuilt();
                return bucketOptions;
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
                if (message instanceof BucketOptions) {
                    return mergeFrom((BucketOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BucketOptions bucketOptions) {
                if (bucketOptions == BucketOptions.getDefaultInstance()) {
                    return this;
                }
                int i = C19712.$SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[bucketOptions.getOptionsCase().ordinal()];
                if (i == 1) {
                    mergeLinearBuckets(bucketOptions.getLinearBuckets());
                } else if (i == 2) {
                    mergeExponentialBuckets(bucketOptions.getExponentialBuckets());
                } else if (i == 3) {
                    mergeExplicitBuckets(bucketOptions.getExplicitBuckets());
                }
                mergeUnknownFields(bucketOptions.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                BucketOptions bucketOptions = null;
                try {
                    try {
                        BucketOptions bucketOptions2 = (BucketOptions) BucketOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (bucketOptions2 != null) {
                            mergeFrom(bucketOptions2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        BucketOptions bucketOptions3 = (BucketOptions) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            bucketOptions = bucketOptions3;
                            if (bucketOptions != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bucketOptions != null) {
                        mergeFrom(bucketOptions);
                    }
                    throw th;
                }
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public OptionsCase getOptionsCase() {
                return OptionsCase.forNumber(this.optionsCase_);
            }

            public Builder clearOptions() {
                this.optionsCase_ = 0;
                this.options_ = null;
                onChanged();
                return this;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasLinearBuckets() {
                return this.optionsCase_ == 1;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Linear getLinearBuckets() {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 1) {
                        return (Linear) this.options_;
                    }
                    return Linear.getDefaultInstance();
                }
                if (this.optionsCase_ == 1) {
                    return singleFieldBuilderV3.getMessage();
                }
                return Linear.getDefaultInstance();
            }

            public Builder setLinearBuckets(Linear linear) {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(linear);
                } else {
                    if (linear == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = linear;
                    onChanged();
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder setLinearBuckets(Linear.Builder builder) {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder mergeLinearBuckets(Linear linear) {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 1 && this.options_ != Linear.getDefaultInstance()) {
                        this.options_ = Linear.newBuilder((Linear) this.options_).mergeFrom(linear).buildPartial();
                    } else {
                        this.options_ = linear;
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 1) {
                        singleFieldBuilderV3.mergeFrom(linear);
                    }
                    this.linearBucketsBuilder_.setMessage(linear);
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder clearLinearBuckets() {
                if (this.linearBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 1) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                        onChanged();
                    }
                } else {
                    if (this.optionsCase_ == 1) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.linearBucketsBuilder_.clear();
                }
                return this;
            }

            public Linear.Builder getLinearBucketsBuilder() {
                return getLinearBucketsFieldBuilder().getBuilder();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public LinearOrBuilder getLinearBucketsOrBuilder() {
                SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> singleFieldBuilderV3;
                if (this.optionsCase_ == 1 && (singleFieldBuilderV3 = this.linearBucketsBuilder_) != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (this.optionsCase_ == 1) {
                    return (Linear) this.options_;
                }
                return Linear.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Linear, Linear.Builder, LinearOrBuilder> getLinearBucketsFieldBuilder() {
                if (this.linearBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 1) {
                        this.options_ = Linear.getDefaultInstance();
                    }
                    this.linearBucketsBuilder_ = new SingleFieldBuilderV3<>((Linear) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 1;
                onChanged();
                return this.linearBucketsBuilder_;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasExponentialBuckets() {
                return this.optionsCase_ == 2;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Exponential getExponentialBuckets() {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 2) {
                        return (Exponential) this.options_;
                    }
                    return Exponential.getDefaultInstance();
                }
                if (this.optionsCase_ == 2) {
                    return singleFieldBuilderV3.getMessage();
                }
                return Exponential.getDefaultInstance();
            }

            public Builder setExponentialBuckets(Exponential exponential) {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(exponential);
                } else {
                    if (exponential == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = exponential;
                    onChanged();
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder setExponentialBuckets(Exponential.Builder builder) {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder mergeExponentialBuckets(Exponential exponential) {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 2 && this.options_ != Exponential.getDefaultInstance()) {
                        this.options_ = Exponential.newBuilder((Exponential) this.options_).mergeFrom(exponential).buildPartial();
                    } else {
                        this.options_ = exponential;
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 2) {
                        singleFieldBuilderV3.mergeFrom(exponential);
                    }
                    this.exponentialBucketsBuilder_.setMessage(exponential);
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder clearExponentialBuckets() {
                if (this.exponentialBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 2) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                        onChanged();
                    }
                } else {
                    if (this.optionsCase_ == 2) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.exponentialBucketsBuilder_.clear();
                }
                return this;
            }

            public Exponential.Builder getExponentialBucketsBuilder() {
                return getExponentialBucketsFieldBuilder().getBuilder();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public ExponentialOrBuilder getExponentialBucketsOrBuilder() {
                SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> singleFieldBuilderV3;
                if (this.optionsCase_ == 2 && (singleFieldBuilderV3 = this.exponentialBucketsBuilder_) != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (this.optionsCase_ == 2) {
                    return (Exponential) this.options_;
                }
                return Exponential.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Exponential, Exponential.Builder, ExponentialOrBuilder> getExponentialBucketsFieldBuilder() {
                if (this.exponentialBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 2) {
                        this.options_ = Exponential.getDefaultInstance();
                    }
                    this.exponentialBucketsBuilder_ = new SingleFieldBuilderV3<>((Exponential) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 2;
                onChanged();
                return this.exponentialBucketsBuilder_;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasExplicitBuckets() {
                return this.optionsCase_ == 3;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Explicit getExplicitBuckets() {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 3) {
                        return (Explicit) this.options_;
                    }
                    return Explicit.getDefaultInstance();
                }
                if (this.optionsCase_ == 3) {
                    return singleFieldBuilderV3.getMessage();
                }
                return Explicit.getDefaultInstance();
            }

            public Builder setExplicitBuckets(Explicit explicit) {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(explicit);
                } else {
                    if (explicit == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = explicit;
                    onChanged();
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder setExplicitBuckets(Explicit.Builder builder) {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder mergeExplicitBuckets(Explicit explicit) {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 3 && this.options_ != Explicit.getDefaultInstance()) {
                        this.options_ = Explicit.newBuilder((Explicit) this.options_).mergeFrom(explicit).buildPartial();
                    } else {
                        this.options_ = explicit;
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 3) {
                        singleFieldBuilderV3.mergeFrom(explicit);
                    }
                    this.explicitBucketsBuilder_.setMessage(explicit);
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder clearExplicitBuckets() {
                if (this.explicitBucketsBuilder_ == null) {
                    if (this.optionsCase_ == 3) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                        onChanged();
                    }
                } else {
                    if (this.optionsCase_ == 3) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.explicitBucketsBuilder_.clear();
                }
                return this;
            }

            public Explicit.Builder getExplicitBucketsBuilder() {
                return getExplicitBucketsFieldBuilder().getBuilder();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public ExplicitOrBuilder getExplicitBucketsOrBuilder() {
                SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> singleFieldBuilderV3;
                if (this.optionsCase_ == 3 && (singleFieldBuilderV3 = this.explicitBucketsBuilder_) != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (this.optionsCase_ == 3) {
                    return (Explicit) this.options_;
                }
                return Explicit.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Explicit, Explicit.Builder, ExplicitOrBuilder> getExplicitBucketsFieldBuilder() {
                if (this.explicitBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 3) {
                        this.options_ = Explicit.getDefaultInstance();
                    }
                    this.explicitBucketsBuilder_ = new SingleFieldBuilderV3<>((Explicit) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 3;
                onChanged();
                return this.explicitBucketsBuilder_;
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

        public static BucketOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BucketOptions> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<BucketOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BucketOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.google.api.Distribution$2 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19712 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase = new int[BucketOptions.OptionsCase.values().length];

        static {
            try {
                $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[BucketOptions.OptionsCase.LINEAR_BUCKETS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[BucketOptions.OptionsCase.EXPONENTIAL_BUCKETS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[BucketOptions.OptionsCase.EXPLICIT_BUCKETS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$api$Distribution$BucketOptions$OptionsCase[BucketOptions.OptionsCase.OPTIONS_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Exemplar extends GeneratedMessageV3 implements ExemplarOrBuilder {
        public static final int ATTACHMENTS_FIELD_NUMBER = 3;
        private static final Exemplar DEFAULT_INSTANCE = new Exemplar();
        private static final Parser<Exemplar> PARSER = new AbstractParser<Exemplar>() { // from class: com.google.api.Distribution.Exemplar.1
            @Override // com.google.protobuf.Parser
            public Exemplar parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Exemplar(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TIMESTAMP_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private List<Any> attachments_;
        private byte memoizedIsInitialized;
        private Timestamp timestamp_;
        private double value_;

        private Exemplar(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Exemplar() {
            this.memoizedIsInitialized = (byte) -1;
            this.attachments_ = Collections.emptyList();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Exemplar();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Exemplar(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 9) {
                                    this.value_ = codedInputStream.readDouble();
                                } else if (readTag == 18) {
                                    Timestamp.Builder builder = this.timestamp_ != null ? this.timestamp_.toBuilder() : null;
                                    this.timestamp_ = (Timestamp) codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.timestamp_);
                                        this.timestamp_ = builder.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    if (!(z2 & true)) {
                                        this.attachments_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.attachments_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
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
                        this.attachments_ = Collections.unmodifiableList(this.attachments_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_Exemplar_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.f1322xc3614745.ensureFieldAccessorsInitialized(Exemplar.class, Builder.class);
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public double getValue() {
            return this.value_;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public boolean hasTimestamp() {
            return this.timestamp_ != null;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public Timestamp getTimestamp() {
            Timestamp timestamp = this.timestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public TimestampOrBuilder getTimestampOrBuilder() {
            return getTimestamp();
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public List<Any> getAttachmentsList() {
            return this.attachments_;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public List<? extends AnyOrBuilder> getAttachmentsOrBuilderList() {
            return this.attachments_;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public int getAttachmentsCount() {
            return this.attachments_.size();
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public Any getAttachments(int i) {
            return this.attachments_.get(i);
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public AnyOrBuilder getAttachmentsOrBuilder(int i) {
            return this.attachments_.get(i);
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
            double d = this.value_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(1, d);
            }
            if (this.timestamp_ != null) {
                codedOutputStream.writeMessage(2, getTimestamp());
            }
            for (int i = 0; i < this.attachments_.size(); i++) {
                codedOutputStream.writeMessage(3, this.attachments_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            double d = this.value_;
            int computeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) + 0 : 0;
            if (this.timestamp_ != null) {
                computeDoubleSize += CodedOutputStream.computeMessageSize(2, getTimestamp());
            }
            for (int i2 = 0; i2 < this.attachments_.size(); i2++) {
                computeDoubleSize += CodedOutputStream.computeMessageSize(3, this.attachments_.get(i2));
            }
            int serializedSize = computeDoubleSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Exemplar)) {
                return super.equals(obj);
            }
            Exemplar exemplar = (Exemplar) obj;
            if (Double.doubleToLongBits(getValue()) == Double.doubleToLongBits(exemplar.getValue()) && hasTimestamp() == exemplar.hasTimestamp()) {
                return (!hasTimestamp() || getTimestamp().equals(exemplar.getTimestamp())) && getAttachmentsList().equals(exemplar.getAttachmentsList()) && this.unknownFields.equals(exemplar.unknownFields);
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getValue()));
            if (hasTimestamp()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getTimestamp().hashCode();
            }
            if (getAttachmentsCount() > 0) {
                hashCode = (((hashCode * 37) + 3) * 53) + getAttachmentsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static Exemplar parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Exemplar parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Exemplar parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Exemplar parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Exemplar parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Exemplar parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Exemplar parseFrom(InputStream inputStream) throws IOException {
            return (Exemplar) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Exemplar parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Exemplar) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Exemplar parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Exemplar) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Exemplar parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Exemplar) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Exemplar parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Exemplar) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Exemplar parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Exemplar) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Exemplar exemplar) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(exemplar);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExemplarOrBuilder {
            private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> attachmentsBuilder_;
            private List<Any> attachments_;
            private int bitField0_;
            private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> timestampBuilder_;
            private Timestamp timestamp_;
            private double value_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_Exemplar_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.f1322xc3614745.ensureFieldAccessorsInitialized(Exemplar.class, Builder.class);
            }

            private Builder() {
                this.attachments_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.attachments_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (Exemplar.alwaysUseFieldBuilders) {
                    getAttachmentsFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.value_ = 0.0d;
                if (this.timestampBuilder_ == null) {
                    this.timestamp_ = null;
                } else {
                    this.timestamp_ = null;
                    this.timestampBuilder_ = null;
                }
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.attachments_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_Exemplar_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Exemplar getDefaultInstanceForType() {
                return Exemplar.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Exemplar build() {
                Exemplar buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Exemplar buildPartial() {
                Exemplar exemplar = new Exemplar(this);
                int i = this.bitField0_;
                exemplar.value_ = this.value_;
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 == null) {
                    exemplar.timestamp_ = this.timestamp_;
                } else {
                    exemplar.timestamp_ = singleFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    exemplar.attachments_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 1) != 0) {
                        this.attachments_ = Collections.unmodifiableList(this.attachments_);
                        this.bitField0_ &= -2;
                    }
                    exemplar.attachments_ = this.attachments_;
                }
                onBuilt();
                return exemplar;
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
                if (message instanceof Exemplar) {
                    return mergeFrom((Exemplar) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Exemplar exemplar) {
                if (exemplar == Exemplar.getDefaultInstance()) {
                    return this;
                }
                if (exemplar.getValue() != 0.0d) {
                    setValue(exemplar.getValue());
                }
                if (exemplar.hasTimestamp()) {
                    mergeTimestamp(exemplar.getTimestamp());
                }
                if (this.attachmentsBuilder_ == null) {
                    if (!exemplar.attachments_.isEmpty()) {
                        if (this.attachments_.isEmpty()) {
                            this.attachments_ = exemplar.attachments_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureAttachmentsIsMutable();
                            this.attachments_.addAll(exemplar.attachments_);
                        }
                        onChanged();
                    }
                } else if (!exemplar.attachments_.isEmpty()) {
                    if (!this.attachmentsBuilder_.isEmpty()) {
                        this.attachmentsBuilder_.addAllMessages(exemplar.attachments_);
                    } else {
                        this.attachmentsBuilder_.dispose();
                        this.attachmentsBuilder_ = null;
                        this.attachments_ = exemplar.attachments_;
                        this.bitField0_ &= -2;
                        this.attachmentsBuilder_ = Exemplar.alwaysUseFieldBuilders ? getAttachmentsFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(exemplar.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Exemplar exemplar = null;
                try {
                    try {
                        Exemplar exemplar2 = (Exemplar) Exemplar.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (exemplar2 != null) {
                            mergeFrom(exemplar2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Exemplar exemplar3 = (Exemplar) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            exemplar = exemplar3;
                            if (exemplar != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (exemplar != null) {
                        mergeFrom(exemplar);
                    }
                    throw th;
                }
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public double getValue() {
                return this.value_;
            }

            public Builder setValue(double d) {
                this.value_ = d;
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.value_ = 0.0d;
                onChanged();
                return this;
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public boolean hasTimestamp() {
                return (this.timestampBuilder_ == null && this.timestamp_ == null) ? false : true;
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public Timestamp getTimestamp() {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Timestamp timestamp = this.timestamp_;
                    return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setTimestamp(Timestamp timestamp) {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(timestamp);
                } else {
                    if (timestamp == null) {
                        throw new NullPointerException();
                    }
                    this.timestamp_ = timestamp;
                    onChanged();
                }
                return this;
            }

            public Builder setTimestamp(Timestamp.Builder builder) {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.timestamp_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTimestamp(Timestamp timestamp) {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Timestamp timestamp2 = this.timestamp_;
                    if (timestamp2 != null) {
                        this.timestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                    } else {
                        this.timestamp_ = timestamp;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(timestamp);
                }
                return this;
            }

            public Builder clearTimestamp() {
                if (this.timestampBuilder_ == null) {
                    this.timestamp_ = null;
                    onChanged();
                } else {
                    this.timestamp_ = null;
                    this.timestampBuilder_ = null;
                }
                return this;
            }

            public Timestamp.Builder getTimestampBuilder() {
                onChanged();
                return getTimestampFieldBuilder().getBuilder();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public TimestampOrBuilder getTimestampOrBuilder() {
                SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Timestamp timestamp = this.timestamp_;
                return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
            }

            private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getTimestampFieldBuilder() {
                if (this.timestampBuilder_ == null) {
                    this.timestampBuilder_ = new SingleFieldBuilderV3<>(getTimestamp(), getParentForChildren(), isClean());
                    this.timestamp_ = null;
                }
                return this.timestampBuilder_;
            }

            private void ensureAttachmentsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.attachments_ = new ArrayList(this.attachments_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public List<Any> getAttachmentsList() {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.attachments_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public int getAttachmentsCount() {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.attachments_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public Any getAttachments(int i) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.attachments_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setAttachments(int i, Any any) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, any);
                } else {
                    if (any == null) {
                        throw new NullPointerException();
                    }
                    ensureAttachmentsIsMutable();
                    this.attachments_.set(i, any);
                    onChanged();
                }
                return this;
            }

            public Builder setAttachments(int i, Any.Builder builder) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    this.attachments_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAttachments(Any any) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(any);
                } else {
                    if (any == null) {
                        throw new NullPointerException();
                    }
                    ensureAttachmentsIsMutable();
                    this.attachments_.add(any);
                    onChanged();
                }
                return this;
            }

            public Builder addAttachments(int i, Any any) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, any);
                } else {
                    if (any == null) {
                        throw new NullPointerException();
                    }
                    ensureAttachmentsIsMutable();
                    this.attachments_.add(i, any);
                    onChanged();
                }
                return this;
            }

            public Builder addAttachments(Any.Builder builder) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    this.attachments_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addAttachments(int i, Any.Builder builder) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    this.attachments_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllAttachments(Iterable<? extends Any> iterable) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.attachments_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearAttachments() {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.attachments_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeAttachments(int i) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureAttachmentsIsMutable();
                    this.attachments_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Any.Builder getAttachmentsBuilder(int i) {
                return getAttachmentsFieldBuilder().getBuilder(i);
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public AnyOrBuilder getAttachmentsOrBuilder(int i) {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.attachments_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public List<? extends AnyOrBuilder> getAttachmentsOrBuilderList() {
                RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.attachmentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.attachments_);
            }

            public Any.Builder addAttachmentsBuilder() {
                return getAttachmentsFieldBuilder().addBuilder(Any.getDefaultInstance());
            }

            public Any.Builder addAttachmentsBuilder(int i) {
                return getAttachmentsFieldBuilder().addBuilder(i, Any.getDefaultInstance());
            }

            public List<Any.Builder> getAttachmentsBuilderList() {
                return getAttachmentsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getAttachmentsFieldBuilder() {
                if (this.attachmentsBuilder_ == null) {
                    this.attachmentsBuilder_ = new RepeatedFieldBuilderV3<>(this.attachments_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.attachments_ = null;
                }
                return this.attachmentsBuilder_;
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

        public static Exemplar getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Exemplar> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Exemplar> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Exemplar getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.api.DistributionOrBuilder
    public long getCount() {
        return this.count_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public double getMean() {
        return this.mean_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public double getSumOfSquaredDeviation() {
        return this.sumOfSquaredDeviation_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public boolean hasRange() {
        return this.range_ != null;
    }

    @Override // com.google.api.DistributionOrBuilder
    public Range getRange() {
        Range range = this.range_;
        return range == null ? Range.getDefaultInstance() : range;
    }

    @Override // com.google.api.DistributionOrBuilder
    public RangeOrBuilder getRangeOrBuilder() {
        return getRange();
    }

    @Override // com.google.api.DistributionOrBuilder
    public boolean hasBucketOptions() {
        return this.bucketOptions_ != null;
    }

    @Override // com.google.api.DistributionOrBuilder
    public BucketOptions getBucketOptions() {
        BucketOptions bucketOptions = this.bucketOptions_;
        return bucketOptions == null ? BucketOptions.getDefaultInstance() : bucketOptions;
    }

    @Override // com.google.api.DistributionOrBuilder
    public BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
        return getBucketOptions();
    }

    @Override // com.google.api.DistributionOrBuilder
    public List<Long> getBucketCountsList() {
        return this.bucketCounts_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public int getBucketCountsCount() {
        return this.bucketCounts_.size();
    }

    @Override // com.google.api.DistributionOrBuilder
    public long getBucketCounts(int i) {
        return this.bucketCounts_.getLong(i);
    }

    @Override // com.google.api.DistributionOrBuilder
    public List<Exemplar> getExemplarsList() {
        return this.exemplars_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public List<? extends ExemplarOrBuilder> getExemplarsOrBuilderList() {
        return this.exemplars_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public int getExemplarsCount() {
        return this.exemplars_.size();
    }

    @Override // com.google.api.DistributionOrBuilder
    public Exemplar getExemplars(int i) {
        return this.exemplars_.get(i);
    }

    @Override // com.google.api.DistributionOrBuilder
    public ExemplarOrBuilder getExemplarsOrBuilder(int i) {
        return this.exemplars_.get(i);
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
        getSerializedSize();
        long j = this.count_;
        if (j != 0) {
            codedOutputStream.writeInt64(1, j);
        }
        double d = this.mean_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(2, d);
        }
        double d2 = this.sumOfSquaredDeviation_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(3, d2);
        }
        if (this.range_ != null) {
            codedOutputStream.writeMessage(4, getRange());
        }
        if (this.bucketOptions_ != null) {
            codedOutputStream.writeMessage(6, getBucketOptions());
        }
        if (getBucketCountsList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(58);
            codedOutputStream.writeUInt32NoTag(this.bucketCountsMemoizedSerializedSize);
        }
        for (int i = 0; i < this.bucketCounts_.size(); i++) {
            codedOutputStream.writeInt64NoTag(this.bucketCounts_.getLong(i));
        }
        for (int i2 = 0; i2 < this.exemplars_.size(); i2++) {
            codedOutputStream.writeMessage(10, this.exemplars_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        long j = this.count_;
        int computeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) + 0 : 0;
        double d = this.mean_;
        if (d != 0.0d) {
            computeInt64Size += CodedOutputStream.computeDoubleSize(2, d);
        }
        double d2 = this.sumOfSquaredDeviation_;
        if (d2 != 0.0d) {
            computeInt64Size += CodedOutputStream.computeDoubleSize(3, d2);
        }
        if (this.range_ != null) {
            computeInt64Size += CodedOutputStream.computeMessageSize(4, getRange());
        }
        if (this.bucketOptions_ != null) {
            computeInt64Size += CodedOutputStream.computeMessageSize(6, getBucketOptions());
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.bucketCounts_.size(); i3++) {
            i2 += CodedOutputStream.computeInt64SizeNoTag(this.bucketCounts_.getLong(i3));
        }
        int i4 = computeInt64Size + i2;
        if (!getBucketCountsList().isEmpty()) {
            i4 = i4 + 1 + CodedOutputStream.computeInt32SizeNoTag(i2);
        }
        this.bucketCountsMemoizedSerializedSize = i2;
        for (int i5 = 0; i5 < this.exemplars_.size(); i5++) {
            i4 += CodedOutputStream.computeMessageSize(10, this.exemplars_.get(i5));
        }
        int serializedSize = i4 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Distribution)) {
            return super.equals(obj);
        }
        Distribution distribution = (Distribution) obj;
        if (getCount() != distribution.getCount() || Double.doubleToLongBits(getMean()) != Double.doubleToLongBits(distribution.getMean()) || Double.doubleToLongBits(getSumOfSquaredDeviation()) != Double.doubleToLongBits(distribution.getSumOfSquaredDeviation()) || hasRange() != distribution.hasRange()) {
            return false;
        }
        if ((!hasRange() || getRange().equals(distribution.getRange())) && hasBucketOptions() == distribution.hasBucketOptions()) {
            return (!hasBucketOptions() || getBucketOptions().equals(distribution.getBucketOptions())) && getBucketCountsList().equals(distribution.getBucketCountsList()) && getExemplarsList().equals(distribution.getExemplarsList()) && this.unknownFields.equals(distribution.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getCount())) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getMean()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getSumOfSquaredDeviation()));
        if (hasRange()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getRange().hashCode();
        }
        if (hasBucketOptions()) {
            hashCode = (((hashCode * 37) + 6) * 53) + getBucketOptions().hashCode();
        }
        if (getBucketCountsCount() > 0) {
            hashCode = (((hashCode * 37) + 7) * 53) + getBucketCountsList().hashCode();
        }
        if (getExemplarsCount() > 0) {
            hashCode = (((hashCode * 37) + 10) * 53) + getExemplarsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Distribution parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Distribution parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Distribution parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Distribution parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Distribution parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Distribution parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Distribution parseFrom(InputStream inputStream) throws IOException {
        return (Distribution) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Distribution parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Distribution) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Distribution parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Distribution) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Distribution parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Distribution) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Distribution parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Distribution) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Distribution parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Distribution) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Distribution distribution) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(distribution);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DistributionOrBuilder {
        private int bitField0_;
        private Internal.LongList bucketCounts_;
        private SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> bucketOptionsBuilder_;
        private BucketOptions bucketOptions_;
        private long count_;
        private RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> exemplarsBuilder_;
        private List<Exemplar> exemplars_;
        private double mean_;
        private SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> rangeBuilder_;
        private Range range_;
        private double sumOfSquaredDeviation_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_fieldAccessorTable.ensureFieldAccessorsInitialized(Distribution.class, Builder.class);
        }

        private Builder() {
            this.bucketCounts_ = Distribution.access$7600();
            this.exemplars_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.bucketCounts_ = Distribution.access$7600();
            this.exemplars_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Distribution.alwaysUseFieldBuilders) {
                getExemplarsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.count_ = 0L;
            this.mean_ = 0.0d;
            this.sumOfSquaredDeviation_ = 0.0d;
            if (this.rangeBuilder_ == null) {
                this.range_ = null;
            } else {
                this.range_ = null;
                this.rangeBuilder_ = null;
            }
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            this.bucketCounts_ = Distribution.access$6400();
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.exemplars_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DistributionProto.internal_static_google_api_Distribution_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Distribution getDefaultInstanceForType() {
            return Distribution.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Distribution build() {
            Distribution buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Distribution buildPartial() {
            Distribution distribution = new Distribution(this);
            int i = this.bitField0_;
            distribution.count_ = this.count_;
            distribution.mean_ = this.mean_;
            distribution.sumOfSquaredDeviation_ = this.sumOfSquaredDeviation_;
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                distribution.range_ = this.range_;
            } else {
                distribution.range_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV32 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV32 == null) {
                distribution.bucketOptions_ = this.bucketOptions_;
            } else {
                distribution.bucketOptions_ = singleFieldBuilderV32.build();
            }
            if ((this.bitField0_ & 1) != 0) {
                this.bucketCounts_.makeImmutable();
                this.bitField0_ &= -2;
            }
            distribution.bucketCounts_ = this.bucketCounts_;
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                distribution.exemplars_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.exemplars_ = Collections.unmodifiableList(this.exemplars_);
                    this.bitField0_ &= -3;
                }
                distribution.exemplars_ = this.exemplars_;
            }
            onBuilt();
            return distribution;
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
            if (message instanceof Distribution) {
                return mergeFrom((Distribution) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Distribution distribution) {
            if (distribution == Distribution.getDefaultInstance()) {
                return this;
            }
            if (distribution.getCount() != 0) {
                setCount(distribution.getCount());
            }
            if (distribution.getMean() != 0.0d) {
                setMean(distribution.getMean());
            }
            if (distribution.getSumOfSquaredDeviation() != 0.0d) {
                setSumOfSquaredDeviation(distribution.getSumOfSquaredDeviation());
            }
            if (distribution.hasRange()) {
                mergeRange(distribution.getRange());
            }
            if (distribution.hasBucketOptions()) {
                mergeBucketOptions(distribution.getBucketOptions());
            }
            if (!distribution.bucketCounts_.isEmpty()) {
                if (this.bucketCounts_.isEmpty()) {
                    this.bucketCounts_ = distribution.bucketCounts_;
                    this.bitField0_ &= -2;
                } else {
                    ensureBucketCountsIsMutable();
                    this.bucketCounts_.addAll(distribution.bucketCounts_);
                }
                onChanged();
            }
            if (this.exemplarsBuilder_ == null) {
                if (!distribution.exemplars_.isEmpty()) {
                    if (this.exemplars_.isEmpty()) {
                        this.exemplars_ = distribution.exemplars_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureExemplarsIsMutable();
                        this.exemplars_.addAll(distribution.exemplars_);
                    }
                    onChanged();
                }
            } else if (!distribution.exemplars_.isEmpty()) {
                if (!this.exemplarsBuilder_.isEmpty()) {
                    this.exemplarsBuilder_.addAllMessages(distribution.exemplars_);
                } else {
                    this.exemplarsBuilder_.dispose();
                    this.exemplarsBuilder_ = null;
                    this.exemplars_ = distribution.exemplars_;
                    this.bitField0_ &= -3;
                    this.exemplarsBuilder_ = Distribution.alwaysUseFieldBuilders ? getExemplarsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(distribution.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Distribution distribution = null;
            try {
                try {
                    Distribution distribution2 = (Distribution) Distribution.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (distribution2 != null) {
                        mergeFrom(distribution2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Distribution distribution3 = (Distribution) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        distribution = distribution3;
                        if (distribution != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (distribution != null) {
                    mergeFrom(distribution);
                }
                throw th;
            }
        }

        @Override // com.google.api.DistributionOrBuilder
        public long getCount() {
            return this.count_;
        }

        public Builder setCount(long j) {
            this.count_ = j;
            onChanged();
            return this;
        }

        public Builder clearCount() {
            this.count_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.api.DistributionOrBuilder
        public double getMean() {
            return this.mean_;
        }

        public Builder setMean(double d) {
            this.mean_ = d;
            onChanged();
            return this;
        }

        public Builder clearMean() {
            this.mean_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.DistributionOrBuilder
        public double getSumOfSquaredDeviation() {
            return this.sumOfSquaredDeviation_;
        }

        public Builder setSumOfSquaredDeviation(double d) {
            this.sumOfSquaredDeviation_ = d;
            onChanged();
            return this;
        }

        public Builder clearSumOfSquaredDeviation() {
            this.sumOfSquaredDeviation_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.DistributionOrBuilder
        public boolean hasRange() {
            return (this.rangeBuilder_ == null && this.range_ == null) ? false : true;
        }

        @Override // com.google.api.DistributionOrBuilder
        public Range getRange() {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Range range = this.range_;
                return range == null ? Range.getDefaultInstance() : range;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setRange(Range range) {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(range);
            } else {
                if (range == null) {
                    throw new NullPointerException();
                }
                this.range_ = range;
                onChanged();
            }
            return this;
        }

        public Builder setRange(Range.Builder builder) {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.range_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRange(Range range) {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Range range2 = this.range_;
                if (range2 != null) {
                    this.range_ = Range.newBuilder(range2).mergeFrom(range).buildPartial();
                } else {
                    this.range_ = range;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(range);
            }
            return this;
        }

        public Builder clearRange() {
            if (this.rangeBuilder_ == null) {
                this.range_ = null;
                onChanged();
            } else {
                this.range_ = null;
                this.rangeBuilder_ = null;
            }
            return this;
        }

        public Range.Builder getRangeBuilder() {
            onChanged();
            return getRangeFieldBuilder().getBuilder();
        }

        @Override // com.google.api.DistributionOrBuilder
        public RangeOrBuilder getRangeOrBuilder() {
            SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Range range = this.range_;
            return range == null ? Range.getDefaultInstance() : range;
        }

        private SingleFieldBuilderV3<Range, Range.Builder, RangeOrBuilder> getRangeFieldBuilder() {
            if (this.rangeBuilder_ == null) {
                this.rangeBuilder_ = new SingleFieldBuilderV3<>(getRange(), getParentForChildren(), isClean());
                this.range_ = null;
            }
            return this.rangeBuilder_;
        }

        @Override // com.google.api.DistributionOrBuilder
        public boolean hasBucketOptions() {
            return (this.bucketOptionsBuilder_ == null && this.bucketOptions_ == null) ? false : true;
        }

        @Override // com.google.api.DistributionOrBuilder
        public BucketOptions getBucketOptions() {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                BucketOptions bucketOptions = this.bucketOptions_;
                return bucketOptions == null ? BucketOptions.getDefaultInstance() : bucketOptions;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setBucketOptions(BucketOptions bucketOptions) {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(bucketOptions);
            } else {
                if (bucketOptions == null) {
                    throw new NullPointerException();
                }
                this.bucketOptions_ = bucketOptions;
                onChanged();
            }
            return this;
        }

        public Builder setBucketOptions(BucketOptions.Builder builder) {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.bucketOptions_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeBucketOptions(BucketOptions bucketOptions) {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                BucketOptions bucketOptions2 = this.bucketOptions_;
                if (bucketOptions2 != null) {
                    this.bucketOptions_ = BucketOptions.newBuilder(bucketOptions2).mergeFrom(bucketOptions).buildPartial();
                } else {
                    this.bucketOptions_ = bucketOptions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(bucketOptions);
            }
            return this;
        }

        public Builder clearBucketOptions() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
                onChanged();
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            return this;
        }

        public BucketOptions.Builder getBucketOptionsBuilder() {
            onChanged();
            return getBucketOptionsFieldBuilder().getBuilder();
        }

        @Override // com.google.api.DistributionOrBuilder
        public BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
            SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            BucketOptions bucketOptions = this.bucketOptions_;
            return bucketOptions == null ? BucketOptions.getDefaultInstance() : bucketOptions;
        }

        private SingleFieldBuilderV3<BucketOptions, BucketOptions.Builder, BucketOptionsOrBuilder> getBucketOptionsFieldBuilder() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptionsBuilder_ = new SingleFieldBuilderV3<>(getBucketOptions(), getParentForChildren(), isClean());
                this.bucketOptions_ = null;
            }
            return this.bucketOptionsBuilder_;
        }

        private void ensureBucketCountsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.bucketCounts_ = Distribution.mutableCopy(this.bucketCounts_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.DistributionOrBuilder
        public List<Long> getBucketCountsList() {
            return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.bucketCounts_) : this.bucketCounts_;
        }

        @Override // com.google.api.DistributionOrBuilder
        public int getBucketCountsCount() {
            return this.bucketCounts_.size();
        }

        @Override // com.google.api.DistributionOrBuilder
        public long getBucketCounts(int i) {
            return this.bucketCounts_.getLong(i);
        }

        public Builder setBucketCounts(int i, long j) {
            ensureBucketCountsIsMutable();
            this.bucketCounts_.setLong(i, j);
            onChanged();
            return this;
        }

        public Builder addBucketCounts(long j) {
            ensureBucketCountsIsMutable();
            this.bucketCounts_.addLong(j);
            onChanged();
            return this;
        }

        public Builder addAllBucketCounts(Iterable<? extends Long> iterable) {
            ensureBucketCountsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.bucketCounts_);
            onChanged();
            return this;
        }

        public Builder clearBucketCounts() {
            this.bucketCounts_ = Distribution.access$7800();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        private void ensureExemplarsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.exemplars_ = new ArrayList(this.exemplars_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.DistributionOrBuilder
        public List<Exemplar> getExemplarsList() {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.exemplars_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.DistributionOrBuilder
        public int getExemplarsCount() {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.exemplars_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.DistributionOrBuilder
        public Exemplar getExemplars(int i) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.exemplars_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setExemplars(int i, Exemplar exemplar) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, exemplar);
            } else {
                if (exemplar == null) {
                    throw new NullPointerException();
                }
                ensureExemplarsIsMutable();
                this.exemplars_.set(i, exemplar);
                onChanged();
            }
            return this;
        }

        public Builder setExemplars(int i, Exemplar.Builder builder) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                this.exemplars_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addExemplars(Exemplar exemplar) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(exemplar);
            } else {
                if (exemplar == null) {
                    throw new NullPointerException();
                }
                ensureExemplarsIsMutable();
                this.exemplars_.add(exemplar);
                onChanged();
            }
            return this;
        }

        public Builder addExemplars(int i, Exemplar exemplar) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, exemplar);
            } else {
                if (exemplar == null) {
                    throw new NullPointerException();
                }
                ensureExemplarsIsMutable();
                this.exemplars_.add(i, exemplar);
                onChanged();
            }
            return this;
        }

        public Builder addExemplars(Exemplar.Builder builder) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                this.exemplars_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addExemplars(int i, Exemplar.Builder builder) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                this.exemplars_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllExemplars(Iterable<? extends Exemplar> iterable) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.exemplars_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearExemplars() {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.exemplars_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeExemplars(int i) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExemplarsIsMutable();
                this.exemplars_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Exemplar.Builder getExemplarsBuilder(int i) {
            return getExemplarsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.DistributionOrBuilder
        public ExemplarOrBuilder getExemplarsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.exemplars_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.DistributionOrBuilder
        public List<? extends ExemplarOrBuilder> getExemplarsOrBuilderList() {
            RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> repeatedFieldBuilderV3 = this.exemplarsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.exemplars_);
        }

        public Exemplar.Builder addExemplarsBuilder() {
            return getExemplarsFieldBuilder().addBuilder(Exemplar.getDefaultInstance());
        }

        public Exemplar.Builder addExemplarsBuilder(int i) {
            return getExemplarsFieldBuilder().addBuilder(i, Exemplar.getDefaultInstance());
        }

        public List<Exemplar.Builder> getExemplarsBuilderList() {
            return getExemplarsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Exemplar, Exemplar.Builder, ExemplarOrBuilder> getExemplarsFieldBuilder() {
            if (this.exemplarsBuilder_ == null) {
                this.exemplarsBuilder_ = new RepeatedFieldBuilderV3<>(this.exemplars_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.exemplars_ = null;
            }
            return this.exemplarsBuilder_;
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

    public static Distribution getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Distribution> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Distribution> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Distribution getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
