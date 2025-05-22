package com.pudutech.event_tracking.click;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.core.view.ViewGroupKt;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: ViewClickHook.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0007J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/event_tracking/click/ViewClickHookManager;", "", "()V", "TAG", "", "mHookCallbackMap", "", "Landroid/app/Activity;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "mHookJobMap", "Lkotlinx/coroutines/Job;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "hook", "", "view", "Landroid/view/View;", "(Landroid/view/View;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hookViewClickLis", "Landroid/view/View$OnClickListener;", "registerHook", "activity", "unregisterHook", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ViewClickHookManager {
    public static final ViewClickHookManager INSTANCE = new ViewClickHookManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private static final Map<Activity, ViewTreeObserver.OnGlobalLayoutListener> mHookCallbackMap = new LinkedHashMap();
    private static final Map<Activity, Job> mHookJobMap = new LinkedHashMap();

    private ViewClickHookManager() {
    }

    public final void registerHook(final Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.pudutech.event_tracking.click.ViewClickHookManager$registerHook$mGlobalLayoutListener$1

            /* compiled from: ViewClickHook.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.event_tracking.click.ViewClickHookManager$registerHook$mGlobalLayoutListener$1$1", m3970f = "ViewClickHook.kt", m3971i = {0}, m3972l = {26}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.event_tracking.click.ViewClickHookManager$registerHook$mGlobalLayoutListener$1$1 */
            /* loaded from: classes5.dex */
            static final class C44541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5118p$;

                C44541(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C44541 c44541 = new C44541(completion);
                    c44541.f5118p$ = (CoroutineScope) obj;
                    return c44541;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C44541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    String str2;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5118p$;
                        ViewClickHookManager viewClickHookManager = ViewClickHookManager.INSTANCE;
                        str = ViewClickHookManager.TAG;
                        Log.i(str, "proxyClickEvent start");
                        ViewClickHookManager viewClickHookManager2 = ViewClickHookManager.INSTANCE;
                        Window window = activity.getWindow();
                        Intrinsics.checkExpressionValueIsNotNull(window, "activity.window");
                        View decorView = window.getDecorView();
                        Intrinsics.checkExpressionValueIsNotNull(decorView, "activity.window.decorView");
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (viewClickHookManager2.hook(decorView, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    ViewClickHookManager viewClickHookManager3 = ViewClickHookManager.INSTANCE;
                    str2 = ViewClickHookManager.TAG;
                    Log.i(str2, "proxyClickEvent end");
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                Map map;
                Map map2;
                CoroutineScope coroutineScope;
                Job launch$default;
                ViewClickHookManager viewClickHookManager = ViewClickHookManager.INSTANCE;
                map = ViewClickHookManager.mHookJobMap;
                Job job = (Job) map.get(activity);
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                ViewClickHookManager viewClickHookManager2 = ViewClickHookManager.INSTANCE;
                map2 = ViewClickHookManager.mHookJobMap;
                Activity activity2 = activity;
                ViewClickHookManager viewClickHookManager3 = ViewClickHookManager.INSTANCE;
                coroutineScope = ViewClickHookManager.scope;
                launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C44541(null), 3, null);
                map2.put(activity2, launch$default);
            }
        };
        mHookCallbackMap.put(activity, onGlobalLayoutListener);
        Window window = activity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "activity.window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "activity.window.decorView");
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public final void unregisterHook(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Job remove = mHookJobMap.remove(activity);
        if (remove != null) {
            Job.DefaultImpls.cancel$default(remove, (CancellationException) null, 1, (Object) null);
        }
        mHookCallbackMap.remove(activity);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object hook(View view, Continuation<? super Unit> continuation) {
        ViewClickHookManager$hook$1 viewClickHookManager$hook$1;
        Object obj;
        Object coroutine_suspended;
        int i;
        ViewClickHookManager viewClickHookManager;
        Sequence<View> sequence;
        View view2;
        Iterator<View> it;
        Object obj2;
        ViewClickHookManager$hook$1 viewClickHookManager$hook$12;
        View.OnClickListener onClickListener;
        if (continuation instanceof ViewClickHookManager$hook$1) {
            viewClickHookManager$hook$1 = (ViewClickHookManager$hook$1) continuation;
            if ((viewClickHookManager$hook$1.label & Integer.MIN_VALUE) != 0) {
                viewClickHookManager$hook$1.label -= Integer.MIN_VALUE;
                obj = viewClickHookManager$hook$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = viewClickHookManager$hook$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (view instanceof ViewGroup) {
                        Sequence<View> children = ViewGroupKt.getChildren((ViewGroup) view);
                        Iterator<View> it2 = children.iterator();
                        sequence = children;
                        viewClickHookManager = this;
                        ViewClickHookManager$hook$1 viewClickHookManager$hook$13 = viewClickHookManager$hook$1;
                        view2 = view;
                        it = it2;
                        obj2 = coroutine_suspended;
                        viewClickHookManager$hook$12 = viewClickHookManager$hook$13;
                    } else {
                        viewClickHookManager = this;
                        viewClickHookManager$hook$1.L$0 = viewClickHookManager;
                        viewClickHookManager$hook$1.L$1 = view;
                        viewClickHookManager$hook$1.label = 2;
                        obj = viewClickHookManager.hookViewClickLis(view, viewClickHookManager$hook$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        onClickListener = (View.OnClickListener) obj;
                        if (onClickListener != null) {
                            view.setOnClickListener(new PdClickWrap(300, new ClickArgs(null, 0, 3, null), onClickListener));
                        }
                        return Unit.INSTANCE;
                    }
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            view = (View) viewClickHookManager$hook$1.L$1;
                            ResultKt.throwOnFailure(obj);
                            onClickListener = (View.OnClickListener) obj;
                            if (onClickListener != null && !(onClickListener instanceof PdClickWrap)) {
                                view.setOnClickListener(new PdClickWrap(300, new ClickArgs(null, 0, 3, null), onClickListener));
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object obj3 = viewClickHookManager$hook$1.L$4;
                    it = (Iterator) viewClickHookManager$hook$1.L$3;
                    Sequence<View> sequence2 = (Sequence) viewClickHookManager$hook$1.L$2;
                    View view3 = (View) viewClickHookManager$hook$1.L$1;
                    ViewClickHookManager viewClickHookManager2 = (ViewClickHookManager) viewClickHookManager$hook$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    viewClickHookManager = viewClickHookManager2;
                    viewClickHookManager$hook$12 = viewClickHookManager$hook$1;
                    view2 = view3;
                    sequence = sequence2;
                    obj2 = coroutine_suspended;
                }
                while (it.hasNext()) {
                    View next = it.next();
                    View view4 = next;
                    ViewClickHookManager viewClickHookManager3 = INSTANCE;
                    viewClickHookManager$hook$12.L$0 = viewClickHookManager;
                    viewClickHookManager$hook$12.L$1 = view2;
                    viewClickHookManager$hook$12.L$2 = sequence;
                    viewClickHookManager$hook$12.L$3 = it;
                    viewClickHookManager$hook$12.L$4 = next;
                    viewClickHookManager$hook$12.L$5 = view4;
                    viewClickHookManager$hook$12.label = 1;
                    if (viewClickHookManager3.hook(view4, viewClickHookManager$hook$12) == obj2) {
                        return obj2;
                    }
                }
                view = view2;
                viewClickHookManager$hook$1 = viewClickHookManager$hook$12;
                coroutine_suspended = obj2;
                viewClickHookManager$hook$1.L$0 = viewClickHookManager;
                viewClickHookManager$hook$1.L$1 = view;
                viewClickHookManager$hook$1.label = 2;
                obj = viewClickHookManager.hookViewClickLis(view, viewClickHookManager$hook$1);
                if (obj == coroutine_suspended) {
                }
                onClickListener = (View.OnClickListener) obj;
                if (onClickListener != null) {
                }
                return Unit.INSTANCE;
            }
        }
        viewClickHookManager$hook$1 = new ViewClickHookManager$hook$1(this, continuation);
        obj = viewClickHookManager$hook$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = viewClickHookManager$hook$1.label;
        if (i != 0) {
        }
        while (it.hasNext()) {
        }
        view = view2;
        viewClickHookManager$hook$1 = viewClickHookManager$hook$12;
        coroutine_suspended = obj2;
        viewClickHookManager$hook$1.L$0 = viewClickHookManager;
        viewClickHookManager$hook$1.L$1 = view;
        viewClickHookManager$hook$1.label = 2;
        obj = viewClickHookManager.hookViewClickLis(view, viewClickHookManager$hook$1);
        if (obj == coroutine_suspended) {
        }
        onClickListener = (View.OnClickListener) obj;
        if (onClickListener != null) {
        }
        return Unit.INSTANCE;
    }

    final /* synthetic */ Object hookViewClickLis(View view, Continuation<? super View.OnClickListener> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        if (view.hasOnClickListeners()) {
            try {
                Method declaredMethod = Class.forName("android.view.View").getDeclaredMethod("getListenerInfo", new Class[0]);
                Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "viewClazz.getDeclaredMethod(\"getListenerInfo\")");
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                Object invoke = declaredMethod.invoke(view, new Object[0]);
                Intrinsics.checkExpressionValueIsNotNull(invoke, "listenerInfoMethod.invoke(view)");
                Field declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener");
                Intrinsics.checkExpressionValueIsNotNull(declaredField, "listenerInfoClazz.getDec…Field(\"mOnClickListener\")");
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(invoke);
                if (!(obj instanceof View.OnClickListener)) {
                    obj = null;
                }
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl((View.OnClickListener) obj));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(null));
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                Result.Companion companion3 = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(null));
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
                Result.Companion companion4 = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(null));
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
                Result.Companion companion5 = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(null));
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
                Result.Companion companion6 = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(null));
            }
        } else {
            Result.Companion companion7 = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(null));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
