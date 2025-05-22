package com.pudutech.bumblebee.robot.protocol_utils;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: ProtocolUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R+\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR+\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/protocol_utils/ProtocolUtils;", "", "()V", "TAG", "", "receiveListener", "Lkotlin/Function1;", "Lkotlin/UByteArray;", "", "getReceiveListener", "()Lkotlin/jvm/functions/Function1;", "setReceiveListener", "(Lkotlin/jvm/functions/Function1;)V", "sender", "getSender", "setSender", "sendCANAndWait", "cmd", "waitByte0", "Lkotlin/UByte;", "sendCANAndWait-FaKdhUs", "([BBLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ProtocolUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG = "ProtocolUtils";
    private Function1<? super UByteArray, Unit> receiveListener;
    private Function1<? super UByteArray, Unit> sender;

    public final Function1<UByteArray, Unit> getReceiveListener() {
        return this.receiveListener;
    }

    public final void setReceiveListener(Function1<? super UByteArray, Unit> function1) {
        this.receiveListener = function1;
    }

    public final Function1<UByteArray, Unit> getSender() {
        return this.sender;
    }

    public final void setSender(Function1<? super UByteArray, Unit> function1) {
        this.sender = function1;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: ProtocolUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/protocol_utils/ProtocolUtils$Companion;", "", "()V", "genProtocolUtils", "Lcom/pudutech/bumblebee/robot/protocol_utils/ProtocolUtils;", MqttServiceConstants.SEND_ACTION, "Lkotlin/Function1;", "Lkotlin/UByteArray;", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ProtocolUtils genProtocolUtils(Function1<? super UByteArray, Unit> send) {
            ProtocolUtils protocolUtils = new ProtocolUtils();
            protocolUtils.setSender(send);
            return protocolUtils;
        }
    }

    /* renamed from: sendCANAndWait-FaKdhUs, reason: not valid java name */
    public final Object m4331sendCANAndWaitFaKdhUs(final byte[] bArr, final byte b, Continuation<? super UByteArray> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        int m4578getSizeimpl = 8 - UByteArray.m4578getSizeimpl(bArr);
        byte[] bArr2 = new byte[m4578getSizeimpl];
        for (int i = 0; i < m4578getSizeimpl; i++) {
            Boxing.boxInt(i).intValue();
            bArr2[i] = 0;
        }
        byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(ArraysKt.plus(bArr, UByteArray.m4572constructorimpl(bArr2)));
        setReceiveListener(new Function1<UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.protocol_utils.ProtocolUtils$sendCANAndWait$$inlined$suspendCancellableCoroutine$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UByteArray uByteArray) {
                invoke(uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                if (UByteArray.m4577getimpl(bytes, 0) == b) {
                    if (UByteArray.m4577getimpl(bytes, 7) != UByteArrayUtilsKt.m4332canXORChecksumGBYM_sE(bytes)) {
                        str = this.TAG;
                        Pdlog.m3277w(str, "check xor sum fail. " + UByte.m4563toStringimpl(UByteArray.m4577getimpl(bytes, 7)) + " != " + UByte.m4563toStringimpl(UByteArrayUtilsKt.m4332canXORChecksumGBYM_sE(bytes)));
                        return;
                    }
                    this.setReceiveListener((Function1) null);
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    UByteArray m4570boximpl = UByteArray.m4570boximpl(bytes);
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(m4570boximpl));
                }
            }
        });
        UByteArray.m4582setVurrAj0(m4572constructorimpl, 7, UByteArrayUtilsKt.m4332canXORChecksumGBYM_sE(m4572constructorimpl));
        Function1<UByteArray, Unit> sender = getSender();
        if (sender != null) {
            sender.invoke(UByteArray.m4570boximpl(m4572constructorimpl));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
