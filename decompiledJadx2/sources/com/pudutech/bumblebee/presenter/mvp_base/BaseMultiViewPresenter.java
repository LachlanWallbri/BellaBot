package com.pudutech.bumblebee.presenter.mvp_base;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseMultiViewPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0017\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u0015\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0017\u0010\u001b\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u0010\u0015J\u0014\u0010\u001c\u001a\u00020\u00132\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u001eJ\u0014\u0010\u001f\u001a\u00020\u00132\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u001eR\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR&\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/mvp_base/BaseMultiViewPresenter;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mViews", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Ljava/lang/ref/WeakReference;", "getMViews", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setMViews", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "uiHandler", "Landroid/os/Handler;", "attachLifecycle", "", "view", "(Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;)V", "attachView", "detachView", "onStop", "owner", "Landroidx/lifecycle/LifecycleOwner;", "removeLifecycle", "runOnBusinessThread", "method", "Lkotlin/Function0;", "runOnUIThread", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class BaseMultiViewPresenter<T extends BaseViewInterface> implements BasePresenterInterface {
    private final String TAG = "BasePresenter";
    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    private CopyOnWriteArrayList<WeakReference<T>> mViews = new CopyOnWriteArrayList<>();

    protected String getTAG() {
        return this.TAG;
    }

    public final void runOnUIThread(final Function0<Unit> method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Thread currentThread = Thread.currentThread();
        Looper mainLooper = Looper.getMainLooper();
        Intrinsics.checkExpressionValueIsNotNull(mainLooper, "Looper.getMainLooper()");
        if (currentThread != mainLooper.getThread()) {
            this.uiHandler.post(new Runnable() { // from class: com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter$sam$java_lang_Runnable$0
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
                }
            });
        } else {
            method.invoke();
        }
    }

    public final void runOnBusinessThread(final Function0<Unit> method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Behavior.INSTANCE.runOnTaskThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter$runOnBusinessThread$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                Function0.this.invoke();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CopyOnWriteArrayList<WeakReference<T>> getMViews() {
        return this.mViews;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMViews(CopyOnWriteArrayList<WeakReference<T>> copyOnWriteArrayList) {
        Intrinsics.checkParameterIsNotNull(copyOnWriteArrayList, "<set-?>");
        this.mViews = copyOnWriteArrayList;
    }

    public void attachView(T view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Log.i(getTAG(), "attachView view=" + view);
        Iterator<T> it = this.mViews.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (Intrinsics.areEqual((BaseViewInterface) ((WeakReference) it.next()).get(), view)) {
                z = true;
            }
        }
        if (!z) {
            attachLifecycle(view);
            this.mViews.add(new WeakReference<>(view));
        }
        Log.i(getTAG(), "view size=" + this.mViews.size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void detachView(final T view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Pdlog.m3275i(getTAG(), "detach view=" + view + ' ');
        Iterator<T> it = this.mViews.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (Intrinsics.areEqual((BaseViewInterface) weakReference.get(), view)) {
                removeLifecycle((BaseViewInterface) weakReference.get());
            }
        }
        this.mViews.removeIf(new Predicate<WeakReference<T>>() { // from class: com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter$detachView$2
            @Override // java.util.function.Predicate
            public final boolean test(WeakReference<T> weakReference2) {
                return Intrinsics.areEqual((BaseViewInterface) weakReference2.get(), BaseViewInterface.this) || weakReference2.get() == null;
            }
        });
        Log.i(getTAG(), "view size=" + this.mViews.size());
    }

    private final void attachLifecycle(T view) {
        Lifecycle lifecycle;
        if (view != null) {
            boolean z = view instanceof FragmentActivity;
            Object obj = view;
            if (!z) {
                obj = (T) null;
            }
            FragmentActivity fragmentActivity = (FragmentActivity) obj;
            if (fragmentActivity == null || (lifecycle = fragmentActivity.getLifecycle()) == null) {
                return;
            }
            lifecycle.addObserver(this);
        }
    }

    private final void removeLifecycle(T view) {
        Lifecycle lifecycle;
        if (view != null) {
            boolean z = view instanceof FragmentActivity;
            Object obj = view;
            if (!z) {
                obj = (T) null;
            }
            FragmentActivity fragmentActivity = (FragmentActivity) obj;
            if (fragmentActivity == null || (lifecycle = fragmentActivity.getLifecycle()) == null) {
                return;
            }
            lifecycle.removeObserver(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(final LifecycleOwner owner) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Pdlog.m3275i(getTAG(), "onStop owner=" + owner);
        Iterator<T> it = this.mViews.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (Intrinsics.areEqual((BaseViewInterface) weakReference.get(), owner)) {
                removeLifecycle((BaseViewInterface) weakReference.get());
            }
        }
        this.mViews.removeIf(new Predicate<WeakReference<T>>() { // from class: com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter$onStop$2
            @Override // java.util.function.Predicate
            public final boolean test(WeakReference<T> weakReference2) {
                return Intrinsics.areEqual((BaseViewInterface) weakReference2.get(), LifecycleOwner.this) || weakReference2.get() == null;
            }
        });
        Log.i(getTAG(), "view size=" + this.mViews.size());
    }
}
