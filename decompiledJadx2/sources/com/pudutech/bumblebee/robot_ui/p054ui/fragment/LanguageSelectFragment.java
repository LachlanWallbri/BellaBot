package com.pudutech.bumblebee.robot_ui.p054ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.InitAppManager;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.InitLanguageAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.InitLanguageItem;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LanguageSelectFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u000bH\u0002J\b\u0010\u0011\u001a\u00020\u000bH\u0002J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/fragment/LanguageSelectFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "languageAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/InitLanguageAdapter;", "languageUtils", "Lcom/pudutech/resources/language/LanguageUtils;", "onLanguageSelect", "Lkotlin/Function0;", "", "getOnLanguageSelect", "()Lkotlin/jvm/functions/Function0;", "setOnLanguageSelect", "(Lkotlin/jvm/functions/Function0;)V", "initData", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LanguageSelectFragment extends Fragment {
    private HashMap _$_findViewCache;
    private InitLanguageAdapter languageAdapter;
    private Function0<Unit> onLanguageSelect;
    private final String TAG = "LanguageSelectFragment";
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

    public final Function0<Unit> getOnLanguageSelect() {
        return this.onLanguageSelect;
    }

    public final void setOnLanguageSelect(Function0<Unit> function0) {
        this.onLanguageSelect = function0;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_language_select, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        initView();
        initData();
    }

    private final void initData() {
        ArrayList arrayList = new ArrayList();
        for (Option option : InitAppManager.INSTANCE.getUsrList()) {
            arrayList.add(new InitLanguageItem(option, Intrinsics.areEqual(this.languageUtils.getCurrent(), option)));
        }
        InitLanguageAdapter initLanguageAdapter = this.languageAdapter;
        if (initLanguageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        initLanguageAdapter.setNewData(arrayList);
    }

    private final void initView() {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        this.languageAdapter = new InitLanguageAdapter(context);
        RecyclerView language_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.language_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(language_recycler_view, "language_recycler_view");
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        language_recycler_view.setLayoutManager(new LinearLayoutManager(context2));
        RecyclerView language_recycler_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.language_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(language_recycler_view2, "language_recycler_view");
        InitLanguageAdapter initLanguageAdapter = this.languageAdapter;
        if (initLanguageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        language_recycler_view2.setAdapter(initLanguageAdapter);
        InitLanguageAdapter initLanguageAdapter2 = this.languageAdapter;
        if (initLanguageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        initLanguageAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.LanguageSelectFragment$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                LanguageUtils languageUtils;
                String str2;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                List<?> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        InitLanguageItem initLanguageItem = (InitLanguageItem) obj;
                        if (i == position) {
                            initLanguageItem.setSelect(true);
                            str = LanguageSelectFragment.this.TAG;
                            Pdlog.m3273d(str, "start set language");
                            languageUtils = LanguageSelectFragment.this.languageUtils;
                            LanguageUtils.setLocale$default(languageUtils, RobotContext.INSTANCE.getContext(), initLanguageItem.getOp(), false, 4, null);
                            AiVoiceManager.INSTANCE.changLanguage(initLanguageItem.getOp().getLocale());
                            VoicePlayer.INSTANCE.loadResources();
                            str2 = LanguageSelectFragment.this.TAG;
                            Pdlog.m3273d(str2, "end set language");
                        } else {
                            initLanguageItem.setSelect(false);
                        }
                        i = i2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.InitLanguageItem");
                    }
                }
                adapter.notifyDataSetChanged();
                Function0<Unit> onLanguageSelect = LanguageSelectFragment.this.getOnLanguageSelect();
                if (onLanguageSelect != null) {
                    onLanguageSelect.invoke();
                }
            }
        });
    }
}
