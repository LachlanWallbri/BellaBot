package com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.MapSelectAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.MapSelectItem;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TransparentLoadDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MapView;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.mirsdkwrap.lib.map.MapLine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: MapSelectActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0016J\u0012\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J(\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0016\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u00050#j\b\u0012\u0004\u0012\u00020\u0005`$H\u0016J@\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0016\u0010+\u001a\u0012\u0012\u0004\u0012\u00020,0#j\b\u0012\u0004\u0012\u00020,`$H\u0016J\u001a\u0010-\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\b\u0010.\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010/\u001a\u00020\u0016H\u0002J.\u00100\u001a\u00020\u00162\b\u0010.\u001a\u0004\u0018\u00010\u00052\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u00010\u0005H\u0016ø\u0001\u0000¢\u0006\u0002\b4J\b\u00105\u001a\u00020\u0016H\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00066"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/robot_user_interaction_animation/ui/MapSelectActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "currentMap", "isBackClick", "", "mLoadDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/TransparentLoadDialog;", "mapAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/MapSelectAdapter;", "mapSwitchPresenter", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "getMapSwitchPresenter", "()Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "mapSwitchPresenter$delegate", "Lkotlin/Lazy;", "oldMap", "bindPresenter", "", "dismissLoad", "finishAc", "initData", "initView", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showAll", "type", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$Type;", "names", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "showBackGround", "maxX", "", "minX", "maxY", "minY", "model", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "showChosen", "name", "showLoad", "showSwitchError", "code", "Lkotlin/UByte;", "description", "showSwitchError-olJ16Uo", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapSelectActivity extends MyBaseActivity implements MapSwitchContract.ViewInterface {
    private HashMap _$_findViewCache;
    private String currentMap;
    private boolean isBackClick;
    private TransparentLoadDialog mLoadDialog;
    private MapSelectAdapter mapAdapter;
    private String oldMap;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: mapSwitchPresenter$delegate, reason: from kotlin metadata */
    private final Lazy mapSwitchPresenter = LazyKt.lazy(new Function0<MapSwitchPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$mapSwitchPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MapSwitchPresenter invoke() {
            MapSwitchPresenter mapSwitchPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                mapSwitchPresenter = new MapSwitchPresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class).toString());
                if (!(basePresenterInterface instanceof MapSwitchPresenter)) {
                    basePresenterInterface = null;
                }
                mapSwitchPresenter = (MapSwitchPresenter) basePresenterInterface;
            }
            if (mapSwitchPresenter != null) {
                return mapSwitchPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter");
        }
    });

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MapSwitchContract.Type.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[MapSwitchContract.Type.MAP.ordinal()] = 1;
            $EnumSwitchMapping$0[MapSwitchContract.Type.DINING_LET_OUT.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[MapSwitchContract.Type.values().length];
            $EnumSwitchMapping$1[MapSwitchContract.Type.MAP.ordinal()] = 1;
            $EnumSwitchMapping$1[MapSwitchContract.Type.DINING_LET_OUT.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MapSwitchPresenter getMapSwitchPresenter() {
        return (MapSwitchPresenter) this.mapSwitchPresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_map_select);
        initView();
        setResult(-1);
        bindPresenter();
        initData();
    }

    private final void initData() {
        getMapSwitchPresenter().loadMap();
    }

    private final void initView() {
        MapSelectActivity mapSelectActivity = this;
        this.mapAdapter = new MapSelectAdapter(mapSelectActivity);
        RecyclerView map_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(map_recycler_view, "map_recycler_view");
        map_recycler_view.setLayoutManager(new LinearLayoutManager(mapSelectActivity));
        RecyclerView map_recycler_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(map_recycler_view2, "map_recycler_view");
        MapSelectAdapter mapSelectAdapter = this.mapAdapter;
        if (mapSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        map_recycler_view2.setAdapter(mapSelectAdapter);
        MapSelectAdapter mapSelectAdapter2 = this.mapAdapter;
        if (mapSelectAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        mapSelectAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                MapSwitchPresenter mapSwitchPresenter;
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
                        MapSelectItem mapSelectItem = (MapSelectItem) obj;
                        mapSelectItem.setSelect(i == position);
                        if (mapSelectItem.isSelect()) {
                            MapSelectActivity.this.showLoad();
                            mapSwitchPresenter = MapSelectActivity.this.getMapSwitchPresenter();
                            mapSwitchPresenter.actionChoose(MapSwitchContract.Type.MAP, mapSelectItem.getMapName());
                        }
                        i = i2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.MapSelectItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.back_iv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$initView$2
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
                String str;
                String str2;
                String str3;
                String str4;
                MapSwitchPresenter mapSwitchPresenter;
                String str5;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = MapSelectActivity.this.TAG;
                Pdlog.m3273d(str, "back_iv  OnClickListener");
                str2 = MapSelectActivity.this.oldMap;
                if (str2 != null) {
                    str3 = MapSelectActivity.this.oldMap;
                    str4 = MapSelectActivity.this.currentMap;
                    if (!Intrinsics.areEqual(str3, str4)) {
                        MapSelectActivity.this.isBackClick = true;
                        mapSwitchPresenter = MapSelectActivity.this.getMapSwitchPresenter();
                        MapSwitchContract.Type type = MapSwitchContract.Type.MAP;
                        str5 = MapSelectActivity.this.oldMap;
                        if (str5 == null) {
                            Intrinsics.throwNpe();
                        }
                        mapSwitchPresenter.actionChoose(type, str5);
                        return;
                    }
                }
                MapSelectActivity.this.finishAc();
            }
        }, 3, null));
        ((TextView) _$_findCachedViewById(C4188R.id.confirm_iv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$initView$3
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
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = MapSelectActivity.this.TAG;
                Pdlog.m3273d(str, "confirm_iv  OnClickListener");
                MapSelectActivity.this.finishAc();
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoad() {
        if (this.mLoadDialog == null) {
            this.mLoadDialog = new TransparentLoadDialog(this);
        }
        TransparentLoadDialog transparentLoadDialog = this.mLoadDialog;
        if (transparentLoadDialog == null || transparentLoadDialog.isShowing()) {
            return;
        }
        transparentLoadDialog.show();
    }

    private final void dismissLoad() {
        TransparentLoadDialog transparentLoadDialog = this.mLoadDialog;
        if (transparentLoadDialog == null || !transparentLoadDialog.isShowing()) {
            return;
        }
        transparentLoadDialog.dismiss();
    }

    private final void bindPresenter() {
        getMapSwitchPresenter().replaceView(this);
    }

    private final void unBindPresenter() {
        getMapSwitchPresenter().removeView(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishAc() {
        unBindPresenter();
        finish();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showAll(MapSwitchContract.Type type, ArrayList<String> names) {
        boolean z;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(names, "names");
        if (WhenMappings.$EnumSwitchMapping$0[type.ordinal()] != 1) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        boolean z3 = false;
        for (String str : names) {
            if (Intrinsics.areEqual(getMapSwitchPresenter().getMapChosen(), str)) {
                getMapSwitchPresenter().actionChoose(MapSwitchContract.Type.MAP, str);
                z2 = true;
                z = true;
            } else {
                z = z3;
                z2 = false;
            }
            arrayList.add(new MapSelectItem(str, z2));
            z3 = z;
        }
        if (!z3 && names.size() > 0) {
            MapSwitchPresenter mapSwitchPresenter = getMapSwitchPresenter();
            MapSwitchContract.Type type2 = MapSwitchContract.Type.MAP;
            String str2 = names.get(0);
            Intrinsics.checkExpressionValueIsNotNull(str2, "names[0]");
            mapSwitchPresenter.actionChoose(type2, str2);
        }
        if (this.oldMap == null) {
            this.oldMap = getMapSwitchPresenter().getMapChosen();
        }
        MapSelectAdapter mapSelectAdapter = this.mapAdapter;
        if (mapSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        mapSelectAdapter.setNewData(arrayList);
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showBackGround(int maxX, int minX, int maxY, int minY, ArrayList<MapLine> model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showBackGround " + model);
        ((MapView) _$_findCachedViewById(C4188R.id.map_iv)).setMapData(maxX, maxY, minX, minY, model);
        dismissLoad();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showChosen(MapSwitchContract.Type type, String name) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Pdlog.m3273d(this.TAG, "showChosen " + type + ',' + name + ' ');
        if (WhenMappings.$EnumSwitchMapping$1[type.ordinal()] != 1) {
            return;
        }
        if (this.isBackClick && Intrinsics.areEqual(this.oldMap, name)) {
            Pdlog.m3273d(this.TAG, "showBackGround isBackClick");
            finishAc();
            return;
        }
        this.currentMap = name;
        MapSelectAdapter mapSelectAdapter = this.mapAdapter;
        if (mapSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        List<MapSelectItem> data = mapSelectAdapter.getData();
        Intrinsics.checkExpressionValueIsNotNull(data, "mapAdapter.data");
        for (MapSelectItem mapSelectItem : data) {
            mapSelectItem.setSelect(Intrinsics.areEqual(mapSelectItem.getMapName(), name));
        }
        MapSelectAdapter mapSelectAdapter2 = this.mapAdapter;
        if (mapSelectAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        mapSelectAdapter2.notifyDataSetChanged();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    /* renamed from: showSwitchError-olJ16Uo */
    public void mo4294showSwitchErrorolJ16Uo(String name, UByte code, String description) {
        Pdlog.m3274e(this.TAG, "showSwitchError " + name + ',' + description);
        dismissLoad();
        Context context = RobotContext.INSTANCE.getContext();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C4188R.string.pdStr11_14);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr11_14)");
        Object[] objArr = new Object[2];
        if (name == null) {
            name = "";
        }
        objArr[0] = name;
        if (description == null) {
            description = "";
        }
        objArr[1] = description;
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        ToastUtils.show(context, format, new Object[0]);
    }
}
