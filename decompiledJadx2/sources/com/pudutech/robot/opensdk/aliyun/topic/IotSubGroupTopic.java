package com.pudutech.robot.opensdk.aliyun.topic;

import android.os.SystemClock;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.opensdk.aliyun.IotShadow;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.bean.BaseRootParameterBean;
import com.pudutech.robot.opensdk.bean.DestinationBody;
import com.pudutech.robot.opensdk.bean.DeviceOnlineBody;
import com.pudutech.robot.opensdk.bean.PageBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IotSubGroupTopic.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/topic/IotSubGroupTopic;", "Lcom/pudutech/robot/opensdk/aliyun/topic/BaseSubTopic;", "productKey", "", "deviceName", "(Ljava/lang/String;Ljava/lang/String;)V", "TAG", "getRole", "getTopic", "onMsg", "", "msgJson", "msgType", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IotSubGroupTopic extends BaseSubTopic {
    private final String TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IotSubGroupTopic(String productKey, String deviceName) {
        super(productKey, deviceName);
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        this.TAG = "IotSubGroupTopic";
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseSubTopic
    public String getRole() {
        BaseRootParameterBean rootParameterBean = getRootParameterBean();
        if (Intrinsics.areEqual(rootParameterBean != null ? rootParameterBean.getSourceType() : null, Constant.INSTANCE.getROLE_BEEPER())) {
            return Constant.INSTANCE.getROLE_BEEPER();
        }
        BaseRootParameterBean rootParameterBean2 = getRootParameterBean();
        if (Intrinsics.areEqual(rootParameterBean2 != null ? rootParameterBean2.getSourceType() : null, Constant.INSTANCE.getROLE_SDK())) {
            return Constant.INSTANCE.getROLE_SDK();
        }
        BaseRootParameterBean rootParameterBean3 = getRootParameterBean();
        return Intrinsics.areEqual(rootParameterBean3 != null ? rootParameterBean3.getSourceType() : null, Constant.INSTANCE.getROLE_DISINFECTION()) ? Constant.INSTANCE.getROLE_DISINFECTION() : "";
    }

    @Override // com.pudutech.robot.opensdk.interf.ISubTopic
    public String getTopic() {
        return Constant.INSTANCE.subGroupTopicTemplate(getProductKey(), getDeviceName());
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseSubTopic
    public void onMsg(String msgJson, String msgType) {
        BaseMsgBean<?> baseMsgBean;
        Intrinsics.checkParameterIsNotNull(msgJson, "msgJson");
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        if (getRole().length() == 0) {
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE$robot_open_sdk_release())) {
                IotShadow.INSTANCE.updateSdkDeviceOnline((DeviceOnlineBody) ((BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<DeviceOnlineBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubGroupTopic$onMsg$type$1
                }.getType())).getBody());
                return;
            } else {
                Pdlog.m3274e(this.TAG, "onMsg : unknown sourceType");
                return;
            }
        }
        if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_QUERY_DESTINATION_ROBOT())) {
            Object fromJson = getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<DestinationBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubGroupTopic$onMsg$baseMsgBean$type$1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson<BaseMsgBea…tionBody>>(msgJson, type)");
            baseMsgBean = (BaseMsgBean) fromJson;
        } else {
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_QUERY_STATE())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, BaseMsgBean.class);
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_REQUEST_DATA())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<PageBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubGroupTopic$onMsg$baseMsgBean$type$2
                }.getType());
            } else {
                Pdlog.m3274e(this.TAG, "un know msg type " + msgJson);
                return;
            }
            Intrinsics.checkExpressionValueIsNotNull(baseMsgBean, "if (msgType == Constant.…     return\n            }");
        }
        IotShadow.INSTANCE.getWhiteDevices().put(baseMsgBean.getSource(), Long.valueOf(SystemClock.elapsedRealtime()));
        notifyEvent(baseMsgBean);
    }
}
