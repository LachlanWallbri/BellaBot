package io.opencensus.trace;

import io.opencensus.common.Function;
import io.opencensus.internal.Utils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class AttributeValue {
    @Deprecated
    public abstract <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4);

    public abstract <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5);

    public static AttributeValue stringAttributeValue(String str) {
        return AttributeValueString.create(str);
    }

    public static AttributeValue booleanAttributeValue(boolean z) {
        return AttributeValueBoolean.create(Boolean.valueOf(z));
    }

    public static AttributeValue longAttributeValue(long j) {
        return AttributeValueLong.create(Long.valueOf(j));
    }

    public static AttributeValue doubleAttributeValue(double d) {
        return AttributeValueDouble.create(Double.valueOf(d));
    }

    AttributeValue() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class AttributeValueString extends AttributeValue {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String getStringValue();

        static AttributeValue create(String str) {
            return new AutoValue_AttributeValue_AttributeValueString((String) Utils.checkNotNull(str, "stringValue"));
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4) {
            return function.apply(getStringValue());
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5) {
            return function.apply(getStringValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class AttributeValueBoolean extends AttributeValue {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Boolean getBooleanValue();

        static AttributeValue create(Boolean bool) {
            return new AutoValue_AttributeValue_AttributeValueBoolean((Boolean) Utils.checkNotNull(bool, "booleanValue"));
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4) {
            return function2.apply(getBooleanValue());
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5) {
            return function2.apply(getBooleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class AttributeValueLong extends AttributeValue {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Long getLongValue();

        static AttributeValue create(Long l) {
            return new AutoValue_AttributeValue_AttributeValueLong((Long) Utils.checkNotNull(l, "longValue"));
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4) {
            return function3.apply(getLongValue());
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5) {
            return function3.apply(getLongValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class AttributeValueDouble extends AttributeValue {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Double getDoubleValue();

        static AttributeValue create(Double d) {
            return new AutoValue_AttributeValue_AttributeValueDouble((Double) Utils.checkNotNull(d, "doubleValue"));
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4) {
            return function4.apply(getDoubleValue());
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5) {
            return function4.apply(getDoubleValue());
        }
    }
}
