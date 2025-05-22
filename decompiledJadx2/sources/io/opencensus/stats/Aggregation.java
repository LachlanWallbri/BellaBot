package io.opencensus.stats;

import io.opencensus.common.Function;
import io.opencensus.internal.Utils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Aggregation {
    public abstract <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5);

    private Aggregation() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Sum extends Aggregation {
        private static final Sum INSTANCE = new AutoValue_Aggregation_Sum();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Sum() {
            super();
        }

        public static Sum create() {
            return INSTANCE;
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Count extends Aggregation {
        private static final Count INSTANCE = new AutoValue_Aggregation_Count();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Count() {
            super();
        }

        public static Count create() {
            return INSTANCE;
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function2.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    @Deprecated
    /* loaded from: classes8.dex */
    public static abstract class Mean extends Aggregation {
        private static final Mean INSTANCE = new AutoValue_Aggregation_Mean();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Mean() {
            super();
        }

        public static Mean create() {
            return INSTANCE;
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function5.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Distribution extends Aggregation {
        public abstract BucketBoundaries getBucketBoundaries();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Distribution() {
            super();
        }

        public static Distribution create(BucketBoundaries bucketBoundaries) {
            Utils.checkNotNull(bucketBoundaries, "bucketBoundaries");
            return new AutoValue_Aggregation_Distribution(bucketBoundaries);
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function3.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class LastValue extends Aggregation {
        private static final LastValue INSTANCE = new AutoValue_Aggregation_LastValue();

        /* JADX INFO: Access modifiers changed from: package-private */
        public LastValue() {
            super();
        }

        public static LastValue create() {
            return INSTANCE;
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function4.apply(this);
        }
    }
}
