package com.pudutech.freeinstall_ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.lifecycle.Observer;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog;
import com.pudutech.freeinstall_ui.dialog.IdentityFailDialog;
import com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.viewmodel.NewMapViewModel;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.aidl.mapify.LocalizationListener;
import com.pudutech.mirsdk.aidl.mapify.MapingModuleListener;
import com.pudutech.mirsdk.aidl.serialize.MapStepType;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.MapData;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import com.pudutech.opengl_draw.layer.PointLayer;
import com.pudutech.opengl_draw.layer.RobotLayer;
import com.pudutech.opengl_draw.layer.ViewControlLayer;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: NewMapActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r*\u0001\u0011\u0018\u0000 >2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001>B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020$H\u0016J\b\u0010)\u001a\u00020\rH\u0016J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020$H\u0016J\b\u0010,\u001a\u00020$H\u0002J\b\u0010-\u001a\u00020$H\u0002J\b\u0010.\u001a\u00020$H\u0002J\b\u0010/\u001a\u00020$H\u0002J\u0012\u00100\u001a\u00020$2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\b\u00103\u001a\u00020\u0014H\u0016J\b\u00104\u001a\u00020$H\u0002J\b\u00105\u001a\u00020$H\u0014J\b\u00106\u001a\u00020$H\u0014J\b\u00107\u001a\u00020$H\u0002J\b\u00108\u001a\u00020$H\u0002J\b\u00109\u001a\u00020$H\u0002J\u0010\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u00020\u0005H\u0016J\b\u0010<\u001a\u00020$H\u0002J\b\u0010=\u001a\u00020$H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/NewMapActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/NewMapViewModel;", "()V", "TAG", "", "cameraPreviewDialog", "Lcom/pudutech/freeinstall_ui/dialog/CameraPreviewDialog;", "destination", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "firstVector3d", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "isControl", "", "isDestroy", "isMapValid", "localizationer", "com/pudutech/freeinstall_ui/activity/NewMapActivity$localizationer$1", "Lcom/pudutech/freeinstall_ui/activity/NewMapActivity$localizationer$1;", "mapHeight", "", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "mapName", "mapWith", "occupancyOneListener", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer$OccupancyOneListener;", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointLayer;", "robotLayer", "Lcom/pudutech/opengl_draw/layer/RobotLayer;", "typeCreateMap", "vector3d", "viewControlLayer", "Lcom/pudutech/opengl_draw/layer/ViewControlLayer;", "addReInitModules", "", "checkMapValid", "mapData", "Lcom/pudutech/mirsdk/mircore/coreparcel/MapData;", "createObserver", "currentActivityIsDark", "destroy", "dismissLoading", "dismissPreviewDialog", "initIntent", "initListener", "initMap", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "locate", "onDestroy", "onPause", "setBtnEnable", "setFirstPoint", "showInputDialog", "showLoading", "message", "showNoticeDialog", "showPreviewDialog", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class NewMapActivity extends BaseActivity<NewMapViewModel> {
    public static final String LOCALIZATION_LISTENER = "localization_Listener";
    public static final String RE_INIT = "re_init_NewMapActivity";
    private HashMap _$_findViewCache;
    private CameraPreviewDialog cameraPreviewDialog;
    private Destination destination;
    private Vector3d firstVector3d;
    private boolean isControl;
    private boolean isDestroy;
    private boolean isMapValid;
    private int mapHeight;
    private OccupancyGridLayer mapLayer;
    private int mapWith;
    private PointLayer pointLayer;
    private RobotLayer robotLayer;
    private Vector3d vector3d;
    private ViewControlLayer viewControlLayer;
    private final String TAG = "NewMapActivity";
    private String mapName = "";
    private int typeCreateMap = 1;
    private final NewMapActivity$localizationer$1 localizationer = new LocalizationListener.Stub() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$localizationer$1
        @Override // com.pudutech.mirsdk.aidl.mapify.LocalizationListener
        public void updateRobotPosition(Vector3d p0) {
            RobotLayer robotLayer;
            Vector3d vector3d;
            Vector3d vector3d2;
            String str;
            boolean z;
            Vector3d vector3d3;
            boolean z2;
            Vector3d vector3d4;
            Vector3d vector3d5;
            if (p0 == null) {
                return;
            }
            NewMapActivity.this.vector3d = p0;
            Utils.Companion companion = Utils.INSTANCE;
            robotLayer = NewMapActivity.this.robotLayer;
            vector3d = NewMapActivity.this.vector3d;
            companion.updateRobotPosition(robotLayer, vector3d);
            vector3d2 = NewMapActivity.this.firstVector3d;
            if (vector3d2 != null) {
                NewMapActivity.this.setFirstPoint();
            }
            str = NewMapActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("#########");
            z = NewMapActivity.this.isMapValid;
            sb.append(z);
            Pdlog.m3273d(str, sb.toString());
            vector3d3 = NewMapActivity.this.firstVector3d;
            if (vector3d3 != null) {
                z2 = NewMapActivity.this.isMapValid;
                if (z2) {
                    return;
                }
                Utils.Companion companion2 = Utils.INSTANCE;
                vector3d4 = NewMapActivity.this.vector3d;
                if (vector3d4 == null) {
                    Intrinsics.throwNpe();
                }
                vector3d5 = NewMapActivity.this.firstVector3d;
                if (vector3d5 == null) {
                    Intrinsics.throwNpe();
                }
                if (companion2.calculateDistance(vector3d4, vector3d5, 250L)) {
                    NewMapActivity.this.isMapValid = true;
                    NewMapActivity.this.setBtnEnable();
                }
            }
        }
    };
    private final OccupancyGridLayer.OccupancyOneListener occupancyOneListener = new OccupancyGridLayer.OccupancyOneListener() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$occupancyOneListener$1
        @Override // com.pudutech.opengl_draw.layer.OccupancyGridLayer.OccupancyOneListener
        public final void onSuccess() {
            String str;
            boolean z;
            str = NewMapActivity.this.TAG;
            Pdlog.m3273d(str, "地图绘制成功");
            z = NewMapActivity.this.isControl;
            if (z) {
                return;
            }
            NewMapActivity.this.locate();
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineModel.values().length];

        static {
            $EnumSwitchMapping$0[MachineModel.RecycleDog.ordinal()] = 1;
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

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void dismissLoading() {
    }

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void showLoading(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_new_map;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        initIntent();
        initMap();
        initListener();
        showNoticeDialog();
    }

    private final void initIntent() {
        this.typeCreateMap = getIntent().getIntExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, 1);
        if (this.typeCreateMap == 2) {
            this.destination = SpDataUtils.INSTANCE.getMapStartPoint();
        }
    }

    private final void initListener() {
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$initListener$$inlined$singleClick$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                int i;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    z = this.isMapValid;
                    if (z) {
                        i = this.typeCreateMap;
                        if (i != 2) {
                            this.showInputDialog();
                            return;
                        } else {
                            BaseActivity.showLoadingDialog$default(this, null, false, 1, null);
                            ((NewMapViewModel) this.getMViewModel()).checkFinishMappingMarkerVisible();
                            return;
                        }
                    }
                    ToastUtils toastUtils = ToastUtils.INSTANCE;
                    String string = this.getString(C5362R.string.please_push_robot);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.please_push_robot)");
                    toastUtils.showShortToast(string);
                }
            }
        });
        final TextView textView2 = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$initListener$$inlined$singleClick$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView2) > j || (textView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView2, currentTimeMillis);
                    BaseActivity.showLoadingDialog$default(this, null, false, 1, null);
                    ((NewMapViewModel) this.getMViewModel()).reInit();
                }
            }
        });
        final ImageView imageView = (ImageView) _$_findCachedViewById(C5362R.id.iv_locate);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$initListener$$inlined$singleClick$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(imageView) > j || (imageView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(imageView, currentTimeMillis);
                    this.locate();
                }
            }
        });
        LocateMappingManager.INSTANCE.addLocalizationListener(LOCALIZATION_LISTENER, this.localizationer);
        addReInitModules();
    }

    private final void addReInitModules() {
        LocateMappingManager.INSTANCE.addMapingModuleListener(RE_INIT, new MapingModuleListener.Stub() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$addReInitModules$1
            @Override // com.pudutech.mirsdk.aidl.mapify.MapingModuleListener
            public void mapingInitStepResult(MapStepType p0, MappingCoreInitState p1) {
                String str;
                if (p0 == MapStepType.ReInitModules && p1 == MappingCoreInitState.Success) {
                    str = NewMapActivity.this.TAG;
                    Pdlog.m3273d(str, "initModuleStepResult " + p0);
                    NewMapActivity.this.dismissLoadingDialog();
                    NewMapActivity.this.finish();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void locate() {
        if (this.firstVector3d != null) {
            this.isControl = false;
            VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
            Intrinsics.checkExpressionValueIsNotNull(visualizationView, "visualizationView");
            visualizationView.getCamera().fullPreview(Utils.INSTANCE.getScreenScale(this.mapWith, this.mapHeight));
        }
    }

    private final void initMap() {
        this.viewControlLayer = new ViewControlLayer(this, (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView));
        this.mapLayer = new OccupancyGridLayer();
        this.robotLayer = new RobotLayer();
        this.pointLayer = new PointLayer();
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        if (visualizationView != null) {
            visualizationView.onCreate(new CopyOnWriteArrayList<>(), CommonExtKt.getScreenWidth(this) * 2, CommonExtKt.getScreenHeight(this) * 2);
            visualizationView.addLayer(this.mapLayer);
            visualizationView.addLayer(this.pointLayer);
            visualizationView.addLayer(this.robotLayer);
            visualizationView.addLayer(this.viewControlLayer);
            visualizationView.onStart();
        }
        ViewControlLayer viewControlLayer = this.viewControlLayer;
        if (viewControlLayer != null) {
            viewControlLayer.setControlListener(new ViewControlLayer.ControlListener() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$initMap$2
                @Override // com.pudutech.opengl_draw.layer.ViewControlLayer.ControlListener
                public final void control(boolean z) {
                    if (z) {
                        NewMapActivity.this.isControl = z;
                    }
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        NewMapActivity newMapActivity = this;
        ((NewMapViewModel) getMViewModel()).getCheckMarkerLiveData().observe(newMapActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                String str;
                str = NewMapActivity.this.TAG;
                Pdlog.m3273d(str, "checkMarkerLiveData " + bool);
                if (bool.booleanValue()) {
                    NewMapActivity.this.dismissPreviewDialog();
                    BaseActivity.showLoadingDialog$default(NewMapActivity.this, null, false, 1, null);
                } else {
                    NewMapActivity.this.dismissLoadingDialog();
                    NewMapActivity.this.showPreviewDialog();
                }
            }
        });
        ((NewMapViewModel) getMViewModel()).getMapProcessLiveData().observe(newMapActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                String str;
                int i;
                String str2;
                Destination destination;
                Destination destination2;
                str = NewMapActivity.this.TAG;
                Pdlog.m3273d(str, "mapProcessLiveData " + it);
                NewMapActivity.this.dismissLoadingDialog();
                ((NewMapViewModel) NewMapActivity.this.getMViewModel()).cancel();
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    i = NewMapActivity.this.typeCreateMap;
                    if (i == 2) {
                        Integer mapLevel = SpDataUtils.INSTANCE.getMapLevel();
                        int intValue = mapLevel != null ? 1 + mapLevel.intValue() : 1;
                        NewMapViewModel newMapViewModel = (NewMapViewModel) NewMapActivity.this.getMViewModel();
                        String mapName = SpDataUtils.INSTANCE.getMapName();
                        if (mapName == null) {
                            mapName = "";
                        }
                        destination2 = NewMapActivity.this.destination;
                        newMapViewModel.saveOptimizedMap(mapName, destination2, intValue);
                    } else {
                        NewMapViewModel newMapViewModel2 = (NewMapViewModel) NewMapActivity.this.getMViewModel();
                        str2 = NewMapActivity.this.mapName;
                        destination = NewMapActivity.this.destination;
                        newMapViewModel2.saveOptimizedMap(str2, destination, 1);
                    }
                    NewMapActivity newMapActivity2 = NewMapActivity.this;
                    newMapActivity2.startActivity(new Intent(newMapActivity2, (Class<?>) MapSuccessActivity.class));
                    NewMapActivity.this.finish();
                    return;
                }
                ToastUtils.INSTANCE.showShortToast("地图优化超时");
            }
        });
        ((NewMapViewModel) getMViewModel()).getGetRealMapLiveData().observe(newMapActivity, new Observer<MapData>() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$createObserver$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MapData mapData) {
                OccupancyGridLayer occupancyGridLayer;
                OccupancyGridLayer.OccupancyOneListener occupancyOneListener;
                Vector3d vector3d;
                boolean z;
                if (mapData == null) {
                    return;
                }
                NewMapActivity.this.mapWith = mapData.getSize_x();
                NewMapActivity.this.mapHeight = mapData.getSize_y();
                Utils.Companion companion = Utils.INSTANCE;
                occupancyGridLayer = NewMapActivity.this.mapLayer;
                occupancyOneListener = NewMapActivity.this.occupancyOneListener;
                companion.updateMap(occupancyGridLayer, mapData, occupancyOneListener);
                vector3d = NewMapActivity.this.firstVector3d;
                if (vector3d == null) {
                    z = NewMapActivity.this.isMapValid;
                    if (z) {
                        return;
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C45711(mapData, null), 3, null);
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: NewMapActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.activity.NewMapActivity$createObserver$3$1", m3970f = "NewMapActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.freeinstall_ui.activity.NewMapActivity$createObserver$3$1 */
            /* loaded from: classes3.dex */
            public static final class C45711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MapData $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5231p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C45711(MapData mapData, Continuation continuation) {
                    super(2, continuation);
                    this.$it = mapData;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45711 c45711 = new C45711(this.$it, completion);
                    c45711.f5231p$ = (CoroutineScope) obj;
                    return c45711;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5231p$;
                    NewMapActivity.this.checkMapValid(this.$it);
                    return Unit.INSTANCE;
                }
            }
        });
        ((NewMapViewModel) getMViewModel()).getMemoryLimitLiveData().observe(newMapActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$createObserver$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                String str;
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    str = NewMapActivity.this.TAG;
                    Pdlog.m3273d(str, "memoryLimitLiveData");
                    NewMapActivity.this.mapName = "pdmap_" + System.currentTimeMillis();
                    BaseActivity.showLoadingDialog$default(NewMapActivity.this, null, false, 1, null);
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C45721(null), 3, null);
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: NewMapActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.activity.NewMapActivity$createObserver$4$1", m3970f = "NewMapActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.freeinstall_ui.activity.NewMapActivity$createObserver$4$1 */
            /* loaded from: classes3.dex */
            public static final class C45721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5232p$;

                C45721(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45721 c45721 = new C45721(completion);
                    c45721.f5232p$ = (CoroutineScope) obj;
                    return c45721;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5232p$;
                    ((NewMapViewModel) NewMapActivity.this.getMViewModel()).finishMap();
                    return Unit.INSTANCE;
                }
            }
        });
        ((NewMapViewModel) getMViewModel()).startMapping();
        ((NewMapViewModel) getMViewModel()).getRealMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkMapValid(MapData mapData) {
        Pdlog.m3273d(this.TAG, "checkMapValid");
        int size_x = mapData.getSize_x();
        int size_y = mapData.getSize_y();
        byte[] data = mapData.getData();
        if (size_x == 0 || size_y == 0) {
            return;
        }
        for (byte b : data) {
            if (b == ((byte) 255)) {
                if (this.typeCreateMap == 2) {
                    this.isMapValid = true;
                    this.firstVector3d = this.vector3d;
                    setBtnEnable();
                } else {
                    this.firstVector3d = this.vector3d;
                }
                setFirstPoint();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFirstPoint() {
        Pdlog.m3273d(this.TAG, "setFirstPoint");
        if (this.destination == null) {
            this.destination = new Destination();
            Destination destination = this.destination;
            if (destination != null) {
                String string = getString(C5362R.string.start_point);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.start_point)");
                destination.setName(string);
            }
            Destination destination2 = this.destination;
            if (destination2 != null) {
                Vector3d vector3d = this.firstVector3d;
                if (vector3d == null) {
                    Intrinsics.throwNpe();
                }
                destination2.setVector(vector3d);
            }
            Destination destination3 = this.destination;
            if (destination3 != null) {
                destination3.setMode(Constants.POINT_TYPE_START);
            }
        }
        PointLayer pointLayer = this.pointLayer;
        if (pointLayer != null) {
            Utils.Companion companion = Utils.INSTANCE;
            Destination destination4 = this.destination;
            if (destination4 == null) {
                Intrinsics.throwNpe();
            }
            Transform destinationToTransform = companion.destinationToTransform(destination4);
            Drawable drawable = getDrawable(C5362R.drawable.icon_start_point);
            Bitmap bitmap$default = drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null;
            Destination destination5 = this.destination;
            if (destination5 == null) {
                Intrinsics.throwNpe();
            }
            pointLayer.update(destinationToTransform, bitmap$default, destination5.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBtnEnable() {
        runOnUiThread(new Runnable() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$setBtnEnable$1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z;
                z = NewMapActivity.this.isMapValid;
                if (z) {
                    ((TextView) NewMapActivity.this._$_findCachedViewById(C5362R.id.tv_next_step)).setBackgroundResource(C5362R.drawable.rectangle_btn_blu_8);
                } else {
                    ((TextView) NewMapActivity.this._$_findCachedViewById(C5362R.id.tv_next_step)).setBackgroundResource(C5362R.drawable.rectangle_btn_gray_8);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showInputDialog() {
        final InputFreeInstallDialog inputFreeInstallDialog = new InputFreeInstallDialog(this);
        String string = getString(C5362R.string.please_input_name);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.please_input_name)");
        inputFreeInstallDialog.setTitle(string);
        String string2 = getString(C5362R.string.sure);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.sure)");
        inputFreeInstallDialog.setBtnText(string2);
        inputFreeInstallDialog.setMaxLength(24);
        inputFreeInstallDialog.setOnInputResult(new Function1<String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$showInputDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!(it.length() == 0)) {
                    NewMapActivity.this.mapName = it;
                    inputFreeInstallDialog.dismiss();
                    BaseActivity.showLoadingDialog$default(NewMapActivity.this, null, false, 1, null);
                    ((NewMapViewModel) NewMapActivity.this.getMViewModel()).checkFinishMappingMarkerVisible();
                    return;
                }
                ToastUtils toastUtils = ToastUtils.INSTANCE;
                String string3 = NewMapActivity.this.getString(C5362R.string.please_input_name);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.please_input_name)");
                toastUtils.showShortToast(string3);
            }
        });
        inputFreeInstallDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPreviewDialog() {
        Dialog dialog;
        Pdlog.m3273d(this.TAG, "showPreviewDialog");
        CameraPreviewDialog cameraPreviewDialog = this.cameraPreviewDialog;
        if (cameraPreviewDialog == null || (dialog = cameraPreviewDialog.getDialog()) == null || !dialog.isShowing()) {
            CameraPreviewDialog cameraPreviewDialog2 = new CameraPreviewDialog();
            cameraPreviewDialog2.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$showPreviewDialog$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str;
                    str = NewMapActivity.this.TAG;
                    Pdlog.m3273d(str, "showPreviewDialog--onBtnClickListener--closeCheck");
                    ((NewMapViewModel) NewMapActivity.this.getMViewModel()).closeCheck();
                }
            });
            this.cameraPreviewDialog = cameraPreviewDialog2;
            CameraPreviewDialog cameraPreviewDialog3 = this.cameraPreviewDialog;
            if (cameraPreviewDialog3 != null) {
                cameraPreviewDialog3.showDialog(getSupportFragmentManager(), "preview");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dismissPreviewDialog() {
        CameraPreviewDialog cameraPreviewDialog = this.cameraPreviewDialog;
        if (cameraPreviewDialog != null) {
            cameraPreviewDialog.dismissDialog();
        }
        this.cameraPreviewDialog = (CameraPreviewDialog) null;
    }

    private final void showNoticeDialog() {
        final IdentityFailDialog identityFailDialog = new IdentityFailDialog();
        identityFailDialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.NewMapActivity$showNoticeDialog$identityFailDialog$1$1
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
                IdentityFailDialog.this.dismissDialog();
            }
        });
        identityFailDialog.showDialog(getSupportFragmentManager(), "Notice");
        MachineModel robotType = MachineInfoHelper.INSTANCE.getRobotType();
        if (robotType != null && WhenMappings.$EnumSwitchMapping$0[robotType.ordinal()] == 1) {
            identityFailDialog.setBitmapResource(Integer.valueOf(C5362R.drawable.pic_push_map_recycle_dog));
        } else {
            identityFailDialog.setBitmapResource(Integer.valueOf(C5362R.drawable.pic_push_map));
        }
        identityFailDialog.setContent(getString(C5362R.string.push_map_notice));
        identityFailDialog.setButton(getString(C5362R.string.sure));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (isFinishing()) {
            destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        destroy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void destroy() {
        if (this.isDestroy) {
            return;
        }
        Pdlog.m3273d(this.TAG, "destroy");
        ((NewMapViewModel) getMViewModel()).cancel();
        ((NewMapViewModel) getMViewModel()).closeCheck();
        dismissPreviewDialog();
        LocateMappingManager.INSTANCE.removeLocalizationListener(LOCALIZATION_LISTENER);
        LocateMappingManager.INSTANCE.removeReinitModuleListener(RE_INIT);
        this.pointLayer = (PointLayer) null;
        this.viewControlLayer = (ViewControlLayer) null;
        this.robotLayer = (RobotLayer) null;
        this.mapLayer = (OccupancyGridLayer) null;
        this.isDestroy = true;
    }
}
