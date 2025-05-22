package com.aliyun.alink.linksdk.tools.log;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RealTimeLogStrategy implements ILogStrategy {
    private static final String TAG = "RealTimeLogStrategy";
    private Handler mHandler;

    @Override // com.aliyun.alink.linksdk.tools.log.ILogStrategy
    public boolean isSupport() {
        return true;
    }

    public RealTimeLogStrategy(Handler handler) {
        this.mHandler = null;
        this.mHandler = handler;
    }

    @Override // com.aliyun.alink.linksdk.tools.log.ILogStrategy
    public void log(int i, String str, String str2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(i, new LogModel(str, str2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class LogHandler extends Handler {
        /* JADX INFO: Access modifiers changed from: package-private */
        public LogHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null || !(message.obj instanceof LogModel)) {
                Log.w(RealTimeLogStrategy.TAG, "handleMessage msg=null||msg.obj not LogModel.");
            } else {
                LogModel logModel = (LogModel) message.obj;
                ALog.getLogUpload().realTimeUpload(message.what, logModel.tag, logModel.message);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class LogModel {
        public String message;
        public String tag;

        LogModel(String str, String str2) {
            this.tag = str;
            this.message = str2;
        }
    }
}
