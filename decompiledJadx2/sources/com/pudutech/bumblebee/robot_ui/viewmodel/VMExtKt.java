package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: VMExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001az\u0010\u0000\u001a\u00020\u0001*\u00020\u00022'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\t2#\b\u0002\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00070\u000b2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001az\u0010\u0000\u001a\u00020\u0001*\u00020\u00152'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\t2#\b\u0002\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00070\u000b2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a#\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\t\u001a#\u0010\u0017\u001a\u00020\u0001*\u00020\u00152\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\t\u001a#\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\t\u001a#\u0010\u0019\u001a\u00020\u0001*\u00020\u00152\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\t\u001a\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u001b\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, m3961d2 = {"launch", "Lkotlinx/coroutines/Job;", "Landroidx/lifecycle/ViewModel;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "onError", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", C3898x.f4338g, "onComplete", "Lkotlin/Function0;", "coroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Landroidx/lifecycle/ViewModel;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CoroutineDispatcher;)Lkotlinx/coroutines/Job;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BaseFVM;", "(Lcom/pudutech/bumblebee/robot_ui/viewmodel/BaseFVM;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CoroutineDispatcher;)Lkotlinx/coroutines/Job;", "launchIO", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/ViewModelLaunchBuild;", "launchMain", "toLiveData", "Landroidx/lifecycle/LiveData;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/MutableLiveData;", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VMExtKt {
    public static final Job launchIO(ViewModel launchIO, Function1<? super ViewModelLaunchBuild, Unit> block) {
        Intrinsics.checkParameterIsNotNull(launchIO, "$this$launchIO");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ViewModelLaunchBuild viewModelLaunchBuild = new ViewModelLaunchBuild();
        block.invoke(viewModelLaunchBuild);
        if (viewModelLaunchBuild.getJob$robot_ui_robotRelease() == null) {
            throw new RuntimeException("doJob 不能为空");
        }
        Function2<CoroutineScope, Continuation<? super Unit>, Object> job$robot_ui_robotRelease = viewModelLaunchBuild.getJob$robot_ui_robotRelease();
        if (job$robot_ui_robotRelease == null) {
            Intrinsics.throwNpe();
        }
        return launch$default(launchIO, job$robot_ui_robotRelease, viewModelLaunchBuild.getOnError$robot_ui_robotRelease(), viewModelLaunchBuild.getOnComplete$robot_ui_robotRelease(), (CoroutineDispatcher) null, 8, (Object) null);
    }

    public static final Job launchMain(ViewModel launchMain, Function1<? super ViewModelLaunchBuild, Unit> block) {
        Intrinsics.checkParameterIsNotNull(launchMain, "$this$launchMain");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ViewModelLaunchBuild viewModelLaunchBuild = new ViewModelLaunchBuild();
        block.invoke(viewModelLaunchBuild);
        if (viewModelLaunchBuild.getJob$robot_ui_robotRelease() == null) {
            throw new RuntimeException("doJob 不能为空");
        }
        Function2<CoroutineScope, Continuation<? super Unit>, Object> job$robot_ui_robotRelease = viewModelLaunchBuild.getJob$robot_ui_robotRelease();
        if (job$robot_ui_robotRelease == null) {
            Intrinsics.throwNpe();
        }
        return launch(launchMain, job$robot_ui_robotRelease, viewModelLaunchBuild.getOnError$robot_ui_robotRelease(), viewModelLaunchBuild.getOnComplete$robot_ui_robotRelease(), Dispatchers.getMain());
    }

    public static final <T> LiveData<T> toLiveData(MutableLiveData<T> toLiveData) {
        Intrinsics.checkParameterIsNotNull(toLiveData, "$this$toLiveData");
        return toLiveData;
    }

    public static /* synthetic */ Job launch$default(ViewModel viewModel, Function2 function2, Function1 function1, Function0 function0, CoroutineDispatcher coroutineDispatcher, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.VMExtKt$launch$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.VMExtKt$launch$2
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        if ((i & 8) != 0) {
            coroutineDispatcher = Dispatchers.getIO();
        }
        return launch(viewModel, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) function2, (Function1<? super Throwable, Unit>) function1, (Function0<Unit>) function0, coroutineDispatcher);
    }

    public static final Job launch(ViewModel launch, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, CoroutineDispatcher coroutineDispatcher) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(launch, "$this$launch");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "coroutineDispatcher");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(launch), coroutineDispatcher.plus(new VMExtKt$launch$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, launch, onError)), null, new VMExtKt$launch$4(block, onComplete, null), 2, null);
        return launch$default;
    }

    public static final Job launchIO(BaseFVM launchIO, Function1<? super ViewModelLaunchBuild, Unit> block) {
        Intrinsics.checkParameterIsNotNull(launchIO, "$this$launchIO");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ViewModelLaunchBuild viewModelLaunchBuild = new ViewModelLaunchBuild();
        block.invoke(viewModelLaunchBuild);
        if (viewModelLaunchBuild.getJob$robot_ui_robotRelease() == null) {
            throw new RuntimeException("doJob 不能为空");
        }
        Function2<CoroutineScope, Continuation<? super Unit>, Object> job$robot_ui_robotRelease = viewModelLaunchBuild.getJob$robot_ui_robotRelease();
        if (job$robot_ui_robotRelease == null) {
            Intrinsics.throwNpe();
        }
        return launch$default(launchIO, job$robot_ui_robotRelease, viewModelLaunchBuild.getOnError$robot_ui_robotRelease(), viewModelLaunchBuild.getOnComplete$robot_ui_robotRelease(), (CoroutineDispatcher) null, 8, (Object) null);
    }

    public static final Job launchMain(BaseFVM launchMain, Function1<? super ViewModelLaunchBuild, Unit> block) {
        Intrinsics.checkParameterIsNotNull(launchMain, "$this$launchMain");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ViewModelLaunchBuild viewModelLaunchBuild = new ViewModelLaunchBuild();
        block.invoke(viewModelLaunchBuild);
        if (viewModelLaunchBuild.getJob$robot_ui_robotRelease() == null) {
            throw new RuntimeException("doJob 不能为空");
        }
        Function2<CoroutineScope, Continuation<? super Unit>, Object> job$robot_ui_robotRelease = viewModelLaunchBuild.getJob$robot_ui_robotRelease();
        if (job$robot_ui_robotRelease == null) {
            Intrinsics.throwNpe();
        }
        return launch(launchMain, job$robot_ui_robotRelease, viewModelLaunchBuild.getOnError$robot_ui_robotRelease(), viewModelLaunchBuild.getOnComplete$robot_ui_robotRelease(), Dispatchers.getMain());
    }

    public static /* synthetic */ Job launch$default(BaseFVM baseFVM, Function2 function2, Function1 function1, Function0 function0, CoroutineDispatcher coroutineDispatcher, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.VMExtKt$launch$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.VMExtKt$launch$6
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        if ((i & 8) != 0) {
            coroutineDispatcher = Dispatchers.getIO();
        }
        return launch(baseFVM, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) function2, (Function1<? super Throwable, Unit>) function1, (Function0<Unit>) function0, coroutineDispatcher);
    }

    public static final Job launch(BaseFVM launch, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, CoroutineDispatcher coroutineDispatcher) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(launch, "$this$launch");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "coroutineDispatcher");
        launch$default = BuildersKt__Builders_commonKt.launch$default(launch.getScope(), coroutineDispatcher, null, new VMExtKt$launch$7(block, onComplete, onError, null), 2, null);
        return launch$default;
    }
}
