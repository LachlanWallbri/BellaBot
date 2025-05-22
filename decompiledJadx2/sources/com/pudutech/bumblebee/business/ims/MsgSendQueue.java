package com.pudutech.bumblebee.business.ims;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.config.IMSConfig;
import com.pudutech.bumblebee.business.ims.interf.I_IMSClient;
import com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2;
import com.pudutech.bumblebee.business.ims.lora.LoRaClient;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class MsgSendQueue extends Thread {
    private static final String TAG = MsgSendQueue.class.getSimpleName();
    private I_IMSClient mIMSClient;
    private boolean isActive = false;
    private List<MessageProtobuf.Msg> mMsgList = new CopyOnWriteArrayList();

    public MsgSendQueue(LoRaClient loRaClient) {
        this.mIMSClient = loRaClient;
    }

    public void active() {
        this.isActive = true;
    }

    public void inactive() {
        this.isActive = false;
    }

    public void addMsg(MessageProtobuf.Msg msg) {
        if (msg == null) {
            return;
        }
        synchronized (this) {
            if (!containsMsg(msg.getMsgId())) {
                this.mMsgList.add(msg);
                Pdlog.m3273d(TAG, "notify...");
                notify();
            }
        }
    }

    private void removeMsg(MessageProtobuf.Msg msg) {
        if (msg == null) {
            return;
        }
        if (containsMsg(msg.getMsgId())) {
            this.mMsgList.remove(msg);
        }
        Pdlog.m3273d(TAG, "size = " + this.mMsgList.size());
    }

    public void release() {
        inactive();
        List<MessageProtobuf.Msg> list = this.mMsgList;
        if (list != null && list.size() > 0) {
            this.mMsgList.clear();
        }
        this.mMsgList = null;
        interrupt();
    }

    private boolean containsMsg(String str) {
        List<MessageProtobuf.Msg> list;
        if (str != null && !str.isEmpty() && (list = this.mMsgList) != null && !list.isEmpty()) {
            Iterator<MessageProtobuf.Msg> it = this.mMsgList.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().getMsgId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!isInterrupted()) {
            try {
                if (!this.isActive || this.mMsgList == null || this.mMsgList.size() == 0) {
                    synchronized (this) {
                        Pdlog.m3273d(TAG, "wait...");
                        wait();
                    }
                }
                for (MessageProtobuf.Msg msg : this.mMsgList) {
                    try {
                        sendMsg(msg);
                        sleep(1500L);
                        removeMsg(msg);
                    } catch (Throwable th) {
                        removeMsg(msg);
                        throw th;
                    }
                }
                Pdlog.m3273d(TAG, "running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void sendMsg(MessageProtobuf.Msg msg) {
        try {
            byte[] byteArray = msg.toByteArray();
            int length = byteArray.length;
            ByteBuffer allocate = ByteBuffer.allocate(IMSConfig.FRAME_HEAD.length + 1 + length);
            allocate.put(IMSConfig.FRAME_HEAD);
            allocate.put((byte) (length & 255));
            allocate.put(byteArray);
            if (this.mIMSClient instanceof LoRaClient) {
                Peripherals.INSTANCE.getIms().sendMsg(allocate.array());
                Pdlog.m3273d(TAG, "sendMsg() \nlength = " + allocate.array().length + "\nchannel = " + LoRaChannelManager2.INSTANCE.getINSTANCE().getCurrentChannel() + "\ndata = " + Arrays.toString(allocate.array()) + "\nmsg = " + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
