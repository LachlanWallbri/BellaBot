package com.pudu.library.loracall;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudu.library.loracall.bean.LoRaVersionParam;
import com.pudu.library.loracall.bean.LoraUpdateParam;
import com.pudu.library.loracall.utils.UpdateUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: MsgReceiveHandle.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0006J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, m3961d2 = {"Lcom/pudu/library/loracall/MsgReceiveHandle;", "", "()V", "listenerMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "receiveList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "sendHandle", "Lcom/pudu/library/loracall/MsgSendHandle;", "getSendHandle", "()Lcom/pudu/library/loracall/MsgSendHandle;", "addListener", "", TransferTable.COLUMN_KEY, "listener", "dispatchMsg", "msgType", "Lcom/pudu/library/loracall/ReceiveMsgType;", "handleMsg", "slipMsgBean", "Lcom/pudu/library/loracall/SlipMsgBean;", "handleType01", "handleType02", "handleType03", "handleType05", "receiveMsg", "data", "", "removeListener", "Listener", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MsgReceiveHandle {
    private final ConcurrentHashMap<String, Listener> listenerMap = new ConcurrentHashMap<>();
    private final ArrayList<Byte> receiveList = new ArrayList<>();

    /* compiled from: MsgReceiveHandle.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "", "receive", "", "msgType", "Lcom/pudu/library/loracall/ReceiveMsgType;", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface Listener {
        void receive(ReceiveMsgType msgType);
    }

    private final MsgSendHandle getSendHandle() {
        return LoRaClient.INSTANCE.getInstance().getMsgSendHandle();
    }

    public final void receiveMsg(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        for (byte b : data) {
            if (b == -64) {
                if (this.receiveList.size() < 7) {
                    this.receiveList.clear();
                } else {
                    SlipMsgBean slipToMsg = SlipTool.INSTANCE.slipToMsg(this.receiveList);
                    if (slipToMsg != null) {
                        handleMsg(slipToMsg);
                    }
                    this.receiveList.clear();
                }
            } else {
                this.receiveList.add(Byte.valueOf(b));
            }
        }
    }

    private final void handleMsg(SlipMsgBean slipMsgBean) {
        MsgSendHandle sendHandle;
        byte type = slipMsgBean.getType();
        if (type == 1) {
            handleType01(slipMsgBean);
            return;
        }
        if (type == 2) {
            handleType02(slipMsgBean);
            return;
        }
        if (type == 3) {
            handleType03(slipMsgBean);
            return;
        }
        if (type == 5) {
            handleType05(slipMsgBean);
            return;
        }
        if (type != 16) {
            if (type == 32 && (sendHandle = getSendHandle()) != null) {
                sendHandle.receiveAck(slipMsgBean);
                return;
            }
            return;
        }
        MsgSendHandle sendHandle2 = getSendHandle();
        if (sendHandle2 != null) {
            sendHandle2.receiveAck(slipMsgBean);
        }
    }

    private final void handleType01(SlipMsgBean slipMsgBean) {
        byte id = slipMsgBean.getId();
        if (id != -46) {
            if (id != -43) {
                return;
            }
            final LoraUpdateParam loraUpdateParam = new LoraUpdateParam(slipMsgBean.getData());
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgReceiveHandle$handleType01$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "请求固件数据--" + LoraUpdateParam.this;
                }
            }, 1, null);
            if (LoRaClient.INSTANCE.getInstance().getUpdateFile() == null) {
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgReceiveHandle$handleType01$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "主动请求固件数据--" + LoraUpdateParam.this;
                    }
                }, 1, null);
                LoRaClient.INSTANCE.getInstance().setUpdateFile(new File(UpdateUtils.INSTANCE.getUpdateFilePath()));
            }
            if (loraUpdateParam.getIndexData() + loraUpdateParam.getLength() > LoRaClient.INSTANCE.getInstance().getUpdateFileByte().length) {
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgReceiveHandle$handleType01$4
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "请求固件数据--数据越界错误";
                    }
                }, 1, null);
                return;
            }
            SlipMsgBean slipMsgBean2 = new SlipMsgBean((byte) 16, (byte) DimensionsKt.TVDPI, null, null, 12, null);
            slipMsgBean2.setFrameNumber(slipMsgBean.getFrameNumber());
            slipMsgBean2.setData(ArraysKt.plus(slipMsgBean.getData(), ArraysKt.copyOfRange(LoRaClient.INSTANCE.getInstance().getUpdateFileByte(), loraUpdateParam.getIndexData(), loraUpdateParam.getIndexData() + loraUpdateParam.getLength())));
            MsgSendHandle sendHandle = getSendHandle();
            if (sendHandle != null) {
                sendHandle.sendMsg(slipMsgBean2);
            }
            dispatchMsg(new LoraUpdateProgressState((loraUpdateParam.getIndexData() * 1.0f) / LoRaClient.INSTANCE.getInstance().getUpdateFileByte().length));
            return;
        }
        final File updateFile = LoRaClient.INSTANCE.getInstance().getUpdateFile();
        if (updateFile != null) {
            final long length = updateFile.length();
            final byte[] bArr = KotlinUtilsKt.tolBytes((int) length);
            byte[] bArr2 = {0, 0};
            if (LoRaVersionParam.INSTANCE.getLoRaVersion().length >= 6) {
                bArr2[0] = LoRaVersionParam.INSTANCE.getLoRaVersion()[4];
                bArr2[1] = LoRaVersionParam.INSTANCE.getLoRaVersion()[5];
            }
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgReceiveHandle$handleType01$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "收到固件查询--- updateFile: " + updateFile.getAbsoluteFile() + " length: " + length + " byteArray " + KotlinUtilsKt.bytesToInt$default(bArr, 0, 1, null);
                }
            }, 1, null);
            SlipMsgBean slipMsgBean3 = new SlipMsgBean((byte) 16, (byte) 210, null, null, 12, null);
            slipMsgBean3.setFrameNumber(slipMsgBean.getFrameNumber());
            slipMsgBean3.setData(new byte[]{LoRaClient.INSTANCE.getInstance().getFirmwareType(), bArr[0], bArr[1], bArr[2], bArr[3], bArr2[0], bArr2[1]});
            MsgSendHandle sendHandle2 = getSendHandle();
            if (sendHandle2 != null) {
                sendHandle2.sendMsg(slipMsgBean3);
            }
        }
    }

    private final void handleType02(final SlipMsgBean slipMsgBean) {
        if (slipMsgBean.getId() != -40) {
            return;
        }
        SlipMsgBean slipMsgBean2 = new SlipMsgBean((byte) 16, (byte) 216, null, null, 12, null);
        slipMsgBean2.setFrameNumber(slipMsgBean.getFrameNumber());
        slipMsgBean2.setData(new byte[]{LoRaClient.INSTANCE.getInstance().getFirmwareType()});
        MsgSendHandle sendHandle = getSendHandle();
        if (sendHandle != null) {
            sendHandle.sendMsg(slipMsgBean2);
        }
        if (slipMsgBean.getData().length >= 2) {
            LoraUpdateState loraUpdateState = new LoraUpdateState(slipMsgBean.getData()[0], slipMsgBean.getData()[1]);
            if (loraUpdateState.getState() == 5) {
                LoRaClient.INSTANCE.getInstance().setUpdateFile((File) null);
                dispatchMsg(new LoraUpdateProgressState(1.0f));
            }
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.MsgReceiveHandle$handleType02$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "更新状态--" + ((int) SlipMsgBean.this.getData()[1]);
                }
            }, 1, null);
            dispatchMsg(loraUpdateState);
        }
    }

    private final void handleType03(SlipMsgBean slipMsgBean) {
        byte id = slipMsgBean.getId();
        if (id == 1) {
            SlipMsgBean slipMsgBean2 = new SlipMsgBean((byte) 32, (byte) 1, null, null, 12, null);
            slipMsgBean2.setFrameNumber(slipMsgBean.getFrameNumber());
            MsgSendHandle sendHandle = getSendHandle();
            if (sendHandle != null) {
                sendHandle.sendMsg(slipMsgBean2);
            }
            if (!(slipMsgBean.getData().length == 0)) {
                dispatchMsg(new LoraState(slipMsgBean.getData()[0]));
                return;
            }
            return;
        }
        if (id == 2) {
            SlipMsgBean slipMsgBean3 = new SlipMsgBean((byte) 32, (byte) 2, null, null, 12, null);
            slipMsgBean3.setFrameNumber(slipMsgBean.getFrameNumber());
            MsgSendHandle sendHandle2 = getSendHandle();
            if (sendHandle2 != null) {
                sendHandle2.sendMsg(slipMsgBean3);
            }
            if (!(slipMsgBean.getData().length == 0)) {
                dispatchMsg(new LoraNetState(slipMsgBean.getData()[0]));
                return;
            }
            return;
        }
        if (id == 4) {
            SlipMsgBean slipMsgBean4 = new SlipMsgBean((byte) 32, (byte) 4, null, null, 12, null);
            slipMsgBean4.setFrameNumber(slipMsgBean.getFrameNumber());
            MsgSendHandle sendHandle3 = getSendHandle();
            if (sendHandle3 != null) {
                sendHandle3.sendMsg(slipMsgBean4);
            }
            if (!(slipMsgBean.getData().length == 0)) {
                dispatchMsg(new LoraRunState(slipMsgBean.getData()[0]));
                return;
            }
            return;
        }
        if (id != 32) {
            return;
        }
        SlipMsgBean slipMsgBean5 = new SlipMsgBean((byte) 32, (byte) 32, null, null, 12, null);
        slipMsgBean5.setFrameNumber(slipMsgBean.getFrameNumber());
        MsgSendHandle sendHandle4 = getSendHandle();
        if (sendHandle4 != null) {
            sendHandle4.sendMsg(slipMsgBean5);
        }
        if (!(slipMsgBean.getData().length == 0)) {
            dispatchMsg(new LoraMsg(slipMsgBean.getData()));
        }
    }

    private final void handleType05(SlipMsgBean slipMsgBean) {
        if (slipMsgBean.getId() != 48) {
            return;
        }
        dispatchMsg(new LoraSignalStrength(slipMsgBean.getData()));
    }

    private final void dispatchMsg(ReceiveMsgType msgType) {
        Iterator<Map.Entry<String, Listener>> it = this.listenerMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().receive(msgType);
        }
    }

    public final void addListener(String key, Listener listener) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.listenerMap.get(key) != null) {
            this.listenerMap.remove(key);
        }
        this.listenerMap.put(key, listener);
    }

    public final void removeListener(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.listenerMap.remove(key);
    }
}
