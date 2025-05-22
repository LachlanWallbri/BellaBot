package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.adapter.TtsVoiceAdapter;
import com.pudutech.peanut.robot_ui.p063ui.dialog.CustomTtsVoiceInputDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.util.InputMethodUtil;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.robot.peripherals.BuildConfig;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliverySettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\u0012\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0002J\u0010\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0006H\u0002J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u0017H\u0016J\u001a\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010-\u001a\u00020\u0017H\u0002J\b\u0010.\u001a\u00020\u0017H\u0002J\u0010\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u00020\u0006H\u0002J\b\u00101\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0012j\b\u0012\u0004\u0012\u00020\u0004`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000¨\u00062"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/DeliverySettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "TYPE_CRUISE", "", "TYPE_DELIVER", "TYPE_DIRECT", "customVoiceType", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "dialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "isDialogCruiseShow", "", "isDialogDeliverShow", "isDialogDirectShow", "speedList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "ttsVoiceAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TtsVoiceAdapter;", "cruiseSpeed", "", "deliverSpeed", "directSpeed", "getSpeed", "p", "gotToAddCustomWordsDialog", "initDeliverFaceSwitch", "initSpeedData", "initVoiceData", "isTooFast", "speed_index", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setCustomVoiceEmptyVisible", "showCustomInputDialog", "showFastSpeedTip", "type", "updateCustomWords", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DeliverySettingFragment extends Fragment {
    private HashMap _$_findViewCache;
    private ShowTipMsgDialog dialog;
    private boolean isDialogCruiseShow;
    private boolean isDialogDeliverShow;
    private boolean isDialogDirectShow;
    private TtsVoiceAdapter ttsVoiceAdapter;
    private final String TAG = "SpeedFragment";
    private final ArrayList<String> speedList = CollectionsKt.arrayListOf(Constans.KEY_DEFAULT_SPEED_CONFIG, "0.6", "0.7", "0.8", "0.9", "1.0", BuildConfig.VERSION_NAME, "1.2");
    private final int TYPE_CRUISE = 1;
    private final int TYPE_DELIVER = 2;
    private final int TYPE_DIRECT = 3;
    private TtsVoiceHelper.TtsVoiceType customVoiceType = TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TtsVoiceHelper.TtsVoiceType.values().length];

        static {
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.USHER_TYPE.ordinal()] = 4;
        }
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

    public static final /* synthetic */ TtsVoiceAdapter access$getTtsVoiceAdapter$p(DeliverySettingFragment deliverySettingFragment) {
        TtsVoiceAdapter ttsVoiceAdapter = deliverySettingFragment.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        return ttsVoiceAdapter;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_delivery_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initSpeedData();
        deliverSpeed();
        directSpeed();
        cruiseSpeed();
        initVoiceData();
        initDeliverFaceSwitch();
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
        ((Switch) _$_findCachedViewById(C5508R.id.deliver_face_mode_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$initDeliverFaceSwitch$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = DeliverySettingFragment.this.TAG;
                Pdlog.m3273d(str2, "deliver_face_mode_switch " + z);
                com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.setDeliverFaceSwitch(z);
            }
        });
    }

    private final void initSpeedData() {
        ArrayList<String> speedLevels = RobotConfig.INSTANCE.getSpeedLevels();
        ArrayList<String> arrayList = speedLevels;
        if (!(arrayList == null || arrayList.isEmpty())) {
            Pdlog.m3273d(this.TAG, "speedLevels " + speedLevels.toString() + " , " + speedLevels.size());
            this.speedList.clear();
            this.speedList.addAll(arrayList);
        }
        Iterator<String> it = this.speedList.iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "speedList.iterator()");
        while (it.hasNext()) {
            String next = it.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "it.next()");
            try {
                if (Double.parseDouble(next) > 0.9d) {
                    it.remove();
                }
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "initSpeedData : " + Log.getStackTraceString(e));
            }
        }
        if (this.speedList.isEmpty()) {
            this.speedList.addAll(CollectionsKt.arrayListOf(Constans.KEY_DEFAULT_SPEED_CONFIG, "0.6", "0.7", "0.8", "0.9"));
        }
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
            cruise_speed_bar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$cruiseSpeed$1
                private float currentCruiseSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = DeliverySettingFragment.this.TAG;
                    Pdlog.m3275i(str, "onSeeking cruiseSpeed ====" + params.progressFloat);
                    this.currentCruiseSpeed = params.progressFloat;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str;
                    ArrayList arrayList4;
                    ArrayList arrayList5;
                    boolean isTooFast;
                    int i;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int rint = (int) Math.rint((this.currentCruiseSpeed / 100.0f) * (size - 1));
                    str = DeliverySettingFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("cruiseSpeed onStopTrackingTouch:  ");
                    sb.append(this.currentCruiseSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = DeliverySettingFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = DeliverySettingFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutReturnSpeed(Float.parseFloat((String) obj));
                    isTooFast = DeliverySettingFragment.this.isTooFast(rint);
                    if (isTooFast) {
                        DeliverySettingFragment deliverySettingFragment = DeliverySettingFragment.this;
                        i = deliverySettingFragment.TYPE_CRUISE;
                        deliverySettingFragment.showFastSpeedTip(i);
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTooFast(int speed_index) {
        try {
            String str = this.speedList.get(speed_index);
            Intrinsics.checkExpressionValueIsNotNull(str, "speedList[speed_index]");
            return Double.parseDouble(str) >= 0.9d;
        } catch (Exception unused) {
            return false;
        }
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
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.dialog = new ShowTipMsgDialog(requireContext);
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
            speed_degree2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$deliverSpeed$1
                private float currentDeliverSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = DeliverySettingFragment.this.TAG;
                    Pdlog.m3275i(str, "onSeeking====" + params.progressFloat);
                    this.currentDeliverSpeed = params.progressFloat;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str;
                    ArrayList arrayList4;
                    ArrayList arrayList5;
                    boolean isTooFast;
                    int i;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int rint = (int) Math.rint((this.currentDeliverSpeed / 100.0f) * (size - 1));
                    str = DeliverySettingFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onStopTrackingTouch:  ");
                    sb.append(this.currentDeliverSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = DeliverySettingFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = DeliverySettingFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutMdSpeed(Float.parseFloat((String) obj));
                    isTooFast = DeliverySettingFragment.this.isTooFast(rint);
                    if (isTooFast) {
                        DeliverySettingFragment deliverySettingFragment = DeliverySettingFragment.this;
                        i = deliverySettingFragment.TYPE_DELIVER;
                        deliverySettingFragment.showFastSpeedTip(i);
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
            speed_direct2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$directSpeed$1
                private float currentDeliverSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = DeliverySettingFragment.this.TAG;
                    Pdlog.m3275i(str, "onSeeking====" + params.progressFloat);
                    this.currentDeliverSpeed = params.progressFloat;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str;
                    ArrayList arrayList4;
                    ArrayList arrayList5;
                    boolean isTooFast;
                    int i;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int rint = (int) Math.rint((this.currentDeliverSpeed / 100.0f) * (size - 1));
                    str = DeliverySettingFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onStopTrackingTouch:  ");
                    sb.append(this.currentDeliverSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = DeliverySettingFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = DeliverySettingFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutDirectSpeed(Float.parseFloat((String) obj));
                    isTooFast = DeliverySettingFragment.this.isTooFast(rint);
                    if (isTooFast) {
                        DeliverySettingFragment deliverySettingFragment = DeliverySettingFragment.this;
                        i = deliverySettingFragment.TYPE_DIRECT;
                        deliverySettingFragment.showFastSpeedTip(i);
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void initVoiceData() {
        ((LinearLayout) _$_findCachedViewById(C5508R.id.addVoiceLlv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$initVoiceData$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeliverySettingFragment.this.gotToAddCustomWordsDialog();
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        this.ttsVoiceAdapter = new TtsVoiceAdapter(requireContext);
        TtsVoiceAdapter ttsVoiceAdapter = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter.setFragmentManager(requireFragmentManager());
        RecyclerView tts_cruise_voice_recycler_view = (RecyclerView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_recycler_view, "tts_cruise_voice_recycler_view");
        tts_cruise_voice_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView tts_cruise_voice_recycler_view2 = (RecyclerView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_recycler_view2, "tts_cruise_voice_recycler_view");
        TtsVoiceAdapter ttsVoiceAdapter2 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        tts_cruise_voice_recycler_view2.setAdapter(ttsVoiceAdapter2);
        TtsVoiceAdapter ttsVoiceAdapter3 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter3.setNewData(TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null));
        TtsVoiceAdapter ttsVoiceAdapter4 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter4.setType(this.customVoiceType);
        TtsVoiceAdapter ttsVoiceAdapter5 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter5.setEmptyView(C5508R.layout.layout_custom_word_empty, (RecyclerView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_recycler_view));
        TtsVoiceAdapter ttsVoiceAdapter6 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        View emptyView = ttsVoiceAdapter6.getEmptyView();
        Intrinsics.checkExpressionValueIsNotNull(emptyView, "ttsVoiceAdapter.emptyView");
        LinearLayout linearLayout = (LinearLayout) emptyView.findViewById(C5508R.id.layoutCustomVoiceEmpty);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "ttsVoiceAdapter.emptyView.layoutCustomVoiceEmpty");
        ViewExtKt.onSingleClick(linearLayout, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$initVoiceData$2
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
                DeliverySettingFragment.this.gotToAddCustomWordsDialog();
            }
        });
        TextView cruise_context_tv = (TextView) _$_findCachedViewById(C5508R.id.cruise_context_tv);
        Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv, "cruise_context_tv");
        cruise_context_tv.setText(getString(C5508R.string.pdStr7_122));
        Switch open_cruise_switch = (Switch) _$_findCachedViewById(C5508R.id.open_cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch, "open_cruise_switch");
        open_cruise_switch.setChecked(com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getDeliverVoiceSwitch());
        TtsVoiceAdapter ttsVoiceAdapter7 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter7.setType(this.customVoiceType);
        TtsVoiceAdapter ttsVoiceAdapter8 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter8.setOnCloseSwtichListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$initVoiceData$3
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
                Switch open_cruise_switch2 = (Switch) DeliverySettingFragment.this._$_findCachedViewById(C5508R.id.open_cruise_switch);
                Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch2, "open_cruise_switch");
                if (open_cruise_switch2.isChecked()) {
                    Switch open_cruise_switch3 = (Switch) DeliverySettingFragment.this._$_findCachedViewById(C5508R.id.open_cruise_switch);
                    Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch3, "open_cruise_switch");
                    open_cruise_switch3.setChecked(false);
                }
            }
        });
        ((Switch) _$_findCachedViewById(C5508R.id.open_cruise_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$initVoiceData$4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType2;
                str = DeliverySettingFragment.this.TAG;
                Pdlog.m3273d(str, "open_cruise_switch " + z);
                if (z) {
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    Context requireContext2 = DeliverySettingFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType = TtsVoiceHelper.TtsVoiceOpenType.OPEN;
                    ttsVoiceType2 = DeliverySettingFragment.this.customVoiceType;
                    ttsVoiceHelper.setTtsType(requireContext2, ttsVoiceOpenType, ttsVoiceType2);
                } else {
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    Context requireContext3 = DeliverySettingFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType2 = TtsVoiceHelper.TtsVoiceOpenType.CLOSE;
                    ttsVoiceType = DeliverySettingFragment.this.customVoiceType;
                    ttsVoiceHelper2.setTtsType(requireContext3, ttsVoiceOpenType2, ttsVoiceType);
                }
                com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.setDeliverVoiceSwitch(z);
                DeliverySettingFragment.access$getTtsVoiceAdapter$p(DeliverySettingFragment.this).notifyDataSetChanged();
            }
        });
        setCustomVoiceEmptyVisible();
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$initVoiceData$5
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
                DeliverySettingFragment.this.updateCustomWords();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCustomWords() {
        TtsVoiceAdapter ttsVoiceAdapter = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter.setNewData(TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null));
        TtsVoiceAdapter ttsVoiceAdapter2 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter2.notifyDataSetChanged();
        setCustomVoiceEmptyVisible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void gotToAddCustomWordsDialog() {
        InputMethodUtil.setDefaultInputMethod(getContext(), "gokey");
        showCustomInputDialog();
    }

    private final void setCustomVoiceEmptyVisible() {
        if (TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null).isEmpty()) {
            LinearLayout addVoiceLlv = (LinearLayout) _$_findCachedViewById(C5508R.id.addVoiceLlv);
            Intrinsics.checkExpressionValueIsNotNull(addVoiceLlv, "addVoiceLlv");
            addVoiceLlv.setVisibility(8);
        } else {
            LinearLayout addVoiceLlv2 = (LinearLayout) _$_findCachedViewById(C5508R.id.addVoiceLlv);
            Intrinsics.checkExpressionValueIsNotNull(addVoiceLlv2, "addVoiceLlv");
            addVoiceLlv2.setVisibility(0);
        }
    }

    private final void showCustomInputDialog() {
        String string;
        if (TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null).size() >= 30) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), getString(C5508R.string.custom_word_arrived_max), new Object[0]);
            return;
        }
        CustomTtsVoiceInputDialog customTtsVoiceInputDialog = new CustomTtsVoiceInputDialog();
        int i = WhenMappings.$EnumSwitchMapping$0[this.customVoiceType.ordinal()];
        if (i == 1) {
            string = getString(C5508R.string.pdStr7_120);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_120)");
        } else if (i == 2) {
            string = getString(C5508R.string.pdStr7_121);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_121)");
        } else if (i == 3) {
            string = getString(C5508R.string.solicit_my_self_setting);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.solicit_my_self_setting)");
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            string = getString(C5508R.string.greeter_setting_self);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.greeter_setting_self)");
        }
        customTtsVoiceInputDialog.setTitle(string);
        customTtsVoiceInputDialog.setType(this.customVoiceType);
        FragmentManager requireFragmentManager = requireFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(requireFragmentManager, "requireFragmentManager()");
        customTtsVoiceInputDialog.show(requireFragmentManager, "voice_custom");
        customTtsVoiceInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.DeliverySettingFragment$showCustomInputDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                if (DeliverySettingFragment.this.getContext() != null) {
                    DeliverySettingFragment.this.updateCustomWords();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Pdlog.m3273d(this.TAG, "onDestroyView");
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        TtsVoiceAdapter ttsVoiceAdapter = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter.resetPlayStatus();
        _$_clearFindViewByIdCache();
    }
}
