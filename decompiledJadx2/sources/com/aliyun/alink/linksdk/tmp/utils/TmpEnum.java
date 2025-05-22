package com.aliyun.alink.linksdk.tmp.utils;

import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpEnum {
    private static final String TAG = "[Tmp]TmpEnum";

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum ChannelStrategy {
        LOCAL_CHANNEL_FIRST,
        LOCAL_CHANNEL_ONLY,
        CLOUD_CHANNEL_ONLY
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum DiscoveryDeviceState {
        DISCOVERY_STATE_ONLINE,
        DISCOVERY_STATE_OFFLINE
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum GroupRoleType {
        UNKNOWN("null"),
        CONTROLLER(TmpConstant.GROUP_ROLE_CONTROLLER),
        DEVICE(TmpConstant.GROUP_ROLE_DEVICE);

        private String value;

        GroupRoleType(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum DeviceState {
        UNKNOW(0),
        CONNECTED(1),
        DISCONNECTED(2),
        CONNECTING(3);

        private int value;

        DeviceState(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static DeviceState createConnectState(ConnectState connectState) {
            if (ConnectState.CONNECTED == connectState) {
                return CONNECTED;
            }
            if (ConnectState.DISCONNECTED == connectState || ConnectState.CONNECTFAIL == connectState) {
                return DISCONNECTED;
            }
            if (ConnectState.CONNECTING == connectState) {
                return CONNECTING;
            }
            ALog.m480e(TmpEnum.TAG, "createConnectState state unknown state:" + connectState);
            return UNKNOW;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum DeviceShadowUpdateType {
        UPDATE_OPTION_TSL(1),
        UPDATE_OPTION_DEVICE_DETAIL_INFO(2),
        UPDATE_OPTION_PROPERTIES(4),
        UPDATE_OPTION_STATUS(8),
        UPDATE_OPTION_ALL(-1);

        private int value;

        DeviceShadowUpdateType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
