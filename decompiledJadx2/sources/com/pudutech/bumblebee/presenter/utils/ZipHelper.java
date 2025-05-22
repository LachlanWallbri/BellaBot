package com.pudutech.bumblebee.presenter.utils;

import com.pudutech.base.Pdlog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: ZipHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/ZipHelper;", "", "()V", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ZipHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "ZipHelper";

    @JvmStatic
    public static final String decompressForGzip(byte[] bArr) {
        return Companion.decompressForGzip$default(INSTANCE, bArr, null, 2, null);
    }

    @JvmStatic
    public static final String decompressForGzip(byte[] bArr, String str) {
        return INSTANCE.decompressForGzip(bArr, str);
    }

    @JvmStatic
    public static final String decompressToStringForZlib(byte[] bArr) {
        return Companion.decompressToStringForZlib$default(INSTANCE, bArr, null, 2, null);
    }

    @JvmStatic
    public static final String decompressToStringForZlib(byte[] bArr, String str) {
        return INSTANCE.decompressToStringForZlib(bArr, str);
    }

    private ZipHelper() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /* compiled from: ZipHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u0010\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\nJ\u0010\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u0004J\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\n2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0007J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nJ\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/ZipHelper$Companion;", "", "()V", "TAG", "", "closeQuietly", "", "closeable", "Ljava/io/Closeable;", "compressForGzip", "", "string", "compressForZlib", "bytesToCompress", "stringToCompress", "decompressForGzip", "compressed", "charsetName", "decompressForZlib", "bytesToDecompress", "decompressToStringForZlib", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        @JvmStatic
        public final String decompressForGzip(byte[] bArr) {
            return decompressForGzip$default(this, bArr, null, 2, null);
        }

        @JvmStatic
        public final String decompressToStringForZlib(byte[] bArr) {
            return decompressToStringForZlib$default(this, bArr, null, 2, null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ String decompressToStringForZlib$default(Companion companion, byte[] bArr, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                str = "UTF-8";
            }
            return companion.decompressToStringForZlib(bArr, str);
        }

        @JvmStatic
        public final String decompressToStringForZlib(byte[] bytesToDecompress, String charsetName) {
            Intrinsics.checkParameterIsNotNull(bytesToDecompress, "bytesToDecompress");
            Intrinsics.checkParameterIsNotNull(charsetName, "charsetName");
            byte[] decompressForZlib = decompressForZlib(bytesToDecompress);
            String str = (String) null;
            try {
                int length = decompressForZlib.length;
                Charset forName = Charset.forName(charsetName);
                Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
                str = new String(decompressForZlib, 0, length, forName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Pdlog.m3273d(ZipHelper.TAG, "decompressToStringForZlib compress response is " + str);
            return str;
        }

        public final byte[] decompressForZlib(byte[] bytesToDecompress) {
            Intrinsics.checkParameterIsNotNull(bytesToDecompress, "bytesToDecompress");
            byte[] bArr = new byte[0];
            Inflater inflater = new Inflater();
            int length = bytesToDecompress.length;
            inflater.setInput(bytesToDecompress, 0, length);
            ArrayList arrayList = new ArrayList();
            while (!inflater.needsInput()) {
                try {
                    byte[] bArr2 = new byte[length];
                    int inflate = inflater.inflate(bArr2);
                    for (int i = 0; i < inflate; i++) {
                        arrayList.add(Byte.valueOf(bArr2[i]));
                    }
                } catch (DataFormatException e) {
                    e.printStackTrace();
                }
            }
            bArr = new byte[arrayList.size()];
            int length2 = bArr.length;
            for (int i2 = 0; i2 < length2; i2++) {
                bArr[i2] = ((Number) arrayList.get(i2)).byteValue();
            }
            inflater.end();
            return bArr;
        }

        public final byte[] compressForZlib(byte[] bytesToCompress) {
            Deflater deflater = new Deflater();
            deflater.setInput(bytesToCompress);
            deflater.finish();
            byte[] bArr = new byte[32767];
            int deflate = deflater.deflate(bArr);
            byte[] bArr2 = new byte[deflate];
            System.arraycopy(bArr, 0, bArr2, 0, deflate);
            return bArr2;
        }

        public final byte[] compressForZlib(String stringToCompress) {
            Intrinsics.checkParameterIsNotNull(stringToCompress, "stringToCompress");
            byte[] bArr = (byte[]) null;
            try {
                Charset forName = Charset.forName("UTF-8");
                Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
                byte[] bytes = stringToCompress.getBytes(forName);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                return compressForZlib(bytes);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return bArr;
            }
        }

        public final byte[] compressForGzip(String string) {
            ByteArrayOutputStream byteArrayOutputStream;
            GZIPOutputStream gZIPOutputStream;
            Intrinsics.checkParameterIsNotNull(string, "string");
            ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream) null;
            GZIPOutputStream gZIPOutputStream2 = (GZIPOutputStream) null;
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream(string.length());
                    try {
                        gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    } catch (IOException e) {
                        e = e;
                    } catch (Throwable th) {
                        th = th;
                        gZIPOutputStream = gZIPOutputStream2;
                        Companion companion = this;
                        companion.closeQuietly(gZIPOutputStream);
                        companion.closeQuietly(byteArrayOutputStream);
                        throw th;
                    }
                    try {
                        Charset forName = Charset.forName("UTF-8");
                        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
                        byte[] bytes = string.getBytes(forName);
                        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                        gZIPOutputStream.write(bytes);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        Companion companion2 = this;
                        companion2.closeQuietly(gZIPOutputStream);
                        companion2.closeQuietly(byteArrayOutputStream);
                        return byteArray;
                    } catch (IOException e2) {
                        e = e2;
                        gZIPOutputStream2 = gZIPOutputStream;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        e.printStackTrace();
                        Companion companion3 = this;
                        companion3.closeQuietly(gZIPOutputStream2);
                        companion3.closeQuietly(byteArrayOutputStream2);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        Companion companion4 = this;
                        companion4.closeQuietly(gZIPOutputStream);
                        companion4.closeQuietly(byteArrayOutputStream);
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = byteArrayOutputStream2;
            }
        }

        public static /* synthetic */ String decompressForGzip$default(Companion companion, byte[] bArr, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                str = "UTF-8";
            }
            return companion.decompressForGzip(bArr, str);
        }

        @JvmStatic
        public final String decompressForGzip(byte[] compressed, String charsetName) {
            GZIPInputStream gZIPInputStream;
            ByteArrayInputStream byteArrayInputStream;
            Intrinsics.checkParameterIsNotNull(compressed, "compressed");
            int length = compressed.length;
            GZIPInputStream gZIPInputStream2 = (GZIPInputStream) null;
            ByteArrayInputStream byteArrayInputStream2 = (ByteArrayInputStream) null;
            try {
                try {
                    byteArrayInputStream = new ByteArrayInputStream(compressed);
                    try {
                        gZIPInputStream = new GZIPInputStream(byteArrayInputStream, length);
                        try {
                            StringBuilder sb = new StringBuilder();
                            byte[] bArr = new byte[length];
                            Ref.IntRef intRef = new Ref.IntRef();
                            while (true) {
                                int read = gZIPInputStream.read(bArr);
                                intRef.element = read;
                                if (read != -1) {
                                    int i = intRef.element;
                                    Charset forName = Charset.forName(charsetName);
                                    Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
                                    sb.append(new String(bArr, 0, i, forName));
                                } else {
                                    Pdlog.m3273d(ZipHelper.TAG, "current zip helper compress response is " + ((Object) sb));
                                    String sb2 = sb.toString();
                                    Companion companion = this;
                                    companion.closeQuietly(gZIPInputStream);
                                    companion.closeQuietly(byteArrayInputStream);
                                    return sb2;
                                }
                            }
                        } catch (IOException e) {
                            e = e;
                            gZIPInputStream2 = gZIPInputStream;
                            byteArrayInputStream2 = byteArrayInputStream;
                            e.printStackTrace();
                            Companion companion2 = this;
                            companion2.closeQuietly(gZIPInputStream2);
                            companion2.closeQuietly(byteArrayInputStream2);
                            return null;
                        } catch (Throwable th) {
                            th = th;
                            Companion companion3 = this;
                            companion3.closeQuietly(gZIPInputStream);
                            companion3.closeQuietly(byteArrayInputStream);
                            throw th;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    } catch (Throwable th2) {
                        th = th2;
                        gZIPInputStream = gZIPInputStream2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    gZIPInputStream = gZIPInputStream2;
                    byteArrayInputStream = byteArrayInputStream2;
                }
            } catch (IOException e3) {
                e = e3;
            }
        }

        private final void closeQuietly(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception unused) {
                }
            }
        }
    }
}
