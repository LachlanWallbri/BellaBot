package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.LostLocalizationListener;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationLostDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0011H\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u0016\u001a\u00020\u0011H\u0016R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "type", "", "(Landroid/content/Context;Ljava/lang/String;)V", "themeResId", "", "(Landroid/content/Context;I)V", "LOCATE_STATUS", "TAG", "aniView", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoView;", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "mContext", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismiss", "init", "show", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LocationLostDialog extends Dialog {
    private final String LOCATE_STATUS;
    private final String TAG;
    private FaceVideoView aniView;
    private final Function0<Unit> locationLostTouchCancelCallback;
    private Context mContext;
    private String type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocationLostDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LocationLostDialog";
        this.LOCATE_STATUS = "locate_status";
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LocationLostDialog$locationLostTouchCancelCallback$1
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
                str = LocationLostDialog.this.TAG;
                Pdlog.m3273d(str, "locationLostTouchCancelCallback");
                LocationLostDialog.this.dismiss();
            }
        };
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocationLostDialog(Context context, String type) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.TAG = "LocationLostDialog";
        this.LOCATE_STATUS = "locate_status";
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LocationLostDialog$locationLostTouchCancelCallback$1
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
                str = LocationLostDialog.this.TAG;
                Pdlog.m3273d(str, "locationLostTouchCancelCallback");
                LocationLostDialog.this.dismiss();
            }
        };
        this.type = type;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocationLostDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LocationLostDialog";
        this.LOCATE_STATUS = "locate_status";
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LocationLostDialog$locationLostTouchCancelCallback$1
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
                str = LocationLostDialog.this.TAG;
                Pdlog.m3273d(str, "locationLostTouchCancelCallback");
                LocationLostDialog.this.dismiss();
            }
        };
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        build();
    }

    private final void build() {
        String str;
        String str2;
        View inflate = getLayoutInflater().inflate(C5508R.layout.layout_lost_location_dialog, (ViewGroup) null);
        this.aniView = (FaceVideoView) inflate.findViewById(C5508R.id.face_animation_view);
        if (this.type != null) {
            if (RobotMapManager.INSTANCE.getCameraType() == CameraType.FACE_CAMERA) {
                RobotMapManager.INSTANCE.addLocateStatusListener(this.LOCATE_STATUS, new LostLocalizationListener.Stub() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LocationLostDialog$build$1$1
                    @Override // com.pudutech.mirsdk.aidl.LostLocalizationListener
                    public void LostLocalization(LocalizationStatus p0) {
                    }
                });
                FaceVideoView faceVideoView = this.aniView;
                if (faceVideoView != null) {
                    Context context = this.mContext;
                    if (context == null || (str2 = context.getString(C5508R.string.hint_marker_laser_lost)) == null) {
                        str2 = "";
                    }
                    faceVideoView.changeHintContent(str2);
                }
            } else {
                FaceVideoView faceVideoView2 = this.aniView;
                if (faceVideoView2 != null) {
                    Context context2 = this.mContext;
                    if (context2 == null || (str = context2.getString(C5508R.string.pdStr8_11)) == null) {
                        str = "";
                    }
                    faceVideoView2.changeHintContent(str);
                }
            }
        }
        requestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "window.decorView");
        decorView.setSystemUiVisibility(3846);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        setContentView(inflate);
        window.setLayout(-1, -1);
        setCanceledOnTouchOutside(false);
        FaceVideoView faceVideoView3 = this.aniView;
        if (faceVideoView3 != null) {
            faceVideoView3.setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        }
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LocationLostDialog$build$2
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                FaceVideoView faceVideoView4;
                VoicePlayTasks.INSTANCE.playMarkerLostLocation();
                faceVideoView4 = LocationLostDialog.this.aniView;
                if (faceVideoView4 != null) {
                    faceVideoView4.playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                }
            }
        });
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        Pdlog.m3273d(this.TAG, "dismiss");
        if (this.type == null || RobotMapManager.INSTANCE.getCameraType() != CameraType.FACE_CAMERA) {
            return;
        }
        RobotMapManager.INSTANCE.removeLocateStatusListener(this.LOCATE_STATUS);
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
    }
}
