package com.iflytek.aiui.data.video;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.aiui.data.video.AbstractC3554b;
import com.iflytek.aiui.data.video.PreviewImpl;
import com.iflytek.aiui.pro.AbstractC3617h0;
import com.iflytek.aiui.pro.C3651y0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

/* loaded from: classes4.dex */
class Camera2 extends AbstractC3554b {
    private static final SparseIntArray INTERNAL_FACINGS;
    private static final int MAX_PREVIEW_HEIGHT = 720;
    private static final int MAX_PREVIEW_WIDTH = 1280;
    private static final String TAG = "Camera2";
    static byte[] TMP_DATA;
    private boolean mAutoFocus;
    CameraDevice mCamera;
    private CameraCharacteristics mCameraCharacteristics;
    private final CameraDevice.StateCallback mCameraDeviceCallback;
    private String mCameraId;
    private final CameraManager mCameraManager;
    AbstractC3550g mCaptureCallback;
    CameraCaptureSession mCaptureSession;
    private int mDisplayOrientation;
    private int mFacing;
    private int mFlash;
    private ImageReader mImageReader;
    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener;
    private final C3558f mPictureSizes;
    CaptureRequest.Builder mPreviewRequestBuilder;
    private final C3558f mPreviewSizes;
    private final CameraCaptureSession.StateCallback mSessionCallback;

    /* renamed from: com.iflytek.aiui.data.video.Camera2$a */
    /* loaded from: classes4.dex */
    class C3544a extends CameraDevice.StateCallback {
        C3544a() {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            if (((AbstractC3617h0) Camera2.this).mCaptureListener instanceof AbstractC3554b.a) {
                ((AbstractC3554b.a) ((AbstractC3617h0) Camera2.this).mCaptureListener).m824c();
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            Camera2.this.mCamera = null;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            C3651y0.m1625g(Camera2.TAG, "onError: " + cameraDevice.getId() + " (" + i + ")");
            Camera2.this.mCamera = null;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            Camera2 camera2 = Camera2.this;
            camera2.mCamera = cameraDevice;
            if (camera2.mImageReader == null) {
                Camera2.this.stop();
                return;
            }
            if (((AbstractC3617h0) Camera2.this).mCaptureListener instanceof AbstractC3554b.a) {
                ((AbstractC3554b.a) ((AbstractC3617h0) Camera2.this).mCaptureListener).m825f();
            }
            Camera2.this.startCaptureSession();
        }
    }

    /* renamed from: com.iflytek.aiui.data.video.Camera2$b */
    /* loaded from: classes4.dex */
    class C3545b extends CameraCaptureSession.StateCallback {
        C3545b() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(CameraCaptureSession cameraCaptureSession) {
            CameraCaptureSession cameraCaptureSession2 = Camera2.this.mCaptureSession;
            if (cameraCaptureSession2 == null || !cameraCaptureSession2.equals(cameraCaptureSession)) {
                return;
            }
            Camera2.this.mCaptureSession = null;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            C3651y0.m1625g(Camera2.TAG, "Failed to configure capture session.");
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            String str;
            Camera2 camera2 = Camera2.this;
            if (camera2.mCamera == null) {
                return;
            }
            camera2.mCaptureSession = cameraCaptureSession;
            camera2.updateAutoFocus();
            Camera2.this.updateFlash();
            try {
                Camera2 camera22 = Camera2.this;
                camera22.mCaptureSession.setRepeatingRequest(camera22.mPreviewRequestBuilder.build(), Camera2.this.mCaptureCallback, null);
            } catch (CameraAccessException e) {
                e = e;
                str = "Failed to start camera preview because it couldn't access camera";
                C3651y0.m1626h(Camera2.TAG, str, e);
            } catch (IllegalStateException e2) {
                e = e2;
                str = "Failed to start camera preview.";
                C3651y0.m1626h(Camera2.TAG, str, e);
            }
        }
    }

    /* renamed from: com.iflytek.aiui.data.video.Camera2$c */
    /* loaded from: classes4.dex */
    class C3546c extends AbstractC3550g {
        C3546c() {
        }

        @Override // com.iflytek.aiui.data.video.Camera2.AbstractC3550g
        /* renamed from: a */
        public void mo812a() {
            Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
            m815d(3);
            try {
                Camera2 camera2 = Camera2.this;
                camera2.mCaptureSession.capture(camera2.mPreviewRequestBuilder.build(), this, null);
                Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
            } catch (CameraAccessException e) {
                C3651y0.m1626h(Camera2.TAG, "Failed to run precapture sequence.", e);
            }
        }

        @Override // com.iflytek.aiui.data.video.Camera2.AbstractC3550g
        /* renamed from: b */
        public void mo813b() {
            Camera2.this.captureStillPicture();
        }
    }

    /* renamed from: com.iflytek.aiui.data.video.Camera2$d */
    /* loaded from: classes4.dex */
    class C3547d implements ImageReader.OnImageAvailableListener {

        /* renamed from: a */
        byte[] f2175a = null;

        /* renamed from: b */
        int f2176b = 0;

        /* renamed from: c */
        int f2177c = 0;

        /* renamed from: d */
        long f2178d;

        C3547d() {
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            Image acquireLatestImage = imageReader.acquireLatestImage();
            if (acquireLatestImage == null) {
                if (acquireLatestImage != null) {
                    acquireLatestImage.close();
                    return;
                }
                return;
            }
            try {
                if (this.f2178d <= 0 || System.currentTimeMillis() - this.f2178d < 1000) {
                    if (this.f2178d == 0) {
                        this.f2178d = System.currentTimeMillis();
                    }
                    this.f2176b++;
                } else {
                    this.f2177c = (int) (1000.0f / (((float) (System.currentTimeMillis() - this.f2178d)) / (this.f2176b * 1.0f)));
                    C3651y0.m1627i(Camera2.TAG, "on onImageAvailable time:" + (acquireLatestImage.getTimestamp() / 1000000) + "fps :" + this.f2177c);
                    this.f2178d = 0L;
                    this.f2176b = 0;
                }
                int width = acquireLatestImage.getWidth();
                int height = acquireLatestImage.getHeight();
                byte[] bArr = this.f2175a;
                if (bArr == null || bArr.length < ((width * height) * ImageFormat.getBitsPerPixel(17)) / 8) {
                    this.f2175a = new byte[((width * height) * ImageFormat.getBitsPerPixel(17)) / 8];
                }
                C3556d.m828a(acquireLatestImage, this.f2175a);
                Bundle bundle = new Bundle();
                bundle.putString("data_type", InternalConstant.DTYPE_IMAGE);
                bundle.putString("format", "6");
                bundle.putString("fps", "25");
                bundle.putString("width", String.valueOf(width));
                bundle.putString("height", String.valueOf(height));
                bundle.putString("stride", String.valueOf(width));
                ((AbstractC3617h0) Camera2.this).mCaptureListener.mo863d(this.f2175a, ((width * height) * ImageFormat.getBitsPerPixel(17)) / 8, bundle);
                if (acquireLatestImage != null) {
                    acquireLatestImage.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (acquireLatestImage != null) {
                        try {
                            acquireLatestImage.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
    }

    /* renamed from: com.iflytek.aiui.data.video.Camera2$e */
    /* loaded from: classes4.dex */
    class C3548e implements PreviewImpl.Callback {
        C3548e() {
        }

        @Override // com.iflytek.aiui.data.video.PreviewImpl.Callback
        public void onSurfaceChanged() {
            Camera2.this.startCaptureSession();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.data.video.Camera2$f */
    /* loaded from: classes4.dex */
    public class C3549f extends CameraCaptureSession.CaptureCallback {
        C3549f() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            Camera2.this.unlockFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.aiui.data.video.Camera2$g */
    /* loaded from: classes4.dex */
    public static abstract class AbstractC3550g extends CameraCaptureSession.CaptureCallback {

        /* renamed from: a */
        private int f2182a;

        AbstractC3550g() {
        }

        /* renamed from: c */
        private void m814c(CaptureResult captureResult) {
            int i = this.f2182a;
            if (i == 1) {
                Integer num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num == null) {
                    return;
                }
                if (num.intValue() != 4 && num.intValue() != 5) {
                    return;
                }
                Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num2 != null && num2.intValue() != 2) {
                    m815d(2);
                    mo812a();
                    return;
                }
            } else {
                if (i == 3) {
                    Integer num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                    if (num3 == null || num3.intValue() == 5 || num3.intValue() == 4 || num3.intValue() == 2) {
                        m815d(4);
                        return;
                    }
                    return;
                }
                if (i != 4) {
                    return;
                }
                Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num4 != null && num4.intValue() == 5) {
                    return;
                }
            }
            m815d(5);
            mo813b();
        }

        /* renamed from: a */
        public abstract void mo812a();

        /* renamed from: b */
        public abstract void mo813b();

        /* renamed from: d */
        void m815d(int i) {
            this.f2182a = i;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            m814c(totalCaptureResult);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            m814c(captureResult);
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        INTERNAL_FACINGS = sparseIntArray;
        sparseIntArray.put(0, 1);
        sparseIntArray.put(1, 0);
        TMP_DATA = null;
    }

    public Camera2(PreviewImpl previewImpl, Context context, AbstractC3617h0.a aVar, String str) {
        super(previewImpl == null ? CameraCompat.createPreview(context, null) : previewImpl, aVar);
        this.mCameraDeviceCallback = new C3544a();
        this.mSessionCallback = new C3545b();
        this.mCaptureCallback = new C3546c();
        this.mOnImageAvailableListener = new C3547d();
        this.mPreviewSizes = new C3558f();
        this.mPictureSizes = new C3558f();
        this.mCameraManager = (CameraManager) context.getSystemService("camera");
        this.mCameraId = str;
        this.mPreview.setCallback(new C3548e());
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

    private boolean chooseCameraIdByFacing() {
        try {
            int i = INTERNAL_FACINGS.get(this.mFacing);
            String[] cameraIdList = this.mCameraManager.getCameraIdList();
            if (cameraIdList.length == 0) {
                return false;
            }
            for (String str : cameraIdList) {
                C3651y0.m1627i(TAG, "find camera Id :" + str);
                CameraCharacteristics cameraCharacteristics = this.mCameraManager.getCameraCharacteristics(str);
                Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (num != null && num.intValue() == i) {
                    this.mCameraId = str;
                    this.mCameraCharacteristics = cameraCharacteristics;
                    return true;
                }
            }
            this.mCameraId = cameraIdList[0];
            return true;
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to get a list of camera devices", e);
        }
    }

    private C3557e chooseOptimalSize() {
        SortedSet<C3557e> m836e = this.mPreviewSizes.m836e(this.mAspectRatio);
        C3557e first = m836e.first();
        int i = this.maxImagePixel;
        for (C3557e c3557e : m836e) {
            if (c3557e.m830b() * c3557e.m831c() <= i) {
                first = c3557e;
            }
        }
        return first;
    }

    private boolean collectCameraInfo() {
        try {
            CameraCharacteristics cameraCharacteristics = this.mCameraManager.getCameraCharacteristics(this.mCameraId);
            this.mCameraCharacteristics = cameraCharacteristics;
            Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
            if (num == null) {
                C3651y0.m1625g(TAG, "Unexpected state: LENS_FACING null");
            }
            int size = INTERNAL_FACINGS.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                SparseIntArray sparseIntArray = INTERNAL_FACINGS;
                if (sparseIntArray.valueAt(i) == num.intValue()) {
                    this.mFacing = sparseIntArray.keyAt(i);
                    break;
                }
                this.mFacing = 0;
                i++;
            }
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (streamConfigurationMap == null) {
                C3651y0.m1625g(TAG, "Failed to get configuration map: " + this.mCameraId);
                return false;
            }
            this.mPreviewSizes.m833b();
            for (Size size2 : streamConfigurationMap.getOutputSizes(this.mPreview.getOutputClass())) {
                this.mPreviewSizes.m832a(new C3557e(size2.getWidth(), size2.getHeight()));
            }
            this.mPictureSizes.m833b();
            collectPictureSizes(this.mPictureSizes, streamConfigurationMap);
            ArrayList arrayList = new ArrayList();
            for (C3553a c3553a : this.mPreviewSizes.m834c()) {
                if (!this.mPictureSizes.m834c().contains(c3553a)) {
                    arrayList.add(c3553a);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mPreviewSizes.m835d((C3553a) it.next());
            }
            if (this.mPreviewSizes.m834c().contains(this.mAspectRatio)) {
                return true;
            }
            this.mAspectRatio = this.mPreviewSizes.m834c().iterator().next();
            return true;
        } catch (CameraAccessException e) {
            C3651y0.m1626h(TAG, "Failed to get camera" + this.mCameraId + " info", e);
            return false;
        }
    }

    public static byte[] cropNV21(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        if (i3 > i || i4 > i2) {
            return null;
        }
        int i7 = (i3 / 4) * 4;
        int i8 = (i4 / 4) * 4;
        int i9 = (i5 / 4) * 4;
        int i10 = (i6 / 4) * 4;
        int i11 = i9 * i10;
        int i12 = i11 / 2;
        byte[] bArr2 = TMP_DATA;
        if (bArr2 == null || bArr2.length != i11 + i12) {
            TMP_DATA = new byte[i12 + i11];
        }
        for (int i13 = i8; i13 < i10 + i8; i13++) {
            for (int i14 = i7; i14 < i7 + i9; i14++) {
                byte[] bArr3 = TMP_DATA;
                int i15 = i13 - i8;
                bArr3[((i15 * i9) + i14) - i7] = bArr[(i13 * i) + i14];
                bArr3[((((i15 / 2) * i9) + i11) + i14) - i7] = bArr[((i13 / 2) * i) + (i2 * i) + i14];
            }
        }
        return TMP_DATA;
    }

    private Rect getZoomRect(float f) {
        try {
            float floatValue = ((Float) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue() * 10.0f;
            Rect rect = (Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            if (f > floatValue || f <= 1.0f) {
                if (f == 0.0f) {
                    return new Rect(0, 0, rect.width(), rect.height());
                }
                return null;
            }
            int width = (int) (rect.width() / floatValue);
            int i = (int) f;
            int width2 = ((rect.width() - width) / 100) * i;
            int height = ((rect.height() - ((int) (rect.height() / floatValue))) / 100) * i;
            int i2 = width2 - (width2 & 3);
            int i3 = height - (height & 3);
            return new Rect(i2, i3, rect.width() - i2, rect.height() - i3);
        } catch (Exception unused) {
            Log.e(TAG, "Error during camera init");
            return null;
        }
    }

    private void lockFocus() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        try {
            this.mCaptureCallback.m815d(1);
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException e) {
            C3651y0.m1626h(TAG, "Failed to lock focus.", e);
        }
    }

    private void prepareImageReader() {
        ImageReader imageReader = this.mImageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        C3557e chooseOptimalSize = chooseOptimalSize();
        C3651y0.m1627i(TAG, "set picture size :" + chooseOptimalSize.m831c() + "x" + chooseOptimalSize.m830b());
        ImageReader newInstance = ImageReader.newInstance(chooseOptimalSize.m831c(), chooseOptimalSize.m830b(), 35, 2);
        this.mImageReader = newInstance;
        newInstance.setOnImageAvailableListener(this.mOnImageAvailableListener, null);
    }

    private boolean startOpeningCamera() {
        try {
            C3651y0.m1627i(TAG, "open camera id " + this.mCameraId);
            this.mCameraManager.openCamera(this.mCameraId, this.mCameraDeviceCallback, (Handler) null);
            return true;
        } catch (CameraAccessException e) {
            C3651y0.m1626h(TAG, "Failed to open camera: " + this.mCameraId, e);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void captureStillPicture() {
        CaptureRequest.Key key;
        int i;
        CaptureRequest.Key key2;
        try {
            CaptureRequest.Builder createCaptureRequest = this.mCamera.createCaptureRequest(2);
            createCaptureRequest.addTarget(this.mImageReader.getSurface());
            CaptureRequest.Key key3 = CaptureRequest.CONTROL_AF_MODE;
            createCaptureRequest.set(key3, this.mPreviewRequestBuilder.get(key3));
            int i2 = this.mFlash;
            int i3 = 1;
            if (i2 == 0) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                key = CaptureRequest.FLASH_MODE;
                i = 0;
            } else {
                if (i2 != 1) {
                    if (i2 == 2) {
                        createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                        key2 = CaptureRequest.FLASH_MODE;
                    } else if (i2 == 3) {
                        key2 = CaptureRequest.CONTROL_AE_MODE;
                    } else {
                        if (i2 != 4) {
                            int intValue = ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                            CaptureRequest.Key key4 = CaptureRequest.JPEG_ORIENTATION;
                            int i4 = this.mDisplayOrientation;
                            if (this.mFacing != 1) {
                                i3 = -1;
                            }
                            createCaptureRequest.set(key4, Integer.valueOf(((intValue + (i4 * i3)) + 360) % 360));
                            this.mCaptureSession.stopRepeating();
                            this.mCaptureSession.capture(createCaptureRequest.build(), new C3549f(), null);
                        }
                        key2 = CaptureRequest.CONTROL_AE_MODE;
                    }
                    createCaptureRequest.set(key2, 2);
                    int intValue2 = ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                    CaptureRequest.Key key42 = CaptureRequest.JPEG_ORIENTATION;
                    int i42 = this.mDisplayOrientation;
                    if (this.mFacing != 1) {
                    }
                    createCaptureRequest.set(key42, Integer.valueOf(((intValue2 + (i42 * i3)) + 360) % 360));
                    this.mCaptureSession.stopRepeating();
                    this.mCaptureSession.capture(createCaptureRequest.build(), new C3549f(), null);
                }
                key = CaptureRequest.CONTROL_AE_MODE;
                i = 3;
            }
            createCaptureRequest.set(key, i);
            int intValue22 = ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
            CaptureRequest.Key key422 = CaptureRequest.JPEG_ORIENTATION;
            int i422 = this.mDisplayOrientation;
            if (this.mFacing != 1) {
            }
            createCaptureRequest.set(key422, Integer.valueOf(((intValue22 + (i422 * i3)) + 360) % 360));
            this.mCaptureSession.stopRepeating();
            this.mCaptureSession.capture(createCaptureRequest.build(), new C3549f(), null);
        } catch (CameraAccessException e) {
            C3651y0.m1626h(TAG, "Cannot capture a still picture.", e);
        }
    }

    protected void collectPictureSizes(C3558f c3558f, StreamConfigurationMap streamConfigurationMap) {
        for (Size size : streamConfigurationMap.getOutputSizes(35)) {
            this.mPictureSizes.m832a(new C3557e(size.getWidth(), size.getHeight()));
        }
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    C3553a getAspectRatio() {
        return this.mAspectRatio;
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    boolean getAutoFocus() {
        return this.mAutoFocus;
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

    public float getMaxZoom() {
        try {
            return ((Float) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue() * 10.0f;
        } catch (Exception unused) {
            Log.e(TAG, "Error during camera init");
            return -1.0f;
        }
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    C3557e getPreviewSize() {
        return new C3557e(this.mImageReader.getWidth(), this.mImageReader.getHeight());
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public int getSampleRate() {
        return 0;
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    Set<C3553a> getSupportedAspectRatios() {
        return this.mPreviewSizes.m834c();
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
        if (c3553a == null || c3553a.equals(this.mAspectRatio) || !this.mPreviewSizes.m834c().contains(c3553a)) {
            return false;
        }
        this.mAspectRatio = c3553a;
        prepareImageReader();
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession == null) {
            return true;
        }
        cameraCaptureSession.close();
        this.mCaptureSession = null;
        startCaptureSession();
        return true;
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    void setAutoFocus(boolean z) {
        if (this.mAutoFocus == z) {
            return;
        }
        this.mAutoFocus = z;
        if (this.mPreviewRequestBuilder != null) {
            updateAutoFocus();
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
                } catch (CameraAccessException unused) {
                    this.mAutoFocus = !this.mAutoFocus;
                }
            }
        }
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    void setDisplayOrientation(int i) {
        this.mDisplayOrientation = i;
        this.mPreview.setDisplayOrientation(i);
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
        int i2 = this.mFlash;
        if (i2 == i) {
            return;
        }
        this.mFlash = i;
        if (this.mPreviewRequestBuilder != null) {
            updateFlash();
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
                } catch (CameraAccessException unused) {
                    this.mFlash = i2;
                }
            }
        }
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public int start() {
        if ((TextUtils.isEmpty(this.mCameraId) && !chooseCameraIdByFacing()) || !collectCameraInfo()) {
            return -1;
        }
        prepareImageReader();
        return !startOpeningCamera() ? -1 : 0;
    }

    void startCaptureSession() {
        if (isCameraOpened()) {
            try {
                this.mPreviewRequestBuilder = this.mCamera.createCaptureRequest(1);
                float maxZoom = getMaxZoom();
                if (maxZoom > 0.0f) {
                    float f = this.mCameraScale;
                    if (f > 0.0f) {
                        this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, getZoomRect(f * maxZoom));
                        C3651y0.m1627i(TAG, "set camera max zoom :" + maxZoom + "scale" + this.mCameraScale + " rect:" + this.mPreviewRequestBuilder.get(CaptureRequest.SCALER_CROP_REGION));
                    }
                }
                ArrayList arrayList = new ArrayList();
                if (this.mPreview.isReady()) {
                    C3557e chooseOptimalSize = chooseOptimalSize();
                    C3651y0.m1627i(TAG, "set picture size :" + chooseOptimalSize.m831c() + "x" + chooseOptimalSize.m830b());
                    this.mPreview.setBufferSize(chooseOptimalSize.m831c(), chooseOptimalSize.m830b());
                    Surface surface = this.mPreview.getSurface();
                    this.mPreviewRequestBuilder.addTarget(surface);
                    arrayList.add(surface);
                }
                arrayList.add(this.mImageReader.getSurface());
                this.mPreviewRequestBuilder.addTarget(this.mImageReader.getSurface());
                this.mCamera.createCaptureSession(arrayList, this.mSessionCallback, null);
            } catch (CameraAccessException unused) {
                throw new RuntimeException("Failed to start camera session");
            }
        }
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public void stop() {
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            try {
                cameraCaptureSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mCaptureSession = null;
        }
        CameraDevice cameraDevice = this.mCamera;
        if (cameraDevice != null) {
            try {
                cameraDevice.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mCamera = null;
        }
        ImageReader imageReader = this.mImageReader;
        if (imageReader != null) {
            try {
                imageReader.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.mImageReader = null;
        }
    }

    @Override // com.iflytek.aiui.data.video.AbstractC3554b
    void takePicture() {
        if (this.mAutoFocus) {
            lockFocus();
        } else {
            captureStillPicture();
        }
    }

    void unlockFocus() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
        try {
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
            updateAutoFocus();
            updateFlash();
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
            this.mCaptureCallback.m815d(0);
        } catch (CameraAccessException e) {
            C3651y0.m1626h(TAG, "Failed to restart camera preview.", e);
        }
    }

    void updateAutoFocus() {
        CaptureRequest.Builder builder;
        CaptureRequest.Key key;
        int i = 0;
        if (this.mAutoFocus) {
            int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            if (iArr != null && iArr.length != 0 && (iArr.length != 1 || iArr[0] != 0)) {
                builder = this.mPreviewRequestBuilder;
                key = CaptureRequest.CONTROL_AF_MODE;
                i = 4;
                builder.set(key, i);
            }
            this.mAutoFocus = false;
        }
        builder = this.mPreviewRequestBuilder;
        key = CaptureRequest.CONTROL_AF_MODE;
        builder.set(key, i);
    }

    void updateFlash() {
        int i;
        CaptureRequest.Key key;
        CaptureRequest.Builder builder;
        CaptureRequest.Builder builder2;
        CaptureRequest.Key key2;
        int i2;
        int i3 = this.mFlash;
        int i4 = 4;
        if (i3 != 0) {
            if (i3 == 1) {
                builder2 = this.mPreviewRequestBuilder;
                key2 = CaptureRequest.CONTROL_AE_MODE;
                i2 = 3;
            } else {
                if (i3 == 2) {
                    this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                    builder = this.mPreviewRequestBuilder;
                    key = CaptureRequest.FLASH_MODE;
                    i = 2;
                    builder.set(key, i);
                }
                if (i3 == 3) {
                    builder2 = this.mPreviewRequestBuilder;
                    key2 = CaptureRequest.CONTROL_AE_MODE;
                    i2 = 2;
                } else if (i3 != 4) {
                    return;
                }
            }
            builder2.set(key2, i2);
            builder = this.mPreviewRequestBuilder;
            key = CaptureRequest.FLASH_MODE;
            i = 0;
            builder.set(key, i);
        }
        i4 = 1;
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(i4));
        builder = this.mPreviewRequestBuilder;
        key = CaptureRequest.FLASH_MODE;
        i = 0;
        builder.set(key, i);
    }
}
