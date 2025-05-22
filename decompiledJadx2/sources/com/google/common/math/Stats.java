package com.google.common.math;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Stats implements Serializable {
    static final int BYTES = 40;
    private static final long serialVersionUID = 0;
    private final long count;
    private final double max;
    private final double mean;
    private final double min;
    private final double sumOfSquaresOfDeltas;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Stats(long j, double d, double d2, double d3, double d4) {
        this.count = j;
        this.mean = d;
        this.sumOfSquaresOfDeltas = d2;
        this.min = d3;
        this.max = d4;
    }

    /* renamed from: of */
    public static Stats m739of(Iterable<? extends Number> iterable) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(iterable);
        return statsAccumulator.snapshot();
    }

    /* renamed from: of */
    public static Stats m740of(Iterator<? extends Number> it) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(it);
        return statsAccumulator.snapshot();
    }

    /* renamed from: of */
    public static Stats m744of(double... dArr) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(dArr);
        return statsAccumulator.snapshot();
    }

    /* renamed from: of */
    public static Stats m745of(int... iArr) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(iArr);
        return statsAccumulator.snapshot();
    }

    /* renamed from: of */
    public static Stats m746of(long... jArr) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(jArr);
        return statsAccumulator.snapshot();
    }

    /* renamed from: of */
    public static Stats m741of(DoubleStream doubleStream) {
        return ((StatsAccumulator) doubleStream.collect($$Lambda$NxiGhALC83YI0wnmXZSB7kxRx8.INSTANCE, $$Lambda$no_TxIFzLTgAj_T0Gi5Zx0u0Iks.INSTANCE, $$Lambda$RCzjAXrdtN5utawKgArjIM_6Qhc.INSTANCE)).snapshot();
    }

    /* renamed from: of */
    public static Stats m742of(IntStream intStream) {
        return ((StatsAccumulator) intStream.collect($$Lambda$NxiGhALC83YI0wnmXZSB7kxRx8.INSTANCE, $$Lambda$N2aUQ0vhoZ_gqty4bDlD6FDa3jc.INSTANCE, $$Lambda$RCzjAXrdtN5utawKgArjIM_6Qhc.INSTANCE)).snapshot();
    }

    /* renamed from: of */
    public static Stats m743of(LongStream longStream) {
        return ((StatsAccumulator) longStream.collect($$Lambda$NxiGhALC83YI0wnmXZSB7kxRx8.INSTANCE, $$Lambda$JkZ6g3izIAIgsg4YbL_47EarzPg.INSTANCE, $$Lambda$RCzjAXrdtN5utawKgArjIM_6Qhc.INSTANCE)).snapshot();
    }

    public static Collector<Number, StatsAccumulator, Stats> toStats() {
        return Collector.of($$Lambda$NxiGhALC83YI0wnmXZSB7kxRx8.INSTANCE, new BiConsumer() { // from class: com.google.common.math.-$$Lambda$Stats$gIGA63isveyeNLr_xbLB7R-NDpA
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((StatsAccumulator) obj).add(((Number) obj2).doubleValue());
            }
        }, new BinaryOperator() { // from class: com.google.common.math.-$$Lambda$Stats$x72XYbkktMWiYqlA01ilEp2GerY
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Stats.lambda$toStats$1((StatsAccumulator) obj, (StatsAccumulator) obj2);
            }
        }, new Function() { // from class: com.google.common.math.-$$Lambda$TzxTvGzDas9fpS3j1qYSoIhy88c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((StatsAccumulator) obj).snapshot();
            }
        }, Collector.Characteristics.UNORDERED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ StatsAccumulator lambda$toStats$1(StatsAccumulator statsAccumulator, StatsAccumulator statsAccumulator2) {
        statsAccumulator.addAll(statsAccumulator2);
        return statsAccumulator;
    }

    public long count() {
        return this.count;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0);
        return this.mean;
    }

    public double sum() {
        return this.mean * this.count;
    }

    public double populationVariance() {
        Preconditions.checkState(this.count > 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1) {
            return 0.0d;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / count();
    }

    public double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public double sampleVariance() {
        Preconditions.checkState(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / (this.count - 1);
    }

    public double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public double min() {
        Preconditions.checkState(this.count != 0);
        return this.min;
    }

    public double max() {
        Preconditions.checkState(this.count != 0);
        return this.max;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Stats stats = (Stats) obj;
        return this.count == stats.count && Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(stats.mean) && Double.doubleToLongBits(this.sumOfSquaresOfDeltas) == Double.doubleToLongBits(stats.sumOfSquaresOfDeltas) && Double.doubleToLongBits(this.min) == Double.doubleToLongBits(stats.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(stats.max);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.count), Double.valueOf(this.mean), Double.valueOf(this.sumOfSquaresOfDeltas), Double.valueOf(this.min), Double.valueOf(this.max));
    }

    public String toString() {
        if (count() > 0) {
            return MoreObjects.toStringHelper(this).add("count", this.count).add("mean", this.mean).add("populationStandardDeviation", populationStandardDeviation()).add("min", this.min).add("max", this.max).toString();
        }
        return MoreObjects.toStringHelper(this).add("count", this.count).toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    public static double meanOf(Iterable<? extends Number> iterable) {
        return meanOf(iterable.iterator());
    }

    public static double meanOf(Iterator<? extends Number> it) {
        Preconditions.checkArgument(it.hasNext());
        double doubleValue = it.next().doubleValue();
        long j = 1;
        while (it.hasNext()) {
            double doubleValue2 = it.next().doubleValue();
            j++;
            doubleValue = (Doubles.isFinite(doubleValue2) && Doubles.isFinite(doubleValue)) ? doubleValue + ((doubleValue2 - doubleValue) / j) : StatsAccumulator.calculateNewMeanNonFinite(doubleValue, doubleValue2);
        }
        return doubleValue;
    }

    public static double meanOf(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            double d2 = dArr[i];
            d = (Doubles.isFinite(d2) && Doubles.isFinite(d)) ? d + ((d2 - d) / (i + 1)) : StatsAccumulator.calculateNewMeanNonFinite(d, d2);
        }
        return d;
    }

    public static double meanOf(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        double d = iArr[0];
        for (int i = 1; i < iArr.length; i++) {
            double d2 = iArr[i];
            d = (Doubles.isFinite(d2) && Doubles.isFinite(d)) ? d + ((d2 - d) / (i + 1)) : StatsAccumulator.calculateNewMeanNonFinite(d, d2);
        }
        return d;
    }

    public static double meanOf(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        double d = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            double d2 = jArr[i];
            d = (Doubles.isFinite(d2) && Doubles.isFinite(d)) ? d + ((d2 - d) / (i + 1)) : StatsAccumulator.calculateNewMeanNonFinite(d, d2);
        }
        return d;
    }

    public byte[] toByteArray() {
        ByteBuffer order = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
        writeTo(order);
        return order.array();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeTo(ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer);
        Preconditions.checkArgument(byteBuffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, byteBuffer.remaining());
        byteBuffer.putLong(this.count).putDouble(this.mean).putDouble(this.sumOfSquaresOfDeltas).putDouble(this.min).putDouble(this.max);
    }

    public static Stats fromByteArray(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkArgument(bArr.length == 40, "Expected Stats.BYTES = %s remaining , got %s", 40, bArr.length);
        return readFrom(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Stats readFrom(ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer);
        Preconditions.checkArgument(byteBuffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, byteBuffer.remaining());
        return new Stats(byteBuffer.getLong(), byteBuffer.getDouble(), byteBuffer.getDouble(), byteBuffer.getDouble(), byteBuffer.getDouble());
    }
}
