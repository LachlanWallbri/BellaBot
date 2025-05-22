package com.pudutech.robot.opensdk.aliyun;

import android.util.Log;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.opensdk.interf.ISubTopic;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: AliyunIot.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J \u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u000f"}, m3961d2 = {"com/pudutech/robot/opensdk/aliyun/AliyunIot$iotNotifyListener$1", "Lcom/aliyun/alink/linksdk/cmp/core/listener/IConnectNotifyListener;", "onConnectStateChange", "", "p0", "", "p1", "Lcom/aliyun/alink/linksdk/cmp/core/base/ConnectState;", "onNotify", "connectId", "topic", "message", "Lcom/aliyun/alink/linksdk/cmp/core/base/AMessage;", "shouldHandle", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AliyunIot$iotNotifyListener$1 implements IConnectNotifyListener {
    final /* synthetic */ AliyunIot this$0;

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public boolean shouldHandle(String p0, String p1) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AliyunIot$iotNotifyListener$1(AliyunIot aliyunIot) {
        this.this$0 = aliyunIot;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onConnectStateChange(String p0, ConnectState p1) {
        String str;
        boolean z;
        String str2;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean isNetActive;
        String str3;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onConnectStateChange : p0 = " + p0 + "; p1 = " + p1 + "; ");
        if (p1 != null) {
            this.this$0.connectState = p1;
        }
        z = this.this$0.isRelease;
        if (!z) {
            z4 = this.this$0.isEnable;
            if (z4) {
                this.this$0.notifyConnectByIot(p1);
                if (p1 == ConnectState.DISCONNECTED) {
                    z5 = this.this$0.isIniting;
                    if (z5) {
                        return;
                    }
                    isNetActive = this.this$0.isNetActive();
                    if (isNetActive) {
                        str3 = this.this$0.TAG;
                        Pdlog.m3273d(str3, "onConnectStateChange : isNetActive , reconnect ");
                        this.this$0.tryReconnectIotTask();
                        return;
                    }
                    return;
                }
                return;
            }
        }
        str2 = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onConnectStateChange isRelease = ");
        z2 = this.this$0.isRelease;
        sb.append(z2);
        sb.append(" , isEnable = ");
        z3 = this.this$0.isEnable;
        sb.append(z3);
        Pdlog.m3273d(str2, sb.toString());
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onNotify(String connectId, String topic, AMessage message) {
        String str;
        boolean z;
        String str2;
        boolean z2;
        boolean z3;
        boolean z4;
        String str3;
        CopyOnWriteArraySet copyOnWriteArraySet;
        HashMap hashMap;
        String str4;
        Intrinsics.checkParameterIsNotNull(connectId, "connectId");
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(message, "message");
        try {
            z = this.this$0.isRelease;
            if (!z) {
                z4 = this.this$0.isEnable;
                if (z4) {
                    Object obj = message.data;
                    if (obj != null) {
                        String str5 = new String((byte[]) obj, Charsets.UTF_8);
                        str3 = this.this$0.TAG;
                        Pdlog.m3273d(str3, "onNotify : connectId = " + connectId + "; topic = " + topic + "; message = " + str5 + "; ");
                        copyOnWriteArraySet = this.this$0.waitRespReqs;
                        Iterator it = copyOnWriteArraySet.iterator();
                        while (it.hasNext()) {
                            if (((IotRequest) it.next()).doReplayMsgIfNeed(topic, str5)) {
                                str4 = this.this$0.TAG;
                                Pdlog.m3273d(str4, "onNotify : is resp msg ");
                                return;
                            }
                        }
                        if (str5.length() > 0) {
                            hashMap = this.this$0.subTopics;
                            ISubTopic iSubTopic = (ISubTopic) hashMap.get(topic);
                            if (iSubTopic != null) {
                                iSubTopic.onMsg(str5);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
                }
            }
            str2 = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onNotify : isRelease = ");
            z2 = this.this$0.isRelease;
            sb.append(z2);
            sb.append(" , isEnable = ");
            z3 = this.this$0.isEnable;
            sb.append(z3);
            Pdlog.m3273d(str2, sb.toString());
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3274e(str, Log.getStackTraceString(e));
        }
    }
}
