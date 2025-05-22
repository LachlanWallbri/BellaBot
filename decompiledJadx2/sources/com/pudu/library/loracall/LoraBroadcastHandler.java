package com.pudu.library.loracall;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: LoraBroadcastHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudu/library/loracall/LoraBroadcastHandler;", "", "()V", "mHandler", "Landroid/os/Handler;", "thread", "Landroid/os/HandlerThread;", "removeBroadcast", "", "startBroadcast", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoraBroadcastHandler {
    private static final Handler mHandler;
    public static final LoraBroadcastHandler INSTANCE = new LoraBroadcastHandler();
    private static final HandlerThread thread = new HandlerThread("Lora_timer_thread", 10);

    static {
        thread.start();
        mHandler = new Handler(thread.getLooper(), new Handler.Callback() { // from class: com.pudu.library.loracall.LoraBroadcastHandler.1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                if (message.what == 1) {
                    KotlinUtilsKt.log$default(LoraBroadcastHandler.INSTANCE, null, new Function0<String>() { // from class: com.pudu.library.loracall.LoraBroadcastHandler.1.1
                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "发送机器人状态";
                        }
                    }, 1, null);
                    LoRaHelper.INSTANCE.getOnStateChangeListener().invoke();
                }
                return true;
            }
        });
    }

    private LoraBroadcastHandler() {
    }

    public final void startBroadcast() {
        removeBroadcast();
        mHandler.sendEmptyMessageDelayed(1, 5000L);
    }

    public final void removeBroadcast() {
        mHandler.removeMessages(1);
    }
}
