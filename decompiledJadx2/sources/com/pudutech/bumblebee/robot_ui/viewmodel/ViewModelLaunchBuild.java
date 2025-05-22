package com.pudutech.bumblebee.robot_ui.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VMExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J7\u0010\u0003\u001a\u00020\u00072'\u0010\u001b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\b\bø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0014\u0010\u000e\u001a\u00020\u00072\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fJ\u001a\u0010\u0014\u001a\u00020\u00072\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070\u0015RB\u0010\u0003\u001a%\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004¢\u0006\u0002\b\bX\u0080\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R&\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/ViewModelLaunchBuild;", "", "()V", "job", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "getJob$robot_ui_robotRelease", "()Lkotlin/jvm/functions/Function2;", "setJob$robot_ui_robotRelease", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "onComplete", "Lkotlin/Function0;", "getOnComplete$robot_ui_robotRelease", "()Lkotlin/jvm/functions/Function0;", "setOnComplete$robot_ui_robotRelease", "(Lkotlin/jvm/functions/Function0;)V", "onError", "Lkotlin/Function1;", "", "getOnError$robot_ui_robotRelease", "()Lkotlin/jvm/functions/Function1;", "setOnError$robot_ui_robotRelease", "(Lkotlin/jvm/functions/Function1;)V", "block", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ViewModelLaunchBuild {
    private Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> job;
    private Function1<? super Throwable, Unit> onError = new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.ViewModelLaunchBuild$onError$1
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }
    };
    private Function0<Unit> onComplete = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.ViewModelLaunchBuild$onComplete$1
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }
    };

    public final Function2<CoroutineScope, Continuation<? super Unit>, Object> getJob$robot_ui_robotRelease() {
        return this.job;
    }

    public final void setJob$robot_ui_robotRelease(Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.job = function2;
    }

    public final Function1<Throwable, Unit> getOnError$robot_ui_robotRelease() {
        return this.onError;
    }

    public final void setOnError$robot_ui_robotRelease(Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.onError = function1;
    }

    public final Function0<Unit> getOnComplete$robot_ui_robotRelease() {
        return this.onComplete;
    }

    public final void setOnComplete$robot_ui_robotRelease(Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "<set-?>");
        this.onComplete = function0;
    }

    public final void job(Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.job = block;
    }

    public final void onError(Function1<? super Throwable, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.onError = block;
    }

    public final void onComplete(Function0<Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.onComplete = block;
    }
}
