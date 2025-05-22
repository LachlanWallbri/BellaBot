package com.pudutech.lidar.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BufferUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/lidar/util/BufferUtil;", "", "()V", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BufferUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: BufferUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J&\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0007¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/lidar/util/BufferUtil$Companion;", "", "()V", "containsByteArray", "", "min", "", "max", "getByteBuffer", "Ljava/nio/ByteBuffer;", "byteArray", "getFloatBuffer", "Ljava/nio/FloatBuffer;", "floatArray", "", "getIntBuffer", "Ljava/nio/IntBuffer;", "intArray", "", "toHexString", "", "src", "start", "", "end", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        public final String toHexString(byte[] bArr) {
            return toHexString$default(this, bArr, 0, 0, 6, null);
        }

        public final String toHexString(byte[] bArr, int i) {
            return toHexString$default(this, bArr, i, 0, 4, null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ByteBuffer getByteBuffer(byte[] byteArray) {
            Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(byteArray.length);
            allocateDirect.put(byteArray);
            allocateDirect.position(0);
            Intrinsics.checkExpressionValueIsNotNull(allocateDirect, "byteBuffer.apply {\n     …position(0)\n            }");
            return allocateDirect;
        }

        public final IntBuffer getIntBuffer(int[] intArray) {
            Intrinsics.checkParameterIsNotNull(intArray, "intArray");
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(intArray.length * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            IntBuffer asIntBuffer = allocateDirect.asIntBuffer();
            asIntBuffer.put(intArray);
            asIntBuffer.position(0);
            Intrinsics.checkExpressionValueIsNotNull(asIntBuffer, "byteBuffer.asIntBuffer()…osition(0)\n\n            }");
            return asIntBuffer;
        }

        public final FloatBuffer getFloatBuffer(float[] floatArray) {
            Intrinsics.checkParameterIsNotNull(floatArray, "floatArray");
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(floatArray.length * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
            asFloatBuffer.put(floatArray);
            asFloatBuffer.position(0);
            Intrinsics.checkExpressionValueIsNotNull(asFloatBuffer, "byteBuffer.asFloatBuffer…position(0)\n            }");
            return asFloatBuffer;
        }

        public static /* synthetic */ String toHexString$default(Companion companion, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                if (bArr == null) {
                    Intrinsics.throwNpe();
                }
                i2 = bArr.length;
            }
            return companion.toHexString(bArr, i, i2);
        }

        public final String toHexString(byte[] src, int start, int end) {
            return src == null ? "" : CollectionsKt.joinToString$default(UByteArray.m4570boximpl(UByteArray.m4572constructorimpl(src)), " ", null, null, 0, null, new Function1<UByte, String>() { // from class: com.pudutech.lidar.util.BufferUtil$Companion$toHexString$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ String invoke(UByte uByte) {
                    return invoke(uByte.getData());
                }

                public final String invoke(byte b) {
                    return StringsKt.padStart(UStringsKt.m5471toStringLxnNnR4(b, 16), 2, '0');
                }
            }, 30, null);
        }

        public final boolean containsByteArray(byte[] min, byte[] max) {
            Intrinsics.checkParameterIsNotNull(min, "min");
            Intrinsics.checkParameterIsNotNull(max, "max");
            ArrayList arrayList = new ArrayList();
            int length = min.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                byte b = min[i];
                if (ArraysKt.indexOf(max, b) > -1) {
                    arrayList.add(Byte.valueOf(b));
                }
                i++;
            }
            return arrayList.size() == min.length;
        }
    }
}
