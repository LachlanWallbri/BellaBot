package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Async.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\u0001\u001a;\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n0\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\u0001H\u0007¢\u0006\u0002\b\u000b\u001a9\u0010\f\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000f\u001aF\u0010\f\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n0\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\b\u0010\u001aZ\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012\"\u0004\b\u0000\u0010\u0006*\u0002H\u00062\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u001d\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u000f¢\u0006\u0002\u0010\u0017\u001aR\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012\"\u0004\b\u0000\u0010\u0006*\u0002H\u00062\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u001d\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u000f¢\u0006\u0002\u0010\u0018\u001a`\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0012\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u001a*\u0002H\u00062\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u001d\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b\u0012\u0004\u0012\u0002H\u001a0\u0001¢\u0006\u0002\b\u000f¢\u0006\u0002\u0010\u0017\u001aX\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0012\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u001a*\u0002H\u00062\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u001d\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b\u0012\u0004\u0012\u0002H\u001a0\u0001¢\u0006\u0002\b\u000f¢\u0006\u0002\u0010\u0018\u001a.\u0010\u001b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u001c*\b\u0012\u0004\u0012\u0002H\u00060\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\u0001\u001a9\u0010\u001d\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u001c*\b\u0012\u0004\u0012\u0002H\u00060\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000f\u001a,\u0010\u001e\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\b2\u0014\u0010\t\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0006\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u001d\u0010\u001f\u001a\u00020\u0003*\u00020\u001c2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030 H\u0086\b\u001a#\u0010\u001f\u001a\u00020\u0003*\u00020\u000e2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u000f\u001a*\u0010!\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\u0001\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"crashLogger", "Lkotlin/Function1;", "", "", "activityUiThread", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/app/Activity;", "Lorg/jetbrains/anko/AnkoAsyncContext;", C3898x.f4339h, "Lorg/jetbrains/anko/AnkoContext;", "activityContextUiThread", "activityUiThreadWithContext", "Lkotlin/Function2;", "Landroid/content/Context;", "Lkotlin/ExtensionFunctionType;", "activityContextUiThreadWithContext", "doAsync", "Ljava/util/concurrent/Future;", "exceptionHandler", "executorService", "Ljava/util/concurrent/ExecutorService;", "task", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Ljava/util/concurrent/ExecutorService;Lkotlin/jvm/functions/Function1;)Ljava/util/concurrent/Future;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/concurrent/Future;", "doAsyncResult", "R", "fragmentUiThread", "Landroid/app/Fragment;", "fragmentUiThreadWithContext", "onComplete", "runOnUiThread", "Lkotlin/Function0;", "uiThread", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AsyncKt {
    private static final Function1<Throwable, Unit> crashLogger = new Function1<Throwable, Unit>() { // from class: org.jetbrains.anko.AsyncKt$crashLogger$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable throwable) {
            Intrinsics.checkParameterIsNotNull(throwable, "throwable");
            throwable.printStackTrace();
        }
    };

    public static final void runOnUiThread(final Context receiver, final Function1<? super Context, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (Intrinsics.areEqual(ContextHelper.INSTANCE.getMainThread(), Thread.currentThread())) {
            f.invoke(receiver);
        } else {
            ContextHelper.INSTANCE.getHandler().post(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$runOnUiThread$1
                @Override // java.lang.Runnable
                public final void run() {
                    f.invoke(receiver);
                }
            });
        }
    }

    public static final void runOnUiThread(Fragment receiver, Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Activity activity = receiver.getActivity();
        if (activity != null) {
            activity.runOnUiThread(new AsyncKt$runOnUiThread$2(f));
        }
    }

    public static final <T> void onComplete(AnkoAsyncContext<T> receiver, final Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        final T t = receiver.getWeakRef().get();
        if (Intrinsics.areEqual(ContextHelper.INSTANCE.getMainThread(), Thread.currentThread())) {
            f.invoke(t);
        } else {
            ContextHelper.INSTANCE.getHandler().post(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$onComplete$1
                @Override // java.lang.Runnable
                public final void run() {
                    Function1.this.invoke(t);
                }
            });
        }
    }

    public static final <T> boolean uiThread(AnkoAsyncContext<T> receiver, final Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        final T t = receiver.getWeakRef().get();
        if (t == null) {
            return false;
        }
        if (Intrinsics.areEqual(ContextHelper.INSTANCE.getMainThread(), Thread.currentThread())) {
            f.invoke(t);
            return true;
        }
        ContextHelper.INSTANCE.getHandler().post(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$uiThread$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1.this.invoke(t);
            }
        });
        return true;
    }

    public static final <T extends Activity> boolean activityUiThread(AnkoAsyncContext<T> receiver, final Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        final T t = receiver.getWeakRef().get();
        if (t == null || t.isFinishing()) {
            return false;
        }
        t.runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$activityUiThread$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1 function1 = Function1.this;
                Activity activity = t;
                Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
                function1.invoke(activity);
            }
        });
        return true;
    }

    public static final <T extends Activity> boolean activityUiThreadWithContext(AnkoAsyncContext<T> receiver, final Function2<? super Context, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        final T t = receiver.getWeakRef().get();
        if (t == null || t.isFinishing()) {
            return false;
        }
        t.runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$activityUiThreadWithContext$1
            @Override // java.lang.Runnable
            public final void run() {
                Function2 function2 = Function2.this;
                Activity activity = t;
                Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
                function2.invoke(activity, activity);
            }
        });
        return true;
    }

    public static final <T extends Activity> boolean activityContextUiThread(AnkoAsyncContext<AnkoContext<T>> receiver, final Function1<? super T, Unit> f) {
        final T owner;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        AnkoContext<T> ankoContext = receiver.getWeakRef().get();
        if (ankoContext == null || (owner = ankoContext.getOwner()) == null || owner.isFinishing()) {
            return false;
        }
        owner.runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$activityUiThread$2
            @Override // java.lang.Runnable
            public final void run() {
                Function1.this.invoke(owner);
            }
        });
        return true;
    }

    public static final <T extends Activity> boolean activityContextUiThreadWithContext(AnkoAsyncContext<AnkoContext<T>> receiver, final Function2<? super Context, ? super T, Unit> f) {
        final T owner;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        AnkoContext<T> ankoContext = receiver.getWeakRef().get();
        if (ankoContext == null || (owner = ankoContext.getOwner()) == null || owner.isFinishing()) {
            return false;
        }
        owner.runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$activityUiThreadWithContext$2
            @Override // java.lang.Runnable
            public final void run() {
                Function2 function2 = Function2.this;
                Activity activity = owner;
                function2.invoke(activity, activity);
            }
        });
        return true;
    }

    public static final <T extends Fragment> boolean fragmentUiThread(AnkoAsyncContext<T> receiver, final Function1<? super T, Unit> f) {
        Activity activity;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        final T t = receiver.getWeakRef().get();
        if (t == null || t.isDetached() || (activity = t.getActivity()) == null) {
            return false;
        }
        activity.runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$fragmentUiThread$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1 function1 = Function1.this;
                Fragment fragment = t;
                Intrinsics.checkExpressionValueIsNotNull(fragment, "fragment");
                function1.invoke(fragment);
            }
        });
        return true;
    }

    public static final <T extends Fragment> boolean fragmentUiThreadWithContext(AnkoAsyncContext<T> receiver, final Function2<? super Context, ? super T, Unit> f) {
        final Activity activity;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        final T t = receiver.getWeakRef().get();
        if (t == null || t.isDetached() || (activity = t.getActivity()) == null) {
            return false;
        }
        activity.runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.AsyncKt$fragmentUiThreadWithContext$1
            @Override // java.lang.Runnable
            public final void run() {
                Function2 function2 = Function2.this;
                Activity activity2 = activity;
                Fragment fragment = t;
                Intrinsics.checkExpressionValueIsNotNull(fragment, "fragment");
                function2.invoke(activity2, fragment);
            }
        });
        return true;
    }

    public static /* bridge */ /* synthetic */ Future doAsync$default(Object obj, Function1 function1, Function1 function12, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = crashLogger;
        }
        return doAsync(obj, function1, function12);
    }

    public static final <T> Future<Unit> doAsync(T t, final Function1<? super Throwable, Unit> function1, final Function1<? super AnkoAsyncContext<T>, Unit> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        final AnkoAsyncContext ankoAsyncContext = new AnkoAsyncContext(new WeakReference(t));
        return BackgroundExecutor.INSTANCE.submit(new Function0<Unit>() { // from class: org.jetbrains.anko.AsyncKt$doAsync$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                try {
                } catch (Throwable th) {
                    Function1 function12 = function1;
                    if ((function12 != null ? (Unit) function12.invoke(th) : null) != null) {
                        return;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        });
    }

    public static /* bridge */ /* synthetic */ Future doAsync$default(Object obj, Function1 function1, ExecutorService executorService, Function1 function12, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = crashLogger;
        }
        return doAsync(obj, function1, executorService, function12);
    }

    public static final <T> Future<Unit> doAsync(T t, final Function1<? super Throwable, Unit> function1, ExecutorService executorService, final Function1<? super AnkoAsyncContext<T>, Unit> task) {
        Intrinsics.checkParameterIsNotNull(executorService, "executorService");
        Intrinsics.checkParameterIsNotNull(task, "task");
        final AnkoAsyncContext ankoAsyncContext = new AnkoAsyncContext(new WeakReference(t));
        Future<Unit> submit = executorService.submit((Callable) new Callable<Unit>() { // from class: org.jetbrains.anko.AsyncKt$doAsync$2
            @Override // java.util.concurrent.Callable
            public /* bridge */ /* synthetic */ Unit call() {
                call2();
                return Unit.INSTANCE;
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: call, reason: avoid collision after fix types in other method */
            public final void call2() {
                try {
                    Function1.this.invoke(ankoAsyncContext);
                } catch (Throwable th) {
                    Function1 function12 = function1;
                    if (function12 != null) {
                    }
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(submit, "executorService.submit<U…voke(thr)\n        }\n    }");
        return submit;
    }

    public static /* bridge */ /* synthetic */ Future doAsyncResult$default(Object obj, Function1 function1, Function1 function12, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = crashLogger;
        }
        return doAsyncResult(obj, function1, function12);
    }

    public static final <T, R> Future<R> doAsyncResult(T t, final Function1<? super Throwable, Unit> function1, final Function1<? super AnkoAsyncContext<T>, ? extends R> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        final AnkoAsyncContext ankoAsyncContext = new AnkoAsyncContext(new WeakReference(t));
        return BackgroundExecutor.INSTANCE.submit(new Function0<R>() { // from class: org.jetbrains.anko.AsyncKt$doAsyncResult$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final R invoke() {
                try {
                    return (R) Function1.this.invoke(ankoAsyncContext);
                } catch (Throwable th) {
                    Function1 function12 = function1;
                    if (function12 != null) {
                    }
                    throw th;
                }
            }
        });
    }

    public static /* bridge */ /* synthetic */ Future doAsyncResult$default(Object obj, Function1 function1, ExecutorService executorService, Function1 function12, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = crashLogger;
        }
        return doAsyncResult(obj, function1, executorService, function12);
    }

    public static final <T, R> Future<R> doAsyncResult(T t, final Function1<? super Throwable, Unit> function1, ExecutorService executorService, final Function1<? super AnkoAsyncContext<T>, ? extends R> task) {
        Intrinsics.checkParameterIsNotNull(executorService, "executorService");
        Intrinsics.checkParameterIsNotNull(task, "task");
        final AnkoAsyncContext ankoAsyncContext = new AnkoAsyncContext(new WeakReference(t));
        Future<R> submit = executorService.submit(new Callable<R>() { // from class: org.jetbrains.anko.AsyncKt$doAsyncResult$2
            @Override // java.util.concurrent.Callable
            public final R call() {
                try {
                    return (R) Function1.this.invoke(ankoAsyncContext);
                } catch (Throwable th) {
                    Function1 function12 = function1;
                    if (function12 != null) {
                    }
                    throw th;
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(submit, "executorService.submit<R…throw thr\n        }\n    }");
        return submit;
    }
}
