package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Modifier;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner;

/* compiled from: ReflectJavaModifierListOwner.kt */
/* loaded from: classes8.dex */
public interface ReflectJavaModifierListOwner extends JavaModifierListOwner {
    int getModifiers();

    /* compiled from: ReflectJavaModifierListOwner.kt */
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        public static boolean isAbstract(ReflectJavaModifierListOwner reflectJavaModifierListOwner) {
            return Modifier.isAbstract(reflectJavaModifierListOwner.getModifiers());
        }

        public static boolean isStatic(ReflectJavaModifierListOwner reflectJavaModifierListOwner) {
            return Modifier.isStatic(reflectJavaModifierListOwner.getModifiers());
        }

        public static boolean isFinal(ReflectJavaModifierListOwner reflectJavaModifierListOwner) {
            return Modifier.isFinal(reflectJavaModifierListOwner.getModifiers());
        }

        public static Visibility getVisibility(ReflectJavaModifierListOwner reflectJavaModifierListOwner) {
            int modifiers = reflectJavaModifierListOwner.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                Visibility visibility = Visibilities.PUBLIC;
                Intrinsics.checkExpressionValueIsNotNull(visibility, "Visibilities.PUBLIC");
                return visibility;
            }
            if (Modifier.isPrivate(modifiers)) {
                Visibility visibility2 = Visibilities.PRIVATE;
                Intrinsics.checkExpressionValueIsNotNull(visibility2, "Visibilities.PRIVATE");
                return visibility2;
            }
            if (Modifier.isProtected(modifiers)) {
                Visibility visibility3 = Modifier.isStatic(modifiers) ? JavaVisibilities.PROTECTED_STATIC_VISIBILITY : JavaVisibilities.PROTECTED_AND_PACKAGE;
                Intrinsics.checkExpressionValueIsNotNull(visibility3, "if (Modifier.isStatic(mo…ies.PROTECTED_AND_PACKAGE");
                return visibility3;
            }
            Visibility visibility4 = JavaVisibilities.PACKAGE_VISIBILITY;
            Intrinsics.checkExpressionValueIsNotNull(visibility4, "JavaVisibilities.PACKAGE_VISIBILITY");
            return visibility4;
        }
    }
}
