package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.MoreCollectors;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public final class MoreCollectors {
    private static final Collector<Object, ?, Optional<Object>> TO_OPTIONAL = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$AW8d5rj-de7n52sHHlfKGLys6o4
        @Override // java.util.function.Supplier
        public final Object get() {
            return new MoreCollectors.ToOptionalState();
        }
    }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$OugZ7uzX1ge93a2UFbU199Ga9uI
        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            ((MoreCollectors.ToOptionalState) obj).add(obj2);
        }
    }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$T3Z3qL6s2UgnlIibFIHZE8hnQd8
        @Override // java.util.function.BiFunction
        public final Object apply(Object obj, Object obj2) {
            return ((MoreCollectors.ToOptionalState) obj).combine((MoreCollectors.ToOptionalState) obj2);
        }
    }, new Function() { // from class: com.google.common.collect.-$$Lambda$qKNWqC3OxvkO7x9RxwopFUo-Eg4
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((MoreCollectors.ToOptionalState) obj).getOptional();
        }
    }, Collector.Characteristics.UNORDERED);
    private static final Object NULL_PLACEHOLDER = new Object();
    private static final Collector<Object, ?, Object> ONLY_ELEMENT = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$AW8d5rj-de7n52sHHlfKGLys6o4
        @Override // java.util.function.Supplier
        public final Object get() {
            return new MoreCollectors.ToOptionalState();
        }
    }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$MoreCollectors$oN3vFXc6_LKZJQOgj9PeBSrbA4g
        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            MoreCollectors.lambda$static$0((MoreCollectors.ToOptionalState) obj, obj2);
        }
    }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$T3Z3qL6s2UgnlIibFIHZE8hnQd8
        @Override // java.util.function.BiFunction
        public final Object apply(Object obj, Object obj2) {
            return ((MoreCollectors.ToOptionalState) obj).combine((MoreCollectors.ToOptionalState) obj2);
        }
    }, new Function() { // from class: com.google.common.collect.-$$Lambda$MoreCollectors$A6KiO1a0N4D-Le0rKqSWOUITsXo
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return MoreCollectors.lambda$static$1((MoreCollectors.ToOptionalState) obj);
        }
    }, Collector.Characteristics.UNORDERED);

    public static <T> Collector<T, ?, Optional<T>> toOptional() {
        return (Collector<T, ?, Optional<T>>) TO_OPTIONAL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$0(ToOptionalState toOptionalState, Object obj) {
        if (obj == null) {
            obj = NULL_PLACEHOLDER;
        }
        toOptionalState.add(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$static$1(ToOptionalState toOptionalState) {
        Object element = toOptionalState.getElement();
        if (element == NULL_PLACEHOLDER) {
            return null;
        }
        return element;
    }

    public static <T> Collector<T, ?, T> onlyElement() {
        return (Collector<T, ?, T>) ONLY_ELEMENT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ToOptionalState {
        static final int MAX_EXTRAS = 4;
        Object element = null;
        List<Object> extras = null;

        IllegalArgumentException multiples(boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append("expected one element but was: <");
            sb.append(this.element);
            for (Object obj : this.extras) {
                sb.append(", ");
                sb.append(obj);
            }
            if (z) {
                sb.append(", ...");
            }
            sb.append(Typography.greater);
            throw new IllegalArgumentException(sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void add(Object obj) {
            Preconditions.checkNotNull(obj);
            if (this.element == null) {
                this.element = obj;
                return;
            }
            List<Object> list = this.extras;
            if (list == null) {
                this.extras = new ArrayList(4);
                this.extras.add(obj);
            } else {
                if (list.size() < 4) {
                    this.extras.add(obj);
                    return;
                }
                throw multiples(true);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ToOptionalState combine(ToOptionalState toOptionalState) {
            if (this.element == null) {
                return toOptionalState;
            }
            if (toOptionalState.element == null) {
                return this;
            }
            if (this.extras == null) {
                this.extras = new ArrayList();
            }
            this.extras.add(toOptionalState.element);
            List<Object> list = toOptionalState.extras;
            if (list != null) {
                this.extras.addAll(list);
            }
            if (this.extras.size() <= 4) {
                return this;
            }
            List<Object> list2 = this.extras;
            list2.subList(4, list2.size()).clear();
            throw multiples(true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Optional<Object> getOptional() {
            if (this.extras == null) {
                return Optional.ofNullable(this.element);
            }
            throw multiples(false);
        }

        Object getElement() {
            Object obj = this.element;
            if (obj == null) {
                throw new NoSuchElementException();
            }
            if (this.extras == null) {
                return obj;
            }
            throw multiples(false);
        }
    }

    private MoreCollectors() {
    }
}
