package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.CustomCallContract;
import com.pudutech.bumblebee.presenter.robot_open_task.CustomCallPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: CustomCallHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0014\u001a\u00020\u000e2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fJ\u0014\u0010\u0016\u001a\u00020\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J\u001c\u0010\u0018\u001a\u00020\u000e2\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\fJ\u001a\u0010\u001a\u001a\u00020\u000e2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\fJ\u0016\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\rH\u0016J\b\u0010#\u001a\u00020\u000eH\u0016J\u0012\u0010$\u001a\u00020\u000e2\b\u0010\"\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u0013H\u0016J\u001a\u0010'\u001a\u00020\u000e2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fJ\u0014\u0010(\u001a\u00020\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J\u001c\u0010)\u001a\u00020\u000e2\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\fJ\u001a\u0010*\u001a\u00020\u000e2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\fR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R&\u0010\t\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00100\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0011\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0012\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\f0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/CustomCallHelper;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallContract$ViewInterface;", "()V", "customCallPresenter", "Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallPresenter;", "getCustomCallPresenter", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallPresenter;", "customCallPresenter$delegate", "Lkotlin/Lazy;", "onCallListenerList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Ljava/lang/ref/WeakReference;", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "", "onCancelListenerList", "Lkotlin/Function0;", "onCompleteListenerList", "onContentListenerList", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "addCallListener", "onCallListener", "addCancelListener", "onCancelListener", "addCompleteListener", "onCompleteListener", "addContentListener", "onContentListener", "notificationCustomCall", "state", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallState;", "type", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallOperationType;", "onCall", "task", "onCancel", "onComplete", "onContent", AIUIConstant.KEY_CONTENT, "removeCallListener", "removeCancelListener", "removeCompleteListener", "removeContentListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CustomCallHelper implements CustomCallContract.ViewInterface {
    public static final CustomCallHelper INSTANCE;

    /* renamed from: customCallPresenter$delegate, reason: from kotlin metadata */
    private static final Lazy customCallPresenter;
    private static final CopyOnWriteArrayList<WeakReference<Function1<CustomCallTargetBean, Unit>>> onCallListenerList;
    private static final CopyOnWriteArrayList<WeakReference<Function0<Unit>>> onCancelListenerList;
    private static final CopyOnWriteArrayList<WeakReference<Function1<CustomCallTargetBean, Unit>>> onCompleteListenerList;
    private static final CopyOnWriteArrayList<WeakReference<Function1<ICustomCallBean, Unit>>> onContentListenerList;

    private final CustomCallPresenter getCustomCallPresenter() {
        return (CustomCallPresenter) customCallPresenter.getValue();
    }

    static {
        CustomCallHelper customCallHelper = new CustomCallHelper();
        INSTANCE = customCallHelper;
        customCallPresenter = LazyKt.lazy(new Function0<CustomCallPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.CustomCallHelper$customCallPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CustomCallPresenter invoke() {
                CustomCallPresenter customCallPresenter2;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(CustomCallPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(CustomCallPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    customCallPresenter2 = new CustomCallPresenter();
                    presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(CustomCallPresenter.class).toString(), customCallPresenter2);
                } else {
                    if (!(basePresenterInterface instanceof CustomCallPresenter)) {
                        basePresenterInterface = null;
                    }
                    customCallPresenter2 = (CustomCallPresenter) basePresenterInterface;
                }
                if (customCallPresenter2 != null) {
                    return customCallPresenter2;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_open_task.CustomCallPresenter");
            }
        });
        onCallListenerList = new CopyOnWriteArrayList<>();
        onCancelListenerList = new CopyOnWriteArrayList<>();
        onContentListenerList = new CopyOnWriteArrayList<>();
        onCompleteListenerList = new CopyOnWriteArrayList<>();
        customCallHelper.getCustomCallPresenter().replaceView(customCallHelper);
    }

    private CustomCallHelper() {
    }

    public final void addCallListener(Function1<? super CustomCallTargetBean, Unit> onCallListener) {
        Intrinsics.checkParameterIsNotNull(onCallListener, "onCallListener");
        onCallListenerList.add(new WeakReference<>(onCallListener));
    }

    public final void removeCallListener(final Function1<? super CustomCallTargetBean, Unit> onCallListener) {
        Intrinsics.checkParameterIsNotNull(onCallListener, "onCallListener");
        onCallListenerList.removeIf(new Predicate<WeakReference<Function1<? super CustomCallTargetBean, ? extends Unit>>>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.CustomCallHelper$removeCallListener$1
            @Override // java.util.function.Predicate
            public /* bridge */ /* synthetic */ boolean test(WeakReference<Function1<? super CustomCallTargetBean, ? extends Unit>> weakReference) {
                return test2((WeakReference<Function1<CustomCallTargetBean, Unit>>) weakReference);
            }

            /* renamed from: test, reason: avoid collision after fix types in other method */
            public final boolean test2(WeakReference<Function1<CustomCallTargetBean, Unit>> weakReference) {
                return weakReference.get() == null || Intrinsics.areEqual(weakReference.get(), Function1.this);
            }
        });
    }

    public final void addCancelListener(Function0<Unit> onCancelListener) {
        Intrinsics.checkParameterIsNotNull(onCancelListener, "onCancelListener");
        onCancelListenerList.add(new WeakReference<>(onCancelListener));
    }

    public final void removeCancelListener(final Function0<Unit> onCancelListener) {
        Intrinsics.checkParameterIsNotNull(onCancelListener, "onCancelListener");
        onCancelListenerList.removeIf(new Predicate<WeakReference<Function0<? extends Unit>>>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.CustomCallHelper$removeCancelListener$1
            @Override // java.util.function.Predicate
            public /* bridge */ /* synthetic */ boolean test(WeakReference<Function0<? extends Unit>> weakReference) {
                return test2((WeakReference<Function0<Unit>>) weakReference);
            }

            /* renamed from: test, reason: avoid collision after fix types in other method */
            public final boolean test2(WeakReference<Function0<Unit>> weakReference) {
                return weakReference.get() == null || Intrinsics.areEqual(weakReference.get(), Function0.this);
            }
        });
    }

    public final void addContentListener(Function1<? super ICustomCallBean, Unit> onContentListener) {
        Intrinsics.checkParameterIsNotNull(onContentListener, "onContentListener");
        onContentListenerList.add(new WeakReference<>(onContentListener));
    }

    public final void removeContentListener(final Function1<? super ICustomCallBean, Unit> onContentListener) {
        Intrinsics.checkParameterIsNotNull(onContentListener, "onContentListener");
        onContentListenerList.removeIf(new Predicate<WeakReference<Function1<? super ICustomCallBean, ? extends Unit>>>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.CustomCallHelper$removeContentListener$1
            @Override // java.util.function.Predicate
            public /* bridge */ /* synthetic */ boolean test(WeakReference<Function1<? super ICustomCallBean, ? extends Unit>> weakReference) {
                return test2((WeakReference<Function1<ICustomCallBean, Unit>>) weakReference);
            }

            /* renamed from: test, reason: avoid collision after fix types in other method */
            public final boolean test2(WeakReference<Function1<ICustomCallBean, Unit>> weakReference) {
                return weakReference.get() == null || Intrinsics.areEqual(weakReference.get(), Function1.this);
            }
        });
    }

    public final void addCompleteListener(Function1<? super CustomCallTargetBean, Unit> onCompleteListener) {
        Intrinsics.checkParameterIsNotNull(onCompleteListener, "onCompleteListener");
        onCompleteListenerList.add(new WeakReference<>(onCompleteListener));
    }

    public final void removeCompleteListener(final Function1<? super CustomCallTargetBean, Unit> onCompleteListener) {
        Intrinsics.checkParameterIsNotNull(onCompleteListener, "onCompleteListener");
        onCompleteListenerList.removeIf(new Predicate<WeakReference<Function1<? super CustomCallTargetBean, ? extends Unit>>>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.CustomCallHelper$removeCompleteListener$1
            @Override // java.util.function.Predicate
            public /* bridge */ /* synthetic */ boolean test(WeakReference<Function1<? super CustomCallTargetBean, ? extends Unit>> weakReference) {
                return test2((WeakReference<Function1<CustomCallTargetBean, Unit>>) weakReference);
            }

            /* renamed from: test, reason: avoid collision after fix types in other method */
            public final boolean test2(WeakReference<Function1<CustomCallTargetBean, Unit>> weakReference) {
                return weakReference.get() == null || Intrinsics.areEqual(weakReference.get(), Function1.this);
            }
        });
    }

    public final void notificationCustomCall(CustomCallState state, CustomCallOperationType type) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(type, "type");
        getCustomCallPresenter().notificationCustomCall(state, type);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.CustomCallContract.ViewInterface
    public void onCall(CustomCallTargetBean task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Iterator<T> it = onCallListenerList.iterator();
        while (it.hasNext()) {
            Function1 function1 = (Function1) ((WeakReference) it.next()).get();
            if (function1 != null) {
            }
        }
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.CustomCallContract.ViewInterface
    public void onCancel() {
        Iterator<T> it = onCancelListenerList.iterator();
        while (it.hasNext()) {
            Function0 function0 = (Function0) ((WeakReference) it.next()).get();
            if (function0 != null) {
            }
        }
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.CustomCallContract.ViewInterface
    public void onContent(ICustomCallBean content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Iterator<T> it = onContentListenerList.iterator();
        while (it.hasNext()) {
            Function1 function1 = (Function1) ((WeakReference) it.next()).get();
            if (function1 != null) {
            }
        }
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.CustomCallContract.ViewInterface
    public void onComplete(CustomCallTargetBean task) {
        Iterator<T> it = onCompleteListenerList.iterator();
        while (it.hasNext()) {
            Function1 function1 = (Function1) ((WeakReference) it.next()).get();
            if (function1 != null) {
            }
        }
    }
}
