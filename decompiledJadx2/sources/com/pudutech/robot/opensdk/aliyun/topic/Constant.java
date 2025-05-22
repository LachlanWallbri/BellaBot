package com.pudutech.robot.opensdk.aliyun.topic;

import com.amitshekhar.utils.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: Constant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\bW\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010L\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010M\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010N\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010O\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010P\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010Q\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010R\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010S\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010T\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010U\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010V\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010W\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010X\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010Y\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J\u0016\u0010Z\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0014\u0010'\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0014\u0010)\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0014\u0010+\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0014\u0010-\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0014\u0010/\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0006R\u0014\u00101\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0006R\u0014\u00103\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0006R\u0014\u00105\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0006R\u0014\u00107\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0006R\u0014\u00109\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0006R\u0014\u0010;\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0006R\u0014\u0010=\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0006R\u0014\u0010?\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0006R\u0014\u0010A\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0006R\u0014\u0010C\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0006R\u0014\u0010E\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0006R\u0014\u0010G\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0006¨\u0006["}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/topic/Constant;", "", "()V", "MSG_TYPE_ACTION_COMMAND", "", "getMSG_TYPE_ACTION_COMMAND", "()Ljava/lang/String;", "MSG_TYPE_ARRIVED", "MSG_TYPE_BROADCAST_BEEPER", "MSG_TYPE_CALL", "getMSG_TYPE_CALL", "MSG_TYPE_CANCEL_CALL", "getMSG_TYPE_CANCEL_CALL", "MSG_TYPE_CUSTOM_CALL", "getMSG_TYPE_CUSTOM_CALL", "MSG_TYPE_CUSTOM_CALL_CANCEL", "getMSG_TYPE_CUSTOM_CALL_CANCEL", "MSG_TYPE_CUSTOM_CALL_COMPLETE", "getMSG_TYPE_CUSTOM_CALL_COMPLETE", "MSG_TYPE_CUSTOM_CALL_CONTENT", "getMSG_TYPE_CUSTOM_CALL_CONTENT", "MSG_TYPE_DELEVERY_TRAY_ORDER", "getMSG_TYPE_DELEVERY_TRAY_ORDER", "MSG_TYPE_DELIVERY_TASK", "getMSG_TYPE_DELIVERY_TASK", "MSG_TYPE_DIS_ACTION", "getMSG_TYPE_DIS_ACTION", "MSG_TYPE_DIS_STATUS", "getMSG_TYPE_DIS_STATUS", "MSG_TYPE_GET_DEVICE_ONLINE_STATE", "getMSG_TYPE_GET_DEVICE_ONLINE_STATE$robot_open_sdk_release", "MSG_TYPE_GET_ROBOT_CURRENT_MAP", "getMSG_TYPE_GET_ROBOT_CURRENT_MAP", "MSG_TYPE_INIT_ROBOT_SHADOW_CONFIG", "getMSG_TYPE_INIT_ROBOT_SHADOW_CONFIG$robot_open_sdk_release", "MSG_TYPE_NOTIFY_CUSTOM_CALL", "getMSG_TYPE_NOTIFY_CUSTOM_CALL$robot_open_sdk_release", "MSG_TYPE_NOTIFY_DELIVERY_TASK", "getMSG_TYPE_NOTIFY_DELIVERY_TASK$robot_open_sdk_release", "MSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE", "getMSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE$robot_open_sdk_release", "MSG_TYPE_NOTIFY_GO_STATE", "getMSG_TYPE_NOTIFY_GO_STATE$robot_open_sdk_release", "MSG_TYPE_NOTIFY_QR_CODE_CONTENT", "getMSG_TYPE_NOTIFY_QR_CODE_CONTENT$robot_open_sdk_release", "MSG_TYPE_NOTIFY_ROBOT_MOVE_STATE", "getMSG_TYPE_NOTIFY_ROBOT_MOVE_STATE$robot_open_sdk_release", "MSG_TYPE_NOTIFY_ROBOT_ORDER_STATE", "getMSG_TYPE_NOTIFY_ROBOT_ORDER_STATE$robot_open_sdk_release", "MSG_TYPE_NOTIFY_ROBOT_POSE", "getMSG_TYPE_NOTIFY_ROBOT_POSE$robot_open_sdk_release", "MSG_TYPE_NOTIFY_ROBOT_POWER", "getMSG_TYPE_NOTIFY_ROBOT_POWER$robot_open_sdk_release", "MSG_TYPE_POST_DOOR_CONTROL_STATE", "getMSG_TYPE_POST_DOOR_CONTROL_STATE", "MSG_TYPE_QUERY_DESTINATION_ROBOT", "getMSG_TYPE_QUERY_DESTINATION_ROBOT", "MSG_TYPE_QUERY_STATE", "getMSG_TYPE_QUERY_STATE", "MSG_TYPE_REQUEST_DATA", "getMSG_TYPE_REQUEST_DATA", "MSG_TYPE_REQUEST_DOOR_CONTROL", "getMSG_TYPE_REQUEST_DOOR_CONTROL$robot_open_sdk_release", "MSG_TYPE_UNBIND", "getMSG_TYPE_UNBIND$robot_open_sdk_release", "MSG_TYPE_UNBIND_ALL", "getMSG_TYPE_UNBIND_ALL$robot_open_sdk_release", "ROLE_BEEPER", "getROLE_BEEPER", "ROLE_DISINFECTION", "getROLE_DISINFECTION", "ROLE_SDK", "getROLE_SDK", "getShadowConfig", Constants.f1200PK, "dn", "pubBeeperTopicTemplate", "pubDeviceConnected", "pubDisinfectionTopicTemplate", "pubGroupTopicTemplate", "pubSdkTopicTemplate", "pubServiceTopicTemplate", "pubThingProUpdate", "subBeeperTopicTemplate", "subDisinfectionTopicTemplate", "subGroupTopicTemplate", "subSdkTopicTemplate", "subServiceTopicTemplate", "subShadowConfig", "unbindReplayTopicTemplate", "unbindTopicTemplate", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Constant {
    public static final String MSG_TYPE_ARRIVED = "arrived";
    public static final String MSG_TYPE_BROADCAST_BEEPER = "broadcastBeeper";
    public static final Constant INSTANCE = new Constant();
    private static final String MSG_TYPE_REQUEST_DATA = MSG_TYPE_REQUEST_DATA;
    private static final String MSG_TYPE_REQUEST_DATA = MSG_TYPE_REQUEST_DATA;
    private static final String MSG_TYPE_QUERY_STATE = MSG_TYPE_QUERY_STATE;
    private static final String MSG_TYPE_QUERY_STATE = MSG_TYPE_QUERY_STATE;
    private static final String MSG_TYPE_QUERY_DESTINATION_ROBOT = MSG_TYPE_QUERY_DESTINATION_ROBOT;
    private static final String MSG_TYPE_QUERY_DESTINATION_ROBOT = MSG_TYPE_QUERY_DESTINATION_ROBOT;
    private static final String MSG_TYPE_CALL = "call";
    private static final String MSG_TYPE_CANCEL_CALL = "cancelCall";
    private static final String MSG_TYPE_DELEVERY_TRAY_ORDER = MSG_TYPE_DELEVERY_TRAY_ORDER;
    private static final String MSG_TYPE_DELEVERY_TRAY_ORDER = MSG_TYPE_DELEVERY_TRAY_ORDER;
    private static final String MSG_TYPE_DELIVERY_TASK = MSG_TYPE_DELIVERY_TASK;
    private static final String MSG_TYPE_DELIVERY_TASK = MSG_TYPE_DELIVERY_TASK;
    private static final String MSG_TYPE_ACTION_COMMAND = MSG_TYPE_ACTION_COMMAND;
    private static final String MSG_TYPE_ACTION_COMMAND = MSG_TYPE_ACTION_COMMAND;
    private static final String MSG_TYPE_GET_ROBOT_CURRENT_MAP = MSG_TYPE_GET_ROBOT_CURRENT_MAP;
    private static final String MSG_TYPE_GET_ROBOT_CURRENT_MAP = MSG_TYPE_GET_ROBOT_CURRENT_MAP;
    private static final String MSG_TYPE_POST_DOOR_CONTROL_STATE = MSG_TYPE_POST_DOOR_CONTROL_STATE;
    private static final String MSG_TYPE_POST_DOOR_CONTROL_STATE = MSG_TYPE_POST_DOOR_CONTROL_STATE;
    private static final String MSG_TYPE_CUSTOM_CALL = MSG_TYPE_CUSTOM_CALL;
    private static final String MSG_TYPE_CUSTOM_CALL = MSG_TYPE_CUSTOM_CALL;
    private static final String MSG_TYPE_CUSTOM_CALL_CANCEL = MSG_TYPE_CUSTOM_CALL_CANCEL;
    private static final String MSG_TYPE_CUSTOM_CALL_CANCEL = MSG_TYPE_CUSTOM_CALL_CANCEL;
    private static final String MSG_TYPE_CUSTOM_CALL_CONTENT = MSG_TYPE_CUSTOM_CALL_CONTENT;
    private static final String MSG_TYPE_CUSTOM_CALL_CONTENT = MSG_TYPE_CUSTOM_CALL_CONTENT;
    private static final String MSG_TYPE_CUSTOM_CALL_COMPLETE = MSG_TYPE_CUSTOM_CALL_COMPLETE;
    private static final String MSG_TYPE_CUSTOM_CALL_COMPLETE = MSG_TYPE_CUSTOM_CALL_COMPLETE;
    private static final String MSG_TYPE_DIS_STATUS = MSG_TYPE_DIS_STATUS;
    private static final String MSG_TYPE_DIS_STATUS = MSG_TYPE_DIS_STATUS;
    private static final String MSG_TYPE_DIS_ACTION = MSG_TYPE_DIS_ACTION;
    private static final String MSG_TYPE_DIS_ACTION = MSG_TYPE_DIS_ACTION;
    private static final String MSG_TYPE_NOTIFY_GO_STATE = MSG_TYPE_NOTIFY_GO_STATE;
    private static final String MSG_TYPE_NOTIFY_GO_STATE = MSG_TYPE_NOTIFY_GO_STATE;
    private static final String MSG_TYPE_NOTIFY_ROBOT_ORDER_STATE = MSG_TYPE_NOTIFY_ROBOT_ORDER_STATE;
    private static final String MSG_TYPE_NOTIFY_ROBOT_ORDER_STATE = MSG_TYPE_NOTIFY_ROBOT_ORDER_STATE;
    private static final String MSG_TYPE_NOTIFY_DELIVERY_TASK = MSG_TYPE_NOTIFY_DELIVERY_TASK;
    private static final String MSG_TYPE_NOTIFY_DELIVERY_TASK = MSG_TYPE_NOTIFY_DELIVERY_TASK;
    private static final String MSG_TYPE_NOTIFY_ROBOT_POWER = MSG_TYPE_NOTIFY_ROBOT_POWER;
    private static final String MSG_TYPE_NOTIFY_ROBOT_POWER = MSG_TYPE_NOTIFY_ROBOT_POWER;
    private static final String MSG_TYPE_NOTIFY_ROBOT_MOVE_STATE = MSG_TYPE_NOTIFY_ROBOT_MOVE_STATE;
    private static final String MSG_TYPE_NOTIFY_ROBOT_MOVE_STATE = MSG_TYPE_NOTIFY_ROBOT_MOVE_STATE;
    private static final String MSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE = MSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE;
    private static final String MSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE = MSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE;
    private static final String MSG_TYPE_GET_DEVICE_ONLINE_STATE = MSG_TYPE_GET_DEVICE_ONLINE_STATE;
    private static final String MSG_TYPE_GET_DEVICE_ONLINE_STATE = MSG_TYPE_GET_DEVICE_ONLINE_STATE;
    private static final String MSG_TYPE_INIT_ROBOT_SHADOW_CONFIG = MSG_TYPE_INIT_ROBOT_SHADOW_CONFIG;
    private static final String MSG_TYPE_INIT_ROBOT_SHADOW_CONFIG = MSG_TYPE_INIT_ROBOT_SHADOW_CONFIG;
    private static final String MSG_TYPE_REQUEST_DOOR_CONTROL = MSG_TYPE_REQUEST_DOOR_CONTROL;
    private static final String MSG_TYPE_REQUEST_DOOR_CONTROL = MSG_TYPE_REQUEST_DOOR_CONTROL;
    private static final String MSG_TYPE_NOTIFY_ROBOT_POSE = MSG_TYPE_NOTIFY_ROBOT_POSE;
    private static final String MSG_TYPE_NOTIFY_ROBOT_POSE = MSG_TYPE_NOTIFY_ROBOT_POSE;
    private static final String MSG_TYPE_UNBIND_ALL = MSG_TYPE_UNBIND_ALL;
    private static final String MSG_TYPE_UNBIND_ALL = MSG_TYPE_UNBIND_ALL;
    private static final String MSG_TYPE_UNBIND = MSG_TYPE_UNBIND;
    private static final String MSG_TYPE_UNBIND = MSG_TYPE_UNBIND;
    private static final String MSG_TYPE_NOTIFY_QR_CODE_CONTENT = MSG_TYPE_NOTIFY_QR_CODE_CONTENT;
    private static final String MSG_TYPE_NOTIFY_QR_CODE_CONTENT = MSG_TYPE_NOTIFY_QR_CODE_CONTENT;
    private static final String MSG_TYPE_NOTIFY_CUSTOM_CALL = MSG_TYPE_NOTIFY_CUSTOM_CALL;
    private static final String MSG_TYPE_NOTIFY_CUSTOM_CALL = MSG_TYPE_NOTIFY_CUSTOM_CALL;
    private static final String ROLE_SDK = "sdk";
    private static final String ROLE_BEEPER = ROLE_BEEPER;
    private static final String ROLE_BEEPER = ROLE_BEEPER;
    private static final String ROLE_DISINFECTION = ROLE_DISINFECTION;
    private static final String ROLE_DISINFECTION = ROLE_DISINFECTION;

    private Constant() {
    }

    public final String getMSG_TYPE_REQUEST_DATA() {
        return MSG_TYPE_REQUEST_DATA;
    }

    public final String getMSG_TYPE_QUERY_STATE() {
        return MSG_TYPE_QUERY_STATE;
    }

    public final String getMSG_TYPE_QUERY_DESTINATION_ROBOT() {
        return MSG_TYPE_QUERY_DESTINATION_ROBOT;
    }

    public final String getMSG_TYPE_CALL() {
        return MSG_TYPE_CALL;
    }

    public final String getMSG_TYPE_CANCEL_CALL() {
        return MSG_TYPE_CANCEL_CALL;
    }

    public final String getMSG_TYPE_DELEVERY_TRAY_ORDER() {
        return MSG_TYPE_DELEVERY_TRAY_ORDER;
    }

    public final String getMSG_TYPE_DELIVERY_TASK() {
        return MSG_TYPE_DELIVERY_TASK;
    }

    public final String getMSG_TYPE_ACTION_COMMAND() {
        return MSG_TYPE_ACTION_COMMAND;
    }

    public final String getMSG_TYPE_GET_ROBOT_CURRENT_MAP() {
        return MSG_TYPE_GET_ROBOT_CURRENT_MAP;
    }

    public final String getMSG_TYPE_POST_DOOR_CONTROL_STATE() {
        return MSG_TYPE_POST_DOOR_CONTROL_STATE;
    }

    public final String getMSG_TYPE_CUSTOM_CALL() {
        return MSG_TYPE_CUSTOM_CALL;
    }

    public final String getMSG_TYPE_CUSTOM_CALL_CANCEL() {
        return MSG_TYPE_CUSTOM_CALL_CANCEL;
    }

    public final String getMSG_TYPE_CUSTOM_CALL_CONTENT() {
        return MSG_TYPE_CUSTOM_CALL_CONTENT;
    }

    public final String getMSG_TYPE_CUSTOM_CALL_COMPLETE() {
        return MSG_TYPE_CUSTOM_CALL_COMPLETE;
    }

    public final String getMSG_TYPE_DIS_STATUS() {
        return MSG_TYPE_DIS_STATUS;
    }

    public final String getMSG_TYPE_DIS_ACTION() {
        return MSG_TYPE_DIS_ACTION;
    }

    public final String getMSG_TYPE_NOTIFY_GO_STATE$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_GO_STATE;
    }

    public final String getMSG_TYPE_NOTIFY_ROBOT_ORDER_STATE$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_ROBOT_ORDER_STATE;
    }

    public final String getMSG_TYPE_NOTIFY_DELIVERY_TASK$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_DELIVERY_TASK;
    }

    public final String getMSG_TYPE_NOTIFY_ROBOT_POWER$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_ROBOT_POWER;
    }

    public final String getMSG_TYPE_NOTIFY_ROBOT_MOVE_STATE$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_ROBOT_MOVE_STATE;
    }

    public final String getMSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_DEVICE_ONLINE_STATE;
    }

    public final String getMSG_TYPE_GET_DEVICE_ONLINE_STATE$robot_open_sdk_release() {
        return MSG_TYPE_GET_DEVICE_ONLINE_STATE;
    }

    public final String getMSG_TYPE_INIT_ROBOT_SHADOW_CONFIG$robot_open_sdk_release() {
        return MSG_TYPE_INIT_ROBOT_SHADOW_CONFIG;
    }

    public final String getMSG_TYPE_REQUEST_DOOR_CONTROL$robot_open_sdk_release() {
        return MSG_TYPE_REQUEST_DOOR_CONTROL;
    }

    public final String getMSG_TYPE_NOTIFY_ROBOT_POSE$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_ROBOT_POSE;
    }

    public final String getMSG_TYPE_UNBIND_ALL$robot_open_sdk_release() {
        return MSG_TYPE_UNBIND_ALL;
    }

    public final String getMSG_TYPE_UNBIND$robot_open_sdk_release() {
        return MSG_TYPE_UNBIND;
    }

    public final String getMSG_TYPE_NOTIFY_QR_CODE_CONTENT$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_QR_CODE_CONTENT;
    }

    public final String getMSG_TYPE_NOTIFY_CUSTOM_CALL$robot_open_sdk_release() {
        return MSG_TYPE_NOTIFY_CUSTOM_CALL;
    }

    public final String getROLE_SDK() {
        return ROLE_SDK;
    }

    public final String getROLE_BEEPER() {
        return ROLE_BEEPER;
    }

    public final String getROLE_DISINFECTION() {
        return ROLE_DISINFECTION;
    }

    public final String pubBeeperTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/pub";
    }

    public final String pubGroupTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/pub_group";
    }

    public final String pubSdkTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/pub_sdk";
    }

    public final String pubDisinfectionTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/pub_disinfection";
    }

    public final String pubServiceTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/pub_service";
    }

    public final String subServiceTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/sub_service";
    }

    public final String subSdkTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/sub_sdk";
    }

    public final String subBeeperTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/sub";
    }

    public final String subDisinfectionTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/sub_disinfection";
    }

    public final String subGroupTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/sub_group";
    }

    public final String unbindTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/unbind";
    }

    public final String unbindReplayTopicTemplate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/user/unbind_replay";
    }

    public final String pubDeviceConnected(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return '/' + pk + '/' + dn + "/connect/status";
    }

    public final String getShadowConfig(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return "/shadow/update/" + pk + '/' + dn;
    }

    public final String subShadowConfig(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return "/shadow/get/" + pk + '/' + dn;
    }

    public final String pubThingProUpdate(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return "/sys/" + pk + '/' + dn + "/thing/event/property/post";
    }
}
