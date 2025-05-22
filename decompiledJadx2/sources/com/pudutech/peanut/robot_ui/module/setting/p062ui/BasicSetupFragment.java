package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.SupportedLocaleExtKt;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectLanguageAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectLanguageItem;
import com.pudutech.peanut.robot_ui.p063ui.view.MaxHeightRecyclerView;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.SystemBrightManager;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.Option;
import com.pudutech.resources.language.SupportedLocale;
import com.pudutech.robot.module.voice.RobotVoicePlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BasicSetupFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J&\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u001a\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/BasicSetupFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "languageAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectLanguageAdapter;", "languageUtils", "Lcom/pudutech/resources/language/LanguageUtils;", "initData", "", "initSun", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onViewCreated", "view", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BasicSetupFragment extends Fragment {
    private HashMap _$_findViewCache;
    private SelectLanguageAdapter languageAdapter;
    private final String TAG = "BasicSetupFragment";
    private final LanguageUtils languageUtils = new LanguageUtils(RobotContext.INSTANCE.getContext());

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
        return inflater.inflate(C5508R.layout.fragment_base_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private final void initData() {
        ArrayList arrayList = new ArrayList();
        for (Option option : SupportedLocaleExtKt.getList(SupportedLocale.INSTANCE)) {
            arrayList.add(new SelectLanguageItem(option, Intrinsics.areEqual(this.languageUtils.getCurrent(), option)));
        }
        SelectLanguageAdapter selectLanguageAdapter = this.languageAdapter;
        if (selectLanguageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        selectLanguageAdapter.setNewData(arrayList);
    }

    private final void initView() {
        SelectLanguageAdapter selectLanguageAdapter;
        FragmentActivity it = getActivity();
        if (it != null) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            selectLanguageAdapter = new SelectLanguageAdapter(it);
        } else {
            selectLanguageAdapter = null;
        }
        if (selectLanguageAdapter == null) {
            Intrinsics.throwNpe();
        }
        this.languageAdapter = selectLanguageAdapter;
        MaxHeightRecyclerView language_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.language_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(language_recycler_view, "language_recycler_view");
        language_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView language_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.language_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(language_recycler_view2, "language_recycler_view");
        SelectLanguageAdapter selectLanguageAdapter2 = this.languageAdapter;
        if (selectLanguageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        language_recycler_view2.setAdapter(selectLanguageAdapter2);
        SelectLanguageAdapter selectLanguageAdapter3 = this.languageAdapter;
        if (selectLanguageAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        selectLanguageAdapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.BasicSetupFragment$initView$2
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                String str;
                LanguageUtils languageUtils;
                LanguageUtils languageUtils2;
                String str2;
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
                        SelectLanguageItem selectLanguageItem = (SelectLanguageItem) obj;
                        if (i2 == i) {
                            selectLanguageItem.setSelect(true);
                            str = BasicSetupFragment.this.TAG;
                            Pdlog.m3273d(str, "start set language");
                            languageUtils = BasicSetupFragment.this.languageUtils;
                            LanguageUtils.setLocale$default(languageUtils, RobotContext.INSTANCE.getContext(), selectLanguageItem.getOp(), false, 4, null);
                            RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                            languageUtils2 = BasicSetupFragment.this.languageUtils;
                            robotVoicePlayer.switchDefResources(languageUtils2.getCurrent().getLocale());
                            AiVoiceManager.INSTANCE.changLanguage(selectLanguageItem.getOp().getLocale());
                            str2 = BasicSetupFragment.this.TAG;
                            Pdlog.m3273d(str2, "end set language");
                        } else {
                            selectLanguageItem.setSelect(false);
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectLanguageItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initSun();
    }

    private final void initSun() {
        int systemBrightness = SystemBrightManager.getSystemBrightness(getActivity());
        Pdlog.m3273d(this.TAG, "currentScreenBrightness:" + systemBrightness);
        SeekBar light_degree = (SeekBar) _$_findCachedViewById(C5508R.id.light_degree);
        Intrinsics.checkExpressionValueIsNotNull(light_degree, "light_degree");
        light_degree.setProgress(systemBrightness - SystemBrightManager.MINI_VALUE);
        SeekBar light_degree2 = (SeekBar) _$_findCachedViewById(C5508R.id.light_degree);
        Intrinsics.checkExpressionValueIsNotNull(light_degree2, "light_degree");
        light_degree2.setMax(SystemBrightManager.MAX_VALUE - SystemBrightManager.MINI_VALUE);
        ((SeekBar) _$_findCachedViewById(C5508R.id.light_degree)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.BasicSetupFragment$initSun$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String str;
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                int i = progress + SystemBrightManager.MINI_VALUE;
                if (fromUser) {
                    str = BasicSetupFragment.this.TAG;
                    Pdlog.m3273d(str, "user set screen light level to " + i);
                    SystemBrightManager.setSystemBrightness(BasicSetupFragment.this.getActivity(), i);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
            }
        });
    }
}
