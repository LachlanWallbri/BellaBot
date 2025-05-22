package kotlin.reflect.jvm.internal.impl.descriptors;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface MemberDescriptor extends DeclarationDescriptorNonRoot, DeclarationDescriptorWithVisibility {
    Modality getModality();

    Visibility getVisibility();

    boolean isActual();

    boolean isExpect();

    boolean isExternal();
}
