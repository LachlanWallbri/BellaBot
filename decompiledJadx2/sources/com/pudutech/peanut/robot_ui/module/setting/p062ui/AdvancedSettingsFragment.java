package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.importmusic.QRCodeUtils;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.robot.module.openapi.RobotOpenApiManager;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack;
import com.pudutech.robot.peripherals.BuildConfig;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AdvancedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0087\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0016H\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0012\u0010$\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010&\u001a\u00020\u0016H\u0002J\b\u0010'\u001a\u00020\u0016H\u0002J\b\u0010(\u001a\u00020\u0016H\u0002J\b\u0010)\u001a\u00020\u0016H\u0002J\b\u0010*\u001a\u00020\u0016H\u0002J\b\u0010+\u001a\u00020\u0016H\u0002J\b\u0010,\u001a\u00020\u0016H\u0002J\b\u0010-\u001a\u00020\u0016H\u0002J\u0010\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u000200H\u0016J&\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u00109\u001a\u00020\u0016H\u0016J\u001a\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u0002022\b\u00107\u001a\u0004\u0018\u000108H\u0016J\"\u0010<\u001a\u00020\u00162\b\u0010=\u001a\u0004\u0018\u00010>2\u000e\u0010?\u001a\n\u0018\u00010@j\u0004\u0018\u0001`AH\u0002J\u0010\u0010B\u001a\u00020\u00162\u0006\u0010C\u001a\u00020\u0006H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u001cj\b\u0012\u0004\u0012\u00020\u0004`\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/AdvancedSettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "TYPE_CRUISE", "", "TYPE_DELIVER", "TYPE_DIRECT", "dialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "iGenBindCodeCallBack", "com/pudutech/peanut/robot_ui/module/setting/ui/AdvancedSettingsFragment$iGenBindCodeCallBack$1", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/AdvancedSettingsFragment$iGenBindCodeCallBack$1;", "isDialogCruiseShow", "", "isDialogDeliverShow", "isDialogDirectShow", "mainHandler", "Landroid/os/Handler;", "onAboutInfoSwitchChange", "Lkotlin/Function0;", "", "getOnAboutInfoSwitchChange", "()Lkotlin/jvm/functions/Function0;", "setOnAboutInfoSwitchChange", "(Lkotlin/jvm/functions/Function0;)V", "speedList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "cruiseSpeed", "deliverSpeed", "directSpeed", "generateQRCodeBitmap", "Landroid/graphics/Bitmap;", "code", "getSpeed", "p", "initAboutInfoSwitch", "initAutoDeliverView", "initDeliverFaceSwitch", "initQrCodeView", "initRecycleModeSwitch", "initSingleTrayMultiTableSwitch", "initSpeedData", "initView", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onViewCreated", "view", "showBindCode", "current", "Lcom/pudutech/robot/opensdk/aliyun/bean/BindCodeData;", C3898x.f4338g, "Ljava/lang/Exception;", "Lkotlin/Exception;", "showFastSpeedTip", "type", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AdvancedSettingsFragment extends Fragment {
    private HashMap _$_findViewCache;
    private ShowTipMsgDialog dialog;
    private boolean isDialogCruiseShow;
    private boolean isDialogDeliverShow;
    private boolean isDialogDirectShow;
    private Function0<Unit> onAboutInfoSwitchChange;
    private final String TAG = "AdvancedSettingsFragment";
    private final ArrayList<String> speedList = CollectionsKt.arrayListOf(Constans.KEY_DEFAULT_SPEED_CONFIG, "0.6", "0.7", "0.8", "0.9", "1.0", BuildConfig.VERSION_NAME, "1.2");
    private final int TYPE_CRUISE = 1;
    private final int TYPE_DELIVER = 2;
    private final int TYPE_DIRECT = 3;
    private final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            String str;
            AdvancedSettingsFragment$iGenBindCodeCallBack$1 advancedSettingsFragment$iGenBindCodeCallBack$1;
            if (AdvancedSettingsFragment.this.getContext() != null) {
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "auto gen qrcode");
                RobotOpenApiManager robotOpenApiManager = RobotOpenApiManager.INSTANCE;
                advancedSettingsFragment$iGenBindCodeCallBack$1 = AdvancedSettingsFragment.this.iGenBindCodeCallBack;
                robotOpenApiManager.genBind(advancedSettingsFragment$iGenBindCodeCallBack$1);
            }
            return true;
        }
    });
    private final AdvancedSettingsFragment$iGenBindCodeCallBack$1 iGenBindCodeCallBack = new IGenBindCodeCallBack() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$iGenBindCodeCallBack$1
        @Override // com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack
        public void onFailed(Exception e) {
            String str;
            Intrinsics.checkParameterIsNotNull(e, "e");
            str = AdvancedSettingsFragment.this.TAG;
            Pdlog.m3274e(str, "onFailed : " + Log.getStackTraceString(e));
            AdvancedSettingsFragment.this.showBindCode(null, e);
        }

        @Override // com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack
        public void onSuccess(BindCodeData code) {
            String str;
            Intrinsics.checkParameterIsNotNull(code, "code");
            str = AdvancedSettingsFragment.this.TAG;
            Pdlog.m3273d(str, "onSuccess : code = " + code + "; ");
            if (AdvancedSettingsFragment.this.isDetached()) {
                return;
            }
            AdvancedSettingsFragment.this.showBindCode(code, null);
        }
    };

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

    public final Function0<Unit> getOnAboutInfoSwitchChange() {
        return this.onAboutInfoSwitchChange;
    }

    public final void setOnAboutInfoSwitchChange(Function0<Unit> function0) {
        this.onAboutInfoSwitchChange = function0;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_advanced_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private final void initView() {
        initSpeedData();
        initRecycleModeSwitch();
        initDeliverFaceSwitch();
        initSingleTrayMultiTableSwitch();
        initAboutInfoSwitch();
        deliverSpeed();
        directSpeed();
        cruiseSpeed();
        initQrCodeView();
        initAutoDeliverView();
    }

    private final void initSpeedData() {
        ArrayList<String> speedLevels = RobotConfig.INSTANCE.getSpeedLevels();
        ArrayList<String> arrayList = speedLevels;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Pdlog.m3273d(this.TAG, "speedLevels " + speedLevels);
        this.speedList.clear();
        this.speedList.addAll(arrayList);
    }

    private final void initAutoDeliverView() {
        Switch auto_delivery_switch = (Switch) _$_findCachedViewById(C5508R.id.auto_delivery_switch);
        Intrinsics.checkExpressionValueIsNotNull(auto_delivery_switch, "auto_delivery_switch");
        auto_delivery_switch.setChecked(BusinessSetting.INSTANCE.getDelayAutoFinishSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initAutoDeliverView ");
        Switch auto_delivery_switch2 = (Switch) _$_findCachedViewById(C5508R.id.auto_delivery_switch);
        Intrinsics.checkExpressionValueIsNotNull(auto_delivery_switch2, "auto_delivery_switch");
        sb.append(auto_delivery_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C5508R.id.auto_delivery_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoDeliverView$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "auto_delivery_switch " + z);
                BusinessSetting.INSTANCE.setDelayAutoFinishSwitch(z);
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.deliver_voice_loop_reset_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoDeliverView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str2;
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "deliver_voice_loop_reset_tv");
                BusinessSetting.INSTANCE.setArrivalLoopVoiceDelayTime_ms(1000 * 15);
                ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.deliver_voice_loop_et)).setText(String.valueOf(15L));
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.pause_auto_active_reset_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoDeliverView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str2;
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "pause_auto_active_reset_tv");
                BusinessSetting.INSTANCE.setNotCruisePauseKeepTime_ms(1000 * 10);
                ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.pause_auto_active_et)).setText(String.valueOf(10L));
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.cruise_pause_auto_active_reset_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoDeliverView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str2;
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "cruise_pause_auto_active_reset_tv");
                BusinessSetting.INSTANCE.setCruisePauseKeepTime_ms(1000 * 20);
                ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.cruise_pause_auto_active_et)).setText(String.valueOf(20L));
            }
        });
        long j = 1000;
        ((EditText) _$_findCachedViewById(C5508R.id.auto_finish_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getDelayAutoFinish_ms() / j));
        ((EditText) _$_findCachedViewById(C5508R.id.deliver_voice_loop_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getArrivalLoopVoiceDelayTime_ms() / j));
        ((EditText) _$_findCachedViewById(C5508R.id.pause_auto_active_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / j));
        ((EditText) _$_findCachedViewById(C5508R.id.cruise_pause_auto_active_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getCruisePauseKeepTime_ms() / j));
        EditText auto_finish_et = (EditText) _$_findCachedViewById(C5508R.id.auto_finish_et);
        Intrinsics.checkExpressionValueIsNotNull(auto_finish_et, "auto_finish_et");
        auto_finish_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoDeliverView$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "auto_finish_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "1";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 600) {
                        parseLong = 600;
                        ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.auto_finish_et)).setText(String.valueOf(600L));
                    } else if (parseLong <= 0) {
                        parseLong = 1;
                        ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.auto_finish_et)).setText(String.valueOf(1L));
                    }
                    BusinessSetting.INSTANCE.setDelayAutoFinish_ms(parseLong * 1000);
                } catch (Exception e) {
                    str3 = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3274e(str3, "auto_finish_et : " + Log.getStackTraceString(e));
                }
            }
        });
        EditText deliver_voice_loop_et = (EditText) _$_findCachedViewById(C5508R.id.deliver_voice_loop_et);
        Intrinsics.checkExpressionValueIsNotNull(deliver_voice_loop_et, "deliver_voice_loop_et");
        deliver_voice_loop_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoDeliverView$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "deliver_voice_loop_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "1";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 30) {
                        parseLong = 30;
                        ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.deliver_voice_loop_et)).setText(String.valueOf(30L));
                    } else if (parseLong <= 0) {
                        parseLong = 1;
                        ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.deliver_voice_loop_et)).setText(String.valueOf(1L));
                    }
                    BusinessSetting.INSTANCE.setArrivalLoopVoiceDelayTime_ms(parseLong * 1000);
                } catch (Exception e) {
                    str3 = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3274e(str3, "deliver_voice_loop_et : " + Log.getStackTraceString(e));
                }
            }
        });
        EditText pause_auto_active_et = (EditText) _$_findCachedViewById(C5508R.id.pause_auto_active_et);
        Intrinsics.checkExpressionValueIsNotNull(pause_auto_active_et, "pause_auto_active_et");
        pause_auto_active_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoDeliverView$$inlined$doAfterTextChanged$3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "pause_auto_active_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "0";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 600) {
                        parseLong = 600;
                        ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.pause_auto_active_et)).setText(String.valueOf(600L));
                    } else if (parseLong < 5) {
                        parseLong = 5;
                    }
                    BusinessSetting.INSTANCE.setNotCruisePauseKeepTime_ms(parseLong * 1000);
                } catch (Exception e) {
                    str3 = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3274e(str3, "pause_auto_active_et : " + Log.getStackTraceString(e));
                }
            }
        });
        EditText cruise_pause_auto_active_et = (EditText) _$_findCachedViewById(C5508R.id.cruise_pause_auto_active_et);
        Intrinsics.checkExpressionValueIsNotNull(cruise_pause_auto_active_et, "cruise_pause_auto_active_et");
        cruise_pause_auto_active_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoDeliverView$$inlined$doAfterTextChanged$4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                String valueOf = String.valueOf(s);
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "pause_auto_active_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "0";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 600) {
                        parseLong = 600;
                        ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.cruise_pause_auto_active_et)).setText(String.valueOf(600L));
                    } else if (parseLong < 5) {
                        parseLong = 5;
                    }
                    BusinessSetting.INSTANCE.setCruisePauseKeepTime_ms(parseLong * 1000);
                } catch (Exception e) {
                    str3 = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3274e(str3, "cruise_pause_auto_active_et : " + Log.getStackTraceString(e));
                }
            }
        });
    }

    private final void initQrCodeView() {
        Switch beeper_call_switch = (Switch) _$_findCachedViewById(C5508R.id.beeper_call_switch);
        Intrinsics.checkExpressionValueIsNotNull(beeper_call_switch, "beeper_call_switch");
        beeper_call_switch.setChecked(RobotOpenApiManager.INSTANCE.getSwitch());
        ((Switch) _$_findCachedViewById(C5508R.id.beeper_call_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str;
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "beeper_call_switch " + z);
                RobotOpenApiManager.INSTANCE.setSwitch(z);
            }
        });
        ((CardView) _$_findCachedViewById(C5508R.id.gen_code_cv)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$2
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
            public void onSingleClick() {
                String str;
                AdvancedSettingsFragment$iGenBindCodeCallBack$1 advancedSettingsFragment$iGenBindCodeCallBack$1;
                if (!RobotOpenApiManager.INSTANCE.getSwitch()) {
                    ToastUtils.show(AdvancedSettingsFragment.this.getContext(), AdvancedSettingsFragment.this.getString(C5508R.string.pdStr7_151), new Object[0]);
                    return;
                }
                ProgressBar gen_code_progress = (ProgressBar) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.gen_code_progress);
                Intrinsics.checkExpressionValueIsNotNull(gen_code_progress, "gen_code_progress");
                gen_code_progress.setVisibility(0);
                CardView gen_code_cv = (CardView) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.gen_code_cv);
                Intrinsics.checkExpressionValueIsNotNull(gen_code_cv, "gen_code_cv");
                gen_code_cv.setEnabled(false);
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initQrCodeView onSingleClick");
                RobotOpenApiManager robotOpenApiManager = RobotOpenApiManager.INSTANCE;
                advancedSettingsFragment$iGenBindCodeCallBack$1 = AdvancedSettingsFragment.this.iGenBindCodeCallBack;
                robotOpenApiManager.genBind(advancedSettingsFragment$iGenBindCodeCallBack$1);
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.beeper_call_open_tv)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$3
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "beeper_call_open_tv onSingleClick : ");
                RelativeLayout call_open_layout = (RelativeLayout) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.call_open_layout);
                Intrinsics.checkExpressionValueIsNotNull(call_open_layout, "call_open_layout");
                if (call_open_layout.getVisibility() == 0) {
                    RelativeLayout call_open_layout2 = (RelativeLayout) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.call_open_layout);
                    Intrinsics.checkExpressionValueIsNotNull(call_open_layout2, "call_open_layout");
                    call_open_layout2.setVisibility(8);
                    TextView beeper_call_open_tv = (TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.beeper_call_open_tv);
                    Intrinsics.checkExpressionValueIsNotNull(beeper_call_open_tv, "beeper_call_open_tv");
                    beeper_call_open_tv.setText(AdvancedSettingsFragment.this.getString(C5508R.string.pdStr7_143));
                    Drawable drawable = AdvancedSettingsFragment.this.getResources().getDrawable(C5508R.drawable.icon_settings_advanced_open);
                    Intrinsics.checkExpressionValueIsNotNull(drawable, "drawable");
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    ((TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.beeper_call_open_tv)).setCompoundDrawables(null, null, drawable, null);
                    return;
                }
                RelativeLayout call_open_layout3 = (RelativeLayout) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.call_open_layout);
                Intrinsics.checkExpressionValueIsNotNull(call_open_layout3, "call_open_layout");
                call_open_layout3.setVisibility(0);
                TextView beeper_call_open_tv2 = (TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.beeper_call_open_tv);
                Intrinsics.checkExpressionValueIsNotNull(beeper_call_open_tv2, "beeper_call_open_tv");
                beeper_call_open_tv2.setText(AdvancedSettingsFragment.this.getString(C5508R.string.pdStr7_144));
                Drawable drawable2 = AdvancedSettingsFragment.this.getResources().getDrawable(C5508R.drawable.icon_settings_advanced_packup);
                Intrinsics.checkExpressionValueIsNotNull(drawable2, "drawable");
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                ((TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C5508R.id.beeper_call_open_tv)).setCompoundDrawables(null, null, drawable2, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showBindCode(BindCodeData current, Exception e) {
        Pdlog.m3273d(this.TAG, "showBindCode : current = " + current + "; e = " + e + "; ");
        if (getContext() == null) {
            return;
        }
        ProgressBar gen_code_progress = (ProgressBar) _$_findCachedViewById(C5508R.id.gen_code_progress);
        Intrinsics.checkExpressionValueIsNotNull(gen_code_progress, "gen_code_progress");
        gen_code_progress.setVisibility(8);
        CardView gen_code_cv = (CardView) _$_findCachedViewById(C5508R.id.gen_code_cv);
        Intrinsics.checkExpressionValueIsNotNull(gen_code_cv, "gen_code_cv");
        gen_code_cv.setEnabled(true);
        if (e == null && current != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AdvancedSettingsFragment$showBindCode$1(this, current, null), 2, null);
        } else {
            ToastUtils.show(getContext(), getString(C5508R.string.pdStr7_136), new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap generateQRCodeBitmap(String code) {
        return QRCodeUtils.createQRCodeBitmap(code, 400, 400);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Pdlog.m3273d(this.TAG, "onDetach ");
        this.mainHandler.removeMessages(0);
    }

    private final void initSingleTrayMultiTableSwitch() {
        Switch single_tray_multi_table_switch = (Switch) _$_findCachedViewById(C5508R.id.single_tray_multi_table_switch);
        Intrinsics.checkExpressionValueIsNotNull(single_tray_multi_table_switch, "single_tray_multi_table_switch");
        single_tray_multi_table_switch.setChecked(com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getSingleTrayMultiTableSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initSingleTrayMultiTableSwitch ");
        Switch single_tray_multi_table_switch2 = (Switch) _$_findCachedViewById(C5508R.id.single_tray_multi_table_switch);
        Intrinsics.checkExpressionValueIsNotNull(single_tray_multi_table_switch2, "single_tray_multi_table_switch");
        sb.append(single_tray_multi_table_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C5508R.id.single_tray_multi_table_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initSingleTrayMultiTableSwitch$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "single_tray_multi_table_switch " + z);
                com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.setSingleTrayMultiTableSwitch(z);
            }
        });
    }

    private final void initAboutInfoSwitch() {
        Switch about_info_setting_mode_switch = (Switch) _$_findCachedViewById(C5508R.id.about_info_setting_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(about_info_setting_mode_switch, "about_info_setting_mode_switch");
        about_info_setting_mode_switch.setChecked(com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getSettingAboutInfoSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initAboutInfoSwitch ");
        Switch about_info_setting_mode_switch2 = (Switch) _$_findCachedViewById(C5508R.id.about_info_setting_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(about_info_setting_mode_switch2, "about_info_setting_mode_switch");
        sb.append(about_info_setting_mode_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C5508R.id.about_info_setting_mode_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAboutInfoSwitch$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "about_info_setting_mode_switch " + z);
                com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.setSettingAboutInfoSwitch(z);
                Function0<Unit> onAboutInfoSwitchChange = AdvancedSettingsFragment.this.getOnAboutInfoSwitchChange();
                if (onAboutInfoSwitchChange != null) {
                    onAboutInfoSwitchChange.invoke();
                }
            }
        });
    }

    private final void initDeliverFaceSwitch() {
        Switch deliver_face_mode_switch = (Switch) _$_findCachedViewById(C5508R.id.deliver_face_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_face_mode_switch, "deliver_face_mode_switch");
        deliver_face_mode_switch.setChecked(com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getDeliverFaceSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initDeliverFaceSwitch ");
        Switch deliver_face_mode_switch2 = (Switch) _$_findCachedViewById(C5508R.id.deliver_face_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_face_mode_switch2, "deliver_face_mode_switch");
        sb.append(deliver_face_mode_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C5508R.id.deliver_face_mode_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initDeliverFaceSwitch$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "deliver_face_mode_switch " + z);
                com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.setDeliverFaceSwitch(z);
            }
        });
    }

    private final void initRecycleModeSwitch() {
        Switch recycle_mode_switch = (Switch) _$_findCachedViewById(C5508R.id.recycle_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(recycle_mode_switch, "recycle_mode_switch");
        recycle_mode_switch.setChecked(com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getRecyclingPlateSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initRecycleModeSwitch ");
        Switch recycle_mode_switch2 = (Switch) _$_findCachedViewById(C5508R.id.recycle_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(recycle_mode_switch2, "recycle_mode_switch");
        sb.append(recycle_mode_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C5508R.id.recycle_mode_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$initRecycleModeSwitch$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "recycle_mode_switch " + z);
                com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.setRecyclingPlateSwitch(z);
            }
        });
    }

    private final String getSpeed(String p) {
        String str = this.speedList.contains("0.8") ? "0.8" : (String) CollectionsKt.last((List) this.speedList);
        if (!CollectionsKt.contains(this.speedList, p)) {
            return str;
        }
        if (p != null) {
            return p;
        }
        Intrinsics.throwNpe();
        return p;
    }

    private final void deliverSpeed() {
        final int size = this.speedList.size();
        float peanutMdSpeed = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getPeanutMdSpeed();
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == peanutMdSpeed) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "speed list " + this.speedList + " level index " + indexOf + " level " + peanutMdSpeed);
        IndicatorSeekBar speed_degree = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_degree);
        Intrinsics.checkExpressionValueIsNotNull(speed_degree, "speed_degree");
        speed_degree.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_degree);
        Object[] array = this.speedList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_degree)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_degree)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar speed_degree2 = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_degree);
            Intrinsics.checkExpressionValueIsNotNull(speed_degree2, "speed_degree");
            speed_degree2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$deliverSpeed$1
                private float currentDeliverSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3275i(str, "onSeeking====" + params.progressFloat);
                    this.currentDeliverSpeed = params.progressFloat;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str;
                    ArrayList arrayList4;
                    ArrayList arrayList5;
                    ArrayList arrayList6;
                    int i;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int rint = (int) Math.rint((this.currentDeliverSpeed / 100.0f) * (size - 1));
                    str = AdvancedSettingsFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onStopTrackingTouch:  ");
                    sb.append(this.currentDeliverSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = AdvancedSettingsFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = AdvancedSettingsFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutMdSpeed(Float.parseFloat((String) obj));
                    arrayList6 = AdvancedSettingsFragment.this.speedList;
                    if (arrayList6.size() - 4 <= rint) {
                        AdvancedSettingsFragment advancedSettingsFragment = AdvancedSettingsFragment.this;
                        i = advancedSettingsFragment.TYPE_DELIVER;
                        advancedSettingsFragment.showFastSpeedTip(i);
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void cruiseSpeed() {
        final int size = this.speedList.size();
        float peanutReturnSpeed = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getPeanutReturnSpeed();
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == peanutReturnSpeed) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "cruiseSpeed list " + this.speedList + " level index " + indexOf + " level " + peanutReturnSpeed);
        IndicatorSeekBar cruise_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.cruise_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(cruise_speed_bar, "cruise_speed_bar");
        cruise_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.cruise_speed_bar);
        Object[] array = this.speedList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.cruise_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.cruise_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar cruise_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.cruise_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(cruise_speed_bar2, "cruise_speed_bar");
            cruise_speed_bar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$cruiseSpeed$1
                private float currentCruiseSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3275i(str, "onSeeking cruiseSpeed ====" + params.progressFloat);
                    this.currentCruiseSpeed = params.progressFloat;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str;
                    ArrayList arrayList4;
                    ArrayList arrayList5;
                    ArrayList arrayList6;
                    int i;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int rint = (int) Math.rint((this.currentCruiseSpeed / 100.0f) * (size - 1));
                    str = AdvancedSettingsFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("cruiseSpeed onStopTrackingTouch:  ");
                    sb.append(this.currentCruiseSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = AdvancedSettingsFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = AdvancedSettingsFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutReturnSpeed(Float.parseFloat((String) obj));
                    arrayList6 = AdvancedSettingsFragment.this.speedList;
                    if (arrayList6.size() - 4 <= rint) {
                        AdvancedSettingsFragment advancedSettingsFragment = AdvancedSettingsFragment.this;
                        i = advancedSettingsFragment.TYPE_CRUISE;
                        advancedSettingsFragment.showFastSpeedTip(i);
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void directSpeed() {
        final int size = this.speedList.size();
        float peanutDirectSpeed = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getPeanutDirectSpeed();
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == peanutDirectSpeed) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "speed list " + this.speedList + " level index " + indexOf + " level " + peanutDirectSpeed);
        IndicatorSeekBar speed_direct = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_direct);
        Intrinsics.checkExpressionValueIsNotNull(speed_direct, "speed_direct");
        speed_direct.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_direct);
        Object[] array = this.speedList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_direct)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_direct)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar speed_direct2 = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.speed_direct);
            Intrinsics.checkExpressionValueIsNotNull(speed_direct2, "speed_direct");
            speed_direct2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$directSpeed$1
                private float currentDeliverSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3275i(str, "onSeeking====" + params.progressFloat);
                    this.currentDeliverSpeed = params.progressFloat;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str;
                    ArrayList arrayList4;
                    ArrayList arrayList5;
                    ArrayList arrayList6;
                    int i;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int rint = (int) Math.rint((this.currentDeliverSpeed / 100.0f) * (size - 1));
                    str = AdvancedSettingsFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onStopTrackingTouch:  ");
                    sb.append(this.currentDeliverSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = AdvancedSettingsFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = AdvancedSettingsFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutDirectSpeed(Float.parseFloat((String) obj));
                    arrayList6 = AdvancedSettingsFragment.this.speedList;
                    if (arrayList6.size() - 4 <= rint) {
                        AdvancedSettingsFragment advancedSettingsFragment = AdvancedSettingsFragment.this;
                        i = advancedSettingsFragment.TYPE_DIRECT;
                        advancedSettingsFragment.showFastSpeedTip(i);
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFastSpeedTip(int type) {
        Pdlog.m3273d(this.TAG, "showFastSpeedTip type = " + type);
        if (type == this.TYPE_CRUISE) {
            if (this.isDialogCruiseShow) {
                return;
            } else {
                this.isDialogCruiseShow = true;
            }
        } else if (type == this.TYPE_DELIVER) {
            if (this.isDialogDeliverShow) {
                return;
            } else {
                this.isDialogDeliverShow = true;
            }
        } else if (type == this.TYPE_DIRECT) {
            if (this.isDialogDirectShow) {
                return;
            } else {
                this.isDialogDirectShow = true;
            }
        }
        if (getContext() == null) {
            return;
        }
        if (this.dialog == null) {
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            this.dialog = new ShowTipMsgDialog(context);
            ShowTipMsgDialog showTipMsgDialog = this.dialog;
            if (showTipMsgDialog == null) {
                Intrinsics.throwNpe();
            }
            String string = getString(C5508R.string.pdStr7_62);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_62)");
            showTipMsgDialog.showTipMsg(string);
        }
        ShowTipMsgDialog showTipMsgDialog2 = this.dialog;
        if (showTipMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog2.show();
    }
}
