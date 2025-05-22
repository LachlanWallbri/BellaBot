package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: OverridingStrategy.kt */
/* loaded from: classes2.dex */
public abstract class OverridingStrategy {
    public abstract void addFakeOverride(CallableMemberDescriptor callableMemberDescriptor);

    public abstract void inheritanceConflict(CallableMemberDescriptor callableMemberDescriptor, CallableMemberDescriptor callableMemberDescriptor2);

    public abstract void overrideConflict(CallableMemberDescriptor callableMemberDescriptor, CallableMemberDescriptor callableMemberDescriptor2);

    public void setOverriddenDescriptors(CallableMemberDescriptor member, Collection<? extends CallableMemberDescriptor> overridden) {
        Intrinsics.checkParameterIsNotNull(member, "member");
        Intrinsics.checkParameterIsNotNull(overridden, "overridden");
        member.setOverriddenDescriptors(overridden);
    }
}
