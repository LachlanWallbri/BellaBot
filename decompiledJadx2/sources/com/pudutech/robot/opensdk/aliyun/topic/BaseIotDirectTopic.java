package com.pudutech.robot.opensdk.aliyun.topic;

import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.bean.CallBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseIotDirectTopic.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H&J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0004J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H&R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/topic/BaseIotDirectTopic;", "Lcom/pudutech/robot/opensdk/aliyun/topic/BaseSubTopic;", "productKey", "", "deviceName", "(Ljava/lang/String;Ljava/lang/String;)V", "TAG", "getTAG", "()Ljava/lang/String;", "checkSource", "", MapElement.Source.SOURCE, "notify", "", "baseMsgBean", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", "onMsg", "msgJson", "msgType", "onOtherMsg", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseIotDirectTopic extends BaseSubTopic {
    private final String TAG;

    public abstract boolean checkSource(String source);

    public abstract void onOtherMsg(String msgJson, String msgType);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseIotDirectTopic(String productKey, String deviceName) {
        super(productKey, deviceName);
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        this.TAG = "BaseIotDirectTopic";
    }

    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseSubTopic
    public void onMsg(String msgJson, String msgType) {
        Intrinsics.checkParameterIsNotNull(msgJson, "msgJson");
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        Pdlog.m3273d(getTAG(), "onMsg=" + msgType + ' ' + msgJson);
        if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CALL()) || Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CANCEL_CALL())) {
            Object fromJson = getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<CallBody>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.BaseIotDirectTopic$onMsg$baseMsgBean$type$1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson<BaseMsgBea…CallBody>>(msgJson, type)");
            notify((BaseMsgBean) fromJson);
            return;
        }
        onOtherMsg(msgJson, msgType);
    }

    protected final void notify(BaseMsgBean<?> baseMsgBean) {
        Intrinsics.checkParameterIsNotNull(baseMsgBean, "baseMsgBean");
        if (checkSource(baseMsgBean.getSource())) {
            notifyEvent(baseMsgBean);
        }
    }
}
