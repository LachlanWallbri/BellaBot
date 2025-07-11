package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.Set;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: DescriptorRenderer.kt */
/* loaded from: classes2.dex */
public interface DescriptorRendererOptions {
    AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy();

    boolean getDebugMode();

    boolean getEnhancedTypes();

    Set<FqName> getExcludedTypeAnnotationClasses();

    void setAnnotationArgumentsRenderingPolicy(AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy);

    void setClassifierNamePolicy(ClassifierNamePolicy classifierNamePolicy);

    void setDebugMode(boolean z);

    void setExcludedTypeAnnotationClasses(Set<FqName> set);

    void setModifiers(Set<? extends DescriptorRendererModifier> set);

    void setParameterNameRenderingPolicy(ParameterNameRenderingPolicy parameterNameRenderingPolicy);

    void setReceiverAfterName(boolean z);

    void setRenderCompanionObjectName(boolean z);

    void setStartFromName(boolean z);

    void setTextFormat(RenderingFormat renderingFormat);

    void setVerbose(boolean z);

    void setWithDefinedIn(boolean z);

    void setWithoutSuperTypes(boolean z);

    void setWithoutTypeParameters(boolean z);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: DescriptorRenderer.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static boolean getIncludeAnnotationArguments(DescriptorRendererOptions descriptorRendererOptions) {
            return descriptorRendererOptions.getAnnotationArgumentsRenderingPolicy().getIncludeAnnotationArguments();
        }

        public static boolean getIncludeEmptyAnnotationArguments(DescriptorRendererOptions descriptorRendererOptions) {
            return descriptorRendererOptions.getAnnotationArgumentsRenderingPolicy().getIncludeEmptyAnnotationArguments();
        }
    }
}
