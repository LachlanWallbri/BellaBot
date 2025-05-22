package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.delivery_task.DestinationContract;
import com.pudutech.bumblebee.presenter.delivery_task.DestinationPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.base.BaseDialog;
import com.pudutech.bumblebee.robot_ui.bean.Beeper;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TableBindAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TableBindItem;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TableWatchBindItem;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: SelectBindBoxDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 @2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001@B\u0005¢\u0006\u0002\u0010\u0004J\u001a\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u0001H\u0016J\b\u0010\u001f\u001a\u00020\u001bH\u0002J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0!H\u0002J\b\u0010\"\u001a\u00020\u0017H\u0016J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000e0!H\u0002J\b\u0010$\u001a\u00020\u001bH\u0002J\b\u0010%\u001a\u00020\u001bH\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J\u0012\u0010'\u001a\u00020\u001b2\b\u0010(\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010)\u001a\u00020\u001bH\u0016J\b\u0010*\u001a\u00020\u001bH\u0016J\u0018\u0010+\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020\u001bH\u0002J\b\u0010/\u001a\u00020\u001bH\u0002J\b\u00100\u001a\u00020\u001bH\u0002J\u000e\u00101\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\tJ\u0010\u00102\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u00103\u001a\u00020\u001bH\u0002J\b\u00104\u001a\u00020\u001bH\u0002J\b\u00105\u001a\u00020\u001bH\u0002J\u001e\u00106\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00060!H\u0016JJ\u00109\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020728\u0010:\u001a4\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060<0;j\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00060<j\b\u0012\u0004\u0012\u00020\u0006`>`=H\u0016J\b\u0010?\u001a\u00020\u001bH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/SelectBindBoxDialog;", "Lcom/pudutech/bumblebee/robot_ui/base/BaseDialog;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DestinationContract$ViewInterface;", "Landroid/view/View$OnClickListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "beeper", "Lcom/pudutech/bumblebee/robot_ui/bean/Beeper;", "bindAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TableBindAdapter;", "bindTableList", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TableBindItem;", "destinationPresenter", "Lcom/pudutech/bumblebee/presenter/delivery_task/DestinationPresenter;", "getDestinationPresenter", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DestinationPresenter;", "destinationPresenter$delegate", "Lkotlin/Lazy;", "tableList", "type", "", "unBindAdapter", "unBindTableList", "bindView", "", "rootView", "Landroid/view/View;", "dialog", "getBindData", "getBindSelectList", "", "getDialogLayoutId", "getUnBindSelectList", "initListener", "initPresenter", "initSelectLayout", "onClick", "v", "onDestroy", "onResume", "selectAll", "checked", "", "setAdd", "setAddColor", "setAllSelect", "setBeeper", "setChecked", "setDelete", "setDeleteColor", "setUnBindAllSelect", "showAll", "Lcom/pudutech/bumblebee/presenter/delivery_task/DestinationContract$Type;", "destinations", "showAllGroups", "groups", "Ljava/util/LinkedHashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/LinkedHashMap;", "Lkotlin/collections/ArrayList;", "updateTableList", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelectBindBoxDialog extends BaseDialog implements DestinationContract.ViewInterface, View.OnClickListener {
    public static final int TYPE_BIND_CLICK = 1;
    public static final int TYPE_UNBIND_CLICK = 2;
    private HashMap _$_findViewCache;
    private Beeper beeper;
    private TableBindAdapter bindAdapter;
    private TableBindAdapter unBindAdapter;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: destinationPresenter$delegate, reason: from kotlin metadata */
    private final Lazy destinationPresenter = LazyKt.lazy(new Function0<DestinationPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectBindBoxDialog$destinationPresenter$2
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
    private final List<TableBindItem> tableList = new ArrayList();
    private final List<TableBindItem> bindTableList = new ArrayList();
    private final List<TableBindItem> unBindTableList = new ArrayList();
    private int type = 1;

    private final DestinationPresenter getDestinationPresenter() {
        return (DestinationPresenter) this.destinationPresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
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

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DestinationContract.ViewInterface
    public void showAllGroups(DestinationContract.Type type, LinkedHashMap<String, ArrayList<String>> groups) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(groups, "groups");
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public int getDialogLayoutId() {
        return C4188R.layout.dialog_select_bind_box;
    }

    public final void setBeeper(Beeper beeper) {
        Intrinsics.checkParameterIsNotNull(beeper, "beeper");
        this.beeper = beeper;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setFullScreen();
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
        Beeper beeper = this.beeper;
        title_tv.setText(beeper != null ? beeper.getMac() : null);
        initSelectLayout();
        initPresenter();
        getBindData();
        initListener();
    }

    private final void initListener() {
        SelectBindBoxDialog selectBindBoxDialog = this;
        ((ImageView) _$_findCachedViewById(C4188R.id.cancel)).setOnClickListener(selectBindBoxDialog);
        ((CheckBox) _$_findCachedViewById(C4188R.id.cb_bind)).setOnClickListener(selectBindBoxDialog);
        ((CheckBox) _$_findCachedViewById(C4188R.id.cb_unbind)).setOnClickListener(selectBindBoxDialog);
        ((TextView) _$_findCachedViewById(C4188R.id.tv_delete)).setOnClickListener(selectBindBoxDialog);
        ((TextView) _$_findCachedViewById(C4188R.id.tv_add)).setOnClickListener(selectBindBoxDialog);
        ((TextView) _$_findCachedViewById(C4188R.id.tv_sure_delete)).setOnClickListener(selectBindBoxDialog);
        ((TextView) _$_findCachedViewById(C4188R.id.tv_cancel)).setOnClickListener(selectBindBoxDialog);
    }

    private final void initPresenter() {
        getDestinationPresenter().replaceView(this);
    }

    private final void initSelectLayout() {
        RecyclerView bind_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.bind_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(bind_recycler_view, "bind_recycler_view");
        bind_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        TableBindAdapter tableBindAdapter = new TableBindAdapter();
        tableBindAdapter.setOnItemClickListener(new Function2<TableBindItem, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectBindBoxDialog$initSelectLayout$$inlined$also$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TableBindItem tableBindItem, Integer num) {
                invoke(tableBindItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(TableBindItem item, int i) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                SelectBindBoxDialog.this.setAllSelect();
            }
        });
        this.bindAdapter = tableBindAdapter;
        TableBindAdapter tableBindAdapter2 = this.bindAdapter;
        if (tableBindAdapter2 != null) {
            tableBindAdapter2.setNewData(this.bindTableList);
        }
        RecyclerView bind_recycler_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.bind_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(bind_recycler_view2, "bind_recycler_view");
        bind_recycler_view2.setAdapter(this.bindAdapter);
        RecyclerView unbind_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.unbind_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(unbind_recycler_view, "unbind_recycler_view");
        unbind_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        TableBindAdapter tableBindAdapter3 = new TableBindAdapter();
        tableBindAdapter3.setOnItemClickListener(new Function2<TableBindItem, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectBindBoxDialog$initSelectLayout$$inlined$also$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TableBindItem tableBindItem, Integer num) {
                invoke(tableBindItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(TableBindItem item, int i) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                SelectBindBoxDialog.this.setUnBindAllSelect();
            }
        });
        this.unBindAdapter = tableBindAdapter3;
        TableBindAdapter tableBindAdapter4 = this.unBindAdapter;
        if (tableBindAdapter4 != null) {
            tableBindAdapter4.setNewData(this.unBindTableList);
        }
        RecyclerView unbind_recycler_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.unbind_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(unbind_recycler_view2, "unbind_recycler_view");
        unbind_recycler_view2.setAdapter(this.unBindAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAllSelect() {
        List<TableBindItem> list = this.bindTableList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!((TableBindItem) obj).isSelect()) {
                arrayList.add(obj);
            }
        }
        CheckBox cb_bind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_bind);
        Intrinsics.checkExpressionValueIsNotNull(cb_bind, "cb_bind");
        cb_bind.setChecked(arrayList.isEmpty());
        setDeleteColor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUnBindAllSelect() {
        List<TableBindItem> list = this.unBindTableList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!((TableBindItem) obj).isSelect()) {
                arrayList.add(obj);
            }
        }
        CheckBox cb_unbind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_unbind);
        Intrinsics.checkExpressionValueIsNotNull(cb_unbind, "cb_unbind");
        cb_unbind.setChecked(arrayList.isEmpty());
        setAddColor();
    }

    private final void getBindData() {
        getDestinationPresenter().loadMap();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b1  */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // com.pudutech.bumblebee.presenter.delivery_task.DestinationContract.ViewInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void showAll(DestinationContract.Type type, List<String> destinations) {
        List<TableBindItem> list;
        Beeper beeper;
        TableBindItem tableBindItem;
        TableBindItem tableBindItem2;
        Object obj;
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        Pdlog.m3273d(this.TAG, "SelectBindBoxDialog ---- showAll: " + type + ' ' + destinations);
        if (type == DestinationContract.Type.TABLE && this.beeper != null) {
            List<TableWatchBindItem> watchTableBindInfo = Constans.INSTANCE.getWatchTableBindInfo();
            if (watchTableBindInfo != null) {
                Iterator it = watchTableBindInfo.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    String mac = ((TableWatchBindItem) obj).getMac();
                    Beeper beeper2 = this.beeper;
                    if (beeper2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Intrinsics.areEqual(mac, beeper2.getMac())) {
                        break;
                    }
                }
                TableWatchBindItem tableWatchBindItem = (TableWatchBindItem) obj;
                if (tableWatchBindItem != null) {
                    list = tableWatchBindItem.getTableBindItems();
                    ArrayList arrayList = new ArrayList();
                    if (list != null) {
                        for (TableBindItem tableBindItem3 : list) {
                            if (!destinations.contains(tableBindItem3.getName())) {
                                arrayList.add(tableBindItem3);
                            }
                        }
                    }
                    if (list != null) {
                        list.removeAll(arrayList);
                    }
                    this.tableList.clear();
                    this.tableList.addAll(list == null ? list : new ArrayList());
                    for (String str : destinations) {
                        if (list != null) {
                            Iterator it2 = list.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    tableBindItem2 = it2.next();
                                    if (Intrinsics.areEqual(str, ((TableBindItem) tableBindItem2).getName())) {
                                        break;
                                    }
                                } else {
                                    tableBindItem2 = 0;
                                    break;
                                }
                            }
                            tableBindItem = tableBindItem2;
                        } else {
                            tableBindItem = null;
                        }
                        if (tableBindItem == null) {
                            this.tableList.add(new TableBindItem(str, false, false, 6, null));
                        }
                    }
                    Constans constans = Constans.INSTANCE;
                    beeper = this.beeper;
                    if (beeper == null) {
                        Intrinsics.throwNpe();
                    }
                    String mac2 = beeper.getMac();
                    Intrinsics.checkExpressionValueIsNotNull(mac2, "beeper!!.mac");
                    constans.saveWatchTableBindInfo(mac2, this.tableList);
                }
            }
            list = null;
            ArrayList arrayList2 = new ArrayList();
            if (list != null) {
            }
            if (list != null) {
            }
            this.tableList.clear();
            this.tableList.addAll(list == null ? list : new ArrayList());
            while (r13.hasNext()) {
            }
            Constans constans2 = Constans.INSTANCE;
            beeper = this.beeper;
            if (beeper == null) {
            }
            String mac22 = beeper.getMac();
            Intrinsics.checkExpressionValueIsNotNull(mac22, "beeper!!.mac");
            constans2.saveWatchTableBindInfo(mac22, this.tableList);
        }
        this.bindTableList.clear();
        this.unBindTableList.clear();
        for (TableBindItem tableBindItem4 : this.tableList) {
            if (tableBindItem4.isBind()) {
                this.bindTableList.add(tableBindItem4);
            } else {
                this.unBindTableList.add(tableBindItem4);
            }
        }
        List<TableBindItem> list2 = this.bindTableList;
        if (list2.size() > 1) {
            CollectionsKt.sortWith(list2, new Comparator<T>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectBindBoxDialog$showAll$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((TableBindItem) t).getName(), ((TableBindItem) t2).getName());
                }
            });
        }
        List<TableBindItem> list3 = this.unBindTableList;
        if (list3.size() > 1) {
            CollectionsKt.sortWith(list3, new Comparator<T>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectBindBoxDialog$showAll$$inlined$sortBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((TableBindItem) t).getName(), ((TableBindItem) t2).getName());
                }
            });
        }
        TableBindAdapter tableBindAdapter = this.bindAdapter;
        if (tableBindAdapter != null) {
            tableBindAdapter.notifyDataSetChanged();
        }
        TableBindAdapter tableBindAdapter2 = this.unBindAdapter;
        if (tableBindAdapter2 != null) {
            tableBindAdapter2.notifyDataSetChanged();
        }
        setDeleteColor();
        setAddColor();
        CheckBox cb_bind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_bind);
        Intrinsics.checkExpressionValueIsNotNull(cb_bind, "cb_bind");
        cb_bind.setChecked(false);
        CheckBox cb_unbind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_unbind);
        Intrinsics.checkExpressionValueIsNotNull(cb_unbind, "cb_unbind");
        cb_unbind.setChecked(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer valueOf = v != null ? Integer.valueOf(v.getId()) : null;
        int i = C4188R.id.cancel;
        if (valueOf != null && valueOf.intValue() == i) {
            dismissDialog();
            return;
        }
        int i2 = C4188R.id.cb_bind;
        if (valueOf != null && valueOf.intValue() == i2) {
            this.type = 1;
            setChecked(this.type);
            return;
        }
        int i3 = C4188R.id.cb_unbind;
        if (valueOf != null && valueOf.intValue() == i3) {
            this.type = 2;
            setChecked(this.type);
            return;
        }
        int i4 = C4188R.id.tv_delete;
        if (valueOf != null && valueOf.intValue() == i4) {
            if (getBindSelectList().isEmpty()) {
                ToastUtils.show(getContext(), getString(C4188R.string.un_select), new Object[0]);
                return;
            }
            LinearLayout ll_sure_delete = (LinearLayout) _$_findCachedViewById(C4188R.id.ll_sure_delete);
            Intrinsics.checkExpressionValueIsNotNull(ll_sure_delete, "ll_sure_delete");
            ll_sure_delete.setVisibility(0);
            TextView tv_delete = (TextView) _$_findCachedViewById(C4188R.id.tv_delete);
            Intrinsics.checkExpressionValueIsNotNull(tv_delete, "tv_delete");
            tv_delete.setVisibility(8);
            return;
        }
        int i5 = C4188R.id.tv_add;
        if (valueOf != null && valueOf.intValue() == i5) {
            setAdd();
            return;
        }
        int i6 = C4188R.id.tv_cancel;
        if (valueOf != null && valueOf.intValue() == i6) {
            LinearLayout ll_sure_delete2 = (LinearLayout) _$_findCachedViewById(C4188R.id.ll_sure_delete);
            Intrinsics.checkExpressionValueIsNotNull(ll_sure_delete2, "ll_sure_delete");
            ll_sure_delete2.setVisibility(8);
            TextView tv_delete2 = (TextView) _$_findCachedViewById(C4188R.id.tv_delete);
            Intrinsics.checkExpressionValueIsNotNull(tv_delete2, "tv_delete");
            tv_delete2.setVisibility(0);
            return;
        }
        int i7 = C4188R.id.tv_sure_delete;
        if (valueOf != null && valueOf.intValue() == i7) {
            LinearLayout ll_sure_delete3 = (LinearLayout) _$_findCachedViewById(C4188R.id.ll_sure_delete);
            Intrinsics.checkExpressionValueIsNotNull(ll_sure_delete3, "ll_sure_delete");
            ll_sure_delete3.setVisibility(8);
            TextView tv_delete3 = (TextView) _$_findCachedViewById(C4188R.id.tv_delete);
            Intrinsics.checkExpressionValueIsNotNull(tv_delete3, "tv_delete");
            tv_delete3.setVisibility(0);
            setDelete();
        }
    }

    private final void setDelete() {
        List<TableBindItem> bindSelectList = getBindSelectList();
        if (bindSelectList.isEmpty()) {
            ToastUtils.show(getContext(), getString(C4188R.string.un_select), new Object[0]);
            return;
        }
        List<TableBindItem> list = bindSelectList;
        this.bindTableList.removeAll(list);
        TableBindAdapter tableBindAdapter = this.bindAdapter;
        if (tableBindAdapter != null) {
            tableBindAdapter.notifyDataSetChanged();
        }
        for (TableBindItem tableBindItem : bindSelectList) {
            tableBindItem.setSelect(false);
            tableBindItem.setBind(false);
        }
        this.unBindTableList.addAll(list);
        List<TableBindItem> list2 = this.unBindTableList;
        if (list2.size() > 1) {
            CollectionsKt.sortWith(list2, new Comparator<T>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectBindBoxDialog$setDelete$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((TableBindItem) t).getName(), ((TableBindItem) t2).getName());
                }
            });
        }
        TableBindAdapter tableBindAdapter2 = this.unBindAdapter;
        if (tableBindAdapter2 != null) {
            tableBindAdapter2.notifyDataSetChanged();
        }
        CheckBox cb_bind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_bind);
        Intrinsics.checkExpressionValueIsNotNull(cb_bind, "cb_bind");
        cb_bind.setChecked(false);
        CheckBox cb_unbind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_unbind);
        Intrinsics.checkExpressionValueIsNotNull(cb_unbind, "cb_unbind");
        cb_unbind.setChecked(false);
        updateTableList();
        ToastUtils.show(getContext(), getString(C4188R.string.delete_success), new Object[0]);
    }

    private final void updateTableList() {
        this.tableList.clear();
        this.tableList.addAll(this.bindTableList);
        this.tableList.addAll(this.unBindTableList);
        List<TableBindItem> list = this.tableList;
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, new Comparator<T>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectBindBoxDialog$updateTableList$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((TableBindItem) t).getName(), ((TableBindItem) t2).getName());
                }
            });
        }
        Constans constans = Constans.INSTANCE;
        Beeper beeper = this.beeper;
        if (beeper == null) {
            Intrinsics.throwNpe();
        }
        String mac = beeper.getMac();
        Intrinsics.checkExpressionValueIsNotNull(mac, "beeper!!.mac");
        constans.saveWatchTableBindInfo(mac, this.tableList);
        setDeleteColor();
        setAddColor();
    }

    private final void setAdd() {
        List<TableBindItem> unBindSelectList = getUnBindSelectList();
        if (unBindSelectList.isEmpty()) {
            ToastUtils.show(getContext(), getString(C4188R.string.un_select), new Object[0]);
            return;
        }
        List<TableBindItem> list = unBindSelectList;
        this.unBindTableList.removeAll(list);
        TableBindAdapter tableBindAdapter = this.unBindAdapter;
        if (tableBindAdapter != null) {
            tableBindAdapter.notifyDataSetChanged();
        }
        for (TableBindItem tableBindItem : unBindSelectList) {
            tableBindItem.setSelect(false);
            tableBindItem.setBind(true);
        }
        this.bindTableList.addAll(list);
        List<TableBindItem> list2 = this.bindTableList;
        if (list2.size() > 1) {
            CollectionsKt.sortWith(list2, new Comparator<T>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectBindBoxDialog$setAdd$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((TableBindItem) t).getName(), ((TableBindItem) t2).getName());
                }
            });
        }
        TableBindAdapter tableBindAdapter2 = this.bindAdapter;
        if (tableBindAdapter2 != null) {
            tableBindAdapter2.notifyDataSetChanged();
        }
        CheckBox cb_bind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_bind);
        Intrinsics.checkExpressionValueIsNotNull(cb_bind, "cb_bind");
        cb_bind.setChecked(false);
        CheckBox cb_unbind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_unbind);
        Intrinsics.checkExpressionValueIsNotNull(cb_unbind, "cb_unbind");
        cb_unbind.setChecked(false);
        updateTableList();
        ToastUtils.show(getContext(), getString(C4188R.string.add_success), new Object[0]);
    }

    private final List<TableBindItem> getBindSelectList() {
        List<TableBindItem> list = this.bindTableList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((TableBindItem) obj).isSelect()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final List<TableBindItem> getUnBindSelectList() {
        List<TableBindItem> list = this.unBindTableList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((TableBindItem) obj).isSelect()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final void setChecked(int type) {
        if (type != 1) {
            if (type == 2) {
                if (this.unBindTableList.isEmpty()) {
                    CheckBox cb_unbind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_unbind);
                    Intrinsics.checkExpressionValueIsNotNull(cb_unbind, "cb_unbind");
                    cb_unbind.setChecked(false);
                    return;
                } else {
                    CheckBox cb_unbind2 = (CheckBox) _$_findCachedViewById(C4188R.id.cb_unbind);
                    Intrinsics.checkExpressionValueIsNotNull(cb_unbind2, "cb_unbind");
                    selectAll(type, cb_unbind2.isChecked());
                    TableBindAdapter tableBindAdapter = this.unBindAdapter;
                    if (tableBindAdapter != null) {
                        tableBindAdapter.notifyDataSetChanged();
                    }
                }
            }
        } else if (this.bindTableList.isEmpty()) {
            CheckBox cb_bind = (CheckBox) _$_findCachedViewById(C4188R.id.cb_bind);
            Intrinsics.checkExpressionValueIsNotNull(cb_bind, "cb_bind");
            cb_bind.setChecked(false);
            return;
        } else {
            CheckBox cb_bind2 = (CheckBox) _$_findCachedViewById(C4188R.id.cb_bind);
            Intrinsics.checkExpressionValueIsNotNull(cb_bind2, "cb_bind");
            selectAll(type, cb_bind2.isChecked());
            TableBindAdapter tableBindAdapter2 = this.bindAdapter;
            if (tableBindAdapter2 != null) {
                tableBindAdapter2.notifyDataSetChanged();
            }
        }
        setDeleteColor();
        setAddColor();
    }

    private final void selectAll(int type, boolean checked) {
        if (type == 1) {
            Iterator<T> it = this.bindTableList.iterator();
            while (it.hasNext()) {
                ((TableBindItem) it.next()).setSelect(checked);
            }
        } else {
            if (type != 2) {
                return;
            }
            Iterator<T> it2 = this.unBindTableList.iterator();
            while (it2.hasNext()) {
                ((TableBindItem) it2.next()).setSelect(checked);
            }
        }
    }

    private final void setDeleteColor() {
        if (!getBindSelectList().isEmpty()) {
            ((TextView) _$_findCachedViewById(C4188R.id.tv_delete)).setTextColor(getResources().getColor(C4188R.color.setting_tray_enable_color));
        } else {
            ((TextView) _$_findCachedViewById(C4188R.id.tv_delete)).setTextColor(getResources().getColor(C4188R.color.c_a8a8a8));
        }
    }

    private final void setAddColor() {
        if (!getUnBindSelectList().isEmpty()) {
            ((TextView) _$_findCachedViewById(C4188R.id.tv_add)).setTextColor(getResources().getColor(C4188R.color.c_0072FF));
        } else {
            ((TextView) _$_findCachedViewById(C4188R.id.tv_add)).setTextColor(getResources().getColor(C4188R.color.c_a8a8a8));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getDestinationPresenter().removeView(this);
    }
}
