package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.SafeModeManager;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceIndicatorSeekBarChangeListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.widget.SwitchWrapper;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.robot.peripherals.BuildConfig;
import com.warkiz.widget.IndicatorSeekBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpeedFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0015\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\u001cH\u0002J\b\u0010 \u001a\u00020\u001cH\u0002J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\"2\u0006\u0010#\u001a\u00020\u0004H\u0002J\u001e\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u00062\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040'H\u0002J&\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\u001cH\u0016J\u001a\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00103\u001a\u00020\u001cH\u0002J\u0010\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0019j\b\u0012\u0004\u0012\u00020\u0004`\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SpeedFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "TYPE_CRUISE", "", "TYPE_DELIVER", "TYPE_GOHOME", "TYPE_GREETER", "TYPE_RECYCLE", "dialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "isDIalogGoHomeShow", "", "isDIalogGreeterShow", "isDialogCruiseShow", "isDialogDeliverShow", "isDialogRecycleShow", "isSafeModeSwitching", "safeModeListener", "com/pudutech/bumblebee/robot_ui/module/setting/ui/SpeedFragment$safeModeListener$1", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SpeedFragment$safeModeListener$1;", "safeModeOldState", "speedList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "cruiseSpeed", "", "deliverSpeed", "goHomeSpeed", "greeterSpeed", "initSafeModeSwitch", "initSpeedData", "", "currentSpeedLevel", "isTooFast", "speed_index", "speedData", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "recyclingSpeed", "showFastSpeedTip", "type", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SpeedFragment extends Fragment {
    private HashMap _$_findViewCache;
    private ShowTipMsgDialog dialog;
    private boolean isDIalogGoHomeShow;
    private boolean isDIalogGreeterShow;
    private boolean isDialogCruiseShow;
    private boolean isDialogDeliverShow;
    private boolean isDialogRecycleShow;
    private boolean isSafeModeSwitching;
    private boolean safeModeOldState;
    private final String TAG = "SpeedFragment";
    private final ArrayList<String> speedList = CollectionsKt.arrayListOf("0.2", "0.3", "0.4", Constans.KEY_DEFAULT_SPEED_CONFIG, "0.6", "0.7", "0.8", "0.9", "1.0", BuildConfig.VERSION_NAME, "1.2");
    private final int TYPE_CRUISE = 1;
    private final int TYPE_DELIVER = 2;
    private final int TYPE_GOHOME = 3;
    private final int TYPE_GREETER = 4;
    private final int TYPE_RECYCLE = 5;
    private final SpeedFragment$safeModeListener$1 safeModeListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SpeedFragment$safeModeListener$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public void invoke(boolean isOpen) {
            String str;
            boolean z;
            str = SpeedFragment.this.TAG;
            Pdlog.m3273d(str, "onStateChanged " + isOpen);
            ((SwitchWrapper) SpeedFragment.this._$_findCachedViewById(C4188R.id.safe_switch)).silentlySetChecked(isOpen);
            z = SpeedFragment.this.safeModeOldState;
            if (z == isOpen) {
                ToastUtils.show(SpeedFragment.this.getContext(), C4188R.string.hint_check_emergency_state);
            }
            SpeedFragment.this.isSafeModeSwitching = false;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_speed_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        deliverSpeed();
        cruiseSpeed();
        goHomeSpeed();
        greeterSpeed();
        recyclingSpeed();
        initSafeModeSwitch();
    }

    private final List<String> initSpeedData(String currentSpeedLevel) {
        ArrayList arrayList = new ArrayList();
        List<String> speedLevels = RobotSetting.INSTANCE.getSpeedLevels();
        List<String> list = speedLevels;
        if (!(list == null || list.isEmpty())) {
            Pdlog.m3273d(this.TAG, "initSpeedData speedLevels " + speedLevels.toString() + " , " + speedLevels.size());
            this.speedList.clear();
            this.speedList.addAll(list);
        }
        double parseDouble = Double.parseDouble(currentSpeedLevel);
        if (parseDouble >= 0.5d && parseDouble <= 0.9d) {
            ArrayList<String> arrayList2 = this.speedList;
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : arrayList2) {
                double parseDouble2 = Double.parseDouble((String) obj);
                if (parseDouble2 >= 0.5d && parseDouble2 <= 0.9d) {
                    arrayList3.add(obj);
                }
            }
            arrayList.addAll(arrayList3);
        } else if (parseDouble < 0.5d) {
            ArrayList<String> arrayList4 = this.speedList;
            ArrayList arrayList5 = new ArrayList();
            for (Object obj2 : arrayList4) {
                double parseDouble3 = Double.parseDouble((String) obj2);
                if (parseDouble3 >= parseDouble && parseDouble3 <= 0.9d) {
                    arrayList5.add(obj2);
                }
            }
            arrayList.addAll(arrayList5);
        } else if (parseDouble > 0.9d) {
            ArrayList<String> arrayList6 = this.speedList;
            ArrayList arrayList7 = new ArrayList();
            for (Object obj3 : arrayList6) {
                double parseDouble4 = Double.parseDouble((String) obj3);
                if (parseDouble4 >= 0.5d && parseDouble4 <= parseDouble) {
                    arrayList7.add(obj3);
                }
            }
            arrayList.addAll(arrayList7);
        }
        return arrayList;
    }

    private final void goHomeSpeed() {
        String goHomeSpeedLevel = RobotSpeedUtil.INSTANCE.getGoHomeSpeedLevel(RobotContext.INSTANCE.getContext());
        List<String> initSpeedData = initSpeedData(goHomeSpeedLevel);
        int size = initSpeedData.size();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = initSpeedData.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(goHomeSpeedLevel)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        int indexOf = arrayList2.isEmpty() ? 0 : initSpeedData.indexOf(arrayList2.get(0));
        Pdlog.m3273d(this.TAG, "goHomeSpeed list " + initSpeedData + " level index " + indexOf + " level " + goHomeSpeedLevel);
        IndicatorSeekBar go_home_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(go_home_speed_bar, "go_home_speed_bar");
        go_home_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar);
        Object[] array = initSpeedData.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar go_home_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(go_home_speed_bar2, "go_home_speed_bar");
            go_home_speed_bar2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new SpeedFragment$goHomeSpeed$1(this, size, initSpeedData), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void cruiseSpeed() {
        String cruiseSpeedLevel = RobotSpeedUtil.INSTANCE.getCruiseSpeedLevel(RobotContext.INSTANCE.getContext());
        List<String> initSpeedData = initSpeedData(cruiseSpeedLevel);
        int size = initSpeedData.size();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = initSpeedData.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(cruiseSpeedLevel)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        int indexOf = arrayList2.isEmpty() ? 0 : initSpeedData.indexOf(arrayList2.get(0));
        Pdlog.m3273d(this.TAG, "cruiseSpeed list " + initSpeedData + " level index " + indexOf + " level " + cruiseSpeedLevel);
        IndicatorSeekBar cruise_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(cruise_speed_bar, "cruise_speed_bar");
        cruise_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar);
        Object[] array = initSpeedData.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar cruise_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(cruise_speed_bar2, "cruise_speed_bar");
            cruise_speed_bar2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new SpeedFragment$cruiseSpeed$1(this, size, initSpeedData), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void deliverSpeed() {
        String deliverSpeedLevel = RobotSpeedUtil.INSTANCE.getDeliverSpeedLevel(RobotContext.INSTANCE.getContext());
        List<String> initSpeedData = initSpeedData(deliverSpeedLevel);
        int size = initSpeedData.size();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = initSpeedData.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(deliverSpeedLevel)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        int indexOf = arrayList2.isEmpty() ? 0 : initSpeedData.indexOf(arrayList2.get(0));
        Pdlog.m3273d(this.TAG, "deliverSpeed list " + initSpeedData + " level index " + indexOf + " level " + deliverSpeedLevel);
        IndicatorSeekBar speed_degree = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree);
        Intrinsics.checkExpressionValueIsNotNull(speed_degree, "speed_degree");
        speed_degree.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree);
        Object[] array = initSpeedData.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar speed_degree2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree);
            Intrinsics.checkExpressionValueIsNotNull(speed_degree2, "speed_degree");
            speed_degree2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new SpeedFragment$deliverSpeed$1(this, size, initSpeedData), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void greeterSpeed() {
        String greeterSpeedLevel = RobotSpeedUtil.INSTANCE.getGreeterSpeedLevel(RobotContext.INSTANCE.getContext());
        List<String> initSpeedData = initSpeedData(greeterSpeedLevel);
        int size = initSpeedData.size();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = initSpeedData.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(greeterSpeedLevel)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        int indexOf = arrayList2.isEmpty() ? 0 : initSpeedData.indexOf(arrayList2.get(0));
        Pdlog.m3273d(this.TAG, "greeterSpeed list " + initSpeedData + " level index " + indexOf + " level " + greeterSpeedLevel);
        IndicatorSeekBar greeter_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.greeter_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(greeter_speed_bar, "greeter_speed_bar");
        greeter_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.greeter_speed_bar);
        Object[] array = initSpeedData.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.greeter_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.greeter_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar greeter_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.greeter_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(greeter_speed_bar2, "greeter_speed_bar");
            greeter_speed_bar2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new SpeedFragment$greeterSpeed$1(this, size, initSpeedData), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void recyclingSpeed() {
        String recyclingSpeedLevel = RobotSpeedUtil.INSTANCE.getRecyclingSpeedLevel(RobotContext.INSTANCE.getContext());
        List<String> initSpeedData = initSpeedData(recyclingSpeedLevel);
        int size = initSpeedData.size();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = initSpeedData.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(recyclingSpeedLevel)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        int indexOf = arrayList2.isEmpty() ? 0 : initSpeedData.indexOf(arrayList2.get(0));
        Pdlog.m3273d(this.TAG, "recyclingSpeed list " + initSpeedData + " level index " + indexOf + " level " + recyclingSpeedLevel);
        IndicatorSeekBar recycle_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(recycle_speed_bar, "recycle_speed_bar");
        recycle_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar);
        Object[] array = initSpeedData.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar recycle_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(recycle_speed_bar2, "recycle_speed_bar");
            recycle_speed_bar2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new SpeedFragment$recyclingSpeed$1(this, size, initSpeedData), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void initSafeModeSwitch() {
        SwitchWrapper safe_switch = (SwitchWrapper) _$_findCachedViewById(C4188R.id.safe_switch);
        Intrinsics.checkExpressionValueIsNotNull(safe_switch, "safe_switch");
        safe_switch.setChecked(Constans.INSTANCE.isSafeMode());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initSafeModeSwitch ");
        SwitchWrapper safe_switch2 = (SwitchWrapper) _$_findCachedViewById(C4188R.id.safe_switch);
        Intrinsics.checkExpressionValueIsNotNull(safe_switch2, "safe_switch");
        sb.append(safe_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((SwitchWrapper) _$_findCachedViewById(C4188R.id.safe_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SpeedFragment$initSafeModeSwitch$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton view, boolean z) {
                boolean z2;
                String str2;
                boolean z3;
                Intrinsics.checkParameterIsNotNull(view, "view");
                z2 = SpeedFragment.this.isSafeModeSwitching;
                if (!z2) {
                    SpeedFragment.this.isSafeModeSwitching = true;
                    str2 = SpeedFragment.this.TAG;
                    Pdlog.m3273d(str2, "change SafeModeSwitch " + z);
                    SpeedFragment.this.safeModeOldState = z ^ true;
                    MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
                    z3 = SpeedFragment.this.safeModeOldState;
                    mirSdkManager.switchSecurity(z3, z);
                    return;
                }
                ((SwitchWrapper) SpeedFragment.this._$_findCachedViewById(C4188R.id.safe_switch)).silentlySetChecked(!z);
            }
        }, 3, null);
        SafeModeManager.INSTANCE.addSafeStateListener(this.safeModeListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        SafeModeManager.INSTANCE.removeSafeStateListener(this.safeModeListener);
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTooFast(int speed_index, List<String> speedData) {
        try {
            return Double.parseDouble(speedData.get(speed_index)) >= 0.9d;
        } catch (Exception unused) {
            return false;
        }
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
        } else if (type == this.TYPE_GOHOME) {
            if (this.isDIalogGoHomeShow) {
                return;
            } else {
                this.isDIalogGoHomeShow = true;
            }
        } else if (type == this.TYPE_GREETER) {
            if (this.isDIalogGreeterShow) {
                return;
            } else {
                this.isDIalogGreeterShow = true;
            }
        } else if (type == this.TYPE_RECYCLE) {
            if (this.isDialogRecycleShow) {
                return;
            } else {
                this.isDialogRecycleShow = true;
            }
        }
        if (getContext() == null) {
            return;
        }
        if (this.dialog == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            this.dialog = new ShowTipMsgDialog(requireActivity);
            ShowTipMsgDialog showTipMsgDialog = this.dialog;
            if (showTipMsgDialog == null) {
                Intrinsics.throwNpe();
            }
            String string = getString(C4188R.string.pdStr7_62);
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
