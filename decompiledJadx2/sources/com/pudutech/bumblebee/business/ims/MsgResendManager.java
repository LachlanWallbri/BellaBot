package com.pudutech.bumblebee.business.ims;

import android.util.Log;
import com.pudutech.bumblebee.business.ims.listener.IMsgSentStatusListener;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class MsgResendManager extends Thread {
    private static final String TAG = MsgResendManager.class.getSimpleName();
    private CopyOnWriteArrayList<CMsg> mMsgList = new CopyOnWriteArrayList<>();
    private boolean isActive = false;
    private Map<String, Integer> mResendCountMap = new ConcurrentHashMap();
    private Map<String, IMsgSentStatusListener> mOnMsgSentStatusListenerMap = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class CMsg {
        private MessageProtobuf.Msg msg;
        private long sendTime;

        private CMsg() {
        }
    }

    public void active() {
        this.isActive = true;
    }

    public void inactive() {
        this.isActive = false;
    }

    public void release() {
        inactive();
        this.mMsgList.clear();
        this.mMsgList = null;
        this.mResendCountMap.clear();
        this.mResendCountMap = null;
        this.mOnMsgSentStatusListenerMap.clear();
        this.mOnMsgSentStatusListenerMap = null;
        interrupt();
    }

    public void addMsg(MessageProtobuf.Msg msg, IMsgSentStatusListener iMsgSentStatusListener) {
        if (msg == null) {
            return;
        }
        synchronized (this) {
            if (!containsMsg(msg.getMsgId())) {
                CMsg cMsg = new CMsg();
                cMsg.msg = msg;
                cMsg.sendTime = System.currentTimeMillis();
                this.mMsgList.add(cMsg);
                this.mResendCountMap.put(msg.getMsgId(), 0);
                this.mOnMsgSentStatusListenerMap.put(msg.getMsgId(), iMsgSentStatusListener);
            }
            Log.d(TAG, "notify...");
            notify();
        }
    }

    public void removeMsg(String str) {
        CMsg findMsgByMsgId = findMsgByMsgId(str);
        if (findMsgByMsgId != null && containsMsg(str)) {
            this.mMsgList.remove(findMsgByMsgId);
            this.mResendCountMap.remove(str);
            this.mOnMsgSentStatusListenerMap.remove(str);
        }
    }

    private CMsg findMsgByMsgId(String str) {
        CopyOnWriteArrayList<CMsg> copyOnWriteArrayList;
        if (str != null && !str.isEmpty() && (copyOnWriteArrayList = this.mMsgList) != null && !copyOnWriteArrayList.isEmpty()) {
            Iterator<CMsg> it = this.mMsgList.iterator();
            while (it.hasNext()) {
                CMsg next = it.next();
                if (str.equals(next.msg.getMsgId())) {
                    return next;
                }
            }
        }
        return null;
    }

    public IMsgSentStatusListener findMsgSentStatusListenerByMsgId(String str) {
        if (str != null && !str.isEmpty()) {
            for (Map.Entry<String, IMsgSentStatusListener> entry : this.mOnMsgSentStatusListenerMap.entrySet()) {
                if (str.equals(entry.getKey())) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    private boolean containsMsg(String str) {
        if (str != null && !str.isEmpty()) {
            Iterator<CMsg> it = this.mMsgList.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().msg.getMsgId())) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getResendCountByMsgId(String str) {
        if (str != null && !str.isEmpty()) {
            for (Map.Entry<String, Integer> entry : this.mResendCountMap.entrySet()) {
                if (str.equals(entry.getKey())) {
                    return entry.getValue().intValue();
                }
            }
        }
        return -1;
    }

    private void updateMsgResendCount(String str, int i) {
        this.mResendCountMap.put(str, Integer.valueOf(i));
    }

    private void updateMsgSendTime(String str, long j) {
        CMsg findMsgByMsgId;
        int i;
        if (str == null || str.isEmpty() || (findMsgByMsgId = findMsgByMsgId(str)) == null) {
            return;
        }
        findMsgByMsgId.sendTime = j;
        Iterator<CMsg> it = this.mMsgList.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            CMsg next = it.next();
            if (findMsgByMsgId.msg.getMsgId().equals(next.msg.getMsgId())) {
                i = this.mMsgList.indexOf(next);
                break;
            }
        }
        if (i == -1) {
            return;
        }
        this.mMsgList.set(i, findMsgByMsgId);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int resendCountByMsgId;
        while (!isInterrupted()) {
            try {
                if (!this.isActive || this.mMsgList == null || this.mMsgList.isEmpty()) {
                    synchronized (this) {
                        Log.d(TAG, "wait...");
                        wait();
                    }
                }
                sleep(500L);
                Iterator<CMsg> it = this.mMsgList.iterator();
                while (it.hasNext()) {
                    CMsg next = it.next();
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - next.sendTime > 2000 && (resendCountByMsgId = getResendCountByMsgId(next.msg.getMsgId())) != -1) {
                        if (resendCountByMsgId < Integer.MAX_VALUE) {
                            int i = resendCountByMsgId + 1;
                            Log.d(TAG, "Retransmission message, msg = \n" + next.msg + "\nresendCount = " + i);
                            updateMsgSendTime(next.msg.getMsgId(), currentTimeMillis);
                            updateMsgResendCount(next.msg.getMsgId(), i);
                            IMSClientFactory.INSTANCE.getIMSClient().sendMsg(next.msg, false, findMsgSentStatusListenerByMsgId(next.msg.getMsgId()));
                        } else {
                            Log.w(TAG, "Msg sent failure, the maximum number of retransmissions has been reached. msg = \n" + next.msg);
                            removeMsg(next.msg.getMsgId());
                            IMsgSentStatusListener findMsgSentStatusListenerByMsgId = findMsgSentStatusListenerByMsgId(next.msg.getMsgId());
                            if (findMsgSentStatusListenerByMsgId != null) {
                                findMsgSentStatusListenerByMsgId.onSentFailed(next.msg, "Msg sent failed, the maximum number of retransmissions has been reached.");
                                this.mOnMsgSentStatusListenerMap.remove(next.msg.getMsgId());
                            }
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Log.e(TAG, "MsgResendManager stop while.");
                return;
            }
        }
    }
}
