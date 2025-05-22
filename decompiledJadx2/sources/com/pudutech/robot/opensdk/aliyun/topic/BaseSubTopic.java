package com.pudutech.robot.opensdk.aliyun.topic;

import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.bean.BaseRootParameterBean;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ISubTopic;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseSubTopic.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001c\"\b\b\u0000\u0010\u001d*\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001d0 H\u0004J\b\u0010!\u001a\u00020\u0003H&J\u0014\u0010\"\u001a\u00020#2\n\u0010$\u001a\u0006\u0012\u0002\b\u00030 H\u0004J.\u0010%\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010\u00032\b\u0010'\u001a\u0004\u0018\u00010\u00032\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020\u0003H\u0016J\u0018\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H&J\u001c\u0010.\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010\u00032\b\u0010'\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010/\u001a\u00020\u0010H\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\b\"\u0004\b\u0012\u0010\nR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u00060"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/topic/BaseSubTopic;", "Lcom/pudutech/robot/opensdk/interf/ISubTopic;", "productKey", "", "deviceName", "(Ljava/lang/String;Ljava/lang/String;)V", "TAG", "getDeviceName", "()Ljava/lang/String;", "setDeviceName", "(Ljava/lang/String;)V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "isSuccess", "", "getProductKey", "setProductKey", "rootParameterBean", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseRootParameterBean;", "getRootParameterBean", "()Lcom/pudutech/robot/opensdk/aliyun/bean/BaseRootParameterBean;", "setRootParameterBean", "(Lcom/pudutech/robot/opensdk/aliyun/bean/BaseRootParameterBean;)V", "asBinder", "Landroid/os/IBinder;", "getBaseMsgContext", "Lcom/pudutech/robot/opensdk/MsgContext;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/robot/opensdk/interf/IBody;", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", "getRole", "notifyEvent", "", "msgBean", "onFailue", "topic", MqttServiceConstants.PAYLOAD, "code", "", "message", "onMsg", "msgJson", "msgType", "onSuccess", "subSuccess", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseSubTopic implements ISubTopic {
    private final String TAG;
    private String deviceName;
    private final Gson gson;
    private boolean isSuccess;
    private String productKey;
    private BaseRootParameterBean rootParameterBean;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    public abstract String getRole();

    public abstract void onMsg(String msgJson, String msgType);

    public BaseSubTopic(String productKey, String deviceName) {
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        this.productKey = productKey;
        this.deviceName = deviceName;
        this.TAG = "BaseSubTopic";
        this.gson = new Gson();
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final String getProductKey() {
        return this.productKey;
    }

    public final void setDeviceName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceName = str;
    }

    public final void setProductKey(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.productKey = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Gson getGson() {
        return this.gson;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BaseRootParameterBean getRootParameterBean() {
        return this.rootParameterBean;
    }

    protected final void setRootParameterBean(BaseRootParameterBean baseRootParameterBean) {
        this.rootParameterBean = baseRootParameterBean;
    }

    @Override // com.pudutech.robot.opensdk.interf.ISubTopic
    /* renamed from: subSuccess, reason: from getter */
    public boolean getIsSuccess() {
        return this.isSuccess;
    }

    @Override // com.pudutech.robot.opensdk.interf.ISubTopic
    public void onMsg(String msgJson) {
        Intrinsics.checkParameterIsNotNull(msgJson, "msgJson");
        this.rootParameterBean = (BaseRootParameterBean) this.gson.fromJson(msgJson, BaseRootParameterBean.class);
        BaseRootParameterBean baseRootParameterBean = this.rootParameterBean;
        if (baseRootParameterBean == null) {
            Intrinsics.throwNpe();
        }
        onMsg(msgJson, baseRootParameterBean.getMsgType());
    }

    @Override // com.pudutech.pdmqtt.OnPublishCallback
    public void onSuccess(String topic, String payload) {
        Pdlog.m3273d(this.TAG, "topic = " + getTopic() + " , onSuccess");
        this.isSuccess = true;
    }

    @Override // com.pudutech.pdmqtt.OnPublishCallback
    public void onFailue(String topic, String payload, int code, String message) {
        Pdlog.m3274e(this.TAG, "topic = " + getTopic() + " , onFailure = " + message);
        this.isSuccess = false;
    }

    protected final <T extends IBody> MsgContext<T> getBaseMsgContext(BaseMsgBean<T> msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return new MsgContext<>(0, getRole(), msg.getMsgType(), msg.getSource(), msg.getMsgId(), msg.getGroupId(), msg.getBody());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyEvent(BaseMsgBean<?> msgBean) {
        Intrinsics.checkParameterIsNotNull(msgBean, "msgBean");
        RobotOpenSdk.INSTANCE.notifyEvent$robot_open_sdk_release(getBaseMsgContext(msgBean));
    }
}
