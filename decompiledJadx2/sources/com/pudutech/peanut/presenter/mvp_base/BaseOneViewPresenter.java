package com.pudutech.peanut.presenter.mvp_base;

import android.os.Handler;
import android.os.Looper;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.pudutech.base.Pdlog;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.peanut.presenter.Behavior;
import com.pudutech.peanut.presenter.mvp_base.BaseViewInterface;
import com.pudutech.peanut.presenter.utils.cloner.cloning.Cloner;
import java.lang.ref.WeakReference;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.FilenameUtils;

/* compiled from: BaseOneViewPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\fH\u0002J\u0006\u0010\u001d\u001a\u00020\fJ\u0006\u0010\u001e\u001a\u00020\fJ\b\u0010\u001f\u001a\u00020\u0015H\u0002J\u0006\u0010 \u001a\u00020\fJ\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010%\u001a\u00020\fH\u0014J\b\u0010&\u001a\u00020\fH\u0014J\b\u0010'\u001a\u00020\fH\u0002J\u0006\u0010(\u001a\u00020\fJ\b\u0010)\u001a\u00020\fH\u0002J\u0015\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010,J\u0015\u0010-\u001a\u00020\f2\u0006\u0010+\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010,J\u0014\u0010.\u001a\u00020\f2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0014\u00100\u001a\u00020\f2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\u0004\u0018\u00018\u00008DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/mvp_base/BaseOneViewPresenter;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/peanut/presenter/mvp_base/BaseViewInterface;", "Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "cache", "Ljava/util/concurrent/LinkedBlockingQueue;", "Lkotlin/Function0;", "", "cloner", "Lcom/pudutech/peanut/presenter/utils/cloner/cloning/Cloner;", "getCloner", "()Lcom/pudutech/peanut/presenter/utils/cloner/cloning/Cloner;", "handlingEvent", "mView", "Ljava/lang/ref/WeakReference;", "needCache", "", "needHandleAgain", "theView", "getTheView", "()Lcom/pudutech/peanut/presenter/mvp_base/BaseViewInterface;", "uiHandler", "Landroid/os/Handler;", "attachLifecycle", "cacheForNextPage", "cacheForNextPageAndHandleAgain", "checkThreadLegal", "cleanCache", "onDestroy", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "onViewAttach", "onViewRemoved", "pollFromCache", "postCacheToUI", "removeLifecycle", "removeView", "view", "(Lcom/pudutech/peanut/presenter/mvp_base/BaseViewInterface;)V", "replaceView", "runOnBusinessThread", "method", "runOnUIThread", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class BaseOneViewPresenter<T extends BaseViewInterface> implements BasePresenterInterface {
    private Function0<Unit> handlingEvent;
    private WeakReference<T> mView;
    private boolean needCache;
    private boolean needHandleAgain;
    private final String TAG = "BaseOneViewPresenter";
    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    private LinkedBlockingQueue<Function0<Unit>> cache = new LinkedBlockingQueue<>();
    private final Cloner cloner = new Cloner();

    protected String getTAG() {
        return this.TAG;
    }

    private final boolean checkThreadLegal() {
        if (!(!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper()))) {
            return true;
        }
        Pdlog.m3274e(getTAG(), "need to be called in main thread !!!  my=" + Looper.myLooper() + "  main=" + Looper.getMainLooper());
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        for (int i = 3; i <= 8 && i < stackTrace.length; i++) {
            StackTraceElement stack = stackTrace[i];
            String tag = getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("called by ");
            Intrinsics.checkExpressionValueIsNotNull(stack, "stack");
            sb.append(stack.getClassName());
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            sb.append(stack.getMethodName());
            Pdlog.m3274e(tag, sb.toString());
            Pdlog.m3274e(getTAG(), "in " + stack.getFileName() + " line" + stack.getLineNumber());
        }
        return false;
    }

    public final void cacheForNextPage() {
        if (checkThreadLegal()) {
            this.needCache = true;
            Pdlog.m3273d(getTAG(), "cache for next page");
        }
    }

    public final void cacheForNextPageAndHandleAgain() {
        if (checkThreadLegal()) {
            this.needCache = true;
            this.needHandleAgain = true;
            Pdlog.m3275i(getTAG(), "cacheForNextPageAndHandleAgain");
        }
    }

    public final void cleanCache() {
        if (checkThreadLegal()) {
            this.cache.clear();
            this.needHandleAgain = false;
            this.needCache = false;
            Pdlog.m3273d(getTAG(), "clear cache");
        }
    }

    public final void postCacheToUI() {
        if (checkThreadLegal()) {
            Pdlog.m3273d(getTAG(), "postCacheToUI");
            this.needCache = false;
            pollFromCache();
        }
    }

    public final void runOnUIThread(Function0<Unit> method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        this.cache.offer(method);
        this.uiHandler.post(new Runnable() { // from class: com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter$runOnUIThread$1
            @Override // java.lang.Runnable
            public final void run() {
                BaseOneViewPresenter.this.pollFromCache();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pollFromCache() {
        if (this.needCache) {
            return;
        }
        if (!this.needHandleAgain) {
            this.handlingEvent = this.cache.poll();
        }
        this.needHandleAgain = false;
        Function0<Unit> function0 = this.handlingEvent;
        if (function0 != null) {
            if (function0 != null) {
                function0.invoke();
            }
            this.uiHandler.post(new Runnable() { // from class: com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter$pollFromCache$1
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOneViewPresenter.this.pollFromCache();
                }
            });
        }
    }

    public final void runOnBusinessThread(final Function0<Unit> method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Behavior.INSTANCE.runOnTaskThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter$runOnBusinessThread$1
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
    public final T getTheView() {
        WeakReference<T> weakReference = this.mView;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    protected final Cloner getCloner() {
        return this.cloner;
    }

    public void replaceView(T view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (checkThreadLegal()) {
            String tag = getTAG();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("replaceView view=");
            sb.append(view);
            sb.append(" when old=");
            WeakReference<T> weakReference = this.mView;
            sb.append(weakReference != null ? weakReference.get() : null);
            sb.append(" needCache=");
            sb.append(this.needCache);
            sb.append(" needHandleAgain=");
            sb.append(this.needHandleAgain);
            sb.append(" cache.size=");
            sb.append(this.cache.size());
            sb.append(' ');
            sb.append(this.handlingEvent);
            objArr[0] = sb.toString();
            Pdlog.m3275i(tag, objArr);
            WeakReference<T> weakReference2 = this.mView;
            if (Intrinsics.areEqual(view, weakReference2 != null ? weakReference2.get() : null)) {
                Pdlog.m3277w(getTAG(), "replaceView when new is the same one as old. then return");
                return;
            }
            removeLifecycle();
            this.mView = new WeakReference<>(view);
            attachLifecycle();
            this.needCache = false;
            this.uiHandler.post(new Runnable() { // from class: com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter$replaceView$1
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOneViewPresenter.this.pollFromCache();
                }
            });
        }
    }

    public void removeView(T view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (checkThreadLegal()) {
            Pdlog.m3275i(getTAG(), "remove view=" + view);
            WeakReference<T> weakReference = this.mView;
            if (Intrinsics.areEqual(weakReference != null ? weakReference.get() : null, view)) {
                removeLifecycle();
            }
            this.mView = (WeakReference) null;
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onStop LifecycleOwner=");
        sb.append(owner);
        sb.append("  mView=");
        WeakReference<T> weakReference = this.mView;
        sb.append(weakReference != null ? weakReference.get() : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(tag, objArr);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onDestroy LifecycleOwner=");
        sb.append(owner);
        sb.append("  mView=");
        WeakReference<T> weakReference = this.mView;
        sb.append(weakReference != null ? weakReference.get() : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(tag, objArr);
        WeakReference<T> weakReference2 = this.mView;
        if (Intrinsics.areEqual(owner, weakReference2 != null ? weakReference2.get() : null)) {
            Pdlog.m3275i(getTAG(), "onDestroy remove");
            removeLifecycle();
            this.mView = (WeakReference) null;
        }
    }

    private final void attachLifecycle() {
        T t;
        Lifecycle lifecycle;
        WeakReference<T> weakReference = this.mView;
        if (weakReference != null && (t = weakReference.get()) != null) {
            if (!(t instanceof FragmentActivity)) {
                t = (T) null;
            }
            FragmentActivity fragmentActivity = t;
            if (fragmentActivity != null && (lifecycle = fragmentActivity.getLifecycle()) != null) {
                lifecycle.addObserver(this);
            }
        }
        onViewAttach();
    }

    private final void removeLifecycle() {
        T t;
        Lifecycle lifecycle;
        WeakReference<T> weakReference = this.mView;
        if (weakReference != null && (t = weakReference.get()) != null) {
            if (!(t instanceof FragmentActivity)) {
                t = (T) null;
            }
            FragmentActivity fragmentActivity = t;
            if (fragmentActivity != null && (lifecycle = fragmentActivity.getLifecycle()) != null) {
                lifecycle.removeObserver(this);
            }
        }
        onViewRemoved();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onViewAttach() {
        Pdlog.m3273d(getTAG(), "onViewAttach");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onViewRemoved() {
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onViewRemoved view=");
        WeakReference<T> weakReference = this.mView;
        sb.append(weakReference != null ? weakReference.get() : null);
        sb.append("  thread=");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        objArr[0] = sb.toString();
        Pdlog.m3275i(tag, objArr);
    }
}
