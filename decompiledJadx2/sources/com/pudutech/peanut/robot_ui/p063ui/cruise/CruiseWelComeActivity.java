package com.pudutech.peanut.robot_ui.p063ui.cruise;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.robot.TouchListenerManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: CruiseWelComeActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0007J1\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0016J\u0012\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0011H\u0002J\u001c\u0010\u001c\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001d\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u0007H\u0002J\b\u0010\u001f\u001a\u00020\u000eH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseWelComeActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "isShowLowPowerDialog", "", "lowerPowerDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "getTableNumber", "", "notifyBatteryInfo", "state", "", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFeatureChange", "type", "showDialog", NotificationCompat.CATEGORY_MESSAGE, "needCloseSensorFace", "showLowerNotify", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseWelComeActivity extends BatteryBaseActivity {
    private String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;
    private boolean isShowLowPowerDialog;
    private ShowTipMsgDialog lowerPowerDialog;

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.cruise_wecome_acitvity);
        getTableNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onFeatureChange " + type);
        if (type == 0) {
            AiVoiceManager.INSTANCE.startAiRecording();
        } else {
            if (type == 1 || type == 2 || type == 3 || type != 4) {
                return;
            }
            AiVoiceManager.INSTANCE.stopAiRecording();
        }
    }

    private final void showLowerNotify() {
        Pdlog.m3273d(this.TAG, "showLowerNotify ");
        if (this.isShowLowPowerDialog) {
            return;
        }
        Pdlog.m3273d(this.TAG, "showBatteryEvent ShowLowPowerDialog");
        ShowTipMsgDialog showTipMsgDialog = this.lowerPowerDialog;
        if (showTipMsgDialog != null && showTipMsgDialog.isShowing()) {
            this.isShowLowPowerDialog = true;
            return;
        }
        this.isShowLowPowerDialog = true;
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr2_17);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_17)");
        this.lowerPowerDialog = showDialog$default(this, string, false, 2, null);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(final MotionEvent event) {
        ListenerList<TouchListenerManager.OnTouchListener> touchListeners = TouchListenerManager.INSTANCE.getTouchListeners();
        if (touchListeners != null) {
            touchListeners.forEach(Dispatchers.getMain(), new Function1<TouchListenerManager.OnTouchListener, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeActivity$dispatchTouchEvent$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TouchListenerManager.OnTouchListener onTouchListener) {
                    invoke2(onTouchListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TouchListenerManager.OnTouchListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onTouchListener(event);
                }
            });
        }
        return super.dispatchTouchEvent(event);
    }

    static /* synthetic */ ShowTipMsgDialog showDialog$default(CruiseWelComeActivity cruiseWelComeActivity, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cruiseWelComeActivity.showDialog(str, z);
    }

    private final ShowTipMsgDialog showDialog(String msg, final boolean needCloseSensorFace) {
        return MyBaseActivity.showTipDialog$default(this, msg, new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeActivity$showDialog$1
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                CruiseWelComeActivity.this.onFeatureChange(4);
                boolean z = needCloseSensorFace;
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeActivity$showDialog$2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                CruiseWelComeActivity.this.onFeatureChange(0);
            }
        }, null, 8, null);
    }

    public final void getTableNumber() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CruiseWelComeActivity$getTableNumber$1(this, null), 3, null);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 1) {
            if (this.isShowLowPowerDialog) {
                return;
            }
            showLowerNotify();
        } else if (state == 3 && i != null && i.intValue() <= 5 && i.intValue() <= 2) {
            i.intValue();
        }
    }
}
