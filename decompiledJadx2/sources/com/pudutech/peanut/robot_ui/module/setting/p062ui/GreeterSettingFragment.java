package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectPositionAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectPositionItem;
import com.pudutech.peanut.robot_ui.p063ui.adapter.TtsVoiceAdapter;
import com.pudutech.peanut.robot_ui.p063ui.dialog.CustomTtsVoiceInputDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.p063ui.view.MaxHeightRecyclerView;
import com.pudutech.peanut.robot_ui.util.InputMethodUtil;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: GreeterSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\rH\u0002J\b\u0010\u0012\u001a\u00020\rH\u0002J\b\u0010\u0013\u001a\u00020\rH\u0002J\b\u0010\u0014\u001a\u00020\rH\u0003J\b\u0010\u0015\u001a\u00020\rH\u0002J&\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\rH\u0016J\b\u0010\u001f\u001a\u00020\rH\u0016J\u001a\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\"\u001a\u00020\rH\u0002J\b\u0010#\u001a\u00020\rH\u0002J\b\u0010$\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/GreeterSettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "customVoiceType", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "outSiteAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectPositionAdapter;", "ttsVoiceAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TtsVoiceAdapter;", "usherAdapter", "gotToAddCustomWordsDialog", "", "initCruiseVoiceInterval", "initCustomVoiceView", "initData", "initDiningLetOutData", "initGreeterSwitch", "initUsherData", "initView", "initViewData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onDetach", "onViewCreated", "view", "setCustomVoiceEmptyVisible", "showCustomInputDialog", "updateCustomWords", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GreeterSettingFragment extends Fragment {
    private HashMap _$_findViewCache;
    private SelectPositionAdapter outSiteAdapter;
    private TtsVoiceAdapter ttsVoiceAdapter;
    private SelectPositionAdapter usherAdapter;
    private final String TAG = "SpeedFragment";
    private TtsVoiceHelper.TtsVoiceType customVoiceType = TtsVoiceHelper.TtsVoiceType.USHER_TYPE;

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

    public static final /* synthetic */ TtsVoiceAdapter access$getTtsVoiceAdapter$p(GreeterSettingFragment greeterSettingFragment) {
        TtsVoiceAdapter ttsVoiceAdapter = greeterSettingFragment.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        return ttsVoiceAdapter;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_greeter_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initCustomVoiceView();
        initCruiseVoiceInterval();
        initGreeterSwitch();
    }

    private final void initGreeterSwitch() {
        Switch deliver_face_mode_switch = (Switch) _$_findCachedViewById(C5508R.id.deliver_face_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_face_mode_switch, "deliver_face_mode_switch");
        deliver_face_mode_switch.setChecked(Constans.INSTANCE.getGreeterFaceSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initGreeterSwitch ");
        Switch deliver_face_mode_switch2 = (Switch) _$_findCachedViewById(C5508R.id.deliver_face_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_face_mode_switch2, "deliver_face_mode_switch");
        sb.append(deliver_face_mode_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C5508R.id.deliver_face_mode_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initGreeterSwitch$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = GreeterSettingFragment.this.TAG;
                Pdlog.m3273d(str2, "greeter_mode_switch " + z);
                Constans.INSTANCE.setGreeterFaceSwitch(z);
            }
        });
    }

    private final void initData() {
        initViewData();
    }

    private final void initViewData() {
        initDiningLetOutData();
        initUsherData();
    }

    private final void initUsherData() {
        ArrayList<String> ushers = RobotMapManager.INSTANCE.getUshers();
        ArrayList arrayList = new ArrayList();
        for (String str : ushers) {
            arrayList.add(new SelectPositionItem(str, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentTakeMap())));
        }
        if (arrayList.size() > 0) {
            TextView usher_position_tv = (TextView) _$_findCachedViewById(C5508R.id.usher_position_tv);
            Intrinsics.checkExpressionValueIsNotNull(usher_position_tv, "usher_position_tv");
            usher_position_tv.setVisibility(0);
            CardView usher_position_layout = (CardView) _$_findCachedViewById(C5508R.id.usher_position_layout);
            Intrinsics.checkExpressionValueIsNotNull(usher_position_layout, "usher_position_layout");
            usher_position_layout.setVisibility(0);
        } else {
            CardView usher_position_layout2 = (CardView) _$_findCachedViewById(C5508R.id.usher_position_layout);
            Intrinsics.checkExpressionValueIsNotNull(usher_position_layout2, "usher_position_layout");
            usher_position_layout2.setVisibility(8);
        }
        SelectPositionAdapter selectPositionAdapter = this.usherAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        selectPositionAdapter.setNewData(arrayList);
    }

    private final void initDiningLetOutData() {
        ArrayList arrayList = new ArrayList();
        for (String str : RobotMapManager.INSTANCE.getUshers()) {
            arrayList.add(new SelectPositionItem(str, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentMapUsherChosen())));
        }
        if (arrayList.size() <= 0) {
            CardView out_food_position_layout = (CardView) _$_findCachedViewById(C5508R.id.out_food_position_layout);
            Intrinsics.checkExpressionValueIsNotNull(out_food_position_layout, "out_food_position_layout");
            out_food_position_layout.setVisibility(8);
        }
        SelectPositionAdapter selectPositionAdapter = this.outSiteAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
        }
        selectPositionAdapter.setNewData(arrayList);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Pdlog.m3273d(this.TAG, "onDestroyView");
        Function0<Unit> function0 = (Function0) null;
        TtsVoiceHelper.INSTANCE.setOnCruiseConfigChangeListener(function0);
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(function0);
        TtsVoiceHelper.INSTANCE.setOnSolicitConfigChangeListener(function0);
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
        TtsVoiceHelper.INSTANCE.setOnSolicitConfigChangeListener(function0);
    }

    private final void initView() {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        this.outSiteAdapter = new SelectPositionAdapter(context);
        MaxHeightRecyclerView solicit_map_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.solicit_map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(solicit_map_recycler_view, "solicit_map_recycler_view");
        solicit_map_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView solicit_map_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.solicit_map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(solicit_map_recycler_view2, "solicit_map_recycler_view");
        SelectPositionAdapter selectPositionAdapter = this.outSiteAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
        }
        solicit_map_recycler_view2.setAdapter(selectPositionAdapter);
        SelectPositionAdapter selectPositionAdapter2 = this.outSiteAdapter;
        if (selectPositionAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
        }
        selectPositionAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initView$1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                List<Object> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i2 = 0;
                for (Object obj : data) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        SelectPositionItem selectPositionItem = (SelectPositionItem) obj;
                        selectPositionItem.setSelect(i2 == i);
                        if (selectPositionItem.isSelect()) {
                            RobotMapManager.INSTANCE.setCurrentMapUsherChosen(selectPositionItem.getId());
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectPositionItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        this.usherAdapter = new SelectPositionAdapter(context2);
        MaxHeightRecyclerView take_map_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.take_map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(take_map_recycler_view, "take_map_recycler_view");
        take_map_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView take_map_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.take_map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(take_map_recycler_view2, "take_map_recycler_view");
        SelectPositionAdapter selectPositionAdapter3 = this.usherAdapter;
        if (selectPositionAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        take_map_recycler_view2.setAdapter(selectPositionAdapter3);
        SelectPositionAdapter selectPositionAdapter4 = this.usherAdapter;
        if (selectPositionAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        selectPositionAdapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initView$2
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                List<Object> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i2 = 0;
                for (Object obj : data) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        SelectPositionItem selectPositionItem = (SelectPositionItem) obj;
                        selectPositionItem.setSelect(i2 == i);
                        if (selectPositionItem.isSelect()) {
                            RobotMapManager.INSTANCE.setCurrentTakeMap(selectPositionItem.getId());
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectPositionItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private final void initCruiseVoiceInterval() {
        Pdlog.m3273d(this.TAG, "initSolicitVoiceInterval");
        final ArrayList arrayListOf = CollectionsKt.arrayListOf("5", "10", "15", "20", "25");
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), Constans.KEY_USHER_VOICE_INTERVAL, "20");
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
            voice_interval_degree.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCruiseVoiceInterval$1
                private int interval;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str2 = GreeterSettingFragment.this.TAG;
                    Pdlog.m3275i(str2, "onSeeking voice_interval ====" + params.progressFloat);
                    this.interval = params.progress;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int roundToInt = MathKt.roundToInt((this.interval / 100.0f) * (arrayListOf.size() - 1));
                    str2 = GreeterSettingFragment.this.TAG;
                    Pdlog.m3275i(str2, "voice_interval onStopTrackingTouch:  " + this.interval + ' ' + roundToInt + ' ' + ((String) arrayListOf.get(roundToInt)));
                    Context context = RobotContext.INSTANCE.getContext();
                    Object obj2 = arrayListOf.get(roundToInt);
                    Intrinsics.checkExpressionValueIsNotNull(obj2, "listArray[index]");
                    SpUtils.set(context, Constans.KEY_USHER_VOICE_INTERVAL, (String) obj2);
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
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
        ttsVoiceAdapter3.setEmptyView(C5508R.layout.layout_custom_word_empty, (RecyclerView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_recycler_view));
        TtsVoiceAdapter ttsVoiceAdapter4 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        View emptyView = ttsVoiceAdapter4.getEmptyView();
        Intrinsics.checkExpressionValueIsNotNull(emptyView, "ttsVoiceAdapter.emptyView");
        LinearLayout linearLayout = (LinearLayout) emptyView.findViewById(C5508R.id.layoutCustomVoiceEmpty);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "ttsVoiceAdapter.emptyView.layoutCustomVoiceEmpty");
        ViewExtKt.onSingleClick(linearLayout, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$1
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
                GreeterSettingFragment.this.gotToAddCustomWordsDialog();
            }
        });
        TtsVoiceAdapter ttsVoiceAdapter5 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter5.setNewData(TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null));
        TtsVoiceAdapter ttsVoiceAdapter6 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter6.setType(this.customVoiceType);
        TtsVoiceAdapter ttsVoiceAdapter7 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter7.setOnCloseSwtichListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$2
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
                Switch open_solicit_switch = (Switch) GreeterSettingFragment.this._$_findCachedViewById(C5508R.id.open_solicit_switch);
                Intrinsics.checkExpressionValueIsNotNull(open_solicit_switch, "open_solicit_switch");
                if (open_solicit_switch.isChecked()) {
                    Switch open_solicit_switch2 = (Switch) GreeterSettingFragment.this._$_findCachedViewById(C5508R.id.open_solicit_switch);
                    Intrinsics.checkExpressionValueIsNotNull(open_solicit_switch2, "open_solicit_switch");
                    open_solicit_switch2.setChecked(false);
                }
            }
        });
        TtsVoiceHelper.INSTANCE.setOnCruiseConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$3
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
                if (GreeterSettingFragment.this.getContext() != null) {
                    ttsVoiceType = GreeterSettingFragment.this.customVoiceType;
                    if (ttsVoiceType == TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE) {
                        GreeterSettingFragment.access$getTtsVoiceAdapter$p(GreeterSettingFragment.this).notifyDataSetChanged();
                    }
                }
            }
        });
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$4
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
                if (GreeterSettingFragment.this.getContext() != null) {
                    ttsVoiceType = GreeterSettingFragment.this.customVoiceType;
                    if (ttsVoiceType == TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE) {
                        GreeterSettingFragment.access$getTtsVoiceAdapter$p(GreeterSettingFragment.this).notifyDataSetChanged();
                    }
                }
            }
        });
        TtsVoiceHelper.INSTANCE.setOnSolicitConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$5
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
                if (GreeterSettingFragment.this.getContext() != null) {
                    ttsVoiceType = GreeterSettingFragment.this.customVoiceType;
                    if (ttsVoiceType == TtsVoiceHelper.TtsVoiceType.SOLICIT_TYPE) {
                        GreeterSettingFragment.access$getTtsVoiceAdapter$p(GreeterSettingFragment.this).notifyDataSetChanged();
                    }
                }
            }
        });
        TtsVoiceHelper.INSTANCE.setOnUsherConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$6
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
                if (GreeterSettingFragment.this.getContext() != null) {
                    ttsVoiceType = GreeterSettingFragment.this.customVoiceType;
                    if (ttsVoiceType == TtsVoiceHelper.TtsVoiceType.USHER_TYPE) {
                        GreeterSettingFragment.this.updateCustomWords();
                    }
                }
            }
        });
        ((LinearLayout) _$_findCachedViewById(C5508R.id.addVoiceLlv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GreeterSettingFragment.this.gotToAddCustomWordsDialog();
            }
        });
        Switch open_solicit_switch = (Switch) _$_findCachedViewById(C5508R.id.open_solicit_switch);
        Intrinsics.checkExpressionValueIsNotNull(open_solicit_switch, "open_solicit_switch");
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        open_solicit_switch.setChecked(ttsVoiceHelper.checkTtsOpenType(requireContext2, this.customVoiceType) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        ((Switch) _$_findCachedViewById(C5508R.id.open_solicit_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType2;
                str = GreeterSettingFragment.this.TAG;
                Pdlog.m3273d(str, "open_cruise_switch " + z);
                if (z) {
                    Constans.INSTANCE.setGreeterSwitch(true);
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    Context requireContext3 = GreeterSettingFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType = TtsVoiceHelper.TtsVoiceOpenType.OPEN;
                    ttsVoiceType2 = GreeterSettingFragment.this.customVoiceType;
                    ttsVoiceHelper2.setTtsType(requireContext3, ttsVoiceOpenType, ttsVoiceType2);
                } else {
                    Constans.INSTANCE.setGreeterSwitch(false);
                    TtsVoiceHelper ttsVoiceHelper3 = TtsVoiceHelper.INSTANCE;
                    Context requireContext4 = GreeterSettingFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext4, "requireContext()");
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType2 = TtsVoiceHelper.TtsVoiceOpenType.CLOSE;
                    ttsVoiceType = GreeterSettingFragment.this.customVoiceType;
                    ttsVoiceHelper3.setTtsType(requireContext4, ttsVoiceOpenType2, ttsVoiceType);
                }
                GreeterSettingFragment.access$getTtsVoiceAdapter$p(GreeterSettingFragment.this).notifyDataSetChanged();
            }
        });
        setCustomVoiceEmptyVisible();
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$initCustomVoiceView$9
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
                GreeterSettingFragment.this.updateCustomWords();
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
        customTtsVoiceInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.GreeterSettingFragment$showCustomInputDialog$1
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
                if (GreeterSettingFragment.this.getContext() != null) {
                    GreeterSettingFragment.this.updateCustomWords();
                }
            }
        });
    }
}
