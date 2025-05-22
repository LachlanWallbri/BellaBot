package com.pudutech.mirsdk.hardware.cameralib.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.ImageView;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.cameralib.C5139R;
import com.pudutech.mirsdk.hardware.cameralib.CameraLib;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraPreviewActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0016\u0010\u0018\u001a\u00020\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\b\u0010\u001a\u001a\u00020\nH\u0002J\b\u0010\u001b\u001a\u00020\nH\u0002J\u001a\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R;\u0010\u0005\u001a,\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R2\u0010\u0011\u001a&\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012j\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\u0013`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/cameralib/activity/CameraPreviewActivity;", "Landroid/app/Activity;", "()V", "TAG", "", "cameraFrameDistributor", "Lkotlin/Function6;", "Landroid/os/ParcelFileDescriptor;", "", "", "", "getCameraFrameDistributor", "()Lkotlin/jvm/functions/Function6;", "cameraListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData;", "cameraPreName", "viewMap", "Ljava/util/HashMap;", "Landroid/widget/ImageView;", "Lkotlin/collections/HashMap;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setPreCallback", "listener", "startCamera", "stopCamera", "updateImage", InternalConstant.DTYPE_IMAGE, "bmp", "Landroid/graphics/Bitmap;", "MarkerCamera_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CameraPreviewActivity extends Activity {
    private HashMap _$_findViewCache;
    private final String TAG = "CameraPreviewActivity";
    private HashMap<Integer, ImageView> viewMap = new HashMap<>();
    private final String cameraPreName = "camera_preview";
    private final ThreadSafeListener<IMarkerCameraData> cameraListeners = new ThreadSafeListener<>();
    private final Function6<ParcelFileDescriptor, Integer, Integer, Integer, Integer, Long, Unit> cameraFrameDistributor = new Function6<ParcelFileDescriptor, Integer, Integer, Integer, Integer, Long, Unit>() { // from class: com.pudutech.mirsdk.hardware.cameralib.activity.CameraPreviewActivity$cameraFrameDistributor$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(6);
        }

        @Override // kotlin.jvm.functions.Function6
        public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3, Integer num4, Long l) {
            invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue(), num4.intValue(), l.longValue());
            return Unit.INSTANCE;
        }

        public final void invoke(final ParcelFileDescriptor parcelFileDescriptor, final int i, final int i2, final int i3, final int i4, final long j) {
            ThreadSafeListener threadSafeListener;
            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
            threadSafeListener = CameraPreviewActivity.this.cameraListeners;
            threadSafeListener.notify(new Function2<IMarkerCameraData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.cameralib.activity.CameraPreviewActivity$cameraFrameDistributor$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraData iMarkerCameraData, String str) {
                    invoke2(iMarkerCameraData, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraData it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onFrame(parcelFileDescriptor, i, i2, i3, i4, j);
                }
            });
        }
    };

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

    public final Function6<ParcelFileDescriptor, Integer, Integer, Integer, Integer, Long, Unit> getCameraFrameDistributor() {
        return this.cameraFrameDistributor;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5139R.layout.activity_camera_preview);
        startCamera();
    }

    private final void startCamera() {
        Pdlog.m3276v(this.TAG, "startCamera...");
        setPreCallback(this.cameraListeners);
        CameraLib.INSTANCE.openCamera(this.cameraFrameDistributor, "wide_angle");
    }

    private final void stopCamera() {
        CameraLib.INSTANCE.closeCamera("wide_angle");
        this.cameraListeners.remove(this.cameraPreName);
    }

    private final void setPreCallback(ThreadSafeListener<IMarkerCameraData> listener) {
        listener.add(this.cameraPreName, new CameraPreviewActivity$setPreCallback$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateImage(ImageView image, Bitmap bmp) {
        if (image != null) {
            image.setImageBitmap(bmp);
        }
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("imagew:");
        sb.append(image != null ? Integer.valueOf(image.getWidth()) : null);
        sb.append(",height:");
        sb.append(image != null ? Integer.valueOf(image.getHeight()) : null);
        sb.append(",bmp.w=");
        sb.append(bmp.getWidth());
        sb.append(", bmp.h=");
        sb.append(bmp.getHeight());
        objArr[0] = sb.toString();
        Pdlog.m3273d("test-imatge", objArr);
        if (image != null) {
            image.postInvalidate();
        }
    }
}
