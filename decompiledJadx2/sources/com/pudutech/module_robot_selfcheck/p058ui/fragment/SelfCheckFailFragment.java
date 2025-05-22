package com.pudutech.module_robot_selfcheck.p058ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.LinearLayout;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.fragment.FragmentKt;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.fragment.BaseFragment;
import com.pudutech.disinfect.baselib.ext.lifecycle.LifecycleExtKt;
import com.pudutech.disinfect.baselib.ext.util.AppStartupExtKt;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.module_robot_selfcheck.C5365R;
import com.pudutech.module_robot_selfcheck.domain.request.SelfCheckRequest;
import com.pudutech.module_robot_selfcheck.p058ui.CheckPwdDialog;
import com.pudutech.module_robot_selfcheck.p058ui.SelfCheckFailDetailsView;
import com.pudutech.module_robot_selfcheck.p058ui.dialog.TipsDialog;
import com.pudutech.module_robot_selfcheck.p058ui.p059vm.SelfCheckViewModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KProperty;

/* compiled from: SelfCheckFailFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/fragment/SelfCheckFailFragment;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseFragment;", "Lcom/pudutech/module_robot_selfcheck/ui/vm/SelfCheckViewModel;", "()V", "TAG", "", "checkPwdDialog", "Lcom/pudutech/module_robot_selfcheck/ui/CheckPwdDialog;", "isDev", "", "lastHitTimestamp", "", "mDevHitCountdown", "", "createObserver", "", "getViewModel", "Lkotlin/Lazy;", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "openDevMode", "parseErrorMsgTitle", "step", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "showCheckPermissionDialog", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelfCheckFailFragment extends BaseFragment<SelfCheckViewModel> {
    private final String TAG;
    private HashMap _$_findViewCache;
    private CheckPwdDialog checkPwdDialog;
    private boolean isDev;
    private long lastHitTimestamp;
    private int mDevHitCountdown;

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

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public SelfCheckFailFragment() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
        this.mDevHitCountdown = 17;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public int layoutId() {
        return C5365R.layout.fragment_self_check_fail;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void initView(Bundle savedInstanceState) {
        ((ConstraintLayout) _$_findCachedViewById(C5365R.id.rootView)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFailFragment$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelfCheckFailFragment.this.openDevMode();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openDevMode() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.lastHitTimestamp;
        if (elapsedRealtime - j > 500 && j != 0) {
            this.mDevHitCountdown = 17;
            this.lastHitTimestamp = SystemClock.elapsedRealtime();
            return;
        }
        this.lastHitTimestamp = SystemClock.elapsedRealtime();
        if (this.isDev) {
            this.mDevHitCountdown = 17;
            this.isDev = false;
        }
        int i = this.mDevHitCountdown;
        if (i <= 0) {
            if (i < 0) {
                ToastUtils toastUtils = ToastUtils.INSTANCE;
                String string = getString(C5365R.string.pdStr1_6);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_6)");
                toastUtils.showShortToast(string);
                return;
            }
            return;
        }
        this.mDevHitCountdown = i - 1;
        int i2 = this.mDevHitCountdown;
        if (i2 == 0) {
            Pdlog.m3277w(this.TAG, "go to debug activity");
            showCheckPermissionDialog();
            this.isDev = true;
        } else if (1 <= i2 && 4 >= i2) {
            Pdlog.m3277w(this.TAG, "go to debug activity");
            ToastUtils toastUtils2 = ToastUtils.INSTANCE;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string2 = getString(C5365R.string.pdStr1_5, Integer.valueOf(this.mDevHitCountdown));
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(\n             …                        )");
            Object[] objArr = new Object[0];
            String format = String.format(string2, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            toastUtils2.showShortToast(format);
        }
    }

    private final void showCheckPermissionDialog() {
        if (this.checkPwdDialog == null) {
            final SelfCheckFailFragment selfCheckFailFragment = this;
            final CheckPwdDialog checkPwdDialog = new CheckPwdDialog();
            checkPwdDialog.setOnVerifySuccessfulListener(new Function0<Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFailFragment$$special$$inlined$apply$lambda$1
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
                    Context context = CheckPwdDialog.this.getContext();
                    if (context != null) {
                        AppStartupExtKt.startDebugFunction(context, new Function1<String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFailFragment$$special$$inlined$apply$lambda$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                invoke2(str);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                String str;
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                TipsDialog tipsDialog = new TipsDialog(it);
                                FragmentActivity activity = CheckPwdDialog.this.getActivity();
                                FragmentManager supportFragmentManager = activity != null ? activity.getSupportFragmentManager() : null;
                                str = selfCheckFailFragment.TAG;
                                tipsDialog.showDialog(supportFragmentManager, str);
                            }
                        });
                    }
                }
            });
            selfCheckFailFragment.checkPwdDialog = checkPwdDialog;
        }
        CheckPwdDialog checkPwdDialog2 = this.checkPwdDialog;
        if (checkPwdDialog2 != null) {
            FragmentActivity activity = getActivity();
            checkPwdDialog2.showDialog(activity != null ? activity.getSupportFragmentManager() : null, this.TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String parseErrorMsgTitle(InitStep step) {
        switch (step) {
            case OTACheck:
                return "OTA";
            case CheckBattery:
                String string = getString(C5365R.string.battery);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.battery)");
                return string;
            case CheckCamera:
                String string2 = getString(C5365R.string.camera);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.camera)");
                return string2;
            case CheckLidar:
                String string3 = getString(C5365R.string.lidar);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.lidar)");
                return string3;
            case CheckRGBD:
                return "RGBD";
            case CheckMap:
                String string4 = getString(C5365R.string.map);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.map)");
                return string4;
            default:
                return step.name();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public Lazy<SelfCheckViewModel> getViewModel() {
        final int i = C5365R.id.nav_self_check;
        final KProperty kProperty = null;
        final Function0 function0 = (Function0) null;
        final Lazy lazy = LazyKt.lazy(new Function0<NavBackStackEntry>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFailFragment$getViewModel$$inlined$navGraphViewModels$1
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
        return FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SelfCheckViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFailFragment$getViewModel$$inlined$navGraphViewModels$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFailFragment$getViewModel$$inlined$navGraphViewModels$3
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
        SelfCheckRequest selfCheckRequest;
        SelfCheckViewModel mvm = getMVM();
        LifecycleExtKt.observe(this, (mvm == null || (selfCheckRequest = mvm.getSelfCheckRequest()) == null) ? null : selfCheckRequest.getSelfCheckFailLiveData(), new Function1<ArrayMap<InitStep, String>, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.SelfCheckFailFragment$createObserver$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayMap<InitStep, String> arrayMap) {
                invoke2(arrayMap);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayMap<InitStep, String> it) {
                String str;
                String parseErrorMsgTitle;
                Context context = SelfCheckFailFragment.this.getContext();
                if (context != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    for (Map.Entry<InitStep, String> entry : it.entrySet()) {
                        InitStep k = entry.getKey();
                        String value = entry.getValue();
                        str = SelfCheckFailFragment.this.TAG;
                        Pdlog.m3274e(str, "InitStep: " + k + "   " + value);
                        LinearLayout linearLayout = (LinearLayout) SelfCheckFailFragment.this._$_findCachedViewById(C5365R.id.selfCheckFailMsgContainer);
                        Intrinsics.checkExpressionValueIsNotNull(context, "this");
                        SelfCheckFailFragment selfCheckFailFragment = SelfCheckFailFragment.this;
                        Intrinsics.checkExpressionValueIsNotNull(k, "k");
                        parseErrorMsgTitle = selfCheckFailFragment.parseErrorMsgTitle(k);
                        if (value == null) {
                            value = "";
                        }
                        linearLayout.addView(new SelfCheckFailDetailsView(context, parseErrorMsgTitle, value));
                    }
                }
            }
        });
    }
}
