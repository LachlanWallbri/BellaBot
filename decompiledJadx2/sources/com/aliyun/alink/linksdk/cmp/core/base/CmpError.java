package com.aliyun.alink.linksdk.cmp.core.base;

import androidx.core.view.InputDeviceCompat;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.linksdk.tools.AError;
import com.hoho.android.usbserial.driver.UsbId;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CmpError extends AError {
    public CmpError() {
        setSubDomain("cmpError");
    }

    public static CmpError PARAMS_ERROR() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(510);
        cmpError.setMsg("params error");
        return cmpError;
    }

    public static CmpError UNKNOW() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(511);
        cmpError.setMsg("unsupport");
        return cmpError;
    }

    public static CmpError UNSUPPORT() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(512);
        cmpError.setMsg("unsupport");
        return cmpError;
    }

    public static CmpError APIGW_SEND_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(InputDeviceCompat.SOURCE_DPAD);
        cmpError.setMsg("api gateway send fail");
        return cmpError;
    }

    public static CmpError REGISTER_CONNECT_ERROR_EXIST() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(DMErrorCode.ERROR_CMP_REGISTER_CONNECT_ERROR_EXIST);
        cmpError.setMsg("connect is exist");
        return cmpError;
    }

    public static CmpError REGISTER_MQTT_CONNECT_ERROR_APIGW_NOT_REG() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(515);
        cmpError.setMsg("Please register api gateway firstly");
        return cmpError;
    }

    public static CmpError REGISTER_MQTT_CONNECT_ERROR_AUTH_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(UsbId.ARM_MBED);
        cmpError.setMsg("get mobile triple values error");
        return cmpError;
    }

    public static CmpError SEND_ERROR_CONNECT_NOT_FOUND() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(517);
        cmpError.setMsg("connect not found , please register firstly");
        return cmpError;
    }

    public static CmpError ALCS_INIT_MULTIPORT_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(518);
        cmpError.setMsg("init alcs multiport fail");
        return cmpError;
    }

    public static CmpError CONNECT_AUTH_INFO_ERROR() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(519);
        cmpError.setMsg("auth result data error");
        return cmpError;
    }

    public static CmpError ALCS_INIT_ERROR() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(520);
        cmpError.setMsg("alcs client init error");
        return cmpError;
    }

    public static CmpError CONNECT_FAIL_DISCONNECT() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(DMErrorCode.ERROR_CMP_SEND_ERROR_CONNECT_NOT_CONNECTED);
        cmpError.setMsg("current connect is not connected");
        return cmpError;
    }

    public static CmpError ALCS_SEND_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(522);
        cmpError.setMsg("Alcs send fail");
        return cmpError;
    }

    public static CmpError ALCS_SUBSCRIBE_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(523);
        cmpError.setMsg("Alcs subscribe fail");
        return cmpError;
    }

    public static CmpError ALCS_UNSUBSCRIBE_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(524);
        cmpError.setMsg("Alcs un subscribe fail");
        return cmpError;
    }

    public static CmpError ALCS_REQUEST_CLIENT_AUTH_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(525);
        cmpError.setMsg("Alcs request client auth info fail");
        return cmpError;
    }

    public static CmpError ALCS_REQUEST_SERVER_AUTH_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(526);
        cmpError.setMsg("Alcs request server auth info fail");
        return cmpError;
    }

    public static CmpError MQTT_CONNECT_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(527);
        cmpError.setMsg("mqtt connect fail");
        return cmpError;
    }

    public static CmpError HUB_API_SEND_FAIL() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(DMErrorCode.ERROR_API_CLIENT_SEND_FAIL);
        cmpError.setMsg("hub api client send fail");
        return cmpError;
    }

    public static CmpError REGISTER_CONNECT_IS_REGISTERING() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(DMErrorCode.ERROR_CMP_REGISTER_CONNECT_IS_REGISTERING);
        cmpError.setMsg("singleton connect is Registering");
        return cmpError;
    }

    public static CmpError PUBLISH_RESOURCE_ERROR() {
        CmpError cmpError = new CmpError();
        cmpError.setCode(530);
        cmpError.setMsg("publish resource error");
        return cmpError;
    }

    public String toString() {
        return "CmpError:[code = " + getCode() + ",msg = " + getMsg() + "]";
    }
}
