package kotlin.reflect.jvm.internal.impl.resolve;

import defpackage.C$r8$backportedMethods$utility$Boolean$1$hashCode;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: overridingUtils.kt */
/* loaded from: classes2.dex */
public final class OverridingUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <H> Collection<H> selectMostSpecificInEachOverridableGroup(Collection<? extends H> selectMostSpecificInEachOverridableGroup, Function1<? super H, ? extends CallableDescriptor> descriptorByHandle) {
        Intrinsics.checkParameterIsNotNull(selectMostSpecificInEachOverridableGroup, "$this$selectMostSpecificInEachOverridableGroup");
        Intrinsics.checkParameterIsNotNull(descriptorByHandle, "descriptorByHandle");
        if (selectMostSpecificInEachOverridableGroup.size() <= 1) {
            return selectMostSpecificInEachOverridableGroup;
        }
        LinkedList linkedList = new LinkedList(selectMostSpecificInEachOverridableGroup);
        SmartSet create = SmartSet.Companion.create();
        while (true) {
            LinkedList linkedList2 = linkedList;
            if (!linkedList2.isEmpty()) {
                Object first = CollectionsKt.first((List<? extends Object>) linkedList);
                final SmartSet create2 = SmartSet.Companion.create();
                Collection<C$r8$backportedMethods$utility$Boolean$1$hashCode> extractMembersOverridableInBothWays = OverridingUtil.extractMembersOverridableInBothWays(first, linkedList2, descriptorByHandle, new Function1<H, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                        invoke2((C7796x410e6287<H>) obj);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(H it) {
                        SmartSet smartSet = SmartSet.this;
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        smartSet.add(it);
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(extractMembersOverridableInBothWays, "OverridingUtil.extractMe…nflictedHandles.add(it) }");
                if (extractMembersOverridableInBothWays.size() == 1 && create2.isEmpty()) {
                    Object single = CollectionsKt.single(extractMembersOverridableInBothWays);
                    Intrinsics.checkExpressionValueIsNotNull(single, "overridableGroup.single()");
                    create.add(single);
                } else {
                    C$r8$backportedMethods$utility$Boolean$1$hashCode c$r8$backportedMethods$utility$Boolean$1$hashCode = (Object) OverridingUtil.selectMostSpecificMember(extractMembersOverridableInBothWays, descriptorByHandle);
                    Intrinsics.checkExpressionValueIsNotNull(c$r8$backportedMethods$utility$Boolean$1$hashCode, "OverridingUtil.selectMos…roup, descriptorByHandle)");
                    CallableDescriptor invoke = descriptorByHandle.invoke(c$r8$backportedMethods$utility$Boolean$1$hashCode);
                    for (C$r8$backportedMethods$utility$Boolean$1$hashCode it : extractMembersOverridableInBothWays) {
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        if (!OverridingUtil.isMoreSpecific(invoke, descriptorByHandle.invoke(it))) {
                            create2.add(it);
                        }
                    }
                    SmartSet smartSet = create2;
                    if (!smartSet.isEmpty()) {
                        create.addAll(smartSet);
                    }
                    create.add(c$r8$backportedMethods$utility$Boolean$1$hashCode);
                }
            } else {
                return create;
            }
        }
    }

    public static final <D extends CallableDescriptor> void retainMostSpecificInEachOverridableGroup(Collection<D> retainMostSpecificInEachOverridableGroup) {
        Intrinsics.checkParameterIsNotNull(retainMostSpecificInEachOverridableGroup, "$this$retainMostSpecificInEachOverridableGroup");
        Collection<?> selectMostSpecificInEachOverridableGroup = selectMostSpecificInEachOverridableGroup(retainMostSpecificInEachOverridableGroup, new Function1<D, D>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1
            /* JADX WARN: Incorrect return type in method signature: (TD;)TD; */
            @Override // kotlin.jvm.functions.Function1
            public final CallableDescriptor invoke(CallableDescriptor receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                return receiver;
            }
        });
        if (retainMostSpecificInEachOverridableGroup.size() == selectMostSpecificInEachOverridableGroup.size()) {
            return;
        }
        retainMostSpecificInEachOverridableGroup.retainAll(selectMostSpecificInEachOverridableGroup);
    }
}
