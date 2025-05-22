package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.manager.InitAppManager;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectLanguageAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectLanguageItem;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TransparentLoadDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MaxHeightRecyclerView;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSeekBarChangeListener;
import com.pudutech.bumblebee.robot_ui.util.SystemBrightManager;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.Option;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: BasicSetupFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J&\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\b\u0010\u001e\u001a\u00020\u0010H\u0016J\u001a\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/BasicSetupFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "languageAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectLanguageAdapter;", "languageUtils", "Lcom/pudutech/resources/language/LanguageUtils;", "mCurIndex", "", "mLoadDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/TransparentLoadDialog;", "mScope", "Lkotlinx/coroutines/CoroutineScope;", "creatLoad", "", "creatThread", "initData", "initSun", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onViewCreated", "view", "setProgressTv", "seekBar", "Landroid/widget/SeekBar;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BasicSetupFragment extends Fragment {
    private HashMap _$_findViewCache;
    private SelectLanguageAdapter languageAdapter;
    private TransparentLoadDialog mLoadDialog;
    private CoroutineScope mScope;
    private final String TAG = "BasicSetupFragment";
    private final LanguageUtils languageUtils = new LanguageUtils(RobotContext.INSTANCE.getContext());
    private int mCurIndex = -1;

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
        return inflater.inflate(C4188R.layout.fragment_base_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        creatLoad();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void creatThread() {
        if (this.mScope == null) {
            this.mScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
            Pdlog.m3273d(this.TAG, "creatThread");
        }
    }

    private final void creatLoad() {
        if (this.mLoadDialog != null || getContext() == null) {
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        this.mLoadDialog = new TransparentLoadDialog(requireContext);
    }

    private final void initData() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : InitAppManager.INSTANCE.getUsrList()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Option option = (Option) obj;
            if (Intrinsics.areEqual(this.languageUtils.getCurrent(), option)) {
                this.mCurIndex = i;
                z = true;
            } else {
                z = false;
            }
            arrayList.add(new SelectLanguageItem(option, z));
            i = i2;
        }
        SelectLanguageAdapter selectLanguageAdapter = this.languageAdapter;
        if (selectLanguageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        selectLanguageAdapter.setNewData(arrayList);
    }

    private final void initView() {
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        this.languageAdapter = new SelectLanguageAdapter(requireContext);
        MaxHeightRecyclerView language_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.language_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(language_recycler_view, "language_recycler_view");
        language_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView language_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.language_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(language_recycler_view2, "language_recycler_view");
        SelectLanguageAdapter selectLanguageAdapter = this.languageAdapter;
        if (selectLanguageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        language_recycler_view2.setAdapter(selectLanguageAdapter);
        SelectLanguageAdapter selectLanguageAdapter2 = this.languageAdapter;
        if (selectLanguageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        selectLanguageAdapter2.setOnItemClickListener(new BasicSetupFragment$initView$1(this));
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initSun();
    }

    private final void initSun() {
        int systemBrightness = SystemBrightManager.getSystemBrightness(getActivity());
        Pdlog.m3273d(this.TAG, "currentScreenBrightness:" + systemBrightness);
        SeekBar light_degree = (SeekBar) _$_findCachedViewById(C4188R.id.light_degree);
        Intrinsics.checkExpressionValueIsNotNull(light_degree, "light_degree");
        light_degree.setProgress(systemBrightness - SystemBrightManager.MINI_VALUE);
        SeekBar light_degree2 = (SeekBar) _$_findCachedViewById(C4188R.id.light_degree);
        Intrinsics.checkExpressionValueIsNotNull(light_degree2, "light_degree");
        light_degree2.setMax(SystemBrightManager.MAX_VALUE - SystemBrightManager.MINI_VALUE);
        SeekBar light_degree3 = (SeekBar) _$_findCachedViewById(C4188R.id.light_degree);
        Intrinsics.checkExpressionValueIsNotNull(light_degree3, "light_degree");
        setProgressTv(light_degree3);
        ((SeekBar) _$_findCachedViewById(C4188R.id.light_degree)).setOnSeekBarChangeListener(new VoiceSeekBarChangeListener(false, null, 0, new Function3<SeekBar, Integer, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BasicSetupFragment$initSun$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SeekBar seekBar, Integer num, Boolean bool) {
                invoke(seekBar, num.intValue(), bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(SeekBar seekBar, int i, boolean z) {
                String str;
                String str2;
                int i2 = i + SystemBrightManager.MINI_VALUE;
                if (seekBar != null) {
                    BasicSetupFragment.this.setProgressTv(seekBar);
                }
                if (z) {
                    if (SystemBrightManager.isAutoBrightness(BasicSetupFragment.this.getActivity())) {
                        str2 = BasicSetupFragment.this.TAG;
                        Pdlog.m3273d(str2, "change light mode from auto to manual");
                        SystemBrightManager.setBrightnessMode(BasicSetupFragment.this.getActivity(), 0);
                    }
                    str = BasicSetupFragment.this.TAG;
                    Pdlog.m3273d(str, "user set screen light level to " + i2);
                    SystemBrightManager.setSystemBrightness(BasicSetupFragment.this.getActivity(), i2);
                }
            }
        }, null, null, 55, null));
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        TransparentLoadDialog transparentLoadDialog = this.mLoadDialog;
        if (transparentLoadDialog != null) {
            transparentLoadDialog.dismiss();
        }
        CoroutineScope coroutineScope = this.mScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setProgressTv(SeekBar seekBar) {
        int progress = (seekBar.getProgress() * 100) / seekBar.getMax();
        TextView progress_tv = (TextView) _$_findCachedViewById(C4188R.id.progress_tv);
        Intrinsics.checkExpressionValueIsNotNull(progress_tv, "progress_tv");
        StringBuilder sb = new StringBuilder();
        sb.append(progress);
        sb.append('%');
        progress_tv.setText(sb.toString());
    }
}
