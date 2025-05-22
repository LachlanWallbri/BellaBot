package com.pudutech.bumblebee.robot_ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestination;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeyBtnListDetailView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0002J*\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u000e0\u000e2\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013H\u0002J\u0014\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/KeyBtnListDetailView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TAG", "", "llContent", "Landroid/widget/LinearLayout;", "dividerParams", "Landroid/widget/LinearLayout$LayoutParams;", "dividerView", "Landroid/view/View;", "layoutParams", "newMapItem", "kotlin.jvm.PlatformType", "item", "Lkotlin/Pair;", "", "Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestination;", "setData", "", "list", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class KeyBtnListDetailView extends FrameLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private LinearLayout llContent;

    public KeyBtnListDetailView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ KeyBtnListDetailView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (AttributeSet) null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyBtnListDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "KeyBtnListDetailView";
        FrameLayout.inflate(context, C4188R.layout.view_call_key_btn_list, this);
        View findViewById = findViewById(C4188R.id.llContent);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.llContent)");
        this.llContent = (LinearLayout) findViewById;
    }

    public final void setData(List<KeyBtnWithDestination> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3275i(this.TAG, "setData > " + list);
        View tvEmptyView = findViewById(C4188R.id.tvEmptyView);
        View svContentLayout = findViewById(C4188R.id.svContentLayout);
        if (list.isEmpty()) {
            Intrinsics.checkExpressionValueIsNotNull(tvEmptyView, "tvEmptyView");
            tvEmptyView.setVisibility(0);
            Intrinsics.checkExpressionValueIsNotNull(svContentLayout, "svContentLayout");
            svContentLayout.setVisibility(8);
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(tvEmptyView, "tvEmptyView");
        tvEmptyView.setVisibility(8);
        Intrinsics.checkExpressionValueIsNotNull(svContentLayout, "svContentLayout");
        svContentLayout.setVisibility(0);
        this.llContent.removeAllViews();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            String map = ((KeyBtnWithDestination) obj).getMap();
            Object obj2 = linkedHashMap.get(map);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(map, obj2);
            }
            ((List) obj2).add(obj);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            this.llContent.addView(newMapItem(new Pair<>(entry.getKey(), entry.getValue())), layoutParams());
            this.llContent.addView(dividerView(), dividerParams());
        }
    }

    private final View newMapItem(Pair<String, ? extends List<KeyBtnWithDestination>> item) {
        View inflate = View.inflate(getContext(), C4188R.layout.item_key_btn, null);
        View findViewById = inflate.findViewById(C4188R.id.tvMapName);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<TextView>(R.id.tvMapName)");
        ((TextView) findViewById).setText(item.getFirst());
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C4188R.id.llMapContent);
        int i = 0;
        for (Object obj : item.getSecond()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KeyBtnWithDestination keyBtnWithDestination = (KeyBtnWithDestination) obj;
            View inflate2 = View.inflate(inflate.getContext(), C4188R.layout.item_key_btn2, null);
            ((TextView) inflate2.findViewById(C4188R.id.tvMapPoint)).setText(keyBtnWithDestination.getMap_point());
            ((TextView) inflate2.findViewById(C4188R.id.tvKeyBtnMac)).setText(keyBtnWithDestination.getPid());
            linearLayout.addView(inflate2, layoutParams());
            if (i != item.getSecond().size() - 1) {
                linearLayout.addView(dividerView(), dividerParams());
            }
            i = i2;
        }
        return inflate;
    }

    private final View dividerView() {
        View view = new View(getContext());
        view.setBackgroundColor(Color.parseColor("#ffC2C2C2"));
        return view;
    }

    private final LinearLayout.LayoutParams layoutParams() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    private final LinearLayout.LayoutParams dividerParams() {
        return new LinearLayout.LayoutParams(-1, 1);
    }
}
