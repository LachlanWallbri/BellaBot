package com.pudutech.freeinstall_ui.dialog;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: CameraPreviewDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\u001a\u0010\u0010\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0001H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\nH\u0016J\b\u0010\u0019\u001a\u00020\nH\u0002J\b\u0010\u001a\u001a\u00020\nH\u0002J\b\u0010\u001b\u001a\u00020\nH\u0002J\b\u0010\u001c\u001a\u00020\nH\u0002J\b\u0010\u001d\u001a\u00020\nH\u0002J\b\u0010\u001e\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006 "}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/CameraPreviewDialog;", "Lcom/pudutech/freeinstall_ui/dialog/BaseDialog;", "()V", "cameraType", "Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "closeable", "", "isCameraOpen", "onBtnClickListener", "Lkotlin/Function0;", "", "getOnBtnClickListener", "()Lkotlin/jvm/functions/Function0;", "setOnBtnClickListener", "(Lkotlin/jvm/functions/Function0;)V", "addCameraListener", "bindView", "rootView", "Landroid/view/View;", "dialog", "getDialogLayoutId", "", "onDismiss", "Landroid/content/DialogInterface;", "onResume", "removeCameraListener", "setCameraOpen", "setFaceCameraListener", "setMarkerCameraListener", "setNoticeText", "setView", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CameraPreviewDialog extends BaseDialog {
    public static final String FACE_CAMERA_LISTENER = "face_camera_listener";
    public static final String MARKER_CAMERA_LISTENER = "marker_camera_listener";
    public static final String TAG = "CameraPreviewDialog";
    private HashMap _$_findViewCache;
    private boolean isCameraOpen;
    private Function0<Unit> onBtnClickListener;
    private boolean closeable = true;
    private CameraType cameraType = CameraType.MARKER_CAMERA;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineModel.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[MachineModel.BellaBot.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineModel.RecycleDog.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[CameraType.values().length];
            $EnumSwitchMapping$1[CameraType.MARKER_CAMERA.ordinal()] = 1;
        }
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
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

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final Function0<Unit> getOnBtnClickListener() {
        return this.onBtnClickListener;
    }

    public final void setOnBtnClickListener(Function0<Unit> function0) {
        this.onBtnClickListener = function0;
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public int getDialogLayoutId() {
        return C5362R.layout.dialog_camera_preview;
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setFullScreen();
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        this.cameraType = SpDataUtils.INSTANCE.getCameraType();
        Pdlog.m3273d(TAG, "bindView -- cameraType " + this.cameraType);
        setView();
        setNoticeText();
        setCameraOpen();
        ((ImageView) _$_findCachedViewById(C5362R.id.btn_open)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog$bindView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                boolean z2;
                CameraPreviewDialog cameraPreviewDialog = CameraPreviewDialog.this;
                z = cameraPreviewDialog.isCameraOpen;
                cameraPreviewDialog.isCameraOpen = !z;
                StringBuilder sb = new StringBuilder();
                sb.append("btn_open---");
                z2 = CameraPreviewDialog.this.isCameraOpen;
                sb.append(z2);
                Pdlog.m3273d(CameraPreviewDialog.TAG, sb.toString());
                CameraPreviewDialog.this.setCameraOpen();
            }
        });
    }

    private final void setView() {
        Pdlog.m3273d(TAG, "setView---closeable---" + this.closeable);
        if (this.closeable) {
            ImageView iv_close = (ImageView) _$_findCachedViewById(C5362R.id.iv_close);
            Intrinsics.checkExpressionValueIsNotNull(iv_close, "iv_close");
            iv_close.setVisibility(0);
            ((ImageView) _$_findCachedViewById(C5362R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog$setView$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CameraPreviewDialog.this.dismissDialog();
                    Function0<Unit> onBtnClickListener = CameraPreviewDialog.this.getOnBtnClickListener();
                    if (onBtnClickListener != null) {
                        onBtnClickListener.invoke();
                    }
                }
            });
        } else {
            ImageView iv_close2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_close);
            Intrinsics.checkExpressionValueIsNotNull(iv_close2, "iv_close");
            iv_close2.setVisibility(8);
        }
        if (((ImageView) _$_findCachedViewById(C5362R.id.iv_guid)) != null) {
            MachineModel robotType = MachineInfoHelper.INSTANCE.getRobotType();
            if (robotType != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[robotType.ordinal()];
                if (i == 1) {
                    ((ImageView) _$_findCachedViewById(C5362R.id.iv_guid)).setImageResource(C5362R.drawable.pic_marker_guid);
                    return;
                } else if (i == 2) {
                    ((ImageView) _$_findCachedViewById(C5362R.id.iv_guid)).setImageResource(C5362R.drawable.pic_marker_guid_recycle_dog);
                    return;
                }
            }
            ((ImageView) _$_findCachedViewById(C5362R.id.iv_guid)).setImageResource(C5362R.drawable.pic_marker_guid);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCameraOpen() {
        Pdlog.m3273d(TAG, "setCameraOpen -- cameraType " + this.cameraType + " -- isCameraOpen " + this.isCameraOpen);
        if (this.isCameraOpen) {
            addCameraListener();
            ImageView btn_open = (ImageView) _$_findCachedViewById(C5362R.id.btn_open);
            Intrinsics.checkExpressionValueIsNotNull(btn_open, "btn_open");
            Sdk27PropertiesKt.setImageResource(btn_open, C5362R.drawable.icon_button_open);
            ImageView iv_guid = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
            Intrinsics.checkExpressionValueIsNotNull(iv_guid, "iv_guid");
            iv_guid.setVisibility(4);
            ImageView iv_camera_pic = (ImageView) _$_findCachedViewById(C5362R.id.iv_camera_pic);
            Intrinsics.checkExpressionValueIsNotNull(iv_camera_pic, "iv_camera_pic");
            iv_camera_pic.setVisibility(0);
            LottieAnimationView lav_guid_two = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid_two);
            Intrinsics.checkExpressionValueIsNotNull(lav_guid_two, "lav_guid_two");
            lav_guid_two.setVisibility(8);
            if (SpDataUtils.INSTANCE.getCameraType() == CameraType.MARKER_CAMERA) {
                ImageView iv_camera_box = (ImageView) _$_findCachedViewById(C5362R.id.iv_camera_box);
                Intrinsics.checkExpressionValueIsNotNull(iv_camera_box, "iv_camera_box");
                iv_camera_box.setVisibility(8);
                return;
            } else {
                ImageView iv_camera_box2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_camera_box);
                Intrinsics.checkExpressionValueIsNotNull(iv_camera_box2, "iv_camera_box");
                iv_camera_box2.setVisibility(0);
                return;
            }
        }
        removeCameraListener();
        ImageView iv_camera_pic2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_camera_pic);
        Intrinsics.checkExpressionValueIsNotNull(iv_camera_pic2, "iv_camera_pic");
        iv_camera_pic2.setVisibility(8);
        ImageView btn_open2 = (ImageView) _$_findCachedViewById(C5362R.id.btn_open);
        Intrinsics.checkExpressionValueIsNotNull(btn_open2, "btn_open");
        Sdk27PropertiesKt.setImageResource(btn_open2, C5362R.drawable.icon_button_close);
        if (SpDataUtils.INSTANCE.getCameraType() == CameraType.MARKER_CAMERA) {
            ImageView iv_guid2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
            Intrinsics.checkExpressionValueIsNotNull(iv_guid2, "iv_guid");
            iv_guid2.setVisibility(0);
            LottieAnimationView lav_guid_two2 = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid_two);
            Intrinsics.checkExpressionValueIsNotNull(lav_guid_two2, "lav_guid_two");
            lav_guid_two2.setVisibility(8);
            return;
        }
        ImageView iv_guid3 = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
        Intrinsics.checkExpressionValueIsNotNull(iv_guid3, "iv_guid");
        iv_guid3.setVisibility(0);
        LottieAnimationView lav_guid_two3 = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid_two);
        Intrinsics.checkExpressionValueIsNotNull(lav_guid_two3, "lav_guid_two");
        lav_guid_two3.setVisibility(0);
    }

    private final void setNoticeText() {
        if (WhenMappings.$EnumSwitchMapping$1[SpDataUtils.INSTANCE.getCameraType().ordinal()] == 1) {
            TextView tv_content = (TextView) _$_findCachedViewById(C5362R.id.tv_content);
            Intrinsics.checkExpressionValueIsNotNull(tv_content, "tv_content");
            tv_content.setText(getString(C5362R.string.create_map_notice_marker));
            TextView tv_open_camera = (TextView) _$_findCachedViewById(C5362R.id.tv_open_camera);
            Intrinsics.checkExpressionValueIsNotNull(tv_open_camera, "tv_open_camera");
            tv_open_camera.setText(getString(C5362R.string.top_camera_preview));
            return;
        }
        TextView tv_content2 = (TextView) _$_findCachedViewById(C5362R.id.tv_content);
        Intrinsics.checkExpressionValueIsNotNull(tv_content2, "tv_content");
        tv_content2.setText(getString(C5362R.string.create_map_notice_face));
        TextView tv_open_camera2 = (TextView) _$_findCachedViewById(C5362R.id.tv_open_camera);
        Intrinsics.checkExpressionValueIsNotNull(tv_open_camera2, "tv_open_camera");
        tv_open_camera2.setText(getString(C5362R.string.face_camera_preview));
    }

    private final void addCameraListener() {
        Pdlog.m3273d(TAG, "addCameraListener " + SpDataUtils.INSTANCE.getCameraType());
        if (SpDataUtils.INSTANCE.getCameraType() == CameraType.FACE_CAMERA) {
            setFaceCameraListener();
        } else {
            setMarkerCameraListener();
        }
    }

    private final void setFaceCameraListener() {
        RobotMapManager.INSTANCE.addFaceCameraListener(FACE_CAMERA_LISTENER, new CameraPreviewDialog$setFaceCameraListener$1(this));
    }

    private final void setMarkerCameraListener() {
        RobotMapManager.INSTANCE.addMarkerCameraListener(MARKER_CAMERA_LISTENER, new CameraPreviewDialog$setMarkerCameraListener$1(this));
    }

    private final void removeCameraListener() {
        RobotMapManager.INSTANCE.removeFaceCameraListener(FACE_CAMERA_LISTENER);
        RobotMapManager.INSTANCE.removeMarkerCameraListener(MARKER_CAMERA_LISTENER);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        super.onDismiss(dialog);
        removeCameraListener();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid_two);
        if (lottieAnimationView == null || !lottieAnimationView.isAnimating()) {
            return;
        }
        lottieAnimationView.cancelAnimation();
    }
}
