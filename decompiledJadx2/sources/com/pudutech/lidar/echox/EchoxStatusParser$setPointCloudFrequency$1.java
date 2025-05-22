package com.pudutech.lidar.echox;

import com.pudutech.base.Pdlog;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: EchoxStatusParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lidar.echox.EchoxStatusParser$setPointCloudFrequency$1", m3970f = "EchoxStatusParser.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class EchoxStatusParser$setPointCloudFrequency$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $newPointCloudFrequency;
    final /* synthetic */ byte[] $originDifopData;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5458p$;
    final /* synthetic */ EchoxStatusParser this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EchoxStatusParser$setPointCloudFrequency$1(EchoxStatusParser echoxStatusParser, int i, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = echoxStatusParser;
        this.$newPointCloudFrequency = i;
        this.$originDifopData = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EchoxStatusParser$setPointCloudFrequency$1 echoxStatusParser$setPointCloudFrequency$1 = new EchoxStatusParser$setPointCloudFrequency$1(this.this$0, this.$newPointCloudFrequency, this.$originDifopData, completion);
        echoxStatusParser$setPointCloudFrequency$1.f5458p$ = (CoroutineScope) obj;
        return echoxStatusParser$setPointCloudFrequency$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EchoxStatusParser$setPointCloudFrequency$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x010e, code lost:
    
        if (r3 == null) goto L26;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0124  */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.net.DatagramSocket] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.net.DatagramSocket] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Map map;
        Map map2;
        byte b;
        int i;
        int i2;
        String str;
        Throwable th;
        DatagramSocket datagramSocket;
        Exception e;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        int i3;
        String str7;
        String str8;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5458p$;
            map = this.this$0.pointCloudFrequencyMap;
            int i4 = 1;
            i4 = 1;
            if (!map.containsKey(Boxing.boxInt(this.$newPointCloudFrequency))) {
                str8 = this.this$0.TAG;
                Pdlog.m3273d(str8, "setPointCloudFrequency: error pointCloudFrequency data");
                return Unit.INSTANCE;
            }
            map2 = this.this$0.pointCloudFrequencyMap;
            byte[] bArr = (byte[]) map2.get(Boxing.boxInt(this.$newPointCloudFrequency));
            byte[] bArr2 = this.$originDifopData;
            b = this.this$0.pointCloudFrequencyCmd;
            bArr2[4] = b;
            byte[] bArr3 = this.$originDifopData;
            i = this.this$0.pointCloudFrequencySetStartPosition;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            bArr3[i] = bArr[0];
            byte[] bArr4 = this.$originDifopData;
            i2 = this.this$0.pointCloudFrequencySetStartPosition;
            bArr4[i2 + 1] = bArr[1];
            str = this.this$0.TAG;
            ?? r3 = {"setPointCloudFrequency start"};
            Pdlog.m3273d(str, r3);
            DatagramSocket datagramSocket2 = (DatagramSocket) null;
            try {
                try {
                    datagramSocket = new DatagramSocket();
                } catch (Exception e2) {
                    datagramSocket = datagramSocket2;
                    e = e2;
                } catch (Throwable th2) {
                    r3 = datagramSocket2;
                    th = th2;
                    str2 = this.this$0.TAG;
                    Object[] objArr = new Object[i4];
                    objArr[0] = "pointCloudFrequencySendSocket close";
                    Pdlog.m3273d(str2, objArr);
                    if (r3 != 0) {
                    }
                    throw th;
                }
                try {
                    str5 = this.this$0.socketIP;
                    InetAddress byName = InetAddress.getByName(str5);
                    str6 = this.this$0.TAG;
                    Pdlog.m3273d(str6, "setPointCloudFrequency: originDifopData size = " + this.$originDifopData.length);
                    byte[] bArr5 = this.$originDifopData;
                    int length = this.$originDifopData.length;
                    i3 = this.this$0.fpgaRequestPort;
                    datagramSocket.send(new DatagramPacket(bArr5, length, byName, i3));
                    str7 = this.this$0.TAG;
                    ?? r2 = {"pointCloudFrequencySendSocket close"};
                    Pdlog.m3273d(str7, r2);
                    i4 = r2;
                    r3 = datagramSocket;
                } catch (Exception e3) {
                    e = e3;
                    str3 = this.this$0.TAG;
                    Pdlog.m3273d(str3, "setPointCloudFrequency: Exception is " + e);
                    e.printStackTrace();
                    str4 = this.this$0.TAG;
                    ?? r22 = {"pointCloudFrequencySendSocket close"};
                    Pdlog.m3273d(str4, r22);
                    i4 = r22;
                    r3 = datagramSocket;
                }
                r3.close();
                return Unit.INSTANCE;
            } catch (Throwable th3) {
                th = th3;
                str2 = this.this$0.TAG;
                Object[] objArr2 = new Object[i4];
                objArr2[0] = "pointCloudFrequencySendSocket close";
                Pdlog.m3273d(str2, objArr2);
                if (r3 != 0) {
                    r3.close();
                }
                throw th;
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
