package com.pudutech.module_robot_selfcheck.p058ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.fragment.FragmentKt;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.fragment.BaseFragment;
import com.pudutech.disinfect.baselib.ext.lifecycle.LifecycleExtKt;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.disinfect.baselib.util.NetStatusUtil;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.module_robot_selfcheck.C5365R;
import com.pudutech.module_robot_selfcheck.RobotInitProcessor;
import com.pudutech.module_robot_selfcheck.data.ActiveStatusWrapInfo;
import com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest;
import com.pudutech.module_robot_selfcheck.enums.ActiveStatus;
import com.pudutech.module_robot_selfcheck.p058ui.fragment.CheckingActiveFragment;
import com.pudutech.module_robot_selfcheck.p058ui.p059vm.SelfCheckViewModel;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: CheckingActiveFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0002J\b\u0010\u0013\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/fragment/CheckingActiveFragment;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseFragment;", "Lcom/pudutech/module_robot_selfcheck/ui/vm/SelfCheckViewModel;", "()V", "TAG", "", "WIFI_SET_RESULT", "", "isFirst", "", "createObserver", "", "getViewModel", "Lkotlin/Lazy;", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "observer", "onResume", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CheckingActiveFragment extends BaseFragment<SelfCheckViewModel> {
    private HashMap _$_findViewCache;
    private final String TAG = "CheckingActiveFragment";
    private final int WIFI_SET_RESULT = 100;
    private boolean isFirst = true;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActiveStatus.values().length];

        static {
            $EnumSwitchMapping$0[ActiveStatus.Active.ordinal()] = 1;
            $EnumSwitchMapping$0[ActiveStatus.Testing.ordinal()] = 2;
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void createObserver() {
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public int layoutId() {
        return C5365R.layout.fragment_check_active;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void initView(Bundle savedInstanceState) {
        ImageView imageView = (ImageView) _$_findCachedViewById(C5365R.id.iv_loading);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(2000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        ImageView imageView2 = (ImageView) _$_findCachedViewById(C5365R.id.iv_loading);
        if (imageView2 != null) {
            imageView2.startAnimation(rotateAnimation);
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!NetStatusUtil.isConnected(RobotInitProcessor.INSTANCE.getINSTANCE().getContext$module_robot_selfcheck_release()) || !NetStatusUtil.isWifi(RobotInitProcessor.INSTANCE.getINSTANCE().getContext$module_robot_selfcheck_release())) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CheckingActiveFragment$onResume$1(this, null), 3, null);
        } else {
            observer();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public Lazy<SelfCheckViewModel> getViewModel() {
        final int i = C5365R.id.nav_self_check;
        final KProperty kProperty = null;
        final Function0 function0 = (Function0) null;
        final Lazy lazy = LazyKt.lazy(new Function0<NavBackStackEntry>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.CheckingActiveFragment$getViewModel$$inlined$navGraphViewModels$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final NavBackStackEntry invoke() {
                return FragmentKt.findNavController(Fragment.this).getBackStackEntry(i);
            }
        });
        return FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SelfCheckViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.CheckingActiveFragment$getViewModel$$inlined$navGraphViewModels$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                Lazy lazy2 = Lazy.this;
                KProperty kProperty2 = kProperty;
                NavBackStackEntry backStackEntry = (NavBackStackEntry) lazy2.getValue();
                Intrinsics.checkExpressionValueIsNotNull(backStackEntry, "backStackEntry");
                ViewModelStore viewModelStore = backStackEntry.getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "backStackEntry.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.CheckingActiveFragment$getViewModel$$inlined$navGraphViewModels$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory factory;
                Function0 function02 = Function0.this;
                if (function02 != null && (factory = (ViewModelProvider.Factory) function02.invoke()) != null) {
                    return factory;
                }
                Lazy lazy2 = lazy;
                KProperty kProperty2 = kProperty;
                NavBackStackEntry backStackEntry = (NavBackStackEntry) lazy2.getValue();
                Intrinsics.checkExpressionValueIsNotNull(backStackEntry, "backStackEntry");
                ViewModelProvider.Factory defaultViewModelProviderFactory = backStackEntry.getDefaultViewModelProviderFactory();
                Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "backStackEntry.defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    private final void observer() {
        ActiveRequest activeRequest;
        ActiveRequest activeRequest2;
        SelfCheckViewModel mvm = getMVM();
        LifecycleExtKt.observe(this, (mvm == null || (activeRequest2 = mvm.getActiveRequest()) == null) ? null : activeRequest2.getActiveStatusLD(), new Function1<ActiveStatusWrapInfo, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.CheckingActiveFragment$observer$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActiveStatusWrapInfo activeStatusWrapInfo) {
                invoke2(activeStatusWrapInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ActiveStatusWrapInfo activeStatusWrapInfo) {
                String str;
                ActiveRequest activeRequest3;
                MutableLiveData<ActiveStatusWrapInfo> activeStatusLD;
                ActiveRequest activeRequest4;
                MutableLiveData<ActiveStatusWrapInfo> activeStatusLD2;
                str = CheckingActiveFragment.this.TAG;
                Pdlog.m3273d(str, "info " + activeStatusWrapInfo);
                if (activeStatusWrapInfo == null) {
                    return;
                }
                ImageView imageView = (ImageView) CheckingActiveFragment.this._$_findCachedViewById(C5365R.id.iv_loading);
                if (imageView != null) {
                    imageView.clearAnimation();
                }
                int i = CheckingActiveFragment.WhenMappings.$EnumSwitchMapping$0[activeStatusWrapInfo.getActiveState().ordinal()];
                if (i == 1 || i == 2) {
                    MirSdkManager.INSTANCE.notifyMapFinish();
                    SelfCheckViewModel mvm2 = CheckingActiveFragment.this.getMVM();
                    if (mvm2 != null && (activeRequest3 = mvm2.getActiveRequest()) != null && (activeStatusLD = activeRequest3.getActiveStatusLD()) != null) {
                        activeStatusLD.setValue(null);
                    }
                    NavigationExtKt.nav(CheckingActiveFragment.this).navigate(C5365R.id.action_checkingActiveFragment_to_selfCheckFragment);
                    return;
                }
                SelfCheckViewModel mvm3 = CheckingActiveFragment.this.getMVM();
                if (mvm3 != null && (activeRequest4 = mvm3.getActiveRequest()) != null && (activeStatusLD2 = activeRequest4.getActiveStatusLD()) != null) {
                    activeStatusLD2.setValue(null);
                }
                NavigationExtKt.nav(CheckingActiveFragment.this).navigate(C5365R.id.action_checkingActiveFragment_to_ActiveRobotFragment);
            }
        });
        SelfCheckViewModel mvm2 = getMVM();
        if (mvm2 == null || (activeRequest = mvm2.getActiveRequest()) == null) {
            return;
        }
        activeRequest.requestActiveRobot();
    }
}
