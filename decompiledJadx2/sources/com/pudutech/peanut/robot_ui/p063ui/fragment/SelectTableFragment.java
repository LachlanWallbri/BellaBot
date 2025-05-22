package com.pudutech.peanut.robot_ui.p063ui.fragment;

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
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.peanut.presenter.PresenterHolder;
import com.pudutech.peanut.presenter.delivery_task.DestinationContract;
import com.pudutech.peanut.presenter.delivery_task.DestinationPresenter;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener;
import com.pudutech.peanut.robot_ui.p063ui.adapter.TablesAdapter;
import com.pudutech.peanut.robot_ui.p063ui.view.TablesClassifyLayout;
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
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: SelectTableFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010(\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0005H\u0016J\b\u0010)\u001a\u00020\u001bH\u0002J\b\u0010*\u001a\u00020\u001bH\u0002J\u0010\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020-H\u0016J&\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u0001032\b\u00104\u001a\u0004\u0018\u000105H\u0016J\b\u00106\u001a\u00020\u001bH\u0016J\b\u00107\u001a\u00020\u001bH\u0016J\b\u00108\u001a\u00020\u001bH\u0016J\u001a\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020/2\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u001e\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0016JJ\u0010?\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020=28\u0010@\u001a4\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\"0!j\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\"j\b\u0012\u0004\u0012\u00020\u0005`$`#H\u0016J\b\u0010A\u001a\u00020\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R7\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR@\u0010 \u001a4\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\"0!j\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\"j\b\u0012\u0004\u0012\u00020\u0005`$`#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u0012\u0012\u0004\u0012\u00020\u00050\"j\b\u0012\u0004\u0012\u00020\u0005`$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006B"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/SelectTableFragment;", "Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract$ViewInterface;", "Lcom/pudutech/peanut/robot_ui/ui/fragment/ISelectTableFragment;", "()V", "TAG", "", "allTables", "", "destinationPresenter", "Lcom/pudutech/peanut/presenter/delivery_task/DestinationPresenter;", "getDestinationPresenter", "()Lcom/pudutech/peanut/presenter/delivery_task/DestinationPresenter;", "destinationPresenter$delegate", "Lkotlin/Lazy;", ES6Iterator.VALUE_PROPERTY, "", "isBirthdayTheme", "()Z", "setBirthdayTheme", "(Z)V", "isShowAllGroup", "setShowAllGroup", "onSelectTable", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", Constants.POINT_TYPE_TABLE, "", "getOnSelectTable", "()Lkotlin/jvm/functions/Function1;", "setOnSelectTable", "(Lkotlin/jvm/functions/Function1;)V", "tableGroups", "Ljava/util/LinkedHashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/LinkedHashMap;", "Lkotlin/collections/ArrayList;", "tableList", "tablesAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TablesAdapter;", "hasTable", "initDate", "initTablesRecyclerView", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onStart", "onViewCreated", "view", "showAll", "type", "Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract$Type;", "destinations", "showAllGroups", "groups", "updateTheme", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelectTableFragment extends ISelectTableFragment implements DestinationContract.ViewInterface {
    private HashMap _$_findViewCache;
    private List<String> allTables;
    private boolean isBirthdayTheme;
    private boolean isShowAllGroup;
    private Function1<? super String, Unit> onSelectTable;
    private TablesAdapter tablesAdapter;
    private final String TAG = "SelectTableFragment";
    private final ArrayList<String> tableList = new ArrayList<>();
    private LinkedHashMap<String, ArrayList<String>> tableGroups = new LinkedHashMap<>();

    /* renamed from: destinationPresenter$delegate, reason: from kotlin metadata */
    private final Lazy destinationPresenter = LazyKt.lazy(new Function0<DestinationPresenter>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.SelectTableFragment$destinationPresenter$2
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

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DestinationContract.Type.values().length];

        static {
            $EnumSwitchMapping$0[DestinationContract.Type.TABLE.ordinal()] = 1;
            $EnumSwitchMapping$0[DestinationContract.Type.DINING_OUTLET.ordinal()] = 2;
            $EnumSwitchMapping$0[DestinationContract.Type.DISHWASHING_ROOM.ordinal()] = 3;
            $EnumSwitchMapping$0[DestinationContract.Type.TRANSIT.ordinal()] = 4;
        }
    }

    private final DestinationPresenter getDestinationPresenter() {
        return (DestinationPresenter) this.destinationPresenter.getValue();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
    public Function1<String, Unit> getOnSelectTable() {
        return this.onSelectTable;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
    public void setOnSelectTable(Function1<? super String, Unit> function1) {
        this.onSelectTable = function1;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
    /* renamed from: isBirthdayTheme, reason: from getter */
    public boolean getIsBirthdayTheme() {
        return this.isBirthdayTheme;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
    public void setBirthdayTheme(boolean z) {
        this.isBirthdayTheme = z;
        updateTheme();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
    /* renamed from: isShowAllGroup, reason: from getter */
    public boolean getIsShowAllGroup() {
        return this.isShowAllGroup;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
    public void setShowAllGroup(boolean z) {
        if (this.isShowAllGroup != z) {
            this.tableGroups.clear();
            getDestinationPresenter().loadMap();
        }
        this.isShowAllGroup = z;
    }

    private final void updateTheme() {
        if (getIsBirthdayTheme()) {
            TablesClassifyLayout tablesClassifyLayout = (TablesClassifyLayout) _$_findCachedViewById(C5508R.id.tables_classify_layout);
            if (tablesClassifyLayout != null) {
                tablesClassifyLayout.switchBirthdayTheme(true);
            }
            TablesAdapter tablesAdapter = this.tablesAdapter;
            if (tablesAdapter != null) {
                tablesAdapter.setDarkTheme(true);
            }
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C5508R.id.tables_recycler_view);
            if (recyclerView != null) {
                recyclerView.setBackgroundResource(C5508R.drawable.shape_radius_28_393b3d);
                return;
            }
            return;
        }
        TablesClassifyLayout tablesClassifyLayout2 = (TablesClassifyLayout) _$_findCachedViewById(C5508R.id.tables_classify_layout);
        if (tablesClassifyLayout2 != null) {
            tablesClassifyLayout2.switchBirthdayTheme(false);
        }
        TablesAdapter tablesAdapter2 = this.tablesAdapter;
        if (tablesAdapter2 != null) {
            tablesAdapter2.setDarkTheme(false);
        }
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C5508R.id.tables_recycler_view);
        if (recyclerView2 != null) {
            recyclerView2.setBackgroundResource(C5508R.drawable.shape_radius_28_fcfcfc);
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment
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
        return inflater.inflate(C5508R.layout.fragment_select_table, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initTablesRecyclerView();
        initDate();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateTheme();
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
        if (getContext() == null) {
            return;
        }
        ((TablesClassifyLayout) _$_findCachedViewById(C5508R.id.tables_classify_layout)).setOnClassifySelectChange(new Function2<String, Integer, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.SelectTableFragment$initTablesRecyclerView$1
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
                TablesAdapter tablesAdapter;
                ArrayList arrayList3;
                List list;
                ArrayList arrayList4;
                TablesAdapter tablesAdapter2;
                Intrinsics.checkParameterIsNotNull(name, "name");
                if (i == 0) {
                    arrayList3 = SelectTableFragment.this.tableList;
                    arrayList3.clear();
                    list = SelectTableFragment.this.allTables;
                    if (list != null) {
                        arrayList4 = SelectTableFragment.this.tableList;
                        arrayList4.addAll(list);
                        tablesAdapter2 = SelectTableFragment.this.tablesAdapter;
                        if (tablesAdapter2 != null) {
                            tablesAdapter2.notifyDataSetChanged();
                            return;
                        }
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
                    tablesAdapter = SelectTableFragment.this.tablesAdapter;
                    if (tablesAdapter != null) {
                        tablesAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        this.tablesAdapter = new TablesAdapter(requireContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 3, 1, false);
        RecyclerView tables_recycler_view = (RecyclerView) _$_findCachedViewById(C5508R.id.tables_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tables_recycler_view, "tables_recycler_view");
        tables_recycler_view.setLayoutManager(gridLayoutManager);
        TablesAdapter tablesAdapter = this.tablesAdapter;
        if (tablesAdapter != null) {
            tablesAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C5508R.id.tables_recycler_view));
        }
        TablesAdapter tablesAdapter2 = this.tablesAdapter;
        if (tablesAdapter2 != null) {
            tablesAdapter2.setNewData(this.tableList);
        }
        ((RecyclerView) _$_findCachedViewById(C5508R.id.tables_recycler_view)).addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.SelectTableFragment$initTablesRecyclerView$2
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                Intrinsics.checkParameterIsNotNull(outRect, "outRect");
                Intrinsics.checkParameterIsNotNull(view, "view");
                Intrinsics.checkParameterIsNotNull(parent, "parent");
                Intrinsics.checkParameterIsNotNull(state, "state");
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                RecyclerView.Adapter adapter = parent.getAdapter();
                int itemCount = adapter != null ? adapter.getItemCount() : 0;
                outRect.top = childAdapterPosition < 3 ? 12 : 0;
                int i = (childAdapterPosition + 1) % 3;
                outRect.left = i == 1 ? 9 : 18;
                outRect.right = i != 0 ? 0 : 9;
                int i2 = itemCount % 3;
                outRect.bottom = (itemCount - childAdapterPosition) - 1 < (i2 != 0 ? i2 : 3) ? 10 : 0;
            }
        });
        TablesAdapter tablesAdapter3 = this.tablesAdapter;
        if (tablesAdapter3 != null) {
            tablesAdapter3.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.SelectTableFragment$initTablesRecyclerView$3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener
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
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.DestinationContract.ViewInterface
    public void showAll(DestinationContract.Type type, List<String> destinations) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        Pdlog.m3273d(this.TAG, "showAll: " + type + ' ' + destinations);
        if (type == DestinationContract.Type.TABLE) {
            this.allTables = destinations;
            this.tableList.clear();
            this.tableList.addAll(destinations);
            TablesAdapter tablesAdapter = this.tablesAdapter;
            if (tablesAdapter != null) {
                tablesAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.DestinationContract.ViewInterface
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
            TablesClassifyLayout tables_classify_layout = (TablesClassifyLayout) _$_findCachedViewById(C5508R.id.tables_classify_layout);
            Intrinsics.checkExpressionValueIsNotNull(tables_classify_layout, "tables_classify_layout");
            tables_classify_layout.setVisibility(8);
            View line = _$_findCachedViewById(C5508R.id.line);
            Intrinsics.checkExpressionValueIsNotNull(line, "line");
            line.setVisibility(0);
            return;
        }
        TablesClassifyLayout tables_classify_layout2 = (TablesClassifyLayout) _$_findCachedViewById(C5508R.id.tables_classify_layout);
        Intrinsics.checkExpressionValueIsNotNull(tables_classify_layout2, "tables_classify_layout");
        tables_classify_layout2.setVisibility(0);
        View line2 = _$_findCachedViewById(C5508R.id.line);
        Intrinsics.checkExpressionValueIsNotNull(line2, "line");
        line2.setVisibility(8);
        TablesClassifyLayout tablesClassifyLayout = (TablesClassifyLayout) _$_findCachedViewById(C5508R.id.tables_classify_layout);
        Set<String> keySet = this.tableGroups.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "tableGroups.keys");
        tablesClassifyLayout.setClassifyData(keySet);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.tableGroups.clear();
        getDestinationPresenter().loadMap();
    }
}
