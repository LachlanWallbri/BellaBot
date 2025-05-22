package com.aliyun.alink.linksdk.tools.log;

import android.os.Handler;
import android.os.HandlerThread;
import com.aliyun.alink.linksdk.tools.log.RealTimeLogStrategy;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LogStrategyFactory {
    private Handler fileHandler;
    private HandlerThread handlerThread;
    private Handler realTimeHandler;
    private ILogStrategy strategy;

    /* synthetic */ LogStrategyFactory(C11411 c11411) {
        this();
    }

    private LogStrategyFactory() {
        this.strategy = null;
        this.handlerThread = null;
        this.realTimeHandler = null;
        this.fileHandler = null;
        HandlerThread handlerThread = new HandlerThread("LogHandlerThread");
        this.handlerThread = handlerThread;
        handlerThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class SingletoHolder {
        static final LogStrategyFactory INSTANCE = new LogStrategyFactory(null);

        private SingletoHolder() {
        }
    }

    public static LogStrategyFactory getInstance() {
        return SingletoHolder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.tools.log.LogStrategyFactory$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C11411 {
        static final /* synthetic */ int[] $SwitchMap$com$aliyun$alink$linksdk$tools$log$LogStrategyType;

        static {
            int[] iArr = new int[LogStrategyType.values().length];
            $SwitchMap$com$aliyun$alink$linksdk$tools$log$LogStrategyType = iArr;
            try {
                iArr[LogStrategyType.LOGCAT_STRATEGY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$aliyun$alink$linksdk$tools$log$LogStrategyType[LogStrategyType.REALTIME_STRATEGY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ILogStrategy createStrategy(LogStrategyType logStrategyType) {
        int i = C11411.$SwitchMap$com$aliyun$alink$linksdk$tools$log$LogStrategyType[logStrategyType.ordinal()];
        if (i == 1) {
            this.strategy = new LogcatLogStrategy();
        } else if (i == 2) {
            if (this.realTimeHandler == null) {
                this.realTimeHandler = new RealTimeLogStrategy.LogHandler(this.handlerThread.getLooper());
            }
            this.strategy = new RealTimeLogStrategy(this.realTimeHandler);
        } else {
            this.strategy = new LogcatLogStrategy();
        }
        return this.strategy;
    }
}
