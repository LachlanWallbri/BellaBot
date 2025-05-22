package com.pudu.library.loracall.base;

import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: BaseFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final /* synthetic */ class BaseFragment$isBindingInit$1 extends MutablePropertyReference0 {
    BaseFragment$isBindingInit$1(BaseFragment baseFragment) {
        super(baseFragment);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "binding";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(BaseFragment.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getBinding()Landroidx/databinding/ViewDataBinding;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return ((BaseFragment) this.receiver).getBinding();
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((BaseFragment) this.receiver).setBinding((ViewDataBinding) obj);
    }
}
