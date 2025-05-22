package com.pudu.library.loracall;

import com.pudutech.serialport.library.SerialPortHelper;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: MsgSendHandle.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eJ\b\u0010\u0017\u001a\u00020\u0015H\u0017J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u000eJ\u0006\u0010\u001a\u001a\u00020\u0015R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, m3961d2 = {"Lcom/pudu/library/loracall/MsgSendHandle;", "Ljava/lang/Thread;", "serialPortHelper", "Lcom/pudutech/serialport/library/SerialPortHelper;", "(Lcom/pudutech/serialport/library/SerialPortHelper;)V", ES6Iterator.VALUE_PROPERTY, "", "frameNumber", "setFrameNumber", "(I)V", "lock", "Ljava/lang/Object;", "msgQueue", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudu/library/loracall/SlipMsgBean;", "slipMsgAck", "getSlipMsgAck", "()Lcom/pudu/library/loracall/SlipMsgBean;", "setSlipMsgAck", "(Lcom/pudu/library/loracall/SlipMsgBean;)V", "receiveAck", "", "slipMsgBean", "run", "sendMsg", "slipMsg", "stopHandle", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MsgSendHandle extends Thread {
    private volatile int frameNumber;
    private final Object lock;
    private final CopyOnWriteArrayList<SlipMsgBean> msgQueue;
    private final SerialPortHelper serialPortHelper;
    private volatile SlipMsgBean slipMsgAck;

    public MsgSendHandle(SerialPortHelper serialPortHelper) {
        Intrinsics.checkParameterIsNotNull(serialPortHelper, "serialPortHelper");
        this.serialPortHelper = serialPortHelper;
        this.lock = new Object();
        this.msgQueue = new CopyOnWriteArrayList<>();
    }

    public final SlipMsgBean getSlipMsgAck() {
        return this.slipMsgAck;
    }

    public final void setSlipMsgAck(SlipMsgBean slipMsgBean) {
        this.slipMsgAck = slipMsgBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFrameNumber(int i) {
        if (i > 255) {
            i = 0;
        }
        this.frameNumber = i;
    }

    public final void sendMsg(SlipMsgBean slipMsg) {
        Intrinsics.checkParameterIsNotNull(slipMsg, "slipMsg");
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgSendHandle$sendMsg$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                CopyOnWriteArrayList copyOnWriteArrayList;
                StringBuilder sb = new StringBuilder();
                sb.append("sendMsg msgQueue:");
                copyOnWriteArrayList = MsgSendHandle.this.msgQueue;
                sb.append(copyOnWriteArrayList.size());
                return sb.toString();
            }
        }, 1, null);
        BuildersKt__Builders_commonKt.launch$default(KotlinUtilsKt.getMyScope(), null, null, new MsgSendHandle$sendMsg$2(this, slipMsg, null), 3, null);
    }

    public final void stopHandle() {
        interrupt();
        this.msgQueue.clear();
    }

    public final void receiveAck(SlipMsgBean slipMsgBean) {
        Intrinsics.checkParameterIsNotNull(slipMsgBean, "slipMsgBean");
        synchronized (this.lock) {
            this.slipMsgAck = slipMsgBean;
            this.lock.notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!isInterrupted()) {
            try {
                synchronized (this.lock) {
                    KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgSendHandle$run$$inlined$synchronized$lambda$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            CopyOnWriteArrayList copyOnWriteArrayList;
                            StringBuilder sb = new StringBuilder();
                            sb.append("发送队列 msgQueue ：");
                            copyOnWriteArrayList = MsgSendHandle.this.msgQueue;
                            sb.append(copyOnWriteArrayList.size());
                            return sb.toString();
                        }
                    }, 1, null);
                    if (this.msgQueue.isEmpty()) {
                        this.lock.wait();
                    }
                    final SlipMsgBean msg = (SlipMsgBean) CollectionsKt.first((List) this.msgQueue);
                    boolean z = msg.getFrameNumber() == 0;
                    while (msg.getCount() > 0) {
                        if (msg.getType() != 5 && z) {
                            long currentTimeMillis = System.currentTimeMillis() + 500;
                            if (z) {
                                msg.setFrameNumber((byte) this.frameNumber);
                            }
                            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgSendHandle$run$1$4
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final String invoke() {
                                    return "消息发送：" + SlipMsgBean.this;
                                }
                            }, 1, null);
                            SerialPortHelper serialPortHelper = this.serialPortHelper;
                            SlipTool slipTool = SlipTool.INSTANCE;
                            Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
                            serialPortHelper.writeData(slipTool.msgToSlip(msg));
                            this.lock.wait(500L);
                            final long currentTimeMillis2 = currentTimeMillis - System.currentTimeMillis();
                            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgSendHandle$run$1$5
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final String invoke() {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("消息发送是否超时：");
                                    sb.append(currentTimeMillis2 < 0);
                                    sb.append(' ');
                                    sb.append("相差时间: ");
                                    sb.append(currentTimeMillis2);
                                    sb.append(" 重试次数：");
                                    sb.append(3 - msg.getCount());
                                    return sb.toString();
                                }
                            }, 1, null);
                            if (currentTimeMillis2 < 0) {
                                msg.setCount(msg.getCount() - 1);
                                Function1<SlipMsgBean, Unit> callback = msg.getCallback();
                                if (callback != null && msg.getCount() <= 0) {
                                    callback.invoke(new SlipMsgBean());
                                }
                            } else {
                                msg.setCount(0);
                                Function1<SlipMsgBean, Unit> callback2 = msg.getCallback();
                                if (callback2 != null) {
                                    String valueOf = String.valueOf((int) msg.getId());
                                    SlipMsgBean slipMsgBean = this.slipMsgAck;
                                    if (Intrinsics.areEqual(valueOf, slipMsgBean != null ? String.valueOf((int) slipMsgBean.getId()) : null)) {
                                        String valueOf2 = String.valueOf((int) msg.getFrameNumber());
                                        SlipMsgBean slipMsgBean2 = this.slipMsgAck;
                                        if (Intrinsics.areEqual(valueOf2, String.valueOf(slipMsgBean2 != null ? Byte.valueOf(slipMsgBean2.getFrameNumber()) : null))) {
                                            SlipMsgBean slipMsgBean3 = this.slipMsgAck;
                                            if (slipMsgBean3 == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            callback2.invoke(slipMsgBean3);
                                        }
                                    }
                                    callback2.invoke(new SlipMsgBean());
                                }
                                this.slipMsgAck = (SlipMsgBean) null;
                            }
                        }
                        if (z) {
                            msg.setFrameNumber((byte) this.frameNumber);
                        }
                        if (z) {
                            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgSendHandle$run$1$2
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final String invoke() {
                                    return "消息发送不需要回应：" + SlipMsgBean.this;
                                }
                            }, 1, null);
                        } else {
                            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgSendHandle$run$1$3
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final String invoke() {
                                    return "回应应答：" + SlipMsgBean.this;
                                }
                            }, 1, null);
                        }
                        SerialPortHelper serialPortHelper2 = this.serialPortHelper;
                        SlipTool slipTool2 = SlipTool.INSTANCE;
                        Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
                        serialPortHelper2.writeData(slipTool2.msgToSlip(msg));
                        msg.setCount(0);
                    }
                    CollectionsKt.removeFirst(this.msgQueue);
                    if (z) {
                        setFrameNumber(this.frameNumber + 1);
                    }
                    KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgSendHandle$run$$inlined$synchronized$lambda$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            int i;
                            CopyOnWriteArrayList copyOnWriteArrayList;
                            StringBuilder sb = new StringBuilder();
                            sb.append("消息发送成功 frameNumber  :");
                            i = MsgSendHandle.this.frameNumber;
                            sb.append(i);
                            sb.append("  msgQueue ");
                            copyOnWriteArrayList = MsgSendHandle.this.msgQueue;
                            sb.append(copyOnWriteArrayList.size());
                            return sb.toString();
                        }
                    }, 1, null);
                    Unit unit = Unit.INSTANCE;
                }
            } catch (Exception e) {
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgSendHandle$run$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "处理数据错误：" + KotlinUtilsKt.stackTraceToString(e);
                    }
                }, 1, null);
            }
        }
    }
}
