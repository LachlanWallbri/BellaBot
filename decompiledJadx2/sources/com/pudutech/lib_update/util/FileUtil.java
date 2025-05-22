package com.pudutech.lib_update.util;

import android.util.Log;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: FileUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/lib_update/util/FileUtil;", "", "()V", "Companion", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FileUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: FileUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/lib_update/util/FileUtil$Companion;", "", "()V", "TAG", "", "calculateFileMD5", "updateFile", "Ljava/io/File;", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String calculateFileMD5(File updateFile) {
            Intrinsics.checkParameterIsNotNull(updateFile, "updateFile");
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                Intrinsics.checkExpressionValueIsNotNull(messageDigest, "MessageDigest.getInstance(\"MD5\")");
                try {
                    FileInputStream fileInputStream = new FileInputStream(updateFile);
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
                                    Log.e(FileUtil.TAG, "Exception on closing MD5 input stream", e);
                                }
                                return lowerCase;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        } catch (IOException e2) {
                            throw new RuntimeException("Unable to process file for MD5", e2);
                        }
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            Log.e(FileUtil.TAG, "Exception on closing MD5 input stream", e3);
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e4) {
                    Log.e(FileUtil.TAG, "Exception while getting FileInputStream", e4);
                    return null;
                }
            } catch (NoSuchAlgorithmException e5) {
                Log.e(FileUtil.TAG, "Exception while getting digest", e5);
                return null;
            }
        }
    }
}
