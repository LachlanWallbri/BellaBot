package com.pudutech.module_robot_selfcheck.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.CommonFloatViewControlManager;
import com.pudutech.disinfect.baselib.ext.lifecycle.LifecycleExtKt;
import com.pudutech.disinfect.baselib.widget.TopStatusBarLayout;
import com.pudutech.module_robot_selfcheck.p058ui.FloatStatusBar;
import com.pudutech.module_robot_selfcheck.p058ui.p059vm.StatusBarViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: FloatStatusBarManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001c\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\fH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0016J\u001a\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0013H\u0016J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/base/FloatStatusBarManager;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Lcom/pudutech/disinfect/baselib/base/CommonFloatViewControlManager;", "()V", "isAddRegister", "", "isShowStatusBar", "mFloatStatusBar", "Lcom/pudutech/module_robot_selfcheck/ui/FloatStatusBar;", "checkStatusBarIsShowing", "", "block", "Lkotlin/Function1;", "Lcom/pudutech/disinfect/baselib/widget/TopStatusBarLayout;", "dismissFloatView", "onActivityCreated", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "showFloatView", "isShow", "theme", "", "showStatusBar", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FloatStatusBarManager implements Application.ActivityLifecycleCallbacks, CommonFloatViewControlManager {
    public static final FloatStatusBarManager INSTANCE = new FloatStatusBarManager();
    private static boolean isAddRegister;
    private static boolean isShowStatusBar;
    private static FloatStatusBar mFloatStatusBar;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(outState, "outState");
    }

    private FloatStatusBarManager() {
    }

    @Override // com.pudutech.disinfect.baselib.base.CommonFloatViewControlManager
    public void showFloatView(boolean isShow, int theme) {
        isShowStatusBar = isShow;
        if (!isShow && mFloatStatusBar != null) {
            dismissFloatView();
        } else {
            showStatusBar(theme);
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.CommonFloatViewControlManager
    public void dismissFloatView() {
        FloatStatusBar floatStatusBar = mFloatStatusBar;
        if (floatStatusBar != null) {
            floatStatusBar.dismiss();
        }
        mFloatStatusBar = (FloatStatusBar) null;
        if (isAddRegister) {
            BaseApp.INSTANCE.getINSTANCE().unregisterActivityLifecycleCallbacks(this);
            isAddRegister = false;
        }
    }

    public final void showStatusBar(int theme) {
        TopStatusBarLayout mStatusBar;
        if (mFloatStatusBar == null) {
            mFloatStatusBar = new FloatStatusBar();
            Unit unit = Unit.INSTANCE;
        }
        FloatStatusBar floatStatusBar = mFloatStatusBar;
        if (floatStatusBar != null) {
            if (floatStatusBar != null && (mStatusBar = floatStatusBar.getMStatusBar()) != null) {
                mStatusBar.refreshTheme(theme);
            }
            if (floatStatusBar.getIsShowing() || !isShowStatusBar) {
                return;
            }
            FloatStatusBar floatStatusBar2 = mFloatStatusBar;
            if (floatStatusBar2 != null && !floatStatusBar2.getIsShowing()) {
                floatStatusBar2.show();
            }
            if (!isAddRegister) {
                BaseApp.INSTANCE.getINSTANCE().registerActivityLifecycleCallbacks(INSTANCE);
            }
            isAddRegister = true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (activity instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) activity;
            final FragmentActivity fragmentActivity2 = fragmentActivity;
            StatusBarViewModel statusBarViewModel = (StatusBarViewModel) new ViewModelLazy(Reflection.getOrCreateKotlinClass(StatusBarViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$$special$$inlined$viewModels$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ViewModelStore invoke() {
                    ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                    Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
                    return viewModelStore;
                }
            }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$$special$$inlined$viewModels$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ViewModelProvider.Factory invoke() {
                    ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                    Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                    return defaultViewModelProviderFactory;
                }
            }).getValue();
            FragmentActivity fragmentActivity3 = fragmentActivity;
            LifecycleExtKt.observe(fragmentActivity3, statusBarViewModel.isChargingLiveData(), new Function1<Boolean, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke2(bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final Boolean bool) {
                    FloatStatusBarManager.INSTANCE.checkStatusBarIsShowing(new Function1<TopStatusBarLayout, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TopStatusBarLayout topStatusBarLayout) {
                            invoke2(topStatusBarLayout);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TopStatusBarLayout it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Boolean lv = bool;
                            Intrinsics.checkExpressionValueIsNotNull(lv, "lv");
                            it.setCharge(lv.booleanValue());
                        }
                    });
                }
            });
            LifecycleExtKt.observe(fragmentActivity3, statusBarViewModel.getScheduleLiveData(), new Function1<Integer, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke2(num);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final Integer num) {
                    FloatStatusBarManager.INSTANCE.checkStatusBarIsShowing(new Function1<TopStatusBarLayout, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TopStatusBarLayout topStatusBarLayout) {
                            invoke2(topStatusBarLayout);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TopStatusBarLayout it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Integer lv = num;
                            Intrinsics.checkExpressionValueIsNotNull(lv, "lv");
                            it.setRobotCount(lv.intValue());
                        }
                    });
                }
            });
            LifecycleExtKt.observe(fragmentActivity3, statusBarViewModel.getBatteryLiveData(), new Function1<Integer, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke2(num);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final Integer num) {
                    FloatStatusBarManager.INSTANCE.checkStatusBarIsShowing(new Function1<TopStatusBarLayout, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$3.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TopStatusBarLayout topStatusBarLayout) {
                            invoke2(topStatusBarLayout);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TopStatusBarLayout it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            it.setBattery(num);
                        }
                    });
                }
            });
            LifecycleExtKt.observe(fragmentActivity3, statusBarViewModel.getWifiStatusLiveData(), new Function1<Boolean, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$4
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke2(bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final Boolean bool) {
                    FloatStatusBarManager.INSTANCE.checkStatusBarIsShowing(new Function1<TopStatusBarLayout, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$4.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TopStatusBarLayout topStatusBarLayout) {
                            invoke2(topStatusBarLayout);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TopStatusBarLayout it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Boolean lv = bool;
                            Intrinsics.checkExpressionValueIsNotNull(lv, "lv");
                            it.changeNetStatus(lv.booleanValue());
                        }
                    });
                }
            });
            LifecycleExtKt.observe(fragmentActivity3, statusBarViewModel.getBluetoothStatusLiveData(), new Function1<Boolean, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke2(bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final Boolean bool) {
                    FloatStatusBarManager.INSTANCE.checkStatusBarIsShowing(new Function1<TopStatusBarLayout, Unit>() { // from class: com.pudutech.module_robot_selfcheck.base.FloatStatusBarManager$onActivityStarted$1$1$5.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TopStatusBarLayout topStatusBarLayout) {
                            invoke2(topStatusBarLayout);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TopStatusBarLayout it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Boolean lv = bool;
                            Intrinsics.checkExpressionValueIsNotNull(lv, "lv");
                            it.setBluetooth(lv.booleanValue());
                        }
                    });
                }
            });
            return;
        }
        throw new IllegalStateException("current activity isn't FragmentActivity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (BaseApp.INSTANCE.getMActivityCount() <= 0) {
            dismissFloatView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkStatusBarIsShowing(Function1<? super TopStatusBarLayout, Unit> block) {
        FloatStatusBar floatStatusBar = mFloatStatusBar;
        if (floatStatusBar == null || !floatStatusBar.getIsShowing()) {
            return;
        }
        block.invoke(floatStatusBar.getMStatusBar());
    }
}
