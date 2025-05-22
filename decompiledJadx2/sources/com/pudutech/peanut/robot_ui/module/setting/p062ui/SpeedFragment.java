package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.util.PlaySound;
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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpeedFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u0012\u0010\u0016\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u0006H\u0002J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u001a\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0010j\b\u0012\u0004\u0012\u00020\u0004`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/SpeedFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "TYPE_CRUISE", "", "TYPE_DELIVER", "TYPE_DIRECT", "dialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "isDialogCruiseShow", "", "isDialogDeliverShow", "isDialogDirectShow", "speedList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "cruiseSpeed", "", "deliverSpeed", "directSpeed", "getSpeed", "p", "initSpeedData", "isTooFast", "speed_index", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "showFastSpeedTip", "type", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SpeedFragment extends Fragment {
    private HashMap _$_findViewCache;
    private ShowTipMsgDialog dialog;
    private boolean isDialogCruiseShow;
    private boolean isDialogDeliverShow;
    private boolean isDialogDirectShow;
    private final String TAG = "SpeedFragment";
    private final ArrayList<String> speedList = CollectionsKt.arrayListOf(Constans.KEY_DEFAULT_SPEED_CONFIG, "0.6", "0.7", "0.8", "0.9", "1.0", BuildConfig.VERSION_NAME, "1.2");
    private final int TYPE_CRUISE = 1;
    private final int TYPE_DELIVER = 2;
    private final int TYPE_DIRECT = 3;

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
        return inflater.inflate(C5508R.layout.fragment_speed_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initSpeedData();
        deliverSpeed();
        directSpeed();
        cruiseSpeed();
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
            cruise_speed_bar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.SpeedFragment$cruiseSpeed$1
                private float currentCruiseSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = SpeedFragment.this.TAG;
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
                    str = SpeedFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("cruiseSpeed onStopTrackingTouch:  ");
                    sb.append(this.currentCruiseSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = SpeedFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = SpeedFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutReturnSpeed(Float.parseFloat((String) obj));
                    isTooFast = SpeedFragment.this.isTooFast(rint);
                    if (isTooFast) {
                        SpeedFragment speedFragment = SpeedFragment.this;
                        i = speedFragment.TYPE_CRUISE;
                        speedFragment.showFastSpeedTip(i);
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
        float allSpeed = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getAllSpeed();
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == allSpeed) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "speed list " + this.speedList + " level index " + indexOf + " level " + allSpeed);
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
            speed_degree2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.SpeedFragment$deliverSpeed$1
                private float currentDeliverSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = SpeedFragment.this.TAG;
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
                    str = SpeedFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onStopTrackingTouch:  ");
                    sb.append(this.currentDeliverSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = SpeedFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = SpeedFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setAllSpeed(Float.parseFloat((String) obj));
                    isTooFast = SpeedFragment.this.isTooFast(rint);
                    if (isTooFast) {
                        SpeedFragment speedFragment = SpeedFragment.this;
                        i = speedFragment.TYPE_DELIVER;
                        speedFragment.showFastSpeedTip(i);
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
            speed_direct2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.SpeedFragment$directSpeed$1
                private float currentDeliverSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = SpeedFragment.this.TAG;
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
                    str = SpeedFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onStopTrackingTouch:  ");
                    sb.append(this.currentDeliverSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = SpeedFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = SpeedFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutDirectSpeed(Float.parseFloat((String) obj));
                    isTooFast = SpeedFragment.this.isTooFast(rint);
                    if (isTooFast) {
                        SpeedFragment speedFragment = SpeedFragment.this;
                        i = speedFragment.TYPE_DIRECT;
                        speedFragment.showFastSpeedTip(i);
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
