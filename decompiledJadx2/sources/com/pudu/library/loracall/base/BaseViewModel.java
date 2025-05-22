package com.pudu.library.loracall.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: BaseViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0014J\u0006\u0010\u0017\u001a\u00020\u0015R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, m3961d2 = {"Lcom/pudu/library/loracall/base/BaseViewModel;", "Ljava/io/Serializable;", "Landroidx/lifecycle/ViewModel;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "handler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "loading", "Landroidx/lifecycle/MutableLiveData;", "", "getLoading", "()Landroidx/lifecycle/MutableLiveData;", "parentJob", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "closeLoading", "", "onCleared", "showLoading", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class BaseViewModel extends ViewModel implements Serializable {
    private final CoroutineExceptionHandler handler = new BaseViewModel$$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE);
    private final CoroutineContext parentJob = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(this.handler);
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(getCoroutineContext());
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private final CoroutineContext getCoroutineContext() {
        return this.parentJob.plus(Dispatchers.getIO());
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
    }

    public final MutableLiveData<Boolean> getLoading() {
        return this.loading;
    }

    public final void closeLoading() {
        this.loading.postValue(false);
    }

    public final void showLoading() {
        this.loading.postValue(true);
    }
}
