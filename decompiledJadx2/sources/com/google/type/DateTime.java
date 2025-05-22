package com.google.type;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.type.TimeZone;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class DateTime extends GeneratedMessageV3 implements DateTimeOrBuilder {
    public static final int DAY_FIELD_NUMBER = 3;
    public static final int HOURS_FIELD_NUMBER = 4;
    public static final int MINUTES_FIELD_NUMBER = 5;
    public static final int MONTH_FIELD_NUMBER = 2;
    public static final int NANOS_FIELD_NUMBER = 7;
    public static final int SECONDS_FIELD_NUMBER = 6;
    public static final int TIME_ZONE_FIELD_NUMBER = 9;
    public static final int UTC_OFFSET_FIELD_NUMBER = 8;
    public static final int YEAR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private int day_;
    private int hours_;
    private byte memoizedIsInitialized;
    private int minutes_;
    private int month_;
    private int nanos_;
    private int seconds_;
    private int timeOffsetCase_;
    private Object timeOffset_;
    private int year_;
    private static final DateTime DEFAULT_INSTANCE = new DateTime();
    private static final Parser<DateTime> PARSER = new AbstractParser<DateTime>() { // from class: com.google.type.DateTime.1
        @Override // com.google.protobuf.Parser
        public DateTime parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DateTime(codedInputStream, extensionRegistryLite);
        }
    };

    private DateTime(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.timeOffsetCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private DateTime() {
        this.timeOffsetCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new DateTime();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DateTime(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.year_ = codedInputStream.readInt32();
                        } else if (readTag == 16) {
                            this.month_ = codedInputStream.readInt32();
                        } else if (readTag == 24) {
                            this.day_ = codedInputStream.readInt32();
                        } else if (readTag == 32) {
                            this.hours_ = codedInputStream.readInt32();
                        } else if (readTag == 40) {
                            this.minutes_ = codedInputStream.readInt32();
                        } else if (readTag == 48) {
                            this.seconds_ = codedInputStream.readInt32();
                        } else if (readTag != 56) {
                            if (readTag == 66) {
                                Duration.Builder builder = this.timeOffsetCase_ == 8 ? ((Duration) this.timeOffset_).toBuilder() : null;
                                this.timeOffset_ = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((Duration) this.timeOffset_);
                                    this.timeOffset_ = builder.buildPartial();
                                }
                                this.timeOffsetCase_ = 8;
                            } else if (readTag == 74) {
                                TimeZone.Builder builder2 = this.timeOffsetCase_ == 9 ? ((TimeZone) this.timeOffset_).toBuilder() : null;
                                this.timeOffset_ = codedInputStream.readMessage(TimeZone.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom((TimeZone) this.timeOffset_);
                                    this.timeOffset_ = builder2.buildPartial();
                                }
                                this.timeOffsetCase_ = 9;
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        } else {
                            this.nanos_ = codedInputStream.readInt32();
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
        return DateTimeProto.internal_static_google_type_DateTime_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DateTimeProto.internal_static_google_type_DateTime_fieldAccessorTable.ensureFieldAccessorsInitialized(DateTime.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public enum TimeOffsetCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        UTC_OFFSET(8),
        TIME_ZONE(9),
        TIMEOFFSET_NOT_SET(0);

        private final int value;

        TimeOffsetCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static TimeOffsetCase valueOf(int i) {
            return forNumber(i);
        }

        public static TimeOffsetCase forNumber(int i) {
            if (i == 0) {
                return TIMEOFFSET_NOT_SET;
            }
            if (i == 8) {
                return UTC_OFFSET;
            }
            if (i != 9) {
                return null;
            }
            return TIME_ZONE;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.type.DateTimeOrBuilder
    public TimeOffsetCase getTimeOffsetCase() {
        return TimeOffsetCase.forNumber(this.timeOffsetCase_);
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getYear() {
        return this.year_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getMonth() {
        return this.month_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getDay() {
        return this.day_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getHours() {
        return this.hours_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getMinutes() {
        return this.minutes_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getSeconds() {
        return this.seconds_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public int getNanos() {
        return this.nanos_;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public boolean hasUtcOffset() {
        return this.timeOffsetCase_ == 8;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public Duration getUtcOffset() {
        if (this.timeOffsetCase_ == 8) {
            return (Duration) this.timeOffset_;
        }
        return Duration.getDefaultInstance();
    }

    @Override // com.google.type.DateTimeOrBuilder
    public DurationOrBuilder getUtcOffsetOrBuilder() {
        if (this.timeOffsetCase_ == 8) {
            return (Duration) this.timeOffset_;
        }
        return Duration.getDefaultInstance();
    }

    @Override // com.google.type.DateTimeOrBuilder
    public boolean hasTimeZone() {
        return this.timeOffsetCase_ == 9;
    }

    @Override // com.google.type.DateTimeOrBuilder
    public TimeZone getTimeZone() {
        if (this.timeOffsetCase_ == 9) {
            return (TimeZone) this.timeOffset_;
        }
        return TimeZone.getDefaultInstance();
    }

    @Override // com.google.type.DateTimeOrBuilder
    public TimeZoneOrBuilder getTimeZoneOrBuilder() {
        if (this.timeOffsetCase_ == 9) {
            return (TimeZone) this.timeOffset_;
        }
        return TimeZone.getDefaultInstance();
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
        int i = this.year_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        int i2 = this.month_;
        if (i2 != 0) {
            codedOutputStream.writeInt32(2, i2);
        }
        int i3 = this.day_;
        if (i3 != 0) {
            codedOutputStream.writeInt32(3, i3);
        }
        int i4 = this.hours_;
        if (i4 != 0) {
            codedOutputStream.writeInt32(4, i4);
        }
        int i5 = this.minutes_;
        if (i5 != 0) {
            codedOutputStream.writeInt32(5, i5);
        }
        int i6 = this.seconds_;
        if (i6 != 0) {
            codedOutputStream.writeInt32(6, i6);
        }
        int i7 = this.nanos_;
        if (i7 != 0) {
            codedOutputStream.writeInt32(7, i7);
        }
        if (this.timeOffsetCase_ == 8) {
            codedOutputStream.writeMessage(8, (Duration) this.timeOffset_);
        }
        if (this.timeOffsetCase_ == 9) {
            codedOutputStream.writeMessage(9, (TimeZone) this.timeOffset_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.year_;
        int computeInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0;
        int i3 = this.month_;
        if (i3 != 0) {
            computeInt32Size += CodedOutputStream.computeInt32Size(2, i3);
        }
        int i4 = this.day_;
        if (i4 != 0) {
            computeInt32Size += CodedOutputStream.computeInt32Size(3, i4);
        }
        int i5 = this.hours_;
        if (i5 != 0) {
            computeInt32Size += CodedOutputStream.computeInt32Size(4, i5);
        }
        int i6 = this.minutes_;
        if (i6 != 0) {
            computeInt32Size += CodedOutputStream.computeInt32Size(5, i6);
        }
        int i7 = this.seconds_;
        if (i7 != 0) {
            computeInt32Size += CodedOutputStream.computeInt32Size(6, i7);
        }
        int i8 = this.nanos_;
        if (i8 != 0) {
            computeInt32Size += CodedOutputStream.computeInt32Size(7, i8);
        }
        if (this.timeOffsetCase_ == 8) {
            computeInt32Size += CodedOutputStream.computeMessageSize(8, (Duration) this.timeOffset_);
        }
        if (this.timeOffsetCase_ == 9) {
            computeInt32Size += CodedOutputStream.computeMessageSize(9, (TimeZone) this.timeOffset_);
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
        if (!(obj instanceof DateTime)) {
            return super.equals(obj);
        }
        DateTime dateTime = (DateTime) obj;
        if (getYear() != dateTime.getYear() || getMonth() != dateTime.getMonth() || getDay() != dateTime.getDay() || getHours() != dateTime.getHours() || getMinutes() != dateTime.getMinutes() || getSeconds() != dateTime.getSeconds() || getNanos() != dateTime.getNanos() || !getTimeOffsetCase().equals(dateTime.getTimeOffsetCase())) {
            return false;
        }
        int i = this.timeOffsetCase_;
        if (i == 8) {
            if (!getUtcOffset().equals(dateTime.getUtcOffset())) {
                return false;
            }
        } else if (i == 9 && !getTimeZone().equals(dateTime.getTimeZone())) {
            return false;
        }
        return this.unknownFields.equals(dateTime.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getYear()) * 37) + 2) * 53) + getMonth()) * 37) + 3) * 53) + getDay()) * 37) + 4) * 53) + getHours()) * 37) + 5) * 53) + getMinutes()) * 37) + 6) * 53) + getSeconds()) * 37) + 7) * 53) + getNanos();
        int i2 = this.timeOffsetCase_;
        if (i2 == 8) {
            i = ((hashCode2 * 37) + 8) * 53;
            hashCode = getUtcOffset().hashCode();
        } else {
            if (i2 == 9) {
                i = ((hashCode2 * 37) + 9) * 53;
                hashCode = getTimeZone().hashCode();
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

    public static DateTime parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static DateTime parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DateTime parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static DateTime parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DateTime parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static DateTime parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DateTime parseFrom(InputStream inputStream) throws IOException {
        return (DateTime) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DateTime parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DateTime) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DateTime parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DateTime) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DateTime parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DateTime) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DateTime parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DateTime) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DateTime parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DateTime) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DateTime dateTime) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(dateTime);
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
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DateTimeOrBuilder {
        private int day_;
        private int hours_;
        private int minutes_;
        private int month_;
        private int nanos_;
        private int seconds_;
        private int timeOffsetCase_;
        private Object timeOffset_;
        private SingleFieldBuilderV3<TimeZone, TimeZone.Builder, TimeZoneOrBuilder> timeZoneBuilder_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> utcOffsetBuilder_;
        private int year_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DateTimeProto.internal_static_google_type_DateTime_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DateTimeProto.internal_static_google_type_DateTime_fieldAccessorTable.ensureFieldAccessorsInitialized(DateTime.class, Builder.class);
        }

        private Builder() {
            this.timeOffsetCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.timeOffsetCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DateTime.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.year_ = 0;
            this.month_ = 0;
            this.day_ = 0;
            this.hours_ = 0;
            this.minutes_ = 0;
            this.seconds_ = 0;
            this.nanos_ = 0;
            this.timeOffsetCase_ = 0;
            this.timeOffset_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DateTimeProto.internal_static_google_type_DateTime_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DateTime getDefaultInstanceForType() {
            return DateTime.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DateTime build() {
            DateTime buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DateTime buildPartial() {
            DateTime dateTime = new DateTime(this);
            dateTime.year_ = this.year_;
            dateTime.month_ = this.month_;
            dateTime.day_ = this.day_;
            dateTime.hours_ = this.hours_;
            dateTime.minutes_ = this.minutes_;
            dateTime.seconds_ = this.seconds_;
            dateTime.nanos_ = this.nanos_;
            if (this.timeOffsetCase_ == 8) {
                SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.utcOffsetBuilder_;
                if (singleFieldBuilderV3 == null) {
                    dateTime.timeOffset_ = this.timeOffset_;
                } else {
                    dateTime.timeOffset_ = singleFieldBuilderV3.build();
                }
            }
            if (this.timeOffsetCase_ == 9) {
                SingleFieldBuilderV3<TimeZone, TimeZone.Builder, TimeZoneOrBuilder> singleFieldBuilderV32 = this.timeZoneBuilder_;
                if (singleFieldBuilderV32 == null) {
                    dateTime.timeOffset_ = this.timeOffset_;
                } else {
                    dateTime.timeOffset_ = singleFieldBuilderV32.build();
                }
            }
            dateTime.timeOffsetCase_ = this.timeOffsetCase_;
            onBuilt();
            return dateTime;
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
            if (message instanceof DateTime) {
                return mergeFrom((DateTime) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DateTime dateTime) {
            if (dateTime == DateTime.getDefaultInstance()) {
                return this;
            }
            if (dateTime.getYear() != 0) {
                setYear(dateTime.getYear());
            }
            if (dateTime.getMonth() != 0) {
                setMonth(dateTime.getMonth());
            }
            if (dateTime.getDay() != 0) {
                setDay(dateTime.getDay());
            }
            if (dateTime.getHours() != 0) {
                setHours(dateTime.getHours());
            }
            if (dateTime.getMinutes() != 0) {
                setMinutes(dateTime.getMinutes());
            }
            if (dateTime.getSeconds() != 0) {
                setSeconds(dateTime.getSeconds());
            }
            if (dateTime.getNanos() != 0) {
                setNanos(dateTime.getNanos());
            }
            int i = C34922.$SwitchMap$com$google$type$DateTime$TimeOffsetCase[dateTime.getTimeOffsetCase().ordinal()];
            if (i == 1) {
                mergeUtcOffset(dateTime.getUtcOffset());
            } else if (i == 2) {
                mergeTimeZone(dateTime.getTimeZone());
            }
            mergeUnknownFields(dateTime.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            DateTime dateTime = null;
            try {
                try {
                    DateTime dateTime2 = (DateTime) DateTime.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (dateTime2 != null) {
                        mergeFrom(dateTime2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    DateTime dateTime3 = (DateTime) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        dateTime = dateTime3;
                        if (dateTime != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (dateTime != null) {
                    mergeFrom(dateTime);
                }
                throw th;
            }
        }

        @Override // com.google.type.DateTimeOrBuilder
        public TimeOffsetCase getTimeOffsetCase() {
            return TimeOffsetCase.forNumber(this.timeOffsetCase_);
        }

        public Builder clearTimeOffset() {
            this.timeOffsetCase_ = 0;
            this.timeOffset_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getYear() {
            return this.year_;
        }

        public Builder setYear(int i) {
            this.year_ = i;
            onChanged();
            return this;
        }

        public Builder clearYear() {
            this.year_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getMonth() {
            return this.month_;
        }

        public Builder setMonth(int i) {
            this.month_ = i;
            onChanged();
            return this;
        }

        public Builder clearMonth() {
            this.month_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getDay() {
            return this.day_;
        }

        public Builder setDay(int i) {
            this.day_ = i;
            onChanged();
            return this;
        }

        public Builder clearDay() {
            this.day_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getHours() {
            return this.hours_;
        }

        public Builder setHours(int i) {
            this.hours_ = i;
            onChanged();
            return this;
        }

        public Builder clearHours() {
            this.hours_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getMinutes() {
            return this.minutes_;
        }

        public Builder setMinutes(int i) {
            this.minutes_ = i;
            onChanged();
            return this;
        }

        public Builder clearMinutes() {
            this.minutes_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getSeconds() {
            return this.seconds_;
        }

        public Builder setSeconds(int i) {
            this.seconds_ = i;
            onChanged();
            return this;
        }

        public Builder clearSeconds() {
            this.seconds_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public int getNanos() {
            return this.nanos_;
        }

        public Builder setNanos(int i) {
            this.nanos_ = i;
            onChanged();
            return this;
        }

        public Builder clearNanos() {
            this.nanos_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public boolean hasUtcOffset() {
            return this.timeOffsetCase_ == 8;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public Duration getUtcOffset() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.utcOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.timeOffsetCase_ == 8) {
                    return (Duration) this.timeOffset_;
                }
                return Duration.getDefaultInstance();
            }
            if (this.timeOffsetCase_ == 8) {
                return singleFieldBuilderV3.getMessage();
            }
            return Duration.getDefaultInstance();
        }

        public Builder setUtcOffset(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.utcOffsetBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(duration);
            } else {
                if (duration == null) {
                    throw new NullPointerException();
                }
                this.timeOffset_ = duration;
                onChanged();
            }
            this.timeOffsetCase_ = 8;
            return this;
        }

        public Builder setUtcOffset(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.utcOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.timeOffset_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.timeOffsetCase_ = 8;
            return this;
        }

        public Builder mergeUtcOffset(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.utcOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.timeOffsetCase_ == 8 && this.timeOffset_ != Duration.getDefaultInstance()) {
                    this.timeOffset_ = Duration.newBuilder((Duration) this.timeOffset_).mergeFrom(duration).buildPartial();
                } else {
                    this.timeOffset_ = duration;
                }
                onChanged();
            } else {
                if (this.timeOffsetCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(duration);
                }
                this.utcOffsetBuilder_.setMessage(duration);
            }
            this.timeOffsetCase_ = 8;
            return this;
        }

        public Builder clearUtcOffset() {
            if (this.utcOffsetBuilder_ == null) {
                if (this.timeOffsetCase_ == 8) {
                    this.timeOffsetCase_ = 0;
                    this.timeOffset_ = null;
                    onChanged();
                }
            } else {
                if (this.timeOffsetCase_ == 8) {
                    this.timeOffsetCase_ = 0;
                    this.timeOffset_ = null;
                }
                this.utcOffsetBuilder_.clear();
            }
            return this;
        }

        public Duration.Builder getUtcOffsetBuilder() {
            return getUtcOffsetFieldBuilder().getBuilder();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public DurationOrBuilder getUtcOffsetOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3;
            if (this.timeOffsetCase_ == 8 && (singleFieldBuilderV3 = this.utcOffsetBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.timeOffsetCase_ == 8) {
                return (Duration) this.timeOffset_;
            }
            return Duration.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getUtcOffsetFieldBuilder() {
            if (this.utcOffsetBuilder_ == null) {
                if (this.timeOffsetCase_ != 8) {
                    this.timeOffset_ = Duration.getDefaultInstance();
                }
                this.utcOffsetBuilder_ = new SingleFieldBuilderV3<>((Duration) this.timeOffset_, getParentForChildren(), isClean());
                this.timeOffset_ = null;
            }
            this.timeOffsetCase_ = 8;
            onChanged();
            return this.utcOffsetBuilder_;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public boolean hasTimeZone() {
            return this.timeOffsetCase_ == 9;
        }

        @Override // com.google.type.DateTimeOrBuilder
        public TimeZone getTimeZone() {
            SingleFieldBuilderV3<TimeZone, TimeZone.Builder, TimeZoneOrBuilder> singleFieldBuilderV3 = this.timeZoneBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.timeOffsetCase_ == 9) {
                    return (TimeZone) this.timeOffset_;
                }
                return TimeZone.getDefaultInstance();
            }
            if (this.timeOffsetCase_ == 9) {
                return singleFieldBuilderV3.getMessage();
            }
            return TimeZone.getDefaultInstance();
        }

        public Builder setTimeZone(TimeZone timeZone) {
            SingleFieldBuilderV3<TimeZone, TimeZone.Builder, TimeZoneOrBuilder> singleFieldBuilderV3 = this.timeZoneBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(timeZone);
            } else {
                if (timeZone == null) {
                    throw new NullPointerException();
                }
                this.timeOffset_ = timeZone;
                onChanged();
            }
            this.timeOffsetCase_ = 9;
            return this;
        }

        public Builder setTimeZone(TimeZone.Builder builder) {
            SingleFieldBuilderV3<TimeZone, TimeZone.Builder, TimeZoneOrBuilder> singleFieldBuilderV3 = this.timeZoneBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.timeOffset_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.timeOffsetCase_ = 9;
            return this;
        }

        public Builder mergeTimeZone(TimeZone timeZone) {
            SingleFieldBuilderV3<TimeZone, TimeZone.Builder, TimeZoneOrBuilder> singleFieldBuilderV3 = this.timeZoneBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.timeOffsetCase_ == 9 && this.timeOffset_ != TimeZone.getDefaultInstance()) {
                    this.timeOffset_ = TimeZone.newBuilder((TimeZone) this.timeOffset_).mergeFrom(timeZone).buildPartial();
                } else {
                    this.timeOffset_ = timeZone;
                }
                onChanged();
            } else {
                if (this.timeOffsetCase_ == 9) {
                    singleFieldBuilderV3.mergeFrom(timeZone);
                }
                this.timeZoneBuilder_.setMessage(timeZone);
            }
            this.timeOffsetCase_ = 9;
            return this;
        }

        public Builder clearTimeZone() {
            if (this.timeZoneBuilder_ == null) {
                if (this.timeOffsetCase_ == 9) {
                    this.timeOffsetCase_ = 0;
                    this.timeOffset_ = null;
                    onChanged();
                }
            } else {
                if (this.timeOffsetCase_ == 9) {
                    this.timeOffsetCase_ = 0;
                    this.timeOffset_ = null;
                }
                this.timeZoneBuilder_.clear();
            }
            return this;
        }

        public TimeZone.Builder getTimeZoneBuilder() {
            return getTimeZoneFieldBuilder().getBuilder();
        }

        @Override // com.google.type.DateTimeOrBuilder
        public TimeZoneOrBuilder getTimeZoneOrBuilder() {
            SingleFieldBuilderV3<TimeZone, TimeZone.Builder, TimeZoneOrBuilder> singleFieldBuilderV3;
            if (this.timeOffsetCase_ == 9 && (singleFieldBuilderV3 = this.timeZoneBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.timeOffsetCase_ == 9) {
                return (TimeZone) this.timeOffset_;
            }
            return TimeZone.getDefaultInstance();
        }

        private SingleFieldBuilderV3<TimeZone, TimeZone.Builder, TimeZoneOrBuilder> getTimeZoneFieldBuilder() {
            if (this.timeZoneBuilder_ == null) {
                if (this.timeOffsetCase_ != 9) {
                    this.timeOffset_ = TimeZone.getDefaultInstance();
                }
                this.timeZoneBuilder_ = new SingleFieldBuilderV3<>((TimeZone) this.timeOffset_, getParentForChildren(), isClean());
                this.timeOffset_ = null;
            }
            this.timeOffsetCase_ = 9;
            onChanged();
            return this.timeZoneBuilder_;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* renamed from: com.google.type.DateTime$2 */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class C34922 {
        static final /* synthetic */ int[] $SwitchMap$com$google$type$DateTime$TimeOffsetCase = new int[TimeOffsetCase.values().length];

        static {
            try {
                $SwitchMap$com$google$type$DateTime$TimeOffsetCase[TimeOffsetCase.UTC_OFFSET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$type$DateTime$TimeOffsetCase[TimeOffsetCase.TIME_ZONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$type$DateTime$TimeOffsetCase[TimeOffsetCase.TIMEOFFSET_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static DateTime getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DateTime> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<DateTime> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public DateTime getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
