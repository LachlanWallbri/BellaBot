package com.pudutech.lidar;

import com.pudutech.base.Pdlog;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class HLS extends SerialLidar {

    /* renamed from: PI */
    private static final double f5446PI = 3.141592653589793d;
    private static final String TAG = "Lidar";
    private static final int begin_angle = 0;
    private static final int lazer_packages_len = 42;
    private ControlStep step = ControlStep.IDLE;
    private Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.HLS.1
        @Override // java.lang.Runnable
        public void run() {
            HLS.this.controlFSM();
        }
    };
    private boolean isbegin_angle = false;
    private List<LidarNode> nodes = new ArrayList();
    private List<LidarNode> oneFrameNodes = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public enum ControlStep {
        START_DTR,
        SCAN,
        STOP,
        STOP_DTR,
        IDLE
    }

    private static int unsignedByteToInt(byte b) {
        return b & 255;
    }

    @Override // com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 230400;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void controlFSM() {
        Pdlog.m3275i("Lidar", "controlFSM step=" + this.step);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = C47044.$SwitchMap$com$pudutech$lidar$HLS$ControlStep[this.step.ordinal()];
            if (i == 1) {
                lidarInterface.setDTR(false);
                this.step = ControlStep.SCAN;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
            } else if (i == 2) {
                lidarInterface.send(new byte[]{98});
                this.step = ControlStep.IDLE;
            } else if (i == 3) {
                lidarInterface.setDTR(true);
                this.step = ControlStep.STOP;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
            } else {
                if (i != 4) {
                    return;
                }
                lidarInterface.send(new byte[]{101});
                this.step = ControlStep.IDLE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* renamed from: com.pudutech.lidar.HLS$4 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C47044 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$HLS$ControlStep;

        static {
            int[] iArr = new int[ControlStep.values().length];
            $SwitchMap$com$pudutech$lidar$HLS$ControlStep = iArr;
            try {
                iArr[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$HLS$ControlStep[ControlStep.SCAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$HLS$ControlStep[ControlStep.STOP_DTR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$HLS$ControlStep[ControlStep.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] bArr) {
        parseData(bArr);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3275i("Lidar", "start scan");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.HLS.2
            @Override // java.lang.Runnable
            public void run() {
                if (HLS.this.step == ControlStep.IDLE || HLS.this.step == ControlStep.STOP || HLS.this.step == ControlStep.STOP_DTR) {
                    HLS.this.controlHandler.removeCallbacks(HLS.this.controlRunnable);
                    HLS.this.step = ControlStep.START_DTR;
                    HLS.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3275i("Lidar", "stop scan");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.HLS.3
            @Override // java.lang.Runnable
            public void run() {
                if (HLS.this.step == ControlStep.IDLE || !(HLS.this.step == ControlStep.STOP || HLS.this.step == ControlStep.STOP_DTR)) {
                    HLS.this.controlHandler.removeCallbacks(HLS.this.controlRunnable);
                    HLS.this.step = ControlStep.STOP_DTR;
                    HLS.this.controlFSM();
                }
            }
        });
    }

    private List<LidarNode> parseData(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (bArr.length % 42 != 0) {
            combination(arrayList);
            return arrayList;
        }
        Pdlog.m3276v("Lidar", "laser data length: " + bArr.length);
        int length = bArr.length / 42;
        for (int i = 0; i < length; i++) {
            int i2 = i * 42;
            int unsignedByteToInt = unsignedByteToInt(bArr[i2 + 1]);
            unsignedByteToInt(bArr[i2 + 3]);
            unsignedByteToInt(bArr[i2 + 2]);
            int i3 = (unsignedByteToInt - 160) * 6;
            for (int i4 = 0; i4 < 6; i4++) {
                LidarNode lidarNode = new LidarNode();
                lidarNode.angle = i3 + i4;
                if (this.isbegin_angle || lidarNode.angle == 0) {
                    this.isbegin_angle = true;
                    int i5 = i4 * 6;
                    lidarNode.quality = unsignedByteToInt(bArr[i2 + 4 + i5]) + (unsignedByteToInt(bArr[(i2 + 5) + i5]) << 8);
                    lidarNode.dist = unsignedByteToInt(bArr[i2 + 6 + i5]) + (unsignedByteToInt(bArr[(i2 + 7) + i5]) << 8);
                    float f = lidarNode.dist;
                    lidarNode.angleInRad = Math.toRadians(lidarNode.angle);
                    lidarNode.distance_m = f / 1000.0d;
                    arrayList.add(lidarNode);
                }
            }
        }
        combination(arrayList);
        return arrayList;
    }

    private List<LidarNode> fillLidar(List<LidarNode> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < 360; i2++) {
            LidarNode lidarNode = new LidarNode();
            if (i >= list.size()) {
                lidarNode.set(0, i2, 0, 0.0d, 0.0d);
            } else if (Math.abs(list.get(i).angle - i2) < 0.001d) {
                lidarNode.set(list.get(i));
                i++;
            } else {
                lidarNode.set(0, i2, 0, 0.0d, 0.0d);
            }
            arrayList.add(lidarNode);
        }
        return arrayList;
    }

    private void combination(List<LidarNode> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (list.get(0).angle != 0) {
            this.nodes.addAll(list);
            return;
        }
        if (this.nodes.size() != 360 || this.nodes.isEmpty()) {
            this.oneFrameNodes = fillLidar(this.nodes);
        } else {
            this.oneFrameNodes = this.nodes;
        }
        LidarListener listener = getListener();
        if (listener != null) {
            Pdlog.m3276v("Lidar", "on One Frame Complete");
            if (this.oneFrameNodes.isEmpty()) {
                Pdlog.m3277w("Lidar", "empty frame");
            } else {
                listener.onOneFrameComplete(this.oneFrameNodes);
            }
        } else {
            Pdlog.m3277w("Lidar", "lidarCallback is null");
        }
        ArrayList arrayList = new ArrayList();
        this.nodes = arrayList;
        arrayList.addAll(list);
    }
}
