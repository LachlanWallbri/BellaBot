package com.pudutech.robot.opensdk.aliyun;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.base.StringUtils;
import com.pudutech.pdmqtt.OnPublishCallback;
import com.pudutech.pdmqtt.client.IMqttClient;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.bean.BaseRootParameterBean;
import com.pudutech.robot.opensdk.bean.pub.BaseNotifyPub;
import com.pudutech.robot.opensdk.interf.IPubMsg;
import com.pudutech.robot.opensdk.utils.GenRandomUtils;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IotRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001:B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\r\u0010*\u001a\u00020\u001fH\u0000¢\u0006\u0002\b+J$\u0010,\u001a\u00020\u001f2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00032\u000e\u0010.\u001a\n\u0018\u00010/j\u0004\u0018\u0001`0H\u0002J\u0016\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u0003J\u0006\u00105\u001a\u00020\u001fJ\u0006\u00106\u001a\u000202J\b\u00107\u001a\u00020\u001fH\u0002J\u001d\u00108\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b9R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0014¨\u0006;"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/IotRequest;", "", TypedValues.Attributes.S_TARGET, "", "productKey", "deviceName", "groupId", "pubMsg", "Lcom/pudutech/robot/opensdk/interf/IPubMsg;", "callBack", "Lcom/pudutech/robot/opensdk/aliyun/IotRequest$CallBack;", "mqttClient", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/robot/opensdk/interf/IPubMsg;Lcom/pudutech/robot/opensdk/aliyun/IotRequest$CallBack;Lcom/pudutech/pdmqtt/client/IMqttClient;)V", "TAG", "TIME_OUT", "", "getCallBack", "()Lcom/pudutech/robot/opensdk/aliyun/IotRequest$CallBack;", "getDeviceName", "()Ljava/lang/String;", "getGroupId", "gson", "Lcom/google/gson/Gson;", "handler", "Landroid/os/Handler;", "getMqttClient", "()Lcom/pudutech/pdmqtt/client/IMqttClient;", "msgId", "onCallbackFinish", "Lkotlin/Function0;", "", "getOnCallbackFinish", "()Lkotlin/jvm/functions/Function0;", "setOnCallbackFinish", "(Lkotlin/jvm/functions/Function0;)V", MqttServiceConstants.PAYLOAD, "getProductKey", "getPubMsg", "()Lcom/pudutech/robot/opensdk/interf/IPubMsg;", "retry", "getTarget", "destroy", "destroy$robot_open_sdk_release", "doCallback", "data", NotificationCompat.CATEGORY_ERROR, "Ljava/lang/Exception;", "Lkotlin/Exception;", "doReplayMsgIfNeed", "", "topic", NotificationCompat.CATEGORY_MESSAGE, "doRequest", "needWaitResp", "setTimeoutTask", "stopRetryIfNeed", "stopRetryIfNeed$robot_open_sdk_release", "CallBack", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IotRequest {
    private final String TAG;
    private final int TIME_OUT;
    private final CallBack callBack;
    private final String deviceName;
    private final String groupId;
    private final Gson gson;
    private final Handler handler;
    private final IMqttClient mqttClient;
    private String msgId;
    private Function0<Unit> onCallbackFinish;
    private String payload;
    private final String productKey;
    private final IPubMsg pubMsg;
    private int retry;
    private final String target;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: IotRequest.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/IotRequest$CallBack;", "", "onFailed", "", C3898x.f4338g, "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", NotificationCompat.CATEGORY_MESSAGE, "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface CallBack {
        void onFailed(Exception e);

        void onSuccess(String msg);
    }

    public IotRequest(String target, String productKey, String deviceName, String groupId, IPubMsg pubMsg, CallBack callBack, IMqttClient iMqttClient) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(groupId, "groupId");
        Intrinsics.checkParameterIsNotNull(pubMsg, "pubMsg");
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        this.target = target;
        this.productKey = productKey;
        this.deviceName = deviceName;
        this.groupId = groupId;
        this.pubMsg = pubMsg;
        this.callBack = callBack;
        this.mqttClient = iMqttClient;
        this.TAG = "IotRequest";
        this.gson = new Gson();
        this.TIME_OUT = 1001;
        this.payload = "";
        this.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.robot.opensdk.aliyun.IotRequest$handler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                int i2;
                int i3;
                String str;
                int i4;
                String str2;
                int i5;
                int i6 = message.what;
                i = IotRequest.this.TIME_OUT;
                if (i6 == i) {
                    int retryCount = IotRequest.this.getPubMsg().getRetryCount();
                    i2 = IotRequest.this.retry;
                    if (retryCount <= i2) {
                        str2 = IotRequest.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("do timeout : retry = ");
                        i5 = IotRequest.this.retry;
                        sb.append(i5);
                        Pdlog.m3273d(str2, sb.toString());
                        IotRequest.this.doCallback(null, new IOException("iot wait resp timeout"));
                    } else {
                        IotRequest iotRequest = IotRequest.this;
                        i3 = iotRequest.retry;
                        iotRequest.retry = i3 + 1;
                        str = IotRequest.this.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("do retry = ");
                        i4 = IotRequest.this.retry;
                        sb2.append(i4);
                        Pdlog.m3273d(str, sb2.toString());
                        IotRequest.this.doRequest();
                    }
                }
                return true;
            }
        });
    }

    public final String getTarget() {
        return this.target;
    }

    public final String getProductKey() {
        return this.productKey;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    public final IPubMsg getPubMsg() {
        return this.pubMsg;
    }

    public final CallBack getCallBack() {
        return this.callBack;
    }

    public final IMqttClient getMqttClient() {
        return this.mqttClient;
    }

    public final Function0<Unit> getOnCallbackFinish() {
        return this.onCallbackFinish;
    }

    public final void setOnCallbackFinish(Function0<Unit> function0) {
        this.onCallbackFinish = function0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTimeoutTask() {
        if (this.retry <= this.pubMsg.getRetryCount()) {
            this.handler.sendEmptyMessageDelayed(this.TIME_OUT, this.pubMsg.getTimeout());
        } else {
            doCallback(null, new IOException("iot wait resp timeout"));
        }
    }

    public final synchronized void doRequest() {
        this.msgId = GenRandomUtils.getRandom$default(8, false, 2, null);
        String msgType = this.pubMsg.getMsgType();
        String str = this.deviceName;
        String str2 = this.target;
        String str3 = this.msgId;
        if (str3 == null) {
            Intrinsics.throwNpe();
        }
        String json = this.gson.toJson(new BaseMsgBean(msgType, str, str2, str3, this.pubMsg, this.groupId));
        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(baseMsgBean)");
        this.payload = json;
        String topic = this.pubMsg.getTopic(this.productKey, this.deviceName);
        IMqttClient iMqttClient = this.mqttClient;
        if (iMqttClient != null) {
            iMqttClient.publish(topic, this.payload, new OnPublishCallback.Stub() { // from class: com.pudutech.robot.opensdk.aliyun.IotRequest$doRequest$1
                @Override // com.pudutech.pdmqtt.OnPublishCallback
                public void onSuccess(String topic2, String payload) {
                    String str4;
                    str4 = IotRequest.this.TAG;
                    Pdlog.m3273d(str4, "onResponse : pubMsg = " + payload + ' ');
                    if (IotRequest.this.needWaitResp()) {
                        IotRequest.this.setTimeoutTask();
                    } else {
                        IotRequest.this.doCallback("", null);
                    }
                }

                @Override // com.pudutech.pdmqtt.OnPublishCallback
                public void onFailue(String topic2, String payload, int code, String message) {
                    int i;
                    String str4;
                    int i2;
                    i = IotRequest.this.retry;
                    if (i >= IotRequest.this.getPubMsg().getRetryCount()) {
                        str4 = IotRequest.this.TAG;
                        Pdlog.m3273d(str4, "onFailure : pubMsg = " + payload + "; error = " + message + "; topic = " + topic2);
                        IotRequest.this.doCallback(null, new IOException(message));
                        return;
                    }
                    IotRequest iotRequest = IotRequest.this;
                    i2 = iotRequest.retry;
                    iotRequest.retry = i2 + 1;
                    IotRequest.this.doRequest();
                }
            });
        }
    }

    static /* synthetic */ void doCallback$default(IotRequest iotRequest, String str, Exception exc, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        iotRequest.doCallback(str, exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doCallback(String data, Exception err) {
        Pdlog.m3273d(this.TAG, "doCallback : pubMsg = " + this.payload + ' ');
        this.handler.removeMessages(this.TIME_OUT);
        Function0<Unit> function0 = this.onCallbackFinish;
        if (function0 != null) {
            function0.invoke();
        }
        if (err != null) {
            this.callBack.onFailed(err);
            return;
        }
        CallBack callBack = this.callBack;
        if (data == null) {
            data = "";
        }
        callBack.onSuccess(data);
    }

    public final boolean needWaitResp() {
        return !StringUtils.isEmpty(this.pubMsg.getReplyTopic(this.productKey, this.deviceName));
    }

    public final boolean doReplayMsgIfNeed(String topic, String msg) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!Intrinsics.areEqual(topic, this.pubMsg.getReplyTopic(this.productKey, this.deviceName))) {
            return false;
        }
        if (StringUtils.isEmpty(this.pubMsg.getMsgType())) {
            doCallback(msg, null);
            return true;
        }
        try {
            BaseRootParameterBean baseRootParameterBean = (BaseRootParameterBean) this.gson.fromJson(msg, BaseRootParameterBean.class);
            if (Intrinsics.areEqual(baseRootParameterBean.getMsgType(), this.pubMsg.getMsgType()) && Intrinsics.areEqual(baseRootParameterBean.getMsgId(), this.msgId)) {
                Pdlog.m3273d(this.TAG, "doReplayMsgIfNeed : current req = " + this.payload + " , msgid = " + this.msgId + " ; topic = " + topic + "; ");
                doCallback(msg, null);
                return true;
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
        return false;
    }

    public final void stopRetryIfNeed$robot_open_sdk_release(String target, IPubMsg pubMsg) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(pubMsg, "pubMsg");
        if (!(!Intrinsics.areEqual(target, this.target)) && (pubMsg instanceof BaseNotifyPub) && Intrinsics.areEqual(pubMsg.getMsgType(), this.pubMsg.getMsgType()) && Intrinsics.areEqual(((BaseNotifyPub) pubMsg).getClass(), this.pubMsg.getClass()) && this.pubMsg.getRetryCount() > 0) {
            Pdlog.m3273d(this.TAG, "stopRetryIfNeed : target = " + target + "; pubMsg = " + pubMsg + "; ");
            this.retry = this.pubMsg.getRetryCount();
        }
    }

    public final void destroy$robot_open_sdk_release() {
        this.handler.removeMessages(this.TIME_OUT);
    }
}
