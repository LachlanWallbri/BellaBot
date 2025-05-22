package com.aliyun.alink.linksdk.tmp.utils;

import android.util.Log;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.alink.p022h2.api.Constraint;
import com.amazonaws.services.p048s3.internal.Constants;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.http.protocol.HTTP;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Secure {
    private static final String TAG = "[Tmp]Secure";

    public static byte[] getHMacSha1Str(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), Constants.HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(Constants.HMAC_SHA1_ALGORITHM);
            mac.init(secretKeySpec);
            return mac.doFinal(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static String md5(String str) {
        try {
            byte[] bytes = str.getBytes(HTTP.ASCII);
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getSignValue(String str, String str2, String str3, String str4) {
        ALog.m479d(TAG, "clientId:" + str + " pk:" + str2 + " dn:" + str2 + " devSec:" + str4);
        String str5 = TmpConstant.KEY_CLIENT_ID + str + "deviceName" + str3 + Constraint.PARAM_DEVICE_SECRET + str4 + "productKey" + str2;
        String str6 = null;
        try {
            ALog.m479d(TAG, "input:" + str5);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str5.getBytes());
            byte[] digest = messageDigest.digest();
            byte[] copyOfRange = Arrays.copyOfRange(digest, 0, digest.length);
            str6 = TextHelper.byte2hex(copyOfRange, copyOfRange.length);
            Log.d(TAG, "digest:" + str6);
            return str6;
        } catch (Exception e) {
            e.printStackTrace();
            return str6;
        }
    }
}
