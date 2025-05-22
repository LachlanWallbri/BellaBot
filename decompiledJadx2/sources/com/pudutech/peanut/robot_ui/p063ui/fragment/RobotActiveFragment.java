package com.pudutech.peanut.robot_ui.p063ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.preference.MMKVPreference;
import com.pudutech.disinfect.baselib.widget.CNumberKeyPanel;
import com.pudutech.disinfect.baselib.widget.CTextButton;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.peanut.presenter.PresenterHolder;
import com.pudutech.peanut.presenter.activate_task.ActivateContract;
import com.pudutech.peanut.presenter.activate_task.ActivatePresenter;
import com.pudutech.peanut.presenter.net.req.Data;
import com.pudutech.peanut.presenter.net.req.RobotActiveReq;
import com.pudutech.peanut.presenter.utils.WifiUtil;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LockMachineDialog;
import com.pudutech.peanut.robot_ui.p063ui.wifi.WifiConnectActivity;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: RobotActiveFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010L\u001a\u00020>H\u0002J\b\u0010M\u001a\u00020>H\u0002J\b\u0010N\u001a\u00020>H\u0002J\b\u0010O\u001a\u00020>H\u0002J&\u0010P\u001a\u0004\u0018\u00010Q2\u0006\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010U2\b\u0010V\u001a\u0004\u0018\u00010WH\u0016J\b\u0010X\u001a\u00020>H\u0016J\u001a\u0010Y\u001a\u00020>2\u0006\u0010Z\u001a\u00020Q2\b\u0010V\u001a\u0004\u0018\u00010WH\u0016J\u0010\u0010[\u001a\u00020>2\u0006\u0010\\\u001a\u00020\u0005H\u0002J\b\u0010]\u001a\u00020>H\u0002J\u001a\u0010^\u001a\u00020>2\u0006\u0010_\u001a\u00020`2\b\u0010a\u001a\u0004\u0018\u00010bH\u0016J\b\u0010c\u001a\u00020>H\u0002J\b\u0010d\u001a\u00020>H\u0002J\u0012\u0010e\u001a\u00020>2\b\u0010a\u001a\u0004\u0018\u00010bH\u0002J\b\u0010f\u001a\u00020>H\u0002J\u0010\u0010g\u001a\u00020>2\u0006\u0010h\u001a\u00020+H\u0016J\b\u0010i\u001a\u00020>H\u0002J\b\u0010j\u001a\u00020>H\u0002J\b\u0010k\u001a\u00020>H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011R\u001c\u0010!\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011R\u001c\u0010$\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u001c\u0010'\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u000f\"\u0004\b)\u0010\u0011R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u00103\u001a\u0004\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00100\"\u0004\b5\u00102R\u001c\u00106\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u000f\"\u0004\b8\u0010\u0011R\u001c\u00109\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u000f\"\u0004\b;\u0010\u0011R\"\u0010<\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010=X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001c\u0010I\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u000f\"\u0004\bK\u0010\u0011¨\u0006l"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/RobotActiveFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ViewInterface;", "()V", "TAG", "", "activatePresenter", "Lcom/pudutech/peanut/presenter/activate_task/ActivatePresenter;", "getActivatePresenter", "()Lcom/pudutech/peanut/presenter/activate_task/ActivatePresenter;", "activatePresenter$delegate", "Lkotlin/Lazy;", "active_code_fail", "Landroid/widget/TextView;", "getActive_code_fail", "()Landroid/widget/TextView;", "setActive_code_fail", "(Landroid/widget/TextView;)V", "active_code_llt", "Landroid/widget/LinearLayout;", "getActive_code_llt", "()Landroid/widget/LinearLayout;", "setActive_code_llt", "(Landroid/widget/LinearLayout;)V", "active_code_tv", "Lcom/pudutech/disinfect/baselib/widget/CTextButton;", "getActive_code_tv", "()Lcom/pudutech/disinfect/baselib/widget/CTextButton;", "setActive_code_tv", "(Lcom/pudutech/disinfect/baselib/widget/CTextButton;)V", "active_tv", "getActive_tv", "setActive_tv", "back_tv", "getBack_tv", "setBack_tv", "code_net_setting_tv", "getCode_net_setting_tv", "setCode_net_setting_tv", "input_code_tv", "getInput_code_tv", "setInput_code_tv", "isCodeActive", "", "isGetActiveState", "iv_password_clear", "Landroid/widget/ImageView;", "getIv_password_clear", "()Landroid/widget/ImageView;", "setIv_password_clear", "(Landroid/widget/ImageView;)V", "loading_iv", "getLoading_iv", "setLoading_iv", "mac_tv", "getMac_tv", "setMac_tv", "net_setting_tv", "getNet_setting_tv", "setNet_setting_tv", "onBackClick", "Lkotlin/Function0;", "", "getOnBackClick", "()Lkotlin/jvm/functions/Function0;", "setOnBackClick", "(Lkotlin/jvm/functions/Function0;)V", "panel_keyboard", "Lcom/pudutech/disinfect/baselib/widget/CNumberKeyPanel;", "getPanel_keyboard", "()Lcom/pudutech/disinfect/baselib/widget/CNumberKeyPanel;", "setPanel_keyboard", "(Lcom/pudutech/disinfect/baselib/widget/CNumberKeyPanel;)V", "title_tv", "getTitle_tv", "setTitle_tv", "bind", "hideAll", "initView", "jumpCheckAc", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onViewCreated", "view", "showActiveCode", "text", "showActiveLoading", "showActiveStatus", "status", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$RobotActiveStatus;", "info", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ActiveRobotInfo;", "showActiveSuccess", "showFirstNet", "showInactive", "showInactiveNetError", "showLocalActiveResult", SpeechUtility.TAG_RESOURCE_RESULT, "showLockDialog", "showSuccess", "unbind", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotActiveFragment extends Fragment implements ActivateContract.ViewInterface {
    private HashMap _$_findViewCache;
    private TextView active_code_fail;
    private LinearLayout active_code_llt;
    private CTextButton active_code_tv;
    private TextView active_tv;
    private TextView back_tv;
    private TextView code_net_setting_tv;
    private TextView input_code_tv;
    private boolean isCodeActive;
    private ImageView iv_password_clear;
    private ImageView loading_iv;
    private TextView mac_tv;
    private TextView net_setting_tv;
    private Function0<Unit> onBackClick;
    private CNumberKeyPanel panel_keyboard;
    private TextView title_tv;
    private final String TAG = "RobotActiveFragment";

    /* renamed from: activatePresenter$delegate, reason: from kotlin metadata */
    private final Lazy activatePresenter = LazyKt.lazy(new Function0<ActivatePresenter>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.RobotActiveFragment$activatePresenter$2
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
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.presenter.activate_task.ActivatePresenter");
        }
    });
    private boolean isGetActiveState = true;

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

    public final TextView getBack_tv() {
        return this.back_tv;
    }

    public final void setBack_tv(TextView textView) {
        this.back_tv = textView;
    }

    public final TextView getMac_tv() {
        return this.mac_tv;
    }

    public final void setMac_tv(TextView textView) {
        this.mac_tv = textView;
    }

    public final TextView getTitle_tv() {
        return this.title_tv;
    }

    public final void setTitle_tv(TextView textView) {
        this.title_tv = textView;
    }

    public final ImageView getLoading_iv() {
        return this.loading_iv;
    }

    public final void setLoading_iv(ImageView imageView) {
        this.loading_iv = imageView;
    }

    public final TextView getNet_setting_tv() {
        return this.net_setting_tv;
    }

    public final void setNet_setting_tv(TextView textView) {
        this.net_setting_tv = textView;
    }

    public final TextView getActive_tv() {
        return this.active_tv;
    }

    public final void setActive_tv(TextView textView) {
        this.active_tv = textView;
    }

    public final TextView getInput_code_tv() {
        return this.input_code_tv;
    }

    public final void setInput_code_tv(TextView textView) {
        this.input_code_tv = textView;
    }

    public final ImageView getIv_password_clear() {
        return this.iv_password_clear;
    }

    public final void setIv_password_clear(ImageView imageView) {
        this.iv_password_clear = imageView;
    }

    public final CNumberKeyPanel getPanel_keyboard() {
        return this.panel_keyboard;
    }

    public final void setPanel_keyboard(CNumberKeyPanel cNumberKeyPanel) {
        this.panel_keyboard = cNumberKeyPanel;
    }

    public final TextView getActive_code_fail() {
        return this.active_code_fail;
    }

    public final void setActive_code_fail(TextView textView) {
        this.active_code_fail = textView;
    }

    public final LinearLayout getActive_code_llt() {
        return this.active_code_llt;
    }

    public final void setActive_code_llt(LinearLayout linearLayout) {
        this.active_code_llt = linearLayout;
    }

    public final CTextButton getActive_code_tv() {
        return this.active_code_tv;
    }

    public final void setActive_code_tv(CTextButton cTextButton) {
        this.active_code_tv = cTextButton;
    }

    public final TextView getCode_net_setting_tv() {
        return this.code_net_setting_tv;
    }

    public final void setCode_net_setting_tv(TextView textView) {
        this.code_net_setting_tv = textView;
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
        return inflater.inflate(C5508R.layout.fragment_robot_active, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        bind();
        initView();
    }

    private final void initView() {
        View view = getView();
        if (view != null) {
            this.back_tv = (TextView) view.findViewById(C5508R.id.back_tv);
            this.mac_tv = (TextView) view.findViewById(C5508R.id.mac_tv);
            this.title_tv = (TextView) view.findViewById(C5508R.id.title_tv);
            this.loading_iv = (ImageView) view.findViewById(C5508R.id.loading_iv);
            this.net_setting_tv = (TextView) view.findViewById(C5508R.id.net_setting_tv);
            this.active_tv = (TextView) view.findViewById(C5508R.id.active_tv);
            this.input_code_tv = (TextView) view.findViewById(C5508R.id.input_code_tv);
            this.iv_password_clear = (ImageView) view.findViewById(C5508R.id.iv_password_clear);
            this.panel_keyboard = (CNumberKeyPanel) view.findViewById(C5508R.id.panel_keyboard);
            this.active_code_fail = (TextView) view.findViewById(C5508R.id.active_code_fail);
            this.active_code_llt = (LinearLayout) view.findViewById(C5508R.id.active_code_llt);
            this.active_code_tv = (CTextButton) view.findViewById(C5508R.id.active_code_tv);
            this.code_net_setting_tv = (TextView) view.findViewById(C5508R.id.code_net_setting_tv);
        }
        if (getContext() != null) {
            hideAll();
            WifiUtil wifiUtil = WifiUtil.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            if (wifiUtil.isNetworkAvailable(requireContext)) {
                showActiveLoading();
                ActivatePresenter activatePresenter = getActivatePresenter();
                Context requireContext2 = requireContext();
                Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
                activatePresenter.checkActiveStatus(requireContext2);
            } else {
                showFirstNet();
            }
            TextView textView = this.back_tv;
            if (textView != null) {
                textView.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.RobotActiveFragment$initView$2
                    @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
                    public void onSingleClick() {
                        super.onSingleClick();
                        Function0<Unit> onBackClick = RobotActiveFragment.this.getOnBackClick();
                        if (onBackClick != null) {
                            onBackClick.invoke();
                        }
                    }
                });
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getContext() != null) {
            WifiUtil wifiUtil = WifiUtil.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            if (wifiUtil.isNetworkAvailable(requireContext)) {
                showActiveLoading();
                ActivatePresenter activatePresenter = getActivatePresenter();
                Context requireContext2 = requireContext();
                Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
                activatePresenter.checkActiveStatus(requireContext2);
                return;
            }
            showFirstNet();
        }
    }

    private final void showFirstNet() {
        hideAll();
        TextView textView = this.back_tv;
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = this.net_setting_tv;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = this.mac_tv;
        if (textView3 != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C5508R.string.pdStr1_16);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_16)");
            Object[] objArr = {WifiUtil.INSTANCE.getMac()};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            textView3.setText(format);
        }
        TextView textView4 = this.title_tv;
        if (textView4 != null) {
            textView4.setText(getString(C5508R.string.pdStr1_15));
        }
        TextView textView5 = this.title_tv;
        if (textView5 != null) {
            textView5.setVisibility(0);
        }
        TextView textView6 = this.net_setting_tv;
        if (textView6 != null) {
            textView6.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.RobotActiveFragment$showFirstNet$1
                @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
                public void onSingleClick() {
                    super.onSingleClick();
                    RobotActiveFragment.this.startActivity(new Intent(RobotActiveFragment.this.getActivity(), (Class<?>) WifiConnectActivity.class));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showActiveCode(String text) {
        TextView textView;
        if (getContext() != null) {
            hideAll();
            LinearLayout linearLayout = this.active_code_llt;
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            TextView textView2 = this.input_code_tv;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            TextView textView3 = this.input_code_tv;
            if (textView3 != null) {
                textView3.setMovementMethod(new ScrollingMovementMethod());
            }
            CNumberKeyPanel cNumberKeyPanel = this.panel_keyboard;
            if (cNumberKeyPanel != null) {
                cNumberKeyPanel.setVisibility(0);
            }
            if (!this.isGetActiveState && (textView = this.active_code_fail) != null) {
                textView.setVisibility(0);
            }
            TextView textView4 = this.code_net_setting_tv;
            if (textView4 != null) {
                textView4.setVisibility(0);
            }
            TextView textView5 = this.code_net_setting_tv;
            if (textView5 != null) {
                textView5.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.RobotActiveFragment$showActiveCode$1
                    @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
                    public void onSingleClick() {
                        super.onSingleClick();
                        RobotActiveFragment.this.startActivity(new Intent(RobotActiveFragment.this.getActivity(), (Class<?>) WifiConnectActivity.class));
                    }
                });
            }
            CTextButton cTextButton = this.active_code_tv;
            if (cTextButton != null) {
                final CTextButton cTextButton2 = cTextButton;
                final long j = 800;
                cTextButton2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.RobotActiveFragment$showActiveCode$$inlined$singleClick$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivatePresenter activatePresenter;
                        ActivatePresenter activatePresenter2;
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - ViewExtKt.getLastClickTime(cTextButton2) > j || (cTextButton2 instanceof Checkable)) {
                            ViewExtKt.setLastClickTime(cTextButton2, currentTimeMillis);
                            CTextButton active_code_tv = this.getActive_code_tv();
                            if (active_code_tv != null) {
                                active_code_tv.setClickable(false);
                            }
                            TextView input_code_tv = this.getInput_code_tv();
                            CharSequence text2 = input_code_tv != null ? input_code_tv.getText() : null;
                            if ((text2 == null || text2.length() == 0) || text2.length() < 8) {
                                ToastUtils.show(this.requireContext(), this.getString(C5508R.string.sn_code_hint), new Object[0]);
                                CTextButton active_code_tv2 = this.getActive_code_tv();
                                if (active_code_tv2 != null) {
                                    active_code_tv2.setClickable(true);
                                }
                            } else {
                                WifiUtil wifiUtil = WifiUtil.INSTANCE;
                                Context requireContext = this.requireContext();
                                Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
                                if (!wifiUtil.isNetworkAvailable(requireContext)) {
                                    RobotActiveFragment robotActiveFragment = this;
                                    String string = robotActiveFragment.getString(C5508R.string.pdStr1_12);
                                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_12)");
                                    robotActiveFragment.showActiveCode(string);
                                    CTextButton active_code_tv3 = this.getActive_code_tv();
                                    if (active_code_tv3 != null) {
                                        active_code_tv3.setClickable(true);
                                    }
                                } else {
                                    RobotActiveReq robotActiveReq = new RobotActiveReq();
                                    Data data = new Data();
                                    data.setCode(text2.toString());
                                    data.setName("小花生");
                                    activatePresenter = this.getActivatePresenter();
                                    data.setPid(activatePresenter.getPID());
                                    robotActiveReq.setData(data);
                                    this.isCodeActive = true;
                                    this.isGetActiveState = false;
                                    activatePresenter2 = this.getActivatePresenter();
                                    activatePresenter2.robotActiveReq(robotActiveReq);
                                }
                            }
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                        }
                    }
                });
            }
            CNumberKeyPanel cNumberKeyPanel2 = this.panel_keyboard;
            if (cNumberKeyPanel2 != null) {
                cNumberKeyPanel2.setMaxLength(8);
            }
            CNumberKeyPanel cNumberKeyPanel3 = this.panel_keyboard;
            if (cNumberKeyPanel3 != null) {
                cNumberKeyPanel3.setOnItemClickListener(new Function3<String, List<String>, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.RobotActiveFragment$showActiveCode$3
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
                        TextView input_code_tv = RobotActiveFragment.this.getInput_code_tv();
                        if (input_code_tv != null) {
                            input_code_tv.setText(CollectionsKt.joinToString$default(it2, "", null, null, 0, null, null, 62, null));
                        }
                    }
                });
            }
            ImageView imageView = this.iv_password_clear;
            if (imageView != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.RobotActiveFragment$showActiveCode$4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CNumberKeyPanel panel_keyboard = RobotActiveFragment.this.getPanel_keyboard();
                        if (panel_keyboard != null) {
                            panel_keyboard.clearAll();
                        }
                        TextView input_code_tv = RobotActiveFragment.this.getInput_code_tv();
                        if (input_code_tv != null) {
                            input_code_tv.setText("");
                        }
                        TextView active_code_fail = RobotActiveFragment.this.getActive_code_fail();
                        if (active_code_fail != null) {
                            active_code_fail.setText("");
                        }
                    }
                });
            }
            TextView textView6 = this.active_code_fail;
            if (textView6 != null) {
                textView6.setText(text);
            }
        }
    }

    private final void showInactiveNetError() {
        TextView textView = this.title_tv;
        if (textView != null) {
            textView.setText(getString(C5508R.string.pdStr1_12));
        }
        LinearLayout linearLayout = this.active_code_llt;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        TextView textView2 = this.net_setting_tv;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
    }

    private final void showActiveLoading() {
        hideAll();
        TextView textView = this.title_tv;
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = this.title_tv;
        if (textView2 != null) {
            textView2.setText(getString(C5508R.string.pdStr1_14));
        }
        ImageView imageView = this.loading_iv;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(2000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        ImageView imageView2 = this.loading_iv;
        if (imageView2 != null) {
            imageView2.startAnimation(rotateAnimation);
        }
    }

    private final void hideAll() {
        TextView textView = this.back_tv;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.mac_tv;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        TextView textView3 = this.title_tv;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
        ImageView imageView = this.loading_iv;
        if (imageView != null) {
            imageView.clearAnimation();
        }
        ImageView imageView2 = this.loading_iv;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextView textView4 = this.net_setting_tv;
        if (textView4 != null) {
            textView4.setVisibility(8);
        }
        TextView textView5 = this.input_code_tv;
        if (textView5 != null) {
            textView5.setVisibility(8);
        }
        CNumberKeyPanel cNumberKeyPanel = this.panel_keyboard;
        if (cNumberKeyPanel != null) {
            cNumberKeyPanel.setVisibility(8);
        }
        TextView textView6 = this.active_code_fail;
        if (textView6 != null) {
            textView6.setVisibility(8);
        }
        LinearLayout linearLayout = this.active_code_llt;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        TextView textView7 = this.active_tv;
        if (textView7 != null) {
            textView7.setVisibility(8);
        }
        TextView textView8 = this.code_net_setting_tv;
        if (textView8 != null) {
            textView8.setVisibility(8);
        }
    }

    private final void bind() {
        getActivatePresenter().replaceView(this);
    }

    private final void unbind() {
        getActivatePresenter().removeView(this);
    }

    @Override // com.pudutech.peanut.presenter.activate_task.ActivateContract.ViewInterface
    public void showActiveStatus(ActivateContract.RobotActiveStatus status, ActivateContract.ActiveRobotInfo info) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3273d(this.TAG, "showActiveStatus : status = " + status + "; info = " + info);
        if (getContext() != null) {
            if (status == ActivateContract.RobotActiveStatus.ACTIVE || status == ActivateContract.RobotActiveStatus.TESTING) {
                if (this.isCodeActive) {
                    showSuccess();
                    return;
                } else {
                    jumpCheckAc();
                    return;
                }
            }
            if (status == ActivateContract.RobotActiveStatus.INACTIVE) {
                MirSdkManager.INSTANCE.setActive(false);
                this.isCodeActive = false;
                showInactive(info);
            }
        }
    }

    @Override // com.pudutech.peanut.presenter.activate_task.ActivateContract.ViewInterface
    public void showLocalActiveResult(boolean result) {
        Pdlog.m3273d(this.TAG, "showLocalActiveResult : result = " + result + "; ");
        if (result) {
            showActiveSuccess();
        }
    }

    private final void showActiveSuccess() {
        if (getContext() != null) {
            Pdlog.m3273d(this.TAG, "showLocalActiveSuccess ");
            TextView textView = this.title_tv;
            if (textView != null) {
                textView.setText(getString(C5508R.string.pdStr1_22));
            }
            TextView textView2 = this.net_setting_tv;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            LinearLayout linearLayout = this.active_code_llt;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
        }
    }

    private final void showInactive(ActivateContract.ActiveRobotInfo info) {
        if (getContext() != null) {
            CTextButton cTextButton = this.active_code_tv;
            if (cTextButton != null) {
                cTextButton.setClickable(true);
            }
            if (info == null) {
                String string = getString(C5508R.string.pdStr1_10);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_10)");
                showActiveCode(string);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.WIFI_NOT_CONNECT) {
                String string2 = getString(C5508R.string.pdStr1_12);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr1_12)");
                showActiveCode(string2);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.TESTING_TIMEOUT) {
                String string3 = getString(C5508R.string.pdStr1_10);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr1_10)");
                showActiveCode(string3);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.SERVER_STATUS) {
                String string4 = getString(C5508R.string.pdStr1_10);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.pdStr1_10)");
                showActiveCode(string4);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.REQUEST_ERROR) {
                String string5 = getString(C5508R.string.pdStr1_12);
                Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.pdStr1_12)");
                showActiveCode(string5);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.STATUS_UNABLE_ACTIVE) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string6 = getString(C5508R.string.status_unable_active);
                Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.status_unable_active)");
                Object[] objArr = {WifiUtil.INSTANCE.getMac()};
                String format = String.format(string6, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                showActiveCode(format);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.EXCEPTION_RECORD) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String string7 = getString(C5508R.string.exception_record);
                Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.exception_record)");
                Object[] objArr2 = {WifiUtil.INSTANCE.getMac()};
                String format2 = String.format(string7, Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
                showActiveCode(format2);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.NEED_FROZEN) {
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String string8 = getString(C5508R.string.need_frozen);
                Intrinsics.checkExpressionValueIsNotNull(string8, "getString(R.string.need_frozen)");
                Object[] objArr3 = {WifiUtil.INSTANCE.getMac()};
                String format3 = String.format(string8, Arrays.copyOf(objArr3, objArr3.length));
                Intrinsics.checkExpressionValueIsNotNull(format3, "java.lang.String.format(format, *args)");
                showActiveCode(format3);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.CODE_NOT_FOUND) {
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String string9 = getString(C5508R.string.code_not_found);
                Intrinsics.checkExpressionValueIsNotNull(string9, "getString(R.string.code_not_found)");
                Object[] objArr4 = {WifiUtil.INSTANCE.getMac()};
                String format4 = String.format(string9, Arrays.copyOf(objArr4, objArr4.length));
                Intrinsics.checkExpressionValueIsNotNull(format4, "java.lang.String.format(format, *args)");
                showActiveCode(format4);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.CODE_HAS_USE) {
                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                String string10 = getString(C5508R.string.code_has_use);
                Intrinsics.checkExpressionValueIsNotNull(string10, "getString(R.string.code_has_use)");
                Object[] objArr5 = {WifiUtil.INSTANCE.getMac()};
                String format5 = String.format(string10, Arrays.copyOf(objArr5, objArr5.length));
                Intrinsics.checkExpressionValueIsNotNull(format5, "java.lang.String.format(format, *args)");
                showActiveCode(format5);
                return;
            }
            if (info.getInactiveType() == ActivateContract.InactiveType.PARAM_IS_MISSING) {
                StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                String string11 = getString(C5508R.string.pid_not_found);
                Intrinsics.checkExpressionValueIsNotNull(string11, "getString(R.string.pid_not_found)");
                Object[] objArr6 = {WifiUtil.INSTANCE.getMac()};
                String format6 = String.format(string11, Arrays.copyOf(objArr6, objArr6.length));
                Intrinsics.checkExpressionValueIsNotNull(format6, "java.lang.String.format(format, *args)");
                showActiveCode(String.valueOf(format6));
                return;
            }
            StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
            String string12 = getString(C5508R.string.code_invalid);
            Intrinsics.checkExpressionValueIsNotNull(string12, "getString(R.string.code_invalid)");
            Object[] objArr7 = {WifiUtil.INSTANCE.getMac()};
            String format7 = String.format(string12, Arrays.copyOf(objArr7, objArr7.length));
            Intrinsics.checkExpressionValueIsNotNull(format7, "java.lang.String.format(format, *args)");
            showActiveCode(format7);
        }
    }

    private final void showLockDialog() {
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        LockMachineDialog lockMachineDialog = new LockMachineDialog(requireContext);
        String string = getString(C5508R.string.pdStr1_18);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_18)");
        lockMachineDialog.showTipMsg(string);
        lockMachineDialog.show();
    }

    private final void showSuccess() {
        if (getContext() != null) {
            hideAll();
            MapingFuntionManager.INSTANCE.defaultResetRobot();
            TextView textView = this.title_tv;
            if (textView != null) {
                textView.setVisibility(0);
            }
            TextView textView2 = this.title_tv;
            if (textView2 != null) {
                textView2.setText(getString(C5508R.string.pdStr1_9));
            }
            TextView textView3 = this.active_tv;
            if (textView3 != null) {
                textView3.setText(getString(C5508R.string.pdStr8_13));
            }
            TextView textView4 = this.active_tv;
            if (textView4 != null) {
                textView4.setVisibility(0);
            }
            MMKVPreference.INSTANCE.encode(Constans.ACTIVE_FIRST, (Object) true);
            TextView textView5 = this.active_tv;
            if (textView5 != null) {
                textView5.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.RobotActiveFragment$showSuccess$1
                    @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
                    public void onSingleClick() {
                        super.onSingleClick();
                        MirSdkManager.INSTANCE.notifyMapFinish();
                        RobotActiveFragment.this.jumpCheckAc();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpCheckAc() {
        MMKVPreference.INSTANCE.encode(Constans.ACTIVE_FIRST, (Object) true);
        Pdlog.m3273d(this.TAG, "jumpCheckAc ");
        unbind();
        Context context = getContext();
        if (context != null) {
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
            }
            AnkoInternals.internalStartActivity(context, SelfCheckActivity.class, new Pair[0]);
            ((Activity) context).finish();
        }
    }
}
