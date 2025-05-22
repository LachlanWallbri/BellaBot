package com.pudutech.disinfect.baselib.base.viewmodel.ext;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.disinfect.baselib.base.activity.BaseVmActivity;
import com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import com.pudutech.disinfect.baselib.state.AppException;
import com.pudutech.disinfect.baselib.state.ResultState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseViewModelExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aT\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042-\u0010\u0005\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001at\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000e\u001a\u00020\u00072\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00102\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001al\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u00022\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00102\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001at\u0010\u0017\u001a\u00020\r\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000e\u001a\u00020\u00072\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00102\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a`\u0010\u0018\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00102\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00102\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001f\u001a`\u0010\u0018\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030 2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00102\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00102\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001f\u001aH\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\"2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001f2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\u0010\u001az\u0010$\u001a\u00020\r\"\u0004\b\u0000\u0010\u0002*\u00020\"2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00102\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0002\u0010(\u001ad\u0010$\u001a\u00020\r\"\u0004\b\u0000\u0010\u0002*\u00020\"2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00102\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001b0)2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0002\u0010*\u001av\u0010+\u001a\u00020\r\"\u0004\b\u0000\u0010\u0002*\u00020\"2\u001c\u0010\u000f\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00102\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0002\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, m3961d2 = {"executeResponse", "", ExifInterface.GPS_DIRECTION_TRUE, "response", "Lcom/pudutech/disinfect/baselib/network/BaseResponse;", "success", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lcom/pudutech/disinfect/baselib/network/BaseResponse;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestNetworkData", "Lkotlinx/coroutines/Job;", "scope", "block", "Lkotlin/Function1;", "error", "Lcom/pudutech/disinfect/baselib/state/AppException;", AIUIConstant.KEY_TAG, "", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)Lkotlinx/coroutines/Job;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)Lkotlinx/coroutines/Job;", "requestNetworkDataCallMain", "handleResultState", "Lcom/pudutech/disinfect/baselib/base/activity/BaseVmActivity;", "resultState", "Lcom/pudutech/disinfect/baselib/state/ResultState;", "onSuccess", "onError", "onLoading", "Lkotlin/Function0;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseVmFragment;", "launch", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "", "request", "isShowDialog", "", "loadingMessage", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLjava/lang/String;)Lkotlinx/coroutines/Job;", "Landroidx/lifecycle/MutableLiveData;", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;Lkotlin/jvm/functions/Function1;Landroidx/lifecycle/MutableLiveData;ZLjava/lang/String;)Lkotlinx/coroutines/Job;", "requestSkipCheck", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class BaseViewModelExtKt {
    public static /* synthetic */ void handleResultState$default(BaseVmActivity baseVmActivity, ResultState resultState, Function1 function1, Function1 function12, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = (Function1) null;
        }
        if ((i & 8) != 0) {
            function0 = (Function0) null;
        }
        handleResultState((BaseVmActivity<?>) baseVmActivity, resultState, function1, (Function1<? super AppException, Unit>) function12, (Function0<Unit>) function0);
    }

    public static final <T> void handleResultState(BaseVmActivity<?> handleResultState, ResultState<? extends T> resultState, Function1<? super T, Unit> onSuccess, Function1<? super AppException, Unit> function1, Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(handleResultState, "$this$handleResultState");
        Intrinsics.checkParameterIsNotNull(resultState, "resultState");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        if (resultState instanceof ResultState.Loading) {
            handleResultState.showLoading(((ResultState.Loading) resultState).getLoadingMessage());
            return;
        }
        if (resultState instanceof ResultState.Success) {
            handleResultState.dismissLoading();
            onSuccess.invoke((Object) ((ResultState.Success) resultState).getData());
        } else if (resultState instanceof ResultState.Error) {
            handleResultState.dismissLoading();
            if (function1 != null) {
                function1.invoke(((ResultState.Error) resultState).getError());
            }
        }
    }

    public static /* synthetic */ void handleResultState$default(BaseVmFragment baseVmFragment, ResultState resultState, Function1 function1, Function1 function12, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = (Function1) null;
        }
        if ((i & 8) != 0) {
            function0 = (Function0) null;
        }
        handleResultState((BaseVmFragment<?>) baseVmFragment, resultState, function1, (Function1<? super AppException, Unit>) function12, (Function0<Unit>) function0);
    }

    public static final <T> void handleResultState(BaseVmFragment<?> handleResultState, ResultState<? extends T> resultState, Function1<? super T, Unit> onSuccess, Function1<? super AppException, Unit> function1, Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(handleResultState, "$this$handleResultState");
        Intrinsics.checkParameterIsNotNull(resultState, "resultState");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        if (resultState instanceof ResultState.Loading) {
            handleResultState.showLoading(((ResultState.Loading) resultState).getLoadingMessage());
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        if (resultState instanceof ResultState.Success) {
            handleResultState.dismissLoading();
            onSuccess.invoke((Object) ((ResultState.Success) resultState).getData());
        } else if (resultState instanceof ResultState.Error) {
            handleResultState.dismissLoading();
            if (function1 != null) {
                function1.invoke(((ResultState.Error) resultState).getError());
            }
        }
    }

    public static /* synthetic */ Job request$default(BaseViewModel baseViewModel, Function1 function1, MutableLiveData mutableLiveData, boolean z, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            str = "请求网络中....";
        }
        return request(baseViewModel, function1, mutableLiveData, z, str);
    }

    public static final <T> Job request(BaseViewModel request, Function1<? super Continuation<? super BaseResponse<T>>, ? extends Object> block, MutableLiveData<ResultState<T>> resultState, boolean z, String loadingMessage) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(request, "$this$request");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(resultState, "resultState");
        Intrinsics.checkParameterIsNotNull(loadingMessage, "loadingMessage");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(request), null, null, new BaseViewModelExtKt$request$1(z, resultState, loadingMessage, block, null), 3, null);
        return launch$default;
    }

    public static /* synthetic */ Job request$default(BaseViewModel baseViewModel, Function1 function1, Function1 function12, Function1 function13, boolean z, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            function13 = new Function1<AppException, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$request$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        Function1 function14 = function13;
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            str = "请求网络中...";
        }
        return request(baseViewModel, function1, function12, function14, z2, str);
    }

    public static final <T> Job request(BaseViewModel request, Function1<? super Continuation<? super BaseResponse<T>>, ? extends Object> block, Function1<? super T, Unit> success, Function1<? super AppException, Unit> error, boolean z, String loadingMessage) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(request, "$this$request");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(loadingMessage, "loadingMessage");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(request), null, null, new BaseViewModelExtKt$request$3(request, z, loadingMessage, block, success, error, null), 3, null);
        return launch$default;
    }

    public static /* synthetic */ Job requestSkipCheck$default(BaseViewModel baseViewModel, Function1 function1, Function1 function12, Function1 function13, boolean z, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            function12 = new Function1<T, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestSkipCheck$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj2) {
                    invoke2((BaseViewModelExtKt$requestSkipCheck$1<T>) obj2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(T t) {
                }
            };
        }
        Function1 function14 = function12;
        if ((i & 4) != 0) {
            function13 = new Function1<AppException, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestSkipCheck$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        Function1 function15 = function13;
        if ((i & 8) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            str = "请求网络中...";
        }
        return requestSkipCheck(baseViewModel, function1, function14, function15, z2, str);
    }

    public static final <T> Job requestSkipCheck(BaseViewModel requestSkipCheck, Function1<? super Continuation<? super T>, ? extends Object> block, Function1<? super T, Unit> success, Function1<? super AppException, Unit> error, boolean z, String loadingMessage) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(requestSkipCheck, "$this$requestSkipCheck");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(loadingMessage, "loadingMessage");
        if (z) {
            requestSkipCheck.getLoadingChange().getShowDialog().postValue(loadingMessage);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(requestSkipCheck), null, null, new BaseViewModelExtKt$requestSkipCheck$3(requestSkipCheck, block, success, error, null), 3, null);
        return launch$default;
    }

    public static final <T> Object executeResponse(BaseResponse<T> baseResponse, Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new BaseViewModelExtKt$executeResponse$2(baseResponse, function3, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    public static /* synthetic */ void launch$default(BaseViewModel baseViewModel, Function0 function0, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = new Function1<Throwable, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$launch$1
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
        launch(baseViewModel, function0, function1, function12);
    }

    public static final <T> void launch(BaseViewModel launch, Function0<? extends T> block, Function1<? super T, Unit> success, Function1<? super Throwable, Unit> error) {
        Intrinsics.checkParameterIsNotNull(launch, "$this$launch");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(error, "error");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(launch), null, null, new BaseViewModelExtKt$launch$2(block, success, error, null), 3, null);
    }

    public static /* synthetic */ Job requestNetworkData$default(Function1 function1, Function1 function12, Function1 function13, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            function13 = new Function1<AppException, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestNetworkData$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        if ((i & 8) != 0) {
            str = "requestNetworkData";
        }
        return requestNetworkData(function1, function12, function13, str);
    }

    public static final <T> Job requestNetworkData(Function1<? super Continuation<? super BaseResponse<T>>, ? extends Object> block, Function1<? super T, Unit> success, Function1<? super AppException, Unit> error, String tag) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return requestNetworkData(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), block, success, error, tag);
    }

    public static /* synthetic */ Job requestNetworkData$default(CoroutineScope coroutineScope, Function1 function1, Function1 function12, Function1 function13, String str, int i, Object obj) {
        if ((i & 8) != 0) {
            function13 = new Function1<AppException, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestNetworkData$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        if ((i & 16) != 0) {
            str = "requestNetworkData";
        }
        return requestNetworkData(coroutineScope, function1, function12, function13, str);
    }

    public static final <T> Job requestNetworkData(CoroutineScope scope, Function1<? super Continuation<? super BaseResponse<T>>, ? extends Object> block, Function1<? super T, Unit> success, Function1<? super AppException, Unit> error, String tag) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new BaseViewModelExtKt$requestNetworkData$3(tag, block, success, error, null), 3, null);
        return launch$default;
    }

    public static /* synthetic */ Job requestNetworkDataCallMain$default(CoroutineScope coroutineScope, Function1 function1, Function1 function12, Function1 function13, String str, int i, Object obj) {
        if ((i & 8) != 0) {
            function13 = new Function1<AppException, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestNetworkDataCallMain$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        if ((i & 16) != 0) {
            str = "requestNetworkDataCallMain";
        }
        return requestNetworkDataCallMain(coroutineScope, function1, function12, function13, str);
    }

    public static final <T> Job requestNetworkDataCallMain(CoroutineScope scope, Function1<? super Continuation<? super BaseResponse<T>>, ? extends Object> block, Function1<? super T, Unit> success, Function1<? super AppException, Unit> error, String tag) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new BaseViewModelExtKt$requestNetworkDataCallMain$2(tag, block, success, error, null), 3, null);
        return launch$default;
    }
}
