package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.KotlinMetadataFinder;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KotlinClassFinder.kt */
/* loaded from: classes2.dex */
public interface KotlinClassFinder extends KotlinMetadataFinder {
    Result findKotlinClassOrContent(JavaClass javaClass);

    Result findKotlinClassOrContent(ClassId classId);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: KotlinClassFinder.kt */
    /* loaded from: classes2.dex */
    public static abstract class Result {
        private Result() {
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KotlinJvmBinaryClass toKotlinJvmBinaryClass() {
            KotlinClass kotlinClass = (KotlinClass) (!(this instanceof KotlinClass) ? null : this);
            if (kotlinClass != null) {
                return kotlinClass.getKotlinJvmBinaryClass();
            }
            return null;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: KotlinClassFinder.kt */
        /* loaded from: classes2.dex */
        public static final class KotlinClass extends Result {
            private final KotlinJvmBinaryClass kotlinJvmBinaryClass;

            public boolean equals(Object obj) {
                if (this != obj) {
                    return (obj instanceof KotlinClass) && Intrinsics.areEqual(this.kotlinJvmBinaryClass, ((KotlinClass) obj).kotlinJvmBinaryClass);
                }
                return true;
            }

            public int hashCode() {
                KotlinJvmBinaryClass kotlinJvmBinaryClass = this.kotlinJvmBinaryClass;
                if (kotlinJvmBinaryClass != null) {
                    return kotlinJvmBinaryClass.hashCode();
                }
                return 0;
            }

            public String toString() {
                return "KotlinClass(kotlinJvmBinaryClass=" + this.kotlinJvmBinaryClass + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public KotlinClass(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
                super(null);
                Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinJvmBinaryClass");
                this.kotlinJvmBinaryClass = kotlinJvmBinaryClass;
            }

            public final KotlinJvmBinaryClass getKotlinJvmBinaryClass() {
                return this.kotlinJvmBinaryClass;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: KotlinClassFinder.kt */
        /* loaded from: classes2.dex */
        public static final class ClassFileContent extends Result {
            private final byte[] content;

            public boolean equals(Object obj) {
                if (this != obj) {
                    return (obj instanceof ClassFileContent) && Intrinsics.areEqual(this.content, ((ClassFileContent) obj).content);
                }
                return true;
            }

            public int hashCode() {
                byte[] bArr = this.content;
                if (bArr != null) {
                    return Arrays.hashCode(bArr);
                }
                return 0;
            }

            public String toString() {
                return "ClassFileContent(content=" + Arrays.toString(this.content) + ")";
            }

            public final byte[] getContent() {
                return this.content;
            }
        }
    }
}
