package com.pudutech.module_robot_selfcheck.domain.request;

import androidx.collection.ArrayMap;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: SelfCheckRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012H\u0010\u0002\u001aD\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005 \t*!\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b0\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0002\b\n"}, m3961d2 = {"<anonymous>", "", "p1", "Landroidx/collection/ArrayMap;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class SelfCheckRequest$onSelfCheckListener$1 extends FunctionReference implements Function1<ArrayMap<InitStep, String>, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SelfCheckRequest$onSelfCheckListener$1(MutableLiveData mutableLiveData) {
        super(1, mutableLiveData);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "postValue";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(MutableLiveData.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "postValue(Ljava/lang/Object;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ArrayMap<InitStep, String> arrayMap) {
        invoke2(arrayMap);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(ArrayMap<InitStep, String> arrayMap) {
        ((MutableLiveData) this.receiver).postValue(arrayMap);
    }
}
