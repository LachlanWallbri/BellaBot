package com.pudutech.bumblebee.business.ims.interf;

import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.pudutech.bumblebee.business.ims.listener.IConnectStatusListener;
import com.pudutech.bumblebee.business.ims.listener.IMsgReceivedListener;
import com.pudutech.bumblebee.business.ims.listener.IMsgSentStatusListener;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: I_IMSClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J$\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\b\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H&J(\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/interf/I_IMSClient;", "", "checkMsgLegitimacy", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "init", "context", "Landroid/content/Context;", "connectStatusListener", "Lcom/pudutech/bumblebee/business/ims/listener/IConnectStatusListener;", "msgReceivedListener", "Lcom/pudutech/bumblebee/business/ims/listener/IMsgReceivedListener;", "release", "", "removeMsg", "msgId", "", "sendMsg", "joinResendManager", "msgSentStatusListener", "Lcom/pudutech/bumblebee/business/ims/listener/IMsgSentStatusListener;", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface I_IMSClient {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final String TAG = "I_IMSClient";

    boolean checkMsgLegitimacy(MessageProtobuf.Msg msg);

    I_IMSClient init(Context context, IConnectStatusListener connectStatusListener, IMsgReceivedListener msgReceivedListener);

    void release();

    void removeMsg(String msgId);

    void sendMsg(MessageProtobuf.Msg msg, boolean joinResendManager, IMsgSentStatusListener msgSentStatusListener);

    /* compiled from: I_IMSClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/interf/I_IMSClient$Companion;", "", "()V", "TAG", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String TAG = "I_IMSClient";

        private Companion() {
        }
    }

    /* compiled from: I_IMSClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void sendMsg$default(I_IMSClient i_IMSClient, MessageProtobuf.Msg msg, boolean z, IMsgSentStatusListener iMsgSentStatusListener, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendMsg");
            }
            if ((i & 2) != 0) {
                z = true;
            }
            if ((i & 4) != 0) {
                iMsgSentStatusListener = (IMsgSentStatusListener) null;
            }
            i_IMSClient.sendMsg(msg, z, iMsgSentStatusListener);
        }

        public static boolean checkMsgLegitimacy(I_IMSClient i_IMSClient, MessageProtobuf.Msg msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            String msgId = msg.getMsgId();
            if (msgId == null || msgId.length() == 0) {
                Log.w("I_IMSClient", "checkMsgLegitimacy() failure, msgId is null or empty.");
                return false;
            }
            if (msg.getMsgType() == 0) {
                Log.w("I_IMSClient", "checkMsgLegitimacy() failure, msgType = 0.");
                return false;
            }
            String sender = msg.getSender();
            if (sender == null || sender.length() == 0) {
                Log.w("I_IMSClient", "checkMsgLegitimacy() failure, sender is null or empty.");
                return false;
            }
            String receiver = msg.getReceiver();
            if (receiver == null || receiver.length() == 0) {
                Log.w("I_IMSClient", "checkMsgLegitimacy() failure, receiver is null or empty.");
                return false;
            }
            Log.d("I_IMSClient", "checkMsgLegitimacy() successfully.");
            return true;
        }
    }
}
