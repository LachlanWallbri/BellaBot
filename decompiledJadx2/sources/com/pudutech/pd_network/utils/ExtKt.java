package com.pudutech.pd_network.utils;

import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0003\u001a\f\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0004"}, m3961d2 = {"TAG", "", "md5", "Ljava/io/File;", "pd_network_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ExtKt {
    private static final String TAG = "EXT";

    public static final String md5(String md5) {
        Intrinsics.checkParameterIsNotNull(md5, "$this$md5");
        if (md5.length() == 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            byte[] bytes = md5.getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            messageDigest.update(bytes);
            String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
            while (bigInteger.length() < 32) {
                bigInteger = '0' + bigInteger;
            }
            return bigInteger;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String md5(File md5) {
        Intrinsics.checkParameterIsNotNull(md5, "$this$md5");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            Intrinsics.checkExpressionValueIsNotNull(messageDigest, "MessageDigest.getInstance(\"MD5\")");
            try {
                FileInputStream fileInputStream = new FileInputStream(md5);
                byte[] bArr = new byte[8192];
                try {
                    try {
                        for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                            messageDigest.update(bArr, 0, read);
                        }
                        String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {bigInteger};
                        String format = String.format("%32s", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        String replace$default = StringsKt.replace$default(format, ' ', '0', false, 4, (Object) null);
                        if (replace$default != null) {
                            String lowerCase = replace$default.toLowerCase();
                            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                            try {
                                fileInputStream.close();
                            } catch (IOException e) {
                                NetWorkLog.INSTANCE.mo3279e(TAG, "Exception on closing MD5 input stream " + e.getMessage());
                            }
                            return lowerCase;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    } catch (IOException e2) {
                        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                        String str = TAG;
                        String message = e2.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        netWorkLog.mo3279e(str, message);
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            NetWorkLog.INSTANCE.mo3279e(TAG, "Exception on closing MD5 input stream " + e3.getMessage());
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        NetWorkLog.INSTANCE.mo3279e(TAG, "Exception on closing MD5 input stream " + e4.getMessage());
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                NetWorkLog.INSTANCE.mo3279e(TAG, "Exception while getting FileInputStream " + e5.getMessage());
                return null;
            }
        } catch (NoSuchAlgorithmException e6) {
            NetWorkLog.INSTANCE.mo3279e(TAG, "Exception while getting digest " + e6.getMessage());
            return null;
        }
    }
}
