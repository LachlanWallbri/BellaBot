package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.CheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserCheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.MultiMapFragment;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.DeliverTaskEditActivity;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MapSelectAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MapSelectItem;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectPositionAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectPositionItem;
import com.pudutech.peanut.robot_ui.p063ui.dialog.MapSwitchTipDialog;
import com.pudutech.peanut.robot_ui.p063ui.view.MapView;
import com.pudutech.peanut.robot_ui.p063ui.view.MaxHeightRecyclerView;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.widget.MyScrollView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: MultiMapFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000o\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0002J\b\u0010\u001e\u001a\u00020\u001aH\u0002J\b\u0010\u001f\u001a\u00020\u001aH\u0003J\b\u0010 \u001a\u00020\u001aH\u0002J\u0010\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0016J&\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u001aH\u0016J\b\u0010-\u001a\u00020\u001aH\u0016J\u001a\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020%2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u00020\u000fH\u0002J\u0010\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u0004H\u0002J\b\u00104\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u00065"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/MultiMapFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "TASK_CHEACK_LOCATION", "", "chargingAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectPositionAdapter;", "greetAdapter", "handler", "Landroid/os/Handler;", "isFinished", "", "mMapSelectItem", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapSelectItem;", "mShowTipDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "mapAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapSelectAdapter;", "outSiteAdapter", "switchMapResultListener", "com/pudutech/peanut/robot_ui/module/setting/ui/MultiMapFragment$switchMapResultListener$1", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/MultiMapFragment$switchMapResultListener$1;", "usherAdapter", "initChargingData", "", "initData", "initDiningLetOutData", "initGreetData", "initUsherData", "initView", "initViewData", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onViewCreated", "view", "showSelectMapDialog", "mapItem", "showTipDialog", "tips", "startCheckLocation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MultiMapFragment extends Fragment {
    private HashMap _$_findViewCache;
    private SelectPositionAdapter chargingAdapter;
    private SelectPositionAdapter greetAdapter;
    private boolean isFinished;
    private MapSelectItem mMapSelectItem;
    private ShowTipMsgDialog mShowTipDialog;
    private MapSelectAdapter mapAdapter;
    private SelectPositionAdapter outSiteAdapter;
    private SelectPositionAdapter usherAdapter;
    private final String TAG = "MultiMapFragment";
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = MultiMapFragment.this.TASK_CHEACK_LOCATION;
            if (i2 == i) {
                MultiMapFragment.this.startCheckLocation();
            } else if (i2 == 1) {
                MultiMapFragment.this.initViewData();
            } else if (i2 == 2) {
                ((MyScrollView) MultiMapFragment.this._$_findCachedViewById(C5508R.id.scroll_view)).fullScroll(33);
            }
            return true;
        }
    });
    private final MultiMapFragment$switchMapResultListener$1 switchMapResultListener = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$switchMapResultListener$1
        @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
        public void onResult(boolean b, String errorMsg) {
            String str;
            str = MultiMapFragment.this.TAG;
            Pdlog.m3273d(str, "switchMapResultListener onResult = " + b);
            if (MultiMapFragment.this.isDetached()) {
                return;
            }
            MultiMapFragment.this.isFinished = false;
            if (b) {
                DeliverTaskEditActivity.INSTANCE.setChangeMap(1);
                MultiMapFragment.this.initViewData();
                MultiMapFragment.this.showTipDialog("地图切换成功，重新定位中...");
                MultiMapFragment.this.startCheckLocation();
                return;
            }
            Context context = RobotContext.INSTANCE.getContext();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = MultiMapFragment.this.getString(C5508R.string.pdStr11_14);
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
    private final int TASK_CHEACK_LOCATION = 100;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LocateCase.values().length];

        static {
            $EnumSwitchMapping$0[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$0[LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$0[LocateCase.Slamware.ordinal()] = 3;
        }
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

    public static final /* synthetic */ MapSelectAdapter access$getMapAdapter$p(MultiMapFragment multiMapFragment) {
        MapSelectAdapter mapSelectAdapter = multiMapFragment.mapAdapter;
        if (mapSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        return mapSelectAdapter;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_multi_map, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private final void initData() {
        RobotMapManager.INSTANCE.addSwitchMapResultListener(this.switchMapResultListener);
        RobotMapManager.INSTANCE.getMaps(new Function1<ArrayList<String>, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$initData$1
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
                    arrayList.add(new MapSelectItem(str, Intrinsics.areEqual(RobotMapManager.INSTANCE.getDefaultPdmap(), str)));
                }
                MultiMapFragment.access$getMapAdapter$p(MultiMapFragment.this).setNewData(arrayList);
            }
        });
        this.handler.sendEmptyMessageDelayed(1, 200L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.isFinished = false;
        DeliverTaskEditActivity.INSTANCE.setChangeMap(0);
        MapSelectItem mapSelectItem = this.mMapSelectItem;
        if (mapSelectItem != null) {
            showSelectMapDialog(mapSelectItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initViewData() {
        RobotMapManager.INSTANCE.getMapDrawPath(RobotMapManager.INSTANCE.getDefaultPdmap(), new Function1<MapDrawPathLoader, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$initViewData$1
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
            public final void invoke2(MapDrawPathLoader mapDrawPathLoader) {
                MapView mapView;
                if (mapDrawPathLoader == null || (mapView = (MapView) MultiMapFragment.this._$_findCachedViewById(C5508R.id.map_iv)) == null) {
                    return;
                }
                mapView.setMapData(mapDrawPathLoader.getMaxX(), mapDrawPathLoader.getMaxY(), mapDrawPathLoader.getMinX(), mapDrawPathLoader.getMinY(), mapDrawPathLoader.getMapLines());
            }
        });
        initDiningLetOutData();
        initUsherData();
        initGreetData();
        initChargingData();
    }

    private final void initChargingData() {
        List<String> chargingPiles = RobotMapManager.INSTANCE.getChargingPiles();
        ArrayList arrayList = new ArrayList();
        if (chargingPiles != null) {
            for (String str : chargingPiles) {
                arrayList.add(new SelectPositionItem(str, Intrinsics.areEqual(str, Constans.INSTANCE.getChargingPosition())));
            }
        }
        if (arrayList.size() > 0) {
            TextView textView = (TextView) _$_findCachedViewById(C5508R.id.charging_position_tv);
            if (textView != null) {
                textView.setVisibility(0);
            }
            CardView cardView = (CardView) _$_findCachedViewById(C5508R.id.charging_position_layout);
            if (cardView != null) {
                cardView.setVisibility(0);
            }
        } else {
            TextView textView2 = (TextView) _$_findCachedViewById(C5508R.id.charging_position_tv);
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            CardView cardView2 = (CardView) _$_findCachedViewById(C5508R.id.charging_position_layout);
            if (cardView2 != null) {
                cardView2.setVisibility(8);
            }
        }
        SelectPositionAdapter selectPositionAdapter = this.chargingAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chargingAdapter");
        }
        selectPositionAdapter.setNewData(arrayList);
    }

    private final void initUsherData() {
        TextView textView;
        ArrayList<String> ushers = RobotMapManager.INSTANCE.getUshers();
        if ((!ushers.isEmpty()) && (textView = (TextView) _$_findCachedViewById(C5508R.id.usher_position_tv)) != null) {
            textView.setVisibility(8);
        }
        ArrayList arrayList = new ArrayList();
        for (String str : ushers) {
            arrayList.add(new SelectPositionItem(str, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentMapUsherChosen())));
        }
        if (arrayList.size() > 0) {
            TextView textView2 = (TextView) _$_findCachedViewById(C5508R.id.usher_position_tv);
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            CardView cardView = (CardView) _$_findCachedViewById(C5508R.id.usher_position_layout);
            if (cardView != null) {
                cardView.setVisibility(0);
            }
        } else {
            TextView textView3 = (TextView) _$_findCachedViewById(C5508R.id.usher_position_tv);
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            CardView cardView2 = (CardView) _$_findCachedViewById(C5508R.id.usher_position_layout);
            if (cardView2 != null) {
                cardView2.setVisibility(8);
            }
        }
        SelectPositionAdapter selectPositionAdapter = this.usherAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        selectPositionAdapter.setNewData(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTipDialog(String tips) {
        if (this.mShowTipDialog == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            this.mShowTipDialog = new ShowTipMsgDialog(requireActivity);
        }
        ShowTipMsgDialog showTipMsgDialog = this.mShowTipDialog;
        if (showTipMsgDialog == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog.showTipMsg(tips);
        ShowTipMsgDialog showTipMsgDialog2 = this.mShowTipDialog;
        if (showTipMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog2.setCanCancel(false);
        ShowTipMsgDialog showTipMsgDialog3 = this.mShowTipDialog;
        if (showTipMsgDialog3 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog3.setCallback(new ShowTipMsgDialog.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$showTipDialog$1
            @Override // com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog.Callback
            public void onDissmiss(ShowTipMsgDialog dailog) {
                Intrinsics.checkParameterIsNotNull(dailog, "dailog");
                MultiMapFragment.this.mShowTipDialog = (ShowTipMsgDialog) null;
            }
        });
        ShowTipMsgDialog showTipMsgDialog4 = this.mShowTipDialog;
        if (showTipMsgDialog4 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog4.show();
    }

    private final void initGreetData() {
        ArrayList<String> ushers = RobotMapManager.INSTANCE.getUshers();
        ArrayList arrayList = new ArrayList();
        for (String str : ushers) {
            arrayList.add(new SelectPositionItem(str, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentTakeMap())));
        }
        if (arrayList.size() > 0) {
            TextView textView = (TextView) _$_findCachedViewById(C5508R.id.greet_position_tv);
            if (textView != null) {
                textView.setVisibility(0);
            }
            CardView cardView = (CardView) _$_findCachedViewById(C5508R.id.greet_position_layout);
            if (cardView != null) {
                cardView.setVisibility(0);
            }
        } else {
            CardView cardView2 = (CardView) _$_findCachedViewById(C5508R.id.greet_position_layout);
            if (cardView2 != null) {
                cardView2.setVisibility(8);
            }
            TextView textView2 = (TextView) _$_findCachedViewById(C5508R.id.greet_position_tv);
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
        }
        SelectPositionAdapter selectPositionAdapter = this.greetAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("greetAdapter");
        }
        selectPositionAdapter.setNewData(arrayList);
    }

    private final void initDiningLetOutData() {
        ArrayList arrayList = new ArrayList();
        for (String str : RobotMapManager.INSTANCE.getDinningOutLets()) {
            arrayList.add(new SelectPositionItem(str, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen())));
        }
        if (arrayList.size() > 0) {
            TextView textView = (TextView) _$_findCachedViewById(C5508R.id.out_food_position_tv);
            if (textView != null) {
                textView.setVisibility(0);
            }
            CardView cardView = (CardView) _$_findCachedViewById(C5508R.id.out_food_position_layout);
            if (cardView != null) {
                cardView.setVisibility(0);
            }
        } else {
            TextView textView2 = (TextView) _$_findCachedViewById(C5508R.id.out_food_position_tv);
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            CardView cardView2 = (CardView) _$_findCachedViewById(C5508R.id.out_food_position_layout);
            if (cardView2 != null) {
                cardView2.setVisibility(8);
            }
        }
        SelectPositionAdapter selectPositionAdapter = this.outSiteAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
        }
        selectPositionAdapter.setNewData(arrayList);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        RobotMapManager.INSTANCE.removeSwitchMapResultListener(this.switchMapResultListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSelectMapDialog(final MapSelectItem mapItem) {
        Pdlog.m3273d(this.TAG, "showSelectMapDialog : mapName = " + mapItem.getMapName() + "; ");
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        final MapSwitchTipDialog mapSwitchTipDialog = new MapSwitchTipDialog(requireContext);
        mapSwitchTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$showSelectMapDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MultiMapFragment.this.isFinished = false;
                mapSwitchTipDialog.dismiss();
            }
        });
        mapSwitchTipDialog.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$showSelectMapDialog$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Handler handler;
                RobotMapManager.INSTANCE.switchMap(RobotMapManager.INSTANCE.getCurrentFloorName(), mapItem.getMapName());
                List<MapSelectItem> data = MultiMapFragment.access$getMapAdapter$p(MultiMapFragment.this).getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "mapAdapter.data");
                for (MapSelectItem mapSelectItem : data) {
                    mapSelectItem.setSelect(Intrinsics.areEqual(mapSelectItem.getMapName(), mapItem.getMapName()));
                }
                MultiMapFragment.access$getMapAdapter$p(MultiMapFragment.this).notifyDataSetChanged();
                handler = MultiMapFragment.this.handler;
                handler.sendEmptyMessageDelayed(2, 150L);
                mapSwitchTipDialog.dismiss();
            }
        });
        mapSwitchTipDialog.show();
    }

    private final void initView() {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        this.mapAdapter = new MapSelectAdapter(context);
        RecyclerView map_recycler_view = (RecyclerView) _$_findCachedViewById(C5508R.id.map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(map_recycler_view, "map_recycler_view");
        map_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
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
        mapSelectAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$initView$1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                boolean z;
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
                        if (i2 == i && !mapSelectItem.isSelect()) {
                            z = MultiMapFragment.this.isFinished;
                            if (!z) {
                                MultiMapFragment.this.isFinished = true;
                                MultiMapFragment.this.mMapSelectItem = mapSelectItem;
                                MultiMapFragment.this.showSelectMapDialog(mapSelectItem);
                            }
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.MapSelectItem");
                    }
                }
            }
        });
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        this.outSiteAdapter = new SelectPositionAdapter(context2);
        MaxHeightRecyclerView out_food_position_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.out_food_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(out_food_position_recycler_view, "out_food_position_recycler_view");
        out_food_position_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView out_food_position_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.out_food_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(out_food_position_recycler_view2, "out_food_position_recycler_view");
        SelectPositionAdapter selectPositionAdapter = this.outSiteAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
        }
        out_food_position_recycler_view2.setAdapter(selectPositionAdapter);
        SelectPositionAdapter selectPositionAdapter2 = this.outSiteAdapter;
        if (selectPositionAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
        }
        selectPositionAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$initView$2
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
                        SelectPositionItem selectPositionItem = (SelectPositionItem) obj;
                        selectPositionItem.setSelect(i2 == i);
                        if (selectPositionItem.isSelect()) {
                            RobotMapManager.INSTANCE.setCurrentMapDiningOutLetChosen(selectPositionItem.getId());
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectPositionItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        Context context3 = getContext();
        if (context3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
        this.usherAdapter = new SelectPositionAdapter(context3);
        MaxHeightRecyclerView usher_position_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.usher_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(usher_position_recycler_view, "usher_position_recycler_view");
        usher_position_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView usher_position_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.usher_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(usher_position_recycler_view2, "usher_position_recycler_view");
        SelectPositionAdapter selectPositionAdapter3 = this.usherAdapter;
        if (selectPositionAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        usher_position_recycler_view2.setAdapter(selectPositionAdapter3);
        SelectPositionAdapter selectPositionAdapter4 = this.usherAdapter;
        if (selectPositionAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        selectPositionAdapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$initView$3
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
                        SelectPositionItem selectPositionItem = (SelectPositionItem) obj;
                        selectPositionItem.setSelect(i2 == i);
                        if (selectPositionItem.isSelect()) {
                            RobotMapManager.INSTANCE.setCurrentMapUsherChosen(selectPositionItem.getId());
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectPositionItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        Context context4 = getContext();
        if (context4 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context4, "context!!");
        this.greetAdapter = new SelectPositionAdapter(context4);
        MaxHeightRecyclerView take_map_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.take_map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(take_map_recycler_view, "take_map_recycler_view");
        take_map_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView take_map_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.take_map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(take_map_recycler_view2, "take_map_recycler_view");
        SelectPositionAdapter selectPositionAdapter5 = this.greetAdapter;
        if (selectPositionAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("greetAdapter");
        }
        take_map_recycler_view2.setAdapter(selectPositionAdapter5);
        SelectPositionAdapter selectPositionAdapter6 = this.greetAdapter;
        if (selectPositionAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("greetAdapter");
        }
        selectPositionAdapter6.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$initView$4
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
                        SelectPositionItem selectPositionItem = (SelectPositionItem) obj;
                        selectPositionItem.setSelect(i2 == i);
                        if (selectPositionItem.isSelect()) {
                            RobotMapManager.INSTANCE.setCurrentTakeMap(selectPositionItem.getId());
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectPositionItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        Context context5 = getContext();
        if (context5 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context5, "context!!");
        this.chargingAdapter = new SelectPositionAdapter(context5);
        MaxHeightRecyclerView charging_position_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.charging_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(charging_position_recycler_view, "charging_position_recycler_view");
        charging_position_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView charging_position_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.charging_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(charging_position_recycler_view2, "charging_position_recycler_view");
        SelectPositionAdapter selectPositionAdapter7 = this.chargingAdapter;
        if (selectPositionAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chargingAdapter");
        }
        charging_position_recycler_view2.setAdapter(selectPositionAdapter7);
        SelectPositionAdapter selectPositionAdapter8 = this.chargingAdapter;
        if (selectPositionAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chargingAdapter");
        }
        selectPositionAdapter8.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$initView$5
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
                        SelectPositionItem selectPositionItem = (SelectPositionItem) obj;
                        selectPositionItem.setSelect(i2 == i);
                        if (selectPositionItem.isSelect()) {
                            Constans.INSTANCE.setChargingPosition(selectPositionItem.getId());
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectPositionItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheckLocation() {
        Pdlog.m3273d(this.TAG, "startCheckLocation");
        RobotMapManager.INSTANCE.checkLocationSelftInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MultiMapFragment$startCheckLocation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                ShowTipMsgDialog showTipMsgDialog;
                String str2;
                Handler handler;
                int i;
                String str3;
                ShowTipMsgDialog showTipMsgDialog2;
                str = MultiMapFragment.this.TAG;
                Pdlog.m3273d(str, "onLocationInitListener is:" + z);
                if (z) {
                    handler = MultiMapFragment.this.handler;
                    i = MultiMapFragment.this.TASK_CHEACK_LOCATION;
                    handler.removeMessages(i);
                    str3 = MultiMapFragment.this.TAG;
                    Pdlog.m3273d(str3, "onLocationInitListener is:" + z + " go to HomeMenuActivity");
                    showTipMsgDialog2 = MultiMapFragment.this.mShowTipDialog;
                    if (showTipMsgDialog2 != null) {
                        showTipMsgDialog2.dismiss();
                        return;
                    }
                    return;
                }
                showTipMsgDialog = MultiMapFragment.this.mShowTipDialog;
                if (showTipMsgDialog != null) {
                    showTipMsgDialog.dismiss();
                }
                int i2 = MultiMapFragment.WhenMappings.$EnumSwitchMapping$0[RobotMapManager.INSTANCE.getLocateCase().ordinal()];
                if (i2 == 1) {
                    FragmentActivity activity = MultiMapFragment.this.getActivity();
                    if (activity != null) {
                        Intent intent = new Intent(activity, (Class<?>) CheckLocationActivity.class);
                        intent.putExtra("state", 1);
                        FragmentActivity activity2 = MultiMapFragment.this.getActivity();
                        if (activity2 != null) {
                            activity2.startActivity(intent);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (i2 != 2 && i2 != 3) {
                    str2 = MultiMapFragment.this.TAG;
                    Pdlog.m3274e(str2, "onLocationInitListener  :LocateCase wrong " + RobotMapManager.INSTANCE.getLocateCase());
                    FragmentActivity activity3 = MultiMapFragment.this.getActivity();
                    if (activity3 != null) {
                        AnkoInternals.internalStartActivity(activity3, CheckLocationActivity.class, new Pair[0]);
                        return;
                    }
                    return;
                }
                FragmentActivity activity4 = MultiMapFragment.this.getActivity();
                if (activity4 != null) {
                    Intent intent2 = new Intent(activity4, (Class<?>) LaserCheckLocationActivity.class);
                    intent2.putExtra("state", 1);
                    FragmentActivity activity5 = MultiMapFragment.this.getActivity();
                    if (activity5 != null) {
                        activity5.startActivity(intent2);
                    }
                }
            }
        }, 5000L);
    }
}
