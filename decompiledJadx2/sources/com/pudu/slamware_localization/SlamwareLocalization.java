package com.pudu.slamware_localization;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.util.Log;
import android.util.Pair;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudu.slamware_localization.NetworkCallbackImpl;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.SlamStatus;
import com.slamtec.slamware.AbstractSlamwarePlatform;
import com.slamtec.slamware.discovery.DeviceManager;
import com.slamtec.slamware.robot.CompositeMap;
import com.slamtec.slamware.robot.Location;
import com.slamtec.slamware.robot.Pose;
import com.slamtec.slamware.robot.Rotation;
import com.slamtec.slamware.sdp.CompositeMapHelper;
import java.io.File;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: SlamwareLocalization.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010$\u001a\u00020\nJ\u0006\u0010%\u001a\u00020\u001dJ\u0006\u0010&\u001a\u00020'J\b\u0010(\u001a\u00020\bH\u0002J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,J\b\u0010-\u001a\u00020*H\u0002J\u0010\u0010.\u001a\u00020*2\b\u0010/\u001a\u0004\u0018\u00010\u0004J\u001e\u00100\u001a\u00020*2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002022\u0006\u00104\u001a\u000202R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, m3961d2 = {"Lcom/pudu/slamware_localization/SlamwareLocalization;", "", "()V", "TAG", "", "buf", "", "cnt", "", "hasInit", "", "initFinish", "isInit", "locateStatus", "lostFlag", "mListener", "Lcom/pudu/slamware_localization/NetworkCallbackImpl$OnWifiStateChangedListener;", "mPlatform", "Lcom/slamtec/slamware/AbstractSlamwarePlatform;", "mRunnable", "Ljava/lang/Runnable;", "getMRunnable", "()Ljava/lang/Runnable;", "onPoseListener", "Lcom/pudu/slamware_localization/IPoseListener;", "originMap", "pose", "Lcom/slamtec/slamware/robot/Pose;", RequestParameters.POSITION, "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "quality", "qualityAver", "rotation", "Lcom/slamtec/slamware/robot/Rotation;", "thread", "Ljava/lang/Thread;", "checkInitFinish", "getPose", "getSlamStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus;", "getSum", "init", "", "context", "Landroid/content/Context;", "initBuf", "setMap", "srcFilePath", "setPose", "x", "", "y", CompressorStreamFactory.f8930Z, "slamware_localization_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SlamwareLocalization {
    private int cnt;
    private boolean hasInit;
    private boolean initFinish;
    private boolean isInit;
    private boolean locateStatus;
    private boolean lostFlag;
    private AbstractSlamwarePlatform mPlatform;
    private IPoseListener onPoseListener;
    private int quality;
    private int qualityAver;
    private Thread thread;
    private final String TAG = "Slamware_Localization";
    private String originMap = "/sdcard/pudu/map/origin.stcm";
    private Pose pose = new Pose();
    private Rotation rotation = new Rotation();
    private Vector3d position = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private int[] buf = new int[10];
    private final NetworkCallbackImpl.OnWifiStateChangedListener mListener = new NetworkCallbackImpl.OnWifiStateChangedListener() { // from class: com.pudu.slamware_localization.SlamwareLocalization$mListener$1
        @Override // com.pudu.slamware_localization.NetworkCallbackImpl.OnWifiStateChangedListener
        public void onAvailable() {
            String str;
            Pair<Integer, String> execCommand = RootCmd.execCommand("ip route add 192.168.11.0/24 dev eth0 proto static scope link table wlan0", true);
            SlamwareLocalization.this.isInit = false;
            str = SlamwareLocalization.this.TAG;
            Pdlog.m3273d(str, "on available pair: " + execCommand);
        }

        @Override // com.pudu.slamware_localization.NetworkCallbackImpl.OnWifiStateChangedListener
        public void onUnavailable() {
            String str;
            Pair<Integer, String> execCommand = RootCmd.execCommand("ip route add 192.168.11.0/24 dev eth0 proto static scope link table wlan0", true);
            SlamwareLocalization.this.isInit = false;
            str = SlamwareLocalization.this.TAG;
            Pdlog.m3273d(str, "on unavailable pair: " + execCommand);
        }
    };
    private final Runnable mRunnable = new Runnable() { // from class: com.pudu.slamware_localization.SlamwareLocalization$mRunnable$1
        /* JADX WARN: Incorrect condition in loop: B:4:0x001a */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void run() {
            String str;
            boolean z;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            Pose pose;
            Vector3d vector3d;
            Pose pose2;
            Vector3d vector3d2;
            Pose pose3;
            Vector3d vector3d3;
            Rotation rotation;
            int i;
            int[] iArr;
            int i2;
            int i3;
            int sum;
            String str7;
            int i4;
            int i5;
            int i6;
            int i7;
            String str8;
            boolean z2;
            String str9;
            SlamwareLocalization slamwareLocalization = SlamwareLocalization.this;
            str = slamwareLocalization.TAG;
            Pdlog.m3273d(str, "start get pose");
            while (true) {
                Thread.sleep(25L);
                while (!z) {
                    try {
                        str4 = slamwareLocalization.TAG;
                        Pdlog.m3273d(str4, "Slamware init");
                        AbstractSlamwarePlatform connect = DeviceManager.connect("192.168.11.1", 1445);
                        Intrinsics.checkExpressionValueIsNotNull(connect, "DeviceManager.connect(\"192.168.11.1\", 1445)");
                        slamwareLocalization.mPlatform = connect;
                        slamwareLocalization.isInit = true;
                        str5 = slamwareLocalization.TAG;
                        Pdlog.m3273d(str5, "Slamware init finish");
                    } catch (Exception e) {
                        str2 = slamwareLocalization.TAG;
                        Pdlog.m3274e(str2, Log.getStackTraceString(e));
                        str3 = slamwareLocalization.TAG;
                        Pdlog.m3274e(str3, "Slamware init exception");
                        slamwareLocalization.isInit = false;
                    }
                    Thread.sleep(250L);
                }
                try {
                    Pose pose4 = SlamwareLocalization.access$getMPlatform$p(slamwareLocalization).getPose();
                    Intrinsics.checkExpressionValueIsNotNull(pose4, "mPlatform.getPose()");
                    slamwareLocalization.pose = pose4;
                    pose = slamwareLocalization.pose;
                    Rotation rotation2 = pose.getRotation();
                    Intrinsics.checkExpressionValueIsNotNull(rotation2, "pose.getRotation()");
                    slamwareLocalization.rotation = rotation2;
                    vector3d = slamwareLocalization.position;
                    pose2 = slamwareLocalization.pose;
                    vector3d.setX(pose2.getX());
                    vector3d2 = slamwareLocalization.position;
                    pose3 = slamwareLocalization.pose;
                    vector3d2.setY(pose3.getY());
                    vector3d3 = slamwareLocalization.position;
                    rotation = slamwareLocalization.rotation;
                    vector3d3.setZ(rotation.getYaw());
                    slamwareLocalization.quality = SlamwareLocalization.access$getMPlatform$p(slamwareLocalization).getLocalizationQuality();
                    i = slamwareLocalization.cnt;
                    slamwareLocalization.cnt = i + 1;
                    iArr = slamwareLocalization.buf;
                    i2 = slamwareLocalization.cnt;
                    i3 = slamwareLocalization.quality;
                    iArr[i2 % 10] = i3;
                    sum = slamwareLocalization.getSum();
                    slamwareLocalization.qualityAver = sum / 10;
                    str7 = slamwareLocalization.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("quality aver:");
                    i4 = slamwareLocalization.qualityAver;
                    sb.append(i4);
                    Pdlog.m3273d(str7, sb.toString());
                    i5 = slamwareLocalization.qualityAver;
                    if (i5 > 56) {
                        slamwareLocalization.initFinish = true;
                        slamwareLocalization.locateStatus = true;
                    }
                    i6 = slamwareLocalization.qualityAver;
                    if (i6 > 45) {
                        z2 = slamwareLocalization.lostFlag;
                        if (z2) {
                            str9 = slamwareLocalization.TAG;
                            Pdlog.m3273d(str9, "set locate recovery");
                            slamwareLocalization.locateStatus = true;
                            slamwareLocalization.lostFlag = false;
                        }
                    }
                    i7 = slamwareLocalization.qualityAver;
                    if (i7 < 40) {
                        str8 = slamwareLocalization.TAG;
                        Pdlog.m3273d(str8, "set lost locate");
                        slamwareLocalization.lostFlag = true;
                        slamwareLocalization.locateStatus = false;
                    }
                } catch (Exception e2) {
                    Log.getStackTraceString(e2);
                    slamwareLocalization.isInit = false;
                    str6 = slamwareLocalization.TAG;
                    Pdlog.m3274e(str6, "get Slamware pose | quality exception");
                }
            }
        }
    };

    public static final /* synthetic */ AbstractSlamwarePlatform access$getMPlatform$p(SlamwareLocalization slamwareLocalization) {
        AbstractSlamwarePlatform abstractSlamwarePlatform = slamwareLocalization.mPlatform;
        if (abstractSlamwarePlatform == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlatform");
        }
        return abstractSlamwarePlatform;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        NetworkCallbackImpl networkCallbackImpl = new NetworkCallbackImpl(this.mListener);
        NetworkRequest build = new NetworkRequest.Builder().build();
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ((ConnectivityManager) systemService).registerNetworkCallback(build, networkCallbackImpl);
        initBuf();
        if (this.hasInit) {
            return;
        }
        RootCmd.execCommand("ip rule add to 192.168.11.0/24 table eth0 prio 10", true);
        while (!this.isInit) {
            try {
                Pdlog.m3273d(this.TAG, "Slamware init");
                AbstractSlamwarePlatform connect = DeviceManager.connect("192.168.11.1", 1445);
                Intrinsics.checkExpressionValueIsNotNull(connect, "DeviceManager.connect(\"192.168.11.1\", 1445)");
                this.mPlatform = connect;
                this.isInit = true;
                Pdlog.m3273d(this.TAG, "Slamware init finish");
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
                Pdlog.m3274e(this.TAG, "Slamware init exception");
                this.isInit = false;
            }
            Thread.sleep(2000L);
        }
        this.hasInit = true;
        this.thread = new Thread(this.mRunnable);
        Thread thread = this.thread;
        if (thread != null) {
            thread.start();
        }
        RootCmd.execCommand("ip rule add to 192.168.11.0/24 table eth0 prio 10", true);
    }

    public final void setMap(String srcFilePath) {
        if (srcFilePath != null) {
            this.originMap = srcFilePath;
        }
        this.initFinish = false;
        AbstractSlamwarePlatform abstractSlamwarePlatform = this.mPlatform;
        if (abstractSlamwarePlatform == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlatform");
        }
        abstractSlamwarePlatform.clearMap();
        AbstractSlamwarePlatform abstractSlamwarePlatform2 = this.mPlatform;
        if (abstractSlamwarePlatform2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlatform");
        }
        abstractSlamwarePlatform2.setMapUpdate(false);
        this.pose = new Pose(new Location(0.0f, 0.0f, 0.0f), new Rotation(0.0f, 0.0f, 0.0f));
        try {
            if (!new File(this.originMap).exists()) {
                Pdlog.m3274e(this.TAG, "can not find stmc map");
            } else {
                Pdlog.m3273d(this.TAG, "send map to slamware");
                CompositeMap loadFile = new CompositeMapHelper().loadFile(this.originMap);
                Intrinsics.checkExpressionValueIsNotNull(loadFile, "compositeMapHelper.loadFile(originMap)");
                AbstractSlamwarePlatform abstractSlamwarePlatform3 = this.mPlatform;
                if (abstractSlamwarePlatform3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mPlatform");
                }
                abstractSlamwarePlatform3.setCompositeMap(loadFile, this.pose);
            }
        } catch (Exception e) {
            String str = this.TAG;
            e.printStackTrace();
            Pdlog.m3274e(str, Unit.INSTANCE);
        }
        Thread.sleep(20L);
        setPose(0.0f, 0.0f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSum() {
        int length = this.buf.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += this.buf[i2];
        }
        return i;
    }

    private final void initBuf() {
        int length = this.buf.length;
        for (int i = 0; i < length; i++) {
            this.buf[i] = 0;
        }
    }

    public final boolean checkInitFinish() {
        Pdlog.m3273d(this.TAG, "check init finish");
        return this.initFinish;
    }

    public final SlamStatus getSlamStatus() {
        if (this.locateStatus) {
            return SlamStatus.LaserSuccess;
        }
        return SlamStatus.LaserLocateLose;
    }

    /* renamed from: getPose, reason: from getter */
    public final Vector3d getPosition() {
        return this.position;
    }

    public final void setPose(float x, float y, float z) {
        Pdlog.m3273d(this.TAG, "set posex:" + x + "y:" + y + "z:" + z);
        Pose pose = new Pose();
        this.lostFlag = false;
        this.initFinish = false;
        pose.setLocation(new Location(x, y, 0.0f));
        pose.setRotation(new Rotation(z, 0.0f, 0.0f));
        AbstractSlamwarePlatform abstractSlamwarePlatform = this.mPlatform;
        if (abstractSlamwarePlatform == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlatform");
        }
        abstractSlamwarePlatform.setPose(pose);
    }

    public final Runnable getMRunnable() {
        return this.mRunnable;
    }
}
