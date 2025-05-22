package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TablesClassifyDragAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TablesClassifyItem;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TablesClassifyLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\fH\u0002J\b\u0010(\u001a\u00020\u001fH\u0002J$\u0010)\u001a\u00020\u00162\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\f0+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\f0+H\u0002J\u0014\u0010-\u001a\u00020\u001f2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0+J>\u0010/\u001a\u00020\u001f26\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u001bJ\u000e\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u0016J\b\u00102\u001a\u00020\u001fH\u0002R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R@\u0010\u001a\u001a4\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\"0!j\b\u0012\u0004\u0012\u00020\"`#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/TablesClassifyLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "callback", "Lcom/pudutech/bumblebee/robot_ui/ui/view/TableClassifyDragCallback;", "getCallback", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/TableClassifyDragCallback;", "setCallback", "(Lcom/pudutech/bumblebee/robot_ui/ui/view/TableClassifyDragCallback;)V", "currentSelectIndex", "isShowAllGroup", "", "()Z", "setShowAllGroup", "(Z)V", "onClassifySelectChange", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "", "tablesClassify", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyItem;", "Lkotlin/collections/ArrayList;", "tablesClassifyAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyDragAdapter;", "getMaxWidth", "text", "initView", "isListEqual", "l0", "", "l1", "setClassifyData", "items", "setOnClassifySelectChange", "switchBirthdayTheme", "boolean", "updateSelectStatus", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TablesClassifyLayout extends RelativeLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    public TableClassifyDragCallback callback;
    private int currentSelectIndex;
    private boolean isShowAllGroup;
    private Function2<? super String, ? super Integer, Unit> onClassifySelectChange;
    private final ArrayList<TablesClassifyItem> tablesClassify;
    private TablesClassifyDragAdapter tablesClassifyAdapter;

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

    public final TableClassifyDragCallback getCallback() {
        TableClassifyDragCallback tableClassifyDragCallback = this.callback;
        if (tableClassifyDragCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("callback");
        }
        return tableClassifyDragCallback;
    }

    public final void setCallback(TableClassifyDragCallback tableClassifyDragCallback) {
        Intrinsics.checkParameterIsNotNull(tableClassifyDragCallback, "<set-?>");
        this.callback = tableClassifyDragCallback;
    }

    /* renamed from: isShowAllGroup, reason: from getter */
    public final boolean getIsShowAllGroup() {
        return this.isShowAllGroup;
    }

    public final void setShowAllGroup(boolean z) {
        this.isShowAllGroup = z;
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
            ((LayoutInflater) systemService).inflate(C4188R.layout.layout_tables_classify_drag, this);
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            this.tablesClassifyAdapter = new TablesClassifyDragAdapter(context2);
            TablesClassifyDragAdapter tablesClassifyDragAdapter = this.tablesClassifyAdapter;
            if (tablesClassifyDragAdapter == null) {
                Intrinsics.throwNpe();
            }
            tablesClassifyDragAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.classify_recycler_view));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(0);
            RecyclerView classify_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.classify_recycler_view);
            Intrinsics.checkExpressionValueIsNotNull(classify_recycler_view, "classify_recycler_view");
            classify_recycler_view.setLayoutManager(linearLayoutManager);
            ((ImageView) _$_findCachedViewById(C4188R.id.iv_left)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.TablesClassifyLayout$initView$1
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
                    int i;
                    int i2;
                    Function2 function2;
                    int i3;
                    ArrayList arrayList;
                    int i4;
                    int i5;
                    Intrinsics.checkParameterIsNotNull(it, "it");
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
                        RecyclerView classify_recycler_view2 = (RecyclerView) TablesClassifyLayout.this._$_findCachedViewById(C4188R.id.classify_recycler_view);
                        Intrinsics.checkExpressionValueIsNotNull(classify_recycler_view2, "classify_recycler_view");
                        RecyclerView.LayoutManager layoutManager = classify_recycler_view2.getLayoutManager();
                        if (layoutManager != null) {
                            i3 = TablesClassifyLayout.this.currentSelectIndex;
                            layoutManager.scrollToPosition(i3);
                        }
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                    }
                }
            }, 3, null));
            ((ImageView) _$_findCachedViewById(C4188R.id.iv_right)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.TablesClassifyLayout$initView$2
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
                    int i;
                    ArrayList arrayList;
                    int i2;
                    Function2 function2;
                    int i3;
                    ArrayList arrayList2;
                    int i4;
                    int i5;
                    Intrinsics.checkParameterIsNotNull(it, "it");
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
                        RecyclerView classify_recycler_view2 = (RecyclerView) TablesClassifyLayout.this._$_findCachedViewById(C4188R.id.classify_recycler_view);
                        Intrinsics.checkExpressionValueIsNotNull(classify_recycler_view2, "classify_recycler_view");
                        RecyclerView.LayoutManager layoutManager = classify_recycler_view2.getLayoutManager();
                        if (layoutManager != null) {
                            i3 = TablesClassifyLayout.this.currentSelectIndex;
                            layoutManager.scrollToPosition(i3);
                        }
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                    }
                }
            }, 3, null));
            TablesClassifyDragAdapter tablesClassifyDragAdapter2 = this.tablesClassifyAdapter;
            if (tablesClassifyDragAdapter2 == null) {
                Intrinsics.throwNpe();
            }
            tablesClassifyDragAdapter2.setNewData(this.tablesClassify);
            TablesClassifyDragAdapter tablesClassifyDragAdapter3 = this.tablesClassifyAdapter;
            if (tablesClassifyDragAdapter3 == null) {
                Intrinsics.throwNpe();
            }
            tablesClassifyDragAdapter3.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.TablesClassifyLayout$initView$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(null, 0, 3, null);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
                public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                    int i;
                    Function2 function2;
                    ArrayList arrayList;
                    Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                    Intrinsics.checkParameterIsNotNull(view, "view");
                    i = TablesClassifyLayout.this.currentSelectIndex;
                    if (i != position) {
                        TablesClassifyLayout.this.currentSelectIndex = position;
                        TablesClassifyLayout.this.updateSelectStatus();
                        function2 = TablesClassifyLayout.this.onClassifySelectChange;
                        if (function2 != null) {
                            arrayList = TablesClassifyLayout.this.tablesClassify;
                        }
                    }
                }
            });
            TablesClassifyDragAdapter tablesClassifyDragAdapter4 = this.tablesClassifyAdapter;
            if (tablesClassifyDragAdapter4 == null) {
                Intrinsics.throwNpe();
            }
            this.callback = new TableClassifyDragCallback(tablesClassifyDragAdapter4);
            TableClassifyDragCallback tableClassifyDragCallback = this.callback;
            if (tableClassifyDragCallback == null) {
                Intrinsics.throwUninitializedPropertyAccessException("callback");
            }
            tableClassifyDragCallback.setOnDragFinishListener(new Function2<Integer, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.TablesClassifyLayout$initView$4
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                    invoke(num.intValue(), num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i, int i2) {
                    TablesClassifyDragAdapter tablesClassifyDragAdapter5;
                    tablesClassifyDragAdapter5 = TablesClassifyLayout.this.tablesClassifyAdapter;
                    List<TablesClassifyItem> data = tablesClassifyDragAdapter5 != null ? tablesClassifyDragAdapter5.getData() : null;
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    if (data != null) {
                        int i3 = 0;
                        for (Object obj : data) {
                            int i4 = i3 + 1;
                            if (i3 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            TablesClassifyItem tablesClassifyItem = (TablesClassifyItem) obj;
                            linkedHashSet.add(tablesClassifyItem.getName());
                            if (tablesClassifyItem.isSelect()) {
                                TablesClassifyLayout.this.currentSelectIndex = i3;
                            }
                            i3 = i4;
                        }
                    }
                    if (TablesClassifyLayout.this.getIsShowAllGroup()) {
                        Constans.INSTANCE.saveDragSortDirect(linkedHashSet);
                    } else {
                        Constans.INSTANCE.saveDragSort(linkedHashSet);
                    }
                }
            });
            TableClassifyDragCallback tableClassifyDragCallback2 = this.callback;
            if (tableClassifyDragCallback2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("callback");
            }
            new ItemTouchHelper(tableClassifyDragCallback2).attachToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.classify_recycler_view));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public final void setClassifyData(Set<String> items) {
        Set<String> dragSort;
        Intrinsics.checkParameterIsNotNull(items, "items");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String string = getContext().getString(C4188R.string.pdStr2_16);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr2_16)");
        linkedHashSet.add(string);
        linkedHashSet.addAll(items);
        if (this.isShowAllGroup) {
            dragSort = Constans.INSTANCE.getDragSortDirect();
        } else {
            dragSort = Constans.INSTANCE.getDragSort();
        }
        if (dragSort != null && isListEqual(linkedHashSet, dragSort)) {
            linkedHashSet.clear();
            linkedHashSet.addAll(dragSort);
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (Object obj : linkedHashSet) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            arrayList.add(new TablesClassifyItem(str, i == 0));
            i2 = Math.max(i2, getMaxWidth(str));
            i = i3;
        }
        TablesClassifyDragAdapter tablesClassifyDragAdapter = this.tablesClassifyAdapter;
        if (tablesClassifyDragAdapter != null) {
            tablesClassifyDragAdapter.setWidth(i2);
        }
        this.tablesClassify.clear();
        this.tablesClassify.addAll(arrayList);
        this.currentSelectIndex = 0;
        updateSelectStatus();
        Function2<? super String, ? super Integer, Unit> function2 = this.onClassifySelectChange;
        if (function2 != null) {
            function2.invoke(this.tablesClassify.get(0).getName(), 0);
        }
    }

    private final int getMaxWidth(String text) {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(C4188R.layout.item_table_classify_drag, (ViewGroup) null);
            if (!(inflate instanceof TextView)) {
                return 0;
            }
            TextView textView = (TextView) inflate;
            TextPaint paint = textView.getPaint();
            Intrinsics.checkExpressionValueIsNotNull(paint, "paint");
            Intrinsics.checkExpressionValueIsNotNull(paint.getFontMetrics(), "paint.fontMetrics");
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            return rect.width() + textView.getPaddingLeft() + textView.getPaddingRight() + 6;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    private final boolean isListEqual(Set<String> l0, Set<String> l1) {
        return l1.containsAll(l0) & l0.containsAll(l1);
    }

    public final void switchBirthdayTheme(boolean r2) {
        TablesClassifyDragAdapter tablesClassifyDragAdapter = this.tablesClassifyAdapter;
        if (tablesClassifyDragAdapter != null) {
            tablesClassifyDragAdapter.setDarkTheme(r2);
        }
        if (r2) {
            ((RecyclerView) _$_findCachedViewById(C4188R.id.classify_recycler_view)).setBackgroundResource(C4188R.drawable.shape_radius_16_393b3d);
        } else {
            ((RecyclerView) _$_findCachedViewById(C4188R.id.classify_recycler_view)).setBackgroundResource(C4188R.drawable.shape_radius_16_white);
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
        TablesClassifyDragAdapter tablesClassifyDragAdapter = this.tablesClassifyAdapter;
        if (tablesClassifyDragAdapter == null) {
            Intrinsics.throwNpe();
        }
        tablesClassifyDragAdapter.notifyDataSetChanged();
    }

    public final void setOnClassifySelectChange(Function2<? super String, ? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.onClassifySelectChange = callback;
    }
}
