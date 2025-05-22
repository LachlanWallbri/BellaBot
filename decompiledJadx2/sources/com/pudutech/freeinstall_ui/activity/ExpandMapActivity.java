package com.pudutech.freeinstall_ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog;
import com.pudutech.freeinstall_ui.dialog.CountDownLoadingDialog;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.viewmodel.CreateMapViewModel;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.aidl.mapify.MapingModuleListener;
import com.pudutech.mirsdk.aidl.serialize.MapStepType;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpandMapActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\tH\u0016J\u0006\u0010\u0014\u001a\u00020\u000fJ\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0002J\u0012\u0010\u001b\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u000fH\u0002J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u000fH\u0014J\b\u0010\"\u001a\u00020\u000fH\u0002J\u0006\u0010#\u001a\u00020\u000fJ\b\u0010$\u001a\u00020\u000fH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/ExpandMapActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/CreateMapViewModel;", "()V", "cameraPreviewDialog", "Lcom/pudutech/freeinstall_ui/dialog/CameraPreviewDialog;", "countDownLoadingDialog", "Lcom/pudutech/freeinstall_ui/dialog/CountDownLoadingDialog;", "isCurrentClick", "", "isFromEdit", "isShutDown", "singleClickListener", "Landroid/view/View$OnClickListener;", "addLocateListener", "", "addMappingModuleListener", "addMappingSensorListener", "createObserver", "currentActivityIsDark", "dismissExpandLoadingDialog", "dismissPreviewDialog", "inMarker", "initIntent", "intent", "Landroid/content/Intent;", "initListener", "initView", "saveInstanceState", "Landroid/os/Bundle;", "jump", "layoutId", "", "onDestroy", "removeMappingSensorListener", "showExpandLoadingDialog", "showPreviewDialog", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ExpandMapActivity extends BaseActivity<CreateMapViewModel> {
    public static final String FROM_EDIT_MAP_ARGUMENTS = "from_edit_map_arguments";
    public static final String LOCATE_LISTENER = "locate_listener_ExpandMapActivity";
    public static final String RE_INIT = "re_init_ExpandMapActivity";
    public static final String TAG = "ExpandMapActivity";
    private HashMap _$_findViewCache;
    private CameraPreviewDialog cameraPreviewDialog;
    private CountDownLoadingDialog countDownLoadingDialog;
    private boolean isCurrentClick;
    private boolean isFromEdit;
    private boolean isShutDown;
    private final View.OnClickListener singleClickListener = new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.ExpandMapActivity$singleClickListener$1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            boolean z;
            boolean z2;
            if (view != null) {
                int id = view.getId();
                if (id == C5362R.id.tv_back) {
                    z = ExpandMapActivity.this.isFromEdit;
                    if (z) {
                        z2 = ExpandMapActivity.this.isShutDown;
                        if (z2) {
                            ExpandMapActivity expandMapActivity = ExpandMapActivity.this;
                            expandMapActivity.startActivity(new Intent(expandMapActivity, (Class<?>) SelectMapSettingActivity.class).putExtra("from", 5));
                        }
                    }
                    ExpandMapActivity.this.finish();
                    return;
                }
                if (id == C5362R.id.tv_complete) {
                    ExpandMapActivity.this.jump();
                }
            }
        }
    };

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
        return C5362R.layout.activity_expand_map;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        initIntent(intent);
        initListener();
    }

    private final void initListener() {
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_complete), this.singleClickListener, 0L, 2, (Object) null);
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_back), this.singleClickListener, 0L, 2, (Object) null);
        addLocateListener();
        addMappingModuleListener();
    }

    private final void addLocateListener() {
        MirSdkManager.INSTANCE.addLocateListener(LOCATE_LISTENER, new ExpandMapActivity$addLocateListener$1(this));
    }

    private final void addMappingModuleListener() {
        LocateMappingManager.INSTANCE.addMapingModuleListener(RE_INIT, new MapingModuleListener.Stub() { // from class: com.pudutech.freeinstall_ui.activity.ExpandMapActivity$addMappingModuleListener$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.pudutech.mirsdk.aidl.mapify.MapingModuleListener
            public void mapingInitStepResult(MapStepType p0, MappingCoreInitState p1) {
                boolean z;
                Pdlog.m3273d(ExpandMapActivity.TAG, "mapingInitStepResult " + p0 + ' ' + p1);
                z = ExpandMapActivity.this.isCurrentClick;
                if (z) {
                    if (p0 == MapStepType.ReInitModules && p1 == MappingCoreInitState.Success) {
                        ((CreateMapViewModel) ExpandMapActivity.this.getMViewModel()).setExtendMap();
                        return;
                    }
                    if (p0 == MapStepType.InitExtendDrawing && p1 == MappingCoreInitState.Success) {
                        ExpandMapActivity.this.addMappingSensorListener();
                        ((CreateMapViewModel) ExpandMapActivity.this.getMViewModel()).checkBeginMappingMarkerVisible();
                        ExpandMapActivity.this.isCurrentClick = false;
                        return;
                    }
                    ExpandMapActivity.this.dismissLoadingDialog();
                }
            }
        });
    }

    private final void initIntent(Intent intent) {
        this.isFromEdit = intent.getBooleanExtra("from_edit_map_arguments", false);
        Pdlog.m3273d(TAG, "----isFromEdit" + this.isFromEdit);
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
        ((CreateMapViewModel) getMViewModel()).getCheckMarkerLiveData().observe(this, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.ExpandMapActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                Pdlog.m3273d(ExpandMapActivity.TAG, "checkMarkerLiveData " + it);
                ExpandMapActivity.this.dismissLoadingDialog();
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    ExpandMapActivity.this.dismissPreviewDialog();
                    ExpandMapActivity.this.inMarker();
                } else {
                    ExpandMapActivity.this.showPreviewDialog();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void inMarker() {
        Intent intent = new Intent(this, (Class<?>) NewMapActivity.class);
        intent.putExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, 2);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void jump() {
        Pdlog.m3273d(TAG, "jump");
        BaseActivity.showLoadingDialog$default(this, null, false, 1, null);
        this.isCurrentClick = true;
        ((CreateMapViewModel) getMViewModel()).reinitAlgoModules();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPreviewDialog() {
        CameraPreviewDialog cameraPreviewDialog = new CameraPreviewDialog();
        cameraPreviewDialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.ExpandMapActivity$showPreviewDialog$$inlined$apply$lambda$1
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
                Pdlog.m3273d(ExpandMapActivity.TAG, "showPreviewDialog--onBtnClickListener--closeCheck");
                ((CreateMapViewModel) ExpandMapActivity.this.getMViewModel()).cancelMap();
            }
        });
        this.cameraPreviewDialog = cameraPreviewDialog;
        CameraPreviewDialog cameraPreviewDialog2 = this.cameraPreviewDialog;
        if (cameraPreviewDialog2 != null) {
            cameraPreviewDialog2.showDialog(getSupportFragmentManager(), "preview");
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

    public final void showExpandLoadingDialog() {
        Dialog dialog;
        Pdlog.m3273d(TAG, "showExpandLoadingDialog");
        if (this.countDownLoadingDialog == null) {
            this.countDownLoadingDialog = new CountDownLoadingDialog();
        }
        CountDownLoadingDialog countDownLoadingDialog = this.countDownLoadingDialog;
        if (countDownLoadingDialog != null) {
            String string = getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
            countDownLoadingDialog.setTitle(string);
        }
        CountDownLoadingDialog countDownLoadingDialog2 = this.countDownLoadingDialog;
        if (countDownLoadingDialog2 != null) {
            countDownLoadingDialog2.closeVisible(false);
        }
        CountDownLoadingDialog countDownLoadingDialog3 = this.countDownLoadingDialog;
        if (countDownLoadingDialog3 == null || (dialog = countDownLoadingDialog3.getDialog()) == null || !dialog.isShowing()) {
            CountDownLoadingDialog countDownLoadingDialog4 = this.countDownLoadingDialog;
            if ((countDownLoadingDialog4 != null && countDownLoadingDialog4.isAdded()) || isFinishing() || isDestroyed()) {
                return;
            }
            Pdlog.m3273d(TAG, "showExpandLoadingDialog loading");
            CountDownLoadingDialog countDownLoadingDialog5 = this.countDownLoadingDialog;
            if (countDownLoadingDialog5 != null) {
                countDownLoadingDialog5.showDialog(getSupportFragmentManager(), "countDownLoading");
            }
        }
    }

    public final void dismissExpandLoadingDialog() {
        Pdlog.m3273d(TAG, "dismissExpandLoadingDialog");
        CountDownLoadingDialog countDownLoadingDialog = this.countDownLoadingDialog;
        if (countDownLoadingDialog != null) {
            countDownLoadingDialog.dismissDialog();
        }
        this.countDownLoadingDialog = (CountDownLoadingDialog) null;
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
