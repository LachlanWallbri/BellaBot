package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: methodSignatureMapping.kt */
/* loaded from: classes2.dex */
public abstract class JvmType {
    private JvmType() {
    }

    public /* synthetic */ JvmType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: methodSignatureMapping.kt */
    /* loaded from: classes2.dex */
    public static final class Primitive extends JvmType {
        private final JvmPrimitiveType jvmPrimitiveType;

        public Primitive(JvmPrimitiveType jvmPrimitiveType) {
            super(null);
            this.jvmPrimitiveType = jvmPrimitiveType;
        }

        public final JvmPrimitiveType getJvmPrimitiveType() {
            return this.jvmPrimitiveType;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: methodSignatureMapping.kt */
    /* loaded from: classes2.dex */
    public static final class Object extends JvmType {
        private final String internalName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Object(String internalName) {
            super(null);
            Intrinsics.checkParameterIsNotNull(internalName, "internalName");
            this.internalName = internalName;
        }

        public final String getInternalName() {
            return this.internalName;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: methodSignatureMapping.kt */
    /* loaded from: classes2.dex */
    public static final class Array extends JvmType {
        private final JvmType elementType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Array(JvmType elementType) {
            super(null);
            Intrinsics.checkParameterIsNotNull(elementType, "elementType");
            this.elementType = elementType;
        }

        public final JvmType getElementType() {
            return this.elementType;
        }
    }

    public String toString() {
        return JvmTypeFactoryImpl.INSTANCE.toString(this);
    }
}
