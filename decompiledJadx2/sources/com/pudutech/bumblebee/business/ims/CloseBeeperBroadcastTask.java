package com.pudutech.bumblebee.business.ims;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.config.MsgType;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public class CloseBeeperBroadcastTask extends Thread {
    private static final String TAG = CloseBeeperBroadcastTask.class.getSimpleName();
    private boolean isActive = false;
    private Function1<MessageProtobuf.Msg, Unit> onSendSucceed = new Function1<MessageProtobuf.Msg, Unit>() { // from class: com.pudutech.bumblebee.business.ims.CloseBeeperBroadcastTask.1
        @Override // kotlin.jvm.functions.Function1
        public Unit invoke(MessageProtobuf.Msg msg) {
            CloseBeeperBroadcastTask.this.inactive();
            return null;
        }
    };

    public void active() {
        this.isActive = true;
        synchronized (this) {
            notify();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inactive() {
        this.isActive = false;
    }

    public void release() {
        inactive();
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!isInterrupted()) {
            try {
                if (!this.isActive) {
                    synchronized (this) {
                        Pdlog.m3273d(TAG, "停止关闭呼叫器广播");
                        wait();
                    }
                }
                Pdlog.m3273d(TAG, "开始关闭呼叫器广播");
                sendCloseBroadcastMsg();
                Thread.sleep(100L);
                Pdlog.m3273d(TAG, "持续关闭呼叫器广播中...");
            } catch (InterruptedException unused) {
                return;
            }
        }
    }

    private void sendCloseBroadcastMsg() {
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = IMSKit.INSTANCE.getInstance().generateCommonMsgBuilder(MsgType.AddBeeperBroadcastControl);
        if (generateCommonMsgBuilder != null) {
            IMSKit.INSTANCE.getInstance().sendMsg(generateCommonMsgBuilder.setBroadcastControl(MessageProtobuf.BroadcastControl.newBuilder().setEnable(false).build()).build(), this.onSendSucceed, null, false, true);
        }
    }
}
