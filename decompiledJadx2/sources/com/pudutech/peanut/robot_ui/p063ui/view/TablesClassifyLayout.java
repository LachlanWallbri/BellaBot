package com.pudutech.peanut.robot_ui.p063ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.adapter.TablesClassifyAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.TablesClassifyItem;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: TablesClassifyLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001b\u001a\u00020\u0014H\u0002J\u0014\u0010\u001c\u001a\u00020\u00142\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001eJ>\u0010\u001f\u001a\u00020\u001426\u0010 \u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u0010J\u000e\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020\u0014H\u0002R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u000f\u001a4\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/TablesClassifyLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "currentSelectIndex", "onClassifySelectChange", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "", "tablesClassify", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TablesClassifyItem;", "Lkotlin/collections/ArrayList;", "tablesClassifyAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TablesClassifyAdapter;", "initView", "setClassifyData", "items", "", "setOnClassifySelectChange", "callback", "switchBirthdayTheme", "boolean", "", "updateSelectStatus", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TablesClassifyLayout extends RelativeLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private int currentSelectIndex;
    private Function2<? super String, ? super Integer, Unit> onClassifySelectChange;
    private final ArrayList<TablesClassifyItem> tablesClassify;
    private TablesClassifyAdapter tablesClassifyAdapter;

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

    public TablesClassifyLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.tablesClassify = new ArrayList<>();
        initView();
    }

    public TablesClassifyLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.tablesClassify = new ArrayList<>();
        initView();
    }

    public TablesClassifyLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.tablesClassify = new ArrayList<>();
        initView();
    }

    private final void initView() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService != null) {
            ((LayoutInflater) systemService).inflate(C5508R.layout.layout_tables_classify, this);
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            this.tablesClassifyAdapter = new TablesClassifyAdapter(context2);
            TablesClassifyAdapter tablesClassifyAdapter = this.tablesClassifyAdapter;
            if (tablesClassifyAdapter == null) {
                Intrinsics.throwNpe();
            }
            tablesClassifyAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C5508R.id.classify_recycler_view));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(0);
            RecyclerView classify_recycler_view = (RecyclerView) _$_findCachedViewById(C5508R.id.classify_recycler_view);
            Intrinsics.checkExpressionValueIsNotNull(classify_recycler_view, "classify_recycler_view");
            classify_recycler_view.setLayoutManager(linearLayoutManager);
            ((FrameLayout) _$_findCachedViewById(C5508R.id.flLeft)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.TablesClassifyLayout$initView$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i;
                    int i2;
                    Function2 function2;
                    int i3;
                    ArrayList arrayList;
                    int i4;
                    int i5;
                    i = TablesClassifyLayout.this.currentSelectIndex;
                    if (i > 0) {
                        TablesClassifyLayout tablesClassifyLayout = TablesClassifyLayout.this;
                        i2 = tablesClassifyLayout.currentSelectIndex;
                        tablesClassifyLayout.currentSelectIndex = i2 - 1;
                        TablesClassifyLayout.this.updateSelectStatus();
                        function2 = TablesClassifyLayout.this.onClassifySelectChange;
                        if (function2 != null) {
                            arrayList = TablesClassifyLayout.this.tablesClassify;
                            i4 = TablesClassifyLayout.this.currentSelectIndex;
                            String name = ((TablesClassifyItem) arrayList.get(i4)).getName();
                            i5 = TablesClassifyLayout.this.currentSelectIndex;
                        }
                        RecyclerView classify_recycler_view2 = (RecyclerView) TablesClassifyLayout.this._$_findCachedViewById(C5508R.id.classify_recycler_view);
                        Intrinsics.checkExpressionValueIsNotNull(classify_recycler_view2, "classify_recycler_view");
                        RecyclerView.LayoutManager layoutManager = classify_recycler_view2.getLayoutManager();
                        if (layoutManager != null) {
                            i3 = TablesClassifyLayout.this.currentSelectIndex;
                            layoutManager.scrollToPosition(i3);
                        }
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    }
                }
            });
            ((LinearLayout) _$_findCachedViewById(C5508R.id.llRight)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.TablesClassifyLayout$initView$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i;
                    ArrayList arrayList;
                    int i2;
                    Function2 function2;
                    int i3;
                    ArrayList arrayList2;
                    int i4;
                    int i5;
                    i = TablesClassifyLayout.this.currentSelectIndex;
                    arrayList = TablesClassifyLayout.this.tablesClassify;
                    if (i < arrayList.size() - 1) {
                        TablesClassifyLayout tablesClassifyLayout = TablesClassifyLayout.this;
                        i2 = tablesClassifyLayout.currentSelectIndex;
                        tablesClassifyLayout.currentSelectIndex = i2 + 1;
                        TablesClassifyLayout.this.updateSelectStatus();
                        function2 = TablesClassifyLayout.this.onClassifySelectChange;
                        if (function2 != null) {
                            arrayList2 = TablesClassifyLayout.this.tablesClassify;
                            i4 = TablesClassifyLayout.this.currentSelectIndex;
                            String name = ((TablesClassifyItem) arrayList2.get(i4)).getName();
                            i5 = TablesClassifyLayout.this.currentSelectIndex;
                        }
                        RecyclerView classify_recycler_view2 = (RecyclerView) TablesClassifyLayout.this._$_findCachedViewById(C5508R.id.classify_recycler_view);
                        Intrinsics.checkExpressionValueIsNotNull(classify_recycler_view2, "classify_recycler_view");
                        RecyclerView.LayoutManager layoutManager = classify_recycler_view2.getLayoutManager();
                        if (layoutManager != null) {
                            i3 = TablesClassifyLayout.this.currentSelectIndex;
                            layoutManager.scrollToPosition(i3);
                        }
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    }
                }
            });
            TablesClassifyAdapter tablesClassifyAdapter2 = this.tablesClassifyAdapter;
            if (tablesClassifyAdapter2 == null) {
                Intrinsics.throwNpe();
            }
            tablesClassifyAdapter2.setNewData(this.tablesClassify);
            TablesClassifyAdapter tablesClassifyAdapter3 = this.tablesClassifyAdapter;
            if (tablesClassifyAdapter3 == null) {
                Intrinsics.throwNpe();
            }
            tablesClassifyAdapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.TablesClassifyLayout$initView$3
                @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view, int i) {
                    int i2;
                    Function2 function2;
                    ArrayList arrayList;
                    i2 = TablesClassifyLayout.this.currentSelectIndex;
                    if (i2 != i) {
                        TablesClassifyLayout.this.currentSelectIndex = i;
                        TablesClassifyLayout.this.updateSelectStatus();
                        function2 = TablesClassifyLayout.this.onClassifySelectChange;
                        if (function2 != null) {
                            arrayList = TablesClassifyLayout.this.tablesClassify;
                        }
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    }
                }
            });
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 0;
            ((RecyclerView) _$_findCachedViewById(C5508R.id.classify_recycler_view)).addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.TablesClassifyLayout$initView$4
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
                    super.onScrolled(recyclerView, dx, dy);
                    str = TablesClassifyLayout.this.TAG;
                    Pdlog.m3273d(str, dx + "---" + dy);
                    Ref.IntRef intRef2 = intRef;
                    intRef2.element = intRef2.element + dx;
                    ImageView ivBg = (ImageView) TablesClassifyLayout.this._$_findCachedViewById(C5508R.id.ivBg);
                    Intrinsics.checkExpressionValueIsNotNull(ivBg, "ivBg");
                    if ((ivBg.getVisibility() == 0) && intRef.element < 15) {
                        ImageView ivBg2 = (ImageView) TablesClassifyLayout.this._$_findCachedViewById(C5508R.id.ivBg);
                        Intrinsics.checkExpressionValueIsNotNull(ivBg2, "ivBg");
                        ivBg2.setVisibility(8);
                    }
                    ImageView ivBg3 = (ImageView) TablesClassifyLayout.this._$_findCachedViewById(C5508R.id.ivBg);
                    Intrinsics.checkExpressionValueIsNotNull(ivBg3, "ivBg");
                    if ((ivBg3.getVisibility() == 0) || intRef.element <= 25) {
                        return;
                    }
                    ImageView ivBg4 = (ImageView) TablesClassifyLayout.this._$_findCachedViewById(C5508R.id.ivBg);
                    Intrinsics.checkExpressionValueIsNotNull(ivBg4, "ivBg");
                    ivBg4.setVisibility(8);
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
                    super.onScrollStateChanged(recyclerView, newState);
                    str = TablesClassifyLayout.this.TAG;
                    Pdlog.m3273d(str, newState + "---");
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public final void setClassifyData(Set<String> items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        ArrayList arrayList = new ArrayList();
        String string = getContext().getString(C5508R.string.pdStr2_16);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr2_16)");
        arrayList.add(new TablesClassifyItem(string, true));
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            arrayList.add(new TablesClassifyItem((String) it.next(), false, 2, null));
        }
        this.tablesClassify.clear();
        this.tablesClassify.addAll(arrayList);
        this.currentSelectIndex = 0;
        updateSelectStatus();
    }

    public final void switchBirthdayTheme(boolean r6) {
        if (r6) {
            FrameLayout flLeft = (FrameLayout) _$_findCachedViewById(C5508R.id.flLeft);
            Intrinsics.checkExpressionValueIsNotNull(flLeft, "flLeft");
            Drawable drawable = (Drawable) null;
            flLeft.setBackground(drawable);
            LinearLayout llRight = (LinearLayout) _$_findCachedViewById(C5508R.id.llRight);
            Intrinsics.checkExpressionValueIsNotNull(llRight, "llRight");
            llRight.setBackground(drawable);
            RelativeLayout rlBg = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlBg);
            Intrinsics.checkExpressionValueIsNotNull(rlBg, "rlBg");
            rlBg.setBackground(getContext().getDrawable(C5508R.drawable.shape_393b3d_16_corner_bg));
            ((ImageView) _$_findCachedViewById(C5508R.id.ivBg)).setImageDrawable(getContext().getDrawable(C5508R.drawable.img_bday_mask_left));
            ((ImageView) _$_findCachedViewById(C5508R.id.ivRightBg)).setImageDrawable(getContext().getDrawable(C5508R.drawable.img_bday_mask_right));
        } else {
            FrameLayout flLeft2 = (FrameLayout) _$_findCachedViewById(C5508R.id.flLeft);
            Intrinsics.checkExpressionValueIsNotNull(flLeft2, "flLeft");
            flLeft2.setBackground(getContext().getDrawable(C5508R.drawable.select_tab_class_bg));
            LinearLayout llRight2 = (LinearLayout) _$_findCachedViewById(C5508R.id.llRight);
            Intrinsics.checkExpressionValueIsNotNull(llRight2, "llRight");
            llRight2.setBackground(getContext().getDrawable(C5508R.drawable.select_tab_class_bg));
            RelativeLayout rlBg2 = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlBg);
            Intrinsics.checkExpressionValueIsNotNull(rlBg2, "rlBg");
            rlBg2.setBackground(getContext().getDrawable(C5508R.drawable.shape_radius_16_white));
            ((ImageView) _$_findCachedViewById(C5508R.id.ivBg)).setImageDrawable(getContext().getDrawable(C5508R.drawable.img_home_mask_left));
            ((ImageView) _$_findCachedViewById(C5508R.id.ivRightBg)).setImageDrawable(getContext().getDrawable(C5508R.drawable.img_home_mask));
        }
        TablesClassifyAdapter tablesClassifyAdapter = this.tablesClassifyAdapter;
        if (tablesClassifyAdapter != null) {
            tablesClassifyAdapter.setDarkTheme(r6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectStatus() {
        int i = 0;
        for (Object obj : this.tablesClassify) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((TablesClassifyItem) obj).setSelect(i == this.currentSelectIndex);
            i = i2;
        }
        TablesClassifyAdapter tablesClassifyAdapter = this.tablesClassifyAdapter;
        if (tablesClassifyAdapter == null) {
            Intrinsics.throwNpe();
        }
        tablesClassifyAdapter.notifyDataSetChanged();
    }

    public final void setOnClassifySelectChange(Function2<? super String, ? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.onClassifySelectChange = callback;
    }
}
