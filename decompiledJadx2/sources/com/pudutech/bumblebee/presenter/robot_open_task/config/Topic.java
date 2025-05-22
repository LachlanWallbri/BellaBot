package com.pudutech.bumblebee.presenter.robot_open_task.config;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Topic.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b)\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040.J\u0016\u0010/\u001a\u0002002\u0006\u0010*\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001a\u0010'\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\b¨\u00061"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/config/Topic;", "", "()V", "callCompleteDown", "", "getCallCompleteDown", "()Ljava/lang/String;", "setCallCompleteDown", "(Ljava/lang/String;)V", "callCompleteUp", "getCallCompleteUp", "setCallCompleteUp", "callDetailUp", "getCallDetailUp", "setCallDetailUp", "callDown", "getCallDown", "setCallDown", "callUp", "getCallUp", "setCallUp", "cmdDown", "getCmdDown", "setCmdDown", "commonMessageDown", "getCommonMessageDown", "setCommonMessageDown", "commonMessageUp", "getCommonMessageUp", "setCommonMessageUp", "commonStatusDown", "getCommonStatusDown", "setCommonStatusDown", "commonStatusUp", "getCommonStatusUp", "setCommonStatusUp", "deviceName", "getDeviceName", "setDeviceName", "g4WatchArrive", "getG4WatchArrive", "setG4WatchArrive", "productKey", "getProductKey", "setProductKey", "getCallTopic", "", "initTopic", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Topic {
    public static final Topic INSTANCE = new Topic();
    private static String commonStatusUp = "";
    private static String commonStatusDown = "";
    private static String cmdDown = "";
    private static String callDown = "";
    private static String callCompleteDown = "";
    private static String callUp = "";
    private static String callDetailUp = "";
    private static String g4WatchArrive = "";
    private static String callCompleteUp = "";
    private static String commonMessageDown = "";
    private static String commonMessageUp = "";
    private static String productKey = "";
    private static String deviceName = "";

    private Topic() {
    }

    public final String getCommonStatusUp() {
        return commonStatusUp;
    }

    public final void setCommonStatusUp(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        commonStatusUp = str;
    }

    public final String getCommonStatusDown() {
        return commonStatusDown;
    }

    public final void setCommonStatusDown(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        commonStatusDown = str;
    }

    public final String getCmdDown() {
        return cmdDown;
    }

    public final void setCmdDown(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        cmdDown = str;
    }

    public final String getCallDown() {
        return callDown;
    }

    public final void setCallDown(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        callDown = str;
    }

    public final String getCallCompleteDown() {
        return callCompleteDown;
    }

    public final void setCallCompleteDown(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        callCompleteDown = str;
    }

    public final String getCallUp() {
        return callUp;
    }

    public final void setCallUp(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        callUp = str;
    }

    public final String getCallDetailUp() {
        return callDetailUp;
    }

    public final void setCallDetailUp(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        callDetailUp = str;
    }

    public final String getG4WatchArrive() {
        return g4WatchArrive;
    }

    public final void setG4WatchArrive(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        g4WatchArrive = str;
    }

    public final String getCallCompleteUp() {
        return callCompleteUp;
    }

    public final void setCallCompleteUp(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        callCompleteUp = str;
    }

    public final String getCommonMessageDown() {
        return commonMessageDown;
    }

    public final void setCommonMessageDown(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        commonMessageDown = str;
    }

    public final String getCommonMessageUp() {
        return commonMessageUp;
    }

    public final void setCommonMessageUp(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        commonMessageUp = str;
    }

    public final String getProductKey() {
        return productKey;
    }

    public final void setProductKey(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        productKey = str;
    }

    public final String getDeviceName() {
        return deviceName;
    }

    public final void setDeviceName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        deviceName = str;
    }

    public final void initTopic(String productKey2, String deviceName2) {
        Intrinsics.checkParameterIsNotNull(productKey2, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName2, "deviceName");
        productKey = productKey2;
        deviceName = deviceName2;
        commonStatusDown = '/' + productKey2 + '/' + deviceName2 + "/user/common/status/down";
        cmdDown = '/' + productKey2 + '/' + deviceName2 + "/user/cmd/down";
        callDown = '/' + productKey2 + '/' + deviceName2 + "/user/common/call/down";
        callCompleteDown = '/' + productKey2 + '/' + deviceName2 + "/user/common/call/complete/down";
        commonMessageDown = '/' + productKey2 + '/' + deviceName2 + "/user/common/message/down";
        commonStatusUp = '/' + productKey2 + '/' + deviceName2 + "/user/common/status/up";
        callUp = '/' + productKey2 + '/' + deviceName2 + "/user/common/call/up";
        callCompleteUp = '/' + productKey2 + '/' + deviceName2 + "/user/common/call/complete/up";
        callDetailUp = '/' + productKey2 + '/' + deviceName2 + "/user/common/call/detail/up";
        commonMessageUp = '/' + productKey2 + '/' + deviceName2 + "/user/common/message/up";
        g4WatchArrive = '/' + productKey2 + '/' + deviceName2 + "/user/notify/v1";
    }

    public final Set<String> getCallTopic() {
        return SetsKt.setOf((Object[]) new String[]{commonStatusDown, cmdDown, callDown, callCompleteDown, commonMessageDown});
    }
}
