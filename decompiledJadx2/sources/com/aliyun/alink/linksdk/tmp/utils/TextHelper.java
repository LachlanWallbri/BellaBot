package com.aliyun.alink.linksdk.tmp.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.resource.ResDescpt;
import com.aliyun.alink.linksdk.tmp.resource.TResManager;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.List;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TextHelper {
    static final String RANDOM_BYTE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    protected static final String TAG = "[Tmp]TextHelper";
    protected static Random sRandom = new Random();

    public static String combineStr(String str, String str2) {
        return str + str2;
    }

    public static String combineStr(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String queryProduct(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments == null || pathSegments.size() <= 1) {
            return null;
        }
        return pathSegments.get(1);
    }

    public static String queryDeviceName(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments == null || pathSegments.size() <= 2) {
            return null;
        }
        return pathSegments.get(2);
    }

    public static String queryEventIdentifier(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equalsIgnoreCase(TmpConstant.METHOD_PROPERTY_POST)) {
            return "post";
        }
        String[] split = str.split("\\.");
        if (split == null || split.length <= 1) {
            return null;
        }
        if ("post".equalsIgnoreCase(split[split.length - 1])) {
            return split[split.length - 2];
        }
        return split[split.length - 1];
    }

    public static String getRandomString() {
        return getRandomString(16);
    }

    public static String getRandomString(int i) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(RANDOM_BYTE.charAt(sRandom.nextInt(62)));
        }
        return sb.toString();
    }

    public static int getRandomInt() {
        return sRandom.nextInt();
    }

    public static String formatDownRawTopic(String str, String str2) {
        return "/sys/" + str + "/" + str2 + TmpConstant.URI_THING + TmpConstant.URI_MODEL + "/down_raw";
    }

    public static String formatPostReplyTopic(String str, String str2) {
        return "/sys/" + str + "/" + str2 + "/" + TmpConstant.EVENT_PROPERTY_URI_PRE + "/" + TmpConstant.EVENT_PROPERTY_URI_POST_REPLY;
    }

    public static String formatDownRawId(String str, String str2) {
        return str + str2 + "/" + TmpConstant.IDENTIFIER_RAW_DATA_DOWN;
    }

    public static String formatTopic(String str, String str2, String str3) {
        return "/dev/" + str + "/" + str2 + str3;
    }

    public static String formatReplaceTopic(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, str2).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, str3);
    }

    public static String getTopicStr(DeviceModel deviceModel, String str) {
        ResDescpt.ResElementType idType = TResManager.getIdType(deviceModel, str);
        ALog.m479d(TAG, "getTopicStr identifier:" + str + " type:" + idType + " deviceModel:" + deviceModel);
        if (TextUtils.isEmpty(str) || idType == null) {
            ALog.m480e(TAG, "getTopicStr null");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = C11391.f1039xe793efd4[idType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        sb = new StringBuilder(TmpConstant.PATH_DISCOVERY);
                    } else if (i == 5 && deviceModel != null) {
                        sb = new StringBuilder("/");
                        sb.append("dev");
                        sb.append("/");
                        sb.append(deviceModel.getProfile().getProdKey());
                        sb.append("/");
                        sb.append(deviceModel.getProfile().getName());
                        sb.append("/");
                        sb.append(TmpConstant.URI_PREFIX_ALCS_SERVICE);
                        sb.append("/");
                        sb.append(str);
                    }
                } else if (deviceModel != null) {
                    sb = new StringBuilder("/");
                    sb.append("sys");
                    sb.append("/");
                    sb.append(deviceModel.getProfile().getProdKey());
                    sb.append("/");
                    sb.append(deviceModel.getProfile().getName());
                    if (str.equalsIgnoreCase("post")) {
                        sb.append("/");
                        sb.append(TmpConstant.EVENT_PROPERTY_URI_PRE);
                        sb.append("/");
                        sb.append(str);
                    } else {
                        sb.append("/");
                        sb.append(TmpConstant.EVENT_URI_PRE);
                        sb.append("/");
                        sb.append(str);
                        sb.append("/");
                        sb.append("post");
                    }
                }
            } else if (deviceModel != null) {
                sb = new StringBuilder("/");
                sb.append("sys");
                sb.append("/");
                sb.append(deviceModel.getProfile().getProdKey());
                sb.append("/");
                sb.append(deviceModel.getProfile().getName());
                sb.append("/");
                sb.append(TmpConstant.METHOD_URI_PRE);
                sb.append("/");
                sb.append(str);
            }
        } else if (deviceModel != null) {
            sb = new StringBuilder("/");
            sb.append("sys");
            sb.append("/");
            sb.append(deviceModel.getProfile().getProdKey());
            sb.append("/");
            sb.append(deviceModel.getProfile().getName());
            sb.append("/");
            sb.append(TmpConstant.PROPERTY_URI_PRE);
            sb.append("/");
            sb.append(str);
        }
        return sb.toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.tmp.utils.TextHelper$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C11391 {

        /* renamed from: $SwitchMap$com$aliyun$alink$linksdk$tmp$resource$ResDescpt$ResElementType */
        static final /* synthetic */ int[] f1039xe793efd4;

        static {
            int[] iArr = new int[ResDescpt.ResElementType.values().length];
            f1039xe793efd4 = iArr;
            try {
                iArr[ResDescpt.ResElementType.PROPERTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1039xe793efd4[ResDescpt.ResElementType.SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1039xe793efd4[ResDescpt.ResElementType.EVENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1039xe793efd4[ResDescpt.ResElementType.DISCOVERY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1039xe793efd4[ResDescpt.ResElementType.ALCS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static String byte2hex(byte[] bArr, int i) {
        StringBuilder sb = new StringBuilder();
        if (bArr.length < i) {
            i = bArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(String.format("%02X", Integer.valueOf(bArr[i2] & 255)));
        }
        return sb.toString();
    }

    public static String formatResponseData(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            try {
                return new String((byte[]) obj, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return String.valueOf(obj);
    }
}
