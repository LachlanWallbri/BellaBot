package com.pudutech.robot.opensdk.aliyun.topic;

import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.robot.opensdk.aliyun.IotShadow;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowAuthConfig;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowAuthConfigSdkBean;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowStateValue;
import com.pudutech.robot.opensdk.bean.ActionCommandBody;
import com.pudutech.robot.opensdk.bean.CustomCallBody;
import com.pudutech.robot.opensdk.bean.CustomCallCancelBody;
import com.pudutech.robot.opensdk.bean.CustomCallCompleteBody;
import com.pudutech.robot.opensdk.bean.CustomCallContentBody;
import com.pudutech.robot.opensdk.bean.DeliveryTaskBody;
import com.pudutech.robot.opensdk.bean.DoorControlStateBody;
import com.pudutech.robot.opensdk.bean.PageBody;
import com.pudutech.robot.opensdk.bean.RobotDeliveryTrayOrderBody;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IotSubSdkTopic.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0016R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/topic/IotSubSdkTopic;", "Lcom/pudutech/robot/opensdk/aliyun/topic/BaseIotDirectTopic;", "productKey", "", "deviceName", "(Ljava/lang/String;Ljava/lang/String;)V", "TAG", "getTAG", "()Ljava/lang/String;", "checkSource", "", MapElement.Source.SOURCE, "getRole", "getTopic", "onOtherMsg", "", "msgJson", "msgType", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IotSubSdkTopic extends BaseIotDirectTopic {
    private final String TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IotSubSdkTopic(String productKey, String deviceName) {
        super(productKey, deviceName);
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        this.TAG = "IotSubSdkTopic";
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseIotDirectTopic
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseIotDirectTopic
    public void onOtherMsg(String msgJson, String msgType) {
        BaseMsgBean<?> baseMsgBean;
        Intrinsics.checkParameterIsNotNull(msgJson, "msgJson");
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_DELEVERY_TRAY_ORDER())) {
            Object fromJson = getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<RobotDeliveryTrayOrderBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson<BaseMsgBea…rderBody>>(msgJson, type)");
            baseMsgBean = (BaseMsgBean) fromJson;
        } else {
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_REQUEST_DATA())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<PageBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$2
                }.getType());
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_DELIVERY_TASK())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<DeliveryTaskBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$3
                }.getType());
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_ACTION_COMMAND())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<ActionCommandBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$4
                }.getType());
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_QUERY_STATE())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, BaseMsgBean.class);
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_GET_ROBOT_CURRENT_MAP())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, BaseMsgBean.class);
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_POST_DOOR_CONTROL_STATE())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<DoorControlStateBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$5
                }.getType());
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CUSTOM_CALL())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<CustomCallBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$6
                }.getType());
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CUSTOM_CALL_CANCEL())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<CustomCallCancelBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$7
                }.getType());
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CUSTOM_CALL_CONTENT())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<CustomCallContentBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$8
                }.getType());
            } else if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CUSTOM_CALL_COMPLETE())) {
                baseMsgBean = (BaseMsgBean) getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<CustomCallCompleteBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic$onOtherMsg$baseMsgBean$type$9
                }.getType());
            } else {
                Pdlog.m3274e(getTAG(), "unknown msg type " + msgJson);
                return;
            }
            Intrinsics.checkExpressionValueIsNotNull(baseMsgBean, "if (msgType == Constant.…         return\n        }");
        }
        if (!checkSource(baseMsgBean.getSource())) {
            Pdlog.m3274e(getTAG(), "checkSource error : " + baseMsgBean.getSource());
            return;
        }
        IotShadow.INSTANCE.activeSdkDeviceOnlineIfNeed(baseMsgBean.getSource());
        notifyEvent(baseMsgBean);
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseIotDirectTopic
    public boolean checkSource(String source) {
        ArrayList arrayList;
        ShadowAuthConfig authConfig;
        ArrayList<ShadowAuthConfigSdkBean> sdk;
        Intrinsics.checkParameterIsNotNull(source, "source");
        ShadowStateValue shadowConfig = IotShadow.INSTANCE.getShadowConfig();
        if (shadowConfig == null || (authConfig = shadowConfig.getAuthConfig()) == null || (sdk = authConfig.getSdk()) == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : sdk) {
                if (Intrinsics.areEqual(((ShadowAuthConfigSdkBean) obj).getId(), source)) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        }
        if (arrayList != null && (!arrayList.isEmpty())) {
            return true;
        }
        Pdlog.m3273d(getTAG(), "checkSource : source = " + source + "; failed !!");
        return false;
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseSubTopic
    public String getRole() {
        return Constant.INSTANCE.getROLE_SDK();
    }

    @Override // com.pudutech.robot.opensdk.interf.ISubTopic
    public String getTopic() {
        return Constant.INSTANCE.subSdkTopicTemplate(getProductKey(), getDeviceName());
    }
}
