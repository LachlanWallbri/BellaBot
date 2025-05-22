package com.pudutech.installerserver;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.installerserver.proto.InstallerProto;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class InstallerServer {
    private InputStream inputStream;
    private OutputStream outputStream;
    private ReadThread readThread;
    private SendThread sendThread;
    private Socket server;
    private ServerSocket serverSocket;
    private final String TAG = "InstallerServer";
    private final int BUFF_SIZE = 1024;
    private final int PORT = 16888;
    private byte[] readBuff = new byte[1024];
    private ByteArrayOutputStream readStream = new ByteArrayOutputStream();
    private ByteArrayOutputStream sendStream = new ByteArrayOutputStream();
    private List<byte[]> sendBuffList = new ArrayList();
    private boolean sendLoop = true;
    private boolean readLoop = true;
    private boolean isConnected = false;
    private Boolean installerShow = false;
    private ProductMachineType productMachineType = null;

    public static String getGitHash() {
        return "{\"installer\":\"commit: e1c75c4, auth: “songleiquan”<“songleiquan@pudutech.com”>, time: “Wed Jun 23 18:09:36 2021 +0800”\"}";
    }

    public byte[] int22ByteArray(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean openServerSocket() {
        if (this.serverSocket != null) {
            return true;
        }
        try {
            this.serverSocket = new ServerSocket(16888);
            this.serverSocket.setReuseAddress(true);
            this.serverSocket.setSoTimeout(3000);
            return true;
        } catch (IOException e) {
            Pdlog.m3277w("InstallerServer", "init error: ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
            this.serverSocket = null;
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void disconnectClient() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        Pdlog.m3276v("InstallerServer", "disconnectClient now ", " by ", stackTraceElement.getClassName(), ".", stackTraceElement.getMethodName(), " in line ", Integer.valueOf(stackTraceElement.getLineNumber()));
        this.readStream.reset();
        if (this.server != null && !this.server.isClosed()) {
            try {
                this.server.close();
            } catch (IOException e) {
                Pdlog.m3277w("InstallerServer", "disconnectClient error: ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
            }
        }
        this.server = null;
        this.inputStream = null;
        this.outputStream = null;
        this.isConnected = false;
        this.installerShow = false;
        closeServerSocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void closeServerSocket() {
        if (this.serverSocket != null && !this.serverSocket.isClosed()) {
            try {
                this.serverSocket.close();
            } catch (IOException e) {
                Pdlog.m3277w("InstallerServer", "serverSocket.close error: ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
            }
        }
        this.serverSocket = null;
    }

    private InstallerProto.Vector3 buildCoordinate(double[] dArr) {
        InstallerProto.Vector3.Builder newBuilder = InstallerProto.Vector3.newBuilder();
        try {
            newBuilder.setX(Math.rint(dArr[0] * 1000.0d) / 1000.0d);
            newBuilder.setY(Math.rint(dArr[1] * 1000.0d) / 1000.0d);
            newBuilder.setZ(Math.rint(dArr[2] * 1000.0d) / 1000.0d);
        } catch (Exception e) {
            Pdlog.m3277w("InstallerServer", "error build coordinate", e.getLocalizedMessage());
        }
        return newBuilder.build();
    }

    public boolean init(ProductMachineType productMachineType) {
        this.productMachineType = productMachineType;
        if (productMachineType != null) {
            Pdlog.m3273d("InstallerServer", "machine type is ", this.productMachineType.getModel());
        }
        this.readThread = new ReadThread();
        this.readThread.start();
        this.sendThread = new SendThread();
        this.sendThread.start();
        return true;
    }

    public void resetMachineType(ProductMachineType productMachineType) {
        this.productMachineType = productMachineType;
        if (productMachineType != null) {
            Pdlog.m3273d("InstallerServer", "reset machine type is ", this.productMachineType.getModel());
        }
    }

    public void uninit() {
        this.readLoop = false;
        this.sendLoop = false;
        synchronized (this.sendBuffList) {
            this.sendBuffList.notifyAll();
        }
        disconnectClient();
    }

    public synchronized boolean send(String str) {
        if (this.isConnected && str != null && this.installerShow.booleanValue()) {
            synchronized (this.sendBuffList) {
                this.sendStream.reset();
                try {
                    byte[] bytes = str.getBytes();
                    this.sendStream.write(new byte[]{-81, -83});
                    this.sendStream.write(int22ByteArray(bytes.length));
                    this.sendStream.write(bytes);
                    this.sendBuffList.add(this.sendStream.toByteArray());
                    this.sendBuffList.notifyAll();
                } catch (Exception unused) {
                }
            }
            return true;
        }
        return false;
    }

    public synchronized boolean sendLidarData(PolarCoordinates[] polarCoordinatesArr, double d, double d2, double d3) {
        if (this.isConnected && polarCoordinatesArr != null && this.installerShow.booleanValue()) {
            InstallerProto.InstallerProtocol.Builder newBuilder = InstallerProto.InstallerProtocol.newBuilder();
            try {
                InstallerProto.LidarScan.Builder newBuilder2 = InstallerProto.LidarScan.newBuilder();
                for (PolarCoordinates polarCoordinates : polarCoordinatesArr) {
                    if (polarCoordinates.getDistance_m() >= d3) {
                        double distance_m = ((polarCoordinates.getDistance_m() * Math.cos(polarCoordinates.getAngle_rad())) + d) * 1000.0d;
                        double distance_m2 = ((polarCoordinates.getDistance_m() * Math.sin(polarCoordinates.getAngle_rad())) + d2) * 1000.0d;
                        InstallerProto.LidarPolar.Builder newBuilder3 = InstallerProto.LidarPolar.newBuilder();
                        newBuilder3.setX((int) distance_m);
                        newBuilder3.setY((int) distance_m2);
                        newBuilder2.addScans(newBuilder3.build());
                    }
                }
                newBuilder.setType(InstallerProto.InstallerProtocol.ProType.Lidar);
                newBuilder.setScan(newBuilder2.build());
                Pdlog.m3276v("InstallerServer", "send lidar to installer with size", Integer.valueOf(polarCoordinatesArr.length));
                return send(newBuilder.build().toByteArray());
            } catch (Exception e) {
                Pdlog.m3277w("InstallerServer", "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
                return false;
            }
        }
        return false;
    }

    public synchronized boolean sendRobotPose(double[] dArr, double[] dArr2, double[] dArr3) {
        if (this.isConnected && this.installerShow.booleanValue()) {
            InstallerProto.InstallerProtocol.Builder newBuilder = InstallerProto.InstallerProtocol.newBuilder();
            try {
                newBuilder.setType(InstallerProto.InstallerProtocol.ProType.Pose);
                InstallerProto.RobotPose.Builder newBuilder2 = InstallerProto.RobotPose.newBuilder();
                if (dArr != null) {
                    newBuilder2.setPosition(buildCoordinate(dArr));
                }
                if (dArr2 != null) {
                    newBuilder2.setDirection(buildCoordinate(dArr2));
                }
                newBuilder.setPose(newBuilder2.build());
                Pdlog.m3276v("InstallerServer", "send pose to installer");
                return send(newBuilder.build().toByteArray());
            } catch (Exception e) {
                Pdlog.m3277w("InstallerServer", "sendRobotPose exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
                return false;
            }
        }
        return false;
    }

    private synchronized boolean send(byte[] bArr) {
        if (this.isConnected && bArr != null && this.installerShow.booleanValue()) {
            synchronized (this.sendBuffList) {
                this.sendStream.reset();
                try {
                    this.sendStream.write(new byte[]{-81, -83});
                    this.sendStream.write(int22ByteArray(bArr.length));
                    this.sendStream.write(bArr);
                    this.sendBuffList.add(this.sendStream.toByteArray());
                    this.sendBuffList.notifyAll();
                } catch (Exception unused) {
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseBytes(int i, byte[] bArr) {
        if (i <= 0 || i > bArr.length) {
            return;
        }
        int i2 = 0;
        if (this.readStream.size() != 0) {
            this.readStream.write(bArr, 0, i);
            byte[] byteArray = this.readStream.toByteArray();
            this.readStream.reset();
            parseBytes(byteArray.length, byteArray);
            return;
        }
        if (i <= 4) {
            return;
        }
        do {
            if (bArr[i2] == -81 && bArr[i2 + 1] == -83) {
                int i3 = i2 + 2;
                int i4 = i3 + 2;
                int byteArray2Int = byteArray2Int(Arrays.copyOfRange(bArr, i3, i4));
                if (i < byteArray2Int + i4) {
                    this.readStream.write(bArr, i4, i - i4);
                    return;
                } else {
                    parseMsg(bArr, i4, byteArray2Int, i);
                    return;
                }
            }
            i2++;
        } while (i2 != i - 1);
    }

    private void parseMsg(byte[] bArr, int i, int i2, int i3) {
        try {
            try {
                this.readStream.write(bArr, i, i2);
                InstallerProto.InstallerProtocol.Builder newBuilder = InstallerProto.InstallerProtocol.newBuilder();
                newBuilder.mergeFrom(this.readStream.toByteArray());
                InstallerProto.InstallerProtocol build = newBuilder.build();
                Pdlog.m3276v("InstallerServer", "installer cmd type", Integer.valueOf(build.getType().getNumber()));
                int i4 = C46231.f5338x488aac89[build.getType().ordinal()];
                if (i4 == 1) {
                    Pdlog.m3276v("InstallerServer", "installer cmd id", Boolean.valueOf(build.getCmd().getShow()));
                    synchronized (this.installerShow) {
                        this.installerShow = Boolean.valueOf(build.getCmd().getShow());
                    }
                } else if (i4 == 2 && this.productMachineType != null) {
                    InstallerProto.InstallerProtocol.Builder newBuilder2 = InstallerProto.InstallerProtocol.newBuilder();
                    newBuilder2.setType(InstallerProto.InstallerProtocol.ProType.Product);
                    newBuilder2.setPro(this.productMachineType.getModel().getId());
                    newBuilder2.setMainVersion(this.productMachineType.getMainVersion());
                    boolean booleanValue = this.installerShow.booleanValue();
                    synchronized (this.installerShow) {
                        this.installerShow = true;
                    }
                    send(newBuilder2.build().toByteArray());
                    synchronized (this.installerShow) {
                        this.installerShow = Boolean.valueOf(booleanValue);
                    }
                }
            } catch (Exception e) {
                Pdlog.m3277w("InstallerServer", "parse protobuf exception", e.getLocalizedMessage());
            }
            this.readStream.reset();
            int i5 = i + i2;
            int i6 = i3 - i5;
            if (i6 > 0) {
                parseBytes(i6, Arrays.copyOfRange(bArr, i5, i3));
            }
        } catch (Throwable th) {
            this.readStream.reset();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: com.pudutech.installerserver.InstallerServer$1 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C46231 {

        /* renamed from: $SwitchMap$com$pudutech$installerserver$proto$InstallerProto$InstallerProtocol$ProType */
        static final /* synthetic */ int[] f5338x488aac89 = new int[InstallerProto.InstallerProtocol.ProType.values().length];

        static {
            try {
                f5338x488aac89[InstallerProto.InstallerProtocol.ProType.Cmd.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5338x488aac89[InstallerProto.InstallerProtocol.ProType.Product.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public int byteArray2Int(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        int i2 = length;
        while (i2 > 0) {
            int i3 = bArr[i2 - 1] & 255;
            for (int i4 = length - i2; i4 > 0; i4--) {
                i3 <<= 8;
            }
            i2--;
            i |= i3;
        }
        return i;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    class ReadThread extends Thread {
        public ReadThread() {
            super("InstallRead");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (InstallerServer.this.readLoop) {
                try {
                    if (InstallerServer.this.openServerSocket()) {
                        while (!InstallerServer.this.isConnected) {
                            Pdlog.m3276v("InstallerServer", "wait a client connect");
                            try {
                                if (InstallerServer.this.serverSocket != null) {
                                    InstallerServer.this.server = InstallerServer.this.serverSocket.accept();
                                }
                                InstallerServer.this.isConnected = true;
                                InstallerServer.this.closeServerSocket();
                                Pdlog.m3276v("InstallerServer", "accept a client: ", InstallerServer.this.server.getInetAddress().toString());
                            } catch (SocketTimeoutException unused) {
                            }
                        }
                        if (!InstallerServer.this.readLoop) {
                            InstallerServer.this.disconnectClient();
                        } else {
                            InstallerServer.this.inputStream = InstallerServer.this.server.getInputStream();
                            InstallerServer.this.outputStream = InstallerServer.this.server.getOutputStream();
                            InstallerServer.this.readStream.reset();
                            while (InstallerServer.this.isConnected) {
                                int read = InstallerServer.this.inputStream.read(InstallerServer.this.readBuff);
                                if (read > 0) {
                                    InstallerServer.this.parseBytes(read, InstallerServer.this.readBuff);
                                    Pdlog.m3276v("InstallerServer", "recv from installer msg length", Integer.valueOf(read));
                                } else {
                                    InstallerServer.this.disconnectClient();
                                    Pdlog.m3276v("InstallerServer", "iRet is ", Integer.valueOf(read), " and close server socket.");
                                }
                            }
                        }
                    } else {
                        Pdlog.m3276v("InstallerServer", "open server socket failed!");
                        try {
                            sleep(5000L);
                        } catch (InterruptedException e) {
                            Pdlog.m3277w("InstallerServer", "sleep error: ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
                        }
                    }
                } catch (IOException e2) {
                    Pdlog.m3277w("InstallerServer", "ReadThread error: ", e2.getLocalizedMessage(), " :", Log.getStackTraceString(e2));
                    try {
                        InstallerServer.this.disconnectClient();
                        sleep(500L);
                    } catch (InterruptedException e3) {
                        Pdlog.m3277w("InstallerServer", "ReadThread sleep error: ", e3.getLocalizedMessage(), " :", Log.getStackTraceString(e3));
                    }
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    class SendThread extends Thread {
        public SendThread() {
            super("InstallSend");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            loop0: while (InstallerServer.this.sendLoop) {
                while (InstallerServer.this.sendBuffList.isEmpty() && InstallerServer.this.sendLoop) {
                    try {
                        synchronized (InstallerServer.this.sendBuffList) {
                            InstallerServer.this.sendBuffList.wait();
                        }
                    } catch (InterruptedException e) {
                        Pdlog.m3277w("InstallerServer", "SendThread error:", e.toString());
                    }
                }
                if (!InstallerServer.this.sendLoop) {
                    return;
                }
                for (int i = 0; i < InstallerServer.this.sendBuffList.size(); i++) {
                    byte[] bArr = (byte[]) InstallerServer.this.sendBuffList.remove(0);
                    if (bArr != null) {
                        if (bArr.length > 0) {
                            if (InstallerServer.this.outputStream != null) {
                                try {
                                    InstallerServer.this.outputStream.write(bArr);
                                } catch (IOException e2) {
                                    Pdlog.m3277w("InstallerServer", "outputStream write error: ", e2.toString());
                                }
                            } else {
                                Pdlog.m3277w("InstallerServer", "outputStream is null, isConnected: ", Boolean.valueOf(InstallerServer.this.isConnected));
                            }
                        }
                    } else {
                        Pdlog.m3277w("InstallerServer", "send data is null");
                    }
                }
            }
        }
    }
}
