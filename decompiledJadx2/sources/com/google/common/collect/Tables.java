package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Tables {
    private static final Function<? extends Map<?, ?>, ? extends Map<?, ?>> UNMODIFIABLE_WRAPPER = new Function<Map<Object, Object>, Map<Object, Object>>() { // from class: com.google.common.collect.Tables.1
        @Override // com.google.common.base.Function, java.util.function.Function
        public Map<Object, Object> apply(Map<Object, Object> map) {
            return Collections.unmodifiableMap(map);
        }
    };

    static /* synthetic */ Function access$000() {
        return unmodifiableWrapper();
    }

    private Tables() {
    }

    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(java.util.function.Function<? super T, ? extends R> function, java.util.function.Function<? super T, ? extends C> function2, java.util.function.Function<? super T, ? extends V> function3, Supplier<I> supplier) {
        return TableCollectors.toTable(function, function2, function3, supplier);
    }

    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(java.util.function.Function<? super T, ? extends R> function, java.util.function.Function<? super T, ? extends C> function2, java.util.function.Function<? super T, ? extends V> function3, BinaryOperator<V> binaryOperator, Supplier<I> supplier) {
        return TableCollectors.toTable(function, function2, function3, binaryOperator, supplier);
    }

    public static <R, C, V> Table.Cell<R, C, V> immutableCell(R r, C c, V v) {
        return new ImmutableCell(r, c, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static final class ImmutableCell<R, C, V> extends AbstractCell<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final C columnKey;
        private final R rowKey;
        private final V value;

        ImmutableCell(R r, C c, V v) {
            this.rowKey = r;
            this.columnKey = c;
            this.value = v;
        }

        @Override // com.google.common.collect.Table.Cell
        public R getRowKey() {
            return this.rowKey;
        }

        @Override // com.google.common.collect.Table.Cell
        public C getColumnKey() {
            return this.columnKey;
        }

        @Override // com.google.common.collect.Table.Cell
        public V getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static abstract class AbstractCell<R, C, V> implements Table.Cell<R, C, V> {
        @Override // com.google.common.collect.Table.Cell
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            return Objects.equal(getRowKey(), cell.getRowKey()) && Objects.equal(getColumnKey(), cell.getColumnKey()) && Objects.equal(getValue(), cell.getValue());
        }

        @Override // com.google.common.collect.Table.Cell
        public int hashCode() {
            return Objects.hashCode(getRowKey(), getColumnKey(), getValue());
        }

        public String toString() {
            String valueOf = String.valueOf(getRowKey());
            String valueOf2 = String.valueOf(getColumnKey());
            String valueOf3 = String.valueOf(getValue());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
            sb.append("(");
            sb.append(valueOf);
            sb.append(",");
            sb.append(valueOf2);
            sb.append(")=");
            sb.append(valueOf3);
            return sb.toString();
        }
    }

    public static <R, C, V> Table<C, R, V> transpose(Table<R, C, V> table) {
        if (table instanceof TransposeTable) {
            return ((TransposeTable) table).original;
        }
        return new TransposeTable(table);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class TransposeTable<C, R, V> extends AbstractTable<C, R, V> {
        private static final Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>> TRANSPOSE_CELL = new Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>>() { // from class: com.google.common.collect.Tables.TransposeTable.1
            @Override // com.google.common.base.Function, java.util.function.Function
            public Table.Cell<?, ?, ?> apply(Table.Cell<?, ?, ?> cell) {
                return Tables.immutableCell(cell.getColumnKey(), cell.getRowKey(), cell.getValue());
            }
        };
        final Table<R, C, V> original;

        TransposeTable(Table<R, C, V> table) {
            this.original = (Table) Preconditions.checkNotNull(table);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void clear() {
            this.original.clear();
        }

        @Override // com.google.common.collect.Table
        public Map<C, V> column(R r) {
            return this.original.row(r);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Set<R> columnKeySet() {
            return this.original.rowKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V>> columnMap() {
            return this.original.rowMap();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean contains(Object obj, Object obj2) {
            return this.original.contains(obj2, obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsColumn(Object obj) {
            return this.original.containsRow(obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsRow(Object obj) {
            return this.original.containsColumn(obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsValue(Object obj) {
            return this.original.containsValue(obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V get(Object obj, Object obj2) {
            return this.original.get(obj2, obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V put(C c, R r, V v) {
            return this.original.put(r, c, v);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void putAll(Table<? extends C, ? extends R, ? extends V> table) {
            this.original.putAll(Tables.transpose(table));
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V remove(Object obj, Object obj2) {
            return this.original.remove(obj2, obj);
        }

        @Override // com.google.common.collect.Table
        public Map<R, V> row(C c) {
            return this.original.column(c);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Set<C> rowKeySet() {
            return this.original.columnKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V>> rowMap() {
            return this.original.columnMap();
        }

        @Override // com.google.common.collect.Table
        public int size() {
            return this.original.size();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Collection<V> values() {
            return this.original.values();
        }

        @Override // com.google.common.collect.AbstractTable
        Iterator<Table.Cell<C, R, V>> cellIterator() {
            return Iterators.transform(this.original.cellSet().iterator(), TRANSPOSE_CELL);
        }

        @Override // com.google.common.collect.AbstractTable
        Spliterator<Table.Cell<C, R, V>> cellSpliterator() {
            return CollectSpliterators.map(this.original.cellSet().spliterator(), TRANSPOSE_CELL);
        }
    }

    public static <R, C, V> Table<R, C, V> newCustomTable(Map<R, Map<C, V>> map, com.google.common.base.Supplier<? extends Map<C, V>> supplier) {
        Preconditions.checkArgument(map.isEmpty());
        Preconditions.checkNotNull(supplier);
        return new StandardTable(map, supplier);
    }

    public static <R, C, V1, V2> Table<R, C, V2> transformValues(Table<R, C, V1> table, Function<? super V1, V2> function) {
        return new TransformedTable(table, function);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class TransformedTable<R, C, V1, V2> extends AbstractTable<R, C, V2> {
        final Table<R, C, V1> fromTable;
        final Function<? super V1, V2> function;

        TransformedTable(Table<R, C, V1> table, Function<? super V1, V2> function) {
            this.fromTable = (Table) Preconditions.checkNotNull(table);
            this.function = (Function) Preconditions.checkNotNull(function);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean contains(Object obj, Object obj2) {
            return this.fromTable.contains(obj, obj2);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 get(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.function.apply(this.fromTable.get(obj, obj2));
            }
            return null;
        }

        @Override // com.google.common.collect.Table
        public int size() {
            return this.fromTable.size();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void clear() {
            this.fromTable.clear();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 put(R r, C c, V2 v2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V2> table) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 remove(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.function.apply(this.fromTable.remove(obj, obj2));
            }
            return null;
        }

        @Override // com.google.common.collect.Table
        public Map<C, V2> row(R r) {
            return Maps.transformValues(this.fromTable.row(r), this.function);
        }

        @Override // com.google.common.collect.Table
        public Map<R, V2> column(C c) {
            return Maps.transformValues(this.fromTable.column(c), this.function);
        }

        Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>> cellFunction() {
            return new Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>>() { // from class: com.google.common.collect.Tables.TransformedTable.1
                @Override // com.google.common.base.Function, java.util.function.Function
                public Table.Cell<R, C, V2> apply(Table.Cell<R, C, V1> cell) {
                    return Tables.immutableCell(cell.getRowKey(), cell.getColumnKey(), TransformedTable.this.function.apply(cell.getValue()));
                }
            };
        }

        @Override // com.google.common.collect.AbstractTable
        Iterator<Table.Cell<R, C, V2>> cellIterator() {
            return Iterators.transform(this.fromTable.cellSet().iterator(), cellFunction());
        }

        @Override // com.google.common.collect.AbstractTable
        Spliterator<Table.Cell<R, C, V2>> cellSpliterator() {
            return CollectSpliterators.map(this.fromTable.cellSet().spliterator(), cellFunction());
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Set<R> rowKeySet() {
            return this.fromTable.rowKeySet();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Set<C> columnKeySet() {
            return this.fromTable.columnKeySet();
        }

        @Override // com.google.common.collect.AbstractTable
        Collection<V2> createValues() {
            return Collections2.transform(this.fromTable.values(), this.function);
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V2>> rowMap() {
            return Maps.transformValues(this.fromTable.rowMap(), new Function<Map<C, V1>, Map<C, V2>>() { // from class: com.google.common.collect.Tables.TransformedTable.2
                @Override // com.google.common.base.Function, java.util.function.Function
                public Map<C, V2> apply(Map<C, V1> map) {
                    return Maps.transformValues(map, TransformedTable.this.function);
                }
            });
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V2>> columnMap() {
            return Maps.transformValues(this.fromTable.columnMap(), new Function<Map<R, V1>, Map<R, V2>>() { // from class: com.google.common.collect.Tables.TransformedTable.3
                @Override // com.google.common.base.Function, java.util.function.Function
                public Map<R, V2> apply(Map<R, V1> map) {
                    return Maps.transformValues(map, TransformedTable.this.function);
                }
            });
        }
    }

    public static <R, C, V> Table<R, C, V> unmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
        return new UnmodifiableTable(table);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class UnmodifiableTable<R, C, V> extends ForwardingTable<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final Table<? extends R, ? extends C, ? extends V> delegate;

        UnmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
            this.delegate = (Table) Preconditions.checkNotNull(table);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        public Table<R, C, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<Table.Cell<R, C, V>> cellSet() {
            return Collections.unmodifiableSet(super.cellSet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<R, V> column(C c) {
            return Collections.unmodifiableMap(super.column(c));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<C> columnKeySet() {
            return Collections.unmodifiableSet(super.columnKeySet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<C, Map<R, V>> columnMap() {
            return Collections.unmodifiableMap(Maps.transformValues(super.columnMap(), Tables.access$000()));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public V put(R r, C c, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public V remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<C, V> row(R r) {
            return Collections.unmodifiableMap(super.row(r));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<R> rowKeySet() {
            return Collections.unmodifiableSet(super.rowKeySet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<R, Map<C, V>> rowMap() {
            return Collections.unmodifiableMap(Maps.transformValues(super.rowMap(), Tables.access$000()));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Collection<V> values() {
            return Collections.unmodifiableCollection(super.values());
        }
    }

    public static <R, C, V> RowSortedTable<R, C, V> unmodifiableRowSortedTable(RowSortedTable<R, ? extends C, ? extends V> rowSortedTable) {
        return new UnmodifiableRowSortedMap(rowSortedTable);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    static final class UnmodifiableRowSortedMap<R, C, V> extends UnmodifiableTable<R, C, V> implements RowSortedTable<R, C, V> {
        private static final long serialVersionUID = 0;

        public UnmodifiableRowSortedMap(RowSortedTable<R, ? extends C, ? extends V> rowSortedTable) {
            super(rowSortedTable);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        public RowSortedTable<R, C, V> delegate() {
            return (RowSortedTable) super.delegate();
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public SortedMap<R, Map<C, V>> rowMap() {
            return Collections.unmodifiableSortedMap(Maps.transformValues((SortedMap) delegate().rowMap(), Tables.access$000()));
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public SortedSet<R> rowKeySet() {
            return Collections.unmodifiableSortedSet(delegate().rowKeySet());
        }
    }

    private static <K, V> Function<Map<K, V>, Map<K, V>> unmodifiableWrapper() {
        return (Function<Map<K, V>, Map<K, V>>) UNMODIFIABLE_WRAPPER;
    }

    public static <R, C, V> Table<R, C, V> synchronizedTable(Table<R, C, V> table) {
        return Synchronized.table(table, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equalsImpl(Table<?, ?, ?> table, Object obj) {
        if (obj == table) {
            return true;
        }
        if (obj instanceof Table) {
            return table.cellSet().equals(((Table) obj).cellSet());
        }
        return false;
    }
}
