package com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.map.MapDrawPathLoader;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MapSelectAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MapSelectItem;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.view.MapView;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: MapSelectActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0013\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\tH\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0016J\u0012\u0010\u001b\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0016H\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\n\n\u0002\b\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014¨\u0006 "}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/robot_user_interaction_animation/ui/MapSelectActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "()V", "RESULT_CODE_CLOSE_PAGE", "", "getRESULT_CODE_CLOSE_PAGE", "()I", "RESULT_CODE_CLOSE_PAGE$1", "TAG", "", "kotlin.jvm.PlatformType", "currentMap", "isBackClick", "", "mapAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapSelectAdapter;", "oldMap", "state", "switchMapResultListener", "com/pudutech/peanut/robot_ui/module/robot_user_interaction_animation/ui/MapSelectActivity$switchMapResultListener$1", "Lcom/pudutech/peanut/robot_ui/module/robot_user_interaction_animation/ui/MapSelectActivity$switchMapResultListener$1;", "finishAc", "", "initView", "initViewData", "mMap", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "redirectToTargetPage", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapSelectActivity extends MyBaseActivity {
    public static final int RESULT_CODE_CLOSE_PAGE = 1001;
    private HashMap _$_findViewCache;
    private String currentMap;
    private boolean isBackClick;
    private MapSelectAdapter mapAdapter;
    private String oldMap;
    private int state;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: RESULT_CODE_CLOSE_PAGE$1, reason: from kotlin metadata */
    private final int RESULT_CODE_CLOSE_PAGE = 1001;
    private final MapSelectActivity$switchMapResultListener$1 switchMapResultListener = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$switchMapResultListener$1
        @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
        public void onResult(boolean b, String errorMsg) {
            String str;
            boolean z;
            str = MapSelectActivity.this.TAG;
            Pdlog.m3273d(str, "switchMapResultListener onResult = " + b + " errorMsg = " + errorMsg);
            if (MapSelectActivity.this.isDestroyed() || MapSelectActivity.this.isFinishing()) {
                return;
            }
            if (b) {
                z = MapSelectActivity.this.isBackClick;
                if (z) {
                    MapSelectActivity.this.redirectToTargetPage();
                    MapSelectActivity.this.finishAc();
                    return;
                } else {
                    MapSelectActivity.this.initViewData(RobotMapManager.INSTANCE.getDefaultPdmap());
                    return;
                }
            }
            Context context = RobotContext.INSTANCE.getContext();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = MapSelectActivity.this.getString(C5508R.string.pdStr11_14);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr11_14)");
            Object[] objArr = new Object[2];
            objArr[0] = "";
            if (errorMsg == null) {
                errorMsg = "";
            }
            objArr[1] = errorMsg;
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(context, format, new Object[0]);
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LocateCase.values().length];

        static {
            $EnumSwitchMapping$0[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$0[LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$0[LocateCase.Laser.ordinal()] = 3;
            $EnumSwitchMapping$0[LocateCase.Slamware.ordinal()] = 4;
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    public static final /* synthetic */ MapSelectAdapter access$getMapAdapter$p(MapSelectActivity mapSelectActivity) {
        MapSelectAdapter mapSelectAdapter = mapSelectActivity.mapAdapter;
        if (mapSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        return mapSelectAdapter;
    }

    public final int getRESULT_CODE_CLOSE_PAGE() {
        return this.RESULT_CODE_CLOSE_PAGE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void redirectToTargetPage() {
        setResult(this.RESULT_CODE_CLOSE_PAGE);
        int i = WhenMappings.$EnumSwitchMapping$0[RobotMapManager.INSTANCE.getLocateCase().ordinal()];
        if (i == 1) {
            AnkoInternals.internalStartActivity(this, CheckLocationActivity.class, new Pair[0]);
            return;
        }
        if (i == 2) {
            Intent intent = new Intent(this, (Class<?>) CheckLocationActivity.class);
            intent.putExtra("state", 1);
            intent.putExtra("type", 1);
            startActivity(intent);
            return;
        }
        if (i != 3 && i != 4) {
            Pdlog.m3274e(this.TAG, "onLocationInitListener  :LocateCase wrong " + RobotMapManager.INSTANCE.getLocateCase());
            if (this.state == 1) {
                Intent intent2 = new Intent(this, (Class<?>) CheckLocationActivity.class);
                intent2.putExtra("state", 1);
                startActivity(intent2);
                return;
            }
            AnkoInternals.internalStartActivity(this, CheckLocationActivity.class, new Pair[0]);
            return;
        }
        AnkoInternals.internalStartActivity(this, LaserCheckLocationActivity.class, new Pair[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initViewData(String mMap) {
        RobotMapManager.INSTANCE.getMapDrawPath(mMap, new Function1<MapDrawPathLoader, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$initViewData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MapDrawPathLoader mapDrawPathLoader) {
                invoke2(mapDrawPathLoader);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final MapDrawPathLoader mapDrawPathLoader) {
                MapView mapView;
                if (mapDrawPathLoader == null || (mapView = (MapView) MapSelectActivity.this._$_findCachedViewById(C5508R.id.map_iv)) == null) {
                    return;
                }
                mapView.post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$initViewData$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((MapView) MapSelectActivity.this._$_findCachedViewById(C5508R.id.map_iv)).setMapData(mapDrawPathLoader.getMaxX(), mapDrawPathLoader.getMaxY(), mapDrawPathLoader.getMinX(), mapDrawPathLoader.getMinY(), mapDrawPathLoader.getMapLines());
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_map_select);
        initView();
        setResult(-1);
        RobotMapManager.INSTANCE.addSwitchMapResultListener(this.switchMapResultListener);
        RobotMapManager.INSTANCE.getMaps(new Function1<ArrayList<String>, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$onCreate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<String> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayList<String> names) {
                Intrinsics.checkParameterIsNotNull(names, "names");
                ArrayList arrayList = new ArrayList();
                for (String str : names) {
                    boolean z = false;
                    if (Intrinsics.areEqual(RobotMapManager.INSTANCE.getDefaultPdmap(), str)) {
                        z = true;
                        MapSelectActivity.this.oldMap = RobotMapManager.INSTANCE.getDefaultPdmap();
                        MapSelectActivity.this.currentMap = RobotMapManager.INSTANCE.getDefaultPdmap();
                    }
                    arrayList.add(new MapSelectItem(str, z));
                }
                MapSelectActivity.access$getMapAdapter$p(MapSelectActivity.this).setNewData(arrayList);
            }
        });
        initViewData(RobotMapManager.INSTANCE.getDefaultPdmap());
        this.state = getIntent().getIntExtra("state", 0);
    }

    private final void initView() {
        MapSelectActivity mapSelectActivity = this;
        this.mapAdapter = new MapSelectAdapter(mapSelectActivity);
        RecyclerView map_recycler_view = (RecyclerView) _$_findCachedViewById(C5508R.id.map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(map_recycler_view, "map_recycler_view");
        map_recycler_view.setLayoutManager(new LinearLayoutManager(mapSelectActivity));
        RecyclerView map_recycler_view2 = (RecyclerView) _$_findCachedViewById(C5508R.id.map_recycler_view);
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
        mapSelectAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$initView$1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                List<Object> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i2 = 0;
                for (Object obj : data) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        MapSelectItem mapSelectItem = (MapSelectItem) obj;
                        mapSelectItem.setSelect(i2 == i);
                        if (mapSelectItem.isSelect()) {
                            MapSelectActivity.this.currentMap = mapSelectItem.getMapName();
                            MapSelectActivity.this.initViewData(mapSelectItem.getMapName());
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.MapSelectItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.back_iv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = MapSelectActivity.this.TAG;
                Pdlog.m3273d(str, "back_iv  OnClickListener");
                MapSelectActivity.this.finishAc();
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.confirm_iv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.MapSelectActivity$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                str = MapSelectActivity.this.TAG;
                Pdlog.m3273d(str, "confirm_iv  OnClickListener");
                str2 = MapSelectActivity.this.oldMap;
                if (str2 != null) {
                    str3 = MapSelectActivity.this.oldMap;
                    str4 = MapSelectActivity.this.currentMap;
                    if (!Intrinsics.areEqual(str3, str4)) {
                        MapSelectActivity.this.isBackClick = true;
                        RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
                        String currentFloorName = RobotMapManager.INSTANCE.getCurrentFloorName();
                        str5 = MapSelectActivity.this.currentMap;
                        if (str5 == null) {
                            Intrinsics.throwNpe();
                        }
                        robotMapManager.switchMap(currentFloorName, str5);
                        return;
                    }
                }
                MapSelectActivity.this.finishAc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishAc() {
        RobotMapManager.INSTANCE.removeSwitchMapResultListener(this.switchMapResultListener);
        finish();
    }
}
