package com.pudutech.bumblebee.robot_ui.p054ui.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingPresenter;
import com.pudutech.bumblebee.presenter.delivery_task.DestinationContract;
import com.pudutech.bumblebee.presenter.delivery_task.DestinationPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TablesAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.view.TablesClassifyLayout;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.util.DensityUtil;
import com.pudutech.freeinstall_ui.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: SelectTableFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010/\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0005H\u0016J\b\u00100\u001a\u00020\"H\u0002J\b\u00101\u001a\u00020\"H\u0002J\u0010\u00102\u001a\u00020\"2\u0006\u00103\u001a\u000204H\u0016J&\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010=\u001a\u00020\"H\u0016J\b\u0010>\u001a\u00020\"H\u0016J\b\u0010?\u001a\u00020\"H\u0016J\u001a\u0010@\u001a\u00020\"2\u0006\u0010A\u001a\u0002062\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u001e\u0010B\u001a\u00020\"2\u0006\u0010C\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0016JJ\u0010F\u001a\u00020\"2\u0006\u0010C\u001a\u00020D28\u0010G\u001a4\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050)0(j\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050)j\b\u0012\u0004\u0012\u00020\u0005`+`*H\u0016J\b\u0010H\u001a\u00020\"H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\"\u0018\u00010\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R@\u0010'\u001a4\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050)0(j\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050)j\b\u0012\u0004\u0012\u00020\u0005`+`*X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010,\u001a\u0012\u0012\u0004\u0012\u00020\u00050)j\b\u0012\u0004\u0012\u00020\u0005`+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082.¢\u0006\u0002\n\u0000¨\u0006I"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/fragment/SelectTableFragment;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DestinationContract$ViewInterface;", "Lcom/pudutech/bumblebee/robot_ui/ui/fragment/ISelectTableFragment;", "()V", "TAG", "", "allTables", "", "deliverSettingPresenter", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingPresenter;", "getDeliverSettingPresenter", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingPresenter;", "deliverSettingPresenter$delegate", "Lkotlin/Lazy;", "destinationPresenter", "Lcom/pudutech/bumblebee/presenter/delivery_task/DestinationPresenter;", "getDestinationPresenter", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DestinationPresenter;", "destinationPresenter$delegate", ES6Iterator.VALUE_PROPERTY, "", "isBirthdayTheme", "()Z", "setBirthdayTheme", "(Z)V", "isShowAllGroup", "setShowAllGroup", "mColumn", "", "onSelectTable", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", Constants.POINT_TYPE_TABLE, "", "getOnSelectTable", "()Lkotlin/jvm/functions/Function1;", "setOnSelectTable", "(Lkotlin/jvm/functions/Function1;)V", "tableGroups", "Ljava/util/LinkedHashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/LinkedHashMap;", "Lkotlin/collections/ArrayList;", "tableList", "tablesAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesAdapter;", "hasTable", "initDate", "initTablesRecyclerView", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onDetach", "onStart", "onViewCreated", "view", "showAll", "type", "Lcom/pudutech/bumblebee/presenter/delivery_task/DestinationContract$Type;", "destinations", "showAllGroups", "groups", "updateTheme", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelectTableFragment extends ISelectTableFragment implements DestinationContract.ViewInterface {
    private HashMap _$_findViewCache;
    private List<String> allTables;
    private boolean isBirthdayTheme;
    private boolean isShowAllGroup;
    private int mColumn;
    private Function1<? super String, Unit> onSelectTable;
    private TablesAdapter tablesAdapter;
    private final String TAG = "SelectTableFragment";
    private final ArrayList<String> tableList = new ArrayList<>();
    private LinkedHashMap<String, ArrayList<String>> tableGroups = new LinkedHashMap<>();

    /* renamed from: destinationPresenter$delegate, reason: from kotlin metadata */
    private final Lazy destinationPresenter = LazyKt.lazy(new Function0<DestinationPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.SelectTableFragment$destinationPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DestinationPresenter invoke() {
            DestinationPresenter destinationPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(DestinationPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(DestinationPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                destinationPresenter = new DestinationPresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(DestinationPresenter.class).toString());
                if (!(basePresenterInterface instanceof DestinationPresenter)) {
                    basePresenterInterface = null;
                }
                destinationPresenter = (DestinationPresenter) basePresenterInterface;
            }
            if (destinationPresenter == null) {
                Intrinsics.throwNpe();
            }
            return destinationPresenter;
        }
    });

    /* renamed from: deliverSettingPresenter$delegate, reason: from kotlin metadata */
    private final Lazy deliverSettingPresenter = LazyKt.lazy(new Function0<DeliverSettingPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.SelectTableFragment$deliverSettingPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeliverSettingPresenter invoke() {
            DeliverSettingPresenter deliverSettingPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(DeliverSettingPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(DeliverSettingPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                deliverSettingPresenter = new DeliverSettingPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(DeliverSettingPresenter.class).toString(), deliverSettingPresenter);
            } else {
                if (!(basePresenterInterface instanceof DeliverSettingPresenter)) {
                    basePresenterInterface = null;
                }
                deliverSettingPresenter = (DeliverSettingPresenter) basePresenterInterface;
            }
            if (deliverSettingPresenter != null) {
                return deliverSettingPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingPresenter");
        }
    });

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DestinationContract.Type.values().length];

        static {
            $EnumSwitchMapping$0[DestinationContract.Type.TABLE.ordinal()] = 1;
            $EnumSwitchMapping$0[DestinationContract.Type.DINING_OUTLET.ordinal()] = 2;
            $EnumSwitchMapping$0[DestinationContract.Type.DISHWASHING_ROOM.ordinal()] = 3;
            $EnumSwitchMapping$0[DestinationContract.Type.TRANSIT.ordinal()] = 4;
        }
    }

    private final DeliverSettingPresenter getDeliverSettingPresenter() {
        return (DeliverSettingPresenter) this.deliverSettingPresenter.getValue();
    }

    private final DestinationPresenter getDestinationPresenter() {
        return (DestinationPresenter) this.destinationPresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
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

    public static final /* synthetic */ TablesAdapter access$getTablesAdapter$p(SelectTableFragment selectTableFragment) {
        TablesAdapter tablesAdapter = selectTableFragment.tablesAdapter;
        if (tablesAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tablesAdapter");
        }
        return tablesAdapter;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public Function1<String, Unit> getOnSelectTable() {
        return this.onSelectTable;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public void setOnSelectTable(Function1<? super String, Unit> function1) {
        this.onSelectTable = function1;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    /* renamed from: isBirthdayTheme, reason: from getter */
    public boolean getIsBirthdayTheme() {
        return this.isBirthdayTheme;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public void setBirthdayTheme(boolean z) {
        this.isBirthdayTheme = z;
        updateTheme();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    /* renamed from: isShowAllGroup, reason: from getter */
    public boolean getIsShowAllGroup() {
        return this.isShowAllGroup;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public void setShowAllGroup(boolean z) {
        if (this.isShowAllGroup != z) {
            this.tableGroups.clear();
            getDestinationPresenter().loadMap();
        }
        TablesClassifyLayout tablesClassifyLayout = (TablesClassifyLayout) _$_findCachedViewById(C4188R.id.tables_classify_layout);
        if (tablesClassifyLayout != null) {
            tablesClassifyLayout.setShowAllGroup(z);
        }
        this.isShowAllGroup = z;
    }

    private final void updateTheme() {
        if (getView() != null || isAdded()) {
            if (getIsBirthdayTheme()) {
                ((TablesClassifyLayout) _$_findCachedViewById(C4188R.id.tables_classify_layout)).switchBirthdayTheme(true);
                TablesAdapter tablesAdapter = this.tablesAdapter;
                if (tablesAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tablesAdapter");
                }
                tablesAdapter.setDarkTheme(true);
                ((RecyclerView) _$_findCachedViewById(C4188R.id.tables_recycler_view)).setBackgroundResource(C4188R.drawable.shape_radius_28_393b3d);
                return;
            }
            ((TablesClassifyLayout) _$_findCachedViewById(C4188R.id.tables_classify_layout)).switchBirthdayTheme(false);
            TablesAdapter tablesAdapter2 = this.tablesAdapter;
            if (tablesAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tablesAdapter");
            }
            tablesAdapter2.setDarkTheme(false);
            ((RecyclerView) _$_findCachedViewById(C4188R.id.tables_recycler_view)).setBackgroundResource(C4188R.drawable.shape_radius_28_fcfcfc);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public boolean hasTable(String table) {
        Intrinsics.checkParameterIsNotNull(table, "table");
        List<String> list = this.allTables;
        if (list != null) {
            if (list == null) {
                Intrinsics.throwNpe();
            }
            if (!list.isEmpty()) {
                List<String> list2 = this.allTables;
                if (list2 == null) {
                    Intrinsics.throwNpe();
                }
                if (list2.contains(table)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_select_table, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initTablesRecyclerView();
        initDate();
        updateTheme();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getDestinationPresenter().removeView(this);
        _$_clearFindViewByIdCache();
    }

    private final void initDate() {
        getDestinationPresenter().replaceView(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        Pdlog.m3273d(this.TAG, "onAttach  ");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Pdlog.m3273d(this.TAG, "onDetach ");
    }

    private final void initTablesRecyclerView() {
        int tableColumn = getDeliverSettingPresenter().getTableColumn();
        if (getContext() == null || tableColumn == this.mColumn) {
            return;
        }
        this.mColumn = tableColumn;
        ((TablesClassifyLayout) _$_findCachedViewById(C4188R.id.tables_classify_layout)).setOnClassifySelectChange(new Function2<String, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.SelectTableFragment$initTablesRecyclerView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Integer num) {
                invoke(str, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String name, int i) {
                LinkedHashMap linkedHashMap;
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                List list;
                ArrayList arrayList4;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Context context = SelectTableFragment.this.getContext();
                if (Intrinsics.areEqual(name, context != null ? context.getString(C4188R.string.pdStr2_16) : null)) {
                    arrayList3 = SelectTableFragment.this.tableList;
                    arrayList3.clear();
                    list = SelectTableFragment.this.allTables;
                    if (list != null) {
                        arrayList4 = SelectTableFragment.this.tableList;
                        arrayList4.addAll(list);
                        SelectTableFragment.access$getTablesAdapter$p(SelectTableFragment.this).notifyDataSetChanged();
                        return;
                    }
                    return;
                }
                linkedHashMap = SelectTableFragment.this.tableGroups;
                ArrayList arrayList5 = (ArrayList) linkedHashMap.get(name);
                if (arrayList5 != null) {
                    arrayList = SelectTableFragment.this.tableList;
                    arrayList.clear();
                    arrayList2 = SelectTableFragment.this.tableList;
                    arrayList2.addAll(arrayList5);
                    SelectTableFragment.access$getTablesAdapter$p(SelectTableFragment.this).notifyDataSetChanged();
                }
            }
        });
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        this.tablesAdapter = new TablesAdapter(context);
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context2, this.mColumn, 1, false);
        RecyclerView tables_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.tables_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tables_recycler_view, "tables_recycler_view");
        tables_recycler_view.setLayoutManager(gridLayoutManager);
        TablesAdapter tablesAdapter = this.tablesAdapter;
        if (tablesAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tablesAdapter");
        }
        tablesAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.tables_recycler_view));
        TablesAdapter tablesAdapter2 = this.tablesAdapter;
        if (tablesAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tablesAdapter");
        }
        tablesAdapter2.setNewData(this.tableList);
        RecyclerView tables_recycler_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.tables_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tables_recycler_view2, "tables_recycler_view");
        if (tables_recycler_view2.getItemDecorationCount() == 0) {
            ((RecyclerView) _$_findCachedViewById(C4188R.id.tables_recycler_view)).addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.SelectTableFragment$initTablesRecyclerView$2
                @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    Intrinsics.checkParameterIsNotNull(outRect, "outRect");
                    Intrinsics.checkParameterIsNotNull(view, "view");
                    Intrinsics.checkParameterIsNotNull(parent, "parent");
                    Intrinsics.checkParameterIsNotNull(state, "state");
                    outRect.top = DensityUtil.dp2px(SelectTableFragment.this.getContext(), 8.0f);
                    outRect.right = DensityUtil.dp2px(SelectTableFragment.this.getContext(), 8.0f);
                    outRect.bottom = DensityUtil.dp2px(SelectTableFragment.this.getContext(), 8.0f);
                    outRect.left = DensityUtil.dp2px(SelectTableFragment.this.getContext(), 8.0f);
                }
            });
        }
        TablesAdapter tablesAdapter3 = this.tablesAdapter;
        if (tablesAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tablesAdapter");
        }
        tablesAdapter3.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.SelectTableFragment$initTablesRecyclerView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                String str2;
                ArrayList arrayList;
                ArrayList arrayList2;
                String str3;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                str = SelectTableFragment.this.TAG;
                Pdlog.m3273d(str, "tablesAdapter onItemClickListener " + position);
                if (SelectTableFragment.this.getContext() == null || SelectTableFragment.this.isDetached()) {
                    str2 = SelectTableFragment.this.TAG;
                    Pdlog.m3274e(str2, "activity is finish " + position);
                    return;
                }
                arrayList = SelectTableFragment.this.tableList;
                if (arrayList.size() - 1 < position) {
                    str3 = SelectTableFragment.this.TAG;
                    Pdlog.m3274e(str3, "table list is change ?? ");
                    return;
                }
                Function1<String, Unit> onSelectTable = SelectTableFragment.this.getOnSelectTable();
                if (onSelectTable != 0) {
                    arrayList2 = SelectTableFragment.this.tableList;
                    Object obj = arrayList2.get(position);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "tableList[position]");
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DestinationContract.ViewInterface
    public void showAll(DestinationContract.Type type, List<String> destinations) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        Pdlog.m3273d(this.TAG, "showAll: " + type + ' ' + destinations);
        if (type == DestinationContract.Type.TABLE) {
            this.allTables = destinations;
            this.tableList.clear();
            this.tableList.addAll(destinations);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DestinationContract.ViewInterface
    public void showAllGroups(DestinationContract.Type type, LinkedHashMap<String, ArrayList<String>> groups) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(groups, "groups");
        boolean z = true;
        Pdlog.m3273d(this.TAG, "showAllGroups: " + type + ' ' + groups);
        int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            this.tableGroups.putAll(groups);
        } else if ((i == 2 || i == 3 || i == 4) && groups.size() > 0) {
            LinkedHashMap<String, ArrayList<String>> linkedHashMap = groups;
            if (!linkedHashMap.isEmpty()) {
                Iterator<Map.Entry<String, ArrayList<String>>> it = linkedHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().getValue().size() > 0) {
                        break;
                    }
                }
            }
            z = false;
            if (z && getIsShowAllGroup()) {
                this.tableGroups.putAll(linkedHashMap);
            }
        }
        if (this.tableGroups.size() == 0) {
            TablesClassifyLayout tables_classify_layout = (TablesClassifyLayout) _$_findCachedViewById(C4188R.id.tables_classify_layout);
            Intrinsics.checkExpressionValueIsNotNull(tables_classify_layout, "tables_classify_layout");
            tables_classify_layout.setVisibility(8);
            TablesAdapter tablesAdapter = this.tablesAdapter;
            if (tablesAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tablesAdapter");
            }
            tablesAdapter.notifyDataSetChanged();
            return;
        }
        TablesClassifyLayout tables_classify_layout2 = (TablesClassifyLayout) _$_findCachedViewById(C4188R.id.tables_classify_layout);
        Intrinsics.checkExpressionValueIsNotNull(tables_classify_layout2, "tables_classify_layout");
        tables_classify_layout2.setVisibility(0);
        TablesClassifyLayout tablesClassifyLayout = (TablesClassifyLayout) _$_findCachedViewById(C4188R.id.tables_classify_layout);
        Set<String> keySet = this.tableGroups.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "tableGroups.keys");
        tablesClassifyLayout.setClassifyData(keySet);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.tableGroups.clear();
        getDestinationPresenter().loadMap();
        initTablesRecyclerView();
    }
}
