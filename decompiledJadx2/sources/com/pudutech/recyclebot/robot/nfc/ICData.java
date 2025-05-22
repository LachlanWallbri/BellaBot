package com.pudutech.recyclebot.robot.nfc;

import android.content.Context;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.pudutech.base.Pdlog;
import com.pudutech.recyclebot.robot.aidl.serialize.RecycleWorkerType;
import com.pudutech.recyclebot.robot.nfc.RFIDReader;
import com.pudutech.shared.lib_syntime.SystemTimeSetting;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* loaded from: classes2.dex */
public class ICData implements RFIDReader.OnUIDListener {
    public NFCListener listener;
    private final String TAG = "NFC";
    private RFIDReader rfidReader = new RFIDReader();
    private String fileName = "";
    private RecycleWorkerType workerType = RecycleWorkerType.Idle;
    private boolean enableNFC = false;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public interface NFCListener {
        void WorkerID(String str);
    }

    public void open(Context context, NFCListener nFCListener) {
        this.listener = nFCListener;
        this.rfidReader.open(context, this);
    }

    public void enableNFC(RecycleWorkerType recycleWorkerType) {
        if (recycleWorkerType == RecycleWorkerType.Idle) {
            return;
        }
        this.workerType = recycleWorkerType;
        this.enableNFC = true;
    }

    public void disableNFC() {
        this.enableNFC = false;
    }

    @Override // com.pudutech.recyclebot.robot.nfc.RFIDReader.OnUIDListener
    public void onSwipe(byte[] bArr, int i) {
        NFCListener nFCListener;
        StringBuilder sb;
        if (i < 4) {
            Pdlog.m3274e("NFC", "bufferLength is " + i + " lower than 4");
            return;
        }
        if (this.fileName.equals("")) {
            this.fileName = "sdcard/Pudu/ICData." + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(System.currentTimeMillis() + 28800000)) + ".csv";
            if (!new File(this.fileName).exists() && !initDataFile()) {
                Pdlog.m3274e("NFC", "file not exist, initDataFile failed!");
                return;
            }
        }
        if (!this.enableNFC) {
            Pdlog.m3275i("NFC", "Recycle Task not enabled to Record NFC data");
            return;
        }
        String format = new SimpleDateFormat(SystemTimeSetting.TIME_FORMAT).format(new Date(System.currentTimeMillis() + 28800000));
        long j = ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (255 & bArr[3]);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(format);
        sb2.append(",");
        sb2.append(j);
        sb2.append(",");
        int i2 = C56301.f7074xd7e70368[this.workerType.ordinal()];
        if (i2 == 1) {
            sb2.append("前厅收盘服务员");
        } else if (i2 != 2) {
            return;
        } else {
            sb2.append("洗碗间收盘服务员");
        }
        try {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName, true), "utf-8"));
                bufferedWriter.write(sb2.toString());
                bufferedWriter.newLine();
                bufferedWriter.close();
                Runtime.getRuntime().exec(InvokeServiceData.CALL_TYPE_SYNC);
                Pdlog.m3273d("NFC", "show ICNumber", Long.valueOf(j));
                nFCListener = this.listener;
                sb = new StringBuilder();
            } catch (IOException e) {
                Pdlog.m3274e("NFC", "saveData exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
                Pdlog.m3273d("NFC", "show ICNumber", Long.valueOf(j));
                nFCListener = this.listener;
                sb = new StringBuilder();
            }
            sb.append("");
            sb.append(j);
            nFCListener.WorkerID(sb.toString());
        } catch (Throwable th) {
            Pdlog.m3273d("NFC", "show ICNumber", Long.valueOf(j));
            this.listener.WorkerID("" + j);
            throw th;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* renamed from: com.pudutech.recyclebot.robot.nfc.ICData$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C56301 {

        /* renamed from: $SwitchMap$com$pudutech$recyclebot$robot$aidl$serialize$RecycleWorkerType */
        static final /* synthetic */ int[] f7074xd7e70368;

        static {
            int[] iArr = new int[RecycleWorkerType.values().length];
            f7074xd7e70368 = iArr;
            try {
                iArr[RecycleWorkerType.Antechamber.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7074xd7e70368[RecycleWorkerType.DishWashingRoom.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean initDataFile() {
        String[] split = "sdcard/Pudu".split(File.separator);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : split) {
            stringBuffer.append(str);
            stringBuffer.append(File.separator);
            File file = new File(stringBuffer.toString());
            if (!file.exists() && !file.mkdir()) {
                Pdlog.m3274e("NFC", "mkdir " + stringBuffer.toString() + " failed!");
                return false;
            }
        }
        File file2 = new File(this.fileName);
        if (!file2.exists()) {
            try {
                if (!file2.createNewFile()) {
                    Pdlog.m3274e("NFC", "createNewFile " + this.fileName + " failed!");
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                Pdlog.m3274e("NFC", "createNewFile error: " + e.toString());
                return false;
            }
        }
        if (file2.length() == 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.fileName);
                fileOutputStream.write(new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_1, -69, ByteSourceJsonBootstrapper.UTF8_BOM_3});
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));
                bufferedWriter.write("时间,编号,工种");
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileOutputStream.close();
                Runtime.getRuntime().exec(InvokeServiceData.CALL_TYPE_SYNC);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                Pdlog.m3274e("NFC", "FileNotFoundException:" + e2.toString());
                return false;
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                Pdlog.m3274e("NFC", "UnsupportedEncodingException:" + e3.toString());
                return false;
            } catch (IOException e4) {
                e4.printStackTrace();
                Pdlog.m3274e("NFC", "IOException out.write:" + e4.toString());
                return false;
            }
        }
        return true;
    }
}
