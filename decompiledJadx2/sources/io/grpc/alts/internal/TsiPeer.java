package io.grpc.alts.internal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class TsiPeer {
    private final List<Property<?>> properties;

    public TsiPeer(List<Property<?>> list) {
        this.properties = Collections.unmodifiableList(list);
    }

    public List<Property<?>> getProperties() {
        return this.properties;
    }

    public Property<?> getProperty(String str) {
        for (Property<?> property : this.properties) {
            if (property.getName().equals(str)) {
                return property;
            }
        }
        return null;
    }

    public String toString() {
        return new ArrayList(this.properties).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Property<T> {
        private final String name;
        private final T value;

        protected Property(@Nonnull String str, @Nonnull T t) {
            this.name = str;
            this.value = t;
        }

        public final T getValue() {
            return this.value;
        }

        public final String getName() {
            return this.name;
        }

        public String toString() {
            return String.format("%s=%s", this.name, this.value);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class BooleanProperty extends Property<Boolean> {
        public BooleanProperty(@Nonnull String str, boolean z) {
            super(str, Boolean.valueOf(z));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class SignedInt64Property extends Property<Long> {
        public SignedInt64Property(@Nonnull String str, @Nonnull Long l) {
            super(str, l);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class UnsignedInt64Property extends Property<BigInteger> {
        public UnsignedInt64Property(@Nonnull String str, @Nonnull BigInteger bigInteger) {
            super(str, bigInteger);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class DoubleProperty extends Property<Double> {
        public DoubleProperty(@Nonnull String str, @Nonnull Double d) {
            super(str, d);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class StringProperty extends Property<String> {
        public StringProperty(@Nonnull String str, @Nonnull String str2) {
            super(str, str2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class PropertyList extends Property<List<Property<?>>> {
        public PropertyList(@Nonnull String str, @Nonnull List<Property<?>> list) {
            super(str, list);
        }
    }
}
