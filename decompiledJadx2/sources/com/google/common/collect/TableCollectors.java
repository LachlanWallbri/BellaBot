package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.common.collect.TableCollectors;
import com.google.common.collect.Tables;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes3.dex */
final class TableCollectors {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(final Function<? super T, ? extends R> function, final Function<? super T, ? extends C> function2, final Function<? super T, ? extends V> function3) {
        Preconditions.checkNotNull(function, "rowFunction");
        Preconditions.checkNotNull(function2, "columnFunction");
        Preconditions.checkNotNull(function3, "valueFunction");
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$H0eZqfucywqdBoe4iubNJTJHvlg
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ImmutableTable.Builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$TableCollectors$4iqV8Sfan-pGrQdl6ddV6RJSx_0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableTable.Builder) obj).put(function.apply(obj2), function2.apply(obj2), function3.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$sSJHfcH7LagxpW6_Yb9kffXzUKE
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableTable.Builder) obj).combine((ImmutableTable.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$s1PdkapaC8UGa-ZdfnzXBIg8NDs
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableTable.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(final Function<? super T, ? extends R> function, final Function<? super T, ? extends C> function2, final Function<? super T, ? extends V> function3, final BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(function, "rowFunction");
        Preconditions.checkNotNull(function2, "columnFunction");
        Preconditions.checkNotNull(function3, "valueFunction");
        Preconditions.checkNotNull(binaryOperator, "mergeFunction");
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$TableCollectors$cxSb0H_yhTdjch-sj_5uSuPKYU4
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableCollectors.lambda$toImmutableTable$1();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$TableCollectors$8R3g40hF9tMJJ2hSR7FKss1sDA4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TableCollectors.ImmutableTableCollectorState immutableTableCollectorState = (TableCollectors.ImmutableTableCollectorState) obj;
                immutableTableCollectorState.put(function.apply(obj2), function2.apply(obj2), function3.apply(obj2), binaryOperator);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$TableCollectors$T9Q8bOLbHZ7r7rhuaeFk887tS-4
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                TableCollectors.ImmutableTableCollectorState combine;
                combine = ((TableCollectors.ImmutableTableCollectorState) obj).combine((TableCollectors.ImmutableTableCollectorState) obj2, binaryOperator);
                return combine;
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$TableCollectors$WMbIrV_f6UbnybY3BynAN6Cjo4E
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ImmutableTable table;
                table = ((TableCollectors.ImmutableTableCollectorState) obj).toTable();
                return table;
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ImmutableTableCollectorState lambda$toImmutableTable$1() {
        return new ImmutableTableCollectorState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3, Supplier<I> supplier) {
        return toTable(function, function2, function3, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$TableCollectors$6V0M-OCxE6pJc5X2tugCTczP_Yo
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return TableCollectors.lambda$toTable$5(obj, obj2);
            }
        }, supplier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$toTable$5(Object obj, Object obj2) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24 + String.valueOf(valueOf2).length());
        sb.append("Conflicting values ");
        sb.append(valueOf);
        sb.append(" and ");
        sb.append(valueOf2);
        throw new IllegalStateException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(final Function<? super T, ? extends R> function, final Function<? super T, ? extends C> function2, final Function<? super T, ? extends V> function3, final BinaryOperator<V> binaryOperator, Supplier<I> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(function3);
        Preconditions.checkNotNull(binaryOperator);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$TableCollectors$G9EkaxFuxcLoDzSNuBzKPQysmSU
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Table table = (Table) obj;
                TableCollectors.mergeTables(table, function.apply(obj2), function2.apply(obj2), function3.apply(obj2), binaryOperator);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$TableCollectors$OIdeN7uyp2TyuCpZVNuMrCGuZAM
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return TableCollectors.lambda$toTable$7(binaryOperator, (Table) obj, (Table) obj2);
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Table lambda$toTable$7(BinaryOperator binaryOperator, Table table, Table table2) {
        for (Table.Cell cell : table2.cellSet()) {
            mergeTables(table, cell.getRowKey(), cell.getColumnKey(), cell.getValue(), binaryOperator);
        }
        return table;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ImmutableTableCollectorState<R, C, V> {
        final List<MutableCell<R, C, V>> insertionOrder;
        final Table<R, C, MutableCell<R, C, V>> table;

        private ImmutableTableCollectorState() {
            this.insertionOrder = new ArrayList();
            this.table = HashBasedTable.create();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void put(R r, C c, V v, BinaryOperator<V> binaryOperator) {
            MutableCell<R, C, V> mutableCell = this.table.get(r, c);
            if (mutableCell == null) {
                MutableCell<R, C, V> mutableCell2 = new MutableCell<>(r, c, v);
                this.insertionOrder.add(mutableCell2);
                this.table.put(r, c, mutableCell2);
                return;
            }
            mutableCell.merge(v, binaryOperator);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ImmutableTableCollectorState<R, C, V> combine(ImmutableTableCollectorState<R, C, V> immutableTableCollectorState, BinaryOperator<V> binaryOperator) {
            for (MutableCell<R, C, V> mutableCell : immutableTableCollectorState.insertionOrder) {
                put(mutableCell.getRowKey(), mutableCell.getColumnKey(), mutableCell.getValue(), binaryOperator);
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ImmutableTable<R, C, V> toTable() {
            return ImmutableTable.copyOf(this.insertionOrder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class MutableCell<R, C, V> extends Tables.AbstractCell<R, C, V> {
        private final C column;
        private final R row;
        private V value;

        MutableCell(R r, C c, V v) {
            this.row = (R) Preconditions.checkNotNull(r, "row");
            this.column = (C) Preconditions.checkNotNull(c, "column");
            this.value = (V) Preconditions.checkNotNull(v, ES6Iterator.VALUE_PROPERTY);
        }

        @Override // com.google.common.collect.Table.Cell
        public R getRowKey() {
            return this.row;
        }

        @Override // com.google.common.collect.Table.Cell
        public C getColumnKey() {
            return this.column;
        }

        @Override // com.google.common.collect.Table.Cell
        public V getValue() {
            return this.value;
        }

        void merge(V v, BinaryOperator<V> binaryOperator) {
            Preconditions.checkNotNull(v, ES6Iterator.VALUE_PROPERTY);
            this.value = (V) Preconditions.checkNotNull(binaryOperator.apply(this.value, v), "mergeFunction.apply");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <R, C, V> void mergeTables(Table<R, C, V> table, R r, C c, V v, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(v);
        V v2 = table.get(r, c);
        if (v2 == null) {
            table.put(r, c, v);
            return;
        }
        Object apply = binaryOperator.apply(v2, v);
        if (apply == null) {
            table.remove(r, c);
        } else {
            table.put(r, c, apply);
        }
    }

    private TableCollectors() {
    }
}
