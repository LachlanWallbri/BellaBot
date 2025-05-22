package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.manager.AdControllerManager;
import com.pudutech.peanut.robot_ui.manager.AppUpdateManager;
import com.pudutech.peanut.robot_ui.manager.SystemSoundManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AdSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001c\u001a\u00020\nH\u0002J\u0010\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J\u0010\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0002J\b\u0010%\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/AdSettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "checkUpdateDialog", "Landroid/app/Dialog;", "mShowTipDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "checkUpdate", "", "showDialog", "", "enableUpdateLayout", "boolean", "getAdVersion", "initAdVoice", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "showCheckLoadingDialog", "showTipDialog", "tips", "showUpdateDialog", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "showUpdateLoading", "url", "md5", "startCheckConnectState", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AdSettingFragment extends Fragment {
    private final String TAG = "AdSettingFragment";
    private HashMap _$_findViewCache;
    private Dialog checkUpdateDialog;
    private ShowTipMsgDialog mShowTipDialog;

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

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_ad_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private final void initView() {
        enableUpdateLayout(false);
        startCheckConnectState();
        ((RelativeLayout) _$_findCachedViewById(C5508R.id.ad_connect_state_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdSettingFragment.this.startCheckConnectState();
            }
        });
        ((RelativeLayout) _$_findCachedViewById(C5508R.id.check_update)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdSettingFragment.this.checkUpdate(true);
            }
        });
        checkUpdate$default(this, false, 1, null);
        initAdVoice();
    }

    private final void initAdVoice() {
        SeekBar ad_voice_bar = (SeekBar) _$_findCachedViewById(C5508R.id.ad_voice_bar);
        Intrinsics.checkExpressionValueIsNotNull(ad_voice_bar, "ad_voice_bar");
        ad_voice_bar.setProgress(SystemSoundManager.INSTANCE.getSoundHelper().getAdVoice());
        ((SeekBar) _$_findCachedViewById(C5508R.id.ad_voice_bar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$initAdVoice$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                String str;
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                str = AdSettingFragment.this.TAG;
                Pdlog.m3273d(str, "ad progress :" + seekBar.getProgress());
                SystemSoundManager.INSTANCE.getSoundHelper().setAdVoice(seekBar.getProgress());
                PlaySound.play(RobotContext.INSTANCE.getContext(), C5508R.raw.voice_tmp, SystemSoundManager.INSTANCE.getSoundHelper().getNOW_AUDIO_TYPE(), ((float) SystemSoundManager.INSTANCE.getSoundHelper().getAdVoice()) / 100.0f);
            }
        });
    }

    static /* synthetic */ void checkUpdate$default(AdSettingFragment adSettingFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        adSettingFragment.checkUpdate(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkUpdate(final boolean showDialog) {
        AdControllerManager.INSTANCE.checkApkVersion(new Function1<VerionResult, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$checkUpdate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VerionResult verionResult) {
                invoke2(verionResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VerionResult verionResult) {
                String str;
                str = AdSettingFragment.this.TAG;
                Pdlog.m3273d(str, "initView " + verionResult);
                if (AdSettingFragment.this.isDetached() || AdSettingFragment.this.getContext() == null) {
                    return;
                }
                if (verionResult != null && verionResult.getAvailable()) {
                    if (showDialog) {
                        AdSettingFragment.this.showUpdateDialog(verionResult);
                    }
                    TextView version_show = (TextView) AdSettingFragment.this._$_findCachedViewById(C5508R.id.version_show);
                    Intrinsics.checkExpressionValueIsNotNull(version_show, "version_show");
                    Version version = verionResult.getVersion();
                    version_show.setText(version != null ? version.getVersion_name() : null);
                    return;
                }
                TextView version_show2 = (TextView) AdSettingFragment.this._$_findCachedViewById(C5508R.id.version_show);
                Intrinsics.checkExpressionValueIsNotNull(version_show2, "version_show");
                version_show2.setText(AdSettingFragment.this.getString(C5508R.string.pdStr7_47));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showUpdateDialog(final VerionResult vr) {
        AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
        Context context = getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
        appUpdateManager.showUpdateResultDialog((Activity) context, vr, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$showUpdateDialog$1
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
                String str;
                String md5;
                Integer power = BatteryInfoManager.INSTANCE.getPower();
                if ((power != null ? power.intValue() : 0) > 20) {
                    AdSettingFragment adSettingFragment = AdSettingFragment.this;
                    Version version = vr.getVersion();
                    String str2 = "";
                    if (version == null || (str = version.getUrl()) == null) {
                        str = "";
                    }
                    Version version2 = vr.getVersion();
                    if (version2 != null && (md5 = version2.getMd5()) != null) {
                        str2 = md5;
                    }
                    adSettingFragment.showUpdateLoading(str, str2);
                    return;
                }
                AdSettingFragment adSettingFragment2 = AdSettingFragment.this;
                String string = adSettingFragment2.getString(C5508R.string.pdStr7_49);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_49)");
                adSettingFragment2.showTipDialog(string);
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$showUpdateDialog$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        }, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showUpdateLoading(String url, String md5) {
        boolean z = true;
        Pdlog.m3273d(this.TAG, "showUpdateLoading : url = " + url + "; md5 = " + md5 + "; ");
        String str = url;
        if (str == null || str.length() == 0) {
            return;
        }
        String str2 = md5;
        if (str2 != null && str2.length() != 0) {
            z = false;
        }
        if (z) {
            return;
        }
        showCheckLoadingDialog();
        AdControllerManager.INSTANCE.startUpdate(url, md5, new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$showUpdateLoading$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z2) {
                String str3;
                if (AdSettingFragment.this.getContext() == null || AdSettingFragment.this.isDetached()) {
                    return;
                }
                str3 = AdSettingFragment.this.TAG;
                Pdlog.m3273d(str3, "showUpdateLoading " + z2);
                if (z2) {
                    AdSettingFragment adSettingFragment = AdSettingFragment.this;
                    String string = adSettingFragment.getString(C5508R.string.ad_app_update_down_success);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.ad_app_update_down_success)");
                    adSettingFragment.showTipDialog(string);
                    return;
                }
                AdSettingFragment adSettingFragment2 = AdSettingFragment.this;
                String string2 = adSettingFragment2.getString(C5508R.string.ad_app_update_down_failed);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.ad_app_update_down_failed)");
                adSettingFragment2.showTipDialog(string2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTipDialog(String tips) {
        Dialog dialog = this.checkUpdateDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        if (this.mShowTipDialog == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            this.mShowTipDialog = new ShowTipMsgDialog(requireActivity);
        }
        ShowTipMsgDialog showTipMsgDialog = this.mShowTipDialog;
        if (showTipMsgDialog == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog.showTipMsg(tips);
        ShowTipMsgDialog showTipMsgDialog2 = this.mShowTipDialog;
        if (showTipMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog2.setCanCancel(true);
        ShowTipMsgDialog showTipMsgDialog3 = this.mShowTipDialog;
        if (showTipMsgDialog3 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog3.setCallback(new ShowTipMsgDialog.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$showTipDialog$1
            @Override // com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog.Callback
            public void onDissmiss(ShowTipMsgDialog dailog) {
                Intrinsics.checkParameterIsNotNull(dailog, "dailog");
                AdSettingFragment.this.mShowTipDialog = (ShowTipMsgDialog) null;
            }
        });
        ShowTipMsgDialog showTipMsgDialog4 = this.mShowTipDialog;
        if (showTipMsgDialog4 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog4.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheckConnectState() {
        ((TextView) _$_findCachedViewById(C5508R.id.state_tv)).setTextColor(Color.parseColor("#1CC33D"));
        TextView state_tv = (TextView) _$_findCachedViewById(C5508R.id.state_tv);
        Intrinsics.checkExpressionValueIsNotNull(state_tv, "state_tv");
        state_tv.setText(getString(C5508R.string.ad_controller_connecting));
        AdControllerManager.INSTANCE.checkState(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdSettingFragment$startCheckConnectState$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                str = AdSettingFragment.this.TAG;
                Pdlog.m3273d(str, "startCheckConnectState " + z);
                if (AdSettingFragment.this.isDetached() || AdSettingFragment.this.getContext() == null) {
                    return;
                }
                if (z) {
                    ((TextView) AdSettingFragment.this._$_findCachedViewById(C5508R.id.state_tv)).setTextColor(Color.parseColor("#1CC33D"));
                    TextView state_tv2 = (TextView) AdSettingFragment.this._$_findCachedViewById(C5508R.id.state_tv);
                    Intrinsics.checkExpressionValueIsNotNull(state_tv2, "state_tv");
                    state_tv2.setText(AdSettingFragment.this.getString(C5508R.string.ad_controller_connected));
                    AdSettingFragment.this.getAdVersion();
                } else {
                    ((TextView) AdSettingFragment.this._$_findCachedViewById(C5508R.id.state_tv)).setTextColor(AdSettingFragment.this.requireContext().getColor(C5508R.color.click_color_back_press));
                    TextView state_tv3 = (TextView) AdSettingFragment.this._$_findCachedViewById(C5508R.id.state_tv);
                    Intrinsics.checkExpressionValueIsNotNull(state_tv3, "state_tv");
                    state_tv3.setText(AdSettingFragment.this.getString(C5508R.string.ad_controller_desconnected));
                }
                AdSettingFragment.this.enableUpdateLayout(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getAdVersion() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AdSettingFragment$getAdVersion$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enableUpdateLayout(boolean r4) {
        if (r4) {
            ((TextView) _$_findCachedViewById(C5508R.id.current_version_title_tv)).setTextColor(Color.parseColor("#222222"));
            ((TextView) _$_findCachedViewById(C5508R.id.check_title_tv)).setTextColor(Color.parseColor("#222222"));
            TextView version_show = (TextView) _$_findCachedViewById(C5508R.id.version_show);
            Intrinsics.checkExpressionValueIsNotNull(version_show, "version_show");
            version_show.setVisibility(0);
            ((RelativeLayout) _$_findCachedViewById(C5508R.id.check_update)).setBackgroundResource(C5508R.drawable.select_check_update);
            ((LinearLayout) _$_findCachedViewById(C5508R.id.current_version_layout)).setBackgroundResource(C5508R.drawable.select_check_update);
            return;
        }
        ((TextView) _$_findCachedViewById(C5508R.id.current_version_title_tv)).setTextColor(Color.parseColor("#A8A8A8"));
        ((TextView) _$_findCachedViewById(C5508R.id.check_title_tv)).setTextColor(Color.parseColor("#A8A8A8"));
        TextView version_show2 = (TextView) _$_findCachedViewById(C5508R.id.version_show);
        Intrinsics.checkExpressionValueIsNotNull(version_show2, "version_show");
        version_show2.setVisibility(8);
        ((RelativeLayout) _$_findCachedViewById(C5508R.id.check_update)).setBackgroundResource(C5508R.drawable.rectangle_setting_disable_corners);
        ((LinearLayout) _$_findCachedViewById(C5508R.id.current_version_layout)).setBackgroundResource(C5508R.drawable.rectangle_setting_disable_corners);
    }

    private final void showCheckLoadingDialog() {
        FragmentActivity activity = getActivity();
        LayoutInflater layoutInflater = activity != null ? activity.getLayoutInflater() : null;
        View inflate = layoutInflater != null ? layoutInflater.inflate(C5508R.layout.fragment_ad_update_downing_dialog, (ViewGroup) null) : null;
        this.checkUpdateDialog = new Dialog(getActivity());
        Dialog dialog = this.checkUpdateDialog;
        if (dialog != null) {
            dialog.requestWindowFeature(1);
        }
        Dialog dialog2 = this.checkUpdateDialog;
        if (dialog2 != null) {
            dialog2.setCancelable(false);
        }
        Dialog dialog3 = this.checkUpdateDialog;
        Window window = dialog3 != null ? dialog3.getWindow() : null;
        NavigationBarUtil.focusNotAle(window);
        Dialog dialog4 = this.checkUpdateDialog;
        if (dialog4 != null) {
            dialog4.show();
        }
        NavigationBarUtil.hideNavigationBar(window);
        NavigationBarUtil.clearFocusNotAle(window);
        WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        if (window != null) {
            window.setGravity(17);
        }
        if (attributes != null) {
            attributes.width = -1;
        }
        if (attributes != null) {
            attributes.height = -1;
        }
        if (window != null) {
            window.setAttributes(attributes);
        }
        Dialog dialog5 = this.checkUpdateDialog;
        if (dialog5 != null) {
            dialog5.setContentView(inflate);
        }
    }
}
