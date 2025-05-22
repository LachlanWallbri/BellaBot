package com.pudutech.disinfect.baselib.base.viewmodel;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.callback.livedata.event.EventLiveData;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import com.pudutech.disinfect.baselib.state.AppException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
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
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000e\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJT\u0010\u0010\u001a\u00020\f\"\u0004\b\u0000\u0010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00132-\u0010\u0014\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0015¢\u0006\u0002\b\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJT\u0010\u001b\u001a\u00020\f\"\u0004\b\u0000\u0010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00132-\u0010\u0014\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0015¢\u0006\u0002\b\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\b\u0010\u001d\u001a\u00020\fH\u0014Jb\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u00112\"\u0010 \u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00130\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180!2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\f0!2\u0014\b\u0002\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\f0!ø\u0001\u0000¢\u0006\u0002\u0010$Jb\u0010%\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u00112\"\u0010 \u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00130\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180!2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\f0!2\u0014\b\u0002\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\f0!ø\u0001\u0000¢\u0006\u0002\u0010$R\u001f\u0010\u0003\u001a\u00060\u0004R\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R*\u0010\t\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b`\rX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "loadingChange", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel$UiLoadingChange;", "getLoadingChange", "()Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel$UiLoadingChange;", "loadingChange$delegate", "Lkotlin/Lazy;", "onClearEvent", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "addClearEvent", "l", "executeResponse", ExifInterface.GPS_DIRECTION_TRUE, "response", "Lcom/pudutech/disinfect/baselib/network/BaseResponse;", "success", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lcom/pudutech/disinfect/baselib/network/BaseResponse;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeResponseSelfCheck", "", "onCleared", "request", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function1;", "error", "Lcom/pudutech/disinfect/baselib/state/AppException;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "requestSelfCheck", "UiLoadingChange", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public class BaseViewModel extends ViewModel {

    /* renamed from: loadingChange$delegate, reason: from kotlin metadata */
    private final Lazy loadingChange = LazyKt.lazy(new Function0<UiLoadingChange>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel$loadingChange$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseViewModel.UiLoadingChange invoke() {
            return new BaseViewModel.UiLoadingChange();
        }
    });
    private final ArrayList<Function0<Unit>> onClearEvent = new ArrayList<>();

    public final UiLoadingChange getLoadingChange() {
        return (UiLoadingChange) this.loadingChange.getValue();
    }

    public final void addClearEvent(Function0<Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.onClearEvent.add(l);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: BaseViewModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\t\u001a\u0004\b\f\u0010\u0007¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel$UiLoadingChange;", "", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;)V", "dismissDialog", "Lcom/pudutech/disinfect/baselib/callback/livedata/event/EventLiveData;", "", "getDismissDialog", "()Lcom/pudutech/disinfect/baselib/callback/livedata/event/EventLiveData;", "dismissDialog$delegate", "Lkotlin/Lazy;", "showDialog", "", "getShowDialog", "showDialog$delegate", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final class UiLoadingChange {

        /* renamed from: showDialog$delegate, reason: from kotlin metadata */
        private final Lazy showDialog = LazyKt.lazy(new Function0<EventLiveData<String>>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel$UiLoadingChange$showDialog$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final EventLiveData<String> invoke() {
                return new EventLiveData<>();
            }
        });

        /* renamed from: dismissDialog$delegate, reason: from kotlin metadata */
        private final Lazy dismissDialog = LazyKt.lazy(new Function0<EventLiveData<Boolean>>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel$UiLoadingChange$dismissDialog$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final EventLiveData<Boolean> invoke() {
                return new EventLiveData<>();
            }
        });

        public final EventLiveData<Boolean> getDismissDialog() {
            return (EventLiveData) this.dismissDialog.getValue();
        }

        public final EventLiveData<String> getShowDialog() {
            return (EventLiveData) this.showDialog.getValue();
        }

        public UiLoadingChange() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Job request$default(BaseViewModel baseViewModel, Function1 function1, Function1 function12, Function1 function13, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: request");
        }
        if ((i & 4) != 0) {
            function13 = new Function1<AppException, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel$request$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }
            };
        }
        return baseViewModel.request(function1, function12, function13);
    }

    public final <T> Job request(Function1<? super Continuation<? super BaseResponse<T>>, ? extends Object> block, Function1<? super T, Unit> success, Function1<? super AppException, Unit> error) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(error, "error");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new BaseViewModel$request$2(this, block, success, error, null), 3, null);
        return launch$default;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Job requestSelfCheck$default(BaseViewModel baseViewModel, Function1 function1, Function1 function12, Function1 function13, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestSelfCheck");
        }
        if ((i & 4) != 0) {
            function13 = new Function1<AppException, Unit>() { // from class: com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel$requestSelfCheck$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }
            };
        }
        return baseViewModel.requestSelfCheck(function1, function12, function13);
    }

    public final <T> Job requestSelfCheck(Function1<? super Continuation<? super BaseResponse<T>>, ? extends Object> block, Function1<? super String, Unit> success, Function1<? super AppException, Unit> error) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(block, "block");
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(error, "error");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new BaseViewModel$requestSelfCheck$2(this, block, success, error, null), 3, null);
        return launch$default;
    }

    public final <T> Object executeResponseSelfCheck(BaseResponse<T> baseResponse, Function3<? super CoroutineScope, ? super String, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new BaseViewModel$executeResponseSelfCheck$2(baseResponse, function3, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    public final <T> Object executeResponse(BaseResponse<T> baseResponse, Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new BaseViewModel$executeResponse$2(baseResponse, function3, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        Iterator<T> it = this.onClearEvent.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
        this.onClearEvent.clear();
    }
}
