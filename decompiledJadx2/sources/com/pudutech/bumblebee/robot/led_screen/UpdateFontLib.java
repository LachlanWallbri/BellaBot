package com.pudutech.bumblebee.robot.led_screen;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import com.pudutech.bumblebee.robot.protocol_utils.ProtocolUtils;
import com.pudutech.bumblebee.robot.protocol_utils.UByteArrayUtilsKt;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.UStringsKt;
import kotlinx.coroutines.TimeoutKt;
import org.jetbrains.anko.DimensionsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: UpdateFontLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J+\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010/J\u0019\u00100\u001a\u00020\u000e2\u0006\u00101\u001a\u00020+H\u0082@ø\u0001\u0000¢\u0006\u0002\u00102J\u001a\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00106R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u00020\u000bX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0013\u0010\u001a\u001a\u00020\u000bX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\fR\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR-\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00138FX\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\u0013\u0010$\u001a\u00020\u000bX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\fRC\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00132\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0013@FX\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u00067"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/led_screen/UpdateFontLib;", "", "()V", "ProtocolHead", "Lkotlin/UByte;", "getProtocolHead", "()B", "B", "TAG", "", "crcCheckout", "Lkotlin/UByteArray;", "[B", "<set-?>", "", "needUpdate", "getNeedUpdate", "()Z", "onUpdateEvent", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UpdateEvent;", "", "getOnUpdateEvent", "()Lkotlin/jvm/functions/Function1;", "setOnUpdateEvent", "(Lkotlin/jvm/functions/Function1;)V", "prepareUpdate", "protocolUtils", "Lcom/pudutech/bumblebee/robot/protocol_utils/ProtocolUtils;", "getProtocolUtils", "()Lcom/pudutech/bumblebee/robot/protocol_utils/ProtocolUtils;", "protocolUtils$delegate", "Lkotlin/Lazy;", "receiveListener", "getReceiveListener", "setReceiveListener", "requestVersion", ES6Iterator.VALUE_PROPERTY, "sender", "getSender", "setSender", "doCase", "targetVersion", "", "fileBytes", "", "isForce", "(I[BZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepare", "size", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSegment", "bytes", "sendSegment-GBYM_sE", "([B)V", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class UpdateFontLib {
    private Function1<? super UpdateEvent, Unit> onUpdateEvent;
    private Function1<? super UByteArray, Unit> receiveListener;
    private Function1<? super UByteArray, Unit> sender;
    private final String TAG = "UpdateFontLib";
    private boolean needUpdate = true;

    /* renamed from: protocolUtils$delegate, reason: from kotlin metadata */
    private final Lazy protocolUtils = LazyKt.lazy(new Function0<ProtocolUtils>() { // from class: com.pudutech.bumblebee.robot.led_screen.UpdateFontLib$protocolUtils$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ProtocolUtils invoke() {
            return new ProtocolUtils();
        }
    });
    private final byte[] requestVersion = {-107, 6, 0, 0, 0, 0, 0};
    private final byte[] prepareUpdate = {-107, 1, 0, 0, 0, 0, 0};
    private final byte[] crcCheckout = {-107, 4, 0, 0, 0, 0, 0};
    private final byte ProtocolHead = UByte.m4528constructorimpl((byte) 149);

    /* JADX INFO: Access modifiers changed from: private */
    public final ProtocolUtils getProtocolUtils() {
        return (ProtocolUtils) this.protocolUtils.getValue();
    }

    public final boolean getNeedUpdate() {
        return this.needUpdate;
    }

    public final Function1<UpdateEvent, Unit> getOnUpdateEvent() {
        return this.onUpdateEvent;
    }

    public final void setOnUpdateEvent(Function1<? super UpdateEvent, Unit> function1) {
        this.onUpdateEvent = function1;
    }

    public final byte getProtocolHead() {
        return this.ProtocolHead;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object prepare(int i, Continuation<? super Boolean> continuation) {
        UpdateFontLib$prepare$1 updateFontLib$prepare$1;
        int i2;
        UpdateFontLib updateFontLib;
        byte[] storage;
        if (continuation instanceof UpdateFontLib$prepare$1) {
            updateFontLib$prepare$1 = (UpdateFontLib$prepare$1) continuation;
            if ((updateFontLib$prepare$1.label & Integer.MIN_VALUE) != 0) {
                updateFontLib$prepare$1.label -= Integer.MIN_VALUE;
                Object obj = updateFontLib$prepare$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = updateFontLib$prepare$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3275i(this.TAG, "prepare update font. file size=" + i);
                    UpdateFontLib$prepare$response$1 updateFontLib$prepare$response$1 = new UpdateFontLib$prepare$response$1(this, i, null);
                    updateFontLib$prepare$1.L$0 = this;
                    updateFontLib$prepare$1.I$0 = i;
                    updateFontLib$prepare$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(1000L, updateFontLib$prepare$response$1, updateFontLib$prepare$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    updateFontLib = this;
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i3 = updateFontLib$prepare$1.I$0;
                    updateFontLib = (UpdateFontLib) updateFontLib$prepare$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                UByteArray uByteArray = (UByteArray) obj;
                storage = uByteArray != null ? uByteArray.getStorage() : null;
                if (storage != null) {
                    Pdlog.m3277w(updateFontLib.TAG, "response is null.");
                    return Boxing.boxBoolean(false);
                }
                if (UByteArray.m4577getimpl(storage, 1) != UByte.m4528constructorimpl((byte) 2) || UByteArray.m4577getimpl(storage, 2) != UByte.m4528constructorimpl((byte) DimensionsKt.HDPI)) {
                    Pdlog.m3277w(updateFontLib.TAG, "launch response fail");
                    return Boxing.boxBoolean(false);
                }
                return Boxing.boxBoolean(true);
            }
        }
        updateFontLib$prepare$1 = new UpdateFontLib$prepare$1(this, continuation);
        Object obj2 = updateFontLib$prepare$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = updateFontLib$prepare$1.label;
        if (i2 != 0) {
        }
        UByteArray uByteArray2 = (UByteArray) obj2;
        if (uByteArray2 != null) {
        }
        if (storage != null) {
        }
    }

    /* renamed from: sendSegment-GBYM_sE, reason: not valid java name */
    private final void m4328sendSegmentGBYM_sE(byte[] bytes) {
        List<UByteArray> m4333splitPpDY95g = UByteArrayUtilsKt.m4333splitPpDY95g(bytes, 6);
        Pdlog.m3276v(this.TAG, "sendSegment bytes.size=" + UByteArray.m4578getSizeimpl(bytes) + " split to " + m4333splitPpDY95g.size());
        byte[] bArr = {-107, 3};
        Iterator<T> it = m4333splitPpDY95g.iterator();
        while (it.hasNext()) {
            byte[] storage = ((UByteArray) it.next()).getStorage();
            byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(ArraysKt.plus(bArr, storage));
            int m4578getSizeimpl = (8 - UByteArray.m4578getSizeimpl(storage)) - 2;
            byte[] bArr2 = new byte[m4578getSizeimpl];
            for (int i = 0; i < m4578getSizeimpl; i++) {
                bArr2[i] = 0;
            }
            byte[] m4572constructorimpl2 = UByteArray.m4572constructorimpl(ArraysKt.plus(m4572constructorimpl, UByteArray.m4572constructorimpl(bArr2)));
            Function1<? super UByteArray, Unit> function1 = this.sender;
            if (function1 != null) {
                function1.invoke(UByteArray.m4570boximpl(m4572constructorimpl2));
            }
        }
    }

    public static /* synthetic */ Object doCase$default(UpdateFontLib updateFontLib, int i, byte[] bArr, boolean z, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return updateFontLib.doCase(i, bArr, z, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x02c4, code lost:
    
        r13 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13);
        r0 = ((kotlin.UByteArray) r14).getStorage();
        r13 = r13.intValue();
        r26 = r1;
        r1 = new com.pudutech.bumblebee.robot.led_screen.PuduCRC();
        r27 = r2;
        r2 = java.util.Arrays.copyOf(r0, r0.length);
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r7);
        r1.update(r2);
        r1 = r1.getValue();
        r2 = r10.TAG;
        r28 = r3;
        r3 = new java.lang.StringBuilder();
        r20 = r5;
        r3.append("send segment. index=");
        r3.append(r13);
        r3.append(" size=");
        r3.append(kotlin.UByteArray.m4578getSizeimpl(r0));
        r3.append(" crc=");
        r3.append(r1);
        r3.append(" to hex=");
        r3.append(kotlin.text.UStringsKt.m5472toStringV7xB4Y4(kotlin.UInt.m4595constructorimpl(r1), 16));
        com.pudutech.base.Pdlog.m3273d(r2, r3.toString());
        r3 = r26;
        r2 = r27;
        r0 = r1;
        r1 = r10;
        r26 = r13;
        r5 = r4;
        r10 = r0;
        r4 = 3;
        r21 = 0;
        r19 = r7;
        r13 = r11;
        r11 = 1;
        r7 = r28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01cf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00ca  */
    /* JADX WARN: Type inference failed for: r11v27, types: [java.lang.Iterable] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0313 -> B:12:0x032c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object doCase(int i, byte[] bArr, boolean z, Continuation<? super Unit> continuation) {
        UpdateFontLib$doCase$1 updateFontLib$doCase$1;
        Object coroutine_suspended;
        int i2;
        UpdateFontLib updateFontLib;
        Object obj;
        boolean z2;
        byte[] bArr2;
        byte[] storage;
        UInt m4589boximpl;
        Function1<? super UpdateEvent, Unit> function1;
        Object prepare;
        int i3;
        UInt uInt;
        byte[] bArr3;
        byte[] bArr4;
        boolean z3;
        List<UByteArray> m4333splitPpDY95g;
        List<UByteArray> list;
        Iterator it;
        int i4;
        Object next;
        int i5;
        Object obj2;
        UInt uInt2;
        int i6;
        int i7;
        UpdateFontLib$doCase$1 updateFontLib$doCase$12;
        int i8;
        int i9;
        int i10;
        List<UByteArray> list2;
        int i11;
        byte[] bArr5;
        int i12;
        char c;
        int i13 = i;
        if (continuation instanceof UpdateFontLib$doCase$1) {
            updateFontLib$doCase$1 = (UpdateFontLib$doCase$1) continuation;
            if ((updateFontLib$doCase$1.label & Integer.MIN_VALUE) != 0) {
                updateFontLib$doCase$1.label -= Integer.MIN_VALUE;
                Object obj3 = updateFontLib$doCase$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = updateFontLib$doCase$1.label;
                String str = "java.util.Arrays.copyOf(this, size)";
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj3);
                    if (!this.needUpdate && !z) {
                        return Unit.INSTANCE;
                    }
                    Pdlog.m3275i(this.TAG, "doCase targetVersion=" + i13 + " needForce=" + z);
                    UpdateFontLib$doCase$response$1 updateFontLib$doCase$response$1 = new UpdateFontLib$doCase$response$1(this, null);
                    updateFontLib$doCase$1.L$0 = this;
                    updateFontLib$doCase$1.I$0 = i13;
                    updateFontLib$doCase$1.L$1 = bArr;
                    updateFontLib$doCase$1.Z$0 = z;
                    updateFontLib$doCase$1.label = 1;
                    Object withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(2000L, updateFontLib$doCase$response$1, updateFontLib$doCase$1);
                    if (withTimeoutOrNull == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    updateFontLib = this;
                    obj = withTimeoutOrNull;
                    z2 = z;
                    bArr2 = bArr;
                } else if (i2 == 1) {
                    boolean z4 = updateFontLib$doCase$1.Z$0;
                    bArr2 = (byte[]) updateFontLib$doCase$1.L$1;
                    int i14 = updateFontLib$doCase$1.I$0;
                    UpdateFontLib updateFontLib2 = (UpdateFontLib) updateFontLib$doCase$1.L$0;
                    ResultKt.throwOnFailure(obj3);
                    updateFontLib = updateFontLib2;
                    obj = obj3;
                    z2 = z4;
                    i13 = i14;
                } else if (i2 == 2) {
                    uInt = (UInt) updateFontLib$doCase$1.L$3;
                    bArr4 = (byte[]) updateFontLib$doCase$1.L$2;
                    z3 = updateFontLib$doCase$1.Z$0;
                    bArr3 = (byte[]) updateFontLib$doCase$1.L$1;
                    i3 = updateFontLib$doCase$1.I$0;
                    updateFontLib = (UpdateFontLib) updateFontLib$doCase$1.L$0;
                    ResultKt.throwOnFailure(obj3);
                    if (((Boolean) obj3).booleanValue()) {
                        Function1<? super UpdateEvent, Unit> function12 = updateFontLib.onUpdateEvent;
                        if (function12 != null) {
                            function12.invoke(UpdateEvent.FAIL);
                        }
                        return Unit.INSTANCE;
                    }
                    byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                    m4333splitPpDY95g = UByteArrayUtilsKt.m4333splitPpDY95g(UByteArray.m4572constructorimpl(copyOf), 4096);
                    Pdlog.m3275i(updateFontLib.TAG, "split file to segments for sending. segments size=" + m4333splitPpDY95g.size());
                    list = m4333splitPpDY95g;
                    it = list.iterator();
                    i4 = 0;
                    if (!it.hasNext()) {
                    }
                } else if (i2 == 3) {
                    int i15 = updateFontLib$doCase$1.I$6;
                    int i16 = updateFontLib$doCase$1.I$5;
                    int i17 = updateFontLib$doCase$1.I$4;
                    int i18 = updateFontLib$doCase$1.I$3;
                    int i19 = updateFontLib$doCase$1.I$2;
                    byte[] bArr6 = (byte[]) updateFontLib$doCase$1.L$8;
                    Object obj4 = updateFontLib$doCase$1.L$7;
                    Iterator it2 = (Iterator) updateFontLib$doCase$1.L$6;
                    int i20 = updateFontLib$doCase$1.I$1;
                    ?? r11 = (Iterable) updateFontLib$doCase$1.L$5;
                    List<UByteArray> list3 = (List) updateFontLib$doCase$1.L$4;
                    UInt uInt3 = (UInt) updateFontLib$doCase$1.L$3;
                    byte[] bArr7 = (byte[]) updateFontLib$doCase$1.L$2;
                    boolean z5 = updateFontLib$doCase$1.Z$0;
                    byte[] bArr8 = (byte[]) updateFontLib$doCase$1.L$1;
                    int i21 = updateFontLib$doCase$1.I$0;
                    UpdateFontLib updateFontLib3 = (UpdateFontLib) updateFontLib$doCase$1.L$0;
                    ResultKt.throwOnFailure(obj3);
                    int i22 = i15;
                    Object obj5 = coroutine_suspended;
                    int i23 = i18;
                    int i24 = i16;
                    List<UByteArray> list4 = r11;
                    bArr4 = bArr7;
                    Iterator it3 = it2;
                    i3 = i21;
                    int i25 = i20;
                    byte[] bArr9 = bArr6;
                    Object obj6 = obj4;
                    boolean z6 = z5;
                    String str2 = "java.util.Arrays.copyOf(this, size)";
                    int i26 = i17;
                    UpdateFontLib$doCase$1 updateFontLib$doCase$13 = updateFontLib$doCase$1;
                    UInt uInt4 = uInt3;
                    UByteArray uByteArray = (UByteArray) obj3;
                    byte[] storage2 = uByteArray == null ? uByteArray.getStorage() : null;
                    if (storage2 != null) {
                        i11 = i22;
                        bArr5 = bArr4;
                        Pdlog.m3277w(updateFontLib3.TAG, "check crc. response is null");
                        i12 = 1;
                        c = 0;
                    } else {
                        i11 = i22;
                        bArr5 = bArr4;
                        if (UByteArray.m4577getimpl(storage2, 1) == UByte.m4528constructorimpl((byte) 5) && UByteArray.m4577getimpl(storage2, 2) == UByte.m4528constructorimpl((byte) DimensionsKt.HDPI)) {
                            Pdlog.m3273d(updateFontLib3.TAG, "check crc success");
                            bArr4 = bArr5;
                            updateFontLib = updateFontLib3;
                            uInt = uInt4;
                            updateFontLib$doCase$1 = updateFontLib$doCase$13;
                            z3 = z6;
                            m4333splitPpDY95g = list3;
                            bArr3 = bArr8;
                            coroutine_suspended = obj5;
                            int i27 = 1;
                            int i28 = 1;
                            it = it3;
                            list = list4;
                            i4 = i25;
                            if (i28 != 0) {
                                String str3 = updateFontLib.TAG;
                                Object[] objArr = new Object[i27];
                                objArr[0] = "update font fail in segment index=" + i19 + ". finish";
                                Pdlog.m3274e(str3, objArr);
                                Function1<? super UpdateEvent, Unit> function13 = updateFontLib.onUpdateEvent;
                                if (function13 != null) {
                                    function13.invoke(UpdateEvent.FAIL);
                                }
                                return Unit.INSTANCE;
                            }
                            str = str2;
                            if (!it.hasNext()) {
                                next = it.next();
                                i5 = i4 + 1;
                                if (i4 < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                Integer boxInt = Boxing.boxInt(i4);
                                byte[] storage3 = ((UByteArray) next).getStorage();
                                int intValue = boxInt.intValue();
                                UInt uInt5 = uInt;
                                PuduCRC puduCRC = new PuduCRC();
                                byte[] bArr10 = bArr4;
                                byte[] copyOf2 = Arrays.copyOf(storage3, storage3.length);
                                Intrinsics.checkExpressionValueIsNotNull(copyOf2, str);
                                puduCRC.update(copyOf2);
                                int value = puduCRC.getValue();
                                String str4 = updateFontLib.TAG;
                                List<UByteArray> list5 = m4333splitPpDY95g;
                                StringBuilder sb = new StringBuilder();
                                obj2 = coroutine_suspended;
                                sb.append("send segment. index=");
                                sb.append(intValue);
                                sb.append(" size=");
                                sb.append(UByteArray.m4578getSizeimpl(storage3));
                                sb.append(" crc=");
                                sb.append(value);
                                sb.append(" to hex=");
                                sb.append(UStringsKt.m5472toStringV7xB4Y4(UInt.m4595constructorimpl(value), 16));
                                Pdlog.m3273d(str4, sb.toString());
                                uInt2 = uInt5;
                                bArr4 = bArr10;
                                i6 = value;
                                updateFontLib3 = updateFontLib;
                                i7 = intValue;
                                updateFontLib$doCase$12 = updateFontLib$doCase$1;
                                bArr9 = storage3;
                                i8 = 3;
                                i9 = 0;
                                str2 = str;
                                list4 = list;
                                i10 = 1;
                                list2 = list5;
                                if (i10 > i8) {
                                    updateFontLib3.m4328sendSegmentGBYM_sE(bArr9);
                                    byte[] bArr11 = bArr9;
                                    int i29 = i10;
                                    UpdateFontLib$doCase$$inlined$forEachIndexed$lambda$1 updateFontLib$doCase$$inlined$forEachIndexed$lambda$1 = new UpdateFontLib$doCase$$inlined$forEachIndexed$lambda$1(i6, null, updateFontLib3, updateFontLib$doCase$12);
                                    updateFontLib$doCase$12.L$0 = updateFontLib3;
                                    updateFontLib$doCase$12.I$0 = i3;
                                    updateFontLib$doCase$12.L$1 = bArr3;
                                    updateFontLib$doCase$12.Z$0 = z3;
                                    updateFontLib$doCase$12.L$2 = bArr4;
                                    updateFontLib$doCase$12.L$3 = uInt2;
                                    updateFontLib$doCase$12.L$4 = list2;
                                    updateFontLib$doCase$12.L$5 = list4;
                                    updateFontLib$doCase$12.I$1 = i5;
                                    updateFontLib$doCase$12.L$6 = it;
                                    updateFontLib$doCase$12.L$7 = next;
                                    updateFontLib$doCase$12.L$8 = bArr11;
                                    UpdateFontLib updateFontLib4 = updateFontLib3;
                                    updateFontLib$doCase$12.I$2 = i7;
                                    updateFontLib$doCase$12.I$3 = i9;
                                    updateFontLib$doCase$12.I$4 = i29;
                                    updateFontLib$doCase$12.I$5 = i8;
                                    updateFontLib$doCase$12.I$6 = i6;
                                    int i30 = i6;
                                    updateFontLib$doCase$12.label = 3;
                                    Object withTimeoutOrNull2 = TimeoutKt.withTimeoutOrNull(500L, updateFontLib$doCase$$inlined$forEachIndexed$lambda$1, updateFontLib$doCase$12);
                                    Object obj7 = obj2;
                                    if (withTimeoutOrNull2 == obj7) {
                                        return obj7;
                                    }
                                    obj5 = obj7;
                                    bArr8 = bArr3;
                                    bArr9 = bArr11;
                                    it3 = it;
                                    i23 = i9;
                                    updateFontLib3 = updateFontLib4;
                                    z6 = z3;
                                    list3 = list2;
                                    i25 = i5;
                                    i26 = i29;
                                    updateFontLib$doCase$13 = updateFontLib$doCase$12;
                                    obj6 = next;
                                    i19 = i7;
                                    i24 = i8;
                                    uInt4 = uInt2;
                                    obj3 = withTimeoutOrNull2;
                                    i22 = i30;
                                    UByteArray uByteArray2 = (UByteArray) obj3;
                                    if (uByteArray2 == null) {
                                    }
                                    if (storage2 != null) {
                                    }
                                } else {
                                    i27 = 1;
                                    i19 = i7;
                                    updateFontLib = updateFontLib3;
                                    updateFontLib$doCase$1 = updateFontLib$doCase$12;
                                    list = list4;
                                    i4 = i5;
                                    i28 = i9;
                                    coroutine_suspended = obj2;
                                    uInt = uInt2;
                                    m4333splitPpDY95g = list2;
                                    if (i28 != 0) {
                                    }
                                }
                            } else {
                                Pdlog.m3275i(updateFontLib.TAG, "update font done");
                                updateFontLib.needUpdate = false;
                                Function1<? super UpdateEvent, Unit> function14 = updateFontLib.onUpdateEvent;
                                if (function14 != null) {
                                    function14.invoke(UpdateEvent.SUCCESS);
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        i12 = 1;
                        c = 0;
                        Pdlog.m3277w(updateFontLib3.TAG, "check crc. fail");
                    }
                    String str5 = updateFontLib3.TAG;
                    Object[] objArr2 = new Object[i12];
                    objArr2[c] = "try again";
                    Pdlog.m3277w(str5, objArr2);
                    int i31 = i26 + 1;
                    bArr4 = bArr5;
                    uInt2 = uInt4;
                    i8 = i24;
                    updateFontLib$doCase$12 = updateFontLib$doCase$13;
                    z3 = z6;
                    list2 = list3;
                    bArr3 = bArr8;
                    obj2 = obj5;
                    it = it3;
                    i10 = i31;
                    i6 = i11;
                    i7 = i19;
                    next = obj6;
                    i5 = i25;
                    i9 = i23;
                    if (i10 > i8) {
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                UByteArray uByteArray3 = (UByteArray) obj;
                storage = uByteArray3 == null ? uByteArray3.getStorage() : null;
                UInt uInt6 = (UInt) null;
                if (storage != null) {
                    Pdlog.m3277w(updateFontLib.TAG, "response is null. may be not support font update");
                    updateFontLib.needUpdate = false;
                    m4589boximpl = uInt6;
                } else {
                    m4589boximpl = UInt.m4589boximpl(UByteArrayUtilsKt.m4335toUIntGBYM_sE(UByteArray.m4572constructorimpl(ArraysKt.copyOfRange(storage, 2, 6))));
                }
                Pdlog.m3275i(updateFontLib.TAG, "version=" + m4589boximpl);
                if (m4589boximpl != null && m4589boximpl.getData() == i13) {
                    Pdlog.m3277w(updateFontLib.TAG, "version same. " + m4589boximpl + ". unnecessary update");
                    updateFontLib.needUpdate = false;
                }
                if (updateFontLib.needUpdate && !z2) {
                    Function1<? super UpdateEvent, Unit> function15 = updateFontLib.onUpdateEvent;
                    if (function15 != null) {
                        function15.invoke(UpdateEvent.UNNECESSARY);
                    }
                    return Unit.INSTANCE;
                }
                function1 = updateFontLib.onUpdateEvent;
                if (function1 != null) {
                    function1.invoke(UpdateEvent.UPDATING);
                }
                int length = bArr2.length;
                updateFontLib$doCase$1.L$0 = updateFontLib;
                updateFontLib$doCase$1.I$0 = i13;
                updateFontLib$doCase$1.L$1 = bArr2;
                updateFontLib$doCase$1.Z$0 = z2;
                updateFontLib$doCase$1.L$2 = storage;
                updateFontLib$doCase$1.L$3 = m4589boximpl;
                updateFontLib$doCase$1.label = 2;
                prepare = updateFontLib.prepare(length, updateFontLib$doCase$1);
                if (prepare != coroutine_suspended) {
                    return coroutine_suspended;
                }
                i3 = i13;
                uInt = m4589boximpl;
                bArr3 = bArr2;
                bArr4 = storage;
                z3 = z2;
                obj3 = prepare;
                if (((Boolean) obj3).booleanValue()) {
                }
            }
        }
        updateFontLib$doCase$1 = new UpdateFontLib$doCase$1(this, continuation);
        Object obj32 = updateFontLib$doCase$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = updateFontLib$doCase$1.label;
        String str6 = "java.util.Arrays.copyOf(this, size)";
        if (i2 != 0) {
        }
        UByteArray uByteArray32 = (UByteArray) obj;
        if (uByteArray32 == null) {
        }
        UInt uInt62 = (UInt) null;
        if (storage != null) {
        }
        Pdlog.m3275i(updateFontLib.TAG, "version=" + m4589boximpl);
        if (m4589boximpl != null) {
            Pdlog.m3277w(updateFontLib.TAG, "version same. " + m4589boximpl + ". unnecessary update");
            updateFontLib.needUpdate = false;
        }
        if (updateFontLib.needUpdate) {
        }
        function1 = updateFontLib.onUpdateEvent;
        if (function1 != null) {
        }
        int length2 = bArr2.length;
        updateFontLib$doCase$1.L$0 = updateFontLib;
        updateFontLib$doCase$1.I$0 = i13;
        updateFontLib$doCase$1.L$1 = bArr2;
        updateFontLib$doCase$1.Z$0 = z2;
        updateFontLib$doCase$1.L$2 = storage;
        updateFontLib$doCase$1.L$3 = m4589boximpl;
        updateFontLib$doCase$1.label = 2;
        prepare = updateFontLib.prepare(length2, updateFontLib$doCase$1);
        if (prepare != coroutine_suspended) {
        }
    }

    public final void setReceiveListener(Function1<? super UByteArray, Unit> function1) {
        this.receiveListener = function1;
    }

    public final Function1<UByteArray, Unit> getReceiveListener() {
        return getProtocolUtils().getReceiveListener();
    }

    public final Function1<UByteArray, Unit> getSender() {
        return this.sender;
    }

    public final void setSender(Function1<? super UByteArray, Unit> function1) {
        this.sender = function1;
        getProtocolUtils().setSender(function1);
    }
}
