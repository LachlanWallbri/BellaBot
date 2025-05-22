package com.aliyun.alink.linksdk.tmp.devicemodel.loader;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.devicemodel.Event;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class DeviceModelSerializer {
    protected static final String TAG = "[Tmp]DeviceModelSerializer";
    protected DeviceModelSerializer mChild;
    protected String mId;

    public abstract boolean deserialize(String str, String str2, ILoaderHandler iLoaderHandler);

    public abstract String serialize(String str, DeviceModel deviceModel);

    public DeviceModelSerializer(String str) {
        this.mId = str;
    }

    public void appendSerializer(DeviceModelSerializer deviceModelSerializer) {
        DeviceModelSerializer deviceModelSerializer2 = this.mChild;
        if (deviceModelSerializer2 == null) {
            this.mChild = deviceModelSerializer;
        } else {
            deviceModelSerializer2.appendSerializer(deviceModelSerializer);
        }
    }

    public DeviceModel deserializeSync(String str, String str2) {
        return deserializeInner(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DeviceModel deserializeInner(String str) {
        return (DeviceModel) GsonUtils.fromJson(str, new TypeToken<DeviceModel>() { // from class: com.aliyun.alink.linksdk.tmp.devicemodel.loader.DeviceModelSerializer.1
        }.getType());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String serializeInner(DeviceModel deviceModel) {
        return GsonUtils.toJson(deviceModel);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DeviceModelSerializer dispatch(String str) {
        if (TextUtils.isEmpty(this.mId)) {
            LogCat.m471e(TAG, "dispatch empty id error");
        } else if (this.mId.equalsIgnoreCase(str)) {
            return this;
        }
        DeviceModelSerializer deviceModelSerializer = this.mChild;
        if (deviceModelSerializer != null) {
            return deviceModelSerializer.dispatch(str);
        }
        return null;
    }

    public static void addChildModel(DeviceModel deviceModel, DeviceModel deviceModel2) {
        if (deviceModel == null || deviceModel2 == null) {
            return;
        }
        addList(deviceModel.getProperties(), deviceModel2.getProperties());
        addList(deviceModel.getServices(), deviceModel2.getServices());
        addList(deviceModel.getEvents(), deviceModel2.getEvents());
    }

    protected static void expandEvent(String str, DeviceModel deviceModel) {
        if (deviceModel == null) {
            LogCat.m469d(TAG, "expandEvent model empty");
            return;
        }
        if (deviceModel.getEvents() == null || deviceModel.getEvents().isEmpty()) {
            LogCat.m469d(TAG, "expandEvent event empty");
            return;
        }
        for (int i = 0; i < deviceModel.getEvents().size(); i++) {
            Event event = deviceModel.getEvents().get(i);
            if (event != null) {
                event.setName(expand(str, event.getIdentifier()));
            }
        }
    }

    protected static void addList(List list, List list2) {
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        if (list == null) {
            list = new LinkedList();
        }
        list.addAll(list2);
    }

    public static String expand(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return str + "." + str2;
    }

    public static String froamtUrl(String str, String str2) {
        return str + "/" + str2 + ".json";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v16, types: [java.io.BufferedInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String requestDeviceModel(String str, String str2) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2;
        String str3 = null;
        try {
            try {
                str = (HttpURLConnection) new URL(froamtUrl(str, str2)).openConnection();
            } catch (Exception e) {
                e = e;
                str = 0;
                str2 = 0;
            } catch (Throwable th2) {
                str2 = 0;
                byteArrayOutputStream = null;
                th = th2;
                str = 0;
            }
            try {
                str.setConnectTimeout(10000);
                str.setReadTimeout(5000);
                str2 = new BufferedInputStream(str.getInputStream());
            } catch (Exception e2) {
                e = e2;
                str2 = 0;
                str = str;
                byteArrayOutputStream2 = str2;
                e.printStackTrace();
                if (str2 != 0) {
                }
                if (byteArrayOutputStream2 != null) {
                }
                str.disconnect();
                return str3;
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                th = th3;
                str2 = 0;
            }
            try {
                byte[] bArr = new byte[1024];
                byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = str2.read(bArr, 0, 1024);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr, 0, read);
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        if (str2 != 0) {
                            try {
                                str2.close();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e5) {
                                e = e5;
                                e.printStackTrace();
                                str.disconnect();
                                return str3;
                            }
                        }
                        str.disconnect();
                        return str3;
                    }
                }
                str3 = byteArrayOutputStream2.toString();
                try {
                    str2.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            } catch (Exception e7) {
                e = e7;
                byteArrayOutputStream2 = null;
            } catch (Throwable th4) {
                byteArrayOutputStream = null;
                th = th4;
                if (str2 != 0) {
                    try {
                        str2.close();
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e9) {
                        e9.printStackTrace();
                    }
                }
                str.disconnect();
                throw th;
            }
            try {
                byteArrayOutputStream2.close();
            } catch (Exception e10) {
                e = e10;
                e.printStackTrace();
                str.disconnect();
                return str3;
            }
            str.disconnect();
            return str3;
        } catch (Throwable th5) {
            th = th5;
        }
    }
}
