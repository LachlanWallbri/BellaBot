package com.pudutech.bumblebee.robot_ui.p054ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.activate_task.ActivateContract;
import com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestManager;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.InitAppManager;
import com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ValidationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.InputDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.disinfect.baselib.ext.util.StringExtKt;
import com.pudutech.disinfect.baselib.util.UiUtils;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: RobotActiveFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\b\u0010\u001d\u001a\u00020\u0010H\u0002J\b\u0010\u001e\u001a\u00020\u0010H\u0002J\b\u0010\u001f\u001a\u00020\u0010H\u0002J\"\u0010 \u001a\u00020\u00102\u0018\b\u0002\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010#\u0018\u00010\"H\u0002J&\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u0010H\u0016J\b\u0010-\u001a\u00020\u0010H\u0016J\u001a\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020%2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u00100\u001a\u00020\u0010H\u0002J\b\u00101\u001a\u00020\u0010H\u0002J\b\u00102\u001a\u00020\u0010H\u0002J\u001a\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0012\u00108\u001a\u00020\u00102\b\u00106\u001a\u0004\u0018\u000107H\u0002J\b\u00109\u001a\u00020\u0010H\u0002J\b\u0010:\u001a\u00020\u0010H\u0002J\b\u0010;\u001a\u00020\u0010H\u0002J\b\u0010<\u001a\u00020\u0010H\u0002J\u0010\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u00020\u0010H\u0002JA\u0010A\u001a\u00020\u00102%\b\u0002\u0010B\u001a\u001f\u0012\u0013\u0012\u00110?¢\u0006\f\bD\u0012\b\bE\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0010\u0018\u00010C2\u0010\b\u0002\u0010G\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fH\u0002J\b\u0010H\u001a\u00020\u0010H\u0002J\b\u0010I\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/fragment/RobotActiveFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$ViewInterface;", "()V", "TAG", "", "activatePresenter", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivatePresenter;", "getActivatePresenter", "()Lcom/pudutech/bumblebee/presenter/activate_task/ActivatePresenter;", "activatePresenter$delegate", "Lkotlin/Lazy;", "checkPermissionDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;", "onBackClick", "Lkotlin/Function0;", "", "getOnBackClick", "()Lkotlin/jvm/functions/Function0;", "setOnBackClick", "(Lkotlin/jvm/functions/Function0;)V", "touchJob", "Lkotlinx/coroutines/Job;", "bind", "cleanFactory", "disDialog", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "gotoSelfCheckActivity", "hideLoadingView", "initView", "jumpCheckAc", "pair", "Lkotlin/Pair;", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onStop", "onViewCreated", "view", "reloadMac", "showActiveCodeDialog", "showActiveLoading", "showActiveStatus", "status", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$RobotActiveStatus;", "info", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$ActiveRobotInfo;", "showInactive", "showInactiveNetError", "showInactiveServerError", "showInactiveServerStatus", "showLocalActiveFailed", "showLocalActiveResult", SpeechUtility.TAG_RESOURCE_RESULT, "", "showLocalActiveSuccess", "showPasswordDialog", "onPermissionCheckResult", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "onCancelResult", "showSuccess", "unbind", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RobotActiveFragment extends Fragment implements ActivateContract.ViewInterface {
    private HashMap _$_findViewCache;
    private ValidationDialog checkPermissionDialog;
    private Function0<Unit> onBackClick;
    private Job touchJob;
    private final String TAG = "RobotActiveFragment";

    /* renamed from: activatePresenter$delegate, reason: from kotlin metadata */
    private final Lazy activatePresenter = LazyKt.lazy(new Function0<ActivatePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$activatePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivatePresenter invoke() {
            ActivatePresenter activatePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(ActivatePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(ActivatePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                activatePresenter = new ActivatePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(ActivatePresenter.class).toString(), activatePresenter);
            } else {
                if (!(basePresenterInterface instanceof ActivatePresenter)) {
                    basePresenterInterface = null;
                }
                activatePresenter = (ActivatePresenter) basePresenterInterface;
            }
            if (activatePresenter != null) {
                return activatePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter");
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivatePresenter getActivatePresenter() {
        return (ActivatePresenter) this.activatePresenter.getValue();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

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

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final Function0<Unit> getOnBackClick() {
        return this.onBackClick;
    }

    public final void setOnBackClick(Function0<Unit> function0) {
        this.onBackClick = function0;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_robot_active, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        initView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        reloadMac();
        bind();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        unbind();
        disDialog();
    }

    private final void reloadMac() {
        String str;
        Pdlog.m3273d(this.TAG, "reloadMac: " + WifiUtil.INSTANCE.getMac());
        TextView mac_tv = (TextView) _$_findCachedViewById(C4188R.id.mac_tv);
        Intrinsics.checkExpressionValueIsNotNull(mac_tv, "mac_tv");
        String mac = WifiUtil.INSTANCE.getMac();
        Bitmap bitmap = null;
        if (mac != null) {
            String str2 = mac;
            if (str2.length() == 0) {
                str2 = "Null";
            }
            str = str2;
        } else {
            str = null;
        }
        mac_tv.setText(str);
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C4188R.id.active_bar_code_iv);
        String mac2 = WifiUtil.INSTANCE.getMac();
        if (mac2 != null) {
            String str3 = mac2;
            if (str3.length() == 0) {
                str3 = "Null";
            }
            String str4 = str3;
            if (str4 != null) {
                bitmap = StringExtKt.generateBarCode(str4, UiUtils.dip2px(813.0f), UiUtils.dip2px(117.0f));
            }
        }
        appCompatImageView.setImageBitmap(bitmap);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initView() {
        String str;
        Bitmap bitmap;
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initView.start ");
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        sb.append(resources.getConfiguration().locale);
        sb.append(' ');
        Pdlog.m3273d(str2, sb.toString());
        TextView mac_tv = (TextView) _$_findCachedViewById(C4188R.id.mac_tv);
        Intrinsics.checkExpressionValueIsNotNull(mac_tv, "mac_tv");
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac != null) {
            String str3 = mac;
            if (str3.length() == 0) {
                str3 = "Null";
            }
            str = str3;
        } else {
            str = null;
        }
        mac_tv.setText(str);
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(C4188R.id.active_bar_code_iv);
        String mac2 = WifiUtil.INSTANCE.getMac();
        if (mac2 != null) {
            String str4 = mac2;
            if (str4.length() == 0) {
                str4 = "Null";
            }
            String str5 = str4;
            if (str5 != null) {
                bitmap = StringExtKt.generateBarCode(str5, UiUtils.dip2px(813.0f), UiUtils.dip2px(117.0f));
                appCompatImageView.setImageBitmap(bitmap);
                TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
                Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
                title_tv.setText(getString(C4188R.string.pdStr1_15));
                LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new RobotActiveFragment$initView$3(this, null));
                ImageView loading_iv = (ImageView) _$_findCachedViewById(C4188R.id.loading_iv);
                Intrinsics.checkExpressionValueIsNotNull(loading_iv, "loading_iv");
                loading_iv.setVisibility(8);
                LinearLayout net_error_btn_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.net_error_btn_layout);
                Intrinsics.checkExpressionValueIsNotNull(net_error_btn_layout, "net_error_btn_layout");
                net_error_btn_layout.setVisibility(8);
                TextView active_tv = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
                Intrinsics.checkExpressionValueIsNotNull(active_tv, "active_tv");
                active_tv.setText(getString(C4188R.string.pdStr1_17));
                ((TextView) _$_findCachedViewById(C4188R.id.net_setting_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$initView$4
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(null, 0, 3, null);
                    }

                    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                    public void onSingleClick() {
                        super.onSingleClick();
                        RobotActiveFragment.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                    }
                });
                ((TextView) _$_findCachedViewById(C4188R.id.active_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$initView$5
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(null, 0, 3, null);
                    }

                    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                    public void onSingleClick() {
                        String str6;
                        ActivatePresenter activatePresenter;
                        super.onSingleClick();
                        str6 = RobotActiveFragment.this.TAG;
                        Pdlog.m3273d(str6, "active_tv onclick ");
                        if (AgentTestManager.INSTANCE.isHaveTestData()) {
                            RobotActiveFragment.this.cleanFactory();
                            return;
                        }
                        activatePresenter = RobotActiveFragment.this.getActivatePresenter();
                        Context context = RobotActiveFragment.this.getContext();
                        if (context == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                        activatePresenter.checkActiveStatus(context, true);
                        RobotActiveFragment.this.showActiveLoading();
                    }
                });
                if (!InitAppManager.INSTANCE.isNeedSetLanguage()) {
                    TextView back_tv = (TextView) _$_findCachedViewById(C4188R.id.back_tv);
                    Intrinsics.checkExpressionValueIsNotNull(back_tv, "back_tv");
                    ViewExtKt.gone(back_tv);
                }
                ((TextView) _$_findCachedViewById(C4188R.id.back_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$initView$6
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(null, 0, 3, null);
                    }

                    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                    public void onSingleClick() {
                        super.onSingleClick();
                        Function0<Unit> onBackClick = RobotActiveFragment.this.getOnBackClick();
                        if (onBackClick != null) {
                            onBackClick.invoke();
                        }
                    }
                });
                Pdlog.m3273d(this.TAG, "initView.end ");
            }
        }
        bitmap = null;
        appCompatImageView.setImageBitmap(bitmap);
        TextView title_tv2 = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv2, "title_tv");
        title_tv2.setText(getString(C4188R.string.pdStr1_15));
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new RobotActiveFragment$initView$3(this, null));
        ImageView loading_iv2 = (ImageView) _$_findCachedViewById(C4188R.id.loading_iv);
        Intrinsics.checkExpressionValueIsNotNull(loading_iv2, "loading_iv");
        loading_iv2.setVisibility(8);
        LinearLayout net_error_btn_layout2 = (LinearLayout) _$_findCachedViewById(C4188R.id.net_error_btn_layout);
        Intrinsics.checkExpressionValueIsNotNull(net_error_btn_layout2, "net_error_btn_layout");
        net_error_btn_layout2.setVisibility(8);
        TextView active_tv2 = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv2, "active_tv");
        active_tv2.setText(getString(C4188R.string.pdStr1_17));
        ((TextView) _$_findCachedViewById(C4188R.id.net_setting_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                RobotActiveFragment.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.active_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str6;
                ActivatePresenter activatePresenter;
                super.onSingleClick();
                str6 = RobotActiveFragment.this.TAG;
                Pdlog.m3273d(str6, "active_tv onclick ");
                if (AgentTestManager.INSTANCE.isHaveTestData()) {
                    RobotActiveFragment.this.cleanFactory();
                    return;
                }
                activatePresenter = RobotActiveFragment.this.getActivatePresenter();
                Context context = RobotActiveFragment.this.getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                activatePresenter.checkActiveStatus(context, true);
                RobotActiveFragment.this.showActiveLoading();
            }
        });
        if (!InitAppManager.INSTANCE.isNeedSetLanguage()) {
        }
        ((TextView) _$_findCachedViewById(C4188R.id.back_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$initView$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                Function0<Unit> onBackClick = RobotActiveFragment.this.getOnBackClick();
                if (onBackClick != null) {
                    onBackClick.invoke();
                }
            }
        });
        Pdlog.m3273d(this.TAG, "initView.end ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showActiveCodeDialog() {
        FragmentActivity requireActivity = requireActivity();
        if (requireActivity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()!!");
        final InputDialog inputDialog = new InputDialog(requireActivity);
        String string = getString(C4188R.string.pdStr1_20);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_20)");
        inputDialog.setTitle(string);
        String string2 = getString(C4188R.string.pdStr1_17);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr1_17)");
        inputDialog.setBtnText(string2);
        inputDialog.setOnInputResult(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showActiveCodeDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                ActivatePresenter activatePresenter;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!(it.length() == 0)) {
                    activatePresenter = RobotActiveFragment.this.getActivatePresenter();
                    activatePresenter.localActive(it);
                    RobotActiveFragment.this.showActiveLoading();
                    inputDialog.dismiss();
                    return;
                }
                Context requireContext = RobotActiveFragment.this.requireContext();
                if (requireContext == null) {
                    Intrinsics.throwNpe();
                }
                ToastUtils.show(requireContext, RobotActiveFragment.this.getString(C4188R.string.pdStr1_21), new Object[0]);
            }
        });
        inputDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showActiveLoading() {
        TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
        title_tv.setText(getString(C4188R.string.pdStr1_14));
        TextView active_tv = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv, "active_tv");
        active_tv.setVisibility(8);
        LinearLayout net_error_btn_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.net_error_btn_layout);
        Intrinsics.checkExpressionValueIsNotNull(net_error_btn_layout, "net_error_btn_layout");
        net_error_btn_layout.setVisibility(8);
        TextView net_setting_tv = (TextView) _$_findCachedViewById(C4188R.id.net_setting_tv);
        Intrinsics.checkExpressionValueIsNotNull(net_setting_tv, "net_setting_tv");
        net_setting_tv.setVisibility(8);
        ImageView loading_iv = (ImageView) _$_findCachedViewById(C4188R.id.loading_iv);
        Intrinsics.checkExpressionValueIsNotNull(loading_iv, "loading_iv");
        loading_iv.setVisibility(0);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(2000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        ((ImageView) _$_findCachedViewById(C4188R.id.loading_iv)).startAnimation(rotateAnimation);
    }

    private final void hideLoadingView() {
        ((ImageView) _$_findCachedViewById(C4188R.id.loading_iv)).clearAnimation();
        ImageView loading_iv = (ImageView) _$_findCachedViewById(C4188R.id.loading_iv);
        Intrinsics.checkExpressionValueIsNotNull(loading_iv, "loading_iv");
        loading_iv.setVisibility(8);
    }

    private final void bind() {
        getActivatePresenter().replaceView(this);
    }

    private final void unbind() {
        getActivatePresenter().removeView(this);
    }

    @Override // com.pudutech.bumblebee.presenter.activate_task.ActivateContract.ViewInterface
    public void showActiveStatus(ActivateContract.RobotActiveStatus status, ActivateContract.ActiveRobotInfo info) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3273d(this.TAG, "showActiveStatus : status = " + status + "; info = " + info);
        hideLoadingView();
        if (status == ActivateContract.RobotActiveStatus.ACTIVE || status == ActivateContract.RobotActiveStatus.TESTING) {
            showSuccess();
        } else if (status == ActivateContract.RobotActiveStatus.INACTIVE) {
            showInactive(info);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.activate_task.ActivateContract.ViewInterface
    public void showLocalActiveResult(boolean result) {
        Pdlog.m3273d(this.TAG, "showLocalActiveResult : result = " + result + "; ");
        hideLoadingView();
        if (result) {
            showLocalActiveSuccess();
        } else {
            showLocalActiveFailed();
        }
    }

    private final void showLocalActiveSuccess() {
        Pdlog.m3273d(this.TAG, "showLocalActiveSuccess ");
        TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
        title_tv.setText(getString(C4188R.string.pdStr1_22));
        TextView active_tv = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv, "active_tv");
        active_tv.setVisibility(0);
        TextView net_setting_tv = (TextView) _$_findCachedViewById(C4188R.id.net_setting_tv);
        Intrinsics.checkExpressionValueIsNotNull(net_setting_tv, "net_setting_tv");
        net_setting_tv.setVisibility(0);
        LinearLayout net_error_btn_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.net_error_btn_layout);
        Intrinsics.checkExpressionValueIsNotNull(net_error_btn_layout, "net_error_btn_layout");
        net_error_btn_layout.setVisibility(8);
        TextView active_tv2 = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv2, "active_tv");
        active_tv2.setText(getString(C4188R.string.pdStr8_13));
        ((TextView) _$_findCachedViewById(C4188R.id.active_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showLocalActiveSuccess$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                RobotActiveFragment.jumpCheckAc$default(RobotActiveFragment.this, null, 1, null);
            }
        }, 3, null));
    }

    private final void showLocalActiveFailed() {
        Pdlog.m3273d(this.TAG, "showLocalActiveFailed ");
        TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
        title_tv.setText(getString(C4188R.string.pdStr1_23));
        TextView active_tv = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv, "active_tv");
        active_tv.setVisibility(8);
        TextView net_setting_tv = (TextView) _$_findCachedViewById(C4188R.id.net_setting_tv);
        Intrinsics.checkExpressionValueIsNotNull(net_setting_tv, "net_setting_tv");
        net_setting_tv.setVisibility(0);
        LinearLayout net_error_btn_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.net_error_btn_layout);
        Intrinsics.checkExpressionValueIsNotNull(net_error_btn_layout, "net_error_btn_layout");
        net_error_btn_layout.setVisibility(0);
        TextView active_code_tv = (TextView) _$_findCachedViewById(C4188R.id.active_code_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_code_tv, "active_code_tv");
        active_code_tv.setText(getString(C4188R.string.pdStr1_24));
        ((TextView) _$_findCachedViewById(C4188R.id.active_code_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showLocalActiveFailed$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                ActivatePresenter activatePresenter;
                Intrinsics.checkParameterIsNotNull(it, "it");
                activatePresenter = RobotActiveFragment.this.getActivatePresenter();
                Context requireContext = RobotActiveFragment.this.requireContext();
                if (requireContext == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()!!");
                activatePresenter.checkActiveStatus(requireContext, true);
                RobotActiveFragment.this.showActiveLoading();
            }
        }, 3, null));
        ((TextView) _$_findCachedViewById(C4188R.id.active_code_retry_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showLocalActiveFailed$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                RobotActiveFragment.this.showActiveCodeDialog();
            }
        }, 3, null));
    }

    private final void showInactive(ActivateContract.ActiveRobotInfo info) {
        if (info == null) {
            showInactiveServerStatus();
            return;
        }
        if (info.getInactiveType() == ActivateContract.InactiveType.WIFI_NOT_CONNECT) {
            showInactiveNetError();
            return;
        }
        if (info.getInactiveType() == ActivateContract.InactiveType.TESTING_TIMEOUT) {
            showInactiveServerStatus();
            return;
        }
        if (info.getInactiveType() == ActivateContract.InactiveType.SERVER_STATUS) {
            showInactiveServerStatus();
        } else if (info.getInactiveType() == ActivateContract.InactiveType.REQUEST_ERROR) {
            showInactiveNetError();
        } else {
            showInactiveServerStatus();
        }
    }

    private final void showInactiveServerStatus() {
        TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
        title_tv.setText(getString(C4188R.string.pdStr1_10));
        LinearLayout net_error_btn_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.net_error_btn_layout);
        Intrinsics.checkExpressionValueIsNotNull(net_error_btn_layout, "net_error_btn_layout");
        net_error_btn_layout.setVisibility(8);
        TextView net_setting_tv = (TextView) _$_findCachedViewById(C4188R.id.net_setting_tv);
        Intrinsics.checkExpressionValueIsNotNull(net_setting_tv, "net_setting_tv");
        net_setting_tv.setVisibility(0);
        TextView active_tv = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv, "active_tv");
        active_tv.setVisibility(0);
        TextView active_tv2 = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv2, "active_tv");
        active_tv2.setText(getString(C4188R.string.pdStr1_11));
    }

    private final void showInactiveNetError() {
        TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
        title_tv.setText(getString(C4188R.string.pdStr1_12));
        LinearLayout net_error_btn_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.net_error_btn_layout);
        Intrinsics.checkExpressionValueIsNotNull(net_error_btn_layout, "net_error_btn_layout");
        net_error_btn_layout.setVisibility(8);
        TextView net_setting_tv = (TextView) _$_findCachedViewById(C4188R.id.net_setting_tv);
        Intrinsics.checkExpressionValueIsNotNull(net_setting_tv, "net_setting_tv");
        net_setting_tv.setVisibility(0);
        TextView active_tv = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv, "active_tv");
        active_tv.setVisibility(0);
        TextView active_tv2 = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv2, "active_tv");
        active_tv2.setText(getString(C4188R.string.pdStr1_11));
    }

    private final void showInactiveServerError() {
        String string = getString(C4188R.string.pdStr1_13);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_13)");
        try {
            int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) string, "\n", 0, false, 6, (Object) null);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, 0, 30, null, null);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            spannableStringBuilder.setSpan(textAppearanceSpan, lastIndexOf$default, string.length(), 34);
            TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
            Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
            title_tv.setText(spannableStringBuilder);
        } catch (Exception unused) {
            TextView title_tv2 = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
            Intrinsics.checkExpressionValueIsNotNull(title_tv2, "title_tv");
            title_tv2.setText(string);
        }
        LinearLayout net_error_btn_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.net_error_btn_layout);
        Intrinsics.checkExpressionValueIsNotNull(net_error_btn_layout, "net_error_btn_layout");
        net_error_btn_layout.setVisibility(0);
        TextView net_setting_tv = (TextView) _$_findCachedViewById(C4188R.id.net_setting_tv);
        Intrinsics.checkExpressionValueIsNotNull(net_setting_tv, "net_setting_tv");
        net_setting_tv.setVisibility(0);
        TextView active_tv = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv, "active_tv");
        active_tv.setVisibility(8);
        TextView active_code_tv = (TextView) _$_findCachedViewById(C4188R.id.active_code_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_code_tv, "active_code_tv");
        active_code_tv.setText(getString(C4188R.string.pdStr1_19));
        ((TextView) _$_findCachedViewById(C4188R.id.active_code_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showInactiveServerError$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = RobotActiveFragment.this.TAG;
                Pdlog.m3273d(str, "active_code_tv OnClick");
                RobotActiveFragment.this.showActiveCodeDialog();
            }
        }, 3, null));
        ((TextView) _$_findCachedViewById(C4188R.id.active_code_retry_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showInactiveServerError$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                ActivatePresenter activatePresenter;
                Intrinsics.checkParameterIsNotNull(it, "it");
                activatePresenter = RobotActiveFragment.this.getActivatePresenter();
                Context requireContext = RobotActiveFragment.this.requireContext();
                if (requireContext == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()!!");
                activatePresenter.checkActiveStatus(requireContext, true);
                RobotActiveFragment.this.showActiveLoading();
            }
        }, 3, null));
    }

    private final void showSuccess() {
        TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
        title_tv.setText(getString(C4188R.string.pdStr1_9));
        TextView active_tv = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv, "active_tv");
        active_tv.setText(getString(C4188R.string.pdStr8_13));
        TextView active_tv2 = (TextView) _$_findCachedViewById(C4188R.id.active_tv);
        Intrinsics.checkExpressionValueIsNotNull(active_tv2, "active_tv");
        active_tv2.setVisibility(0);
        TextView net_setting_tv = (TextView) _$_findCachedViewById(C4188R.id.net_setting_tv);
        Intrinsics.checkExpressionValueIsNotNull(net_setting_tv, "net_setting_tv");
        net_setting_tv.setVisibility(8);
        ((TextView) _$_findCachedViewById(C4188R.id.active_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showSuccess$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                RobotActiveFragment.jumpCheckAc$default(RobotActiveFragment.this, null, 1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void jumpCheckAc$default(RobotActiveFragment robotActiveFragment, Pair pair, int i, Object obj) {
        if ((i & 1) != 0) {
            pair = (Pair) null;
        }
        robotActiveFragment.jumpCheckAc(pair);
    }

    private final void jumpCheckAc(Pair<String, ? extends Object> pair) {
        Pdlog.m3273d(this.TAG, "jumpCheckAc pair = " + pair);
        Context context = getContext();
        if (context != null) {
            if (context != null) {
                Activity activity = (Activity) context;
                if (pair != null) {
                    AnkoInternals.internalStartActivity(context, SelfCheckActivity.class, new Pair[]{pair});
                } else {
                    AnkoInternals.internalStartActivity(context, SelfCheckActivity.class, new Pair[0]);
                }
                activity.finish();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
    }

    public final void dispatchTouchEvent(MotionEvent event) {
        Job launch$default;
        Integer valueOf = event != null ? Integer.valueOf(event.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 5) {
            if (this.touchJob == null) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotActiveFragment$dispatchTouchEvent$1(this, null), 3, null);
                this.touchJob = launch$default;
                return;
            }
            return;
        }
        if ((valueOf != null && valueOf.intValue() == 6) || (valueOf != null && valueOf.intValue() == 3)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotActiveFragment$dispatchTouchEvent$2(this, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void gotoSelfCheckActivity() {
        jumpCheckAc(new Pair<>(AgentTestManager.KEY_AGENT_TEST_DATA, true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cleanFactory() {
        AgentTestManager agentTestManager = AgentTestManager.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        agentTestManager.cleanFactory(requireContext, CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showPasswordDialog$default(RobotActiveFragment robotActiveFragment, Function1 function1, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function0 = (Function0) null;
        }
        robotActiveFragment.showPasswordDialog(function1, function0);
    }

    private final void showPasswordDialog(final Function1<? super Boolean, Unit> onPermissionCheckResult, final Function0<Unit> onCancelResult) {
        if (this.checkPermissionDialog == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.checkPermissionDialog = new ValidationDialog(requireContext);
        }
        ValidationDialog validationDialog = this.checkPermissionDialog;
        if (validationDialog != null) {
            validationDialog.setMPasswordConfig(CollectionsKt.arrayListOf(Constans.PWD_TEST_MODE));
            validationDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showPasswordDialog$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Function1 function1 = Function1.this;
                    if (function1 != null) {
                    }
                }
            });
            validationDialog.setMClickCall(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$showPasswordDialog$$inlined$apply$lambda$2
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
                    Function0 function0 = onCancelResult;
                    if (function0 != null) {
                    }
                }
            });
            if (validationDialog.isShowing()) {
                return;
            }
            validationDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disDialog() {
        ValidationDialog validationDialog = this.checkPermissionDialog;
        if (validationDialog == null || !validationDialog.isShowing()) {
            return;
        }
        validationDialog.dismiss();
    }
}
