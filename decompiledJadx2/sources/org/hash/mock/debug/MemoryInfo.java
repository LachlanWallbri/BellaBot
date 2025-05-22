package org.hash.mock.debug;

import android.os.Debug;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.pudutech.mirsdk.SolicitService;
import java.text.DecimalFormat;
import org.hash.mock.debug.manager.DebugTools;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class MemoryInfo {
    private static final int UPDATE_MEMORY = 1;
    private static volatile MemoryInfo mInstance;
    private Handler mCheckMemoryHandler;

    private MemoryInfo() {
    }

    public static MemoryInfo getInstance() {
        if (mInstance == null) {
            synchronized (MemoryInfo.class) {
                if (mInstance == null) {
                    mInstance = new MemoryInfo();
                }
            }
        }
        return mInstance;
    }

    public void start() {
        HandlerThread handlerThread = new HandlerThread("check-memory");
        handlerThread.start();
        this.mCheckMemoryHandler = new Handler(handlerThread.getLooper()) { // from class: org.hash.mock.debug.MemoryInfo.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                MemoryInfo.this.checkMemory();
                MemoryInfo.this.mCheckMemoryHandler.sendEmptyMessageDelayed(1, SolicitService.CAMERA_OPEN_TIME_OUT);
            }
        };
        this.mCheckMemoryHandler.sendEmptyMessageDelayed(1, SolicitService.CAMERA_OPEN_TIME_OUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkMemory() {
        if (DebugTools.get().isMemoryInfoShow()) {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            String str = "";
            float f = 0.5f;
            try {
                Integer valueOf = Integer.valueOf(memoryInfo.dalvikPrivateDirty);
                str = new DecimalFormat("#.##").format(valueOf.intValue() / 1024.0d) + "M";
                f = valueOf.intValue() / memoryInfo.getTotalPss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DebugTools.get().updateInfo(str, f);
        }
    }
}
