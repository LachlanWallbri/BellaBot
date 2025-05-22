package com.pudutech.freeinstall_ui.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.bumblebee.BuildConfig;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.freeinstall_ui.activity.MapSelectUserActivity;
import com.pudutech.freeinstall_ui.adapter.MapSelectAdapter;
import com.pudutech.freeinstall_ui.adapter.MapSelectItem;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.viewmodel.MapSelectViewModel;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapSelectUserActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000I\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\u000eJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/MapSelectUserActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/MapSelectViewModel;", "()V", "TAG", "", "curMap", "Lcom/pudutech/freeinstall_ui/adapter/MapSelectItem;", "mapSelectAdapter", "Lcom/pudutech/freeinstall_ui/adapter/MapSelectAdapter;", "switchMapResultListener", "com/pudutech/freeinstall_ui/activity/MapSelectUserActivity$switchMapResultListener$1", "Lcom/pudutech/freeinstall_ui/activity/MapSelectUserActivity$switchMapResultListener$1;", "MapListView", "", "createObserver", "currentActivityIsDark", "", "initView", "saveInstanceState", "Landroid/os/Bundle;", "killMirsdk", "layoutId", "", "onDestroy", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapSelectUserActivity extends BaseActivity<MapSelectViewModel> {
    private HashMap _$_findViewCache;
    private MapSelectItem curMap;
    private final String TAG = "MapSelectUserActivity";
    private MapSelectAdapter mapSelectAdapter = new MapSelectAdapter();
    private final MapSelectUserActivity$switchMapResultListener$1 switchMapResultListener = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.freeinstall_ui.activity.MapSelectUserActivity$switchMapResultListener$1
        @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
        public void onResult(boolean b, String errorMsg) {
            String str;
            str = MapSelectUserActivity.this.TAG;
            Pdlog.m3273d(str, "switchMapResultListener onResult = " + b + ' ' + errorMsg);
            if (b) {
                MapSelectUserActivity.this.dismissLoadingDialog();
                MapSelectUserActivity.this.setResult(4095);
                MapSelectUserActivity.this.finish();
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineModel.values().length];

        static {
            $EnumSwitchMapping$0[MachineModel.BellaBot.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineModel.RecycleDog.ordinal()] = 2;
            $EnumSwitchMapping$0[MachineModel.Peanut.ordinal()] = 3;
            $EnumSwitchMapping$0[MachineModel.Phoenix.ordinal()] = 4;
        }
    }

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
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

    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity
    public boolean currentActivityIsDark() {
        return true;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_map_select_user;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        String string = getString(C5362R.string.loading_map_wating);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.loading_map_wating)");
        BaseActivity.showLoadingDialog$default(this, string, false, 2, null);
        RobotMapManager.INSTANCE.addSwitchMapResultListener(this.switchMapResultListener);
        ((TextView) _$_findCachedViewById(C5362R.id.tv_user_map)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapSelectUserActivity$initView$1
            /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0069  */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onClick(View view) {
                String str;
                String str2;
                Intent launchIntentForPackage;
                MachineModel robotType = MachineInfoHelper.INSTANCE.getRobotType();
                str = MapSelectUserActivity.this.TAG;
                Pdlog.m3273d(str, "robotType: " + robotType);
                if (robotType != null) {
                    int i = MapSelectUserActivity.WhenMappings.$EnumSwitchMapping$0[robotType.ordinal()];
                    if (i == 1) {
                        str2 = BuildConfig.APPLICATION_ID;
                    } else if (i == 2) {
                        str2 = "com.pudutech.recycle.robot";
                    } else if (i == 3) {
                        str2 = "com.pudutech.robot.peanut";
                    } else if (i == 4) {
                        str2 = "com.pudutech.phoenix";
                    }
                    launchIntentForPackage = MapSelectUserActivity.this.getPackageManager().getLaunchIntentForPackage(str2);
                    if (launchIntentForPackage == null) {
                        MapSelectUserActivity.this.startActivity(launchIntentForPackage);
                        MapSelectUserActivity.this.killMirsdk();
                        Tools.execCommand("am force-stop com.pudutech.robot.peanut", true);
                        System.exit(0);
                        return;
                    }
                    ToastUtils.INSTANCE.showToast("没有本体apk", 0);
                    return;
                }
                str2 = "";
                launchIntentForPackage = MapSelectUserActivity.this.getPackageManager().getLaunchIntentForPackage(str2);
                if (launchIntentForPackage == null) {
                }
            }
        });
        MapListView();
    }

    public final void killMirsdk() {
        Object systemService = getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            Pdlog.m3273d("MapifyHelper", "runningAppProcessInfo = " + runningAppProcessInfo.processName);
            if (Intrinsics.areEqual("com.pudutech.mirsdk", runningAppProcessInfo.processName)) {
                Pdlog.m3273d("MapifyHelper", "kill = " + runningAppProcessInfo.processName + " ; pid = " + runningAppProcessInfo.pid);
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        ((MapSelectViewModel) getMViewModel()).m4340getMapListData();
        MapSelectUserActivity mapSelectUserActivity = this;
        ((MapSelectViewModel) getMViewModel()).getMapListData().observe(mapSelectUserActivity, new Observer<List<MapInfo>>() { // from class: com.pudutech.freeinstall_ui.activity.MapSelectUserActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<MapInfo> mapInfos) {
                MapSelectAdapter mapSelectAdapter;
                MapSelectUserActivity.this.dismissLoadingDialog();
                ArrayList arrayList = new ArrayList();
                arrayList.add(new MapSelectItem(null, null, false));
                Intrinsics.checkExpressionValueIsNotNull(mapInfos, "mapInfos");
                for (MapInfo mapInfo : mapInfos) {
                    StringBuilder sb = new StringBuilder();
                    if (mapInfo == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(mapInfo.getMapRoot());
                    sb.append("/optemap.png");
                    File file = new File(sb.toString());
                    if (!file.exists()) {
                        file = new File(mapInfo.getMapRoot() + "/imgmap.png");
                    }
                    arrayList.add(new MapSelectItem(mapInfo, file, false));
                }
                mapSelectAdapter = MapSelectUserActivity.this.mapSelectAdapter;
                mapSelectAdapter.setNewData(arrayList);
            }
        });
        ((MapSelectViewModel) getMViewModel()).isEdit().observe(mapSelectUserActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.MapSelectUserActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                MapSelectUserActivity.this.dismissLoading();
                if (SpDataUtils.INSTANCE.getCameraType() == CameraType.FACE_CAMERA) {
                    RobotMapManager.INSTANCE.selectCameraScheme(CameraType.FACE_CAMERA);
                }
                MapSelectUserActivity mapSelectUserActivity2 = MapSelectUserActivity.this;
                mapSelectUserActivity2.startActivity(new Intent(mapSelectUserActivity2, (Class<?>) ExpandMapActivity.class));
            }
        });
    }

    private final void MapListView() {
        RecyclerView rlv_map_list = (RecyclerView) _$_findCachedViewById(C5362R.id.rlv_map_list);
        Intrinsics.checkExpressionValueIsNotNull(rlv_map_list, "rlv_map_list");
        rlv_map_list.setAdapter(this.mapSelectAdapter);
        RecyclerView rlv_map_list2 = (RecyclerView) _$_findCachedViewById(C5362R.id.rlv_map_list);
        Intrinsics.checkExpressionValueIsNotNull(rlv_map_list2, "rlv_map_list");
        rlv_map_list2.setLayoutManager(new GridLayoutManager(this, 3));
        this.mapSelectAdapter.setOnItemAddClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapSelectUserActivity$MapListView$1
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
                Intent intent = new Intent(MapSelectUserActivity.this, (Class<?>) CreateMapNoticeActivity.class);
                intent.putExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, 1);
                intent.putExtra("from_edit_map_arguments", true);
                MapSelectUserActivity.this.startActivity(intent);
                AbnormalManager.INSTANCE.addHardWareListener();
            }
        });
        this.mapSelectAdapter.setOnItemEditClick(new Function1<MapSelectItem, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapSelectUserActivity$MapListView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MapSelectItem mapSelectItem) {
                invoke2(mapSelectItem);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MapSelectItem it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                MapInfo mapInfo = it.getMapInfo();
                if (mapInfo != null) {
                    MapSelectUserActivity mapSelectUserActivity = MapSelectUserActivity.this;
                    String string = mapSelectUserActivity.getString(C5362R.string.map_decode);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.map_decode)");
                    mapSelectUserActivity.showLoading(string);
                    ((MapSelectViewModel) MapSelectUserActivity.this.getMViewModel()).jumpMap(mapInfo);
                }
            }
        });
        this.mapSelectAdapter.setOnItemSelectClick(new Function1<MapSelectItem, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapSelectUserActivity$MapListView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MapSelectItem mapSelectItem) {
                invoke2(mapSelectItem);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MapSelectItem isMapSelectItem) {
                MapSelectAdapter mapSelectAdapter;
                MapSelectAdapter mapSelectAdapter2;
                Intrinsics.checkParameterIsNotNull(isMapSelectItem, "isMapSelectItem");
                MapSelectUserActivity.this.curMap = isMapSelectItem;
                mapSelectAdapter = MapSelectUserActivity.this.mapSelectAdapter;
                List<MapSelectItem> data = mapSelectAdapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "mapSelectAdapter.data");
                for (MapSelectItem mapSelectItem : data) {
                    if (mapSelectItem == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.freeinstall_ui.adapter.MapSelectItem");
                    }
                    mapSelectItem.setSelect(Intrinsics.areEqual(isMapSelectItem, mapSelectItem));
                }
                mapSelectAdapter2 = MapSelectUserActivity.this.mapSelectAdapter;
                mapSelectAdapter2.notifyDataSetChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        RobotMapManager.INSTANCE.removeSwitchMapResultListener(this.switchMapResultListener);
    }
}
