package io.grpc.netty.shaded.io.netty.util;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface UncheckedBooleanSupplier extends BooleanSupplier {
    public static final UncheckedBooleanSupplier FALSE_SUPPLIER = new UncheckedBooleanSupplier() { // from class: io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier.1
        @Override // io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier, io.grpc.netty.shaded.io.netty.util.BooleanSupplier
        public boolean get() {
            return false;
        }
    };
    public static final UncheckedBooleanSupplier TRUE_SUPPLIER = new UncheckedBooleanSupplier() { // from class: io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier.2
        @Override // io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier, io.grpc.netty.shaded.io.netty.util.BooleanSupplier
        public boolean get() {
            return true;
        }
    };

    @Override // io.grpc.netty.shaded.io.netty.util.BooleanSupplier
    boolean get();
}
