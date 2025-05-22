package com.pudutech.module_robot_selfcheck.p058ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.fragment.FragmentKt;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.fragment.BaseFragment;
import com.pudutech.disinfect.baselib.ext.lifecycle.LifecycleExtKt;
import com.pudutech.disinfect.baselib.ext.util.AppStartupExtKt;
import com.pudutech.disinfect.baselib.ext.util.LogExtKt;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.disinfect.baselib.widget.CNumberKeyPanel;
import com.pudutech.disinfect.baselib.widget.CTextButton;
import com.pudutech.module_robot_selfcheck.C5365R;
import com.pudutech.module_robot_selfcheck.data.ActiveStatusWrapInfo;
import com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest;
import com.pudutech.module_robot_selfcheck.enums.ActiveStatus;
import com.pudutech.module_robot_selfcheck.enums.InactiveType;
import com.pudutech.module_robot_selfcheck.p058ui.CheckPwdDialog;
import com.pudutech.module_robot_selfcheck.p058ui.dialog.TipsDialog;
import com.pudutech.module_robot_selfcheck.p058ui.fragment.ActiveRobotFragment;
import com.pudutech.module_robot_selfcheck.p058ui.p059vm.SelfCheckViewModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KProperty;
import kotlin.text.Regex;

/* compiled from: ActiveRobotFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\rH\u0002J\u0012\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0002J\b\u0010\u0017\u001a\u00020\rH\u0002J\b\u0010\u0018\u001a\u00020\rH\u0002J\b\u0010\u0019\u001a\u00020\rH\u0002J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/fragment/ActiveRobotFragment;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseFragment;", "Lcom/pudutech/module_robot_selfcheck/ui/vm/SelfCheckViewModel;", "()V", "checkPwdDialog", "Lcom/pudutech/module_robot_selfcheck/ui/CheckPwdDialog;", "isDev", "", "lastHitTimestamp", "", "mDevHitCountdown", "", "createObserver", "", "getViewModel", "Lkotlin/Lazy;", "hidePro", "hideResultText", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "openDevMode", "setListeners", "showCheckPermissionDialog", "showPro", "showResultText", "t", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ActiveRobotFragment extends BaseFragment<SelfCheckViewModel> {
    private HashMap _$_findViewCache;
    private CheckPwdDialog checkPwdDialog;
    private boolean isDev;
    private long lastHitTimestamp;
    private int mDevHitCountdown = 17;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[InactiveType.NetworkUnconnected.ordinal()] = 1;
            $EnumSwitchMapping$0[InactiveType.TestingTimeout.ordinal()] = 2;
            $EnumSwitchMapping$0[InactiveType.RequestFailed.ordinal()] = 3;
            $EnumSwitchMapping$0[InactiveType.ServerInactive.ordinal()] = 4;
            $EnumSwitchMapping$0[InactiveType.StatusUnableActive.ordinal()] = 5;
            $EnumSwitchMapping$0[InactiveType.ExceptionRecord.ordinal()] = 6;
            $EnumSwitchMapping$0[InactiveType.NeedFrozen.ordinal()] = 7;
            $EnumSwitchMapping$0[InactiveType.ParamMissing.ordinal()] = 8;
            $EnumSwitchMapping$0[InactiveType.CodeNotFound.ordinal()] = 9;
            $EnumSwitchMapping$0[InactiveType.CodeHasUse.ordinal()] = 10;
            $EnumSwitchMapping$1 = new int[ActiveStatus.values().length];
            $EnumSwitchMapping$1[ActiveStatus.Active.ordinal()] = 1;
            $EnumSwitchMapping$1[ActiveStatus.Testing.ordinal()] = 2;
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

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public int layoutId() {
        return C5365R.layout.fragment_robot_actives;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void initView(Bundle savedInstanceState) {
        TextView input_code_tv = (TextView) _$_findCachedViewById(C5365R.id.input_code_tv);
        Intrinsics.checkExpressionValueIsNotNull(input_code_tv, "input_code_tv");
        input_code_tv.setMovementMethod(new ScrollingMovementMethod());
        TextView mac_tv = (TextView) _$_findCachedViewById(C5365R.id.mac_tv);
        Intrinsics.checkExpressionValueIsNotNull(mac_tv, "mac_tv");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C5365R.string.active_mac);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.active_mac)");
        Object[] objArr = {WifiUtil.INSTANCE.getMac()};
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        mac_tv.setText(format);
        ((TextView) _$_findCachedViewById(C5365R.id.mac_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRobotFragment.this.openDevMode();
            }
        });
        ((CNumberKeyPanel) _$_findCachedViewById(C5365R.id.panel_keyboard)).setMaxLength(8);
        setListeners();
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
            Pdlog.m3277w(LogExtKt.TAG, "go to debug activity");
            showCheckPermissionDialog();
            this.isDev = true;
        } else if (1 <= i2 && 4 >= i2) {
            Pdlog.m3277w(LogExtKt.TAG, "go to debug activity");
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
            final CheckPwdDialog checkPwdDialog = new CheckPwdDialog();
            checkPwdDialog.setOnVerifySuccessfulListener(new Function0<Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$showCheckPermissionDialog$1$1$1
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
                    Context context = CheckPwdDialog.this.getContext();
                    if (context != null) {
                        AppStartupExtKt.startDebugFunction(context, new Function1<String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$showCheckPermissionDialog$1$1$1.1
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
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                TipsDialog tipsDialog = new TipsDialog(it);
                                FragmentActivity activity = CheckPwdDialog.this.getActivity();
                                tipsDialog.showDialog(activity != null ? activity.getSupportFragmentManager() : null, LogExtKt.TAG);
                            }
                        });
                    }
                }
            });
            this.checkPwdDialog = checkPwdDialog;
        }
        CheckPwdDialog checkPwdDialog2 = this.checkPwdDialog;
        if (checkPwdDialog2 != null) {
            FragmentActivity activity = getActivity();
            checkPwdDialog2.showDialog(activity != null ? activity.getSupportFragmentManager() : null, LogExtKt.TAG);
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public Lazy<SelfCheckViewModel> getViewModel() {
        final int i = C5365R.id.nav_self_check;
        final KProperty kProperty = null;
        final Function0 function0 = (Function0) null;
        final Lazy lazy = LazyKt.lazy(new Function0<NavBackStackEntry>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$getViewModel$$inlined$navGraphViewModels$1
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
        return FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SelfCheckViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$getViewModel$$inlined$navGraphViewModels$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$getViewModel$$inlined$navGraphViewModels$3
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

    private final void setListeners() {
        ((CTextButton) _$_findCachedViewById(C5365R.id.active_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$setListeners$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRequest activeRequest;
                CTextButton active_btn = (CTextButton) ActiveRobotFragment.this._$_findCachedViewById(C5365R.id.active_btn);
                Intrinsics.checkExpressionValueIsNotNull(active_btn, "active_btn");
                active_btn.setClickable(false);
                TextView input_code_tv = (TextView) ActiveRobotFragment.this._$_findCachedViewById(C5365R.id.input_code_tv);
                Intrinsics.checkExpressionValueIsNotNull(input_code_tv, "input_code_tv");
                CharSequence code = input_code_tv.getText();
                Intrinsics.checkExpressionValueIsNotNull(code, "code");
                String replace = new Regex("\\s").replace(code, "");
                if (!(replace == null || replace.length() == 0) && replace.length() >= 8) {
                    ActiveRobotFragment.this.hideResultText();
                    ActiveRobotFragment.this.showPro();
                    SelfCheckViewModel mvm = ActiveRobotFragment.this.getMVM();
                    if (mvm == null || (activeRequest = mvm.getActiveRequest()) == null) {
                        return;
                    }
                    activeRequest.requestActiveByCode(replace.toString());
                    return;
                }
                ToastUtils toastUtils = ToastUtils.INSTANCE;
                String string = ActiveRobotFragment.this.getString(C5365R.string.sn_length_error);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.sn_length_error)");
                toastUtils.showShortToast(string);
                CTextButton active_btn2 = (CTextButton) ActiveRobotFragment.this._$_findCachedViewById(C5365R.id.active_btn);
                Intrinsics.checkExpressionValueIsNotNull(active_btn2, "active_btn");
                active_btn2.setClickable(true);
            }
        });
        ((CNumberKeyPanel) _$_findCachedViewById(C5365R.id.panel_keyboard)).setOnItemClickListener(new Function3<String, List<String>, Boolean, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$setListeners$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(String str, List<String> list, Boolean bool) {
                invoke(str, list, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, List<String> it2, boolean z) {
                Intrinsics.checkParameterIsNotNull(it2, "it2");
                String replace = new Regex("(.{4})").replace(CollectionsKt.joinToString$default(it2, "", null, null, 0, null, null, 62, null), "$1 ");
                TextView input_code_tv = (TextView) ActiveRobotFragment.this._$_findCachedViewById(C5365R.id.input_code_tv);
                Intrinsics.checkExpressionValueIsNotNull(input_code_tv, "input_code_tv");
                input_code_tv.setText(replace);
            }
        });
        ((ImageView) _$_findCachedViewById(C5365R.id.iv_password_clear)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$setListeners$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((CNumberKeyPanel) ActiveRobotFragment.this._$_findCachedViewById(C5365R.id.panel_keyboard)).clearAll();
                TextView input_code_tv = (TextView) ActiveRobotFragment.this._$_findCachedViewById(C5365R.id.input_code_tv);
                Intrinsics.checkExpressionValueIsNotNull(input_code_tv, "input_code_tv");
                input_code_tv.setText("");
            }
        });
        ((CTextButton) _$_findCachedViewById(C5365R.id.net_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$setListeners$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRobotFragment.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        });
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseFragment
    public void createObserver() {
        ActiveRequest activeRequest;
        SelfCheckViewModel mvm = getMVM();
        LifecycleExtKt.observe(this, (mvm == null || (activeRequest = mvm.getActiveRequest()) == null) ? null : activeRequest.getActiveStatusLD(), new Function1<ActiveStatusWrapInfo, Unit>() { // from class: com.pudutech.module_robot_selfcheck.ui.fragment.ActiveRobotFragment$createObserver$1
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
                String string;
                Pdlog.m3273d(LogExtKt.TAG, "activeRequest " + activeStatusWrapInfo);
                if (activeStatusWrapInfo == null) {
                    return;
                }
                ActiveRobotFragment.this.hidePro();
                CTextButton active_btn = (CTextButton) ActiveRobotFragment.this._$_findCachedViewById(C5365R.id.active_btn);
                Intrinsics.checkExpressionValueIsNotNull(active_btn, "active_btn");
                active_btn.setClickable(true);
                int i = ActiveRobotFragment.WhenMappings.$EnumSwitchMapping$1[activeStatusWrapInfo.getActiveState().ordinal()];
                if (i == 1 || i == 2) {
                    NavigationExtKt.nav(ActiveRobotFragment.this).navigate(C5365R.id.action_ActiveRobotFragment_to_activeSuccessFragment);
                    return;
                }
                InactiveType inactiveType = activeStatusWrapInfo.getInactiveType();
                if (inactiveType != null) {
                    switch (inactiveType) {
                        case NetworkUnconnected:
                            string = ActiveRobotFragment.this.getString(C5365R.string.pdStr1_12);
                            break;
                        case TestingTimeout:
                            string = ActiveRobotFragment.this.getString(C5365R.string.pdStr1_12);
                            break;
                        case RequestFailed:
                            string = ActiveRobotFragment.this.getString(C5365R.string.pdStr1_12);
                            break;
                        case ServerInactive:
                            string = ActiveRobotFragment.this.getString(C5365R.string.pdStr1_10);
                            break;
                        case StatusUnableActive:
                            string = ActiveRobotFragment.this.getString(C5365R.string.status_unable_active);
                            break;
                        case ExceptionRecord:
                            string = ActiveRobotFragment.this.getString(C5365R.string.exception_record);
                            break;
                        case NeedFrozen:
                            string = ActiveRobotFragment.this.getString(C5365R.string.need_frozen);
                            break;
                        case ParamMissing:
                            string = ActiveRobotFragment.this.getString(C5365R.string.pid_not_found);
                            break;
                        case CodeNotFound:
                            string = ActiveRobotFragment.this.getString(C5365R.string.code_not_found);
                            break;
                        case CodeHasUse:
                            string = ActiveRobotFragment.this.getString(C5365R.string.code_has_use);
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                } else {
                    string = ActiveRobotFragment.this.getString(C5365R.string.pdStr1_10);
                }
                Intrinsics.checkExpressionValueIsNotNull(string, "when (it.inactiveType) {…10)\n                    }");
                ActiveRobotFragment.this.showResultText(string);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showResultText(String t) {
        TextView active_result_tv = (TextView) _$_findCachedViewById(C5365R.id.active_result_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_result_tv, "active_result_tv");
        active_result_tv.setVisibility(0);
        TextView active_result_tv2 = (TextView) _$_findCachedViewById(C5365R.id.active_result_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_result_tv2, "active_result_tv");
        active_result_tv2.setText(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPro() {
        ProgressBar progress_pb = (ProgressBar) _$_findCachedViewById(C5365R.id.progress_pb);
        Intrinsics.checkExpressionValueIsNotNull(progress_pb, "progress_pb");
        progress_pb.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hidePro() {
        ProgressBar progress_pb = (ProgressBar) _$_findCachedViewById(C5365R.id.progress_pb);
        Intrinsics.checkExpressionValueIsNotNull(progress_pb, "progress_pb");
        progress_pb.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideResultText() {
        TextView active_result_tv = (TextView) _$_findCachedViewById(C5365R.id.active_result_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_result_tv, "active_result_tv");
        active_result_tv.setVisibility(8);
    }
}
