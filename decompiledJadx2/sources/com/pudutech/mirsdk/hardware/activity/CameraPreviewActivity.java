package com.pudutech.mirsdk.hardware.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.library.C5193R;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: CameraPreviewActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/CameraPreviewActivity;", "Landroid/app/Activity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "startMarkerPreview", "startRGBPreview", "updateImage", "bitmap", "Landroid/graphics/Bitmap;", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CameraPreviewActivity extends Activity {
    private HashMap _$_findViewCache;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        CameraInterface camera;
        super.onCreate(savedInstanceState);
        setContentView(C5193R.layout.hardware_activity_camera_preview);
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface != null && (camera = hardwareInterface.getCamera()) != null) {
            camera.removeMonocularCameraListener("preview_activity");
        }
        startMarkerPreview();
        ((Switch) _$_findCachedViewById(C5193R.id.switch_camera)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.mirsdk.hardware.activity.CameraPreviewActivity$onCreate$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraInterface camera2;
                MachineInfo machineInfo;
                CameraInterface camera3;
                if (z) {
                    HardwareInterface hardwareInterface2 = HardwareConnection.INSTANCE.getInterface();
                    if (hardwareInterface2 != null && (camera3 = hardwareInterface2.getCamera()) != null) {
                        camera3.removeMonocularCameraListener("preview_activity");
                    }
                    CameraPreviewActivity.this.startMarkerPreview();
                    return;
                }
                HardwareInterface hardwareInterface3 = HardwareConnection.INSTANCE.getInterface();
                if (((hardwareInterface3 == null || (machineInfo = hardwareInterface3.getMachineInfo()) == null) ? null : machineInfo.getMonocularDeviceType()) != MachineInfo.MonocularType.NoDevice) {
                    HardwareInterface hardwareInterface4 = HardwareConnection.INSTANCE.getInterface();
                    if (hardwareInterface4 != null && (camera2 = hardwareInterface4.getCamera()) != null) {
                        camera2.removeMarkerCameraListener("preview_activity");
                    }
                    CameraPreviewActivity.this.startRGBPreview();
                }
            }
        });
        Switch switch_camera = (Switch) _$_findCachedViewById(C5193R.id.switch_camera);
        Intrinsics.checkExpressionValueIsNotNull(switch_camera, "switch_camera");
        switch_camera.setChecked(true);
        ((Button) _$_findCachedViewById(C5193R.id.button_get_camera_error)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.CameraPreviewActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextView textView_error_msg = (TextView) CameraPreviewActivity.this._$_findCachedViewById(C5193R.id.textView_error_msg);
                Intrinsics.checkExpressionValueIsNotNull(textView_error_msg, "textView_error_msg");
                HardwareInterface hardwareInterface2 = HardwareConnection.INSTANCE.getInterface();
                textView_error_msg.setText(hardwareInterface2 != null ? hardwareInterface2.getLastError(CollectionsKt.mutableListOf(DeviceType.Camera)) : null);
            }
        });
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        CameraInterface camera;
        CameraInterface camera2;
        super.onDestroy();
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface != null && (camera2 = hardwareInterface.getCamera()) != null) {
            camera2.removeMarkerCameraListener("preview_activity");
        }
        HardwareInterface hardwareInterface2 = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface2 == null || (camera = hardwareInterface2.getCamera()) == null) {
            return;
        }
        camera.removeMonocularCameraListener("preview_activity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateImage(Bitmap bitmap) {
        ((TouchImageView) _$_findCachedViewById(C5193R.id.imageView)).setImageBitmap(bitmap);
        ((TouchImageView) _$_findCachedViewById(C5193R.id.imageView)).postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startMarkerPreview() {
        CameraInterface camera;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
            return;
        }
        camera.addMarkerCameraListener("preview_activity", new CameraPreviewActivity$startMarkerPreview$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRGBPreview() {
        CameraInterface camera;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
            return;
        }
        camera.addMonocularCameraListener("preview_activity", new CameraPreviewActivity$startRGBPreview$1(this));
    }
}
