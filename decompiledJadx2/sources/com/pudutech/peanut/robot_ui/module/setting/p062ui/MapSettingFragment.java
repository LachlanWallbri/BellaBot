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
import android.widget.Button;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.activity.MapListActivity;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.map.DestinationType;
import com.pudutech.mirsdkwrap.lib.map.MapDrawPathLoader;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.CheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserCheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.MapSettingFragment;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.DeliverTaskEditActivity;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MapItemInfo;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MapListAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MapViewData;
import com.pudutech.peanut.robot_ui.p063ui.adapter.PointListAdapter;
import com.pudutech.peanut.robot_ui.p063ui.dialog.MapSwitchTipDialog;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.LocationAndMapModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: MapSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008b\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001#\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0002J.\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00062\b\b\u0002\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0002J\b\u0010/\u001a\u00020&H\u0002J\b\u00100\u001a\u00020&H\u0002J\b\u00101\u001a\u00020&H\u0002J\b\u00102\u001a\u00020&H\u0002J\b\u00103\u001a\u00020&H\u0002J\b\u00104\u001a\u00020&H\u0002J\b\u00105\u001a\u00020&H\u0002J\b\u00106\u001a\u00020&H\u0002J\b\u00107\u001a\u00020&H\u0002J\b\u00108\u001a\u00020&H\u0002J\u0018\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020,2\u0006\u0010)\u001a\u00020\u0004H\u0002J\b\u0010;\u001a\u00020&H\u0002J&\u0010<\u001a\u0004\u0018\u00010=2\u0006\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010A2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\b\u0010D\u001a\u00020&H\u0016J\b\u0010E\u001a\u00020&H\u0016J\u001a\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\b\u0010H\u001a\u00020&H\u0002J\u0018\u0010I\u001a\u00020&2\u0006\u0010J\u001a\u00020\u00042\u0006\u0010:\u001a\u00020,H\u0002J\u0010\u0010K\u001a\u00020&2\u0006\u0010L\u001a\u00020\u0004H\u0002J\b\u0010M\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$¨\u0006N"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/MapSettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "TASK_CHEACK_LOCATION", "", "currentMap", "dialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/MapSwitchTipDialog;", "handler", "Landroid/os/Handler;", "listAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapListAdapter;", "locateHintDialog", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "getLocateHintDialog", "()Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "setLocateHintDialog", "(Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;)V", "locationAndMapModel", "Lcom/pudutech/peanut/robot_ui/viewmodel/LocationAndMapModel;", "getLocationAndMapModel", "()Lcom/pudutech/peanut/robot_ui/viewmodel/LocationAndMapModel;", "locationAndMapModel$delegate", "Lkotlin/Lazy;", "mShowTipDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "mapListData", "", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapItemInfo;", "pointAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/PointListAdapter;", "pointListData", "switchMapResultListener", "com/pudutech/peanut/robot_ui/module/setting/ui/MapSettingFragment$switchMapResultListener$1", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/MapSettingFragment$switchMapResultListener$1;", "checkLocationState", "", "createDialog", "getMapItemInfo", "name", "type", "isSelect", "", "destinationType", "Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;", "initChargingData", "initData", "initDiningLetOutData", "initGreetData", "initListener", "initRecyclerView", "initSolicitData", "initUsherData", "initView", "initViewData", "notifyMapList", "isChecked", "notifyMapView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onViewCreated", "view", "showLocateHintDialog", "showSelectMapDialog", "mapItem", "showTipDialog", "tips", "startCheckLocation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapSettingFragment extends Fragment {
    private final String TAG;
    private final int TASK_CHEACK_LOCATION;
    private HashMap _$_findViewCache;
    private String currentMap;
    private MapSwitchTipDialog dialog;
    private Handler handler;
    private final MapListAdapter listAdapter;
    private CommonDialog locateHintDialog;

    /* renamed from: locationAndMapModel$delegate, reason: from kotlin metadata */
    private final Lazy locationAndMapModel;
    private ShowTipMsgDialog mShowTipDialog;
    private final List<MapItemInfo> mapListData;
    private final PointListAdapter pointAdapter;
    private final List<MapItemInfo> pointListData;
    private final MapSettingFragment$switchMapResultListener$1 switchMapResultListener;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DestinationType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[DestinationType.USHER.ordinal()] = 1;
            $EnumSwitchMapping$0[DestinationType.SOLICIT.ordinal()] = 2;
            $EnumSwitchMapping$0[DestinationType.CHARGE_PILE.ordinal()] = 3;
            $EnumSwitchMapping$0[DestinationType.DINING_OUTLET.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[LocateCase.values().length];
            $EnumSwitchMapping$1[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$1[LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$1[LocateCase.Laser.ordinal()] = 3;
            $EnumSwitchMapping$1[LocateCase.Slamware.ordinal()] = 4;
        }
    }

    private final LocationAndMapModel getLocationAndMapModel() {
        return (LocationAndMapModel) this.locationAndMapModel.getValue();
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

    /* JADX WARN: Type inference failed for: r0v10, types: [com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$switchMapResultListener$1] */
    public MapSettingFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.locationAndMapModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LocationAndMapModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$$special$$inlined$viewModels$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, (Function0) null);
        this.TAG = "MapSettingFragment";
        this.listAdapter = new MapListAdapter();
        this.pointAdapter = new PointListAdapter();
        this.mapListData = new ArrayList();
        this.pointListData = new ArrayList();
        this.switchMapResultListener = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$switchMapResultListener$1
            @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
            public void onResult(boolean b, String errorMsg) {
                String str;
                MapSwitchTipDialog mapSwitchTipDialog;
                str = MapSettingFragment.this.TAG;
                Pdlog.m3273d(str, "switchMapResultListener onResult = " + b + ' ' + errorMsg);
                if (MapSettingFragment.this.isDetached()) {
                    return;
                }
                if (!b) {
                    mapSwitchTipDialog = MapSettingFragment.this.dialog;
                    if (mapSwitchTipDialog != null) {
                        mapSwitchTipDialog.dismiss();
                    }
                    Context context = RobotContext.INSTANCE.getContext();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = MapSettingFragment.this.getString(C5508R.string.pdStr11_14);
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
                    return;
                }
                DeliverTaskEditActivity.INSTANCE.setChangeMap(1);
                MapSettingFragment.this.initViewData();
                MapSettingFragment.this.showTipDialog("地图切换成功，重新定位中...");
                MapSettingFragment.this.startCheckLocation();
            }
        };
        this.TASK_CHEACK_LOCATION = 100;
        this.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$handler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                int i2 = message.what;
                i = MapSettingFragment.this.TASK_CHEACK_LOCATION;
                if (i2 == i) {
                    MapSettingFragment.this.startCheckLocation();
                } else if (i2 == 1) {
                    MapSettingFragment.this.initViewData();
                }
                return true;
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_map_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        RobotMapManager.INSTANCE.addSwitchMapResultListener(this.switchMapResultListener);
        createDialog();
        initView();
        initData();
        initListener();
    }

    private final void initListener() {
        this.listAdapter.setOnItemSelected(new Function2<String, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$initListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String name, boolean z) {
                Intrinsics.checkParameterIsNotNull(name, "name");
                if (z) {
                    MapSettingFragment.this.showSelectMapDialog(name, z);
                }
            }
        });
        this.pointAdapter.setOnItemSelected(new Function2<MapItemInfo, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$initListener$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(MapItemInfo mapItemInfo, Boolean bool) {
                invoke(mapItemInfo, bool.booleanValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x005f  */
            /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke(MapItemInfo item, boolean z) {
                boolean z2;
                PointListAdapter pointListAdapter;
                PointListAdapter pointListAdapter2;
                String str;
                PointListAdapter pointListAdapter3;
                PointListAdapter pointListAdapter4;
                Intrinsics.checkParameterIsNotNull(item, "item");
                if (!z) {
                    return;
                }
                DestinationType destinationType = item.getDestinationType();
                if (destinationType != null) {
                    int i = MapSettingFragment.WhenMappings.$EnumSwitchMapping$0[destinationType.ordinal()];
                    if (i == 1) {
                        z2 = RobotMapManager.INSTANCE.setCurrentMapUsherChosen(item.getName());
                    } else if (i == 2) {
                        z2 = RobotMapManager.INSTANCE.setCurrentMapSolicit(item.getName());
                    } else if (i == 3) {
                        Constans.INSTANCE.setChargingPosition(item.getName());
                        z2 = Intrinsics.areEqual(Constans.INSTANCE.getChargingPosition(), item.getName());
                    } else if (i == 4) {
                        z2 = RobotMapManager.INSTANCE.setCurrentMapDiningOutLetChosen(item.getName());
                    }
                    if (z2) {
                        return;
                    }
                    pointListAdapter = MapSettingFragment.this.pointAdapter;
                    List<MapItemInfo> data = pointListAdapter.getData();
                    if (data != null) {
                        for (MapItemInfo mapItemInfo : data) {
                            if (mapItemInfo.isCheck() && mapItemInfo.getDestinationType() == item.getDestinationType()) {
                                mapItemInfo.setCheck(false);
                            }
                        }
                    }
                    pointListAdapter2 = MapSettingFragment.this.pointAdapter;
                    List<MapItemInfo> data2 = pointListAdapter2.getData();
                    if (data2 != null) {
                        for (MapItemInfo mapItemInfo2 : data2) {
                            if (Intrinsics.areEqual(item.getName(), mapItemInfo2.getName()) && mapItemInfo2.getDestinationType() == item.getDestinationType()) {
                                mapItemInfo2.setCheck(true);
                            }
                        }
                    }
                    str = MapSettingFragment.this.TAG;
                    pointListAdapter3 = MapSettingFragment.this.pointAdapter;
                    Pdlog.m3274e(str, pointListAdapter3.getData());
                    pointListAdapter4 = MapSettingFragment.this.pointAdapter;
                    pointListAdapter4.notifyDataSetChanged();
                    return;
                }
                z2 = false;
                if (z2) {
                }
            }
        });
        final TextView textView = (TextView) _$_findCachedViewById(C5508R.id.tv_edit_map);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$initListener$$inlined$singleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ViewExtKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ViewExtKt.setLastClickTime(textView, currentTimeMillis);
                    this.requireActivity().startActivity(new Intent(this.requireContext(), (Class<?>) MapListActivity.class));
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyMapList(boolean isChecked, String name) {
        List<MapItemInfo> data = this.listAdapter.getData();
        if (data != null) {
            for (MapItemInfo mapItemInfo : data) {
                if (isChecked) {
                    if (Intrinsics.areEqual(name, mapItemInfo.getName())) {
                        mapItemInfo.setCheck(isChecked);
                    } else {
                        mapItemInfo.setCheck(false);
                    }
                }
            }
        }
        this.currentMap = name;
        this.listAdapter.notifyDataSetChanged();
    }

    private final void initData() {
        Pdlog.m3274e(this.TAG, this.dialog);
        MapSwitchTipDialog mapSwitchTipDialog = this.dialog;
        if (mapSwitchTipDialog != null) {
            mapSwitchTipDialog.show();
            mapSwitchTipDialog.showProgress();
        }
        RobotMapManager.INSTANCE.getMaps(new Function1<ArrayList<String>, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$initData$2
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
                MapListAdapter mapListAdapter;
                List<MapItemInfo> list;
                MapListAdapter mapListAdapter2;
                MapSwitchTipDialog mapSwitchTipDialog2;
                List list2;
                Intrinsics.checkParameterIsNotNull(names, "names");
                for (String str : names) {
                    MapItemInfo mapItemInfo$default = MapSettingFragment.getMapItemInfo$default(MapSettingFragment.this, str, 2, false, null, 12, null);
                    if (Intrinsics.areEqual(RobotMapManager.INSTANCE.getDefaultPdmap(), str)) {
                        mapItemInfo$default.setCheck(true);
                        MapSettingFragment.this.currentMap = str;
                    }
                    list2 = MapSettingFragment.this.mapListData;
                    list2.add(mapItemInfo$default);
                }
                mapListAdapter = MapSettingFragment.this.listAdapter;
                list = MapSettingFragment.this.mapListData;
                mapListAdapter.setData(list);
                mapListAdapter2 = MapSettingFragment.this.listAdapter;
                mapListAdapter2.notifyDataSetChanged();
                MapSettingFragment.this.notifyMapView();
                mapSwitchTipDialog2 = MapSettingFragment.this.dialog;
                if (mapSwitchTipDialog2 != null) {
                    mapSwitchTipDialog2.dismiss();
                }
            }
        });
        this.handler.sendEmptyMessageDelayed(1, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyMapView() {
        List<MapItemInfo> data = this.listAdapter.getData();
        if (data == null) {
            return;
        }
        int size = data.size() - 1;
        int i = 0;
        if (size < 0) {
            return;
        }
        while (true) {
            final MapItemInfo mapItemInfo = data.get(i);
            RobotMapManager.INSTANCE.getMapDrawPath(mapItemInfo.getName(), new Function1<MapDrawPathLoader, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$notifyMapView$$inlined$forEachWithIndex$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    MapListAdapter mapListAdapter;
                    if (mapDrawPathLoader != null) {
                        MapItemInfo.this.setMapData(new MapViewData(mapDrawPathLoader.getMaxX(), mapDrawPathLoader.getMaxY(), mapDrawPathLoader.getMinX(), mapDrawPathLoader.getMinY(), mapDrawPathLoader.getMapLines(), null, 32, null));
                    }
                    mapListAdapter = this.listAdapter;
                    mapListAdapter.notifyDataSetChanged();
                }
            });
            if (i == size) {
                return;
            } else {
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initViewData() {
        Pdlog.m3274e(this.TAG, "initViewData", getContext());
        if (getContext() == null) {
            return;
        }
        this.pointListData.clear();
        initDiningLetOutData();
        initUsherData();
        initSolicitData();
        initChargingData();
        this.pointAdapter.setData(this.pointListData);
        Pdlog.m3274e(this.TAG, this.pointAdapter.getData());
        this.pointAdapter.notifyDataSetChanged();
        MapSwitchTipDialog mapSwitchTipDialog = this.dialog;
        if (mapSwitchTipDialog != null) {
            mapSwitchTipDialog.dismiss();
        }
    }

    private final void initSolicitData() {
        ArrayList<String> ushers = RobotMapManager.INSTANCE.getUshers();
        Pdlog.m3274e(getString(C5508R.string.select_solict), ushers);
        if (ushers == null || ushers.isEmpty()) {
            return;
        }
        List<MapItemInfo> list = this.pointListData;
        String string = getString(C5508R.string.usher_point_select);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.usher_point_select)");
        list.add(getMapItemInfo$default(this, string, 3, false, DestinationType.SOLICIT, 4, null));
        for (String str : ushers) {
            this.pointListData.add(getMapItemInfo(str, 4, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentMapSolicitChosen()), DestinationType.SOLICIT));
        }
    }

    private final void initChargingData() {
        List<String> chargingPiles = RobotMapManager.INSTANCE.getChargingPiles();
        Pdlog.m3274e(getString(C5508R.string.charging_position_select_title), chargingPiles);
        if (chargingPiles == null || chargingPiles.isEmpty()) {
            return;
        }
        List<MapItemInfo> list = this.pointListData;
        String string = getString(C5508R.string.charging_position_select_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.charging_position_select_title)");
        list.add(getMapItemInfo$default(this, string, 3, false, DestinationType.CHARGE_PILE, 4, null));
        for (String str : chargingPiles) {
            this.pointListData.add(getMapItemInfo(str, 4, Intrinsics.areEqual(str, Constans.INSTANCE.getChargingPosition()), DestinationType.CHARGE_PILE));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        DeliverTaskEditActivity.INSTANCE.setChangeMap(0);
        String str = this.TAG;
        Object[] objArr = new Object[2];
        objArr[0] = "onResume";
        List<MapItemInfo> data = this.listAdapter.getData();
        objArr[1] = Boolean.valueOf(data == null || data.isEmpty());
        Pdlog.m3273d(str, objArr);
        List<MapItemInfo> data2 = this.listAdapter.getData();
        if (data2 == null || data2.isEmpty()) {
            return;
        }
        List<MapItemInfo> data3 = this.listAdapter.getData();
        if (data3 != null) {
            data3.clear();
        }
        initData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSelectMapDialog(final String mapItem, final boolean isChecked) {
        final MapSwitchTipDialog mapSwitchTipDialog;
        Pdlog.m3273d(this.TAG, "showSelectMapDialog : mapName = " + mapItem + "; ");
        if (getContext() == null || (mapSwitchTipDialog = this.dialog) == null) {
            return;
        }
        mapSwitchTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$showSelectMapDialog$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                MapSwitchTipDialog.this.dismiss();
            }
        });
        mapSwitchTipDialog.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$showSelectMapDialog$$inlined$run$lambda$1
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
                MapSwitchTipDialog.this.showProgress();
                this.notifyMapList(isChecked, mapItem);
                RobotMapManager.INSTANCE.switchMap(RobotMapManager.INSTANCE.getCurrentFloorName(), mapItem);
            }
        });
        mapSwitchTipDialog.show();
    }

    private final void createDialog() {
        if (getContext() != null && this.dialog == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.dialog = new MapSwitchTipDialog(requireContext);
        }
    }

    private final void initUsherData() {
        ArrayList<String> ushers = RobotMapManager.INSTANCE.getUshers();
        Pdlog.m3274e(getString(C5508R.string.pdStr7_171), ushers);
        if (ushers == null || ushers.isEmpty()) {
            return;
        }
        List<MapItemInfo> list = this.pointListData;
        String string = getString(C5508R.string.select_solict);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.select_solict)");
        list.add(getMapItemInfo$default(this, string, 3, false, DestinationType.USHER, 4, null));
        for (String str : ushers) {
            this.pointListData.add(getMapItemInfo(str, 4, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentMapUsherChosen()), DestinationType.USHER));
        }
    }

    private final void initGreetData() {
        ArrayList<String> ushers = RobotMapManager.INSTANCE.getUshers();
        Pdlog.m3274e(getString(C5508R.string.pdStr7_171), "name =" + ushers);
        if (ushers == null || ushers.isEmpty()) {
            return;
        }
        List<MapItemInfo> list = this.pointListData;
        String string = getString(C5508R.string.pdStr7_171);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_171)");
        list.add(getMapItemInfo$default(this, string, 3, false, DestinationType.USHER, 4, null));
        for (String str : ushers) {
            this.pointListData.add(getMapItemInfo$default(this, str, 4, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentTakeMap()), null, 8, null));
        }
    }

    private final void initDiningLetOutData() {
        ArrayList<String> dinningOutLets = RobotMapManager.INSTANCE.getDinningOutLets();
        Pdlog.m3274e("出餐点", dinningOutLets);
        if (dinningOutLets == null || dinningOutLets.isEmpty()) {
            return;
        }
        List<MapItemInfo> list = this.pointListData;
        String string = getString(C5508R.string.pdStr7_46);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_46)");
        list.add(getMapItemInfo$default(this, string, 3, false, DestinationType.DINING_OUTLET, 4, null));
        for (String str : dinningOutLets) {
            this.pointListData.add(getMapItemInfo(str, 4, Intrinsics.areEqual(str, RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen()), DestinationType.DINING_OUTLET));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        RobotMapManager.INSTANCE.removeSwitchMapResultListener(this.switchMapResultListener);
        this.dialog = (MapSwitchTipDialog) null;
    }

    public final CommonDialog getLocateHintDialog() {
        return this.locateHintDialog;
    }

    public final void setLocateHintDialog(CommonDialog commonDialog) {
        this.locateHintDialog = commonDialog;
    }

    private final void showLocateHintDialog() {
        if (getContext() == null) {
            return;
        }
        if (this.locateHintDialog == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            CommonDialog.Builder builder = new CommonDialog.Builder(requireContext);
            String string = getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(com.pudutech.m…install_ui.R.string.tips)");
            CommonDialog.Builder title = builder.setTitle(string);
            String string2 = getString(C5362R.string.map_success_locate_hint);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(com.pudutech.m….map_success_locate_hint)");
            this.locateHintDialog = title.setMinContent(string2).setClose(true).create();
            final CommonDialog commonDialog = this.locateHintDialog;
            if (commonDialog != null) {
                commonDialog.setCanceledOnTouchOutside(false);
                Button btLeft = commonDialog.getBtLeft();
                if (btLeft != null) {
                    btLeft.setVisibility(8);
                }
                Button btRight = commonDialog.getBtRight();
                if (btRight != null) {
                    btRight.setVisibility(8);
                }
                commonDialog.setCloseClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$showLocateHintDialog$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
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
                        CommonDialog.this.dismiss();
                    }
                });
            }
        }
        CommonDialog commonDialog2 = this.locateHintDialog;
        if (commonDialog2 != null) {
            commonDialog2.show();
        }
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
        showTipMsgDialog3.setCallback(new ShowTipMsgDialog.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$showTipDialog$1
            @Override // com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog.Callback
            public void onDissmiss(ShowTipMsgDialog dailog) {
                Intrinsics.checkParameterIsNotNull(dailog, "dailog");
                MapSettingFragment.this.mShowTipDialog = (ShowTipMsgDialog) null;
            }
        });
        ShowTipMsgDialog showTipMsgDialog4 = this.mShowTipDialog;
        if (showTipMsgDialog4 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog4.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheckLocation() {
        Pdlog.m3273d(this.TAG, "startCheckLocation");
        RobotMapManager.INSTANCE.checkLocationSelftInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$startCheckLocation$1
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
                str = MapSettingFragment.this.TAG;
                Pdlog.m3273d(str, "onLocationInitListener is:" + z);
                if (z) {
                    handler = MapSettingFragment.this.handler;
                    i = MapSettingFragment.this.TASK_CHEACK_LOCATION;
                    handler.removeMessages(i);
                    str3 = MapSettingFragment.this.TAG;
                    Pdlog.m3273d(str3, "onLocationInitListener is:" + z + " go to HomeMenuActivity");
                    showTipMsgDialog2 = MapSettingFragment.this.mShowTipDialog;
                    if (showTipMsgDialog2 != null) {
                        showTipMsgDialog2.dismiss();
                        return;
                    }
                    return;
                }
                showTipMsgDialog = MapSettingFragment.this.mShowTipDialog;
                if (showTipMsgDialog != null) {
                    showTipMsgDialog.dismiss();
                }
                int i2 = MapSettingFragment.WhenMappings.$EnumSwitchMapping$1[RobotMapManager.INSTANCE.getLocateCase().ordinal()];
                if (i2 == 1 || i2 == 2) {
                    FragmentActivity activity = MapSettingFragment.this.getActivity();
                    if (activity != null) {
                        Intent intent = new Intent(activity, (Class<?>) CheckLocationActivity.class);
                        intent.putExtra("state", 1);
                        FragmentActivity activity2 = MapSettingFragment.this.getActivity();
                        if (activity2 != null) {
                            activity2.startActivity(intent);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (i2 != 3 && i2 != 4) {
                    str2 = MapSettingFragment.this.TAG;
                    Pdlog.m3274e(str2, "onLocationInitListener  :LocateCase wrong " + RobotMapManager.INSTANCE.getLocateCase());
                    FragmentActivity activity3 = MapSettingFragment.this.getActivity();
                    if (activity3 != null) {
                        AnkoInternals.internalStartActivity(activity3, CheckLocationActivity.class, new Pair[0]);
                        return;
                    }
                    return;
                }
                FragmentActivity activity4 = MapSettingFragment.this.getActivity();
                if (activity4 != null) {
                    Intent intent2 = new Intent(activity4, (Class<?>) LaserCheckLocationActivity.class);
                    intent2.putExtra("state", 1);
                    FragmentActivity activity5 = MapSettingFragment.this.getActivity();
                    if (activity5 != null) {
                        activity5.startActivity(intent2);
                    }
                }
            }
        }, 5000L);
    }

    private final void checkLocationState() {
        if (getContext() == null) {
            return;
        }
        getLocationAndMapModel().getLocationInitStatus();
        getLocationAndMapModel().getCheckLocationInit().observe(this, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.MapSettingFragment$checkLocationState$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                String str;
                CommonDialog locateHintDialog;
                str = MapSettingFragment.this.TAG;
                Pdlog.m3273d(str, "checkLocationInit " + bool);
                if (bool == null || !bool.booleanValue() || (locateHintDialog = MapSettingFragment.this.getLocateHintDialog()) == null) {
                    return;
                }
                locateHintDialog.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MapItemInfo getMapItemInfo$default(MapSettingFragment mapSettingFragment, String str, int i, boolean z, DestinationType destinationType, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        if ((i2 & 8) != 0) {
            destinationType = (DestinationType) null;
        }
        return mapSettingFragment.getMapItemInfo(str, i, z, destinationType);
    }

    private final MapItemInfo getMapItemInfo(String name, int type, boolean isSelect, DestinationType destinationType) {
        return new MapItemInfo(name, type, destinationType, isSelect, false, null, 48, null);
    }

    private final void initView() {
        initRecyclerView();
    }

    private final void initRecyclerView() {
        RecyclerView recyclerView_map_setting = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView_map_setting);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_map_setting, "recyclerView_map_setting");
        recyclerView_map_setting.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerView recyclerView_map_setting2 = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView_map_setting);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_map_setting2, "recyclerView_map_setting");
        recyclerView_map_setting2.setAdapter(this.listAdapter);
        RecyclerView recyclerView_point = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView_point);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_point, "recyclerView_point");
        recyclerView_point.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerView recyclerView_point2 = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView_point);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_point2, "recyclerView_point");
        recyclerView_point2.setAdapter(this.pointAdapter);
    }
}
