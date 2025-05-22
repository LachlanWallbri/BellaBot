package com.iflytek.aiui.data.video;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.aiui.data.video.AbstractC3554b;
import com.iflytek.aiui.data.video.PreviewImpl;
import com.iflytek.aiui.pro.AbstractC3617h0;
import com.iflytek.aiui.pro.C3651y0;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes4.dex */
class Camera1 extends AbstractC3554b {
    private static final HashMap<Integer, String> FLASH_MODES;
    static final int INVALID_CAMERA_ID = -1;
    private static final String TAG = "Camera1";
    private final AtomicBoolean isPictureCaptureInProgress;
    private boolean mAutoFocus;
    Camera mCamera;
    private int mCameraId;
    private final Camera.CameraInfo mCameraInfo;
    private Camera.Parameters mCameraParameters;
    private C3555c mCameraWindow;
    private int mDisplayOrientation;
    private int mFacing;
    private int mFlash;
    private final C3558f mPictureSizes;
    private final C3558f mPreviewSizes;
    private boolean mShowingPreview;

    /* renamed from: com.iflytek.aiui.data.video.Camera1$a */
    /* loaded from: classes4.dex */
    class C3540a implements PreviewImpl.Callback {
        C3540a() {
        }

        @Override // com.iflytek.aiui.data.video.PreviewImpl.Callback
        public void onSurfaceChanged() {
            Camera1 camera1 = Camera1.this;
            if (camera1.mCamera != null) {
                camera1.setUpPreview();
                Camera1.this.adjustCameraParameters();
            }
        }
    }

    /* renamed from: com.iflytek.aiui.data.video.Camera1$b */
    /* loaded from: classes4.dex */
    class C3541b implements Camera.AutoFocusCallback {
        C3541b() {
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, Camera camera) {
            Camera1.this.takePictureInternal();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.data.video.Camera1$c */
    /* loaded from: classes4.dex */
    public class C3542c implements Camera.PictureCallback {
        C3542c() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            Camera1.this.isPictureCaptureInProgress.set(false);
            camera.cancelAutoFocus();
            camera.startPreview();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.data.video.Camera1$d */
    /* loaded from: classes4.dex */
    public class C3543d implements Camera.PreviewCallback {

        /* renamed from: a */
        byte[] f2167a = null;

        /* renamed from: b */
        int f2168b = 0;

        /* renamed from: c */
        int f2169c = 0;

        /* renamed from: d */
        long f2170d;

        C3543d() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            int i;
            if (this.f2170d <= 0 || System.currentTimeMillis() - this.f2170d < 1000) {
                if (this.f2170d == 0) {
                    this.f2170d = System.currentTimeMillis();
                }
                i = this.f2168b + 1;
            } else {
                this.f2169c = (int) (1000.0f / (((float) (System.currentTimeMillis() - this.f2170d)) / (this.f2168b * 1.0f)));
                C3651y0.m1627i(Camera1.TAG, "on onImageAvailable fps :" + this.f2169c);
                this.f2170d = 0L;
                i = 0;
            }
            this.f2168b = i;
            Camera.Size pictureSize = Camera1.this.mCameraParameters.getPictureSize();
            int i2 = pictureSize.height;
            int i3 = pictureSize.width;
            if (this.f2167a == null) {
                this.f2167a = new byte[((i3 * i2) * ImageFormat.getBitsPerPixel(17)) / 8];
            }
            Bundle bundle = new Bundle();
            bundle.putString("data_type", InternalConstant.DTYPE_IMAGE);
            bundle.putString("format", "6");
            bundle.putString("fps", "25");
            bundle.putString("width", String.valueOf(i3));
            bundle.putString("height", String.valueOf(i2));
            bundle.putString("stride", String.valueOf(i3));
            ((AbstractC3617h0) Camera1.this).mCaptureListener.mo863d(bArr, this.f2167a.length, bundle);
            camera.addCallbackBuffer(bArr);
        }
    }

    static {
        HashMap<Integer, String> hashMap = new HashMap<>();
        FLASH_MODES = hashMap;
        hashMap.put(0, "off");
        hashMap.put(1, DebugKt.DEBUG_PROPERTY_VALUE_ON);
        hashMap.put(2, "torch");
        hashMap.put(3, "auto");
        hashMap.put(4, "red-eye");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Camera1(PreviewImpl previewImpl, Context context, AbstractC3617h0.a aVar, int i) {
        super(previewImpl == null ? CameraCompat.createPreview(context, null) : previewImpl, aVar);
        this.isPictureCaptureInProgress = new AtomicBoolean(false);
        this.mCameraInfo = new Camera.CameraInfo();
        this.mPreviewSizes = new C3558f();
        this.mPictureSizes = new C3558f();
        if (previewImpl == null) {
            C3555c c3555c = new C3555c(context, this.mPreview.getView());
            this.mCameraWindow = c3555c;
            c3555c.m827b();
        }
        this.mCameraId = i;
        this.mPreview.setCallback(new C3540a());
    }

    private int calcCameraRotation(int i) {
        Camera.CameraInfo cameraInfo = this.mCameraInfo;
        if (cameraInfo.facing == 1) {
            return (cameraInfo.orientation + i) % 360;
        }
        return ((this.mCameraInfo.orientation + i) + (isLandscape(i) ? 180 : 0)) % 360;
    }

    private int calcDisplayOrientation(int i) {
        Camera.CameraInfo cameraInfo = this.mCameraInfo;
        int i2 = cameraInfo.facing;
        int i3 = cameraInfo.orientation;
        return i2 == 1 ? (360 - ((i3 + i) % 360)) % 360 : ((i3 - i) + 360) % 360;
    }

    private C3553a chooseAspectRatio() {
        Iterator<C3553a> it = this.mPreviewSizes.m834c().iterator();
        C3553a c3553a = null;
        while (it.hasNext()) {
            c3553a = it.next();
            if (c3553a.equals(AbstractC3554b.DEFAULT_ASPECT_RATIO)) {
                break;
            }
        }
        return c3553a;
    }

    private int chooseCamera() {
        this.mCameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            C3651y0.m1627i(TAG, "find camera id " + i);
            Camera.getCameraInfo(i, this.mCameraInfo);
            if (this.mCameraInfo.facing == this.mFacing) {
                this.mCameraId = i;
                return i;
            }
            this.mCameraId = 0;
        }
        return this.mCameraId;
    }

    private C3557e chooseOptimalSize(SortedSet<C3557e> sortedSet) {
        C3557e first = sortedSet.first();
        int i = this.maxImagePixel;
        for (C3557e c3557e : sortedSet) {
            if (c3557e.m830b() * c3557e.m831c() <= i) {
                first = c3557e;
            }
        }
        return first;
    }

    private boolean isLandscape(int i) {
        return i == 90 || i == 270;
    }

    private boolean openCamera() {
        if (this.mCamera != null) {
            releaseCamera();
        }
        C3651y0.m1627i(TAG, "open camera id " + this.mCameraId);
        try {
            Camera open = Camera.open(this.mCameraId);
            this.mCamera = open;
            this.mCameraParameters = open.getParameters();
            this.mPreviewSizes.m833b();
            for (Camera.Size size : this.mCameraParameters.getSupportedPreviewSizes()) {
                this.mPreviewSizes.m832a(new C3557e(size.width, size.height));
            }
            this.mPictureSizes.m833b();
            for (Camera.Size size2 : this.mCameraParameters.getSupportedPictureSizes()) {
                C3651y0.m1627i(TAG, "support image size :" + size2.width + "*" + size2.height);
                this.mPictureSizes.m832a(new C3557e(size2.width, size2.height));
            }
            if (this.mAspectRatio == null) {
                this.mAspectRatio = AbstractC3554b.DEFAULT_ASPECT_RATIO;
            }
            adjustCameraParameters();
            this.mCamera.setDisplayOrientation(calcDisplayOrientation(this.mDisplayOrientation));
            AbstractC3617h0.a aVar = this.mCaptureListener;
            if (!(aVar instanceof AbstractC3554b.a)) {
                return true;
            }
            ((AbstractC3554b.a) aVar).m825f();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private void releaseCamera() {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.setPreviewCallbackWithBuffer(null);
            this.mCamera.release();
            this.mCamera = null;
            AbstractC3617h0.a aVar = this.mCaptureListener;
            if (aVar instanceof AbstractC3554b.a) {
                ((AbstractC3554b.a) aVar).m824c();
            }
        }
    }

    private boolean setAutoFocusInternal(boolean z) {
        this.mAutoFocus = z;
        if (!isCameraOpened()) {
            return false;
        }
        List<String> supportedFocusModes = this.mCameraParameters.getSupportedFocusModes();
        String str = "infinity";
        if (z && supportedFocusModes.contains("continuous-picture")) {
            str = "continuous-picture";
        } else if (supportedFocusModes.contains("fixed")) {
            str = "fixed";
        } else if (!supportedFocusModes.contains("infinity")) {
            this.mCameraParameters.setFocusMode(supportedFocusModes.get(0));
            return true;
        }
        this.mCameraParameters.setFocusMode(str);
        return true;
    }

    private boolean setFlashInternal(int i) {
        if (!isCameraOpened()) {
            this.mFlash = i;
            return false;
        }
        List<String> supportedFlashModes = this.mCameraParameters.getSupportedFlashModes();
        HashMap<Integer, String> hashMap = FLASH_MODES;
        String str = hashMap.get(Integer.valueOf(i));
        if (supportedFlashModes != null && supportedFlashModes.contains(str)) {
            this.mCameraParameters.setFlashMode(str);
            this.mFlash = i;
            return true;
        }
        String str2 = hashMap.get(Integer.valueOf(this.mFlash));
        if (supportedFlashModes != null && supportedFlashModes.contains(str2)) {
            return false;
        }
        this.mCameraParameters.setFlashMode("off");
        this.mFlash = 0;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void adjustCameraParameters() {
        int i;
        SortedSet<C3557e> m836e = this.mPreviewSizes.m836e(this.mAspectRatio);
        if (m836e == null) {
            C3553a chooseAspectRatio = chooseAspectRatio();
            this.mAspectRatio = chooseAspectRatio;
            m836e = this.mPreviewSizes.m836e(chooseAspectRatio);
        }
        C3557e chooseOptimalSize = chooseOptimalSize(m836e);
        if (this.mShowingPreview) {
            this.mCamera.stopPreview();
        }
        float pictureMaxZoom = getPictureMaxZoom();
        Camera.Parameters parameters = this.mCameraParameters;
        if (pictureMaxZoom > 0.0f) {
            float f = this.mCameraScale;
            if (f > 0.0f) {
                i = (int) (f * pictureMaxZoom);
                parameters.setZoom(i);
                String str = TAG;
                C3651y0.m1627i(str, "set camera zoom :" + this.mCameraParameters.getZoom() + "max zoom :" + pictureMaxZoom);
                this.mCameraParameters.setPreviewFormat(17);
                this.mCameraParameters.setPreviewSize(chooseOptimalSize.m831c(), chooseOptimalSize.m830b());
                C3651y0.m1627i(str, "set preview size :" + chooseOptimalSize.m831c() + "x" + chooseOptimalSize.m830b());
                this.mCameraParameters.setPictureSize(chooseOptimalSize.m831c(), chooseOptimalSize.m830b());
                C3651y0.m1627i(str, "set picture size :" + chooseOptimalSize.m831c() + "x" + chooseOptimalSize.m830b());
                this.mCameraParameters.setRotation(calcCameraRotation(this.mDisplayOrientation));
                setAutoFocusInternal(this.mAutoFocus);
                setFlashInternal(this.mFlash);
                this.mCamera.setParameters(this.mCameraParameters);
                this.mCamera.addCallbackBuffer(new byte[((chooseOptimalSize.m831c() * chooseOptimalSize.m830b()) * ImageFormat.getBitsPerPixel(17)) / 8]);
                this.mCamera.setPreviewCallbackWithBuffer(new C3543d());
                if (this.mShowingPreview) {
                    return;
                }
                this.mCamera.startPreview();
                return;
            }
        }
        i = 0;
        parameters.setZoom(i);
        String str2 = TAG;
        C3651y0.m1627i(str2, "set camera zoom :" + this.mCameraParameters.getZoom() + "max zoom :" + pictureMaxZoom);
        this.mCameraParameters.setPreviewFormat(17);
        this.mCameraParameters.setPreviewSize(chooseOptimalSize.m831c(), chooseOptimalSize.m830b());
        C3651y0.m1627i(str2, "set preview size :" + chooseOptimalSize.m831c() + "x" + chooseOptimalSize.m830b());
        this.mCameraParameters.setPictureSize(chooseOptimalSize.m831c(), chooseOptimalSize.m830b());
        C3651y0.m1627i(str2, "set picture size :" + chooseOptimalSize.m831c() + "x" + chooseOptimalSize.m830b());
        this.mCameraParameters.setRotation(calcCameraRotation(this.mDisplayOrientation));
        setAutoFocusInternal(this.mAutoFocus);
        setFlashInternal(this.mFlash);
        this.mCamera.setParameters(this.mCameraParameters);
        this.mCamera.addCallbackBuffer(new byte[((chooseOptimalSize.m831c() * chooseOptimalSize.m830b()) * ImageFormat.getBitsPerPixel(17)) / 8]);
        this.mCamera.setPreviewCallbackWithBuffer(new C3543d());
        if (this.mShowingPreview) {
        }
    }

    public List<Integer> getAllZoomRatio() {
        if (!this.mCameraParameters.isZoomSupported()) {
            return null;
        }
        C3651y0.m1627i(TAG, "getAllZoomRation = " + this.mCameraParameters.getZoomRatios().toString());
        return this.mCameraParameters.getZoomRatios();
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    C3553a getAspectRatio() {
        return this.mAspectRatio;
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    boolean getAutoFocus() {
        if (!isCameraOpened()) {
            return this.mAutoFocus;
        }
        String focusMode = this.mCameraParameters.getFocusMode();
        return focusMode != null && focusMode.contains(AIUIConstant.INTERACT_MODE_CONTINUOUS);
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    int getDisplayOrientation() {
        return this.mDisplayOrientation;
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    int getFacing() {
        return this.mFacing;
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    int getFlash() {
        return this.mFlash;
    }

    public float getPictureMaxZoom() {
        if (getAllZoomRatio() == null) {
            return -1.0f;
        }
        return Math.round(r0.get(r0.size() - 1).intValue() / 100.0f);
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    C3557e getPreviewSize() {
        Camera.Size previewSize = this.mCameraParameters.getPreviewSize();
        return new C3557e(previewSize.width, previewSize.height);
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public int getSampleRate() {
        return 0;
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    Set<C3553a> getSupportedAspectRatios() {
        C3558f c3558f = this.mPreviewSizes;
        for (C3553a c3553a : c3558f.m834c()) {
            if (this.mPictureSizes.m836e(c3553a) == null) {
                c3558f.m835d(c3553a);
            }
        }
        return c3558f.m834c();
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    boolean isCameraOpened() {
        return this.mCamera != null;
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public void release() {
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    boolean setAspectRatio(C3553a c3553a) {
        if (this.mAspectRatio == null || !isCameraOpened()) {
            this.mAspectRatio = c3553a;
            return true;
        }
        if (this.mAspectRatio.equals(c3553a)) {
            return false;
        }
        if (this.mPreviewSizes.m836e(c3553a) != null) {
            this.mAspectRatio = c3553a;
            adjustCameraParameters();
            return true;
        }
        throw new UnsupportedOperationException(c3553a + " is not supported");
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    void setAutoFocus(boolean z) {
        if (this.mAutoFocus != z && setAutoFocusInternal(z)) {
            this.mCamera.setParameters(this.mCameraParameters);
        }
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    void setDisplayOrientation(int i) {
        if (this.mDisplayOrientation == i) {
            return;
        }
        this.mDisplayOrientation = i;
        if (isCameraOpened()) {
            this.mCameraParameters.setRotation(calcCameraRotation(i));
            this.mCamera.setParameters(this.mCameraParameters);
            this.mCamera.setDisplayOrientation(calcDisplayOrientation(i));
        }
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    void setFacing(int i) {
        if (this.mFacing == i) {
            return;
        }
        this.mFacing = i;
        if (isCameraOpened()) {
            stop();
            start();
        }
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    void setFlash(int i) {
        if (i != this.mFlash && setFlashInternal(i)) {
            this.mCamera.setParameters(this.mCameraParameters);
        }
    }

    boolean setUpPreview() {
        try {
            if (this.mPreview.getOutputClass() == SurfaceHolder.class) {
                this.mCamera.setPreviewDisplay(this.mPreview.getSurfaceHolder());
                return true;
            }
            this.mCamera.setPreviewTexture((SurfaceTexture) this.mPreview.getSurfaceTexture());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            C3651y0.m1625g(TAG, "setup previewTexture fail!!");
            return false;
        }
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public int start() {
        String str;
        String str2;
        if (this.mCameraId == -1 && chooseCamera() == -1) {
            str = TAG;
            str2 = "no camera found!!";
        } else {
            if (!openCamera()) {
                return -1;
            }
            if (setUpPreview()) {
                this.mShowingPreview = true;
                try {
                    this.mCamera.startPreview();
                    return 0;
                } catch (Throwable th) {
                    th.printStackTrace();
                    str = TAG;
                    str2 = "start preview fail!!";
                }
            } else {
                str = TAG;
                str2 = "set preview fail!!";
            }
        }
        C3651y0.m1625g(str, str2);
        return -1;
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public void stop() {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
        }
        this.mShowingPreview = false;
        releaseCamera();
        C3555c c3555c = this.mCameraWindow;
        if (c3555c != null) {
            c3555c.m826a();
        }
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    void takePicture() {
        if (!isCameraOpened()) {
            throw new IllegalStateException("Camera is not ready. Call start() before takePicture().");
        }
        if (!getAutoFocus()) {
            takePictureInternal();
        } else {
            this.mCamera.cancelAutoFocus();
            this.mCamera.autoFocus(new C3541b());
        }
    }

    void takePictureInternal() {
        if (this.isPictureCaptureInProgress.getAndSet(true)) {
            return;
        }
        this.mCamera.takePicture(null, null, null, new C3542c());
    }
}
