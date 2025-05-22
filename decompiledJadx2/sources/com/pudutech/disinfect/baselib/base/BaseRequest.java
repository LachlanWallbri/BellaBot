package com.pudutech.disinfect.baselib.base;

import androidx.lifecycle.ViewModelKt;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR*\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/BaseRequest;", "", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "<set-?>", "Ljava/lang/ref/WeakReference;", "mVM", "getMVM", "()Ljava/lang/ref/WeakReference;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "onCleared", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public class BaseRequest {
    private final String TAG;
    private WeakReference<BaseViewModel> mVM;

    protected void onCleared() {
    }

    public BaseRequest(BaseViewModel VM) {
        Intrinsics.checkParameterIsNotNull(VM, "VM");
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
        VM.addClearEvent(new Function0<Unit>() { // from class: com.pudutech.disinfect.baselib.base.BaseRequest.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BaseRequest.this.onCleared();
            }
        });
        this.mVM = new WeakReference<>(VM);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final WeakReference<BaseViewModel> getMVM() {
        return this.mVM;
    }

    public final CoroutineScope getViewModelScope() {
        BaseViewModel baseViewModel = this.mVM.get();
        if (baseViewModel != null) {
            return ViewModelKt.getViewModelScope(baseViewModel);
        }
        return null;
    }
}
