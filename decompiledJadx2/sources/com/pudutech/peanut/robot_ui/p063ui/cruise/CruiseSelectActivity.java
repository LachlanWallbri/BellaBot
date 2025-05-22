package com.pudutech.peanut.robot_ui.p063ui.cruise;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.MapCruiseLine;
import com.pudutech.mirsdkwrap.lib.map.MapCruisePathLoader;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.AppSettingActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.CuriseSettingActivity;
import com.pudutech.peanut.robot_ui.p063ui.DeliverTaskEditActivity;
import com.pudutech.peanut.robot_ui.p063ui.adapter.CruiseSelectAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.CruiseSelectItem;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.HomeSettingDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.BeeperCallHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.LeaseHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MapView;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseSelectActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012*\u0001\u001e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\b\u0010#\u001a\u00020!H\u0002J\b\u0010$\u001a\u00020!H\u0002J\b\u0010%\u001a\u00020!H\u0002J\u0012\u0010&\u001a\u00020!2\b\u0010'\u001a\u0004\u0018\u00010(H\u0002J\u0012\u0010)\u001a\u00020!2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J1\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020\u00122\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u00182\b\u0010/\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u00100J\u0012\u00101\u001a\u00020!2\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00104\u001a\u00020!H\u0014J\b\u00105\u001a\u00020!H\u0014J\b\u00106\u001a\u00020!H\u0014J\b\u00107\u001a\u00020!H\u0014J\b\u00108\u001a\u00020!H\u0014J\u0010\u00109\u001a\u00020!2\u0006\u0010:\u001a\u00020\u0018H\u0016J\b\u0010;\u001a\u00020!H\u0002J \u0010<\u001a\u00020!2\u0016\u0010=\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002J\b\u0010>\u001a\u00020!H\u0002J\b\u0010?\u001a\u00020!H\u0002J\b\u0010@\u001a\u00020!H\u0002J\b\u0010A\u001a\u00020!H\u0002J\b\u0010B\u001a\u00020!H\u0002J\u0010\u0010C\u001a\u00020!2\u0006\u0010D\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\n\u0012\u0004\u0012\u00020\t\u0018\u0001`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\bj\b\u0012\u0004\u0012\u00020\u0010`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001f¨\u0006E"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseSelectActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "KEY_LAST_SELECT_WAY", "", "TAG", "kotlin.jvm.PlatformType", "allMapData", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/CruiseSelectItem;", "Lkotlin/collections/ArrayList;", "beeperCallHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/BeeperCallHelper;", "cruiseSelectAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/CruiseSelectAdapter;", "currentCruiseLines", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "currentSelectIndex", "", "faceAnimationView", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoView;", "homeSettingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog;", "isFirstStart", "", "isRelease", "isShowLowPowerDialog", "leaseHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/LeaseHelper;", "onHomeSettingDialogClickListener", "com/pudutech/peanut/robot_ui/ui/cruise/CruiseSelectActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseSelectActivity$onHomeSettingDialogClickListener$1;", "bindPresenter", "", "goToCruise", "initCruiseSwitch", "initData", "initView", "jump", "intent", "Landroid/content/Intent;", "jumpAndFinish", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "release", "setNewData", "list", "showSettingDialog", "showSleepAnimation", "showStandbyAnimation", "stopStandby", "unBindPresenter", "updateSelect", "data", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseSelectActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private ArrayList<CruiseSelectItem> allMapData;
    private CruiseSelectAdapter cruiseSelectAdapter;
    private FaceVideoView faceAnimationView;
    private HomeSettingDialog homeSettingDialog;
    private boolean isRelease;
    private boolean isShowLowPowerDialog;
    private final String TAG = getClass().getSimpleName();
    private final String KEY_LAST_SELECT_WAY = "KEY_LAST_SELECT_WAY";
    private boolean isFirstStart = true;
    private final LeaseHelper leaseHelper = new LeaseHelper();
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private int currentSelectIndex = -1;
    private final CruiseSelectActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new OnHomeSettingDialogClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$onHomeSettingDialogClickListener$1
        @Override // com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener
        public void onFunClick(HomeSettingDialog.FunctionType type, Intent intent) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (type == HomeSettingDialog.FunctionType.MUSIC_MODE || type == HomeSettingDialog.FunctionType.CRUISE_MODE || intent == null) {
                return;
            }
            CruiseSelectActivity.this.jumpAndFinish(intent);
        }
    };
    private final ArrayList<MapCruiseLine> currentCruiseLines = new ArrayList<>();

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C5508R.layout.activity_cruise_select);
        initView();
        bindPresenter();
        initData();
        initCruiseSwitch();
        LightPlayManager.INSTANCE.playNormalStatus();
    }

    private final void initCruiseSwitch() {
        Switch cruise_switch = (Switch) _$_findCachedViewById(C5508R.id.cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_switch, "cruise_switch");
        cruise_switch.setChecked(Constans.INSTANCE.getCruiseSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initCruiseSwitch ");
        Switch cruise_switch2 = (Switch) _$_findCachedViewById(C5508R.id.cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_switch2, "cruise_switch");
        sb.append(cruise_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C5508R.id.cruise_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$initCruiseSwitch$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = CruiseSelectActivity.this.TAG;
                Pdlog.m3273d(str2, "curise_mode_switch " + z);
                Constans.INSTANCE.setCruiseSwitch(z);
            }
        });
    }

    private final void initData() {
        RobotMapManager.INSTANCE.getCruisePath(new Function1<MapCruisePathLoader, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$initData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MapCruisePathLoader mapCruisePathLoader) {
                invoke2(mapCruisePathLoader);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final MapCruisePathLoader mapCruisePathLoader) {
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                String str;
                if (CruiseSelectActivity.this.isDestroyed() || CruiseSelectActivity.this.isFinishing()) {
                    return;
                }
                if (mapCruisePathLoader == null) {
                    str = CruiseSelectActivity.this.TAG;
                    Pdlog.m3274e(str, "initData : cruise is null");
                    return;
                }
                arrayList = CruiseSelectActivity.this.currentCruiseLines;
                arrayList.clear();
                arrayList2 = CruiseSelectActivity.this.currentCruiseLines;
                arrayList2.addAll(mapCruisePathLoader.getCruiseLines());
                MapView mapView = (MapView) CruiseSelectActivity.this._$_findCachedViewById(C5508R.id.map_view);
                if (mapView != null) {
                    mapView.post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$initData$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            MapView mapView2 = (MapView) CruiseSelectActivity.this._$_findCachedViewById(C5508R.id.map_view);
                            if (mapView2 != null) {
                                mapView2.setMapData(mapCruisePathLoader.getMaxX(), mapCruisePathLoader.getMaxY(), mapCruisePathLoader.getMinX(), mapCruisePathLoader.getMinY(), mapCruisePathLoader.getMapLines());
                            }
                        }
                    });
                }
                arrayList3 = CruiseSelectActivity.this.currentCruiseLines;
                ArrayList arrayList4 = arrayList3;
                ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
                Iterator it = arrayList4.iterator();
                while (it.hasNext()) {
                    arrayList5.add(new CruiseSelectItem((MapCruiseLine) it.next(), false, 2, null));
                }
                CruiseSelectActivity.this.setNewData(arrayList5);
            }
        });
    }

    private final void initView() {
        ImageView ivBack = (ImageView) _$_findCachedViewById(C5508R.id.ivBack);
        Intrinsics.checkExpressionValueIsNotNull(ivBack, "ivBack");
        ViewExtKt.onSingleClick(ivBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$initView$1
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                CruiseSelectActivity.this.jumpAndFinish(null);
            }
        });
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        ((TextView) _$_findCachedViewById(C5508R.id.btn_start)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$initView$2
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                CruiseSelectActivity.this.goToCruise();
            }
        });
        RecyclerView cruise_ways_recycler_view = (RecyclerView) _$_findCachedViewById(C5508R.id.cruise_ways_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(cruise_ways_recycler_view, "cruise_ways_recycler_view");
        CruiseSelectActivity cruiseSelectActivity = this;
        cruise_ways_recycler_view.setLayoutManager(new LinearLayoutManager(cruiseSelectActivity));
        this.cruiseSelectAdapter = new CruiseSelectAdapter(cruiseSelectActivity);
        CruiseSelectAdapter cruiseSelectAdapter = this.cruiseSelectAdapter;
        if (cruiseSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseSelectAdapter");
        }
        cruiseSelectAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C5508R.id.cruise_ways_recycler_view));
        ((ImageView) _$_findCachedViewById(C5508R.id.setting_info)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$initView$3
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                CruiseSelectActivity.this.showSettingDialog();
            }
        });
        CruiseSelectAdapter cruiseSelectAdapter2 = this.cruiseSelectAdapter;
        if (cruiseSelectAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseSelectAdapter");
        }
        cruiseSelectAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$initView$4
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                String str2;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                str = CruiseSelectActivity.this.TAG;
                Pdlog.m3273d(str, "cruiseSelectAdapter setOnItemChildClickListener " + position);
                List<?> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.CruiseSelectItem");
                    }
                    ((CruiseSelectItem) obj).setSelect(i == position);
                    i = i2;
                }
                CruiseSelectActivity cruiseSelectActivity2 = CruiseSelectActivity.this;
                CruiseSelectActivity cruiseSelectActivity3 = cruiseSelectActivity2;
                str2 = cruiseSelectActivity2.KEY_LAST_SELECT_WAY;
                SpUtils.set((Context) cruiseSelectActivity3, str2, position);
                adapter.notifyDataSetChanged();
                CruiseSelectActivity.this.currentSelectIndex = position;
                CruiseSelectActivity cruiseSelectActivity4 = CruiseSelectActivity.this;
                Object obj2 = adapter.getData().get(position);
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.CruiseSelectItem");
                }
                cruiseSelectActivity4.updateSelect((CruiseSelectItem) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goToCruise() {
        CruiseSelectItem cruiseSelectItem;
        MapCruiseLine mapModel;
        String id;
        if (this.isRelease) {
            Pdlog.m3274e(this.TAG, "goToCruise failed isRelease ");
            return;
        }
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        if ((power != null ? power.intValue() : 0) > 2) {
            int i = this.currentSelectIndex;
            int i2 = -1;
            if (i == -1) {
                String string = getString(C5508R.string.pdStr3_7);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr3_7)");
                MyBaseActivity.showTipDialog$default(this, string, null, null, null, 14, null);
                return;
            }
            ArrayList<CruiseSelectItem> arrayList = this.allMapData;
            if (arrayList != null && (cruiseSelectItem = arrayList.get(i)) != null && (mapModel = cruiseSelectItem.getMapModel()) != null && (id = mapModel.getId()) != null) {
                i2 = Integer.parseInt(id);
            }
            if (i2 < 0) {
                String string2 = getString(C5508R.string.pdStr3_7);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr3_7)");
                MyBaseActivity.showTipDialog$default(this, string2, null, null, null, 14, null);
                return;
            } else {
                Constans.INSTANCE.setCruiseId(i2);
                Intent intent = new Intent(this, (Class<?>) CruiseActivity.class);
                intent.putExtra("CRUISE_ID_KEY", i2);
                DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
                jumpAndFinish(intent);
                return;
            }
        }
        Pdlog.m3273d(this.TAG, "batteryPresenter check Allowed for move is false");
        String string3 = getString(C5508R.string.pdStr2_19);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr2_19)");
        MyBaseActivity.showTipDialog$default(this, string3, null, null, null, 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNewData(ArrayList<CruiseSelectItem> list) {
        Pdlog.m3273d(this.TAG, "setNewData = " + list);
        if (!list.isEmpty()) {
            int i = SpUtils.get((Context) this, this.KEY_LAST_SELECT_WAY, 0);
            if (i > list.size() - 1) {
                i = 0;
            }
            list.get(i).setSelect(true);
            this.currentSelectIndex = i;
            CruiseSelectItem cruiseSelectItem = list.get(i);
            Intrinsics.checkExpressionValueIsNotNull(cruiseSelectItem, "list[index]");
            updateSelect(cruiseSelectItem);
            this.allMapData = list;
        }
        CruiseSelectAdapter cruiseSelectAdapter = this.cruiseSelectAdapter;
        if (cruiseSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseSelectAdapter");
        }
        cruiseSelectAdapter.setNewData(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelect(final CruiseSelectItem data) {
        MapView mapView = (MapView) _$_findCachedViewById(C5508R.id.map_view);
        if (mapView != null) {
            mapView.post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$updateSelect$1
                @Override // java.lang.Runnable
                public final void run() {
                    MapView mapView2 = (MapView) CruiseSelectActivity.this._$_findCachedViewById(C5508R.id.map_view);
                    if (mapView2 != null) {
                        mapView2.setSelectLinesData(data.getMapModel().getLines());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSettingDialog() {
        Pdlog.m3273d(this.TAG, "showSettingDialog");
        if (this.homeSettingDialog == null) {
            this.homeSettingDialog = new HomeSettingDialog(this);
            HomeSettingDialog homeSettingDialog = this.homeSettingDialog;
            if (homeSettingDialog == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog.setOnHomeSettingDialogClickListener(this.onHomeSettingDialogClickListener);
        }
        HomeSettingDialog homeSettingDialog2 = this.homeSettingDialog;
        if (homeSettingDialog2 == null) {
            Intrinsics.throwNpe();
        }
        homeSettingDialog2.show();
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        this.leaseHelper.setOnLeaseExpireForceCloseCallback(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$bindPresenter$1
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
                String str;
                str = CruiseSelectActivity.this.TAG;
                Pdlog.m3273d(str, "onLeaseExpireForceCloseCallback");
                CruiseSelectActivity cruiseSelectActivity = CruiseSelectActivity.this;
                cruiseSelectActivity.jumpAndFinish(new Intent(cruiseSelectActivity, (Class<?>) AppSettingActivity.class));
            }
        });
        this.leaseHelper.bind(this);
        LeaseHelper leaseHelper = this.leaseHelper;
        Application application = getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application, "this.application");
        leaseHelper.startCheck(application);
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        if ((power != null ? power.intValue() : 0) < 10) {
            BatteryBaseActivity.notifyBatteryInfo$default(this, 1, null, false, null, 14, null);
        }
        TextView tvSetting = (TextView) _$_findCachedViewById(C5508R.id.tvSetting);
        Intrinsics.checkExpressionValueIsNotNull(tvSetting, "tvSetting");
        ViewExtKt.onSingleClick(tvSetting, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSelectActivity$bindPresenter$2
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
                str = CruiseSelectActivity.this.TAG;
                Pdlog.m3273d(str, "go to CuriseSettingActivity");
                CruiseSelectActivity cruiseSelectActivity = CruiseSelectActivity.this;
                cruiseSelectActivity.jump(new Intent(cruiseSelectActivity, (Class<?>) CuriseSettingActivity.class));
            }
        });
        VoicePlayTasks.INSTANCE.stop();
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        this.beeperCallHelper.unbind();
        this.leaseHelper.unbind();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        if (intent != null) {
            startActivity(intent);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(Intent intent) {
        Pdlog.m3273d(this.TAG, "jump");
        release();
        startActivity(intent);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(this);
        }
    }

    private final void release() {
        this.isRelease = true;
        unBindPresenter();
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state != 1) {
            if (state == 2) {
                if (i != null) {
                    ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
                    return;
                }
                return;
            } else {
                if (state == 3 || state != 4 || model == null || !isCharging) {
                    return;
                }
                PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
                return;
            }
        }
        if (this.isShowLowPowerDialog) {
            return;
        }
        Pdlog.m3273d(this.TAG, "showBatteryEvent ShowLowPowerDialog");
        this.isShowLowPowerDialog = true;
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr2_17);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_17)");
        MyBaseActivity.showTipDialog$default(this, string, null, null, null, 14, null);
        if (BatteryInfoManager.INSTANCE.getPower() != null) {
            Integer power = BatteryInfoManager.INSTANCE.getPower();
            if ((power != null ? power.intValue() : 0) > 5) {
                VoicePlayTasks.INSTANCE.playLowPower5_10();
                return;
            }
            Integer power2 = BatteryInfoManager.INSTANCE.getPower();
            if ((power2 != null ? power2.intValue() : 0) > 2) {
                VoicePlayTasks.INSTANCE.playLowPower2_5();
                return;
            }
            Integer power3 = BatteryInfoManager.INSTANCE.getPower();
            if ((power3 != null ? power3.intValue() : 0) < 2) {
                VoicePlayTasks.INSTANCE.playLowPower0_2();
            }
        }
    }

    private final void showSleepAnimation() {
        Pdlog.m3273d(this.TAG, "showSleepAnimation");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getSleep());
        setScreenDark();
    }

    private final void showStandbyAnimation() {
        Pdlog.m3273d(this.TAG, "showStandbyAnimation");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getStandby());
    }

    private final void stopStandby() {
        Pdlog.m3273d(this.TAG, "stopStandby");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.stopPlay();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isRelease = false;
        Pdlog.m3273d(this.TAG, "onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }
}
