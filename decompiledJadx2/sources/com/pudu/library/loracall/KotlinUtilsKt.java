package com.pudu.library.loracall;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.MutableLiveData;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.slamtec.slamware.robot.HealthInfo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.CharsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: KotlinUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\u001a\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017\u001a\b\u0010\u0018\u001a\u00020\u0019H\u0002\u001a\n\u0010\u001a\u001a\u00020\u001b*\u00020\u0002\u001a\u0014\u0010\u001c\u001a\u00020\u0010*\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u0010\u001a\u0014\u0010\u001e\u001a\u00020\u0010*\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u0010\u001a\u0014\u0010\u001f\u001a\u00020\u0010*\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u0010\u001a\u0014\u0010 \u001a\u00020\u0002*\u00020!2\b\b\u0001\u0010\"\u001a\u00020\u0010\u001a\n\u0010#\u001a\u00020\u001b*\u00020\u0002\u001a\"\u0010$\u001a\u00020\u0013*\u00020!2\b\b\u0002\u0010%\u001a\u00020\u00022\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00020'\u001a\n\u0010(\u001a\u00020\u0010*\u00020\u0002\u001a\u0014\u0010)\u001a\u00020\u0010*\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u0010\u001a\n\u0010*\u001a\u00020\u0013*\u00020\u0002\u001a\n\u0010+\u001a\u00020\u0002*\u00020,\u001a\u0010\u0010-\u001a\u00020\u001b*\b\u0012\u0004\u0012\u00020/0.\u001a\n\u00100\u001a\u00020\u0002*\u00020\u001b\u001a\n\u00101\u001a\u00020\u0002*\u00020/\u001a\n\u00101\u001a\u00020\u0002*\u00020\u001b\u001a\u0010\u00101\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020/0.\u001a\n\u00102\u001a\u00020\u001b*\u00020\u0010\u001a\n\u00102\u001a\u00020\u001b*\u000203\"\u001f\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t\"\u0015\u0010\f\u001a\u00020\r*\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u0015\u0010\f\u001a\u00020\r*\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0011¨\u00064"}, m3961d2 = {"logList", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getLogList", "()Landroidx/lifecycle/MutableLiveData;", "myScope", "Lkotlinx/coroutines/CoroutineScope;", "getMyScope", "()Lkotlinx/coroutines/CoroutineScope;", "myScope$delegate", "Lkotlin/Lazy;", "dp", "", "getDp", "(F)F", "", "(I)F", "copyFile", "", "input", "Ljava/io/InputStream;", "output", "Ljava/io/OutputStream;", "createJob", "Lkotlin/coroutines/CoroutineContext;", "ascii2ByteArray", "", "bytesToInt", TypedValues.Cycle.S_WAVE_OFFSET, "bytesToIntThree", "bytesToIntTwo", "getString", "", "id", "hex2Bytes", "log", AIUIConstant.KEY_TAG, "block", "Lkotlin/Function0;", "parseColor", "readInt16LE", "showToast", "stackTraceToString", "", "toByteArray", "", "", "toCharString", "toHexString", "tolBytes", "", "library_loracall_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class KotlinUtilsKt {
    private static final Lazy myScope$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<CoroutineScope>() { // from class: com.pudu.library.loracall.KotlinUtilsKt$myScope$2
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            CoroutineContext createJob;
            createJob = KotlinUtilsKt.createJob();
            return CoroutineScopeKt.CoroutineScope(createJob.plus(Dispatchers.getIO()));
        }
    });
    private static final MutableLiveData<String> logList = new MutableLiveData<>("");

    public static final CoroutineScope getMyScope() {
        return (CoroutineScope) myScope$delegate.getValue();
    }

    public static final byte[] tolBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24)};
    }

    public static final byte[] tolBytes(long j) {
        return new byte[]{(byte) (255 & j), (byte) ((65280 & j) >> 8), (byte) ((16711680 & j) >> 16), (byte) ((j & (-16777216)) >> 24)};
    }

    public static final float getDp(int i) {
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        return (i * system.getDisplayMetrics().density) + 0.5f;
    }

    public static final float getDp(float f) {
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        return TypedValue.applyDimension(1, f, system.getDisplayMetrics());
    }

    public static final int parseColor(String parseColor) {
        Intrinsics.checkParameterIsNotNull(parseColor, "$this$parseColor");
        try {
            return Color.parseColor(parseColor);
        } catch (Exception unused) {
            return Color.parseColor("#FFECF4");
        }
    }

    public static final MutableLiveData<String> getLogList() {
        return logList;
    }

    public static /* synthetic */ void log$default(Object obj, String str, Function0 function0, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = "--------";
        }
        log(obj, str, function0);
    }

    public static final void log(Object log, String tag, Function0<String> block) {
        Intrinsics.checkParameterIsNotNull(log, "$this$log");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Pdlog.m3273d("--------" + log.getClass().getSimpleName(), block.invoke());
    }

    public static final void showToast(String showToast) {
        Intrinsics.checkParameterIsNotNull(showToast, "$this$showToast");
        Context mContext$library_loracall_release = LoRaClient.INSTANCE.getInstance().getMContext$library_loracall_release();
        if (mContext$library_loracall_release != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new KotlinUtilsKt$showToast$$inlined$also$lambda$1(mContext$library_loracall_release, null, showToast), 2, null);
        }
    }

    public static final String getString(Object getString, int i) {
        Intrinsics.checkParameterIsNotNull(getString, "$this$getString");
        String string = LoRaClient.INSTANCE.getInstance().getMContext$library_loracall_release().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "LoRaClient.instance.mContext.getString(id)");
        return string;
    }

    public static final String toHexString(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        StringBuilder sb = new StringBuilder();
        for (byte b : toHexString) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Byte.valueOf(b)};
            String format = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            sb.append(format);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    public static final byte[] hex2Bytes(String hex2Bytes) {
        Intrinsics.checkParameterIsNotNull(hex2Bytes, "$this$hex2Bytes");
        int i = 0;
        if (hex2Bytes.length() == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[hex2Bytes.length() / 2];
        int i2 = 0;
        while (i < hex2Bytes.length()) {
            int i3 = i + 2;
            String substring = hex2Bytes.substring(i, i3);
            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            bArr[i2] = (byte) Integer.parseInt(substring, CharsKt.checkRadix(16));
            i2++;
            i = i3;
        }
        return bArr;
    }

    public static final String toHexString(List<Byte> toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        StringBuilder sb = new StringBuilder();
        Iterator<Byte> it = toHexString.iterator();
        while (it.hasNext()) {
            byte byteValue = it.next().byteValue();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Byte.valueOf(byteValue)};
            String format = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            sb.append(format);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    public static final String toHexString(byte b) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Byte.valueOf(b)};
        String format = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
        return format;
    }

    public static final byte[] ascii2ByteArray(String ascii2ByteArray) {
        Intrinsics.checkParameterIsNotNull(ascii2ByteArray, "$this$ascii2ByteArray");
        Charset forName = Charset.forName("US-ASCII");
        Intrinsics.checkNotNullExpressionValue(forName, "Charset.forName(charsetName)");
        byte[] bytes = ascii2ByteArray.getBytes(forName);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    public static final byte[] toByteArray(List<Byte> toByteArray) {
        Intrinsics.checkParameterIsNotNull(toByteArray, "$this$toByteArray");
        byte[] bArr = new byte[toByteArray.size()];
        int i = 0;
        for (Object obj : toByteArray) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            bArr[i] = ((Number) obj).byteValue();
            i = i2;
        }
        return bArr;
    }

    public static /* synthetic */ int bytesToInt$default(byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return bytesToInt(bArr, i);
    }

    public static final int bytesToInt(byte[] bytesToInt, int i) {
        Intrinsics.checkParameterIsNotNull(bytesToInt, "$this$bytesToInt");
        return ((bytesToInt[i + 3] & 255) << 24) | (bytesToInt[i] & 255) | ((bytesToInt[i + 1] & 255) << 8) | ((bytesToInt[i + 2] & 255) << 16);
    }

    public static /* synthetic */ int bytesToIntTwo$default(byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return bytesToIntTwo(bArr, i);
    }

    public static final int bytesToIntTwo(byte[] bytesToIntTwo, int i) {
        Intrinsics.checkParameterIsNotNull(bytesToIntTwo, "$this$bytesToIntTwo");
        return ((bytesToIntTwo[i + 1] & 255) << 8) | (bytesToIntTwo[i] & 255);
    }

    public static /* synthetic */ int readInt16LE$default(byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return readInt16LE(bArr, i);
    }

    public static final int readInt16LE(byte[] readInt16LE, int i) {
        Intrinsics.checkParameterIsNotNull(readInt16LE, "$this$readInt16LE");
        return (readInt16LE[i + 1] << 8) + (readInt16LE[i] & 255);
    }

    public static /* synthetic */ int bytesToIntThree$default(byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return bytesToIntThree(bArr, i);
    }

    public static final int bytesToIntThree(byte[] bytesToIntThree, int i) {
        Intrinsics.checkParameterIsNotNull(bytesToIntThree, "$this$bytesToIntThree");
        return ((bytesToIntThree[i + 2] & 255) << 16) | (bytesToIntThree[i] & 255) | ((bytesToIntThree[i + 1] & 255) << 8);
    }

    public static final String stackTraceToString(Throwable stackTraceToString) {
        Intrinsics.checkParameterIsNotNull(stackTraceToString, "$this$stackTraceToString");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        stackTraceToString.printStackTrace(printWriter);
        printWriter.flush();
        String stringWriter2 = stringWriter.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringWriter2, "sw.toString()");
        return stringWriter2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0061, code lost:
    
        if (r1 == null) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void copyFile(InputStream input, OutputStream output) {
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream;
        Intrinsics.checkParameterIsNotNull(input, "input");
        Intrinsics.checkParameterIsNotNull(output, "output");
        BufferedInputStream bufferedInputStream2 = (BufferedInputStream) null;
        BufferedOutputStream bufferedOutputStream2 = (BufferedOutputStream) null;
        try {
            bufferedInputStream = new BufferedInputStream(input);
            try {
                bufferedOutputStream = new BufferedOutputStream(output);
            } catch (Exception unused) {
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedInputStream != null) {
                }
            } catch (Throwable th2) {
                bufferedOutputStream = bufferedOutputStream2;
                th = th2;
            }
        } catch (Exception unused2) {
            bufferedInputStream = bufferedInputStream2;
        } catch (Throwable th3) {
            bufferedOutputStream = bufferedOutputStream2;
            th = th3;
            bufferedInputStream = bufferedInputStream2;
        }
        try {
            byte[] bArr = new byte[HealthInfo.BaseError.BaseComponentErrorTypeSensorNone];
            Ref.IntRef intRef = new Ref.IntRef();
            while (true) {
                int read = bufferedInputStream.read(bArr);
                intRef.element = read;
                if (read == -1) {
                    break;
                } else {
                    bufferedOutputStream.write(bArr, 0, intRef.element);
                }
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
        } catch (Exception unused3) {
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        } catch (Throwable th4) {
            th = th4;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            output.close();
            input.close();
            throw th;
        }
        bufferedOutputStream.close();
        output.close();
        input.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CoroutineContext createJob() {
        return SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(new KotlinUtilsKt$createJob$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE));
    }

    public static final String toCharString(byte[] toCharString) {
        Intrinsics.checkParameterIsNotNull(toCharString, "$this$toCharString");
        ArrayList arrayList = new ArrayList(toCharString.length);
        for (byte b : toCharString) {
            arrayList.add(Character.valueOf((char) b));
        }
        return CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, new Function1<Character, String>() { // from class: com.pudu.library.loracall.KotlinUtilsKt$toCharString$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(Character ch) {
                return invoke(ch.charValue());
            }

            public final String invoke(char c) {
                return String.valueOf(c) + "";
            }
        }, 31, null);
    }
}
