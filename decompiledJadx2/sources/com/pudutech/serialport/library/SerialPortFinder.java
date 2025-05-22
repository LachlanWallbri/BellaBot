package com.pudutech.serialport.library;

import android.util.Log;
import com.pudutech.base.Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: SerialPortFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J0\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\u0015\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0002¢\u0006\u0002\u0010\fJ$\u0010\r\u001a\u00020\u000e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J.\u0010\r\u001a\u00020\u000e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010\u0011\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0002¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/serialport/library/SerialPortFinder;", "", "()V", "getSerialPortName", "", "idVendor", "idProduct", "product", "interfaceNum", "getTTYFileArray", "", "Ljava/io/File;", "()[Ljava/io/File;", "isPullOut", "", "isSymbolicLink", "file", "readFileToString", "Companion", "library_serialport_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class SerialPortFinder {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<SerialPortFinder>() { // from class: com.pudutech.serialport.library.SerialPortFinder$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SerialPortFinder invoke() {
            return new SerialPortFinder();
        }
    });
    public static final String PATH_ID_PRODUCT = "/../../../../idProduct";
    public static final String PATH_ID_VENDOR = "/../../../../idVendor";
    public static final String PATH_INTERFACE_NUM = "/../../../bInterfaceNumber";
    public static final String PATH_PRODUCT = "/../../../../product";
    public static final String TAG = "SerialPortFinder";

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* compiled from: SerialPortFinder.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/serialport/library/SerialPortFinder$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/serialport/library/SerialPortFinder;", "getINSTANCE", "()Lcom/pudutech/serialport/library/SerialPortFinder;", "INSTANCE$delegate", "Lkotlin/Lazy;", "PATH_ID_PRODUCT", "", "PATH_ID_VENDOR", "PATH_INTERFACE_NUM", "PATH_PRODUCT", "TAG", "library_serialport_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "INSTANCE", "getINSTANCE()Lcom/pudutech/serialport/library/SerialPortFinder;"))};

        public final SerialPortFinder getINSTANCE() {
            Lazy lazy = SerialPortFinder.INSTANCE$delegate;
            Companion companion = SerialPortFinder.INSTANCE;
            KProperty kProperty = $$delegatedProperties[0];
            return (SerialPortFinder) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final File[] getTTYFileArray() {
        Tools.execCommand("chmod 777 /sys/class/tty/", true);
        File file = new File("/sys/class/tty/");
        if (file.exists()) {
            return file.listFiles();
        }
        return null;
    }

    public final String getSerialPortName(String idVendor, String idProduct, String product) throws IllegalArgumentException {
        return getSerialPortName(idVendor, idProduct, product, null);
    }

    public final String getSerialPortName(String idVendor, String idProduct, String product, String interfaceNum) throws IllegalArgumentException {
        File[] tTYFileArray = getTTYFileArray();
        if (tTYFileArray == null) {
            return null;
        }
        for (File file : tTYFileArray) {
            boolean isSymbolicLink = isSymbolicLink(file);
            Log.d(TAG, "isSymlink[" + isSymbolicLink + "], absolutePath[" + file.getAbsolutePath() + "], canonicalPath[" + file.getCanonicalPath() + ']');
            if (isSymbolicLink) {
                File file2 = new File(file.getCanonicalPath() + PATH_ID_VENDOR);
                File file3 = new File(file.getCanonicalPath() + PATH_ID_PRODUCT);
                File file4 = new File(file.getCanonicalPath() + PATH_PRODUCT);
                String str = interfaceNum;
                if (str == null || str.length() == 0) {
                    if (file2.exists() && file3.exists() && file4.exists()) {
                        String readFileToString = readFileToString(file2);
                        String readFileToString2 = readFileToString(file3);
                        String readFileToString3 = readFileToString(file4);
                        if (!(!Intrinsics.areEqual(idVendor, readFileToString)) && !(!Intrinsics.areEqual(idProduct, readFileToString2)) && !(!Intrinsics.areEqual(product, readFileToString3))) {
                            return file.getName();
                        }
                    }
                } else {
                    File file5 = new File(file.getCanonicalPath() + PATH_INTERFACE_NUM);
                    if (file2.exists() && file3.exists() && file4.exists() && file5.exists()) {
                        String readFileToString4 = readFileToString(file2);
                        String readFileToString5 = readFileToString(file3);
                        String readFileToString6 = readFileToString(file4);
                        String readFileToString7 = readFileToString(file5);
                        if (!(!Intrinsics.areEqual(idVendor, readFileToString4)) && !(!Intrinsics.areEqual(idProduct, readFileToString5)) && !(!Intrinsics.areEqual(product, readFileToString6)) && !(!Intrinsics.areEqual(interfaceNum, readFileToString7))) {
                            return file.getName();
                        }
                    }
                }
            }
        }
        return null;
    }

    public final boolean isPullOut(String idVendor, String idProduct, String product) throws IllegalArgumentException {
        return isPullOut(idVendor, idProduct, product, null);
    }

    public final boolean isPullOut(String idVendor, String idProduct, String product, String interfaceNum) throws IllegalArgumentException {
        File[] tTYFileArray = getTTYFileArray();
        if (tTYFileArray != null) {
            for (File file : tTYFileArray) {
                boolean isSymbolicLink = isSymbolicLink(file);
                Log.d(TAG, "isSymlink[" + isSymbolicLink + "], absolutePath[" + file.getAbsolutePath() + "], canonicalPath[" + file.getCanonicalPath() + ']');
                if (isSymbolicLink) {
                    File file2 = new File(file.getCanonicalPath() + PATH_ID_VENDOR);
                    File file3 = new File(file.getCanonicalPath() + PATH_ID_PRODUCT);
                    File file4 = new File(file.getCanonicalPath() + PATH_PRODUCT);
                    String str = interfaceNum;
                    if (str == null || str.length() == 0) {
                        if (file2.exists() && file3.exists() && file4.exists()) {
                            String readFileToString = readFileToString(file2);
                            String readFileToString2 = readFileToString(file3);
                            String readFileToString3 = readFileToString(file4);
                            if (Intrinsics.areEqual(idVendor, readFileToString) && Intrinsics.areEqual(idProduct, readFileToString2) && Intrinsics.areEqual(product, readFileToString3)) {
                                return false;
                            }
                        }
                    } else {
                        File file5 = new File(file.getCanonicalPath() + PATH_INTERFACE_NUM);
                        if (file2.exists() && file3.exists() && file4.exists() && file5.exists()) {
                            String readFileToString4 = readFileToString(file2);
                            String readFileToString5 = readFileToString(file3);
                            String readFileToString6 = readFileToString(file4);
                            String readFileToString7 = readFileToString(file5);
                            if (Intrinsics.areEqual(idVendor, readFileToString4) && Intrinsics.areEqual(idProduct, readFileToString5) && Intrinsics.areEqual(product, readFileToString6) && Intrinsics.areEqual(interfaceNum, readFileToString7)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private final boolean isSymbolicLink(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new NullPointerException("file be null or does not exist.");
        }
        return !Intrinsics.areEqual(file.getAbsolutePath(), file.getCanonicalPath());
    }

    private final String readFileToString(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new NullPointerException("file be null or does not exist.");
        }
        StringBuilder sb = new StringBuilder();
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            try {
                byte[] bArr = new byte[256];
                for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                    sb.append(new String(bArr, 0, read, Charsets.UTF_8));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileInputStream.close();
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
            return StringsKt.replace$default(sb2, "\n", "", false, 4, (Object) null);
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }
}
