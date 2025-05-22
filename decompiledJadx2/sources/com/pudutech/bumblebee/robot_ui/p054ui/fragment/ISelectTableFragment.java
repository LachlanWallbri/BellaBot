package com.pudutech.bumblebee.robot_ui.p054ui.fragment;

import android.view.View;
import androidx.fragment.app.Fragment;
import com.pudutech.freeinstall_ui.utils.Constants;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: ISelectTableFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\fH&R\u0018\u0010\u0003\u001a\u00020\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\u0005\"\u0004\b\t\u0010\u0007R5\u0010\n\u001a\u001f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/fragment/ISelectTableFragment;", "Landroidx/fragment/app/Fragment;", "()V", "isBirthdayTheme", "", "()Z", "setBirthdayTheme", "(Z)V", "isShowAllGroup", "setShowAllGroup", "onSelectTable", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", Constants.POINT_TYPE_TABLE, "", "getOnSelectTable", "()Lkotlin/jvm/functions/Function1;", "setOnSelectTable", "(Lkotlin/jvm/functions/Function1;)V", "hasTable", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class ISelectTableFragment extends Fragment {
    private HashMap _$_findViewCache;

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

    public abstract Function1<String, Unit> getOnSelectTable();

    public abstract boolean hasTable(String table);

    public abstract boolean isBirthdayTheme();

    public abstract boolean isShowAllGroup();

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public abstract void setBirthdayTheme(boolean z);

    public abstract void setOnSelectTable(Function1<? super String, Unit> function1);

    public abstract void setShowAllGroup(boolean z);
}
