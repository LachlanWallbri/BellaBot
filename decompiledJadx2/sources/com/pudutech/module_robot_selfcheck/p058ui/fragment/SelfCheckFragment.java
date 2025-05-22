package com.pudutech.module_robot_selfcheck.p058ui.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.fragment.FragmentKt;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.fragment.BaseFragment;
import com.pudutech.disinfect.baselib.config.ConstantKt;
import com.pudutech.disinfect.baselib.ext.lifecycle.LifecycleExtKt;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.module_robot_selfcheck.C5365R;
import com.pudutech.module_robot_selfcheck.RobotInitProcessor;
import com.pudutech.module_robot_selfcheck.domain.request.SelfCheckRequest;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import com.pudutech.module_robot_selfcheck.p058ui.InitActivity;
import com.pudutech.module_robot_selfcheck.p058ui.fragment.SelfCheckFragment;
import com.pudutech.module_robot_selfcheck.p058ui.p059vm.SelfCheckViewModel;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: SelfCheckFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u000bH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/fragment/SelfCheckFragment;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseFragment;", "Lcom/pudutech/module_robot_selfcheck/ui/vm/SelfCheckViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "state", "Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$InitStatus;", "checkActiveStatus", "", "checkLocation", "checkRobotInit", "createObserver", "finishWithResult", "locationSuccess", "", "getViewModel", "Lkotlin/Lazy;", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "", "onDestroyView", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelfCheckFragment extends BaseFragment<SelfCheckViewModel> {
    private final String TAG;
    private HashMap _$_findViewCache;
    private RobotInitProcessor.InitStatus state;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RobotInitProcessor.InitStatus.values().length];

        static {
            $EnumSwitchMapping$0[RobotInitProcessor.InitStatus.SUCCESS.ordinal()] = 1;
            $EnumSwitchMapping$0[RobotInitProcessor.InitStatus.FAILED.ordinal()] = 2;
            $EnumSwitchMapping$0[RobotInitProcessor.InitStatus.TIMEOUT.ordinal()] = 3;
            $EnumSwitchMapping$0[RobotInitProcessor.InitStatus.EmptyMap.ordinal()] = 4;
            $EnumSwitchMapping$0[RobotInitProcessor.InitStatus.NoTopMap.ordinal()] = 5;
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

    public SelfCheckFragment() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
        this.state = RobotInitProcessor.InitStatus.SUCCESS;
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public int layoutId() {
        return C5365R.layout.fragment_self_check;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void initView(Bundle savedInstanceState) {
        checkActiveStatus();
    }

    private final void checkActiveStatus() {
        MirSdkManager.INSTANCE.notifyMapFinish();
        Pdlog.m3273d(this.TAG, "checkActiveStatus() isActive = true");
        checkRobotInit();
    }

    private final void checkRobotInit() {
        SelfCheckRequest selfCheckRequest;
        SelfCheckViewModel mvm = getMVM();
        if (mvm == null || (selfCheckRequest = mvm.getSelfCheckRequest()) == null) {
            return;
        }
        selfCheckRequest.requestCheckRobotInit();
        LifecycleExtKt.observe(this, selfCheckRequest.getInitStateLiveData(), new Function1<RobotInitProcessor.InitStatus, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFragment$checkRobotInit$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RobotInitProcessor.InitStatus initStatus) {
                invoke2(initStatus);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RobotInitProcessor.InitStatus it) {
                Pdlog.m3273d(SelfCheckFragment.this.getTAG(), "initStateLiveData " + it);
                SelfCheckFragment selfCheckFragment = SelfCheckFragment.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                selfCheckFragment.state = it;
            }
        });
    }

    private final void checkLocation() {
        final SelfCheckRequest selfCheckRequest;
        SelfCheckViewModel mvm = getMVM();
        if (mvm == null || (selfCheckRequest = mvm.getSelfCheckRequest()) == null) {
            return;
        }
        selfCheckRequest.requestCheckLocation();
        LifecycleExtKt.observe(this, selfCheckRequest.getLocationStateLiveData(), new Function1<Boolean, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFragment$checkLocation$$inlined$run$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean it) {
                LifecycleExtKt.removeObservers(this, SelfCheckRequest.this.getLocationStateLiveData());
                SelfCheckFragment selfCheckFragment = this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                selfCheckFragment.finishWithResult(it.booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithResult(boolean locationSuccess) {
        Pdlog.m3273d(this.TAG, "finishWithResult " + locationSuccess);
        FragmentActivity requireActivity = requireActivity();
        if (requireActivity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.module_robot_selfcheck.ui.InitActivity");
        }
        InitActivity initActivity = (InitActivity) requireActivity;
        initActivity.setResult(locationSuccess ? ConstantKt.RESULT_LOCATION_SUCCESS : 4095);
        initActivity.finish();
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public Lazy<SelfCheckViewModel> getViewModel() {
        final int i = C5365R.id.nav_self_check;
        final KProperty kProperty = null;
        final Function0 function0 = (Function0) null;
        final Lazy lazy = LazyKt.lazy(new Function0<NavBackStackEntry>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFragment$getViewModel$$inlined$navGraphViewModels$1
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
        return FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SelfCheckViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFragment$getViewModel$$inlined$navGraphViewModels$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFragment$getViewModel$$inlined$navGraphViewModels$3
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

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void createObserver() {
        MapUpdateManager.INSTANCE.setOnSyncFinishListener(new Function0<Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFragment$createObserver$1
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
                RobotInitProcessor.InitStatus initStatus;
                RobotInitProcessor.InitStatus initStatus2;
                SelfCheckFragment.this.state = RobotInitProcessor.INSTANCE.getINSTANCE().getInitStatus();
                String tag = SelfCheckFragment.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("onSyncFinishListener ");
                initStatus = SelfCheckFragment.this.state;
                sb.append(initStatus);
                Pdlog.m3273d(tag, sb.toString());
                FragmentActivity requireActivity = SelfCheckFragment.this.requireActivity();
                if (requireActivity != null) {
                    InitActivity initActivity = (InitActivity) requireActivity;
                    initStatus2 = SelfCheckFragment.this.state;
                    int i = SelfCheckFragment.WhenMappings.$EnumSwitchMapping$0[initStatus2.ordinal()];
                    if (i == 1) {
                        MapingFuntionManager.INSTANCE.open();
                        SelfCheckFragment.this.finishWithResult(true);
                        return;
                    }
                    if (i == 2 || i == 3) {
                        NavigationExtKt.nav(SelfCheckFragment.this).navigate(C5365R.id.action_SelfCheckFragment_2_SelfCheckFailFragment);
                        return;
                    }
                    if (i == 4) {
                        Pdlog.m3273d(SelfCheckFragment.this.getTAG(), "EmptyMap");
                        MapingFuntionManager.INSTANCE.open();
                        initActivity.setResult(ConstantKt.RESULT_EMPTY);
                        initActivity.finish();
                        return;
                    }
                    if (i != 5) {
                        return;
                    }
                    Pdlog.m3273d(SelfCheckFragment.this.getTAG(), "NoTopMap");
                    MapingFuntionManager.INSTANCE.open();
                    initActivity.setResult(ConstantKt.RESULT_NO_TOP_MAP);
                    initActivity.finish();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.module_robot_selfcheck.ui.InitActivity");
            }
        });
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        MapUpdateManager.INSTANCE.release();
        _$_clearFindViewByIdCache();
    }
}
