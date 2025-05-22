package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
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
import com.pudutech.peanut.robot_ui.util.LanguageUtils;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.robot.peripherals.BuildConfig;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: CuriseSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010$\u001a\u00020\u0010H\u0016J\b\u0010%\u001a\u00020\u0010H\u0016J\b\u0010&\u001a\u00020\u0010H\u0016J\u001a\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010)\u001a\u00020\u0010H\u0002J\b\u0010*\u001a\u00020\u0010H\u0002J\b\u0010+\u001a\u00020\u0010H\u0002J\b\u0010,\u001a\u00020\u0010H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u000bj\b\u0012\u0004\u0012\u00020\u0004`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006-"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/CuriseSettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "customVoiceType", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "dialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "speedList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "ttsVoiceAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TtsVoiceAdapter;", "changeCustomVoiceData", "", "checkLanguage", "curiseSpeed", "gotToAddCustomWordsDialog", "initCruiseVoiceInterval", "initCustomVoiceView", "initSpeedData", "isTooFast", "", "speed_index", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onDetach", "onViewCreated", "view", "setCustomVoiceEmptyVisible", "showCustomInputDialog", "showFastSpeedTip", "updateCustomWords", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CuriseSettingFragment extends Fragment {
    private HashMap _$_findViewCache;
    private ShowTipMsgDialog dialog;
    private TtsVoiceAdapter ttsVoiceAdapter;
    private final String TAG = getClass().getSimpleName();
    private final ArrayList<String> speedList = CollectionsKt.arrayListOf(Constans.KEY_DEFAULT_SPEED_CONFIG, "0.6", "0.7", "0.8", "0.9", "1.0", BuildConfig.VERSION_NAME, "1.2");
    private TtsVoiceHelper.TtsVoiceType customVoiceType = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;

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

    public static final /* synthetic */ TtsVoiceAdapter access$getTtsVoiceAdapter$p(CuriseSettingFragment curiseSettingFragment) {
        TtsVoiceAdapter ttsVoiceAdapter = curiseSettingFragment.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        return ttsVoiceAdapter;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_curise_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initCruiseVoiceInterval();
        initSpeedData();
        initCustomVoiceView();
        checkLanguage();
        curiseSpeed();
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

    private final void curiseSpeed() {
        final int size = this.speedList.size();
        float peanutCuriseSpeed = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.getPeanutCuriseSpeed();
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == peanutCuriseSpeed) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "speed list " + this.speedList + " level index " + indexOf + " level " + peanutCuriseSpeed);
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
            speed_degree2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$curiseSpeed$1
                private float currentDeliverSpeed;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str = CuriseSettingFragment.this.TAG;
                    Pdlog.m3275i(str, "onSeeking====" + params.progressFloat);
                    this.currentDeliverSpeed = params.progressFloat;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str;
                    ArrayList arrayList4;
                    ArrayList arrayList5;
                    boolean isTooFast;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int rint = (int) Math.rint((this.currentDeliverSpeed / 100.0f) * (size - 1));
                    str = CuriseSettingFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onStopTrackingTouch:  ");
                    sb.append(this.currentDeliverSpeed);
                    sb.append(' ');
                    sb.append(rint);
                    sb.append(' ');
                    arrayList4 = CuriseSettingFragment.this.speedList;
                    sb.append((String) arrayList4.get(rint));
                    Pdlog.m3275i(str, sb.toString());
                    com.pudutech.peanut.robot_ui.config.Constans constans = com.pudutech.peanut.robot_ui.config.Constans.INSTANCE;
                    arrayList5 = CuriseSettingFragment.this.speedList;
                    Object obj = arrayList5.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "speedList[speed_index]");
                    constans.setPeanutCuriseSpeed(Float.parseFloat((String) obj));
                    isTooFast = CuriseSettingFragment.this.isTooFast(rint);
                    if (isTooFast) {
                        CuriseSettingFragment.this.showFastSpeedTip();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFastSpeedTip() {
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

    private final void initCruiseVoiceInterval() {
        Pdlog.m3273d(this.TAG, "initCruiseVoiceInterval");
        final ArrayList arrayListOf = CollectionsKt.arrayListOf("5", "10", "15", "20", "25");
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_cruise_voice_interval", "20");
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListOf) {
            if (Integer.parseInt((String) obj) == Integer.parseInt(str)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        int indexOf = arrayList2.isEmpty() ? 0 : arrayListOf.indexOf(arrayList2.get(0));
        Pdlog.m3273d(this.TAG, "cruiseSpeed list " + arrayListOf + " level index " + indexOf + " level " + str);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.voice_interval_degree);
        Object[] array = arrayListOf.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.voice_interval_degree)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.voice_interval_degree)).setProgress((indexOf / (arrayListOf.size() - 1)) * 100.0f);
            IndicatorSeekBar voice_interval_degree = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.voice_interval_degree);
            Intrinsics.checkExpressionValueIsNotNull(voice_interval_degree, "voice_interval_degree");
            voice_interval_degree.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$initCruiseVoiceInterval$1
                private int interval;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str2 = CuriseSettingFragment.this.TAG;
                    Pdlog.m3275i(str2, "onSeeking voice_interval ====" + params.progressFloat);
                    this.interval = params.progress;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int roundToInt = MathKt.roundToInt((this.interval / 100.0f) * (arrayListOf.size() - 1));
                    str2 = CuriseSettingFragment.this.TAG;
                    Pdlog.m3275i(str2, "voice_interval onStopTrackingTouch:  " + this.interval + ' ' + roundToInt + ' ' + ((String) arrayListOf.get(roundToInt)));
                    Context context = RobotContext.INSTANCE.getContext();
                    Object obj2 = arrayListOf.get(roundToInt);
                    Intrinsics.checkExpressionValueIsNotNull(obj2, "listArray[index]");
                    SpUtils.set(context, "key_cruise_voice_interval", (String) obj2);
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void checkLanguage() {
        LanguageUtils.Companion companion = LanguageUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        boolean isZh = companion.isZh(requireContext);
        LanguageUtils.Companion companion2 = LanguageUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        boolean isEnglish = companion2.isEnglish(requireContext2);
        if (isZh || isEnglish) {
            RelativeLayout cruise_voice_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.cruise_voice_layout);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_layout, "cruise_voice_layout");
            cruise_voice_layout.setVisibility(0);
            CardView tts_cruise_voice_layout = (CardView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_layout);
            Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_layout, "tts_cruise_voice_layout");
            tts_cruise_voice_layout.setVisibility(0);
            return;
        }
        RelativeLayout cruise_voice_layout2 = (RelativeLayout) _$_findCachedViewById(C5508R.id.cruise_voice_layout);
        Intrinsics.checkExpressionValueIsNotNull(cruise_voice_layout2, "cruise_voice_layout");
        cruise_voice_layout2.setVisibility(8);
        CardView tts_cruise_voice_layout2 = (CardView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_layout);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_layout2, "tts_cruise_voice_layout");
        tts_cruise_voice_layout2.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    private final void showCustomInputDialog() {
        if (TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null).size() >= 30) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), getString(C5508R.string.custom_word_arrived_max), new Object[0]);
            return;
        }
        CustomTtsVoiceInputDialog customTtsVoiceInputDialog = new CustomTtsVoiceInputDialog();
        String string = getString(C5508R.string.pdStr7_121);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_121)");
        customTtsVoiceInputDialog.setTitle(string);
        customTtsVoiceInputDialog.setType(this.customVoiceType);
        FragmentManager requireFragmentManager = requireFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(requireFragmentManager, "requireFragmentManager()");
        customTtsVoiceInputDialog.show(requireFragmentManager, "voice_custom");
        customTtsVoiceInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$showCustomInputDialog$1
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
                if (CuriseSettingFragment.this.getContext() != null) {
                    CuriseSettingFragment.this.updateCustomWords();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Pdlog.m3273d(this.TAG, "onDestroyView");
        Function0<Unit> function0 = (Function0) null;
        TtsVoiceHelper.INSTANCE.setOnCruiseConfigChangeListener(function0);
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(function0);
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        TtsVoiceAdapter ttsVoiceAdapter = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter.resetPlayStatus();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Pdlog.m3273d(this.TAG, "onDetach");
        Function0<Unit> function0 = (Function0) null;
        TtsVoiceHelper.INSTANCE.setOnCruiseConfigChangeListener(function0);
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(function0);
    }

    private final void changeCustomVoiceData() {
        TtsVoiceAdapter ttsVoiceAdapter = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter.setNewData(TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null));
        Switch open_cruise_switch = (Switch) _$_findCachedViewById(C5508R.id.open_cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch, "open_cruise_switch");
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        open_cruise_switch.setChecked(ttsVoiceHelper.checkTtsOpenType(requireContext, this.customVoiceType) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        TtsVoiceAdapter ttsVoiceAdapter2 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter2.setType(this.customVoiceType);
    }

    private final void initCustomVoiceView() {
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
        ViewExtKt.onSingleClick(linearLayout, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$initCustomVoiceView$1
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
                CuriseSettingFragment.this.gotToAddCustomWordsDialog();
            }
        });
        TtsVoiceHelper.INSTANCE.setOnCruiseConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$initCustomVoiceView$2
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
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                if (CuriseSettingFragment.this.getContext() != null) {
                    ttsVoiceType = CuriseSettingFragment.this.customVoiceType;
                    if (ttsVoiceType == TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE) {
                        CuriseSettingFragment.this.updateCustomWords();
                    }
                }
            }
        });
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$initCustomVoiceView$3
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
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                if (CuriseSettingFragment.this.getContext() != null) {
                    ttsVoiceType = CuriseSettingFragment.this.customVoiceType;
                    if (ttsVoiceType == TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE) {
                        CuriseSettingFragment.access$getTtsVoiceAdapter$p(CuriseSettingFragment.this).notifyDataSetChanged();
                    }
                }
            }
        });
        ((LinearLayout) _$_findCachedViewById(C5508R.id.addVoiceLlv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$initCustomVoiceView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CuriseSettingFragment.this.gotToAddCustomWordsDialog();
            }
        });
        TtsVoiceAdapter ttsVoiceAdapter7 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter7.setOnCloseSwtichListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$initCustomVoiceView$5
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
                Switch open_cruise_switch = (Switch) CuriseSettingFragment.this._$_findCachedViewById(C5508R.id.open_cruise_switch);
                Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch, "open_cruise_switch");
                if (open_cruise_switch.isChecked()) {
                    Switch open_cruise_switch2 = (Switch) CuriseSettingFragment.this._$_findCachedViewById(C5508R.id.open_cruise_switch);
                    Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch2, "open_cruise_switch");
                    open_cruise_switch2.setChecked(false);
                }
            }
        });
        Switch open_cruise_switch = (Switch) _$_findCachedViewById(C5508R.id.open_cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch, "open_cruise_switch");
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        open_cruise_switch.setChecked(ttsVoiceHelper.checkTtsOpenType(requireContext2, this.customVoiceType) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        ((Switch) _$_findCachedViewById(C5508R.id.open_cruise_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.CuriseSettingFragment$initCustomVoiceView$6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType2;
                str = CuriseSettingFragment.this.TAG;
                Pdlog.m3273d(str, "open_cruise_switch " + z);
                if (z) {
                    com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.setCruiseVoiceSwitch(true);
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    Context requireContext3 = CuriseSettingFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType = TtsVoiceHelper.TtsVoiceOpenType.OPEN;
                    ttsVoiceType2 = CuriseSettingFragment.this.customVoiceType;
                    ttsVoiceHelper2.setTtsType(requireContext3, ttsVoiceOpenType, ttsVoiceType2);
                } else {
                    com.pudutech.peanut.robot_ui.config.Constans.INSTANCE.setCruiseVoiceSwitch(false);
                    TtsVoiceHelper ttsVoiceHelper3 = TtsVoiceHelper.INSTANCE;
                    Context requireContext4 = CuriseSettingFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext4, "requireContext()");
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType2 = TtsVoiceHelper.TtsVoiceOpenType.CLOSE;
                    ttsVoiceType = CuriseSettingFragment.this.customVoiceType;
                    ttsVoiceHelper3.setTtsType(requireContext4, ttsVoiceOpenType2, ttsVoiceType);
                }
                CuriseSettingFragment.access$getTtsVoiceAdapter$p(CuriseSettingFragment.this).notifyDataSetChanged();
            }
        });
        setCustomVoiceEmptyVisible();
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
}
