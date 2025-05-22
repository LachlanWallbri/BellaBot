package com.aliyun.alink.dm.api;

import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DMErrorCode {
    public static final int DM_INIT_GET_TSL_RESPONSE_ERROR = 200301;
    public static final int DM_INIT_PARAMS_INVALID = 200201;
    public static final int ERROR_API_CLIENT_SEND_FAIL = 528;
    public static final int ERROR_BUSINESS = 4103;
    public static final int ERROR_CALL_DEPRECATED_INTERFACE = 1102001;
    public static final int ERROR_CALL_INTERFACE_PARAMS_ERROR = 1102000;
    public static final int ERROR_CMP_PARAMS_ERROR = 510;
    public static final int ERROR_CMP_REGISTER_CONNECT_ERROR_EXIST = 514;
    public static final int ERROR_CMP_REGISTER_CONNECT_IS_REGISTERING = 529;
    public static final int ERROR_CMP_SEND_ERROR_CONNECT_NOT_CONNECTED = 521;
    public static final int ERROR_CMP_SEND_ERROR_CONNECT_NOT_FOUND = 517;
    public static final int ERROR_CMP_UNSUPPORT = 511;
    public static final int ERROR_COTA_GET_PARAMS_ERROR = 1101220;
    public static final int ERROR_DM_GET_TMP_IDEVICE = 1101203;
    public static final int ERROR_DM_GET_TSL_INFO_INVALID = 1101201;
    public static final int ERROR_DM_INIT_THING_FAILED_IS_INITING = 1101205;
    public static final int ERROR_DM_INIT_THING_PARAMS_INVALID = 1101202;
    public static final int ERROR_DM_RESET_FAILED = 1101204;
    public static final int ERROR_DUPLICATE_SDK_INIT = 1101020;
    public static final int ERROR_DUPLICATE_SDK_INIT_DM = 100;
    public static final int ERROR_DUPLICATE_SDK_INIT_LK = 101;
    public static final int ERROR_GATEWAY_LABEL_PARAMS_INVALID = 1101311;
    public static final int ERROR_GATEWAY_PERMIT_JOIN_DEVICE_INFO_INVALID = 1101312;
    public static final int ERROR_GATEWAY_SUBDEV_COTA_NULL = 1101307;
    public static final int ERROR_GATEWAY_SUBDEV_DELETED = 1101310;
    public static final int ERROR_GATEWAY_SUBDEV_DISABLED = 1101309;
    public static final int ERROR_GATEWAY_SUBDEV_INFO_INVALID = 1101308;
    public static final int ERROR_GATEWAY_SUBDEV_LABEL_NULL = 1101305;
    public static final int ERROR_GATEWAY_SUBDEV_NOT_LOGIN = 1101301;
    public static final int ERROR_GATEWAY_SUBDEV_SHADOW_NULL = 1101306;
    public static final int ERROR_GATEWAY_SUBDEV_THING_NOT_INITED = 1101304;
    public static final int ERROR_GATEWAY_SUBDEV_WRAPPER_INFO_NULL = 1101302;
    public static final int ERROR_GATEWAY_SUBDEV_WRAPPER_NULL = 1101303;
    public static final int ERROR_GATEWAY_TOPO_NOT_ADDED = 1101300;
    public static final int ERROR_HTTP = -4;
    public static final int ERROR_HTTP_SUBCODE_ILLEGAL_ARGUMENTS = 402;
    public static final int ERROR_HTTP_SUBCODE_UNINITED = 401;
    public static final int ERROR_NETWORK_ERROR = 4101;
    public static final int ERROR_PARAMS_ERROR = 1102002;
    public static final int ERROR_SERVER = 4102;
    public static final int ERROR_SHADOW_INVALID_STATE = 1101230;
    public static final int ERROR_SHADOW_PARAMS_INVALID = 1101232;
    public static final int ERROR_SHADOW_UPDATE_FAILED = 1101231;
    public static final int ERROR_TMP_INIT = 1101200;
    public static final int ERROR_UNKNOW = 4201;
    public static final int LINKKIT_INIT_FAIL = 1101312;
    public static final int MQTT_CONNECT_ERROR = -33;

    public static AError getErrorCode(int i, int i2, String str) {
        AError aError = new AError();
        aError.setCode(i);
        aError.setSubCode(i2);
        aError.setMsg(str);
        return aError;
    }
}
