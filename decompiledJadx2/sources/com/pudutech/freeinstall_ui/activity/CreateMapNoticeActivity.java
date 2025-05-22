package com.pudutech.freeinstall_ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.CameraSupportType;
import com.pudutech.freeinstall_ui.utils.CommonDialogUtils;
import com.pudutech.freeinstall_ui.utils.ConfigDataHelper;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.NodeProgressBar;
import com.pudutech.freeinstall_ui.viewmodel.CreateMapViewModel;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.aidl.mapify.MapingModuleListener;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.MapStepType;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: CreateMapNoticeActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 *2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\u0012\u0010\u001b\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0010H\u0002J\b\u0010\u001f\u001a\u00020\tH\u0016J\b\u0010 \u001a\u00020\u0010H\u0014J\u0012\u0010!\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\"\u001a\u00020\u0010H\u0002J\b\u0010#\u001a\u00020\u0010H\u0002J\b\u0010$\u001a\u00020\u0010H\u0002J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010&\u001a\u00020\u0010H\u0002J\u0012\u0010'\u001a\u00020\u00102\b\b\u0002\u0010(\u001a\u00020\tH\u0002J\b\u0010)\u001a\u00020\u0010H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/CreateMapNoticeActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/CreateMapViewModel;", "()V", "cameraPreviewDialog", "Lcom/pudutech/freeinstall_ui/dialog/CameraPreviewDialog;", "cameraType", "Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "createMapType", "", "isCurrentClick", "", "isFromEdit", "singleClickListener", "Landroid/view/View$OnClickListener;", "addLocateListener", "", "addMappingModuleListener", "addMappingSensorListener", "createObserver", "currentActivityIsDark", "dismissPreviewDialog", "inMarker", "initIntent", "intent", "Landroid/content/Intent;", "initListener", "initView", "saveInstanceState", "Landroid/os/Bundle;", "jump", "layoutId", "onDestroy", "onNewIntent", "removeMappingSensorListener", "setBackVisible", "setCameraSelectView", "setCameraType", "setView", "showPowerDialog", "time", "showPreviewDialog", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CreateMapNoticeActivity extends BaseActivity<CreateMapViewModel> {
    public static final String FROM_EDIT_MAP_ARGUMENTS = "from_edit_map_arguments";
    public static final String LOCATE_LISTENER = "locate_listener_CreateMapNoticeActivity";
    public static final String RE_INIT = "re_init_CreateMapNoticeActivity";
    public static final String TAG = "CreateMapNoticeActivity";
    private HashMap _$_findViewCache;
    private CameraPreviewDialog cameraPreviewDialog;
    private CameraType cameraType;
    private boolean isCurrentClick;
    private boolean isFromEdit;
    private int createMapType = 1;
    private final View.OnClickListener singleClickListener = new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.CreateMapNoticeActivity$singleClickListener$1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (view != null) {
                int id = view.getId();
                if (id == C5362R.id.tv_back) {
                    CreateMapNoticeActivity.this.finish();
                    return;
                }
                if (id == C5362R.id.cl_top_marker) {
                    Integer power = BatteryInfoManager.INSTANCE.getPower();
                    if ((power != null ? power.intValue() : 0) < 35) {
                        ((CreateMapViewModel) CreateMapNoticeActivity.this.getMViewModel()).getChargeTime();
                        return;
                    } else {
                        if (ConfigDataHelper.INSTANCE.getCameraSupportType() != CameraSupportType.CAMERA_FACE) {
                            CreateMapNoticeActivity.this.setCameraType(CameraType.MARKER_CAMERA);
                            CreateMapNoticeActivity.this.setCameraSelectView();
                            CreateMapNoticeActivity.this.jump();
                            return;
                        }
                        ToastUtils.INSTANCE.showShortToast("当前机器不支持天花板定位");
                        return;
                    }
                }
                if (id == C5362R.id.cl_wall_marker) {
                    Integer power2 = BatteryInfoManager.INSTANCE.getPower();
                    if ((power2 != null ? power2.intValue() : 0) < 35) {
                        ((CreateMapViewModel) CreateMapNoticeActivity.this.getMViewModel()).getChargeTime();
                    } else {
                        if (ConfigDataHelper.INSTANCE.getCameraSupportType() != CameraSupportType.CAMERA_MARKER) {
                            CreateMapNoticeActivity.this.setCameraType(CameraType.FACE_CAMERA);
                            CreateMapNoticeActivity.this.setCameraSelectView();
                            CreateMapNoticeActivity.this.jump();
                            return;
                        }
                        ToastUtils.INSTANCE.showShortToast("当前机器不支墙壁定位");
                    }
                }
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineModel.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[MachineModel.BellaBot.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineModel.RecycleDog.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[CameraType.values().length];
            $EnumSwitchMapping$1[CameraType.FACE_CAMERA.ordinal()] = 1;
            $EnumSwitchMapping$1[CameraType.MARKER_CAMERA.ordinal()] = 2;
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
        return C5362R.layout.activity_create_map;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        initIntent(intent);
        setView();
        initListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCameraType(CameraType cameraType) {
        this.cameraType = cameraType;
        SpDataUtils.INSTANCE.saveCameraType(cameraType);
        RobotMapManager.INSTANCE.selectCameraScheme(cameraType);
    }

    private final void initListener() {
        ExtandsKt.singleClick$default((ConstraintLayout) _$_findCachedViewById(C5362R.id.cl_top_marker), this.singleClickListener, 0L, 2, (Object) null);
        ExtandsKt.singleClick$default((ConstraintLayout) _$_findCachedViewById(C5362R.id.cl_wall_marker), this.singleClickListener, 0L, 2, (Object) null);
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_back), this.singleClickListener, 0L, 2, (Object) null);
        addLocateListener();
        addMappingModuleListener();
    }

    private final void addLocateListener() {
        MirSdkManager.INSTANCE.addLocateListener(LOCATE_LISTENER, new CreateMapNoticeActivity$addLocateListener$1(this));
    }

    private final void addMappingModuleListener() {
        LocateMappingManager.INSTANCE.addMapingModuleListener(RE_INIT, new MapingModuleListener.Stub() { // from class: com.pudutech.freeinstall_ui.activity.CreateMapNoticeActivity$addMappingModuleListener$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.pudutech.mirsdk.aidl.mapify.MapingModuleListener
            public void mapingInitStepResult(MapStepType p0, MappingCoreInitState p1) {
                int i;
                boolean z;
                int i2;
                StringBuilder sb = new StringBuilder();
                sb.append("mapingInitStepResult ");
                sb.append(p0);
                sb.append(' ');
                sb.append(p1);
                sb.append(' ');
                i = CreateMapNoticeActivity.this.createMapType;
                sb.append(i);
                Pdlog.m3273d(CreateMapNoticeActivity.TAG, sb.toString());
                z = CreateMapNoticeActivity.this.isCurrentClick;
                if (z) {
                    if (p0 == MapStepType.ReInitModules && p1 == MappingCoreInitState.Success) {
                        i2 = CreateMapNoticeActivity.this.createMapType;
                        if (i2 == 1 || i2 == 3) {
                            CreateMapNoticeActivity.this.addMappingSensorListener();
                            ((CreateMapViewModel) CreateMapNoticeActivity.this.getMViewModel()).checkBeginMappingMarkerVisible();
                            CreateMapNoticeActivity.this.isCurrentClick = false;
                            return;
                        }
                        CreateMapNoticeActivity.this.dismissLoadingDialog();
                        return;
                    }
                    CreateMapNoticeActivity.this.dismissLoadingDialog();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) {
            intent = new Intent();
        }
        initIntent(intent);
    }

    private final void initIntent(Intent intent) {
        this.createMapType = intent.getIntExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, 1);
        this.isFromEdit = intent.getBooleanExtra("from_edit_map_arguments", false);
        Pdlog.m3273d(TAG, "----createMapType" + this.createMapType);
        if (this.createMapType == 1 && !this.isFromEdit) {
            AbnormalManager.INSTANCE.addHardWareListener();
        }
        setBackVisible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addMappingSensorListener() {
        Pdlog.m3273d(TAG, "addMappingSensorListener --- ");
        LocateMappingManager.INSTANCE.addMappingSensorListener();
    }

    private final void removeMappingSensorListener() {
        Pdlog.m3273d(TAG, "removeMappingSensorListener --- ");
        LocateMappingManager.INSTANCE.removeMappingSensorListener();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        CreateMapNoticeActivity createMapNoticeActivity = this;
        ((CreateMapViewModel) getMViewModel()).getCheckMarkerLiveData().observe(createMapNoticeActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.CreateMapNoticeActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                Pdlog.m3273d(CreateMapNoticeActivity.TAG, "checkMarkerLiveData " + it);
                CreateMapNoticeActivity.this.dismissLoadingDialog();
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    CreateMapNoticeActivity.this.dismissPreviewDialog();
                    CreateMapNoticeActivity.this.inMarker();
                } else {
                    CreateMapNoticeActivity.this.showPreviewDialog();
                }
            }
        });
        ((CreateMapViewModel) getMViewModel()).getChargeTimeLiveData().observe(createMapNoticeActivity, new Observer<Integer>() { // from class: com.pudutech.freeinstall_ui.activity.CreateMapNoticeActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer it) {
                CreateMapNoticeActivity createMapNoticeActivity2 = CreateMapNoticeActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                createMapNoticeActivity2.showPowerDialog(it.intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void inMarker() {
        Intent intent = new Intent(this, (Class<?>) NewMapActivity.class);
        intent.putExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, this.createMapType);
        startActivity(intent);
    }

    private final void setView() {
        ((NodeProgressBar) _$_findCachedViewById(C5362R.id.npb_step)).setNodeList(Utils.INSTANCE.getNodeData(1));
        MachineModel robotType = MachineInfoHelper.INSTANCE.getRobotType();
        if (robotType != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[robotType.ordinal()];
            if (i == 1) {
                ((ImageView) _$_findCachedViewById(C5362R.id.iv_guid_top)).setImageResource(C5362R.drawable.icon_mark_top);
            } else if (i == 2) {
                ((ImageView) _$_findCachedViewById(C5362R.id.iv_guid_top)).setImageResource(C5362R.drawable.icon_mark_top_recycle_dog);
            }
            setCameraSelectView();
            SpDataUtils.INSTANCE.clear();
        }
        ((ImageView) _$_findCachedViewById(C5362R.id.iv_guid_top)).setImageResource(C5362R.drawable.icon_mark_top);
        setCameraSelectView();
        SpDataUtils.INSTANCE.clear();
    }

    private final void setBackVisible() {
        if (this.isFromEdit) {
            TextView tv_back = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
            Intrinsics.checkExpressionValueIsNotNull(tv_back, "tv_back");
            tv_back.setVisibility(0);
        } else {
            TextView tv_back2 = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
            Intrinsics.checkExpressionValueIsNotNull(tv_back2, "tv_back");
            tv_back2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCameraSelectView() {
        CameraType cameraType = this.cameraType;
        if (cameraType != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[cameraType.ordinal()];
            if (i == 1) {
                ((ImageView) _$_findCachedViewById(C5362R.id.iv_top_select)).setImageResource(C5362R.drawable.marker_unselect);
                ((ImageView) _$_findCachedViewById(C5362R.id.iv_wall_select)).setImageResource(C5362R.drawable.marker_select);
                return;
            } else if (i == 2) {
                ((ImageView) _$_findCachedViewById(C5362R.id.iv_top_select)).setImageResource(C5362R.drawable.marker_select);
                ((ImageView) _$_findCachedViewById(C5362R.id.iv_wall_select)).setImageResource(C5362R.drawable.marker_unselect);
                return;
            }
        }
        ((ImageView) _$_findCachedViewById(C5362R.id.iv_top_select)).setImageResource(C5362R.drawable.marker_unselect);
        ((ImageView) _$_findCachedViewById(C5362R.id.iv_wall_select)).setImageResource(C5362R.drawable.marker_unselect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void jump() {
        Pdlog.m3273d(TAG, "jump " + this.createMapType);
        BaseActivity.showLoadingDialog$default(this, null, false, 1, null);
        this.isCurrentClick = true;
        LocateMappingManager.INSTANCE.clearOldData();
        ((CreateMapViewModel) getMViewModel()).reinitAlgoModules();
    }

    static /* synthetic */ void showPowerDialog$default(CreateMapNoticeActivity createMapNoticeActivity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 30;
        }
        createMapNoticeActivity.showPowerDialog(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPowerDialog(int time) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C5362R.string.power_lower_notice);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.power_lower_notice)");
        Object[] objArr = {Integer.valueOf(time)};
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        CommonDialogUtils.Companion.showSingleCommonDialog$default(CommonDialogUtils.INSTANCE, this, null, format, null, null, 26, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPreviewDialog() {
        Dialog dialog;
        Pdlog.m3273d(TAG, "showPreviewDialog");
        CameraPreviewDialog cameraPreviewDialog = this.cameraPreviewDialog;
        if (cameraPreviewDialog == null || (dialog = cameraPreviewDialog.getDialog()) == null || !dialog.isShowing()) {
            CameraPreviewDialog cameraPreviewDialog2 = new CameraPreviewDialog();
            cameraPreviewDialog2.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.CreateMapNoticeActivity$showPreviewDialog$$inlined$apply$lambda$1
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
                    Pdlog.m3273d(CreateMapNoticeActivity.TAG, "showPreviewDialog--onBtnClickListener--closeCheck");
                    ((CreateMapViewModel) CreateMapNoticeActivity.this.getMViewModel()).cancelMap();
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

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(TAG, "onDestroy");
        ((CreateMapViewModel) getMViewModel()).closeCheck();
        LocateMappingManager.INSTANCE.removeReinitModuleListener(RE_INIT);
        MirSdkManager.INSTANCE.removeLocateListener(LOCATE_LISTENER);
        removeMappingSensorListener();
        dismissPreviewDialog();
    }
}
